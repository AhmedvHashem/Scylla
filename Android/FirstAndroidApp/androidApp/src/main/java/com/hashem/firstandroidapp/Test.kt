package com.hashem.firstandroidapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.concurrent.atomic.AtomicBoolean

// --- Enum with 3 parameters, only one populated per value ---
enum class UserType(
    val adminLevel: Int? = null,
    val editorLevel: Int? = null,
    val viewerLevel: Int? = null
) {
    ADMIN(adminLevel = 3),
    EDITOR(editorLevel = 2),
    VIEWER(viewerLevel = 1),
    GUEST()
}

// --- User model ---
data class User(
    val id: String,
    val name: String,
    val type: UserType
)

// --- Repository ---
class UserRepository {
    private val fakeDb = mutableMapOf<String, User>()
    suspend fun getUser(id: String): User {
        // // replaced Thread.sleep(1000) with delay for non-blocking
        delay(1000)
        return fakeDb[id] ?: User(id, "Anonymous", UserType.GUEST)
    }

    // // added saveToken which was missing but called in ViewModel
    fun saveToken(token: String) {
        TOKEN = token
    }

    companion object {
        var TOKEN: String? = null
        var LAST_FETCH: Long = 0
    }

    val userCache = mutableListOf<User>()
    val userCount: Int
        get() = userCache.size
}
// for repository would be better if we depend on interface instead of concret impl, for scaling and testing poproses. refer to our project structure docs on conflunce

// --- Sealed UI State ---
sealed interface UserUiState {
    object Loading : UserUiState
    data class Success(val user: List<User>) : UserUiState
    data class Error(val message: String) : UserUiState
}

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    // // replaced MutableSharedFlow(replay = 1) with MutableStateFlow for standard UI state management
    private val _uiState = MutableStateFlow<UserUiState>(UserUiState.Loading)
    val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()
    fun exposeUiState() = _uiState
    private val isFetching = AtomicBoolean(false)
    val totalUsers: Int
        get() = repository.userCache.size

    val tempUsers = mutableListOf<User>()
    
    init {
        // // commented out invalid fetchUser(user.id) as user is not defined in scope
        // fetchUser(user.id)
    }
    
    fun fetchUser(userId: String) {
        if (isFetching.get()) return
        isFetching.set(true)
        // // replaced GlobalScope with viewModelScope for proper coroutine lifecycle management
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.emit(UserUiState.Loading)
            val user = repository.getUser(userId)
            tempUsers.add(user)
            _uiState.emit(UserUiState.Success(tempUsers.toList()))
            UserRepository.LAST_FETCH = System.currentTimeMillis()
            isFetching.set(false)
        }
    }

    fun login(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.emit(UserUiState.Loading)
            when (username) {
                "admin" -> {
                    repository.saveToken("token_admin")
                    _uiState.emit(
                        UserUiState.Success(
                            listOf(
                                User(
                                    "1", "Admin",
                                    UserType.ADMIN
                                )
                            )
                        )
                    )
                }

                "editor" -> {
                    repository.saveToken("token_editor")
                    _uiState.emit(
                        UserUiState.Success(
                            listOf(
                                User(
                                    "2", "Editor",
                                    UserType.EDITOR
                                )
                            )
                        )
                    )
                }

                "viewer" -> {
                    repository.saveToken("token_viewer")
                    _uiState.emit(
                        UserUiState.Success(
                            listOf(
                                User(
                                    "3", "Viewer",
                                    UserType.VIEWER
                                )
                            )
                        )
                    )
                }

                else -> _uiState.emit(UserUiState.Error("Unknown user"))
            }

        }
    }

    fun observeUiState() {
        // // replaced GlobalScope with viewModelScope and Thread.sleep with delay
        viewModelScope.launch(Dispatchers.IO) {
            uiState.collect { state ->
                println("Observed state: $state")
                delay(300)
            }
        }
    }

    fun addUsers(users: List<User>) {
        // // replaced GlobalScope.launch loop with direct list modification (synchronous is safer for simple list add)
        tempUsers.addAll(users)
        _uiState.value = UserUiState.Success(tempUsers.toList())
    }
}

@Composable
fun UserListView(viewModel: UserViewModel) {
    // // changed selectedUser to String to match user names
    var selectedUser by remember { mutableStateOf<String?>(null) }
    // // collect uiState correctly using collectAsState
    val uiState by viewModel.uiState.collectAsState()
    
    // // use a list derived from state
    val users = (uiState as? UserUiState.Success)?.user?.map { it.name } ?: emptyList()

    val selectedCount = users.count { it == selectedUser }
    
    Column {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(users, key = { it }) { user ->
                UserItem(userName = user, selected = selectedUser == user) {
                    selectedUser = user
                }
            }
        }
        Text(
            text = "Selected count: $selectedCount", modifier =
                Modifier.padding(8.dp)
        )
    }
}

@Composable
fun UserItem(userName: String, selected: Boolean, onClick: () -> Unit) {
    // // corrected background color calculation to respond to selection changes
    val backgroundColor = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
            .height(48.dp)
            // // added background modifier
            .background(backgroundColor)
    ) {
        Text(
            text = userName,
            color = if (selected) MaterialTheme.colorScheme.onPrimary else
                MaterialTheme.colorScheme.onSurface
        )
    }
}
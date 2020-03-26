package com.ahmednts.scylla.repository.impl2

import io.reactivex.Flowable

class User

interface IUserRepository {

  fun getUser(userId: String): Flowable<User>
}

// this interface's place is in the domain layer and domain layer should not know where the data come from, so this should not exist
interface IUserLocalRepository : IUserRepository {
  fun setUser(user: User)
}

class RemoteUserRepo(retrofit: String) : IUserRepository {

  override fun getUser(userId: String): Flowable<User> {
    TODO("not implemented")
  }
}

class LocalUserRepo(db: String) : IUserLocalRepository {

  override fun getUser(userId: String): Flowable<User> {
    TODO("not implemented")
  }

  override fun setUser(user: User) {
    TODO("not implemented")
  }
}

class DefaultUserRepo(val remote: IUserRepository, val local: IUserLocalRepository) : IUserLocalRepository {

  override fun getUser(userId: String): Flowable<User> {
    TODO("not implemented")
  }

  override fun setUser(user: User) {
    TODO("not implemented")
  }
}

class GetUserUseCase(private val repository: IUserRepository) {
  fun getUser(userId: String) = repository.getUser(userId)
}

class SetUserUseCase(private val repository: IUserLocalRepository) {
  fun setUser(user: User) = repository.setUser(user)
}

fun main() {

  val remoteDS = "retrofit"
  val remoteRepo = RemoteUserRepo(remoteDS)

  val localDS = "realmDB"
  val localRepo = LocalUserRepo(localDS)

  val repo = DefaultUserRepo(remoteRepo, localRepo)

  GetUserUseCase(remoteRepo).getUser("")
  GetUserUseCase(localRepo).getUser("")
  GetUserUseCase(repo).getUser("")

//  not need cuz setUser() doesnt exist in remoteRepo
//  SetUserUseCase(remoteRepo).setUser(User())
  SetUserUseCase(localRepo).setUser(User())
  SetUserUseCase(repo).setUser(User())
}

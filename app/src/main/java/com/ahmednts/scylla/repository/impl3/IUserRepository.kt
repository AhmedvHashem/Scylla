package com.ahmednts.scylla.repository.impl3

import io.reactivex.Flowable

class User

interface IUserRepository {

  fun getUser(userId: String): Flowable<User>

  fun setUser(user: User)
}

class DefaultUserRepo(retrofit: String, db: String) : IUserRepository {

  override fun getUser(userId: String): Flowable<User> {
    TODO("not implemented")
  }

  override fun setUser(user: User) {
    TODO("not implemented")
  }
}

class RemoteUserRepo(retrofit: String) : IUserRepository {

  override fun getUser(userId: String): Flowable<User> {
    TODO("not implemented")
  }

  //useless
  override fun setUser(user: User) {
    TODO("not implemented")
  }
}

class LocalUserRepo(db: String) : IUserRepository {

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

class SetUserUseCase(private val repository: IUserRepository) {
  fun setUser(user: User) = repository.setUser(user)
}

fun main() {

  val remoteDS = "retrofit"
  val remoteRepo = RemoteUserRepo(remoteDS)

  val localDS = "realmDB"
  val localRepo = LocalUserRepo(localDS)

  val repo = DefaultUserRepo(remoteDS, localDS)

  GetUserUseCase(remoteRepo).getUser("")
  GetUserUseCase(localRepo).getUser("")
  GetUserUseCase(repo).getUser("")

//  not need cuz setUser() is useless in remoteRepo
//  SetUserUseCase(remoteRepo).setUser(User())
  SetUserUseCase(localRepo).setUser(User())
  SetUserUseCase(repo).setUser(User())
}

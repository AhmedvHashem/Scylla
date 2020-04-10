package com.ahmednts.scylla.repository.impl1

import io.reactivex.Flowable

class User

interface IUserRepository {

  fun getUser(userId: String): Flowable<User>

  fun setUser(user: User)
}

class RemoteUserRepo(retrofit: String) : IUserRepository {

  override fun getUser(userId: String): Flowable<User> {
    TODO("Implementation goes here")
  }

  //useless
  override fun setUser(user: User) {
    TODO("not implemented")
  }
}

class LocalUserRepo(db: String) : IUserRepository {

  override fun getUser(userId: String): Flowable<User> {
    TODO("Implementation goes here")
  }

  override fun setUser(user: User) {
    TODO("Implementation goes here")
  }
}

class DefaultUserRepo(val remote: IUserRepository, val local: IUserRepository) : IUserRepository {

  override fun getUser(userId: String): Flowable<User> {
    TODO("Implementation goes here")
  }

  override fun setUser(user: User) {
    TODO("Implementation goes here")
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

  val repo = DefaultUserRepo(remoteRepo, localRepo)

  GetUserUseCase(remoteRepo).getUser("")
  GetUserUseCase(localRepo).getUser("")
  GetUserUseCase(repo).getUser("")

//  not need cuz setUser() is useless in remoteRepo
//  SetUserUseCase(remoteRepo).setUser(User())
  SetUserUseCase(localRepo).setUser(User())
  SetUserUseCase(repo).setUser(User())
}

package com.ahmednts.scylla.repository.impl0

import io.reactivex.Flowable

class User

interface IUserRepository {

  fun getUser(userId: String): Flowable<User>

  //Violates architecture rule: where a repository should not declare where the data come from
  fun getRemoteUser(userId: String): User

  //Violates architecture rule: where a repository should not declare where the data come from
  fun getLocalUser(userId: String): User

  fun setUser(user: User)
}

class DefaultUserRepo(retrofit: String, db: String) : IUserRepository {

  override fun getUser(userId: String): Flowable<User> {
    TODO("not implemented")
  }

  override fun getRemoteUser(userId: String): User {
    TODO("not implemented")
  }

  override fun getLocalUser(userId: String): User {
    TODO("not implemented")
  }

  override fun setUser(user: User) {
    TODO("not implemented")
  }
}

class GetUserUseCase(private val repository: IUserRepository) {
  fun getUser(userId: String) = repository.getUser(userId)
  fun getRemoteUser(userId: String) = repository.getRemoteUser(userId)
  fun getLocalUser(userId: String) = repository.getLocalUser(userId)
}

class SetUserUseCase(private val repository: IUserRepository) {
  fun setUser(user: User) = repository.setUser(user)
}

fun main() {

  val remoteDS = "retrofit"
  val localDS = "realmDB"

  val repo = DefaultUserRepo(remoteDS, localDS)

  GetUserUseCase(repo).getUser("")
  GetUserUseCase(repo).getLocalUser("")
  GetUserUseCase(repo).getRemoteUser("")

  SetUserUseCase(repo).setUser(User())
}

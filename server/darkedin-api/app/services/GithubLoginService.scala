package services

import domain.models.User
import infra.repositories.api.GitHubRepositoryImpl
import infra.repositories.db.UserRepositoryImpl
import javax.inject.{Inject, Singleton}

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class GithubLoginService @Inject()(
  gitHubRepository: GitHubRepositoryImpl,
  userRepository: UserRepositoryImpl
)(
  implicit ec: ExecutionContext
) {

  def execLogin(code: String): Future[User] =
    for {
      token <- gitHubRepository
        .getAuthToken(code)
        .map(_.right.get)
      user  <- gitHubRepository
        .getUserInfo(token)
        .map(_.right.get)
      _ <- userRepository.save(user)
    } yield user

  def esTest(): Future[Unit] =
    gitHubRepository.esTest()
}

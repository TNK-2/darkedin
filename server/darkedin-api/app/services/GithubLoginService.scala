package services

import domain.models.User
import infra.repositories.api.GitHubRepositoryImpl
import javax.inject.Inject

import scala.concurrent.{ExecutionContext, Future}

class GithubLoginService @Inject()(
  gitHubRepository: GitHubRepositoryImpl
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
    } yield user

  def esTest(): Future[Unit] =
    gitHubRepository.esTest()
}

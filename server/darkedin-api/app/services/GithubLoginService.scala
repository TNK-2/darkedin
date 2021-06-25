package services

import domain.models.User
import infra.repositories.api.GitHubRepositoryImpl
import javax.inject.Inject

import scala.concurrent.Future

class GithubLoginService @Inject()(
  gitHubRepository: GitHubRepositoryImpl
) {

  def execLogin(): Future[User] = ???

  def esTest(): Future[Unit] =
    gitHubRepository.esTest()
}

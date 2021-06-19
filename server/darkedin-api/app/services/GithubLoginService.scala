package services

import domain.models.User
import domain.repositories.GitHubRepository
import javax.inject.Inject

import scala.concurrent.Future

class GithubLoginService @Inject()(
  gitHubRepository: GitHubRepository
) {

  def execLogin(): Future[User] = ???
}

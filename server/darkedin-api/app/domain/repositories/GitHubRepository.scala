package domain.repositories

import domain.models.GitHubUser
import domain.models.exception.{InvalidCode, InvalidGitUser}

import scala.concurrent.Future

trait GitHubRepository {
  def getAuthToken(code: String): Future[Either[InvalidCode, String]]
  def getUserInfo(authToken: String): Future[Either[InvalidGitUser, GitHubUser]]
  def esTest(): Future[Unit]
}

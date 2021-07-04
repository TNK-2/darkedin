package domain.repositories

import domain.models.User
import domain.models.exception.{InvalidCode, InvalidGitUser}

import scala.concurrent.Future

trait GitHubRepository {
  def getAuthToken(code: String): Future[Either[InvalidCode, String]]
  def getUserInfo(authToken: String): Future[Either[InvalidGitUser, User]]
  def esTest(): Future[Unit]
}

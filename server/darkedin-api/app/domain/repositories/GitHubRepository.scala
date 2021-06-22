package domain.repositories

import domain.models.User

import scala.concurrent.Future

trait GitHubRepository {
  def getAuthToken(code: String): Future[Option[String]]
  def getUserInfo(authToken: String): Future[Option[User]]
}

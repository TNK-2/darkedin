package domain.repositories

import scala.concurrent.Future

trait GitHubRepository {
  def getAuthToken(code: String): Future[String]
  def getUserInfo(authToken: String)
}

package domain.repositories

import domain.models.User

import scala.concurrent.Future

trait UserRepository {
  def findById(id: String): Future[Option[String]]
  def save(user: User): Future[Unit]
}

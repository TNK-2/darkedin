package domain.repositories

import domain.models.User

import scala.concurrent.Future

trait UserRepository {
  def findById(id: Int): Future[Option[User]]
  def save(user: User): Future[Int]
}

package domain.repositories

import domain.models.User

trait UserRepository {
  def findById(id: String)
  def save(user: User)
}

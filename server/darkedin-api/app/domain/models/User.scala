package domain.models

import akka.http.scaladsl.model.DateTime

case class User(
  val id:Int,
  val name: String,
  val gitUrl: String,
  val avatarUrl: String,
  val accessToken: String,
  val tokenExpiredAt: DateTime,
  val createdAt: DateTime,
  val updatedAt: DateTime
)


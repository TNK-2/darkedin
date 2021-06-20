package domain.models

import org.joda.time.DateTime

case class User(
  id:Int,
  name: String,
  gitUrl: String,
  gitName: String,
  avatarUrl: String,
  accessToken: String,
  tokenExpiredAt: DateTime,
  createdAt: DateTime,
  updatedAt: DateTime
)


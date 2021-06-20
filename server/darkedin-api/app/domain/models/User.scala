package domain.models

import java.time.LocalDateTime

case class User(
  id: Int,
  name: String,
  gitUrl: String,
  gitName: String,
  avatarUrl: String,
  accessToken: String,
  tokenExpiredAt: LocalDateTime,
  createdAt: LocalDateTime,
  updatedAt: LocalDateTime
)


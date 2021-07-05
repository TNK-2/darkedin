package domain.models.exception.user

import domain.models.exception.NotFound

case class UserNotFound(message: String, respString: String)
object UserNotFound extends NotFound

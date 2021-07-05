package domain.models.exception.auth

import domain.models.exception.AuthError

case class InvalidCode(message: String, respString: String)

object InvalidCode extends AuthError

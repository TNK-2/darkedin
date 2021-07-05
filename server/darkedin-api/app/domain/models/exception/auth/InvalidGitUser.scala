package domain.models.exception.auth

import domain.models.exception.AuthError

case class InvalidGitUser(message: String, respString: String)

object InvalidGitUser extends AuthError

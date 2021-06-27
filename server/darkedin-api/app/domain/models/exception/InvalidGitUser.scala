package domain.models.exception

case class InvalidGitUser(message: String, respString: String)

object InvalidGitUser extends AuthError

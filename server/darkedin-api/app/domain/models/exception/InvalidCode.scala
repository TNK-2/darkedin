package domain.models.exception

case class InvalidCode(message: String, respString: String)

object InvalidCode extends AuthError

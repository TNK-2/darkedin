package conf

import javax.inject.{Inject, Singleton}
import play.api.{ Configuration => PlayConfiguration }

@Singleton
class ApplicationConf @Inject() (val config: PlayConfiguration) extends ApplicationConfBase {

}

trait ApplicationConfBase {
  val config: PlayConfiguration
  private def getString(key: String): Option[String] = config.getOptional[String](key)

  val githubAuthUrl: String = getString("auth.github.url").get
  val githubClientId: String = getString("auth.github.client_id").get
  val githubClientSecret: String = getString("auth.github.client_secret").get
  def getGitAuthPath: String = s"$githubAuthUrl?client_id=$githubClientId"
}

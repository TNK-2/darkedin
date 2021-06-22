package infra.repositories.api

import conf.ApplicationConf
import domain.models.User
import domain.repositories.GitHubRepository
import javax.inject.Inject
import play.api.libs.ws.WSClient

import scala.concurrent.{ExecutionContext, Future}

class GitHubRepositoryImpl @Inject()(
  ws: WSClient,
  appConf: ApplicationConf
)(
  implicit ec: ExecutionContext
) extends GitHubRepository {

  override def getAuthToken(code: String): Future[Option[String]] = {
    val query =
      s"""
         |{
         |  "client_id":"${appConf.githubClientId}",
         |  "client_secret":"${appConf.githubClientSecret}",
         |  "code":"${code}"
         |}
         |""".stripMargin

    ws.url(appConf.gitAccessTokenPath)
      .withHttpHeaders("Content-Type" -> "application/json")
      .post(query)
      .map{ resp =>
        resp.body.split("&").headOption match {
          case Some(str) if str.contains("access_token=") =>
            Some(resp.body.split("=").last)
          case _ => None
        }
      }
  }

  override def getUserInfo(authToken: String): Future[Option[User]] = ???
}

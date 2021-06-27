package infra.repositories.api

import conf.ApplicationConf
import domain.models.GitHubUser
import domain.models.exception.InvalidCode
import domain.repositories.GitHubRepository
import javax.inject.Inject
import play.api.Logger
import play.api.libs.ws.{WSClient, WSResponse}
import play.api.libs.ws.ahc.AhcCurlRequestLogger
import utils.aws.Aws4RequestSigner

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

class GitHubRepositoryImpl @Inject()(
  ws: WSClient,
  appConf: ApplicationConf
)(
  implicit ec: ExecutionContext
) extends GitHubRepository {

  private val logger = Logger(this.getClass)

  override def getAuthToken(code: String): Future[Either[InvalidCode, String]] = {
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
      .map { implicit resp =>
        this.statusCheck
        resp.body.split("&").headOption match {
          case Some(str) if str.contains("access_token=") =>
            Right(resp.body.split("=").last)
          case _ =>
            Left(InvalidCode("不正なレスポンス", resp.body))
        }
      }
  }

  override def getUserInfo(authToken: String): Future[Either[InvalidCode, GitHubUser]] = {
    ws.url("https://api.github.com/user")
      .withHttpHeaders("Authorization" -> s"token ${authToken}")
      .get()
      .map { implicit resp =>
        this.statusCheck
        logger.info(s"github user: ${resp.body}")
        Right(GitHubUser())
      }
  }

  override def esTest(): Future[Unit] = {
    val query =
      s"""
         |{
         |  "query": {
         |    "bool": {
         |      "must": [
         |        {
         |          "term": {
         |            "applicationId": "xxxxxxxxxxxx"
         |          }
         |        },
         |        {
         |          "term": {
         |            "corporateId": "xxxxxxxxxxxx"
         |          }
         |        }
         |      ]
         |    }
         |  }
         |}
         |""".stripMargin

    val wsRequest = ws.url("https://search-es-test-cq3beybd7aeyx642aghd7eqh6m.ap-northeast-1.es.amazonaws.com:443/applications-v6.3.1.0/_search")
      .withMethod("POST")
      .withHttpHeaders(
        "Content-Type" -> "application/json",
        "Host" -> "search-es-test-cq3beybd7aeyx642aghd7eqh6m.ap-northeast-1.es.amazonaws.com"
      )

    Aws4RequestSigner.withAws4HeadersForESS(wsRequest, Some(query))
      .withRequestFilter(AhcCurlRequestLogger())
      .post(query)
      .map { resp => logger.warn(s"ess wsclient result... status:${resp.status}, ${resp.statusText}... body:${resp.body}... header:${resp.headers.toString}... underlying:${resp.underlying.toString}") }
  }

  private def statusCheck(implicit wsResponse: WSResponse) =
    if (wsResponse.status < 300) throw new RuntimeException("トークン取得API失敗")

}

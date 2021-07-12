package infra.repositories.api

import conf.ApplicationConf
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.mockito.MockitoSugar
import play.api.libs.ws.WSClient

import scala.concurrent.ExecutionContext

class GitHubRepositoryImplSpec extends PlaySpec with MockitoSugar {

  val repo = new GitHubRepositoryImpl(
    mock[WSClient],
    mock[ApplicationConf]
  )(mock[ExecutionContext])

  "token api returns correct response" must {
    "extract access token correctly" in {
      val mockRespBody = "access_token=DUMMY_z0pKUJnolEEwv&scope=&token_type=bearer"
      val expected = Right("DUMMY_z0pKUJnolEEwv")
      val actual = repo.extractToken(mockRespBody)
      actual mustBe expected
    }
  }
}

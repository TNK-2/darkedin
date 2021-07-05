package controllers

import conf.ApplicationConf
import javax.inject.{Inject, Singleton}
import play.api.Logger
import play.api.mvc.{AnyContent, BaseController, ControllerComponents, Request}
import play.api.libs.json.{Json, Reads}
import services.GithubLoginService

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class LoginController  @Inject()(
  val controllerComponents: ControllerComponents,
  val githubLoginService: GithubLoginService,
  val appConf: ApplicationConf
)(
  implicit ec: ExecutionContext
) extends BaseController {

  private val logger = Logger(this.getClass)

  def index() = Action { implicit req: Request[AnyContent] =>
    logger.debug(s"redirect url : ${appConf.gitAuthPath} :: LoginController.index")
    Redirect(appConf.gitAuthPath)
  }

  implicit val rds = Json.reads[ExecLoginRequest]
  case class ExecLoginRequest(code: String)

  def execLogin() =  Action.async(parse.json) { implicit req =>
    val execLoginReq = req.body.validate[ExecLoginRequest].get
    githubLoginService.execLogin(execLoginReq.code).map { user =>
      Ok(user.accessToken.get)
    }
  }

  def esTest() = Action { implicit req: Request[AnyContent] =>
    // githubLoginService.esTest()
    Ok
  }
}

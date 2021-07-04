package controllers

import conf.ApplicationConf
import javax.inject.{Inject, Singleton}
import play.api.Logger
import play.api.libs.json.{Reads, __}
import play.api.mvc.{AnyContent, BaseController, ControllerComponents, Request}
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

  def execLogin() =  Action.async(parse.json) { req =>
    val code = req.getQueryString("code").get
    githubLoginService.execLogin(code).map { user =>
      Ok(user.accessToken.get)
    }
  }

  def esTest() = Action { implicit req: Request[AnyContent] =>
    // githubLoginService.esTest()
    Ok
  }
}

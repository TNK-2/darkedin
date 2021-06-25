package controllers

import conf.ApplicationConf
import javax.inject.{Inject, Singleton}
import play.api.Logger
import play.api.mvc.{AnyContent, BaseController, ControllerComponents, Request}
import services.GithubLoginService

@Singleton
class LoginController  @Inject()(
  val controllerComponents: ControllerComponents,
  val githubLoginService: GithubLoginService,
  val appConf: ApplicationConf
) extends BaseController {

  private val logger = Logger(this.getClass)

  def index() = Action { implicit request: Request[AnyContent] =>
    logger.debug(s"redirect url : ${appConf.gitAuthPath} :: LoginController.index")
    Redirect(appConf.gitAuthPath)
  }

  def execLogin() =  Action { implicit request: Request[AnyContent] =>
    ???
  }

  def esTest() = Action { implicit request: Request[AnyContent] =>
    githubLoginService.esTest()
    Ok
  }
}

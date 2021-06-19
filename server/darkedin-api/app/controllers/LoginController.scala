package controllers

import conf.ApplicationConf
import javax.inject.{Inject, Singleton}
import play.api.Logger
import play.api.mvc.{AnyContent, BaseController, ControllerComponents, Request}

@Singleton
class LoginController  @Inject()(
  val controllerComponents: ControllerComponents,
  val appConf: ApplicationConf
) extends BaseController {

  private val logger = Logger(this.getClass)

  def index() = Action { implicit request: Request[AnyContent] =>
    logger.debug(s"redirect url : ${appConf.getGitAuthPath} :: LoginController.index")
    Redirect(appConf.getGitAuthPath)
  }

  def execLogin() =  Action { implicit request: Request[AnyContent] =>

  }
}

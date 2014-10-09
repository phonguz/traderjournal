package controllers

import play.api.mvc.{Action, Controller}

object MainController extends Controller with securesocial.core.SecureSocial {

  def index = SecuredAction {
    Ok(views.html.index.render("Hello from Scala"))
  }

}
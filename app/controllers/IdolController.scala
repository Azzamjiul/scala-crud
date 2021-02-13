package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import models.{Idol}
import services.IdolService
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class IdolController @Inject()(val controllerComponents: ControllerComponents, val idolService: IdolService) extends BaseController {

    // def index = Action.async { implicit request =>
    //     Ok("tes tes")
    // }

    def index = Action.async { implicit request =>
        idolService.listAllIdols map { idols =>
            Ok(views.html.idols(idols))
        }
    }
}

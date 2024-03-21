package controllers 
import javax.inject._

import shared.SharedMessages
import play.api.mvc._
import models.TaskListInMemoryModel
import play.api.libs.json.Json
import models._

@Singleton 
class TaskList4 @Inject()(cc:ControllerComponents) extends AbstractController(cc) {
    def load = Action{ implicit request=>
        Ok(views.html.version4Main())
    }

}
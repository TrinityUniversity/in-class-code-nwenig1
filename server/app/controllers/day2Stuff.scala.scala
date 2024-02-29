package controllers


import javax.inject._
import play.api.mvc._
import shared.SharedMessages


class day2Stuff @Inject()(cc:ControllerComponents) extends AbstractController(cc) {
  
    def day2 = Action {  implicit request=>
        Ok(views.html.day2Stuff())
    }
    def showName = Action{ implicit request =>
        val postVals = request.body.asFormUrlEncoded
        postVals.map { args =>
            val username = args("username").head 
            if(models.day2StuffMemoryModel.nameExists(username)){
                Redirect(routes.day2Stuff.next).withSession("username" -> username).flashing("success" ->s"logged in as $username")
            } else{
                Redirect(routes.day2Stuff.day2).flashing("error" -> "Nuh uh")
            }

        }.getOrElse(Redirect(routes.day2Stuff.day2).flashing("error" -> "Naw fam"))
    }
   def next = Action { implicit request =>
    Ok(views.html.next())
   }
   def logout2 = Action { implicit request =>
    Ok(views.html.day2Stuff())

   }
}

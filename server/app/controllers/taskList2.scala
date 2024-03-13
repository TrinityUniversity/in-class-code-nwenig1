package controllers 

import javax.inject._

import shared.SharedMessages
import play.api.mvc._
import models.TaskListInMemoryModel


@Singleton 
class taskList2 @Inject()(cc:ControllerComponents) extends AbstractController(cc) {

  def load2 = Action { implicit request =>
     Ok(views.html.version2Main())
}
  def login = Action {
    Ok(views.html.login2())
  }

  def validate() = Action{ implicit request=>
   val postVals = request.body.asFormUrlEncoded
        postVals.map { args =>
            val username=args("username").head 
            val password=args("password").head 
            if(TaskListInMemoryModel.validateUser(username, password)){
            Ok(views.html.taskList2(TaskListInMemoryModel.getTasks(username)))
            .withSession("username" -> username, "csrfToken" -> play.filters.csrf.CSRF.getToken.get.value); 
            }else{
              Ok(views.html.login2())
            }
            }.getOrElse(Ok(views.html.login2()))
          }
def createUser() = Action { implicit request =>
 val postVals = request.body.asFormUrlEncoded
        postVals.map { args =>
            val username=args("username").head 
            val password=args("password").head 
            if(TaskListInMemoryModel.createUser(username, password)){
            Ok(views.html.taskList2(TaskListInMemoryModel.getTasks(username))).withSession("username" -> username)
            }else{
              Ok(views.html.login2())
            }
            }.getOrElse(Ok(views.html.login2()))
}
def deleteTask(index : Int) = Action { implicit request =>
        val usernameOption=request.session.get("username")
        usernameOption.map { username =>
        TaskListInMemoryModel.removeTask(username, index); 
        Ok(views.html.taskList2(TaskListInMemoryModel.getTasks(username)))
    }.getOrElse(Ok(views.html.login2()))
}
def addTask() = Action{ implicit request=>
    val usernameOption=request.session.get("username")
        usernameOption.map { username =>
            val postVals=request.body.asFormUrlEncoded
            postVals.map{ args =>
            val task=args("newTask").head
        TaskListInMemoryModel.addTask(username, task); 
        Ok(views.html.taskList2(TaskListInMemoryModel.getTasks(username)))
        .withSession("username" -> username)
    }.getOrElse(Ok(views.html.login2()))
}.getOrElse(Ok(views.html.login2()))
}
def logout = Action {
  Redirect(routes.taskList2.load2).withNewSession
}
def generatedJS = Action{
  Ok(views.js.generatedJS())
}
}





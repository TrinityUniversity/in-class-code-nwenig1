package controllers 

import javax.inject._

import shared.SharedMessages
import play.api.mvc._
import models.TaskListInMemoryModel


@Singleton 
class taskList1 @Inject()(cc:ControllerComponents) extends AbstractController(cc) {

    def login = Action{ implicit request=>
        Ok(views.html.login1())
    }
    def validateLoginGet(username: String, password: String) = Action{
        Ok(s"$username logging in with $password")
    }
    def validateLoginPost = Action { implicit request =>
        val postVals = request.body.asFormUrlEncoded
        postVals.map { args =>
            val username=args("username").head 
            val password=args("password").head 
            if(TaskListInMemoryModel.validateUser(username, password)){
            Redirect(routes.taskList1.taskList).withSession("username"-> username).flashing("success" -> "Logged in!")
            }else{
                Redirect(routes.taskList1.login).flashing("error" -> "Invalid username/password combination.")
            }
            }.getOrElse(Redirect(routes.taskList1.login))
    }

    def taskList= Action{ implicit request=>
        val usernameOption = request.session.get("username")
        usernameOption.map{ username =>
            val tasks=TaskListInMemoryModel.getTasks(username)
             Ok(views.html.taskList1(tasks))
            }.getOrElse(Redirect(routes.taskList1.login))
        
       
    }
    def createUser = Action { implicit request =>
        val postVals = request.body.asFormUrlEncoded
        postVals.map { args =>
            val username=args("username").head
            val password=args("password").head 
            if(TaskListInMemoryModel.createUser(username, password)){
                Redirect(routes.taskList1.taskList).withSession("username"-> username)
            } else {
                Redirect(routes.taskList1.login).flashing("error" -> "User Creation Failed")
            }
            }.getOrElse(Redirect(routes.taskList1.login))
        
    }
    def logout = Action{
        Redirect(routes.taskList1.login).withNewSession
    }
    def addTask = Action { implicit request =>
        val usernameOption=request.session.get("username")
        usernameOption.map { username =>
            val postVals=request.body.asFormUrlEncoded
            postVals.map{ args =>
            val task=args("newTask").head
        TaskListInMemoryModel.addTask(username, task); 
        Redirect(routes.taskList1.taskList)
    }.getOrElse(Redirect(routes.taskList1.taskList))
}.getOrElse(Redirect(routes.taskList1.login))
}
  def deleteTask= Action { implicit request =>
        val usernameOption=request.session.get("username")
        usernameOption.map { username =>
            val postVals=request.body.asFormUrlEncoded
            postVals.map{ args =>
            val index=args("index").head.toInt
        TaskListInMemoryModel.removeTask(username, index); 
        Redirect(routes.taskList1.taskList)
    }.getOrElse(Redirect(routes.taskList1.taskList))
}.getOrElse(Redirect(routes.taskList1.login))
}
}

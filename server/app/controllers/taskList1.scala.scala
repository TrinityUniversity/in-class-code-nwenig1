package controllers 

import javax.inject._

import shared.SharedMessages
import play.api.mvc._
import models.TaskListInMemoryModel


@Singleton 
class taskList1 @Inject()(cc:ControllerComponents) extends AbstractController(cc) {

    def login = Action{
        Ok(views.html.login1())
    }
    def validateLoginGet(username: String, password: String) = Action{
        Ok(s"$username logging in with $password")
    }
    def validateLoginPost = Action { request =>
        val postVals = request.body.asFormUrlEncoded
        postVals.map { args =>
            val username=args("username").head 
            val password=args("password").head 
            if(TaskListInMemoryModel.validateUser(username, password)){

            
            Redirect(routes.taskList1.taskList)
            }else{
                Redirect(routes.taskList1.login)
            }
            }.getOrElse(Redirect(routes.taskList1.login))
    }

    def taskList= Action{
        val username = "Mark"
        val password="Pass"
        val tasks=TaskListInMemoryModel.getTasks(username)
        Ok(views.html.taskList1(tasks))
    }
    def createUser = Action { request =>
        val postVals = request.body.asFormUrlEncoded
        postVals.map { args =>
            val username=args("username").head
            val password=args("password").head 
            if(TaskListInMemoryModel.createUser(username, password)){
                Redirect(routes.taskList1.taskList)
            } else {
                Redirect(routes.taskList1.login)
            }
            }.getOrElse(Redirect(routes.taskList1.login))
        
    }

  
}

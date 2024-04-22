package  edu.trinity.videoquizreact

import org.scalajs.dom
import org.scalajs.dom.document
import org.scalajs.dom.html
import org.scalajs.dom.experimental.Fetch
import org.scalajs.dom.experimental.Headers

object Version6{
    val csrfToken = document.getElementById("csrfToken").asInstanceOf[html.Input].value
    val validateRoute = document.getElementById("validateRoute").asInstanceOf[html.Input].value
    val tasksRoute = document.getElementById("tasksRoute").asInstanceOf[html.Input].value
    val createRoute = document.getElementById("createRoute").asInstanceOf[html.Input].value
    val addRoute = document.getElementById("addRoute").asInstanceOf[html.Input].value
    val deleteRoute = document.getElementById("deleteRoute").asInstanceOf[html.Input].value
    val logoutRoute = document.getElementById("logoutRoute").asInstanceOf[html.Input].value
    def init(): Unit = {
        print("in version 6")
    }
    def login(): Unit = {
    val username = document.getElementById("loginName").asInstanceOf[html.Input].value
    val  password = document.getElementById("loginPass").asInstanceOf[html.Input].value
    val headers = new Headers()
    headers.set("Content-Type", "application/json")
    headers.set("Csrf-Token", csrfToken)
    Fetch.fetch(validateRoute, RequestInit(method = HttpMethod.POST) {
        
    }).then(res => res.json()).then(data => {
        if(data){
            document.getElementById("login-section").hidden = true 
            document.getElementById("task-section").hidden = false 
            document.getElementById("login-message").innerHTML= ""
            document.getElementById("create-message").innerHTML= ""
            loadTasks() 
        } else {
            document.getElementById("login-message").innerHTML="login failed"
           
        }

        
    });

    }
}
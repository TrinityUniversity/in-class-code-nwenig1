package controllers



import javax.inject._

import shared.SharedMessages
import play.api.mvc._
import play.api.i18n._
import models.TaskListInMemoryModel
import play.api.libs.json._
import akka.actor.Actor
import play.api.libs.streams.ActorFlow
import akka.actor.ActorSystem
import akka.stream.Materializer
import actors.ChatActor
import akka.actor.Props
import actors.ChatManager


@Singleton 
class WebSocketChat @Inject()(cc:ControllerComponents) (implicit system : ActorSystem, mat: Materializer) extends AbstractController(cc) {
   val manager = system.actorOf(Props[ChatManager], "Manager")
    def index = Action{ implicit request=>
        Ok(views.html.chatPage())
    }
    def socket = WebSocket.accept[String, String] { request =>//strs are type of input, output
        //use akka actors 
        println("Getting socket")
        ActorFlow.actorRef { out =>
           ChatActor.props(out, manager) 
            }
}
}

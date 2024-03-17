package actors

import akka.actor.Actor
import akka.actor.Props
import akka.actor.ActorRef

class ChatActor(out : ActorRef, manager: ActorRef) extends Actor{
    manager ! ChatManager.NewChatter(self) //self is like this pretty much 
    import ChatActor._
    def receive = {
        case s: String => manager ! ChatManager.Message(s) 
        case SendMessage(msg) => out ! msg
        
        case m => println("unhandled message in chatActor " + m) //for debugging purposes, at end 
    }
    out ! "Connected"
}
object ChatActor{
    def props(out : ActorRef, manager: ActorRef) = Props(new ChatActor(out, manager))

    case class SendMessage(msg : String)
}

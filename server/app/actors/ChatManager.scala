package actors

import akka.actor.Actor
import akka.actor.ActorRef

class ChatManager extends Actor {
    private var chatters = List.empty[ActorRef]

   import actors.ChatManager._
   def receive = {
        
        case NewChatter(chatter) => chatters ::=chatter
        case Message(msg)=> for(c <- chatters) c ! ChatActor.SendMessage(msg)
        case m => println("unhandled message in ChatManager " +m) //for debugging purposes, at end 
    }
}

object ChatManager {
    case class NewChatter(chatter : ActorRef)
    case class Message(msg: String)
}

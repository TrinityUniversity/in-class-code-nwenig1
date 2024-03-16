package actors

import akka.actor.Actor
import akka.actor.Props
import akka.actor.ActorRef

class ChatActor(out : ActorRef) extends Actor{
    def receive = {
        case s: String => //pretty much pattern matching 
            println("got message " + s)
        case m => println("unhandled message in chatActor " +m) //for debugging purposes, at end 
    }
    out ! "Connected"
}
object ChatActor{
    def props(out : ActorRef) = Props(new ChatActor(out))
}

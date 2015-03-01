package com.example.mysatusapi

import akka.actor.{ActorSystem, Props}
import akka.actor.ActorDSL._
import akka.event.Logging
import akka.io.IO
import akka.io.Tcp._
import spray.can.Http

object Boot extends App {

  implicit val system = ActorSystem("spray-api-service")
  val log = Logging(system, getClass)

  val callbackActor = actor(new Act {
    become {
      case b @ Bound(connection) => log.info(b.toString)
      case cf @ CommandFailed(command) => log.error(cf.toString)
      case all => log.debug("MyStatusApi App Received a message from Akka.IO: " + all.toString)
    }
  })

  val service = system.actorOf(Props[MyStatusApiServiceActor], "spray-service")

  IO(Http).tell(Http.Bind(service, "localhost", 8080), callbackActor)
}

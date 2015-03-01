package com.example.mysatusapi

import akka.actor.{Actor, Props}
import akka.event.Logging
import spray.routing._
import spray.http._
import MediaTypes._
import spray.json._
import spray.httpx.marshalling._
import spray.httpx.SprayJsonSupport._

class MyStatusApiServiceActor extends Actor with MyStatusApiService {
  def actorRefFactory = context
  def receive = runRoute(myStatusApiRoute)
}

case class BizData(status: String, term: String, message: String)

object MyJsonProtocol extends DefaultJsonProtocol {
  implicit val bizDataFormat = jsonFormat3(BizData)
}

trait MyStatusApiService extends HttpService {

  import MyJsonProtocol._

  val myStatusApiRoute =
    pathPrefix("api") {
      pathPrefix("v1") {
        path("mori_dev" / "status") {
          get {
            respondWithMediaType(`application/json`) {
              complete {
                marshal(BizData("I am super busy.", "4月末まで", "Brought to You By Scala/spray"))
              }
            }
          }
        }
      }
    }
  }

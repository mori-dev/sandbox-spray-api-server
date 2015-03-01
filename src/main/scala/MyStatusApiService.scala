package com.example.mysatusapi

import akka.actor.{Actor, Props}
import akka.event.Logging
import spray.routing._
import spray.http._
import MediaTypes._

class MyStatusApiServiceActor extends Actor with MyStatusApiService {
  def actorRefFactory = context
  def receive = runRoute(myStatusApiRoute)
}

trait MyStatusApiService extends HttpService {
  val myStatusApiRoute =
    pathPrefix("api") {
      pathPrefix("v1") {
        path("mori_dev" / "status") {
          get {
            complete {
              <h1>I am super busy. Thank you.</h1>
            }
          }
        }
      }
    }
  }

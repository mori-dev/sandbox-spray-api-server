name := "MySatusAPI"

version := "0.1"

scalaVersion := "2.10.3"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val sprayVersion = "1.2-RC4"
  val akkaVersion = "2.2.3"
  Seq(
  "io.spray" % "spray-can" % sprayVersion,
  "io.spray" % "spray-routing" % sprayVersion,
  "io.spray" % "spray-client" % sprayVersion,
  "io.spray" %%  "spray-json" % "1.2.5",
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "ch.qos.logback" % "logback-classic" % "1.0.12"
  )
}

seq(Revolver.settings: _*)

atmosSettings

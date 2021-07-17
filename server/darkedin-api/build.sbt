name := """darkedin-api"""
organization := "com.pon.dinapp"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

val AWSJavaSdkVersion = "2.5.52"

scalaVersion := "2.12.6"

libraryDependencies += guice
libraryDependencies += ws
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
libraryDependencies += "org.mockito" % "mockito-core" % "3.11.2" % "test"
libraryDependencies += "com.typesafe.play" %% "play-slick" % "5.0.0"
libraryDependencies += "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0"
libraryDependencies += "software.amazon.awssdk" % "auth" % AWSJavaSdkVersion
libraryDependencies += "software.amazon.awssdk" % "core" % AWSJavaSdkVersion
libraryDependencies += "software.amazon.awssdk" % "regions" % AWSJavaSdkVersion
libraryDependencies += "com.sksamuel.elastic4s" %% "elastic4s-core" % "7.10.7"
libraryDependencies += "org.apache.httpcomponents" % "httpclient" % "4.5.3"
libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.25"
// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.pon.dinapp.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.pon.dinapp.binders._"

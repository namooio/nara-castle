name := "nara-castle-service-play"

version := "1.0"

lazy val `nara-castle-service-play` = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "namoo.nara.castle" % "nara-castle-domain" % "0.3.14-SNAPSHOT",
  "namoo.nara.castle" % "nara-castle-akka" % "0.3.14-SNAPSHOT"
//  "com.typesafe.akka" % "akka-actor_2.11" % "2.5.2",
//  "com.typesafe.play" % "play-java_2.11" % "2.5.2"
)

resolvers += Resolver.mavenLocal

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"


routesGenerator := InjectedRoutesGenerator
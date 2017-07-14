name := "nara-castle-root"

lazy val commonSettings = Seq(
  organization := "nara.castle",
  version := "1.0-SNAPSHOT",
  scalaVersion := "2.12.2"
)

lazy val root = (project in file("."))
  .aggregate(domain, store, akka)

lazy val domain = (project in file("nara-castle-domain"))
  .settings(commonSettings: _*)

lazy val store = (project in file("nara-castle-store-mongo"))
  .settings(commonSettings: _*)
  .dependsOn(domain)

lazy val akka = (project in file("nara-castle-akka"))
  .settings(commonSettings: _*)
  .dependsOn(domain, store % Test)

publishTo := {
  val nexus = "dav:http://nexus.namoo.io/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/nara-snapshots")
  else
    Some("releases"  at nexus + "content/repositories/nara-releases")
}
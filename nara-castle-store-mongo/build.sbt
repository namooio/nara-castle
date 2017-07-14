name := "nara-castle-store-mongo"

libraryDependencies ++= Seq(
  "org.mongodb.morphia" % "morphia" % "1.3.2",
  "org.mongodb" % "mongodb-driver" % "3.4.2",
  "org.mongodb" % "mongo-java-driver" % "3.4.2",
  "de.flapdoodle.embed" % "de.flapdoodle.embed.mongo" % "2.0.0" % Test
)
name := """tsTraderJournal"""

version := "1.0-SNAPSHOT"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
//resolvers += "sbt-plugin-releases" at "http://repo.scala-sbt.org/scalasbt/sbt-plugin-releases/"

libraryDependencies ++= Seq(
  // Select Play modules
  //jdbc,      // The JDBC connection pool and the play.api.db API
  //anorm,     // Scala RDBMS Library
  //javaJdbc,  // Java database API
  //javaEbean, // Java Ebean plugin
  //javaJpa,   // Java JPA plugin
  //filters,   // A set of built-in filters
  javaCore,  // The core Java API
  // WebJars pull in client-side web libraries
  "org.webjars" %% "webjars-play" % "2.2.0",
  "org.webjars" % "bootstrap" % "2.3.1",
  "com.typesafe.slick" %% "slick" % "1.0.1",
  "com.typesafe.play" %% "play-slick" % "0.5.0.8",
  cache,
  "com.typesafe" %% "play-plugins-util" % "2.2.0",
  "com.typesafe" %% "play-plugins-mailer" % "2.2.0",
  "org.mindrot" % "jbcrypt" % "0.3m",
  // Add your own project dependencies in the form:
  // "group" % "artifact" % "version"
  "ws.securesocial" %% "securesocial" % "2.1.3"
)



play.Project.playScalaSettings

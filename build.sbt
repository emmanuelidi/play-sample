
name := """play-sample"""
organization := "org.emmanuelidi"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)
	.enablePlugins(BuildInfoPlugin)
	.settings(
		buildInfoPackage := "org.emmanuelidi.play.sample",
		buildInfoOptions += BuildInfoOption.BuildTime,
		buildInfoOptions += BuildInfoOption.ToJson
	)

scalaVersion := "2.12.4"

libraryDependencies += guice



// disable using the Scala version in output paths and artifacts
crossPaths := false


/*
lazy val root = (project in file("."))
  .enablePlugins(BuildInfoPlugin)
  .settings(
    buildInfoKeys := BuildInfoKey.ofN(name, version, someTask),
    buildInfoPackage := "hello"
  )

buildInfoOptions += BuildInfoOption.ToJson
*/

// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// TEST CONFIGURATION
//

Test / testOptions += Tests.Setup( () => println("Setup") )
Test / testOptions += Tests.Cleanup( () => println("Cleanup") )

/*

javaOptions in Test ++= Seq(
  "-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=9998",
  "-Xms512M",
  "-Xmx1536M",
  "-Xss1M",
  "-XX:MaxPermSize=384M"
)

Test / testOptions += Tests.Argument("-verbosity", "1")
To specify them for a specific test framework only:

Test / testOptions += Tests.Argument(TestFrameworks.ScalaCheck, "-verbosity", "1")


 */

// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// INTEGRATION TEST CONFIGURATION
//
/*
https://www.scala-sbt.org/release/docs/Testing.html#Integration+Tests
 */
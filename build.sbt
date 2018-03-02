
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
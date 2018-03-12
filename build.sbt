
name := """play-sample"""
organization := "org.emmanuelidi"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)
	.enablePlugins(BuildInfoPlugin)
	.settings(
		// https://github.com/sbt/sbt-buildinfo
		buildInfoPackage := "org.emmanuelidi.play.sample",
		buildInfoOptions += BuildInfoOption.BuildTime,
		buildInfoOptions += BuildInfoOption.ToJson
	)
	// https://github.com/sbt/sbt-native-packager
	.enablePlugins(DockerPlugin)


// https://github.com/sbt/sbt-git


scalaVersion := "2.12.4"

libraryDependencies += guice


// Unit Testing
libraryDependencies ++= Seq(
	// https://junit.org/junit4/
	"junit" % "junit" % "4.12" % Test,
	// https://github.com/jhalterman/concurrentunit
	"net.jodah" % "concurrentunit" % "0.4.3" % Test,
	// http://hamcrest.org/JavaHamcrest/
	"org.hamcrest" % "hamcrest-junit" % "2.0.0.0" % Test,
	// https://github.com/awaitility/awaitility
	"org.awaitility" % "awaitility" % "3.1.0" % Test,
	// http://stefanbirkner.github.io/system-rules/
	"com.github.stefanbirkner" % "system-rules" % "1.17.1" % Test
)
/*

        // https://hc.apache.org/index.html
        "org.apache.httpcomponents" % "httpclient" % "4.5.5" % Test
        // https://jersey.github.io/documentation/latest/client.html
        //"javax.ws.rs" % "javax.ws.rs-api" % "2.1" % Test,
        //"org.glassfish.jersey.core" % "jersey-client" % "2.26" % Test


    .settings(libraryDependencies ++= Seq(
        "junit" % "junit" % "4.12" % Test,
        // https://github.com/jhalterman/concurrentunit
        "net.jodah" % "concurrentunit" % "0.4.3" % Test,

        // https://hc.apache.org/index.html
        "org.apache.httpcomponents" % "httpclient" % "4.5.5" % Test
        // https://jersey.github.io/documentation/latest/client.html
        //"javax.ws.rs" % "javax.ws.rs-api" % "2.1" % Test,
        //"org.glassfish.jersey.core" % "jersey-client" % "2.26" % Test
    ))

 */

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

//Test / testOptions += Tests.Setup( () => println("Setup") )
//Test / testOptions += Tests.Cleanup( () => println("Cleanup") )

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


// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// Code Analysis & Reporting
//
/*
[play-sample] $ show sonarProperties
[info] Map(
  sonar.scala.scapegoat.reportPath -> target/scapegoat-report/scapegoat.xml, 
  sonar.sources -> app, 
  sonar.sourceEncoding -> UTF-8, 
  sonar.projectName -> play-sample, 
  sonar.scoverage.reportPath -> target/scoverage-report/scoverage.xml, 
  sonar.projectKey -> play-sample
)
import sbtsonar.SonarPlugin.autoImport.sonarProperties
 
sonarProperties := Map(
  "sonar.projectKey" -> "project-name",
  "sonar.sources" -> "src/main/scala",
  "sonar.sourceEncoding" -> "UTF-8",
  "sonar.scoverage.reportPath" -> "target/scala-2.12/scoverage-report/scoverage.xml",
  "sonar.scala.scapegoat.reportPath" -> "target/scala-2.12/scapegoat-report/scapegoat.xml"
  ...
)

*/
/*

sonarProperties := Map(
  "sonar.projectKey" -> "org.emmanuelidi:play-sample",
  "sonar.projectName" -> "Play Sample",
  "sonar.projectVersion" -> "1.0",
)
*/

// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// CONTAINER IMAGE
//
// See:
//  - https://sbt-native-packager.readthedocs.io/en/stable/formats/universal.html#
//  - https://sbt-native-packager.readthedocs.io/en/stable/formats/docker.html#

// ~~ Informational settings

//packageName in Docker
version in Docker := version.value
maintainer in Docker := "API Platform Team"

// ~~ Environment Settings

dockerBaseImage := "anapsix/alpine-java:8_jdk_unlimited"
//daemonUser in Docker  The user to use when executing the application. Files below the install path also have their ownership set to this user.
dockerExposedPorts := Seq(9000)
//dockerExposedUdpPorts := Seq()
dockerExposedVolumes := Seq("/logs")
// https://docs.openshift.com/enterprise/3.0/creating_images/metadata.html#creating-images-metadata
dockerLabels := Map( "api" -> "guests",
										 "foo" -> "bar")
// dockerEntrypoint	Overrides the default entrypoint for docker-specific service discovery tasks before running the application. Defaults to the bash executable script, available at bin/<script name> in the current WORKDIR of /opt/docker.
//dockerVersion  The docker server version. Used to leverage new docker features while maintaining backwards compatibility.

// ~~ Publishing Settings
//dockerRepository	The repository to which the image is pushed when the docker:publish task is run. This should be of the form [repository.host[:repository.port]] (assumes use of the index.docker.io repository) or [repository.host[:repository.port]][/username] (discouraged, but available for backwards compatibilty.).
dockerUsername	:= Option("emmanuelidi")
dockerUpdateLatest := false


/*
lazy val myTestTask = taskKey[Unit]("my test task")

myTestTask := {
  (test in (core, Test)).value
  (test in (tools, Test)).value
}

 */
lazy val validate = taskKey[Unit]("validate the project is correct and all necessary information is available")
lazy val verify = taskKey[Unit]("run any checks to verify the package is valid and meets quality criteria")

verify := { 
  (jcheckStyle in Compile).value
  (jcheckStyle in Test).value
  println("hello world!")
}


// Findbugs
// See docs/examples at http://findbugs.sourceforge.net/manual/filter.html

findbugsExcludeFilters := Some(
  <FindBugsFilter>
    <Match>
      <Source name="~.*\.scala"/>
    </Match>
    <Match>
      <Class name="~controllers\.routes.*"/>
    </Match>
  </FindBugsFilter>
)
/*
findbugsIncludeFilters := Some(<FindBugsFilter>
  <Match>
    <Class name="de.johoop.Meep" />
  </Match>
</FindBugsFilter>)

val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA,
  settings = Defaults.defaultSettings ++ findbugsSettings).settings(
    findbugsExcludeFilters := Some(
      <FindBugsFilter>
        <!-- See docs/examples at http://findbugs.sourceforge.net/manual/filter.html -->
        <Match>
          <Class name="~views\.html\..*"/>
        </Match>
        <Match>
          <Class name="~Routes.*"/>
        </Match>
        <Match>
          <Class name="~controllers\.routes.*"/>
        </Match>
      </FindBugsFilter>
    )
  )
*/



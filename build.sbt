ThisBuild / name := "mongodb.almaren"
ThisBuild / organization := "com.github.music-of-the-ainur"

lazy val scala212 = "2.12.10"
lazy val scala213 = "2.13.9"

crossScalaVersions := Seq(scala212,scala213)
ThisBuild / scalaVersion := scala213


val sparkVersion = "3.4.1"
val majorVersionReg = "([0-9]+\\.[0-9]+).{0,}".r

val majorVersionReg(majorVersion) = sparkVersion

scalacOptions ++= Seq("-deprecation", "-feature")

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-sql" % sparkVersion % "provided",
  "com.github.music-of-the-ainur" %% "almaren-framework" % s"0.9.10-${majorVersion}" % "provided",
  "org.mongodb.spark" %% "mongo-spark-connector" % "10.2.0" % "provided",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
  "org.scalatest" %% "scalatest" % "3.2.14" % "test"
)

enablePlugins(GitVersioning)

resolvers +=
  "Sonatype OSS Releases" at "https://repo1.maven.org/maven2/"

ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/music-of-the-ainur/mongodb.almaren"),
    "scm:git@github.com:music-of-the-ainur/mongodb.almaren.git"
  )
)
ThisBuild / developers := List(
  Developer(
    id    = "mantovani",
    name  = "Daniel Mantovani",
    email = "daniel.mantovani@modak.com",
    url   = url("https://github.com/music-of-the-ainur")
  ),
    Developer(
    id = "badrinathpatchikolla",
    name = "Badrinath Patchikolla",
    email = "badrinath.patchikolla@modakanalytics.com",
    url = url("https://github.com/music-of-the-ainur")
  )
)

ThisBuild / description := "MongoDB Connector For Almaren Framework"
ThisBuild / licenses := List("Apache 2" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt"))
ThisBuild / homepage := Some(url("https://github.com/music-of-the-ainur/mongodb.almaren"))
ThisBuild / organizationName := "Music of Ainur"
ThisBuild / organizationHomepage := Some(url("https://github.com/music-of-the-ainur"))


// Remove all additional repository other than Maven Central from POM
credentials += Credentials(Path.userHome / ".sbt" / "sonatype_credential")
ThisBuild / pomIncludeRepository := { _ => false }
ThisBuild / publishTo := {
  val centralSnapshots = "https://central.sonatype.com/repository/maven-snapshots/"
  if (version.value.endsWith("-SNAPSHOT")) Some("central-snapshots" at centralSnapshots)
  else localStaging.value
}

ThisBuild / publishMavenStyle := true
updateOptions := updateOptions.value.withGigahorse(false)

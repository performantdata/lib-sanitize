ThisBuild / organization := "livongo"
ThisBuild / scalaVersion := "2.12.17"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-explaintypes",
  "-feature",
  "-unchecked"
)
ThisBuild / libraryDependencies ++= Seq(
  "com.chuusai" %% "shapeless" % "2.3.10",
  "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  "org.scalatest" %% "scalatest-freespec" % "3.2.12" % Test
)

lazy val root = (project in file("."))
  .aggregate(sanitize, datatype, sanitizeDatatype)

val sanitize = project
  .settings(
    name := "sanitize",
    idePackagePrefix := Some("com.performantdata.sanitize")
  )

val datatype = project
  .settings(
    name := "datatype",
    version := "0.1.0-SNAPSHOT",
    idePackagePrefix := Some("com.performantdata.datatype"),
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest-freespec" % "3.2.12" % Test
    )
  )

val sanitizeDatatype = (project in file("sanitize-datatype"))
  .dependsOn(datatype, sanitize)
  .settings(
    name := "sanitize-datatype",
    idePackagePrefix := Some("com.performantdata.sanitize.datatype")
  )

lazy val commonSettings = Seq(
  organization := "tu.dresden.de",
  name := "benchmark-scroll",
  version := "0.0.1",
  scalaVersion := "2.12.0",
  scalacOptions ++= Seq(
  	"-encoding", "utf8",
    "-deprecation",
    "-feature",
    "-language:dynamics",
    "-language:reflectiveCalls",
    "-language:postfixOps",
    "-language:implicitConversions",
    "-unchecked",
    "-target:jvm-1.8"),
  fork := true,
  javaOptions ++= Seq("-server", "-d64", "-Xms1024m", "-Xmx4048m")
)

lazy val scroll = ProjectRef(uri("git://github.com/max-leuthaeuser/SCROLL.git#master"), "core")

lazy val main = (project in file("."))
  .configs(Profile, Graal)
  .dependsOn(scroll)
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.1",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"
  )
  ): _*)

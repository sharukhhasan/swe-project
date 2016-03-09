name := """SWE Project"""

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.6"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "org.postgresql" % "postgresql" % "9.4-1201-jdbc41",
  javaJpa,
  "org.mindrot" % "jbcrypt" % "0.3m",
  "com.adrianhurt" %% "play-bootstrap" % "1.0-P24-B3-SNAPSHOT" exclude("org.webjars", "jquery") exclude("org.webjars" , "bootstrap"),
  "javax.mail" % "mail" % "1.4.1"
)

PlayKeys.externalizeResources := false

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator


//fork in run := true


fork in run := true
name := "ferrier-admin"

version := "0.1.0"

organization := "com.weifan"

scalaVersion := "2.12.8"

resolvers ++= Seq(
  "snapshots"     at "https://oss.sonatype.org/content/repositories/snapshots",
  "staging"       at "https://oss.sonatype.org/content/repositories/staging",
  "releases"      at "https://oss.sonatype.org/content/repositories/releases"
)

enablePlugins(TomcatPlugin)

containerLibs in Tomcat := Seq("com.github.jsimone" % "webapp-runner" % "9.0.14.0" intransitive())

webappWebInfClasses := true
unmanagedResourceDirectories in Test += baseDirectory.value / "src/main/webapp"

scalacOptions ++= Seq("-deprecation", "-unchecked")

libraryDependencies ++= {
  Seq(
    "ch.qos.logback"    % "logback-classic"         % "1.2.3",
    "org.specs2"        %% "specs2-core"            % "3.9.4"            % "test",
    "com.h2database"    % "h2"                      % "1.4.187"
  )
}

scalacOptions in Test ++= Seq("-Yrangepos")

libraryDependencies += "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided"
libraryDependencies += "org.jboss.weld.servlet" % "weld-servlet" % "2.4.8.Final"
libraryDependencies += "javax.validation" % "validation-api" % "2.0.1.Final"
libraryDependencies += "javax.websocket" % "javax.websocket-api" % "1.1" % "provided"
libraryDependencies += "org.glassfish" % "javax.faces" % "2.3.8"
libraryDependencies += "org.omnifaces" % "omnifaces" % "3.2"
libraryDependencies += "org.primefaces" % "primefaces" % "6.2"

val dsVersion = "1.9.0"

// https://mvnrepository.com/artifact/org.apache.deltaspike.core/deltaspike-core-api
libraryDependencies += "org.apache.deltaspike.core" % "deltaspike-core-api" % dsVersion

libraryDependencies += "org.apache.deltaspike.core" % "deltaspike-core-impl" % dsVersion

// https://mvnrepository.com/artifact/org.apache.deltaspike.modules/deltaspike-data-module-impl
libraryDependencies += "org.apache.deltaspike.modules" % "deltaspike-data-module-api" % dsVersion
// https://mvnrepository.com/artifact/org.apache.deltaspike.modules/deltaspike-data-module-impl
libraryDependencies += "org.apache.deltaspike.modules" % "deltaspike-data-module-impl" % dsVersion

// https://mvnrepository.com/artifact/org.hibernate/hibernate-entitymanager
libraryDependencies += "org.hibernate" % "hibernate-entitymanager" % "5.4.1.Final"





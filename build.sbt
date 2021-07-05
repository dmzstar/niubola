
name := "niubola"

version := "0.1.0"

organization := "com.weifan"

scalaVersion := "2.12.14"

val dsVersion = "1.9.5"
val hibernateVersion = "5.4.1.Final"

val pfVersion = "8.0"
val pfeVersion = "8.0.4"

val adminfacesVersion = "1.2.0"

val rewriteVersion = "3.5.0.Final"


//enablePlugins(SbtTwirl)

resolvers ++= Seq(
  "snapshots"     at "https://oss.sonatype.org/content/repositories/snapshots",
  "staging"       at "https://oss.sonatype.org/content/repositories/staging",
  "releases"      at "https://oss.sonatype.org/content/repositories/releases"
)



enablePlugins(TomcatPlugin)

//containerLibs in Tomcat := Seq("com.github.jsimone" % "webapp-runner" % "9.0.14.0" intransitive())

//containerLibs in Tomcat := Seq("com.github.jsimone" % "webapp-runner" % "9.0.27.1" intransitive())

containerLibs in Tomcat := Seq("com.heroku" % "webapp-runner" % "9.0.36.1" intransitive())
webappWebInfClasses := true
containerPort := 9090

//containerLibs in Tomcat += "org.wso2.orbit.javax.xml.bind" % "jaxb-api" % "2.3.1.wso2v2"
//containerLibs in Tomcat += "org.glassfish.jaxb" % "jaxb-runtime" % "2.3.1"


/**
enablePlugins(ContainerPlugin)
containerLibs in Container := Seq(
  "org.apache.tomcat" % "tomcat-catalina" % "9.0.48"
)
*/

containerLaunchCmd in Container :=
  { (port, path) => Seq("webapp.runner.launch.Main", port.toString, path) }


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

// https://mvnrepository.com/artifact/org.scala-lang/scala-reflect
//libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.12.8"

libraryDependencies += "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided"
//libraryDependencies += "javax.el" % "javax.el-api" % "3.0.1-b06"
libraryDependencies += "org.jboss.weld.servlet" % "weld-servlet" % "2.4.8.Final"
libraryDependencies += "javax.validation" % "validation-api" % "2.0.1.Final"
libraryDependencies += "javax.websocket" % "javax.websocket-api" % "1.1" % "provided"
libraryDependencies += "javax.ejb" % "javax.ejb-api" % "3.2.2"


libraryDependencies += "org.ocpsoft.rewrite" % "rewrite-servlet" % rewriteVersion
libraryDependencies += "org.ocpsoft.rewrite" % "rewrite-integration-faces" % rewriteVersion
//libraryDependencies += "org.ocpsoft.rewrite" % "rewrite-integration-cdi" % rewriteVersion

libraryDependencies += "org.glassfish" % "javax.faces" % "2.3.8"
libraryDependencies += "org.omnifaces" % "omnifaces" % "3.2"
libraryDependencies += "org.primefaces" % "primefaces" % pfVersion
// https://mvnrepository.com/artifact/org.primefaces.extensions/primefaces-extensions
libraryDependencies += "org.primefaces.extensions" % "primefaces-extensions" % pfeVersion
libraryDependencies += "org.primefaces.extensions" % "resources-ckeditor" % pfeVersion % "runtime"
libraryDependencies += "net.bootsfaces" % "bootsfaces" % "1.4.1"



libraryDependencies += "org.apache.deltaspike.core" % "deltaspike-core-api" % dsVersion
libraryDependencies += "org.apache.deltaspike.core" % "deltaspike-core-impl" % dsVersion
libraryDependencies += "org.apache.deltaspike.modules" % "deltaspike-data-module-api" % dsVersion
libraryDependencies += "org.apache.deltaspike.modules" % "deltaspike-data-module-impl" % dsVersion

libraryDependencies += "org.hibernate" % "hibernate-entitymanager" % hibernateVersion
libraryDependencies += "org.hibernate" % "hibernate-jpamodelgen" % hibernateVersion



// https://mvnrepository.com/artifact/com.github.adminfaces/admin-theme
//libraryDependencies += "com.github.adminfaces" % "admin-theme" % "1.0.0-RC20"
//libraryDependencies += "org.webjars.bower" % "font-awesome" % "4.7.0"
// https://mvnrepository.com/artifact/com.github.adminfaces/admin-template
libraryDependencies += "com.github.adminfaces" % "admin-template" % adminfacesVersion


//security
libraryDependencies += "org.pac4j" % "pac4j-core" % "3.5.0"








/* basic project info 
name := "JacobianADMMSpark"

organization := "com.lanchao"

version := "0.1.0-SNAPSHOT"

/* scala versions and options */
scalaVersion := "2.10.4"

addCompilerPlugin("com.foursquare.lint" %% "linter" % "0.1-SNAPSHOT")


/* dependencies */
libraryDependencies ++= Seq (
  // -- lang --
  // "org.apache.commons" % "commons-lang3" % "3.1",
  // "org.scalaz" %% "scalaz-core" % "7.0.0-M7",
  // "org.scalaz" %% "scalaz-effect" % "7.0.0-M7",
  // -- util --
  // "com.github.nscala-time" %% "nscala-time" % "0.2.0",
  // "org.spire-math" % "spire_2.10.0" % "0.3.0-M7",
  // "com.github.scopt" %% "scopt" % "2.1.0",
  // "org.rogach" %% "scallop" % "0.6.3",
  // -- collections --
  // "com.google.guava" % "guava" % "13.0.1",
  // "com.chuusai" %% "shapeless" % "1.2.3",
  // "de.sciss" %% "fingertree" % "1.2.+",
  // "com.assembla.scala-incubator" % "graph-core_2.10" % "1.6.0",
  // -- io --
  // "commons-io" % "commons-io" % "2.4",
  // -- logging & configuration --
  // "com.typesafe" %% "scalalogging-slf4j" % "1.0.0",
  // "ch.qos.logback" % "logback-classic" % "1.0.7" % "provided",
  // "com.typesafe" % "config" % "1.0.0",
  // -- database drivers --
  // "com.h2database" % "h2" % "1.2.127",
  // "mysql" % "mysql-connector-java" % "5.1.10",
  // -- persistence --
  // "com.novus" %% "salat" % "1.9.2-SNAPSHOT",
  // "net.debasishg" %% "redisclient" % "2.9",
  // "com.typesafe" %% "slick" % "1.0.0-RC1",
  // "org.squeryl" %% "squeryl" % "0.9.5-6",
  // "com.github.nikita-volkov" % "sorm" % "0.3.5",
  // "fi.reaktor" %% "sqltyped" % "0.1.0",
  // "com.imageworks.scala-migrations" %% "scala-migrations" % "1.1.1",
  // -- serialization --
  // "org.json4s" %% "json4s-native" % "3.1.0",
  // "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.1.3",
  // -- concurrency --
  // "com.typesafe.akka" %% "akka-actor" % "2.2-SNAPSHOT",
  // "org.scala-stm" %% "scala-stm" % "0.7",
  // -- network --
   // "net.databinder.dispatch" %% "dispatch-core" % "0.9.2",
  // -- testing --
  "org.scalacheck" %% "scalacheck" % "1.10.0",
  // "org.specs2" %% "specs2" %o "1.13",
  "org.scalatest" %% "scalatest" % "1.9.2",
  "org.apache.spark" %% "spark-core" % "1.2.0",
  "org.scalanlp" % "breeze-math_2.9.2" % "0.2",
  "org.scalanlp" % "breeze-learn_2.9.2" % "0.2"
)

/* you may need these repos */
resolvers ++= Seq(
  // Resolver.sonatypeRepo("snapshots")
  // Resolver.typesafeIvyRepo("snapshots")
  // Resolver.typesafeIvyRepo("releases")
  // Resolver.typesafeRepo("releases")
  // Resolver.typesafeRepo("snapshots")
  // JavaNet2Repository,
  // JavaNet1Repository,
  // "spray repo" at "http://repo.spray.io",
  // Resolver.mavenLocal,
  // Resolver.sonatypeRepo("Public"),
  "Akka Repository" at "http://repo.akka.io/releases/",
  "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
  "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases/",
  "ScalaNLP Maven2" at "http://repo.scalanlp.org/repo",
  "Linter Repository" at "https://hairyfotr.github.io/linteRepo/releases"
)
*/







name := "JacobianADMM"

version := "1.0"

scalaVersion := "2.10.4"

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.2.0" 

//libraryDependencies += "org.apache.spark" %% "spark-mllib" % "1.1.0-SNAPSHOT" 

libraryDependencies += "org.apache.hadoop" % "hadoop-client" % "1.0.4" 

libraryDependencies += "com.github.scopt" %% "scopt" % "3.2.0"

libraryDependencies  ++= Seq(
    "org.scalanlp" %% "breeze" % "0.7",
    "org.scalanlp" %% "breeze-natives" % "0.7"
)

libraryDependencies += "com.github.scopt" %% "scopt" % "3.2.0"

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0" % "test"

libraryDependencies += "com.novocode" % "junit-interface" % "0.10" % "test"

libraryDependencies += "org.scalacheck" % "scalacheck_2.10" % "1.10.0"

resolvers += Resolver.mavenLocal

resolvers += Resolver.sonatypeRepo("public")

resolvers += "Akka Repository" at "http://repo.akka.io/releases/"

resolvers ++= Seq(
  "Sonatype OSS Releases"  at "http://oss.sonatype.org/content/repositories/releases/",
  "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"
)

javaOptions in (Test,run) += "-Xmx6G"


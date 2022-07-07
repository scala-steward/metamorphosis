import sbt._

object Dependencies {

  object Version {
    val scala = "2.12.16"

    val kafka = "2.7.2"
  }

  val kafkaConnectTransforms = "org.apache.kafka" % "connect-transforms" % Version.kafka

  val scalaReflect = "org.scala-lang" % "scala-reflect" % Version.scala
}

import sbt._

object Dependencies {

  object Version {
    val scala = "2.12.10"

    val kafka = "2.4.0"
  }

  val kafkaConnectTransforms = "org.apache.kafka" % "connect-transforms" % Version.kafka

  val scalaReflect = "org.scala-lang" % "scala-reflect" % Version.scala
}

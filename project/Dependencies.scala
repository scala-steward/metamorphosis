import sbt._

object Dependencies {

  object Version {
    val scala = "2.12.9"

    val cats = "2.1.0"
    val catsEffect = "2.1.0"

    val kafka = "2.4.0"

    val minitest = "2.7.0"
    val scalacheck = "1.14.0"
  }

  val cats = "org.typelevel" %% "cats-core" % Version.cats
  val catsEffect = "org.typelevel" %% "cats-effect" % Version.catsEffect

  val kafkaConnectTransforms = "org.apache.kafka" % "connect-transforms" % Version.kafka

  val minitest = "io.monix" %% "minitest" % Version.minitest
  val minitestLaws = "io.monix" %% "minitest-laws" % Version.minitest
  val scalacheck = "org.scalacheck" %% "scalacheck" % Version.scalacheck
  val catsLaws = "org.typelevel" %% "cats-laws" % Version.cats

  val scalaReflect = "org.scala-lang" % "scala-reflect" % Version.scala
}

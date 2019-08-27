package com.leighperry.metamorphosis

import java.util.concurrent.atomic.AtomicReference
import java.util.{Map => JMap}

import org.apache.kafka.common.config.ConfigDef
import org.apache.kafka.common.config.ConfigDef.{Importance, Type}
import org.apache.kafka.connect.connector.ConnectRecord
import org.apache.kafka.connect.data.{Schema, Struct}
import org.apache.kafka.connect.transforms.Transformation
import org.apache.kafka.connect.transforms.util.SimpleConfig

class UnwrapTransformation[R <: ConnectRecord[R]]() extends Transformation[R] {
  private val targetFieldParameter = "field"

  private val configDef: ConfigDef =
    (new ConfigDef)
      .define(
        targetFieldParameter,
        Type.STRING,
        "",
        Importance.HIGH,
        "Name of the field to unwrap"
      )

  private val targetField: AtomicReference[Option[String]] =
    new AtomicReference(Option.empty)

  override def config(): ConfigDef =
    configDef

  override def configure(props: JMap[String, _]): Unit =
    targetField.set(Option(new SimpleConfig(configDef, props).getString(targetFieldParameter)))

  override def apply(record: R): R =
    (targetField.get, Option(record.valueSchema), Option(record.value)) match {
      case (Some(fieldName), Some(schema), Some(value: Struct)) if value.get(fieldName) != null =>
        newRecord(record, schema.field(fieldName).schema, value.get(fieldName))
      case _ =>
        newRecord(record, null, null)
    }

  def newRecord(record: R, updatedSchema: Schema, updatedValue: Any): R =
    record
      .newRecord(
        record.topic,
        record.kafkaPartition,
        record.keySchema,
        record.key,
        updatedSchema,
        updatedValue,
        record.timestamp,
        record.headers
      )

  override def close(): Unit =
    ()
}

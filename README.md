# Metamorphosis - Kafka-related stuff

# Connect envelope destructuring

```
MSERVER=localhost
MNAME=somename
MTOPICNAME=sometopicname

curl -X POST http://${MSERVER}:8083/connectors \
  -H "Content-Type: application/json" \
  -d @/dev/stdin << EOF
{
  "name": "${MNAME}",
      "config": {
        "name": "${MNAME}",
        "connector.class": "...",
        :
        "transforms": "unwrap",
        "transforms.unwrap.type": "com.leighperry.metamorphosis.UnwrapTransformation",
        "transforms.unwrap.field": "after"
    }
}
EOF
```

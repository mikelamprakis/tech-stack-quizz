## Question 1

```markdown
What is the most common cause of serialization errors when using Avro and Schema Registry?
```

**Options**

```markdown
- A. Kafka topic not found
- B. Invalid consumer group ID
- C. Schema mismatch between producer and consumer
- D. Incorrect broker configuration
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Would cause a topic-related error, not serialization.
- B. Would affect offset management, not schema.
- C. Correct – A different or incompatible schema causes serialization failure.
- D. Broker config issues are less likely to trigger Avro errors.
```

</details>

---

## Question 2

```markdown
Which tool is best for inspecting registered schemas in Schema Registry?
```

**Options**

```markdown
- A. JConsole
- B. cURL with Schema Registry REST API
- C. Kafka Streams API
- D. Kafka Connect UI
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- A. JConsole is for JVM metrics, not schema inspection.
- B. Correct – Schema Registry exposes a REST API for managing schemas.
- C. Kafka Streams is for processing data, not schema management.
- D. Kafka Connect UI helps with connectors, not raw schema operations.
```

</details>

---

## Question 3

```markdown
A 409 Conflict HTTP response from Schema Registry during schema registration usually indicates what?
```

**Options**

```markdown
- A. The topic is missing
- B. The schema already exists
- C. The schema is incompatible with the previous version
- D. Schema Registry service is down
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. REST API doesn’t validate Kafka topics.
- B. Schema Registry handles duplicates gracefully.
- C. Correct – A 409 Conflict indicates a compatibility violation.
- D. Would return 503 or timeout.
```

</details>

---

## Question 4

```markdown
What configuration must be enabled in the producer to auto-register new Avro schemas?
```

**Options**

```markdown
- A. schema.compatibility.level
- B. auto.schema.register=true
- C. avro.schema.auto.create=true
- D. auto.register.schemas=true
```

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**

```markdown
- A. Controls compatibility, not registration.
- B. Not a valid config key.
- C. Not a real Avro setting.
- D. Correct – This enables automatic registration of new schemas.
```

</details>

---

## Question 5

```markdown
What does error “Unknown magic byte!” typically indicate in a Kafka consumer?
```

**Options**

```markdown
- A. Kafka topic doesn't exist
- B. The message isn't Avro-encoded
- C. Consumer offset is invalid
- D. SSL handshake failure
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- A. Would raise a different Kafka-specific error.
- B. Correct – Magic byte helps Avro deserializers detect schema info; missing it implies wrong encoding.
- C. Offset errors raise OffsetOutOfRange exceptions.
- D. Unrelated to schema or serialization.
```

</details>

---

## Question 6

```markdown
Which of the following is a good metric to monitor for Schema Registry performance?
```

**Options**

```markdown
- A. consumer_lag
- B. kafka_controller_count
- C. jetty.requests.count
- D. heap_used_percent
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Relates to consumers, not Schema Registry.
- B. Related to Kafka brokers.
- C. Correct – Schema Registry is a Jetty web server; Jetty metrics help monitor requests.
- D. JVM-level metric, not schema-specific.
```

</details>

---

## Question 7

```markdown
Which compatibility setting allows schemas to change as long as existing consumers continue to work?
```

**Options**

```markdown
- A. FORWARD
- B. NONE
- C. FULL
- D. BACKWARD
```

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**

```markdown
- A. Allows future consumers to read old data.
- B. Disables compatibility checks.
- C. Ensures both backward and forward compatibility.
- D. Correct – BACKWARD compatibility ensures new schemas can read old data.
```

</details>

---

## Question 8

```markdown
Which HTTP status code from the Schema Registry indicates the requested subject does not exist?
```

**Options**

```markdown
- A. 404
- B. 409
- C. 500
- D. 200
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
- A. Correct – 404 means the subject is not found.
- B. 409 signals schema incompatibility.
- C. 500 indicates a server-side error.
- D. 200 means success.
```

</details>

---

## Question 9

```markdown
How can you check if your schema registry is healthy and available?
```

**Options**

```markdown
- A. Send a schema to any topic
- B. Restart all Kafka brokers
- C. Use the `/subjects` endpoint in a health check
- D. Create a dummy consumer group
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. May succeed or fail but doesn’t confirm registry health.
- B. Unnecessary and risky.
- C. Correct – Listing `/subjects` shows the service is responsive and active.
- D. Doesn’t interact with Schema Registry.
```

</details>

---

## Question 10

```markdown
What log message pattern often indicates a deserialization error in Kafka consumers using Avro?
```

**Options**

```markdown
- A. "Offset commit failed"
- B. "NoSuchMethodError"
- C. "AvroTypeException" or "Incompatible schema"
- D. "Topic authorization failed"
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Indicates commit issues, not deserialization.
- B. Java method conflict, not schema-related.
- C. Correct – These messages signal Avro deserialization issues.
- D. Related to ACLs, not Avro.
```

</details>


## Question 11

```markdown
How can you handle a SerializationException thrown by the KafkaAvroDeserializer in a Kafka consumer?
```

**Options**

```markdown
- A. Catch the exception and retry deserializing the message with a different deserializer
- B. Catch the exception and skip the problematic message by committing its offset
- C. Catch the exception and manually retrieve the schema from the Schema Registry for deserialization
- D. Let the exception propagate and handle it at a higher level in the consumer application
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Skipping and committing the offset allows the consumer to proceed.

- A. Incorrect – Retrying with another deserializer is not recommended.
- B. Correct – This is the typical handling approach.
- C. Incorrect – Manual retrieval is impractical.
- D. Incorrect – Letting it propagate may cause consumer to stall.
```

</details>

---
## Question 1

```markdown
When using the Confluent REST Proxy to produce messages, what happens if the `value.schema.id` is provided in the request payload?
```

**Options**

```markdown
- A. The REST Proxy validates the payload against the schema specified by the ID
- B. The REST Proxy retrieves the schema from the Schema Registry and includes it in the produced message
- C. The REST Proxy ignores the `value.schema.id` field and produces the message without any schema information
- D. The REST Proxy returns an error indicating that the `value.schema.id` is not supported
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
When producing messages through the Confluent REST Proxy, you can optionally provide the `value.schema.id` field to specify the schema ID.

- A. The Proxy retrieves the schema by ID and validates the payload.
- B. Incorrect – it doesn’t include the schema itself in the message.
- C. Incorrect – the field is not ignored.
- D. Incorrect – the Proxy supports this field.
```

</details>

---

## Question 2

```markdown
What is the purpose of the `key.converter` and `value.converter` configurations in the Confluent REST Proxy?
```

**Options**

```markdown
- A. To specify the format of the message key and value in the produced messages
- B. To specify the serialization format for the message key and value in the REST API requests and responses
- C. To specify the compression type for the message key and value
- D. To specify the schema ID for the message key and value
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
The converters determine serialization in HTTP communication.

- A. Incorrect – not about Kafka message format.
- B. Correct – applies to HTTP layer serialization.
- C. Incorrect – compression is separate.
- D. Incorrect – schema IDs are in the payload, not converter configs.
```

</details>

---

## Question 3

```markdown
How does the Confluent REST Proxy handle authentication and authorization for production and consumption of messages?
```

**Options**

```markdown
- A. The REST Proxy performs authentication and authorization based on the Kafka ACLs configured in the brokers
- B. The REST Proxy uses its own authentication and authorization mechanism independent of Kafka
- C. The REST Proxy relies on the Schema Registry for authentication and authorization
- D. The REST Proxy does not support authentication and authorization
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
The REST Proxy enforces its own auth and authorization at the HTTP layer.

- A. Incorrect – it doesn’t rely on Kafka ACLs.
- B. Correct – supports basic and JWT auth independently.
- C. Incorrect – Schema Registry is not responsible.
- D. Incorrect – it does support auth mechanisms.
```

</details>

---

## Question 4

```markdown
What is the purpose of the Kafka REST Proxy?
```

**Options**

```markdown
- A. To provide a RESTful interface for producing and consuming messages in Kafka
- B. To manage Kafka clusters and monitor their health
- C. To store and retrieve Avro schemas for Kafka messages
- D. To stream data between Kafka and external systems
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
The REST Proxy enables HTTP access to Kafka.

- A. Correct – it exposes Kafka’s capabilities over REST.
- B. Incorrect – that’s Kafka Manager’s role.
- C. Incorrect – that's Schema Registry's role.
- D. Incorrect – Kafka Connect does that.
```

</details>

---

## Question 5

```markdown
Which HTTP method is used to produce messages to a Kafka topic via the REST Proxy?
```

**Options**

```markdown
- A. GET
- B. POST
- C. PUT
- D. DELETE
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Producing messages involves sending data, which aligns with POST semantics.

- A. Incorrect – GET is for reading.
- B. Correct – POST sends data to Kafka.
- C. Incorrect – PUT is for idempotent writes, not used here.
- D. Incorrect – DELETE is for removing resources.
```

</details>

---

## Question 6

```markdown
How does the Kafka REST Proxy handle consumer offsets?
```

**Options**

```markdown
- A. It stores consumer offsets in a separate Kafka topic
- B. It manages consumer offsets using Zookeeper
- C. It relies on the Kafka brokers to store consumer offsets
- D. It does not manage consumer offsets
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Kafka brokers manage offsets internally.

- A. Incorrect – offset topic is not separate or custom.
- B. Incorrect – Kafka no longer uses Zookeeper for offsets.
- C. Correct – stored in `__consumer_offsets` topic by brokers.
- D. Incorrect – offsets are managed, just not by REST Proxy directly.
```

</details>

---

## Question 7

```markdown
What is the purpose of the `consumer.request.timeout.ms` configuration parameter in the Kafka REST Proxy?
```

**Options**

```markdown
- A. To set the maximum time to wait for a message to be consumed
- B. To set the maximum time to wait for a response from the Kafka broker
- C. To set the maximum time to keep a consumer instance alive without further requests
- D. To set the maximum time to wait for a consumer to join a consumer group
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
This controls idle timeout for consumer instances.

- A. Incorrect – not about waiting for a message.
- B. Incorrect – that’s broker-level.
- C. Correct – controls lifecycle of consumer instance.
- D. Incorrect – group coordination is managed internally.
```

</details>

---

## Question 8

```markdown
How does the Kafka REST Proxy handle authentication?
```

**Options**

```markdown
- A. It uses Kafka's native authentication mechanisms
- B. It supports basic authentication using username and password
- C. It relies on SSL/TLS for authentication
- D. It does not provide built-in authentication mechanisms
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
REST Proxy supports HTTP Basic Auth.

- A. Incorrect – does not integrate directly with Kafka auth.
- B. Correct – uses HTTP headers for basic auth.
- C. Incorrect – SSL provides encryption, not auth alone.
- D. Incorrect – it does provide auth.
```

</details>

---

## Question 9

```markdown
What is the role of the `id` field in the request payload when producing messages via the Kafka REST Proxy?
```

**Options**

```markdown
- A. It specifies the Kafka topic to produce the message to
- B. It represents the key of the message
- C. It uniquely identifies the message within the Kafka cluster
- D. It is an optional field used for client-side message tracking
```

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**

```markdown
The `id` field is for client use only.

- A. Incorrect – topic is specified in the URL.
- B. Incorrect – the message key is separate.
- C. Incorrect – Kafka doesn’t use this field.
- D. Correct – helps clients track requests/responses.
```

</details>

---

## Question 10

```markdown
How can you configure the Kafka REST Proxy to use SSL/TLS for secure communication?
```

**Options**

```markdown
- A. Set `ssl.enabled` to `true` in the REST Proxy configuration
- B. Enable SSL/TLS in the Kafka broker configuration
- C. Configure SSL/TLS in the client application code
- D. No additional configuration is required
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
The REST Proxy has its own SSL config.

- A. Correct – `ssl.enabled=true` enables HTTPS support.
- B. Incorrect – this is for Kafka, not REST Proxy.
- C. Incorrect – REST Proxy must be configured, not the client alone.
- D. Incorrect – you must explicitly enable SSL.
```

</details>

---

## Question 11

```markdown
What data format isn't natively available with the Confluent REST Proxy?
```

**Options**

```markdown
- A. protobuf
- B. binary
- C. avro
- D. json
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
Protocol Buffers are not natively supported.

- A. Correct – not supported natively.
- B. Incorrect – binary is supported.
- C. Incorrect – Avro is natively supported.
- D. Incorrect – JSON is supported.
```

</details>

## Question 12

```markdown
Which HTTP method is used to send (produce) records to a Kafka topic via REST Proxy?
```

**Options**

```markdown
- A. GET
- B. PUT
- C. POST
- D. PATCH
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. GET is used to fetch data, not send it.
- B. PUT is used for updates, not for producing messages.
- C. POST is the correct method to produce messages to Kafka topics.
- D. PATCH is not supported for this operation.
```

</details>

---

## Question 13

```markdown
What is the correct Content-Type header when sending JSON messages using the Kafka REST Proxy?
```

**Options**

```markdown
- A. application/json
- B. application/kafka+json
- C. application/vnd.kafka.json.v2+json
- D. application/x.kafka.v1+json
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Too generic; Kafka REST Proxy requires versioned MIME types.
- B. Invalid MIME type for Kafka.
- C. Correct Kafka-specific MIME type for JSON messages.
- D. Not a valid Kafka REST Proxy content type.
```

</details>

---

## Question 14

```markdown
What field must you include in the payload to control the partition a message is sent to via REST Proxy?
```

**Options**

```markdown
- A. topic
- B. offset
- C. partition
- D. index
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. The topic is part of the URL, not the message body.
- B. Offsets are managed by the broker, not set in the payload.
- C. The "partition" field determines which partition the record is sent to.
- D. "index" is not a recognized field in Kafka message structure.
```

</details>

---

## Question 15

```markdown
Which endpoint is used to create a new consumer instance in REST Proxy?
```

**Options**

```markdown
- A. /topics/{topic}/consumers
- B. /consumers/{group}
- C. /groups/{group}/create
- D. /kafka/subscribe
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- A. Not a valid REST Proxy endpoint.
- B. Correct: This is the endpoint used to create a consumer in a group.
- C. REST Proxy doesn't use this pattern.
- D. This endpoint does not exist.
```

</details>

---

## Question 16

```markdown
After creating a consumer, which REST Proxy endpoint is used to subscribe it to topics?
```

**Options**

```markdown
- A. /subscribe
- B. /consumers/{group}/topics
- C. /consumers/{group}/instances/{instance}/subscription
- D. /groups/{group}/instances/{instance}/subscribe
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Too generic; not a valid Kafka REST endpoint.
- B. Incorrect REST Proxy pattern.
- C. Correct: This endpoint is used to subscribe the consumer to topics.
- D. Invalid endpoint structure.
```

</details>

---

## Question 17

```markdown
What is the correct way to fetch messages from a Kafka consumer via REST Proxy?
```

**Options**

```markdown
- A. GET /topics/{topic}/messages
- B. GET /consumers/{group}/instances/{instance}/records
- C. POST /consumers/{group}/records
- D. GET /records/{topic}
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- A. Invalid endpoint.
- B. Correct: This is the endpoint for polling new records.
- C. POST is not used for polling messages.
- D. Not a supported REST Proxy endpoint.
```

</details>

---

## Question 18

```markdown
How do you safely delete a REST Proxy consumer instance?
```

**Options**

```markdown
- A. Use CLI tools
- B. Send DELETE to /consumers/{group}/instances/{instance}
- C. POST a "delete" message to the topic
- D. Stop the REST Proxy service
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- A. CLI tools are not used for REST Proxy operations.
- B. Correct: This releases consumer resources.
- C. Kafka doesn't interpret messages as commands.
- D. Stopping the proxy is not a safe way to manage consumers.
```

</details>

---

## Question 19

```markdown
What format can you use to send Avro data through REST Proxy?
```

**Options**

```markdown
- A. application/json
- B. application/vnd.kafka.avro.v2+json
- C. application/avro
- D. application/octet-stream
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- A. Too generic for schema-based formats.
- B. Correct: Required for sending Avro via REST Proxy with Schema Registry.
- C. Not used in REST Proxy context.
- D. Binary format is not accepted here.
```

</details>

---

## Question 20

```markdown
What does the REST Proxy return if you send a message that doesn't match the registered schema?
```

**Options**

```markdown
- A. 200 OK
- B. 404 Not Found
- C. 422 Unprocessable Entity
- D. 403 Forbidden
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Would only happen if message is accepted.
- B. Not related to schema validation.
- C. Correct: Schema validation failure leads to 422.
- D. 403 is for permission issues, not validation.
```

</details>

---

## Question 21

```markdown
Which of the following does REST Proxy NOT support?
```

**Options**

```markdown
- A. Sending Avro messages
- B. Creating Kafka topics
- C. Subscribing consumers to topics
- D. Committing offsets
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- A. Fully supported with Schema Registry.
- B. Correct: Topic creation is not supported via REST Proxy.
- C. Supported via subscription endpoint.
- D. Supported with explicit commit call.
```

</details>

---

## Question 22

```markdown
What is the format of the request body when producing multiple messages in one call?
```

**Options**

```markdown
- A. Single JSON object per message
- B. Newline-delimited records
- C. JSON with a "records" array
- D. Base64-encoded binary
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Used for single messages only.
- B. Not a supported format in REST Proxy.
- C. Correct: You send an array of records in a "records" field.
- D. Not needed unless sending binary data explicitly.
```

</details>

---

## Question 23

```markdown
Which component is required for REST Proxy to support Avro, JSON Schema, or Protobuf?
```

**Options**

```markdown
- A. Kafka Streams
- B. Kafka Connect
- C. Schema Registry
- D. Zookeeper
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Irrelevant to serialization.
- B. Used for integration, not schema resolution.
- C. Correct: REST Proxy uses Schema Registry to validate/serialize data.
- D. Not involved in schema management.
```

</details>

---

## Question 24

```markdown
Is the REST Proxy suitable for high-throughput, low-latency applications?
```

**Options**

```markdown
- A. Yes, it’s optimized for that.
- B. No, it's designed for admin purposes only.
- C. Not ideal, better for convenience and compatibility.
- D. Only when used with Kafka Connect.
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Not optimized for high throughput.
- B. It's used for both production and consumption, not just admin tasks.
- C. Correct: REST Proxy is great for compatibility, but native clients perform better.
- D. Kafka Connect is unrelated to REST Proxy performance.
```

</details>

---

## Question 25

```markdown
What happens if you create a consumer instance but do not delete it after use?
```

**Options**

```markdown
- A. Nothing, it's auto-cleaned instantly
- B. Messages will be lost
- C. Resources are held until timeout or manual deletion
- D. The broker shuts down
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. No immediate auto-cleanup.
- B. Messages are not lost unless consumer misbehaves.
- C. Correct: The proxy holds consumer state until timeout or deletion.
- D. The broker is unaffected.
```

</details>

---

## Question 26

```markdown
How does the REST Proxy determine which partition to write to when no key or partition is provided?
```

**Options**

```markdown
- A. Always partition 0
- B. Last used partition
- C. Random partition
- D. Round-robin partitioning
```

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**

```markdown
- A. Would create hot partitions.
- B. Not how partitioning works in Kafka.
- C. Kafka does not use random; it uses a deterministic strategy.
- D. Correct: Kafka REST Proxy defaults to round-robin partitioning when no key/partition is provided.
```

</details>

---


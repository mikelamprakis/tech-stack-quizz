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
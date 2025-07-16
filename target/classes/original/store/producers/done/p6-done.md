## Question 36

```markdown
What happens when `max.in.flight.requests.per.connection` is set to 1 and `enable.idempotence` is set to true in the Kafka producer configuration?
```

**Options**

```markdown
- A. The producer will send messages in batches to improve throughput
- B. The producer will wait for each request to be acknowledged before sending the next request
- C. The producer will retry failed requests automatically
- D. The producer will disable message compression
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
With `max.in.flight.requests.per.connection=1` and `enable.idempotence=true`, the producer sends one message at a time per connection and waits for acknowledgment before proceeding. This guarantees strict ordering and exactly-once delivery but may reduce throughput.

- A. Incorrect – batching is unrelated to these specific settings.
- B. Correct – ensures strict order and delivery guarantees.
- C. Retries may occur but are not directly caused by this setting combination.
- D. Compression is not affected by idempotence or inflight settings.
```

</details>

---

## Question 37

```markdown
How does enabling idempotence affect the performance of the Kafka producer?
```

**Options**

```markdown
- A. It significantly improves the producer's throughput
- B. It has no impact on the producer's performance
- C. It may slightly reduce the producer's throughput
- D. It increases the producer's memory usage
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Enabling idempotence adds overhead due to extra metadata and tracking per message to ensure exactly-once delivery. While the performance impact is small, throughput may be slightly reduced.

- A. Incorrect – it adds overhead, not boosts.
- B. Incorrect – there is some measurable impact.
- C. Correct – slight decrease in throughput due to tracking logic.
- D. Incorrect – memory usage is not notably affected.
```

</details>

---

## Question 38

```markdown
What does the `acks=all` setting in the Kafka producer configuration ensure?
```

**Options**

```markdown
- A. The producer will receive an acknowledgment only after the message is written to all replicas
- B. The producer will receive an acknowledgment only after the message is written to the leader replica
- C. The producer will receive an acknowledgment only after the message is written to all in-sync replicas
- D. The producer will not wait for any acknowledgment and will consider the write successful immediately
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Setting `acks=all` ensures that a message is acknowledged only after all in-sync replicas (ISRs) have persisted it. This setting provides the highest durability guarantee but may introduce additional latency.

- A. Incorrect – all replicas, including out-of-sync ones, are not required.
- B. Incorrect – that's `acks=1`.
- C. Correct – acknowledges after all ISRs write the message.
- D. Incorrect – that's `acks=0`.
```

</details>

---

## Question 39

```markdown
What is the purpose of the `client.id` setting in the Kafka producer and consumer configurations?
```

**Options**

```markdown
- A. To specify a unique identifier for the client within a Kafka cluster
- B. To set the maximum number of requests the client can send or receive
- C. To determine the compression type used for message production or consumption
- D. To control the maximum amount of memory the client can use for buffering
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
The `client.id` helps uniquely identify clients in logs and metrics. It is useful for monitoring and debugging but has no impact on message delivery, memory usage, or compression.

- A. Correct – `client.id` is used for tracking/logging.
- B. Incorrect – not related to throughput or rate limits.
- C. Incorrect – compression is configured separately.
- D. Incorrect – buffering is controlled by other configs.
```

</details>

---

## Question 40

```markdown
What happens if multiple Kafka clients use the same `client.id` value?
```

**Options**

```markdown
- A. The clients will share the same configuration and connection pooling
- B. The clients will be treated as a single logical client by the Kafka brokers
- C. The behavior is undefined, and it may lead to unexpected results or errors
- D. The Kafka brokers will reject the connection attempts from clients with duplicate `client.id`
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Using the same `client.id` across multiple clients can cause confusion in monitoring and metrics. Kafka does not enforce uniqueness, and while it won’t reject connections, it may lead to misattributed logs or unpredictable behavior.

- A. Incorrect – no configuration or connection sharing happens.
- B. Incorrect – brokers track connections independently.
- C. Correct – duplicate IDs confuse monitoring/logs.
- D. Incorrect – Kafka allows duplicate IDs.
```

</details>

---

## Question 41

```markdown
If a producer sends a message with a key to a topic with 5 partitions, which partition will the message be written to?
```

**Options**

```markdown
- A. The partition is randomly selected
- B. The partition is determined based on the hash of the message key
- C. The partition is always the first partition (partition 0)
- D. The partition is determined by the broker
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
When a message has a key, the Kafka producer uses the murmur2 hash of the key to determine the partition. This ensures that messages with the same key are routed to the same partition, preserving order.

- A. Incorrect – hashing provides deterministic selection.
- B. Correct – uses key's hash for partitioning.
- C. Incorrect – not always partition 0.
- D. Incorrect – producer, not broker, decides.
```

</details>

---

## Question 42

```markdown
What happens if a producer sends a message without a key to a topic with 3 partitions?
```

**Options**

```markdown
- A. The message is discarded
- B. The message is sent to a randomly selected partition
- C. The message is sent to all partitions
- D. The message is sent to the partition with the least amount of data
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
When a producer sends a message without a key to a topic, Kafka’s default partitioner uses a round-robin approach to distribute messages across all available partitions.

- A. Incorrect – message is not discarded.
- B. Correct – round-robin (effectively random) partition selection.
- C. Incorrect – not broadcast to all partitions.
- D. Incorrect – Kafka does not inspect partition size/load.
```

</details>

---

Let me know if you'd like these in a downloadable format or converted into [flashcards](f) or [quiz form](f).

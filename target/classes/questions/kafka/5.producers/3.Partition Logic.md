## Question 1

```markdown
What happens when a Kafka producer sends a message without a key?
```

**Options**

```markdown
- A. Kafka assigns it to the same partition every time
- B. Kafka assigns it to partition 0 by default
- C. Kafka distributes it round-robin across partitions
- D. Kafka randomly selects a partition with the fewest messages
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Without a key, no consistent partitioning occurs.
- B. Partition 0 is not the default unless hardcoded.
- C. Kafka uses round-robin for keyless messages.
- D. Kafka does not balance based on message count.
```

</details>

---

## Question 2

```markdown
Why would you use a message key in Kafka producer records?
```

**Options**

```markdown
- A. To improve compression
- B. To enable schema evolution
- C. To guarantee per-key ordering
- D. To avoid needing a partition
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Compression is unrelated to the message key.
- B. Schema evolution is managed by serialization formats.
- C. Message keys ensure all events with the same key go to the same partition.
- D. Partitioning still occurs even with a key.
```

</details>

---

## Question 3

```markdown
Which interface must be implemented to define custom Kafka partitioning logic?
```

**Options**

```markdown
- A. PartitionerFunction
- B. KafkaProducerRouter
- C. org.apache.kafka.clients.producer.Partitioner
- D. org.apache.kafka.common.ClusterRouter
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Not a real interface in Kafka.
- B. Made up.
- C. Correct – this is the Kafka partitioning interface.
- D. Cluster is a class, but not related to routing logic.
```

</details>

---

## Question 4

```markdown
If a key is provided to the producer, what partitioning strategy does Kafka use?
```

**Options**

```markdown
- A. It selects the partition randomly
- B. It uses round-robin across partitions
- C. It uses a hash of the key to pick a partition
- D. It requires a custom partitioner
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Random partitioning only applies with no key and no round-robin.
- B. Round-robin only happens when no key is provided.
- C. Kafka hashes the key to consistently assign a partition.
- D. Custom partitioners are optional, not required.
```

</details>

---

## Question 5

```markdown
What is a potential risk of using the same key repeatedly in Kafka?
```

**Options**

```markdown
- A. It can cause data loss
- B. It may trigger topic deletion
- C. It can create a hot partition
- D. It bypasses the serialization process
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Kafka replication protects against data loss.
- B. Key reuse doesn't affect topic retention policies.
- C. Overusing the same key leads to all messages going to the same partition (hot partition).
- D. Serialization is still required regardless of partitioning.
```

</details>

---

## Question 6

```markdown
What is true about round-robin partitioning in Kafka?
```

**Options**

```markdown
- A. It requires a message key
- B. It guarantees ordering of events
- C. It distributes messages evenly across partitions
- D. It is used only with custom partitioners
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. It’s used when no key is set.
- B. No ordering is guaranteed across partitions.
- C. Round-robin helps balance load evenly.
- D. Round-robin is built-in, not custom.
```

</details>

---

## Question 7

```markdown
In a custom partitioner, what determines how messages are routed?
```

**Options**

```markdown
- A. The producer’s buffer size
- B. The return value of the `partition()` method
- C. The record metadata
- D. The topic's cleanup policy
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- A. Buffer size affects batching, not routing.
- B. The `partition()` method directly controls which partition is used.
- C. Metadata is assigned after the partition is chosen.
- D. Cleanup policy affects retention, not routing.
```

</details>

---

## Question 8

```markdown
Which of the following is NOT a valid reason to implement a custom partitioner?
```

**Options**

```markdown
- A. To route messages by user region
- B. To balance hot keys across partitions
- C. To replace the serializer
- D. To implement business-based routing logic
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Valid – region-based routing is a common use case.
- B. Valid – advanced partitioners can balance hot keys.
- C. Incorrect – serializers are configured separately from partitioners.
- D. Valid – custom business logic is a strong use case.
```

</details>

---


## Question 9

```markdown
Where will a message with no key be stored?
```

**Options**

```markdown
- A. The first partition of the topic
- B. A random topic partition
- C. Any of the topic partitions using round-robin
- D. The partition with least messages
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Kafka producers use round-robin partitioning when no key is provided, spreading messages evenly across partitions.

- A. Not guaranteed — depends on round-robin rotation
- B. Random suggests non-deterministic; not quite accurate
- C. Correct — round-robin is the default partitioner strategy without keys
- D. Kafka does not check partition sizes
```

</details>

## Question 10

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

## Question 11

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

## Question 12

```markdown
Can a producer guarantee the order of messages within a partition when sending messages with different keys?
```

**Options**

```markdown
- A. Yes, messages within a partition are always guaranteed to be in the same order as they were sent by the producer
- B. No, messages with different keys can be written to the same partition in a different order than they were sent
- C. It depends on the configuration of the producer
- D. It depends on the configuration of the topic
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Kafka guarantees ordering *within a partition* for messages with the *same key*. When different keys are used, messages may be routed to different partitions (based on key hash), and even if routed to the same partition, ordering is not guaranteed. Partition assignment and write timing can lead to interleaving of messages.

- A. Misleading—ordering is not universally guaranteed for all keys.
- B. Correct—ordering only applies to the same key in a partition.
- C. Incorrect—ordering is not controlled purely by configuration but by key-based partitioning.
- D. Irrelevant—the topic configuration doesn’t influence per-key ordering.
```

</details>

---

## Question 1

```markdown
When a Kafka producer sends messages without a specified key, how does Kafka determine the partition to which the message is sent?
```

**Options**

```markdown
- A. It sends the message to partition 0 by default
- B. It randomly picks a partition at runtime
- C. It uses a round-robin algorithm among all available partitions
- D. It hashes the serialized message value to choose the partition
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Incorrect – there’s no default static partition.
- B. Incorrect – randomness is not used; round-robin is deterministic.
- C. Correct – Kafka uses round-robin for keyless messages to balance load.
- D. Incorrect – hashing applies only when a key is provided.
```

</details>

---

## Question 2

```markdown
Which of the following best describes what happens when there are more consumers in a consumer group than partitions in a topic?
```

**Options**

```markdown
- A. Kafka throws a ConsumerGroupOverloadException
- B. Some consumers remain idle and do not receive any partitions
- C. The extra consumers share partitions in a round-robin fashion
- D. Kafka splits partitions dynamically to fit all consumers
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- A. Incorrect – Kafka doesn’t throw such an exception.
- B. Correct – extra consumers remain idle since each partition can only be assigned to one consumer in a group.
- C. Incorrect – partitions are not shared between consumers within the same group.
- D. Incorrect – partition count is fixed unless manually changed.
```

</details>

---

## Question 3

```markdown
In Kafka, what is the effect of setting `acks=all` in the producer configuration?
```

**Options**

```markdown
- A. The producer sends the message to all partitions in the topic
- B. The broker stores the message in multiple internal topics
- C. The leader waits for acknowledgment from all in-sync replicas before acknowledging the producer
- D. The message is automatically replicated to all brokers
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Incorrect – producers send to one partition only.
- B. Incorrect – internal topics are unrelated here.
- C. Correct – `acks=all` ensures high durability by waiting for all ISR acknowledgments.
- D. Incorrect – replication depends on the topic’s replication factor, not `acks`.
```

</details>

---

## Question 4

```markdown
What is the primary role of the partition key in Kafka producer messages?
```

**Options**

```markdown
- A. To compress messages in the same batch
- B. To determine the message order across topics
- C. To determine which partition the message is routed to
- D. To add metadata to the message headers
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Incorrect – compression happens at the batch level, not key level.
- B. Incorrect – order is preserved per partition, not across topics.
- C. Correct – keys are hashed to determine partition assignment.
- D. Incorrect – keys are separate from message headers.
```

</details>

---

## Question 5

```markdown
Which of the following scenarios ensures message ordering for a specific key in Kafka?
```

**Options**

```markdown
- A. Using a single partition with multiple consumers
- B. Using the same key for all messages and writing to multiple partitions
- C. Using the same key for all messages, ensuring they go to the same partition
- D. Using round-robin partitioning and enabling idempotence
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Incorrect – only one consumer can read from a single partition in a group.
- B. Incorrect – same key always maps to the same partition.
- C. Correct – Kafka preserves message order per key within a partition.
- D. Incorrect – round-robin does not preserve key-based ordering.
```

</details>

---

## Question 6

```markdown
When using `enable.auto.commit=true`, what is the default behavior of the consumer regarding offset commits?
```

**Options**

```markdown
- A. Commits offset after processing each message
- B. Commits offsets only on consumer shutdown
- C. Commits offsets periodically in the background
- D. Commits offsets after every poll() call regardless of processing
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Incorrect – not every message triggers a commit.
- B. Incorrect – that applies to manual commit with shutdown hooks.
- C. Correct – offsets are auto-committed in the background at a fixed interval (default: 5 seconds).
- D. Incorrect – commits don’t happen after every poll unless manually invoked.
```

</details>

---

## Question 7

```markdown
Which of the following can cause duplicate messages when consuming from Kafka?
```

**Options**

```markdown
- A. Using `acks=1` in the producer and committing offsets before message processing
- B. Using `acks=all` and enabling idempotence in the producer
- C. Setting `enable.auto.commit=false` and using manual offset commit after processing
- D. Using Kafka transactions with transactional.id
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
- A. Correct – committing offsets before processing can lead to duplicate messages on consumer crash.
- B. Incorrect – this is a safe setup.
- C. Incorrect – this is the recommended way to ensure exactly-once (with idempotence).
- D. Incorrect – Kafka transactions are designed to prevent duplicates.
```

</details>

---

## Question 8

```markdown
What happens if a consumer fails after polling messages but before committing the offsets?
```

**Options**

```markdown
- A. The messages are lost and never delivered again
- B. Kafka reassigns the partitions and the new consumer reprocesses those messages
- C. Kafka deletes the uncommitted messages immediately
- D. The consumer group enters a deadlock state
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- A. Incorrect – Kafka retains uncommitted messages.
- B. Correct – uncommitted offsets cause reprocessing after rebalance.
- C. Incorrect – Kafka relies on retention policies, not commit state, for deletion.
- D. Incorrect – consumer group continues normally after rebalance.
```

</details>

---

## Question 9

```markdown
How does Kafka ensure that a consumer in a group gets a unique set of partitions?
```

**Options**

```markdown
- A. By having each consumer subscribe to specific partitions
- B. Through consumer group coordination and partition rebalancing
- C. By assigning partitions randomly at runtime
- D. Using the key of each message to route messages to consumers
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- A. Incorrect – static assignment is possible but uncommon.
- B. Correct – the group coordinator assigns partitions uniquely during rebalances.
- C. Incorrect – partition assignment is not random.
- D. Incorrect – message keys affect partitioning, not consumer routing.
```

</details>

---

## Question 10

```markdown
Which Kafka setting helps maintain message order during retries and batching?
```

**Options**

```markdown
- A. `compression.type`
- B. `max.in.flight.requests.per.connection`
- C. `acks`
- D. `linger.ms`
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- A. Incorrect – affects compression, not ordering.
- B. Correct – setting this to 1 prevents out-of-order sends when retries happen.
- C. Incorrect – controls durability, not ordering.
- D. Incorrect – affects batching, not message order directly.
```

</details>

---

## Question 11

```markdown
What is the default behavior when a Kafka producer fails to send a message after retries are exhausted?
```

**Options**

```markdown
- A. The producer automatically redirects the message to another partition
- B. The producer silently drops the message and logs a warning
- C. The producer throws a `TimeoutException` or `RetriableException`
- D. The producer queues the message for future delivery
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Incorrect – partitions are not switched automatically.
- B. Incorrect – exceptions are thrown, not warnings.
- C. Correct – producers raise exceptions if retries fail.
- D. Incorrect – the failed message is not queued indefinitely.
```

</details>

---

## Question 12

```markdown
Why might a consumer lag behind in offset consumption?
```

**Options**

```markdown
- A. The topic has too many partitions
- B. The consumer is committing offsets too frequently
- C. The consumer processing logic is slower than the incoming message rate
- D. Kafka broker is throttling the consumer intentionally
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Incorrect – partition count affects parallelism, not lag directly.
- B. Incorrect – frequent commits don’t cause lag.
- C. Correct – slow processing leads to offset lag.
- D. Incorrect – consumers aren’t throttled by brokers by default.
```

</details>

---

## Question 13

```markdown
Which condition ensures exactly-once delivery semantics in Kafka?
```

**Options**

```markdown
- A. Setting `acks=1` and enabling auto-commit
- B. Using Kafka Streams with default configuration
- C. Enabling idempotence on the producer and transactions across consumer and producer
- D. Using manual offset commit with `enable.auto.commit=false`
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Incorrect – this can lead to duplicates.
- B. Incorrect – Kafka Streams needs EOS mode configured.
- C. Correct – this is the core of EOS in Kafka.
- D. Incorrect – avoids duplicates but doesn't guarantee EOS.
```

</details>

---

## Question 14

```markdown
When does Kafka reassign partitions among consumers in a group?
```

**Options**

```markdown
- A. Every time a producer sends a message
- B. Only when a new consumer subscribes to the topic
- C. Whenever a consumer joins or leaves the group
- D. At regular intervals specified in `rebalance.interval.ms`
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Incorrect – producers don’t trigger rebalances.
- B. Incorrect – it's not limited to new consumers only.
- C. Correct – group membership changes cause rebalancing.
- D. Incorrect – there is no regular rebalance interval.
```

</details>

---

## Question 15

```markdown
Which producer configuration is most critical to ensure message durability in case of broker failure?
```

**Options**

```markdown
- A. `linger.ms`
- B. `acks=all`
- C. `compression.type`
- D. `batch.size`
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- A. Affects latency, not durability.
- B. Correct – ensures messages are acknowledged by all ISR.
- C. Impacts bandwidth, not durability.
- D. Affects batching behavior, not replication guarantees.
```

</details>

---

## Question 8

```markdown
What happens when a consumer is assigned a partition that does not exist in the Kafka cluster?
```

**Options**

```markdown
- A. The consumer will ignore the non-existent partition and continue processing other assigned partitions
- B. The consumer will throw an exception and stop processing
- C. The consumer will create the partition automatically
- D. The consumer will wait until the partition is created
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
If a consumer is assigned to a non-existent partition, it will throw an exception like `UnknownTopicOrPartitionException` and stop processing. Kafka does not support dynamic creation of partitions by the consumer.

- A. Incorrect — it does not ignore.
- B. Correct — throws exception and stops.
- C. Incorrect — consumers cannot create partitions.
- D. Incorrect — consumer does not wait.
```

</details>

---

## Question 9

```markdown
Can a consumer dynamically change the partitions it is assigned to without stopping and restarting?
```

**Options**

```markdown
- A. Yes, by calling `KafkaConsumer.subscribe()` with a new set of topics
- B. Yes, by calling `KafkaConsumer.assign()` with a new set of partitions
- C. No, partition assignment can only be changed when the consumer is first started
- D. No, partition assignment is fixed for the entire lifecycle of the consumer
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Calling `assign()` with a new set of `TopicPartition` objects allows a consumer to dynamically change its partition assignments at runtime without restarting.

- A. Incorrect — subscribe changes topics, but not partitions manually.
- B. Correct — assign allows dynamic reassignment.
- C. Incorrect — assignment can change at runtime.
- D. Incorrect — assignment is not fixed forever.
```

</details>

---

## Question 10

```markdown
A consumer is part of a consumer group and is currently processing messages. If the consumer crashes and is restarted, what will happen?
```

**Options**

```markdown
- A. The consumer will resume processing from the last committed offset
- B. The consumer will start processing from the earliest available offset
- C. The consumer will start processing from the latest available offset
- D. The consumer will be assigned a new set of partitions
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
When a consumer crashes and restarts, it rejoins the group and resumes from the last committed offset. This ensures no message loss or duplication as long as offsets are committed regularly.

- A. Correct — resumes from committed offset.
- B. Incorrect — does not start from earliest unless configured.
- C. Incorrect — does not start from latest unless configured.
- D. Not guaranteed — partition reassignment depends on rebalance.
```

</details>

---

## Question 11

```markdown
What happens when a new consumer joins an existing consumer group?
```

**Options**

```markdown
- A. The new consumer will start consuming from the earliest available offset for all partitions
- B. The new consumer will start consuming from the latest available offset for all partitions
- C. The new consumer will be assigned a subset of partitions and start consuming from the last committed offset for each partition
- D. The new consumer will wait until the next rebalance before starting to consume
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
When a new consumer joins an existing group, Kafka triggers a rebalance to distribute partitions among all consumers. The new consumer gets a subset of partitions and resumes from the last committed offset for each.

- A. Incorrect — offset depends on last committed offset.
- B. Incorrect — offset depends on last committed offset.
- C. Correct — rebalance and resume from committed offsets.
- D. Incorrect — rebalance happens immediately.
```

</details>

---

## Question 12

```markdown
What is the purpose of the `group.id` property in a Kafka consumer configuration?
```

**Options**

```markdown
- A. To specify the ID of the consumer within a consumer group
- B. To specify the ID of the consumer group the consumer belongs to
- C. To specify the ID of the Kafka cluster the consumer connects to
- D. To specify the ID of the partitions the consumer should read from
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
`group.id` identifies the consumer group to which the consumer belongs. Consumers with the same group ID share partition consumption and offset commits.

- A. Incorrect — consumer instance ID is not `group.id`.
- B. Correct — identifies the consumer group.
- C. Incorrect — Kafka cluster ID is configured elsewhere.
- D. Incorrect — partitions are assigned separately.
```

</details>

---

## Question 13

```markdown
What is the default behavior of the `auto.offset.reset` configuration in Kafka consumers?
```

**Options**

```markdown
- A. It starts consuming from the earliest offset if no committed offset is found
- B. It starts consuming from the latest offset if no committed offset is found
- C. It throws an exception if no committed offset is found
- D. It waits for a committed offset to be available before starting consumption
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
By default, `auto.offset.reset` is set to `latest`. This means if no committed offset exists, the consumer starts reading from the latest offset (end of the topic).

- A. Incorrect — this is if you set it to `earliest`.
- B. Correct — default is `latest`.
- C. Incorrect — `none` causes an exception.
- D. Incorrect — it doesn't wait.
```

</details>

---

## Question 14

```markdown
What happens when a Kafka consumer with `enable.auto.commit=false` calls the `commitSync()` method?
```

**Options**

```markdown
- A. The consumer commits the offsets of the messages it has processed so far
- B. The consumer commits the offsets of the messages it has fetched but not yet processed
- C. The consumer does not commit any offsets and throws an exception
- D. The consumer waits for the next batch of messages to be processed before committing offsets
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
With `enable.auto.commit=false`, the consumer must commit offsets manually. Calling `commitSync()` commits the offsets of messages already processed.

- A. Correct — commits processed message offsets.
- B. Incorrect — fetched but unprocessed messages are not committed.
- C. Incorrect — no exception is thrown.
- D. Incorrect — commitSync() commits immediately.
```

</details>

---

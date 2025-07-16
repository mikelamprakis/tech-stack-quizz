## Question 34

```markdown
What happens if a Kafka consumer requests to read from a partition that does not exist in the specified topic?
```

**Options**

```markdown
- A. The Kafka broker will automatically create the partition and start serving data
- B. The consumer will receive an empty response, indicating that the partition does not exist
- C. The consumer will receive an error message, indicating that the requested partition does not exist
- D. The consumer will be assigned a different, existing partition to read from
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Kafka brokers return an error (e.g., `UNKNOWN_TOPIC_OR_PARTITION`) if a consumer tries to read from a non-existent partition. Brokers do not create partitions on demand, nor do they reassign consumers to other partitions automatically.

- A. Incorrect — Partitions are not auto-created.
- B. Incorrect — The broker returns an error, not empty data.
- C. Correct — An error message is returned.
- D. Incorrect — Consumer is not reassigned automatically.
```

</details>

---

## Question 22

```markdown
What happens when a consumer with `isolation.level=read_committed` encounters a message that is part of an ongoing transaction?
```

**Options**

```markdown
- A. The consumer will read the message immediately
- B. The consumer will wait until the transaction is committed before reading the message
- C. The consumer will skip the message and move on to the next one
- D. The consumer will throw an exception and stop consuming
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
When using `read_committed`, the consumer will wait until the transaction is completed (committed) before making any message from that transaction visible. This ensures transactional consistency and avoids consuming messages that might later be rolled back.

- A. The consumer would read uncommitted messages, breaking isolation.
- B. Correct — waits for transaction commit to maintain consistency.
- C. Skipping would lead to message loss.
- D. No exceptions are thrown; the consumer simply waits.
```

</details>

---

## Question 25

```markdown
What happens when a Kafka consumer is marked as dead due to exceeding the `max.poll.interval.ms` interval?
```

**Options**

```markdown
- A. The consumer is automatically rebalanced, and its partitions are reassigned to other consumers in the group
- B. The consumer receives an exception and must manually rejoin the consumer group
- C. The consumer's offset commits are rolled back, and it starts consuming from the beginning of the assigned partitions
- D. The consumer is permanently removed from the consumer group and cannot rejoin
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
When the consumer exceeds `max.poll.interval.ms`, Kafka triggers a rebalance, reassigning the dead consumer's partitions to others. The consumer can later rejoin.

- A. Correct — triggers rebalance and reassignment.
- B. No exception to consumer, handled by broker.
- C. Offset commits are not rolled back.
- D. The consumer is not permanently removed.
```

</details>

---

## Question 2

```markdown
A topic has three replicas and you set `min.insync.replicas=2`. If two out of three replicas are not available, what happens when a consume request is sent to broker?
```

**Options**

```markdown
- A. Data will be returned from the remaining in-sync replica
- B. An empty message will be returned
- C. A new leader of the partition will be elected
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
`min.insync.replicas` affects **producers** to control write availability. Consumers can still read from any available in-sync replica. So if only one replica is available and in-sync, the consumer will receive data from it.

- A. Correct — consumer reads from remaining ISR.
- B. No empty message returned.
- C. Leader election happens only if leader fails.
```

</details>

---

## Question 38

```markdown
In a topic with a replication factor of 3 and `min.insync.replicas` set to 2, what happens when a consumer sends a read request to a partition with only one in-sync replica?
```

**Options**

```markdown
- A. The consumer receives the requested data from the in-sync replica
- B. The consumer request fails with a `NotEnoughReplicasException`
- C. The consumer receives an empty response
- D. The consumer request remains pending until another replica becomes in-sync
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
`min.insync.replicas` affects write requests, not reads. Consumers can still read from any available in-sync replica, even if only one remains.

- A. Correct — Reads proceed from the available replica.
- B. Incorrect — This error applies to writes, not reads.
- C. Incorrect — Data is returned, not empty.
- D. Incorrect — Reads don’t wait for more replicas.
```

</details>

---

## Question 5

```markdown
A Kafka Streams application tries to consume from an input topic partition. It receives an 'Offset Out Of Range' error from the broker. How should the application handle this?
```

**Options**

```markdown
- A. Reset the consumer offset to the earliest offset and retry
- B. Reset the consumer offset to the latest offset and retry
- C. Trigger a shutdown of the Streams application
- D. Ignore the error and continue processing other partitions
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
- A. ✅ Correct – When offset is out of range, the application should reset to earliest to resume consumption.
- B. ❌ Skips data, causes potential loss.
- C. ❌ Too aggressive; not necessary.
- D. ❌ Risky behavior, leads to partial processing.
```

</details>

---

## Question 36

```markdown
What happens if a Kafka consumer commits an offset for a partition and then crashes before processing the next message?
```

**Options**

```markdown
- A. The consumer will resume processing from the last committed offset when it restarts
- B. The consumer will resume processing from the next message after the last committed offset when it restarts
- C. The consumer will start processing from the beginning of the partition when it restarts
- D. The consumer will be assigned a different partition to process when it restarts
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
When the consumer restarts, it resumes from the last committed offset. This might cause the last processed message to be reprocessed, ensuring no data loss but possibly duplicate processing (at-least-once semantics).

- A. Correct — Resumes from last committed offset.
- B. Incorrect — Next message after committed offset is processed after restarting.
- C. Incorrect — Restart does not mean start from the beginning.
- D. Incorrect — Partition assignment may or may not change.
```

</details>

---

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






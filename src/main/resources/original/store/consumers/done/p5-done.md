## Question 32

```markdown
When a Kafka consumer wants to read data from a specific partition, what information does it need to provide to the Kafka broker?
```

**Options**

```markdown
- A. The topic name and the consumer group ID
- B. The topic name and the offset to start reading from
- C. The topic name, partition number, and offset to start reading from
- D. The topic name, partition number, and consumer group ID
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
To read from a specific partition, the consumer must specify the topic, partition number, and the offset. The consumer group ID is only relevant in automatic assignment mode, not when explicitly reading a specific partition.

- A. Incorrect — Group ID alone does not specify partition or offset.
- B. Incorrect — Partition number is also needed.
- C. Correct — Topic, partition, and offset are all required.
- D. Incorrect — Group ID is irrelevant here.
```

</details>

---

## Question 33

```markdown
How does a Kafka consumer determine which broker to connect to when reading data from a specific partition?
```

**Options**

```markdown
- A. The consumer connects to any available broker and requests the leader for the specific partition
- B. The consumer connects to the Zookeeper ensemble to determine the leader for the specific partition
- C. The consumer uses a round-robin algorithm to select a broker to connect to
- D. The consumer connects to all brokers in the cluster simultaneously
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
The consumer first contacts any broker to get metadata about the topic and partitions. This metadata includes which broker is the leader for the partition. The consumer then connects directly to that leader to fetch data.

- A. Correct — Connects to any broker for metadata, then to leader.
- B. Incorrect — Consumers do not contact Zookeeper.
- C. Incorrect — Selection is based on metadata, not round-robin.
- D. Incorrect — Consumer connects only to the partition leader broker.
```

</details>

---

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

## Question 35

```markdown
When a Kafka consumer commits offsets, what information is included in the commit request?
```

**Options**

```markdown
- A. The consumer group ID and the last processed offset for each partition
- B. The consumer group ID and the next offset to be processed for each partition
- C. The consumer ID and the last processed offset for each partition
- D. The consumer ID and the next offset to be processed for each partition
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
Offset commits include the consumer group ID and the last processed offset for each partition. The consumer ID itself is not included because offsets are tracked at the group level.

- A. Correct — Group ID and last processed offset.
- B. Incorrect — It is the last processed offset, not the next.
- C. Incorrect — Consumer ID is not part of commit.
- D. Incorrect — Next offset is not committed, last processed is.
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

## Question 37

```markdown
What is the purpose of the `enable.auto.commit` configuration property in Kafka consumers?
```

**Options**

```markdown
- A. To automatically commit offsets at a fixed interval
- B. To automatically commit offsets after each message is processed
- C. To enable or disable automatic offset commits
- D. To specify the maximum number of offsets to commit in a single request
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
The `enable.auto.commit` flag enables or disables automatic offset committing. If set to `true`, offsets are committed periodically (as controlled by `auto.commit.interval.ms`). If `false`, the application must manually commit offsets.

- A. Incorrect — Interval is controlled by a different setting.
- B. Incorrect — Auto commit happens periodically, not after each message.
- C. Correct — Enables or disables automatic commits.
- D. Incorrect — No such max offset count setting.
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

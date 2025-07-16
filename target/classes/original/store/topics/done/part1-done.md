## Question 1

```markdown
What is true about partitions? (select two)
```

**Options**

```markdown
- A. A partition has one replica that is leader, while the other replicas are followers.
- B. A broker can have different partitions for the same topic on its disk.
- C. A broker can have both the partition and the replica of the topic on its disk.
- D. You cannot have more partitions than the number of brokers in your cluster.
```

<details><summary>Response:</summary>

**Answer:** A, B

**Explanation:**

```markdown
- A. Correct — Only one replica is elected as the partition leader; others are followers.
- B. Correct — A broker can hold many partitions of the same topic on its disk.
- C. Incorrect — A broker cannot have the same partition and its replica simultaneously.
- D. Incorrect — You can have more partitions than brokers; partitions can be distributed unevenly.
```

</details>

---

## Question 2

```markdown
Your topic is log compacted and you are sending a message with the key K and value null. What will happen?
```

**Options**

```markdown
- A. The message will be ignored by the Kafka broker.
- B. The producer will throw a runtime Exception.
- C. The broker will delete all the messages of the Key K on cleanup.
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Sending a message with a null value is called a tombstone in Kafka. It signals the broker to delete messages with the key K during log compaction.

- A. Incorrect — The message is not ignored; it triggers deletion on compaction.
- B. Incorrect — No exception is thrown by the producer.
- C. Correct — The broker deletes all messages with key K during cleanup.
```

</details>

---

## Question 3

```markdown
Compaction is enabled for a topic in Kafka by setting `log.cleanup.policy=compact`. What is true about log compaction?
```

**Options**

```markdown
- A. Each message stored in the topic is compressed.
- B. Compaction changes the offset of the message.
- C. After cleanup, only one message per key is retained with the latest value.
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Incorrect — Log compaction does not compress messages; it retains only the latest record per key.
- B. Incorrect — Message offsets remain unchanged during compaction.
- C. Correct — After cleanup, only the latest message per key is kept.
```

</details>

---

## Question 4

```markdown
When `auto.create.topics.enable` is set to true in Kafka configuration, under which circumstances does a Kafka broker automatically create a topic? (select three)
```

**Options**

```markdown
- A. Producer sends message to the topic.
- B. Consumer reads message from the topic.
- C. Client requests metadata for the topic.
- D. Client alters the number of partitions of a topic.
```

<details><summary>Response:</summary>

**Answer:** A, B, C

**Explanation:**

```markdown
A Kafka broker auto-creates a topic when:

- A. Producer starts writing messages to the topic.
- B. Consumer starts reading messages from the topic.
- C. Any client requests metadata for the topic.

- D. Incorrect — Altering partitions does not trigger auto-creation.
```

</details>

## Question 5

```markdown
What is true about partitions? (select two)
```

**Options**

```markdown
- A. A partition has a one replica that is leader, while the others replicas are follower.
- B. A broker can have the differnt partition for the same topic on its disk
- C. A broker can have the partition and the replica of topic on its disk
- D. You can not have more partition than the no of broker in your cluster.
```

<details><summary>Response:</summary>

**Answer:** A, B

**Explanation:**

```markdown
- A. Only one of the replicas is elected as partition leader.
- B. A broker can definitely hold many partitions from the same topic on its disk, e.g., a topic with 12 partitions on one broker.
- C. Incorrect, a broker holds either the leader or a replica of a partition but not both at the same time.
- D. Incorrect, you can have more partitions than brokers.
```

</details>

---

## Question 6

```markdown
Your topic is log compacted and you are sending a message with the key K and value null. What will happen?
```

**Options**

```markdown
- A. The message will be ignored by the kafka broker.
- B. The producer will throw run time Exception.
- C. The broker will delete all the messages of the Key K on cleanup.
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Sending a message with a null value is called a tombstone in Kafka and signals log compaction to remove all messages with the key K during cleanup.
- A. Incorrect, message is not ignored.
- B. Incorrect, no exception thrown.
- C. Correct, triggers deletion of messages with that key on compaction.
```

</details>

---

## Question 7

```markdown
Compaction is enabled for a topic in Kafka by setting log.cleanup.policy=compact. What is true about log compaction?
```

**Options**

```markdown
- A. Each message stored in the topic is compressed
- B. Compaction changes the offset of the message
- C. After Cleanup, only one message per key is retained with the latest value
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Log compaction retains the last known value for each key in a partition.
- A. Incorrect, compaction is not compression.
- B. Incorrect, compaction does not change message offsets.
- C. Correct, only one message per key with latest value is retained after cleanup.
```

</details>

---

## Question 8

```markdown
When auto.create.topics.enable is set to true in Kafka configuration,
what are the circumstances under which a Kafka broker automatically creates a topic? (select three)
```

**Options**

```markdown
- A. Producer send message to the topic
- B. Consumer reads message from the topic
- C. Client request the metadata for the topic.
- D. Client Alters the no of partitions of a topic
```

<details><summary>Response:</summary>

**Answer:** A, B, C

**Explanation:**

```markdown
A Kafka broker automatically creates a topic under the following circumstances:
- When a producer starts writing messages to the topic
- When a consumer starts reading messages from the topic
- When any client requests metadata for the topic

D is incorrect, altering partitions does not trigger auto-creation.
```

</details>

---

## Question 9

```markdown
Which of the following statements about `acks` and `min.insync.replicas` are true? (Select all that apply)
```

**Options**

```markdown
- A. `acks` is a producer configuration, while `min.insync.replicas` is a topic configuration
- B. `acks` and `min.insync.replicas` are both producer configurations
- C. `acks` and `min.insync.replicas` are both topic configurations
- D. `acks=all` and `min.insync.replicas=1` provides the strongest durability guarantee
- E. `acks=1` and `min.insync.replicas=2` provides the strongest durability guarantee
- F. For `acks=all` to provide any additional durability over `acks=1`, `min.insync.replicas` must be greater than 1
```

<details><summary>Response:</summary>

**Answer:** A, F

**Explanation:**

```markdown
- A. Correct: `acks` is a producer config; `min.insync.replicas` is a topic config.
- F. Correct: `acks=all` only provides better durability than `acks=1` if `min.insync.replicas > 1`.
- B, C. Incorrect, they are configs at different levels.
- D. Incorrect, with `min.insync.replicas=1`, `acks=all` is no stronger than `acks=1`.
- E. Incorrect, this combination does not provide the strongest durability.
```

</details>
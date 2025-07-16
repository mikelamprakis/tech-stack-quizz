## Question 9

```markdown
A consumer wants to read messages from partitions 0 and 1 of a topic `topic1` using both `subscribe()` and `assign()`. What happens?
```

**Options**

```markdown
- A. This works fine
- B. Throws `IllegalStateException`
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
You must choose either `subscribe()` (automatic partition assignment) or `assign()` (manual assignment). Using both causes an error.

- A. Incorrect — mixing both is not allowed.
- B. Correct — throws `IllegalStateException`.
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

## Question 7

```markdown
A consumer wants to read messages from a specific partition of a topic. Which of the following methods should be used?
```

**Options**

```markdown
- A. KafkaConsumer.subscribe(String topic, int partition)
- B. KafkaConsumer.assign(Collection<TopicPartition> partitions)
- C. KafkaConsumer.subscribe(Collection<TopicPartition> partitions)
- D. KafkaConsumer.assign(String topic, int partition)
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- A. ❌ Invalid method signature.
- B. ✅ Correct – assign() allows specifying exact partitions.
- C. ❌ subscribe() is for topic-level dynamic partition assignment.
- D. ❌ Invalid method signature.
```

</details>

---

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

## Question 26

```markdown
What triggers a partition rebalance in a Kafka consumer group?
```

**Options**

```markdown
- A. Adding a new topic to the Kafka cluster
- B. Changing the replication factor of a topic
- C. Adding a new consumer to the consumer group
- D. Modifying the consumer group ID
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
A rebalance is triggered by changes to consumer group membership, such as a new consumer joining or one leaving.

- A. Adding topics does not trigger consumer rebalance.
- B. Replication changes affect brokers, not consumers.
- C. Correct — new consumers cause rebalance.
- D. Changing group ID creates a new group, not rebalance.
```

</details>

---

## Question 27

```markdown
What happens to the partition assignments during a consumer group rebalance?
```

**Options**

```markdown
- A. Partitions are evenly distributed among the remaining consumers
- B. Partitions are assigned to the consumers based on the consumer group ID
- C. Partitions are randomly assigned to the consumers
- D. Partitions are assigned to the consumers based on the topic name
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
During rebalance, Kafka reassigns partitions to consumers aiming to distribute load evenly according to the assignment strategy.

- A. Correct — partitions evenly distributed.
- B. Group ID does not affect assignments.
- C. Assignment is deterministic, not random.
- D. Topic name does not influence distribution.
```

</details>

---

## Question 31

```markdown
How can you minimize the impact of consumer group rebalances in a Kafka application?
```

**Options**

```markdown
- A. Increase the session timeout value for consumers
- B. Reduce the number of partitions for the consumed topics
- C. Implement a custom partition assignment strategy
- D. Use static group membership for consumers
```

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**

```markdown
Static group membership allows consumers to keep their partition assignments across restarts, reducing rebalances.

- A. Increasing session timeout helps but does not eliminate rebalances.
- B. Fewer partitions may reduce complexity but is not the main solution.
- C. Custom strategies help but don't prevent rebalances.
- D. Correct — static membership reduces rebalance frequency.
```

</details>

---

## Question 18

```markdown
How does Kafka ensure that messages are processed in a balanced way when using multiple consumer instances in a consumer group?
```

**Options**

```markdown
- A. Kafka assigns an equal number of messages to each consumer instance
- B. Kafka assigns partitions to consumer instances in a round-robin fashion
- C. Kafka dynamically adjusts the assignment of partitions based on consumer load
- D. Kafka relies on ZooKeeper to distribute messages evenly among consumer instances
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Kafka assigns partitions to consumers using round-robin (or range) assignment strategies to balance load across consumer instances.

- A. Incorrect — Kafka assigns partitions, not messages directly.
- B. Correct — partitions assigned round-robin or by range.
- C. Incorrect — assignment not dynamically load-based.
- D. Incorrect — ZooKeeper not responsible for message distribution.
```

</details>

---

## Question 2

```markdown
There are two consumers C1 and C2 belonging to the same group G subscribed to topics T1, T2, and T3. Each topic has 4 partitions. Assuming all partitions have data, how many partitions will each consumer be assigned with the Range Assignor?
```

**Options**

```markdown
- A. C1: 6 partitions, C2: 6 partitions
- B. C1: 4 partitions, C2: 8 partitions
- C. C1: 2 partitions from each topic, C2: 2 partitions from each topic
- D. C1: 1 partition from each topic, C2: 3 partitions from each topic
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
- A. ✅ Correct – Range assignor splits partitions per topic evenly, so each consumer gets 2 per topic × 3 topics = 6 partitions.
- B. ❌ Not evenly split per topic.
- C. ❌ That would be true for Round Robin, not Range.
- D. ❌ Incorrect partition distribution.
```

</details>

---

## Question 3

```markdown
There are four consumers C1, C2, C3, C4 belonging to the same group G subscribed to two topics T1 and T2. T1 has 3 partitions and T2 has 2 partitions. With the Round Robin Assignor, which consumer(s) will be assigned partition 2 from topic T1?
```

**Options**

```markdown
- A. C1
- B. C2
- C. C3
- D. C4
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. ❌ C1 is assigned T1-0, T2-1
- B. ❌ C2 is assigned T1-1, T2-0
- C. ✅ Correct – C3 is assigned T1-2
- D. ❌ C4 receives no partitions
```

</details>

---

## Question 4

```markdown
There are three consumers C1, C2, C3 belonging to the same group G subscribed to a topic T. The topic has 10 partitions. If the Sticky Assignor is used, and C1 leaves the group, how will the partitions be rebalanced?
```

**Options**

```markdown
- A. All partitions will be reassigned evenly among C2 and C3
- B. C2 and C3 will retain their existing partitions, and the partitions from C1 will be reassigned to either C2 or C3
- C. All partitions will be reassigned randomly to C2 and C3
- D. C2 and C3 will retain their existing partitions, and the partitions from C1 will not be reassigned
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- A. ❌ Sticky Assignor avoids full redistribution.
- B. ✅ Correct – Sticky Assignor aims to retain current assignments and only redistribute what’s necessary.
- C. ❌ That behavior would be more like Round Robin.
- D. ❌ Kafka ensures all partitions are consumed; C1’s partitions must be reassigned.
```

</details>

---
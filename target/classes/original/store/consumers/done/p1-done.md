## Question 1

```markdown
Which of the following is stored in the Kafka `__consumer_offsets` topic? (Select two)
```

**Options**

```markdown
- A. The latest committed offset for each consumer group
- B. The list of consumers in each consumer group
- C. The mapping of partitions to consumer groups
- D. The last produced message for each topic partition
- E. The earliest committed offset for each consumer group
```

<details><summary>Response:</summary>

**Answer:** A, C

**Explanation:**

```markdown
- A. ✅ Correct – `__consumer_offsets` tracks the latest committed offsets for consumers.
- B. ❌ Managed by the group coordinator but not stored in the topic.
- C. ✅ Correct – Mapping of partitions to consumer groups is persisted.
- D. ❌ Actual messages are stored in the respective topic partitions.
- E. ❌ Only the latest committed offset is stored, not the earliest.
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

## Question 6

```markdown
You are designing a Kafka consumer application that will consume messages from a topic. The messages in the topic are in JSON format. Which of the following properties should you set in the consumer configuration?
```

**Options**

```markdown
- A. key.deserializer=JsonDeserializer
- B. value.deserializer=JsonDeserializer
- C. key.deserializer=StringDeserializer
- D. value.deserializer=StringDeserializer
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- A. ❌ Only needed if the key is JSON, which isn't stated.
- B. ✅ Correct – Value is JSON, so use JsonDeserializer for value.
- C. ❌ Default assumption is keys are strings, but not the focus here.
- D. ❌ Would incorrectly treat JSON payload as a simple string.
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

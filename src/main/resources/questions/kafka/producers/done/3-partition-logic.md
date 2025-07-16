## Question 7

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


## Question 43

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

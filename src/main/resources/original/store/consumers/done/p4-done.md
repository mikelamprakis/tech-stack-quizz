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

## Question 23

```markdown
What is the purpose of the `max.poll.records` setting in the Kafka consumer configuration?
```

**Options**

```markdown
- A. To specify the maximum number of records to return in a single poll
- B. To control the maximum amount of data the consumer can receive per second
- C. To set the maximum number of partitions the consumer can subscribe to
- D. To determine the maximum number of consumers allowed in a consumer group
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
The `max.poll.records` setting specifies the maximum number of records returned in a single call to `poll()`. This allows controlling batch size for memory and processing reasons.

- A. Correct — controls maximum records per poll.
- B. It does not control data rate per second.
- C. Number of partitions is unrelated.
- D. Consumer group size is not controlled here.
```

</details>

---

## Question 24

```markdown
How does the `max.poll.interval.ms` setting affect the behavior of a Kafka consumer?
```

**Options**

```markdown
- A. It specifies the maximum amount of time the consumer can wait before polling for new records
- B. It sets the maximum interval between two consecutive polls before the consumer is considered dead
- C. It determines the maximum time allowed for message processing before committing offsets
- D. It controls the maximum number of records the consumer can poll in a single request
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
`max.poll.interval.ms` defines the max allowed time between calls to `poll()`. If exceeded, the consumer is considered unresponsive and triggers a rebalance.

- A. Not exactly, it's about interval between polls.
- B. Correct — max interval before considered dead.
- C. Not directly related to commit timing.
- D. Record count per poll is controlled by `max.poll.records`.
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

## Question 0

```markdown
How can you gracefully make a Kafka consumer to stop immediately polling data from Kafka and gracefully shut down a consumer application?
```

**Options**

```markdown
- A. Kill the consumer thread
- B. Call the consumer.poll() in another thread
- C. Call the consumer.wakeUp() and catch a WakeUpException
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
You should use `consumer.wakeUp()` to interrupt a long-running `poll()` call, and catch `WakeUpException` to close the consumer gracefully.

- A. Killing the thread is unsafe and abrupt.
- B. Calling poll in another thread does not help stopping immediately.
- C. Correct — wakeUp interrupts poll and allows graceful shutdown.
```

</details>

---

## Question 1

```markdown
A consumer starts and has `auto.offset.reset=none`, and the topic partition currently has data for offsets going from 45 to 2311. The consumer group has committed the offset 10 for the topic before. Where will the consumer read from?
```

**Options**

```markdown
- A. Offset 10
- B. Offset 2311
- C. Offset 45
- D. It will crash
```

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**

```markdown
Since `auto.offset.reset=none`, and the committed offset (10) has been deleted (log start offset is now 45), the consumer will crash because it has no valid starting point.

- A. Offset 10 was committed but no longer exists.
- B. Offset 2311 is the high watermark, not the starting point.
- C. Offset 45 is the earliest available offset but not used due to config.
- D. Correct — consumer crashes without valid offset to start from.
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

## Question 3

```markdown
When using the Confluent Kafka Distribution, where does the schema registry reside?
```

**Options**

```markdown
- A. As an in-memory plugin on Kafka broker
- B. As an in-memory plugin on Kafka Connect worker
- C. As a separate JVM component
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
The schema registry is a standalone REST service running as a separate JVM process, not embedded in Kafka brokers or Kafka Connect workers.

- A. Incorrect — not embedded in Kafka broker.
- B. Incorrect — not embedded in Kafka Connect.
- C. Correct — runs as a separate JVM service.
```

</details>

---

## Question 4

```markdown
What is returned by a `producer.send()` call in the Java API?
```

**Options**

```markdown
- A. A boolean
- B. Unit
- C. Future
- D. Future object
```

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**

```markdown
`producer.send()` returns a `Future<RecordMetadata>` which can be used to track send completion or exceptions.

- A. Not a boolean.
- B. Not unit (void).
- C. Inexact — should specify Future type.
- D. Correct — returns a Future object.
```

</details>

---

## Question 5

```markdown
A Zookeeper ensemble contains 5 servers. What is the maximum number of servers that can go missing and the ensemble still run?
```

**Options**

```markdown
- A. 1
- B. 2
- C. 3
- D. 4
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Zookeeper requires a quorum (majority) to operate. For 5 servers, quorum is 3, so up to 2 servers can fail.

- A. 1 is too conservative.
- B. Correct — 2 servers can fail.
- C. 3 failures would break quorum.
- D. 4 failures would break quorum.
```

</details>

---

## Question 6

```markdown
To allow consumers in a group to resume at the previously committed offset, I need to set the proper value for...
```

**Options**

```markdown
- A. value.deserializer
- B. enable.auto.commit
- C. group.id
- D. auto.offset.reset
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Kafka tracks offsets per consumer group using `group.id`. Without it, committed offsets cannot be linked to the consumer.

- A. Deserializer unrelated to offset tracking.
- B. Auto commit controls offset commit, not offset resumption.
- C. Correct — group.id enables offset tracking per group.
- D. auto.offset.reset defines behavior on missing offsets.
```

</details>

---

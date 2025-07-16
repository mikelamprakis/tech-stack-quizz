## Question 7

```markdown
How does a consumer commit offsets in Kafka?
```

**Options**

```markdown
- A. It directly commits offsets in Zookeeper
- B. It directly sends the message to `__consumer_offsets`
- C. It interacts with the group coordinator broker
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Consumers commit offsets by communicating with the group coordinator broker, which manages offset storage in the internal `__consumer_offsets` topic.

- A. Incorrect — offset commits no longer go to Zookeeper.
- B. Incorrect — consumers do not send messages directly to the internal topic.
- C. Correct — group coordinator handles offset commits.
```

</details>

---

## Question 8

```markdown
To read data from a topic, the following configuration is needed for the consumers:
```

**Options**

```markdown
- A. any broker to connect, and the topic
- B. all brokers, list of topic name and partition
- C. any broker, list of topic name and partition
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
A consumer can connect to any broker to fetch cluster metadata and discover topics and partitions.

- A. Partially correct but incomplete (needs partitions info).
- B. Incorrect — consumers do not need to connect to all brokers.
- C. Correct — connecting to any broker plus topic and partition info is sufficient.
```

</details>

---

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

## Question 10

```markdown
A consumer sends a request to commit offset 2000. Due to a network issue, the broker doesn’t receive it. The consumer continues and commits offset 3000. What should you do?
```

**Options**

```markdown
- A. Nothing
- B. Add a new consumer
- C. Restart the consumer
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
Offset 3000 commit supersedes the earlier 2000 commit. Since the latest offset is committed, no action is needed.

- A. Correct — no action necessary.
- B. Unrelated.
- C. Unnecessary.
```

</details>

---

## Question 11

```markdown
There are 3 producers writing to a topic with 5 partitions. There are 10 consumers in the same consumer group. How many consumers will remain idle?
```

**Options**

```markdown
- A. 3
- B. 5
- C. 10
- D. None
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Only 5 partitions are available, so only 5 consumers can be assigned partitions. The other 5 consumers remain idle.

- A. Incorrect.
- B. Correct — 5 consumers idle.
- C. Incorrect.
- D. Incorrect.
```

</details>

---

## Question 12

```markdown
In Kafka consumer metrics, the fetch-rate is high but each fetch is small. How to improve throughput?
```

**Options**

```markdown
- A. Increase `fetch.min.bytes`
- B. Increase `fetch.max.bytes`
- C. Decrease `fetch.max.bytes`
- D. Decrease `fetch.min.bytes`
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
Increasing `fetch.min.bytes` causes the broker to wait until enough data accumulates before responding, increasing batch size and throughput.

- A. Correct — increases batch size.
- B. Not necessarily effective here.
- C. Incorrect.
- D. Opposite effect.
```

</details>

---

## Question 13

```markdown
A consumer does complex ML processing that takes approximately 6 minutes per record batch and enters rebalances. What to do?
```

**Options**

```markdown
- A. Increase `max.poll.interval.ms` to 600000
- B. Increase `session.timeout.ms` to 600000
- C. Increase `heartbeat.interval.ms` to 600000
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
`max.poll.interval.ms` controls the max time between poll calls before the consumer is considered dead. Increase it to allow longer processing.

- A. Correct — allows 6 minutes processing.
- B. Incorrect — session timeout controls heartbeat failures.
- C. Incorrect — heartbeat interval controls heartbeat frequency.
```

</details>

---

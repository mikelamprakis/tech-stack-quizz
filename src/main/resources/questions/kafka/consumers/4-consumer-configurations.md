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

## Question 15

```markdown
What is the purpose of the `isolation.level` configuration in Kafka consumers?
```

**Options**

```markdown
- A. To control the visibility of transactional messages
- B. To specify the maximum number of messages to be read in a single batch
- C. To determine the behavior when a partition is reassigned to another consumer in the group
- D. To set the level of consistency for reading messages from a partition
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
The `isolation.level` setting controls whether a consumer reads all messages (`read_uncommitted`) or only committed ones (`read_committed`). It's especially relevant when consuming messages produced in transactions.

- A. Correct — controls transactional message visibility.
- B. Incorrect — not related to batch size.
- C. Incorrect — not related to partition reassignment.
- D. Incorrect — consistency is managed differently.
```

</details>

---

## Question 21

```markdown
What is the default value of the `isolation.level` setting in the Kafka consumer configuration?
```

**Options**

```markdown
- A. `read_uncommitted`
- B. `read_committed`
- C. `transactional`
- D. `none`
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
By default, `isolation.level` is `read_uncommitted`, meaning the consumer reads all messages including aborted transactions unless configured otherwise.

- A. Correct — default is `read_uncommitted`.
- B. Incorrect — `read_committed` must be set explicitly.
- C. Incorrect — not a valid setting.
- D. Incorrect — not a valid setting.
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

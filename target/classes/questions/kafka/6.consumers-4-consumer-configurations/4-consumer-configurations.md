## Question 1

```markdown
What is the purpose of the `group.id` configuration in a Kafka consumer?
```

**Options**
```markdown
- A. To specify the Kafka broker addresses
- B. To define the consumer group for coordination and rebalancing
- C. To set the deserializer for message keys
- D. To configure the maximum number of records per poll
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
`group.id` identifies the consumer group, enabling coordination and partition rebalancing among members.

- A. Incorrect: Broker addresses are specified in `bootstrap.servers`.
- B. Correct: `group.id` defines the consumer group.
- C. Incorrect: Deserializers are set via `key.deserializer` and `value.deserializer`.
- D. Incorrect: This is controlled by `max.poll.records`.
```

</details>

---

## Question 2

```markdown
Which configuration determines where a consumer starts reading if no offset is committed?
```

**Options**
```markdown
- A. `session.timeout.ms`
- B. `auto.offset.reset`
- C. `fetch.min.bytes`
- D. `heartbeat.interval.ms`
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
`auto.offset.reset` specifies the behavior when no offset exists (e.g., `latest` or `earliest`).

- A. Incorrect: This controls consumer liveness timeouts.
- B. Correct: `auto.offset.reset` handles offset fallback.
- C. Incorrect: This sets the minimum data size for fetching.
- D. Incorrect: This configures heartbeat frequency.
```

</details>

---

## Question 3

```markdown
What happens if `max.poll.interval.ms` is exceeded?
```

**Options**
```markdown
- A. The consumer is removed from the group
- B. The consumer pauses fetching new messages
- C. The broker restarts the consumer
- D. The consumer switches to a new topic
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
Exceeding `max.poll.interval.ms` signals the consumer is stuck, triggering removal from the group.

- A. Correct: The consumer is considered dead and removed.
- B. Incorrect: Pausing is unrelated to this timeout.
- C. Incorrect: Brokers don’t restart consumers.
- D. Incorrect: Topic switching is manual or application-controlled.
```

</details>

---

## Question 4

```markdown
Which configuration ensures the consumer sends heartbeats frequently enough to stay alive?
```

**Options**
```markdown
- A. `session.timeout.ms`
- B. `heartbeat.interval.ms`
- C. `fetch.min.bytes`
- D. `auto.offset.reset`
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
`heartbeat.interval.ms` sets how often heartbeats are sent to avoid being marked dead.

- A. Incorrect: This sets the liveness timeout window.
- B. Correct: Heartbeat frequency is critical for liveness.
- C. Incorrect: This controls fetch batching.
- D. Incorrect: This handles offset fallback behavior.
```

</details>

---

## Question 5

```markdown
What is the default value for `auto.offset.reset`?
```

**Options**
```markdown
- A. `earliest`
- B. `latest`
- C. `none`
- D. `smallest`
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
The default is `latest`, meaning new consumers start at the end of the topic.

- A. Incorrect: `earliest` is used for debugging but not default.
- B. Correct: Default is `latest`.
- C. Incorrect: `none` throws an error if no offset exists.
- D. Incorrect: `smallest` is a deprecated alias for `earliest`.
```

</details>

---

## Question 6

```markdown
Which configuration controls the minimum amount of data the broker must have before responding to a fetch request?
```

**Options**
```markdown
- A. `max.poll.records`
- B. `fetch.min.bytes`
- C. `session.timeout.ms`
- D. `bootstrap.servers`
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
`fetch.min.bytes` sets the minimum data size (in bytes) for the broker to respond.

- A. Incorrect: This limits records per poll, not fetch size.
- B. Correct: `fetch.min.bytes` controls fetch batching.
- C. Incorrect: This is the consumer liveness timeout.
- D. Incorrect: This lists broker addresses.
```

</details>

---

## Question 7

```markdown
What is the relationship between `session.timeout.ms` and `heartbeat.interval.ms`?
```

**Options**
```markdown
- A. `session.timeout.ms` must be less than `heartbeat.interval.ms`
- B. `heartbeat.interval.ms` must be significantly less than `session.timeout.ms`
- C. They must be equal
- D. They are independent
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Heartbeats must be sent frequently enough (`heartbeat.interval.ms << session.timeout.ms`) to avoid false timeouts.

- A. Incorrect: The opposite is true.
- B. Correct: Heartbeats must arrive within the session timeout.
- C. Incorrect: Equality would risk timeouts.
- D. Incorrect: They are directly related.
```

</details>

---

## Question 8

```markdown
Which configuration is critical for long-running batch processing jobs?
```

**Options**
```markdown
- A. `auto.offset.reset`
- B. `max.poll.interval.ms`
- C. `fetch.min.bytes`
- D. `bootstrap.servers`
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
`max.poll.interval.ms` must be increased for batch jobs to avoid premature removal from the group.

- A. Incorrect: This handles offset fallback, not processing time.
- B. Correct: Long jobs need a higher poll interval.
- C. Incorrect: This optimizes fetch efficiency, not job duration.
- D. Incorrect: This is for broker connectivity.
```

</details>

---

## Question 9

```markdown
What is the purpose of `max.poll.records`?
```

**Options**
```markdown
- A. To limit the number of records returned in a single `poll()` call
- B. To set the maximum number of topics a consumer can subscribe to
- C. To control the size of each message batch sent by the producer
- D. To define the maximum number of consumer groups
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
`max.poll.records` caps the records returned per `poll()` to manage memory/processing load.

- A. Correct: This controls per-poll batch size.
- B. Incorrect: Topic limits are unrelated.
- C. Incorrect: This is a producer-side setting (`batch.size`).
- D. Incorrect: Group limits are broker/cluster settings.
```

</details>

---

## Question 10

```markdown
Which configuration should be increased if a consumer frequently times out during rebalancing?
```

**Options**
```markdown
- A. `session.timeout.ms`
- B. `fetch.min.bytes`
- C. `auto.offset.reset`
- D. `bootstrap.servers`
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
Increasing `session.timeout.ms` gives consumers more time to respond during rebalances.

- A. Correct: This extends the liveness detection window.
- B. Incorrect: This affects fetch efficiency, not timeouts.
- C. Incorrect: This handles offset fallback, not rebalancing.
- D. Incorrect: This lists brokers and doesn’t impact timeouts.
```

</details>


## Question 11

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

## Question 13

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

## Question 14

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

## Question 15

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

## Question 16

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

## Question 17

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

## Question 18

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

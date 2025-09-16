## Question 1

```markdown
Which type of error can be automatically retried by the Kafka producer?
```

**Options**
```markdown
- A. SerializationException
- B. Network glitches
- C. Invalid topic name
- D. Authorization failure
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Transient errors like network glitches can be retried automatically, while permanent errors (e.g., serialization, invalid topic) cannot.

- A. Incorrect: Serialization errors are permanent and not retryable.
- B. Correct: Network issues are transient and retryable.
- C. Incorrect: Invalid topic names are permanent errors.
- D. Incorrect: Authorization failures are permanent.
```

</details>

---

## Question 2

```markdown
What is the purpose of a Dead Letter Topic (DLT) in Kafka?
```

**Options**
```markdown
- A. To store successfully processed messages
- B. To route messages that fail permanently
- C. To compress messages for storage
- D. To increase producer throughput
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
DLTs store messages that cannot be processed due to permanent errors (e.g., corruption, invalid data).

- A. Incorrect: DLTs are for failed messages, not successful ones.
- B. Correct: DLTs handle unrecoverable failures.
- C. Incorrect: Compression is unrelated to DLTs.
- D. Incorrect: DLTs don’t affect throughput.
```

</details>

---

## Question 3

```markdown
Which configuration enables automatic retries for transient errors in Kafka producers?
```

**Options**
```markdown
- A. `retries=0`
- B. `retries=5`
- C. `enable.idempotence=false`
- D. `acks=0`
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
`retries=N` (where N > 0) enables automatic retries for transient errors.

- A. Incorrect: `retries=0` disables retries.
- B. Correct: `retries=5` allows up to 5 retries.
- C. Incorrect: Idempotence affects deduplication, not retry eligibility.
- D. Incorrect: `acks=0` disables retries for transient errors.
```

</details>

---

## Question 4

```markdown
Which of the following is a permanent error that cannot be retried?
```

**Options**
```markdown
- A. Partition leader unavailability
- B. Broker restarts
- C. Record too large
- D. Timeout exceptions
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Permanent errors (e.g., oversized records) require manual handling, unlike transient errors (A, B, D).

- A. Incorrect: Leader unavailability is transient.
- B. Incorrect: Broker restarts are transient.
- C. Correct: Oversized records are permanently invalid.
- D. Incorrect: Timeouts are transient.
```

</details>

---

## Question 5

```markdown
How can you handle per-message send failures in a Kafka producer?
```

**Options**
```markdown
- A. Configure `retry.backoff.ms`
- B. Use the `Callback` API
- C. Increase `buffer.memory`
- D. Set `compression.type`
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
The `Callback` API provides per-message success/failure notifications.

- A. Incorrect: `retry.backoff.ms` controls retry delays, not per-message handling.
- B. Correct: Callbacks allow granular error handling.
- C. Incorrect: Buffer memory is unrelated to error handling.
- D. Incorrect: Compression affects throughput, not error handling.
```

</details>

---

## Question 6

```markdown
What happens if `retries > 0` but `enable.idempotence=false` and `acks=0`?
```

**Options**
```markdown
- A. Kafka retries all transient errors
- B. Kafka does not retry any errors
- C. Only serialization errors are retried
- D. Messages are routed to a DLT automatically
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
With `acks=0` and no idempotence, Kafka disables retries entirely to avoid duplicates.

- A. Incorrect: Retries are disabled in this scenario.
- B. Correct: No retries occur to prevent duplicates.
- C. Incorrect: Serialization errors are never retried.
- D. Incorrect: DLTs require manual configuration.
```

</details>

---

## Question 7

```markdown
Which error handling practice helps avoid infinite retry loops?
```

**Options**
```markdown
- A. Setting `retries=Integer.MAX_VALUE`
- B. Using a Dead Letter Topic
- C. Configuring a max retry count
- D. Disabling callbacks
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
A finite `retries` value (e.g., `retries=5`) prevents infinite retries.

- A. Incorrect: Unlimited retries can cause infinite loops.
- B. Incorrect: DLTs handle permanent errors, not retry limits.
- C. Correct: A max retry count caps retry attempts.
- D. Incorrect: Callbacks are unrelated to retry limits.
```

</details>

---

## Question 8

```markdown
Which exception indicates a message is too large to be sent?
```

**Options**
```markdown
- A. SerializationException
- B. RecordTooLargeException
- C. TimeoutException
- D. AuthorizationException
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
`RecordTooLargeException` is thrown when a message exceeds broker limits.

- A. Incorrect: SerializationException indicates data format issues.
- B. Correct: RecordTooLargeException is specific to oversized messages.
- C. Incorrect: TimeoutException is transient.
- D. Incorrect: AuthorizationException is for permission issues.
```

</details>

---

## Question 9

```markdown
What is the default value for `retries` in Kafka producers?
```

**Options**
```markdown
- A. 0
- B. 5
- C. 10
- D. 2147483647 (Integer.MAX_VALUE)
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
The default `retries=0` disables automatic retries.

- A. Correct: Default is 0 (no retries).
- B. Incorrect: 5 is a common tuning value but not default.
- C. Incorrect: 10 is excessive for most use cases.
- D. Incorrect: Unlimited retries are never the default.
```

</details>

---

## Question 10

```markdown
Which of the following is a best practice for Kafka producer error handling?
```

**Options**
```markdown
- A. Ignore serialization errors
- B. Log errors without context
- C. Route failed messages to a DLT
- D. Disable all retries
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Routing failed messages to a DLT ensures no data is lost permanently.

- A. Incorrect: Serialization errors must be handled explicitly.
- B. Incorrect: Logs should include context (e.g., key/value).
- C. Correct: DLTs are a best practice for unrecoverable errors.
- D. Incorrect: Retries are useful for transient errors.
```

</details>



## Question 11

```markdown
What happens when a producer tries to send a message to a partition whose leader replica is not in-sync?
```

**Options**

```markdown
- A. The producer receives a `NotLeaderOrFollowerException` and retries sending the message
- B. The producer waits until the leader replica becomes in-sync before sending the message
- C. The message is automatically routed to another in-sync replica
- D. The producer receives a `LeaderNotAvailableException` and the message is discarded
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
If the producer tries to send to a broker that is not the leader for a partition, it receives a `NotLeaderOrFollowerException`. The producer then refreshes its metadata and retries. Kafka does not automatically reroute to another replica—only the partition leader can accept writes.

- A. Correct—producer retries after metadata refresh.
- B. Incorrect—producer doesn't wait for ISR recovery.
- C. Incorrect—routing only goes to the partition leader.
- D. Incorrect—the specific exception is `NotLeaderOrFollowerException`.
```

</details>

---
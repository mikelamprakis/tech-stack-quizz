## Question 29

```markdown
How does Kafka's zero-copy optimization handle data transformation or modification?
```

**Options**

```markdown
- A. It automatically applies data transformations during the zero-copy process
- B. It allows custom data transformations to be plugged into the zero-copy mechanism
- C. It does not support data transformations and sends data as-is
- D. It performs data transformations after the data is copied into the application's memory
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Kafka’s zero-copy uses the `sendfile` system call to transfer bytes directly from the page cache to the network socket. No transformation is applied during this process—data is passed through as-is, which boosts performance.

- A. Incorrect – no transformations happen during zero-copy.
- B. Incorrect – no hooks for transformation in zero-copy.
- C. Correct – data is sent as-is.
- D. Incorrect – transformations aren’t performed post-copy either.
```

</details>

---

## Question 30

```markdown
What is the purpose of the `linger.ms` setting in the Kafka producer configuration?
```

**Options**

```markdown
- A. To specify the maximum time to wait for a response from the Kafka broker
- B. To specify the maximum time to wait before sending a batch of messages
- C. To specify the maximum time to wait for a message to be acknowledged by the Kafka broker
- D. To specify the maximum time to wait for a message to be written to the Kafka topic
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
`linger.ms` introduces a delay to allow more messages to accumulate before a batch is sent, helping improve throughput.

- A. Wrong – that’s more related to request timeout.
- B. Correct – controls batching delay.
- C. Wrong – refers to `request.timeout.ms`.
- D. Wrong – message writing is internal to Kafka.
```

</details>

---

## Question 31

```markdown
How does the `batch.size` setting affect the behavior of the Kafka producer?
```

**Options**

```markdown
- A. It specifies the maximum number of messages that can be sent in a single batch
- B. It specifies the maximum size (in bytes) of a batch of messages
- C. It specifies the minimum number of messages required to form a batch
- D. It specifies the minimum size (in bytes) of a message to be included in a batch
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
`batch.size` is the upper limit in bytes for messages in one batch. It's about size, not count.

- A. Incorrect – it’s about size, not message count.
- B. Correct – defines byte limit.
- C. Wrong – no minimum count constraint.
- D. Wrong – batch applies to batches, not individual messages.
```

</details>

---

## Question 32

```markdown
What happens if the Kafka producer exhausts its buffer memory while sending messages?
```

**Options**

```markdown
- A. The producer will block and wait until buffer memory becomes available
- B. The producer will start discarding the oldest messages to free up buffer memory
- C. The producer will start discarding the newest messages to free up buffer memory
- D. The producer will throw an exception and stop sending messages
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
If buffer memory fills up, the producer blocks until space is available or `max.block.ms` is exceeded.

- A. Correct – default behavior is blocking.
- B. Incorrect – Kafka doesn’t evict messages from buffer.
- C. Incorrect – no discarding of newest messages.
- D. Partially true, but only after blocking timeout.
```

</details>

---

## Question 33

```markdown
What is the default value for the `acks` parameter in the Kafka producer configuration?
```

**Options**

```markdown
- A. 0
- B. 1
- C. all
- D. none
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
The default is `acks=1`, meaning only the leader must acknowledge the write.

- A. Incorrect – 0 means no acknowledgment.
- B. Correct – leader-only ack is the default.
- C. Incorrect – `acks=all` must be explicitly set.
- D. Not a valid Kafka config.
```

</details>

---

## Question 34

```markdown
How does the `max.in.flight.requests.per.connection` setting affect the behavior of the Kafka producer when `acks=1`?
```

**Options**

```markdown
- A. It specifies the maximum number of unacknowledged requests allowed per broker connection
- B. It specifies the maximum number of requests that can be sent to the broker concurrently
- C. It specifies the maximum number of messages that can be buffered in the producer's memory
- D. It has no effect when `acks=1`
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
This config limits the number of unacknowledged requests per connection, which can affect ordering if retries happen.

- A. Correct – defines number of unacknowledged sends.
- B. Close, but wording is vague – not about concurrency alone.
- C. Incorrect – that’s `buffer.memory`.
- D. Incorrect – setting still matters with `acks=1`.
```

</details>

---

## Question 35

```markdown
What is the purpose of the `enable.idempotence` setting in the Kafka producer configuration?
```

**Options**

```markdown
- A. To ensure that messages are delivered exactly once to the Kafka broker
- B. To enable compression of messages sent by the producer
- C. To specify the maximum size of a batch of messages
- D. To control the acknowledgment behavior of the producer
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
Idempotence ensures the same message isn't written more than once to a partition, even if retries occur.

- A. Correct – avoids duplicates via sequence numbers.
- B. Incorrect – use `compression.type`.
- C. Incorrect – handled by `batch.size`.
- D. Incorrect – that’s managed by `acks`.
```

</details>
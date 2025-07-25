## Question 1

```markdown
What is the primary role of the `RecordAccumulator` in the Kafka producer?
```

**Options**
```markdown
- A. Compress messages before sending
- B. Buffer and batch records by partition
- C. Manage consumer offsets
- D. Handle broker leader elections
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
The `RecordAccumulator` groups records by partition into batches for efficient sending.

- A. Incorrect: Compression happens after batching.
- B. Correct: The `RecordAccumulator` buffers and batches records.
- C. Incorrect: Consumer offsets are managed by the broker/consumer.
- D. Incorrect: Leader elections are handled by ZooKeeper/Kafka brokers.
```

</details>

---

## Question 2

```markdown
Which component is responsible for actually sending batches to Kafka brokers?
```

**Options**
```markdown
- A. RecordAccumulator
- B. Sender Thread
- C. Main Application Thread
- D. Network Manager
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
The Sender Thread is a dedicated background thread that sends batches from the `RecordAccumulator`.

- A. Incorrect: The `RecordAccumulator` only buffers records.
- B. Correct: The Sender Thread handles transmission.
- C. Incorrect: The main thread only adds records to the buffer.
- D. Incorrect: There is no "Network Manager" component.
```

</details>

---

## Question 3

```markdown
What happens when `linger.ms=0` is configured?
```

**Options**
```markdown
- A. The producer waits indefinitely for a full batch
- B. Batches are sent immediately without waiting
- C. Compression is disabled
- D. The buffer size is set to unlimited
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
`linger.ms=0` means the producer won't wait to send a batch (optimizing for latency).

- A. Incorrect: This would happen with a very high `linger.ms`.
- B. Correct: Batches are sent immediately.
- C. Incorrect: `linger.ms` doesn't affect compression.
- D. Incorrect: Buffer size is controlled by `buffer.memory`.
```

</details>

---

## Question 4

```markdown
What is the default value for `batch.size` in Kafka producers?
```

**Options**
```markdown
- A. 8KB
- B. 16KB
- C. 32KB
- D. 64KB
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
The default `batch.size` is 16KB.

- A. Incorrect: Too small for the default.
- B. Correct: Default is 16KB.
- C. Incorrect: Common tuning value but not default.
- D. Incorrect: Used for high-throughput tuning.
```

</details>

---

## Question 5

```markdown
Which configuration preserves message ordering when retries are enabled?
```

**Options**
```markdown
- A. `max.in.flight.requests.per.connection=1`
- B. `enable.idempotence=true`
- C. `acks=0`
- D. `retries=0`
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
`enable.idempotence=true` ensures ordering even with multiple in-flight requests.

- A. Incorrect: Setting this to 1 also works but reduces throughput.
- B. Correct: Idempotence preserves ordering automatically.
- C. Incorrect: `acks=0` disables retries and ordering guarantees.
- D. Incorrect: `retries=0` disables retries entirely.
```

</details>

---

## Question 6

```markdown
What is the risk of setting `max.in.flight.requests.per.connection` > 1 without idempotence?
```

**Options**
```markdown
- A. Increased CPU usage
- B. Message reordering
- C. Higher compression ratios
- D. Buffer memory leaks
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Without idempotence, multiple in-flight requests can cause message reordering during retries.

- A. Incorrect: CPU usage isn't directly affected.
- B. Correct: The main risk is message reordering.
- C. Incorrect: Compression is unrelated to in-flight requests.
- D. Incorrect: Buffer memory is managed separately.
```

</details>

---

## Question 7

```markdown
Which statement about the KafkaProducer's thread safety is true?
```

**Options**
```markdown
- A. It's unsafe for multi-threaded use
- B. It's thread-safe but may have lock contention under high load
- C. Each thread must create its own producer instance
- D. Only the Sender Thread can call `send()`
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
`KafkaProducer` is thread-safe but may experience lock contention with heavy concurrent use.

- A. Incorrect: It is thread-safe.
- B. Correct: Thread-safe but not contention-free.
- C. Incorrect: A single instance can be shared (with caveats).
- D. Incorrect: Any thread can call `send()`.
```

</details>

---

## Question 8

```markdown
What does the Sender Thread NOT handle?
```

**Options**
```markdown
- A. Picking batches from the RecordAccumulator
- B. Managing consumer group coordination
- C. Handling retries and acknowledgments
- D. Reassigning failed batches
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Consumer group coordination is handled by brokers/consumers, not the producer's Sender Thread.

- A. Incorrect: This is a core Sender Thread responsibility.
- B. Correct: Consumer coordination is unrelated to the producer.
- C. Incorrect: The Sender Thread handles retries and acks.
- D. Incorrect: Failed batches are reassigned by the Sender Thread.
```

</details>

---

## Question 9

```markdown
Which configuration automatically sets `acks=all` and `retries=Integer.MAX_VALUE`?
```

**Options**
```markdown
- A. `max.in.flight.requests.per.connection=5`
- B. `enable.idempotence=true`
- C. `linger.ms=100`
- D. `batch.size=65536`
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Enabling idempotence automatically configures these settings for reliability.

- A. Incorrect: This controls concurrency, not acks/retries.
- B. Correct: Idempotence enables these safety settings.
- C. Incorrect: `linger.ms` controls batching delay.
- D. Incorrect: `batch.size` only affects batch sizing.
```

</details>

---

## Question 10

```markdown
What is the purpose of increasing `max.in.flight.requests.per.connection`?
```

**Options**
```markdown
- A. To reduce network bandwidth
- B. To improve throughput via concurrent batches
- C. To guarantee message ordering
- D. To decrease memory usage
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Higher values allow more concurrent batches in flight, improving throughput.

- A. Incorrect: Bandwidth usage may increase, not decrease.
- B. Correct: More concurrent batches = higher throughput.
- C. Incorrect: This can break ordering without idempotence.
- D. Incorrect: Memory usage may increase with more in-flight requests.
```

</details>



## Question 11

```markdown
When is the `onCompletion()` method called in a Producer Callback?
```

**Options**

```markdown
- A. Immediately after serialization
- B. Immediately when `send()` is called
- C. When the broker response is received
- D. Only on failure
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
The `onCompletion()` method is triggered when Kafka receives a response from the broker, either success or failure. It’s an asynchronous callback used to handle send results.

- A. Not related to serialization
- B. `send()` is async and returns before callback
- C. Correct — callback happens post-response
- D. Incorrect — also called on success
```

</details>

---

## Question 12

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
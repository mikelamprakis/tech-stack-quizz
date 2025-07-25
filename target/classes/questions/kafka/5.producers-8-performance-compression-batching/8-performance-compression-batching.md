## Question 1

```markdown
What is the primary trade-off between throughput and latency in Kafka producers?
```

**Options**
```markdown
- A. Higher throughput reduces CPU usage
- B. Lower latency increases batch size
- C. Higher throughput increases latency
- D. Lower latency reduces network overhead
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
The primary trade-off is that higher throughput requires larger batches or waiting (`linger.ms`), which increases latency. Conversely, low latency sends messages immediately, reducing throughput.

- A. Incorrect: CPU usage is unrelated to this trade-off.
- B. Incorrect: Lower latency uses smaller batches or no batching.
- C. Correct: Larger batches or waiting for `linger.ms` increases throughput but also latency.
- D. Incorrect: Network overhead is higher with low latency (smaller batches).
```

</details>

---

## Question 2

```markdown
Which configuration parameter controls how long a Kafka producer waits to batch messages before sending them?
```

**Options**
```markdown
- A. `batch.size`
- B. `buffer.memory`
- C. `linger.ms`
- D. `max.block.ms`
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
`linger.ms` determines the time the producer waits for additional messages to fill a batch.

- A. Incorrect: `batch.size` sets the maximum size of a batch in bytes.
- B. Incorrect: `buffer.memory` controls the total memory for buffering messages.
- C. Correct: `linger.ms` defines the wait time for batching.
- D. Incorrect: `max.block.ms` sets the maximum time to block when the buffer is full.
```

</details>

---

## Question 3

```markdown
What happens when the Kafka producer's buffer memory (`buffer.memory`) is exhausted?
```

**Options**
```markdown
- A. Messages are automatically compressed
- B. The producer blocks or throws an error
- C. The batch size is reduced dynamically
- D. The producer switches to synchronous mode
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
When the buffer is full, the producer either blocks (up to `max.block.ms`) or throws a `BufferExhaustedException`.

- A. Incorrect: Compression is unrelated to buffer exhaustion.
- B. Correct: The producer blocks or fails if the buffer is full.
- C. Incorrect: Batch size is fixed unless reconfigured.
- D. Incorrect: The producer doesn't switch modes automatically.
```

</details>

---

## Question 4

```markdown
Which compression algorithm offers the best compression ratio but has the highest CPU overhead?
```

**Options**
```markdown
- A. snappy
- B. lz4
- C. gzip
- D. zstd
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
gzip provides the highest compression ratio (5–6:1) but is CPU-intensive.

- A. Incorrect: snappy has moderate compression and low CPU usage.
- B. Incorrect: lz4 is fast but has a lower compression ratio.
- C. Correct: gzip offers the best compression but is slow.
- D. Incorrect: zstd is tunable but not as CPU-heavy as gzip.
```

</details>

---

## Question 5

```markdown
Which Kafka producer configuration is ideal for ultra-low latency applications?
```

**Options**
```markdown
- A. `linger.ms=50`, `batch.size=64KB`
- B. `linger.ms=0`, `batch.size=16KB`
- C. `linger.ms=10`, `batch.size=32KB`
- D. `linger.ms=5`, `batch.size=128KB`
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Ultra-low latency requires `linger.ms=0` (no waiting) and a small `batch.size` (≤16KB).

- A. Incorrect: High latency due to waiting and large batch.
- B. Correct: No waiting and small batches minimize latency.
- C. Incorrect: Moderate latency due to waiting.
- D. Incorrect: Large batches increase latency.
```

</details>

---

## Question 6

```markdown
Which compression algorithm is recommended for high-throughput, low-CPU usage scenarios?
```

**Options**
```markdown
- A. gzip
- B. snappy
- C. lz4
- D. zstd
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
snappy is optimized for speed and low CPU usage, making it ideal for high-throughput pipelines.

- A. Incorrect: gzip is CPU-heavy.
- B. Correct: snappy is fast and lightweight.
- C. Incorrect: lz4 is faster but slightly more CPU-intensive.
- D. Incorrect: zstd is tunable but not as lightweight as snappy.
```

</details>

---

## Question 7

```markdown
What is the default value for `linger.ms` in Kafka producers?
```

**Options**
```markdown
- A. 5 ms
- B. 10 ms
- C. 0 ms
- D. 50 ms
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
The default `linger.ms=0` means no waiting, optimizing for low latency.

- A. Incorrect: 5 ms is a common tuning value but not the default.
- B. Incorrect: 10 ms is used for higher throughput.
- C. Correct: Default is 0 ms (no delay).
- D. Incorrect: 50 ms is for high-throughput tuning.
```

</details>

---

## Question 8

```markdown
Which metric helps detect backpressure in Kafka producers?
```

**Options**
```markdown
- A. `records-per-request-avg`
- B. `compression-rate-avg`
- C. `bufferpool-wait-time-total`
- D. `batch-size-avg`
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
`bufferpool-wait-time-total` indicates time spent waiting for buffer space, signaling backpressure.

- A. Incorrect: Measures batch efficiency, not backpressure.
- B. Incorrect: Tracks compression savings.
- C. Correct: High wait time means buffer contention.
- D. Incorrect: Measures average batch size.
```

</details>

---

## Question 9

```markdown
Which compression algorithm is best for real-time financial applications?
```

**Options**
```markdown
- A. gzip
- B. snappy
- C. lz4
- D. zstd
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
lz4 offers ultra-fast decompression, critical for low-latency financial apps.

- A. Incorrect: gzip is too slow.
- B. Incorrect: snappy is fast but slower than lz4.
- C. Correct: lz4 is the fastest for real-time workloads.
- D. Incorrect: zstd is tunable but not as fast as lz4.
```

</details>

---

## Question 10

```markdown
What is the purpose of the `batch.size` parameter in Kafka producers?
```

**Options**
```markdown
- A. Controls the total memory for buffering messages
- B. Sets the maximum size of a batch per partition
- C. Defines the time to wait for batching
- D. Limits the number of messages per batch
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
`batch.size` sets the maximum batch size (in bytes) per partition before sending.

- A. Incorrect: This is the role of `buffer.memory`.
- B. Correct: `batch.size` limits batch size per partition.
- C. Incorrect: This is the role of `linger.ms`.
- D. Incorrect: It’s size-based, not message-count-based.
```

</details>

---

## Question 11

```markdown
Which configuration is ideal for a high-throughput log pipeline?
```

**Options**
```markdown
- A. `compression.type=gzip`, `linger.ms=0`
- B. `compression.type=snappy`, `linger.ms=5`
- C. `compression.type=lz4`, `linger.ms=50`
- D. `compression.type=zstd`, `linger.ms=100`
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
snappy with `linger.ms=5` balances speed and throughput for logs.

- A. Incorrect: gzip is too slow, and `linger.ms=0` reduces throughput.
- B. Correct: snappy is fast, and `linger.ms=5` improves batching.
- C. Incorrect: `linger.ms=50` adds unnecessary latency.
- D. Incorrect: `linger.ms=100` is excessive for logs.
```

</details>

---

## Question 12

```markdown
Which compression algorithm requires Kafka 2.1+?
```

**Options**
```markdown
- A. gzip
- B. snappy
- C. lz4
- D. zstd
```

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**

```markdown
zstd was introduced in Kafka 2.1 and is not available in older versions.

- A. Incorrect: gzip is supported in all versions.
- B. Incorrect: snappy is widely supported.
- C. Incorrect: lz4 is also widely supported.
- D. Correct: zstd requires Kafka 2.1+.
```

</details>

---

## Question 13

```markdown
What is the default `batch.size` in Kafka producers?
```

**Options**
```markdown
- A. 8 KB
- B. 16 KB
- C. 32 KB
- D. 64 KB
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
The default `batch.size` is 16 KB.

- A. Incorrect: 8 KB is too small for the default.
- B. Correct: Default is 16 KB.
- C. Incorrect: 32 KB is a common tuning value.
- D. Incorrect: 64 KB is for high-throughput tuning.
```

</details>

---

## Question 14

```markdown
Which statement about Kafka compression is true?
```

**Options**
```markdown
- A. Consumers must configure decompression manually
- B. Compression is applied to individual messages
- C. Compression happens at the batch level
- D. Brokers decompress messages before storage
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Kafka compresses batches of messages, not individual messages.

- A. Incorrect: Decompression is automatic for consumers.
- B. Incorrect: Compression is batch-level, not per-message.
- C. Correct: Batches are compressed before sending.
- D. Incorrect: Brokers store messages as compressed batches.
```

</details>

---

## Question 15

```markdown
Which setting is most important for minimizing producer latency?
```

**Options**
```markdown
- A. Increasing `buffer.memory`
- B. Setting `linger.ms=0`
- C. Using `compression.type=zstd`
- D. Setting `batch.size=128KB`
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
`linger.ms=0` ensures messages are sent immediately, minimizing latency.

- A. Incorrect: Increasing buffer memory helps with backpressure but not latency.
- B. Correct: `linger.ms=0` removes batching delays.
- C. Incorrect: Compression type affects throughput, not latency directly.
- D. Incorrect: Large batches increase latency.
```

</details>

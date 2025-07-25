## Question 1

```markdown
Which Kafka producer setting directly increases throughput at the cost of latency?
```

**Options**

```markdown
- A. `acks=all`
- B. `linger.ms`
- C. `compression.type`
- D. `enable.idempotence`
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
`linger.ms` introduces a delay before sending messages to allow batching, increasing throughput but adding latency.

- A. `acks=all` improves durability, not throughput.
- B. Correct – increases batching and throughput with some latency.
- C. May help throughput but not primarily about batching delay.
- D. Adds reliability, not throughput.
```

</details>

---

## Question 2

```markdown
What effect does increasing `batch.size` have on producer performance?
```

**Options**

```markdown
- A. It increases memory usage and reduces batching efficiency
- B. It allows larger batches, increasing throughput
- C. It increases the number of requests sent to Kafka
- D. It decreases the number of retries on failure
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Larger `batch.size` enables grouping more records into one request, improving compression and throughput.

- A. False – larger batch improves batching, not reduces it.
- B. Correct – more efficient use of I/O and network.
- C. False – batching reduces requests, not increases.
- D. False – unrelated to retry behavior.
```

</details>

---

## Question 3

```markdown
How does compression affect producer throughput?
```

**Options**

```markdown
- A. Increases throughput by reducing request payload size
- B. Decreases throughput due to CPU overhead
- C. Has no effect on throughput
- D. Only affects latency, not throughput
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
Compression like `snappy` or `lz4` reduces payload size, leading to fewer bytes over the network and better throughput.

- A. Correct – less data over the wire = more throughput.
- B. Partially true, but impact is often outweighed by network gains.
- C. False – it definitely affects throughput.
- D. False – affects both latency and throughput.
```

</details>

---

## Question 4

```markdown
Which setting can help reduce latency in a low-throughput Kafka producer?
```

**Options**

```markdown
- A. Set `linger.ms` to a high value
- B. Use `acks=all`
- C. Set `linger.ms` to 0
- D. Increase `batch.size`
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
`linger.ms=0` ensures messages are sent as soon as possible, minimizing latency.

- A. False – high `linger.ms` adds delay.
- B. False – increases reliability but not speed.
- C. Correct – sends immediately, low latency.
- D. False – may delay sending to fill batch.
```

</details>

---

## Question 5

```markdown
Why might a high-throughput Kafka producer experience high memory usage?
```

**Options**

```markdown
- A. Because it uses `acks=0`
- B. Because of too many in-flight requests and batching
- C. Because it writes directly to all partitions
- D. Because compression increases memory usage
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Larger batches, more in-flight requests, and async retries all consume memory.

- A. False – `acks` level doesn’t directly affect memory.
- B. Correct – batching + parallelism = more memory usage.
- C. False – partitions don't cause memory pressure.
- D. False – compression reduces payload size.
```

</details>

---

## Question 6

```markdown
What does setting a high `max.in.flight.requests.per.connection` do?
```

**Options**

```markdown
- A. Reduces throughput but increases safety
- B. Increases throughput but may affect message order
- C. Guarantees message ordering
- D. Has no impact on throughput
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Multiple in-flight requests increase throughput but may cause out-of-order delivery on retries.

- A. False – increases throughput, not reduces.
- B. Correct – but reordering may occur if retries happen.
- C. False – to guarantee order, keep it at 1.
- D. False – affects both throughput and ordering.
```

</details>

---

## Question 7

```markdown
What’s the tradeoff when setting `acks=0` for a producer?
```

**Options**

```markdown
- A. High durability, low latency
- B. Low durability, high latency
- C. Low durability, high throughput
- D. High durability, high throughput
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
With `acks=0`, the producer doesn’t wait for acknowledgment, maximizing throughput but risking message loss.

- A. False – no durability at all.
- B. False – low durability, *low* latency.
- C. Correct – fast but risky.
- D. False – no durability here.
```

</details>

---

## Question 8

```markdown
Which configuration improves throughput by increasing concurrency in sending?
```

**Options**

```markdown
- A. `max.in.flight.requests.per.connection`
- B. `enable.idempotence`
- C. `retries`
- D. `linger.ms`
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
Higher `max.in.flight.requests.per.connection` allows more unacknowledged requests in parallel, boosting throughput.

- A. Correct – controls parallel requests.
- B. False – improves delivery semantics, not concurrency.
- C. False – adds retry logic, not throughput directly.
- D. Partial – adds batching but not parallelism.
```

</details>

---

## Question 9

```markdown
What happens if `batch.size` is set too low?
```

**Options**

```markdown
- A. It increases latency
- B. It improves compression ratio
- C. It reduces throughput due to smaller batches
- D. It increases memory usage
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Small batches result in more frequent requests, reducing throughput and efficiency.

- A. False – may reduce latency slightly.
- B. False – smaller batches = worse compression.
- C. Correct – less batching = more overhead.
- D. False – smaller batches use less memory.
```

</details>

---

## Question 10

```markdown
Which combination provides the highest durability but at the cost of throughput?
```

**Options**

```markdown
- A. `acks=1`, `enable.idempotence=false`
- B. `acks=all`, `enable.idempotence=true`
- C. `acks=0`, `retries=0`
- D. `acks=1`, `compression.type=snappy`
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
This combo ensures exactly-once delivery and max durability but with added coordination overhead.

- A. Less durable.
- B. Correct – safest but more expensive.
- C. Fast but very risky.
- D. Compression improves throughput but not durability.
```

</details>

---

## Question 11

```markdown
How does increasing `linger.ms` affect batching?
```

**Options**

```markdown
- A. It disables batching
- B. It allows more records to accumulate per batch
- C. It forces immediate flush to Kafka
- D. It increases retry attempts
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
A higher `linger.ms` allows the producer to collect more messages before sending, improving batching.

- A. False – it improves batching.
- B. Correct – key effect of `linger.ms`.
- C. False – it delays flush.
- D. False – unrelated to retries.
```

</details>

---

## Question 12

```markdown
Which setting helps reduce producer-side CPU usage while maintaining throughput?
```

**Options**

```markdown
- A. `compression.type=gzip`
- B. `compression.type=snappy`
- C. `linger.ms=0`
- D. `acks=1`
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Snappy is a lightweight compression algorithm that balances CPU and speed, improving throughput with minimal CPU load.

- A. False – gzip uses more CPU.
- B. Correct – snappy is fast and efficient.
- C. False – lowers latency but doesn't affect CPU usage much.
- D. Partial – affects durability, not compression/CPU.
```

</details>

## Question 13

```markdown
What is the effect of enabling compression on the producer side in Kafka?
```

**Options**

```markdown
- A. Reduced producer memory usage
- B. Increased consumer CPU usage
- C. Reduced network bandwidth usage
- D. Increased end-to-end latency
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Compression shrinks payloads, lowering network usage.

- A. Incorrect: It increases producer memory while batching.
- B. Partially true: CPU cost rises on decompression.
- C. Correct: Smaller data over the wire.
- D. Partially true: Compression adds overhead but is usually offset by network speed gains.
```

</details>

## Question 14

```markdown
What is the relationship between `batch.size` and `linger.ms` in the Kafka producer configuration?
```

**Options**

```markdown
- A. They are mutually exclusive settings
- B. `linger.ms` is only relevant if `batch.size` is set to 0
- C. `batch.size` is only relevant if `linger.ms` is set to 0
- D. They work together to control when a batch is considered ready to send
```

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**

```markdown
In the Kafka producer, `batch.size` and `linger.ms` work together to determine when a batch of messages is sent:

- `batch.size` defines the maximum amount of data to collect before sending a batch.
- `linger.ms` defines how long to wait for more messages before sending the current batch.

A batch is sent when **either** the `batch.size` is reached **or** the `linger.ms` time elapses, whichever comes first.
This cooperation helps balance latency and throughput.
```

</details>

---

## Question 15

```markdown
What is the effect of increasing `batch.size` in a Kafka producer configuration?
```

**Options**

```markdown
- A. It increases the maximum size of each individual message
- B. It increases the maximum number of messages that can be sent in a single request
- C. It increases the maximum time a batch will wait before being sent
- D. It increases the maximum amount of memory the producer will use for buffering
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Increasing `batch.size` allows the Kafka producer to collect more messages before sending them in a single request, improving throughput. It refers to the **maximum bytes per batch**, not the number of messages directly, but more messages usually means more bytes.

- A is wrong — individual message size is managed by `max.request.size`.
- C is wrong — batching delay is controlled by `linger.ms`.
- D is misleading — `batch.size` is per partition, but total memory usage is controlled by `buffer.memory`.
```

</details>

---

## Question 16

```markdown
What happens if `linger.ms` is set to 0 in the Kafka producer configuration?
```

**Options**

```markdown
- A. The producer will never send any messages
- B. The producer will wait indefinitely for each batch to fill up before sending
- C. The producer will send each message as soon as it is received, without batching
- D. The producer will use the default linger time
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Setting `linger.ms = 0` means the producer **won’t wait at all** — messages are sent as soon as possible.
This reduces latency but disables batching benefits, lowering throughput.

- A is false — messages are still sent.
- B is the opposite of what happens — no waiting occurs.
- D is wrong — 0 is a valid override, not a fallback to default.
```

</details>

---

## Question 17

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

## Question 18

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



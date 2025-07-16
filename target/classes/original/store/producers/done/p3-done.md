## Question 15

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

## Question 16

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

## Question 17

```markdown
What is the relationship between `linger.ms` and `request.timeout.ms` in the Kafka producer configuration?
```

**Options**

```markdown
- A. They are redundant settings that control the same thing
- B. `linger.ms` should always be set higher than `request.timeout.ms`
- C. `request.timeout.ms` should always be set higher than `linger.ms`
- D. They control independent aspects of the producer behavior
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
`linger.ms` is how long the producer waits to fill a batch before sending.
`request.timeout.ms` is how long the producer waits for a broker acknowledgment.

To avoid premature timeouts, `request.timeout.ms` should **always** be set **higher** than `linger.ms`.

- A is incorrect — they control distinct stages (before send vs after send).
- B is backwards — the opposite is true.
- D is partially true, but misses the coordination requirement.
```

</details>

---

## Question 18

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

## Question 19

```markdown
In the Kafka producer API, what is the purpose of the `acks` configuration parameter?
```

**Options**

```markdown
- A. To specify the number of acknowledgments the producer requires the leader to have received before considering a request complete
- B. To specify the number of replicas that must acknowledge a write for the write to be considered successful
- C. To specify the number of times the producer will retry a failed request
- D. To specify the number of partitions a topic must have for the producer to send messages to it
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
`acks` determines how many acknowledgments the **producer requires from the leader** before it considers the write successful:

- `0`: No wait. Fire and forget.
- `1`: Wait for leader only.
- `all`: Wait for all in-sync replicas.

Higher values increase durability, but may reduce throughput.

- B is wrong — it's not about replicas directly, though `acks=all` includes replicas.
- C is controlled by the `retries` config.
- D is unrelated — partition count doesn’t depend on `acks`.
```

</details>

---

## Question 20

```markdown
How does the `min.insync.replicas` broker configuration interact with the `acks` producer configuration?
```

**Options**

```markdown
- A. They are completely independent settings
- B. `acks` must always be set to `all` for `min.insync.replicas` to have any effect
- C. `min.insync.replicas` is only relevant if `acks` is set to `1` or `all`
- D. If `acks` is set to `all`, writes will only succeed if the number of in-sync replicas is at least `min.insync.replicas`
```

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**

```markdown
`min.insync.replicas` and `acks` work together to ensure durability. When `acks=all`, the leader requires acknowledgments from the minimum number of in-sync replicas defined by `min.insync.replicas`. If fewer replicas are in-sync, the write will fail.

- A is wrong because the settings do interact.
- B is incorrect; `min.insync.replicas` affects behavior *only* when `acks=all`, not always.
- C is wrong because it has no effect with `acks=1`.
```

</details>

---

## Question 21

```markdown
What happens if a Kafka producer sends a message with `acks=all` to a topic partition with 3 replicas, but only 2 replicas are currently in-sync?
```

**Options**

```markdown
- A. The write will succeed and the producer will receive an acknowledgment
- B. The write will succeed but the producer will not receive an acknowledgment
- C. The write will be queued until the third replica comes back in-sync
- D. The write will fail and the producer will receive an error
```

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**

```markdown
With `acks=all`, the leader must wait for all in-sync replicas to acknowledge the write. If there are fewer in-sync replicas than required by `min.insync.replicas`, the broker will reject the write and return an error to the producer.

- A and B are incorrect: the write will not succeed.
- C is incorrect: Kafka does not queue writes for unavailable replicas.
```

</details>

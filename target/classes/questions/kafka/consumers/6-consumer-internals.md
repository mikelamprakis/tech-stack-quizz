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

## Question 16

```markdown
What happens if you try to call `poll()` on a KafkaConsumer from multiple threads simultaneously?
```

**Options**

```markdown
- A. The consumer will automatically coordinate the threads to process messages in parallel
- B. The consumer will throw a ConcurrentModificationException
- C. The behavior is undefined and may lead to unexpected results or errors
- D. The consumer will process messages sequentially, with each thread taking turns
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
KafkaConsumer is not thread-safe. Calling `poll()` concurrently from multiple threads leads to undefined behavior and possible errors. Always use one consumer per thread.

- A. Incorrect — no automatic coordination.
- B. Incorrect — no specific ConcurrentModificationException thrown.
- C. Correct — behavior is undefined.
- D. Incorrect — no built-in sequential thread handling.
```

</details>

---

## Question 19

```markdown
What is the primary benefit of Kafka's zero-copy optimization when sending data from producers to consumers?
```

**Options**

```markdown
- A. It reduces the memory overhead by avoiding data duplication in memory
- B. It minimizes the latency by eliminating the need for data serialization and deserialization
- C. It improves the security by encrypting the data during transmission
- D. It increases the parallelism by leveraging multiple CPU cores for data transfer
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
Zero-copy optimization avoids copying data between kernel and user space, reducing memory overhead and improving throughput.

- A. Correct — reduces memory copying.
- B. Incorrect — serialization still needed.
- C. Incorrect — zero-copy is unrelated to encryption.
- D. Incorrect — no direct effect on CPU core parallelism.
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

## Question 33

```markdown
How does a Kafka consumer determine which broker to connect to when reading data from a specific partition?
```

**Options**

```markdown
- A. The consumer connects to any available broker and requests the leader for the specific partition
- B. The consumer connects to the Zookeeper ensemble to determine the leader for the specific partition
- C. The consumer uses a round-robin algorithm to select a broker to connect to
- D. The consumer connects to all brokers in the cluster simultaneously
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
The consumer first contacts any broker to get metadata about the topic and partitions. This metadata includes which broker is the leader for the partition. The consumer then connects directly to that leader to fetch data.

- A. Correct — Connects to any broker for metadata, then to leader.
- B. Incorrect — Consumers do not contact Zookeeper.
- C. Incorrect — Selection is based on metadata, not round-robin.
- D. Incorrect — Consumer connects only to the partition leader broker.
```

</details>

---






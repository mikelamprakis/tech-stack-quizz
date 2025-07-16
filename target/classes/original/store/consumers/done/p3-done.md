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

## Question 17

```markdown
What is the recommended approach to process messages concurrently using the KafkaConsumer?
```

**Options**

```markdown
- A. Create a single KafkaConsumer instance and share it among multiple threads
- B. Create multiple KafkaConsumer instances, each running in its own thread
- C. Use a thread pool to process messages from a single KafkaConsumer instance
- D. Use a lock or synchronization mechanism to coordinate access to a shared KafkaConsumer instance
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
KafkaConsumer is not thread-safe. The recommended approach is to create multiple consumer instances, each in its own thread, allowing safe concurrent consumption.

- A. Incorrect — sharing one consumer across threads is unsafe.
- B. Correct — multiple consumers for concurrency.
- C. Incorrect — a single consumer with thread pool is unsafe.
- D. Incorrect — locking adds complexity and is discouraged.
```

</details>

---

## Question 18

```markdown
How does Kafka ensure that messages are processed in a balanced way when using multiple consumer instances in a consumer group?
```

**Options**

```markdown
- A. Kafka assigns an equal number of messages to each consumer instance
- B. Kafka assigns partitions to consumer instances in a round-robin fashion
- C. Kafka dynamically adjusts the assignment of partitions based on consumer load
- D. Kafka relies on ZooKeeper to distribute messages evenly among consumer instances
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Kafka assigns partitions to consumers using round-robin (or range) assignment strategies to balance load across consumer instances.

- A. Incorrect — Kafka assigns partitions, not messages directly.
- B. Correct — partitions assigned round-robin or by range.
- C. Incorrect — assignment not dynamically load-based.
- D. Incorrect — ZooKeeper not responsible for message distribution.
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

## Question 20

```markdown
What is the purpose of the `isolation.level` setting in the Kafka consumer configuration?
```

**Options**

```markdown
- A. To specify the maximum number of records to fetch in a single request
- B. To control the visibility of transactional messages
- C. To determine the behavior of the consumer when it encounters an invalid offset
- D. To set the maximum amount of time the consumer will wait for new messages
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
`isolation.level` determines whether the consumer reads all messages or only committed transactional messages.

- A. Incorrect — not related to fetch size.
- B. Correct — controls transactional message visibility.
- C. Incorrect — invalid offset handled differently.
- D. Incorrect — timeouts configured separately.
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

## Question 1

```markdown
What happens when you set `max.in.flight.requests.per.connection` to a value greater than 1 in a Kafka producer?
```

**Options**

```markdown
- A. It increases the throughput of the producer
- B. It increases the latency of the producer
- C. It can lead to out-of-order delivery of messages
- D. It has no effect on the producer's behavior
```

<details><summary>Response:</summary> 

**Answer:** C

**Explanation:**

```markdown
Setting `max.in.flight.requests.per.connection` to a value greater than 1 allows multiple requests to be sent without waiting for previous responses. While this improves throughput, if a retry occurs (e.g. after a transient failure), messages may be delivered out of order because some later messages may have already been acknowledged.

- A. Not always true; throughput may increase, but not guaranteed.
- B. Latency might be reduced rather than increased.
- C. Correct — out-of-order delivery is a known risk.
- D. Incorrect — it clearly affects producer behavior.
```

</details>

---

## Question 2

```markdown
To prevent network-induced duplicates when producing to Kafka, I should use:
```

**Options**

```markdown
- A. acks=0
- B. batch.size=0
- C. enable.idempotence=true
- D. retries=0
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Enabling idempotence guarantees that even with retries, messages won’t be duplicated. This setting must be turned on to ensure exactly-once semantics in producers.

- A. Risky — no delivery guarantees
- B. Prevents batching, doesn't affect duplicates
- C. Correct — enables safe retries
- D. Disabling retries reduces duplicates, but not reliably
```

</details>

---

## Question 3

```markdown
A Kafka topic has a replication factor of 3 and `min.insync.replicas=2`. How many brokers can go down before a producer with `acks=all` can't produce?
```

**Options**

```markdown
- A. One broker
- B. Two brokers
- C. All brokers can go down
- D. None
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
With `acks=all` and `min.insync.replicas=2`, at least two replicas must be available. Losing one broker is tolerable, but losing two violates this condition and the producer can no longer send data.

- A. Correct: One broker can go down before we drop below the `min.insync.replicas`.
- B. Incorrect: Losing two brokers leaves only one in-sync replica, which violates the condition.
- C. Incorrect: All brokers can't go down — that halts production entirely.
- D. Incorrect: One broker *can* go down; it's not “none.”
```

</details>

---

## Question 4

```markdown
What happens when you set `max.in.flight.requests.per.connection` to a value greater than 1 in a Kafka producer?
```

**Options**

```markdown
- A. It increases the throughput of the producer
- B. It increases the latency of the producer
- C. It can lead to out-of-order delivery of messages
- D. It has no effect on the producer's behavior
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Setting `max.in.flight.requests.per.connection > 1` allows multiple concurrent requests, improving throughput. But if a retry happens, messages may arrive out of order.

- A. True, but not the best answer. It does increase throughput, but order is the concern.
- B. Incorrect: It may reduce latency.
- C. Correct: It risks message reordering.
- D. Incorrect: It definitely affects producer behavior.
```

</details>

---

## Question 5

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

## Question 6

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

## Question 7

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

## Question 8

```markdown
Can a producer configured with `acks=all` and `retries=Integer.MAX_VALUE` ever experience data loss?
```

**Options**

```markdown
- A. No, this configuration guarantees no data loss under all circumstances
- B. Yes, if the total number of replicas for a partition drops below `min.insync.replicas`
- C. Yes, if `unclean.leader.election.enable=true` and all in-sync replicas fail
- D. Yes, if the producer crashes after the broker acknowledges the write but before the producer records the acknowledgment
```

<details><summary>Response:</summary>

**Answer:** B, C, D

**Explanation:**

```markdown
This configuration offers strong durability, but not perfect guarantees in all failure modes:

- A. False – some rare but real loss scenarios remain.
- B. If replicas drop below `min.insync.replicas`, Kafka rejects writes and data may be lost if retries are exhausted.
- C. If unclean leader election is allowed, a stale replica can become leader, causing message loss.
- D. A producer crash between broker acknowledgment and local acknowledgment tracking can lead to perceived loss.
```

</details>

---

## Question 9

```markdown
What happens if a Kafka producer sends a message to a topic partition and does not receive an acknowledgment from the broker?
```

**Options**

```markdown
- A. The producer will consider the message as successfully sent
- B. The producer will wait indefinitely for the acknowledgment
- C. The producer will retry sending the message based on its retry configuration
- D. The producer will immediately send the next message in the queue
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
If the broker doesn’t acknowledge the write, the producer retries based on its configuration:

- A. False – Acknowledgment is required.
- B. False – Producer uses `request.timeout.ms`.
- C. True – Producer retries if `retries > 0`.
- D. False – Retries precede sending the next message.
```

</details>

---

## Question 10

```markdown
What is the purpose of the `acks` parameter in Kafka producer configuration?
```

**Options**

```markdown
- A. To specify the number of partitions the producer should write to
- B. To specify the number of replicas that must acknowledge a write for it to be considered successful
- C. To specify the number of times the producer should retry sending a message
- D. To specify the maximum size of a batch of messages
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
`acks` controls how many broker acknowledgments are needed for a write to succeed:

- A. False – unrelated to partitions.
- B. True – defines durability level.
- C. False – that’s `retries`.
- D. False – that’s `batch.size`.
```

</details>

---

## Question 11

```markdown
What happens if the `acks` parameter is set to `all` and the minimum in-sync replicas (`min.insync.replicas`) setting is not satisfied?
```

**Options**

```markdown
- A. The producer will retry sending the message until the `min.insync.replicas` requirement is met
- B. The producer will write the message successfully, ignoring the `min.insync.replicas` setting
- C. The producer will receive an error indicating that the `min.insync.replicas` requirement is not met
- D. The producer will wait indefinitely until the `min.insync.replicas` requirement is met
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
When `acks=all`, if in-sync replicas < `min.insync.replicas`, the broker rejects the write:

- A. False – producer does not retry endlessly.
- B. False – it respects this constraint.
- C. True – error is returned.
- D. False – the producer will timeout or error.
```

</details>

---

## Question 12

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

## Question 13

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

## Question 14

```markdown
What happens when `max.in.flight.requests.per.connection` is set to 1 and `enable.idempotence` is set to true in the Kafka producer configuration?
```

**Options**

```markdown
- A. The producer will send messages in batches to improve throughput
- B. The producer will wait for each request to be acknowledged before sending the next request
- C. The producer will retry failed requests automatically
- D. The producer will disable message compression
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
With `max.in.flight.requests.per.connection=1` and `enable.idempotence=true`, the producer sends one message at a time per connection and waits for acknowledgment before proceeding. This guarantees strict ordering and exactly-once delivery but may reduce throughput.

- A. Incorrect – batching is unrelated to these specific settings.
- B. Correct – ensures strict order and delivery guarantees.
- C. Retries may occur but are not directly caused by this setting combination.
- D. Compression is not affected by idempotence or inflight settings.
```

</details>

---

## Question 15

```markdown
How does enabling idempotence affect the performance of the Kafka producer?
```

**Options**

```markdown
- A. It significantly improves the producer's throughput
- B. It has no impact on the producer's performance
- C. It may slightly reduce the producer's throughput
- D. It increases the producer's memory usage
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Enabling idempotence adds overhead due to extra metadata and tracking per message to ensure exactly-once delivery. While the performance impact is small, throughput may be slightly reduced.

- A. Incorrect – it adds overhead, not boosts.
- B. Incorrect – there is some measurable impact.
- C. Correct – slight decrease in throughput due to tracking logic.
- D. Incorrect – memory usage is not notably affected.
```

</details>

---

## Question 16

```markdown
What does the `acks=all` setting in the Kafka producer configuration ensure?
```

**Options**

```markdown
- A. The producer will receive an acknowledgment only after the message is written to all replicas
- B. The producer will receive an acknowledgment only after the message is written to the leader replica
- C. The producer will receive an acknowledgment only after the message is written to all in-sync replicas
- D. The producer will not wait for any acknowledgment and will consider the write successful immediately
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Setting `acks=all` ensures that a message is acknowledged only after all in-sync replicas (ISRs) have persisted it. This setting provides the highest durability guarantee but may introduce additional latency.

- A. Incorrect – all replicas, including out-of-sync ones, are not required.
- B. Incorrect – that's `acks=1`.
- C. Correct – acknowledges after all ISRs write the message.
- D. Incorrect – that's `acks=0`.
```

</details>

---

## Question 17

```markdown
In a topic with a replication factor of 3 and `min.insync.replicas` set to 2, what happens when a producer sends a message with `acks=all` and two replicas are not in-sync?
```

**Options**

```markdown
- A. The producer receives an acknowledgment and the message is successfully written
- B. The producer receives a `NotEnoughReplicasException` and the message is not written
- C. The producer waits indefinitely until at least two replicas become in-sync
- D. The message is written to the leader replica and the producer receives an acknowledgment
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
With `acks=all`, Kafka requires acknowledgment from all *in-sync* replicas. If only one replica (the leader) is in-sync and `min.insync.replicas=2`, the message will not be acknowledged, and a `NotEnoughReplicasException` is returned. The producer will not wait indefinitely, and the write is considered failed.

- A. Incorrect—minimum in-sync replicas requirement not met.
- B. Correct—the write fails due to insufficient in-sync replicas.
- C. Incorrect—Kafka does not wait forever, it fails the request.
- D. Incorrect—acks=all demands full ISR acknowledgement.
```

</details>

---
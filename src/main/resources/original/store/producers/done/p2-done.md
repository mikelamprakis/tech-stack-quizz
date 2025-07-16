## Question 8

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

## Question 9

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

## Question 10

```markdown
What is the effect of setting `acks=0` in a Kafka producer?
```

**Options**

```markdown
- A. The producer will wait for the broker to acknowledge the message before sending the next one
- B. The producer will wait for the leader and all replicas to acknowledge the message
- C. The producer will not wait for any acknowledgement from the broker
- D. The producer will throw an exception if the broker does not acknowledge the message
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
`acks=0` means fire-and-forget. No confirmation is expected, maximizing throughput but sacrificing reliability.

- A. Incorrect: That’s `acks=1` or `acks=all`.
- B. Incorrect: That’s `acks=all`.
- C. Correct: It doesn’t wait for any acknowledgment.
- D. Incorrect: No exception is thrown since no ack is expected.
```

</details>

---

## Question 11

```markdown
What is the relationship between `request.timeout.ms` and `delivery.timeout.ms` in a Kafka producer?
```

**Options**

```markdown
- A. `request.timeout.ms` should always be greater than `delivery.timeout.ms`
- B. `delivery.timeout.ms` should always be greater than `request.timeout.ms`
- C. They should always be set to the same value
- D. They are independent and can be set to any value
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
`request.timeout.ms` governs how long to wait for one request, while `delivery.timeout.ms` is the total retry budget. The latter must be greater.

- A. Incorrect: The opposite is recommended.
- B. Correct: `delivery.timeout.ms` includes potential retries.
- C. Incorrect: It could cause premature failures.
- D. Technically true, but not advised in practice.
```

</details>

---

## Question 12

```markdown
A Kafka producer application needs to send messages to a topic. The messages do not require any particular order. Which of the following properties are mandatory in the producer configuration? (Select two)
```

**Options**

```markdown
- A. `compression.type`
- B. `partitioner.class`
- C. `bootstrap.servers`
- D. `key.serializer`
- E. `value.serializer`
- F. `client.id`
```

<details><summary>Response:</summary>

**Answer:** C, E

**Explanation:**

```markdown
To connect and serialize messages, `bootstrap.servers` and `value.serializer` are required.

- A. Optional: Enhances efficiency, not required.
- B. Optional: Default partitioner is used otherwise.
- C. Required: Without this, no broker connection.
- D. Optional: Only needed if you're sending keys.
- E. Required: Values must be serialized.
- F. Optional: Useful for logging/monitoring, not mandatory.
```

</details>

---

## Question 13

```markdown
What is the purpose of setting `compression.type` in a Kafka producer configuration?
```

**Options**

```markdown
- A. To specify the compression algorithm used when sending data to Kafka
- B. To specify the compression algorithm used when storing data on Kafka brokers
- C. To enable or disable compression for the producer
- D. To set the compression level for the producer
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
The producer compresses records before transmission using the specified algorithm (e.g. `gzip`, `lz4`, `zstd`, etc.).

- A. Correct: It's about producer-to-broker compression.
- B. Misleading: Brokers store whatever is received.
- C. False: Compression is not simply on/off; it's about type.
- D. False: Kafka doesn’t let you tune compression level.
```

</details>

---

## Question 14

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

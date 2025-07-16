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
With `acks=0`, the producer sends data and does not wait for any acknowledgement. It offers the highest throughput but no delivery guarantees.

- A. Describes `acks=1` or `acks=all`
- B. Describes `acks=all`
- C. Correct — no acknowledgements are expected
- D. Incorrect — producer does not throw errors when acks=0
```

</details>

---

## Question 3

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
`delivery.timeout.ms` is the total time allowed to deliver a message including all retries. `request.timeout.ms` is per-request. If delivery timeout is less than request timeout, a send may time out before the request does, which is not ideal.

- A. Opposite — would cause premature timeouts.
- B. Correct — ensures retries work within delivery window.
- C. Not necessary, and may cause unintentional issues.
- D. Technically true, but not recommended without understanding the interplay.
```

</details>

---

## Question 4

```markdown
A Kafka producer application needs to send messages to a topic. The messages do not require any particular order. Which of the following properties are mandatory in the producer configuration? (Select two)
```

**Options**

```markdown
- A. compression.type
- B. partitioner.class
- C. bootstrap.servers
- D. key.serializer
- E. value.serializer
- F. client.id
```

<details><summary>Response:</summary> 

**Answer:** C, E

**Explanation:**

```markdown
`bootstrap.servers` is required to connect to Kafka brokers, and `value.serializer` is needed to serialize the message values. All others are optional or have defaults.

- A. Optional performance setting
- B. Optional for custom partitioning
- C. Required — without it, producer can't connect
- D. Only needed if you use keys
- E. Required — must serialize value
- F. Optional identifier for logs/metrics
```

</details>

---

## Question 5

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

## Question 6

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

## Question 7

```markdown
Where will a message with no key be stored?
```

**Options**

```markdown
- A. The first partition of the topic
- B. A random topic partition
- C. Any of the topic partitions using round-robin
- D. The partition with least messages
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Kafka producers use round-robin partitioning when no key is provided, spreading messages evenly across partitions.

- A. Not guaranteed — depends on round-robin rotation
- B. Random suggests non-deterministic; not quite accurate
- C. Correct — round-robin is the default partitioner strategy without keys
- D. Kafka does not check partition sizes
```

</details>
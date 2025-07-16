## Question 6

```markdown
A Kafka consumer is consuming from a topic partition. It sends a fetch request to the broker and receives a 'Replica Not Available' error. What is the consumer's next action?

```

**Options**

```markdown
- A. Backs off and retries the fetch request after a short delay
- B. Sends an offset commit request to trigger partition rebalancing
- C. Sends a metadata request to refresh its view of the cluster
- D. Closes the connection and tries connecting to a different broker
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
The consumer should refresh metadata to identify a healthy broker that can serve the partition. Rebalancing, retries, or switching brokers won't help without fresh metadata.

- A. Not optimal, consumer needs updated metadata first.
- B. Offset commit doesn't solve replica availability.
- C. Correct — refresh metadata to get current cluster info.
- D. Closing connection unnecessarily without metadata update.
```

</details>

---

## Question 7

```markdown
What happens if you produce to a topic that does not exist, and the broker setting `auto.create.topics.enable` is set to `false`?

```

**Options**

```markdown
- A. The broker will create the topic with default configurations
- B. The broker will reject the produce request and the producer will throw an exception
- C. The producer will automatically create the topic
- D. The producer will wait until the topic is created
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
When `auto.create.topics.enable=false`, Kafka disallows topic creation on demand. The broker rejects produce requests for non-existent topics, causing producer exceptions.

- A. False — no topic creation when disabled.
- B. Correct — broker rejects and producer errors out.
- C. False — producer does not create topics.
- D. False — producer will not wait, it fails immediately.
```

</details>

---

## Question 8

```markdown
What is the default value of `auto.create.topics.enable` in Kafka?

```

**Options**

```markdown
- A. `true`
- B. `false`
- C. It is not set by default
- D. It depends on the Kafka version
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
By default, Kafka has `auto.create.topics.enable` set to `true`. This means topics are auto-created upon first access unless this is explicitly disabled.

- A. Correct — default is true.
- B. False — not default.
- C. False — there is a default setting.
- D. Not generally true; defaults are stable across versions.
```

</details>

---

## Question 9

```markdown
When a topic is automatically created due to `auto.create.topics.enable` being `true`, what configurations are used for the new topic?

```

**Options**

```markdown
- A. The configurations specified by the producer or consumer
- B. The default configurations set on the broker
- C. A combination of producer/consumer configurations and broker defaults
- D. No configurations are set, the topic is created with empty configuration
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Kafka uses the broker's default topic configurations like `num.partitions` and `default.replication.factor` when auto-creating a topic. Client-side configs are ignored.

- A. False — client configs are ignored for auto-creation.
- B. Correct — broker defaults are applied.
- C. False — no mix of configs.
- D. False — defaults are applied, not empty.
```

</details>

---

## Question 10

```markdown
Can Kafka's zero-copy optimization be used in combination with compression?

```

**Options**

```markdown
- A. Yes, zero-copy and compression can be used together seamlessly.
- B. No, zero-copy is incompatible with compression and cannot be used together.
- C. Zero-copy can be used with compression, but it requires additional configuration.
- D. Zero-copy is automatically disabled when compression is enabled.
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
Zero-copy works independently of compression. Compression happens at the producer before data hits disk. Zero-copy transfers data from disk to network socket efficiently, so both can operate seamlessly.

- A. Correct — both work together naturally.
- B. False — no incompatibility.
- C. False — no extra config needed.
- D. False — zero-copy is not disabled.
```

</details>

---

## Question 14

```markdown
A Kafka producer is configured to use the `acks=all` setting while publishing messages to a topic partition that has a replication factor of 3. The topic is also configured with `min.insync.replicas=2`. Broker A hosts the current leader for this partition, while Brokers B and C host the replicas. Due to unforeseen circumstances, both Broker B and Broker C go offline simultaneously. What is the impact on the producer's ability to successfully publish messages to this partition?

```

**Options**

```markdown
- A. The producer will be able to publish messages, but with potential data loss.
- B. The producer will temporarily be unable to publish messages until at least one replica broker comes back online.
- C. The producer will continue to publish messages successfully without any impact.
- D. The producer will immediately switch to another topic's partition that has all replicas available.
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
With `acks=all` and `min.insync.replicas=2`, the producer needs acknowledgments from the leader plus at least one ISR replica. If both replicas go offline, ISR count drops below 2, so the producer cannot publish until a replica rejoins.

- A. False — no publishing possible, not just data loss.
- B. Correct — producer blocks until ISR recovers.
- C. False — publishing is impacted.
- D. False — producer doesn't switch partitions automatically.
```

</details>

---

## Question 15

```markdown
When a Kafka broker starts up, it performs various initialization tasks. Which of the following is NOT one of these tasks?

```

**Options**

```markdown
- A. Registering itself with Zookeeper
- B. Loading the replica assignment for each partition it hosts
- C. Creating a new Zookeeper znode for each topic it has partitions for
- D. Initializing the log directories for each partition it hosts
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Topic znodes are created during topic creation, not broker startup. Broker startup includes registering with Zookeeper, loading replica assignments, and initializing log directories.

- A. Broker registers itself with Zookeeper at startup.
- B. Broker loads its partition replica assignments.
- C. Correct — broker does not create topic znodes.
- D. Broker initializes log directories for its partitions.
```

</details>

---

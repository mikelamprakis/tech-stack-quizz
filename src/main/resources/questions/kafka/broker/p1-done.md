## Question 2

```markdown
A Kafka broker is configured with the following settings:

`num.replication.fetchers=4`  
`replica.fetch.max.bytes=1048576`

What is the maximum amount of data that can be fetched by the broker for replication purposes in a single request?
```

**Options**

```markdown
- A. 1 MB
- B. 4 MB
- C. 1048576 bytes
- D. 4194304 bytes
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
`replica.fetch.max.bytes` is the per-fetcher limit, set to 1 MB (1048576 bytes).  
Each of the 4 fetchers operates independently, so per request the max fetch size is 1 MB.

- A. Correct — per fetch request limit is 1 MB.
- B. Incorrect — 4 MB is total across all fetchers, not per request.
- C. Correct in bytes but answer expects MB format, so A preferred.
- D. Incorrect — 4 * 1 MB = 4 MB total across fetchers, not per request.
```

</details>

---

## Question 4

```markdown
A client connects to a broker in a Kafka cluster and sends a produce request for a topic partition.  
The broker responds with a 'Not Enough Replicas' error. What does the client do next?
```

**Options**

```markdown
- A. Retries sending the produce request to the same broker
- B. Sends metadata request to the same broker to refresh its metadata
- C. Sends produce request to the controller broker
- D. Sends metadata request to the Zookeeper to find the controller broker
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Upon 'Not Enough Replicas' error, the client refreshes its metadata from the broker to get updated ISR and broker status.  
The client does not contact the controller or Zookeeper directly.

- A. Incorrect — retry without metadata update unlikely to succeed.
- B. Correct — refresh metadata to get latest ISR info.
- C. Incorrect — produce requests go to leader brokers, not necessarily controller.
- D. Incorrect — clients do not query Zookeeper directly.
```

</details>

---

## Question 6

```markdown
A Kafka broker is configured as follows:

`log.segment.bytes=1073741824`  
`log.segment.ms=86400000`  
`log.retention.bytes=-1`  
`log.retention.ms=604800000`

When will Kafka start a new log segment for a partition, and how long will the old segments be retained?
```

**Options**

```markdown
- A. New segment after 1 GB or 24 hrs; retain for 7 days
- B. New segment after 1 GB or 24 hrs; retain indefinitely
- C. New segment after 1 GB; retain for 7 days or if total exceeds 1 GB
- D. New segment after 24 hrs; retain for 7 days or if total exceeds 1 GB
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
Kafka rolls log segments on reaching either size (`1GB`) or time (`24 hours`).  
Retention deletes old segments older than 7 days (`log.retention.ms=604800000`).  
Size-based retention is disabled (`log.retention.bytes=-1`).

- A. Correct — segment rolling and retention times match this option.
- B. Incorrect — retention is time-based, not indefinite.
- C. Incorrect — size retention disabled.
- D. Incorrect — segments roll on size or time, not time alone.
```

</details>

---

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

## Question 12

```markdown
In a Kafka cluster, the Controller is a critical component for managing cluster state. Which of the following statements accurately describe the role and election of the Controller? (Select two)

```

**Options**

```markdown
- A. Elected by broker majority
- B. Elected by Zookeeper ensemble
- C. Responsible for partition leader election
- D. Manages consumer group offsets
- E. Automatically assigns replicas to brokers based on load
```

<details><summary>Response:</summary>

**Answer:** B and C

**Explanation:**

```markdown
- B. Correct — the Controller is elected by the Zookeeper ensemble using ephemeral znodes.
- C. Correct — the Controller manages partition leader election, especially after failures.
- A. Incorrect — election is not by broker majority.
- D. Incorrect — consumer offset management is handled by Kafka brokers and stored in `__consumer_offsets`.
- E. Incorrect — replica assignment is done at topic creation or manually, not automatically by Controller.

Note: Newer KRaft mode changes this, but Zookeeper is still common.
```

</details>

---

## Question 13

```markdown
In the context of Kafka's distributed architecture, broker elections are vital for cluster health and stability. Consider the following advanced scenarios where Kafka's internal mechanisms must decide on leadership roles:

```

**Options**

```markdown
- A. The Zookeeper ensemble elects the new Controller based on ephemeral node creation sequence.
- B. The new partition leader is elected based on the ISR list order, favoring replicas with the most recent updates.
- C. Brokers in the main cluster segment with access to Zookeeper retain their roles, while isolated brokers step down until connectivity is restored.
- D. A broker majority within the isolated segment elects a new temporary Controller until the network partition is resolved.
- E. The election of a new partition leader among replicas with identical network latency is determined by a random selection process.
```

<details><summary>Response:</summary>

**Answer:** A, B, and C

**Explanation:**

```markdown
- A. Correct — Zookeeper elects Controller via ephemeral nodes based on creation sequence.
- B. Correct — partition leader election uses the ISR list to pick the most recent replica.
- C. Correct — brokers without Zookeeper connection lose leadership roles.
- D. Incorrect — Kafka does not elect Controllers within isolated network segments.
- E. Incorrect — leader election is deterministic, not random.
```

</details>

## Question 4

```markdown
The Controller is a broker that is... (select two)
```

**Options**

```markdown
- A. elected by broker majority
- B. elected by Zookeeper ensemble
- C. is responsible for partition leader election
```

<details><summary>Response:</summary>

**Answer:** B, C

**Explanation:**

```markdown
The Controller broker is elected by the Zookeeper ensemble and is responsible for partition leader election. Only one broker acts as Controller at a time.

- A. Incorrect — not elected by broker majority.
- B. Correct — elected by Zookeeper ensemble.
- C. Correct — responsible for partition leader election.
```

</details>

---

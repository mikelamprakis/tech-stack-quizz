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

## Question 3

```markdown
What happens to the replicas when a broker in a Kafka cluster goes down?
```

**Options**

```markdown
- A. All replicas on the failed broker are permanently lost
- B. The replicas on the failed broker are automatically redistributed to other brokers
- C. The replicas on the failed broker become unavailable until the broker is restarted
- D. The replicas on the failed broker are immediately promoted to be leaders on other brokers
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
When a broker fails:

- C. Its replicas are temporarily unavailable.
- Leader roles are transferred to other in-sync replicas.
- When the broker comes back, it catches up and resumes replica duties.

Kafka ensures availability unless too many replicas are lost.
```

</details>

---

## Question 8

```markdown
How does Kafka ensure data integrity and consistency across replicas?

- A. By using a two-phase commit protocol
- B. By relying on ZooKeeper for distributed consensus
- C. By implementing a leader-follower replication model
- D. By using a gossip protocol for eventual consistency
```

**Options**

```markdown
- A. Two-phase commit protocol
- B. ZooKeeper for consensus
- C. Leader-follower replication model
- D. Gossip protocol
```

<details><summary>Response:</summary> 

**Answer:** C

**Explanation:**

```markdown
Kafka achieves consistency through replication:

- A. Two-phase commit – ❌ Not used for replication integrity.
- B. ZooKeeper for consensus – ❌ Used for metadata, not replication consistency.
- C. Leader-follower model – ✅ Producers write to leader, followers replicate.
- D. Gossip protocol – ❌ Not applicable in Kafka's architecture.
```

</details>

## Question 9

```markdown
What happens to the replicas when a broker in a Kafka cluster goes down?

- A. All replicas on the failed broker are permanently lost
- B. The replicas on the failed broker are automatically redistributed to other brokers
- C. The replicas on the failed broker become unavailable until the broker is restarted
- D. The replicas on the failed broker are immediately promoted to be leaders on other brokers
```

**Options**

```markdown
- A. All replicas on the failed broker are permanently lost
- B. The replicas on the failed broker are automatically redistributed to other brokers
- C. The replicas on the failed broker become unavailable until the broker is restarted
- D. The replicas on the failed broker are immediately promoted to be leaders on other brokers
```

<details><summary>Response:</summary> 

**Answer:** C

**Explanation:**

```markdown
When a broker fails:

- A. All replicas on the failed broker are permanently lost – ❌ Data isn't lost.
- B. Automatically redistributed – ❌ Kafka does not automatically move replicas.
- C. Become unavailable until restart – ✅ Correct behavior.
- D. Promoted to leaders – ❌ Only existing in-sync replicas can become leaders.
```

</details>

---

## Question 11

```markdown
How does Kafka ensure data integrity and consistency across replicas?
```

**Options**

```markdown
- A. By using a two-phase commit protocol
- B. By relying on ZooKeeper for distributed consensus
- C. By implementing a leader-follower replication model
- D. By using a gossip protocol for eventual consistency
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Kafka uses:

- C. A leader-follower model for consistency.
  - Leader handles all writes.
  - Followers replicate data.
  - Data is committed once replicated to all in-sync replicas.

Other options are incorrect:
- A. Kafka doesn’t use two-phase commit.
- B. ZooKeeper manages metadata, not replication.
- D. Gossip protocols aren’t used by Kafka.
```

</details>

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
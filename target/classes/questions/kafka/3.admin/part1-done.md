## Question 1

```markdown
Which of the following is stored in Zookeeper for a Kafka cluster? (Select two)
```

**Options**

```markdown
- A. Consumer offsets
- B. Kafka broker information
- C. Topic partition assignments
- D. Topic-level configurations
- E. Producer client IDs
```

<details><summary>Response:</summary>

**Answer:** B, D

**Explanation:**

```markdown
In a Kafka cluster, Zookeeper stores:

- B. Kafka broker information: It keeps details about each broker in the cluster.
- D. Topic-level configurations: This includes settings like retention and replication.

Incorrect options:

- A. Consumer offsets are stored in the Kafka topic `__consumer_offsets`.
- C. Partition assignments are handled by the Kafka controller.
- E. Producer client IDs are not stored in Zookeeper.
```

</details>

---

## Question 2

```markdown
In a Kafka cluster, you have a topic with 6 partitions and a replication factor of 3. How many replicas of each partition will be spread across the brokers?
```

**Options**

```markdown
- A. 1 replica per broker
- B. 2 replicas per broker
- C. 3 replicas per broker
- D. 6 replicas per broker
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Each partition has 3 replicas:

- 1 leader + 2 followers.
- For 6 partitions × 3 replicas = 18 replicas total.
- These are spread across brokers for redundancy.

Kafka ensures load balancing but the number of replicas per broker depends on the cluster size.
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

## Question 4

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

## Question 5

```markdown
Which of the following is stored in Zookeeper for a Kafka cluster? (Select two)

- A. Consumer offsets
- B. Kafka broker information
- C. Topic partition assignments
- D. Topic-level configurations
- E. Producer client IDs
```

**Options**

```markdown
- A. Consumer offsets
- B. Kafka broker information
- C. Topic partition assignments
- D. Topic-level configurations
- E. Producer client IDs
```

<details><summary>Response:</summary> 

**Answer:** B, D

**Explanation:**

```markdown
Kafka uses ZooKeeper to store metadata such as:

- A. Consumer offsets – ❌ Stored in `__consumer_offsets` topic.
- B. Kafka broker information – ✅ Includes broker registrations and cluster membership.
- C. Topic partition assignments – ❌ Managed by the Kafka controller.
- D. Topic-level configurations – ✅ Includes settings like retention.
- E. Producer client IDs – ❌ Maintained client-side.
```

</details>

---

## Question 6

```markdown
In a Kafka cluster, you have a topic with 6 partitions and a replication factor of 3. How many replicas of each partition will be spread across the brokers?

- A. 1 replica per broker
- B. 2 replicas per broker
- C. 3 replicas per broker
- D. 6 replicas per broker
```

**Options**

```markdown
- A. 1 replica per broker
- B. 2 replicas per broker
- C. 3 replicas per broker
- D. 6 replicas per broker
```

<details><summary>Response:</summary> 

**Answer:** C

**Explanation:**

```markdown
Each partition has 3 replicas:

- A. 1 replica per broker – ❌ Not accurate for replication factor 3.
- B. 2 replicas per broker – ❌ Underreplicated.
- C. 3 replicas per broker – ✅ Each of the 6 partitions has 3 replicas (18 total).
- D. 6 replicas per broker – ❌ Misrepresents Kafka's replication mechanism.
```

</details>

---

## Question 7

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

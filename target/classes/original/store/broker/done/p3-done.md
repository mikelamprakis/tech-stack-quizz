## Question 11

```markdown
Consider a Kafka topic with the following configuration:

* `cleanup.policy` = "compact,delete"
* `min.cleanable.dirty.ratio` = 0.5
* `delete.retention.ms` = 86400000 (1 day)
* `segment.ms` = 43200000 (12 hours)

Which of the following statements correctly describes the behavior of log compaction and deletion for this topic?

```

**Options**

```markdown
- A. Log compaction and deletion are mutually exclusive; only one policy can be active at any time.
- B. Log compaction will occur once 50% of the segment data is marked as dirty, and logs older than 1 day will be deleted.
- C. Deleted records are removed immediately from the log; `delete.retention.ms` specifies the retention time for all records.
- D. `segment.ms` dictates the maximum lifespan of any record in the log, after which it is eligible for compaction or deletion.
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Kafka supports both compaction and deletion on a topic when `cleanup.policy = compact,delete`.

- Compaction triggers once 50% of a segment is dirty (`min.cleanable.dirty.ratio = 0.5`).
- `delete.retention.ms` specifies how long tombstones (delete markers) are retained (1 day here).
- `segment.ms` controls segment roll frequency, not record retention duration.

- A. Incorrect — compaction and deletion can co-exist.
- B. Correct — reflects actual behavior.
- C. Incorrect — deleted records (tombstones) are kept for retention time.
- D. Incorrect — segment.ms rolls segments, doesn't control retention directly.
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

## Question 1

```markdown
Kafka is configured with following parameters - log.retention.hours = 168 log.retention.minutes = 168 log.retention.ms = 168  
How long will the messages be retained for?
```

**Options**

```markdown
- A. 168ms
- B. 168 mins
- C. 168 hours
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
If more than one similar config is specified, the smaller unit size will take precedence.  
Here all three are set to 168, but Kafka will use the largest unit value that makes sense for retention, which is hours in this case.

- A. Incorrect — milliseconds is the smallest unit but 168 ms is very short retention.
- B. Incorrect — minutes would be shorter retention.
- C. Correct — messages will be retained for 168 hours.
```

</details>

---

## Question 2

```markdown
Once sent to a topic, a message can be modified
```

**Options**

```markdown
- A. Yes
- B. No
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Kafka logs are append-only and the data is immutable. Once a message is written to a topic partition, it cannot be modified.

- A. Incorrect — messages are immutable.
- B. Correct — messages cannot be modified.
```

</details>

---

## Question 3

```markdown
How often is log compaction evaluated?
```

**Options**

```markdown
- A. Every time a message is sent to Kafka
- B. Every time a new partition is created
- C. Every time a segment is closed
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Log compaction is evaluated every time a segment is closed. It will be triggered if enough data is "dirty" based on the dirty ratio configuration.

- A. Incorrect — compaction is not evaluated on every message.
- B. Incorrect — partition creation does not trigger compaction.
- C. Correct — compaction is evaluated on segment close.
```

</details>

---

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

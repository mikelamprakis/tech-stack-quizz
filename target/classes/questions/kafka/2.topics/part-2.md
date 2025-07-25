## Question 1

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

## Question 2

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

## Question 3

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

## Question 4

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

## Question 5

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

## Question 6

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

## Question 7

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

## Question 8

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

## Question 9

```markdown
A Kafka cluster is configured with the following:

`log.retention.hours=48`  
`log.retention.bytes=1073741824`  
`log.segment.bytes=536870912`

Assuming a topic has a constant message production rate, which of the following factors will trigger a log segment to be eligible for deletion?
```

**Options**

```markdown
- A. The log segment is older than 48 hours.
- B. The log segment size exceeds 536870912 bytes (512 MB).
- C. The total size of all log segments for the topic exceeds 1073741824 bytes (1 GB).
- D. All of the above.
```

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**

```markdown
Kafka retention policies combine time and size limits:  
- Segments older than 48 hours are eligible for deletion.  
- Segment size exceeding 512 MB can trigger rolling and deletion.  
- Total log size exceeding 1 GB triggers deletion of oldest segments.  

- A. Correct — time-based retention.
- B. Correct — segment size affects segment rolling.
- C. Correct — total size triggers deletion of old segments.
- D. Correct — all factors apply.
```

</details>

---

## Question 10

```markdown
A Kafka cluster has the following configuration:

`unclean.leader.election.enable=false`

What is the implication of this setting when a partition leader fails and there are no in-sync replicas (ISRs) available?
```

**Options**

```markdown
- A. The partition will remain unavailable until the failed leader recovers.
- B. The partition will elect a new leader from the out-of-sync replicas to maintain availability.
- C. The partition will automatically create a new replica to replace the failed leader.
- D. The partition will be reassigned to another broker in the cluster.
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
When `unclean.leader.election.enable=false`, Kafka disallows election of a leader from out-of-sync replicas.  
So if no ISR is available after leader failure, the partition remains unavailable to preserve data consistency.

- A. Correct — partition remains unavailable until original leader recovers.
- B. Incorrect — election from out-of-sync replicas is disabled.
- C. Incorrect — Kafka does not create replicas automatically.
- D. Incorrect — partition is not reassigned automatically.
```

</details>

---

## Question 11

```markdown
Assuming a Kafka topic is configured with the following settings:

`log.segment.bytes = 1073741824` (1GB)  
`log.retention.ms = 86400000` (1 day)  
`log.retention.bytes = -1`

Which of the following statements accurately describes the log retention policy for this Kafka topic?
```

**Options**

```markdown
- A. Logs are retained based on size; once the log size exceeds 1GB, older segments are deleted.
- B. Logs are retained for exactly one day, regardless of the size of the log.
- C. Logs are retained until the size of the log exceeds 1GB or for one day, whichever comes first.
- D. Logs are retained indefinitely, as `log.retention.bytes` is set to -1, overriding other retention configurations.
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- `log.retention.ms = 86400000` enables time-based retention (1 day).  
- `log.retention.bytes = -1` disables size-based retention.  
- `log.segment.bytes` controls segment file size, not retention policy.

Thus, logs are retained based on time only (1 day), ignoring size.

- A. Incorrect — size-based retention is disabled.
- B. Correct — logs retained for one day regardless of size.
- C. Incorrect — size limit is ignored.
- D. Incorrect — logs are not retained indefinitely.
```

</details>

---

## Question 12

```markdown
A Kafka cluster is configured with:

`default.replication.factor=2`  
`min.insync.replicas=2`

What is the minimum number of brokers required to tolerate one failure and still serve write requests?
```

**Options**

```markdown
- A. 1
- B. 2
- C. 3
- D. 4
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
`min.insync.replicas=2` means two replicas must acknowledge writes.  
To tolerate one broker failure while maintaining two in-sync replicas, you need **3 brokers** in total.

- A. Incorrect — one broker cannot meet insync requirements.
- B. Incorrect — two brokers cannot tolerate one failure and still maintain 2 ISRs.
- C. Correct — three brokers allow one failure and 2 ISRs remain.
- D. Incorrect — more than needed.
```

</details>

---

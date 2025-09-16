## Question 1

```markdown
Which broker configuration parameter sets the default number of partitions for auto-created topics?
```

**Options**
```markdown
- A. `default.replication.factor`
- B. `num.partitions`
- C. `auto.create.topics.enable`
- D. `min.insync.replicas`
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- A. Incorrect. This sets the default replication factor, not partition count.
- B. Correct. `num.partitions` defines the default partition count for auto-created topics.
- C. Incorrect. This enables/disables auto-creation but doesn't control partition count.
- D. Incorrect. This relates to replication safety, not partition defaults.
```

</details>

---

## Question 2

```markdown
What happens if `auto.create.topics.enable=false` and a producer writes to a non-existent topic?
```

**Options**
```markdown
- A. The broker creates the topic with 100 partitions.
- B. The producer receives an `UnknownTopicOrPartitionException`.
- C. The message is silently discarded.
- D. The broker uses a temporary in-memory topic.
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- A. Incorrect. The topic will not be created at all.
- B. Correct. The producer will fail with `UnknownTopicOrPartitionException`.
- C. Incorrect. Kafka does not silently discard messages in this scenario.
- D. Incorrect. Kafka does not use temporary in-memory topics.
```

</details>

---

## Question 3

```markdown
Which configuration controls how long a follower replica can lag behind the leader before being removed from ISR?
```

**Options**
```markdown
- A. `replica.fetch.wait.max.ms`
- B. `replica.lag.time.max.ms`
- C. `zookeeper.session.timeout.ms`
- D. `log.flush.interval.messages`
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- A. Incorrect. This controls how long a replica waits for new data, not ISR membership.
- B. Correct. `replica.lag.time.max.ms` defines the maximum allowed lag before removal from ISR.
- C. Incorrect. This is a ZooKeeper session timeout setting.
- D. Incorrect. This controls disk flushes, not replication.
```

</details>

---

## Question 4

```markdown
What is the purpose of the `log.retention.bytes` broker configuration?
```

**Options**
```markdown
- A. To set the maximum size of an individual message.
- B. To limit the total size of a partition's log before old segments are deleted.
- C. To control the size of ZooKeeper transaction logs.
- D. To configure the broker's disk cache size.
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- A. Incorrect. Message size limits are controlled by `message.max.bytes`.
- B. Correct. This sets size-based retention for partition logs.
- C. Incorrect. This does not affect ZooKeeper.
- D. Incorrect. This is unrelated to disk caching.
```

</details>

---

## Question 5

```markdown
Which configuration enables log compaction for a topic?
```

**Options**
```markdown
- A. `cleanup.policy=compact`
- B. `log.retention.hours=168`
- C. `min.compaction.lag.ms=3600000`
- D. `segment.bytes=1073741824`
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
- A. Correct. `cleanup.policy=compact` enables log compaction.
- B. Incorrect. This sets time-based retention, not compaction.
- C. Incorrect. This sets a delay before compaction but doesn't enable it.
- D. Incorrect. This controls segment file size, not compaction.
```

</details>

---

## Question 6

```markdown
What does the `num.replica.fetchers` broker configuration control?
```

**Options**
```markdown
- A. The number of threads used to replicate data from leaders.
- B. The maximum number of followers per partition.
- C. The number of ZooKeeper connections.
- D. The consumer fetch threads per broker.
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
- A. Correct. This sets the number of threads pulling data from leaders.
- B. Incorrect. This is controlled by `replication.factor`, not fetch threads.
- C. Incorrect. This is unrelated to ZooKeeper connections.
- D. Incorrect. This is a broker-side setting, not consumer-side.
```

</details>

---

## Question 7

```markdown
Which configuration prevents writes if fewer than N replicas are in sync?
```

**Options**
```markdown
- A. `replication.factor`
- B. `min.insync.replicas`
- C. `unclean.leader.election.enable`
- D. `offsets.topic.replication.factor`
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- A. Incorrect. This sets total replicas, not the minimum in-sync requirement.
- B. Correct. `min.insync.replicas` defines the minimum ISR count for writes.
- C. Incorrect. This controls leader election behavior, not write acceptance.
- D. Incorrect. This is specific to the offsets topic.
```

</details>

---

## Question 8

```markdown
What is the purpose of the `broker.id` configuration?
```

**Options**
```markdown
- A. To set the broker's network port.
- B. To uniquely identify the broker in the cluster.
- C. To determine the broker's rack location.
- D. To configure the broker's JVM heap size.
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- A. Incorrect. The port is set by `listeners`, not broker.id.
- B. Correct. Each broker must have a unique integer ID.
- C. Incorrect. Rack awareness uses `broker.rack`, not broker.id.
- D. Incorrect. This is unrelated to JVM configuration.
```

</details>

---

## Question 9

```markdown
Which configuration controls how often log segments are rolled based on time?
```

**Options**
```markdown
- A. `segment.bytes`
- B. `segment.ms`
- C. `log.roll.hours`
- D. `log.flush.interval.ms`
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- A. Incorrect. This controls size-based rolling, not time-based.
- B. Correct. `segment.ms` defines the maximum time before rolling a new segment.
- C. Incorrect. Deprecated alias for `segment.ms`.
- D. Incorrect. This controls disk flushes, not segment rolling.
```

</details>

---

## Question 10

```markdown
What does `log.dir` specify in broker configuration?
```

**Options**
```markdown
- A. The directory for ZooKeeper data.
- B. The location of Kafka's configuration files.
- C. The directory where partition logs are stored.
- D. The temporary workspace for consumer offsets.
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Incorrect. This is unrelated to ZooKeeper.
- B. Incorrect. Configuration files are typically in `/etc/kafka`.
- C. Correct. This defines where partition log segments are stored.
- D. Incorrect. Offsets are stored in a special topic or ZooKeeper.
```

</details>

---

## Question 11
```markdown
How often does a Kafka broker refresh its metadata cache about other brokers and partitions?  
```

**Options**
```markdown
- A. Every 5 seconds  
- B. Only at startup  
- C. When triggered by a metadata change (e.g., broker failure)  
- D. Continuously via a background thread  
```

<details><summary>Response:</summary>  

**Answer:** C

**Explanation:**
```markdown
- C. Correct — Brokers update metadata cache dynamically upon changes (e.g., broker down, topic creation).  
- A/B/D. Incorrect — No fixed intervals; updates are event-driven.  
```  
</details>  

---

## Question 12

```markdown
Which event causes a Kafka broker to proactively refresh its metadata cache?
```

**Options**

```markdown
- A. A new consumer joins the cluster
- B. A partition leader election occurs
- C. A producer publishes a message
- D. A scheduled 5-minute interval passes
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- B. Correct — Leader elections trigger metadata updates to reflect new partition leadership.
- A/C/D. Incorrect — Consumer/producer actions and fixed intervals do not trigger refreshes.
```

</details>

---


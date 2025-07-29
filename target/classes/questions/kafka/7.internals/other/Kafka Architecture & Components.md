## Question 1
```markdown  
In Kafka, why can’t a single topic partition be consumed by multiple consumers within the same consumer group?  
```  

**Options**
```markdown  
- A. Kafka enforces exclusive locks on partitions to prevent concurrent access.  
- B. Partitions are designed to guarantee order, and parallel consumers would violate this.  
- C. ZooKeeper restricts partition ownership to one consumer per group.  
- D. Network bandwidth limitations prevent shared consumption.  
```  

<details><summary>Response:</summary>  

**Answer:** B

**Explanation:**
```markdown  
- A. Kafka uses no locks; it’s a design choice for ordering.  
- B. **Correct.** Partitions are single-threaded logs. Multiple consumers would break offset tracking and ordering guarantees.  
- C. ZooKeeper (or KRaft) tracks assignments but doesn’t enforce this rule.  
- D. Bandwidth is irrelevant to the partitioning model.  
```  
</details>  

---  

## Question 2
```markdown  
What happens to a message if all replicas of a partition (including the leader) fail before the message is fully replicated?  
```  

**Options**
```markdown  
- A. The message is lost permanently.  
- B. The producer retries indefinitely until at least one replica recovers.  
- C. The message is queued in a temporary topic until a replica is available.  
- D. Kafka uses ZooKeeper to reconstruct the message from metadata.  
```  

<details><summary>Response:</summary>  

**Answer:** A

**Explanation:**
```markdown  
- A. **Correct.** Kafka’s durability depends on replication. If no replica acknowledges the write, the message is lost.  
- B. Producers respect `retries` and `delivery.timeout.ms`; they don’t retry indefinitely.  
- C. No such temporary topic exists in Kafka’s design.  
- D. ZooKeeper/KRaft stores metadata, not message content.  
```  
</details>  

---  

## Question 3
```markdown  
How does Kafka’s log segment architecture improve performance?  
```  

**Options**
```markdown  
- A. By compressing individual messages in real-time.  
- B. By allowing sequential disk I/O and background segment deletion.  
- C. By storing all messages in an in-memory ring buffer.  
- D. By dynamically merging underutilized partitions.  
```  

<details><summary>Response:</summary>  

**Answer:** B

**Explanation:**
```markdown  
- A. Compression is optional and per-batch, not tied to segments.  
- B. **Correct.** Segments enable sequential writes and asynchronous cleanup (e.g., `log.segment.bytes`).  
- C. Kafka relies on disk persistence, not in-memory buffers.  
- D. Partitions are immutable once created.  
```  
</details>  

---  

## Question 4
```markdown  
A Kafka topic has 3 partitions with `replication.factor=2`. If one broker fails, what is the minimum number of in-sync replicas (ISR) required to avoid producer blocking when `acks=all`?  
```  

**Options**
```markdown  
- A. 1 (just the leader)  
- B. 2 (all replicas)  
- C. 1 (leader) + 1 (follower) = 2  
- D. 0 (producers continue regardless)  
```  

<details><summary>Response:</summary>  

**Answer:** A

**Explanation:**
```markdown  
- A. **Correct.** With `acks=all`, the leader counts as an ISR. If it’s alive, writes succeed even if followers lag.  
- B. `acks=all` requires only the leader’s acknowledgment by default.  
- C. Misleading—followers need not ack unless `min.insync.replicas` is set higher.  
- D. `acks=all` blocks if no ISR exists.  
```  
</details>  

---  

## Question 5
```markdown  
Which of the following is *not* stored in Kafka’s internal `__consumer_offsets` topic?  
```  

**Options**
```markdown  
- A. The last committed offset for each partition.  
- B. The consumer group’s heartbeat timestamps.  
- C. The schema of the consumed messages.  
- D. The consumer group membership metadata.  
```  

<details><summary>Response:</summary>  

**Answer:** C

**Explanation:**
```markdown  
- A. Offsets are the primary data stored.  
- B. Heartbeats track liveness and are stored here.  
- C. **Correct.** Schemas are stored in Schema Registry (if used), not offsets topic.  
- D. Group membership (e.g., `group.id`) is tracked here.  
```  
</details>  

---  

## Question 6
```markdown  
What is the impact of setting `unclean.leader.election.enable=true`?  
```  

**Options**
```markdown  
- A. It allows faster failovers but risks data loss if out-of-sync replicas become leaders.  
- B. It forces all replicas to sync before election, increasing latency.  
- C. It disables leader elections entirely, relying on manual intervention.  
- D. It enables multi-leader partitions for higher throughput.  
```  

<details><summary>Response:</summary>  

**Answer:** A

**Explanation:**
```markdown  
- A. **Correct.** This setting prioritizes availability over consistency.  
- B. Opposite behavior—this setting bypasses sync checks.  
- C. Elections still occur, but with relaxed rules.  
- D. Kafka never allows multi-leader partitions.  
```  
</details>  

---  

## Question 7
```markdown  
A producer sends a message with a key but experiences a `NotEnoughReplicasException`. What is the most likely cause?  
```  

**Options**
```markdown  
- A. The producer’s `buffer.memory` is exhausted.  
- B. The partition leader is offline, and no ISR replicas are available.  
- C. The consumer group is rebalancing.  
- D. The message key conflicts with an existing offset.  
```  

<details><summary>Response:</summary>  

**Answer:** B

**Explanation:**
```markdown  
- A. Buffer exhaustion causes `TimeoutException`, not replica errors.  
- B. **Correct.** This exception occurs when `acks=all` and insufficient ISRs exist.  
- C. Consumer rebalancing doesn’t affect producer replication.  
- D. Keys and offsets are unrelated concepts.  
```  
</details>  

---  

## Question 8
```markdown  
How does Kafka handle a partition whose leader crashes while a follower is out of sync?  
```  

**Options**
```markdown  
- A. The follower is discarded, and a new replica is created from scratch.  
- B. The out-of-sync follower rejoins the ISR after catching up.  
- C. The partition becomes unavailable until manual repair.  
- D. Kafka triggers compaction to eliminate the lag.  
```  

<details><summary>Response:</summary>  

**Answer:** B

**Explanation:**
```markdown  
- A. Kafka retains replicas and syncs them incrementally.  
- B. **Correct.** Followers fetch missing data from the new leader to rejoin ISR.  
- C. Kafka automatically recovers if `unclean.leader.election.enable=false`.  
- D. Compaction removes duplicate keys, not replication lag.  
```  
</details>  

---  

## Question 9
```markdown  
What is the purpose of the `log.segment.bytes` configuration?  
```  

**Options**
```markdown  
- A. To limit the size of individual messages.  
- B. To control the maximum size of a segment file before rolling to a new one.  
- C. To define the batch size for producer requests.  
- D. To set the retention period for topics.  
```  

<details><summary>Response:</summary>  

**Answer:** B

**Explanation:**
```markdown  
- A. `message.max.bytes` controls message size.  
- B. **Correct.** Segments roll over when this size is reached (e.g., 1GB).  
- C. Batching is controlled by `batch.size` and `linger.ms`.  
- D. Retention uses `log.retention.ms` or `bytes`.  
```  
</details>  

---  

## Question 10
```markdown  
Why might a consumer see duplicate messages even with `enable.auto.commit=true`?  
```  

**Options**
```markdown  
- A. The consumer crashed after processing but before the auto-commit interval elapsed.  
- B. The `auto.offset.reset` policy was set to `earliest`.  
- C. The producer retried messages due to timeouts.  
- D. The topic had `cleanup.policy=compact`.  
```  

<details><summary>Response:</summary>  

**Answer:** A

**Explanation:**
```markdown  
- A. **Correct.** Auto-commit is async; crashes between processing and committing cause replays.  
- B. `auto.offset.reset` affects only initial offsets, not duplicates.  
- C. Producer retries are idempotent if `enable.idempotence=true`.  
- D. Compaction removes duplicates by key but doesn’t prevent consumer-side replays.  
```  
</details>  

---  

*(Questions 11–15 below test advanced scenarios like zero-copy, KRaft metadata, and partition reassignment.)*

---  

## Question 11
```markdown  
How does Kafka’s "zero-copy" optimization improve throughput?  
```  

**Options**
```markdown  
- A. By skipping disk writes for idempotent producers.  
- B. By allowing the OS to transfer data directly from disk to network buffers.  
- C. By compressing messages before they reach the broker.  
- D. By caching all messages in the controller’s memory.  
```  

<details><summary>Response:</summary>  

**Answer:** B

**Explanation:**
```markdown  
- A. Zero-copy is unrelated to idempotence.  
- B. **Correct.** `sendfile()` bypasses user-space copies, reducing CPU overhead.  
- C. Compression happens after data is read into memory.  
- D. The controller doesn’t handle message data.  
```  
</details>  

---  

## Question 12
```markdown  
In KRaft mode, where is the current leader partition metadata stored?  
```  

**Options**
```markdown  
- A. In ZooKeeper’s `/brokers/topics` path.  
- B. In the broker’s local `meta.properties` file.  
- C. In Kafka’s internal `@metadata` topic.  
- D. In each partition’s log segment headers.  
```  

<details><summary>Response:</summary>  

**Answer:** C

**Explanation:**
```markdown  
- A. ZooKeeper is unused in KRaft mode.  
- B. `meta.properties` stores broker ID, not cluster metadata.  
- C. **Correct.** KRaft uses this topic for all metadata (replacing ZooKeeper).  
- D. Log segments store messages, not leadership info.  
```  
</details>  

---  

## Question 13
```markdown  
What is the risk of setting `min.insync.replicas=3` for a topic with `replication.factor=2`?  
```  

**Options**
```markdown  
- A. Producers will block indefinitely because the condition is impossible.  
- B. The topic will automatically increase replication to 3.  
- C. Consumers will skip messages until replication is fixed.  
- D. The controller will mark the topic as read-only.  
```  

<details><summary>Response:</summary>  

**Answer:** A

**Explanation:**
```markdown  
- A. **Correct.** With only 2 replicas, 3 can never be in sync, so `acks=all` producers block.  
- B. Kafka doesn’t auto-adjust replication factors.  
- C. Consumers aren’t directly affected by this producer-side setting.  
- D. Topics aren’t auto-marked read-only.  
```  
</details>  

---  

## Question 14
```markdown  
During partition reassignment, what happens to messages sent to the old leader?  
```  

**Options**
```markdown  
- A. They are forwarded to the new leader by the controller.  
- B. They are buffered in ZooKeeper until reassignment completes.  
- C. They are rejected with `NotLeaderForPartitionException`.  
- D. They are written to both old and new leaders for redundancy.  
```  

<details><summary>Response:</summary>  

**Answer:** C

**Explanation:**
```markdown  
- A. Leaders don’t forward writes; clients must retry.  
- B. ZooKeeper doesn’t buffer messages.  
- C. **Correct.** Producers must refresh metadata and retry.  
- D. Kafka never writes to multiple leaders.  
```  
</details>  

---  

## Question 15
```markdown  
Which feature ensures that a consumer reading from a compacted topic sees the latest value for each key?  
```  

**Options**
```markdown  
- A. Consumer-side caching of key-value pairs.  
- B. The `isolation.level=read_committed` setting.  
- C. The `max.poll.interval.ms` configuration.  
- D. The `offsets.retention.minutes` policy.  
```  

<details><summary>Response:</summary>  

**Answer:** B

**Explanation:**
```markdown  
- A. Kafka doesn’t cache data on consumers.  
- B. **Correct.** `read_committed` ignores aborted transactions in compacted topics.  
- C. This setting handles consumer liveness, not compaction.  
- D. This governs offset retention, not message visibility.  
```  
</details>  

---  

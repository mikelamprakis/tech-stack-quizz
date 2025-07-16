## Question 1
```markdown  
During a controller failover in KRaft mode, what ensures that partition leadership assignments are not lost?  
```  

**Options**
```markdown  
- A. ZooKeeper's persistent ZNodes  
- B. The `@metadata` topic's replicated log  
- C. Brokers caching the last known controller state  
- D. Manual intervention to reconfigure leaders  
```  

<details><summary>Response:</summary>  

**Answer:** B

**Explanation:**
```markdown  
- A. ZooKeeper is not used in KRaft mode.  
- B. **Correct.** The `@metadata` topic durably stores all controller decisions via Raft consensus.  
- C. Brokers rely on the metadata log, not caches, for consistency.  
- D. Failover is automatic in KRaft.  
```  
</details>  

---

## Question 2
```markdown  
What happens if a partition's leader crashes and no ISR replicas are available, with `unclean.leader.election.enable=false`?  
```  

**Options**
```markdown  
- A. The partition becomes read-only until a replica rejoins the ISR.  
- B. Kafka automatically reduces `min.insync.replicas` to allow progress.  
- C. Producers receive `NotEnoughReplicasException` for writes.  
- D. The controller forces a non-ISR replica to become leader.  
```  

<details><summary>Response:</summary>  

**Answer:** C

**Explanation:**
```markdown  
- A. Partitions are never read-only; they become unavailable for writes.  
- B. `min.insync.replicas` cannot be dynamically adjusted.  
- C. **Correct.** Writes fail to ensure consistency when no ISR exists.  
- D. Only allowed if `unclean.leader.election.enable=true`.  
```  
</details>  

---

## Question 3
```markdown  
How does the controller detect a broker failure in KRaft mode?  
```  

**Options**
```markdown  
- A. Via ZooKeeper's ephemeral node expiration.  
- B. By monitoring broker heartbeats to the metadata log.  
- C. Through direct TCP health checks between brokers.  
- D. Using external monitoring tools like Prometheus.  
```  

<details><summary>Response:</summary>  

**Answer:** B

**Explanation:**
```markdown  
- A. ZooKeeper is not used in KRaft.  
- B. **Correct.** Brokers send heartbeats to the metadata log; timeouts trigger failure detection.  
- C. Brokers do not probe each other directly.  
- D. External tools are for observability, not failure detection.  
```  
</details>  

---

## Question 4
```markdown  
What is the purpose of the `controller.quorum.voters` configuration in KRaft?  
```  

**Options**
```markdown  
- A. To list all brokers eligible to store topic data.  
- B. To define which nodes participate in metadata consensus.  
- C. To assign partitions to specific racks for availability.  
- D. To configure the number of ISR replicas per topic.  
```  

<details><summary>Response:</summary>  

**Answer:** B

**Explanation:**
```markdown  
- A. Voters are for metadata, not data storage.  
- B. **Correct.** This config identifies nodes in the Raft quorum (e.g., `1@broker1:9093`).  
- C. Rack awareness is configured via `broker.rack`.  
- D. ISR size is controlled by `replication.factor` and broker health.  
```  
</details>  

---

## Question 5
```markdown  
During a partition reassignment, what happens to messages produced to the old leader?  
```  

**Options**
```markdown  
- A. They are forwarded to the new leader by the controller.  
- B. They are buffered in ZooKeeper until reassignment completes.  
- C. Producers receive `NotLeaderForPartitionException` and must retry.  
- D. The messages are written to both old and new leaders for redundancy.  
```  

<details><summary>Response:</summary>  

**Answer:** C

**Explanation:**
```markdown  
- A. Leaders do not forward writes; clients must refresh metadata.  
- B. ZooKeeper does not buffer messages.  
- C. **Correct.** Producers must handle this exception and retry.  
- D. Kafka never writes to multiple leaders.  
```  
</details>  

---

## Question 6
```markdown  
Why is the ISR critical for leader election?  
```  

**Options**
```markdown  
- A. It ensures only replicas with the latest data can become leaders.  
- B. It allows Kafka to bypass consensus for faster failovers.  
- C. It enables multi-leader replication for high availability.  
- D. It guarantees exactly-once semantics for producers.  
```  

<details><summary>Response:</summary>  

**Answer:** A

**Explanation:**
```markdown  
- A. **Correct.** ISR replicas are guaranteed to have all committed messages.  
- B. Consensus (Raft/ZooKeeper) is still required for election.  
- C. Kafka does not support multi-leader partitions.  
- D. Exactly-once relies on transactions, not ISR.  
```  
</details>  

---

## Question 7
```markdown  
What occurs when a broker rejoins the cluster after a failure and its partitions were reassigned?  
```  

**Options**
```markdown  
- A. It automatically reclaims leadership for all its previous partitions.  
- B. It syncs with the current leaders and becomes a follower.  
- C. Its partitions are deleted to avoid conflicts.  
- D. The controller triggers a full cluster rebalance.  
```  

<details><summary>Response:</summary>  

**Answer:** B

**Explanation:**
```markdown  
- A. Leadership is reassigned; it does not revert automatically.  
- B. **Correct.** The broker catches up from new leaders.  
- C. Partitions are retained unless manually deleted.  
- D. Rebalancing is partition-specific, not cluster-wide.  
```  
</details>  

---

## Question 8
```markdown  
How does the controller enforce `min.insync.replicas` during a broker outage?  
```  

**Options**
```markdown  
- A. It blocks all writes until enough replicas recover.  
- B. It dynamically reduces the setting to maintain availability.  
- C. It temporarily pauses consumers to reduce load.  
- D. It forces unclean leader elections to bypass the requirement.  
```  

<details><summary>Response:</summary>  

**Answer:** A

**Explanation:**
```markdown  
- A. **Correct.** Writes fail if `acks=all` and ISR size < `min.insync.replicas`.  
- B. The setting is immutable at runtime.  
- C. Consumers are unaffected by this producer-side setting.  
- D. Only occurs if `unclean.leader.election.enable=true`.  
```  
</details>  

---

## Question 9
```markdown  
What is the risk of setting `auto.leader.rebalance.enable=true` in a large cluster?  
```  

**Options**
```markdown  
- A. It may cause frequent leader shuffling, impacting throughput.  
- B. It disables replication for underutilized partitions.  
- C. It allows ZooKeeper to take over metadata management.  
- D. It merges partitions to reduce broker load.  
```  

<details><summary>Response:</summary>  

**Answer:** A

**Explanation:**
```markdown  
- A. **Correct.** Frequent rebalancing can add overhead.  
- B. Replication is unaffected by leader rebalancing.  
- C. ZooKeeper is not involved in this process.  
- D. Partitions cannot be merged dynamically.  
```  
</details>  

---

## Question 10
```markdown  
Which metric indicates controller failover latency in KRaft mode?  
```  

**Options**
```markdown  
- A. `kafka.controller:type=KafkaController,name=ActiveControllerCount`  
- B. `kafka.controller:type=KafkaController,name=LastCommittedRecordOffset`  
- C. `kafka.controller:type=KafkaController,name=EventQueueTimeMs`  
- D. `kafka.controller:type=KafkaController,name=MetadataSnapshotLoadTimeMs`  
```  

<details><summary>Response:</summary>  

**Answer:** D

**Explanation:**
```markdown  
- A. Tracks active controllers (always 1), not failover time.  
- B. Measures log progress, not latency.  
- C. Reflects event processing delay, not failover.  
- D. **Correct.** Time to load metadata snapshots impacts failover speed.  
```  
</details>  

---

## Question 11
```markdown  
What prevents split-brain scenarios during controller failover in KRaft?  
```  

**Options**
```markdown  
- A. ZooKeeper's distributed lock mechanism.  
- B. Raft's leader election algorithm ensures only one active controller.  
- C. Brokers vote on the new controller via gossip.  
- D. Manual administrator intervention is required.  
```  

<details><summary>Response:</summary>  

**Answer:** B

**Explanation:**
```markdown  
- A. ZooKeeper is not used in KRaft.  
- B. **Correct.** Raft guarantees a single leader at all times.  
- C. Brokers do not participate in controller election.  
- D. Failover is automatic.  
```  
</details>  

---

## Question 12
```markdown  
How does the controller handle a partition with all replicas out of sync?  
```  

**Options**
```markdown  
- A. It marks the partition as corrupt and deletes it.  
- B. It triggers `min.insync.replicas` auto-adjustment.  
- C. It allows producers to write if `unclean.leader.election.enable=true`.  
- D. It designates the oldest replica as leader to preserve data.  
```  

<details><summary>Response:</summary>  

**Answer:** C

**Explanation:**
```markdown  
- A. Kafka never auto-deletes partitions.  
- B. `min.insync.replicas` is immutable.  
- C. **Correct.** This risks data loss but maintains availability.  
- D. Leadership is not based on replica age.  
```  
</details>  

---

## Question 13
```markdown  
What is the role of the `LeaderEpoch` in partition leadership changes?  
```  

**Options**
```markdown  
- A. To track the number of leaders a partition has had.  
- B. To prevent message duplication during leader failover.  
- C. To enforce SSL authentication between leaders and followers.  
- D. To allocate CPU resources for leader replicas.  
```  

<details><summary>Response:</summary>  

**Answer:** B

**Explanation:**
```markdown  
- A. It’s not a counter; it’s a consistency mechanism.  
- B. **Correct.** `LeaderEpoch` ensures followers truncate divergent logs before syncing.  
- C. Unrelated to security.  
- D. Resource allocation is managed by the OS.  
```  
</details>  

---

## Question 14
```markdown  
Which configuration reduces partition unavailability during controller failover?  
```  

**Options**
```markdown  
- A. `controlled.shutdown.enable=true`  
- B. `controller.quorum.election.timeout.ms=10000`  
- C. `num.partitions=3`  
- D. `log.flush.interval.messages=1`  
```  

<details><summary>Response:</summary>  

**Answer:** B

**Explanation:**
```markdown  
- A. Graceful shutdown doesn’t affect failover speed.  
- B. **Correct.** Lower election timeouts speed up controller failover.  
- C. Partition count doesn’t impact controller recovery.  
- D. Flush settings affect durability, not availability.  
```  
</details>  

---

## Question 15
```markdown  
What happens if the controller and ZooKeeper disagree on leader assignments in hybrid mode (during migration)?  
```  

**Options**
```markdown  
- A. Brokers prioritize ZooKeeper's state for backward compatibility.  
- B. The cluster shuts down to avoid split-brain.  
- C. KRaft overrides ZooKeeper's metadata permanently.  
- D. Producers switch to `acks=1` to bypass the conflict.  
```  

<details><summary>Response:</summary>  

**Answer:** B

**Explanation:**
```markdown  
- A. Hybrid mode requires consistency; disagreement is fatal.  
- B. **Correct.** Kafka prevents split-brain by halting.  
- C. No automatic override exists; manual intervention is needed.  
- D. `acks` settings don’t resolve metadata conflicts.  
```  
</details>  

---
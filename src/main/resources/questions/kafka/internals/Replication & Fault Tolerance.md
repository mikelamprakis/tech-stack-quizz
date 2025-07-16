## Question 1
```markdown  
What is the minimum number of in-sync replicas (ISR) required to maintain write availability when `acks=all` and `min.insync.replicas=2` for a topic with `replication.factor=3`?  
```  

**Options**
```markdown  
- A. 1 (only the leader)  
- B. 2 (leader + 1 follower)  
- C. 3 (all replicas)  
- D. Write availability is impossible with these settings  
```  

<details><summary>Response:</summary>  

**Answer:** B

**Explanation:**
```markdown  
- A. `min.insync.replicas=2` requires at least 2 replicas (leader + 1 follower).  
- B. **Correct.** The leader counts toward ISR, so 1 follower must also be in sync.  
- C. All replicas need not be in sync for writes to succeed.  
- D. Availability is maintained as long as ISR ≥ `min.insync.replicas`.  
```  
</details>  

---

## Question 2
```markdown  
How does Kafka handle replication when a follower replica falls out of the ISR due to slow disk I/O?  
```  

**Options**
```markdown  
- A. The follower is permanently removed from the replica set.  
- B. The follower continues to replicate but is excluded from leader election until it catches up.  
- C. The controller triggers a partition reassignment to replace the slow follower.  
- D. Producers begin routing writes directly to the lagging follower to speed up recovery.  
```  

<details><summary>Response:</summary>  

**Answer:** B

**Explanation:**
```markdown  
- A. Replicas are only removed if the broker fails permanently.  
- B. **Correct.** Followers outside ISR continue fetching data but cannot become leaders.  
- C. Reassignment is manual; Kafka does not auto-replace replicas.  
- D. Producers only write to the leader.  
```  
</details>  

---

## Question 3
```markdown  
What happens if a broker hosting a partition leader fails and `unclean.leader.election.enable=false` while no ISR replicas are available?  
```  

**Options**
```markdown  
- A. The partition becomes read-only until an ISR replica recovers.  
- B. Producers receive `NotEnoughReplicasException` for writes.  
- C. Kafka automatically reduces `min.insync.replicas` to 1.  
- D. The oldest out-of-sync replica is promoted to leader.  
```  

<details><summary>Response:</summary>  

**Answer:** B

**Explanation:**
```markdown  
- A. Partitions are never read-only; they become unavailable for writes.  
- B. **Correct.** Writes fail to ensure consistency when no ISR exists.  
- C. `min.insync.replicas` cannot be dynamically adjusted.  
- D. Only allowed if `unclean.leader.election.enable=true`.  
```  
</details>  

---

## Question 4
```markdown  
Why does Kafka use a pull-based replication model (followers fetch from leaders) instead of push-based?  
```  

**Options**
```markdown  
- A. To allow followers to control their own fetch rates and avoid overwhelming slow brokers.  
- B. Because ZooKeeper cannot support push-based replication.  
- C. To ensure messages are compressed before replication.  
- D. Because TCP push semantics are incompatible with Kafka's protocol.  
```  

<details><summary>Response:</summary>  

**Answer:** A

**Explanation:**
```markdown  
- A. **Correct.** Pull-based replication lets followers manage their load and catch up at their own pace.  
- B. ZooKeeper is unrelated to replication mechanics.  
- C. Compression happens before data reaches the leader, not during replication.  
- D. Kafka's protocol is agnostic to TCP push/pull.  
```  
</details>  

---

## Question 5
```markdown  
What is the purpose of the `replica.lag.time.max.ms` configuration?  
```  

**Options**
```markdown  
- A. To set the maximum time a follower can lag before being removed from ISR.  
- B. To control how often followers ping the controller.  
- C. To limit the duration of unclean leader elections.  
- D. To define the timeout for producer acknowledgments.  
```  

<details><summary>Response:</summary>  

**Answer:** A

**Explanation:**
```markdown  
- A. **Correct.** Followers exceeding this lag are excluded from ISR.  
- B. Heartbeats are controlled by `zookeeper.session.timeout.ms` (ZooKeeper) or KRaft quorum settings.  
- C. Unclean elections are binary (`enable/disable`), not time-based.  
- D. Producer timeouts use `delivery.timeout.ms`.  
```  
</details>  

---

## Question 6
```markdown  
How does Kafka ensure durability when `acks=all` and one ISR replica crashes mid-write?  
```  

**Options**
```markdown  
- A. The write fails, and producers must retry.  
- B. The leader stores the message in a temporary buffer until the replica recovers.  
- C. The write succeeds once the remaining ISR replicas acknowledge it.  
- D. ZooKeeper temporarily stores the message until replication completes.  
```  

<details><summary>Response:</summary>  

**Answer:** C

**Explanation:**
```markdown  
- A. Writes succeed as long as `min.insync.replicas` is met.  
- B. Kafka has no temporary buffers for unacknowledged writes.  
- C. **Correct.** `acks=all` only requires acknowledgment from current ISR members.  
- D. ZooKeeper is not involved in message replication.  
```  
</details>  

---

## Question 7
```markdown  
What metric would indicate a potential data loss risk due to replication issues?  
```  

**Options**
```markdown  
- A. `kafka.server:type=ReplicaManager,name=UnderReplicatedPartitions` > 0  
- B. `kafka.network:type=RequestMetrics,name=TotalTimeMs` spikes  
- C. `kafka.controller:type=KafkaController,name=OfflinePartitionsCount`  
- D. `kafka.log:type=LogFlushStats,name=FlushTimeMs` increases  
```  

<details><summary>Response:</summary>  

**Answer:** A

**Explanation:**
```markdown  
- A. **Correct.** Under-replicated partitions mean some replicas are missing data.  
- B. Measures network latency, not replication health.  
- C. Indicates partition unavailability, not necessarily data loss.  
- D. Reflects disk write performance, not replication.  
```  
</details>  

---

## Question 8
```markdown  
What happens to messages produced with `acks=1` if the leader crashes immediately after acknowledging them but before replicating to followers?  
```  

**Options**
```markdown  
- A. The messages are lost permanently.  
- B. The messages are recovered from the producer's buffer upon leader recovery.  
- C. The messages are reconstructed from ZooKeeper metadata.  
- D. Followers automatically fetch the messages from producers.  
```  

<details><summary>Response:</summary>  

**Answer:** A

**Explanation:**
```markdown  
- A. **Correct.** `acks=1` only guarantees leader persistence; unreplicated messages are lost if the leader crashes.  
- B. Producer buffers are cleared after acknowledgment.  
- C. ZooKeeper does not store message content.  
- D. Followers only fetch from leaders, not producers.  
```  
</details>  

---

## Question 9
```markdown  
How does `min.insync.replicas=2` interact with `unclean.leader.election.enable=true`?  
```  

**Options**
```markdown  
- A. It prevents unclean elections by requiring at least 2 ISR replicas.  
- B. It allows unclean elections but only if at least 2 out-of-sync replicas exist.  
- C. It has no effect on unclean leader election behavior.  
- D. It forces the controller to wait for 2 replicas to sync before allowing elections.  
```  

<details><summary>Response:</summary>  

**Answer:** C

**Explanation:**
```markdown  
- A. `unclean.leader.election.enable=true` overrides this safety check.  
- B. Unclean elections consider any out-of-sync replica, regardless of count.  
- C. **Correct.** The settings are independent; `min.insync.replicas` only affects producers.  
- D. The controller does not wait for syncs if unclean elections are enabled.  
```  
</details>  

---

## Question 10
```markdown  
What is the purpose of the `LeaderEpoch` in Kafka's replication protocol?  
```  

**Options**
```markdown  
- A. To ensure followers truncate divergent logs before syncing with the leader.  
- B. To encrypt messages during replication.  
- C. To allocate CPU resources for replication threads.  
- D. To track the total number of leaders a partition has had.  
```  

<details><summary>Response:</summary>  

**Answer:** A

**Explanation:**
```markdown  
- A. **Correct.** `LeaderEpoch` prevents data divergence during failover.  
- B. Encryption is handled separately (e.g., SSL/TLS).  
- C. Resource allocation is managed by the OS.  
- D. It’s a consistency mechanism, not a counter.  
```  
</details>  

---

## Question 11
```markdown  
How does Kafka handle a scenario where all replicas of a partition fall out of the ISR?  
```  

**Options**
```markdown  
- A. The partition is automatically deleted to preserve cluster health.  
- B. The controller designates the most caught-up replica as leader, even if lagging.  
- C. Writes fail until at least one replica rejoins the ISR.  
- D. Producers switch to `acks=0` mode temporarily.  
```  

<details><summary>Response:</summary>  

**Answer:** C

**Explanation:**
```markdown  
- A. Kafka never auto-deletes partitions.  
- B. Only allowed if `unclean.leader.election.enable=true`.  
- C. **Correct.** Kafka prioritizes consistency over availability in this case.  
- D. Producers cannot dynamically switch acknowledgment modes.  
```  
</details>  

---

## Question 12
```markdown  
What is the risk of setting `replica.lag.time.max.ms` too low?  
```  

**Options**
```markdown  
- A. Followers may be unnecessarily removed from ISR due to transient network delays.  
- B. Producers will timeout before replicas acknowledge writes.  
- C. The controller will trigger frequent partition reassignments.  
- D. ZooKeeper sessions will expire prematurely.  
```  

<details><summary>Response:</summary>  

**Answer:** A

**Explanation:**
```markdown  
- A. **Correct.** Aggressive settings can cause ISR thrashing.  
- B. Producer timeouts are configured separately.  
- C. Reassignments are manual unless `auto.leader.rebalance.enable=true`.  
- D. ZooKeeper sessions use `session.timeout.ms`.  
```  
</details>  

---

## Question 13
```markdown  
Why might a replica remain out of the ISR even after catching up to the leader's log?  
```  

**Options**
```markdown  
- A. The controller has not yet processed the follower's heartbeat.  
- B. The replica must wait for `replica.lag.time.max.ms` to expire before rejoining.  
- C. The follower's disk is too slow to meet the ISR throughput requirements.  
- D. ZooKeeper has not updated the ISR metadata.  
```  

<details><summary>Response:</summary>  

**Answer:** B

**Explanation:**
```markdown  
- A. Controller updates are near real-time in KRaft mode.  
- B. **Correct.** Followers must sustain sync for the full duration to rejoin ISR.  
- C. Throughput is not an ISR criterion; only offset lag matters.  
- D. ZooKeeper is not involved in ISR tracking in KRaft mode.  
```  
</details>  

---

## Question 14
```markdown  
How does `acks=all` provide stronger durability than `acks=1`?  
```  

**Options**
```markdown  
- A. It waits for all replicas in the cluster to acknowledge writes.  
- B. It requires acknowledgment from all ISR replicas, not just the leader.  
- C. It synchronously flushes messages to disk on every broker.  
- D. It verifies checksums across all replicas before acknowledging.  
```  

<details><summary>Response:</summary>  

**Answer:** B

**Explanation:**
```markdown  
- A. Only ISR replicas (not all cluster replicas) must acknowledge.  
- B. **Correct.** `acks=all` ensures writes survive single-broker failures.  
- C. Disk flushes are controlled by `log.flush.*` settings.  
- D. Checksums are validated during fetches, not acknowledgments.  
```  
</details>  

---

## Question 15
```markdown  
What occurs when a follower replica rejoins the ISR after a failure?  
```  

**Options**
```markdown  
- A. It truncates its log to match the leader's `LeaderEpoch` and begins fetching new messages.  
- B. It copies the entire partition log from the leader before resuming replication.  
- C. It becomes a leader immediately to reduce load on the current leader.  
- D. It triggers a full cluster rebalance to redistribute partitions.  
```  

<details><summary>Response:</summary>  

**Answer:** A

**Explanation:**
```markdown  
- A. **Correct.** `LeaderEpoch` ensures consistency before resuming replication.  
- B. Only divergent segments are fetched, not the entire log.  
- C. Leadership only changes via controller election.  
- D. Rebalancing is partition-specific, not cluster-wide.  
```  
</details>  

---

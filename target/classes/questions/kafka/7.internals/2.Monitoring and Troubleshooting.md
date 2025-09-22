## Question 1

```markdown
In KRaft mode, what happens when a broker is removed from the `controller.quorum.voters` configuration?
```

**Options**

```markdown
- A. The removed broker is immediately disconnected from the cluster
- B. The removed broker continues to operate as a non-voter observer
- C. The removed broker becomes a candidate and triggers a new controller election
- D. The removed broker enters a controlled shutdown process
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Removing a broker from `controller.quorum.voters` makes it a non-voter observer but it remains connected and operational.

- A. Incorrect — Broker is not immediately disconnected.
- B. Correct — Broker continues as observer without voting rights.
- C. Incorrect — Broker does not trigger elections or become a candidate.
- D. Incorrect — Broker does not shut down, continues serving clients.
```

</details>

---

## Question 2
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

## Question 3
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

## Question 4

```markdown
What is the significance of the `kafka.controller:type=QuorumController,name=LastAppliedRecordTimestamp` metric in KRaft mode?
```

**Options**

```markdown
- A. It indicates the timestamp of the last record appended to the metadata log
- B. It represents the timestamp of the last record replicated to all the controllers
- C. It measures the timestamp of the last record applied by the active controller
- D. It tracks the timestamp of the last record committed by the active controller
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
In KRaft mode, the `LastAppliedRecordTimestamp` metric measures the timestamp of the last record applied by the active controller.

- A is incorrect because it refers to the last appended record, not applied.
- B is incorrect because it does not measure replication to all controllers.
- C is correct as it tracks the last record applied by the active controller.
- D is incorrect because it does not track committed records, but applied records.
```

</details>

---

## Question 5
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

## Question 6

```markdown
Which of the following metrics is used to monitor the lag between the active KRaft controller and the last committed record in the metadata log?
```

**Options**

```markdown
- A. `kafka.controller:type=KafkaController,name=ActiveControllerCount`
- B. `kafka.controller:type=ControllerEventManager,name=EventQueueTimeMs`
- C. `kafka.controller:type=KafkaController,name=LastCommittedRecordOffset`
- D. `kafka.controller:type=KafkaController,name=LastAppliedRecordLagMs`
```

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**

```markdown
Measures lag from last commit to last applied record.

- A. Incorrect — tracks number of active controllers
- B. Incorrect — measures event queue delay
- C. Incorrect — tracks offsets, not lag
- D. Correct — lag between commit and apply
```

</details>

----

## Question 7

```markdown
What is the purpose of the `kafka.controller:type=ControllerChannelManager,name=TotalQueueSize` metric in KRaft mode?
```

**Options**

```markdown
- A. It measures the total number of messages in the controller's request queue
- B. It indicates the total size of the metadata log in bytes
- C. It represents the total number of active controller connections
- D. It tracks the total number of pending controller requests
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
The `TotalQueueSize` metric measures the total number of messages currently in the controller's request queue.

- A is correct because it tracks the number of queued requests.
- B is incorrect as it doesn't measure log size in bytes.
- C is incorrect; it is not about active connections.
- D is incorrect because it specifically tracks the queue size, not total pending requests across all channels.
```

</details>

---

## Question 8

```markdown
What is the purpose of the `kafka.controller:type=QuorumController,name=ActiveControllerCount` metric in KRaft mode?
```

**Options**

```markdown
- A. It indicates the number of active controllers in the KRaft cluster
- B. It represents the number of brokers currently serving as controllers
- C. It measures the count of controllers that have been active since the cluster started
- D. It tracks the number of controller failover events that have occurred
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
The metric shows how many active controllers currently exist in the cluster, ideally 1.

- A. Correct — Number of active controllers at a given time.
- B. Incorrect — Counts active controllers, not brokers serving as controllers.
- C. Incorrect — Not cumulative, only current count.
- D. Incorrect — Does not track failover event counts.
```

</details>

## Question 9

```markdown
What is the role of the `kafka.controller:type=QuorumController,name=MaxFollowerLag` metric in KRaft mode?
```

**Options**

```markdown
- A. It measures the maximum lag between the active controller and the last committed record in the metadata partition
- B. It indicates the maximum lag between the active controller and the followers in terms of metadata records
- C. It represents the maximum number of records that a follower can lag behind the active controller
- D. It tracks the maximum lag between the followers and the last applied record in the metadata partition
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
This metric shows how far followers are lagging behind the active controller.

- A. Incorrect. That’s not the lag this metric tracks.
- B. Correct. Measures lag in terms of metadata replication.
- C. Incorrect. Not a limit; it measures actual lag.
- D. Incorrect. Refers to applied records, not relevant here.
```

</details>

---

## Question 10

```markdown
What is the purpose of the `kafka.controller:type=KafkaController,name=OfflinePartitionCount` metric in KRaft mode?
```

**Options**

```markdown
- A. To track the number of partitions without an active leader
- B. To monitor the number of partitions that are under-replicated
- C. To measure the number of partitions that are not being consumed by any consumer
- D. To count the number of partitions that have exceeded their retention time
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
Shows number of partitions without leaders.

- A. Correct — detects partition unavailability
- B. Incorrect — under-replication is a different metric
- C. Incorrect — consumers aren’t tracked here
- D. Incorrect — retention time is not related
```

</details>

## Question 11

```markdown
What is the purpose of the `kafka.controller:type=KafkaController,name=ControlledShutdownCount` metric in KRaft mode?
```

**Options**

```markdown
- A. It measures the number of controlled shutdown requests received by the controller
- B. It indicates the number of brokers that have completed a controlled shutdown
- C. It represents the number of brokers that are currently in the process of controlled shutdown
- D. It tracks the number of controlled shutdown failures experienced by the controller
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
This metric shows how many brokers completed a graceful (controlled) shutdown.

- A. Incorrect. Not about requests received.
- B. Correct. Tracks successful shutdowns.
- C. Incorrect. Doesn’t count ongoing shutdowns.
- D. Incorrect. Doesn’t measure failures.
```

</details>

---

## Question 12

```markdown
What is the significance of the `kafka.server:type=SnapshotEmitter,name=LatestSnapshotGeneratedAgeMs` metric in KRaft mode?
```

**Options**

```markdown
- A. It indicates the age of the latest snapshot in milliseconds since the snapshot was generated
- B. It measures the time taken to generate the latest snapshot in milliseconds
- C. It represents the age of the latest snapshot in milliseconds since the process was started
- D. It tracks the time elapsed since the latest snapshot was loaded in milliseconds
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
Shows how old the most recent snapshot is, which helps evaluate snapshot freshness.

- A. Correct. Measures time since snapshot creation.
- B. Incorrect. Doesn’t measure generation duration.
- C. Incorrect. Not tied to process start.
- D. Incorrect. Not about snapshot loading.
```

</details>

---

## Question 13

```markdown
What is the purpose of the `kafka.controller:type=SnapshotEngine,name=SnapshotGenerationTimeoutCount` metric in KRaft mode?
```

**Options**

```markdown
- A. It measures the number of snapshots that were generated successfully within the configured timeout
- B. It indicates the count of snapshots that failed to generate due to a timeout
- C. It represents the number of snapshots that are currently being generated
- D. It tracks the count of snapshots that were generated after exceeding the configured timeout
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
The metric counts the snapshots that failed to generate because they exceeded the configured timeout.

- A. Incorrect — It does not count successful snapshots.
- B. Correct — Tracks snapshots failed due to timeout.
- C. Incorrect — It does not track snapshots currently being generated.
- D. Incorrect — It counts failures, not snapshots generated after timeout.
```

</details>

---

## Question 14

```markdown
What is the significance of the `kafka.controller:type=QuorumController,name=LastCommittedRecordOffset` metric in KRaft mode?
```

**Options**

```markdown
- A. It indicates the offset of the last record that was applied by the controller to the metadata partition
- B. It represents the number of records that have been committed to the metadata partition
- C. It measures the lag between the active controller and the last committed record in the metadata partition
- D. It tracks the offset of the last record that was committed by the active controller
```

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**

```markdown
This metric tracks the offset of the last record committed by the active controller.

- A. Incorrect. That’s `LastAppliedRecordOffset`.
- B. Incorrect. It tracks offset, not count.
- C. Incorrect. Lag is tracked by another metric.
- D. Correct. Shows progress of metadata commits by controller.
```

</details>

---

## Question 15

```markdown
What is the purpose of the `kafka.controller:type=QuorumController,name=LastCommittedRecordOffset` metric in KRaft mode?
```

**Options**

```markdown
- A. It indicates the offset of the last record appended to the metadata log
- B. It represents the offset of the last record replicated to all the controllers
- C. It measures the offset of the last record applied by the active controller
- D. It tracks the offset of the last record committed by the active controller
```

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**

```markdown
The `LastCommittedRecordOffset` metric tracks the offset of the last record committed by the active controller.

- A is incorrect as it does not track appended records.
- B is incorrect; it doesn't track replication to all controllers.
- C is incorrect because it measures committed, not applied records.
- D is correct since it reflects the offset of the last committed record.
```

</details>

---

## Question 16

```markdown
What is the significance of the `kafka.controller:type=KafkaController,name=GlobalTopicCount` metric in KRaft mode?
```

**Options**

```markdown
- A. It represents the total number of topics in the Kafka cluster
- B. It indicates the number of global topics that are not associated with any specific cluster
- C. It measures the number of topics that have global replication enabled
- D. It tracks the count of topics that are globally accessible across all Kafka clusters
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
This metric gives the total topic count, useful for monitoring topic growth and planning.

- A. Correct. Counts all topics in the cluster.
- B. Incorrect. There are no such global topics in Kafka.
- C. Incorrect. Not related to replication.
- D. Incorrect. No cross-cluster global accessibility metric.
```

</details>

## Question 17
```markdown  
Which metric would indicate ZooKeeper is a bottleneck in a legacy Kafka cluster?  
```  

**Options**
```markdown  
- A. High `kafka.controller:type=KafkaController,name=OfflinePartitionsCount`.  
- B. Elevated `zookeeper:type=Leader, name=RequestLatencyMs`.  
- C. Spikes in `kafka.server:type=BrokerTopicMetrics,name=BytesOutPerSec`.  
- D. Increased `kafka.network:type=RequestMetrics,name=TotalTimeMs`.  
```  

<details><summary>Response:</summary>  

**Answer:** B

**Explanation:**
```markdown  
- A. Indicates partition issues, not ZooKeeper latency.  
- B. **Correct.** High ZooKeeper request latency directly impacts metadata operations.  
- C. Measures producer/consumer throughput, not ZooKeeper.  
- D. Reflects general broker load, not ZooKeeper.  
```  
</details>  

---  
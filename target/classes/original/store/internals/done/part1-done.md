## Question 1

```markdown
A Zookeeper ensemble contains 5 servers. What is the maximum number of servers that can go missing and the ensemble still run?
```

**Options**

```markdown
- A. 2
- B. 4
- C. 1
- D. 3
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
Zookeeper requires a majority (quorum) of servers to function.

- A. ✅ 2 servers can fail in a 5-server cluster, leaving 3 (majority) running.
- B. ❌ 4 failed servers means only 1 remains – not enough for quorum.
- C. ❌ 1 failure means 4 remain, but we’re asked for the *maximum* tolerated.
- D. ❌ 3 failures leave 2 – still not a majority in a 5-node cluster.
```

</details>

---

## Question 2

```markdown
You have a Zookeeper cluster that needs to be able to withstand the loss of 2 servers and still be able to function. What size should your Zookeeper cluster have?
```

**Options**

```markdown
- A. 5
- B. 3
- C. 4
- D. 6
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
To tolerate N failures, Zookeeper needs 2N+1 nodes.

- A. ✅ 5 nodes can tolerate 2 failures (2×2+1 = 5).
- B. ❌ 3 nodes can only tolerate 1 failure.
- C. ❌ 4 is even-numbered – Zookeeper clusters must have odd numbers to reach majority.
- D. ❌ 6 nodes still can’t survive 3 failures since quorum is ⌈N/2⌉.
```

</details>


## Question 1

```markdown
What is the purpose of the `process.roles` configuration in KRaft mode?
```

**Options**

```markdown
- A. To specify whether the server acts as a controller, broker, or both
- B. To set the unique identifier for the server
- C. To define the listeners used by the controller
- D. To configure the metrics reporter for the server
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
In KRaft mode, `process.roles` determines whether the server acts as a controller, broker, or both.

- A. Correct — defines the server role(s)
- B. Incorrect — server ID is set using `node.id`
- C. Incorrect — listeners are set via `controller.listener.names`
- D. Incorrect — metrics are configured separately
```

</details>

---

## Question 2

```markdown
What is the recommended value for `process.roles` in a production KRaft cluster?
```

**Options**

```markdown
- A. `broker,controller`
- B. `broker` for broker nodes and `controller` for controller nodes
- C. `controller` for all nodes
- D. Leave `process.roles` unconfigured
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Best practice is to separate broker and controller roles.

- A. Incorrect — combined mode isn't production-ready
- B. Correct — separates roles for scalability/fault tolerance
- C. Incorrect — no brokers means no data handling
- D. Incorrect — required in KRaft mode
```

</details>

---

## Question 3

```markdown
What is the purpose of the `controller.quorum.voters` configuration in KRaft mode?
```

**Options**

```markdown
- A. To specify the listeners used by the controllers
- B. To set the minimum number of in-sync replicas for the controller quorum
- C. To define the list of voters in the controller quorum
- D. To configure the metrics reporter for the controllers
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
This config lists controller nodes that vote in quorum decisions.

- A. Incorrect — that's `controller.listener.names`
- B. Incorrect — in-sync replicas don't apply to controllers
- C. Correct — defines quorum voters
- D. Incorrect — unrelated to metrics
```

</details>

---

## Question 4

```markdown
What is the minimum number of controllers required for a KRaft cluster?
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
A quorum system requires an odd number for fault tolerance.

- A. Incorrect — no fault tolerance
- B. Incorrect — 2-node quorum isn't stable
- C. Correct — 3 is minimum for reliable quorum
- D. Incorrect — valid but not minimum
```

</details>

---

## Question 5

```markdown
What happens if a majority of the controllers in a KRaft cluster become unavailable?
```

**Options**

```markdown
- A. The cluster remains operational with reduced performance
- B. The cluster automatically elects a new set of controllers
- C. The cluster becomes unavailable until a majority of controllers are restored
- D. The brokers take over the responsibilities of the controllers
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Quorum can't function without majority — cluster halts.

- A. Incorrect — system halts, not just slow
- B. Incorrect — quorum isn’t dynamically rebuilt
- C. Correct — requires majority to recover
- D. Incorrect — roles are not interchangeable
```

</details>

---

## Question 6

```markdown
What is the purpose of the `kafka-storage` tool in KRaft mode?
```

**Options**

```markdown
- A. To configure the storage directories for Kafka brokers
- B. To manage the Kafka consumer offsets
- C. To generate a cluster ID and format storage directories
- D. To monitor the disk usage of Kafka brokers
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Used before starting Kafka to format directories with a cluster ID.

- A. Incorrect — it formats, not configures directories
- B. Incorrect — unrelated to offsets
- C. Correct — for generating ID + formatting
- D. Incorrect — not for monitoring
```

</details>

---

## Question 7

```markdown
What is the default location for the Kafka metadata log in KRaft mode?
```

**Options**

```markdown
- A. The first directory specified in the `log.dirs` configuration
- B. The directory specified in the `metadata.log.dir` configuration
- C. The directory specified in the `controller.log.dir` configuration
- D. The Kafka data directory
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
Defaults to the first path in `log.dirs`.

- A. Correct — default behavior
- B. Incorrect — used only if explicitly configured
- C. Incorrect — no such config
- D. Incorrect — no special Kafka "data directory"
```

</details>

---

## Question 8

```markdown
What is the purpose of the `kafka-metadata-quorum` tool in KRaft mode?
```

**Options**

```markdown
- A. To manage the Kafka consumer offsets
- B. To generate a cluster ID for the Kafka cluster
- C. To describe the runtime status of the KRaft metadata quorum
- D. To modify the Kafka topic configurations
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Used to inspect quorum health and metadata replication.

- A. Incorrect — offsets are not its concern
- B. Incorrect — that’s `kafka-storage`
- C. Correct — used with `describe` and `--status`
- D. Incorrect — not for topic config changes
```

</details>

---

## Question 9

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
What is the purpose of the `kafka-dump-log` tool in KRaft mode?
```

**Options**

```markdown
- A. To display the contents of the KRaft metadata log
- B. To modify the Kafka broker configuration
- C. To list the available Kafka topics
- D. To monitor the Kafka cluster performance
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
In KRaft mode, the `kafka-dump-log` tool is used to display the contents of the KRaft metadata log.

- A. Correct. It allows inspection of log segments and snapshots from the cluster metadata directory.
- B. Incorrect. Configuration changes are done via `server.properties` or `kafka-configs`, not this tool.
- C. Incorrect. Use `kafka-topics` to list Kafka topics.
- D. Incorrect. Monitoring is handled by other tools, not `kafka-dump-log`.
```

</details>

---

## Question 12

```markdown
What is the purpose of the `kafka-metadata-shell` tool in KRaft mode?
```

**Options**

```markdown
- A. To modify the Kafka broker configuration
- B. To list the available Kafka topics
- C. To monitor the Kafka cluster performance
- D. To interactively inspect the KRaft metadata
```

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**

```markdown
The `kafka-metadata-shell` tool is used to interactively explore the KRaft metadata state.

- A. Incorrect. Use `kafka-configs` or config files for that.
- B. Incorrect. Use `kafka-topics` to list topics.
- C. Incorrect. Use dedicated monitoring tools.
- D. Correct. This tool provides an interactive shell for inspecting metadata logs.
```

</details>

---

## Question 13

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

## Question 14

```markdown
What is the purpose of the `metadata.max.idle.interval.ms` configuration in KRaft mode?
```

**Options**

```markdown
- A. To set the maximum time allowed for a metadata request to be idle before it is cancelled
- B. To specify the maximum time the active controller can be idle before a new controller is elected
- C. To configure the frequency at which the active controller writes no-op records to the metadata partition
- D. To define the maximum interval allowed between two consecutive metadata log segments
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
This setting controls how often no-op records are written to avoid metadata log idleness.

- A. Incorrect. Not related to metadata request idle timeout.
- B. Incorrect. Not tied to controller election.
- C. Correct. Defines frequency of writing no-op records.
- D. Incorrect. Doesn’t affect segment intervals.
```

</details>

---

## Question 15

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

## Question 16

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

## Question 17

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

## Question 18

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


## Question 21

```markdown
What happens when a new broker joins a KRaft cluster and the `controller.quorum.voters` configuration is not updated to include the new broker?
```

**Options**

```markdown
- A. The new broker automatically becomes a voter in the controller quorum
- B. The new broker joins as an observer and does not participate in the controller quorum voting
- C. The new broker is unable to join the cluster until the configuration is updated
- D. The new broker replaces one of the existing voters in the controller quorum
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
When a new broker joins a KRaft cluster, it does not automatically become a voter in the controller quorum if the `controller.quorum.voters` configuration is not updated to include the new broker.

- A. Incorrect — The new broker does not automatically become a voter without being added to the configuration.
- B. Correct — The new broker joins as an observer and does not participate in voting.
- C. Incorrect — The broker can join as an observer without configuration update.
- D. Incorrect — The new broker does not replace existing voters unless explicitly configured.
```

</details>

---

## Question 22

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

## Question 23

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

## Question 24

```markdown
What is the impact of setting the `controller.quorum.election.backoff.max.ms` configuration to a very high value in KRaft mode?
```

**Options**

```markdown
- A. It increases the frequency of controller elections, improving fault tolerance
- B. It reduces the time taken for a new controller to be elected, minimizing downtime
- C. It prolongs the time taken for a new controller to be elected, potentially increasing downtime
- D. It has no impact on the controller election process
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
A high value prolongs the wait time before brokers attempt election, increasing controller election time and downtime.

- A. Incorrect — Frequency is not increased, it's delayed.
- B. Incorrect — Time to election is increased, not reduced.
- C. Correct — Longer backoff increases election duration and downtime.
- D. Incorrect — The setting affects the election process directly.
```

</details>

---

## Question 25

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

## Question 26

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

## Question 27

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

## Question 28

```markdown
What is the impact of having a large value for the `controller.quorum.request.timeout.ms` configuration in KRaft mode?
```

**Options**

```markdown
- A. It increases the time the controller waits for a quorum of voters to respond to a request
- B. It reduces the time the controller waits for a quorum of voters to respond to a request
- C. It sets the maximum time allowed for the controller to process a request
- D. It determines the frequency at which the controller sends heartbeats to the brokers
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
A large value for `controller.quorum.request.timeout.ms` increases the wait time for a quorum of voters to respond.

- A is correct: more time is allowed for voter responses.
- B is incorrect; a larger value does not reduce wait time.
- C is incorrect; it doesn't limit processing time.
- D is incorrect; heartbeat frequency is controlled separately.
```

</details>

---

## Question 29

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

## Question 30

```markdown
What is the impact of setting `controller.quorum.fetch.timeout.ms` to a very low value in KRaft mode?
```

**Options**

```markdown
- A. It increases the time the controllers wait for a fetch response from the active controller
- B. It reduces the time the controllers wait for a fetch response from the active controller
- C. It sets the maximum time allowed for a controller to fetch data from the brokers
- D. It determines the frequency at which the controllers fetch data from the active controller
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Setting `controller.quorum.fetch.timeout.ms` to a very low value reduces the waiting time for fetch responses.

- A is incorrect because the time decreases.
- B is correct: controllers wait less before timing out.
- C is incorrect; it does not control fetch time from brokers.
- D is incorrect; fetch frequency is controlled elsewhere.
```

</details>
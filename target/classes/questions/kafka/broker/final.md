## Question 1

Assuming a Kafka topic is configured with the following settings:

* `log.segment.bytes` = 1073741824 (1GB)
* `log.retention.ms` = 86400000 (1 day)
* `log.retention.bytes` = -1

Which of the following statements accurately describes the log retention policy for this Kafka topic?

* A. Logs are retained based on size; once the log size exceeds 1GB, older segments are deleted.
* B. Logs are retained for exactly one day, regardless of the size of the log.
* C. Logs are retained until the size of the log exceeds 1GB or for one day, whichever comes first.
* D. Logs are retained indefinitely, as `log.retention.bytes` is set to -1, overriding other retention configurations.

<details><summary>Response:</summary> 

**Answer:** B

**Explanation:**
Kafka deletes segments based on time or size **only if** a limit is configured.

* `log.retention.ms = 86400000` (1 day) is active,
* `log.retention.bytes = -1` disables size-based deletion.
  So, **time-based deletion** is applied: logs older than 1 day are removed.
  `log.segment.bytes = 1GB` just defines the size of each segment file, not the retention policy.

</details>

---

## Question 2

Consider a Kafka topic with the following configuration:

* `cleanup.policy` = "compact,delete"
* `min.cleanable.dirty.ratio` = 0.5
* `delete.retention.ms` = 86400000 (1 day)
* `segment.ms` = 43200000 (12 hours)

Which of the following statements correctly describes the behavior of log compaction and deletion for this topic?

* A. Log compaction and deletion are mutually exclusive; only one policy can be active at any time.
* B. Log compaction will occur once 50% of the segment data is marked as dirty, and logs older than 1 day will be deleted.
* C. Deleted records are removed immediately from the log; `delete.retention.ms` specifies the retention time for all records.
* D. `segment.ms` dictates the maximum lifespan of any record in the log, after which it is eligible for compaction or deletion.

<details><summary>Response:</summary> 

**Answer:** B

**Explanation:**
Kafka supports both compaction and deletion on a topic when `cleanup.policy = compact,delete`.

* Compaction triggers when 50% of the segment is "dirty" (`min.cleanable.dirty.ratio = 0.5`).
* `delete.retention.ms` holds tombstones (delete markers) for 1 day before they're purged.
* `segment.ms` simply determines how often Kafka rolls new segments; it doesn’t control record retention.

</details>

Here are the two questions converted into your desired format:

---

## Question 3

In a Kafka cluster, the Controller is a critical component for managing cluster state. Which of the following statements accurately describe the role and election of the Controller? (Select two)

* A. Elected by broker majority
* B. Elected by Zookeeper ensemble
* C. Responsible for partition leader election
* D. Manages consumer group offsets
* E. Automatically assigns replicas to brokers based on load

<details><summary>Response:</summary> 

**Answer:** B and C

**Explanation:**

* **B. Elected by Zookeeper ensemble**: Kafka uses Zookeeper to manage cluster metadata, including electing the Controller. Brokers create ephemeral nodes, and Zookeeper selects the Controller from live brokers.

* **C. Responsible for partition leader election**: The Controller manages which broker is the leader for each partition, especially during failures.

* **A** is incorrect because election is handled by Zookeeper, not by a broker majority.

* **D** is incorrect since offset management is handled by Kafka brokers and stored in the `__consumer_offsets` topic.

* **E** is incorrect; replica assignment happens at topic creation or via manual tools, not automatically by the Controller.

Note: Kafka's newer KRaft mode (post-Zookeeper) changes this behavior slightly but Zookeeper is still common in many setups.

</details>

---

## Question 4

In the context of Kafka's distributed architecture, broker elections are vital for cluster health and stability. Consider the following advanced scenarios where Kafka's internal mechanisms must decide on leadership roles:

* A. The Zookeeper ensemble elects the new Controller based on ephemeral node creation sequence.
* B. The new partition leader is elected based on the ISR list order, favoring replicas with the most recent updates.
* C. Brokers in the main cluster segment with access to Zookeeper retain their roles, while isolated brokers step down until connectivity is restored.
* D. A broker majority within the isolated segment elects a new temporary Controller until the network partition is resolved.
* E. The election of a new partition leader among replicas with identical network latency is determined by a random selection process.

<details><summary>Response:</summary> 

**Answer:** A, B, and C

**Explanation:**

* **A**: Correct. Zookeeper uses ephemeral nodes to elect a new Controller. The broker that registers first (based on sequence) becomes the new Controller.

* **B**: Correct. Partition leader election is based on the ISR list. The Controller selects the first available in-sync replica as the new leader.

* **C**: Correct. Only brokers with access to Zookeeper retain leadership roles. Isolated brokers lose leadership until they reconnect.

* **D** is incorrect because Kafka does not perform elections within isolated segments.

* **E** is incorrect because election is deterministic based on ISR, not random or latency-based.

</details>

Got it! Here's the formatted version for Questions 5, 6, and 7 in your requested style:

---

## Question 5

A Kafka producer is configured to use the `acks=all` setting while publishing messages to a topic partition that has a replication factor of 3. The topic is also configured with `min.insync.replicas=2`. Broker A hosts the current leader for this partition, while Brokers B and C host the replicas. Due to unforeseen circumstances, both Broker B and Broker C go offline simultaneously. What is the impact on the producer's ability to successfully publish messages to this partition?

* A. The producer will be able to publish messages, but with potential data loss.
* B. The producer will temporarily be unable to publish messages until at least one replica broker comes back online.
* C. The producer will continue to publish messages successfully without any impact.
* D. The producer will immediately switch to another topic's partition that has all replicas available.

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**
With `acks=all`, the producer requires acknowledgments from the leader and all in-sync replicas (ISRs). If Brokers B and C are offline, the ISR drops below the `min.insync.replicas=2` requirement, and the producer won't be able to publish messages. Publishing resumes once at least one replica rejoins the ISR, allowing the `acks=all` requirement to be satisfied.

</details>

---

## Question 6

When a Kafka broker starts up, it performs various initialization tasks. Which of the following is NOT one of these tasks?

* A. Registering itself with Zookeeper
* B. Loading the replica assignment for each partition it hosts
* C. Creating a new Zookeeper znode for each topic it has partitions for
* D. Initializing the log directories for each partition it hosts

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**
Topic znodes in Zookeeper are created during topic creation, not during broker startup. A broker on startup registers itself with Zookeeper, loads its partition assignments, and initializes log directories for the partitions it hosts, but it does not create new znodes for topics unless a topic is being created.

</details>

---

## Question 7

In a Kafka cluster, the Controller is responsible for managing partition states and leadership. How does the Controller ensure that partition leadership is evenly distributed among the brokers in the cluster?

* A. The Controller periodically triggers a rebalance operation to redistribute partition leadership.
* B. The Controller assigns leadership to the broker with the least number of leader partitions for each new partition.
* C. The Controller uses a round-robin algorithm to assign leadership across brokers.
* D. The Controller does not actively manage the distribution of partition leadership among brokers.

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**
The Controller manages partition leadership changes when necessary (e.g., on broker failure), but it does not actively balance leadership across brokers. Kafka does not automatically rebalance partition leadership for even distribution unless explicitly triggered by administrative tools or external logic.

</details>

Here's your updated template with the questions you provided:

---

## Question 8

Consider a Kafka cluster with 5 brokers and a topic with 10 partitions and a replication factor of 3. The cluster experiences a network partition, splitting the brokers into two groups: Group A with 2 brokers and Group B with 3 brokers. Both groups can communicate with Zookeeper. How does Kafka handle partition leadership in this scenario?

* A. Partitions with a leader in Group A will continue to function normally, while partitions with a leader in Group B will be offline until the network partition is resolved.
* B. Partitions with a leader in Group B will continue to function normally, while partitions with a leader in Group A will elect new leaders from the ISRs in Group B.
* C. All partitions will be offline until the network partition is resolved, as the brokers cannot reach a quorum for leader election.
* D. The behavior is non-deterministic and depends on which group the Controller belongs to.

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**
Kafka's leader election is quorum-based. Group B (3 brokers) forms the majority, so it remains functional. Group A (2 brokers) cannot elect leaders due to lack of quorum. For partitions with a leader in Group A, new leaders will be elected from ISRs within Group B.

</details>

---

## Question 9

In a Kafka cluster, a broker is configured with the following settings:

* `num.io.threads=8`
* `num.network.threads=4`
* `num.replica.fetchers=2`

What do these configurations control in terms of the broker's performance and resource utilization?

* A. `num.io.threads` = disk I/O, `num.network.threads` = network I/O, `num.replica.fetchers` = fetch from leader
* B. `num.io.threads` = network I/O, `num.network.threads` = disk I/O, `num.replica.fetchers` = fetch from leader
* C. `num.io.threads` = disk I/O, `num.network.threads` = network I/O, `num.replica.fetchers` = replicate to follower
* D. `num.io.threads` = replicate to follower, `num.network.threads` = disk I/O, `num.replica.fetchers` = network I/O

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

* `num.io.threads`: threads for handling disk I/O like writing/reading logs.
* `num.network.threads`: threads for handling socket/network operations.
* `num.replica.fetchers`: follower threads that pull data from partition leaders.

</details>

---

## Question 10

A Kafka broker is configured as follows:

* `log.segment.bytes=1073741824`
* `log.segment.ms=86400000`
* `log.retention.bytes=-1`
* `log.retention.ms=604800000`

When will Kafka start a new log segment for a partition, and how long will the old segments be retained?

* A. New segment after 1 GB or 24 hrs; retain for 7 days
* B. New segment after 1 GB or 24 hrs; retain indefinitely
* C. New segment after 1 GB; retain for 7 days or if total exceeds 1 GB
* D. New segment after 24 hrs; retain for 7 days or if total exceeds 1 GB

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**
Kafka rolls a segment when it hits either the size limit (`log.segment.bytes=1GB`) or time limit (`log.segment.ms=24h`). Segments are retained based only on time (`log.retention.ms=7d`) since `log.retention.bytes=-1` disables size-based retention.

</details>

---

## Question 11

A Kafka cluster is configured with:

* `default.replication.factor=2`
* `min.insync.replicas=2`

What is the minimum number of brokers required to tolerate one failure and still serve write requests?

* A. 1
* B. 2
* C. 3
* D. 4

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**
To satisfy `min.insync.replicas=2`, two brokers must be online and in-sync. If you want to tolerate one broker failure and still maintain two in-sync replicas, you need **at least 3 brokers** (to allow for one to fail and two to remain available for in-sync acknowledgment).

</details>

Here’s your updated set of questions formatted in the standardized template:

---

## Question 13

A Kafka cluster has the following configuration:

* `unclean.leader.election.enable=false`

What is the implication of this setting when a partition leader fails and there are no in-sync replicas (ISRs) available?

* A. The partition will remain unavailable until the failed leader recovers.
* B. The partition will elect a new leader from the out-of-sync replicas to maintain availability.
* C. The partition will automatically create a new replica to replace the failed leader.
* D. The partition will be reassigned to another broker in the cluster.

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**
When `unclean.leader.election.enable` is set to `false`, Kafka will not allow election of a leader from out-of-sync replicas. So, if no ISR is available during leader failure, the partition remains unavailable until the original leader recovers. This preserves data consistency.

</details>

---

## Question 14

A Kafka broker is configured with the following settings:

* `num.replication.fetchers=4`
* `replica.fetch.max.bytes=1048576`

What is the maximum amount of data that can be fetched by the broker for replication purposes in a single request?

* A. 1 MB
* B. 4 MB
* C. 1048576 bytes
* D. 4194304 bytes

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**
The `replica.fetch.max.bytes` determines the per-fetch limit per fetcher. It’s 1 MB in this case. While there are 4 fetchers, each operates independently. The question asks *per request*, so the answer is 1 MB.

</details>

---

## Question 15

A Kafka cluster is configured with the following:

* `log.retention.hours=48`
* `log.retention.bytes=1073741824`
* `log.segment.bytes=536870912`

Assuming a topic has a constant message production rate, which of the following factors will trigger a log segment to be eligible for deletion?

* A. The log segment is older than 48 hours.
* B. The log segment size exceeds 536870912 bytes (512 MB).
* C. The total size of all log segments for the topic exceeds 1073741824 bytes (1 GB).
* D. All of the above.

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**
Kafka applies both time and size-based log retention policies. A segment may be deleted if it exceeds the time limit, the total log size exceeds the quota, or older segments are pruned to respect retention policies.

</details>

---

## Question 16

A client connects to a broker in a Kafka cluster and sends a produce request for a topic partition. The broker responds with a 'Not Enough Replicas' error. What does the client do next?

* A. Retries sending the produce request to the same broker
* B. Sends metadata request to the same broker to refresh its metadata
* C. Sends produce request to the controller broker
* D. Sends metadata request to the Zookeeper to find the controller broker

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**
The client refreshes its metadata to get updated ISR information and broker status. It doesn't contact the controller or Zookeeper directly in normal operation.

</details>

---

## Question 17

A Kafka consumer is consuming from a topic partition. It sends a fetch request to the broker and receives a 'Replica Not Available' error. What is the consumer's next action?

* A. Backs off and retries the fetch request after a short delay
* B. Sends an offset commit request to trigger partition rebalancing
* C. Sends a metadata request to refresh its view of the cluster
* D. Closes the connection and tries connecting to a different broker

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**
The consumer should refresh metadata to identify a healthy broker that can serve the partition. Rebalancing, retries, or switching brokers won't help without fresh metadata.

</details>

---

## Question 18

What happens if you produce to a topic that does not exist, and the broker setting `auto.create.topics.enable` is set to `false`?

* A. The broker will create the topic with default configurations
* B. The broker will reject the produce request and the producer will throw an exception
* C. The producer will automatically create the topic
* D. The producer will wait until the topic is created

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**
When `auto.create.topics.enable=false`, Kafka will not allow topic creation by client request. The broker rejects the request, and the client receives an error.

</details>

---

## Question 19

What is the default value of `auto.create.topics.enable` in Kafka?

* A. `true`
* B. `false`
* C. It is not set by default
* D. It depends on the Kafka version

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**
By default, Kafka enables auto topic creation (`auto.create.topics.enable=true`). It’s explicitly enabled unless overridden in the broker configuration.

</details>


Here are the new questions reformatted into the consistent template you provided:

---

## Question 20

When a topic is automatically created due to `auto.create.topics.enable` being `true`, what configurations are used for the new topic?

* A. The configurations specified by the producer or consumer
* B. The default configurations set on the broker
* C. A combination of producer/consumer configurations and broker defaults
* D. No configurations are set, the topic is created with empty configuration

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**
When Kafka automatically creates a topic due to `auto.create.topics.enable` being `true`, it uses the default topic configurations set on the broker. These defaults are defined by settings such as:

* `num.partitions`
* `default.replication.factor`

Producer or consumer-level settings are **not** used in this automatic process.

* A and C are incorrect because Kafka ignores client configurations in this case.
* D is incorrect because Kafka does apply default broker settings, not an empty configuration.

</details>

---

## Question 21

Can Kafka's zero-copy optimization be used in combination with compression?

* A. Yes, zero-copy and compression can be used together seamlessly.
* B. No, zero-copy is incompatible with compression and cannot be used together.
* C. Zero-copy can be used with compression, but it requires additional configuration.
* D. Zero-copy is automatically disabled when compression is enabled.

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**
Kafka's zero-copy optimization and compression **can be used together seamlessly**. Compression is handled before data hits the disk, and zero-copy operates at the level of transferring the data from disk to the network socket, regardless of whether it's compressed.

* Zero-copy works with compressed data without modification.
* No extra configuration is needed.
* Compression happens at the producer, and decompression at the consumer.

</details>


Great! Here are your questions 22–26 rewritten using your specified template:

---

## Question 22

What is the relationship between the `replication.factor` of a topic and the `min.insync.replicas` setting?

* A. `min.insync.replicas` must be less than or equal to the `replication.factor`
* B. `min.insync.replicas` must be greater than the `replication.factor`
* C. `min.insync.replicas` and `replication.factor` are independent settings
* D. `min.insync.replicas` must be equal to the `replication.factor`

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**
`min.insync.replicas` must be less than or equal to the `replication.factor`. This is because you cannot require acknowledgments from more replicas than actually exist. The producer will fail to send messages if `min.insync.replicas` is higher than the number of replicas available or in sync.

</details>

---

## Question 23

What happens when a producer sends a message with `acks=all` to a topic that has a `min.insync.replicas` value greater than the number of currently in-sync replicas?

* A. The producer will receive an acknowledgment and the write will succeed
* B. The producer will receive an error indicating that the `min.insync.replicas` requirement is not met
* C. The producer will wait indefinitely until the number of in-sync replicas meets the `min.insync.replicas` requirement
* D. The producer will ignore the `min.insync.replicas` setting and write the message successfully

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**
When `acks=all` is set and the number of in-sync replicas is lower than `min.insync.replicas`, the write fails. Kafka immediately returns an error to the producer (like `NotEnoughReplicasException`) to maintain durability guarantees.

</details>

---

## Question 24

What happens if you set both `log.retention.ms` and `log.retention.minutes` configurations in Kafka?

* A. The larger value will take precedence
* B. The smaller value will take precedence
* C. Kafka will use an average of both values
* D. It will result in a configuration error

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**
Kafka uses the smallest of the retention values provided (`log.retention.ms`, `log.retention.minutes`, or `log.retention.hours`). This ensures that the most restrictive retention setting is enforced.

</details>

---

## Question 25

How can you set a retention period of 2 weeks for a specific topic in Kafka?

* A. Set `retention.ms=1209600000` in the topic configuration
* B. Set `log.retention.hours=336` in the broker configuration
* C. Set `log.retention.ms=1209600000` in the broker configuration
* D. Set `retention.ms=1209600000` in the broker configuration

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**
The correct way to configure a 2-week retention period for a specific topic is by setting `retention.ms=1209600000` (14 days in milliseconds) directly in the **topic** configuration. Broker settings apply cluster-wide.

</details>

---

## Question 26

What is the default value of the `log.retention.hours` configuration in Kafka?

* A. 168 hours (1 week)
* B. 24 hours (1 day)
* C. 720 hours (30 days)
* D. Infinite retention

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**
The default for `log.retention.hours` is **168 hours**, which equals 1 week. This means Kafka retains log segments for 7 days unless overridden by another retention configuration.

</details>

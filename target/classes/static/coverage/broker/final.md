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


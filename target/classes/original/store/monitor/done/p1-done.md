## Question 1

```markdown
Which tool is commonly used to monitor Kafka cluster health and performance?
```

**Options**

```markdown
- A. Nagios
- B. Prometheus
- C. Elasticsearch
- D. Splunk
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Prometheus is widely used for monitoring Kafka cluster health and performance. It collects metrics from Kafka brokers, producers, and consumers, and stores them in a time-series database. Prometheus can be used with Grafana for visualizing these metrics.

- A. While Nagios can be used for monitoring, it's not specialized for Kafka metrics.
- B. Correct — Prometheus is designed for metric scraping and works well with Kafka.
- C. Elasticsearch is used more for logging and search.
- D. Splunk is a log aggregator, not primarily used for metrics.
```

</details>

---

## Question 2

```markdown
What is the primary purpose of JMX in the context of Kafka monitoring?
```

**Options**

```markdown
- A. To configure Kafka brokers
- B. To provide real-time logging of Kafka events
- C. To expose Kafka metrics for monitoring
- D. To manage Kafka ACLs
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
JMX (Java Management Extensions) is used to expose Kafka metrics for monitoring. Kafka brokers expose various metrics through JMX, which can be collected by tools like Prometheus.

- A. JMX does not configure Kafka brokers.
- B. JMX isn't used for real-time logging.
- C. Correct — JMX is used to expose metrics.
- D. ACL management is handled separately from JMX.
```

</details>

---

## Question 3

```markdown
Which Kafka metric would you monitor to detect message delivery delays in a Kafka cluster?
```

**Options**

```markdown
- A. MessagesInPerSec
- B. RequestLatencyMs
- C. UnderReplicatedPartitions
- D. BytesOutPerSec
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
`RequestLatencyMs` indicates the latency of requests, making it useful for detecting delivery delays.

- A. Measures message production rate.
- B. Correct — captures request latency including produce/fetch times.
- C. Indicates replication issues, not latency.
- D. Shows throughput, not delay.
```

</details>

---

## Question 4

```markdown
Which of the following tools can be used to visualize Kafka metrics collected by Prometheus?
```

**Options**

```markdown
- A. Kibana
- B. Grafana
- C. Logstash
- D. Fluentd
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Grafana is widely used to create dashboards for metrics from Prometheus.

- A. Kibana is for Elasticsearch logs, not metrics.
- B. Correct — Grafana supports Prometheus metrics.
- C. Logstash is a log pipeline, not a dashboard tool.
- D. Fluentd handles log shipping, not metric visualization.
```

</details>

---

## Question 5

```markdown
What does the Kafka metric `UnderReplicatedPartitions` indicate?
```

**Options**

```markdown
- A. The number of partitions without a leader
- B. The number of partitions that have fewer replicas than specified
- C. The number of partitions that are not receiving messages
- D. The number of partitions with high message latency
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
`UnderReplicatedPartitions` shows how many partitions don’t have the required number of in-sync replicas.

- A. That’s tracked by a different metric — partition leader status.
- B. Correct — this metric identifies replication problems.
- C. Not related to message flow.
- D. Latency isn't measured by this metric.
```

</details>

---

## Question 6

```markdown
Which Kafka metric should be monitored to ensure sufficient disk space on Kafka brokers?
```

**Options**

```markdown
- A. LogEndOffset
- B. LogSegmentCount
- C. FreeStorageSpace
- D. MessageRate
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
`FreeStorageSpace` directly relates to available disk on brokers.

- A. Tracks latest offset, not disk space.
- B. Measures segment count.
- C. Correct — tracks available disk.
- D. Shows throughput, not storage.
```

</details>

---

## Question 7

```markdown
What is the role of Kafka Exporter in a Kafka monitoring setup?
```

**Options**

```markdown
- A. To collect logs from Kafka brokers
- B. To expose Kafka metrics to Prometheus
- C. To configure Kafka broker settings
- D. To manage Kafka consumer groups
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Kafka Exporter bridges Kafka with Prometheus by exposing broker and consumer metrics.

- A. Not a log collection tool.
- B. Correct — it exposes metrics in Prometheus format.
- C. Broker configuration is separate.
- D. Consumer group management is not its job.
```

</details>


Here are the questions you provided, reformatted using your template:

---

## Question 8

```markdown
Which Kafka metric indicates the time it takes for a record to be acknowledged by all in-sync replicas?
```

**Options**

```markdown
- A. ReplicationLag
- B. FetchLatency
- C. ProducerLatency
- D. ISRTime
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
ReplicationLag indicates the time it takes for a record to be acknowledged by all in-sync replicas. Monitoring this metric helps ensure data consistency and reliability within the Kafka cluster.

- A. Correct — it measures lag between leader and followers.
- B. Measures time taken to fetch data from the broker.
- C. Refers to latency experienced by producer clients.
- D. Not a standard Kafka metric.
```

</details>

---

## Question 9

```markdown
Why is it important to monitor the RequestRate metric in Kafka?
```

**Options**

```markdown
- A. To measure the number of messages being produced
- B. To measure the number of bytes being consumed
- C. To measure the rate of requests being handled by Kafka brokers
- D. To measure the number of partitions
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
RequestRate measures the rate of all types of requests (produce, fetch, etc.) handled by brokers, which is key for assessing broker load.

- A. Measures production activity only.
- B. Focuses on data volume consumed.
- C. Correct — gives insight into overall broker activity.
- D. Unrelated to request throughput.
```

</details>

---

## Question 10

```markdown
What does the Kafka metric ConsumerLag indicate?
```

**Options**

```markdown
- A. The number of messages a consumer has consumed
- B. The number of messages a consumer is behind in processing
- C. The time a consumer takes to process a message
- D. The number of partitions a consumer is subscribed to
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
ConsumerLag shows how far a consumer is behind the latest messages — a key metric for ensuring near real-time processing.

- A. Does not reflect backlog.
- B. Correct — tracks how behind a consumer is.
- C. Not typically tracked in this metric.
- D. Relates to configuration, not lag.
```

</details>

---

## Question 11

```markdown
Which Kafka metric would you monitor to identify potential leader election issues?
```

**Options**

```markdown
- A. LeaderElectionRateAndTimeMs
- B. RequestRate
- C. MessageInPerSec
- D. ISRTime
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
LeaderElectionRateAndTimeMs helps you track how often and how long it takes to elect leaders — frequent elections may indicate problems.

- A. Correct — monitors leader election behavior.
- B. Measures request throughput.
- C. Measures message ingress.
- D. Not directly related to leadership changes.
```

</details>

---

## Question 12

```markdown
What is the purpose of the ActiveControllerCount metric in Kafka?
```

**Options**

```markdown
- A. To count the number of active consumers
- B. To indicate the number of active brokers
- C. To show the number of active controller nodes
- D. To measure the rate of message production
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
ActiveControllerCount should always be 1 in a healthy Kafka cluster — it tracks the controller responsible for metadata changes.

- A. Refers to consumers, not controllers.
- B. Measures broker count, not controllers.
- C. Correct — shows how many controllers are active.
- D. Irrelevant to controller metrics.
```

</details>

---

## Question 13

```markdown
Which Kafka metric helps in monitoring the health of consumer groups?
```

**Options**

```markdown
- A. ConsumerLag
- B. ProducerRequestRate
- C. BrokerTopicBytesOutPerSec
- D. FetchLatency
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
ConsumerLag shows how far consumers are behind, making it the best indicator of consumer group health.

- A. Correct — indicates lag.
- B. Pertains to producers.
- C. Measures output per broker/topic.
- D. Measures time taken for fetch requests.
```

</details>

---

## Question 14

```markdown
Which tool can be used to collect JMX metrics from Kafka brokers for monitoring?
```

**Options**

```markdown
- A. Logstash
- B. JConsole
- C. Metricbeat
- D. Telegraf
```

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**

```markdown
Telegraf includes a JMX input plugin for collecting metrics and forwarding them to systems like Prometheus or InfluxDB.

- A. Used for logs, not metrics.
- B. Good for manual inspection, not automated collection.
- C. Not typically used for JMX.
- D. Correct — ideal for automated JMX metric collection.
```

</details>

---

## Question 15

```markdown
What does the BrokerTopicBytesOutPerSec metric measure in Kafka?
```

**Options**

```markdown
- A. The number of bytes produced to a topic per second
- B. The number of bytes consumed from a topic per second
- C. The number of bytes replicated per second
- D. The number of bytes stored in a topic
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
BrokerTopicBytesOutPerSec shows how much data consumers are pulling from topics.

- A. Refers to producer-side data.
- B. Correct — consumer throughput metric.
- C. Relates to inter-broker replication.
- D. Not typically measured as a rate.
```

</details>

---

## Question 16

```markdown
Which metric should be monitored to detect partition imbalance in a Kafka cluster?
```

**Options**

```markdown
- A. PartitionCount
- B. LeaderCount
- C. UnderReplicatedPartitions
- D. PartitionLoad
```

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**

```markdown
PartitionLoad helps identify whether some brokers are overloaded with too many partitions.

- A. Total number of partitions, not imbalance.
- B. Tracks leadership, not load distribution.
- C. Measures replication state.
- D. Correct — shows load per broker.
```

</details>

---

## Question 17

```markdown
Which Kafka metric indicates the rate of log flush operations?
```

**Options**

```markdown
- A. LogFlushRateAndTimeMs
- B. DiskFlushRate
- C. LogRetentionRate
- D. FlushTime
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
LogFlushRateAndTimeMs shows how often logs are flushed to disk and how long it takes, important for durability and latency.

- A. Correct — built-in Kafka metric.
- B. Not a standard metric.
- C. Related to retention, not flushes.
- D. Too generic and not Kafka-specific.
```

</details>

---

## Question 18

```markdown
What is the significance of the RequestQueueSize metric in Kafka?
```

**Options**

```markdown
- A. It measures the number of requests waiting to be processed by Kafka brokers.
- B. It indicates the number of active consumer requests.
- C. It shows the total number of requests handled by Kafka brokers.
- D. It measures the size of the message queue in bytes.
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
RequestQueueSize reveals how congested the broker is — high values may signal that it can’t keep up.

- A. Correct — shows pending requests.
- B. Only tracks consumer side.
- C. Captured by other metrics like RequestRate.
- D. Not accurate for this metric.
```

</details>

---

## Question 19

```markdown
Which Kafka metric would you monitor to understand the latency experienced by producers?
```

**Options**

```markdown
- A. ProducerRequestRate
- B. ProducerLatency
- C. RequestQueueSize
- D. ProducerRequestQueueTimeMs
```

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**

```markdown
ProducerRequestQueueTimeMs tells how long producer requests wait before being handled — a key latency indicator.

- A. Tracks request volume, not latency.
- B. Not a standard Kafka metric.
- C. Related to broker-side queueing.
- D. Correct — reflects producer experience.
```

</details>

---

## Question 20

```markdown
Which metric is critical for monitoring Kafka broker heap memory usage?
```

**Options**

```markdown
- A. BrokerHeapMemoryUsed
- B. JvmMemoryUsage
- C. HeapMemoryUsage
- D. BrokerJvmHeap
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
JvmMemoryUsage is the proper Kafka/JMX metric for tracking JVM heap memory consumption.

- A, D. Not standard Kafka metrics.
- B. Correct — reliable heap tracking.
- C. Not commonly used in Kafka metrics directly.
```

</details>

---

## Question 21

```markdown
How can the client.id setting be useful in monitoring and troubleshooting Kafka clients?
```

**Options**

```markdown
- A. It allows setting different configuration parameters for each client
- B. It enables tracking and correlating client activity in logs and metrics
- C. It determines the partitioning strategy used by the client
- D. It specifies the maximum number of connections the client can establish
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
client.id is used to identify and trace activity per client — brokers log this ID in metrics and logs.

- A. Configs are shared via other means.
- B. Correct — aids monitoring/debugging.
- C. Handled by partitioner classes.
- D. Not controlled by client.id.
```

</details>

---

## Question 22

```markdown
You are building a consumer application that processes events from a Kafka topic. What is the most important metric to monitor to ensure real-time processing?
```

**Options**

```markdown
- A. MessageInPerSec
- B. BytesInPerSec
- C. RecordsLagMax
- D. BrokerCount
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
records-lag-max shows how far a consumer is behind in real time — it’s the best signal for whether you're processing events on time.

- A. Measures message production.
- B. Tracks data volume in.
- C. Correct — directly tied to lag.
- D. Unrelated to consumer performance.
```

</details>


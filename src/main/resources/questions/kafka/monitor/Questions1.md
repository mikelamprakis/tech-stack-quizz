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

- A. Nagios is a general monitoring tool but not specialized for Kafka metrics.
- B. Prometheus is specialized for metrics collection and integrates well with Kafka.
- C. Elasticsearch is a search engine used more for logging.
- D. Splunk is also focused on log management rather than time-series metrics.
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
JMX (Java Management Extensions) is used to expose Kafka metrics for monitoring. Kafka brokers expose various metrics (such as broker metrics, topic metrics, and consumer group metrics) through JMX.

- A. JMX does not configure brokers.
- B. JMX is not used for real-time logging.
- C. JMX exposes metrics for external monitoring tools.
- D. Kafka ACLs are managed through different means.
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
`RequestLatencyMs` indicates the time taken to process requests in Kafka, which can reveal delays in message delivery.

- A. Measures message production rate, not delay.
- B. Reflects latency and potential delays.
- C. Indicates replication health, not latency.
- D. Tracks consumption throughput, not latency.
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
Grafana is widely used with Prometheus for visualizing time-series metrics, including Kafka metrics.

- A. Kibana works with Elasticsearch, not Prometheus.
- B. Grafana is the correct tool for Prometheus visualizations.
- C. Logstash is a log shipper, not a visualizer.
- D. Fluentd is a log collector, not a dashboard tool.
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
`UnderReplicatedPartitions` shows how many partitions don’t have the expected number of in-sync replicas, indicating a reliability issue.

- A. That would be `OfflinePartitionsCount`.
- B. Correct — indicates under-replication.
- C. Not directly related to replication.
- D. Latency is measured by other metrics.
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
`FreeStorageSpace` reflects available disk space and helps prevent storage issues on Kafka brokers.

- A. Tracks the offset, not disk usage.
- B. Counts log segments but doesn’t reflect space directly.
- C. Correct — shows available storage.
- D. Message rate is about throughput, not storage.
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
Kafka Exporter exposes Kafka metrics (e.g., lag, topic size) to Prometheus for collection and monitoring.

- A. Log collection is handled by other tools.
- B. Correct — Kafka Exporter bridges Kafka and Prometheus.
- C. Configuration is not done through Kafka Exporter.
- D. Consumer group management is separate.
```

</details>

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
`ReplicationLag` measures the delay between leader and follower replicas — a key measure for consistency.

- A. Correct — shows replica acknowledgment delay.
- B. Fetch latency is for consumers pulling messages.
- C. Producer latency is producer-side delay.
- D. ISRTime is not a standard Kafka metric.
```

</details>

---

## Question 9

```markdown
Why is it important to monitor the `RequestRate` metric in Kafka?
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
`RequestRate` shows how many requests (produce, fetch, metadata) are handled by brokers — a key load indicator.

- A. That’s `MessagesInPerSec`.
- B. That’s `BytesOutPerSec`.
- C. Correct — reflects broker request load.
- D. Partition count is a configuration, not a metric.
```

</details>

---

## Question 10

```markdown
What does the Kafka metric `ConsumerLag` indicate?
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
`ConsumerLag` reflects how far behind a consumer is from the latest offset — crucial for ensuring consumers keep up.

- A. That would be a consumption count, not lag.
- B. Correct — shows how delayed the consumer is.
- C. Processing time is not the same as lag.
- D. Subscription info doesn’t reveal lag.
```

</details>

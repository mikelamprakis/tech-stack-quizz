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
LeaderElectionRateAndTimeMs indicates the rate and duration of leader elections. Frequent or slow elections may signal instability.

- A. Correct: It directly tracks leader election behavior.
- B. RequestRate measures general request throughput.
- C. MessageInPerSec shows ingestion rate, unrelated to leadership.
- D. ISRTime is not a standard Kafka metric.
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
ActiveControllerCount shows how many active controllers are in the cluster—there should always be one.

- A. Not about consumers.
- B. Broker count is a different metric.
- C. Correct: Monitors controller status.
- D. Not related to production rate.
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
ConsumerLag shows how behind consumers are, which is key to monitoring consumer group performance.

- A. Correct: Directly measures lag.
- B. Measures producer activity.
- C. Tracks data output, not lag.
- D. Measures fetch delays, not lag extent.
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
Telegraf supports JMX input plugin, making it ideal for Kafka metric collection.

- A. Logstash is not typically used for JMX.
- B. JConsole is interactive, not for automated monitoring.
- C. Metricbeat has JMX modules but less integration than Telegraf.
- D. Correct: Telegraf is widely used for this.
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
BrokerTopicBytesOutPerSec tracks the data leaving the broker to consumers.

- A. Bytes produced is BrokerTopicBytesInPerSec.
- B. Correct: Measures outbound throughput.
- C. Replication metrics are tracked differently.
- D. Topic storage isn’t measured this way.
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
PartitionLoad shows how partitions are distributed across brokers.

- A. Total partition count doesn't show imbalance.
- B. LeaderCount shows leaders, not imbalance.
- C. UnderReplicatedPartitions relates to replication, not balance.
- D. Correct: Detects uneven load distribution.
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
LogFlushRateAndTimeMs tracks how often and how fast logs are flushed to disk.

- A. Correct: Standard metric for flush operations.
- B. Not a real Kafka metric.
- C. Refers to retention, not flushing.
- D. Not a defined Kafka metric.
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
RequestQueueSize reveals potential overload by tracking pending requests.

- A. Correct: Tracks queued requests.
- B. Doesn’t isolate consumer requests.
- C. Total request count is different.
- D. Kafka queues messages in segments, not byte-counted queues.
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
ProducerRequestQueueTimeMs shows how long a producer’s request waits before processing.

- A. Measures request rate, not latency.
- B. Not a standard Kafka metric.
- C. Refers to broker-side queueing.
- D. Correct: Indicates producer-side queuing delay.
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
JvmMemoryUsage shows total JVM heap utilization, which is vital for diagnosing memory issues.

- A. Not a standard Kafka metric.
- B. Correct: Widely used metric.
- C. May refer to internal JVM stats but less common in Kafka tools.
- D. Not a real Kafka metric.
```

</details>

---

## Question 20

```markdown
You are building a consumer application that processes events from a Kafka topic. What is the most important metric to monitor to ensure real-time processing?
```

**Options**

```markdown
- A. Message InPerSec
- B. bytes InPerSec
- C. records-lag-max
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
This metric shows the current lag (number of messages behind the broker), which is the best indicator of whether the consumer is keeping up with real-time data.

- A. Tracks how many messages are published to Kafka, not how well the consumer is processing.
- B. Measures incoming data volume but doesn't reflect consumer processing performance.
- C. Indicates the number of unprocessed records by the consumer — critical for real-time processing.
```

</details>


---

## Question 21

```markdown
How can the `client.id` setting be useful in monitoring and troubleshooting Kafka clients?
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
The `client.id` setting is used to track and correlate Kafka client activity in logs and metrics. By assigning a unique `client.id` to each client, you can easily trace their behavior in Kafka broker logs and monitoring systems. This is especially useful for debugging issues, analyzing performance, and understanding the workload patterns of individual clients.

- A. Incorrect – `client.id` does not configure other parameters.
- B. Correct – it provides traceability in logs and metrics.
- C. Incorrect – partitioning is handled by the producer configuration, not `client.id`.
- D. Incorrect – this is unrelated to connection limits.
```

</details>




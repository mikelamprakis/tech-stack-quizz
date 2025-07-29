## Question 1

```markdown
What is the role of a Kafka broker?
```

**Options**
```markdown
- A. To balance the load between producers and consumers
- B. To serialize and deserialize messages
- C. To monitor consumer offset positions
- D. To store data and serve client requests
```

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**

```markdown
Kafka brokers are servers that store data and handle client requests, ensuring data is available and consistent across the Kafka cluster.
```

</details>

---

## Question 2

```markdown
You are tasked with configuring a Kafka broker to manage how long data is retained before it is deleted to ensure efficient storage management. How do you adjust log retention settings in Kafka?
```

**Options**
```markdown
- A. Set the storage.duration limits in the consumer.properties file.
- B. Modify the log.retention.hours, log.retention.bytes, or log.retention.minutes settings in server.properties.
- C. Change the file.lifetime settings in the log4j.properties file.
- D. Update the data.retention.period in the kafka-configs file.
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Log retention in Kafka is controlled through settings such as log.retention.hours, log.retention.bytes, and log.retention.minutes in the server.properties file. Adjusting these settings allows you to manage how long data is kept before it is deleted, ensuring efficient storage management.
```

</details>

---

## Question 3

```markdown
A Kafka administrator needs to prevent any single client from overloading the cluster. Which feature should they implement?
```

**Options**
```markdown
- A. Enable auto-scaling for the cluster
- B. Reduce the number of topics
- C. Set quotas for byte-rate and request-rate limits
- D. Increase the number of partitions
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Kafka's quota management system allows administrators to limit the rate of data production and consumption by clients, ensuring that no single client or group monopolizes cluster resources. This is implemented via broker configuration settings.
```

</details>

---

## Question 4

```markdown
If a Kafka administrator needs to adjust broker configurations to handle increased load without downtime, which feature should they use?
```

**Options**
```markdown
- A. Load balancing reassignment
- B. Static broker reconfiguration
- C. Dynamic broker configuration
- D. Broker performance tuning
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Kafka's dynamic broker configuration allows administrators to modify certain settings at runtime without restarting brokers, thus avoiding downtime and adapting to changing conditions.
```

</details>

---

## Question 5

```markdown
You are administering a Kafka cluster and need to update a configuration setting that is classified as read-only. What must you do to apply this change, and why is this necessary?
```

**Options**
```markdown
- A. Use the Kafka command-line tool to apply the configuration change immediately
- B. Update the configuration setting in the broker configuration file and restart the broker
- C. Change the configuration setting in ZooKeeper and restart the broker
- D. Update the configuration setting dynamically using the Kafka configuration API
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
In Kafka, read-only configurations are settings that cannot be dynamically updated while the broker is running. To change these configurations, you must update the setting in the broker configuration file and restart the broker. This ensures that changes are fully integrated and effective across the Kafka system, maintaining stability and consistency in broker operations.
```

</details>

---

## Question 6

```markdown
What configuration setting in Kafka determines where logs (data) are stored?
```

**Options**
```markdown
- A. kafka.storage
- B. data.paths
- C. file.locations
- D. log.dirs
```

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**

```markdown
The log.dirs setting in server.properties specifies the directories where Kafka's logs and data are stored.
```

</details>

---

## Question 7

```markdown
How do broker-level settings differ from topic-level settings in Kafka, and when might you use each?
```

**Options**
```markdown
- A. Topic-level settings are only applicable to consumer configurations.
- B. Broker-level settings deal with network configurations, while topic-level settings handle data processing.
- C. Broker-level settings are immutable, while topic-level settings can be changed dynamically.
- D. Broker-level settings apply cluster-wide, whereas topic-level settings allow customization for individual topics.
```

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**

```markdown
Broker-level settings provide cluster-wide defaults that affect all topics unless overridden, while topic-level settings offer granularity, allowing administrators to tailor settings such as retention policies and partition counts based on specific topic requirements.
```

</details>

---

## Question 8

```markdown
What are critical performance metrics to monitor regularly for Kafka brokers to ensure system health and efficiency?
```

**Options**
```markdown
- A. Network I/O rates
- B. Active controller counts
- C. Request rates and latencies
- D. JVM memory usage
```

<details><summary>Response:</summary>

**Answer:** A, C, D

**Explanation:**

```markdown
Essential metrics for maintaining Kafka broker performance include request rates and latencies, network I/O rates, and JVM memory usage. Monitoring these metrics helps in assessing the health and efficiency of the brokers, enabling timely interventions to maintain system stability and performance.
```

</details>

---

## Question 9

```markdown
You are tuning a Kafka broker to handle an increased load of network requests efficiently. What role does the num.network.threads configuration play in Kafka's performance?
```

**Options**
```markdown
- A. It controls the number of threads dedicated to handling network requests.
- B. It dictates the number of threads used for disk I/O operations.
- C. It manages the distribution of data across network channels.
- D. It sets the maximum number of network connections that can be handled simultaneously.
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
The num.network.threads setting is crucial for optimizing Kafka's throughput and handling peak loads by specifying the number of threads used for processing network requests. Adjusting this parameter helps ensure that the broker can efficiently manage incoming and outgoing network traffic, thereby improving overall performance and responsiveness.
```

</details>

---

## Question 10

```markdown
What is the significance of the min.insync.replicas setting in a Kafka environment?
```

**Options**
```markdown
- A. It specifies the minimum number of replicas that must acknowledge a read operation before it is considered successful.
- B. It sets the minimum number of replicas that must acknowledge a write operation for it to be considered successful.
- C. It controls the number of replica fetches that can fail before causing a broker shutdown.
- D. It determines the number of replicas that can be out of sync before the leader is re-elected.
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
The min.insync.replicas setting ensures data durability and high availability by requiring a minimum number of in-sync replicas to acknowledge a write operation before it is considered successful.
```

</details>

---

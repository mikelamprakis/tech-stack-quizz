## Question 1

```markdown
You are managing a ZooKeeper ensemble consisting of 7 servers. Due to a network issue, some servers might go offline. What is the maximum number of servers that can go missing while still allowing the ensemble to operate correctly?
```

**Options**
```markdown
- A. 4
- B. 3
- C. 2
- D. 5
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Majority consists of 3 nodes for 7 nodes cluster, so 4 can go missing.
```

</details>

---

## Question 2

```markdown
What is the role of the quorum controller in Kafka's KIP-500 plan?
```

**Options**
```markdown
- A. It replaces the function of the Kafka brokers.
- B. It serves as the external storage service for Kafka logs.
- C. It acts as the central authority for managing cluster metadata and partition leadership.
- D. It manages consumer group offsets.
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
The quorum controller, introduced with KIP-500, is part of Kafka’s initiative to remove ZooKeeper dependencies. It serves as the central authority for managing cluster metadata, including partition leadership and membership changes, directly within Kafka.
```

</details>

---



## Question 4

```markdown
Which of the following roles does ZooKeeper play in a Kafka environment?
```

**Options**
```markdown
- A. Managing and coordinating Kafka brokers
- B. Storing large data files
- C. Data serialization and deserialization
- D. Executing stream processing
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
ZooKeeper is critical in managing the state of Kafka clusters, including maintaining information about brokers, topics, and partitions.
```

</details>

---

## Question 5

```markdown
How do you configure Kafka's connection to ZooKeeper?
```

**Options**
```markdown
- A. Set the zookeeper.url property in the kafka-config.sh script.
- B. Change the connection.string value in the zookeeper.properties file.
- C. Update the zookeeper.session.timeout.ms in the consumer.properties file.
- D. Modify the zookeeper.connect string in the server.properties file.
```

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**

```markdown
Kafka’s connection to ZooKeeper is configured via the zookeeper.connect property in the server.properties file, where you specify the host and port of the ZooKeeper service.
```

</details>

---

## Question 6

```markdown
Which timestamps are maintained within Kafka messages?
```

**Options**
```markdown
- A. AppendTime
- B. CreateTime
- C. ReadTime
- D. LogAppendTime
```

<details><summary>Response:</summary>

**Answer:** B, D

**Explanation:**

```markdown
CreateTime is set by the producer when the message is created, reflecting the production time. LogAppendTime is set by the broker when the message is appended to the log, used for operations like log retention and message ordering.
```

</details>

---



## Question 8

```markdown
What are the standard ports used by ZooKeeper?
```

**Options**
```markdown
- A. 8080
- B. 3888
- C. 2181
- D. 2888
```

<details><summary>Response:</summary>

**Answer:** B, C, D

**Explanation:**

```markdown
ZooKeeper uses three primary ports for its operations:

2181: This is the client port used for handling client connections to the ZooKeeper server.

2888: Known as the peer port, this is used for communication between ZooKeeper servers within a quorum.

3888: The leader port, used specifically for leader election among ZooKeeper servers. This port facilitates the coordination necessary to determine which node will act as the leader in a quorum. The port 8080 is not a standard ZooKeeper port.
```

</details>

---






## Question 3

```markdown
Besides server.properties, name another important Kafka configuration file.
```

**Options**
```markdown
- A. kafka-system.properties
- B. zookeeper.properties
- C. broker.properties
- D. client.properties
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
The zookeeper.properties file is crucial as it contains configuration details specifically for ZooKeeper, which is integral for Kafka's cluster coordination.
```

</details>

---



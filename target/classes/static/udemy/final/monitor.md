## Question 1

```markdown
What does the request-latency-avg metric indicate in Kafka monitoring?
```

**Options**
```markdown
- A. The average time in milliseconds that requests take to be processed by each broker.
- B. The average number of requests sent per second to Kafka.
- C. The average number of responses or acknowledgments (acks) received per second from brokers.
- D. The average size of batches processed by Kafka brokers.
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
The request-latency-avg metric measures the average time in milliseconds that requests take to be processed by each broker. High values in this metric could indicate performance issues within the broker, such as slow processing speeds or system resource constraints.
```

</details>

---

## Question 2

```markdown
What does the outgoing-byte-rate metric indicate in Kafka monitoring?
```

**Options**
```markdown
- A. The amount of data in bytes sent per second from Kafka, measured globally and for each broker.
- B. The average time in milliseconds that requests take to be processed by each broker.
- C. The number of responses or acknowledgments (acks) received per second from brokers.
- D. The number of requests sent per second to Kafka.
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
The outgoing-byte-rate metric shows the amount of data in bytes sent per second from Kafka, measured globally and for each broker. This metric provides a clear view of network throughput and is essential for network capacity planning and performance assessment.
```

</details>

---

## Question 3

```markdown
Which key metrics are essential to monitor for Kafka's health using JMX?
```

**Options**
```markdown
- A. Consumer lag
- B. Number of active topics
- C. Producer throughput
- D. JVM health
```

<details><summary>Response:</summary>

**Answer:** A, C, D

**Explanation:**

```markdown
Essential Kafka metrics to monitor using Java Management Extensions (JMX) include JVM health (indicative of overall system health), consumer lag (shows if consumers are keeping up with producers), and producer throughput (measures the efficiency of data ingress).
```

</details>

---

## Question 4

```markdown
How can you enable JMX monitoring for Kafka?
```

**Options**
```markdown
- A. Activating JMX in the kafka-server-start.sh script.
- B. By setting the JMX_PORT environment variable before starting Kafka.
- C. Configuring the kafka-monitor.properties file.
- D. Enabling JMX through the Kafka Control Center interface.
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
JMX monitoring for Kafka can be enabled by setting the JMX_PORT environment variable, which allows for performance and health monitoring via JMX-compliant tools.
```

</details>

---

## Question 5

```markdown
What is a primary use of integrating Kafka with Prometheus and Grafana?
```

**Options**
```markdown
- A. To visualize Kafka metrics and improve monitoring
- B. To reduce data storage costs
- C. To increase Kafka's data processing speed
- D. To manage Kafka's user permissions
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
Integrating Kafka with Prometheus for metrics collection and Grafana for visualization helps in creating comprehensive dashboards that provide insights into Kafka's performance and health, enhancing monitoring capabilities.
```

</details>

---

## Question 6

```markdown
Given these Avro types, which code snippet correctly declares a schema with these types?

{
  "type": "record",
  "name": "User",
  "fields" : [
    {
      "name": "id",
      "type": "int"
    },
    {
      "name": "name",
      "type": "string"
    }
  ]
}
```

**Options**
```markdown
- A. Should use complex types instead of primitive.
- B. Should include a namespace.
- C. Correct as shown.
- D. Needs a logical type for id.
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
The provided JSON correctly declares an Avro schema using primitive data types like int for ID and string for name, adhering to Avro's specification for record schemas.
```

</details>

---

## Question 7

```markdown
You are responsible for monitoring a Kafka consumer application that processes video streaming data. Recently, the bytes-consumed-rate metric has shown a significant decrease. What does this indicate?
```

**Options**
```markdown
- A. The total number of bytes stored in the Kafka topic has increased.
- B. The rate at which bytes are consumed per second by the Kafka consumer has increased, indicating improved throughput. Investigate potential over-consumption of resources.
- C. The average number of bytes produced per second by the Kafka producer has decreased.
- D. The rate at which bytes are consumed per second by the Kafka consumer has decreased, indicating reduced throughput.
```

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**

```markdown
The bytes-consumed-rate metric shows the rate at which bytes are consumed per second by a Kafka consumer, providing insights into the throughput of data consumption. A significant decrease in this metric indicates reduced throughput. To address this issue, you should investigate potential network issues or performance bottlenecks in the consumer application.
```

</details>

--- 

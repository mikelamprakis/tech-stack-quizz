What does the request-latency-avg metric indicate in Kafka monitoring?

Your answer is correct
The average time in milliseconds that requests take to be processed by each broker.

The average number of requests sent per second to Kafka.

The average number of responses or acknowledgments (acks) received per second from brokers.

The average size of batches processed by Kafka brokers.

Overall explanation
The request-latency-avg metric measures the average time in milliseconds that requests take to be processed by each broker. High values in this metric could indicate performance issues within the broker, such as slow processing speeds or system resource constraints.

Domain
Kafka Monitoring
Beta

---


What does the outgoing-byte-rate metric indicate in Kafka monitoring?

Your answer is correct
The amount of data in bytes sent per second from Kafka, measured globally and for each broker.

The average time in milliseconds that requests take to be processed by each broker.

The number of responses or acknowledgments (acks) received per second from brokers.

The number of requests sent per second to Kafka.

Overall explanation
The outgoing-byte-rate metric shows the amount of data in bytes sent per second from Kafka, measured globally and for each broker. This metric provides a clear view of network throughput and is essential for network capacity planning and performance assessment.

Domain
Kafka Monitoring
Beta

---


Which key metrics are essential to monitor for Kafka's health using JMX?

(Select all that apply)

Correct selection
Consumer lag

Number of active topics

Correct selection
Producer throughput

Your selection is correct
JVM health

Overall explanation
Essential Kafka metrics to monitor using Java Management Extensions (JMX) include JVM health (indicative of overall system health), consumer lag (shows if consumers are keeping up with producers), and producer throughput (measures the efficiency of data ingress).

Domain
Kafka Monitoring

---


How can you enable JMX monitoring for Kafka?

Activating JMX in the kafka-server-start.sh script.

Your answer is correct
By setting the JMX_PORT environment variable before starting Kafka.

Configuring the kafka-monitor.properties file.

Enabling JMX through the Kafka Control Center interface.

Overall explanation
JMX monitoring for Kafka can be enabled by setting the JMX_PORT environment variable, which allows for performance and health monitoring via JMX-compliant tools.

Domain
Kafka Monitoring
Beta

---

What is a primary use of integrating Kafka with Prometheus and Grafana?

Your answer is correct
To visualize Kafka metrics and improve monitoring

To reduce data storage costs

To increase Kafka's data processing speed

To manage Kafka's user permissions

Overall explanation
Integrating Kafka with Prometheus for metrics collection and Grafana for visualization helps in creating comprehensive dashboards that provide insights into Kafka's performance and health, enhancing monitoring capabilities.

Domain
Kafka Monitoring
Beta

---

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
Should use complex types instead of primitive.

Should include a namespace.

Correct answer
Correct as shown.

Your answer is incorrect
Needs a logical type for id.

Overall explanation
The provided JSON correctly declares an Avro schema using primitive data types like int for ID and string for name, adhering to Avro's specification for record schemas​​.

Domain
Avro
Beta

---

You are responsible for monitoring a Kafka consumer application that processes video streaming data. Recently, the bytes-consumed-rate metric has shown a significant decrease. What does this indicate?

The total number of bytes stored in the Kafka topic has increased.

Your answer is incorrect
The rate at which bytes are consumed per second by the Kafka consumer has increased, indicating improved throughput. Investigate potential over-consumption of resources.

The average number of bytes produced per second by the Kafka producer has decreased.

Correct answer
The rate at which bytes are consumed per second by the Kafka consumer has decreased, indicating reduced throughput.

Overall explanation
The bytes-consumed-rate metric shows the rate at which bytes are consumed per second by a Kafka consumer, providing insights into the throughput of data consumption. A significant decrease in this metric indicates reduced throughput. To address this issue, you should investigate potential network issues or performance bottlenecks in the consumer application.

Domain
Kafka Monitoring






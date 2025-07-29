You are configuring Kafka producers for a data pipeline that processes large volumes of sensor data. To optimize network bandwidth and storage, you decide to enable compression. What considerations should you keep in mind regarding how compression is handled in Kafka?

(Select all that apply)

Correct selection
Consumers automatically decompress messages upon receipt, guided by metadata embedded within the messages.

Your selection is correct
Compression is configured at the producer level, and each producer can choose its compression type independently.

Producers cannot use different compression types when sending messages to the same topic.

Compression in Kafka is enabled on a per-topic basis, and you must configure it separately for each topic.

Overall explanation
Compression in Kafka is not enabled on a per-topic basis but is configured at the producer level. Each producer can choose its compression type independently. Consumers automatically decompress messages upon receipt, guided by metadata embedded within the messages. This approach allows flexibility in compression settings across different producers sending messages to the same topic.

Domain
Kafka Producers
Beta


---


You are optimizing a Kafka producer application for high throughput and efficiency. What is the advantage of using asynchronous send in Kafka producers?

It reduces the memory footprint of the producer.

It ensures that messages are encrypted before being sent to the broker.

It allows for real-time monitoring of message delivery.

Your answer is correct
It allows producers to send messages in batches without waiting for responses from the broker, improving throughput.

Overall explanation
Asynchronous sending in Kafka allows producers to continue sending messages without waiting for broker responses. This enables producers to send messages in batches, maximizing throughput and efficiency by reducing the time spent waiting for acknowledgments from the broker.

Domain
Kafka Producers
Beta

---


How does Kafka support transactional messaging, and what problem does it solve?

By automatically replicating transactions to external systems.

Correct answer
By enabling transactions across multiple topics, preventing partial updates.

By storing all transactional messages in a separate database.

Your answer is incorrect
By using ZooKeeper to coordinate transactions.

Overall explanation
Kafka's transactional support allows producers to write to multiple partitions and topics atomically, ensuring that either all messages in a transaction are committed or none, thus solving the problem of partial writes​​.

Domain
Kafka Core Concepts

---


How does the linger.ms setting impact Kafka producer throughput and latency?

Reduces throughput by delaying message sends.

Decreases latency by sending messages immediately.

Has no impact on throughput but increases latency.

Your answer is correct
Increases both throughput and latency by batching more messages.

Overall explanation
The linger.ms setting allows the producer to wait for additional messages to batch together, increasing throughput at the cost of increased latency.





Domain
Kafka Producers
Beta

---


Consider the following code snippet for configuring a Java Kafka Producer. Will this code work? If not, what is the missing property?



Properties props = new Properties();
props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

KafkaProducer<String, String> producer = new KafkaProducer<>(props);
No, the code is missing the client.id property

No, the code is missing the acks property

Your answer is correct
No, the code is missing the bootstrap.servers property

Yes, the code will work as it is.

Overall explanation
The code is missing the bootstrap.servers property, which sets the initial list of brokers to connect to the Kafka cluster. Without this property, the Kafka Producer will not know which brokers to connect to, and the code will not work.

Domain
Kafka Producers
Beta

---

What are the supported message compression types in Kafka?

(Select all that apply)

brotli

Your selection is correct
zstd

Your selection is correct
gzip

Your selection is correct
snapp

Your selection is correct
lz4

Overall explanation
Kafka supports several message compression types to optimize performance and reduce storage requirements. The supported compression types include GZIP, Snappy, LZ4, and Zstandard (ZSTD). Each compression type offers different trade-offs in terms of compression speed and efficiency. Brotli is not a supported compression type in Kafka.

Domain
Kafka Core Concepts
Beta

---


Consider the following Java code snippet for configuring a Kafka producer. Identify any configuration issues and explain how to fix them.



Properties props = new Properties();
props.put("bootstrap.servers", "localhost:9092");
props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
props.put("acks", "all");
props.put("retries", 3);

KafkaProducer<String, String> producer = new KafkaProducer<>(props);
ProducerRecord<String, String> record = new ProducerRecord<>("my-topic", "key", "value");

producer.send(record);
producer.close();


The "acks" property should be set to "1" for better performance.

Your answer is incorrect
The configuration is correct; no changes are needed.

Correct answer
The "retries" property should be set as a String, not an integer.

The "bootstrap.servers" property should include multiple broker addresses for fault tolerance.

Overall explanation
In the provided code snippet, the "retries" property should be set as a String value, not an integer. The correct configuration should be props.put("retries", "3");. This ensures that the property is properly recognized by the Kafka producer configuration. Additionally, while including multiple broker addresses in the "bootstrap.servers" property (as mentioned in option d) is a good practice for fault tolerance, it is not an issue with the current configuration.

Domain
Kafka Producers
Beta


---


How does a Kafka producer select the partition for a message?

Based on the size of the message.

Based on the priority of the message.

Randomly, to ensure load balancing.

Your answer is correct
If a key is specified, the producer uses it to choose a partition deterministically; without a key, the producer uses a round-robin approach or a custom partitioner.

Overall explanation
Kafka producers use the message key to deterministically assign the message to a specific partition, ensuring order within a partition. If no key is provided, the producer may use a round-robin or other partitioning strategy to distribute messages across available partitions

Domain
Kafka Producers
Beta

---


You are configuring a Kafka producer for a critical application that requires high reliability in message delivery. How does the retries setting enhance reliability in Kafka producers?

By delaying message sends to reduce network congestion.

By encrypting messages to prevent data corruption.

By limiting the number of message sends to reduce server load.

Your answer is correct
By retrying message sends automatically, increasing the chances of success.

Overall explanation
The retries setting in Kafka producers allows for automatic resending of messages that fail to send initially due to transient errors, enhancing the reliability of message delivery. By retrying message sends, the producer increases the likelihood of successful message delivery despite temporary issues such as network interruptions or broker unavailability.

Domain
Kafka Producers

---


What considerations should guide the selection of a compression type for Kafka producers?

The color depth supported by the compression method.

Your answer is correct
The balance between CPU usage and network bandwidth savings.

The security level of the compression algorithm.

The compatibility with consumer decompression capabilities.

Overall explanation
Selecting a compression type should consider the trade-off between CPU resources used for compressing/decompressing messages and the benefits of reduced network bandwidth usage.

Domain
Kafka Producers

----

Why and how would you implement a custom partitioner for a Kafka producer?

To increase message encryption, by altering the default serializer.

Your answer is correct
To distribute messages based on business logic, by implementing the Partitioner interface.

To limit the number of topic subscriptions, by customizing the consumer group.

To increase message encryption, by altering the default serializer.

Overall explanation
Implementing a custom partitioner allows messages to be distributed according to specific business logic, enhancing the logical partitioning of data across the Kafka cluster.

Domain
Kafka Producers
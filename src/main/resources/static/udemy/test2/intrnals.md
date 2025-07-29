Besides server.properties, name another important Kafka configuration file.

kafka-system.properties

Your answer is correct
zookeeper.properties

broker.properties

client.properties

Overall explanation
The zookeeper.properties file is crucial as it contains configuration details specifically for ZooKeeper, which is integral for Kafka's cluster coordination.

Domain
Kafka Configuration

---


Which of the following roles does ZooKeeper play in a Kafka environment?

Your answer is correct
Managing and coordinating Kafka brokers

Storing large data files

Data serialization and deserialization

Executing stream processing

Overall explanation
ZooKeeper is critical in managing the state of Kafka clusters, including maintaining information about brokers, topics, and partitions.

Domain
ZooKeeper
Beta

---

How do you configure Kafka's connection to ZooKeeper?

Set the zookeeper.url property in the kafka-config.sh script.

Your answer is incorrect
Change the connection.string value in the zookeeper.properties file.

Update the zookeeper.session.timeout.ms in the consumer.properties file.

Correct answer
Modify the zookeeper.connect string in the server.properties file.

Overall explanation
Kafka’s connection to ZooKeeper is configured via the zookeeper.connect property in the server.properties file, where you specify the host and port of the ZooKeeper service.

Domain
Kafka Configuration
Beta

---


Which timestamps are maintained within Kafka messages?

AppendTime

Correct selection
CreateTime

Your selection is incorrect
ReadTime

Correct selection
LogAppendTime

Overall explanation
CreateTime is set by the producer when the message is created, reflecting the production time. LogAppendTime is set by the broker when the message is appended to the log, used for operations like log retention and message ordering.

Domain
Kafka Core Concepts

---


How does Kafka's performance benefit from sequential disk I/O?

Your answer is correct
Through reduced disk seek time by writing and reading messages in batches

Increasing the amount of memory used

Reducing the size of messages stored

By enabling faster random access to disk

Overall explanation
Kafka optimizes performance through sequential disk I/O, which minimizes disk seek times and enables high throughput by efficiently writing and reading messages in batches.

Domain
Kafka Core Concepts

---


What are the standard ports used by ZooKeeper?

(Select all that apply)

8080

Correct selection
3888

Your selection is correct
2181

Correct selection
2888

Overall explanation
ZooKeeper uses three primary ports for its operations:

2181: This is the client port used for handling client connections to the ZooKeeper server.

2888: Known as the peer port, this is used for communication between ZooKeeper servers within a quorum.

3888: The leader port, used specifically for leader election among ZooKeeper servers. This port facilitates the coordination necessary to determine which node will act as the leader in a quorum. The port 8080 is not a standard ZooKeeper port.

Domain
ZooKeeper

---


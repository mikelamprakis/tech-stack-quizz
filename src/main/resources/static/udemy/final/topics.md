You are managing a Kafka cluster that handles a growing volume of transaction data. To accommodate the increased data load and improve consumer performance, you decide to adjust the number of partitions for an existing topic. What changes can you make to the number of partitions, and what limitations should you be aware of?

You cannot change the number of partitions for an existing topic once it has been set.

Your answer is correct
You can increase the number of partitions to scale up and accommodate more data or consumers, but you cannot decrease the number of partitions once set.

You can increase the number of partitions, and if needed, decrease them later to optimize performance.

You can decrease the number of partitions to consolidate data and improve performance but cannot increase them.

Overall explanation
In Kafka, you can increase the number of partitions for an existing topic to scale up and accommodate more data or consumers. However, you cannot decrease the number of partitions once set. This limitation ensures data integrity and consistency across the distributed system, as reducing partitions could disrupt data placement and consumer assignments.

Domain
Kafka Core Concepts


---


How does Kafka implement log segment purging?

Using a centralized management system that controls log retention.

Automatically deleting segments older than a specified age without any checks.

Your answer is correct
Through a background thread that checks and enforces retention policies.

By manually deleting old segments via administrator intervention.

Overall explanation
Kafka utilizes a background thread to periodically check log segments against the retention policy, deleting or compacting them as necessary based on topic configuration.

Domain
Kafka Core Concepts


---


What are the default settings for Kafka's log cleanup policies?

(Select all that apply)

Correct selection
log.cleanup.policy=compact deletes records only after the active log segment is committed.

Correct selection
Data is deleted after a week or when the log reaches a specified maximum size.

Your selection is correct
log.cleanup.policy=delete is the default setting for user topics.

log.cleanup.policy=compact is used by default for all Kafka topics.

Overall explanation
In Kafka, the default log cleanup policy for user topics is log.cleanup.policy=delete, which involves deleting data either based on age—typically after a week—or when the log reaches a maximum size, which is by default infinite or -1. The log.cleanup.policy=compact, which is specifically used by default for the __consumer_offsets topic, focuses on compacting logs by removing older duplicate keys only after the active log segment is committed, allowing for infinite retention of unique keys and ensuring more compact storage over time. Option C is incorrect as log.cleanup.policy=compact is not the default for all topics but specifically for system topics like __consumer_offsets.

Domain
Kafka Core Concepts

---


A company uses Kafka for state restoration and needs to ensure it retains at least the last known value for each key. What feature should they use?

Log retention by time

Your answer is correct
Log compaction

MirrorMaker for cross-cluster replication

Transactional logging

Overall explanation
Log compaction in Kafka retains at least the last known value for each key within a partition log, which is crucial for applications that need to restore state after a failure or catch up without reprocessing the entire log.

Domain
Kafka Core Concepts
Beta

---


You are responsible for managing a Kafka cluster and need to ensure that log data does not consume excessive storage space. How does Kafka manage log retention, and what configuration options do you have to control it?

Kafka uses external scripts to manage log retention based on user-defined conditions.

Your answer is correct
Kafka's log retention is managed through policies based on time (log.retention.hours), size (log.retention.bytes), or both.

Kafka automatically deletes old data without any configurable policies.

Kafka's log retention is managed through policies based on message priority.

Overall explanation
Kafka manages log retention through configurable policies based on time (log.retention.hours), size (log.retention.bytes), or both. These policies allow administrators to control how long data is retained and when old data should be deleted to free up storage space, ensuring efficient use of resources and preventing storage overflow.

Domain
Kafka Core Concepts


---


What feature allows Kafka to retain large amounts of data for a configurable period?

Your answer is correct
Disk-based retention

In-memory storage

Log compaction

Transactional support

Overall explanation
Kafka’s disk-based retention feature allows it to store substantial amounts of data for configurable periods, enabling data re-reading or replay as necessary.

Domain
Kafka Core Concepts

---

How does the log.segment.bytes setting affect Kafka's storage layer?

It limits the total storage used by all logs on a broker.

It deletes old segments to free up space.

It compresses log segments to save disk space.

Your answer is correct
It determines the maximum size of a log segment before rolling over to a new one.

Overall explanation
The log.segment.bytes configuration sets the maximum size of a single log segment file, influencing how often new segments are started and how log cleanup is handled.

Domain
Kafka Core Concepts
Beta

---


You are considering increasing the number of partitions for an existing Kafka topic to improve throughput. What impact will this have on message distribution and consumer logic?

Correct answer
Increasing the number of partitions will change the hashing mechanism for message keys, which can disrupt the guarantee that the same keys always go to the same partition.

Your answer is incorrect
Increasing the number of partitions will have no impact on existing message distribution or consumer logic.

Increasing the number of partitions will automatically rebalance the consumer group, ensuring that each consumer receives an equal number of partitions.

Increasing the number of partitions will redistribute existing messages across the new partitions, ensuring balanced load distribution.

Overall explanation
Increasing the number of partitions in a Kafka topic changes the hashing mechanism for message keys, which can disrupt the existing guarantee that the same keys always go to the same partition. Because Kafka logs are immutable, previously stored messages remain in their original partitions and are not redistributed or reshuffled. This can affect consumer logic that relies on key-based ordering within a single partition.

Domain
Kafka Core Concepts
Beta

---


A stateful application needs to restore its state using Kafka and requires access to at least the last known value for each key. Which Kafka feature should be utilized?

Your answer is correct
Log compaction.

Transactional logs.

Retention policies.

Log cleaning.

Overall explanation
Log compaction in Kafka retains at least the last known value for each key within a partition log, making it ideal for applications that need to restore state after a failure or when catching up.

Domain
Kafka Core Concepts

---


How does Kafka manage log files to ensure efficient storage and data availability?

Your answer is correct
By rotating log files based on time or size

By deleting old log files based on size

By storing log files in a distributed manner

By compressing all log files

Overall explanation
Kafka manages log files by segmenting them based on time or size, which helps in managing storage effectively while ensuring data is available when needed.

Domain
Kafka Core Concepts
Beta

---


How does Kafka store data on disk?

In a distributed database system separate from Kafka brokers

Using an in-memory structure that flushes to disk periodically

As a continuous stream of bytes without any segmentation

Your answer is correct
As a series of log files divided into segments

Overall explanation
Kafka organizes data on disk into log files for each topic partition, which are further divided into manageable segments. This method enhances data management and access efficiency.

Domain
Kafka Core Concepts

---

How are messages stored in Kafka?

In a non-relational database

Your answer is correct
In topics divided into partitions

As blocks in a filesystem

In key-value pairs in memory

Overall explanation
Kafka stores messages in topics, and each topic is divided into partitions. Each partition is an ordered, immutable sequence of messages that is continually appended to.

Domain
Kafka Core Concepts
Beta


---


Which of the following steps are required to immediately delete a topic in Kafka and ensure all logs are removed?

(Select all that apply)

Correct selection
Stop Kafka before manually removing the topic data to ensure all logs are deleted.

Correct selection
Manually remove the topic data from the /brokers/topics/<topic_name> directory in ZooKeeper using the rmr command.

Use the kafka-delete-logs command to remove all log files associated with the topic.

Your selection is correct
Use the kafka-topics command with the delete option to mark the topic for deletion.

Overall explanation
To immediately delete a topic in Kafka and ensure all logs are removed, you should first use the kafka-topics command with the delete option to mark the topic for deletion. However, this alone does not instantly remove the logs. To ensure immediate removal, you must stop Kafka and then manually remove the topic data from the /brokers/topics/<topic_name> directory in ZooKeeper using the rmr command. There is no kafka-delete-logs command in Kafka.

Domain
Kafka Setup
Beta

---





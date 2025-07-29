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



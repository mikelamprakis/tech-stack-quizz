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



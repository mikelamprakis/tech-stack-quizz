Sure! Here are your questions rewritten using the exact format you provided:

---

## Question 1

Which of the following is stored in the Kafka `__consumer_offsets` topic? (Select two)

* A. The latest committed offset for each consumer group
* B. The list of consumers in each consumer group
* C. The mapping of partitions to consumer groups
* D. The last produced message for each topic partition
* E. The earliest committed offset for each consumer group

<details><summary>Response:</summary>

**Answer:** A, C

**Explanation:**
The `__consumer_offsets` topic stores the **latest committed offsets** (A) and the **partition-to-consumer-group mapping** (C). This allows Kafka to manage consumer group progress.

* B is managed by the group coordinator, not stored in the topic.
* D is stored in the actual topic partitions.
* E is incorrect because only the **latest** committed offset is retained.

</details>

---

## Question 2

There are two consumers C1 and C2 belonging to the same group G subscribed to topics T1, T2, and T3. Each topic has 4 partitions. Assuming all partitions have data, how many partitions will each consumer be assigned with the Range Assignor?

* A. C1: 6 partitions, C2: 6 partitions
* B. C1: 4 partitions, C2: 8 partitions
* C. C1: 2 partitions from each topic, C2: 2 partitions from each topic
* D. C1: 1 partition from each topic, C2: 3 partitions from each topic

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**
With the **Range Assignor**, partitions are assigned topic-by-topic in contiguous blocks. For each topic with 4 partitions and 2 consumers, each consumer gets 2 partitions.
Since there are 3 topics, each consumer gets `2 * 3 = 6` partitions.

</details>

---

## Question 3

There are four consumers C1, C2, C3, C4 belonging to the same group G subscribed to two topics T1 and T2. T1 has 3 partitions and T2 has 2 partitions. With the Round Robin Assignor, which consumer(s) will be assigned partition 2 from topic T1?

* A. C1
* B. C2
* C. C3
* D. C4

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**
The **Round Robin Assignor** assigns partitions one-by-one to consumers in order. With 5 partitions and 4 consumers, the assignment is:

* C1: T1-0, T2-1
* C2: T1-1, T2-0
* C3: T1-2
* C4: (no partitions)
  Thus, **C3** gets partition 2 from topic T1.

</details>

---

## Question 4

There are three consumers C1, C2, C3 belonging to the same group G subscribed to a topic T. The topic has 10 partitions. If the Sticky Assignor is used, and C1 leaves the group, how will the partitions be rebalanced?

* A. All partitions will be reassigned evenly among C2 and C3
* B. C2 and C3 will retain their existing partitions, and the partitions from C1 will be reassigned to either C2 or C3
* C. All partitions will be reassigned randomly to C2 and C3
* D. C2 and C3 will retain their existing partitions, and the partitions from C1 will not be reassigned

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**
The **Sticky Assignor** aims to preserve existing assignments as much as possible. When C1 leaves, its partitions are redistributed **with minimal disruption** to C2 and C3's current assignments.
This minimizes partition movement and keeps consumption stable.

</details>

---

## Question 5

A Kafka Streams application tries to consume from an input topic partition. It receives an 'Offset Out Of Range' error from the broker. How should the application handle this?

* A. Reset the consumer offset to the earliest offset and retry
* B. Reset the consumer offset to the latest offset and retry
* C. Trigger a shutdown of the Streams application
* D. Ignore the error and continue processing other partitions

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**
An **'Offset Out Of Range'** error means the application is trying to read from an offset that no longer exists (usually due to retention limits).
The correct action is to reset the offset to the **earliest** and retry.

* B would skip over data.
* C is an overreaction.
* D causes loss of data for the affected partition.

</details>


Here is your adjusted version using the specified template for Questions 6–10:

---

## Question 6

You are designing a Kafka consumer application that will consume messages from a topic. The messages in the topic are in JSON format. Which of the following properties should you set in the consumer configuration?

* A. `key.deserializer=JsonDeserializer`
* B. `value.deserializer=JsonDeserializer`
* C. `key.deserializer=StringDeserializer`
* D. `value.deserializer=StringDeserializer`

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**
Since the Kafka topic contains JSON messages, the consumer must use a deserializer capable of converting JSON into Java objects. This means the value deserializer should be set to `JsonDeserializer`. The key format isn't specified, so we cannot assume it is JSON—thus, we only set the deserializer for the value.

</details>

---

## Question 7

A consumer wants to read messages from a specific partition of a topic. Which of the following methods should be used?

* A. `KafkaConsumer.subscribe(String topic, int partition)`
* B. `KafkaConsumer.assign(Collection<TopicPartition> partitions)`
* C. `KafkaConsumer.subscribe(Collection<TopicPartition> partitions)`
* D. `KafkaConsumer.assign(String topic, int partition)`

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**
To read from a specific partition, a Kafka consumer should call `assign(Collection<TopicPartition>)`. This bypasses Kafka’s default partition assignment and lets you manually specify exactly which partition(s) the consumer should read from.

</details>

---

## Question 8

What happens when a consumer is assigned a partition that does not exist in the Kafka cluster?

* A. The consumer will ignore the non-existent partition and continue processing other assigned partitions
* B. The consumer will throw an exception and stop processing
* C. The consumer will create the partition automatically
* D. The consumer will wait until the partition is created

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**
If a consumer is assigned to a non-existent partition, it will throw an exception like `UnknownTopicOrPartitionException` and stop processing. Kafka does not support dynamic creation of partitions by the consumer.

</details>

---

## Question 9

Can a consumer dynamically change the partitions it is assigned to without stopping and restarting?

* A. Yes, by calling `KafkaConsumer.subscribe()` with a new set of topics
* B. Yes, by calling `KafkaConsumer.assign()` with a new set of partitions
* C. No, partition assignment can only be changed when the consumer is first started
* D. No, partition assignment is fixed for the entire lifecycle of the consumer

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**
Calling `assign()` with a new set of `TopicPartition` objects allows a consumer to dynamically change its partition assignments at runtime without restarting.

</details>

---

## Question 10

A consumer is part of a consumer group and is currently processing messages. If the consumer crashes and is restarted, what will happen?

* A. The consumer will resume processing from the last committed offset
* B. The consumer will start processing from the earliest available offset
* C. The consumer will start processing from the latest available offset
* D. The consumer will be assigned a new set of partitions

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**
When a consumer crashes and restarts, it rejoins the group and resumes from the last committed offset. This ensures no message loss or duplication as long as offsets are committed regularly.

</details>


Sure! Here are the adjusted questions using your provided format:

---

## Question 11

What happens when a new consumer joins an existing consumer group?

* A. The new consumer will start consuming from the earliest available offset for all partitions
* B. The new consumer will start consuming from the latest available offset for all partitions
* C. The new consumer will be assigned a subset of partitions and start consuming from the last committed offset for each partition
* D. The new consumer will wait until the next rebalance before starting to consume

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**
When a new consumer joins an existing consumer group, Kafka triggers a rebalance to redistribute the partitions among all consumers, including the new one. Each consumer is then assigned a subset of the topic partitions and starts consuming from the last committed offset.
Unless configured otherwise (e.g., with `auto.offset.reset`), the consumer resumes from where the group left off.

</details>

---

## Question 12

What is the purpose of the `group.id` property in a Kafka consumer configuration?

* A. To specify the ID of the consumer within a consumer group
* B. To specify the ID of the consumer group the consumer belongs to
* C. To specify the ID of the Kafka cluster the consumer connects to
* D. To specify the ID of the partitions the consumer should read from

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**
The `group.id` identifies the consumer group the consumer belongs to. All consumers with the same `group.id` coordinate to consume partitions of a topic collectively, ensuring each partition is only processed by one consumer within the group.

</details>

---

## Question 13

What is the default behavior of the `auto.offset.reset` configuration in Kafka consumers?

* A. It starts consuming from the earliest offset if no committed offset is found
* B. It starts consuming from the latest offset if no committed offset is found
* C. It throws an exception if no committed offset is found
* D. It waits for a committed offset to be available before starting consumption

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**
By default, `auto.offset.reset=latest`, which means the consumer will begin consuming from the latest offset if no committed offset is found. This avoids reading historical data. To start from the beginning instead, set `auto.offset.reset=earliest`.

</details>

---

## Question 14

What happens when a Kafka consumer with `enable.auto.commit=false` calls the `commitSync()` method?

* A. The consumer commits the offsets of the messages it has processed so far
* B. The consumer commits the offsets of the messages it has fetched but not yet processed
* C. The consumer does not commit any offsets and throws an exception
* D. The consumer waits for the next batch of messages to be processed before committing offsets

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**
With `enable.auto.commit=false`, the consumer is responsible for committing offsets manually. Calling `commitSync()` will block until the offsets of the records already processed are committed to Kafka.

</details>

---

## Question 15

What is the purpose of the `isolation.level` configuration in Kafka consumers?

* A. To control the visibility of transactional messages
* B. To specify the maximum number of messages to be read in a single batch
* C. To determine the behavior when a partition is reassigned to another consumer in the group
* D. To set the level of consistency for reading messages from a partition

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**
The `isolation.level` setting controls whether a consumer reads all messages (`read_uncommitted`) or only committed ones (`read_committed`). It's especially relevant when consuming messages produced in transactions.

</details>

---

## Question 16

What happens if you try to call `poll()` on a KafkaConsumer from multiple threads simultaneously?

* A. The consumer will automatically coordinate the threads to process messages in parallel
* B. The consumer will throw a ConcurrentModificationException
* C. The behavior is undefined and may lead to unexpected results or errors
* D. The consumer will process messages sequentially, with each thread taking turns

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**
KafkaConsumer is not thread-safe. Calling `poll()` concurrently from multiple threads leads to undefined behavior. Always restrict a consumer instance to a single thread or use one consumer per thread.

</details>


Here are the adjusted questions using your provided template:

---

## Question 17

What is the recommended approach to process messages concurrently using the KafkaConsumer?

* A. Create a single KafkaConsumer instance and share it among multiple threads
* B. Create multiple KafkaConsumer instances, each running in its own thread
* C. Use a thread pool to process messages from a single KafkaConsumer instance
* D. Use a lock or synchronization mechanism to coordinate access to a shared KafkaConsumer instance

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**
The KafkaConsumer is not thread-safe. The recommended approach to achieve concurrent processing is to create multiple KafkaConsumer instances, each running in its own thread. This allows for safe and parallel consumption from different partitions without introducing concurrency bugs. Kafka’s group management ensures each partition is assigned to a single consumer in the group.

</details>

---

## Question 18

How does Kafka ensure that messages are processed in a balanced way when using multiple consumer instances in a consumer group?

* A. Kafka assigns an equal number of messages to each consumer instance
* B. Kafka assigns partitions to consumer instances in a round-robin fashion
* C. Kafka dynamically adjusts the assignment of partitions based on consumer load
* D. Kafka relies on ZooKeeper to distribute messages evenly among consumer instances

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**
Kafka ensures balanced processing by assigning partitions to consumers using a round-robin strategy (or range strategy by default, depending on configuration). Each partition is assigned to only one consumer instance in a group at a time. This results in an even distribution of partitions across consumers, assuming an even number of partitions and consumers.

</details>

---

## Question 19

What is the primary benefit of Kafka's zero-copy optimization when sending data from producers to consumers?

* A. It reduces the memory overhead by avoiding data duplication in memory
* B. It minimizes the latency by eliminating the need for data serialization and deserialization
* C. It improves the security by encrypting the data during transmission
* D. It increases the parallelism by leveraging multiple CPU cores for data transfer

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**
Kafka uses zero-copy optimization to reduce memory overhead by avoiding unnecessary data copying. It leverages the operating system’s `sendfile` syscall to move data directly from the page cache to the network socket without copying it into user-space memory, leading to better performance and throughput.

</details>

---

## Question 20

What is the purpose of the `isolation.level` setting in the Kafka consumer configuration?

* A. To specify the maximum number of records to fetch in a single request
* B. To control the visibility of transactional messages
* C. To determine the behavior of the consumer when it encounters an invalid offset
* D. To set the maximum amount of time the consumer will wait for new messages

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**
The `isolation.level` controls whether the consumer reads only committed transactional messages or all messages. It can be set to:

* `read_uncommitted` (default): read all messages including uncommitted or aborted ones.
* `read_committed`: only read messages that are part of committed transactions, ensuring stronger consistency guarantees.

</details>

---

## Question 21

What is the default value of the `isolation.level` setting in the Kafka consumer configuration?

* A. `read_uncommitted`
* B. `read_committed`
* C. `transactional`
* D. `none`

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**
The default value of `isolation.level` is `read_uncommitted`. This means that, unless explicitly configured otherwise, a Kafka consumer will read all messages including those that may be part of aborted transactions.

</details>

---

## Question 22

What happens when a consumer with `isolation.level=read_committed` encounters a message that is part of an ongoing transaction?

* A. The consumer will read the message immediately
* B. The consumer will wait until the transaction is committed before reading the message
* C. The consumer will skip the message and move on to the next one
* D. The consumer will throw an exception and stop consuming

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**
When using `read_committed`, the consumer will wait until the transaction is completed (committed) before making any message from that transaction visible. This ensures transactional consistency and avoids consuming messages that might later be rolled back.

</details>


Here are the adjusted questions in the format you provided:

---

## Question 23

What is the purpose of the `max.poll.records` setting in the Kafka consumer configuration?

* A. To specify the maximum number of records to return in a single poll
* B. To control the maximum amount of data the consumer can receive per second
* C. To set the maximum number of partitions the consumer can subscribe to
* D. To determine the maximum number of consumers allowed in a consumer group

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**
The `max.poll.records` setting specifies the maximum number of records returned in a single call to `poll()`. This allows the consumer to limit the number of records fetched and processed in one batch, helping to control memory usage and processing latency. The default is 500.

</details>

---

## Question 24

How does the `max.poll.interval.ms` setting affect the behavior of a Kafka consumer?

* A. It specifies the maximum amount of time the consumer can wait before polling for new records
* B. It sets the maximum interval between two consecutive polls before the consumer is considered dead
* C. It determines the maximum time allowed for message processing before committing offsets
* D. It controls the maximum number of records the consumer can poll in a single request

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**
`max.poll.interval.ms` sets the maximum time between two calls to `poll()`. If this interval is exceeded, the consumer is considered unresponsive and removed from the group. This helps Kafka detect and recover from stuck or slow consumers.

</details>

---

## Question 25

What happens when a Kafka consumer is marked as dead due to exceeding the `max.poll.interval.ms` interval?

* A. The consumer is automatically rebalanced, and its partitions are reassigned to other consumers in the group
* B. The consumer receives an exception and must manually rejoin the consumer group
* C. The consumer's offset commits are rolled back, and it starts consuming from the beginning of the assigned partitions
* D. The consumer is permanently removed from the consumer group and cannot rejoin

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**
If a consumer exceeds the `max.poll.interval.ms` threshold, Kafka triggers a rebalance. The partitions it was responsible for are reassigned to other consumers. The dead consumer can later rejoin the group and receive new assignments.

</details>

---

## Question 26

What triggers a partition rebalance in a Kafka consumer group?

* A. Adding a new topic to the Kafka cluster
* B. Changing the replication factor of a topic
* C. Adding a new consumer to the consumer group
* D. Modifying the consumer group ID

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**
A rebalance is triggered when the composition of the consumer group changes — for example, when a new consumer joins or an existing one leaves. Kafka redistributes partitions among the current consumers to maintain balance.

</details>

---

## Question 27

What happens to the partition assignments during a consumer group rebalance?

* A. Partitions are evenly distributed among the remaining consumers
* B. Partitions are assigned to the consumers based on the consumer group ID
* C. Partitions are randomly assigned to the consumers
* D. Partitions are assigned to the consumers based on the topic name

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**
During a rebalance, Kafka redistributes partitions among all active consumers. Depending on the partition assignment strategy (e.g., range or round-robin), it aims to balance the load across consumers as evenly as possible.

</details>

---

## Question 31

How can you minimize the impact of consumer group rebalances in a Kafka application?

* A. Increase the session timeout value for consumers
* B. Reduce the number of partitions for the consumed topics
* C. Implement a custom partition assignment strategy
* D. Use static group membership for consumers

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**
Using static group membership (by setting `group.instance.id`) helps consumers retain their partition assignments during restarts, which avoids triggering a full group rebalance and reduces downtime or overhead.

</details>

---

## Question 32

When a Kafka consumer wants to read data from a specific partition, what information does it need to provide to the Kafka broker?

* A. The topic name and the consumer group ID
* B. The topic name and the offset to start reading from
* C. The topic name, partition number, and offset to start reading from
* D. The topic name, partition number, and consumer group ID

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**
To read from a specific partition, the consumer must specify the topic, partition number, and the offset. The group ID is only used in automatic assignment mode, not when reading a specific partition explicitly.

</details>

---

## Question 33

How does a Kafka consumer determine which broker to connect to when reading data from a specific partition?

* A. The consumer connects to any available broker and requests the leader for the specific partition
* B. The consumer connects to the Zookeeper ensemble to determine the leader for the specific partition
* C. The consumer uses a round-robin algorithm to select a broker to connect to
* D. The consumer connects to all brokers in the cluster simultaneously

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**
The consumer sends a metadata request to any broker to get information about the topic and partition. This includes which broker is the leader for that partition. It then connects directly to the leader to fetch data.

</details>

Here's your adjusted set of questions using the provided template:

---

## Question 34

What happens if a Kafka consumer requests to read from a partition that does not exist in the specified topic?

* A. The Kafka broker will automatically create the partition and start serving data
* B. The consumer will receive an empty response, indicating that the partition does not exist
* C. The consumer will receive an error message, indicating that the requested partition does not exist
* D. The consumer will be assigned a different, existing partition to read from

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**
If a Kafka consumer tries to read from a non-existent partition, the broker responds with an error message such as `UNKNOWN_TOPIC_OR_PARTITION`. Kafka does **not** auto-create partitions in this scenario, nor does it reassign consumers to different partitions. The application must ensure that it references valid partitions when issuing read requests.

</details>

---

## Question 35

When a Kafka consumer commits offsets, what information is included in the commit request?

* A. The consumer group ID and the last processed offset for each partition
* B. The consumer group ID and the next offset to be processed for each partition
* C. The consumer ID and the last processed offset for each partition
* D. The consumer ID and the next offset to be processed for each partition

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**
Offset commits in Kafka include the **consumer group ID** and the **last successfully processed offset** for each partition. This helps Kafka track the position of consumption per group. The consumer ID is not included in the commit data because offset tracking is done at the group level, not per individual consumer.

</details>

---

## Question 36

What happens if a Kafka consumer commits an offset for a partition and then crashes before processing the next message?

* A. The consumer will resume processing from the last committed offset when it restarts
* B. The consumer will resume processing from the next message after the last committed offset when it restarts
* C. The consumer will start processing from the beginning of the partition when it restarts
* D. The consumer will be assigned a different partition to process when it restarts

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**
If a consumer crashes after committing an offset but before processing the next message, it will resume **from the last committed offset**. This may result in reprocessing the same message, but it ensures no data loss. It's a trade-off between **at-least-once** delivery and performance.

</details>

---

## Question 37

What is the purpose of the `enable.auto.commit` configuration property in Kafka consumers?

* A. To automatically commit offsets at a fixed interval
* B. To automatically commit offsets after each message is processed
* C. To enable or disable automatic offset commits
* D. To specify the maximum number of offsets to commit in a single request

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**
The `enable.auto.commit` property **controls whether offset commits are automatic**. When `true`, offsets are committed periodically based on the `auto.commit.interval.ms` setting. When `false`, the application must commit offsets manually using `commitSync()` or `commitAsync()`.

</details>

---

## Question 38

In a topic with a replication factor of 3 and `min.insync.replicas` set to 2, what happens when a consumer sends a read request to a partition with only one in-sync replica?

* A. The consumer receives the requested data from the in-sync replica
* B. The consumer request fails with a `NotEnoughReplicasException`
* C. The consumer receives an empty response
* D. The consumer request remains pending until another replica becomes in-sync

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**
The `min.insync.replicas` setting applies only to **write operations**, not reads. A consumer can read from any in-sync replica, even if only one remains. So, even with just one ISR, the consumer will still receive the requested data.

</details>


Here’s your entire set of Kafka questions reformatted in the standard template style you requested:

---

## Question 0

How can you gracefully make a Kafka consumer to stop immediately polling data from Kafka and gracefully shut down a consumer application?

* A. Kill the consumer thread
* B. Call the consumer.poll() in another thread
* C. Call the consumer.wakeUp() and catch a WakeUpException

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**
You should use `consumer.wakeUp()` to interrupt a long-running `poll()` call, and catch `WakeUpException` to close the consumer gracefully.
See [this explanation](https://stackoverflow.com/a/37748336/3019499) for more details.

</details>

---

## Question 1

A consumer starts and has `auto.offset.reset=none`, and the topic partition currently has data for offsets going from 45 to 2311. The consumer group has committed the offset 10 for the topic before. Where will the consumer read from?

* A. Offset 10
* B. Offset 2311
* C. Offset 45
* D. It will crash

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**
Since `auto.offset.reset=none`, and the committed offset (10) has been deleted (log start offset is now 45), the consumer will crash — it has no valid starting point.

</details>



---

Sure! Here's your set of questions adjusted into the requested format:

---

## Question 2

A topic has three replicas and you set `min.insync.replicas=2`. If two out of three replicas are not available, what happens when a consume request is sent to broker?

* A. Data will be returned from the remaining in-sync replica
* B. An empty message will be returned
* C. A new leader of the partition will be elected

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**
`min.insync.replicas` affects **producers** and controls write availability, not consumers. Consumers can still read from any available in-sync replica. So if only one replica is available and in-sync, the consumer will receive data from it.

</details>

---

## Question 3

When using the Confluent Kafka Distribution, where does the schema registry reside?

* A. As an in-memory plugin on Kafka broker
* B. As an in-memory plugin on Kafka Connect worker
* C. As a separate JVM component

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**
The schema registry is a standalone REST service that runs as a separate JVM process, not embedded inside Kafka brokers or Kafka Connect workers.

</details>

---

## Question 4

What is returned by a `producer.send()` call in the Java API?

* A. A boolean
* B. Unit
* C. Future
* D. Future object

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**
`producer.send()` returns a `Future<RecordMetadata>` object, which can be used to handle the result of the send operation synchronously or asynchronously.

</details>

---

## Question 5

A Zookeeper ensemble contains 5 servers. What is the maximum number of servers that can go missing and the ensemble still run?

* A. 1
* B. 2
* C. 3
* D. 4

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**
Zookeeper requires a majority (quorum) to function. For 5 servers, quorum is 3, so up to 2 servers can fail and the ensemble will still work.

</details>

---

## Question 6

To allow consumers in a group to resume at the previously committed offset, I need to set the proper value for...

* A. value.deserializer
* B. enable.auto.commit
* C. group.id
* D. auto.offset.reset

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**
Kafka tracks offsets per consumer group using the `group.id`. Without setting `group.id`, Kafka cannot associate committed offsets to the consumer group.

</details>

---

## Question 7

How does a consumer commit offsets in Kafka?

* A. It directly commits offsets in Zookeeper
* B. It directly sends the message to `__consumer_offsets`
* C. It interacts with the group coordinator broker

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**
Consumers commit offsets by communicating with the group coordinator broker, which manages offset storage in the internal `__consumer_offsets` topic.

</details>

---

## Question 8

To read data from a topic, the following configuration is needed for the consumers:

* A. any broker to connect, and the topic
* B. all brokers, list of topic name and partition
* C. any broker, list of topic name and partition

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**
A consumer can connect to any broker to fetch metadata and receive the topic and partition info. The consumer needs to know the list of topics and partitions to consume from.

</details>

---

## Question 9

A consumer wants to read messages from partitions 0 and 1 of a topic `topic1` using both `subscribe()` and `assign()`. What happens?

* A. This works fine
* B. Throws `IllegalStateException`

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**
You must use either `subscribe()` (for automatic partition assignment) or `assign()` (for manual assignment), not both at the same time.

</details>

---

## Question 10

A consumer sends a request to commit offset 2000. Due to a network issue, the broker doesn’t receive it. The consumer continues and commits offset 3000. What should you do?

* A. Nothing
* B. Add a new consumer
* C. Restart the consumer

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**
The commit of offset 3000 supersedes the earlier 2000 commit. Since the latest offset is committed, no action is needed.

</details>

---

## Question 11

There are 3 producers writing to a topic with 5 partitions. There are 10 consumers in the same consumer group. How many consumers will remain idle?

* A. 3
* B. 5
* C. 10
* D. None

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**
Since there are only 5 partitions, only 5 consumers can be assigned partitions. The other 5 consumers will be idle.

</details>

---

## Question 12

In Kafka consumer metrics, the fetch-rate is high but each fetch is small. How to improve throughput?

* A. Increase `fetch.min.bytes`
* B. Increase `fetch.max.bytes`
* C. Decrease `fetch.max.bytes`
* D. Decrease `fetch.min.bytes`

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**
Increasing `fetch.min.bytes` causes the broker to wait for more data before responding, increasing batch sizes and improving throughput.

</details>

---

## Question 13

A consumer does complex ML processing that takes approximately 6 minutes per record batch and enters rebalances. What to do?

* A. Increase `max.poll.interval.ms` to 600000
* B. Increase `session.timeout.ms` to 600000
* C. Increase `heartbeat.interval.ms` to 600000

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**
`max.poll.interval.ms` controls how long a consumer can go without calling `.poll()` before being considered dead and triggering a rebalance. Increasing it allows longer processing times.

</details>

---

## Question 14

Which actions will trigger a partition rebalance for a consumer group? (Select 3)

* A. Add the broker to the cluster
* B. Remove the broker from the cluster
* C. Add a new consumer to the group
* D. Increase partitions of the topic
* E. A consumer in the group shuts down

<details><summary>Response:</summary>

**Answer:** C, D, E

**Explanation:**
Rebalances occur when consumers join/leave the group or partitions change. Adding/removing brokers does not cause consumer group rebalances.

</details>

---

## Question 15

```java
while (true) {
    ConsumerRecords<String, String> records = consumer.poll(100);
    try {
        consumer.commitSync();
    } catch (CommitFailedException e) {
        log.error("commit failed", e);
    }
    for (ConsumerRecord<String, String> record : records) {
        System.out.printf("topic = %s, partition = %s, offset = %d, key = %s, value = %s\n",
            record.topic(), record.partition(), record.offset(), record.key(), record.value());
    }
}
```

What delivery guarantee does this offer?

* A. At-most once
* B. Exactly once
* C. At-least once

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**
Offsets are committed **before** processing records. If the consumer crashes after committing but before processing, messages will be lost → at-most once delivery.

</details>

---

## Question 16

A producer uses `acks=1` and sends a message to the leader. When will a consumer see this message?

* A. When the High Watermark has advanced
* B. Right away
* C. Never — the produce request will fail

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**
Consumers read only up to the High Watermark, which advances after the message is replicated to all in-sync replicas. `acks=1` means only leader ack, so the High Watermark advances later.

</details>

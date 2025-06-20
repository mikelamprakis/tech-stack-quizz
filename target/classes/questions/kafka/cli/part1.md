## Question 1

How will you find out all the partitions without a leader?

* A. kafka-topic.sh--broker-list localhost:9092 --describe--under-replicated-partitions
* B. kafka-topic.sh--broker-list localhost:2181 --describe--under-replicated-partitions
* C. kafka-topic.sh--zookeeper localhost:2181 --describe--unavailable-partitions
* D. kafka-topic.sh--zookeeper localhost:9092 --describe--unavailable-partitions

<details><summary>Response:</summary> 

**Answer:** C

**Explanation:**
Please note that as of Kafka 2.2, the `--zookeeper` option is deprecated.
You can now use:
`kafka-topics.sh --bootstrap-server localhost:9092 --describe --unavailable-partitions`

</details>

---

## Question 2

Which Kafka CLI should you use to consume from a topic?

* A. kafka-console
* B. kafka-console-consumer
* C. kafka-topic
* D. kfaka-consumer-groups

<details><summary>Response:</summary> 

**Answer:** B

**Explanation:**
Example usage:
`kafka-console-consumer --bootstrap-server 127.0.0.1:9092 --topic test --from-beginning`

</details>

---

## Question 3

The kafka-console-consumer CLI, when used with the default options:

* A. always use the same group id
* B. does not use the group id
* C. uses a random group id

<details><summary>Response:</summary> 

**Answer:** C

**Explanation:**
If a group ID is not specified, the `kafka-console-consumer` generates a random consumer group ID.

</details>


---

## Question 4: Viewing Kafka Topic Configuration


How can you view the current configuration of a Kafka topic?

* A. Use the `kafka-topics.sh --describe` command
* B. Use the `kafka-configs.sh --describe` command
* C. Use the `zookeeper-shell.sh` command to navigate to the topic's configuration znode
* D. Look in the Kafka broker's log files for the topic configuration

<details><summary>Response:</summary> 

**Answer:** B

**Explanation:**
To view a topic’s configuration, use:

```bash
kafka-configs.sh --bootstrap-server <broker> --entity-type topics --entity-name <topic-name> --describe
```

This displays topic-level configurations like `retention.ms`, `cleanup.policy`, `compression.type`, etc.

* **Option A** shows metadata (partition count, leader, etc.), not config.
* **Option C** is outdated and Zookeeper is being phased out.
* **Option D** is incorrect—log files don't store configuration data.

</details>

---

## Question 5: kafka-console-consumer and Consumer Group Behavior


What is the default behavior of the `kafka-console-consumer` when no consumer group is specified?

* A. It joins a random consumer group
* B. It creates a new consumer group with a generated name
* C. It fails with an error indicating that a consumer group must be specified
* D. It consumes messages without joining any consumer group

<details><summary>Response:</summary> 

**Answer:** B

**Explanation:**
When no `--group` is specified, the consumer auto-generates a group name like `console-consumer-<random-id>`, isolating its offset tracking from other consumers.

* **A** is incorrect—it doesn’t join existing groups randomly.
* **C** is false—Kafka CLI tools handle missing groups gracefully.
* **D** is incorrect—it always belongs to a group, even if it’s generated.

</details>

---

## Question 3: Using `--from-beginning`


How does the `kafka-console-consumer` behave when you specify the `--from-beginning` option?

* A. It starts consuming messages from the earliest available offset in the assigned partitions
* B. It starts consuming messages from the latest available offset
* C. It starts consuming messages from a specific offset that you provide
* D. It starts consuming messages from a random offset

<details><summary>Response:</summary> 

**Answer:** A

**Explanation:**
Using `--from-beginning` causes the consumer to seek to the earliest offset and replay all available messages.

* **B** is the default behavior *without* `--from-beginning`.
* **C** is invalid—there’s no option to set a specific offset directly in this command.
* **D** is incorrect—it’s deterministic (earliest), not random.

</details>

---

## Question 4: Multiple Consumers in Same Group


What happens when you run multiple instances of the `kafka-console-consumer` with the same consumer group?

* A. The instances will consume messages independently, each receiving a copy of every message
* B. The instances will collaborate and distribute the partitions among themselves for parallel consumption
* C. The instances will compete for messages, and each message will be consumed by only one instance
* D. The instances will consume messages in a round-robin fashion, with each instance receiving a subset of messages

<details><summary>Response:</summary> 

**Answer:** B

**Explanation:**
In a shared consumer group, Kafka assigns partitions across instances, enabling parallel consumption. Each partition is processed by a single consumer.

* **A** is wrong—each message goes to only one group member.
* **C** is misleading—there’s no message-level contention, just partition assignment.
* **D** incorrectly describes the assignment strategy.

</details>

---

## Question 5: Creating a Topic via CLI


How can you create a topic named `test` with 3 partitions and a replication factor of 2 using the Kafka CLI?

* A. `kafka-topics.sh --create --zookeeper localhost:2181 --topic test --partitions 3 --replication-factor 2`
* B. `kafka-topics.sh --create --bootstrap-server localhost:9092 --topic test --partitions 3 --replication-factor 2`
* C. `kafka-console-producer.sh --broker-list localhost:9092 --topic test --partitions 3 --replication-factor 2`
* D. `kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --partitions 3 --replication-factor 2`

<details><summary>Response:</summary> 

**Answer:** B

**Explanation:**
Correct usage with a broker connection:

```bash
kafka-topics.sh --create --bootstrap-server localhost:9092 --topic test --partitions 3 --replication-factor 2
```

* **A** is deprecated (Zookeeper).
* **C** and **D** are invalid—those scripts don’t support topic creation.

</details>

---

## Question 6: Listing Topics


Which command can you use to list all the topics in a Kafka cluster?

* A. `kafka-topics.sh --list --zookeeper localhost:2181`
* B. `kafka-topics.sh --list --bootstrap-server localhost:9092`
* C. `kafka-console-producer.sh --list --broker-list localhost:9092`
* D. `kafka-console-consumer.sh --list --bootstrap-server localhost:9092`

<details><summary>Response:</summary> 

**Answer:** B

**Explanation:**
The correct modern command is:

```bash
kafka-topics.sh --list --bootstrap-server localhost:9092
```

* **A** uses Zookeeper, which is deprecated.
* **C** and **D** are incorrect—those tools don’t support listing topics.

</details>

---

## Question 7: Describing Topic Configuration

How can you describe the configuration of a topic named `test` using the Kafka CLI?

* A. `kafka-topics.sh --describe --topic test --zookeeper localhost:2181`
* B. `kafka-topics.sh --describe --topic test --bootstrap-server localhost:9092`
* C. `kafka-configs.sh --describe --entity-type topics --entity-name test --zookeeper localhost:2181`
* D. `kafka-configs.sh --describe --entity-type topics --entity-name test --bootstrap-server localhost:9092`

<details><summary>Response:</summary> 

**Answer:** D

**Explanation:**
This command gives you full topic config details:

```bash
kafka-configs.sh --describe --entity-type topics --entity-name test --bootstrap-server localhost:9092
```

* **A** and **C** rely on Zookeeper (deprecated).
* **B** shows metadata (not configs) like partitions and replication.

</details>

---

## Question 21

Which Kafka CLI command lists all topics in the cluster?

* A. kafka-topics.sh --list --bootstrap-server localhost:9092
* B. kafka-console-consumer.sh --list --bootstrap-server localhost:9092
* C. kafka-configs.sh --list --bootstrap-server localhost:9092
* D. kafka-broker-api-versions.sh --list --bootstrap-server localhost:9092

<details><summary>Response:</summary> 

**Answer:** A

**Explanation:**
To list all Kafka topics in a cluster, you use the `kafka-topics.sh` command with the `--list` option and the `--bootstrap-server` to specify the Kafka broker address.

</details>

---

## Question 22

Which command can be used to describe a Kafka topic's configuration?

* A. kafka-configs.sh --describe --entity-type topics --entity-name <topic-name> --bootstrap-server localhost:9092
* B. kafka-topics.sh --describe --topic <topic-name> --bootstrap-server localhost:9092
* C. Both A and B
* D. kafka-console-consumer.sh --describe --topic <topic-name> --bootstrap-server localhost:9092

<details><summary>Response:</summary> 

**Answer:** C

**Explanation:**
You can describe a topic's basic configuration (partitions, replication, etc.) using `kafka-topics.sh --describe`. For more detailed configuration (like retention, cleanup policy), use `kafka-configs.sh --describe`.

</details>

---

## Question 23

What is required for the `kafka-configs.sh` command to successfully alter a topic configuration?

* A. A running Zookeeper instance
* B. A configuration file with all topic details
* C. Use of `--alter`, `--entity-type`, `--entity-name`, and `--add-config` flags
* D. Creating the topic again with new configs

<details><summary>Response:</summary> 

**Answer:** C

**Explanation:**
To alter a topic’s configuration, you must use the `--alter` flag, specify the `--entity-type` (topics), the `--entity-name` (topic name), and the `--add-config` flag with the desired key-value pair.

</details>

---

## Question 24

How can you verify that a Kafka broker is reachable?

* A. kafka-topics.sh --describe --bootstrap-server localhost:9092
* B. kafka-broker-api-versions.sh --bootstrap-server localhost:9092
* C. kafka-consumer-groups.sh --list --bootstrap-server localhost:9092
* D. All of the above

<details><summary>Response:</summary> 

**Answer:** D

**Explanation:**
All these commands interact with the Kafka broker via the `--bootstrap-server` flag. If the broker is reachable, the commands will execute successfully and return data.

</details>

---

## Question 25

How can you find out the partition count for a given topic?

* A. kafka-topics.sh --describe --topic <topic-name> --bootstrap-server localhost:9092
* B. kafka-console-consumer.sh --describe --topic <topic-name> --bootstrap-server localhost:9092
* C. kafka-configs.sh --describe --entity-type topics --entity-name <topic-name> --bootstrap-server localhost:9092
* D. kafka-consumer-groups.sh --describe --topic <topic-name> --bootstrap-server localhost:9092

<details><summary>Response:</summary> 

**Answer:** A

**Explanation:**
To see the number of partitions for a topic, use `kafka-topics.sh --describe`, which shows each partition, leader, replicas, and ISRs for the topic.

</details>

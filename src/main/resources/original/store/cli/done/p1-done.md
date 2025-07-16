## Question 1

```markdown
How will you find out all the partitions without a leader?
```

**Options**

```markdown
- A. kafka-topic.sh--broker-list localhost:9092 --describe--under-replicated-partitions
- B. kafka-topic.sh--broker-list localhost:2181 --describe--under-replicated-partitions
- C. kafka-topic.sh--zookeeper localhost:2181 --describe--unavailable-partitions
- D. kafka-topic.sh--zookeeper localhost:9092 --describe--unavailable-partitions
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
To find unavailable partitions, the correct CLI is:

kafka-topic.sh --zookeeper localhost:2181 --describe --unavailable-partitions

Note: Starting from Kafka 2.2, --zookeeper is deprecated. The preferred way is:

kafka-topics.sh --bootstrap-server localhost:9092 --describe --unavailable-partitions

- A. Incorrect flag and argument for partition info
- B. Same issue as A, and incorrect port for Zookeeper
- C. ✅ Correct usage for unavailable partitions with Zookeeper
- D. Incorrect because port 9092 is for brokers, not Zookeeper
```

</details>

---

## Question 2

```markdown
Which Kafka CLI should you use to consume from a topic?
```

**Options**

```markdown
- A. kafka-console
- B. kafka-console-consumer
- C. kafka-topic
- D. kafka-consumer-groups
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
To consume messages from a Kafka topic, use:

kafka-console-consumer --bootstrap-server 127.0.0.1:9092 --topic test --from-beginning

- A. kafka-console does not exist
- B. ✅ Correct CLI tool for consuming messages
- C. kafka-topic is for topic management, not message consumption
- D. kafka-consumer-groups is for inspecting and managing consumer groups
```

</details>

---

## Question 3

```markdown
The kafka-console-consumer CLI, when used with the default options:
```

**Options**

```markdown
- A. always use the same group id
- B. does not use the group id
- C. uses a random group id
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
If you do not specify a `--group` option when using kafka-console-consumer, it generates a random group ID each time, meaning it won't commit offsets or maintain group state.

- A. Incorrect, unless explicitly defined
- B. Incorrect, a group is always assigned—even if randomly
- C. ✅ Correct — it assigns a random group ID
```

</details>



## Question 4

```markdown
How can you view the current configuration of a Kafka topic?
```

**Options**

```markdown
- A. Use the kafka-topics.sh --describe command
- B. Use the kafka-configs.sh --describe command
- C. Use the zookeeper-shell.sh command to navigate to the topic's configuration znode
- D. Look in the Kafka broker's log files for the topic configuration
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

````markdown
To view the current configuration of a Kafka topic, use:

```bash
kafka-configs.sh --bootstrap-server <broker> --entity-type topics --entity-name <topic-name> --describe
````

* A. Shows metadata (partition, replication, leader) but not config properties.
* B. ✅ Correct tool for viewing topic configuration (e.g. retention.ms, cleanup.policy).
* C. Outdated method using Zookeeper, discouraged in modern Kafka.
* D. Broker logs do not contain topic configuration.

````

</details>

---

## Question 2

```markdown
What is the default behavior of the `kafka-console-consumer` when no consumer group is specified?
````

**Options**

```markdown
- A. It joins a random consumer group
- B. It creates a new consumer group with a generated name
- C. It fails with an error indicating that a consumer group must be specified
- D. It consumes messages without joining any consumer group
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
The console consumer creates a unique group like `console-consumer-<random-id>`.

- A. It doesn't join an existing group at random.
- B. ✅ It creates a new group automatically.
- C. It doesn't error out—fallback behavior is implemented.
- D. Always joins a group, even if generated.
```

</details>

---

## Question 5

```markdown
How does the `kafka-console-consumer` behave when you specify the `--from-beginning` option?
```

**Options**

```markdown
- A. It starts consuming messages from the earliest available offset in the assigned partitions
- B. It starts consuming messages from the latest available offset in the assigned partitions
- C. It starts consuming messages from a specific offset that you provide
- D. It starts consuming messages from a random offset in the assigned partitions
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
`--from-beginning` seeks to the earliest available offset.

- A. ✅ Correct – processes the full topic history.
- B. Default behavior without `--from-beginning`.
- C. `--from-beginning` doesn’t allow specific offsets.
- D. No randomness – it deterministically uses the start.
```

</details>

---

## Question 6

```markdown
What happens when you run multiple instances of the `kafka-console-consumer` with the same consumer group?
```

**Options**

```markdown
- A. The instances will consume messages independently, each receiving a copy of every message
- B. The instances will collaborate and distribute the partitions among themselves for parallel consumption
- C. The instances will compete for messages, and each message will be consumed by only one instance
- D. The instances will consume messages in a round-robin fashion, with each instance receiving a subset of messages
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Kafka distributes partitions to consumers in the same group.

- A. Not true – consumers don’t get duplicate messages.
- B. ✅ Partitions are split among group members.
- C. No competition—Kafka handles partition assignment.
- D. Assignment is by partition, not round-robin messages.
```

</details>

---

## Question 7

```markdown
How can you create a topic named "test" with 3 partitions and a replication factor of 2 using the Kafka CLI?
```

**Options**

```markdown
- A. kafka-topics.sh --create --zookeeper localhost:2181 --topic test --partitions 3 --replication-factor 2
- B. kafka-topics.sh --create --bootstrap-server localhost:9092 --topic test --partitions 3 --replication-factor 2
- C. kafka-console-producer.sh --broker-list localhost:9092 --topic test --partitions 3 --replication-factor 2
- D. kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --partitions 3 --replication-factor 2
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Creating topics uses `kafka-topics.sh` with `--create`.

- A. Uses deprecated Zookeeper option.
- B. ✅ Correct modern syntax with `--bootstrap-server`.
- C. kafka-console-producer.sh is for sending messages.
- D. kafka-console-consumer.sh is for consuming.
```

</details>

---

## Question 8

```markdown
Which command can you use to list all the topics in a Kafka cluster?
```

**Options**

```markdown
- A. kafka-topics.sh --list --zookeeper localhost:2181
- B. kafka-topics.sh --list --bootstrap-server localhost:9092
- C. kafka-console-producer.sh --list --broker-list localhost:9092
- D. kafka-console-consumer.sh --list --bootstrap-server localhost:9092
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Use `kafka-topics.sh` with `--list` and `--bootstrap-server`.

- A. Deprecated Zookeeper usage.
- B. ✅ Correct and modern.
- C. kafka-console-producer doesn’t list topics.
- D. kafka-console-consumer doesn’t list topics.
```

</details>

---

## Question 9

```markdown
How can you describe the configuration of a topic named "test" using the Kafka CLI?
```

**Options**

```markdown
- A. kafka-topics.sh --describe --topic test --zookeeper localhost:2181
- B. kafka-topics.sh --describe --topic test --bootstrap-server localhost:9092
- C. kafka-configs.sh --describe --entity-type topics --entity-name test --zookeeper localhost:2181
- D. kafka-configs.sh --describe --entity-type topics --entity-name test --bootstrap-server localhost:9092
```

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**

```markdown
Use `kafka-configs.sh` for configuration details.

- A. Incorrect tool (`kafka-topics.sh`) and uses Zookeeper.
- B. Same wrong tool, but right server flag.
- C. Correct tool, but deprecated Zookeeper.
- D. ✅ Correct modern syntax for config details.
```

</details>

---

## Question 10

```markdown
Which Kafka CLI command is used to produce messages to a topic?
```

**Options**

```markdown
- A. kafka-console-producer.sh
- B. kafka-console-consumer.sh
- C. kafka-topics.sh
- D. kafka-configs.sh
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
Producing = publishing. Use `kafka-console-producer.sh`.

- A. ✅ Used for sending messages.
- B. Console consumer is for reading messages.
- C. kafka-topics manages topic metadata.
- D. kafka-configs handles entity configs.
```

</details>

---

## Question 11

```markdown
How can you consume messages from the beginning of a topic named "test" using the Kafka CLI?
```

**Options**

```markdown
- A. kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning
- B. kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test
- C. kafka-console-producer.sh --bootstrap-server localhost:9092 --topic test --from-beginning
- D. kafka-console-producer.sh --bootstrap-server localhost:9092 --topic test
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
Use `--from-beginning` to read from the earliest offset.

- A. ✅ Correct full command to consume from start.
- B. Default reads from latest offset.
- C. Producer CLI doesn’t consume messages.
- D. Same as above – wrong CLI tool.
```

</details>

---

## Question 12

```markdown
What is the purpose of the `--group` option in the `kafka-console-consumer.sh` command?
```

**Options**

```markdown
- A. To specify the consumer group ID for the console consumer
- B. To specify the number of consumer instances in the group
- C. To specify the list of topics to consume from
- D. To specify the bootstrap server for the consumer
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
Use `--group` to assign the consumer to a named group.

- A. ✅ Identifies the group the consumer belongs to.
- B. Number of instances isn't configured here.
- C. Use `--topic` for topic selection.
- D. Use `--bootstrap-server` for broker connection.
```

</details>

---
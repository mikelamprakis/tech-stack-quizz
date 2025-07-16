
## CLI
>
How will you find out all the partitions without a leader?

1. kafka-topic.sh--broker-list localhost:9092 --describe--under-replicated-partitions
2. kafka-topic.sh--broker-list localhost:2181 --describe--under-replicated-partitions
3. kafka-topic.sh--zookeeper localhost:2181 --describe--unavailable-partitions
4. kafka-topic.sh--zookeeper localhost:9092 --describe--unavailable-partitions



Explanation
Please note that as of Kafka 2.2, the --zookeeper option is deprecated and you can now use:
kafka-topics.sh --bootstrap-server localhost:9092 --describe --unavailable-partitions

---

Which Kafka CLI should you use to consume from a topic?
1. kafka-console
2. kafka-console-consumer
3. kafka-topic
4. kfaka-consumer-groups


Explanation
Example: kafka-console-consumer --bootstrap-server 127.0.0.1:9092 --topic test --from-beginning

---

The kafka-console-consumer CLI, when used with the default options
>
1. always use the same group id
2. does not use the group id
3. uses a random group id


Explanation
If a group is not specified, the kafka-console-consumer generates a random consumer group.


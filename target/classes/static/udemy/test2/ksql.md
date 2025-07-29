Which type of join in KSQL supports windowed joins?

KStream-to-KTable

KStream-to-GlobalKTable

Your answer is correct
KStream-to-KStream

KTable-to-KTable

Overall explanation
In KSQL, only KStream-to-KStream joins support windowed joins. KTable-to-KTable, KStream-to-KTable, and KStream-to-GlobalKTable joins do not support windowed joins.

Domain
Kafka KSQL
Beta

---

Consider the following KSQL statement to create a stream from a Kafka topic. Identify the issue in the code and explain how to fix it.



CREATE STREAM orders (
order_id INT KEY,
order_amount DOUBLE,
order_time BIGINT
) WITH (
KAFKA_TOPIC='orders-topic',
VALUE_FORMAT='JSON'
);
The VALUE_FORMAT should be AVRO.

Your answer is incorrect
The KAFKA_TOPIC property should be TOPIC_NAME.

Correct answer
The order_id field should be of type VARCHAR.

The CREATE STREAM statement is correct; no changes are needed.

Overall explanation
The issue in the provided CREATE STREAM statement is that the order_id field is incorrectly defined as INT. In KSQL, Kafka keys and values are typically represented as strings for JSON format. The order_id field should be of type VARCHAR.

Domain
Kafka KSQL
Beta

---



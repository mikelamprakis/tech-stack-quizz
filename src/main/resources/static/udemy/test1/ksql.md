Consider the following KSQL statement to create a stream from a Kafka topic.

Identify any issues in the code and explain how to fix them.



CREATE STREAM orders (
order_id VARCHAR,
order_amount DOUBLE,
order_time BIGINT
) WITH (
KAFKA_TOPIC='orders-topic',
VALUE_FORMAT='JSON'
);
Your answer is incorrect
The KAFKA_TOPIC property should be TOPIC_NAME.

The order_id field should be of type INT.

The VALUE_FORMAT should be AVRO.

Correct answer
The CREATE STREAM statement is correct; no changes are needed.

Overall explanation
The provided CREATE STREAM statement is correct. It creates a stream named orders with the specified fields and configurations, including the Kafka topic (KAFKA_TOPIC='orders-topic') and the value format (VALUE_FORMAT='JSON'). No changes are needed.

Domain
Kafka KSQL
Beta

---


What is the default port for the KSQL server?

9092

Your answer is correct
8088

7070

8443

Overall explanation
The default port for the KSQL server is 8088. This port is used for all client communications with the KSQL server, including the KSQL CLI and REST API interactions, facilitating the management and querying of streaming data through SQL-like syntax.

Domain
Kafka KSQL


---

What are the two primary deployment options for KSQL?

Your selection is correct
Headless mode

Batch mode

Real-time mode

Your selection is correct
Interactive mode

Overall explanation
KSQL can be deployed in Interactive mode, which allows for command-line interactions and using REST APIs, or in Headless mode, designed for automated, script-driven environments without user interaction​​.

Domain
Kafka KSQL
Beta

---


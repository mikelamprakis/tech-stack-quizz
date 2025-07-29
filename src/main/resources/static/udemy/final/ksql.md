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


You are using KSQL to analyze sales and customer data. You have two streams, sales_stream and customer_stream. You want to create a new stream that includes all sales records, along with matching customer information if available. If there is no matching customer information for a sales record, the sales record should still be included in the result.

Which type of join would you use for this scenario?

Inner Join

Right Join

Your answer is correct
Left Join

Outer Join

Overall explanation
In this scenario, you want to include all records from the sales_stream (the left side of the join) and match them with records from the customer_stream. If no matching customer information is found, the sales record should still be included in the result. This is achieved using a Left Join, which includes all records from the left side and matches records from the right side when available.

Domain
Kafka KSQL
Beta

---


What is a characteristic of a Tumbling Window in KSQL?

Your answer is correct
Has a fixed duration without overlaps.

Overlaps with other windows.

Dynamically adjusts based on activity.

Used primarily for joining data streams.

Overall explanation
A Tumbling Window is defined by a fixed duration and does not overlap with other windows, making it suitable for segmenting data into distinct, non-overlapping intervals.

Domain
Kafka KSQL
Beta

---


What are the differences between persistent and non-persistent queries in KSQL Kafka?

(Select all that apply)

Your selection is correct
Persistent queries continuously read from and write to Kafka topics.

Correct selection
Stateless non-persistent queries only read data and do not write to Kafka topics.

Non-persistent queries continually write to Kafka topics.

Your selection is correct
Stateful non-persistent queries read and temporarily write to Kafka, but this data is deleted when the query is terminated.

Overall explanation
In Kafka, persistent queries, such as those created using CREATE STREAM AS SELECT and CREATE TABLE AS SELECT, continuously read from and write to Kafka topics. Non-persistent queries, on the other hand, do not continually write to Kafka topics. Stateless non-persistent queries, like simple SELECT ... FROM foo WHERE ..., only read data. Stateful non-persistent queries, which include operations like COUNT and JOIN, read and temporarily write to Kafka, but this data is deleted when the query is terminated.

Domain
Kafka KSQL
Beta

---


Consider the following KSQL query to create a new stream from an existing stream using a CASE statement. Identify any issues in the code and explain how to fix it.



CREATE STREAM enriched_users AS
SELECT
firstname,
UCASE(lastname) AS uppercase_lastname,
countrycode,
rating,
CASE
WHEN rating < 2.5 THEN 'Poor'
WHEN rating BETWEEN 2.5 AND 4.2 THEN 'Good'
ELSE 'Excellent'
END AS rating_description
FROM userprofile_stream;
Your answer is incorrect
The rating_description field should be removed.

The UCASE function should be replaced with TOUPPER.

The CASE statement should use IF instead.

Correct answer
The code snippet is correct; no changes are needed.

Overall explanation
The provided KSQL query correctly creates a new stream named enriched_users from an existing stream userprofile_stream. It selects and transforms fields using a CASE statement to create a rating_description field based on the rating. The UCASE function is used to convert lastname to uppercase, and all syntax and logic are correctly implemented. No changes are needed.

Domain
Kafka KSQL
Beta

---


In the context of KSQL (ksqlDB), which capabilities simplify building data pipelines? Select all applicable features:

Your selection is incorrect
Real-time data analytics and transformations directly on Kafka streams.

Integration with external systems via Kafka Connect for sourcing and sinking data.

Your selection is correct
Stream processing using SQL-like syntax for operations such as SELECT, JOIN, and WINDOW.

Correct selection
Real-time data analytics and transformations directly on Kafka streams.

Overall explanation
KSQL (ksqlDB) simplifies the development of Kafka data pipelines by providing:

A SQL-like syntax that allows complex stream processing tasks like joins, filters, and windowed aggregations to be defined in a way familiar to many developers.

Capabilities for performing real-time data analytics and transformations, enabling immediate insights and actions based on streaming data, directly within Kafka.

Domain
Kafka KSQL
Beta

---

What defines a Hopping Window in KSQL?

Your answer is correct
A fixed-duration window that overlaps based on a defined interval.

A window that captures all events until explicitly closed.

A window used exclusively for batch processing.

A window that dynamically adjusts based on events.

Overall explanation
Hopping Windows have a fixed duration but overlap with others based on a 'hop' interval, allowing events to be included in multiple windows, which can provide more comprehensive coverage of data across time.

Domain
Kafka KSQL
Beta

---



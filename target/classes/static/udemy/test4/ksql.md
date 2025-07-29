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



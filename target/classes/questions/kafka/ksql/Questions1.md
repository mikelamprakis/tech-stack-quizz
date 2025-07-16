## Question 1

```markdown
Which of the following KSQL statements will cause writes to a Kafka topic? (Select two)
```

**Options**

```markdown
- A. CREATE STREAM FROM_TOPIC AS SELECT * FROM source_topic;
- B. CREATE TABLE FROM_TOPIC AS SELECT * FROM source_topic;
- C. SELECT * FROM source_topic EMIT CHANGES;
- D. DESCRIBE source_topic;
- E. SHOW QUERIES;
```

<details><summary>Response:</summary>

**Answer:** A, B

**Explanation:**

```markdown
In KSQL, `CREATE STREAM AS SELECT` and `CREATE TABLE AS SELECT` create new Kafka topics and write to them.

- A. Correct – creates a new stream that writes to a topic.
- B. Correct – creates a table and writes to a topic.
- C. Incorrect – transient query, does not write to a Kafka topic.
- D. Incorrect – metadata query.
- E. Incorrect – metadata query.
```

</details>

---

## Question 2

```markdown
What happens when you run a CREATE STREAM statement without an AS SELECT clause in KSQL?
```

**Options**

```markdown
- A. It creates a new stream and writes metadata to the KSQL command topic.
- B. It creates a new stream and starts writing data to it from the KSQL application.
- C. It fails because CREATE STREAM must always include an AS SELECT clause.
- D. It creates a new empty stream but doesn't write anything to Kafka.
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
CREATE STREAM without AS SELECT registers a stream on an existing topic; metadata is stored in the command topic.

- A. Correct – metadata is written.
- B. Incorrect – no data is written automatically.
- C. Incorrect – AS SELECT is optional.
- D. Incorrect – metadata is written, not the stream itself.
```

</details>

---

## Question 3

```markdown
What is the purpose of the PARTITIONS clause in a KSQL CREATE TABLE statement?
```

**Options**

```markdown
- A. To specify the number of partitions for the output Kafka topic
- B. To specify the partitioning key for the output Kafka topic
- C. To specify the number of partitions to read from the input Kafka topic
- D. To specify the partitioning key to read from the input Kafka topic
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
The PARTITIONS clause defines how many partitions the resulting Kafka topic will have.

- A. Correct – controls output topic partitions.
- B. Incorrect – partitioning key is handled differently.
- C. Incorrect – input topic partitions are not configured here.
- D. Incorrect – key specification uses other syntax.
```

</details>

---

## Question 4

```markdown
Which query type is not supported by KSQL?
```

**Options**

```markdown
- A. Stream-to-Stream JOINs
- B. Table-to-Table JOINs
- C. Stream-to-Table JOINs
- D. Complex Nested Queries
```

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**

```markdown
KSQL doesn’t support deeply nested or complex subqueries.

- A. Supported
- B. Supported
- C. Supported
- D. Correct – not supported.
```

</details>

---

## Question 5

```markdown
What is a KSQL table?
```

**Options**

```markdown
- A. A mutable collection of key-value pairs
- B. An immutable, append-only collection of records
- C. A stateful, changelog-based table
- D. A temporary view of streaming data
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
KSQL tables are stateful structures built from changelog topics.

- A. Too generic
- B. Describes a stream
- C. Correct – changelog-based table
- D. Incorrect – not temporary
```

</details>

---

## Question 6

```markdown
Which KSQL function is used to convert a string to uppercase?
```

**Options**

```markdown
- A. UPPER()
- B. TO_UPPER()
- C. STRING_UPPER()
- D. CONVERT_UPPER()
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
UPPER() is the valid KSQL function for uppercase conversion.

- A. Correct
- B. Incorrect – not a valid function
- C. Incorrect – not supported
- D. Incorrect – invalid
```

</details>

---

## Question 7

```markdown
What does the WINDOW clause in a KSQL query specify?
```

**Options**

```markdown
- A. The time frame for aggregations
- B. The filter condition for the query
- C. The key for partitioning the data
- D. The join condition between streams
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
The WINDOW clause defines a time-based window for aggregations.

- A. Correct
- B. Incorrect – filtering is handled by WHERE
- C. Incorrect – handled by PARTITION BY
- D. Incorrect – JOINs have separate syntax
```

</details>

---

## Question 8

```markdown
Which data format is not supported by KSQL for serialization and deserialization?
```

**Options**

```markdown
- A. JSON
- B. Protobuf
- C. Avro
- D. Thrift
```

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**

```markdown
KSQL supports JSON, Avro, and Protobuf; not Thrift.

- A. Supported
- B. Supported
- C. Supported
- D. Correct – not supported
```

</details>

---

## Question 9

```markdown
How can you create a stream in KSQL from an existing Kafka topic?
```

**Options**

```markdown
- A. CREATE STREAM stream_name FROM topic_name;
- B. CREATE STREAM stream_name (columns) WITH (kafka_topic='topic_name', value_format='format');
- C. CREATE STREAM stream_name WITH (kafka_topic='topic_name', value_format='format');
- D. CREATE STREAM stream_name AS SELECT * FROM topic_name;
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Correct syntax involves defining columns and specifying the topic and format.

- A. Incorrect – invalid syntax
- B. Correct
- C. Incorrect – missing column definition
- D. Incorrect – used for derived streams
```

</details>

---

## Question 10

```markdown
What is the purpose of the PARTITION BY clause in KSQL?
```

**Options**

```markdown
- A. To split the stream into multiple topics
- B. To repartition the data based on a specified column
- C. To create a new table from a stream
- D. To define the output format of the query
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
PARTITION BY changes the partitioning key within a stream.

- A. Incorrect – no topic splitting
- B. Correct
- C. Incorrect – uses CREATE TABLE
- D. Incorrect – not format-related
```

</details>

---

Here you go:

## Question

```markdown
What Java library is KSQL based on?
```

**Options**

```markdown
- A. Apache Flink
- B. Kafka Streams
- C. Apache Spark
- D. Storm
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
KSQL is based on Kafka Streams and allows you to express transformations in the SQL language that get automatically converted to a Kafka Streams program in the backend.

- A, C, and D are incorrect because KSQL does not build on Apache Flink, Apache Spark, or Storm.
```

</details>

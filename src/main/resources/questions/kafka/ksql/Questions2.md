## Question 11

```markdown
Which KSQL function is used to concatenate two strings?
```

**Options**

```markdown
- A. CONCAT()
- B. JOIN()
- C. MERGE()
- D. APPEND()
```

<details><summary>Response:</summary> 

**Answer:** A

**Explanation:**

```markdown
The `CONCAT()` function in KSQL is used to concatenate two strings.

- B, C, and D are incorrect because they are not valid KSQL functions for concatenating strings.
```

</details>

---

## Question 12

```markdown
What is the role of the `KEY` keyword in KSQL table creation?
```

**Options**

```markdown
- A. To define the primary key of the table
- B. To specify the partitioning key of the table
- C. To assign a unique identifier to each record
- D. To create an index on the table
```

<details><summary>Response:</summary> 

**Answer:** A

**Explanation:**

```markdown
The `KEY` keyword in KSQL table creation is used to specify the primary key of the table, which is also used for partitioning.

- B, C, and D are incorrect because they do not accurately describe the role of the `KEY` keyword in KSQL.
```

</details>

---

## Question 13

```markdown
Which statement is true about KSQL streams?
```

**Options**

```markdown
- A. They store historical data indefinitely
- B. They are append-only collections of immutable records
- C. They can be directly queried for the current state
- D. They do not support windowed aggregations
```

<details><summary>Response:</summary> 

**Answer:** B

**Explanation:**

```markdown
KSQL streams are append-only collections of immutable records that represent the continuous flow of data.

- A, C, and D are incorrect because streams do not store data indefinitely, they represent immutable records, and they do support windowed aggregations.
```

</details>

---

## Question 14

```markdown
Which KSQL command is used to terminate a running query?
```

**Options**

```markdown
- A. DROP QUERY
- B. STOP QUERY
- C. TERMINATE
- D. DELETE QUERY
```

<details><summary>Response:</summary> 

**Answer:** C

**Explanation:**

```markdown
The `TERMINATE` command is used to stop a running query in KSQL.

- A, B, and D are incorrect because they are not valid commands for terminating a query in KSQL.
```

</details>

---

## Question 15

```markdown
What is the result of executing the following KSQL query: `SELECT * FROM my_stream EMIT CHANGES;`?
```

**Options**

```markdown
- A. It creates a new table from the stream
- B. It continuously outputs the current state of the stream
- C. It returns a snapshot of the stream at a point in time
- D. It filters records based on a condition
```

<details><summary>Response:</summary> 

**Answer:** B

**Explanation:**

```markdown
The `SELECT * FROM my_stream EMIT CHANGES;` query continuously outputs the current state of the stream, providing a real-time view of the data as it arrives.

- A, C, and D are incorrect because they do not describe the behavior of the `EMIT CHANGES` clause.
```

</details>

---

## Question 16

```markdown
Which clause in KSQL is used to define the duration of a hopping window?
```

**Options**

```markdown
- A. SIZE
- B. DURATION
- C. HOP
- D. WINDOW
```

<details><summary>Response:</summary> 

**Answer:** A

**Explanation:**

```markdown
The `SIZE` clause is used to define the duration of a hopping window in KSQL.

- B, C, and D are incorrect because they are not valid clauses for defining the duration of a hopping window in KSQL.
```

</details>

---

## Question 17

```markdown
How can you perform an inner join between two streams in KSQL?
```

**Options**

```markdown
A. CREATE STREAM new_stream AS SELECT * FROM stream1 INNER JOIN stream2 WITHIN 5 MINUTES ON stream1.key = stream2.key;

B. CREATE STREAM new_stream AS SELECT * FROM stream1 JOIN stream2 ON stream1.key = stream2.key;

C. CREATE STREAM new_stream AS SELECT * FROM stream1 LEFT JOIN stream2 WITHIN 5 MINUTES ON stream1.key = stream2.key;

D. CREATE STREAM new_stream AS SELECT * FROM stream1 CROSS JOIN stream2 ON stream1.key = stream2.key;
```

<details><summary>Response:</summary> 

**Answer:** A

**Explanation:**

```markdown
The correct syntax to perform an inner join between two streams in KSQL is:

CREATE STREAM new_stream AS
SELECT *
FROM stream1
INNER JOIN stream2
WITHIN 5 MINUTES
ON stream1.key = stream2.key;

- `INNER JOIN` specifies an inner join.
- `WITHIN 5 MINUTES` defines the time window necessary for stream-to-stream joins.
- `ON stream1.key = stream2.key` is the join condition.

Option B is incorrect because it lacks the mandatory `WITHIN` clause.

Option C is incorrect because it is a left join, not an inner join.

Option D is incorrect because cross joins are not supported between streams in KSQL.
```

</details>

---

## Question 18

```markdown
What does the `GROUP BY` clause do in a KSQL query?
```

**Options**

```markdown
- A. It filters records based on a condition
- B. It partitions the data by a specified key
- C. It aggregates data based on specified columns
- D. It orders the data by a specified column
```

<details><summary>Response:</summary> 

**Answer:** C

**Explanation:**

```markdown
The `GROUP BY` clause in a KSQL query aggregates data based on specified columns, allowing for calculations like COUNT, SUM, AVG, etc., over grouped records.

- A, B, and D are incorrect because they describe different functionalities.
```

</details>

---

## Question 19

```markdown
Which keyword is used to create a persistent query in KSQL?
```

**Options**

```markdown
- A. PERSIST
- B. CREATE STREAM AS
- C. CREATE PERSISTENT QUERY
- D. SAVE
```

<details><summary>Response:</summary> 

**Answer:** B

**Explanation:**

```markdown
The `CREATE STREAM AS` keyword is used to create a persistent query in KSQL. This query continuously processes data and stores the results in a new stream.

- A, C, and D are incorrect because they are not valid keywords for creating a persistent query in KSQL.
```

</details>

---

## Question 20

```markdown
Which function is used to calculate the number of records in a KSQL stream?
```

**Options**

```markdown
- A. COUNT()
- B. SUM()
- C. AVG()
- D. MAX()
```

<details><summary>Response:</summary> 

**Answer:** A

**Explanation:**

```markdown
The `COUNT()` function is used to calculate the number of records in a KSQL stream.

- B, C, and D are incorrect because they perform different types of calculations.
```

</details>

---

Let me know if you want me to generate quizzes, explanations, or other formats!

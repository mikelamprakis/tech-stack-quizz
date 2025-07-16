## Question 21

```markdown
How can you convert a stream into a table in KSQL?
```

**Options**

```markdown
- A. CREATE TABLE table_name AS SELECT * FROM stream_name;
- B. INSERT INTO table_name SELECT * FROM stream_name;
- C. CREATE TABLE table_name FROM stream_name;
- D. CONVERT STREAM stream_name TO TABLE table_name;
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
The correct syntax to convert a stream into a table in KSQL is:

- A. `CREATE TABLE table_name AS SELECT * FROM stream_name;` — valid syntax for conversion.
- B. Incorrect, not valid syntax for this operation.
- C. Incorrect, invalid syntax.
- D. Incorrect, no such command in KSQL.
```

</details>

---

## Question 22

```markdown
What is the purpose of the `AVRO` format in KSQL?
```

**Options**

```markdown
- A. To provide a human-readable format for data
- B. To enable complex data types and schema evolution
- C. To ensure data is stored as plain text
- D. To simplify data parsing
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- A. Incorrect, Avro is a binary format, not human-readable.
- B. Correct, Avro enables complex data types and supports schema evolution.
- C. Incorrect, Avro is not plain text.
- D. Incorrect, simplification is not its primary purpose.
```

</details>

---

## Question 23

```markdown
Which KSQL function is used to extract the year from a timestamp?
```

**Options**

```markdown
- A. EXTRACTYEAR()
- B. GETYEAR()
- C. YEAR()
- D. EXTRACT(YEAR FROM timestamp)
```

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**

```markdown
- A. Invalid function.
- B. Invalid function.
- C. Invalid function.
- D. Correct, standard SQL syntax used in KSQL to extract year.
```

</details>

---

## Question 24

```markdown
How do you handle null values in KSQL?
```

**Options**

```markdown
- A. Use the `IS NULL` and `IS NOT NULL` predicates
- B. Use the `NULLIFY()` function
- C. Replace null values with default values using `COALESCE()`
- D. Both A and C
```

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**

```markdown
- A. Correct, used to check for nulls.
- B. Incorrect, `NULLIFY()` is not a KSQL function.
- C. Correct, `COALESCE()` replaces nulls with defaults.
- D. Correct, combining A and C covers handling null values.
```

</details>

---

## Question 25

```markdown
Which KSQL function calculates the total sum of a column's values?
```

**Options**

```markdown
- A. SUM()
- B. TOTAL()
- C. ADD()
- D. AGGREGATE()
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
- A. Correct, `SUM()` calculates the total sum.
- B. Incorrect, no such function.
- C. Incorrect, no such function.
- D. Incorrect, no such function.
```

</details>

---

## Question 26

```markdown
What is the default retention period for KSQL streams?
```

**Options**

```markdown
- A. 7 days
- B. 1 day
- C. 1 week
- D. 2 days
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
- A. Correct, default retention period is 7 days.
- B. Incorrect, can be configured but not default.
- C. Equivalent to 7 days but default is expressed as 7 days.
- D. Incorrect, can be configured but not default.
```

</details>

---

## Question 27

```markdown
How can you filter records in a KSQL stream?
```

**Options**

```markdown
- A. By using the `FILTER` clause
- B. By using the `WHERE` clause
- C. By using the `HAVING` clause
- D. By using the `LIMIT` clause
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- A. Incorrect, no `FILTER` clause in KSQL.
- B. Correct, `WHERE` clause is used to filter records.
- C. Incorrect, `HAVING` is for aggregated filtering, not streams.
- D. Incorrect, `LIMIT` limits rows but does not filter conditions.
```

</details>

---

## Question 28

```markdown
Which KSQL function can be used to format timestamps?
```

**Options**

```markdown
- A. FORMAT_TIMESTAMP()
- B. TO_TIMESTAMP()
- C. DATE_FORMAT()
- D. TIMESTAMP_FORMAT()
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Invalid function.
- B. Invalid for formatting (used for conversion).
- C. Correct, `DATE_FORMAT()` formats timestamps.
- D. Invalid function.
```

</details>

---

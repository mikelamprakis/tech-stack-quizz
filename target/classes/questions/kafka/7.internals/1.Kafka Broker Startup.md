## Question 3

```markdown
When a Kafka broker starts up, it performs various initialization tasks. Which of the following is NOT one of these tasks?

```

**Options**

```markdown
- A. Registering itself with Zookeeper
- B. Loading the replica assignment for each partition it hosts
- C. Creating a new Zookeeper znode for each topic it has partitions for
- D. Initializing the log directories for each partition it hosts
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Topic znodes are created during topic creation, not broker startup. Broker startup includes registering with Zookeeper, loading replica assignments, and initializing log directories.

- A. Broker registers itself with Zookeeper at startup.
- B. Broker loads its partition replica assignments.
- C. Correct — broker does not create topic znodes.
- D. Broker initializes log directories for its partitions.
```

</details>

---

## Question 8

```markdown
During startup, how does a Kafka broker handle corrupt log segments in its partitions?
```

**Options**

```markdown
- A. Deletes corrupt segments and starts fresh.
- B. Attempts recovery using the segment index files.
- C. Marks the partition offline until manual repair.
- D. Replicates data from other brokers to replace corrupt segments.
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- B. Correct — Brokers use index files to recover valid data.
- A/C/D. Incorrect — No automatic deletion/replication; manual intervention may be needed if recovery fails.
```

</details>

---

## Question 12

```markdown
If a Kafka broker fails to initialize its log directories during startup, what is the expected behavior?
```

**Options**

```markdown
- A. The broker skips affected partitions and starts normally.
- B. The broker shuts down and logs an error.
- C. The broker recreates empty log directories automatically.
- D. The broker delegates partition hosting to another broker.
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- B. Correct — Broker startup fails if log directories cannot be initialized.
- A/C/D. Incorrect — Brokers do not skip, auto-recreate, or delegate partitions.
```

</details>

---

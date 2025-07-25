## Question 1

```markdown
Which of the following is stored in Zookeeper for a Kafka cluster? (Select two)
```

**Options**

```markdown
- A. Consumer offsets
- B. Kafka broker information
- C. Topic partition assignments
- D. Topic-level configurations
- E. Producer client IDs
```

<details><summary>Response:</summary>

**Answer:** B, D

**Explanation:**

```markdown
In a Kafka cluster, Zookeeper stores:

- B. Kafka broker information: It keeps details about each broker in the cluster.
- D. Topic-level configurations: This includes settings like retention and replication.

Incorrect options:

- A. Consumer offsets are stored in the Kafka topic `__consumer_offsets`.
- C. Partition assignments are handled by the Kafka controller.
- E. Producer client IDs are not stored in Zookeeper.
```

</details>

---

## Question 14

```markdown
Which of the following is stored in Zookeeper for a Kafka cluster? (Select two)

- A. Consumer offsets
- B. Kafka broker information
- C. Topic partition assignments
- D. Topic-level configurations
- E. Producer client IDs
```

**Options**

```markdown
- A. Consumer offsets
- B. Kafka broker information
- C. Topic partition assignments
- D. Topic-level configurations
- E. Producer client IDs
```

<details><summary>Response:</summary> 

**Answer:** B, D

**Explanation:**

```markdown
Kafka uses ZooKeeper to store metadata such as:

- A. Consumer offsets – ❌ Stored in `__consumer_offsets` topic.
- B. Kafka broker information – ✅ Includes broker registrations and cluster membership.
- C. Topic partition assignments – ❌ Managed by the Kafka controller.
- D. Topic-level configurations – ✅ Includes settings like retention.
- E. Producer client IDs – ❌ Maintained client-side.
```

</details>

---

## Question 15

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
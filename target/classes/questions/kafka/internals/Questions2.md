## Question 11

```markdown
What is the purpose of the `kafka-dump-log` tool in KRaft mode?
```

**Options**

```markdown
- A. To display the contents of the KRaft metadata log
- B. To modify the Kafka broker configuration
- C. To list the available Kafka topics
- D. To monitor the Kafka cluster performance
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
In KRaft mode, the `kafka-dump-log` tool is used to display the contents of the KRaft metadata log. It allows you to inspect the log segments and snapshots for the cluster metadata directory.

- A. ✅ Correct – Used to decode metadata logs with `--cluster-metadata-decoder`.
- B. ❌ Incorrect – Configuration is managed via `server.properties` or `kafka-configs`.
- C. ❌ Incorrect – Use `kafka-topics` to list topics.
- D. ❌ Incorrect – Monitoring is done using metrics and external tools.
```

</details>

---

## Question 12

```markdown
What is the purpose of the `kafka-metadata-shell` tool in KRaft mode?
```

**Options**

```markdown
- A. To modify the Kafka broker configuration
- B. To list the available Kafka topics
- C. To monitor the Kafka cluster performance
- D. To interactively inspect the KRaft metadata
```

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**

```markdown
The `kafka-metadata-shell` tool provides an interactive way to explore KRaft metadata.

- A. ❌ Incorrect – Not used for modifying configs.
- B. ❌ Incorrect – Topic listing is done with `kafka-topics`.
- C. ❌ Incorrect – Monitoring uses metrics.
- D. ✅ Correct – This is a shell-like tool to inspect metadata, e.g., `ls`, `cat`, etc.
```

</details>

---

## Question 13

```markdown
What is the significance of the `kafka.controller:type=QuorumController,name=LastCommittedRecordOffset` metric in KRaft mode?
```

**Options**

```markdown
- A. It indicates the offset of the last record that was applied by the controller to the metadata partition
- B. It represents the number of records that have been committed to the metadata partition
- C. It measures the lag between the active controller and the last committed record in the metadata partition
- D. It tracks the offset of the last record that was committed by the active controller
```

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**

```markdown
This metric shows how far the active controller has progressed in committing metadata records.

- A. ❌ Incorrect – That's `LastAppliedRecordOffset`.
- B. ❌ Incorrect – Not about count, it's about offset.
- C. ❌ Incorrect – Lag is handled by `LastAppliedRecordLagMs`.
- D. ✅ Correct – It tracks the offset committed by the active controller.
```

</details>

---

## Question 14

```markdown
What is the purpose of the `metadata.max.idle.interval.ms` configuration in KRaft mode?
```

**Options**

```markdown
- A. To set the maximum time allowed for a metadata request to be idle before it is cancelled
- B. To specify the maximum time the active controller can be idle before a new controller is elected
- C. To configure the frequency at which the active controller writes no-op records to the metadata partition
- D. To define the maximum interval allowed between two consecutive metadata log segments
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
This config determines how often no-op records are written to keep metadata partition alive.

- A. ❌ Incorrect – Not related to requests.
- B. ❌ Incorrect – Controller elections are handled elsewhere.
- C. ✅ Correct – Controls how frequently the controller emits no-op metadata records.
- D. ❌ Incorrect – It doesn’t define log segment intervals.
```

</details>

---

## Question 15

```markdown
What is the role of the `kafka.controller:type=QuorumController,name=MaxFollowerLag` metric in KRaft mode?
```

**Options**

```markdown
- A. It measures the maximum lag between the active controller and the last committed record in the metadata partition
- B. It indicates the maximum lag between the active controller and the followers in terms of metadata records
- C. It represents the maximum number of records that a follower can lag behind the active controller
- D. It tracks the maximum lag between the followers and the last applied record in the metadata partition
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
This metric ensures followers are keeping up with the active controller's metadata changes.

- A. ❌ Incorrect – It’s not about lag to committed record.
- B. ✅ Correct – Measures the actual lag in records between controller and its followers.
- C. ❌ Incorrect – It’s not a configurable limit, but an observed value.
- D. ❌ Incorrect – Not about applied records.
```

</details>

---

## Question 16

```markdown
What is the significance of the `kafka.server:type=SnapshotEmitter,name=LatestSnapshotGeneratedAgeMs` metric in KRaft mode?
```

**Options**

```markdown
- A. It indicates the age of the latest snapshot in milliseconds since the snapshot was generated
- B. It measures the time taken to generate the latest snapshot in milliseconds
- C. It represents the age of the latest snapshot in milliseconds since the process was started
- D. It tracks the time elapsed since the latest snapshot was loaded in milliseconds
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
This metric tracks how old the most recent snapshot is.

- A. ✅ Correct – Time since the snapshot was generated.
- B. ❌ Incorrect – Not the generation time duration.
- C. ❌ Incorrect – Not tied to process start time.
- D. ❌ Incorrect – Doesn’t relate to snapshot loading.
```

</details>

---

## Question 17

```markdown
What is the purpose of the `kafka.controller:type=KafkaController,name=ControlledShutdownCount` metric in KRaft mode?
```

**Options**

```markdown
- A. It measures the number of controlled shutdown requests received by the controller
- B. It indicates the number of brokers that have completed a controlled shutdown
- C. It represents the number of brokers that are currently in the process of controlled shutdown
- D. It tracks the number of controlled shutdown failures experienced by the controller
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
This metric counts the number of brokers that completed graceful shutdowns.

- A. ❌ Incorrect – Not about received requests.
- B. ✅ Correct – Shows successful controlled shutdowns.
- C. ❌ Incorrect – Doesn’t show brokers currently shutting down.
- D. ❌ Incorrect – Doesn’t count failures.
```

</details>

---

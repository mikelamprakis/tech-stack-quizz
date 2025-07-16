## Question 1

```markdown
A Kafka cluster has the following configuration:

`unclean.leader.election.enable=false`

What is the implication of this setting when a partition leader fails and there are no in-sync replicas (ISRs) available?
```

**Options**

```markdown
- A. The partition will remain unavailable until the failed leader recovers.
- B. The partition will elect a new leader from the out-of-sync replicas to maintain availability.
- C. The partition will automatically create a new replica to replace the failed leader.
- D. The partition will be reassigned to another broker in the cluster.
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
When `unclean.leader.election.enable=false`, Kafka disallows election of a leader from out-of-sync replicas.  
So if no ISR is available after leader failure, the partition remains unavailable to preserve data consistency.

- A. Correct — partition remains unavailable until original leader recovers.
- B. Incorrect — election from out-of-sync replicas is disabled.
- C. Incorrect — Kafka does not create replicas automatically.
- D. Incorrect — partition is not reassigned automatically.
```

</details>

---

## Question 2

```markdown
A Kafka broker is configured with the following settings:

`num.replication.fetchers=4`  
`replica.fetch.max.bytes=1048576`

What is the maximum amount of data that can be fetched by the broker for replication purposes in a single request?
```

**Options**

```markdown
- A. 1 MB
- B. 4 MB
- C. 1048576 bytes
- D. 4194304 bytes
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
`replica.fetch.max.bytes` is the per-fetcher limit, set to 1 MB (1048576 bytes).  
Each of the 4 fetchers operates independently, so per request the max fetch size is 1 MB.

- A. Correct — per fetch request limit is 1 MB.
- B. Incorrect — 4 MB is total across all fetchers, not per request.
- C. Correct in bytes but answer expects MB format, so A preferred.
- D. Incorrect — 4 * 1 MB = 4 MB total across fetchers, not per request.
```

</details>

---

## Question 3

```markdown
A Kafka cluster is configured with the following:

`log.retention.hours=48`  
`log.retention.bytes=1073741824`  
`log.segment.bytes=536870912`

Assuming a topic has a constant message production rate, which of the following factors will trigger a log segment to be eligible for deletion?
```

**Options**

```markdown
- A. The log segment is older than 48 hours.
- B. The log segment size exceeds 536870912 bytes (512 MB).
- C. The total size of all log segments for the topic exceeds 1073741824 bytes (1 GB).
- D. All of the above.
```

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**

```markdown
Kafka retention policies combine time and size limits:  
- Segments older than 48 hours are eligible for deletion.  
- Segment size exceeding 512 MB can trigger rolling and deletion.  
- Total log size exceeding 1 GB triggers deletion of oldest segments.  

- A. Correct — time-based retention.
- B. Correct — segment size affects segment rolling.
- C. Correct — total size triggers deletion of old segments.
- D. Correct — all factors apply.
```

</details>

---

## Question 4

```markdown
A client connects to a broker in a Kafka cluster and sends a produce request for a topic partition.  
The broker responds with a 'Not Enough Replicas' error. What does the client do next?
```

**Options**

```markdown
- A. Retries sending the produce request to the same broker
- B. Sends metadata request to the same broker to refresh its metadata
- C. Sends produce request to the controller broker
- D. Sends metadata request to the Zookeeper to find the controller broker
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Upon 'Not Enough Replicas' error, the client refreshes its metadata from the broker to get updated ISR and broker status.  
The client does not contact the controller or Zookeeper directly.

- A. Incorrect — retry without metadata update unlikely to succeed.
- B. Correct — refresh metadata to get latest ISR info.
- C. Incorrect — produce requests go to leader brokers, not necessarily controller.
- D. Incorrect — clients do not query Zookeeper directly.
```

</details>

---

## Question 5

```markdown
Assuming a Kafka topic is configured with the following settings:

`log.segment.bytes = 1073741824` (1GB)  
`log.retention.ms = 86400000` (1 day)  
`log.retention.bytes = -1`

Which of the following statements accurately describes the log retention policy for this Kafka topic?
```

**Options**

```markdown
- A. Logs are retained based on size; once the log size exceeds 1GB, older segments are deleted.
- B. Logs are retained for exactly one day, regardless of the size of the log.
- C. Logs are retained until the size of the log exceeds 1GB or for one day, whichever comes first.
- D. Logs are retained indefinitely, as `log.retention.bytes` is set to -1, overriding other retention configurations.
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- `log.retention.ms = 86400000` enables time-based retention (1 day).  
- `log.retention.bytes = -1` disables size-based retention.  
- `log.segment.bytes` controls segment file size, not retention policy.

Thus, logs are retained based on time only (1 day), ignoring size.

- A. Incorrect — size-based retention is disabled.
- B. Correct — logs retained for one day regardless of size.
- C. Incorrect — size limit is ignored.
- D. Incorrect — logs are not retained indefinitely.
```

</details>

---

## Question 6

```markdown
A Kafka broker is configured as follows:

`log.segment.bytes=1073741824`  
`log.segment.ms=86400000`  
`log.retention.bytes=-1`  
`log.retention.ms=604800000`

When will Kafka start a new log segment for a partition, and how long will the old segments be retained?
```

**Options**

```markdown
- A. New segment after 1 GB or 24 hrs; retain for 7 days
- B. New segment after 1 GB or 24 hrs; retain indefinitely
- C. New segment after 1 GB; retain for 7 days or if total exceeds 1 GB
- D. New segment after 24 hrs; retain for 7 days or if total exceeds 1 GB
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
Kafka rolls log segments on reaching either size (`1GB`) or time (`24 hours`).  
Retention deletes old segments older than 7 days (`log.retention.ms=604800000`).  
Size-based retention is disabled (`log.retention.bytes=-1`).

- A. Correct — segment rolling and retention times match this option.
- B. Incorrect — retention is time-based, not indefinite.
- C. Incorrect — size retention disabled.
- D. Incorrect — segments roll on size or time, not time alone.
```

</details>

---

## Question 7

```markdown
A Kafka cluster is configured with:

`default.replication.factor=2`  
`min.insync.replicas=2`

What is the minimum number of brokers required to tolerate one failure and still serve write requests?
```

**Options**

```markdown
- A. 1
- B. 2
- C. 3
- D. 4
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
`min.insync.replicas=2` means two replicas must acknowledge writes.  
To tolerate one broker failure while maintaining two in-sync replicas, you need **3 brokers** in total.

- A. Incorrect — one broker cannot meet insync requirements.
- B. Incorrect — two brokers cannot tolerate one failure and still maintain 2 ISRs.
- C. Correct — three brokers allow one failure and 2 ISRs remain.
- D. Incorrect — more than needed.
```

</details>

---

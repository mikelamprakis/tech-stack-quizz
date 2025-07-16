## Question 43

```markdown
Can a producer guarantee the order of messages within a partition when sending messages with different keys?
```

**Options**

```markdown
- A. Yes, messages within a partition are always guaranteed to be in the same order as they were sent by the producer
- B. No, messages with different keys can be written to the same partition in a different order than they were sent
- C. It depends on the configuration of the producer
- D. It depends on the configuration of the topic
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Kafka guarantees ordering *within a partition* for messages with the *same key*. When different keys are used, messages may be routed to different partitions (based on key hash), and even if routed to the same partition, ordering is not guaranteed. Partition assignment and write timing can lead to interleaving of messages.

- A. Misleading—ordering is not universally guaranteed for all keys.
- B. Correct—ordering only applies to the same key in a partition.
- C. Incorrect—ordering is not controlled purely by configuration but by key-based partitioning.
- D. Irrelevant—the topic configuration doesn’t influence per-key ordering.
```

</details>

---

## Question 44

```markdown
What happens when a producer tries to send a message to a partition whose leader replica is not in-sync?
```

**Options**

```markdown
- A. The producer receives a `NotLeaderOrFollowerException` and retries sending the message
- B. The producer waits until the leader replica becomes in-sync before sending the message
- C. The message is automatically routed to another in-sync replica
- D. The producer receives a `LeaderNotAvailableException` and the message is discarded
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
If the producer tries to send to a broker that is not the leader for a partition, it receives a `NotLeaderOrFollowerException`. The producer then refreshes its metadata and retries. Kafka does not automatically reroute to another replica—only the partition leader can accept writes.

- A. Correct—producer retries after metadata refresh.
- B. Incorrect—producer doesn't wait for ISR recovery.
- C. Incorrect—routing only goes to the partition leader.
- D. Incorrect—the specific exception is `NotLeaderOrFollowerException`.
```

</details>

---

## Question 45

```markdown
In a topic with a replication factor of 3 and `min.insync.replicas` set to 2, what happens when a producer sends a message with `acks=all` and two replicas are not in-sync?
```

**Options**

```markdown
- A. The producer receives an acknowledgment and the message is successfully written
- B. The producer receives a `NotEnoughReplicasException` and the message is not written
- C. The producer waits indefinitely until at least two replicas become in-sync
- D. The message is written to the leader replica and the producer receives an acknowledgment
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
With `acks=all`, Kafka requires acknowledgment from all *in-sync* replicas. If only one replica (the leader) is in-sync and `min.insync.replicas=2`, the message will not be acknowledged, and a `NotEnoughReplicasException` is returned. The producer will not wait indefinitely, and the write is considered failed.

- A. Incorrect—minimum in-sync replicas requirement not met.
- B. Correct—the write fails due to insufficient in-sync replicas.
- C. Incorrect—Kafka does not wait forever, it fails the request.
- D. Incorrect—acks=all demands full ISR acknowledgement.
```

</details>

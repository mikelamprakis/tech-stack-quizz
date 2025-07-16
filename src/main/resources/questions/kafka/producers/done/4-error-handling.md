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
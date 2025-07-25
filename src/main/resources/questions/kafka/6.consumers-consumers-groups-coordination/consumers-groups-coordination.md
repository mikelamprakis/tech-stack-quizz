## Question 1

```markdown
In a consumer group with 5 members and a topic with 3 partitions, how many consumers will be actively processing messages during normal operation?
```

**Options**
```markdown
- A. 1
- B. 3
- C. 5
- D. 2
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Kafka assigns each partition to exactly one consumer. With 3 partitions, only 3 consumers will be active.

- A. Incorrect: Would underutilize available partitions
- B. Correct: Matches 1:1 partition-to-consumer mapping
- C. Incorrect: More consumers than partitions
- D. Incorrect: Doesn't match partition count
```

</details>

---

## Question 2

```markdown
During a rebalance, what happens if the elected group leader fails before completing the assignment?
```

**Options**
```markdown
- A. The group remains in rebalance state indefinitely
- B. A new leader is elected and the process restarts
- C. The coordinator assigns partitions randomly
- D. Consumers revert to their previous assignments
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
The group coordinator detects leader failure and initiates a new leader election to complete the rebalance.

- A. Incorrect: Kafka has failure recovery mechanisms
- B. Correct: Standard failover procedure
- C. Incorrect: Coordinator doesn't make assignments
- D. Incorrect: Previous assignments are revoked
```

</details>

---

## Question 3

```markdown
What occurs when two consumer groups with the same group.id subscribe to different topics?
```

**Options**
```markdown
- A. The second group fails to initialize
- B. Both groups merge into a single coordinated group
- C. They operate independently with separate offsets
- D. Kafka throws a duplicate group exception
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Consumer groups are identified solely by group.id and maintain independent offset tracking, regardless of subscribed topics.

- A. Incorrect: Multiple groups can share group.id
- B. Incorrect: Groups don't merge based on subscriptions
- C. Correct: Groups are isolated by ID only
- D. Incorrect: No exception occurs
```

</details>

---

## Question 4

```markdown
A consumer group using RangeAssignor has 3 members (C1-C3) and a topic with partitions p0-p5. Which statement about the assignment is true?
```

**Options**
```markdown
- A. Partitions are assigned in strict numerical order
- B. Some consumers may get more partitions than others
- C. Assignment follows a perfect round-robin distribution
- D. Partition counts per consumer differ by at most 1
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
RangeAssignor can create uneven assignments (e.g., C1:p0-p1, C2:p2-p3, C3:p4-p5) leading to imbalance.

- A. Incorrect: Numerical but not necessarily balanced
- B. Correct: Range can cause uneven distribution
- C. Incorrect: That's RoundRobin behavior
- D. Incorrect: StickyAssignor has this property
```

</details>

---

## Question 5

```markdown
What is the minimum number of consumers required to trigger a rebalance when using static membership?
```

**Options**
```markdown
- A. 1
- B. 2
- C. 50% of the group
- D. All members must agree
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
With static membership, even a single consumer joining/leaving can trigger rebalance, though the impact may be minimized.

- A. Correct: Any membership change can trigger
- B. Incorrect: No minimum participant requirement
- C. Incorrect: No quorum-based triggering
- D. Incorrect: Not a consensus process
```

</details>

---

## Question 6

```markdown
When does the group coordinator initiate a rebalance for a consumer that hasn't sent heartbeats?
```

**Options**
```markdown
- A. Immediately after first missed heartbeat
- B. After `session.timeout.ms` expires
- C. When `max.poll.interval.ms` is exceeded
- D. After 3 consecutive heartbeat failures
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
The coordinator waits for the full session timeout before declaring a consumer dead and triggering rebalance.

- A. Incorrect: Allows for temporary network issues
- B. Correct: Session timeout governs liveness
- C. Incorrect: Poll timeout is separate from heartbeats
- D. Incorrect: No fixed retry count
```

</details>

---

## Question 7

```markdown
In a group with CooperativeStickyAssignor, what happens to a partition when its assigned consumer fails?
```

**Options**
```markdown
- A. It's reassigned while other partitions remain stable
- B. All partitions undergo complete reassignment
- C. The partition remains unassigned until recovery
- D. The coordinator takes temporary ownership
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
Cooperative rebalancing only revokes and reassigns affected partitions, minimizing disruption.

- A. Correct: Minimal reassignment occurs
- B. Incorrect: That's traditional rebalancing
- C. Incorrect: Partitions don't stay unassigned
- D. Incorrect: Coordinators don't consume
```

</details>

---

## Question 8

```markdown
What occurs if a consumer joins a group but doesn't subscribe to any topics?
```

**Options**
```markdown
- A. It receives no partitions but remains in group
- B. The join fails with InvalidSubscriptionException
- C. It triggers continuous rebalance loops
- D. The coordinator assigns random partitions
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
The consumer remains group member but receives no assignments until subscribing to topics.

- A. Correct: Valid but inactive membership
- B. Incorrect: No exception is thrown
- C. Incorrect: Doesn't cause instability
- D. Incorrect: No arbitrary assignments
```

</details>

---

## Question 9

```markdown
How does Kafka prevent two consumers with the same `group.instance.id` from joining simultaneously?
```

**Options**
```markdown
- A. Session tokens invalidate duplicates
- B. The coordinator rejects conflicting registrations
- C. Zookeeper enforces unique ephemeral nodes
- D. Consumers detect and resolve conflicts peer-to-peer
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
The group coordinator maintains member registry and rejects duplicate static IDs.

- A. Incorrect: No token mechanism exists
- B. Correct: Coordinator enforces uniqueness
- C. Incorrect: Modern Kafka doesn't use Zookeeper for this
- D. Incorrect: No peer conflict resolution
```

</details>

---

## Question 10

```markdown
What happens to a consumer group's offsets when all members leave permanently?
```

**Options**
```markdown
- A. They are deleted immediately
- B. They persist according to `offsets.retention.minutes`
- C. They transfer to the coordinator broker
- D. They are compacted into a single offset
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Offsets are retained for the configured retention period before being deleted.

- A. Incorrect: Immediate deletion would break restart scenarios
- B. Correct: Retention policy governs cleanup
- C. Incorrect: Offsets stay in `__consumer_offsets`
- D. Incorrect: No compaction occurs
```

</details>

---

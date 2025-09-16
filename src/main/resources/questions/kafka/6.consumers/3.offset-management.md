## Question 1

```markdown
What happens when a Kafka consumer with `enable.auto.commit=false` calls the `commitSync()` method?
```

**Options**

```markdown
- A. The consumer commits the offsets of the messages it has processed so far
- B. The consumer commits the offsets of the messages it has fetched but not yet processed
- C. The consumer does not commit any offsets and throws an exception
- D. The consumer waits for the next batch of messages to be processed before committing offsets
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
With `enable.auto.commit=false`, the consumer must commit offsets manually. Calling `commitSync()` commits the offsets of messages already processed.

- A. Correct — commits processed message offsets.
- B. Incorrect — fetched but unprocessed messages are not committed.
- C. Incorrect — no exception is thrown.
- D. Incorrect — commitSync() commits immediately.
```

</details>

---

## Question 2

```markdown
A consumer is part of a consumer group and is currently processing messages. If the consumer crashes and is restarted, what will happen?
```

**Options**

```markdown
- A. The consumer will resume processing from the last committed offset
- B. The consumer will start processing from the earliest available offset
- C. The consumer will start processing from the latest available offset
- D. The consumer will be assigned a new set of partitions
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
When a consumer crashes and restarts, it rejoins the group and resumes from the last committed offset. This ensures no message loss or duplication as long as offsets are committed regularly.

- A. Correct — resumes from committed offset.
- B. Incorrect — does not start from earliest unless configured.
- C. Incorrect — does not start from latest unless configured.
- D. Not guaranteed — partition reassignment depends on rebalance.
```

</details>

---

## Question 3

```markdown
Which of the following is stored in the Kafka `__consumer_offsets` topic? (Select two)
```

**Options**

```markdown
- A. The latest committed offset for each consumer group
- B. The list of consumers in each consumer group
- C. The mapping of partitions to consumer groups
- D. The last produced message for each topic partition
- E. The earliest committed offset for each consumer group
```

<details><summary>Response:</summary>

**Answer:** A, C

**Explanation:**

```markdown
- A. ✅ Correct – `__consumer_offsets` tracks the latest committed offsets for consumers.
- B. ❌ Managed by the group coordinator but not stored in the topic.
- C. ✅ Correct – Mapping of partitions to consumer groups is persisted.
- D. ❌ Actual messages are stored in the respective topic partitions.
- E. ❌ Only the latest committed offset is stored, not the earliest.
```

</details>

---

## Question 4

```markdown
A consumer sends a request to commit offset 2000. Due to a network issue, the broker doesn’t receive it. The consumer continues and commits offset 3000. What should you do?
```

**Options**

```markdown
- A. Nothing
- B. Add a new consumer
- C. Restart the consumer
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
Offset 3000 commit supersedes the earlier 2000 commit. Since the latest offset is committed, no action is needed.

- A. Correct — no action necessary.
- B. Unrelated.
- C. Unnecessary.
```

</details>

---

## Question 5

```markdown
How does a consumer commit offsets in Kafka?
```

**Options**

```markdown
- A. It directly commits offsets in Zookeeper
- B. It directly sends the message to `__consumer_offsets`
- C. It interacts with the group coordinator broker
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Consumers commit offsets by communicating with the group coordinator broker, which manages offset storage in the internal `__consumer_offsets` topic.

- A. Incorrect — offset commits no longer go to Zookeeper.
- B. Incorrect — consumers do not send messages directly to the internal topic.
- C. Correct — group coordinator handles offset commits.
```

</details>

---

## Question 6

```markdown
A consumer starts and has `auto.offset.reset=none`, and the topic partition currently has data for offsets going from 45 to 2311. The consumer group has committed the offset 10 for the topic before. Where will the consumer read from?
```

**Options**

```markdown
- A. Offset 10
- B. Offset 2311
- C. Offset 45
- D. It will crash
```

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**

```markdown
Since `auto.offset.reset=none`, and the committed offset (10) has been deleted (log start offset is now 45), the consumer will crash because it has no valid starting point.

- A. Offset 10 was committed but no longer exists.
- B. Offset 2311 is the high watermark, not the starting point.
- C. Offset 45 is the earliest available offset but not used due to config.
- D. Correct — consumer crashes without valid offset to start from.
```

</details>

---

## Question 7

```markdown
To allow consumers in a group to resume at the previously committed offset, I need to set the proper value for...
```

**Options**

```markdown
- A. value.deserializer
- B. enable.auto.commit
- C. group.id
- D. auto.offset.reset
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Kafka tracks offsets per consumer group using `group.id`. Without it, committed offsets cannot be linked to the consumer.

- A. Deserializer unrelated to offset tracking.
- B. Auto commit controls offset commit, not offset resumption.
- C. Correct — group.id enables offset tracking per group.
- D. auto.offset.reset defines behavior on missing offsets.
```

</details>

---

## Question 8

```markdown
When a Kafka consumer commits offsets, what information is included in the commit request?
```

**Options**

```markdown
- A. The consumer group ID and the last processed offset for each partition
- B. The consumer group ID and the next offset to be processed for each partition
- C. The consumer ID and the last processed offset for each partition
- D. The consumer ID and the next offset to be processed for each partition
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
Offset commits include the consumer group ID and the last processed offset for each partition. The consumer ID itself is not included because offsets are tracked at the group level.

- A. Correct — Group ID and last processed offset.
- B. Incorrect — It is the last processed offset, not the next.
- C. Incorrect — Consumer ID is not part of commit.
- D. Incorrect — Next offset is not committed, last processed is.
```

</details>

---

## Question 9

```markdown
A Kafka consumer group has 3 members consuming from a topic with 6 partitions. During a rebalance, one consumer crashes before committing offsets. After the rebalance completes, what happens to the uncommitted messages from the crashed consumer's partitions?
```

**Options**
```markdown
- A. Messages are lost permanently
- B. The new consumer for those partitions reprocesses them
- C. Kafka automatically commits the offsets before reassignment
- D. The messages remain unprocessed until manually reset
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
During rebalance, uncommitted offsets remain in Kafka's log. The new consumer for those partitions will start from the last committed offset, potentially reprocessing messages.

- A. Incorrect: Kafka retains unprocessed messages in the topic.
- B. Correct: The new consumer reprocesses from the last committed offset.
- C. Incorrect: Kafka doesn't auto-commit during rebalance.
- D. Incorrect: Messages aren't orphaned - they're reassigned.
```

</details>

---

## Question 10

```markdown
A consumer using `enable.auto.commit=true` with `auto.commit.interval.ms=5000` crashes 3 seconds after processing a message but before the auto-commit. What happens to that message?
```

**Options**
```markdown
- A. It's permanently lost
- B. It's reprocessed by the same consumer after restart
- C. It's reprocessed by another consumer in the group
- D. It's skipped due to the crash
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
With auto-commit, offsets are only committed at the interval (5s here). The uncommitted message will be reassigned and reprocessed.

- A. Incorrect: Messages aren't lost, just reprocessed.
- B. Incorrect: The consumer may not get the same partitions.
- C. Correct: Another consumer will reprocess it.
- D. Incorrect: Kafka doesn't skip messages due to crashes.
```

</details>

---

## Question 11

```markdown
In a transactional producer-consumer setup with `isolation.level=read_committed`, what happens if a consumer polls messages while a transaction is in progress?
```

**Options**
```markdown
- A. It sees all messages including uncommitted ones
- B. It sees only messages from committed transactions
- C. It blocks until the transaction completes
- D. It receives a special "transaction in progress" marker
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
With `read_committed`, consumers only see messages from completed transactions, maintaining atomicity.

- A. Incorrect: That would be `read_uncommitted`.
- B. Correct: Only committed transaction messages are visible.
- C. Incorrect: Consumers aren't blocked by transactions.
- D. Incorrect: No special markers are sent.
```

</details>

---

## Question 12

```markdown
A consumer manually commits offset 100 for partition 0, then processes but crashes before committing offset 150. After restart, it's assigned partition 0 again. What's the first offset it will receive?
```

**Options**
```markdown
- A. 100 (last committed offset)
- B. 101 (next after last commit)
- C. 150 (where it crashed)
- D. Depends on `auto.offset.reset`
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Consumers always start from the next offset after the last committed one (101 in this case), regardless of crash state.

- A. Incorrect: 100 was already processed.
- B. Correct: Consumers start from next unprocessed offset.
- C. Incorrect: The consumer never committed 150.
- D. Incorrect: `auto.offset.reset` only applies with no commit history.
```

</details>

---

## Question 13

```markdown
What happens if you configure `enable.auto.commit=true` AND manually call `commitSync()` in a consumer?
```

**Options**
```markdown
- A. Offsets are committed twice, causing duplicates
- B. The manual commit overrides the auto-commit
- C. Kafka throws a configuration exception
- D. The auto-commit interval resets after manual commit
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Manual commits take precedence - the last commit (manual or auto) determines the actual offset position.

- A. Incorrect: Kafka tracks offsets, not commit counts.
- B. Correct: Last commit wins, regardless of source.
- C. Incorrect: This configuration is allowed.
- D. Incorrect: The auto-commit timer isn't affected.
```

</details>

---

## Question 14

```markdown
A consumer group has `auto.offset.reset=earliest` with no prior commits. The topic has 1000 messages which are then deleted via retention. When new consumers join, what happens?
```

**Options**
```markdown
- A. They start from offset 0 and get "message not found" errors
- B. They start from the earliest available offset (now empty)
- C. They start from the latest offset (end of log)
- D. They fail to start with an offset out of range error
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
When the configured reset offset (earliest=0) is unavailable due to retention, Kafka falls back to the log end offset.

- A. Incorrect: Kafka handles missing offsets gracefully.
- B. Incorrect: The "earliest" offset no longer exists.
- C. Correct: Falls back to latest when earliest is gone.
- D. Incorrect: This would happen with `auto.offset.reset=none`.
```

</details>

---

## Question 15

```markdown
In a consumer group with 10 partitions and 5 consumers, one consumer fails after processing but before committing offsets for partitions 8-9. During rebalance, what's the minimum number of messages that could be reprocessed?
```

**Options**
```markdown
- A. All messages since the last commit for partitions 8-9
- B. Only the last message from each partition
- C. Zero if another consumer had already committed those offsets
- D. Exactly one message per partition
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
The worst-case scenario requires reprocessing all uncommitted messages from the failed consumer's partitions.

- A. Correct: Minimum reprocessing is all uncommitted messages.
- B. Incorrect: Kafka doesn't track "last message" specially.
- C. Incorrect: Offsets are per-group, not per-consumer.
- D. Incorrect: No such guarantee exists in Kafka.
```

</details>

---
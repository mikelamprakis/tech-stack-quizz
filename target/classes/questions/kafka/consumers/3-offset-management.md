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
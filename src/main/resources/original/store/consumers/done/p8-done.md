## Question 14

```markdown
Which actions will trigger a partition rebalance for a consumer group? (Select 3)
```

**Options**

```markdown
- A. Add the broker to the cluster
- B. Remove the broker from the cluster
- C. Add a new consumer to the group
- D. Increase partitions of the topic
- E. A consumer in the group shuts down
```

<details><summary>Response:</summary>

**Answer:** C, D, E

**Explanation:**

```markdown
Rebalances occur when consumers join or leave the group or when partitions change.

- A. Adding brokers does not trigger rebalance.
- B. Removing brokers does not trigger rebalance.
- C. Adding a consumer triggers rebalance.
- D. Increasing partitions triggers rebalance.
- E. Consumer shutdown triggers rebalance.
```

</details>

---

## Question 15

````markdown
What delivery guarantee does this consumer code offer?

```java
while (true) {
    ConsumerRecords<String, String> records = consumer.poll(100);
    try {
        consumer.commitSync();
    } catch (CommitFailedException e) {
        log.error("commit failed", e);
    }
    for (ConsumerRecord<String, String> record : records) {
        System.out.printf("topic = %s, partition = %s, offset = %d, key = %s, value = %s\n",
            record.topic(), record.partition(), record.offset(), record.key(), record.value());
    }
}
````

````

**Options**
```markdown
- A. At-most once
- B. Exactly once
- C. At-least once
````

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
Offsets are committed **before** processing records, so if the consumer crashes after committing but before processing, messages will be lost.

- A. Correct — at-most once delivery.
- B. Not exactly once, as processing may be skipped.
- C. Incorrect — not at-least once.
```

</details>

---

## Question 16

```markdown
A producer uses `acks=1` and sends a message to the leader. When will a consumer see this message?
```

**Options**

```markdown
- A. When the High Watermark has advanced
- B. Right away
- C. Never — the produce request will fail
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
Consumers only read messages up to the High Watermark (HW), which advances after the message is replicated to all in-sync replicas. With `acks=1`, only the leader acknowledges the write, so the HW advances later.

- A. Correct — consumer sees message after HW advances.
- B. Incorrect — consumer does not see immediately.
- C. Incorrect — message does not fail.
```

</details>

---

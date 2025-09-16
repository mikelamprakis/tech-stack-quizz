## Question 1

```markdown
What is the purpose of the `group.id` property in a Kafka consumer configuration?
```

**Options**

```markdown
- A. To specify the ID of the consumer within a consumer group
- B. To specify the ID of the consumer group the consumer belongs to
- C. To specify the ID of the Kafka cluster the consumer connects to
- D. To specify the ID of the partitions the consumer should read from
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
`group.id` identifies the consumer group to which the consumer belongs. Consumers with the same group ID share partition consumption and offset commits.

- A. Incorrect — consumer instance ID is not `group.id`.
- B. Correct — identifies the consumer group.
- C. Incorrect — Kafka cluster ID is configured elsewhere.
- D. Incorrect — partitions are assigned separately.
```

</details>

---

## Question 2

```markdown
What is the purpose of the `max.poll.records` setting in the Kafka consumer configuration?
```

**Options**

```markdown
- A. To specify the maximum number of records to return in a single poll
- B. To control the maximum amount of data the consumer can receive per second
- C. To set the maximum number of partitions the consumer can subscribe to
- D. To determine the maximum number of consumers allowed in a consumer group
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
The `max.poll.records` setting specifies the maximum number of records returned in a single call to `poll()`. This allows controlling batch size for memory and processing reasons.

- A. Correct — controls maximum records per poll.
- B. It does not control data rate per second.
- C. Number of partitions is unrelated.
- D. Consumer group size is not controlled here.
```

</details>

---

## Question 3

```markdown
What is the recommended approach to process messages concurrently using the KafkaConsumer?
```

**Options**

```markdown
- A. Create a single KafkaConsumer instance and share it among multiple threads
- B. Create multiple KafkaConsumer instances, each running in its own thread
- C. Use a thread pool to process messages from a single KafkaConsumer instance
- D. Use a lock or synchronization mechanism to coordinate access to a shared KafkaConsumer instance
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
KafkaConsumer is not thread-safe. The recommended approach is to create multiple consumer instances, each in its own thread, allowing safe concurrent consumption.

- A. Incorrect — sharing one consumer across threads is unsafe.
- B. Correct — multiple consumers for concurrency.
- C. Incorrect — a single consumer with thread pool is unsafe.
- D. Incorrect — locking adds complexity and is discouraged.
```

</details>

---

## Question 4

```markdown
What happens when a new consumer joins an existing consumer group?
```

**Options**

```markdown
- A. The new consumer will start consuming from the earliest available offset for all partitions
- B. The new consumer will start consuming from the latest available offset for all partitions
- C. The new consumer will be assigned a subset of partitions and start consuming from the last committed offset for each partition
- D. The new consumer will wait until the next rebalance before starting to consume
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
When a new consumer joins an existing group, Kafka triggers a rebalance to distribute partitions among all consumers. The new consumer gets a subset of partitions and resumes from the last committed offset for each.

- A. Incorrect — offset depends on last committed offset.
- B. Incorrect — offset depends on last committed offset.
- C. Correct — rebalance and resume from committed offsets.
- D. Incorrect — rebalance happens immediately.
```

</details>

---

## Question 5

```markdown
There are 3 producers writing to a topic with 5 partitions. There are 10 consumers in the same consumer group. How many consumers will remain idle?
```

**Options**

```markdown
- A. 3
- B. 5
- C. 10
- D. None
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Only 5 partitions are available, so only 5 consumers can be assigned partitions. The other 5 consumers remain idle.

- A. Incorrect.
- B. Correct — 5 consumers idle.
- C. Incorrect.
- D. Incorrect.
```

</details>

---

## Question 6

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

## Question 7

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

## Question 8

```markdown  
You have a Kafka cluster with 5 brokers and a topic with 10 partitions. You want to consume messages from this topic using a consumer group with 3 consumers. What is the maximum number of partitions that can be assigned to a single consumer?  
```  

**Options**
```markdown  
- A. 3  
- B. 4  
- C. 5  
- D. 10  
```  

<details><summary>Response:</summary>  

**Answer:** B

**Explanation:**

```markdown  
Kafka distributes partitions as evenly as possible among consumers.  

- A. Would leave 1 partition unassigned  
- B. Correct. 4+3+3 distribution covers all 10 partitions  
- C. Would require at least 2 consumers with 5 partitions each  
- D. All partitions can't be assigned to one consumer in a group  
```  

</details>  

---  

## Question 9

```markdown  
You have a Kafka cluster with 3 brokers and a topic with 12 partitions. You want to create a consumer group with 4 consumers to consume messages from this topic. How many consumers will be actively consuming messages?  
```  

**Options**
```markdown  
- A. 1  
- B. 3  
- C. 4  
- D. 12  
```  

<details><summary>Response:</summary>  

**Answer:** C

**Explanation:**

```markdown  
All consumers will be active when partitions > consumers.  

- A. Would leave 11 partitions unprocessed  
- B. Would leave 3 partitions unprocessed  
- C. Correct. 12 partitions distributed to 4 consumers (3 each)  
- D. Consumers can't be assigned more than one partition each  
```  

</details>  

---  

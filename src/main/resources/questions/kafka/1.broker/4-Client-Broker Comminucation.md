
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

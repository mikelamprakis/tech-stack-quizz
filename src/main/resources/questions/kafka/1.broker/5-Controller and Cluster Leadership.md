## Question 7

```markdown
The Controller is a broker that is... (select two)
```

**Options**

```markdown
- A. elected by broker majority
- B. elected by Zookeeper ensemble
- C. is responsible for partition leader election
```

<details><summary>Response:</summary>

**Answer:** B, C

**Explanation:**

```markdown
The Controller broker is elected by the Zookeeper ensemble and is responsible for partition leader election. Only one broker acts as Controller at a time.

- A. Incorrect — not elected by broker majority.
- B. Correct — elected by Zookeeper ensemble.
- C. Correct — responsible for partition leader election.
```

</details>

---

## Question 12

```markdown
In a Kafka cluster, the Controller is a critical component for managing cluster state. Which of the following statements accurately describe the role and election of the Controller? (Select two)

```

**Options**

```markdown
- A. Elected by broker majority
- B. Elected by Zookeeper ensemble
- C. Responsible for partition leader election
- D. Manages consumer group offsets
- E. Automatically assigns replicas to brokers based on load
```

<details><summary>Response:</summary>

**Answer:** B and C

**Explanation:**

```markdown
- B. Correct — the Controller is elected by the Zookeeper ensemble using ephemeral znodes.
- C. Correct — the Controller manages partition leader election, especially after failures.
- A. Incorrect — election is not by broker majority.
- D. Incorrect — consumer offset management is handled by Kafka brokers and stored in `__consumer_offsets`.
- E. Incorrect — replica assignment is done at topic creation or manually, not automatically by Controller.

Note: Newer KRaft mode changes this, but Zookeeper is still common.
```

</details>

---

## Question 13

```markdown
In the context of Kafka's distributed architecture, broker elections are vital for cluster health and stability. Consider the following advanced scenarios where Kafka's internal mechanisms must decide on leadership roles:

```

**Options**

```markdown
- A. The Zookeeper ensemble elects the new Controller based on ephemeral node creation sequence.
- B. The new partition leader is elected based on the ISR list order, favoring replicas with the most recent updates.
- C. Brokers in the main cluster segment with access to Zookeeper retain their roles, while isolated brokers step down until connectivity is restored.
- D. A broker majority within the isolated segment elects a new temporary Controller until the network partition is resolved.
- E. The election of a new partition leader among replicas with identical network latency is determined by a random selection process.
```

<details><summary>Response:</summary>

**Answer:** A, B, and C

**Explanation:**

```markdown
- A. Correct — Zookeeper elects Controller via ephemeral nodes based on creation sequence.
- B. Correct — partition leader election uses the ISR list to pick the most recent replica.
- C. Correct — brokers without Zookeeper connection lose leadership roles.
- D. Incorrect — Kafka does not elect Controllers within isolated network segments.
- E. Incorrect — leader election is deterministic, not random.
```

</details>
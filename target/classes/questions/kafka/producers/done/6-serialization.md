## Question 24

```markdown
Which of the following is true about the relationship between producers and consumers in Kafka?
```

**Options**

```markdown
- A. Producers and consumers must use the same serialization format
- B. Producers and consumers must be written in the same programming language
- C. Producers and consumers are decoupled by the Kafka topic
- D. Producers must know about the consumers to send messages to them
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Kafka decouples producers and consumers via topics.

- A. False – Kafka supports many serialization formats.
- B. False – Kafka is language-agnostic.
- C. True – Producers and consumers operate independently through topics.
- D. False – Producers don’t know or care about consumers.
```

</details>

---
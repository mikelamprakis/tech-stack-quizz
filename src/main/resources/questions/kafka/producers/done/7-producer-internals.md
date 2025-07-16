## Question 5

```markdown
When is the `onCompletion()` method called in a Producer Callback?
```

**Options**

```markdown
- A. Immediately after serialization
- B. Immediately when `send()` is called
- C. When the broker response is received
- D. Only on failure
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
The `onCompletion()` method is triggered when Kafka receives a response from the broker, either success or failure. It’s an asynchronous callback used to handle send results.

- A. Not related to serialization
- B. `send()` is async and returns before callback
- C. Correct — callback happens post-response
- D. Incorrect — also called on success
```

</details>

---

## Question 29

```markdown
How does Kafka's zero-copy optimization handle data transformation or modification?
```

**Options**

```markdown
- A. It automatically applies data transformations during the zero-copy process
- B. It allows custom data transformations to be plugged into the zero-copy mechanism
- C. It does not support data transformations and sends data as-is
- D. It performs data transformations after the data is copied into the application's memory
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Kafka’s zero-copy uses the `sendfile` system call to transfer bytes directly from the page cache to the network socket. No transformation is applied during this process—data is passed through as-is, which boosts performance.

- A. Incorrect – no transformations happen during zero-copy.
- B. Incorrect – no hooks for transformation in zero-copy.
- C. Correct – data is sent as-is.
- D. Incorrect – transformations aren’t performed post-copy either.
```

</details>

---
## Question 1

```markdown
What is the primary purpose of subject naming strategies in a multi-tenant Kafka setup?
```

**Options**

```markdown
- A. Compress schema data for faster delivery
- B. Assign schemas to specific brokers
- C. Prevent schema conflicts between tenants
- D. Encrypt Avro payloads for each tenant
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Compression is handled at a different layer.
- B. Brokers are not schema-aware.
- C. Correct – Subject naming strategies help isolate schemas between tenants.
- D. Schema Registry doesn’t handle encryption.
```

</details>

---

## Question 2

```markdown
In the default subject naming strategy, what does the subject name typically include?
```

**Options**

```markdown
- A. A random UUID
- B. Just the topic name
- C. Topic name and record name
- D. Broker ID and record type
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. UUIDs are not part of the strategy.
- B. Too simplistic and causes schema conflicts.
- C. Correct – Default strategy is `<topic>-<recordType>` (e.g., `users-value`).
- D. Brokers aren't referenced in subject names.
```

</details>

---

## Question 3

```markdown
Which subject naming strategy is best suited for multi-tenant environments?
```

**Options**

```markdown
- A. TopicNameStrategy
- B. RecordNameStrategy
- C. TopicRecordNameStrategy
- D. RandomSubjectStrategy
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. May cause conflicts across tenants with the same record name.
- B. Uses only the record name, which isn't tenant-aware.
- C. Correct – Combines topic and record name for better isolation.
- D. Not a real strategy.
```

</details>

---

## Question 4

```markdown
Which naming strategy uses only the Avro record's fully qualified name as the subject?
```

**Options**

```markdown
- A. TopicNameStrategy
- B. RecordNameStrategy
- C. TopicRecordNameStrategy
- D. FieldNameStrategy
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- A. Uses topic + suffix (`-key`/`-value`)
- B. Correct – RecordNameStrategy uses only the record name.
- C. Combines topic and record name.
- D. FieldNameStrategy does not exist.
```

</details>

---

## Question 5

```markdown
Why can TopicNameStrategy cause problems in multi-tenant environments?
```

**Options**

```markdown
- A. It disables compatibility checks
- B. It prevents schema evolution
- C. It can cause schema conflicts across tenants using the same topic
- D. It randomly assigns schema IDs
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Compatibility is still enforced.
- B. Evolution works, but conflicts can still arise.
- C. Correct – Same topic name across tenants may result in shared subjects.
- D. Schema IDs are deterministic.
```

</details>

---

## Question 6

```markdown
What does `TopicRecordNameStrategy` prevent in multi-tenant Kafka setups?
```

**Options**

```markdown
- A. Consumer group rebalancing
- B. Schema duplication across topics
- C. Compatibility issues between Avro and Protobuf
- D. Conflicts between schemas using the same record name
```

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**

```markdown
- A. Unrelated to schema registry.
- B. Duplication may still happen but is less likely.
- C. Irrelevant – Avro/Protobuf managed separately.
- D. Correct – Combines topic and record name to avoid same-name record conflicts.
```

</details>

---

## Question 7

```markdown
Which naming strategy is ideal for sharing a schema across multiple topics?
```

**Options**

```markdown
- A. RecordNameStrategy
- B. TopicNameStrategy
- C. TopicRecordNameStrategy
- D. SharedTopicStrategy
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
- A. Correct – Uses only the record name, enabling reuse across topics.
- B. Ties schema to specific topics.
- C. Combines both – not ideal for reuse.
- D. Not a valid strategy.
```

</details>

---

## Question 8

```markdown
When using `RecordNameStrategy`, what must developers ensure for compatibility?
```

**Options**

```markdown
- A. Every schema has a unique ID
- B. Schemas must be manually registered
- C. Schema names must be globally unique
- D. Schema evolution is disabled
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. The registry handles IDs.
- B. Auto-registration is possible.
- C. Correct – Since record name is the subject, it must be unique across tenants.
- D. Evolution still applies.
```

</details>

---

## Question 9

```markdown
What is a good strategy for automating schema management in multi-tenant pipelines?
```

**Options**

```markdown
- A. Use the default strategy and manual versioning
- B. Use unique record names per tenant and automate registration
- C. Register all schemas once and reuse them blindly
- D. Avoid using Schema Registry in multi-tenant systems
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- A. Manual versioning is error-prone.
- B. Correct – Unique names and automated flows avoid conflicts.
- C. Reuse can cause compatibility violations.
- D. Schema Registry is designed to support multi-tenancy.
```

</details>

---

## Question 10

```markdown
Which of the following best describes a 'subject' in Schema Registry terminology?
```

**Options**

```markdown
- A. A message key
- B. A Kafka topic
- C. A unique identifier for a schema version group
- D. A Protobuf package
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Subject may refer to key/value schemas but isn't the key itself.
- B. Topic is often part of a subject, not the subject itself.
- C. Correct – A subject groups versions of a specific schema.
- D. Protobuf package names are unrelated to subjects.
```

</details>

## Question 11

```markdown
Where does the Confluent Schema Registry store its own configuration?
```

**Options**

```markdown
- A. In Zookeeper
- B. In a Kafka topic
- C. On the filesystem
- D. In a database
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
The Confluent Schema Registry uses Zookeeper to store its own configuration. When the Schema Registry starts up, it reads its configuration from a Zookeeper path, which defaults to `/schema-registry`.

Some of the key configuration properties stored in Zookeeper include:

- `kafkastore.topic`: The Kafka topic that the Schema Registry uses to store schema data.
- `master.eligibility`: Whether the instance is eligible to be the master.
- `host.name`: The host name to use for the Schema Registry instance.
- `port`: The port to run the Schema Registry instance on.

So while the Schema Registry uses a Kafka topic to store the actual schema data, it uses Zookeeper for its own configuration.

- B is incorrect because the Kafka topic stores schema data, not configuration.
- C and D are incorrect because the Schema Registry does not use filesystem or a database for its configuration.
```

</details>

---

## Question 12

```markdown
How does the Confluent Schema Registry ensure high availability?
```

**Options**

```markdown
- A. By running multiple instances and electing a master
- B. By relying on the availability of the underlying Kafka cluster
- C. By replicating data across multiple Zookeeper instances
- D. By using a distributed consensus protocol among instances
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
The Confluent Schema Registry ensures high availability by running multiple instances in a cluster mode and electing one instance as the master.

Key points:

- Multiple Schema Registry instances run simultaneously.
- One instance is elected as the "master," responsible for handling all write requests like new schema registrations and config changes.
- All instances can serve read requests.
- If the master fails, a new master is elected automatically.
- This process ensures continuous availability and consistency for writes and scalable read performance.

- B is incorrect because high availability is not solely reliant on Kafka's availability.
- C is incorrect because Zookeeper is used for configuration, not for data replication for HA.
- D is incorrect because the Schema Registry does not implement a distributed consensus protocol like Raft or Paxos; it uses a simpler master election mechanism.
```

</details>


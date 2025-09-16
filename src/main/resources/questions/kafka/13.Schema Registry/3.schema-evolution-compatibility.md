## Question 1

```markdown
What does "schema evolution" mean in the context of Apache Avro and Kafka?
```

**Options**

```markdown
- A. Changing how messages are serialized
- B. Automatically generating schemas
- C. Modifying a schema over time while maintaining data compatibility
- D. Upgrading Kafka brokers with new schema features
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Serialization method is not the core idea.
- B. Schema generation is a separate concern.
- C. Correct – Schema evolution is the process of changing schemas while ensuring they remain compatible with old/new data.
- D. Broker upgrades have nothing to do with schema evolution.
```

</details>

---

## Question 2

```markdown
Which compatibility type allows consumers using the new schema to read data written with the old schema?
```

**Options**

```markdown
- A. Backward
- B. Forward
- C. Full
- D. None
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
- A. Correct – Backward compatibility means the new schema can read old data.
- B. Forward compatibility is the opposite direction.
- C. Full includes both backward and forward.
- D. "None" implies no compatibility rules.
```

</details>

---

## Question 3

```markdown
Which compatibility type allows old consumers to read data produced with the new schema?
```

**Options**

```markdown
- A. Backward
- B. Forward
- C. Full
- D. Strict
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- A. Backward is the reverse direction.
- B. Correct – Forward compatibility lets old consumers read new data.
- C. Full includes both directions but this specifically refers to forward.
- D. Strict is not a valid compatibility type in Avro.
```

</details>

---

## Question 4

```markdown
What is “full” compatibility in Schema Registry?
```

**Options**

```markdown
- A. Consumers can only read messages from the current version
- B. Messages are always encoded in JSON and Avro
- C. Both forward and backward compatibility are ensured
- D. Schema Registry enforces encryption
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. That’s the opposite of compatibility.
- B. JSON encoding isn’t required for compatibility.
- C. Correct – Full compatibility ensures data can be read in both directions (old→new and new→old).
- D. Compatibility settings don’t relate to encryption.
```

</details>

---

## Question 5

```markdown
Which of the following schema changes is considered **backward-compatible**?
```

**Options**

```markdown
- A. Removing a required field
- B. Adding a required field with no default
- C. Adding an optional field with a default value
- D. Renaming a field
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Removing fields breaks old data.
- B. Required fields with no default cause deserialization failures.
- C. Correct – Optional fields with defaults don’t break compatibility.
- D. Renaming breaks field mapping unless aliases are used.
```

</details>

---

## Question 6

```markdown
What happens if you register a schema that breaks the current compatibility setting?
```

**Options**

```markdown
- A. It’s accepted and logged
- B. It replaces the previous version
- C. Schema Registry rejects it with an error
- D. It disables the compatibility check temporarily
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Violations are not just logged—they're blocked.
- B. Incompatible schemas are not accepted.
- C. Correct – Schema Registry enforces compatibility rules strictly.
- D. No automatic disabling occurs.
```

</details>

---

## Question 7

```markdown
What is the default compatibility setting in Confluent Schema Registry?
```

**Options**

```markdown
- A. NONE
- B. BACKWARD
- C. FORWARD
- D. FULL
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- A. NONE disables compatibility checks.
- B. Correct – BACKWARD is the default to support consumers reading older data.
- C. FORWARD is used less frequently.
- D. FULL is stricter than the default.
```

</details>

---

## Question 8

```markdown
Which of the following changes **violates** forward compatibility?
```

**Options**

```markdown
- A. Adding a new optional field with a default
- B. Removing a field
- C. Adding a new record field with a default
- D. Keeping the schema unchanged
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- A. This is forward-compatible.
- B. Correct – Removing a field breaks older consumers expecting it.
- C. Adding with a default is safe.
- D. No change means no compatibility issue.
```

</details>

---

## Question 9

```markdown
Why is schema evolution important in Kafka-based systems?
```

**Options**

```markdown
- A. To increase Kafka partition count automatically
- B. To allow applications to evolve independently without breaking
- C. To reduce Kafka disk usage
- D. To avoid using serializers
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- A. Partitioning is unrelated to schema evolution.
- B. Correct – It helps different services evolve schemas independently while maintaining data compatibility.
- C. Storage is a separate concern.
- D. Serializers are still required.
```

</details>

---

## Question 10

```markdown
Which tool can you use to test schema compatibility before deployment?
```

**Options**

```markdown
- A. Kafka Connect CLI
- B. Kafka broker config
- C. Schema Registry REST API
- D. Zookeeper shell
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Kafka Connect CLI doesn’t handle schema validation directly.
- B. Broker config does not validate Avro schemas.
- C. Correct – The Schema Registry REST API exposes endpoints for compatibility checks.
- D. Zookeeper has no role in schema validation.
```

</details>

## Question 11

```markdown
In Avro, removing a field that does not have a default value is what kind of schema evolution?
```

**Options**

```markdown
- A. Backward
- B. Full
- C. Forward
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
Removing a field without a default value is considered backward-compatible schema evolution.

- A is correct because clients with the new schema (without the field) can still read records written with the old schema.
- B (full) means both backward and forward compatibility, which is stronger.
- C (forward) means new schema can read old data, but removing a field without a default does not guarantee forward compatibility.
```

</details>



## Question 12

```markdown
How does the Confluent Schema Registry ensure compatibility between different versions of a schema?
```

**Options**

```markdown
- A. By enforcing strict backward compatibility for all schema changes
- B. By allowing schema changes that are both backward and forward compatible
- C. By automatically generating compatibility reports for schema versions
- D. By using a compatibility setting to define allowed schema evolution rules
```

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**

```markdown
Schema Registry uses configurable compatibility settings to validate new schema versions against older ones.

- A. ❌ Not strictly enforced unless configured
- B. ❌ Full compatibility isn't the default
- C. ❌ Compatibility is enforced, not just reported
- D. ✅ Correct – Compatibility rules are defined via settings like BACKWARD, FORWARD, etc.
```

</details>

---

## Question 13

```markdown
What is the default compatibility setting in the Confluent Schema Registry for schema evolution?
```

**Options**

```markdown
- A. BACKWARD
- B. FORWARD
- C. FULL
- D. NONE
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
By default, Schema Registry enforces BACKWARD compatibility so consumers can read new data with older schemas.

- A. ✅ Correct – Default mode, good for rewindable consumers
- B. ❌ Not the default
- C. ❌ FULL includes BACKWARD and FORWARD, but not default
- D. ❌ NONE disables compatibility checks
```

</details>

---

## Question 14

```markdown
What is the impact of removing a required field that has a default value in an Avro schema?
````

**Options**

```markdown
- A. It is a backward compatible change
- B. It is a forward compatible change
- C. It is both a backward and forward compatible change
- D. It is an incompatible change
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
Removing a required field with a default value is backward compatible. Older readers can still interpret new data using defaults.

- A. ✅ Correct – Defaults allow compatibility
- B. ❌ Not forward compatible because older data lacks the field
- C. ❌ Only backward compatible
- D. ❌ Not fully incompatible due to default
```

</details>

---

## Question 15

```markdown
What compatibility level is maintained when adding a new optional field to an Avro schema?
```

**Options**

```markdown
- A. Backward compatibility
- B. Forward compatibility
- C. Full compatibility
- D. No compatibility
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Adding a field with a default value makes it optional. Both old and new schemas can interpret each other's data, ensuring full compatibility.

- A. ❌ Only partial view
- B. ❌ Forward compatibility is just one side
- C. ✅ Correct – Supports both directions
- D. ❌ There is compatibility
```

</details>

---

## Question 16

```markdown
What is the effect of changing the data type of a field in an Avro schema?
```

**Options**

```markdown
- A. It is a backward compatible change
- B. It is a forward compatible change
- C. It is both a backward and forward compatible change
- D. It is an incompatible change
```

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**

```markdown
Changing the type alters how data is serialized. Readers or writers expecting the old type will fail to interpret the data.

- A. ❌ Breaks old consumers
- B. ❌ Breaks new readers
- C. ❌ Not compatible in either direction
- D. ✅ Correct – Type change = incompatibility
```

</details>

---

## Question 17

```markdown
In the Confluent Schema Registry, what is the default compatibility setting for new schemas?
```

**Options**

```markdown
- A. BACKWARD
- B. FORWARD
- C. FULL
- D. NONE
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
BACKWARD is the default compatibility mode in Schema Registry. It ensures data written with newer schemas can be consumed using older schema versions.

- A. ✅ Correct – Ideal for Kafka consumers that rewind
- B. ❌ Not default
- C. ❌ FULL requires explicit configuration
- D. ❌ NONE disables safety checks
```

</details>


## Question 18

```markdown
How does Confluent Schema Registry ensure compatibility when registering a new schema version?
```

**Options**

```markdown
- A. By comparing the new schema with the latest version only
- B. By comparing the new schema with all previous versions
- C. By comparing the new schema with a user-specified set of previous versions
- D. By ignoring previous versions and only validating the new schema
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
The Schema Registry compares new schemas with the latest registered version based on the compatibility mode.

- A. Correct — compared against the latest
- B. Not how compatibility is enforced
- C. User-specified comparison is not supported
- D. Registry does not ignore older versions
```

</details>

---

## Question 19

```markdown
What compatibility mode allows a new schema to both read data written by an old schema and write data that can be read by the old schema?
```

**Options**

```markdown
- A. BACKWARD
- B. FORWARD
- C. FULL
- D. NONE
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
FULL mode ensures both backward and forward compatibility.

- A. Ensures only backward compatibility
- B. Ensures only forward compatibility
- C. Correct — ensures both directions
- D. Disables compatibility checks
```

</details>

---

## Question 20

```markdown
What is the default compatibility mode in Confluent Schema Registry?
```

**Options**

```markdown
- A. BACKWARD
- B. FORWARD
- C. FULL
- D. NONE
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
By default, Confluent Schema Registry enforces BACKWARD compatibility.

- A. Correct — default mode
- B. Optional mode, not default
- C. Must be explicitly configured
- D. Compatibility is not disabled by default
```

</details>

---

## Question 21

```markdown
How does the Confluent Schema Registry handle schema evolution?
```

**Options**

```markdown
- A. By automatically converting old schemas to new schemas
- B. By storing all schema versions and applying compatibility checks
- C. By enforcing schema changes directly on the producer side
- D. By modifying the schema directly in the consumer application
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Schema evolution is handled by storing versions and validating new ones.

- A. No automatic schema transformation
- B. Correct — uses stored versions and checks
- C. Compatibility checks are enforced at registration, not producer
- D. Registry does not alter consumer schema
```

</details>

---

## Question 22

```markdown
What happens if a schema fails the compatibility check when being registered in the Confluent Schema Registry?
```

**Options**

```markdown
- A. The schema is registered with a warning
- B. The schema is rejected, and an error is returned
- C. The schema is registered, but compatibility is disabled
- D. The schema is registered with a lower priority
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Schemas failing compatibility checks are rejected outright.

- A. No warnings — hard failure
- B. Correct — registration is blocked
- C. Compatibility mode is enforced unless changed manually
- D. Registry has no schema prioritization
```

</details>

---

## Question 23

```markdown
What is the purpose of the compatibility setting in the Confluent Schema Registry?
```

**Options**

```markdown
- A. It defines which serialization format (Avro, Protobuf) is used.
- B. It controls how schemas can evolve over time.
- C. It sets the compatibility between different Schema Registry versions.
- D. It configures compatibility between the Schema Registry and Kafka brokers.
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Compatibility settings control how schema versions evolve safely.

- A. Incorrect – Format choice is separate.
- B. Correct – Defines forward/backward/full compatibility.
- C. Incorrect – Registry version compatibility is unrelated.
- D. Incorrect – Brokers and registry don't have such compatibility settings.
```

</details>

---

## Question 24

```markdown
In Avro, what is the effect of adding a field to a record schema without a default value?
```

**Options**

```markdown
- A. It is a backward compatible change
- B. It is a forward compatible change
- C. It is both a backward and forward compatible change
- D. It is an incompatible change
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Adding a field without a default breaks backward compatibility.

- A. Incorrect – Breaks backward compatibility.
- B. Correct – Forward compatible since old readers ignore new fields.
- C. Incorrect – Not compatible both ways.
- D. Incorrect – Not entirely incompatible.
```

</details>

---

## Question 25

```markdown
What is the Avro schema evolution rule for removing a field?
```

**Options**

```markdown
- A. It is always a compatible change
- B. It is a backward compatible change
- C. It is a forward compatible change
- D. It is an incompatible change
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Removing a field is backward compatible but not forward compatible.

- A. Incorrect – Not always compatible.
- B. Correct – Readers can ignore missing fields.
- C. Incorrect – Forward compatibility is broken.
- D. Incorrect – Not completely incompatible.
```

</details>

---

## Question 26

```markdown
In Avro, what is the compatibility implication of changing the name of a record schema?
```

**Options**

```markdown
- A. It is a backward compatible change
- B. It is a forward compatible change
- C. It is both a backward and forward compatible change
- D. It is an incompatible change
```

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**

```markdown
Changing the record name breaks both forward and backward compatibility.

- A. Incorrect – Breaks backward compatibility.
- B. Incorrect – Breaks forward compatibility.
- C. Incorrect – Not compatible both ways.
- D. Correct – Incompatible change due to schema name mismatch.
```

</details>

---
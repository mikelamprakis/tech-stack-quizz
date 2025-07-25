## Question 1

```markdown
What does "strongly-typed" mean in the context of Kafka producers and consumers using Avro?
```

**Options**

```markdown
- A. You manually write JSON strings
- B. You use runtime reflection to map fields
- C. You use generated Java classes from Avro schemas
- D. You rely only on schema IDs at runtime
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. JSON doesn't provide compile-time type safety.
- B. Reflection is dynamic, not strongly-typed.
- C. Correct – Avro generates Java classes, giving compile-time checks.
- D. Schema IDs are used, but they don't provide strong typing on their own.
```

</details>

---

## Question 2

```markdown
How do you generate Java classes from an Avro schema file?
```

**Options**

```markdown
- A. Using the Kafka broker’s built-in generator
- B. With the Schema Registry API
- C. With the `avro-tools` or Maven plugin during build
- D. By writing them manually from the Avro definition
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Kafka brokers do not generate Java code.
- B. Schema Registry serves schemas, but doesn't generate code.
- C. Correct – `avro-tools` and Maven plugins are commonly used to generate Java classes.
- D. Manual implementation defeats the purpose of automation and type safety.
```

</details>

---

## Question 3

```markdown
What is the main benefit of using generated Avro Java classes in Kafka applications?
```

**Options**

```markdown
- A. Smaller message size
- B. Full runtime flexibility
- C. Compile-time type checking and IDE support
- D. No need for a Schema Registry
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Message size is a benefit of Avro itself, not generated classes.
- B. Strong typing limits flexibility but improves safety.
- C. Correct – Type safety and IDE support are key benefits.
- D. Schema Registry is still needed for (de)serialization coordination.
```

</details>

---

## Question 4

```markdown
What happens if you try to send an object that doesn't match the Avro schema using KafkaAvroSerializer?
```

**Options**

```markdown
- A. Kafka will convert it automatically
- B. Schema Registry will rewrite the schema
- C. A serialization exception will occur
- D. It will fall back to JSON encoding
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. No automatic conversion is performed.
- B. Schema Registry never rewrites schemas.
- C. Correct – The serializer expects objects matching the schema.
- D. Kafka does not fall back to other formats automatically.
```

</details>

---

## Question 5

```markdown
Which interface must your Avro-generated Java classes implement to be serializable by KafkaAvroSerializer?
```

**Options**

```markdown
- A. Serializable
- B. AvroType
- C. SpecificRecord
- D. KafkaMessage
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Java's `Serializable` isn’t related to Avro.
- B. `AvroType` is not a valid interface.
- C. Correct – `SpecificRecord` is the Avro interface implemented by generated classes.
- D. `KafkaMessage` is not part of Kafka or Avro.
```

</details>

---

## Question 6

```markdown
What is a typical filename extension for Avro schema files used to generate Java classes?
```

**Options**

```markdown
- A. .avsc
- B. .json
- C. .proto
- D. .xml
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
- A. Correct – Avro schemas use `.avsc` files (Avro Schema).
- B. JSON is the syntax of `.avsc`, but the file extension is not `.json`.
- C. `.proto` is for Protocol Buffers.
- D. Avro does not use XML.
```

</details>

---

## Question 7

```markdown
What configuration must you use for the producer to serialize Java objects with Avro?
```

**Options**

```markdown
- A. `key.serializer=StringSerializer`
- B. `value.serializer=KafkaAvroSerializer`
- C. `value.serializer=ObjectSerializer`
- D. `key.serializer=JsonAvroSerializer`
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- A. String serializer is for raw strings, not Avro.
- B. Correct – KafkaAvroSerializer handles schema registration and binary encoding.
- C. ObjectSerializer is not a Kafka serializer.
- D. JsonAvroSerializer is not a standard Kafka component.
```

</details>

---

## Question 8

```markdown
How does Avro maintain type safety across producer and consumer in Java applications?
```

**Options**

```markdown
- A. By encoding types as Java comments
- B. By generating and using strongly-typed Java classes
- C. By adding schema names in message headers
- D. By converting JSON into POJOs
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- A. Comments have no impact on type safety.
- B. Correct – Both sides use generated classes that enforce field names and types.
- C. Headers are not used for this.
- D. Avro does not rely on JSON for Java type enforcement.
```

</details>

---

## Question 9

```markdown
What tool is commonly used in a Maven build to auto-generate Avro classes?
```

**Options**

```markdown
- A. avro-codegen-maven-plugin
- B. avro-maven-plugin
- C. kafka-schema-generator
- D. json-avro-cli
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- A. Not an actual Maven plugin.
- B. Correct – `avro-maven-plugin` is used to generate Java classes from `.avsc`.
- C. This is not a standard tool.
- D. json-avro-cli is unrelated to Java code generation.
```

</details>

---

## Question 10

```markdown
Why is using strongly-typed classes recommended when working with Avro and Kafka in Java?
```

**Options**

```markdown
- A. It allows using Avro without Schema Registry
- B. It helps with logging only
- C. It provides compile-time validation and fewer runtime errors
- D. It increases message size
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Schema Registry is still required.
- B. Type safety goes beyond logging.
- C. Correct – You catch errors at compile time instead of runtime.
- D. Message size is unrelated to typing in Java.
```

</details>

## Question 11

```markdown
In Java, Avro SpecificRecord classes are:
```

**Options**

```markdown
- A. Automatically generated from Avro schema
- B. Automatically generated from Avro schema plus Maven/Gradle plugin
- C. Written manually by the programmer
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Avro SpecificRecord classes are generated automatically from Avro schema using build tools such as Maven or Gradle plugins.

- A is partially correct but usually generation happens as part of the build process with plugins.
- B is correct because Maven/Gradle plugins generate these classes during the build.
- C is incorrect because these classes are not typically handwritten; they are generated for correctness and convenience.
```

</details>

---

## Question 12

```markdown
What happens when a Kafka consumer using KafkaAvroDeserializer encounters a message without a schema ID?
```

**Options**

```markdown
- A. The consumer throws a SerializationException
- B. The consumer skips the message and moves to the next one
- C. The consumer attempts to deserialize the message using the latest schema
- D. The consumer falls back to using the GenericRecord deserializer
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
Without a schema ID, deserialization fails immediately.

- A. Correct – A SerializationException is thrown.
- B. Incorrect – Consumer doesn’t skip it silently.
- C. Incorrect – Latest schema can’t be used blindly.
- D. Incorrect – No fallback mechanism like that exists.
```

</details>

---

## Question 13

```markdown
What are the benefits of using Confluent Schema Registry and KafkaAvroDeserializer in a Kafka consumer?
```

**Options**

```markdown
- A. Automatic schema evolution and compatibility checks
- B. Improved deserialization performance compared to generic deserializers
- C. Ability to deserialize messages without knowing the schema upfront
- D. All of the above
```

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**

```markdown
All mentioned advantages are valid benefits.

- A. Correct – Schema Registry ensures evolution rules are enforced.
- B. Correct – Avro is efficient compared to generic formats.
- C. Correct – Schema ID in message enables automatic schema resolution.
- D. Correct – Combines all the above.
```

</details>

---
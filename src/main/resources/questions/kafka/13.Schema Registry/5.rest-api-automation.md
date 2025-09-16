## Question 1

```markdown
Which HTTP method is used to register a new schema version in the Schema Registry?
```

**Options**

```markdown
- A. GET
- B. PUT
- C. POST
- D. DELETE
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. GET is used to retrieve data, not create.
- B. PUT is often for updating entire resources.
- C. Correct – POST is used to register a new schema version.
- D. DELETE removes a schema, not registers one.
```

</details>

---

## Question 2

```markdown
What endpoint retrieves the latest version of a schema for a given subject?
```

**Options**

```markdown
- A. `/subjects/{subject}/versions`
- B. `/schemas/latest`
- C. `/subjects/{subject}/versions/latest`
- D. `/subjects/latest`
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Lists all versions, but not the latest one.
- B. Not a valid endpoint in Schema Registry.
- C. Correct – This returns the most recent version for a subject.
- D. Not a valid endpoint.
```

</details>

---

## Question 3

```markdown
What does the `/compatibility/subjects/{subject}/versions/{version}` endpoint do?
```

**Options**

```markdown
- A. Registers a schema
- B. Deletes a version
- C. Checks schema compatibility
- D. Lists all subjects
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Registration happens via `/subjects/.../versions`.
- B. Deletion uses the DELETE method.
- C. Correct – This checks if a schema is compatible with a specific version.
- D. Not relevant to this endpoint.
```

</details>

---

## Question 4

```markdown
Which content type should be used when posting Avro schemas to the REST API?
```

**Options**

```markdown
- A. application/json
- B. application/avro
- C. text/plain
- D. application/schema+json
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
- A. Correct – The REST API accepts JSON payloads when registering schemas.
- B. Not a supported MIME type here.
- C. Not structured enough for schema definitions.
- D. This isn’t a standard content type for Schema Registry.
```

</details>

---

## Question 5

```markdown
What kind of ID does Schema Registry return upon successful schema registration?
```

**Options**

```markdown
- A. UUID string
- B. Kafka partition ID
- C. Integer schema ID
- D. Hash value
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. UUIDs are not used for schema IDs.
- B. Irrelevant to Schema Registry.
- C. Correct – Schema Registry assigns a unique integer ID to each schema.
- D. Hashing is not used directly for identifying schemas.
```

</details>

---

## Question 6

```markdown
Which HTTP method and endpoint can be used to change compatibility settings for a subject?
```

**Options**

```markdown
- A. POST `/subjects/{subject}/compatibility`
- B. PUT `/config/{subject}`
- C. PATCH `/subjects/{subject}/config`
- D. PUT `/subjects/{subject}/versions`
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- A. POST is not used for config changes.
- B. Correct – PUT to `/config/{subject}` updates compatibility settings.
- C. PATCH is not supported.
- D. This endpoint is for schema version registration.
```

</details>

---

## Question 7

```markdown
What does the `/subjects` endpoint return?
```

**Options**

```markdown
- A. All schema IDs
- B. A list of all registered subjects
- C. The latest compatibility config
- D. The current version of the Schema Registry
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- A. Schemas are not returned here.
- B. Correct – It returns a list of all subjects (schema keys).
- C. Compatibility config has a different endpoint.
- D. Version info is not available here.
```

</details>

---

## Question 8

```markdown
How can the Schema Registry REST API help with automation?
```

**Options**

```markdown
- A. By triggering Kafka rebalancing
- B. By storing Avro schemas in a relational DB
- C. By enabling CI/CD pipelines to validate and register schemas
- D. By monitoring consumer lag
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Schema Registry has no role in consumer group management.
- B. It uses Kafka or storage backends, but not SQL DBs directly.
- C. Correct – It allows you to automate schema validation/registration via APIs.
- D. That’s a Kafka metrics responsibility.
```

</details>

---

## Question 9

```markdown
What response do you get when registering a schema that already exists?
```

**Options**

```markdown
- A. HTTP 500 Internal Server Error
- B. The same schema ID as before
- C. A new schema ID every time
- D. Null response
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- A. It’s not an error; re-registration is valid.
- B. Correct – The existing ID is returned if the schema is unchanged.
- C. A new ID is only given for new, unique schemas.
- D. Responses always include at least the ID.
```

</details>

---

## Question 10

```markdown
What is the purpose of the `/schemas/ids/{id}` endpoint?
```

**Options**

```markdown
- A. Register a new schema
- B. Validate schema compatibility
- C. Retrieve the schema definition using its ID
- D. Delete a schema version
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
- A. Registration uses `/subjects/...`
- B. Compatibility checks use `/compatibility/...`
- C. Correct – This endpoint retrieves a schema by ID.
- D. Deletion has a separate endpoint.
```

</details>

---

## Question 11

```markdown
How can you retrieve the latest version of a schema from the Confluent Schema Registry using its REST API?
```

**Options**

```markdown
- A. Send a GET request to `/subjects/<subject>/versions/latest`
- B. Send a POST request to `/schemas/<subject>/versions/latest`
- C. Send a GET request to `/schemas/<subject>/versions/latest`
- D. Send a POST request to `/subjects/<subject>/versions/latest`
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
Use the REST endpoint `/subjects/<subject>/versions/latest` with a GET request to retrieve the latest version of a schema.

- A. ✅ Correct – This is the correct REST endpoint and method
- B. ❌ POST not used for fetching schema
- C. ❌ Incorrect URL structure
- D. ❌ POST is incorrect for this action
```

</details>

---

## Question 12

```markdown
What is the purpose of the `kafkastore.topic` configuration in the Confluent Schema Registry?
```

**Options**

```markdown
- A. To specify the Kafka topic where the Schema Registry stores its schema data
- B. To define the compatibility setting for schema evolution
- C. To set the frequency at which the Schema Registry checks for schema updates
- D. To configure the retention period for old schema versions
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
Schema Registry stores schema data in a Kafka topic, typically named `_schemas`, which can be customized using `kafkastore.topic`.

- A. ✅ Correct – Determines where schemas are stored
- B. ❌ Compatibility is handled via `/config` endpoint
- C. ❌ No periodic polling; schemas are stored on registration
- D. ❌ Schema retention is tied to Kafka topic settings, not this property
```

</details>

---

## Question 13

```markdown
How can you change the compatibility setting for a specific subject in the Confluent Schema Registry using its REST API?
```

**Options**

```markdown
- A. Send a PUT request to `/config/<subject>`
- B. Send a POST request to `/config/<subject>`
- C. Send a PUT request to `/compatibility/<subject>`
- D. Send a POST request to `/compatibility/<subject>`
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

````markdown
To change the compatibility for a subject, send a PUT request to `/config/<subject>` with a JSON body like:
{
  "compatibility": "FULL"
}

* A. ✅ Correct – PUT with compatibility setting
* B. ❌ POST not used for configuration changes here
* C. ❌ `/compatibility` is not a valid endpoint
* D. ❌ Same issue as C
````


</details>

----

## Question 14

```markdown
Which command-line tool can be used to interact with the Confluent Schema Registry?
```

**Options**

```markdown
- A. kafka-schema-registry
- B. schema-registry-cli
- C. confluent-hub
- D. kafka-schema-cli
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
`schema-registry-cli` is a community or Confluent-provided tool to manage schemas.

- A. Not a valid tool name
- B. Correct — used to manage schemas and compatibility
- C. confluent-hub is for installing plugins
- D. Not an official CLI tool
```

</details>

---
Consider the following Avro schema definition for a Customer record that uses a logical type for the login_ts field.

Identify any issues in the code and explain how to fix them.



{
"namespace": "com.company",
"type": "record",
"name": "Customer",
"fields": [
{
"name": "customer_id",
"type": "string"
},
{
"name": "login_ts",
"type": "long",
"logicalType": "timestamp-millis"
}
]
}
Your answer is incorrect
The logicalType should be removed from the login_ts field.

The customer_id field should use int instead of string.

Correct answer
The logicalType should be timestamp_millis instead of timestamp-millis.

The login_ts field should use int instead of long.

Overall explanation
The issue in the provided Avro schema is that the logicalType for the login_ts field is incorrectly specified as timestamp-millis instead of timestamp_millis.

Domain
Avro
Beta

---


What characteristics define full compatibility in schema evolution?

(Select all that apply)

Remove fields without defaults.

Your selection is correct
Only remove fields that have defaults.

Add fields without defaults.

Your selection is correct
Only add fields with defaults.



Overall explanation
Full compatibility means making changes that are both backward and forward compatible. This includes adding new fields with default values and removing existing fields that have defaults, ensuring no disruptions regardless of the schema version.

Domain
Confluent Schema Registry
Beta

---

What are Avro logical types and their purposes?

( Select all that apply)

Correct selection
Decimal: Provides precise numerical representation.

Your selection is correct
Date: Represents the number of days since the Unix epoch.

Correct selection
Time-millis: Represents time in milliseconds since midnight.

Your selection is correct
Timestamp-millis: Measures time in milliseconds since the Unix epoch.

Overall explanation
Avro logical types extend the basic types to provide additional semantics for accurately representing data types like decimals and dates. These types make it easier to handle standardized date and time formats across different systems​​.

Domain
Avro
Beta

---


What is the purpose of using a Generic Record in Avro?

To enforce strict data type checks at runtime.

To bypass the need for code generation.

Your answer is correct
To interact directly with data using dynamic schema definitions.

To enable schema-less data serialization.

Overall explanation
Generic Records in Avro allow for dynamic interaction with data without the need for code generation, making them useful for applications where schemas are determined at runtime or where schema flexibility is needed​​.

Domain
Avro
Beta

---


Where does the Kafka Schema Registry store all the schemas?

In ZooKeeper along with other metadata.

In a dedicated database outside the Kafka cluster.

In a configuration file on each Kafka broker.

Your answer is correct
In the _schemas topic within the Kafka cluster.

Overall explanation
The Kafka Schema Registry stores all schema information in the _schemas topic within the Kafka cluster. This dedicated topic ensures that schemas are persisted, replicated, and available cluster-wide, facilitating reliable schema management and validation across different consumer and producer applications.

Domain
Confluent Schema Registry
Beta

---

What are the key features provided by the Confluent Schema Registry?

(Select all that apply)

Encrypt all data in transit

Automatically generate schemas based on message content

Your selection is correct
Manage schema versions

Your selection is correct
Enforce compatibility rules

Your selection is correct
Provide a centralized schema repository

Overall explanation
The Confluent Schema Registry provides several key features to enhance the management of schemas in a Kafka ecosystem. These features include managing schema versions, enforcing compatibility rules to ensure that schema changes do not break existing applications, and providing a centralized schema repository for storing and retrieving schemas. It does not automatically generate schemas based on message content or specifically encrypt all data in transit, although data security can be managed through other Kafka configurations.

Domain
Confluent Schema Registry
Beta

---


What are the best practices to follow when writing an Avro schema?

(Select all that apply)

Your selection is correct
Always give default values when evolving a schema.

Provide default values for all fields, including the primary key.

Enums should be used frequently for evolving data types.

Your selection is correct
Ensure the primary key is required and has no default values.

Your selection is correct
Use aliases instead of renaming fields to maintain compatibility.

Overall explanation
When designing Avro schemas, it's crucial to ensure that the primary key field is always required and lacks default values to maintain data integrity and uniqueness. Using aliases instead of renaming fields helps avoid breaking changes and maintains backward compatibility. Providing default values when adding new fields in schema evolution ensures that older data remains compatible with newer schema versions, preventing data ingestion or serialization errors. Enums are advised to be used cautiously due to their poor evolution compatibility.

Domain
Avro
Beta

-----


Consider the following old and new schemas for an Employee record in Kafka using Avro. What type of compatibility change is this?

Old Schema:

{
"namespace": "com.company",
"type": "record",
"name": "Employee",
"fields": [
{
"name": "firstName",
"type": "string"
},
{
"name": "lastName",
"type": "string"
}
]
}


New Schema:

{
"namespace": "com.company",
"type": "record",
"name": "Employee",
"fields": [
{
"name": "firstName",
"type": "string"
},
{
"name": "department",
"type": "string", "default": "engineering"
}
]
}


Forward compatibility

Full compatibility

No compatibility

Your answer is correct
Backward compatibility

Overall explanation
The new schema introduces a new field department with a default value and removes the old field lastName. This change ensures that data written with the old schema can still be read with the new schema using the default value for the new field, thereby maintaining backward compatibility. This is not a forward or full compatibility change, since it deletes a field without a default value.

Domain
Confluent Schema Registry
Beta

---

What are the key features of Avro as a data format?

Your selection is correct
Requires Avro tools for data inspection due to compression.

Offers native support for encrypting data.

Your selection is correct
Supports schema evolution and schema enforcement.

Correct selection
Lacks support for some programming languages.

Overall explanation
Avro is notable for its JSON-defined schemas that provide robust support for schema evolution, enabling backward and forward compatibility. However, its use can be limited by language support and typically requires specialized tools to read and write data due to its binary encoding format​​.

Domain
Avro
Beta

---


Consider the following old and new schemas for an Employee record in Kafka using Avro. What type of compatibility change is this?



Old Schema:

{
"namespace": "com.company",
"type": "record",
"name": "Employee",
"fields": [
{ "name": "firstName", "type": "string" },
{ "name": "lastName", "type": "string" }
]
}


New Schema:

{
"namespace": "com.company",
"type": "record",
"name": "Employee",
"fields": [
{ "name": "firstName", "type": "string" },
{ "name": "lastName", "type": "string" },
{ "name": "department", "type": "string" }
]
}
Backward compatibility

Correct answer
Forward compatibility

Your answer is incorrect
No compatibility

Full compatibility

Overall explanation
The new schema adds a new field department. Forward compatibility allows an older schema to read data written by a new schema. When reading new data with the older schema, Avro will just ignore new fields.

Domain
Confluent Schema Registry
Beta

---

What are the different kinds of schema evolution in Avro?

Your selection is correct
Full compatibility

Your selection is correct
Forward compatibility

Your selection is correct
Backward compatibility

Reverse changes

Overall explanation
Schema evolution in Avro supports various compatibility modes to manage how data is read across different versions. Backward and forward compatibilities ensure seamless transitions between schema versions, while breaking changes highlight the versions that are not interoperable​.

Domain
Confluent Schema Registry
Beta

---


What is the main function of Confluent Schema Registry?

To increase data replication and fault tolerance in Kafka.

To enable message routing between producers and consumers.

Your answer is correct
To provide storage for Kafka schemas and enforce compatibility settings.

To enhance message serialization and deserialization processes.

Overall explanation
Confluent Schema Registry serves as a centralized repository for storing Avro schemas and allows you to enforce backward, forward, or full compatibility rules on schema evolution, ensuring that all producers and consumers adhere to the same schema rules.

Domain
Confluent Schema Registry
Beta

---









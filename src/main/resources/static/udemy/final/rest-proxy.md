Consider the following headers required for making a request to the Confluent REST Proxy. Identify any issues in the headers and explain how to fix them.



Headers:

Content-Type: application/vnd.kafka.avro.v1+json
Accept: application/vnd.kafka.v2+json
Correct answer
The api_version in the Content-Type header should always be v2.

Your answer is incorrect
The Content-Type header should use application/vnd.kafka.binary.v2+json.

The Accept header should use application/vnd.kafka.avro.v2+xml.

The serialization_format should be xml instead of json.

Overall explanation
The issue in the provided headers is that the api_version in the Content-Type header is incorrectly specified as v1. It should always be v2. The corrected headers should be:



Content-Type: application/vnd.kafka.avro.v2+json
Accept: application/vnd.kafka.v2+json
Domain
Confluent REST Proxy


---


What data formats are supported by the Confluent REST Proxy for message transmission?

(Select all that apply)

Your selection is correct
JSON

XML

Your selection is correct
Binary

Protocol Buffers

Correct selection
Avro

Overall explanation
The Confluent REST Proxy natively supports JSON, Avro, and Binary data formats for message transmission. Although Protocol Buffers is not directly supported, users can utilize the binary format to handle Protocol Buffers data. XML is not supported by the Confluent REST Proxy.

Domain
Confluent REST Proxy
Beta

---



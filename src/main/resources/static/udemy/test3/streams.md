Describe the architecture of Kafka Streams and its key design principles.

It requires a separate processing cluster.

It does not support scalable and elastic processing.

It is suitable only for small-scale applications.

Your answer is correct
It operates directly within a Kafka cluster.

Overall explanation
Kafka Streams operates directly within a Kafka cluster, leveraging Kafka's capabilities without needing a separate processing cluster. It is designed based on principles of easy scalability, elastic and fault-tolerant processing, suitable for stream processing at any scale.

Domain
Kafka Streams
Beta

----


Consider the following Java code snippet for a Kafka Streams application. Identify any issues in the code and explain how to fix them.



Properties props = new Properties();
props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-example");
props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

StreamsBuilder builder = new StreamsBuilder();
KStream<String, String> source = builder.stream("input-topic");
KStream<String, String> transformed = source.mapValues(value -> value.toUpperCase());

transformed.to("output-topic");

KafkaStreams streams = new KafkaStreams(builder.build(), props);
streams.start();
Your answer is incorrect
The streams.start() method should be replaced with streams.run().

The mapValues function should be replaced with flatMapValues.

Correct answer
The Serdes.String().getClass() should be replaced with Serdes.String().getClass().getName().

The KStream should be KTable for the transformation.

Overall explanation
The issue in the provided code snippet is with the SerDe configuration. The Serdes.String().getClass() should be replaced with Serdes.String().getClass().getName() to properly set the default SerDe classes in the properties

Domain
Kafka Streams
Beta

---


How can you convert a KTable to a KStream in Kafka Streams?

By directly writing the KTable to a topic

Your answer is incorrect
KTable cannot be converted to KStream

Correct answer
Using the toStream() method on the KTable

Using the through() method on the KTable

Overall explanation
The toStream() method is used to convert a KTable into a KStream, enabling the stream to emit updates as they occur, which is crucial for applications that require real-time updates.

Domain
Kafka Streams
Beta

---


What are SerDes in Kafka Streams, and why are they important?

They manage state stores.

Your answer is correct
They handle data serialization and deserialization.

They compress data for efficient storage.

They control access to data.

Overall explanation
SerDes (Serializer/Deserializer) are crucial in Kafka Streams for converting data between the byte format used in Kafka topics and the data types used in applications, ensuring that data is correctly encoded and decoded during processing.

Domain
Kafka Streams
Beta

---


What distinguishes stateless from stateful operations in Kafka Streams?

Stateful operations do not depend on previous data.

All operations in Kafka Streams are inherently stateful.

Stateless operations require external state management.

Your answer is correct
Stateful operations leverage internal state stores to perform computations.

Overall explanation
In Kafka Streams, stateful operations, such as aggregations or joins, require maintaining state over time to compute their results, whereas stateless operations like map and filter do not.

Domain
Kafka Streams
Beta

---


How do Transform and TransformValues differ in their operation within a KStream?

(Select all that apply)

Both functions are used to filter records without any transformation.

Transform cannot operate without a subsequent aggregation.

Your selection is correct
TransformValues only modifies values and does not affect partitioning.

Correct selection
Transform alters both key and value, possibly triggering repartitioning.

Overall explanation
Transform applies a transformation to each record using a custom Transformer, which can alter both keys and values, potentially requiring repartitioning. TransformValues, however, focuses solely on the values, leaving the keys intact and avoiding repartitioning​​.

Domain
Kafka Streams

---



What is defined by a Kafka Streams topology?

The physical deployment configuration of the Kafka servers.

The disk storage used by the Kafka brokers.

Your answer is correct
The logical flow and processing steps of the stream processing application.

The network architecture of the Kafka cluster.

Overall explanation
A Kafka Streams topology outlines the processing logic of a stream processing application, depicting how records are transformed, aggregated, or processed as they flow through the system.

Domain
Kafka Streams
Beta

---


Which types of join operations does Kafka Streams support?

KStream-KTable join

KTable-KTable join

Your answer is correct
All of the above

KStream-KStream join

Overall explanation
Kafka Streams supports various types of joins, including KStream-KStream, KTable-KTable, and KStream-KTable, enabling flexible data processing and enriching streams with additional data sources.

Domain
Kafka Streams
Beta


Given the following code snippet, what is the primary function being set up in this Kafka Streams application?



StreamsBuilder builder = new StreamsBuilder();
KStream<String, String> textLines = builder.stream("TextLinesTopic");
KTable<String, Long> wordCounts = textLines
.flatMapValues(value -> Arrays.asList(value.toLowerCase().split("\\W+")))
.groupBy((key, word) -> word)
.count();
wordCounts.toStream().to("WordsWithCountsTopic");
Filtering unique words from a stream of text.

Translating text from one language to another.

Encrypting messages in a stream.

Your answer is correct
Counting words from a stream of text.

Overall explanation
This code snippet demonstrates how to set up a basic Kafka Streams application to count words from a stream of text. It reads from "TextLinesTopic", splits each line into words, groups by word, counts occurrences, and writes the results to "WordsWithCountsTopic"​​.

Domain
Kafka Streams
Beta

---


A company plans to expand its Kafka Streams application to handle increased loads. What scalability feature should they utilize?

Your answer is correct
Horizontal scaling by partitioning topics and distributing loads across more instances

Implementing more robust state stores

Vertical scaling by increasing server specifications

All of the above

Overall explanation
Kafka Streams achieves scalability through the partitioning of input topics and distributing the processing load across multiple application instances, which allows the system to handle increased loads efficiently as the application scales.

Domain
Kafka Streams
Beta

---

What types of windowing are available in Kafka Streams and their functions?

(Select all that apply)

Sequential Windows

Your selection is correct
Tumbling Windows

Correct selection
Sliding Windows

Your selection is correct
Hopping Windows

Overall explanation
Kafka Streams supports various windowing types to handle time-series data. Tumbling windows capture events in non-overlapping, fixed-duration blocks. Hopping windows overlap and can capture events in multiple windows. Sliding windows dynamically adjust their size based on the arrival times of records.

Domain
Kafka Streams
Beta

---

What are the functions of flatMapValues and branch operations in Kafka Streams?

(Select all that apply)

flatMapValues: Aggregates values across different keys

branch: Merges multiple streams into one

Your selection is correct
flatMapValues: Transforms each input record into multiple output records

Your selection is correct
branch: Filters records into multiple streams based on predicates

Overall explanation
flatMapValues is used for expanding records into more than one output record, effectively "flattening" the results, while branch is used to split the stream into several streams based on given predicates.

Domain
Kafka Streams

---

When building a Kafka Streams application to compute running totals from incoming sales data, which Kafka Streams operations are essential?

(Select all that apply)

Your selection is correct
reduce

mapValues

Your selection is incorrect
windowedBy

Your selection is correct
groupBy

Overall explanation
For computing running totals in a stateful manner, it's crucial to use groupBy to organize the records by a specific key that defines how the data should be accumulated (e.g., by customer ID or product ID). Following this grouping, a reduce operation is used to perform the actual aggregation, summing up the values continuously as new data comes in. This setup is vital for maintaining an updated state of running totals within the Kafka Streams application​​.

Domain
Kafka Streams
Beta

---

Is the join operation in Kafka stateful?

No, the join operation in Kafka is stateless and does not require maintaining any state information.

Your answer is incorrect
Yes, the join operation in Kafka is stateful, but it does not support combining data across different time windows or key matches.

Correct answer
Yes, the join operation in Kafka is stateful, requiring state information about the streams or tables being joined.

No, the join operation in Kafka is handled by an external database, which maintains the necessary state.

Overall explanation
Yes, the join operation in Kafka is stateful. It requires maintaining state information about the streams or tables being joined to handle the combination of data across different time windows or key matches. This statefulness allows Kafka to manage complex joins that integrate data arriving at different times, ensuring accurate and timely results in stream processing applications.

Domain
Kafka Streams
Beta

---


You are designing a Kafka Streams application to process data from a topic with 10 partitions. How will the number of tasks be determined in your application, and what is the significance of this configuration?

Correct answer
The number of tasks will be equal to the number of partitions in the source topic, with each task processing messages from exactly one partition.

The number of tasks will be set manually in the application configuration to control parallelism.

The number of tasks will be dynamically adjusted based on the workload, optimizing resource utilization.

Your answer is incorrect
The number of tasks will be equal to the number of consumers in the consumer group, ensuring balanced processing.

Overall explanation
In Kafka Streams, the number of tasks is directly equal to the number of partitions in the source topic. This one-to-one mapping ensures that each task processes messages from exactly one partition, optimizing parallel processing and enhancing overall application scalability and performance. Therefore, with a topic of 10 partitions, there will be 10 tasks in the Kafka Streams application.

Domain
Kafka Streams
Beta

---

What types of windowing are available in Kafka Streams, and what are their uses?

Your selection is correct
Hopping windows for overlapping time windows.

Your selection is correct
Sliding windows for continuous updates.

Your selection is correct
Tumbling windows for fixed-time analytics.

Sequential windows for strict order processing.

Overall explanation
Kafka Streams supports various windowing types like tumbling, hopping, and sliding windows, each serving different processing needs such as fixed-time analytics, overlapping analyses, and continuous updates.

Domain
Kafka Streams
Beta

---

How do interactive queries enhance Kafka Streams applications in a real-time data monitoring scenario?

Your answer is correct
They enable real-time querying of state within the application.

They restrict data flow to improve system performance.

They allow the application to serve as a batch processing system.

They serialize state data for archival purposes.

Overall explanation
Interactive queries extend the functionality of Kafka Streams applications by exposing the current state stored in state stores to external queries, enabling real-time data monitoring and analysis, which is crucial for scenarios requiring immediate data insights.

Domain
Kafka Streams
Beta

---


Consider the following Java code snippet for configuring Kafka Streams to achieve exactly-once semantics. Identify the issue in the code and explain how to fix it.



Properties props = new Properties();
props.put(StreamsConfig.PROCESSING_GAURANTEE, StreamsConfig.EXACTLY_ONCE);
KafkaStreams streams = new KafkaStreams(builder, props);
The props should include a setting for bootstrap.servers.

Your answer is incorrect
The StreamsConfig.EXACTLY_ONCE should be replaced with StreamsConfig.EXACTLY_ONCE_CONFIG.

Correct answer
The StreamsConfig.PROCESSING_GAURANTEE should be replaced with StreamsConfig.PROCESSING_GAURANTEE_CONFIG.

The builder should be replaced with topology.

Overall explanation
The issue in the provided code snippet is StreamsConfig.PROCESSING_GAURANTEE property should be StreamsConfig.PROCESSING_GAURANTEE_CONFIG.

The corrected code should be:



Properties props = new Properties();
props.put(StreamsConfig.PROCESSING_GUARANTEE_CONFIG, StreamsConfig.EXACTLY_ONCE);
KafkaStreams streams = new KafkaStreams(builder, props);
Domain
Kafka Streams
Beta

---

You are responsible for maintaining a Kafka Streams application and need to ensure its optimal performance. What key metrics should you monitor to achieve this?

Number of active streams

Commit rate and latency

Process rate and latency

Your answer is correct
Both A and B

Overall explanation
Key metrics for monitoring Kafka Streams applications include process-rate, process-latency, commit-rate, and commit-latency. Monitoring these metrics helps ensure that the application is performing optimally by providing insights into the efficiency and speed of data processing and commit operations.

Domain
Kafka Streams
Beta

---

What aggregation operations are supported by Kafka Streams?

(Select all that apply)

Your selection is correct
Summing values across a defined key.

Automatically adjusting the window size based on data volume.

Your selection is correct
Counting the number of events per key.

Your selection is correct
Calculating the minimum and maximum values for each key.

Overall explanation
Kafka Streams supports several fundamental aggregation operations, including:

Count, which tallies the number of events for each unique key, useful for understanding frequency or occurrence.

Sum, which aggregates numerical data points across a key, providing a total that reflects cumulative metrics.

Min and Max, which are used to identify the lowest and highest values recorded for each key, important for tracking extremes in data streams​​.

Domain
Kafka Streams
Beta

----






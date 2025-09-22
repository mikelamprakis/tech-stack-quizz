What characteristic makes Kafka highly scalable?

Fixed number of partitions

Single-threaded operations

Your answer is correct
Distributed architecture

In-memory computing

Overall explanation
Kafka is highly scalable due to its distributed architecture, which allows topics to be partitioned and replicated across multiple brokers.

Domain
Kafka Core Concepts

---

What foundational architectural principle does Kafka utilize to achieve its capabilities?

Distributed database management.

Peer-to-peer network management.

Your answer is correct
Distributed commit log.

Event sourcing with command query responsibility segregation (CQRS).

Overall explanation
Kafka is fundamentally built on the principle of a distributed commit log, enabling it to provide high-throughput, fault-tolerant storage across multiple servers.

Domain
Kafka Core Concepts
Beta

---


Which of the following programming languages are used to develop Apache Kafka?

(Select all that apply)

C++

Python

Correct selection
Scala

Your selection is correct
Java

Overall explanation
Apache Kafka is developed using Java and Scala, which are the primary languages for its implementation.

Domain
Kafka Core Concepts
Beta


---


You are managing a Kafka cluster that is experiencing periodic slowdowns during high-throughput periods. You suspect that JVM garbage collection might be the cause. How would you adjust the JVM settings to potentially reduce these GC-related slowdowns?

(Select all that apply)

Correct selection
Switch to a low-pause garbage collector like G1GC to reduce disruption during high-throughput periods.

Decrease the heap size to force more frequent garbage collections, ensuring that each collection is quicker.

Your selection is incorrect
Enable real-time garbage collection to prevent any pauses.

Correct selection
Increase the JVM heap size to provide more memory for objects before garbage collection is triggered.

Overall explanation
In this scenario, the periodic slowdowns during high-load periods could be mitigated by:

Increasing the JVM heap size, which can allow more objects to reside in memory before triggering a garbage collection, potentially reducing the frequency of GC events.

Switching to a garbage collector that focuses on minimizing pause times, such as the G1 Garbage Collector (G1GC). G1GC is designed to better support systems requiring large heaps and high-throughput, offering more predictable garbage collection pauses by working concurrently with application threads​​.

Domain
Kafka Configuration

---



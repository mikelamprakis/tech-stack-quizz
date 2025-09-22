You are managing a ZooKeeper ensemble consisting of 7 servers. Due to a network issue, some servers might go offline. What is the maximum number of servers that can go missing while still allowing the ensemble to operate correctly?

4

Your answer is correct
3

2

5

Overall explanation
Majority consists of 3 nodes for 7 nodes cluster, so 4 can go missing

Domain
Zookeeper
Beta


---


What is the role of the quorum controller in Kafka's KIP-500 plan?

It replaces the function of the Kafka brokers.

It serves as the external storage service for Kafka logs.

Your answer is correct
It acts as the central authority for managing cluster metadata and partition leadership.

It manages consumer group offsets.

Overall explanation
The quorum controller, introduced with KIP-500, is part of Kafka’s initiative to remove ZooKeeper dependencies. It serves as the central authority for managing cluster metadata, including partition leadership and membership changes, directly within Kafka.

Domain
Kafka Core Concepts
Beta

---



What is the role of a Kafka broker?

To balance the load between producers and consumers

To serialize and deserialize messages

To monitor consumer offset positions

Your answer is correct
To store data and serve client requests

Overall explanation
Kafka brokers are servers that store data and handle client requests, ensuring data is available and consistent across the Kafka cluster.

Domain
Kafka Brokers


----


You are tasked with configuring a Kafka broker to manage how long data is retained before it is deleted to ensure efficient storage management. How do you adjust log retention settings in Kafka?

Set the storage.duration limits in the consumer.properties file.

Your answer is correct
Modify the log.retention.hours, log.retention.bytes, or log.retention.minutes settings in server.properties.

Change the file.lifetime settings in the log4j.properties file.

Update the data.retention.period in the kafka-configs file.

Overall explanation
Log retention in Kafka is controlled through settings such as log.retention.hours, log.retention.bytes, and log.retention.minutes in the server.properties file. Adjusting these settings allows you to manage how long data is kept before it is deleted, ensuring efficient storage management.

Domain
Kafka Configuration

---


A Kafka administrator needs to prevent any single client from overloading the cluster. Which feature should they implement?

Enable auto-scaling for the cluster

Reduce the number of topics

Your answer is correct
Set quotas for byte-rate and request-rate limits

Increase the number of partitions

Overall explanation
Kafka's quota management system allows administrators to limit the rate of data production and consumption by clients, ensuring that no single client or group monopolizes cluster resources. This is implemented via broker configuration settings.

Domain
Kafka Configuration


-----


If a Kafka administrator needs to adjust broker configurations to handle increased load without downtime, which feature should they use?

Load balancing reassignment

Static broker reconfiguration

Correct answer
Dynamic broker configuration

Your answer is incorrect
Broker performance tuning

Overall explanation
Kafka's dynamic broker configuration allows administrators to modify certain settings at runtime without restarting brokers, thus avoiding downtime and adapting to changing conditions.

Domain
Kafka Configuration
Beta

---



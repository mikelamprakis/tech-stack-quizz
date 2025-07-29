You are administering a Kafka cluster and need to update a configuration setting that is classified as read-only. What must you do to apply this change, and why is this necessary?

Your answer is incorrect
Use the Kafka command-line tool to apply the configuration change immediately

Correct answer
Update the configuration setting in the broker configuration file and restart the broker

Change the configuration setting in ZooKeeper and restart the broker

Update the configuration setting dynamically using the Kafka configuration API

Overall explanation
In Kafka, read-only configurations are settings that cannot be dynamically updated while the broker is running. To change these configurations, you must update the setting in the broker configuration file and restart the broker. This ensures that changes are fully integrated and effective across the Kafka system, maintaining stability and consistency in broker operations.

Domain
Kafka Configuration
Beta

---


What configuration setting in Kafka determines where logs (data) are stored?

kafka.storage

data.paths

file.locations

Your answer is correct
log.dirs

Overall explanation
The log.dirs setting in server.properties specifies the directories where Kafka's logs and data are stored.

Domain
Kafka Configuration

---


How do broker-level settings differ from topic-level settings in Kafka, and when might you use each?

Topic-level settings are only applicable to consumer configurations.

Broker-level settings deal with network configurations, while topic-level settings handle data processing.

Broker-level settings are immutable, while topic-level settings can be changed dynamically.

Your answer is correct
Broker-level settings apply cluster-wide, whereas topic-level settings allow customization for individual topics.

Overall explanation
Broker-level settings provide cluster-wide defaults that affect all topics unless overridden, while topic-level settings offer granularity, allowing administrators to tailor settings such as retention policies and partition counts based on specific topic requirements.

Domain
Kafka Configuration
Beta

---


What are critical performance metrics to monitor regularly for Kafka brokers to ensure system health and efficiency?

(Select all that apply)

Correct selection
Network I/O rates

Your selection is incorrect
Active controller counts

Correct selection
Request rates and latencies

Correct selection
JVM memory usage

Overall explanation
Essential metrics for maintaining Kafka broker performance include request rates and latencies, network I/O rates, and JVM memory usage. Monitoring these metrics helps in assessing the health and efficiency of the brokers, enabling timely interventions to maintain system stability and performance​​.

Domain
Kafka Brokers
Beta

---



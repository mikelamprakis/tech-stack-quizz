Which of the following steps are required to immediately delete a topic in Kafka and ensure all logs are removed?

(Select all that apply)

Correct selection
Stop Kafka before manually removing the topic data to ensure all logs are deleted.

Correct selection
Manually remove the topic data from the /brokers/topics/<topic_name> directory in ZooKeeper using the rmr command.

Use the kafka-delete-logs command to remove all log files associated with the topic.

Your selection is correct
Use the kafka-topics command with the delete option to mark the topic for deletion.

Overall explanation
To immediately delete a topic in Kafka and ensure all logs are removed, you should first use the kafka-topics command with the delete option to mark the topic for deletion. However, this alone does not instantly remove the logs. To ensure immediate removal, you must stop Kafka and then manually remove the topic data from the /brokers/topics/<topic_name> directory in ZooKeeper using the rmr command. There is no kafka-delete-logs command in Kafka.

Domain
Kafka Setup
Beta

---



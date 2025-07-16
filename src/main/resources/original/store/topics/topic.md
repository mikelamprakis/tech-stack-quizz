## Topic

What is true about partitions? (select two)

1. A partition has a one replica that is leader, while the others replicas are follower.
2. A broker can have the differnt partition for the  same topic on it's disk
3. A broker can have the  partition and the replica of topic on it's disk
4. You can not have more partition than the no of broker in your cluster.

Explanation
Only one of the replicas is elected as partition leader.
And a broker can definitely hold many partitions from the same topic on its disk, try creating a topic with 12 partitions on one broker!


---

Your topic is log compacted and you are sending a message with the key K and value null. What will happen?

1. The message will be ignored by the kafka broker.
2. The producer will throw run time Exception.
3. The broker will delete all the mesages of the Key K on cleanup.



Explanation
Sending a message with the null value is called a tombstone in Kafka and will ensure the log compacted topic does not contain any messages with the key K upon compaction


---


Compaction is enabled for a topic in Kafka by setting log.cleanup.policy=compact. What is true about log compaction?

1. Each message stored in the topic is compressed
2. Compaction changes the offset of the message
3. After Cleanup , only one message per key is retained with the latest value


Explanation
Log compaction retains at least the last known value for each record key for a single topic partition.
All compacted log offsets remain valid, even if record at offset has been compacted away as a consumer will get the next highest offset.


---


When auto.create.topics.enable is set to true in Kafka configuration,
what are the circumstances under which a Kafka broker automatically creates a topic? (select three)

1. Producer send message to the topic
2. Consumer reads meassage from the topic
3. Client request the metadata for the topic.
4. Client Alters the no of partitions of a topic



Explanation
<p>A kafka broker automatically creates a topic under the following circumstances: <ul> 
<li>When a producer starts writing messages to the topic</li> <li>When a consumer starts reading messages from the topic</li>
<li>When any client requests metadata for the topic</li></ul></p>





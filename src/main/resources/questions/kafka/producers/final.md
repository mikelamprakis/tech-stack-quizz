## Question 1

What happens when you set `max.in.flight.requests.per.connection` to a value greater than 1 in a Kafka producer?

* A. It increases the throughput of the producer
* B. It increases the latency of the producer
* C. It can lead to out-of-order delivery of messages
* D. It has no effect on the producer's behavior

<details><summary>Response:</summary> 

**Answer:** C

**Explanation:**
Setting `max.in.flight.requests.per.connection` to a value greater than 1 allows multiple requests to be sent without waiting for previous responses. While this improves throughput, if a retry occurs (e.g. after a transient failure), messages may be delivered out of order because some later messages may have already been acknowledged.

</details>

---

## Question 2

What is the effect of setting `acks=0` in a Kafka producer?

* A. The producer will wait for the broker to acknowledge the message before sending the next one
* B. The producer will wait for the leader and all replicas to acknowledge the message
* C. The producer will not wait for any acknowledgement from the broker
* D. The producer will throw an exception if the broker does not acknowledge the message

<details><summary>Response:</summary> 

**Answer:** C

**Explanation:**
With `acks=0`, the producer sends data and does not wait for any acknowledgement. It offers the highest throughput but no delivery guarantees.

</details>

---

## Question 3

What is the relationship between `request.timeout.ms` and `delivery.timeout.ms` in a Kafka producer?

* A. `request.timeout.ms` should always be greater than `delivery.timeout.ms`
* B. `delivery.timeout.ms` should always be greater than `request.timeout.ms`
* C. They should always be set to the same value
* D. They are independent and can be set to any value

<details><summary>Response:</summary> 

**Answer:** B

**Explanation:**
`delivery.timeout.ms` is the total time allowed to deliver a message including all retries. `request.timeout.ms` is per-request. If delivery timeout is less than request timeout, a send may time out before the request does, which is not ideal.

</details>

---

## Question 4

A Kafka producer application needs to send messages to a topic. The messages do not require any particular order. Which of the following properties are mandatory in the producer configuration? (Select two)

* A. `compression.type`
* B. `partitioner.class`
* C. `bootstrap.servers`
* D. `key.serializer`
* E. `value.serializer`
* F. `client.id`

<details><summary>Response:</summary> 

**Answer:** C, E

**Explanation:**
`bootstrap.servers` is required to connect to Kafka brokers, and `value.serializer` is needed to serialize the message values. All others are optional or conditionally required.

</details>

---

## Question 5

**When is the `onCompletion()` method called in a Producer Callback?**

```java
private class ProducerCallback implements Callback {
    @Override
    public void onCompletion(RecordMetadata metadata, Exception e) {
        if (e != null) e.printStackTrace();
    }
}
```

* A. Immediately after serialization
* B. Immediately when `send()` is called
* C. When the broker response is received
* D. Only on failure

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**
The `onCompletion()` method is triggered when Kafka receives a response from the broker, either success or failure. It’s an asynchronous callback used to handle send results.

</details>

---

## Question 6

**To prevent network-induced duplicates when producing to Kafka, I should use:**

* A. `acks=0`
* B. `batch.size=0`
* C. `enable.idempotence=true`
* D. `retries=0`

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**
Enabling idempotence guarantees that even with retries, messages won’t be duplicated. This setting must be turned on to ensure exactly-once semantics in producers.

</details>

---

## Question 7

**Where will a message with no key be stored?**

* A. The first partition of the topic
* B. A random topic partition
* C. Any of the topic partitions using round-robin
* D. The partition with least messages

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**
Kafka producers use round-robin partitioning when no key is provided, spreading messages evenly across partitions.

</details>

---

## Question 8

**A Kafka topic has a replication factor of 3 and `min.insync.replicas=2`. How many brokers can go down before a producer with `acks=all` can't produce?**

* A. One broker
* B. Two brokers
* C. All brokers can go down
* D. None

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**
With `acks=all` and `min.insync.replicas=2`, at least two replicas must be available. Losing one broker is tolerable, but losing two violates this condition and the producer can no longer send data.

</details>

## Question 1

Which of the following is stored in Zookeeper for a Kafka cluster? (Select two)

* A. Consumer offsets
* B. Kafka broker information
* C. Topic partition assignments
* D. Topic-level configurations
* E. Producer client IDs

<details><summary>Response:</summary> 

**Answer:** B, D

**Explanation:**
Kafka uses ZooKeeper to store metadata such as:

* **B:** Kafka broker information – includes broker registrations and cluster membership.
* **D:** Topic-level configurations – includes topic-specific settings like retention.

**A** is stored in `__consumer_offsets` topic. **C** is managed by the Kafka controller. **E** is client-side only.

</details>

---

## Question 2

In a Kafka cluster, you have a topic with 6 partitions and a replication factor of 3. How many replicas of each partition will be spread across the brokers?

* A. 1 replica per broker
* B. 2 replicas per broker
* C. 3 replicas per broker
* D. 6 replicas per broker

<details><summary>Response:</summary> 

**Answer:** C

**Explanation:**
Each of the 6 partitions will have **3 replicas** (1 leader + 2 followers), distributed across brokers.
Total replicas = 6 × 3 = 18.
Kafka ensures these replicas are spread out for fault tolerance.

</details>

---

## Question 3

What happens to the replicas when a broker in a Kafka cluster goes down?

* A. All replicas on the failed broker are permanently lost
* B. The replicas on the failed broker are automatically redistributed to other brokers
* C. The replicas on the failed broker become unavailable until the broker is restarted
* D. The replicas on the failed broker are immediately promoted to be leaders on other brokers

<details><summary>Response:</summary> 

**Answer:** C

**Explanation:**
When a broker goes down:

* Replicas on that broker become **temporarily unavailable**.
* If the broker hosted a leader, a new leader is elected from in-sync replicas.
* The replicas are **not moved** — they resume once the broker is back.

</details>

---

## Question 4

How does Kafka ensure data integrity and consistency across replicas?

* A. By using a two-phase commit protocol
* B. By relying on ZooKeeper for distributed consensus
* C. By implementing a leader-follower replication model
* D. By using a gossip protocol for eventual consistency

<details><summary>Response:</summary> 

**Answer:** C

**Explanation:**
Kafka uses a **leader-follower** model:

* Producers write to the leader.
* Followers replicate the leader's log.
* A message is "committed" once acknowledged by all in-sync replicas.

This provides **strong consistency** and **fault tolerance**.

</details>

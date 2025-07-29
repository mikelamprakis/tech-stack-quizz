## Question 1
```markdown  
During a controller failover in KRaft mode, what ensures that partition leadership assignments are not lost?  
```  

**Options**
```markdown  
- A. ZooKeeper's persistent ZNodes  
- B. The `@metadata` topic's replicated log  
- C. Brokers caching the last known controller state  
- D. Manual intervention to reconfigure leaders  
```  

<details><summary>Response:</summary>  

**Answer:** B

**Explanation:**
```markdown  
- A. ZooKeeper is not used in KRaft mode.  
- B. **Correct.** The `@metadata` topic durably stores all controller decisions via Raft consensus.  
- C. Brokers rely on the metadata log, not caches, for consistency.  
- D. Failover is automatic in KRaft.  
```  
</details>  

---

## Question 2
```markdown  
How does the controller detect a broker failure in KRaft mode?  
```  

**Options**
```markdown  
- A. Via ZooKeeper's ephemeral node expiration.  
- B. By monitoring broker heartbeats to the metadata log.  
- C. Through direct TCP health checks between brokers.  
- D. Using external monitoring tools like Prometheus.  
```  

<details><summary>Response:</summary>  

**Answer:** B

**Explanation:**
```markdown  
- A. ZooKeeper is not used in KRaft.  
- B. **Correct.** Brokers send heartbeats to the metadata log; timeouts trigger failure detection.  
- C. Brokers do not probe each other directly.  
- D. External tools are for observability, not failure detection.  
```  
</details>  

---

## Question 3
```markdown  
What is the purpose of the `controller.quorum.voters` configuration in KRaft?  
```  

**Options**
```markdown  
- A. To list all brokers eligible to store topic data.  
- B. To define which nodes participate in metadata consensus.  
- C. To assign partitions to specific racks for availability.  
- D. To configure the number of ISR replicas per topic.  
```  

<details><summary>Response:</summary>  

**Answer:** B

**Explanation:**
```markdown  
- A. Voters are for metadata, not data storage.  
- B. **Correct.** This config identifies nodes in the Raft quorum (e.g., `1@broker1:9093`).  
- C. Rack awareness is configured via `broker.rack`.  
- D. ISR size is controlled by `replication.factor` and broker health.  
```  
</details>  

---

## Question 4

```markdown
What happens if a majority of the controllers in a KRaft cluster become unavailable?
```

**Options**

```markdown
- A. The cluster remains operational with reduced performance
- B. The cluster automatically elects a new set of controllers
- C. The cluster becomes unavailable until a majority of controllers are restored
- D. The brokers take over the responsibilities of the controllers
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Quorum can't function without majority — cluster halts.

- A. Incorrect — system halts, not just slow
- B. Incorrect — quorum isn’t dynamically rebuilt
- C. Correct — requires majority to recover
- D. Incorrect — roles are not interchangeable
```

</details>

---

## Question 5

```markdown
What is the minimum number of controllers required for a KRaft cluster?
```

**Options**

```markdown
- A. 1
- B. 2
- C. 3
- D. 4
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
A quorum system requires an odd number for fault tolerance.

- A. Incorrect — no fault tolerance
- B. Incorrect — 2-node quorum isn't stable
- C. Correct — 3 is minimum for reliable quorum
- D. Incorrect — valid but not minimum
```

</details>

---

## Question 6

```markdown
What is the impact of having a large value for the `controller.quorum.request.timeout.ms` configuration in KRaft mode?
```

**Options**

```markdown
- A. It increases the time the controller waits for a quorum of voters to respond to a request
- B. It reduces the time the controller waits for a quorum of voters to respond to a request
- C. It sets the maximum time allowed for the controller to process a request
- D. It determines the frequency at which the controller sends heartbeats to the brokers
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
A large value for `controller.quorum.request.timeout.ms` increases the wait time for a quorum of voters to respond.

- A is correct: more time is allowed for voter responses.
- B is incorrect; a larger value does not reduce wait time.
- C is incorrect; it doesn't limit processing time.
- D is incorrect; heartbeat frequency is controlled separately.
```

</details>

---

## Question 7

```markdown
What is the impact of setting the `controller.quorum.election.backoff.max.ms` configuration to a very high value in KRaft mode?
```

**Options**

```markdown
- A. It increases the frequency of controller elections, improving fault tolerance
- B. It reduces the time taken for a new controller to be elected, minimizing downtime
- C. It prolongs the time taken for a new controller to be elected, potentially increasing downtime
- D. It has no impact on the controller election process
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
A high value prolongs the wait time before brokers attempt election, increasing controller election time and downtime.

- A. Incorrect — Frequency is not increased, it's delayed.
- B. Incorrect — Time to election is increased, not reduced.
- C. Correct — Longer backoff increases election duration and downtime.
- D. Incorrect — The setting affects the election process directly.
```

</details>

---

## Question 8

```markdown
What is the purpose of the `controller.quorum.voters` configuration in KRaft mode?
```

**Options**

```markdown
- A. To specify the listeners used by the controllers
- B. To set the minimum number of in-sync replicas for the controller quorum
- C. To define the list of voters in the controller quorum
- D. To configure the metrics reporter for the controllers
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
This config lists controller nodes that vote in quorum decisions.

- A. Incorrect — that's `controller.listener.names`
- B. Incorrect — in-sync replicas don't apply to controllers
- C. Correct — defines quorum voters
- D. Incorrect — unrelated to metrics
```

</details>

---

## Question 9
```markdown  
What is the risk of setting `auto.leader.rebalance.enable=true` in a large cluster?  
```  

**Options**
```markdown  
- A. It may cause frequent leader shuffling, impacting throughput.  
- B. It disables replication for underutilized partitions.  
- C. It allows ZooKeeper to take over metadata management.  
- D. It merges partitions to reduce broker load.  
```  

<details><summary>Response:</summary>  

**Answer:** A

**Explanation:**
```markdown  
- A. **Correct.** Frequent rebalancing can add overhead.  
- B. Replication is unaffected by leader rebalancing.  
- C. ZooKeeper is not involved in this process.  
- D. Partitions cannot be merged dynamically.  
```  
</details>  

---

## Question 10

```markdown
What is the recommended value for `process.roles` in a production KRaft cluster?
```

**Options**

```markdown
- A. `broker,controller`
- B. `broker` for broker nodes and `controller` for controller nodes
- C. `controller` for all nodes
- D. Leave `process.roles` unconfigured
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Best practice is to separate broker and controller roles.

- A. Incorrect — combined mode isn't production-ready
- B. Correct — separates roles for scalability/fault tolerance
- C. Incorrect — no brokers means no data handling
- D. Incorrect — required in KRaft mode
```

</details>

---

## Question 11
```markdown  
What prevents split-brain scenarios during controller failover in KRaft?  
```  

**Options**
```markdown  
- A. ZooKeeper's distributed lock mechanism.  
- B. Raft's leader election algorithm ensures only one active controller.  
- C. Brokers vote on the new controller via gossip.  
- D. Manual administrator intervention is required.  
```  

<details><summary>Response:</summary>  

**Answer:** B

**Explanation:**
```markdown  
- A. ZooKeeper is not used in KRaft.  
- B. **Correct.** Raft guarantees a single leader at all times.  
- C. Brokers do not participate in controller election.  
- D. Failover is automatic.  
```  
</details>  

---

## Question 12
```markdown  
How does the controller handle a partition with all replicas out of sync?  
```  

**Options**
```markdown  
- A. It marks the partition as corrupt and deletes it.  
- B. It triggers `min.insync.replicas` auto-adjustment.  
- C. It allows producers to write if `unclean.leader.election.enable=true`.  
- D. It designates the oldest replica as leader to preserve data.  
```  

<details><summary>Response:</summary>  

**Answer:** C

**Explanation:**
```markdown  
- A. Kafka never auto-deletes partitions.  
- B. `min.insync.replicas` is immutable.  
- C. **Correct.** This risks data loss but maintains availability.  
- D. Leadership is not based on replica age.  
```  
</details>  

---

## Question 13

```markdown
What is the impact of setting `controller.quorum.fetch.timeout.ms` to a very low value in KRaft mode?
```

**Options**

```markdown
- A. It increases the time the controllers wait for a fetch response from the active controller
- B. It reduces the time the controllers wait for a fetch response from the active controller
- C. It sets the maximum time allowed for a controller to fetch data from the brokers
- D. It determines the frequency at which the controllers fetch data from the active controller
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Setting `controller.quorum.fetch.timeout.ms` to a very low value reduces the waiting time for fetch responses.

- A is incorrect because the time decreases.
- B is correct: controllers wait less before timing out.
- C is incorrect; it does not control fetch time from brokers.
- D is incorrect; fetch frequency is controlled elsewhere.
```

</details>

---

## Question 14
```markdown  
Which configuration reduces partition unavailability during controller failover?  
```  

**Options**
```markdown  
- A. `controlled.shutdown.enable=true`  
- B. `controller.quorum.election.timeout.ms=10000`  
- C. `num.partitions=3`  
- D. `log.flush.interval.messages=1`  
```  

<details><summary>Response:</summary>  

**Answer:** B

**Explanation:**
```markdown  
- A. Graceful shutdown doesn’t affect failover speed.  
- B. **Correct.** Lower election timeouts speed up controller failover.  
- C. Partition count doesn’t impact controller recovery.  
- D. Flush settings affect durability, not availability.  
```  
</details>  

---

## Question 15
```markdown  
What happens if the controller and ZooKeeper disagree on leader assignments in hybrid mode (during migration)?  
```  

**Options**
```markdown  
- A. Brokers prioritize ZooKeeper's state for backward compatibility.  
- B. The cluster shuts down to avoid split-brain.  
- C. KRaft overrides ZooKeeper's metadata permanently.  
- D. Producers switch to `acks=1` to bypass the conflict.  
```  

<details><summary>Response:</summary>  

**Answer:** B

**Explanation:**
```markdown  
- A. Hybrid mode requires consistency; disagreement is fatal.  
- B. **Correct.** Kafka prevents split-brain by halting.  
- C. No automatic override exists; manual intervention is needed.  
- D. `acks` settings don’t resolve metadata conflicts.  
```  
</details>  

---

## Question 16

```markdown
What happens when a new broker joins a KRaft cluster and the `controller.quorum.voters` configuration is not updated to include the new broker?
```

**Options**

```markdown
- A. The new broker automatically becomes a voter in the controller quorum
- B. The new broker joins as an observer and does not participate in the controller quorum voting
- C. The new broker is unable to join the cluster until the configuration is updated
- D. The new broker replaces one of the existing voters in the controller quorum
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
When a new broker joins a KRaft cluster, it does not automatically become a voter in the controller quorum if the `controller.quorum.voters` configuration is not updated to include the new broker.

- A. Incorrect — The new broker does not automatically become a voter without being added to the configuration.
- B. Correct — The new broker joins as an observer and does not participate in voting.
- C. Incorrect — The broker can join as an observer without configuration update.
- D. Incorrect — The new broker does not replace existing voters unless explicitly configured.
```

</details>

---

## Question 17
```markdown  
How does KRaft mode handle controller failover differently from ZooKeeper mode?  
```  

**Options**
```markdown  
- A. KRaft uses a hot standby controller, while ZooKeeper requires a full election.  
- B. KRaft’s controller state is rebuilt from broker snapshots, while ZooKeeper relies on ephemeral nodes.  
- C. KRaft’s failover is faster because metadata is stored in Kafka’s log, not an external system.  
- D. ZooKeeper mode has no controller role.  
```  

<details><summary>Response:</summary>  

**Answer:** C

**Explanation:**
```markdown  
- A. KRaft uses Raft leader election, not hot standbys.  
- B. KRaft replicates metadata logs; no broker snapshots are needed.  
- C. **Correct.** KRaft’s log-based storage avoids ZooKeeper’s external latency.  
- D. ZooKeeper mode has a controller (elected via ZooKeeper).  
```  
</details>  

---





-------

## Question 18
```markdown  
In KRaft mode, what is the purpose of the `controller.quorum.voters` configuration?  
```  

**Options**
```markdown  
- A. To list all brokers eligible to become partition leaders.  
- B. To define the set of nodes participating in the metadata quorum.  
- C. To enforce voter-based authorization for topic creation.  
- D. To assign ZooKeeper roles during migration.  
```  

<details><summary>Response:</summary>  

**Answer:** B

**Explanation:**
```markdown  
- A. Partition leadership is separate from the metadata quorum.  
- B. **Correct.** This config identifies nodes (e.g., `1@broker1:9093`) in the Raft quorum.  
- C. No such authorization mechanism exists.  
- D. ZooKeeper is irrelevant in KRaft.  
```  
</details>  

---

## Question 19

```markdown
What is the purpose of the `metadata.max.idle.interval.ms` configuration in KRaft mode?
```

**Options**

```markdown
- A. To set the maximum time allowed for a metadata request to be idle before it is cancelled
- B. To specify the maximum time the active controller can be idle before a new controller is elected
- C. To configure the frequency at which the active controller writes no-op records to the metadata partition
- D. To define the maximum interval allowed between two consecutive metadata log segments
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
This setting controls how often no-op records are written to avoid metadata log idleness.

- A. Incorrect. Not related to metadata request idle timeout.
- B. Incorrect. Not tied to controller election.
- C. Correct. Defines frequency of writing no-op records.
- D. Incorrect. Doesn’t affect segment intervals.
```

</details>

---

## Question 20
```markdown  
In KRaft mode, how does a broker discover the active controller?  
```  

**Options**
```markdown  
- A. By querying ZooKeeper’s `/controller` path.  
- B. By reading the latest `@metadata` log entry.  
- C. By broadcasting a request to all brokers.  
- D. By consulting the `controller.quorum.voters` list.  
```  

<details><summary>Response:</summary>  

**Answer:** B

**Explanation:**
```markdown  
- A. ZooKeeper is unused.  
- B. **Correct.** The metadata log tracks the current controller.  
- C. No broadcast mechanism exists.  
- D. The voters list identifies quorum members, not the active controller.  
```  
</details>  

---  

## Question 21
```markdown  
What is the impact of setting `process.roles=broker,controller` in KRaft mode?  
```  

**Options**
```markdown  
- A. The node acts as both a data broker and metadata controller, violating separation of concerns.  
- B. The node participates in the Raft quorum and stores topic partitions.  
- C. The node becomes read-only to prioritize metadata consistency.  
- D. The node refuses connections from producers/consumers.  
```  

<details><summary>Response:</summary>  

**Answer:** B

**Explanation:**
```markdown  
- A. Co-locating roles is supported but requires careful resource allocation.  
- B. **Correct.** This is a valid (though resource-intensive) configuration.  
- C. The node remains fully functional.  
- D. It handles both data and control plane traffic.  
```  
</details>  

---  

## Question 22

```markdown
What is the purpose of the `process.roles` configuration in KRaft mode?
```

**Options**

```markdown
- A. To specify whether the server acts as a controller, broker, or both
- B. To set the unique identifier for the server
- C. To define the listeners used by the controller
- D. To configure the metrics reporter for the server
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
In KRaft mode, `process.roles` determines whether the server acts as a controller, broker, or both.

- A. Correct — defines the server role(s)
- B. Incorrect — server ID is set using `node.id`
- C. Incorrect — listeners are set via `controller.listener.names`
- D. Incorrect — metrics are configured separately
```

</details>

---











## Question 10

```markdown
Can Kafka's zero-copy optimization be used in combination with compression?

```

**Options**

```markdown
- A. Yes, zero-copy and compression can be used together seamlessly.
- B. No, zero-copy is incompatible with compression and cannot be used together.
- C. Zero-copy can be used with compression, but it requires additional configuration.
- D. Zero-copy is automatically disabled when compression is enabled.
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
Zero-copy works independently of compression. Compression happens at the producer before data hits disk. Zero-copy transfers data from disk to network socket efficiently, so both can operate seamlessly.

- A. Correct — both work together naturally.
- B. False — no incompatibility.
- C. False — no extra config needed.
- D. False — zero-copy is not disabled.
```

</details>

---


## Question 7

```markdown
The Controller is a broker that is... (select two)
```

**Options**

```markdown
- A. elected by broker majority
- B. elected by Zookeeper ensemble
- C. is responsible for partition leader election
```

<details><summary>Response:</summary>

**Answer:** B, C

**Explanation:**

```markdown
The Controller broker is elected by the Zookeeper ensemble and is responsible for partition leader election. Only one broker acts as Controller at a time.

- A. Incorrect — not elected by broker majority.
- B. Correct — elected by Zookeeper ensemble.
- C. Correct — responsible for partition leader election.
```

</details>

---

## Question 12

```markdown
In a Kafka cluster, the Controller is a critical component for managing cluster state. Which of the following statements accurately describe the role and election of the Controller? (Select two)

```

**Options**

```markdown
- A. Elected by broker majority
- B. Elected by Zookeeper ensemble
- C. Responsible for partition leader election
- D. Manages consumer group offsets
- E. Automatically assigns replicas to brokers based on load
```

<details><summary>Response:</summary>

**Answer:** B and C

**Explanation:**

```markdown
- B. Correct — the Controller is elected by the Zookeeper ensemble using ephemeral znodes.
- C. Correct — the Controller manages partition leader election, especially after failures.
- A. Incorrect — election is not by broker majority.
- D. Incorrect — consumer offset management is handled by Kafka brokers and stored in `__consumer_offsets`.
- E. Incorrect — replica assignment is done at topic creation or manually, not automatically by Controller.

Note: Newer KRaft mode changes this, but Zookeeper is still common.
```

</details>

---

## Question 13

```markdown
In the context of Kafka's distributed architecture, broker elections are vital for cluster health and stability. Consider the following advanced scenarios where Kafka's internal mechanisms must decide on leadership roles:

```

**Options**

```markdown
- A. The Zookeeper ensemble elects the new Controller based on ephemeral node creation sequence.
- B. The new partition leader is elected based on the ISR list order, favoring replicas with the most recent updates.
- C. Brokers in the main cluster segment with access to Zookeeper retain their roles, while isolated brokers step down until connectivity is restored.
- D. A broker majority within the isolated segment elects a new temporary Controller until the network partition is resolved.
- E. The election of a new partition leader among replicas with identical network latency is determined by a random selection process.
```

<details><summary>Response:</summary>

**Answer:** A, B, and C

**Explanation:**

```markdown
- A. Correct — Zookeeper elects Controller via ephemeral nodes based on creation sequence.
- B. Correct — partition leader election uses the ISR list to pick the most recent replica.
- C. Correct — brokers without Zookeeper connection lose leadership roles.
- D. Incorrect — Kafka does not elect Controllers within isolated network segments.
- E. Incorrect — leader election is deterministic, not random.
```

</details>

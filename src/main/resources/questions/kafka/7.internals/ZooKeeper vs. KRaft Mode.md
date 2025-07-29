## Question 1
```markdown  
In KRaft mode, what happens to metadata consistency if the active controller crashes during a topic creation request?  
```  

**Options**
```markdown  
- A. The operation is lost, requiring manual reprocessing.  
- B. The metadata quorum ensures another controller resumes the operation atomically.  
- C. ZooKeeper temporarily takes over to complete the request.  
- D. All brokers vote on the new topic configuration.  
```  

<details><summary>Response:</summary>  

**Answer:** B

**Explanation:**
```markdown  
- A. Incorrect. KRaft’s Raft consensus guarantees durability via replicated logs.  
- B. **Correct.** The metadata quorum ensures failover with no data loss.  
- C. ZooKeeper is unused in KRaft mode.  
- D. Brokers don’t vote; the controller orchestrates changes.  
```  
</details>  

---  

## Question 2
```markdown  
Why does KRaft mode reduce operational complexity compared to ZooKeeper?  
```  

**Options**
```markdown  
- A. It eliminates the need to manage a separate ZooKeeper cluster.  
- B. It allows brokers to directly modify metadata without consensus.  
- C. It removes the requirement for replication factors.  
- D. It merges controller and broker roles into a single process.  
```  

<details><summary>Response:</summary>  

**Answer:** A

**Explanation:**
```markdown  
- A. **Correct.** KRaft internalizes metadata management, removing ZooKeeper dependency.  
- B. Metadata changes still require quorum consensus.  
- C. Replication factors remain critical for data durability.  
- D. Controller/broker roles are still distinct (though colocated).  
```  
</details>  

---  

## Question 3
```markdown  
What is a key limitation of ZooKeeper that KRaft mode addresses?  
```  

**Options**
```markdown  
- A. ZooKeeper cannot store message payloads.  
- B. ZooKeeper’s write throughput degrades with large metadata volumes.  
- C. ZooKeeper lacks TLS support for secure communication.  
- D. ZooKeeper requires manual partition reassignment.  
```  

<details><summary>Response:</summary>  

**Answer:** B

**Explanation:**
```markdown  
- A. ZooKeeper was never designed for message storage.  
- B. **Correct.** ZooKeeper’s scalability limits were a bottleneck for Kafka metadata.  
- C. ZooKeeper supports TLS but isn’t the core limitation.  
- D. Reassignment is automated in both modes.  
```  
</details>  

---

## Question 4

```markdown
You have a Zookeeper cluster that needs to be able to withstand the loss of 2 servers and still be able to function. What size should your Zookeeper cluster have?
```

**Options**

```markdown
- A. 5
- B. 3
- C. 4
- D. 6
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
To tolerate N failures, Zookeeper needs 2N+1 nodes.

- A. ✅ 5 nodes can tolerate 2 failures (2×2+1 = 5).
- B. ❌ 3 nodes can only tolerate 1 failure.
- C. ❌ 4 is even-numbered – Zookeeper clusters must have odd numbers to reach majority.
- D. ❌ 6 nodes still can’t survive 3 failures since quorum is ⌈N/2⌉.
```

</details>

## Question 5
```markdown  
What happens if a KRaft controller partition (`@metadata`) becomes unavailable?  
```  

**Options**
```markdown  
- A. Producers and consumers continue working, but admin operations fail.  
- B. The entire cluster shuts down to prevent split-brain.  
- C. Brokers fall back to ZooKeeper temporarily.  
- D. Kafka automatically creates a new `@metadata` partition.  
```  

<details><summary>Response:</summary>  

**Answer:** A

**Explanation:**
```markdown  
- A. **Correct.** Data planes (producers/consumers) use cached metadata, but control plane operations (e.g., topic creation) block.  
- B. Only the controller is affected; brokers remain operational.  
- C. ZooKeeper is not a fallback in KRaft mode.  
- D. The partition is replicated via Raft; it cannot be "recreated" ad hoc.  
```  
</details>  

---  

## Question 6
```markdown  
Which ZooKeeper-specific feature is *not* replaced by KRaft?  
```  

**Options**
```markdown  
- A. Ephemeral nodes for broker liveness tracking.  
- B. Persistent metadata storage for topics.  
- C. Leader election for partition replicas.  
- D. SASL authentication for administrative APIs.  
```  

<details><summary>Response:</summary>  

**Answer:** D

**Explanation:**
```markdown  
- A. KRaft uses broker heartbeats, not ephemeral nodes.  
- B. Metadata is stored in Kafka’s log, not ZooKeeper.  
- C. Leader election is now Raft-based.  
- D. **Correct.** SASL is a security protocol independent of metadata management.  
```  
</details>  

---  

## Question 7

```markdown
A Zookeeper ensemble contains 5 servers. What is the maximum number of servers that can go missing and the ensemble still run?
```

**Options**

```markdown
- A. 2
- B. 4
- C. 1
- D. 3
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
Zookeeper requires a majority (quorum) of servers to function.

- A. ✅ 2 servers can fail in a 5-server cluster, leaving 3 (majority) running.
- B. ❌ 4 failed servers means only 1 remains – not enough for quorum.
- C. ❌ 1 failure means 4 remain, but we’re asked for the *maximum* tolerated.
- D. ❌ 3 failures leave 2 – still not a majority in a 5-node cluster.
```

</details>

---

## Question 8
```markdown  
How does ZooKeeper mode handle metadata propagation compared to KRaft?  
```  

**Options**
```markdown  
- A. ZooKeeper pushes metadata to brokers, while KRaft uses pull-based gossip.  
- B. ZooKeeper requires brokers to poll for changes, while KRaft streams updates via the metadata log.  
- C. Both modes use identical push-based mechanisms.  
- D. ZooKeeper replicates metadata via Kafka topics.  
```  

<details><summary>Response:</summary>  

**Answer:** B

**Explanation:**
```markdown  
- A. ZooKeeper uses watches (not push), and KRaft uses log replication.  
- B. **Correct.** ZooKeeper brokers poll ZK, while KRaft brokers subscribe to the metadata log.  
- C. The mechanisms differ fundamentally.  
- D. ZooKeeper stores metadata in its own ZNodes.  
```  
</details>  

---  

## Question 9

```markdown
What is the purpose of the `kafka-metadata-shell` tool in KRaft mode?
```

**Options**

```markdown
- A. To modify the Kafka broker configuration
- B. To list the available Kafka topics
- C. To monitor the Kafka cluster performance
- D. To interactively inspect the KRaft metadata
```

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**

```markdown
The `kafka-metadata-shell` tool is used to interactively explore the KRaft metadata state.

- A. Incorrect. Use `kafka-configs` or config files for that.
- B. Incorrect. Use `kafka-topics` to list topics.
- C. Incorrect. Use dedicated monitoring tools.
- D. Correct. This tool provides an interactive shell for inspecting metadata logs.
```

</details>

---

## Question 10

```markdown
What is the purpose of the `kafka-dump-log` tool in KRaft mode?
```

**Options**

```markdown
- A. To display the contents of the KRaft metadata log
- B. To modify the Kafka broker configuration
- C. To list the available Kafka topics
- D. To monitor the Kafka cluster performance
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
In KRaft mode, the `kafka-dump-log` tool is used to display the contents of the KRaft metadata log.

- A. Correct. It allows inspection of log segments and snapshots from the cluster metadata directory.
- B. Incorrect. Configuration changes are done via `server.properties` or `kafka-configs`, not this tool.
- C. Incorrect. Use `kafka-topics` to list Kafka topics.
- D. Incorrect. Monitoring is handled by other tools, not `kafka-dump-log`.
```

</details>

---

## Question 11

```markdown
What is the purpose of the `kafka-storage` tool in KRaft mode?
```

**Options**

```markdown
- A. To configure the storage directories for Kafka brokers
- B. To manage the Kafka consumer offsets
- C. To generate a cluster ID and format storage directories
- D. To monitor the disk usage of Kafka brokers
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Used before starting Kafka to format directories with a cluster ID.

- A. Incorrect — it formats, not configures directories
- B. Incorrect — unrelated to offsets
- C. Correct — for generating ID + formatting
- D. Incorrect — not for monitoring
```

</details>

---

## Question 12

```markdown
What is the default location for the Kafka metadata log in KRaft mode?
```

**Options**

```markdown
- A. The first directory specified in the `log.dirs` configuration
- B. The directory specified in the `metadata.log.dir` configuration
- C. The directory specified in the `controller.log.dir` configuration
- D. The Kafka data directory
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
Defaults to the first path in `log.dirs`.

- A. Correct — default behavior
- B. Incorrect — used only if explicitly configured
- C. Incorrect — no such config
- D. Incorrect — no special Kafka "data directory"
```

</details>

---

## Question 13
```markdown  
Why might KRaft mode improve topic creation latency compared to ZooKeeper mode?  
```  

**Options**
```markdown  
- A. KRaft skips replica assignment for new topics.  
- B. ZooKeeper’s sequential ZNode writes introduce delays.  
- C. KRaft uses in-memory metadata caches for all operations.  
- D. ZooKeeper requires manual approval for topic creation.  
```  

<details><summary>Response:</summary>  

**Answer:** B

**Explanation:**
```markdown  
- A. Replica assignment still occurs.  
- B. **Correct.** ZooKeeper’s write amplification adds latency.  
- C. KRaft relies on persisted logs, not just caches.  
- D. Approval is not a ZooKeeper feature.  
```  
</details>  

---  

## Question 14
```markdown  
Which command would *not* work in a KRaft-only cluster?  
```  

**Options**
```markdown  
- A. `kafka-topics --bootstrap-server BROKER --create --topic test`.  
- B. `kafka-configs --zookeeper ZK:2181 --entity-type topics --alter`.  
- C. `kafka-metadata-quorum --bootstrap-server BROKER --describe`.  
- D. `kafka-features --bootstrap-server BROKER --describe`.  
```  

<details><summary>Response:</summary>  

**Answer:** B

**Explanation:**
```markdown  
- A. Works (uses broker API).  
- B. **Correct.** `--zookeeper` is invalid in KRaft mode.  
- C. Valid KRaft command.  
- D. Valid for feature flag management.  
```  
</details>  

---  

## Question 15
```markdown  
What guarantees does KRaft’s Raft consensus provide that ZooKeeper does not?  
```  

**Options**
```markdown  
- A. Linearizable reads without ZooKeeper’s sequential consistency.  
- B. Higher throughput for message production.  
- C. Built-in support for tiered storage.  
- D. Automatic partition rebalancing.  
```  

<details><summary>Response:</summary>  

**Answer:** A

**Explanation:**
```markdown  
- A. **Correct.** KRaft’s Raft implementation offers stronger consistency guarantees.  
- B. Throughput is unrelated to consensus.  
- C. Tiered storage is a broker feature.  
- D. Rebalancing is handled by the controller, not consensus.  
```  
</details>  

---

## Question 16

```markdown
What is the purpose of the `kafka-metadata-quorum` tool in KRaft mode?
```

**Options**

```markdown
- A. To manage the Kafka consumer offsets
- B. To generate a cluster ID for the Kafka cluster
- C. To describe the runtime status of the KRaft metadata quorum
- D. To modify the Kafka topic configurations
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
Used to inspect quorum health and metadata replication.

- A. Incorrect — offsets are not its concern
- B. Incorrect — that’s `kafka-storage`
- C. Correct — used with `describe` and `--status`
- D. Incorrect — not for topic config changes
```

</details>

---


## Question 1

```markdown
Which of the following is stored in Zookeeper for a Kafka cluster? (Select two)
```

**Options**

```markdown
- A. Consumer offsets
- B. Kafka broker information
- C. Topic partition assignments
- D. Topic-level configurations
- E. Producer client IDs
```

<details><summary>Response:</summary>

**Answer:** B, D

**Explanation:**

```markdown
In a Kafka cluster, Zookeeper stores:

- B. Kafka broker information: It keeps details about each broker in the cluster.
- D. Topic-level configurations: This includes settings like retention and replication.

Incorrect options:

- A. Consumer offsets are stored in the Kafka topic `__consumer_offsets`.
- C. Partition assignments are handled by the Kafka controller.
- E. Producer client IDs are not stored in Zookeeper.
```

</details>

---


## Question 10

```markdown
What type of Zookeeper znode does a Kafka broker create during startup to indicate its liveness?
```

**Options**

```markdown
- A. Persistent znode
- B. Ephemeral znode
- C. Sequential znode
- D. Container znode
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- B. Correct — Brokers use ephemeral znodes to register themselves, which disappear if the broker disconnects.
- A/C/D. Incorrect — Persistent, sequential, and container znodes are not used for broker registration.
```

</details>

---

## Question 11

```markdown
How does a newly started Kafka broker synchronize metadata about existing topics and partitions?
```

**Options**

```markdown
- A. By fetching metadata from the Controller broker
- B. By reading Zookeeper znodes under `/brokers/topics`
- C. By querying all other brokers in the cluster
- D. By loading metadata from its local log directories
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
- B. Correct — Brokers fetch topic/partition metadata from Zookeeper's `/brokers/topics` hierarchy.
- A. Incorrect — The Controller manages leader elections, not metadata sync.
- C/D. Incorrect — Brokers do not query peers or rely on local logs for initial metadata.
```

</details>

---

## Question 4
```markdown
Which of the following actions does a Kafka broker perform via Zookeeper during startup? (Select two)
```

**Options**
```markdown
- A. Registers its broker ID and ephemeral znode  
- B. Fetches the latest consumer offsets  
- C. Downloads all topic messages to local disk  
- D. Retrieves the current Controller broker ID  
- E. Validates SSL certificates for all other brokers  
```

<details><summary>Response:</summary>  

**Answer:** A, D

**Explanation:**
```markdown
- A. Correct — Brokers register ephemeral znodes in Zookeeper to signal liveliness.  
- D. Correct — Brokers identify the Controller via Zookeeper.  
- B. Incorrect — Offsets are stored in `__consumer_offsets`.  
- C. Incorrect — Brokers only load assigned partitions.  
- E. Incorrect — SSL validation is independent of Zookeeper.  
```  
</details>  

---

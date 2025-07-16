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

### **Question 4**
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
What is a risk of enabling `unclean.leader.election.enable` in KRaft mode?  
```  

**Options**
```markdown  
- A. It corrupts the `@metadata` topic.  
- B. It allows out-of-sync replicas to become metadata leaders, risking inconsistency.  
- C. It disables the controller quorum entirely.  
- D. It forces ZooKeeper to take over metadata duties.  
```  

<details><summary>Response:</summary>  

**Answer:** B

**Explanation:**
```markdown  
- A. This setting affects data partitions, not metadata.  
- B. **Correct.** While rare, this could allow stale metadata to propagate.  
- C. The quorum remains active.  
- D. ZooKeeper is inactive in KRaft.  
```  
</details>  

---  

## Question 10
```markdown  
Which metric would indicate ZooKeeper is a bottleneck in a legacy Kafka cluster?  
```  

**Options**
```markdown  
- A. High `kafka.controller:type=KafkaController,name=OfflinePartitionsCount`.  
- B. Elevated `zookeeper:type=Leader, name=RequestLatencyMs`.  
- C. Spikes in `kafka.server:type=BrokerTopicMetrics,name=BytesOutPerSec`.  
- D. Increased `kafka.network:type=RequestMetrics,name=TotalTimeMs`.  
```  

<details><summary>Response:</summary>  

**Answer:** B

**Explanation:**
```markdown  
- A. Indicates partition issues, not ZooKeeper latency.  
- B. **Correct.** High ZooKeeper request latency directly impacts metadata operations.  
- C. Measures producer/consumer throughput, not ZooKeeper.  
- D. Reflects general broker load, not ZooKeeper.  
```  
</details>  

---  

## Question 11
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

## Question 12
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
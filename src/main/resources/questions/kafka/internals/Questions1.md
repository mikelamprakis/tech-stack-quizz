## Question 1

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
In KRaft mode, the `process.roles` configuration is used to specify whether the server acts as a controller, broker, or both.

- A. ✅ Correct — Used to define the role of the server
- B. ❌ The unique identifier is configured with `node.id`
- C. ❌ The controller listeners are set using `controller.listener.names`
- D. ❌ Metrics reporters are configured separately
```

</details>

---

## Question 2

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
Production KRaft clusters should use separate roles for brokers and controllers.

- A. ❌ Combined mode is not supported in production
- B. ✅ Recommended for isolating and scaling roles independently
- C. ❌ All-controller setup lacks brokers for data handling
- D. ❌ `process.roles` is mandatory in KRaft mode
```

</details>

---

## Question 3

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
This config defines which controller nodes vote in leader elections and replicate metadata.

- A. ❌ Listeners are configured with `controller.listener.names`
- B. ❌ No in-sync replicas concept for controllers
- C. ✅ Used for specifying controller quorum voters
- D. ❌ Unrelated to metrics configuration
```

</details>

---

## Question 4

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
KRaft relies on quorum-based metadata management. A minimum of 3 ensures fault tolerance.

- A. ❌ No fault tolerance with a single node
- B. ❌ Two nodes can't form a majority if one fails
- C. ✅ Minimum for quorum and availability
- D. ❌ Valid but not the minimum required
```

</details>

---

## Question 5

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
KRaft requires a majority quorum to function. Without it, the cluster halts.

- A. ❌ Cluster halts, not just degraded
- B. ❌ No automatic re-election without quorum
- C. ✅ Must restore majority to resume operations
- D. ❌ Brokers don't assume controller duties in KRaft
```

</details>

---

## Question 6

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
This tool is used for initializing KRaft nodes with a cluster ID.

- A. ❌ Does not configure directories — just formats them
- B. ❌ Unrelated to consumer offsets
- C. ✅ Generates cluster ID and formats node storage
- D. ❌ No monitoring functionality
```

</details>

---

## Question 7

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
If `metadata.log.dir` is not set, the first entry in `log.dirs` is used.

- A. ✅ This is the default behavior
- B. ❌ Only applies if explicitly set
- C. ❌ Not a valid KRaft config
- D. ❌ Too ambiguous — Kafka doesn’t define a generic "data dir"
```

</details>

---

## Question 8

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
This tool provides introspection into the state of the controller quorum.

- A. ❌ Offset management is not its function
- B. ❌ Cluster IDs are created via `kafka-storage`
- C. ✅ Used to inspect quorum health and status
- D. ❌ Use `kafka-configs` for topic configs
```

</details>

---

## Question 9

```markdown
Which of the following metrics is used to monitor the lag between the active KRaft controller and the last committed record in the metadata log?
```

**Options**

```markdown
- A. `kafka.controller:type=KafkaController,name=ActiveControllerCount`
- B. `kafka.controller:type=ControllerEventManager,name=EventQueueTimeMs`
- C. `kafka.controller:type=KafkaController,name=LastCommittedRecordOffset`
- D. `kafka.controller:type=KafkaController,name=LastAppliedRecordLagMs`
```

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**

```markdown
This metric shows how far behind the controller is in applying metadata records.

- A. ❌ Shows how many controllers are active, not lag
- B. ❌ Measures event processing delay, unrelated
- C. ❌ Shows committed offset but not the lag
- D. ✅ Accurately reflects metadata lag for followers
```

</details>

---

## Question 21

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

In KRaft mode, the `controller.quorum.voters` configuration explicitly defines the set of brokers that are part of the controller quorum and participate in the voting process for leader election and metadata management. If a new broker is not added to this configuration, it will join the cluster as an observer.

As an observer, the new broker can still receive and replicate metadata from the active controller, but it does not have voting rights and does not actively participate in the quorum's decision-making process. This allows the new broker to catch up with the current state of the cluster without disrupting the existing controller quorum.

To promote the new broker to a voter, the `controller.quorum.voters` configuration needs to be updated to include the new broker's details (`{id}@{host}:{port}`). Once the configuration is updated and propagated to all the brokers, the new broker will become a full-fledged member of the controller quorum.

- A. Incorrect – the broker must be manually added to become a voter.
- B. Correct – the broker joins as a non-voting observer.
- C. Incorrect – it can still join as an observer.
- D. Incorrect – it does not replace an existing voter unless configured to.
```

</details>

---

## Question 22

```markdown
What is the purpose of the `kafka.controller:type=SnapshotEngine,name=SnapshotGenerationTimeoutCount` metric in KRaft mode?
```

**Options**

```markdown
- A. It measures the number of snapshots that were generated successfully within the configured timeout
- B. It indicates the count of snapshots that failed to generate due to a timeout
- C. It represents the number of snapshots that are currently being generated
- D. It tracks the count of snapshots that were generated after exceeding the configured timeout
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
In KRaft mode, the `SnapshotGenerationTimeoutCount` metric counts how many snapshot generation attempts failed due to exceeding the configured timeout period.

Snapshot generation captures the state of the metadata log and helps with faster startup and reduced log size. If snapshot creation takes too long, this metric increases, signaling possible performance or resource issues.

- A. Incorrect – this metric tracks failures, not successful snapshots.
- B. Correct – it indicates failed snapshot attempts due to timeout.
- C. Incorrect – it does not track in-progress snapshots.
- D. Incorrect – it does not count successful snapshots that simply exceeded the timeout.
```

</details>

---

## Question 23

```markdown
In KRaft mode, what happens when a broker is removed from the `controller.quorum.voters` configuration?
```

**Options**

```markdown
- A. The removed broker is immediately disconnected from the cluster
- B. The removed broker continues to operate as a non-voter observer
- C. The removed broker becomes a candidate and triggers a new controller election
- D. The removed broker enters a controlled shutdown process
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
In KRaft mode, removing a broker from `controller.quorum.voters` turns it into an observer. It continues to function normally for client operations but no longer participates in controller elections or metadata voting.

- A. Incorrect – it stays connected and functional.
- B. Correct – it becomes a non-voting observer.
- C. Incorrect – it no longer participates in elections.
- D. Incorrect – it remains online; there’s no shutdown triggered.
```

</details>

---

## Question 24

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
The `controller.quorum.election.backoff.max.ms` sets the upper bound for random delay before brokers attempt controller election. A high value means longer potential wait times, delaying election and increasing failover downtime.

- A. Incorrect – it actually reduces election frequency.
- B. Incorrect – longer backoff means slower elections.
- C. Correct – long backoff delays election, increasing potential downtime.
- D. Incorrect – it directly impacts election timing.
```

</details>

---

## Question 25

```markdown
What is the purpose of the `kafka.controller:type=QuorumController,name=ActiveControllerCount` metric in KRaft mode?
```

**Options**

```markdown
- A. It indicates the number of active controllers in the KRaft cluster
- B. It represents the number of brokers currently serving as controllers
- C. It measures the count of controllers that have been active since the cluster started
- D. It tracks the number of controller failover events that have occurred
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
In KRaft mode, this metric indicates how many controllers are currently active.

- A. ✅ Shows number of active controllers – should be 1 in a healthy cluster.
- B. ❌ It’s not tied to brokers acting as controllers.
- C. ❌ Not a historical count, just current state.
- D. ❌ Doesn’t count failovers; only shows active controller count.
```

</details>

---

## Question 26

```markdown
What is the significance of the `kafka.controller:type=QuorumController,name=LastAppliedRecordTimestamp` metric in KRaft mode?
```

**Options**

```markdown
- A. It indicates the timestamp of the last record appended to the metadata log
- B. It represents the timestamp of the last record replicated to all the controllers
- C. It measures the timestamp of the last record applied by the active controller
- D. It tracks the timestamp of the last record committed by the active controller
```

<details><summary>Response:</summary>

**Answer:** C

**Explanation:**

```markdown
This metric shows how up-to-date the active controller is in processing metadata.

- A. ❌ Appending ≠ applying.
- B. ❌ It’s not about replication to all controllers.
- C. ✅ Timestamp of last record the active controller applied.
- D. ❌ “Committed” is not the same as “applied.”
```

</details>

---

## Question 27

```markdown
What is the purpose of the `kafka.controller:type=ControllerChannelManager,name=TotalQueueSize` metric in KRaft mode?
```

**Options**

```markdown
- A. It measures the total number of messages in the controller's request queue
- B. It indicates the total size of the metadata log in bytes
- C. It represents the total number of active controller connections
- D. It tracks the total number of pending controller requests
```

<details><summary>Response:</summary>

**Answer:** A

**Explanation:**

```markdown
The metric shows the load on the controller’s request queue.

- A. ✅ Represents number of queued requests waiting to be processed.
- B. ❌ Not related to metadata log size.
- C. ❌ Not about connections.
- D. ❌ Similar, but only “messages in queue” is accurate.
```

</details>

---

## Question 28

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
This timeout controls how long the controller waits for quorum replies.

- A. ✅ Longer timeout means more wait time before failure.
- B. ❌ Larger value ≠ shorter time.
- C. ❌ Not about request processing duration.
- D. ❌ Heartbeat intervals are separate settings.
```

</details>

---

## Question 29

```markdown
What is the purpose of the `kafka.controller:type=QuorumController,name=LastCommittedRecordOffset` metric in KRaft mode?
```

**Options**

```markdown
- A. It indicates the offset of the last record appended to the metadata log
- B. It represents the offset of the last record replicated to all the controllers
- C. It measures the offset of the last record applied by the active controller
- D. It tracks the offset of the last record committed by the active controller
```

<details><summary>Response:</summary>

**Answer:** D

**Explanation:**

```markdown
This metric tracks the last offset that has been committed to a quorum.

- A. ❌ “Appended” happens before “committed.”
- B. ❌ Doesn’t require all controllers – just a quorum.
- C. ❌ Applying ≠ committing.
- D. ✅ Shows offset of last quorum-acknowledged (committed) record.
```

</details>

---

## Question 30

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
This timeout governs how long controllers wait before giving up on fetch responses.

- A. ❌ A low value shortens the wait, not increases it.
- B. ✅ Low value = short time to wait for fetch response.
- C. ❌ Not about broker fetches.
- D. ❌ Frequency is a different setting (polling interval).
```

</details>

---

## Question 31

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
In a 5-node Zookeeper cluster, a **majority (quorum)** of 3 nodes must be available for the ensemble to operate.

- A. Correct — if 2 nodes go down, 3 remain, which is a majority.
- B. Incorrect — with 4 nodes down, only 1 is left (not enough for quorum).
- C. Incorrect — although the ensemble still runs with only 1 node down, the question asks for the *maximum* number that can go missing.
- D. Incorrect — if 3 go down, only 2 are left, which is less than quorum.
```

</details>

---

## Question 32

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
To tolerate the failure of N servers in a Zookeeper ensemble, you need a total of **2N+1** nodes.

- A. Correct — 2N+1 = 5 can tolerate up to 2 failures.
- B. Incorrect — a 3-node cluster can tolerate only 1 failure.
- C. Incorrect — even if 2 nodes go down in a 4-node cluster, quorum is lost.
- D. Incorrect — while 6 nodes are more than enough, Zookeeper prefers odd numbers to avoid split-brain scenarios.
```

</details>


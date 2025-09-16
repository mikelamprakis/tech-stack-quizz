## Question 1

```markdown
You are using Kafka REST Proxy to receive data from producers, and the data is base64-encoded. How does the Kafka REST Proxy handle this base64-encoded data, and what should consumers expect when reading the data from Kafka?
```

**Options**
```markdown
- A. The Kafka REST Proxy converts the base64-encoded data into JSON before sending it to Kafka, and consumers will receive the data as JSON objects.
- B. The Kafka REST Proxy converts the base64-encoded data into bytes before sending it to Kafka, and consumers will receive the data as binary.
- C. The Kafka REST Proxy stores the data as plain text in Kafka, and consumers will receive it as text.
- D. The Kafka REST Proxy stores the data as base64-encoded strings in Kafka, and consumers must decode it when reading.
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
When the Kafka REST Proxy receives base64-encoded data from producers, it converts this data into bytes before sending it to Kafka. Therefore, consumers that are reading directly from Kafka will receive this data as binary. This conversion process ensures that the data remains compatible and consistent for all parties interacting with the Kafka ecosystem, regardless of their method of data submission or consumption.
```

</details>

---

## Question 2

```markdown
What aspects of Kafka security should be monitored to ensure a secure environment?
```

**Options**
```markdown
- A. Keeping an eye on ACL changes
- B. Monitoring failed authentication attempts (failed_authentication_total)
- C. Ensuring SASL mechanisms are functioning as expected
- D. Ensuring SSL/TLS handshake rate (ssl_handshake_rate) is functioning as expected
- E. Monitoring the message compression type for all topics
```

<details><summary>Response:</summary>

**Answer:** A, B, C, D

**Explanation:**

```markdown
To maintain a secure Kafka environment, it is crucial to monitor failed authentication attempts (failed_authentication_total), ensure that the SSL/TLS handshake rate (ssl_handshake_rate) and SASL mechanisms are functioning as expected, and keep an eye on ACL changes. These aspects help ensure that the security mechanisms are working correctly and any potential security issues are quickly identified and addressed. Monitoring message compression type is not directly related to Kafka security.
```

</details>

---

## Question 3

```markdown
Which SASL mechanisms are supported by Kafka for authentication?
```

**Options**
```markdown
- A. SASL/GSSAPI (Kerberos)
- B. SASL/MD5
- C. SASL/SCRAM
- D. SASL/SSL
- E. SASL/PLAINTEXT
- F. SASL/OAUTHBEARER
```

<details><summary>Response:</summary>

**Answer:** A, C, E, F

**Explanation:**

```markdown
Kafka supports several SASL mechanisms for authentication:

SASL/PLAINTEXT: Simple username/password mechanism; easy to set up but considered less secure and typically used for development environments.

SASL/SCRAM: Uses usernames and passwords along with a challenge-response mechanism to enhance security.

SASL/GSSAPI (Kerberos): Integrates with systems like Microsoft Active Directory for robust security, though complex to configure.

SASL/OAUTHBEARER: Utilizes OAUTH2 tokens, suitable for modern applications with token-based authentication systems.

All SASL mechanisms except PLAINTEXT require SSL encryption on the broker side to ensure secure transmission of authentication data.
```

</details>

---

## Question 4

```markdown
You are tasked with setting up Access Control Lists (ACLs) in a Kafka cluster to manage user permissions. Why do you need to connect directly to ZooKeeper for ACL management?
```

**Options**
```markdown
- A. Kafka brokers handle ACL management internally, so there is no need to connect to ZooKeeper
- B. ZooKeeper is used to store and manage ACL data, so direct connections to ZooKeeper are necessary
- C. ACL data is managed through Kafka's REST API, so direct connections to ZooKeeper are not required
- D. ACLs are managed by the Kafka controller, so connect to the controller node to modify ACLs
```

<details><summary>Response:</summary>

**Answer:** B

**Explanation:**

```markdown
Direct connections to ZooKeeper are used for managing Access Control Lists (ACLs) in Kafka because ACL data is stored and managed within ZooKeeper. Commands such as setting ACLs for users or topics involve specifying ZooKeeper's connection details directly to ensure that the access permissions are properly configured and enforced across the Kafka cluster. For example, using the kafka-acls tool to modify ACLs requires a direct ZooKeeper connection.
```

</details>

--- 

You are using KSQL to analyze sales and customer data. You have two streams, sales_stream and customer_stream. You want to create a new stream that includes all sales records, along with matching customer information if available. If there is no matching customer information for a sales record, the sales record should still be included in the result.

Which type of join would you use for this scenario?

Inner Join

Right Join

Your answer is correct
Left Join

Outer Join

Overall explanation
In this scenario, you want to include all records from the sales_stream (the left side of the join) and match them with records from the customer_stream. If no matching customer information is found, the sales record should still be included in the result. This is achieved using a Left Join, which includes all records from the left side and matches records from the right side when available.

Domain
Kafka KSQL
Beta

---


What is a characteristic of a Tumbling Window in KSQL?

Your answer is correct
Has a fixed duration without overlaps.

Overlaps with other windows.

Dynamically adjusts based on activity.

Used primarily for joining data streams.

Overall explanation
A Tumbling Window is defined by a fixed duration and does not overlap with other windows, making it suitable for segmenting data into distinct, non-overlapping intervals.

Domain
Kafka KSQL
Beta

---



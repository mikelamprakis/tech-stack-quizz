A Zookeeper ensemble contains 5 servers. What is the maximum number of servers that can go missing and the ensemble still run?

1. 2
2. 4
3. 1
4. 3


Explanation
majority consists of 3 zk nodes for 5 nodes zk cluster, so 2 can fail


---

You have a Zookeeper cluster that needs to be able to withstand the loss of 2 servers and still be able to function.
What size should your Zookeeper cluster have?

1. 5
2. 3
3. 4
4. 6


Explanation
Your Zookeeper cluster needs to have an odd number of servers, and must maintain a majority of servers up to be able to vote.
Therefore, a 2N+1 zookeeper cluster can survive to N zookeeper being down, so here the right answer is N=2, 2*N+1=5

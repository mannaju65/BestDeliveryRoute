Problem statement : 
Find shortest possible path for an delivery executive to complete deliveries of a batch of orders of any food delivery app.

Assumptions provided in problem statement :

1. average speed of delivery partner is 20km/hr.
2. restaurents were informed about these orders at the exact same time and all of them confirm on doing it immediately.

Assumptions Regarding Delivery :

1. If there are multiple orders to be picked up from same restaurent then delivery partner will wait at restaurent untill all the orders are prepared.
2. A customer can order only from one restaurent at a time.


Overview : 

Implemented an in-memory application to solve this problem statement.
Created all Necessary Entity classes with minimum data memebers required to implement the solution. [Customer, Restaurant, DeliveryPartner, Order etc.]

Algorithm to find the best way to finish orders in shortest time :-
Greedy :
1. The starting point of the path is the driver's location itself. time = 0.
2. Create a list of possible nodes(nodesToVisit) that the driver can visit from current location -
   so at time = 0 the driver can go only to the restaurents.
3. From current location calculate the times to reach each node in nodesToVisit list and then pick 
   the node with minimum time required. add that picked node to shortest path.
   - If the picked node is a restaurent then we need to consider the food preparation time also in the above calculation.
   - If the picked node is a restaurent then we will add the customer of that restaurent's orders in the nodesToVisit list.

4. step 3 will until there is no node left to visit.

Brute Force :

1. The starting point of the path is the driver's location itself. time = 0.
2. Recursively find all possible combination of nodes to complete the batch.
   - Need to consider that a customer location node can only be reached after picking up it's order.
3. Find the shortest time path by checking the time to reach last node for every possible path.
# Senate-Bus-Problem
Devising a solution to a problem that requires synchronization.

This program will demonstate how to use mutexes and semaphores to acheive synchronization in an actual program.

# Problem
This problem was originally based on the Senate bus at Wellesley College. Riders come to a bus
stop and wait for a bus. When the bus arrives, all the waiting riders invoke boardBus, but anyone who
arrives while the bus is boarding has to wait for the next bus. The capacity of the bus is 50 people; if there
are more than 50 people waiting, some will have to wait for the next bus. When all the waiting riders have
boarded, the bus can invoke depart. If the bus arrives when there are no riders, it should depart
immediately.

1. To compile the code use the following command :
	javac Main.java

2. To run the programme : 
	java Main

3. To exist from the programme : 
	Press any key

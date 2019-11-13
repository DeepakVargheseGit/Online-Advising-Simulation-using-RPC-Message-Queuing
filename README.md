Implemented an online advising system which is similar to what you might have used for registering your classes.
Students request clearance for a course from the advisor. 
At some other time the advisor approves or disapproves the request and then the student is notified of the advisor’s decision. 
You will require 4 processes to simulate this: a student process, an advisor process, a notification process and a message queuing server (MQS). The student process, advisor process and notification process communicate through the message queuing server (message oriented middleware). 
Communication from these processes to the message queuing server is through remote procedure calls.


The Student process works on behalf of the student. It takes as input (from the command prompt) the name of the student and the course for which the student wants clearance. The Student process should then send a message through the MQS to the Advisor seeking clearance. It should keep doing this (under control of the user) until it is killed.

The Student process works on behalf of the student. It takes as input (from the command prompt) the name of the student and the course for which the student wants clearance. The Student process should then send a message through the MQS to the Advisor seeking clearance. It should keep doing this (under control of the user) until it is killed.

Advisor Process:

When it is started, the Advisor Process should contact the MQS for any message from the Student process. If there are any messages then it should retrieve them and approve or disapprove the requests based on a random probability. It should then send a message for each request (message) through the MQS to the Notification process so that it (the Notification process) can notify the Student process about the decision. If there are no messages for the Advisor process in the MQS then it should sleep for 3 seconds and again contact back to check for messages. It should keep doing this until is killed. You should also print out a message for each request (message) that the Advisor process processes (whether it is approved or not).

Notification Process:

The Notification process notifies the Student process about the Advisor’s decision. To simulate this you just have to print on the command prompt the student name, course and the advisor decision. The Notification process should check MQS for messages from the Advisor process. If there are any messages then it should retrieve them and notify the student by printing as stated above. If there are no messages then it should sleep for 7 seconds and then contact the MQS again. It should keep doing this until is killed.

Message Queuing Server (MQS):

This process simulates the Message Queuing server. MQS should have the following:
1. It should be able to store or return a message from or to any requesting process.
2. You should have only one physical queue (single array, linked list or queue, etc.) to store the messages from both Student and Advisor process.
3. Messages once retrieved should be physically deleted from the queue.
4. Messages should be persistent, i.e. even if you shut down the server the messages should not be lost. For that you should store the messages in a file. When the MQS is restarted it should reload all the messages from the file to your main memory data structure (array or linked-list etc). We will assume that the MQS will be killed only after all the 3 other processes are killed so that you don’t need to worry about problems that would arise if it were killed while in communication.

Other Specifications:
1. The MQS will be started first. The other 3 processes can be started or stopped in any order. So make sure that your application works fine independently of the order in which these 3 processes are started and stopped.
2. For the Advisor and Notification process, print a message (like “no message found”) on the console when it probes the MQS and there is no message.
3. You may use any programming language, Operating system, or Server to complete this lab.

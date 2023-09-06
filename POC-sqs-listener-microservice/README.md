
There are two ways to poll Amazon SQS:

1. Short polling:
        1. With long polling, your application polls the service for short period of time, such as 2 seconds.
        2. If there are no messages available, your application will receive an empty response and will have to poll again.
2. Long polling:
        1. With short polling, your application polls the service for a longer period of time, such as 20 seconds.
        2. If there are no messages available, your application will still receive a response, but response will contain a message that tells your application to wait up to 20 seconds for messages to become available.

Long polling is recommended when consuming messages from Amazon SQS because this will reduce the number of empty responses that your application receives.

---
## **poll** 
*verb*
`TELECOMMUNICATIONS|COMPUTING`
check the status (of a device), especially as part of a repeated cycle.

--- 

We don't want to write the polling algorithm on our own because this will involve threading and failure handling.
Unless necessary, otherwise.

**Spring Cloud** for AWS brings the polling logic for SQS and SNS.

We annotate the method with `@SqsListener` and can subscribe to a queue.

All SQS messages are strings. 

We get support for serializing the objects and deserializing the messages from the AWS SDK.

**_serializing :_** _converting Java objects to strings._ 
**_deserializing :_** _converting messages to Java objects._

---
## Types of SQS queues

There are two types of queue in Amazon SQS.
1. Standard queue
2. FIFO queue

## 1. Standard queue
1. A Standard queue in Amazon SQS is a type of message queue that allows you to send and receive messages between different components or systems.

    _Imagine you have two software components, Components A and Component B, that need to communicate with each other._
    _However, they may not be running at the same time._
	*To enable communication and ensure that messages are reliably delivered between them, you can use a standard SQS queue.*

	**Benefits of using a Standard SQS queue:**
	
	1. Reliable message delivery: Messages sent to a Standard queue are stored redundantly across multiple servers, ensuring their durability and availability.
	2. Scalability: Standard queues can handle a high volume of messages and scale automatically to accommodate increased message traffic.
	3. Asynchronous communication: Components can send and receive messages independently, allowing them to operate asynchronously and decoupled from each other.
	4. Decoupling components: Standard queues help decouple the sender and the receiver allowing them to evolve independently without affecting each other
	5. Retry and error handling: If a message fails to be processed by the receiver, SQS can be configured to automatically retry or move the message to a designated error queue for further analysis.

	**Use Cases for Standard SQS queues:**
	
	1. Event-driven architectures :
			Standard queues are well-suited for building event-driven systems where components need to exchange messages asynchronously.
			For example, you can use Standard queues to decouple event producer from event consumers, allowing them to operate independently and scale as needed.
	2. Load leveling and scaling:
			When you have a system that experiences variable or unpredictable loads, a Standard queue can act as a buffer between components that produce work and the components that process it. 
			Standard queues help distribute load evenly across multiple consumers by allowing multiple instances of a component to process messages concurrently. This enables horizontal scaling and efficient utilization of resources.
	3. Notifications and alerts:
			Standard queues can be used to deliver notification and alerts to subscribers.
			For example, you can send notification about system events, updates or important changes to interested parties using Standard queue as an intermediary.
	4. Log processing:
			Standard queues can facilitate log processing and analysis workflows. 
			Log messages from different sources can be sent to a queue, and consumers can process and analyze logs asynchronously, enabling efficient log management and monitoring.
	5. Task queues:
			Standard queues can be used to implement task queues, where components submit tasks to be processed by workers. This is useful for scenarios such as job scheduling, background processing, and distributed computing.
			
## 2. FIFO queue
2. A FIFO (First-In-First-Out) queue in Amazon SQS is type of message queue that ensures strict ordering and exactly-once processing of messages.

	*Imagine you have 2 software components, Component A and Component B, that need to communicate with each other while preserving the order of messages .
	If the order of messages is important - for example, if they represent a task that must be processed sequentially- you can use an SQS FIFO queue.

	*Component A can send messages to FIFO queue, and each message is assigned a unique identifier.
	The FIFO queue guarantees that the messages are stored and delivered in the exact same order they are sent. The first message sent will be the first message received.

	*Component B can retrieve messages from FIFO queue one by one, in the same order they were sent. This ensures that the messages are processed sequentially and in a predictable manner.*

	**Benefits of using a FIFO queue:**
	1. Strict message ordering: FIFO queues guarantees that messages are processed in the exact order they are sent. This ensures that the order of tasks or event is maintained.
	2. Exactly-once processing: FIFO queues ensure that each message is processed exactly once, eliminating duplicates. This readability is crucial for critical or sensitive applications.
	3. Message duplication: FIFO queues can identify and eliminate duplicate messages based on their unique identifiers. This prevents the same message from being processed multiple times.
	4. High throughput: FIFO queues can handle a large number of messages per second while maintaining the order and exactly-once processing guarantees.
	5. Multiple message groups: FIFO queues support message groups, which allows you to group related messages together. Messages within the same group are always processed one by one, ensuring order within the group while allowing for concurrency across different groups. 
	
	**Use cases for FIFO queues:**

	1. Order processing and fulfilment: 
			 FIFO queues are commonly used in e-commerce applications to handle order processing and fulfilment. They ensure that the orders are processed in the exact order they are received, ensuring fairness and adherence to customer expectations.
	2. Financial transactions: 
			FIFO queues are valuable for processing financial transactions, where the order of operations is critical. For example, processing stocks trades or financial transfers in the order they are received helps maintain consistency and prevent discrepancies. 
	3. Deduplication of requests: 
			FIFO queues with their exactly-once processing guarantee can be used to handle deduplications of requests. If duplicate requests are received, FIFO queue will only process the first occurrence, avoiding redundant or erroneous operations.
	4. Sequential workflows: 
			FIFO queues are suitable for orchestrating sequential workflows or multi-step processes. Each step can be represented by a message in the queue, and consumers process the messages in the precise order, ensuring workflow progresses clearly.
	5. Message driven architectures with strict ordering requirements: 
			In certain systems, maintaining strict messaging ordering is crucial. FIFO queues provide the necessary reliability and ordering guarantees for applications where the sequence of the message is critical, such as event processing, data pipelines, or stateful workflows.  


---
## SQS Queue configuration 



![[create-queue-config.png]]


### **Visibility timeout:** 
When a message is received from an Amazon SQS queue by a consumer, its visibility is temporarily hidden from other consumers for the duration of the visibility timeout. 

Visibility timeout refers to the duration during which a message retrieved from an Amazon SQS queue becomes temporarily invisible to other consumers.

<blockquote> <i><p>This message is not physically in the queue during the visibility timeout period.</p>

<p>During this period, the message is considered "in-flight" and is associated with the consumer that retrieved it.</p>

<p>The consumer that retrieved the message has a specific amount of time, known as the processing time, to process and delete the message from the queue.</p>

<p>If the consumer successfully processes the message and it is deleted within the processing time, the message is removed from the queue permanently.</p>

<p>If the consumer fails to process and delete the message within the processing time, or if it encounters an error during processing, the message becomes visible to other consumer again after the visibility timeout expires.<p>
<p>This allows another consumer to attempt processing the message.</p></i></blockquote>

Visibility timeout ensures that the message is exclusively processed by a single consumer at a time.

Once the message is successfully processed and deleted, it is entirely removed from the queue.

<blockquote>
	<i>
	<p><b> Q. What is an "in-flight" message? What happens in this "in-flight" period?</b></p>
	<p>"In-flight" refers to the state of a message in Amazon SQS when it has been retrieved by a consumer and is being actively processed.</p>
	<p>During the "in-flight" period,</p>
	<ul>
		<p>
			<li>the message remains invisible to other consumers during this time.
		</p>
		<p>
			<li>The consumer has a specific processing time to complete the necessary operations.
		</p>
		<p>
			<li>If the message is successfully processed and deleted, it is removed from the system.
		</p>
		<p>
		<li>If the consumer fails to process and delete the message within the processing time or encounters an error during processing, 2 possible scenarios occur:
			<ol>
			<br>
				<li>Visible to other consumers:  
					<p>
						If the consumer fails to delete the message within processing time, the message becomes visible to other consumers again after the visibility timeout expires. This allows another consumer to attempt processing the message.
					</p>
				<li>Move to a dead-letter queue:
					<p>
						If the consumer encounters an error during processing, it should handle the error appropriately (e.g., logging, error reporting) and potentially takes corrective actions such as retrying the process or moving the message to a dead-letter queue for further analysis.
					</p>
			</ol>
	</p>
	</ul>
	
	</i>
</blockquote>

#### Different scenarios related to visibility timeout:

##### 1. Successful processing within the visibility timeout: 
If the consumer retrieves a message, processes it successfully, and deletes it from the queue within the visibility timeout period, the message is considered considered successfully processed and removed from the queue.


##### 2. Processing time exceed the visibility timeout: 
If the consumer needs more time to process the message than the visibility timeout allows, and the consumer fails to delete the message, within the visibility timeout, two possibility arise:
	
***If the consumer can extend the visibility timeout by explicitly updating the message's timeout attribute***, it can do so to continue processing the message.
	
 ***If the consumer cannot extend the visibility timeout or fails to do so***, the message becomes visible to other consumer once the visibility timeout expires. This allows other consumers to have the opportunity to retrieve and process the message.


##### 3. Processing failure or error handling:
If the consumer encounters an error or exception during processing, it should handle the error appropriately.
Depending on the error handling strategy implemented, the consumer may choose to delete the message if it's determined to be unrecoverable, move it to a dead-letter queue for further analysis or processing, or perform retries within the designated visibility timeout period.

In SQS, visibility timeout and processing time are separate concepts.

The visibility timeout controls the period during which a message is hidden from other consumers after retrieval.

Processing time represents the expected duration for a consumer to complete the necessary operations on a message.



### Delivery Delay:



---
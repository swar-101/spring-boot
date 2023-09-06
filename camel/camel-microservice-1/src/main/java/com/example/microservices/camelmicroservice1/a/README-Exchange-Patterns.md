## Exchange pattern 
In Apache Camel, an Exchange Pattern (or simply referred to as "Exchange") is a fundamental that defines the way messages are exchanged between components in a route.
An Exchange is an object that contains a message and its metadata, such as headers and properties.

There are two main exchange patterns in Apache Camel: InOnly and Out only.

### InOnly Exchange:
- The InOnly exchange is a one-way exchange, where a message is sent from the sender to the receiver, but no response is expected.
- The InOnly exchange is often used for fire-and-forget scenarios, where the sender does not care about the result of the operation.

*for e.g. a weather service could publish updates to a message broker and subscribers could subscribe to recieve updates.*

### InOut Exchange:
- The InOut is a two-way exchange, where the message is sent from the sender to the receiver where a response is expected.
- The InOut exchange is often used for request-reply scenarios, where the sender expects a response from the server.

*for e.g. a web client could send a request to a web service and receive a response in return.*

In Apache Camel, the exchange pattern is specified by the end-point that is being used. 


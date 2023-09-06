# A basic Amazon SQS queue listener microservice
___
### *In order to consume messages via an Amazon SQS queue, this application has to poll the service to see if there are any messages available.*

## A Basic SQS Listener Microservice Tutorial

We are going to create a microservice that will listen to the messages from an SQS queue as shown in the diagram below:

![[sqs-listener-microservice-tutorial-intro.png]]

Since, this is a demonstration to understand how we can a build a basic SQS listener microservice, we are only going to configure the components with the bare essentials!

Pre-requisites:
1. An IAM user with full access to the SQS resources.
2. A Spring Boot app that is going to listen to the queue that is produced by Amazon SQS. 
3. ...


Imagine, we have a user management service that requires users to register via an email confirmation. Once the user is registered, a message is pushed to an SQS queue where our microservice will listen to it. The message is received by the microservice so that it can be conveyed to other dependent service. 

For simplicity, we will create a custom message via Amazon SQS using an IAM user that has access to all the SQS resource (make this a hyperlink).

Also, we need the microservice that listens to this message queue, therefore, we create a microservice with the following dependencies:
1. Spring Cloud AWS messaging.
2. 



---

## Create an SQS Queue

Sign-in with an IAM user with `FullAdminAccess`, search in Management Console for 'Simple Queue Service'. 

![create-queue.png](sqs-listener/src/images/create-queue.png)

1. Select `Create queue`.

![create-queue-details.png](sqs-listener/src/images/create-queue-details.png)

2. Let the queue type be **Standard**, since we only want to test if the message is being received by our microservice application. 




1. Enter the Name as `user.info`, this will be the name of our queue.


---
`com.example.config.sqsConfig`

```java
        @Configuration
        public class SqsConfig {
        
            @Bean
            @Primary
            public AmazonSQSAsync amazonSQSAsync() {
                return AmazonSQSAsyncClientBuilder.standard().withCredentials(
                        new DefaultAWSCredentialsProviderChain()).build();
            }
        }
```
### `@Bean`

### `@Primary`

### `amazonSQSAsync()` 
`amazonSQSAsync()` annotated with `@Bean` and `@Primary` is a Spring Bean configuration 
method that creates and configures an instance of the `AmazonAsync` client.

The `AmazonAsyncClientBuilder()` is used to build client with the desired configuration.
In this case, it sets the AWS credentials by using the `DefaultAWSCredentialsProviderChain`
to obtain them.

The `AmazonSQSAsync` client allows asynchronous interaction with the Amazon SQS service.

---
`DefaultAWSCredentialsProviderChain` is used to provide AWS credentials for authentication 
and authorization when making requests to AWS services.

In the above code, the `DefaultAWSCredentialsProviderChain` class is used to obtain AWS
credentials for connecting to AWS Simple Queue Service(SQS).

`DefaultAWSCredentialsProviderChain` class automatically searches for AWS credentials in a
specific order from various sources, such as,
        1. System environment variables
        2. Java system properties 
        3. AWS configuration files
in this particular order.

The order in which credential providers are searched depends on the implementation of 
the `DefaultAWSCredentialsProviderChain`, but typically it follows a common pattern:

**1. Environment Variable Provider:** The chain may start checking if AWS credentials are 
                        available as environment variables such as `SECRET_KEY` and 
                        `AWS_SECRET_ACCESS_KEY`. If valid credentials are found they
                        are returned.
**2. Java System Property Provider:** Next chain may look for AWS credentials in Java
                        system properties, such as `aws.accessKeyId` and `aws.secretKey`.
                        If valid credentials are found they are returned.
**3. Profile provider:** The chain may proceed to check if AWS credentials are configured in
                        the AWS configuration files, specifically the default profile or 
                        a named profile. The configuration files commonly used are 
                        `~/.aws/credentials` (for Linux or macOS) and 
                        `%USERPROFILE%\.aws\credentials` (for Windows). If valid 
                        credentials are found in configuration files, they are returned.
**4. Instance Metadata Provider(EC2 Instance Profiles):** Lastly, if the code is running on 
                        an Amazon EC2 instance, the chain may attempt to retrieve 
                        credentials from the instance metadata service associated with
                        the EC2 instance. This provider allows EC2 instances to 
                        automatically retrieve credentials without explicitly providing 
                        them. If valid credentials are found, they are returned.

By using chain of credential providers, the `DefaultAWSCredentialsProviderChain` offers
flexibility and convenience for obtaining AWS credentials.
These options provide developers the ease to seamlessly authenticate with AWS service.

In production, AWS credentials are managed by **AWS Secrets Manager**.
Depending on how the credentials would be manged for a service in production, 
`DefualtAWSCredentialsProviderChain` can be used.

According to the requirements, the credential management strategies can incorporate 
`DefaultAWSCredentialsProviderChain` as the first provider in the chain or as a fallback
option after other providers.

This allows your application to obtain the credentials from Secret Manager along with 
other sources in the chain.
Making the app flexible and adaptable to different credential management strategies.





---
`com.example.sqslistener.UserRegisterdListener.Java`

```java
    @Log4j2
    @Component
    public class UserRegistered() {
    
    @SqsListener(value={$queue.name}, deletionPolicy = SqSMessageDeletionPolicy.ON_SUCCESS)
    public void receiveUserInfo(String message) {
        if(!message=isEmpty()) {
            log.info("Received message", message);
        }
    }
}
```

### `Log4j2`

`@Log4j2` is a logging annotation used with the Log4j 2 library. It enables logging capabilities 
for the class, allowing log statements to be written to the log.

### `@Component`

`@Component` is a Spring annotation that marks the class as a Spring-managed component, 
indicating that it should be instantiated and managed by the Spring framework.


### `@SqsListener`

The `@SqsListener` annotation is used to configure the method as an SQS message listener. It has
two important attributes:
1. `value=${queue.name}`: This attribute specifies the name of the SQS queue from which messages
    should be received. The actual queue is expected to be provided as a Spring placeholder 
    `queue.name`.
2. `deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS`: This attribute defines deletion policy 
    for the received message. In this case, the message will be deleted from the SQS queue if the 
    `receiveUserInfo()` method executes successfully (i.e. it doesn't throw an exception).





---

## Create a listener microservice


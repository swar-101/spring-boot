package com.example.microservices.camelmicroservice1.a;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

// ⚠ very important to annotate this as a @Component, otherwise it will not be
// automatically registered as a bean, ergo won't be eligible for dependency
// injection therefore it will show 0 routes have started up when you run the app!

// @Component annotation is used to mark a class as a candidate for component-scanning
// and auto-detection of beans.
//@Component
public class MyFirstTimerRouter extends RouteBuilder {

    /* What is `RouteBuilder`?
    *
    *
    * */

    // Step 1.4.1: We are auto-wiring the class component as a best practice so that
    // - it provides better readability
    // - it is easier to test
    // - code is easier to change
    @Autowired
    private GetCurrentTimeBean getCurrentTimeBean;

    @Autowired
    private SimpleLoggingProcessingComponent loggingComponent;

    @Override
    public void configure() throws Exception {

        /* 📃
        * In order to route a message we will make use of the following:
        *
        *   - queue         >> but instead of queue we'll use 'timer' to keep things simple
        *   - transformation
        *   - database      >> but instead of storing in the db we're going use log
        */



        //  Step 1.1: Simple 'from-to' route.
        from("timer:first-timer")
        .to("log:first-timer");
        /*
        * The expected output message should be...
        * Exchange [ExchangePattern: InOnly, BodyType: null, Body : [Body is null]]
        *
        * In the above example, the String in the `from()` parameter is called `RouteDefinition`
        * which is the endpoint.
        *  */



        //Step 1.2: Route with a constant message using `transform()`.
        from("timer:first-timer-edit")
        .transform().constant("My Constant Message")
                .to("log:first-timer"); //database
        /*
        * In the above example, the output message is a constant.
        * */



        //Step 1.3: Route with a constant message which sends current local time and date.
        from("timer:first-time-edit2")
        .transform().constant("Time now is:" + LocalDateTime.now())
        .to("log:first-timer");
        /*
        * In the above example, the message output gives the same time because the message was
        * sent using a `constant()`.
        * */



        //Step 1.4.0: Using Spring bean
        from("timer:first-time-edit3")
        .bean("getCurrentTimeBean")
        .to("log:first-timer");
        /*
        * In the above example, `bean()` should have the name of the specific bean.
        * Which is `getCurrentTimeBean` in our case. Spring will scan for components
        * that may contain the particular bean, after finding the specific component,
        * it will create the `getCurrentTimeBean`.
        * Since, the `bean()` method is parameterized with the bean name directly, it
        * is considered as a bad practice. This is because, if in the future the name
        * of this component is changed it would start failing.
        * So we will auto-wire the `GetCurrentTimeBean` component class, so that the
        * above dependency is not hardcoded in the code.
        * So that changes can be made in one place rather than editing multiple parts
        * of the code.
        * [Go to 1.4.1 Line no. 27] to read why using @Autowired is the best practice!
        * */



        //Step 1.5: Using Spring beans (Best practice)
        from("timer:first-timer-edit4")
        .bean(getCurrentTimeBean)
        .to("log:first-timer");
        /*
        * In the above example, we substituted an auto-wired instance of the component class `GetCurrentTimeBean`
        * instead of a hardcoded bean name, this is considered a best practice. 😄
        * */



        //Step 1.6: Understanding processing and transformation with a simple example.
        from("time:first-timer-edit5")
        .log("${body}")
        .transform().constant("This is a constant message.")
        .log("${body}")
        .bean(getCurrentTimeBean)
        .log("${body}")
        .bean(loggingComponent)
        .log("${body}")
        .bean(new SimpleLoggingProcessor())
        .to("log:first-timer");
        /*
        * In the above example, `log()` method is the processing operations that are performed
        *
        * */
    }
}
@Component
class GetCurrentTimeBean {
    public String getCurrentTime() {
        return "Time now is" + LocalDateTime.now();
    }
    /*
     * Ideally, the above component should've been on the separate package of its own.
     * as the package's own public class.
     * */
}

@Component
class SimpleLoggingProcessingComponent {
    private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessingComponent.class);

    public void process(String message) {
        logger.info("SimpleLoggingProcessingComponent {}", message);
    }
}

/*
* There are 2 types of operation that you can do on a specific route
* 1. Processing
* 2. Transformation
*
* - Processing involves operations that does not specifically make a change on the body
*   of the message after receiving it.
* - Transformation involves operations that makes changes to the body of the message.
*
* This is how processing and transformation can be distinguished from each other.
* */


/* W H Y   P R O C E S S O R ?
 * Why do we need to implement `Processor` interface?
 * In Apache Camel we need to implement `Processor` interface to define custom processing logic
 * for messages in a route. This can be useful when you need to perform specific actions on a
 * message before it is sent to its final destination.
 *
 * By implementing the `Processor` interface, you can:
 * 1. Modify the message content - you can use `Processor` to add, remove or update information
 *       in the message before it is sent to the final destination.
 * 2. Add custom headers - You can add custom headers to the message to store additional message
 *       that can be used later in the route.
 * 3. Apply transformation - You can use the `Processor` to apply transformations to the message,
 *       such as converting the message to a different format or encrypting/decrypting sensitive
 *       information.
 * 4. Trigger side effects - You can use the `Processor` to trigger side effects, such as logging
 *       information, updating a database, or sending notifications.
 *
 * By implementing custom processing logic using the `Processor`, you can make your routes more
 * flexible and easily adapt to changing requirements.
 * */

class SimpleLoggingProcessor implements Processor {
    private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
//        logger.info("SimpleLoggingProcessor {}", exchange);
        /*
        * if you don't want to print `exchange` we can print the message body*/

        logger.info("SimpleLoggingProcessor {}", exchange.getMessage().getBody());

    }
}
package com.example.microservices.camelmicroservice1.routes.b;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyFileRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:files/input")
        .log("${body}")
        .to("file:files/output");
    }
    /*
    *
    * This code sets up basic Apache Camel route to read the
    * contents of a file  from the "files/input" and write it
    * to a file in the "files/output" directory. Here's a breakdown
    * of how the code works:
    *
    * 1. The `configure()` method is called by Camel during route,
    *    and is used to define the route and its behaviour.
    *
    * 2. The `from()` method specifies the starting point for the
    *    route, which in this case is the "files/input" directory.
    *
    * 3. The `log()` method is a process that logs the body of the
    *    message received by the route to the console. In this case,
    *    it logs the content of the file being read.
    *
    * 4. Finally, the `to()` method specifies the endpoint where the
    *    route will write this file. In this case it write to
    *    "files/output" directory.
    * */
}

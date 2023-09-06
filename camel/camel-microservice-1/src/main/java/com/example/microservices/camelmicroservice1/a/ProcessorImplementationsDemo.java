package com.example.microservices.camelmicroservice1.a;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.dbcp.BasicDataSource;
import org.json.JSONObject;
import org.json.XML;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProcessorImplementationsDemo {

    /*
    * 1. Modify the message content:
    * */
    public class MessageModifier implements Processor {
        public void process(Exchange exchange) {
            String message = exchange.getIn().getBody(String.class);
            message = message.toUpperCase();
            exchange.getIn().setBody(message);
        }
    }
    /*
    * In this example, the `MessageModifier` `Processor` converts the
    * message body to uppercase before it is sent to its final destination
    * */


    /*
    * 2. Add custom headers:
    * */
    public class HeaderAdder implements Processor {
        public void process(Exchange exchange) {
            exchange.getIn().setHeader("CUSTOM_HEADER", "VALUE");
        }
    }
    /*
    * In this example `HeaderAdder``Processor` creates the `CUSTOM_HEADER`
    * with the value `VALUE` to the message before it is sent to its final
    * destination */


    /*
    * 3. Apply transformations:
    * */
    public class XMLToJSON implements Processor {
        public void process(Exchange exchange) {
            String xml = exchange.getIn().getBody(String.class);
            JSONObject json = XML.toJSONObject(xml);
            exchange.getIn().setBody(json.toString());
        }
    }
    /*
    * In this example `XMLToJSON` `Processor` converts an XML message to a
    * JSON message before it is sent to the final destination
    * */


    /*
    * 4. Trigger side effects:
    * */
    public class Logger implements Processor {
        public void process(Exchange exchange) {
            String message = exchange.getIn().getBody(String.class);
            System.out.println("Received message:" + message);
        }
    }
    /* S I D E    E F F E C T S*/
    /*
    * In this example, the `Logger``Processor` logs the message to the console
    * before it is sent to the final destination. This is an example of side
    * effect as the logging of the message is not directly related to the
    * processing of the message, but is still important for the overall
    * functioning of the system.
    * Additionally, here are some examples of side effects in Camel:
    *
    * 1. Logging - You can log information about the message, such as its
    *       contents or headers, to help diagnose issues or track the
    *       message flow. Here is a shortened example..
    * */
    public class LoggingProcessor implements Processor {
        public void process(Exchange exchange) {
            System.out.println("Received message:" + exchange.getIn().getBody());
        }
    }


    /*
    * 2. Updating a database - You can use the `Processor` to update information
    *       in a database such as adding a new record or updating an existing one.
    *       Here is an example..
    *  */
    public class DatabaseProcessor implements Processor {
        public void process(Exchange exchange) throws SQLException {
            BasicDataSource basicDataSource = new BasicDataSource();
            basicDataSource.setDriverClassName("org.postgresql.Driver");
            basicDataSource.setUrl("jdbc:postgresql://localhost:5432/mydatabase");
            basicDataSource.setUsername("postgres");// configure db
            basicDataSource.setPassword("root");// configure db

            Connection connection = basicDataSource.getConnection();

            PreparedStatement statement = connection
                    .prepareStatement("UPDATE users SET name = ? WHERE id =?");
            /*
            * The `prepareStatement()` method takes SQL query as `String` argument and returns a
            * `PreparedStatement` object.
            * We can then use this `PreparedStatement` object to set values for any
            * placeholders in the SQL query, and then execute the query by calling its
            * `executeQuery` or `executeUpdate` method, depending on the type of query being
            * executed.
            *  */
            statement.setString(1, exchange.getIn().getHeader("name", String.class));
            /*
             * The `setString(int parameterIndex, String x)` method is used to set value for a placeholder in a
             * `java.sql.PreparedStatement`.
             * int parameterIndex - The first argument is the index of the placeholder in the SQL query,
             * starting from 1.
             * String x - The second argument is the value to be set for the placeholder. In this case,
             * the value is retrieved from the header of the camel `Exchange` object using the
             * `exchange.getIn().getHeader("name", String.class)` expression.
             * This header is expected to contain the value of the `name` header, and is expected to be of
             * type `String`.
             * */
            statement.setInt(2, exchange.getIn().getHeader("id", Integer.class));
            /*
            * What is placeholder?
            * In this context, a placeholder is a placeholder for a value that will be set in the SQL query
            * before execution.
            * Placeholders are represented by a question mark (?) symbol in the SQL query.
            * The actual values for the placeholders are set using the `set` methods of the
            * `PrepareStatement`, such as `setString` or `setInt`, `setLong`, etc.
            * The use of placeholder allows the same SQL query to be executed multiple times with different
            * values, without having to recompile the query each time.
            * This can result in improved performance, as well as protection against SQL attacks, since the
            * values are passed to the database as separate parameters and are not embedded directly in the
            * SQL query.
            * */

            statement.executeUpdate();

            connection.close();
        }
    }

    public class EmailProcessor implements Processor {
        public void process(Exchange exchange) {
            exchange.getIn().setBody("Dear customer your order has been processed.");
            exchange.getIn().setHeader("to", "customer@example.com");
            exchange.getIn().setHeader("subject", "Order Processed");
        }
    }
}

## _This README file features all the annotations mentioned in the artifacts with simple and concise explanation_

### 1. @Autowired:

`@Autowired` annotation is used to automatically wire (or inject) a dependency into a Spring-managed bean.

```java
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
}
```

In the above example, there is a certain `UserService` class that requires an instance of `UserRepository` to find the list of all users.

Instead, of creating an instance of `UserRepository` within `UserService`.
`@Autowired` is used to inject an instance that is managed by Spring.

This `@Autowired` instance is created and manged by Spring.
Spring creates a single instance of the object and injects it into any beans that requires it.
This means that you don't have to create and mange the object yourself, Spring takes care of creating and managing the object yourself.

Overall, the main difference between an `@Autowired` object and a normal instance is that an `@Autowired` object is managed by Spring and its lifecycle is controlled by Spring's IOC (Inversion Of Control) Container.

This can make it easier to manage dependencies and can help reduce the amount of boilerplate code required to configure and manage objects in your application.

#### Auto-wiring the constructor (best practice)

Auto-wiring the constructor is considered a best practice because it promotes better code -
	_readability, maintainability, and testability_

Instead of auto-wiring an instance variable it is recommended to auto-wire the constructor of a class because, it is not immediately clear what dependencies a class require to function properly, this can be difficult for other developers to understand and modify the code, especially as the class grows more complex and requires more dependencies.

_Readability_
Auto-wiring a constructor makes it clear which dependencies a class requires, and it reduces the amount of boilerplate code needed to instantiate those dependencies. This makes the code easier to read and understand. 

_Maintainability_
Auto-wiring the constructor makes it easier to add or remove dependencies from a class, and reduces the risk of introducing bugs when making changes. This is because the constructor parameters serve as a contract that specifies the dependencies required by the class.

_Testability_
When you auto-wire a constructor, you can easily mock the dependencies of the class under test by passing the mock objects as constructor arguments. This makes it easier to write unit tests because you can isolate the class from being tested from its dependencies. Because, the goal of the unit test is to test the behavior of the class being tested, not the behavior of its dependencies.
This allows you to test the behavior of the class, without worrying about the behavior of its  dependencies.

### 2. @RestController

Rest controller is a Spring MVC annotation that combines the `@Controller` and `@ResoponseBody` annotations to simply the creation of RESTful web services. 

The `@Controller` annotation is used to mark a class as a controller. 
The `@ResponseBody` is used to indicate that the return value of a method should be serialized directly to the HTTP response body in a format such as JSON or XML.

When a class is annotated with `@RestController`, Spring MVC automatically detects it during component scanning and maps HTTP requests to methods in the class based on the value of the `@RequestMapping` annotation.

`@RequestMapping` is used to specify the URI path that will trigger the request, as well as the request method that will be handled. 

For example, the following code defines a `@RestController` class that handles HTTP GET requests to the `/hello` endpoint:

```java
	@RestController
	@RequestMapping
	public class HelloWorldController {

		@GetMapping
		public String sayHello() {
			return "Hello World";
		}
	}
```


In this example, the `@GetMapping` annotation is used to indicate that the `sayHello()` method should be invoked when an HTTP GET request is made to the `/hello` endpoint.

The method returns a string that will be serialized to the response body.

When a controller class is annotated with @RestController, Spring automatically detects and configures it as a RESTful web service. It eliminates the need for developers to use `@ResponseBody` to indicate that a method should return a response body, as @RestController implicitly does this.

#### Using @RestController (best practices):

1. Use appropriate HTTP methods: 
	The HTTP methods such as GET, PUT, POST, DELETE, etc., should be used correctly to map the endpoints to appropriate methods.
2. Use appropriate HTTP codes:
	The status codes should be correctly in response to the requests sent to the endpoints.
3. Keep it simple:
	The method should be designed to perform a single, specific function, and the endpoint should be kept as simple as possible.
4. Use clear and concise naming conventions:
	The method names and endpoints should be named clearly and concisely, so that it is easy for the client to understand what they do.
5. Implement error handling:
	Proper error handling should be implemented to handle errors and exceptions, and to provide informative error messages to the client.
6. Use proper data serialization:
	The data be serialized properly to match the data format requested by the client, such as JSON or XML.
7. Use proper security mechanisms:
	The security mechanisms should be implemented to ensure that the RESTful web service is secure and can be used by authorized clients only.
8. Use proper testing methodologies:
	Proper testing methodologies should be used to test the RESTful web service, including functional testing, integration testing, and unit testing.	

By following these practices, developers can create robust, reliable, and secure RESTful service using the @RestController annotation in Spring MVC.


### 3. @RequestBody

`@RequestBody` is a Spring Boot annotation that is used to indicate that a method parameter should be bound to the body of the HTTP request.

When a client sends an HTTP request to the server, the request body contains the data that the client wants to send to the server. 

The `@RequestBody` annotation tells Spring Boot to map the request body data to map the request body data to the annotated parameter.

Here are some key points to keep in mind when using the `@RequestBody` annotation:
 - It can only be used with the methods that have a `@RequestMapping`, `@PostMapping`,  `@PutMapping` or `@PatchMapping` annotation.
 - It can only be used with HTTP requests that have a request body (such as `PUT`, `POST` or `PATCH` requests).
 - By default, Spring Boot expects the request body to be in JSON format, but this can be used to support other formats.
 - The annotated parameter can be any Java object, and Spring Boot will use a message converter to convert the request body data into an instance of the parameter type.

#### Using @RequestBody (best practices)

1. Use `@RequestBody` only when necessary: 
	The `@RequestBody` annotation should only be used when your method requires access to the request body data. If you're not using the request body data, there is no need to add unnecessary complexity to your code. This also helps to keep your code more maintainable and easier to understand.

2. Keep your request bodies small and focused:
	Sending a lot of data in the request body can cause performance issues and increase the risk of security vulnerabilities. It is best to send only the data that is necessary for the request.
	Additionally, keeping your request bodies small and focused makes your API more user-friendly, as clients don't have to say unnecessary data.

3. Validate the request body of the data:
	Validating the request body data is important to prevent security vulnerabilities and ensure data integrity. Malicious users may try to send invalid or harmful data in the request body, which could cause unexpected behavior or data corruption. By validating the request body data, you can ensure that it meets the expected format and constraints.

5. Use the appropriate HTTP verb for the action you're performing:
	Using the appropriate HTTP verb for the action you're performing is important for ensuring that your API is RESTful and follows best practices. For example, use `POST` for creating a new resource, use `PUT` for updating an existing resource, and `DELETE` for deleting a resource. Using the correct verb also helps to prevent confusion and unexpected behavior for clients using your API.

In summary, following these best practices can make your code more maintainable, secure, and user-friendly. By using the `@RequestBody` annotation only when necessary, keeping your resource bodies small and focused, validating the request body data, and using an appropriate HTTP verb for the action you're performing, you can create a more effective and efficient API.


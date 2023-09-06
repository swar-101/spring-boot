package main.java.com.springhow.examples.springboot.rediscache;


import main.java.com.springhow.examples.springboot.rediscache.entities.Item;
import main.java.com.springhow.examples.springboot.rediscache.service.ItemService;

import main.java.com.springhow.examples.springboot.rediscache.entities.ItemRepository;

import main.java.com.springhow.examples.springboot.rediscache.entities.User;
import main.java.com.springhow.examples.springboot.rediscache.entities.UserRepository;

import main.java.com.springhow.examples.springboot.rediscache.entities.Invoice;
import main.java.com.springhow.examples.springboot.rediscache.entities.InvoiceRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import javax.servlet.annotation.WebServlet;
import java.math.BigDecimal;

@EnableCaching
@SpringBootApplication
public class SpringBootRedisCacheExampleApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(SpringBootRedisCacheExampleApplication.class);

	@Autowired
	private ItemService itemService;

	private final ItemRepository itemRepository;

	private final UserRepository userRepository;
	
	private final InvoiceRepository invoiceRepository;

	@Autowired
	public SpringBootRedisCacheExampleApplication(ItemRepository itemRepository, UserRepository userRepository, InvoiceRepository invoiceRepository) {
		this.itemRepository = itemRepository;
		this.userRepository = userRepository;
		this.invoiceRepository = invoiceRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRedisCacheExampleApplication.class, args);
	}

//    @Override
//    public void run(String... args) throws Exception {
//        logger.info("Loading test data.");
//        itemService.createItem(new Item("Shirt Small", BigDecimal.valueOf(28.99)));
//        itemService.createItem(new Item("Pants Large", BigDecimal.valueOf(21.99)));
//    }

	@Override
	public void run(String... strings) {
		// Populating embedded database here
		logger.info("Saving items. Current item count is {}.", itemRepository.count());
		Item shubham = new Item("ShubhamItem", BigDecimal.valueOf(28.99));
		Item pankaj = new Item("PankajItem", BigDecimal.valueOf(56.19));
		Item lewis = new Item("LewisItem", BigDecimal.valueOf(89.78));

		itemRepository.save(shubham);
		itemRepository.save(pankaj);
		itemRepository.save(lewis);
		logger.info("Done saving items. Data: {}.", itemRepository.findAll());

		// Populating embedded database here
		logger.info("Saving users. Current user count is {}.", userRepository.count());
		User shubhamUser = new User("ShubhamUser", 2000);
		User pankajUser = new User("PankajUser", 29000);
		User lewisUser = new User("LewisUser", 550);

		userRepository.save(shubhamUser);
		userRepository.save(pankajUser);
		userRepository.save(lewisUser);
		logger.info("Done saving users. Data: {}.", userRepository.findAll());
		

		
		logger.info("Saving invoice. Current invoice count is {}.", invoiceRepository.count());
		Invoice invoiceOne = new Invoice(1,"cakeinvoice", new Double(35.0));
		Invoice invoiceTwo = new Invoice(2,"colddrinkinvoice", new Double(85.0));
		
		
		invoiceRepository.save(invoiceOne);
		invoiceRepository.save(invoiceTwo);
		logger.info("Done saving invoice. Data: {}.", invoiceRepository.findAll());

		
	}
	
//	@Bean
//	public ServletRegistrationBean h2servletRegistration() {
//	    ServletRegistrationBean registration = new ServletRegistrationBean();
//	    registration.addUrlMappings("/console/*");
//	    return registration;
//	}
}

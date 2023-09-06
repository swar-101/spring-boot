package test;

import org.springframework.beans.factory.annotation.Autowired;

import com.springhow.examples.springboot.rediscache.entities.User;
import com.springhow.examples.springboot.rediscache.entities.UserRepository;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = SpringBootRedisCacheExampleApplication.class)
public class SpringBootJPATest {
	
	private SpringBootRedisCacheExampleApplication application;

	
	@BeforeEach
	void setUp() {
		application = new SpringBootRedisCacheExampleApplication();
	}

	@Test
	void testInit() {
		assertNotNull(application);
	}

    
    /*@Autowired
    private final UserRepository userRepository;


    @Test
    public void givenGenericEntityRepository_whenSaveAndRetreiveEntity_thenOK() {
//       User genericEntity = 
//          genericEntityRepository.save(new GenericEntity("test"));
//        GenericEntity foundedEntity = 
//          genericEntityRepository.findOne(genericEntity.getId());
//      
    	
        User genericEntity = new User("ManjuUser", 2356);
		
		userRepository.save(genericEntity);

		User foundedEntity = userRepository.findOne(genericEntity.getId()).orElseThrow(RuntimeException::new);
	    
        assertNotNull(foundedEntity);
        assertEquals(genericEntity.getValue(), foundedEntity.getValue());
    }*/
}
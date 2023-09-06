package main.java.com.springhow.examples.springboot.rediscache.controller;

import main.java.com.springhow.examples.springboot.rediscache.entities.Item;
import main.java.com.springhow.examples.springboot.rediscache.service.ItemService;
import org.springframework.web.bind.annotation.*;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import main.java.com.springhow.examples.springboot.rediscache.entities.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping(path = "/items/")
public class ItemController {
	
	private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
	
	private final ItemRepository itemRepository;

	  @Autowired
	  public ItemController(ItemRepository itemRepository) {
	    this.itemRepository = itemRepository;
	  }

    /*private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }*/
    
    @Cacheable(value = "items", key = "#id", unless = "#result.followers < 12000")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Item getItem(@PathVariable Integer id) {
    	logger.info("Getting item with ID {}.", id);
      //return itemRepository.findOne(Long.valueOf(itemId));
      Item item = itemRepository.findById(id).orElseThrow(RuntimeException::new);
      logger.info("Loading data from DB {}", item);
      return item;
    }

    /*@Cacheable(value = "items", key = "#id", unless = "#result.followers < 12000")
    @GetMapping("/{id}")
    public Item getItem(@PathVariable Integer id) {
        return itemService.getItem(id);
    }*/

    /*@PostMapping("/")
    public Item createItem(@RequestBody Item request) {
        return itemService.createItem(request);
    }
    
    @CachePut(value = "items", key = "#item.id")
    @PutMapping("/{id}")
    public Item createItem(@PathVariable Integer id, @RequestBody Item item) {
        return itemService.updateItem(id, item);
    }*/
}

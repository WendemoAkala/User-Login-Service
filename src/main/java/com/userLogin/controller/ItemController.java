package com.userLogin.controller;

import com.userLogin.model.Item;
import com.userLogin.model.ItemRequest;
import com.userLogin.repository.ItemRepository;
import com.userLogin.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/public/items")
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemService itemService;


    @GetMapping("/all")
    public List<Item> getAllItems() {
        // Return a list of all available items
        return itemRepository.findAll();
    }
    @PostMapping("/create")
    public ResponseEntity<?> createItem(@RequestBody ItemRequest item) {
        try{
        itemService.createItem(item);
        return null;
    } catch (Exception exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
    }
    @GetMapping("/search")
    public ResponseEntity<List<Item>> searchItemsByName(@RequestParam String title) {
        List<Item> searchResults = itemService.searchItemsByName(title);
        if (searchResults.isEmpty()) {
            return ResponseEntity.noContent().build(); // No items found
        } else {
            return ResponseEntity.ok(searchResults);
        }
    }

}


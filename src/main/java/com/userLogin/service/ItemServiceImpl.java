package com.userLogin.service;

import com.userLogin.model.Item;
import com.userLogin.model.ItemRequest;
import com.userLogin.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    private ItemRepository itemRepository;
    @Override
    public void createItem(ItemRequest itemRequest) {

        itemRepository.createItem(itemRequest.toItem());
    }
    @Override
    public Item findItemByTitle(String title) {
        return itemRepository.findItemByTitle(title);
    }

    @Override
    public List<Item> searchItemsByName(String title) {
        itemRepository.searchItemsByName(title);
        return null;
    }

}

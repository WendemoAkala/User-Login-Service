package com.userLogin.service;

import com.userLogin.model.Item;
import com.userLogin.model.ItemRequest;

import java.util.List;

public interface ItemService {
    void createItem(ItemRequest item) ;

    Item findItemByTitle(String title);

    List<Item> searchItemsByName(String title);
}

package com.userLogin.repository;

import com.userLogin.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {

    void createItem(Item item);

    Item findItemByTitle(String title);
    List<Item> findByTitleContaining(String title);

    Item save(Item item);

    List<Item> searchItemsByName(String title);

    List<Item> findAll();

    Optional<Object> findById(Long itemId);
}

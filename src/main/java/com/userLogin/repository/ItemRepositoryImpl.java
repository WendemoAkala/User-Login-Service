package com.userLogin.repository;

import com.userLogin.model.Item;
import com.userLogin.repository.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Repository
public class ItemRepositoryImpl implements ItemRepository{
    private static final String ITEM_TABLE_NAME = "item";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void createItem(Item item) {
        String sql = "INSERT INTO " + ITEM_TABLE_NAME + " (title,photoUrl, price, stockCount) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, item.getTitle(), item.getPhotoUrl(), item.getPrice(),
                item.getStockCount());
            }

    @Override
    public Item findItemByTitle(String title) {
        String sql = "SELECT * FROM " + ITEM_TABLE_NAME + " WHERE title=?";
        try {
            return jdbcTemplate.queryForObject(sql, new ItemMapper(), title);
        } catch (EmptyResultDataAccessException error) {
            return null;
        }
    }

    @Override
    public List<Item> findByTitleContaining(String title) {

        String sql = "SELECT * FROM " + ITEM_TABLE_NAME + " WHERE title=?";
        try {
            return Collections.singletonList(jdbcTemplate.queryForObject(sql, new ItemMapper(), title));
        } catch (EmptyResultDataAccessException error) {
            return null;
        }
    }

    @Override
    public Item save(Item item) {
        return null;
    }

    @Override
    public List<Item> searchItemsByName(String title) {
        String sql = "SELECT * FROM " + ITEM_TABLE_NAME + " WHERE title=?";
        try {
            return Collections.singletonList(jdbcTemplate.queryForObject(sql, new ItemMapper(), title));
        } catch (EmptyResultDataAccessException error) {
            return null;
        }
    }

    @Override
    public List<Item> findAll() {
        return null;
    }

    @Override
    public Optional<Object> findById(Long itemId) {
        return Optional.empty();
    }


}

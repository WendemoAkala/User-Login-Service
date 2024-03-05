package com.userLogin.repository;

import com.userLogin.model.CustomUser;
import com.userLogin.model.Favorite;
import com.userLogin.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
@Repository
public class FavoriteRepositoryImpl implements FavoriteRepository{
    private static final String FAVORITE_TABLE_NAME = "favorite";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void createFavorite(Favorite favorite) {
        String sql = "INSERT INTO " + FAVORITE_TABLE_NAME + " (userId, item) VALUES (?, ?)";
        jdbcTemplate.update(sql,favorite.getUserId(),favorite.getItem() );
    }

    @Override
    public List<Favorite> findByCustomUser(CustomUser customUser) {
        return null;
    }

    @Override
    public Collection<Object> findByUserAndItem(CustomUser customUser, Item item) {
        return null;
    }

    @Override
    public void addToFavorites(Favorite favorite) {
        String sql = "INSERT INTO " + FAVORITE_TABLE_NAME + " (userId,item) VALUES (?, ?)";
        jdbcTemplate.update(sql, favorite.getUserId(), favorite.getItem());
    }

    @Override
    public Favorite save(Favorite favorite) {
        return null;
    }

    @Override
    public void delete(Favorite favorite) {

    }


}

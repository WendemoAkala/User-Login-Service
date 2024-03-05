package com.userLogin.repository;


import com.userLogin.model.CustomUser;
import com.userLogin.model.Favorite;
import com.userLogin.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Collection;
import java.util.List;
@EnableJpaRepositories
public interface FavoriteRepository {

    void createFavorite(Favorite favorite);

    List<Favorite> findByCustomUser(CustomUser customUser);

    Collection<Object> findByUserAndItem(CustomUser customUser, Item item);

    void addToFavorites(Favorite favorite);

    Favorite save(Favorite favorite);

    void delete(Favorite favorite);
}

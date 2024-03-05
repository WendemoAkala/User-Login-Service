package com.userLogin.service;

import com.userLogin.model.Favorite;
import com.userLogin.model.FavoriteRequest;
import com.userLogin.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteServiceImpl implements  FavoriteService{
    @Autowired
    private FavoriteRepository favoriteRepository;

    @Override
    public void addToFavorites(FavoriteRequest favoriteRequest) throws Exception {
        Favorite existingFavorite = favoriteRequest.findFavoriteByUserId(favoriteRequest);
        if(existingFavorite != null){
            throw new Exception("Item " + favoriteRequest.getUserId() + " is already taken");
        }
        favoriteRepository.addToFavorites(favoriteRequest.toFavorite());
    }
}

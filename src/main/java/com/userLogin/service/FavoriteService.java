package com.userLogin.service;

import com.userLogin.model.FavoriteRequest;

public interface FavoriteService {
    void addToFavorites(FavoriteRequest favoriteRequest) throws Exception;
}

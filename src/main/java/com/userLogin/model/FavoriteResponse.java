package com.userLogin.model;

import javax.persistence.ManyToOne;

public class FavoriteResponse {
    @ManyToOne
    private CustomUser customUser;

    @ManyToOne
    private Item item;

    public FavoriteResponse (CustomUser customUser, Item item) {
        this.customUser = customUser;
        this.item = item;
    }

    public CustomUser getCustomUser() {
        return customUser;
    }

    public void setCustomUser(CustomUser customUser) {
        this.customUser = customUser;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
    public  Favorite toFavorite()
    {
        return new Favorite(
                null,
                this.customUser,
                this.item
        );
    }

    public Favorite findFavoriteByCustomUser(CustomUser customUser) {
        return null;
    }
}

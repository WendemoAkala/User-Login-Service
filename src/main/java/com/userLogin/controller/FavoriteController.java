package com.userLogin.controller;
import com.userLogin.model.CustomUser;
import com.userLogin.model.Favorite;
import com.userLogin.model.FavoriteRequest;
import com.userLogin.model.Item;
import com.userLogin.repository.FavoriteRepository;
import com.userLogin.repository.ItemRepository;
import com.userLogin.repository.UserRepository;
import com.userLogin.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/public/favorites")
public class FavoriteController {
    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    FavoriteService favoriteService;
    @PostMapping("/add")
    public Favorite addToFavorites(@RequestParam Long id, @RequestParam Long itemId, @RequestBody FavoriteRequest favoriteRequest) throws Exception {
        // Check if the user and item exist
        CustomUser customUser = (CustomUser) userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        Item item = (Item) itemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Item not found"));

        // Check if the item is not already in the user's favorites
        if (favoriteRepository.findByUserAndItem(customUser, item).isEmpty()) {

            Favorite favorite = new Favorite(id, customUser.getId(), (List<Item>) item);
            favorite.setId(id);
            favorite.setUserId(id);
            favorite.setItem((List<Item>) item);
            return favoriteRepository.save(favorite);
        }

        // Handle the case when the item is already in the user's favorites
        // You may return an appropriate response or throw an exception
        favoriteService.addToFavorites(favoriteRequest);
        return null;
    }

    @DeleteMapping("/remove")
    public void removeFromFavorites(@RequestParam Long id, @RequestParam Long itemId) {
        // Check if the user and item exist
        CustomUser customUser = (CustomUser) userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        Item item = (Item) itemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Item not found"));

        // Find and delete the favorite relationship if it exists
//        favoriteRepository.findByUserAndItem(customUser, item).isPresent(favoriteRepository::delete);
        Collection<Object> favorites = favoriteRepository.findByUserAndItem(customUser, item);

        if (!favorites.isEmpty()) {
            for (Object favorite : favorites) {
                favoriteRepository.delete((Favorite) favorite);
            }
        }
    }

    @GetMapping("/user/{userId}")
    public List<List<Item>> getUserFavorites(@PathVariable Long id) {
        // Return a list of items that the user has in their favorites
        CustomUser customUser = (CustomUser) userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        List<Favorite> favorites = favoriteRepository.findByCustomUser(customUser);
        return favorites.stream().map(Favorite::getItem).collect(Collectors.toList());
    }

    // Additional methods if needed
}

package com.example.cartserver;

import com.example.mallserver.mapper.UserMapper;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CartService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final CartMapper cartMapper;

    @Autowired
    public CartService(CartMapper cartMapper) {
        this.cartMapper = cartMapper;
    }

    @Transactional
    public void addToCart(Long userId, CartItem cartItem) {
        try {
            cartMapper.insertCartItem(userId, cartItem);
            log.info("Item added to cart for user {}", userId);
        } catch (Exception e) {
            log.error("Error adding item to cart for user {}: {}", userId, e.getMessage());
            throw e;
        }
    }

    @Transactional
    public void removeFromCart(Long userId, Long productId) {
        try {
            cartMapper.deleteCartItem(userId, productId);
            log.info("Item removed from cart for user {}", userId);
        } catch (Exception e) {
            log.error("Error removing item from cart for user {}: {}", userId, e.getMessage());
            throw e;
        }
    }

    @Transactional
    public void updateCartItem(Long userId, Long productId, Integer quantity) {
        try {
            cartMapper.updateCartItem(userId, productId, quantity);
            log.info("Item updated in cart for user {}: {}", userId, productId);
        } catch (Exception e) {
            log.error("Error updating cart item for user {}: {}", userId, e.getMessage());
            throw e;
        }
    }

    @Cacheable(value = "cartCache", key = "#userId")
    public List<CartItem> getCartItems(Long userId) {
        try {
            return cartMapper.getCartItemsByUserId(userId);
        } catch (Exception e) {
            log.error("Error retrieving cart items for user {}: {}", userId, e.getMessage());
            throw new NoSuchElementException("Cart items could not be retrieved");
        }
    }

    @CacheEvict(value = "cartCache", key = "#userId")
    public void clearCart(Long userId) {
        try {
            cartMapper.clearCartByUserId(userId);
            log.info("Cart cleared for user {}", userId);
        } catch (Exception e) {
            log.error("Error clearing cart for user {}: {}", userId, e.getMessage());
            throw e;
        }
    }
}
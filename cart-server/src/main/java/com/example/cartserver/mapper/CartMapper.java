package com.example.cartserver.mapper;

import com.example.mallserver.model.CartItem;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartMapper {

    @Insert("INSERT INTO cart(user_id, product_id, quantity) VALUES(#{userId}, #{item.productId}, #{item.quantity})")
    void insertCartItem(@Param("userId") Long userId, @Param("item") CartItem cartItem);

    @Delete("DELETE FROM cart WHERE user_id = #{userId} AND product_id = #{productId}")
    void deleteCartItem(@Param("userId") Long userId, @Param("productId") Long productId);

    @Update("UPDATE cart SET quantity = #{quantity} WHERE user_id = #{userId} AND product_id = #{productId}")
    void updateCartItem(@Param("userId") Long userId, @Param("productId") Long productId, @Param("quantity") Integer quantity);

    @Select("SELECT * FROM cart WHERE user_id = #{userId}")
    @Results({
            @Result(property = "productId", column = "product_id"),
            @Result(property = "quantity", column = "quantity")
    })
    List<CartItem> getCartItemsByUserId(@Param("userId") Long userId);

    @Delete("DELETE FROM cart WHERE user_id = #{userId}")
    void clearCartByUserId(@Param("userId") Long userId);
}

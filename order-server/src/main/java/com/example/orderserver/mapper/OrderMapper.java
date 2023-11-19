

@Mapper
public interface OrderMapper {

    @Select("SELECT * FROM orders WHERE id = #{id}")
    Order selectOrderById(Long id);

    @Insert("INSERT INTO orders(customer_id, order_date, status) VALUES(#{customerId}, #{orderDate}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertOrder(Order order);

    @Update("UPDATE orders SET customer_id = #{customerId}, order_date = #{orderDate}, status = #{status} WHERE id = #{id}")
    int updateOrder(Order order);

    @Delete("DELETE FROM orders WHERE id = #{id}")
    int deleteOrderById(Long id);

    // 可以根据需要添加更多的方法，例如按照不同的条件查询订单，批量插入等。
}

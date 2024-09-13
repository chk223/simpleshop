package simpleshop.shop.domain;

import java.util.UUID;

public class OrderItem {
    private UUID id;
    private Order order;
    private Item item;
    private int quantity;
    private Double price;
}

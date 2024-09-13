package simpleshop.shop.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Order {
    private UUID id;
    private User user;
    private List<Item> orderItems;
    private String status;
    private LocalDate orderDate;
    private Double totalAmount;
}

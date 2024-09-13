package simpleshop.shop.domain;

public class Payment {
    private Long id;
    private Order order;
    private Double amount;
    private String method;
    private String status;
}

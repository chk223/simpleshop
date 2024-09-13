package simpleshop.shop.domain;

import java.time.LocalDate;

public class Review {
    private Long id;
    private User user;
    private Item item;
    private String content;
    private int rating;
    private LocalDate createdDate;
}

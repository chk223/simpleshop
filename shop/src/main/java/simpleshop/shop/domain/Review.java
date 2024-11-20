package simpleshop.shop.domain;

import java.time.LocalDate;
import java.util.UUID;

public class Review {
    private UUID id;
    private String userId;
    private String userName;
    private UUID itemId;
    private String content;
    private int like;
    private LocalDate createdDate;

    public Review(String userId,UUID itemId, String userName, String content) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.userName = userName;
        this.itemId = itemId;
        this.content = content;
        this.like = 0;
        this.createdDate = LocalDate.now();
    }
}


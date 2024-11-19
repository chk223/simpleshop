package simpleshop.shop.domain;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Map;
import java.util.UUID;

public class User {
    private UUID id;
    @NotBlank(message = "Id는 공백이 포함될 수 없습니다.")
    @Size(max=20,message = "Id는 20자 이내여야 합니다.")
    private String userId;
    @NotBlank(message = "비밀번호는 공백이 포함될 수 없습니다.")
    @Size(max=20,message = "비밀번호는 20자 이내여야 합니다.")
    private String userPassword;
    @NotBlank(message = "닉네임은 공백이 포함될 수 없습니다.")
    @Size(max=10,message = "닉네임은 10자 이내여야 합니다.")
    private String userName;
    private Cart cart;
    private Grade grade;
    //orderId, order
    private Map<UUID, Order> orders;

    public User(String userId, String userPassword, String userName) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.cart = null;
        this.grade = Grade.BASIC;
        this.orders = null;
    }

    public UUID getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Map<UUID, Order> getOrders() {
        return orders;
    }

    public void setOrders(Map<UUID, Order> orders) {
        this.orders = orders;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}

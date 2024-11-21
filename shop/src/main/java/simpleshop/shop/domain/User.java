package simpleshop.shop.domain;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
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
    private Grade grade;
    //orderId, order
    private List<UUID> orders;

    public User(String userId, String userPassword, String userName) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.grade = Grade.BASIC;
        this.orders = new ArrayList<>();
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

    public List<UUID> getOrders() {
        return orders;
    }

    public void addOrder(UUID orderId) {
        this.orders.add(orderId);
    }

    public void removeOrder(UUID orderId) {
        this.orders.removeIf(order -> order.equals(orderId));
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
}

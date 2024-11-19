package simpleshop.shop.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserForm {
    @NotBlank(message = "Id는 공백이 포함될 수 없습니다.")
    @Size(max=20,message = "Id는 20자 이내여야 합니다.")
    private String userId;
    @NotBlank(message = "비밀번호는 공백이 포함될 수 없습니다.")
    @Size(max=20,message = "비밀번호는 20자 이내여야 합니다.")
    private String userPassword;
    @NotBlank(message = "닉네임은 공백이 포함될 수 없습니다.")
    @Size(max=10,message = "닉네임은 10자 이내여야 합니다.")
    private String userName;

    public UserForm(String userId, String userPassword, String userName) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserForm() {
    }

    public UserForm(String userId, String userPassword) {
        this.userId = userId;
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
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

package simpleshop.shop.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginForm {
    @NotBlank(message = "Id는 공백이 포함될 수 없습니다.")
    @Size(max=20,message = "Id는 20자 이내여야 합니다.")
    private String userId;
    @NotBlank(message = "비밀번호는 공백이 포함될 수 없습니다.")
    @Size(max=20,message = "비밀번호는 20자 이내여야 합니다.")
    private String userPassword;

    public LoginForm() {
    }

    public LoginForm(String userId, String userPassword) {
        this.userId = userId;
        this.userPassword = userPassword;
    }

    public @NotBlank(message = "Id는 공백이 포함될 수 없습니다.") @Size(max = 20, message = "Id는 20자 이내여야 합니다.") String getUserId() {
        return userId;
    }

    public void setUserId(@NotBlank(message = "Id는 공백이 포함될 수 없습니다.") @Size(max = 20, message = "Id는 20자 이내여야 합니다.") String userId) {
        this.userId = userId;
    }

    public @NotBlank(message = "비밀번호는 공백이 포함될 수 없습니다.") @Size(max = 20, message = "비밀번호는 20자 이내여야 합니다.") String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(@NotBlank(message = "비밀번호는 공백이 포함될 수 없습니다.") @Size(max = 20, message = "비밀번호는 20자 이내여야 합니다.") String userPassword) {
        this.userPassword = userPassword;
    }
}

package simpleshop.shop.domain;

public class UserDetail {
    private String userPassword;
    private String userName;
    private Grade grade;

    public UserDetail(String userPassword, String userName, Grade grade) {
        this.userPassword = userPassword;
        this.userName = userName;
        this.grade = grade;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}

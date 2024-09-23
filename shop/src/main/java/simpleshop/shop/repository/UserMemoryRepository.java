package simpleshop.shop.repository;

import org.springframework.stereotype.Repository;
import simpleshop.shop.domain.Grade;
import simpleshop.shop.domain.Order;
import simpleshop.shop.domain.User;
import simpleshop.shop.domain.UserForm;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class UserMemoryRepository implements UserRepository{

    private static final Map<String, User> userStorage = new HashMap<>();//static
    @Override
    public User join(User user) {
        if(userStorage.get(user.getUserId()) == null) {
            userStorage.put(user.getUserId(), user);
            return user;
        }
        else return null;
        /*userStorage.put(user.getUserId(),user);
        return user;*/
    }

    @Override
    public User findUserById(String userId) {
        return userStorage.get(userId);
    }

    @Override
    public User updateUserInfo(String userId, UserForm userForm) {
        User findUser = userStorage.get(userId);
        findUser.setUserPassword(userForm.getUserPassword());
        return findUser;
    }

    @Override
    public void userGrade(String userId, Grade grade) {
        User findUser = userStorage.get(userId);
        findUser.setGrade(grade);
    }

    @Override
    public void deleteUser(String userId) {
        userStorage.remove(userId);
    }
    public void addOrder(User user, Map<UUID, Order> orders){
        user.setOrders(orders);
    }
    public void removeOrder(User user, Map<UUID, Order> orders){
        user.setOrders(orders);
    }
    public void clearStore() {
        userStorage.clear();
    }
}

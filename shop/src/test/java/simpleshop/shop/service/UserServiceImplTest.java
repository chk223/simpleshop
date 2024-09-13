package simpleshop.shop.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import simpleshop.shop.domain.User;
import simpleshop.shop.domain.UserForm;
import simpleshop.shop.repository.UserRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserRepository userRepository;

    @AfterEach
    void afterEach() {
        userRepository.clearStore();
    }
    @Test
    void regist() {
        //given

        //when
        User userA = new User("UserAId", "UserApw", null);
        User userB = new User("UserBId", "UserBpw", null);

        //then
        User rUserA = userRepository.join(userA);
        User rUserB = userRepository.join(userB);
        assertThat(rUserA.getUserId()).isEqualTo(userA.getUserId());
        assertThat(rUserB.getUserId()).isEqualTo(userB.getUserId());
        assertThat(rUserA.getUserPassword()).isEqualTo(userA.getUserPassword());
        assertThat(rUserB.getUserPassword()).isEqualTo(userB.getUserPassword());
    }

    @Test
    void findUser() {
        //given
        User userA = new User("UserAId", "UserApw", null);
        User userB = new User("UserBId", "UserBpw", null);
        User userC = new User("UserCId", "UserCpw", null);
        User rUserA = userRepository.join(userA);
        User rUserB = userRepository.join(userB);
        User rUserC = userRepository.join(userC);
        //when
        User findUserA = userRepository.findUserById(userA.getUserId());
        User findUserB = userRepository.findUserById(userB.getUserId());
        User findUserC = userRepository.findUserById(userC.getUserId());
        //then
        assertThat(rUserA.getUserId()).isEqualTo(findUserA.getUserId());
        assertThat(rUserB.getUserId()).isEqualTo(findUserB.getUserId());
        assertThat(rUserC.getUserId()).isEqualTo(findUserC.getUserId());
        assertThat(rUserA.getUserPassword()).isEqualTo(findUserA.getUserPassword());
        assertThat(rUserB.getUserPassword()).isEqualTo(findUserB.getUserPassword());
        assertThat(rUserC.getUserPassword()).isEqualTo(findUserC.getUserPassword());
    }

    @Test
    void changeUserInfo() {
        //given
        User userA = new User("UserAId", "UserApw", null);
        User userB = new User("UserBId", "UserBpw", null);
        User rUserA = userRepository.join(userA);
        User rUserB = userRepository.join(userB);
        //when
        UserForm userFormA = new UserForm(userA.getUserId(),"ChangedUserApw");
        User changedUserA = userRepository.updateUserInfo(userA.getUserId(),userFormA);
        //then
        String changedUserAPassword = userRepository.findUserById(userA.getUserId()).getUserPassword();
        assertThat(changedUserAPassword).isEqualTo("ChangedUserApw");
        assertThat(changedUserA.getUserPassword()).isEqualTo("ChangedUserApw");
    }

    @Test
    void withdraw() {
        //given
        User userA = new User("UserAId", "UserApw", null);
        User userB = new User("UserBId", "UserBpw", null);
        User rUserA = userRepository.join(userA);
        User rUserB = userRepository.join(userB);
        //when
        userRepository.deleteUser(userA.getUserId());
        //then
        assertThat(userRepository.findUserById(userA.getUserId())).isNull();
    }
}
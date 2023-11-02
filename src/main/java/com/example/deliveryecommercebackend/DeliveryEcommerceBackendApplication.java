package com.example.deliveryecommercebackend;

import com.example.deliveryecommercebackend.model.NewsType;
import com.example.deliveryecommercebackend.model.Role;
import com.example.deliveryecommercebackend.model.User;
import com.example.deliveryecommercebackend.repository.ActionRepository;
import com.example.deliveryecommercebackend.repository.NewsTypeRepository;
import com.example.deliveryecommercebackend.repository.RoleRepository;
import com.example.deliveryecommercebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class DeliveryEcommerceBackendApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DeliveryEcommerceBackendApplication.class, args);
    }

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private NewsTypeRepository newsTypeRepository;

    @Override
    public void run(String... args) throws Exception {
//        Role role = new Role();
//        role.setRoleId(1);
//        role.setName("admin");
//        role.setDes("admin");
//        roleRepository.save(role);
//
//        User user = new User();
//        user.setUser_id("1");
//        user.setFullName("user default");
//        user.setAccount("userdefault");
//        user.setEmail("userdefault@gmail.com");
//        user.setRole(role);
//        user.setPassword("123456");
//        userRepository.save(user);
//
//        NewsType newstype = new NewsType();
//        newstype.setCode("type1");
//        newstype.setName("type 1 nhe");
//        newstype.setDes("type1");
//        newsTypeRepository.save(newstype);
//
//        Role roleAdmin = new Role();
//        roleAdmin.setRoleId(1);
//        roleAdmin.setDes("admin");
//        roleAdmin.setName("admin");
//        roleRepository.save(roleAdmin);
//
//        Role roleStaff = new Role();
//        roleStaff.setRoleId(3);
//        roleStaff.setDes("staff");
//        roleStaff.setName("staff");
//        roleRepository.save(roleStaff);
//
//
//        Role roleCustomer = new Role();
//        roleCustomer.setRoleId(2);
//        roleCustomer.setDes("customer");
//        roleCustomer.setName("customer");
//        roleRepository.save(roleCustomer);
//
//        Role roleShipper = new Role();
//        roleShipper.setRoleId(4);
//        roleShipper.setDes("shipper");
//        roleShipper.setName("shipper");
//        roleRepository.save(roleShipper);
//
//        Action action = new Action();
//        action.setAction_id(0);
//        action.setName("waiting");
//        action.setDes("waiting for accepting");
//        action.setType("admin");
//        action.set_deleted(false);
//        action.setCreated(Date.valueOf(LocalDate.now()));
//
//        actionRepository.save(action);
//
//        Action action1 = new Action();
//        action1.setAction_id(1);
//        action1.setName("accept");
//        action1.setDes("accepted");
//        action1.setType("admin");
//        action1.set_deleted(false);
//        action1.setCreated(Date.valueOf(LocalDate.now()));
//
//        actionRepository.save(action1);
//
//        Action action2 = new Action();
//        action2.setAction_id(2);
//        action2.setName("decline");
//        action2.setDes("decline");
//        action2.setType("admin");
//        action2.set_deleted(false);
//        action2.setCreated(Date.valueOf(LocalDate.now()));
//
//        actionRepository.save(action2);

    }
}

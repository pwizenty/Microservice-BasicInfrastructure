package de.fhdortmund.service.usermanagement.controller;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import de.fhdortmund.service.usermanagement.dao.UserDao;
import de.fhdortmund.service.usermanagement.entities.User;

import java.security.Principal;

/**
 * Created by phil on 29.12.16.
 */
@Controller
public class UserManagementController {
    @Autowired
    private UserDao userDao;

    /**
     * Request mapping for creating a user
     *
     * @param email email adress of the user
     * @param surName lastname of the user
     * @param firstName firstname of the user
     * @param username username, must be unique
     * @param password password of the user
     * @return id of the user
     */
    @RequestMapping("/register")
    @ResponseBody
    public String register(Principal principal, String email, String surName, String firstName, String username,
        String password) {
        System.out.println(principal.getName());

        if (!email.contains("@")) {
            return "Email is not valid!";
        }

        if (userDao.findByEmail(email) != null) {
            return "Email Adress is already in use!";
        } else {
            User user = new User(email, surName, firstName, username, password);
            userDao.save(user);
            return "User registered with Id: " + user.getId();
        }
    }

    /**
     * Request mapping for deleting a user
     *
     * @param id of the useraccount
     * @return status message
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(long id) {
        try {
            User user = new User(id);
            userDao.delete(user);
        } catch (Exception ex) {
            return "Error deleting the user:" + ex.toString();
        }
        return "User succesfully deleted!";
    }

    /**
     * Request mapping for getting the user id by email adress
     *
     * @param email email of the user
     * @return user id
     */
    @RequestMapping("/get-by-email")
    @ResponseBody
    public String getByEmail(String email) {
        String userId = "";
        try {
            User user = userDao.findByEmail(email);
            userId = String.valueOf(user.getId());
        } catch (Exception ex) {
            return "User not found";
        }
        return "The user id is: " + userId;
    }

    /**
     * Request mapping for getting the user by username
     *
     * @param username of the user
     * @return user object
     */
    @RequestMapping("/get-by-username")
    @ResponseBody
    public User getByUsername(String username) {
        User user = new User();
        try {
            user = userDao.findByUsername(username);
        } catch (Exception ex) {
            return null;
        }
        return user;
    }
}

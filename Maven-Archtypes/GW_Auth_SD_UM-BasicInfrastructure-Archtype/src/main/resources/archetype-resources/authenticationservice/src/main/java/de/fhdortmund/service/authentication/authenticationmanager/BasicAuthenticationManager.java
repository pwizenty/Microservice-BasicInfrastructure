package de.fhdortmund.service.authentication.authenticationmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import de.fhdortmund.service.authentication.dao.UserDao;
import de.fhdortmund.service.authentication.entities.User;

import java.util.ArrayList;
import java.util.logging.Logger;


/**
 * Created by phil on 12.01.17.
 * This class handles the userauthentication when the user trys to login.
 */
@Component
public class BasicAuthenticationManager implements AuthenticationManager {
    static Logger logger = Logger.getLogger(BasicAuthenticationManager.class.getName());

    @Autowired
    UserDao userDao;

    /**
     * This methode handles the authentication
     *
     * @param authentication Contains Username and Password.
     * @return Userpasswordtoken Generated token.
     * @throws AuthenticationException this should not happen.
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName().toString();
        String password = authentication.getCredentials().toString();

        User user = userDao.findByUsername( username) ;

        if (user == null) {
            return null;
        }

        if (user.getUsername().equals(username) && user.getPassword().equals(password))  {
            logger.info("User getting logged in.");
            return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
        }
        return null;
    }
}

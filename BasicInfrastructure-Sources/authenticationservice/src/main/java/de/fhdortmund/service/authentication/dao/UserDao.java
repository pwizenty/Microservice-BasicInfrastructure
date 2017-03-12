package de.fhdortmund.service.authentication.dao;

import org.springframework.data.repository.CrudRepository;

import de.fhdortmund.service.authentication.entities.User;


/**
 * Created by phil on 18.01.17.
 */
public interface UserDao extends CrudRepository<User, Long> {
    public User findByEmail( String email );

    public User findByUsername(String username );
}

package de.fhdortmund.service.usermanagement.dao;

import org.springframework.data.repository.CrudRepository;

import de.fhdortmund.service.usermanagement.entities.User;


/**
 * Created by phil on 29.12.16.
 */
public interface UserDao extends CrudRepository<User, Long> {
    public User findByEmail( String email );

    public User findByUsername( String username );
}

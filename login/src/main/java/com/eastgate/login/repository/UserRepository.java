package com.eastgate.login.repository;

import com.eastgate.login.entity.Role;
import com.eastgate.login.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findById (int id);
    List<User> findAll();
    User deleteById(int id);

    @Override
    void deleteAll();
    User findByUsername(String username);
    List<User> findByRoles (Role role);
}

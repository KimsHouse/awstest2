package com.ssafy.BackEnd.repository;

import com.ssafy.BackEnd.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query(value = "select u from User u where email = :email and password = :password")
    User findUser(String email, String password);

    List<User> findByEmail (String Email);

    User findByName (String name);

//    User findByProfileId (Long profile_id);
}

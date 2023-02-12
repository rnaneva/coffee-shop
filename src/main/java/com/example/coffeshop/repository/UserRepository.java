package com.example.coffeshop.repository;

import com.example.coffeshop.model.entities.Order;
import com.example.coffeshop.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameAndPassword(String username, String password);

    @Query("select u from User u order by size(u.orders) desc")
    List<User> findAllByOrderByOrdersDesc();

}

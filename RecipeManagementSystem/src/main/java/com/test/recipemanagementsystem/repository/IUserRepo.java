package com.test.recipemanagementsystem.repository;

import com.test.recipemanagementsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User, Integer> {
    @Query(nativeQuery = true, value = "Select * from user where email = :email")
    User findFirstByEmail(String email);
}

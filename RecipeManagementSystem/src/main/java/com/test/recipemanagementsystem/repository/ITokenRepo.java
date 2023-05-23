package com.test.recipemanagementsystem.repository;

import com.test.recipemanagementsystem.model.AuthenticationToken;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ITokenRepo extends JpaRepository<AuthenticationToken, String> {
    @Query(nativeQuery = true, value = "select * from authentication_token where user_id = :id")
    Integer findByUserId(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "Delete from authentication_token where user_id = :id")
    Integer deleteByUserId(@Param("id") Integer id);
}

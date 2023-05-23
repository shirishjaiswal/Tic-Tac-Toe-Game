package com.test.recipemanagementsystem.repository;

import com.test.recipemanagementsystem.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepo extends JpaRepository<Comment, Integer> {
}

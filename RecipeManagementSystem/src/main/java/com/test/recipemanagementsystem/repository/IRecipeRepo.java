package com.test.recipemanagementsystem.repository;

import com.test.recipemanagementsystem.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRecipeRepo extends JpaRepository<Recipe, Integer> {
}

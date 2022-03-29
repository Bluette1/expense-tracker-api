package com.pairlearning.expensetrackerapi.repositories;

import java.util.List;

import com.pairlearning.expensetrackerapi.domain.Category;
import com.pairlearning.expensetrackerapi.exceptions.ETBadRequestException;
import com.pairlearning.expensetrackerapi.exceptions.ETResourceNotFoundException;

public interface CategoryRepository {
  List<Category> findall(Integer userId) throws ETResourceNotFoundException;

  Category findById(Integer userId, Integer categoryId) throws ETResourceNotFoundException;

  Integer create(Integer userId, String title, String description) throws ETBadRequestException;

  void update(Integer userId, Integer categoryId, Category category) throws ETBadRequestException;

  void removeById(Integer userId, Integer categoryId) throws ETResourceNotFoundException;
}

package com.pairlearning.expensetrackerapi.services;

import java.util.List;

import com.pairlearning.expensetrackerapi.domain.Category;
import com.pairlearning.expensetrackerapi.exceptions.ETBadRequestException;
import com.pairlearning.expensetrackerapi.exceptions.ETResourceNotFoundException;

public interface CategoryService {
  List<Category> fetchAllCategories(Integer userId);

  Category fetchCategoryById(Integer userId, Integer categoryId) throws ETResourceNotFoundException;

  Category addCategory(Integer userId, String title, String description) throws ETBadRequestException;

  void updateCategory(Integer userId, Integer categoryId, Category category) throws ETBadRequestException;

  void removeCategoryWithAllTransactions(Integer userId, Integer categoryId) throws ETResourceNotFoundException;
}

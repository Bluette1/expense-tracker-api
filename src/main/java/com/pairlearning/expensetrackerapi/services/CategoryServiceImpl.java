package com.pairlearning.expensetrackerapi.services;

import java.util.List;

import com.pairlearning.expensetrackerapi.domain.Category;
import com.pairlearning.expensetrackerapi.exceptions.ETBadRequestException;
import com.pairlearning.expensetrackerapi.exceptions.ETResourceNotFoundException;
import com.pairlearning.expensetrackerapi.repositories.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
  @Autowired
  CategoryRepository categoryRepository;

  @Override
  public Category addCategory(Integer userId, String title, String description) throws ETBadRequestException {
    Integer categoryId = categoryRepository.create(userId, title, description);
    return categoryRepository.findById(userId, categoryId);
  }

  @Override
  public List<Category> fetchAllCategories(Integer userId) {
    return categoryRepository.findAll(userId);
  }

  @Override
  public Category fetchCategoryById(Integer userId, Integer categoryId) throws ETResourceNotFoundException {
    return categoryRepository.findById(userId, categoryId);
  }

  @Override
  public void removeCategoryWithAllTransactions(Integer userId, Integer categoryId) throws ETResourceNotFoundException {
  }

  @Override
  public void updateCategory(Integer userId, Integer categoryId, Category category) throws ETBadRequestException {
    categoryRepository.update(userId, categoryId, category);
  }

}

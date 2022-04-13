package com.pairlearning.expensetrackerapi.domain;

public class Category {
  private Integer categoryId;
  private Integer userId;
  private String title;
  private String description;
  private Double totalExpense;

  public Category(Integer categoryId, Integer userId, String title, String description, Double totalExpense) {
    this.categoryId = categoryId;
    this.userId = userId;
    this.title = title;
    this.description = description;
    this.totalExpense = totalExpense;
  }

  public Integer getCategoryId() {
    return this.categoryId;
  }

  public Integer getUserId() {
    return this.userId;
  }

  public String getTitle() {
    return this.title;
  }

  public String getDescription() {
    return this.description;
  }

  public Double getTotalExpense() {
    return this.totalExpense;
  }
}

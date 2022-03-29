package com.pairlearning.expensetrackerapi.domain;

public class Transaction {
  private Integer transactionId;
  private Integer userId;
  private Integer categoryId;
  private Double amount;
  private Long transactionDate;
  private String note;

  public Transaction(
      Integer transactionId,
      Integer userId,
      Integer categoryId,
      Double amount,
      Long transactionDate,
      String note) {
    this.transactionId = transactionId;
    this.userId = userId;
    this.categoryId = categoryId;
    this.transactionDate = transactionDate;
    this.note = note;
  }

  public Integer getTransactionId() {
    return this.transactionId;
  }

  public Integer getUserId() {
    return this.userId;
  }

  public Integer getCategoryId() {
    return this.categoryId;
  }

  public Double getAmount() {
    return this.amount;
  }

  public String getNote() {
    return this.note;
  }

  public Long getTransactionDate() {
    return this.transactionDate;
  }
}

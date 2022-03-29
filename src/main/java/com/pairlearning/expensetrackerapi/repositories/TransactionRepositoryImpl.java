package com.pairlearning.expensetrackerapi.repositories;

import java.util.List;

import com.pairlearning.expensetrackerapi.domain.Transaction;
import com.pairlearning.expensetrackerapi.exceptions.ETBadRequestException;
import com.pairlearning.expensetrackerapi.exceptions.ETResourceNotFoundException;

public class TransactionRepositoryImpl implements TransactionRepository {

  @Override
  public List<Transaction> getAll(Integer userId, Integer categoryId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Transaction findById(Integer userId, Integer categoryId, Integer transactionId)
      throws ETResourceNotFoundException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Integer create(Integer userId, Integer categoryId, Integer transactionId, Double amount, Long transactionDate,
      String note) throws ETBadRequestException {
    // TODO Auto-generated method stub
    return null;
  }
  
}

package com.pairlearning.expensetrackerapi.services;

import java.util.List;

import com.pairlearning.expensetrackerapi.domain.Transaction;
import com.pairlearning.expensetrackerapi.exceptions.ETBadRequestException;
import com.pairlearning.expensetrackerapi.exceptions.ETResourceNotFoundException;

public class TransactionServiceImpl implements TransactionService {

  @Override
  public List<Transaction> findAllTransactions(Integer userId, Integer categoryId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Transaction fetchTransactionById(Integer userId, Integer categoryId, Integer transactionId)
      throws ETResourceNotFoundException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Transaction addTransaction(Integer userId, Integer categoryId, Integer transactionId, Double amount,
      Long transactionDate, String note) throws ETBadRequestException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void updateTransaction(Integer userId, Integer categoryId, Integer transactionId, Transaction transaction)
      throws ETBadRequestException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void removeById(Integer userId, Integer categoryId, Integer transactionId) throws ETResourceNotFoundException {
    // TODO Auto-generated method stub
    
  }
  
}

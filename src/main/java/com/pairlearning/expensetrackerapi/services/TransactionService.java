package com.pairlearning.expensetrackerapi.services;

import java.util.List;
import com.pairlearning.expensetrackerapi.domain.Transaction;
import com.pairlearning.expensetrackerapi.exceptions.ETBadRequestException;
import com.pairlearning.expensetrackerapi.exceptions.ETResourceNotFoundException;

public interface TransactionService {
  List<Transaction> findAllTransactions(Integer userId, Integer categoryId);

  Transaction fetchTransactionById(Integer userId, Integer categoryId, Integer transactionId)
      throws ETResourceNotFoundException;

  Transaction addTransaction(Integer userId, Integer categoryId, Integer transactionId, Double amount,
      Long transactionDate, String note) throws ETBadRequestException;

  void updateTransaction(Integer userId, Integer categoryId, Integer transactionId, Transaction transaction) throws ETBadRequestException;

  void removeById(Integer userId, Integer categoryId, Integer transactionId) throws ETResourceNotFoundException;
}

package com.pairlearning.expensetrackerapi.repositories;

import java.util.List;
import com.pairlearning.expensetrackerapi.domain.Transaction;
import com.pairlearning.expensetrackerapi.exceptions.ETBadRequestException;
import com.pairlearning.expensetrackerapi.exceptions.ETResourceNotFoundException;

public interface TransactionRepository {
  List<Transaction> getAll(Integer userId, Integer categoryId);
  Transaction findById(Integer userId, Integer categoryId, Integer transactionId) throws ETResourceNotFoundException;
  Integer create(Integer userId, Integer categoryId, Double amount, Long transactionDate, String note) throws ETBadRequestException;
}

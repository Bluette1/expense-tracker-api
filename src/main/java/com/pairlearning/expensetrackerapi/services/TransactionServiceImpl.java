package com.pairlearning.expensetrackerapi.services;

import java.util.List;

import com.pairlearning.expensetrackerapi.domain.Transaction;
import com.pairlearning.expensetrackerapi.exceptions.ETBadRequestException;
import com.pairlearning.expensetrackerapi.exceptions.ETResourceNotFoundException;
import com.pairlearning.expensetrackerapi.repositories.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
  @Autowired
  TransactionRepository transactionRepository;

  @Override
  public List<Transaction> findAllTransactions(Integer userId, Integer categoryId) {
    return transactionRepository.getAll(userId, categoryId);
  }

  @Override
  public Transaction fetchTransactionById(Integer userId, Integer categoryId, Integer transactionId)
      throws ETResourceNotFoundException {
    return transactionRepository.findById(userId, categoryId, transactionId);
  }

  @Override
  public Transaction addTransaction(Integer userId, Integer categoryId, Double amount,
      Long transactionDate, String note) throws ETBadRequestException {
    int transactionId = transactionRepository.create(userId, categoryId, amount, transactionDate, note);
    return transactionRepository.findById(userId, categoryId, transactionId);
  }

  @Override
  public void updateTransaction(Integer userId, Integer categoryId, Integer transactionId, Transaction transaction)
      throws ETBadRequestException {
    transactionRepository.update(userId, categoryId, transactionId, transaction);
  }

  @Override
  public void removeById(Integer userId, Integer categoryId, Integer transactionId) throws ETResourceNotFoundException {
    transactionRepository.removeById(userId, categoryId, transactionId);
  }
  
}

package com.pairlearning.expensetrackerapi.repositories;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.List;

import com.pairlearning.expensetrackerapi.domain.Category;
import com.pairlearning.expensetrackerapi.exceptions.ETBadRequestException;
import com.pairlearning.expensetrackerapi.exceptions.ETResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

  private static final String SQL_CREATE = "INSERT INTO ET_CATEGORIES(CATEGORY_ID, USER_ID, TITLE"
      + ", DESCRIPTION) VALUES(NEXTVAL('ET_CATEGORIES_SEQ'), ?, ?, ?)";
  private static final String FIND_BY_ID = "SELECT C.CATEGORY_ID, C.USER_ID, C.TITLE, C.DESCRIPTION, COALESCE(SUM(T.AMOUNT), 0) TOTAL_EXPENSE FROM ET_TRANSACTIONS T RIGHT OUTER JOIN "
      + "ET_CATEGORIES C ON T.CATEGORY_ID = C.CATEGORY_ID WHERE C.USER_ID = ? AND C.CATEGORY_ID = ? "
      + "GROUP BY C.CATEGORY_ID";

  private static final String SQL_FIND_ALL = "SELECT C.CATEGORY_ID, C.USER_ID, C.TITLE, C.DESCRIPTION, COALESCE(SUM(T.AMOUNT), 0) TOTAL_EXPENSE FROM ET_TRANSACTIONS T RIGHT OUTER JOIN "
      + "ET_CATEGORIES C ON T.CATEGORY_ID = C.CATEGORY_ID WHERE C.USER_ID = ?"
      + "GROUP BY C.CATEGORY_ID";

  private static final String SQL_UPDATE = "UPDATE ET_CATEGORIES SET TITLE = ?, DESCRIPTION = ? WHERE USER_ID = ? AND CATEGORY_ID = ?";

  private static final String SQL_DELETE_CATEGORY = "DELETE FROM ET_CATEGORIES WHERE USER_ID = ? AND CATEGORY_ID = ?";

  private static final String SQL_DELETE_TRANSACTIONS = "DELETE FROM ET_TRANSACTIONS WHERE CATEGORY_ID = ?";

  @Autowired
  JdbcTemplate jdbcTemplate;

  @Override
  public Integer create(Integer userId, String title, String description) throws ETBadRequestException {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    try {
      jdbcTemplate.update(connection -> {
        PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, userId);
        ps.setString(2, title);
        ps.setString(3, description);
        return ps;
      }, keyHolder);
      return (Integer) keyHolder.getKeys().get("CATEGORY_ID");
    } catch (Exception e) {
      throw new ETBadRequestException("Invalid Request");
    }
  }

  @Override
  public Category findById(Integer userId, Integer categoryId) throws ETResourceNotFoundException {
    try {
      return jdbcTemplate.queryForObject(FIND_BY_ID, new Object[] { userId, categoryId }, categoryRowMapper);

    } catch (Exception e) {
      throw new ETResourceNotFoundException("Category not found");
    }
  }

  @Override
  public List<Category> findAll(Integer userId) throws ETResourceNotFoundException {
    try {
      return jdbcTemplate.query(SQL_FIND_ALL, new Object[] { userId }, categoryRowMapper);

    } catch (Exception e) {
      throw new ETResourceNotFoundException("Resource not found");
    }
  }

  @Override
  public void removeById(Integer userId, Integer categoryId) throws ETResourceNotFoundException {
    this.removeAllCatTransactions(categoryId);
    jdbcTemplate.update(SQL_DELETE_CATEGORY, new Object[]{userId, categoryId});
  }

  @Override
  public void update(Integer userId, Integer categoryId, Category category) throws ETBadRequestException {
    try {
      jdbcTemplate.update(SQL_UPDATE,
          new Object[] { category.getTitle(), category.getDescription(), userId, categoryId });

    } catch (Exception e) {
      throw new ETBadRequestException("Invalid request");
    }
  }

  private void removeAllCatTransactions(Integer categoryId) {
    jdbcTemplate.update(SQL_DELETE_TRANSACTIONS, new Object[]{ categoryId });
  }

  private RowMapper<Category> categoryRowMapper = ((rs, rowNum) -> {
    return new Category(
        rs.getInt("CATEGORY_ID"),
        rs.getInt("USER_ID"),
        rs.getString("TITLE"),
        rs.getString("DESCRIPTION"),
        rs.getDouble("TOTAL_EXPENSE"));
  });

}

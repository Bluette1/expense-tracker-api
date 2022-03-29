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
  public List<Category> findall(Integer userId) throws ETResourceNotFoundException {
    return null;
  }

  @Override
  public void removeById(Integer userId, Integer categoryId) throws ETResourceNotFoundException {
  }

  @Override
  public void update(Integer userId, Integer categoryId, Category category) throws ETBadRequestException {
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

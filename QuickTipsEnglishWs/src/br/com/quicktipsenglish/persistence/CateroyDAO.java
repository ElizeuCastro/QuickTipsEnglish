package br.com.quicktipsenglish.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CateroyDAO {

	public Category save(final Category category) {
		int resultId = -1;
		try {
			final String sql = "insert into category (category_name) value (?)";
			final Connection connection = ConnectionFactory.getConnection();
			final PreparedStatement statement = connection.prepareStatement(
					sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, category.getName());
			statement.executeUpdate();
			final ResultSet resultSet = statement.getGeneratedKeys();
			while (resultSet.next()) {
				resultId = resultSet.getInt(1);
			}
			resultSet.close();
		} catch (final SQLException e) {
			throw new RuntimeException(e);
		} finally {
			category.setId(resultId);
			ConnectionFactory.closeConnection();
		}
		return category;
	}

	public List<Category> categories() {
		final List<Category> categories = new ArrayList<Category>();
		try {
			final Statement statement = ConnectionFactory.getConnection()
					.createStatement();
			final ResultSet resultSet = statement
					.executeQuery("select * from category order by category_name asc");
			while (resultSet.next()) {
				final Category category = new Category(resultSet.getInt(1),
						resultSet.getString(2));
				categories.add(category);
			}
		} catch (final SQLException e) {
			throw new RuntimeException(e);
		}
		return categories;
	}

}

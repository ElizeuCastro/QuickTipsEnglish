package br.com.quicktipsenglish.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.quicktipsenglish.model.User;

public class UserDAO {

	public User save(User user) {
		int generetedId = -1;
		try {
			final String sql = "insert into user (user_email, user_nickname, user_password) values "
					+ "(?,?,?)";
			PreparedStatement statement = ConnectionFactory.getConnection()
					.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getNickName());
			statement.setString(3, user.getPassword());
			statement.executeUpdate();
			ResultSet resultSet = statement.getGeneratedKeys();
			while (resultSet.next()) {
				generetedId = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			user.setId(generetedId);
			ConnectionFactory.closeConnection();
		}
		return user;
	}

	public boolean login(User user) {
		final String sql = "select * from user where user_nickname = ? and user_password = ?";
		boolean sucess = false;
		try {
			PreparedStatement statement = ConnectionFactory.getConnection()
					.prepareStatement(sql);
			statement.setString(1, user.getNickName());
			statement.setString(2, user.getPassword());
			final ResultSet resultSet = statement.executeQuery();
			sucess = resultSet.next();
		} catch (final SQLException e) {
			throw new RuntimeException(e);
		}
		return sucess;
	}
}

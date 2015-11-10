package br.com.quicktipsenglish.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.quicktipsenglish.model.Tips;
import br.com.quicktipsenglish.model.TipsItem;

public class TipsDAO {

	public Tips save(final Tips tips) {
		int resultId = -1;
		try {
			final String sql = "insert into tips (tips_name) value (?)";
			final Connection connection = ConnectionFactory.getConnection();
			final PreparedStatement statement = connection.prepareStatement(
					sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, tips.getName());
			statement.executeUpdate();
			final ResultSet resultSet = statement.getGeneratedKeys();
			while (resultSet.next()) {
				resultId = resultSet.getInt(1);
			}
			resultSet.close();
		} catch (final SQLException e) {
			throw new RuntimeException(e);
		} finally {
			tips.setId(resultId);
			ConnectionFactory.closeConnection();
		}
		return tips;
	}

	public List<Tips> getTips() {
		final List<Tips> list = new ArrayList<Tips>();
		try {
			final String tipsSql = "select * from tips order by tips_name";
			final Statement statement = ConnectionFactory.getConnection()
					.createStatement();
			final ResultSet resultSet = statement.executeQuery(tipsSql);
			while (resultSet.next()) {
				final Tips tips = new Tips(resultSet.getInt(1),
						resultSet.getString(2));
				final List<TipsItem> items = getTipsItems(tips.getId());
				tips.setTips(items);
				list.add(tips);
			}
		} catch (final SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	private List<TipsItem> getTipsItems(final int tipId) {
		final List<TipsItem> items = new ArrayList<TipsItem>();
		try {
			final String sql = "select tips_item_id, tips_item_description_br,"
					+ " tips_item_description_us, tips_item_tips_id"
					+ " from tips_item where tips_item_tips_id = ?";
			final PreparedStatement statement = ConnectionFactory
					.getConnection().prepareStatement(sql);
			statement.setInt(1, tipId);
			final ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				final TipsItem item = new TipsItem(resultSet.getInt(1),
						resultSet.getString(2), resultSet.getString(3),
						resultSet.getInt(4));
				items.add(item);
			}
		} catch (final SQLException e) {
			throw new RuntimeException(e);
		}
		return items;
	}
}

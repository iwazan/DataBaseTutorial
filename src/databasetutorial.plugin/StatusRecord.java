package databasetutorial.plugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.entity.Player;


public class StatusRecord {

	private Connection conn;
	private String host, database, username, password;
	private int port;

	public StatusRecord() {
		host = "localhost";
		port = 3306;
		database = "xxxx";
		username = "root";
		password = "*********";
	}

	public StatusRecord savePlayer(Player player) {
		try {
			openConnection();
			String name = player.getName();
			UUID uuid = player.getUniqueId();

			String sql = "INSERT INTO players (uuid, name) VALUES (?, ?);";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, uuid.toString().replace("-", ""));
			preparedStatement.setString(2, name);
			preparedStatement.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
		}
		return null;
	}

	private void openConnection() throws SQLException, ClassNotFoundException {
		if (conn != null && !conn.isClosed()) {
			return;
		}

		synchronized (this) {
			if (conn != null && !conn.isClosed()) {
				return;
			}
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.username, this.password);
		}
	}
}

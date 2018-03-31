package databasetutorial.plugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.plugin.java.JavaPlugin;

public class UseJdbc extends JavaPlugin {

	public static void Main() throws Exception {
		//String servername  = "localhost";
		String databasename = "xxxx";
		String user = "root";
		String password  = "********";
		//String serverencoding = "UTF-8";
		String url = "jdbc:mysql://localhost/" +  databasename;
		Connection con = null;

		try{
			//取得
			//Class.forName( "com.mysql.jdbc.Driver" ).newInstance();
			con = DriverManager.getConnection(url, user, password);
			System.out.println( "Connected.." );
			Statement st = con.createStatement();
			String sqlStr = "SELECT * FROM test";
			ResultSet result = st.executeQuery( sqlStr );
			while(result.next())
			{
				String str1 = result.getString( "id" );
				String str2 = result.getString( "name" );
				System.out.println("\n" + str1 + "," + str2 );
			}
			//
			result.close();
			st.close();
			con.close();
		}catch( SQLException e){
			System.out.println( "Connection Failed." +  e.toString());
			throw new Exception();
		}/*catch (InstantiationException | IllegalAccessException | ClassNotFoundException e){
			System.out.println("ロード失敗  " + e);
		}*/
		finally{
			try{
				if(con != null){
					con.close();
				}
			}
			catch(Exception e){
				System.out.println("Exception2 :" + e.toString());
				throw new Exception();
			}
		}

	}

}

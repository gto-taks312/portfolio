//DBから指定した日付のデータを取得するSQL

package sql;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import config.DBconfig;
import object.DiaryData;

public class GetPastDiary {

	public DiaryData getPastDiary(String date) throws FileNotFoundException {
		
		//JDBCドライバを明示的にロード
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
				
		//データベースへの接続情報をプロパティファイルから取得
		DBconfig db_info = new DBconfig();
		String url = db_info.getDBinfo().get("url");
		String user = db_info.getDBinfo().get("user");
		String pass = db_info.getDBinfo().get("password");
	
		DiaryData diary = new DiaryData();
			
		//データベースへの接続、try〜catch~resourcesを使用
		try(Connection conn = DriverManager.getConnection(url, user, pass)){

			Statement stmt = conn.createStatement();
			
			//引数のdateを渡して、DBより一致するレコードのデータを取得するSQL文
			String getDiary_sql = "select * from diary_tb where day='" + date + "'";
			ResultSet rs = stmt.executeQuery(getDiary_sql);
			
			//DBから取得したデータをdiaryに格納する
			while(rs.next()) {
				diary.setSubject(rs.getString("subject"));
				diary.setContent(rs.getString("content"));
				diary.setLearning(rs.getString("learning"));
				diary.setDifficulty(rs.getString("difficulty"));
				diary.setLevel(rs.getString("level"));	
			}
			
		}catch (SQLException e) {
			System.out.println("データベースとの接続を閉じます");
			e.printStackTrace();
		}
		
		return diary;
		
	}
}

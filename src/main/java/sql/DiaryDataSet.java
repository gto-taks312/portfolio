package sql;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;

import config.DBconfig;
import object.TimeControl;

public class DiaryDataSet {
	public void diarySet(int student_id, String attendance_time, String subject, String content, String learning, String difficulty, String level) throws FileNotFoundException {
		
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
			
			//データベースへの接続、try〜catch~resourcesを使用
			try(Connection conn = DriverManager.getConnection(url, user, pass)){
				Statement stmt = (Statement)conn.createStatement();
				
				TimeControl time = new TimeControl();
				
				//String型のattendance_timeをLocalDateTime型に変換するメソッドを呼び出す
				LocalDateTime dateTime =  time.getLocalDateTime(attendance_time);
				
				//LocalDateTime型に変換されたデータをLocalDate型に変換するメソッド
				LocalDate date = time.getLocalDate(dateTime);
				
				//LocalDate型に変換されたデータをString型に変換するメソッド
				String day = time.getDay(date);
					
				String diarySet_sql = 
					"insert into diary_tb(student_id, day, subject, content, learning, difficulty, level) "
					+ "values(" + student_id + ",'" + day + "','" + subject + "','" + content + "','" + learning + "','" + difficulty + "','" + level + "')";
				
				stmt.executeUpdate(diarySet_sql);
				
				System.out.println("データベースへの日報記録完了");
				
				
				
			}catch (SQLException e) {
				System.out.println("データベースとの接続を閉じます");
				e.printStackTrace();
			}
			
	}
		
}

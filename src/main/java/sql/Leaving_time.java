//ログアウトボタンが押された時間を取得し、データベースに記録するSQL

package sql;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import config.DBconfig;
import object.Student;
import object.TimeControl;

public class Leaving_time{
	public void set_time(Student student) throws FileNotFoundException{
		
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
			
			//TimeControlクラスのgetNowDateTimeメソッドを呼びだし、現在の時刻をStirng型に変換したデータを受け取る
			TimeControl time = new TimeControl();
			String now = time.getNowDateTime();
			
			//ログアウト時間を取得し、ログイン時に取得していた生徒ID部分の行を更新
			String timeSet_sql = "UPDATE work_tb SET leaving_time = '" + now + "' WHERE attendance_time = '" + student.getAttendance_time() + "'";
			//SQLを実行
			stmt.executeUpdate(timeSet_sql);

			//studentクラスのsetLeaving_timeにログアウト時の時間をセット
			student.setLeaving_time(now);
			
			System.out.println("データベースへのログアウト時間登録完了");
			
		}catch (SQLException e) { 
			System.out.println("データベースとの接続を閉じます");
			e.printStackTrace();
		}
	}
	
}
//ログインボタンが押された時間を取得し、DBに記録するSQL

package sql;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import config.DBconfig;
import object.Student;
import object.TimeControl;

public class Attendance_time{
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
			
			Statement stmt = conn.createStatement();
			
			//TimeControlクラスのgetNowDateTimeメソッドを呼びだし、現在の時刻をStirng型に変換したデータを受け取る
			TimeControl time = new TimeControl();
			String now = time.getNowDateTime();
			
			//ログイン時間を取得し、DBに追加するSQL文を変数に代入
			String timeSet_sql = "insert into work_tb(student_id, attendance_time) values(" + student.getId() + ", '" + now + "')";
			//SQLを実行
			stmt.executeUpdate(timeSet_sql);
			
			//studentクラスのsetAttendance_timeにログイン時の時間をセット
			student.setAttendance_time(now);
			
			System.out.println("データベースへのログイン時間登録完了");
			
		}catch (SQLException e) {
			System.out.println("データベースとの接続を閉じます");
			e.printStackTrace();
		}
		
	}
	
}
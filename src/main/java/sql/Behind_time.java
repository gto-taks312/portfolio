//登校予定時間からどれくらい遅れたかをDBに記録するSQL（時間、分を記録）

package sql;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import config.DBconfig;
import object.Student;
import object.TimeControl;

public class Behind_time {
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
			
			TimeControl time = new TimeControl();
			//studetnクラスに格納されているString型のattendance_timeをLocalDateTime型に変換するメソッド
			LocalDateTime now = time.getLocalDateTime(student.getAttendance_time());
			
			//学校の開始基準時間（Hourのみ）を設定
			LocalDateTime ldt = LocalDateTime.now().with(LocalTime.of(8, 0));
			
			//ログインした時刻と設定した登校時間の差を計算（Hourのみ）
			Duration behindTime = Duration.between(ldt, now);
			
			int behindTimeHours = (int)behindTime.toHours();
			int behindTimeMinutes = 0;

			//ログインした時刻（分）が45〜59なら45を引いた数を遅刻時間の分に設定。0〜44なら15を足した数を設定。
			//もし時刻（分）が0〜45分なら差を格納している変数から時刻（時）を−１する。
			if(now.getMinute() >= 45 && now.getMinute() <= 59) {
				behindTimeMinutes = now.getMinute() - 45;
			}else if(now.getMinute() >= 0 && now.getMinute() <= 44) {
				behindTimeHours -= 1;
				behindTimeMinutes = now.getMinute() + 15;
			}
			
			//LocalDateTime型のデータを時間：分：秒のみ取得しString型へ変換。UPDATE文でDBへ遅刻時間を登録。
			LocalDateTime bt = LocalDateTime.now().with(LocalTime.of(behindTimeHours, behindTimeMinutes, now.getSecond()));
			DateTimeFormatter datetimeformatter = DateTimeFormatter.ofPattern("HH:mm:ss");
			String behind_time = datetimeformatter.format(bt);
			String behindTime_sql = "UPDATE work_tb SET behind_time = '" + behind_time + "' WHERE attendance_time = '" + student.getAttendance_time() + "'";
			//SQLを実行
			stmt.executeUpdate(behindTime_sql);
			
			System.out.println("データベースへの遅刻時間登録完了");
			
		}catch (SQLException e) {
			System.out.println("データベースとの接続を閉じます");
			e.printStackTrace();
		}
	}
	
}

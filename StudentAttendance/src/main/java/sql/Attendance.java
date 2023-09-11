//DBに遅刻していたら”遅刻”、してなければ何も登録しないSQL

package sql;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import config.DBconfig;
import object.Student;

public class Attendance {
	public void set_attendance(Student student) throws FileNotFoundException {
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
	
				Behind_time bt = new Behind_time();
				
				LocalDateTime now = LocalDateTime.now();
				
				//もし、ログインした時刻が9時をすぎているか、または8時45分をすぎていたらDBのattendannceに”遅刻"と登録。
				//遅刻時間を登録するBehind_timeクラスを呼び出す。
				//ログイン時刻が8時45分未満ならDBのattendanceには何も登録しない
				if(now.getHour() >= 9 || (now.getHour() == 8 && now.getMinute() >= 45)) {
					String attendanceSet_sql = "UPDATE work_tb SET attendance = '遅刻' WHERE attendance_time = '" + student.getAttendance_time() + "'";
					//SQLを実行
					stmt.executeUpdate(attendanceSet_sql);
					
					bt.set_time(student);
				}else {
					String attendanceSet_sql = "UPDATE work_tb SET attendance = '---', behind_time = '---' WHERE attendance_time = '" + student.getAttendance_time() + "'";
					//SQLを実行
					stmt.executeUpdate(attendanceSet_sql);
				}
				
				System.out.println("データベースへの遅刻の有無登録完了");
			
			}catch (SQLException e) {
				System.out.println("データベースとの接続を閉じます");
				e.printStackTrace();
			}
				
	}
}

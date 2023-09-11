//DBに早退したら”早退”、してなければ何も登録しないSQL

package sql;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import config.DBconfig;
import object.Student;

public class Leaving {
	public void set_leaving(Student student) throws FileNotFoundException {
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
	
				Leaving_early_time let = new Leaving_early_time();
				
				LocalDateTime now = LocalDateTime.now();
				
				//もし、ログアウトした時刻が15時よりも前か、または15時〜15時30分ならDBのleavingに”早退"と登録。
				//早退時間を登録するLeaving_early_timeクラスを呼び出す。
				//ログアウト時刻が15時30分以降ならDBのleavingには何も登録しない
				if(now.getHour() < 15 || (now.getHour() == 15 && now.getMinute() <= 30)) {
					String leavingSet_sql = "UPDATE work_tb SET leaving = '早退' WHERE attendance_time = '" + student.getAttendance_time() + "'";
					//SQLを実行
					stmt.executeUpdate(leavingSet_sql);
					
					let.set_time(student);
				}else {
					String leavingSet_sql = "UPDATE work_tb SET leaving = '---', leaving_early_time = '---' WHERE attendance_time = '" + student.getAttendance_time() + "'";
					//SQLを実行
					stmt.executeUpdate(leavingSet_sql);
				}
				
				System.out.println("データベースへの遅刻の有無登録完了");
			
			}catch (SQLException e) {
				System.out.println("データベースとの接続を閉じます");
				e.printStackTrace();
			}
				
	}
}

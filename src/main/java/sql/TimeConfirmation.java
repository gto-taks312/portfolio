package sql;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import config.DBconfig;
import object.TimeControl;

public class TimeConfirmation {
	
	
	public ArrayList<ArrayList<String>> getTime(String month) throws FileNotFoundException, NullPointerException {
		
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
			
			ArrayList<ArrayList<String>> arrays = new ArrayList<ArrayList<String>>();
			
			//データベースへの接続、try〜catch~resourcesを使用
			try(Connection conn = DriverManager.getConnection(url, user, pass)){

				Statement stmt = conn.createStatement();			
			
				//引数のmonthを渡して、DBのwork_tbからdayカラムにmonthを含むレコードのデータを取得するSQL文
				String getTime_sql = "select attendance_time, attendance, leaving_time, leaving from work_tb where attendance_time like '%" + month + "%'";
				ResultSet rs = stmt.executeQuery(getTime_sql);		
				
				//現在ログインしている日時を取得し、LocalDate型に変更。それをString型に変換
				TimeControl tc = new TimeControl();
				LocalDate ld = tc.getLocalDate(LocalDateTime.now());
				String ld_now = tc.getDay(ld);

				while(rs.next()) {
				//DBより取得した登校時間（attendance_time）をLocalDateTime型に変換し、
				//そこから日付のみ（LocalDate型）のみ取得。String型に変換する
				LocalDateTime ldt = tc.getLocalDateTime(rs.getString("attendance_time"));
				LocalDate attecdance_ldt = tc.getLocalDate(ldt);
				String aldt = tc.getDay(attecdance_ldt);
								
					//ログインしている日付とDBから取得した日付を比較し、
					//ログインしている日付以前の情報を格納していく
					if(!(aldt.equals(ld_now))) {
						ArrayList<String> list = new ArrayList<>(List.of(rs.getString("attendance_time"), rs.getString("attendance"), rs.getString("leaving_time"), rs.getString("leaving")));
						arrays.add(list);
					}
				}
				
			}catch (SQLException e) {
				System.out.println("データベースとの接続を閉じます");
				e.printStackTrace();
			}				
			
			return arrays;
			
	}
}
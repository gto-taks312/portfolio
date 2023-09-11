//ログイン画面で入力されたID・パスワードと
//DBに登録されている値が一致しているかの確認を行うSQL
//一致していればDB内にある情報をstudentクラスに格納する

package sql;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.DBconfig;
import object.Student;

public class Login {
	public Student check(String student_id, String password) throws FileNotFoundException{
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
		
		//実行するSQL
		String login_sql = "select * from student_tb" + " where student_id = ? and password = ?;";  
		//ログインした生徒のオブジェクトを作成
		Student student = new Student();
		
		//データベースへの接続、try〜catch~resourcesを使用
		try(Connection conn = DriverManager.getConnection(url, user, pass)){
			
			PreparedStatement stmt = conn.prepareStatement(login_sql);
			
			//変数login_sqlの１番目の？に引数のuser_idをセット
			stmt.setString(1, student_id);
			//変数login_sqlの２番目の？に引数のpasswordをセット
			stmt.setString(2, password);
			
			//SQLを実行して結果を取得
			ResultSet rs = stmt.executeQuery();
			
			//データベースから取得した値をAdminオブジェクトに格納する
			//値がなければ、login_flag(false)のみ格納
			if(rs.next()) {
				student.setId(rs.getInt("student_id"));
				student.setName(rs.getString("name"));
				student.setPassword(rs.getString("password"));
				student.setDepartment(rs.getString("department"));
				student.setLogin_flag(true);
			} else {
				student.setLogin_flag(false);
			}			
			
		}catch (SQLException e) {
			System.out.println("データベースとの接続を閉じます");
			e.printStackTrace();
		}
		
		return student;
	}
}

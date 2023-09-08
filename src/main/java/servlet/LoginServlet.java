package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import object.Student;
import sql.Attendance;
import sql.Attendance_time;
import sql.Login;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    //ログイン画面を表示
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//文字コードの設定
		response.setContentType("text/html; charset = UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		//ログイン画面で入力された値(id,パスワード)を取得
		String user_id = request.getParameter("student_id");
		String password = request.getParameter("password");
		
		//ログイン画面で入力された値を元に、データベースに登録された生徒データの値を取得
		//入力された情報でデータベースから値が取得できない場合、ログイン失敗
		Login login = new Login();
		Attendance_time at = new Attendance_time();
		Attendance attendance = new Attendance();
		Student student = login.check(user_id, password);
		
		if(student.isLogin_flag()) {
			//情報が格納されているstudentを引数に、ログイン時間をDBに登録するAttendance＿timeクラスのメソッドを呼び出す
			at.set_time(student);
			
			//情報が格納されているstudentを引数に、遅刻しているかどうかをDBに登録するattendandeクラスのメソッドを呼び出す
			attendance.set_attendance(student);
			
			//セッションオブジェクトを作成し、生徒情報を格納
			HttpSession session = request.getSession(true);
			session.setAttribute("student", student);
			
			System.out.println("ログイン成功");
			
			//ログイン成功したら次の画面へ遷移する
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/HomeScreen.jsp");
			dispatcher.forward(request, response);
			
		}else {
			//ログイン失敗したらログイン画面へ遷移
			System.out.println("ログイン失敗");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login_failed.jsp");
			dispatcher.forward(request, response);
		}
	}
}


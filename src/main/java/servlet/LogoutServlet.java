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
import sql.Leaving;
import sql.Leaving_time;


@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	//ログアウト機能の実装
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//文字コードの設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		Leaving_time lt = new Leaving_time();
		Leaving leaving = new Leaving();
		
		//ログイン時に格納されている生徒情報を呼び出し、ログアウト時間をDBに登録するLeaving＿timeクラスのメソッドを呼び出す
		HttpSession student_session = request.getSession();
		Student student = (Student)student_session.getAttribute("student");
		
		//情報が格納されているstudentを引数に、ログアウト時間をDBに登録するLeaving＿timeクラスのメソッドを呼び出す
		lt.set_time(student);
		
		//情報が格納されているstudentを引数に、早退したかどうかをDBに登録するleavingクラスのメソッドを呼び出す
		leaving.set_leaving(student);
		
		//セッションオブジェクトを作成。引数falseを入れて、セッションが開始されてなければnullを返す。開始されていればセッションを破棄。
		HttpSession session = request.getSession(false);
		if(session != null) {
			System.out.println("セッションが存在しているので、セッションを破棄します。");
			session.invalidate();
		} else {
			System.out.println("セッションが存在していません。");
		}
		
		session = request.getSession(false);
		//セッションが破棄されていたらログアウト成功とし、ログイン画面に戻る。
		if(session == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		} else {
			System.out.println("セッションが破棄されていません。");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}

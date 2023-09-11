package servlet;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import object.Student;
import sql.TimeConfirmation;

@WebServlet("/TimeConfirmationServlet")
public class TimeConfirmationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/TimeConfirmation.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			//選択された月をString型のmonthに格納
			String month = request.getParameter("month");
			
			//ログイン時にセットしている生徒情報が入ったセッションを取得
			HttpSession student_session = request.getSession();
			Student student = (Student)student_session.getAttribute("student");
		
			//monthを引数に、DBよりmonthと一致する情報を取得するメソッド
			TimeConfirmation tc = new TimeConfirmation();
			ArrayList<ArrayList<String>> time = tc.getTime(month, student);
			
			if(!(time.isEmpty())) { 
				//上記メソッドで返ってきた値が空でなければ、
				//返ってきた日報情報をsessionに格納しておく
				HttpSession session = request.getSession();	
				session.setAttribute("time", time);
		
				//登下校時間を表示する画面に遷移させる
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/ConfirmationResult.jsp");	
				dispatcher.forward(request, response);
			
			}else {
				//メソッドで返ってきた値が空（ログインしていない日）だったら、読み込み失敗画面へ遷移
				System.out.println("値のない日付が選択された");
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/null_time.jsp");	
				dispatcher.forward(request, response);
			}
			
		}catch(NullPointerException e) {
			//ログイン、もしくはログアウトし忘れて値がnullの時には、読み込み失敗画面へ遷移
			System.out.println("attenndance_timeまたはleaving_timeがnullの日付が選択された");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/null_time.jsp");	
			dispatcher.forward(request, response);
		}
		
	}

}
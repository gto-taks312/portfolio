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
		
			//monthを引数に、DBよりmonthと一致する情報を取得するメソッド
			TimeConfirmation tc = new TimeConfirmation();
			ArrayList<ArrayList<String>> time = tc.getTime(month);
			
			if(!(time.isEmpty())) {
			//上記より帰ってきた日報情報をsessionに格納しておく
			HttpSession session = request.getSession();	
			session.setAttribute("time", time);
		
			//登下校時間を表示する画面に遷移させる
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/ConfirmationResult.jsp");	
			dispatcher.forward(request, response);
			
			}else {
				System.out.println("値のない日付が選択された");
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/null_time.jsp");	
				dispatcher.forward(request, response);
			}
			
		}catch(NullPointerException e) {
			System.out.println("attenndance_timeまたはleaving_timeがnullの日付が選択された");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/null_time.jsp");	
			dispatcher.forward(request, response);
		}
		
	}

}
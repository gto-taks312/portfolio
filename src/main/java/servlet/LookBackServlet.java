package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import object.DiaryData;
import sql.GetPastDiary;


@WebServlet("/LookBackServlet")
public class LookBackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/pastDiary.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//選択された日付をString型のdayに格納
		String day = request.getParameter("hiduke");
		
		//dayを引数に、DBよりdayと一致する情報を取り出すメソッド
		GetPastDiary gpd = new GetPastDiary();
		DiaryData diary = gpd.getPastDiary(day);
		
		//上記より帰ってきた日報情報をsessionに格納しておく
		HttpSession session = request.getSession();
		session.setAttribute("diary", diary);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/pastDiaryDisplay.jsp");
		dispatcher.forward(request, response);
		
	}

}

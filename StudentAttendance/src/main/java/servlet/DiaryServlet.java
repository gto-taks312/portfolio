package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import object.DiarySaveFailed;
import object.Student;
import sql.DiaryDataSet;


@WebServlet("/DiaryServlet")
public class DiaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/diary.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//文字コードの設定
		response.setContentType("text/html; charset = UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		//日報入力画面で入力された各項目を取得し、変数に格納
		String subject = request.getParameter("kamoku");
		String content = request.getParameter("naiyou");
		String learning = request.getParameter("manabi");
		String difficulty = request.getParameter("muzukashii");
		String level = request.getParameter("hyouka");
		
		//ログイン時にセットしている生徒情報が入ったセッションを取得
		HttpSession student_session = request.getSession();
		Student student = (Student)student_session.getAttribute("student");
		
		//変数levelに入っている値が整数であればtrue、整数以外が入っていればfalseをresultに代入しておく
		boolean result = true;
		for(int i = 0; i < level.length(); i++) {
			if(Character.isDigit(level.charAt(i))) {
				continue;
			}else {
				result = false;
				break;
			}
		}
		
		//上記でlevelが整数と確認できていたら、String型のlevelをint型に変換する
		int int_level = 0;
		if(result == true) {
			int_level = Integer.parseInt(level);
		}
		
		//各変数のうち、いずれかが何も入力されていなければ記録失敗の画面へ遷移
		if(subject.isEmpty() || content.isEmpty() || learning.isEmpty() || difficulty.isEmpty() || level.isEmpty()) {
			
			System.out.println("日報記録失敗");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/diarySaveFailed.jsp");
			dispatcher.forward(request, response);
			
		}else if(result == false) { //resultにfalseが入っていたら(levelの値に整数以外があったら)記録失敗の画面へ遷移
				
			System.out.println("日報記録失敗");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/diarySaveFailed_3.jsp");
			dispatcher.forward(request, response);
				
		}else if(subject.length() <= 50 && content.length() <= 255 && learning.length() <= 255 && difficulty.length() <= 255 && (int_level > 0 && int_level <= 5)) {
			//日報で入力した各項目がDBに登録できる文字数以内なら、登録するメソッドをよび記録成功画面へ遷移
			
			//生徒のi	dとログイン時間、入力された各項目を引数に、DBへ登録するメソッドを呼び出す
			DiaryDataSet dds = new DiaryDataSet();
			dds.diarySet(student.getId(), student.getAttendance_time(), subject,  content, learning, difficulty, level) ;
		
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/DiarySaveOk.jsp");
			dispatcher.forward(request, response);
			
		} else {  //各項目の文字数がDBに登録できない文字数だったら保存できないと知らせる画面へ遷移
			DiarySaveFailed dsf = new DiarySaveFailed();
				
			//各項目で入力した情報の文字数を格納しておく
			dsf.setSubject_length(subject.length());
			dsf.setContent_length(content.length());
			dsf.setLearning_length(learning.length());
			dsf.setDifficulty_length(difficulty.length());	
			dsf.setLevel(int_level);
				
			HttpSession session = request.getSession();
			session.setAttribute("diarySaveFailed", dsf);
				
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/diarySaveFailed_2.jsp");
			dispatcher.forward(request, response);
		}
	}

}

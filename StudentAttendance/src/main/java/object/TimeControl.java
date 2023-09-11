package object;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeControl {
	
		//現在の時刻（LocalDateTime型）を指定するString型に変換するメソッド
		public String getNowDateTime() {
			DateTimeFormatter datetimeformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime localDateTime = LocalDateTime.now();
			return localDateTime.format(datetimeformatter);
		}
	
		//String型の引数をLocalDateTime型に変換するメソッド
		public LocalDateTime getLocalDateTime(String time) {
			DateTimeFormatter datetimeformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime datetime = LocalDateTime.parse(time, datetimeformatter);
			return datetime;
		}
			
		//引数のLocalDate型のデータを指定するString型に変換するメソッド
		public String getDay(LocalDate date) {
			DateTimeFormatter datetimeformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			return date.format(datetimeformatter);
		}
		
		//引数のLocalDateTime型のデータをLocalDate型に変換するメソッド
		public LocalDate getLocalDate(LocalDateTime ldt) {
			LocalDate localdate = LocalDate.of(ldt.getYear(), ldt.getMonth(), ldt.getDayOfMonth());
			return localdate;
		}
						
}

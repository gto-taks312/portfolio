package object;

public class DiaryData {
	
	private String subject;
	private String content;
	private String learning;
	private String difficulty;
	private String level;
	

	
	public void setSubject(String subject){
		this.subject = subject;
	}
	public String getSubject() {
		return subject;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	public String getContent() {
		return content;
	}
	
	public void setLearning(String learning){
		this.learning = learning;
	}
	public String getLearning() {
		return learning;
	}
	
	public void setDifficulty(String difficulty){
		this.difficulty = difficulty;
	}
	public String getDifficulty() {
		return difficulty;
	}
	
	public void setLevel(String level){
		this.level = level;
	}
	public String getLevel() {
		return level;
	}
	
}

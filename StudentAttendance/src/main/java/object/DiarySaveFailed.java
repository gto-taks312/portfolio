package object;

public class DiarySaveFailed {
	private int subject_length;
	private int content_length;
	private int learning_length;
	private int difficulty_length;
	private int level;
	
	public void setSubject_length(int subject_length) {
		this.subject_length = subject_length;
	}
	public int getSubject_length() {
		return this.subject_length;
	}
	public void setContent_length(int content_length) {
		this.content_length = content_length;
	}
	public int getContent_length() {
		return this.content_length;
	}
	public void setLearning_length(int learning_length) {
		this.learning_length = learning_length;
	}
	public int getLearning_length() {
		return this.learning_length;
	}
	public void setDifficulty_length(int difficulty_length) {
		this.difficulty_length = difficulty_length;
	}
	public int getDifficulty_length() {
		return this.difficulty_length;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getLevel() {
		return this.level;
	}
	
	public String[] getDiaryData() {
		String[] diaryData = new String[5];
		
		diaryData[0] = "「科目」の文字数が多すぎます。入力しなおしてください。";
		diaryData[1] = "「講義内容」の文字数が多すぎます。入力しなおしてください。";
		diaryData[2] = "「学んだこと」の文字数が多すぎます。入力しなおしてください。";
		diaryData[3] = "「難しかった点」の文字数が多すぎます。入力しなおしてください。";
		diaryData[4] = "「習熟度」の値が間違っています。１〜５の数字を入力しなおしてください。";
		
		return diaryData;
	}
}
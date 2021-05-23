import java.io.Serializable;


public class Question implements Serializable{

	private static final long serialVersionUID = 1L;
	private String question;
	private String ans[];
	private int correctAnswer;
	
	public Question(String question, String ans1, String ans2, String ans3,	String ans4, int correct) {
		this.question = question;
		ans = new String[4];
		ans[0] = ans1;
		ans[1] = ans2;
		ans[2] = ans3;
		ans[3] = ans4;
		setCorrectAnswer(correct);
	}
	
	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer(int index){
		return ans[index];
	}
	
	public String getCorrectAnswer(){
		if(correctAnswer != -1)
			return ans[correctAnswer];
		else
			return "---";
	}
	
	public int getCorrectAnswerNumber(){
		return this.correctAnswer;
	}
	
	public void setCorrectAnswer(int num){
		correctAnswer = num;
	}

}

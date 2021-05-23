import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;


public class QuestionManager implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Question> questionList;
	
		
	public QuestionManager(){
		questionList = new ArrayList<Question>();
	}
	
	public void addQuestion(Question q){
		questionList.add(q);
	}
	
	public Question getQuestion(){
		Random r = new Random();
		Question dummy = new Question("No questions!, please add some...","","","","",1);
		if(questionList.size() == 0)
			return dummy;
		else{
			int index = r.nextInt(questionList.size());
			return questionList.get(index);
		}

	}
}
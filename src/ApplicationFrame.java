import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.*;


public class ApplicationFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton play, addQuestion;
	private QuestionManager questionManager;
	private AddQuestionFrame questionFrame;
	private PlayGameFrame playGameFrame;
	
	public static final String QUESTIONS_FILE_NAME = "questions.ser";
	public static final String GAME_FOLDER = "Game_Data";

	public ApplicationFrame(){
		super("Who wants to be a billionaire!!");
		questionManager = loadQuestions(); 
		questionFrame = new AddQuestionFrame(questionManager);
		playGameFrame = new PlayGameFrame(questionManager);
		play = new JButton("Play!!");
		addQuestion = new JButton("Add Questions");
		Font f = new Font("Jokerman", Font.BOLD, 24); 
		play.setFont(f);
		addQuestion.setFont(f);
		addQuestion.addActionListener(this);
		play.addActionListener(this);
		JPanel panel = new JPanel(new GridLayout(2,1));
		panel.add(play);
		panel.add(addQuestion);
		this.setSize(400, 200);
		this.setContentPane(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public QuestionManager loadQuestions(){
		try{
			FileInputStream fileIn = 
					new FileInputStream(GAME_FOLDER+File.separator+QUESTIONS_FILE_NAME);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			questionManager = (QuestionManager)in.readObject();
			fileIn.close();
			in.close();
		}
		catch(Exception e){
			System.out.println("Cannot read file "+QUESTIONS_FILE_NAME);
		}
		if(questionManager == null){
			questionManager = new QuestionManager();
		}
		return questionManager;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == play){
			playGameFrame.loadNextQuestion();
			playGameFrame.setVisible(true);
		}
		else{
			questionFrame.setVisible(true);
		}
			
	}
	
	
	
	
	
	
	
	
	

}

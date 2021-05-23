import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.swing.*;

public class PlayGameFrame extends JFrame implements ActionListener {


	private static final long serialVersionUID = 1L;
	private JLabel questionLabel;
	private JRadioButton[] answers;
	private ButtonGroup group;
	private JButton commitAnswerButton,saveGameButton;
	private JPanel masterPanel, questionPanel, answerPanel, buttonsPanel, counterPanel;
	private QuestionManager questionManager;
	private Question currentQuestion;
	
	private int correctCounter = 0, wrongCounter = 0;
	private JLabel correctCounterLabel, wrongCounterLabel;

	public PlayGameFrame(QuestionManager questionManager){
		super("Play!");
		this.questionManager = questionManager;
		Font f = new Font("Lucida Bright", Font.ITALIC, 24);
		Font fArial = new Font("Arial", Font.PLAIN, 20);
		Font fTimes = new Font("Times", Font.PLAIN, 18);
		masterPanel = new JPanel(new GridLayout(5,1));
		questionPanel = new JPanel(); 
		answerPanel = new JPanel();
		buttonsPanel = new JPanel(); 
		counterPanel = new JPanel();
		correctCounterLabel = new JLabel("Correct Answers: "); correctCounterLabel.setFont(fArial);	correctCounterLabel.setForeground(Color.blue);
		wrongCounterLabel = new JLabel("Wrong Answers: ");	wrongCounterLabel.setFont(fArial); wrongCounterLabel.setForeground(Color.red);
		counterPanel.add(correctCounterLabel);
		counterPanel.add(wrongCounterLabel);
		group = new ButtonGroup();
		currentQuestion = this.questionManager.getQuestion();
		questionLabel = new JLabel(currentQuestion.getQuestion());
		questionLabel.setFont(f);
		answers = new JRadioButton[4];
		for(int i=0; i<4; i++){
			answers[i] = new JRadioButton(currentQuestion.getAnswer(i));
			answers[i].setFont(fTimes);
			group.add(answers[i]);
		}
		commitAnswerButton = new JButton("Commit");
		commitAnswerButton.addActionListener(this);
		saveGameButton = new JButton("Save game");
		saveGameButton.addActionListener(this);
		
		JLabel qL = new JLabel("Question: ");
		qL.setFont(fArial);
		questionPanel.add(qL);
		questionPanel.add(questionLabel);
		
		answerPanel.add(answers[0]);
		answerPanel.add(answers[1]);
		answerPanel.add(answers[2]);
		answerPanel.add(answers[3]);
		
		buttonsPanel.add(commitAnswerButton);
	//	buttonsPanel.add(saveGameButton);
		
		masterPanel.add(questionPanel);
		masterPanel.add(answerPanel);
		masterPanel.add(buttonsPanel);
		masterPanel.add(counterPanel);
		
		this.setContentPane(masterPanel);
		this.setSize(686,216);
	}

	public void loadNextQuestion(){
		currentQuestion = this.questionManager.getQuestion();
		questionLabel.setText(currentQuestion.getQuestion());
		for(int i=0; i<4; i++){
			answers[i].setText(currentQuestion.getAnswer(i));
		}
		group.clearSelection();
		correctCounterLabel.setText("Correct Answers: "+correctCounter+"      ");
		wrongCounterLabel.setText("Wrong Answers: "+wrongCounter);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == commitAnswerButton){
			int selectedAnswer = -1;
			for(int i=0; i< 4; i++){
				if(answers[i].isSelected()){
					selectedAnswer = i;
					break;
				}
			}
			int correctAnswer = currentQuestion.getCorrectAnswerNumber();
			if(selectedAnswer != -1 && selectedAnswer != correctAnswer){
				JOptionPane.showMessageDialog(this, 
						"Sorry, wrong answer!\n" +
						"Correct answer is: "+currentQuestion.getCorrectAnswer(),
						"Wrong Answer",
						JOptionPane.ERROR_MESSAGE);
				wrongCounter++;
				loadNextQuestion();
			}
			else if(selectedAnswer != -1 && selectedAnswer == correctAnswer){
				JOptionPane.showMessageDialog(this, 
						"Answer correct!",
						"Well Done!",
						JOptionPane.INFORMATION_MESSAGE);
				correctCounter++;
				loadNextQuestion();
			}
			else if(selectedAnswer == -1){
				JOptionPane.showMessageDialog(this, 
						"Please select an answer",
						"No answer selected",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

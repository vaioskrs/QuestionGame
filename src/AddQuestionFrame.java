import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.swing.*;

public class AddQuestionFrame extends JFrame implements ActionListener {


	private static final long serialVersionUID = 1L;
	private JTextField question, ans1, ans2, ans3, ans4;
	private JRadioButton[] radioButtons;
	private ButtonGroup group;
	private JButton addQuestionButton,saveQuestionsButton;
	private JPanel masterPanel, questionPanel;
	private QuestionManager questionManager;

	public AddQuestionFrame(QuestionManager questionManager){
		super("Add Question");
		this.questionManager = questionManager;
		masterPanel = new JPanel(new GridLayout(1,1));
		questionPanel = new JPanel();
		question = new JTextField(50);
		ans1 = new JTextField(50);
		ans2 = new JTextField(50);
		ans3 = new JTextField(50);
		ans4 = new JTextField(50);
		group = new ButtonGroup();
		radioButtons = new JRadioButton[4];
		for(int i=0; i<4; i++){
			radioButtons[i] = new JRadioButton("Answer "+(i+1));
			group.add(radioButtons[i]);
		}
		addQuestionButton = new JButton("Make Question");
		addQuestionButton.addActionListener(this);
		saveQuestionsButton = new JButton("Save Questions to File");
		saveQuestionsButton.addActionListener(this);

		questionPanel.add(new JLabel("Question: "));
		questionPanel.add(question);
		questionPanel.add(radioButtons[0]);
		questionPanel.add(ans1);
		questionPanel.add(radioButtons[1]);
		questionPanel.add(ans2);
		questionPanel.add(radioButtons[2]);
		questionPanel.add(ans3);
		questionPanel.add(radioButtons[3]);
		questionPanel.add(ans4);
		questionPanel.add(addQuestionButton);
		questionPanel.add(saveQuestionsButton);
		masterPanel.add(questionPanel);
		this.setContentPane(masterPanel);
		this.setSize(686,216);
	}

	private void clearFields(){
		question.setText("");
		ans1.setText("");
		ans2.setText("");
		ans3.setText("");
		ans4.setText("");
		group.clearSelection();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == addQuestionButton){
			String q = question.getText();
			String a1 = ans1.getText();
			String a2 = ans2.getText();
			String a3 = ans3.getText();
			String a4 = ans4.getText();
			int correctAns = -1;
			for(int i=0; i<4; i++){
				if(radioButtons[i].isSelected()){
					correctAns = i;
					break;
				}
			}
			if(correctAns == -1){
				JOptionPane.showMessageDialog(this, "Please select the" +
						" correct answer");
			}
			else{
				Question question = new Question(q,a1,a2,a3,a4,correctAns);
				questionManager.addQuestion(question);
				clearFields();
			}
		}
		else{
			try{
				File outDir = new File(ApplicationFrame.GAME_FOLDER);
				if(!outDir.exists()){
					outDir.mkdir();
				}
				FileOutputStream fos 
				= new FileOutputStream(outDir+
										File.separator+
										ApplicationFrame.QUESTIONS_FILE_NAME);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				if(questionManager != null)
					oos.writeObject(questionManager);
				oos.close();
				fos.close();
			}
			catch(Exception ex){
				
			}
		}
	}
	
	
	
	
	
	
	
	
	

}

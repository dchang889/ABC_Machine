import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GUI extends JFrame{

	private JTextField addtf, deltf;;
	private JLabel addLabel, delLabel;
	private JTextArea ta;
	private JButton resetB;
	private JScrollPane sp;
	private ArrayList<String> wordList = new ArrayList<String>();

	public GUI() {

		super("ABC Machine");

		GroupLayout layout = new GroupLayout(this.getContentPane());
		this.getContentPane().setLayout(layout);
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateContainerGaps(true);

		addLabel = new JLabel("Add Word: ");

		addtf = new JTextField("", 12);

		delLabel = new JLabel("Delete Word: ");

		deltf = new JTextField("", 12);

		ta = new JTextArea(30,20);
		ta.setEditable(false);
		sp = new JScrollPane(ta);

		resetB = new JButton("          Reset          ");

		doL(layout);

		EHandler handler = new EHandler();
		addtf.addActionListener(handler);
		deltf.addActionListener(handler);
		resetB.addActionListener(handler);

	}

	private void doL(GroupLayout layout) {
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(addLabel)
										.addComponent(addtf)
										)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(delLabel)
										.addComponent(deltf)
										)	
								)
						.addComponent(sp)
						.addComponent(resetB)
						)
				);

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(addLabel)
										.addComponent(delLabel)
										)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(addtf)
										.addComponent(deltf)
										)
								)
						)
				.addComponent(sp)
				.addComponent(resetB)
				);

	}

	private class EHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			String string = "";
			if(event.getSource()==addtf)
				addWord(event, string);
			else if(event.getSource()==deltf)
				deleteWord(event, string);
			else if (event.getSource()==resetB)
				reset();
			refresh();
		}


		private void reset() {
			wordList.clear();			
		}

		private void refresh() {
			ta.setText("");
			for (int i = 0; i < wordList.size(); i++){
				ta.append(wordList.get(i) + "\n");
			}

		}//End of Refresh

		private void addWord(ActionEvent event, String string) {
			try {
				string = String.format(event.getActionCommand());
				wordList.add(string);
				Collections.sort(wordList);
				JOptionPane.showMessageDialog(null, string + " has been added.");
			}
			catch (Exception e){
				JOptionPane.showMessageDialog(null, string + " could not be added to the list.");
			}		

		}//End if Add Word

		private void deleteWord(ActionEvent event, String string) {

			string = String.format(event.getActionCommand());

			if (wordList.indexOf(string) != -1) {

				wordList.remove(string);

				JOptionPane.showMessageDialog(null, string + " has been removed.");
			}
			else
			{
				JOptionPane.showMessageDialog(null, string + " does not exist within the list.");
			}			
		}//End of Delete Word	

	}//End of Event Handler
}//End of GUI Class
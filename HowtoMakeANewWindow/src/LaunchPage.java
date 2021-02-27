import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class LaunchPage implements ActionListener{

	JFrame frame = new JFrame();
	JButton myButton = new JButton("Press me like you press...");
	
	
	public LaunchPage() {
		
		myButton.setBounds(100, 160, 200, 40);
		myButton.setFocusable(false);
		myButton.addActionListener(this);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420,420);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.add(myButton);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==myButton) {
			frame.dispose();		//get rid of other window
			NewWindow myWindow = new NewWindow();
		}
		
	}
}

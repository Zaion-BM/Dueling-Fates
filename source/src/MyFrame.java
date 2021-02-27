import javax.swing.*;

import source.MyPanel;

import java.awt.*;

/*import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

//FIRST VIDEO ------------------------------------------------
public class MyFrame  extends JFrame{	//leszármazik JFrame.-ből

	MyFrame(){
		
		this.setTitle("JFrame Title");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//EXIT with X, HIDE on Close is default
		this.setResizable(false); 
		this.setSize(420,420);
		this.setVisible(true);
		
		ImageIcon image = new ImageIcon("src/pepelaugh.png");		//create imageIcon
		this.setIconImage(image.getImage());						//imagenek fgv-ét meghívjuk 
		this.getContentPane().setBackground(new Color(60,145,255));	//RGB, hexya 0xF36HFG
		
	}
}
*/

//FOURTH VIDEO ------------------------------------------------
/*public class MyFrame extends JFrame implements ActionListener{	//leszármazik JFrame.-ből implement a method

	JButton button; 		//make it "global"
	JLabel label;
	
	MyFrame(){
	
		ImageIcon icon2 = new ImageIcon("src/forsenCD.jpg");
		ImageIcon icon = new ImageIcon("src/pepelaugh.png");
		
		label = new JLabel();
		label.setIcon(icon2);
		label.setBounds(50, 50, 150, 150);
		label.setVisible(false);
		
		button = new JButton();		//Instantiate in constructor! - példányosít
		button.setBounds(200, 200, 350, 400);
		button.addActionListener(this);
		//button.addActionListener(e -> System.out.println("Lamda Expression"));
		button.setText("Menu Button");
		button.setFocusable(false);
		button.setIcon(icon);
		button.setHorizontalTextPosition(JButton.CENTER);
		button.setVerticalTextPosition(JButton.BOTTOM);
		button.setFont(new Font("Baloo Thambi",Font.PLAIN, 14));
		button.setIconTextGap(+10);
		button.setForeground(Color.red);
		button.setBackground(Color.LIGHT_GRAY);
		button.setBorder(BorderFactory.createEtchedBorder());
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setSize(700,700);
		this.setVisible(true);
		
		//Don't forget to add!!
		this.add(button);
		this.add(label);
	
	}

@Override			//CLick on MyFrame - Add unimplemented method - this frame listen to events
public void actionPerformed(ActionEvent e) {
	
	if(e.getSource()==button) {
		System.out.println("The Two Time");
		button.setEnabled(false);
		label.setVisible(true);

	}
}
	
}
*/	
	/*
//11th VIDEO ------------------------------------------------

	public class MyFrame extends JFrame implements ActionListener{

		JButton button;
		JTextField textField;
		
		public MyFrame() {

			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//EXIT with X, HIDE on Close is default
			this.setLayout(new FlowLayout());
			
			button = new JButton("Submit");
			button.addActionListener(this);
			button.setFont(new Font("Baloo Thambi",Font.PLAIN, 15));
			button.setFocusable(false);
			
			
			textField = new JTextField();
			textField.setPreferredSize(new Dimension(250,30));
			textField.setFont(new Font("Baloo Thambi",Font.PLAIN,24));
			textField.setForeground(new Color(0xA68FE2));
			textField.setBackground(Color.black);
			textField.setCaretColor(Color.white);
			textField.setText("Enter username");
			
			
			this.add(button);
			this.add(textField);			
			this.pack();		//size will adjust
			this.setVisible(true);
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==button) {
				System.out.println("Welcome " + textField.getText() + "!");
				button.setEnabled(false);
				textField.setEditable(false);
			}
			}
			}
		*/	
//12th VIDEO ------------------------------------------------

		/*public class MyFrame extends JFrame implements ActionListener{

			JButton button = new JButton();
			JCheckBox box = new JCheckBox();
			ImageIcon xIcon;
			ImageIcon pipaIcon;
			
			public MyFrame() {

				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//EXIT with X, HIDE on Close is default
				this.setLayout(new FlowLayout());
				
				button.setText("Submit");
				button.addActionListener(this);
				
				xIcon = new ImageIcon("src/pog.jpg");
				pipaIcon = new ImageIcon("src/pepelaugh.png");
				
				box.setText("Choose Character");
				box.setFocusable(false);
				box.setFont(new Font("Impact", Font.PLAIN, 25));
				box.setIcon(xIcon);
				box.setSelectedIcon(pipaIcon);
				
				this.add(box);
				this.add(button);
				this.pack();		//size will adjust
				this.setVisible(true);

		}
	@Override
	public void actionPerformed(ActionEvent e) {	
			if(e.getSource()==button) {
				System.out.println(box.isSelected());
			}
		
		}
}
		*/	
				
//13th VIDEO ------------------------------------------------
/*				
public class MyFrame extends JFrame implements ActionListener{

	JRadioButton heroKnight;
	JRadioButton heroNinja;
	JRadioButton heroPeasent;
	ImageIcon knightIcon;
	ImageIcon ninjaIcon;
	
	public MyFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//EXIT with X, HIDE on Close is default
		this.setLayout(new FlowLayout());
		
		knightIcon = new ImageIcon("src/knight.png");
		heroKnight = new JRadioButton("Knight");
		heroKnight.addActionListener(this);
		heroKnight.setIcon(knightIcon);
		
		ninjaIcon = new ImageIcon("src/Ninja.png");
		heroNinja = new JRadioButton("Ninja");	
		heroNinja.addActionListener(this);
		heroNinja.setIcon(ninjaIcon);
		
		heroPeasent = new JRadioButton("Peasent");
		heroPeasent.addActionListener(this);
		
		ButtonGroup selection = new ButtonGroup();
		selection.add(heroPeasent);
		selection.add(heroKnight);
		selection.add(heroNinja);
		
		
		this.add(heroPeasent);
		this.add(heroKnight);
		this.add(heroNinja);
		
		this.pack();		//size will adjust
		this.setVisible(true);

	}
				
		@Override
		public void actionPerformed(ActionEvent e) {	
				if(e.getSource()==heroKnight) {
					System.out.println("Knight selected");
				}
				else if(e.getSource()==heroNinja) {
					System.out.println("Ninja selected");
				}
				else if(e.getSource()==heroPeasent) {
					System.out.println("You must be crazy!");
				}
			
			}
}
*/
//14th VIDEO ------------------------------------------------
/*
public class MyFrame extends JFrame implements ActionListener{

	JComboBox comboBox;
public MyFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//EXIT with X, HIDE on Close is default
		this.setLayout(new FlowLayout());
		
		//Integer[] = animals = {} 		//Wrapper class!!!!
		String[] animals = {"dog", "cat", "bear"};
		comboBox = new JComboBox(animals);
		comboBox.addActionListener(this);
		
		//comboBox.setEditable(true);
		//System.out.println(comboBox.getItemCount());
		//comboBox.insertItemAt("pig", 0);
		//comboBox.setSelectedIndex(0);		//first run is at 0
		//comboBox.removeItem("cat");
		
		this.add(comboBox);
		this.pack();
		this.setVisible(true);
		
		
		
}

@Override
public void actionPerformed(ActionEvent e) {

		if(e.getSource()==comboBox) {
			//System.out.println(comboBox.getSelectedItem());
			System.out.println(comboBox.getSelectedIndex());

		}
}
}

*/

//15th VIDEO ------------------------------------------------
/*
 
  slider = new Jslider(0,100,50);			//50  kezdő érték
  slider.setPaintTicks(true);
  slider.setMinorTickSpacing(10);
  
  slider.setPaintTrack(true);
  slider.setMajortickSpacing(25);		//Major tick
  
  slider.setPaintLabels(true);
  slider.setORientation(SwingConstants.Vertical);
  //slider.getValue(); //addChangeListener!!!!!!!!!!!!!!!!!!!!!!!!!!!!
  slider.setText("Celsius")
  

 */

//16th VIDEO ------------------------------------------------
/*
 * JProgressBar 
 * bar.setValue(0);
 * bar.setBounds(0,0,420,50);
 * bar.setStringPainted(true);
 * 
 * HitbyWeapon
 */

/*public class MyFrame extends JFrame implements ActionListener{

	JProgressBar Health;
	JButton button;
public MyFrame() {
	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//EXIT with X, HIDE on Close is default
		this.setLayout(new FlowLayout());
		
		button = new JButton("Shoot!");
		button.addActionListener(this);
		
		Health = new JProgressBar();
        Health.setString("");
        //Health.setFont(new Font("Arial",Font.PLAIN,14));
        Health.setPreferredSize(new Dimension(500,30));
		Health.setValue(100);
		Health.setBounds(0,0,420,50);
		Health.setStringPainted(true);
		Health.setForeground(Color.red);
		Health.setBackground(Color.white);
		
		this.setForeground(Color.green);
		this.setLocation(500, 500);
		this.add(button);
		this.add(Health);
		this.pack();
		this.setVisible(true);		
		
}

public void takenDamage() {
		Health.setValue(Health.getValue()-20);
	}

@Override
public void actionPerformed(ActionEvent e) {

	if(e.getSource()==button) {
		takenDamage();
		
	}
}
}
*/
//JfileChooser 		showSaveDialog
//Jmenu
//JmenuBar
//JcolorChooser

//20th VIDEO ------------------------------------------------
//Keylistener 
/*
public class MyFrame extends JFrame implements KeyListener{		//KEY

	JLabel label;

public MyFrame() {
	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//EXIT with X, HIDE on Close is default
		this.setLayout(null);
		label = new JLabel();
		label.setBounds(0, 0, 100, 100);
		label.setBackground(Color.red);
		label.setOpaque(true);
		//label.setIcon(icon);
		
		this.add(label);
		this.addKeyListener(this);
		this.setSize(500,500);
		this.setVisible(true);		
		
}

@Override
public void keyTyped(KeyEvent e) {
	// char output
	switch (e.getKeyChar()) {
		case 'a': label.setLocation(label.getX()-3, label.getY());
			break;
		case 'w': label.setLocation(label.getX(), label.getY()-3);
			break;
		case 's': label.setLocation(label.getX(), label.getY()+3);
			break;		
		case 'd': label.setLocation(label.getX()+3, label.getY());
			break;		
	}
}

@Override
public void keyPressed(KeyEvent e) {
	// int putput
	
	switch (e.getKeyCode()) {
	case 37: label.setLocation(label.getX()-3, label.getY());
		break;
	case 38: label.setLocation(label.getX(), label.getY()-3);
		break;
	case 40: label.setLocation(label.getX(), label.getY()+3);
		break;		
	case 39: label.setLocation(label.getX()+3, label.getY());
		break;
	}
	
}

@Override
public void keyReleased(KeyEvent e) {
	// button released
	//System.out.println("You released key char: " + e.getKeyChar());
	System.out.println("You released key code: " + e.getKeyCode());
	
}

}
*/

//21th VIDEO ------------------------------------------------
//Mouse listener

/*
public class MyFrame extends JFrame implements MouseListener{		//KEY


	JLabel label;
	
public MyFrame() {
	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//EXIT with X, HIDE on Close is default
		this.setLayout(null);
		
		label = new JLabel();
		label.setBounds(0, 0, 100, 100);
		label.setBackground(Color.red);
		label.setOpaque(true);
		label.addMouseListener(this);
		
		this.add(label);
		this.setSize(500,500);
		this.setVisible(true);		
		
}

@Override
public void mouseClicked(java.awt.event.MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mousePressed(java.awt.event.MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseReleased(java.awt.event.MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseEntered(java.awt.event.MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseExited(java.awt.event.MouseEvent e) {
	// TODO Auto-generated method stub
	
}
}
*/

//21th VIDEO ------------------------------------------------

/*
public class Game {
	
	JFrame frame;
	JLabel label;
	
	Action upAction;
	Action downAction;

	Game(){
	
	
		frame = new JFrame("KeyBinding");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		frame.setLayout(null);
		
		label = new JLabel();
		label.setBounds(150, 50, 100, 100);
		label.setBackground(Color.red);
		label.setOpaque(true);
		
		upAction = new UpAction();
		downAction = new DownAction();
		
		label.getInputMap().put(KeyStroke.getKeyStroke("UP"), "Felfele");
		label.getActionMap().put("Felfele", upAction);
		
		label.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "Lefele");
		label.getActionMap().put("Lefele", downAction);
		
		
		frame.setVisible(true);
		frame.add(label);
}

public class UpAction extends AbstractAction{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		label.setLocation(label.getX(), label.getY()-10);
	}
	
}

public class DownAction extends AbstractAction{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		label.setLocation(label.getX(), label.getY()+10);

	}

}

}
*/
//24th VIDEO ------------------------------------------------

public class MyFrame extends JFrame{		//KEY

	//MyPanel panel;
public MyFrame() {
	

		//panel = new MyPanel();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//EXIT with X, HIDE on Close is default
		
		this.setLayout(null);
		this.pack();
		this.setSize(500,500);
		
		//this.add(panel);
		this.setVisible(true);		
		
}
public void paint (Graphics g) {
	
	Image image = new ImageIcon("src/pog.jpg").getImage();
	Graphics2D g2d = (Graphics2D) g;
	g2d.drawImage(image, 70, 80, null);
	//g2d.setPaint(Color.red);			//Z axis, ami később van az lesz felül
	//g2d.setStroke(new BasicStroke(2));
	//g2d.drawLine(0, 0, 500, 500);
	//g2d.drawRect(40, 40, 100, 200);
	//g2d.setPaint(Color.orange);
	//g2d.fillOval(100, 100, 100, 100);
	//g2d.drawArc(200, 200, 100, 100, 180, 180);
	//g2d.fillArc(200, 200, 100, 100, 180, 180);
	
	int[] xPoints = {150, 250, 350};
	int[] yPoints = {300, 150, 300};
	g2d.setPaint(Color.BLACK);
	g2d.fillPolygon(xPoints, yPoints, 3);
	g2d.setFont(new Font("Baloo Thambi",Font.PLAIN,20));
	
	g2d.drawString("PogChamp !!4!!", 100, 100);
	//sysout //System.out.println();
}
//repaint
//CTRL+shift+ + zoomn
//parseINT convert to int to store
//random.nextdouble()
//scanner??
//wrapper acces to methods
//Integer a = 12
//ArrayList<String> food = new ArrayList<String>();


}











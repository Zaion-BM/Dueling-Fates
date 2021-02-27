public class Main {
	
	public static void main(String[] args) {
	
	
	//FIRST VIDEO ------------------------------------------------
	/*
	JFrame frame = new JFrame();
	frame.setTitle("JFrame Title");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//EXIT with X, HIDE on Close is default
	frame.setResizable(false); 
	frame.setSize(420,420);
	frame.setVisible(true);
	
	ImageIcon image = new ImageIcon("src/pepelaugh.png");		//create imageIcon
	frame.setIconImage(image.getImage());						//imagenek fgv-ét meghívjuk 
	frame.getContentPane().setBackground(new Color(60,145,255));	//RGB, hexya 0xF36HFG
	*/
		
		
	//MyFrame myFrame = new MyFrame();	
	//new MyFrame();
	
	//SECOND VIDEO ------------------------------------------------
	/*
	ImageIcon ForsenCD = new ImageIcon("src/forsenCD.jpg");
	Border border = BorderFactory.createLineBorder(Color.green, 3);
	
	JLabel label = new JLabel();
	label.setText("The Real Two Time!");
	label.setIcon(ForsenCD);
	label.setHorizontalTextPosition(JLabel.CENTER);	 //Kép közepén a text, left, right of iamgeicon
	label.setVerticalTextPosition(JLabel.TOP);		//Top, center, bottom of imageicon
	label.setForeground(Color.red);
	label.setFont(new Font("Baloo Thambi",Font.PLAIN,20));
	label.setIconTextGap(12); //set text és kép közti távolság
	label.setBackground(Color.black);
	label.setOpaque(true); 		//make background visible
	label.setBorder(border);
	label.setVerticalAlignment(JLabel.CENTER);
	label.setHorizontalAlignment(JLabel.CENTER);
	//label.setBounds(50, 0, 450, 450);			//set X, Y within frames and size
	
	JFrame frame = new JFrame();
	frame.setTitle("JFrame Title");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//EXIT with X, HIDE on Close is default
	frame.getContentPane().setBackground(new Color(0xFEFEFE));	//RGB, hexya 0xF36HFG
	frame.setSize(600,600);
	//frame.setLayout(null);					//frame marad 
	frame.add(label);
	frame.setVisible(true);
	//frame.pack();
	*/
		
	//THIRD VIDEO ------------------------------------------------
	/*
	ImageIcon imageIcon = new ImageIcon("src/forsenCD.jpg");
		
	JLabel label = new JLabel();
	label.setText("REAL!!");
	label.setIcon(imageIcon);
	//label.setVerticalAlignment(JLabel.CENTER);
	//label.setHorizontalAlignment(JLabel.CENTER);
	label.setBounds(0, 0, 230, 230);
	
	JPanel redPanel = new JPanel();
	redPanel.setBackground(Color.red);
	redPanel.setBounds(0,0,250,250);			//A Kék panelhez relatív eltolás és nem a teljes frame (lehet nem látszódik a kép)
	//redPanel.setLayout(new BorderLayout());
	redPanel.setLayout(null);

		
	JPanel bluePanel = new JPanel();
	bluePanel.setBackground(Color.blue);
	bluePanel.setBounds(250,0,250,250);
	bluePanel.setLayout(null);

	JPanel greenPanel = new JPanel();
	greenPanel.setBackground(Color.green);
	greenPanel.setBounds(0,250,500,250);
	greenPanel.setLayout(null);
		
	JFrame frame = new JFrame();
	frame.setTitle("JFrame Title");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//EXIT with X, HIDE on Close is default
	frame.getContentPane().setBackground(new Color(0xFEFEFE));	//RGB, hexya 0xF36HFG
	frame.setSize(600,600);
	frame.setLayout(null);					//frame marad 
	frame.setVisible(true);
	
	greenPanel.add(label);
	frame.add(redPanel);
	frame.add(bluePanel);
	frame.add(greenPanel);
*/
	//FOURTH VIDEO ------------------------------------------------
	//new MyFrame();
		
	//FIFTH VIDEO ------------------------------------------------
	/*	
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.setLayout(new BorderLayout(10,10));  		//No gap between x és y
		
		
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
		
		panel1.setBackground(Color.red);
		panel2.setBackground(Color.green);
		panel3.setBackground(Color.blue);
		panel4.setBackground(Color.black);
		panel5.setBackground(Color.yellow);
		
		panel1.setPreferredSize(new Dimension(100,100));
		panel2.setPreferredSize(new Dimension(100,100));
		panel3.setPreferredSize(new Dimension(100,100));
		panel4.setPreferredSize(new Dimension(100,100));
		panel5.setPreferredSize(new Dimension(100,100));
		
		//--------------------sub-----------------

		JPanel panel11 = new JPanel();
		JPanel panel21 = new JPanel();
		JPanel panel31 = new JPanel();
		JPanel panel41 = new JPanel();
		JPanel panel51 = new JPanel();
		
		panel11.setBackground(Color.yellow);
		panel21.setBackground(Color.PINK);
		panel31.setBackground(Color.WHITE);
		panel41.setBackground(Color.gray);
		panel51.setBackground(Color.CYAN);
		
		panel5.setLayout(new BorderLayout());
		
		panel11.setPreferredSize(new Dimension(50,50));
		panel21.setPreferredSize(new Dimension(50,50));
		panel31.setPreferredSize(new Dimension(50,50));
		panel41.setPreferredSize(new Dimension(50,50));
		panel51.setPreferredSize(new Dimension(50,50));
		
		panel5.add(panel11,BorderLayout.NORTH);
		panel5.add(panel21,BorderLayout.SOUTH);
		panel5.add(panel31,BorderLayout.EAST);
		panel5.add(panel41,BorderLayout.WEST);
		panel5.add(panel51,BorderLayout.CENTER);
		
		frame.add(panel1, BorderLayout.NORTH);
		frame.add(panel2, BorderLayout.WEST);
		frame.add(panel3, BorderLayout.EAST);
		frame.add(panel4, BorderLayout.SOUTH);
		frame.add(panel5, BorderLayout.CENTER);
		*/
		//SIXTH VIDEO ------------------------------------------------
		/*
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));		//LEADING, TRAILING horiz, vertic
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(100,250));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(new FlowLayout());
		
		panel.add(new JButton("1"));		//FASTER
		panel.add(new JButton("2"));
		panel.add(new JButton("3"));
		panel.add(new JButton("4"));
		panel.add(new JButton("5"));
		panel.add(new JButton("6"));
		panel.add(new JButton("7"));
		panel.add(new JButton("8"));
		panel.add(new JButton("9")); 
		
		frame.add(panel);
		frame.setVisible(true);
		*/
		//SEVENTH VIDEO ------------------------------------------------
		/*
		frame.setLayout(new GridLayout(3,3,10,10));
		*/
		//EIGHT VIDEO ------------------------------------------------
		/*
		JLabel label1 = new JLabel();
		label1.setOpaque(true);
		label1.setBackground(Color.red);
		label1.setBounds(50, 50, 200, 200);
		
		JLabel label2 = new JLabel();
		label2.setOpaque(true);
		label2.setBackground(Color.blue);
		label2.setBounds(150, 150, 200, 200);
		
		JLabel label3 = new JLabel();
		label3.setOpaque(true);
		label3.setBackground(Color.pink);
		label3.setBounds(100, 100, 200, 200);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0,0,500,500);
		layeredPane.add(label1, Integer.valueOf(0));		//JLayeredPane.DEFAULT_LAYER alsó
		layeredPane.add(label3, Integer.valueOf(1));
		layeredPane.add(label2, Integer.valueOf(2));		//legfelső
		
		JFrame frame = new JFrame();
		frame.add(layeredPane);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setLayout(null);
		frame.setVisible(true);
		*/
	
		//TENTH VIDEO ------------------------------------------------
		
		//JOptionPane.showMessageDialog(null, "GET over Here", "title", JOptionPane.PLAIN_MESSAGE);
		//JOptionPane.showMessageDialog(null, "GET over Here", "title", JOptionPane.INFORMATION_MESSAGE);
		//JOptionPane.showMessageDialog(null, "GET?", "title", JOptionPane.QUESTION_MESSAGE);
		//JOptionPane.showMessageDialog(null, "Warning", "title", JOptionPane.WARNING_MESSAGE);
		//while(true) {
		//JOptionPane.showMessageDialog(null, "GET over Here", "title", JOptionPane.ERROR_MESSAGE);
		//}
		//int answer = System.out.println(JOptionPane.showConfirmDialog(null, "Bro, do you even code", "Title!!44!!",JOptionPane.YES_NO_CANCEL_OPTION));
		//String name = JOptionPane.showInputDialog("What is your N4me: ");
		//System.out.println("Hello " + name + "!");
		/*
		String[] responses = {"No U!", "Fokin Hell!", "F*** U!"};
		ImageIcon image = new ImageIcon("src/pog.jpg");
		JOptionPane.showOptionDialog(null, 
				"Your mom gay", "Secret msg", 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.INFORMATION_MESSAGE, 
				image, 
				responses, 
				0);
		*/
		
		//Eleventh VIDEO ------------------------------------------------
		
		MyFrame frame = new MyFrame();
		//Game game = new Game();

		
	}
}
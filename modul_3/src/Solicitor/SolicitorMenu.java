package Solicitor;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

/**
 * Klasa odpowiadajaca zawyswietlanie graficznego interfejsu uzytkownika w menu podzialu majatku.
 *
 */
public class SolicitorMenu extends JFrame {
	/**
	 * f_menu = this
	 */
	final SolicitorMenu f_menu = this;
	final SolicitorConfig s_config = new SolicitorConfig();
	final JButton 
			/**
			 *	przycisk odtwarzania sekretu
			 */
			b_regenerator = new JButton(),
			/**
			 * przycisk odszyfrowania
			*/
			b_decipherer = new JButton(),
			/**
			* przycisk odczytywania testamentu
			 */
			b_reader = new JButton(),
			/**
			 * przycisk do wyjscia
			 */
			b_exit = new JButton();
	final JPanel p_panel1 = new JPanel(); //panel na przyciski
	final JPanel p_panel2 = new JPanel(); // panel na exit
	final JPanel p_panel3 = new JPanel(); // panel na na label
	final JLabel l_label = new JLabel(); // label na nazwe
	
	public SolicitorMenu(){
		super(SolicitorConfig.MenuTitle);
		

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setSize(SolicitorConfig.MenuSize[0], SolicitorConfig.MenuSize[1]);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		
		/* obrazki  na buttonach */
		try {
			Image img = ImageIO.read(getClass().getResource(SolicitorConfig.MenuButtons[0]));
				b_regenerator.setIcon(new ImageIcon(img));
			Image img1 = ImageIO.read(getClass().getResource(SolicitorConfig.MenuButtons[1]));
				b_decipherer.setIcon(new ImageIcon(img1));
			Image img2 = ImageIO.read(getClass().getResource(SolicitorConfig.MenuButtons[2]));
				b_reader.setIcon(new ImageIcon(img2));
		    Image img3 = ImageIO.read(getClass().getResource(SolicitorConfig.MenuButtons[3]));
		    	b_exit.setIcon(new ImageIcon(img3));
		    Image img4 = ImageIO.read(getClass().getResource(SolicitorConfig.MenuButtons[4]));
		    	l_label.setIcon(new ImageIcon(img4));
		  } catch (IOException ex) {
			  
		  }
		
		b_regenerator.setBorder(new EmptyBorder(0,0,0,0));
		b_decipherer.setBorder(new EmptyBorder(0,0,0,0));
		b_reader.setBorder(new EmptyBorder(0,0,0,0));
		b_exit.setBorder(new EmptyBorder(0,0,0,0));
		l_label.setBorder(new EmptyBorder(0,0,0,0));
		
		p_panel1.setLayout(new FlowLayout());
		p_panel1.add(b_regenerator);
		p_panel1.add(b_decipherer);
		p_panel1.add(b_reader);
		p_panel2.setLayout(new BorderLayout());
		p_panel2.add(b_exit, BorderLayout.EAST);
		p_panel3.setLayout(new FlowLayout());
		p_panel3.add(l_label);
		
		this.add(p_panel1,BorderLayout.CENTER);
		this.add(p_panel2,BorderLayout.SOUTH);
		this.add(p_panel3, BorderLayout.NORTH);
		
		p_panel1.setBackground(Color.black);
		p_panel2.setBackground(Color.black);
		p_panel3.setBackground(Color.black);
		
		
		/*		Odtwarzanie testamentu		*/
		b_regenerator.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				SolicitorConfig.regenerator=true;
				Regenerator regenerator = new Regenerator();
				f_menu.dispose();
                
				
			}
		});
		
		
		/*	Odszyfrowanie testamentu */		
		
		b_decipherer.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				SolicitorConfig.decipherer = true;
				Decipherer decipherer = new Decipherer();
				decipherer.pack();
				f_menu.dispose();
                
	         
			} 
		});
		
		
		/*	Odczytywanie testamentu */		
		
		b_reader.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				SolicitorConfig.reader = true;
				Reader reader = new Reader();
				reader.pack();
				f_menu.dispose();
                
	         
			} 
		});
		
		/*		EXIT	*/	
		
		b_exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
			
		});
		
	}
	
	/**
	 * Glowna funkcja programu.
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {	
		SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				new SolicitorMenu();
			}
		});
		TimeUnit.SECONDS.sleep(1);
	SwingUtilities.invokeLater(new Runnable() {
	public void run(){
			
		}
	
			
	});
	}
}

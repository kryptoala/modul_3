package Solicitor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

/**
 * Klasa odpowiadajaca za okna powiadomien wyswietlane w module podzialu majatku.
 *
 */
public class SolicitorNotification extends JFrame {
	final SolicitorNotification f_solicitorNotification = this;
	
	private JPanel 
	p_panel = new JPanel(),
	p_panel1 = new JPanel();
	
	private JLabel l_label = new JLabel();
	
	private JButton b_exit = new JButton();
	
	private JTextPane t_textPane = new JTextPane();
	
	@SuppressWarnings("deprecation")
	public SolicitorNotification(){
		
		super("Powiadomienie");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		if(SolicitorConfig.reader && SolicitorConfig.success) 
		{ 
			this.setSize(500, 500); 
		} 
		else 
		{ 
			this.setSize(SolicitorConfig.NotificationSize[0],SolicitorConfig.NotificationSize[1]); 
		}
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		
		try {
			Image img = ImageIO.read(getClass().getResource("mini_powrot.png"));
				b_exit.setIcon(new ImageIcon(img));
		} catch (IOException ex) {}
		
		
		if(!SolicitorConfig.success)
		{
			if(SolicitorConfig.regenerator)
			{
				if(!SolicitorConfig.wrong_form)
					{
						try {
							Image img1 = ImageIO.read(getClass().getResource(SolicitorConfig.RegeneratorNotifications[0]));
							l_label.setIcon(new ImageIcon(img1));
							} catch (IOException ex) {}
					}
				else
					{
						try {
							Image img1 = ImageIO.read(getClass().getResource(SolicitorConfig.RegeneratorNotifications[2]));
							l_label.setIcon(new ImageIcon(img1));
							} catch (IOException ex) {}
					}
				
			}
			
			if(SolicitorConfig.decipherer)
			{
				try {
					Image img1 = ImageIO.read(getClass().getResource(SolicitorConfig.DeciphererNotifications[0]));
					l_label.setIcon(new ImageIcon(img1));
					} catch (IOException ex) {}
			}
			
			if(SolicitorConfig.reader)
			{
				try {
					Image img1 = ImageIO.read(getClass().getResource(SolicitorConfig.ReaderNotifications[0]));
					l_label.setIcon(new ImageIcon(img1));
					} catch (IOException ex) {}
			}
		}
		
		if(SolicitorConfig.success)
		{
			if(SolicitorConfig.regenerator)
			{
			try {
				Image img1 = ImageIO.read(getClass().getResource(SolicitorConfig.RegeneratorNotifications[1]));
				l_label.setIcon(new ImageIcon(img1));
				} catch (IOException ex) {}
			}
			
			if(SolicitorConfig.decipherer)
			{
				try {
					Image img1 = ImageIO.read(getClass().getResource(SolicitorConfig.DeciphererNotifications[1]));
					l_label.setIcon(new ImageIcon(img1));
					} catch (IOException ex) {}
			}
			
			if(SolicitorConfig.reader)
			{
				try {
					Image img1 = ImageIO.read(getClass().getResource(SolicitorConfig.ReaderNotifications[1]));
					l_label.setIcon(new ImageIcon(img1));
					} catch (IOException ex) {}
			}
		}
		
		b_exit.setBorder(new EmptyBorder(0,0,0,0));
		l_label.setBorder(new EmptyBorder(0,0,0,0));
		
		p_panel.setLayout(new BorderLayout());
		
		if(SolicitorConfig.reader && SolicitorConfig.success)
		{
			p_panel.add(l_label, BorderLayout.NORTH);
		} 
		else 
		{
			p_panel.add(l_label, BorderLayout.CENTER);
		}
			
		if(SolicitorConfig.reader && SolicitorConfig.success) 
		{ 
			try { 
				StyleContext context = new StyleContext();
				StyledDocument document = new DefaultStyledDocument(context);
				MutableAttributeSet style = context.getStyle(StyleContext.DEFAULT_STYLE);
				StyleConstants.setAlignment(style, StyleConstants.ALIGN_JUSTIFIED);
				StyleConstants.setLeftIndent(style, 16);
				StyleConstants.setRightIndent(style, 16);
				t_textPane = new JTextPane(document);
				String content = new Scanner(new File(SolicitorConfig.DecipheredPath)).useDelimiter("\\Z").next();
				t_textPane.setText(content); 
				// t_textArea.disable();
				t_textPane.disable();
				t_textPane.setDisabledTextColor(Color.WHITE);
				t_textPane.setBackground(Color.BLACK);
				t_textPane.setForeground(Color.WHITE);
				} catch (FileNotFoundException e1) { 
					// TODO Auto-generated catch block 
					e1.printStackTrace(); 
					} 
			JScrollPane s_scrollPane = new JScrollPane(t_textPane); 
			p_panel.add(s_scrollPane, BorderLayout.CENTER);
			t_textPane.setCaretPosition(0);
			}
		
		
		p_panel1.setLayout(new BorderLayout());
		p_panel1.add(b_exit, BorderLayout.EAST);
		
		this.add(p_panel, BorderLayout.CENTER);
		this.add(p_panel1, BorderLayout.SOUTH);
		
		p_panel.setBackground(Color.black);
		p_panel1.setBackground(Color.black);
		
		b_exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(SolicitorConfig.success)
				{					
					SolicitorConfig.regenerator = false;
					SolicitorConfig.decipherer = false;
					SolicitorConfig.reader = false;
					
					SolicitorConfig.success = false;
					
					SolicitorMenu solicitorMenu = new SolicitorMenu();
					solicitorMenu.setLocationRelativeTo(null);
					solicitorMenu.setResizable(false);
					solicitorMenu.setVisible(true);
					f_solicitorNotification.dispose();
				}
				else
				{
				
					if(SolicitorConfig.regenerator)
					{
						if(SolicitorConfig.wrong_form)
						{
							SolicitorConfig.clear = true;
						}
							new Regenerator();
							f_solicitorNotification.dispose();
					}
					
					if(SolicitorConfig.decipherer)
					{
						Decipherer decipherer = new Decipherer();
						decipherer.pack();
						f_solicitorNotification.dispose();
					}
					
					if(SolicitorConfig.reader)
					{
						Reader reader = new Reader();
						reader.pack();
						f_solicitorNotification.dispose();
					}
					
				}
				
			}
			
		});
	}
}

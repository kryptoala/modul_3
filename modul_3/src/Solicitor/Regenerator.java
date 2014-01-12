package Solicitor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/**
 * Klasa odpowiadajaca za modul sluzacy do odtwarzania sekretu z czesci.
 *
 */
public class Regenerator extends JFrame{
	
	final Regenerator f_regenerator = this;
	
	private JPanel 
	p_panel1 = new JPanel(), // panel do labeli
	p_panel2 = new JPanel(), // ogolny panel do przyciskow
	p_panel3 = new JPanel(), // panel do przyciskow
	p_panel4 = new JPanel(); // panel do TextFielda
	
	private JLabel 
	l_label = new JLabel(), // label gorny
	l_label1 = new JLabel(); // napis 
	
	private JButton
	b_next = new JButton(), // weryfikuj
	b_back = new JButton(),
	b_exit = new JButton();
	
	private JTextArea
	t_textArea = new JTextArea(1,1);//wpisywanie klucza zaszyfrowanej wiadomosci
	
	private JScrollPane s_scrollPane = new JScrollPane(t_textArea);
	

	
	public Regenerator(){
		
		super("Odtwarzanie sekretu");
		

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setSize(SolicitorConfig.RegeneratorWindowSize[0], SolicitorConfig.RegeneratorWindowSize[1]);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		
		try {
			
				
			
				
			Image img1 = ImageIO.read(getClass().getResource(SolicitorConfig.RegeneratorButtons[0]));
			   l_label.setIcon(new ImageIcon(img1));	
			if(SolicitorConfig.last)
			   {
				Image img2 = ImageIO.read(getClass().getResource(SolicitorConfig.RegeneratorButtons[1]));
		    	l_label1.setIcon(new ImageIcon(img2));
			   }
			else
			   {
				   Image img2 = ImageIO.read(getClass().getResource(SolicitorConfig.RegeneratorButtons[5]));
			    	l_label1.setIcon(new ImageIcon(img2));
			   }
		    Image img3 = ImageIO.read(getClass().getResource(SolicitorConfig.RegeneratorButtons[2]));
				b_next.setIcon(new ImageIcon(img3));
		    Image img4 = ImageIO.read(getClass().getResource(SolicitorConfig.RegeneratorButtons[3]));
				b_back.setIcon(new ImageIcon(img4));
			Image img5 = ImageIO.read(getClass().getResource(SolicitorConfig.RegeneratorButtons[4]));
				b_exit.setIcon(new ImageIcon(img5));
		     	
		  } catch (IOException ex) {
			  
		  }
		
		if(SolicitorConfig.clear)
			{	
			SolicitorConfig.last = false;
			SolicitorConfig.first = true;
			SolicitorConfig.needed_parts = 100;
			SolicitorConfig.current_part = 1; // ile razy wpisywano
			SolicitorConfig.lagranged = false; // czy rozwielomianowanie sie udalo
			SolicitorConfig.wrong_form = false; // czy wpisywane rzeczy maja dobra postac
			SolicitorConfig.clear = false;
			SolicitorConfig.SharersList.clear();
			}
	
			
		b_next.setBorder(new EmptyBorder(0,0,0,0));
		b_back.setBorder(new EmptyBorder(0,0,0,0));
		b_exit.setBorder(new EmptyBorder(0,0,0,0));
		l_label.setBorder(new EmptyBorder(0,0,0,0));
		l_label1.setBorder(new EmptyBorder(0,0,0,0));
		
		p_panel1.setLayout(new GridLayout(2,1));
		p_panel1.add(l_label);
		p_panel1.add(l_label1);
	

		p_panel2.setLayout(new BorderLayout());
		p_panel2.add(p_panel3, BorderLayout.EAST);
		
		p_panel3.setLayout(new BorderLayout());
		p_panel3.add(b_next,BorderLayout.NORTH);
		p_panel3.add(b_back,BorderLayout.CENTER);
		p_panel3.add(b_exit,BorderLayout.SOUTH);
		
        JPanel temp_panel1 = new JPanel();
        JLabel temp_label1 = new JLabel("xxx");
        JLabel temp_label2 = new JLabel("xxx");
        
        temp_label1.setForeground(Color.black);
        temp_label2.setForeground(Color.black);
        
        // t_textArea.setPreferredSize(new Dimension(25,20));
 
        temp_panel1.setLayout(new BorderLayout());
        temp_panel1.setBackground(Color.black);
        
        temp_panel1.add(temp_label1, BorderLayout.WEST);
        temp_panel1.add(s_scrollPane, BorderLayout.CENTER);
        temp_panel1.add(temp_label2, BorderLayout.EAST);
        
        
        p_panel4.setLayout(new GridLayout());
        p_panel4.add(temp_panel1);
        t_textArea.setLineWrap(true);
        this.add(p_panel1,BorderLayout.NORTH);
        this.add(p_panel4, BorderLayout.CENTER);
        this.add(p_panel2, BorderLayout.PAGE_END);
        
        p_panel1.setBackground(Color.black);
        p_panel2.setBackground(Color.black);
        p_panel3.setBackground(Color.black);
        p_panel4.setBackground(Color.black); 	

		/*	next button */
		b_next.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(SolicitorConfig.first)
				{
					neededParts();
					if(!SolicitorConfig.wrong_form)
					{
					SolicitorConfig.first = false;
					}
				}
				
				if(!SolicitorConfig.wrong_form)
					{
	
				
					if(SolicitorConfig.last || SolicitorConfig.needed_parts == 0)
						{
							currentSharer();
							if(!SolicitorConfig.wrong_form)
							{
								Lagrange1();
									if(SolicitorConfig.lagranged)
									{
										SolicitorConfig.success = true;	
										SolicitorConfig.current_part = 1;
										SolicitorConfig.needed_parts = 100;
										SolicitorConfig.lagranged = false;
										new SolicitorNotification();
										f_regenerator.dispose();
									}
								else
									{
										new SolicitorNotification();
										f_regenerator.dispose();
									}
							}
							else
							{
								System.out.println("dupa");
								new SolicitorNotification();
								f_regenerator.dispose();
							}
						}
					else
						{
						
							if(SolicitorConfig.current_part == SolicitorConfig.needed_parts)
								{
									SolicitorConfig.last = true;
								}
							
							currentSharer();
							if(!SolicitorConfig.wrong_form)
							{
								SolicitorConfig.current_part++;
								f_regenerator.dispose();
								new Regenerator();	
							}
							else
							{
								System.out.println("dupa");
								new SolicitorNotification();
								f_regenerator.dispose();
							}
						}
					}
				else
				{
					System.out.println("dupa");
					new SolicitorNotification();
					f_regenerator.dispose();
					
				}
				
			}
			
		});
		
		/*	back button */
		b_back.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				SolicitorConfig.regenerator = false;
				new SolicitorMenu();
				f_regenerator.dispose();
			}
			
		});
		
		/*		EXIT	*/	
		
		b_exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				SolicitorConfig.regenerator=false;
				System.exit(0);
			}
			
		});
	}
	
	  void neededParts(){
			// oddzielanie liczby potrzebnych czesci
		  try{
			 String s = t_textArea.getText();
			 String[] s_break = s.split("number");
			 SolicitorConfig.needed_parts = new Integer(s_break[0]);
			 SolicitorConfig.needed_parts = SolicitorConfig.needed_parts -1;
	  		}
	  		  catch (NumberFormatException Argument)
			  	{
				  SolicitorConfig.wrong_form = true;
				  System.out.println("NumberFormatex1");
				}
			  catch (ArrayIndexOutOfBoundsException Argument)
			  	{
				  SolicitorConfig.wrong_form = true;
				  System.out.println("ArrayIndex1");
				}
	  }
	  
	  void currentSharer(){
			
		  try{
			 String s = t_textArea.getText();
			 String[] s_break = s.split("number");
			 String message = s_break[1].toString();
			 
			//odzielanie numeru obecnego wpisywacza
			 String[] s_break1 = message.split("current");
			 SolicitorConfig.Shares.add(new Integer(s_break1[0]));	
			 String[] s_break3 = s_break1[1].toString().split("prime");
			 SolicitorConfig.Prime = new BigInteger(s_break3[0],16).toString();
			 
			String[] s_break2 = s_break3[1].toString().split(" ");
			for (int i = 0; i<s_break2.length; i++)
			{
			s_break2[i] = new BigInteger(s_break2[i],16).toString();
			}
			
			// zapisywanie liczb do wektora
			
			SolicitorConfig.SharersList.add(SolicitorConfig.current_part-1, s_break2);
			System.out.println("Sharers list size = " + SolicitorConfig.SharersList.size());
		  } 
		  catch (NumberFormatException Argument)
		  	{
			  SolicitorConfig.wrong_form = true;
			  System.out.println("NumberFormatex2");
			}
		  catch (ArrayIndexOutOfBoundsException Argument)
		  	{
			  SolicitorConfig.wrong_form = true;
			  System.out.println("ArrayIndex2");
			}
		  
			/*
			for (int i = 0; i < s_break2.length; i++)
			{
				System.out.println("dupa + "+i);
				
				 SolicitorConfig.SharersList.get(SolicitorConfig.current_part-1)[i] = s_break2[i];
				
			}
			*/
		}
	  
	  
void Lagrange1(){
		  
		  BigInteger[] c = new BigInteger[SolicitorConfig.SharersList.get(0).length];
		  BigInteger prime = new BigInteger(SolicitorConfig.Prime);
		  
		  // wyliczam jedynie SKLADOWA STALA, czyli:
		  // c = c0 + c1 + ... cn, gdzie:
		  // cn = yn * (x0[n] * x1[n] * x2 *... *xn) / xn *((xn - x1)*(xn - x2)*...* (xn-xn))  -> ta koncowka to wiadomo o co chodzi
		  
		  // obliczanie cn
		  
		  
		  // x1*x2*x3...
		  BigInteger[] iloczyn = new BigInteger[SolicitorConfig.Shares.size()];
		  
		  for(int i = 0; i < SolicitorConfig.Shares.size(); i++)
		  {

			  iloczyn[i]=BigInteger.valueOf(1);
			
			  for (int j = 0; j<SolicitorConfig.Shares.size(); j++)
			  {
				  if (j!=i)
				  {
					  //x1*x2*x3...
					  iloczyn[i] = iloczyn[i].multiply(BigInteger.valueOf(SolicitorConfig.Shares.get(j)));
					  //System.out.println("iloczynwwww[" +i + "] =" + iloczyn[i] );	
					  //System.out.println("SolicitorConfig.Shares.get(" +j + ") =" + SolicitorConfig.Shares.get(j).toString() );	
					  
				  }
				  
			
			  }
		  }
		  
		  // mianownik

		 BigInteger[] mianownik = new BigInteger[SolicitorConfig.Shares.size()];
		  
		  for (int i = 0; i< mianownik.length; i++)
		  {
			  mianownik[i] = BigInteger.valueOf(1);
			  for (int j = 0; j<SolicitorConfig.Shares.size(); j++)
			  {
				  if (j!=i)
				  {
					  //x2-x1 * x2 - x3 * x2-x4...
					  System.out.println(" i = " + i + " j = " + j );
					  System.out.println(" wartosc(i) = " + SolicitorConfig.Shares.get(i) + " wartosc(j) = " + SolicitorConfig.Shares.get(j) + " roznica =" + (SolicitorConfig.Shares.get(i) - SolicitorConfig.Shares.get(j)));
					  BigInteger k = modul_odwrotnosci_BigInteger(BigInteger.valueOf((SolicitorConfig.Shares.get(j) - SolicitorConfig.Shares.get(i))));
					  System.out.println(" odwrotnosc = " + k);
					  mianownik[i] = mianownik[i].multiply(k);
					  System.out.println(" mianownik[" +i+"] = " + mianownik[i]);
				  }
			
			  }
		  }
		  
		  
		  // c0
		  for (int i = 0; i < SolicitorConfig.SharersList.get(0).length; i++)
		  {
			  c[i]=BigInteger.valueOf(0);
			  for (int j = 0; j < SolicitorConfig.SharersList.size(); j++ )
			  {
				  // c0 + c1 + c2
				  
				 
				BigInteger b=new BigInteger(SolicitorConfig.SharersList.get(j)[i]);
				System.out.println(" b = " +b);
				System.out.println("licznik[" + j + "] =" + iloczyn[j] );	
				System.out.println("mianownik[" + j + "] =" + mianownik[j] );	
			// c[i] = c[i].add(b.multiply(BigDecimal.valueOf(iloczyn[j])).divide(BigDecimal.valueOf(mianownik[j]), 3, RoundingMode.UP));
			 //c[i] = c[i].add(b.multiply(BigDecimal.valueOf(iloczyn[j])).multiply(BigDecimal.valueOf(modul_odwrotnosci(mianownik[j]))));
				 c[i] = c[i].add(b.multiply(iloczyn[j]).multiply(mianownik[j]));
				// System.out.println("c[" + i + "] =" + c[i] );	
			  }
			  
		  }

		  
		  
		  for (int i = 0; i < c.length; i++)
		  {
			  // jezeli c[i] po zmodulowaniu jest wieksze niz prime/20
			  c[i] = c[i].mod(prime);
			/*
			  switch(c[i].toBigInteger().compareTo(prime.divide(new BigInteger("2"))))
			  {
			  case 1: c[i] = new BigDecimal(c[i].toBigInteger().subtract(prime));
			  }
			*/
			
		  }
		  
		  write_file_BI(c, SolicitorConfig.CipheredPath);
		  
		  SolicitorConfig.lagranged = true;
	  }

	  /**
	   * ?!
	   * @param liczba
	   * ?!
	   * @return
	   * ?!
	   */
	  public BigInteger modul_odwrotnosci_BigInteger(BigInteger liczba)
	  {
		  BigInteger a,u,w,x,q, z;
		  a=liczba;
		  BigInteger b=new BigInteger(SolicitorConfig.Prime);
		  
		   u = BigInteger.valueOf(1);
		   if(a.compareTo(new BigInteger("0"))==-1) w = a.multiply(BigInteger.valueOf(-1));
		   else w=a;
		   x = BigInteger.valueOf(0); z = b;
		   while(w.compareTo(BigInteger.valueOf(0)) == 1)
		   {
		     if(w.compareTo(z)==-1)
		     {
		       q = u; u = x; x = q;
		       q = w; w = z; z = q;
		     }
		     q = w.divide(z);
		     u = u.subtract(q.multiply(x));
		     w = w.subtract(q.multiply(z));
		   }
		 //  if(z == 1)
		 //  {
		     if(x.compareTo(BigInteger.valueOf(0)) == -1) x = x.add(b);
		 	if(a.compareTo(BigInteger.valueOf(0))==-1) x=b.subtract(x);
		 	 return x;
		   //}
		  
	  }
	  
	  /**
	   * ?!
	   * @param BI
	   * ?!
	   * @param filepath
	   * ?!
	   */
	  public void write_file_BI(BigInteger[] BI, String filepath)
	  {
		
		  DataOutputStream outWriter = null;
		  
			try {
				outWriter = new DataOutputStream(new FileOutputStream(new File(filepath)));	
				String s = "";
				 for (int i = 0; i < BI.length-1; i++) {
					 s = s + BI[i].toString()+ " ";
					  outWriter.writeBytes(BI[i].toString(16)+ " "); 
				  	}
				 s = s + BI[BI.length-1].toString(16);
				 String s1 = BI[BI.length-1].toString(16);
				 
				 for(int i = 0; i<s.length()%16; i++)
				 {
					 s1= s1 + "\0";
					 
				 }
				 outWriter.writeBytes(s1);
				  outWriter.flush();  
					 outWriter.close(); 
				 
			} catch (IOException Argument) {
				// TODO Auto-generated catch block
			}

		  
	  }
}

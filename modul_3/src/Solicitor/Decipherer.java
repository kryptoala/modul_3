package Solicitor;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/**
 * Klasa odpowiadajaca za modul odszyfrowywania.
 *
 */
public class Decipherer extends JFrame {

	final Decipherer f_decipherer = this;

	private JPanel p_panel1 = new JPanel(), // panel do labeli
			p_panel2 = new JPanel(), // ogolny panel do przyciskow
			p_panel3 = new JPanel(), // panel do przyciskow
			p_panel4 = new JPanel(); // panel do TextFielda

	private JLabel l_label = new JLabel(), // label gorny
			l_label1 = new JLabel(); // napis

	private JButton b_decipher = new JButton(), // odszyfruj
			b_back = new JButton(), b_exit = new JButton();

	private JTextArea t_textArea = new JTextArea(1, 1);// wpisywanie klucza AES

	static String decrypted = new String(); // odkodowywanie
	static String IV = "LoveMyLittlePony";
	  static String added = "7526841457218985643178021036504875960236520145541012518565148898747412366985630891287524586247522671102877002546010257870486522450211982";
	  
	public Decipherer() {

		super("Odszyfrowywanie testamentu");

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setSize(SolicitorConfig.DeciphererWindowSize[0],
				SolicitorConfig.DeciphererWindowSize[1]);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);

		try {

			Image img1 = ImageIO.read(getClass().getResource(
					SolicitorConfig.DeciphererButtons[0]));
			l_label.setIcon(new ImageIcon(img1));
			Image img2 = ImageIO.read(getClass().getResource(
					SolicitorConfig.DeciphererButtons[1]));
			l_label1.setIcon(new ImageIcon(img2));

			Image img3 = ImageIO.read(getClass().getResource(
					SolicitorConfig.DeciphererButtons[2]));
			b_decipher.setIcon(new ImageIcon(img3));
			Image img4 = ImageIO.read(getClass().getResource(
					SolicitorConfig.DeciphererButtons[3]));
			b_back.setIcon(new ImageIcon(img4));
			Image img5 = ImageIO.read(getClass().getResource(
					SolicitorConfig.DeciphererButtons[4]));
			b_exit.setIcon(new ImageIcon(img5));

		} catch (IOException ex) {

		}

		b_decipher.setBorder(new EmptyBorder(0, 0, 0, 0));
		b_back.setBorder(new EmptyBorder(0, 0, 0, 0));
		b_exit.setBorder(new EmptyBorder(0, 0, 0, 0));
		l_label.setBorder(new EmptyBorder(0, 0, 0, 0));
		l_label1.setBorder(new EmptyBorder(0, 0, 0, 0));

		p_panel1.setLayout(new GridLayout(2, 1));
		p_panel1.add(l_label);
		p_panel1.add(l_label1);

		p_panel2.setLayout(new BorderLayout());
		p_panel2.add(p_panel3, BorderLayout.EAST);

		p_panel3.setLayout(new BorderLayout());
		p_panel3.add(b_decipher, BorderLayout.NORTH);
		p_panel3.add(b_back, BorderLayout.CENTER);
		p_panel3.add(b_exit, BorderLayout.SOUTH);

        JPanel temp_panel1 = new JPanel();
        JLabel temp_label1 = new JLabel("xxx");
        JLabel temp_label2 = new JLabel("xxx");
        
        temp_label1.setForeground(Color.black);
        temp_label2.setForeground(Color.black);
        
        t_textArea.setPreferredSize(new Dimension(25,20));
 
        temp_panel1.setLayout(new BorderLayout());
        temp_panel1.setBackground(Color.black);
        
        temp_panel1.add(temp_label1, BorderLayout.WEST);
        temp_panel1.add(t_textArea, BorderLayout.CENTER);
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

		/* share button */
		b_decipher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Decipher();

				if (SolicitorConfig.deciphered) {
					SolicitorConfig.success = true;
				}

				new SolicitorNotification();

				f_decipherer.dispose();

			}

		});

		/* back button */
		b_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SolicitorConfig.decipherer = false;
				new SolicitorMenu();
				f_decipherer.dispose();
			}

		});

		/* EXIT */

		b_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SolicitorConfig.decipherer = false;
				System.exit(0);
			}

		});

	}

	/**
	 * Metoda odpowiadajaca za procedure odszyfrowywania.
	 */
	public void Decipher()
		{

		      decryptance(t_textArea.getText().toString(), SolicitorConfig.CipheredPath);
		      System.out.println("decrypted text: " + decrypted);


				decrypted = decrypted.replaceAll("\u0000.*", "");
				// decrypted = decrypted.replaceAll("\n", "");
				
				byte[] b = 	decrypted.getBytes();
				System.out.println("decrypted text: " + decrypted);
				System.out.println("decrypted text: " + decrypted);
		     FileWriter decipher1;
			try {
				

				decipher1 = new FileWriter(SolicitorConfig.DecipheredPath);
			     decipher1.write(decrypted);
        		 decrypted = "";
			     decipher1.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}	

		}

	void decryptance(String encryptionKey, String filepath) {
		try {
			String s_line = "";

			FileReader fr_file = new FileReader(filepath);
			BufferedReader brBuffer = new BufferedReader(fr_file);

			try {
				while ((s_line = brBuffer.readLine()) != null) {

					String[] s_break = s_line.split(" ");
					byte[] decipher = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0,
							0, 0, 0, 0, 0, 0, 0 };

					int s = 0;
					byte b;
					try {
						for (int g = 0; g < s_break.length / 16; g++) {
							for (int a = 0; a < 16; a++) {
								s = (g * 16) + a;
								
								s_break[s] = s_break[s].replaceAll("\u0000.*", "");
								String[] temp = s_break[s].split("\0");
								//b = new Byte(""
								//		+ (Integer.parseInt(temp[0]) - 250));
								
								BigInteger temporary_BI = new BigInteger("" +temp[0],16);
								BigDecimal temporary_BD = new BigDecimal(temporary_BI).divide(new BigDecimal(added), RoundingMode.HALF_DOWN);
				        		 b = new Byte("" +(temporary_BD.subtract(new BigDecimal("250"))));
								decipher[a] = b;
							}
							decrypted = decrypted
									+ decrypt(decipher, encryptionKey);
							SolicitorConfig.deciphered = true;
						}
					} catch (Exception e) {
						System.out.println(e);
						// TODO Auto-generated catch block

					}

				}

			} catch (IOException Argument) {
				System.out.println("blad");
			}
		} catch (FileNotFoundException Argument) {
			System.out.println("blad: nie znaleziono pliku");
		}
	}

	/**
	 * Metoda odszyfrowujaca.
	 * @param cipherText
	 * Tablica danych binarnych stanowiacych zaszyfrowany tekst.
	 * @param encryptionKey
	 * Lancuch znakowy stanowiacy klucz potrzebny do odszyfrowania tresci testamentu.
	 * @return
	 * Metoda zwraca odszyfrowany tekst.
	 * @throws Exception
	 */
	public static String decrypt(byte[] cipherText, String encryptionKey)
			throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
		SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"),
				"AES");
		cipher.init(Cipher.DECRYPT_MODE, key,
				new IvParameterSpec(IV.getBytes("UTF-8")));
		return new String(cipher.doFinal(cipherText), "UTF-8");
	}
}

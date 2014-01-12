package Solicitor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Klasa odpowiadajaca za modul sluzacy do odczytywania tresci testamentu i weryfikacji jego autentycznosci.
 *
 */
public class Reader extends JFrame {
	final Reader f_reader = this;
	
	private JPanel 
	p_panel1 = new JPanel(), // panel do labeli
	p_panel2 = new JPanel(), // ogolny panel do przyciskow
	p_panel3 = new JPanel(); // panel do przyciskow
	//p_panel4 = new JPanel(); // panel do TextFielda
	
	private JLabel 
	l_label = new JLabel(), // label gorny
	l_label1 = new JLabel(); // napis 
	
	private JButton
	b_verify = new JButton(), // weryfikuj
	b_back = new JButton(),
	b_exit = new JButton();
	
	//private JTextArea
	//t_textArea = new JTextArea(1,1);
	
	public Reader(){
		
		super("Odczytywanie testamentu");
		

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setSize(SolicitorConfig.ReaderWindowSize[0], SolicitorConfig.ReaderWindowSize[1]);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		
		try {
			
				
			
				
			Image img1 = ImageIO.read(getClass().getResource(SolicitorConfig.ReaderButtons[0]));
			   l_label.setIcon(new ImageIcon(img1));	
		    Image img2 = ImageIO.read(getClass().getResource(SolicitorConfig.ReaderButtons[1]));
		    	l_label1.setIcon(new ImageIcon(img2));
		    	
		    Image img3 = ImageIO.read(getClass().getResource(SolicitorConfig.ReaderButtons[2]));
				b_verify.setIcon(new ImageIcon(img3));
		    Image img4 = ImageIO.read(getClass().getResource(SolicitorConfig.ReaderButtons[3]));
				b_back.setIcon(new ImageIcon(img4));
			Image img5 = ImageIO.read(getClass().getResource(SolicitorConfig.ReaderButtons[4]));
				b_exit.setIcon(new ImageIcon(img5));
		     	
		  } catch (IOException ex) {
			  
		  }
		
		
		b_verify.setBorder(new EmptyBorder(0,0,0,0));
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
		p_panel3.add(b_verify,BorderLayout.NORTH);
		p_panel3.add(b_back,BorderLayout.CENTER);
		p_panel3.add(b_exit,BorderLayout.SOUTH);
		
		//p_panel4.setLayout(new GridLayout());
		//p_panel4.add(t_textArea);
		//t_textArea.setLineWrap(true);
		
		this.add(p_panel1,BorderLayout.NORTH);
		//this.add(p_panel4, BorderLayout.CENTER);
		this.add(p_panel2, BorderLayout.PAGE_END);
		
		p_panel1.setBackground(Color.black);
		p_panel2.setBackground(Color.black);
		p_panel3.setBackground(Color.black);
		//p_panel4.setBackground(Color.black);
	
		/*	share button */
		b_verify.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Verify();
				
				if(SolicitorConfig.read)
				{
					SolicitorConfig.success = true;
				}
				
				new SolicitorNotification();
				
				f_reader.dispose();
	
			}
			
		});
		
		/*	back button */
		b_back.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				SolicitorConfig.reader = false;
				new SolicitorMenu();
				f_reader.dispose();
			}
			
		});
		
		/*		EXIT	*/	
		
		b_exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				SolicitorConfig.reader=false;
				System.exit(0);
			}
			
		});
		
	}
	/*
    private static boolean verify( String message, String sign, PublicKey publicKey )
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, UnsupportedEncodingException, NoSuchProviderException
        {
            final Signature sig = Signature.getInstance( "DSA", "SUN" );
            sig.initVerify( publicKey );
            sig.update( message.getBytes( "UTF-8" ) );

            final byte[] bytes = Base64Decoder.decode( URLDecoder.decode( sign, "UTF-8") );

            return sig.verify( bytes );
        }
        */
 /*   
    private static PublicKey getKey( String keyPath )
            throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, NoSuchProviderException
        {
            final KeyFactory keyFactory = KeyFactory.getInstance( "DSA","SUN" );
           // InputStream inputStream = new FileInputStream(keyPath);
           // java.io.Reader      rr      = new InputStreamReader(inputStream, "UTF-8");
            FileReader fr_file = new FileReader(keyPath);
		     BufferedReader brBuffer = new BufferedReader(fr_file);
            final PemReader reader = new PemReader(brBuffer);
            Object pemObj = reader.readPemObject();
            final byte[] pubKey = reader.readPemObject(  ).getContent(  );
            reader.close();
            final X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec( pubKey );

            return keyFactory.generatePublic( publicKeySpec );
        }
*/


	/**
	 * Metoda odpowiadajaca za procedure weryfikowania autentycznosci testamentu.
	 */
	@SuppressWarnings("resource")
	public void Verify(){
		
		   try {
			   Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

			    FileInputStream fis = new FileInputStream(SolicitorConfig.DecipheredPath);
		        BufferedInputStream bufin = new BufferedInputStream(fis);
		        byte[] buffer = new byte[1024];
		        while (bufin.available() != 0) 
		        {
		          bufin.read(buffer);
		        }
		        
			   
			    boolean verified;

			    //wczytywanie klucza publicznego
		     	FileInputStream keyfis = new FileInputStream(SolicitorConfig.Public_key);
		     	StringBuilder builder = new StringBuilder();
		     	int ch;
		     	while((ch = keyfis.read()) != -1){
		     	    builder.append((char)ch);
		     	}
		     	BigInteger public_key1 = new BigInteger(builder.toString());
		        byte[] encKey = public_key1.toByteArray();
		        keyfis.read(encKey);
		        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encKey);

		        KeyFactory keyFactory = KeyFactory.getInstance("DSA");
		        PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);
		        
		        
		        //wczytywanie podpisu
		        FileInputStream keyfis1 = new FileInputStream(SolicitorConfig.Sign);
		     	StringBuilder builder1 = new StringBuilder();
		     	int ch1;
		     	while((ch1 = keyfis1.read()) != -1){
		     	    builder1.append((char)ch1);
		     	}
		     	BigInteger sign = new BigInteger(builder1.toString());
		        byte[] encKey1 = sign.toByteArray();
		        keyfis1.read(encKey1);
		        
		        //weryfikacja testamentu
			    verified = verifySig(buffer, pubKey, encKey1);

		        System.out.println("signature verifies: " + verified);
		        SolicitorConfig.read = verified;
		      } catch (Exception e) {
		        System.err.println("Caught exception " + e.toString());
		      }
	}
	
	/**
	 * Metoda sluzaca do przeprowadzania weryfikacji autentycznosci tresci testamentu.
	 * @param data
	 * Tablica danych binarnych stanowiacych tresc, ktorej autentycznosc ma zostac zweryfikowana.
	 * @param key
	 * Klucz publiczny osoby spisujacej testament.
	 * @param sig
	 * Tablica danych binarnych stanowiacych podpis.
	 * @return
	 * Metoda zwraca wynik weryfikacji.
	 * @throws Exception
	 */
	 public static boolean verifySig(byte[] data, PublicKey key, byte[] sig) throws Exception {
		    Signature signer = Signature.getInstance("SHA1withDSA");
		    signer.initVerify(key);
		    signer.update(data);
		    return (signer.verify(sig));

		  }
}

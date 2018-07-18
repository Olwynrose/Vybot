import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;



public class PanOptions 
{
	JButton bLangue;
	JButton bEtalon;
	
	JButton bRetour;
	JButton bValider;
	JButton bSecret;
	
	int langue;
	int etalonnage;
	int secret;
	int clic;
	
	JTextArea tLangue;
	JTextArea tEtalon;
	JTextArea tSecret;
	int cond;
	
	public PanOptions() throws InterruptedException, AWTException, IOException
	{
		int buf_etalon = 0;
		cond = 0;
		langue = Main.donnees.langue;			// 0: Francais, 1: Anglais
		etalonnage = Main.donnees.etalonnage;		// 0:Fiesta, 1: autre jeu
		secret = Main.donnees.secret;			// 0: indétectable, 1: précis
		clic = 0;
		Main.pan.removeAll();
		Main.pan.setLayout(null);
		
		
		// interface
		int h0 = 100;
		int h_bout = 50;
		int i_bout = 40;
		int l_bout = 200;
		
		int h_text = 40;
		int i_text = 30;
		
		tLangue = new JTextArea();
		if(langue==0)
		{
			tLangue.setText("Langue");
		}
		else
		{
			tLangue.setText("Language");
		}
		
		tLangue.setOpaque(false);
		tLangue.setFont(new Font("Consolas", Font.BOLD, 22));
		tLangue.setForeground(Color.WHITE);
		tLangue.setBounds((Main.jdim-l_bout)/2-l_bout, h0, l_bout, i_text);
		Main.pan.add(tLangue);
		
		bLangue = new JButton(Main.iBouton);
		bLangue.setBackground(Color.BLUE);
		if(langue==0)
		{
			bLangue.setText("Français");
		}
		else
		{
			bLangue.setText("English");
		}
        bLangue.setVerticalTextPosition(SwingConstants.CENTER);
        bLangue.setHorizontalTextPosition(SwingConstants.CENTER);
		bLangue.setBounds((Main.jdim-l_bout)/2, h0+h_text, l_bout, i_bout);
		bLangue.setForeground(Color.BLACK);
		Main.pan.add(bLangue);
		bLangue.addActionListener(new AFrancais());
		
		tEtalon = new JTextArea();
		if(langue==0)
		{
			tEtalon.setText("Etalonnage");
		}
		else
		{
			tEtalon.setText("Etalonnage");
		}
		tEtalon.setOpaque(false);
		tEtalon.setFont(new Font("Consolas", Font.BOLD, 22));
		tEtalon.setForeground(Color.WHITE);
		tEtalon.setBounds((Main.jdim-l_bout)/2-l_bout, h0+h_text+h_bout, l_bout, i_text);
		Main.pan.add(tEtalon);
		
		bEtalon = new JButton(Main.iBouton);
		bEtalon.setBackground(Color.BLUE);
		if(etalonnage==0)
		{
			if(langue==0)
			{
				bEtalon.setText("Fiesta online");
			}
			else
			{
				bEtalon.setText("Fiesta online");
			}
		}
		else
		{
			if(langue==0)
			{
				bEtalon.setText("Autre jeux");
			}
			else
			{
				bEtalon.setText("Other games");
			}
		}
        bEtalon.setVerticalTextPosition(SwingConstants.CENTER);
        bEtalon.setHorizontalTextPosition(SwingConstants.CENTER);
		bEtalon.setBounds((Main.jdim-l_bout)/2, h0+2*h_text+h_bout, l_bout, i_bout);
		bEtalon.setForeground(Color.BLACK);
		Main.pan.add(bEtalon);
		bEtalon.addActionListener(new AEtalon());
       
		tSecret = new JTextArea();
		if(langue==0)
		{
			tSecret.setText("Type de bot");
		}
		else
		{
			tSecret.setText("Bot's type");
		}
		tSecret.setOpaque(false);
		tSecret.setFont(new Font("Consolas", Font.BOLD, 22));
		tSecret.setForeground(Color.WHITE);
		tSecret.setBounds((Main.jdim-l_bout)/2-l_bout, h0+2*h_text+2*h_bout, l_bout, i_text);
		Main.pan.add(tSecret);
		
		bSecret = new JButton(Main.iBouton);
		bSecret.setBackground(Color.BLUE);
		if(secret==0)
		{
			if(langue==0)
			{
				bSecret.setText("Indétectable");
			}
			else
			{
				bSecret.setText("Undetectable");
			}
		}
		else
		{
			if(langue==0)
			{
				bSecret.setText("Précis");
			}
			else
			{
				bSecret.setText("Precise");
			}
		}
        bSecret.setVerticalTextPosition(SwingConstants.CENTER);
        bSecret.setHorizontalTextPosition(SwingConstants.CENTER);
		bSecret.setBounds((Main.jdim-l_bout)/2, h0+3*h_text+2*h_bout, l_bout, i_bout);
		bSecret.setForeground(Color.BLACK);
		Main.pan.add(bSecret);
		bSecret.addActionListener(new ASecret());
		
		bRetour = new JButton(Main.iBouton);
		if(langue==0)
		{
			bRetour.setText("Retour");
		}
		else
		{
			bRetour.setText("Back");
		}
		bRetour.setBackground(Color.BLUE);
		bRetour.setVerticalTextPosition(SwingConstants.CENTER);
		bRetour.setHorizontalTextPosition(SwingConstants.CENTER);
		bRetour.setBounds(Main.jdim/2-200, Main.idim - 60, 130, 25);
		bRetour.addActionListener(new ARetour());
		Main.pan.add(bRetour);
				
		bValider = new JButton(Main.iBouton);
		if(langue==0)
		{
			bValider.setText("Valider");
		}
		else
		{
			bValider.setText("Confirm");
		}
		bValider.setBackground(Color.BLUE);
		bValider.setVerticalTextPosition(SwingConstants.CENTER);
		bValider.setHorizontalTextPosition(SwingConstants.CENTER);
		bValider.setBounds(Main.jdim/2+70, Main.idim - 60, 130, 25);
		bValider.addActionListener(new AValider());
		Main.pan.add(bValider);
				
		Main.fondPanel.setBounds(-1, -15, Main.jdim, Main.idim);
		Main.pan.add(Main.fondPanel);
		
		Main.pan.repaint(); 
		Main.pan.validate();
				    
		while(cond == 0)
		{
			Thread.sleep(200);
			if(clic == 1)
			{
				if(langue==0)
				{
					tLangue.setText("Langue");
				}
				else
				{
					tLangue.setText("Language");
				}
				if(langue==0)
				{
					bLangue.setText("Français");
				}
				else
				{
					bLangue.setText("English");
				}
				if(etalonnage==0)
				{
					if(langue==0)
					{
						bEtalon.setText("Fiesta online");
					}
					else
					{
						bEtalon.setText("Fiesta online");
					}
				}
				else
				{
					if(langue==0)
					{
						bEtalon.setText("Autre jeux");
					}
					else
					{
						bEtalon.setText("Other games");
					}
				}
				if(langue==0)
				{
					tSecret.setText("Type de bot");
				}
				else
				{
					tSecret.setText("Bot's type");
				}
				if(secret==0)
				{
					if(langue==0)
					{
						bSecret.setText("Indétectable");
					}
					else
					{
						bSecret.setText("Undetectable");
					}
				}
				else
				{
					if(langue==0)
					{
						bSecret.setText("Précis");
					}
					else
					{
						bSecret.setText("Precise");
					}
				}
				if(langue==0)
				{
					bValider.setText("Valider");
				}
				else
				{
					bValider.setText("Confirm");
				}
				if(langue==0)
				{
					bRetour.setText("Retour");
				}
				else
				{
					bRetour.setText("Back");
				}
				Main.pan.repaint(); 
				Main.pan.validate();
				clic = 0;
			}// if clic == 1
		}// while cond
		
		if(cond==2)
		{
			if(etalonnage==1)
			{
				// reference Fiesta 1280*768 sur écran 1600*900
				Main.references.idimVie = 64;
				Main.references.jdimVie = 190;
				Main.references.idimChat = 675;
				Main.references.jdimChat = 38;
				Main.references.idimCarte = 58;
				Main.references.jdimCarte = 1114;
				Main.references.idimSelec = 30;
				Main.references.jdimSelec = 597;
			}
			else
			{
				if(buf_etalon==1)
				{
					Main.onglet = 9;
				}
			}
			Main.donnees.langue = langue;
			Main.donnees.etalonnage = etalonnage;
			Main.donnees.secret = secret;
			Main.donnees.sauvegardeO();
			
		}
		
	}
	public class AFrancais implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			langue = (langue + 1) % 2;
			clic = 1;
		}
	}
	
	public class AEtalon implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			etalonnage = (etalonnage + 1) % 2;
			clic = 1;
		}
	}
	
	public class ASecret implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			secret = (secret + 1) % 2;
			clic = 1;
		}
	}
	
	public class ARetour implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			cond = 1;
			Main.onglet = 1;
			bLangue.setEnabled(false);
		}
	}
	
	public class AValider implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			cond = 2;
			Main.onglet = 1;
			bLangue.setEnabled(false);
		}
	}
	
}

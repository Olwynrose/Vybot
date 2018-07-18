import java.awt.AWTException;
import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;




public class PanLearn2
{
	JButton bSelec;
	JButton bChat;
	JButton bVie;
	JButton bMana;
	JButton bMob;
	JButton bRand;
	
	JButton bRetour;				// retour a la page précédente
	JButton bValider;				// sauvegarde la séquence
	
	char[] dialogue1;
	int n_Text1;
	int maxText;
	int maj;
	
	int h_Chat;
	int h_Vie;
	int h_Mana;
	int h_Scroll;
	int x_Scroll;
	int d_x;
	
	int h0_cond;
	int l_cond;
	int h_cond;
	int h_bcond;
	
	int cond;
	int val_cond;	// 0:rand, 1:chat, 2:vie, 3:mana, 4:mob, 5:detec
	int clic;
	ImageIcon iBouton_selec;
	
	public PanLearn2() throws InterruptedException, IOException, AWTException
	{
		String str_nom;
		int n_lim = 18;
		int buf_lnom1 = 0;
		cond = 0;
		clic = 1;
		val_cond = 0;
		maxText = 20;
		
		iBouton_selec = new ImageIcon("Icones/Bouton_selec.gif");
		
		// dimensions des boutons
		h0_cond = 200;
		l_cond = 200;
		h_cond = 10;
		h_bcond = 40;
		h_Chat = 0;
		h_Vie = 0;
		h_Mana = 0;
		h_Scroll = 0;
		x_Scroll = (Main.donnees.th_conds[Main.seq1]*(l_cond-20))/100 + (Main.jdim-l_cond)/2;
		
		int i = 0;
		
		Main.donnees = new Donnees();
		val_cond = Main.donnees.conds[Main.seq1];
		dialogue1 = new char[maxText];
		for(i=0;i<Math.min(Main.donnees.str_conds[Main.seq1].length(), maxText);i++)
		{
			dialogue1[i] = Main.donnees.str_conds[Main.seq1].charAt(i);
		}
		n_Text1 = Math.min(Main.donnees.str_conds[Main.seq1].length(), maxText);
		clic = 1;
		
		i = -1;
		bSelec = new JButton(Main.iBouton);
		if(Main.donnees.langue==0)
		{
			bSelec.setText("Détection");
		}
		else
		{
			bSelec.setText("Detection");
		}
		bSelec.setVerticalTextPosition(SwingConstants.CENTER);
		bSelec.setHorizontalTextPosition(SwingConstants.CENTER);
		bSelec.setBounds((Main.jdim-l_cond)/2, h0_cond+i*(h_cond+h_bcond), l_cond, h_bcond);
		Main.pan.add(bSelec);
		bSelec.addActionListener(new ASelec());
		i = i + 1;
		
		bChat = new JButton(Main.iBouton);
		if(Main.donnees.langue==0)
		{
			bChat.setText("Chat");
		}
		else
		{
			bChat.setText("Chat");
		}
		bChat.setVerticalTextPosition(SwingConstants.CENTER);
		bChat.setHorizontalTextPosition(SwingConstants.CENTER);
		bChat.setBounds((Main.jdim-l_cond)/2, h0_cond+i*(h_cond+h_bcond), l_cond, h_bcond);
		Main.pan.add(bChat);
		bChat.addActionListener(new AChat());
		i = i + 1;
		
		bVie = new JButton(Main.iBouton);
		if(Main.donnees.langue==0)
		{
			bVie.setText("Vie");
		}
		else
		{
			bVie.setText("Health");
		}
		bVie.setVerticalTextPosition(SwingConstants.CENTER);
		bVie.setHorizontalTextPosition(SwingConstants.CENTER);
		bVie.setBounds((Main.jdim-l_cond)/2, h0_cond+i*(h_cond+h_bcond), l_cond, h_bcond);
		Main.pan.add(bVie);
		bVie.addActionListener(new AVie());
		i = i + 1;
		
		bMana = new JButton(Main.iBouton);
		if(Main.donnees.langue==0)
		{
			bMana.setText("Mana");
		}
		else
		{
			bMana.setText("Mana");
		}
		bMana.setVerticalTextPosition(SwingConstants.CENTER);
		bMana.setHorizontalTextPosition(SwingConstants.CENTER);
		bMana.setBounds((Main.jdim-l_cond)/2, h0_cond+i*(h_cond+h_bcond), l_cond, h_bcond);
		Main.pan.add(bMana);
		bMana.addActionListener(new AMana());
		i = i + 1;
		
		bMob = new JButton(Main.iBouton);
		if(Main.donnees.langue==0)
		{
			bMob.setText("Selection de mob");
		}
		else
		{
			bMob.setText("Mob selected");
		}
		bMob.setVerticalTextPosition(SwingConstants.CENTER);
		bMob.setHorizontalTextPosition(SwingConstants.CENTER);
		bMob.setBounds((Main.jdim-l_cond)/2, h0_cond+i*(h_cond+h_bcond), l_cond, h_bcond);
		Main.pan.add(bMob);
		bMob.addActionListener(new AMob());
		i = i + 1;
		
		bRand = new JButton(Main.iBouton);
		if(Main.donnees.langue==0)
		{
			bRand.setText("Aléatoire");
		}
		else
		{
			bRand.setText("Random");
		}
		bRand.setVerticalTextPosition(SwingConstants.CENTER);
		bRand.setHorizontalTextPosition(SwingConstants.CENTER);
		bRand.setBounds((Main.jdim-l_cond)/2, h0_cond+i*(h_cond+h_bcond), l_cond, h_bcond+50);
		Main.pan.add(bRand);
		bRand.addActionListener(new ARand());
		i = i + 1;
		
		
		
		
		
		
		bRetour = new JButton(Main.iBouton);
		if(Main.donnees.langue==0)
		{
			bRetour.setText("Retour");
		}
		else
		{
			bRetour.setText("Back");
		}
		bRetour.setVerticalTextPosition(SwingConstants.CENTER);
		bRetour.setHorizontalTextPosition(SwingConstants.CENTER);
		bRetour.setBounds(0, Main.idim - 60, 130, 25);
		Main.pan.add(bRetour);
		bRetour.addActionListener(new ARetour());

		bValider = new JButton(Main.iBouton);
		if(Main.donnees.langue==0)
		{
			bValider.setText("Valider");
		}
		else
		{
			bValider.setText("Confirm");
		}
		bValider.setVerticalTextPosition(SwingConstants.CENTER);
		bValider.setHorizontalTextPosition(SwingConstants.CENTER);
		bValider.setBounds(Main.jdim-130, Main.idim - 60, 130, 25);
		Main.pan.add(bValider);
		bValider.addActionListener(new AValider());
		
		
		// ============================= Texte chat ==============================
		JTextArea tDialogue1 = new JTextArea();
		maj = 0;
		tDialogue1 = new JTextArea(" ");
		tDialogue1.setBounds((Main.jdim-l_cond)/2, h0_cond+(h_cond+h_bcond), l_cond, 18);
		Main.pan.add(tDialogue1);
		tDialogue1.setEditable(false);
		tDialogue1.setVisible(false);
		tDialogue1.addKeyListener(new KeyListener() {
			/** Handle the key typed event from the text field. */
			public void keyTyped(KeyEvent e) {

			}

			/** Handle the key pressed event from the text field. */
			public void keyPressed(KeyEvent e) {
				int a =e.getKeyCode();
				if(n_Text1<maxText && ( (a>= 65 && a<=90) || (a>= 96 && a<=105) || (a>= 49 && a<=57) || a==110 || a==32) )
				{
					if(maj==0)
					{
						dialogue1[n_Text1] = e.getKeyChar();
					}
					else
					{
						if(a>= 65 && a<=90)
						{
							dialogue1[n_Text1] = Character.toUpperCase(e.getKeyChar());
						}
						else
						{
							dialogue1[n_Text1] = e.getKeyChar();
						}

					}
					n_Text1 = n_Text1 + 1;

				}
				if(a==8 && n_Text1>0)
				{
					n_Text1 = n_Text1 - 1;
				}
				if(a==16)
				{
					maj = 1;
				}
			}

			/** Handle the key released event from the text field. */
			public void keyReleased(KeyEvent e) {
				int a =e.getKeyCode();
				if(a==16)
				{
					maj = 0;
				}
			}
		});
		// =====================================================================

		JLabel scrollbar = new JLabel();
		scrollbar.setLayout(null);
		scrollbar.setBackground(Color.BLACK);
		scrollbar.setBounds((Main.jdim-l_cond)/2, h_Scroll, l_cond, 20);
		scrollbar.setVisible(false);

		JButton scrollball = new JButton(Main.iScroll);
		scrollball.setLayout(null);
		scrollball.setVerticalTextPosition(SwingConstants.CENTER);
		scrollball.setHorizontalTextPosition(SwingConstants.CENTER);
		scrollball.setBackground(Color.BLUE);
		scrollball.setBounds((Main.jdim-l_cond)/2, h_Scroll, 20, 20);
		scrollball.setBorderPainted(false);
		scrollball.setVisible(false);


		scrollball.addMouseMotionListener( new MouseMotionAdapter() 
		{
			public void mouseDragged( MouseEvent e )
			{

				d_x = e.getX();
				x_Scroll = Math.min(Math.max(x_Scroll + d_x, (Main.jdim-l_cond)/2), (Main.jdim+l_cond)/2-20);


				scrollball.setLocation(x_Scroll, h_Scroll);
				Main.pan.repaint();
			}
		} );
		Main.pan.add(scrollball);
		Main.pan.add(scrollbar);
		
		Main.pan.add(Main.fondPanel);
		Main.pan.repaint();
		
		while(cond == 0)
		{
			Thread.sleep(100);
			if(clic == 1)
			{
				selection();
				i = 1;
				bVie.setBounds((Main.jdim-l_cond)/2, h0_cond+i*(h_cond+h_bcond)+h_Chat, l_cond, h_bcond);
				i = i+1;
				bMana.setBounds((Main.jdim-l_cond)/2, h0_cond+i*(h_cond+h_bcond)+h_Chat+h_Vie, l_cond, h_bcond);
				i = i+1;
				bMob.setBounds((Main.jdim-l_cond)/2, h0_cond+i*(h_cond+h_bcond)+h_Chat+h_Vie+h_Mana, l_cond, h_bcond);
				i = i+1;
				bRand.setBounds((Main.jdim-l_cond)/2, h0_cond+i*(h_cond+h_bcond)+h_Chat+h_Vie+h_Mana, l_cond, h_bcond);
				if(val_cond == 1)
				{
					tDialogue1.setVisible(true);
				}
				else
				{
					tDialogue1.setVisible(false);
				}
				if(val_cond == 2 || val_cond == 3 || val_cond == 0)
				{
					scrollball.setVisible(true);
					scrollbar.setVisible(true);
					scrollbar.setBounds((Main.jdim-l_cond)/2, h_Scroll, l_cond, 20);
					scrollball.setLocation(x_Scroll, h_Scroll);
				}
				else
				{
					scrollball.setVisible(false);
					scrollbar.setVisible(false);
				}
				clic = 0;
				Main.pan.repaint();
			}
			// ===================================== Affichege des dialogues =========
						if(n_Text1!=buf_lnom1)
						{
							if(n_Text1>0)
							{
								if(n_Text1<n_lim)
								{
									str_nom = (String.valueOf(dialogue1[0]));
									for(i=1;i<n_Text1;i++)
									{
										str_nom = new StringBuilder().append(str_nom).append(String.valueOf(dialogue1[i])).toString();
									}
								}
								else
								{
									str_nom = (String.valueOf(dialogue1[n_Text1-n_lim]));
									for(i=n_Text1-n_lim+1;i<n_Text1;i++)
									{
										str_nom = new StringBuilder().append(str_nom).append(String.valueOf(dialogue1[i])).toString();
									}
								}
							}
							else
							{
								str_nom = " ";
							}

							tDialogue1.setText(str_nom);

							buf_lnom1 = n_Text1;
						}

		} // fin de la condition de sortie
		
		// sauvegarde
		if(cond == 2)
		{
			Main.donnees.conds[Main.seq1] = val_cond;
			Main.donnees.th_conds[Main.seq1] = (100*(x_Scroll-(Main.jdim-l_cond)/2))/(l_cond-20);
			//System.out.println(Main.donnees.th_conds[Main.seq1]);
			Main.donnees.sauvegarde1();
			if(val_cond==1)
			{
				if(n_Text1>0)
				{
					if(n_Text1<n_lim)
					{
						str_nom = (String.valueOf(dialogue1[0]));
						for(i=1;i<n_Text1;i++)
						{
							str_nom = new StringBuilder().append(str_nom).append(String.valueOf(dialogue1[i])).toString();
						}
					}
					else
					{
						str_nom = (String.valueOf(dialogue1[n_Text1-n_lim]));
						for(i=n_Text1-n_lim+1;i<n_Text1;i++)
						{
							str_nom = new StringBuilder().append(str_nom).append(String.valueOf(dialogue1[i])).toString();
						}
					}
				}
				else
				{
					str_nom = " ";
				}
				new PanLearn3(str_nom);
				cond = 2;
				Main.onglet = 6;
			}
			if(val_cond==5)
			{
				GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
				int jMax = gd.getDisplayMode().getWidth();
				Main.pan.removeAll();
				Main.window.setSize(135, 258);
				Main.window.setAlwaysOnTop(true);
				Main.window.setLocation(jMax-135, 0);
				new PanLearnCond();
				Main.window.setAlwaysOnTop(false);
				Main.window.setLocation(jMax-Main.jdim, 0);
				cond = 2;
				Main.onglet = 6;
			}
		}
		
	} // fin du constructeur
	


	/*
	 *  ======================================================
	 * 	======================================================
	 * 	======================================================
	 */
	
	
	public class ASelec implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			val_cond = 5;
			clic = 1;
		}
	}
	
	public class ARand implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			val_cond = 0;
			clic = 1;
		}
	}
	
	public class AChat implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			val_cond = 1;
			clic = 1;
		}
	}
	
	public class AVie implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			val_cond = 2;
			clic = 1;
		}
	}
	
	public class AMana implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			val_cond = 3;
			clic = 1;
		}
	}
	
	public class AMob implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			val_cond = 4;
			clic = 1;
		}
	}
	
	public void selection()
	{
		/*bRand.setBackground(Color.RED);
		bChat.setBackground(Color.RED);
		bVie.setBackground(Color.RED);
		bMana.setBackground(Color.RED);
		bMob.setBackground(Color.RED);*/
		bRand.setIcon(Main.iBouton);
		bChat.setIcon(Main.iBouton);
		bVie.setIcon(Main.iBouton);
		bMana.setIcon(Main.iBouton);
		bMob.setIcon(Main.iBouton);
		bSelec.setIcon(Main.iBouton);
		switch(val_cond)
		{
			case 0:
			{
				bRand.setIcon(iBouton_selec);
				h_Chat = 0;
				h_Vie = 0;
				h_Mana = 0;
				h_Scroll = h0_cond+5*(h_cond+h_bcond);
			}
			break;
			case 1:
			{
				bChat.setIcon(iBouton_selec);
				h_Chat = 28;
				h_Vie = 0;
				h_Mana = 0;
			}
			break;
			case 2:
			{
				bVie.setIcon(iBouton_selec);
				h_Chat = 0;
				h_Vie = 30;
				h_Mana = 0;
				h_Scroll = h0_cond+2*(h_cond+h_bcond);
			}
			break;
			case 3:
			{
				bMana.setIcon(iBouton_selec);
				h_Chat = 0;
				h_Vie = 0;
				h_Mana = 30;
				h_Scroll = h0_cond+3*(h_cond+h_bcond);
			}
			break;
			case 4:
			{
				bMob.setIcon(iBouton_selec);
				h_Chat = 0;
				h_Vie = 0;
				h_Mana = 0;
			}
			break;
			case 5:
			{
				bSelec.setIcon(iBouton_selec);
				h_Chat = 0;
				h_Vie = 0;
				h_Mana = 0;
			}
			break;
		}
	}
	public class ARetour implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			cond = 1;
			Main.onglet = 6;
			bRetour.setEnabled(false);
		}
	}

	public class AValider implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			cond = 2;
			Main.onglet = 6;
			bRetour.setEnabled(false);
		}
	}
	
}

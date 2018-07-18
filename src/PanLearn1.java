import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;


public class PanLearn1
{	
	JButton[] bActions;				// pages s�quence d'actions
	JButton bAction1; 				// page s�quence d'action 1
	JButton bAction2; 				// page s�quence d'action 2
	JButton bAction3; 				// page s�quence d'action 3
	JButton[] bActs;				// active/desactive la sequence d action selectionn�e
	JButton bAct1; 					// active/desactive la sequence d action 1
	JButton bAct2; 					// active/desactive la sequence d action 2
	JButton bAct3; 					// active/desactive la sequence d action 3
	int[] is_acts;					// sequences d actions activ�es ou non
	int is_act1, is_act2, is_act3;			// sequence d action activ�e ou non
	
	JButton bCondition;				// page condition de l action

	JButton bClic;					// ajoute une action clic
	JButton bSelec;					// ajoute une action s�lection
	JButton bMarche;				// ajoute une action marche
	JButton bMarcheUru;				// ajoute une action marche uru
	JButton bAttend;				// ajoute une action attend

	JTextArea tPersos;				// choix de la touche personalis�e
	JButton bPerso;					// ajoute une action touche personalis�e	
	JTextArea tPerso;

	JTextArea tDialogues;
	JButton bDialogue1;				// ajoute l action dialogue 1
	JTextArea tDialogue1;				// choix du dialogue 1
	JButton bDialogue2;				// ajoute l action dialogue 1
	JTextArea tDialogue2;				// choix du dialogue 2
	JButton bDialogue3;				// ajoute l action dialogue 1
	JTextArea tDialogue3;				// choix du dialogue 3
	JTextArea tSequence;				// choix du non de la s�quence
	JButton bRetour;				// retour a la page pr�c�dente
	JButton bValider;				// sauvegarde la s�quence

	JButton[] bFlechesBas;				// choix de l ordre des actions
	JButton[] bFlechesHaut;				// choix de l ordre des actions
	JButton[] bSupp;				// supprime une action
	JButton[] bPlus;				// choix de la dur�e de l action
	JButton[] bMoins;				// choix de la dur�e de l action

	JPanel panAct;					// panel contenant la s�quence
	JPanel panFenAct;				// panel contenant le panel de s�quence (de taille limit�)
	
	ImageIcon croix;
	ImageIcon croix_selec;
	ImageIcon iAd;
	ImageIcon iPlus;
	ImageIcon iMoins;
	ImageIcon iBas;
	ImageIcon iHaut;

	JTextArea[] tAct;
	JTextArea[] tTemps;
	String[] sAct;
	String[] sTemps;
	int[] tempo;

	int touche;
	int clic_touche;

	char[] dialogue1;
	char[] dialogue2;
	char[] dialogue3;
	char[] bufChar;
	char[] sequence;
	String str_nom;

	int h_scroll;				// position de la scroll ball�
	int max_scroll;				// position max du scroll
	int min_scroll;				// position min du scroll
	int cond_scroll;			// scroll actif
	int d_x;				// variable pour la position de la souris (scroll)
	int maxAct;				// nombre maximum d actions par s�quence 
	int n_act;				// nombre effectif d actions
	int Last_act;
	int maxText;				// nombre maximum de characteres dans les dialogues
	int n_Text1;				// nombre effectif de characteres dans les dialogues
	int n_Text2;				// nombre effectif de characteres dans les dialogues
	int n_Text3;				// nombre effectif de characteres dans les dialogues
	int maxSeq;
	int n_seq;
	int buf_lnom1;
	int buf_lnom2;
	int buf_lnom3;
	int buf_lseq;
	int maj;
	int ctrl;
	int cond;
	int clic;				// bouton press�
	int clic_wizz;				// mauvaise action
	int clic_scroll;			// scroll utilis�
	int clic_tps;				// change le temps de l'action (-1:inactif, >=0:actif sur l'indice clic_tps)
	int clic_add;				// ajoute une action
	int clic_supp;				// supprime une action (-1:inactif, >=0:actif sur l'indice clic_supp)
	int clic_move;				// d�place une action (interverti 2 actions) (-1:inactif, >=0:actif sur l'indice clic_move)
	int typeSeq;
	int add_att;				// ajoute un attend si = 1
	
	
	public PanLearn1(int typeSeq_in) throws InterruptedException, AWTException, IOException
	{

		int n_lim = 15;

		// interface s�quence 
		int h_act = 26;
		int l_act = 100; 	// largeur action
		int h0_act = 60;	// r�f�rence des actions
		int l0_act = 50;
		int h0_lim = 80;
		
		
		int l_haut = 14;
		int l_tps = 30;
		int l0_tps = 2;
		int l_supp = 22;

		int h_seq = 0;
		int h_fenSeq = Main.idim-h0_lim-l0_act;
		int l0_scroll = l0_act + l_act + 100;
		min_scroll = l0_act+20;
		max_scroll = Main.idim-h0_lim-20;
		h_scroll = max_scroll;
		
		// interface boutons sequence
		int l_choix = 200;
		int h_bout = 45;
		int h_txt = 28;
		int h0_bout= 200;	// r�f�rence des boutons

		// interface boutons actions
		int h_bAct = 25;
		int h0_bAct= 75;	// r�f�rence des boutons
		
		// interface actions sp�ciales
		int l0_bAct1 = 30;
		int l_act1 = l_choix;
		int h0_bAct1 = h0_bAct;
		int h_bAct1 = h_bAct+5;
		

		cond = 0;
		clic = 0;
		clic_touche = 0;
		clic_wizz = 0;
		clic_scroll = 0;
		cond_scroll = 0;
		clic_tps = -1;
		if(Main.add_att == 1)
		{
			clic_add = 1;
		}
		else
		{
			clic_add = 0;
		}
		clic_supp = -1;
		clic_move = -1;
		maj = 0;
		ctrl = 0;
		buf_lnom1 = 0;
		buf_lnom2 = 0;
		buf_lnom3 = 0;
		buf_lseq = 0;
		add_att = 0;
		
		Main.pan.removeAll();
		Main.pan.setLayout(null);
		Main.pan.add(Main.fondPanel);
		Main.pan.repaint(); 
		Main.pan.validate();
		Main.pan.removeAll();
		Main.pan.setLayout(null);
		
		int i,j;
		maxAct = Main.donnees.maxSeq;
		maxText = 140;
		maxSeq = 12;
		n_act = 0;
		Last_act = 0;
		typeSeq = typeSeq_in;
		
		bFlechesBas = new JButton[maxAct-1];
		bFlechesHaut = new JButton[maxAct-1];
		bSupp = new JButton[maxAct];
		bPlus = new JButton[maxAct];
		bMoins = new JButton[maxAct];
		tAct = new JTextArea[maxAct];
		tTemps = new JTextArea[maxAct];
		sAct = new String[maxAct];
		sTemps = new String[maxAct];
		tempo = new int[maxAct];
		
		panFenAct = new JPanel();
		panFenAct.setBounds(l0_act, h0_act, l_act + 100, h_fenSeq);
		panFenAct.setBackground(Color.red);
		panFenAct.setLayout(null);
		panFenAct.setOpaque(false);
		
		
		panAct = new JPanel();
		panAct.setBounds(0, 0, l_act + 100, h_act*maxAct);
		panAct.setBackground(Color.red);
		panAct.setLayout(null);
		panAct.setOpaque(false);
		panFenAct.add(panAct);

		croix = new ImageIcon("Icones/croix_selec1.png");
		croix_selec = new ImageIcon("Icones/croix.png");
		iAd = new ImageIcon("Icones/Pplus.png");
		iPlus = new ImageIcon("Icones/plus.png");
		iMoins = new ImageIcon("Icones/moins.png");
		iBas = new ImageIcon("Icones/bas.png");
		iHaut = new ImageIcon("Icones/haut.png");
		
		//                         Chargement des donnees

		Main.donnees = new Donnees();

		dialogue1 = new char[maxText];
		n_Text1 = 1;
		dialogue2 = new char[maxText];
		n_Text2 = 1;
		dialogue3 = new char[maxText];
		n_Text3 = 1;
		
		if(typeSeq==1)
		{// S�quence principale
			str_nom = Main.donnees.dialogues[Main.seq][0];
			n_Text1 = Math.min(str_nom.length(), maxText);
			for(j=0;j<n_Text1;j++)
			{
				dialogue1[j] = str_nom.charAt(j);
			}
			str_nom = Main.donnees.dialogues[Main.seq][1];
			n_Text2 = Math.min(str_nom.length(), maxText);
			for(j=0;j<n_Text2;j++)
			{
				dialogue2[j] = str_nom.charAt(j);
			}
			str_nom = Main.donnees.dialogues[Main.seq][2];
			n_Text3 = Math.min(str_nom.length(), maxText);
			for(j=0;j<n_Text3;j++)
			{
				dialogue3[j] = str_nom.charAt(j);
			}
			
			for(i=0;i<Main.donnees.lSeq[Main.seq];i++)
			{
				sAct[i] = Main.donnees.aSeq[Main.seq][i].action;
				//System.out.println(sAct[i]);
				tempo[i] = Main.donnees.aSeq[Main.seq][i].tps;
			}
			n_act = Main.donnees.lSeq[Main.seq];
			
			is_acts = new int[Main.n_seq1];
			for(i=0;i<Main.n_seq1;i++)
			{
				is_acts[i] = Main.donnees.is_act[Main.seq][i];
			}
			is_act1 = Main.donnees.is_act[Main.seq][0];
			is_act2 = Main.donnees.is_act[Main.seq][1];
			is_act3 = Main.donnees.is_act[Main.seq][2];
			clic = 1;
		}
		else
		{
			for(i=0;i<Main.donnees.lSeq1[Main.seq1];i++)
			{
				sAct[i] = Main.donnees.aSeq1[Main.seq1][i].action;
				//System.out.println(sAct[i]);
				tempo[i] = Main.donnees.aSeq1[Main.seq1][i].tps;
			}
			str_nom = Main.donnees.dialogues1[Main.seq1][0];
			n_Text1 = Math.min(str_nom.length(), maxText);
			for(j=0;j<n_Text1;j++)
			{
				dialogue1[j] = str_nom.charAt(j);
			}
			str_nom = Main.donnees.dialogues1[Main.seq1][1];
			n_Text2 = Math.min(str_nom.length(), maxText);
			for(j=0;j<n_Text2;j++)
			{
				dialogue2[j] = str_nom.charAt(j);
			}
			str_nom = Main.donnees.dialogues1[Main.seq1][2];
			n_Text3 = Math.min(str_nom.length(), maxText);
			for(j=0;j<n_Text3;j++)
			{
				dialogue3[j] = str_nom.charAt(j);
			}
			n_act = Main.donnees.lSeq1[Main.seq1];
			clic = 1;
		}
		
		JLabel scrollbar = new JLabel();
		scrollbar.setLayout(null);
		scrollbar.setBackground(Color.BLACK);
		scrollbar.setBounds(l0_scroll, h0_act, Main.idim-h0_lim-l0_act, 20);
		scrollbar.setVisible(false);

		JButton scrollball = new JButton(Main.iScroll);
		scrollball.setLayout(null);
		scrollball.setVerticalTextPosition(SwingConstants.CENTER);
		scrollball.setHorizontalTextPosition(SwingConstants.CENTER);
		scrollball.setBackground(Color.BLUE);
		scrollball.setBounds(l0_scroll, h_scroll, 20, 20);
		scrollball.setBorderPainted(false);
		scrollball.setVisible(false);

		scrollball.addMouseMotionListener( new MouseMotionAdapter() 
		{
			public void mouseDragged( MouseEvent e )
			{

				d_x = e.getY();
				h_scroll = Math.max(Math.min(h_scroll + d_x, max_scroll), min_scroll);
				scrollball.setLocation(l0_scroll, h_scroll);
				clic_scroll = 1;
				Main.pan.repaint();
			}
		} );
		
		
		// interface boutons

		if(typeSeq==1)
		{
			bActions = new JButton[Main.n_seq1];
			for(i=0;i<Main.n_seq1;i++)
			{
				bActions[i] = new JButton(Main.iBouton);
				bActions[i].setText(Main.donnees.nameSeq1[i]);
				bActions[i].setBackground(Color.BLUE);
				bActions[i].setVerticalTextPosition(SwingConstants.CENTER);
				bActions[i].setHorizontalTextPosition(SwingConstants.CENTER);
				bActions[i].setBounds(Main.jdim-l_act1-l0_bAct1, h0_bAct1+i*h_bAct1, l_act1-22, 20);
				bActions[i].addActionListener(new ASeqActs(i));
			}
			/* ancienne version (3 seq1 en dur)
			bAction1 = new JButton(Main.iBouton);
			bAction1.setText(Main.donnees.nameSeq1[0]);
			bAction1.setBackground(Color.BLUE);
			bAction1.setVerticalTextPosition(SwingConstants.CENTER);
			bAction1.setHorizontalTextPosition(SwingConstants.CENTER);
			bAction1.setBounds(Main.jdim-l_choix-10, h0_bAct, l_choix-22, 20);
			bAction1.addActionListener(new ASeqAct1());

			bAction2 = new JButton(Main.iBouton);
			bAction2.setText(Main.donnees.nameSeq1[1]);
			bAction2.setBackground(Color.BLUE);
			bAction2.setVerticalTextPosition(SwingConstants.CENTER);
			bAction2.setHorizontalTextPosition(SwingConstants.CENTER);
			bAction2.setBounds(Main.jdim-l_choix-10, h0_bAct+h_bAct, l_choix-22, 20);
			bAction2.addActionListener(new ASeqAct2());

			bAction3 = new JButton(Main.iBouton);
			bAction3.setText(Main.donnees.nameSeq1[2]);
			bAction3.setBackground(Color.BLUE);
			bAction3.setVerticalTextPosition(SwingConstants.CENTER);
			bAction3.setHorizontalTextPosition(SwingConstants.CENTER);
			bAction3.setBounds(Main.jdim-l_choix-10, h0_bAct+2*h_bAct, l_choix-22, 20);
			bAction3.addActionListener(new ASeqAct3());
			*/
			
			bActs = new JButton[Main.n_seq1];
			for(i=0;i<Main.n_seq1;i++)
			{
				bActs[i] = new JButton();
				if(is_acts[i]==0)
				{
					bActs[i].setBackground(Color.RED);
				}
				else
				{
					bActs[i].setBackground(Color.GREEN);
				}
				bActs[i].setVerticalTextPosition(SwingConstants.CENTER);
				bActs[i].setHorizontalTextPosition(SwingConstants.CENTER);
				bActs[i].setBounds(Main.jdim-20-l0_bAct1, h0_bAct1+i*h_bAct1, 20, 20);
				bActs[i].addActionListener(new AIsActs(i));
			}
			/*// ancienne version (3 seq1 en dur)
			bAct1 = new JButton();
			if(is_act1==0)
			{
				bAct1.setBackground(Color.RED);
			}
			else
			{
				bAct1.setBackground(Color.GREEN);
			}
			bAct1.setVerticalTextPosition(SwingConstants.CENTER);
			bAct1.setHorizontalTextPosition(SwingConstants.CENTER);
			bAct1.setBounds(Main.jdim-30, h0_bAct, 20, 20);
			bAct1.addActionListener(new AIsAct1());

			bAct2 = new JButton();
			if(is_act2==0)
			{
				bAct2.setBackground(Color.RED);
			}
			else
			{
				bAct2.setBackground(Color.GREEN);
			}
			bAct2.setVerticalTextPosition(SwingConstants.CENTER);
			bAct2.setHorizontalTextPosition(SwingConstants.CENTER);
			bAct2.setBounds(Main.jdim-30, h0_bAct+h_bAct, 20, 20);
			bAct2.addActionListener(new AIsAct2());

			bAct3 = new JButton();
			if(is_act3==0)
			{
				bAct3.setBackground(Color.RED);
			}
			else
			{
				bAct3.setBackground(Color.GREEN);
			}
			bAct3.setVerticalTextPosition(SwingConstants.CENTER);
			bAct3.setHorizontalTextPosition(SwingConstants.CENTER);
			bAct3.setBounds(Main.jdim-30, h0_bAct+2*h_bAct, 20, 20);
			bAct3.addActionListener(new AIsAct3());
			*/
		}
		else // s�quence actions
		{
			bCondition = new JButton(Main.iBouton);
			bCondition.setText("Condition");
			bCondition.setBackground(Color.BLUE);
			bCondition.setVerticalTextPosition(SwingConstants.CENTER);
			bCondition.setHorizontalTextPosition(SwingConstants.CENTER);
			bCondition.setBounds(Main.jdim-l_choix-l0_bAct1, h0_bAct, l_choix, 30);
			bCondition.addActionListener(new ACondition());
		}

		JTextArea tTitre = new JTextArea();
		if(typeSeq==1)
		{
			if(Main.donnees.langue==0)
			{
				tTitre.setText("S�quence de fond");
			}
			else
			{
				tTitre.setText("Back sequence");
			}
			tTitre.setOpaque(false);
			tTitre.setFont(new Font("Consolas", Font.BOLD, 22));
			tTitre.setForeground(Color.WHITE);
		}
		else
		{
			if(Main.donnees.langue==0)
			{
				tTitre.setText("S�quence sp�ciale");
			}
			else
			{
				tTitre.setText("Special sequence");
			}
			tTitre.setOpaque(false);
			tTitre.setFont(new Font("Consolas", Font.BOLD, 22));
			tTitre.setForeground(Color.WHITE);
		}
		tTitre.setBounds(Main.jdim/2-100, 8, 220, 28);
		tTitre.setEditable(false);
		tTitre.setVisible(true);

		if(typeSeq==2)
		{
			bSelec = new JButton(Main.iBouton);
			if(Main.donnees.langue==0)
			{
				bSelec.setText("<Html><center>S�lection<br>du joueur<Html>");
			}
			else
			{
				bSelec.setText("<Html><center>Select<br>player<Html>");
			}
			bSelec.setBackground(Color.BLUE);
			bSelec.setVerticalTextPosition(SwingConstants.CENTER);
			bSelec.setHorizontalTextPosition(SwingConstants.CENTER);
			bSelec.setBounds((Main.jdim-l_choix)/2, h0_bout-2*h_bout, l_choix, 40);
			bSelec.addActionListener(new ASelec());
		}
		
		bClic = new JButton(Main.iBouton);
		if(Main.donnees.langue==0)
		{
			bClic.setText("Souris");
		}
		else
		{
			bClic.setText("Mouse");
		}
		bClic.setBackground(Color.BLUE);
		bClic.setVerticalTextPosition(SwingConstants.CENTER);
		bClic.setHorizontalTextPosition(SwingConstants.CENTER);
		bClic.setBounds((Main.jdim-l_choix)/2, h0_bout-h_bout, l_choix, 40);
		bClic.addActionListener(new AClic());
		
		bMarcheUru = new JButton(Main.iBouton);
		if(Main.donnees.langue==0)
		{
			bMarcheUru.setText("<html><center>Marche<br>Uruga</center></html>");
		}
		else
		{
			bMarcheUru.setText("<html><center>Walk<br>Uruga</center></html>");
		}
		bMarcheUru.setBackground(Color.BLUE);
		bMarcheUru.setVerticalTextPosition(SwingConstants.CENTER);
		bMarcheUru.setHorizontalTextPosition(SwingConstants.CENTER);
		bMarcheUru.setBounds((Main.jdim-l_choix)/2, h0_bout, l_choix, 40);
		bMarcheUru.addActionListener(new AMarcheUru());

		bMarche = new JButton(Main.iBouton);
		if(Main.donnees.langue==0)
		{
			bMarche.setText("<html><center>Marche<br>(autre map)</center></html>");
		}
		else
		{
			bMarche.setText("<html><center>Walk<br>(other map)</center></html>");
		}
		bMarche.setBackground(Color.BLUE);
		bMarche.setVerticalTextPosition(SwingConstants.CENTER);
		bMarche.setHorizontalTextPosition(SwingConstants.CENTER);
		bMarche.setBounds((Main.jdim-l_choix)/2, h0_bout+h_bout, l_choix, 40);
		bMarche.addActionListener(new AMarche());
		
		bAttend = new JButton(Main.iBouton);
		if(Main.donnees.langue==0)
		{
			bAttend.setText("Attend");
		}
		else
		{
			bAttend.setText("Tempo");
		}
		bAttend.setBackground(Color.BLUE);
		bAttend.setVerticalTextPosition(SwingConstants.CENTER);
		bAttend.setHorizontalTextPosition(SwingConstants.CENTER);
		bAttend.setBounds((Main.jdim-l_choix)/2, h0_bout+2*h_bout, l_choix, 40);
		bAttend.addActionListener(new AAttend());

		if(Main.donnees.langue==0)
		{
			tPersos = new JTextArea("Clavier");
		}
		else
		{
			tPersos = new JTextArea("Keyboard");
		}
		tPersos.setOpaque(false);
		tPersos.setFont(new Font("Consolas", Font.PLAIN, 16));
		tPersos.setForeground(Color.WHITE);
		tPersos.setBounds((Main.jdim-l_choix)/2, h0_bout+3*h_bout, l_choix, 18);
		tPersos.setEditable(false);
		tPersos.setVisible(true);

		bPerso = new JButton(iAd);
		bPerso.setBounds((Main.jdim+l_choix)/2-18, h0_bout+3*h_bout+h_txt, 18, 18); // Main.jdim-28
		bPerso.addActionListener(new APerso());

		touche = 49;
		tPerso = new JTextArea("Touche : 49");
		tPerso.setBounds((Main.jdim-l_choix)/2, h0_bout+3*h_bout+h_txt, l_choix-18, 18);
		tPerso.setEditable(false);
		tPerso.setVisible(true);
		tPerso.addKeyListener(new KeyListener() {
			/** Handle the key typed event from the text field. */
			public void keyTyped(KeyEvent e) {

			}

			/** Handle the key pressed event from the text field. */
			public void keyPressed(KeyEvent e) {
				int a =e.getKeyCode();
				/*
				 * Regle codes clavier : 
				 * si une touche seule est press�e : touche = keyCode
				 * la touche maj ajoute 1000 au keyCode si elle est prss�e
				 * la touche ctrl ajoute 2000 au keyCode si elle est press�e
				 * la touche maj est prioritaire sur ctrl
				 */
				if(a==16)
				{
					maj = 1;
				}
				else
				{
					if(a==17)
					{
						ctrl = 1;
					}
					else
					{
						if(maj==1)
						{
							touche = 1000+e.getKeyCode();
						}
						else
						{
							if(ctrl==1)
							{
								touche = 2000+e.getKeyCode();
							}
							else
							{
								touche = e.getKeyCode();
							}
						}
					}
				}


				clic_touche = 1;
			}

			/** Handle the key released event from the text field. */
			public void keyReleased(KeyEvent e) {
				int a =e.getKeyCode();
				if(a==16)
				{
					maj = 0;
				}
				if(a==17)
				{
					ctrl = 0;
				}
			}
		});




		// ===================================== Dialogues =======================================

		
		JTextArea tDialogues = new JTextArea("Chat");
		tDialogues.setOpaque(false);
		tDialogues.setFont(new Font("Consolas", Font.PLAIN, 16));
		tDialogues.setForeground(Color.WHITE);
		tDialogues.setBounds((Main.jdim-l_choix)/2, h0_bout+3*h_bout+2*h_txt, l_choix, 18);
		tDialogues.setEditable(false);
		tDialogues.setVisible(true);
		bDialogue1 = new JButton(iAd);
		bDialogue1.setBounds((Main.jdim+l_choix)/2-18, h0_bout+3*h_bout+3*h_txt, 18, 18);
		bDialogue1.addActionListener(new ADialogue1());
		tDialogue1 = new JTextArea(" ");
		tDialogue1.setBounds((Main.jdim-l_choix)/2, h0_bout+3*h_bout+3*h_txt, l_choix-18, 18);
		tDialogue1.setEditable(false);
		tDialogue1.setVisible(true);
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
		bDialogue2 = new JButton(iAd);
		bDialogue2.setBounds((Main.jdim+l_choix)/2-18, h0_bout+3*h_bout+4*h_txt, 18, 18);
		bDialogue2.addActionListener(new ADialogue2());
		tDialogue2 = new JTextArea(" ");
		tDialogue2.setBounds((Main.jdim-l_choix)/2, h0_bout+3*h_bout+4*h_txt, l_choix-18, 18);
		tDialogue2.setEditable(false);
		tDialogue2.setVisible(true);
		tDialogue2.addKeyListener(new KeyListener() {
			/** Handle the key typed event from the text field. */
			public void keyTyped(KeyEvent e) {

			}

			/** Handle the key pressed event from the text field. */
			public void keyPressed(KeyEvent e) {
				int a =e.getKeyCode();
				//System.out.println(a);
				if(n_Text2<maxText && ( (a>= 65 && a<=90) || (a>= 96 && a<=105) || (a>= 49 && a<=57) || a==110 || a==32) )
				{
					if(maj==0)
					{
						dialogue2[n_Text2] = e.getKeyChar();
					}
					else
					{
						if(a>= 65 && a<=90)
						{
							dialogue2[n_Text2] = Character.toUpperCase(e.getKeyChar());
						}
						else
						{
							dialogue2[n_Text2] = e.getKeyChar();
						}

					}
					n_Text2 = n_Text2 + 1;

				}
				if(a==8 && n_Text2>0)
				{
					n_Text2 = n_Text2 - 1;
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
		// ==========================================================================
		bDialogue3 = new JButton(iAd);
		bDialogue3.setBounds((Main.jdim+l_choix)/2-18, h0_bout+3*h_bout+5*h_txt, 18, 18);
		bDialogue3.addActionListener(new ADialogue3());
		tDialogue3 = new JTextArea(" ");
		tDialogue3.setBounds((Main.jdim-l_choix)/2, h0_bout+3*h_bout+5*h_txt, l_choix-18, 18);
		tDialogue3.setEditable(false);
		tDialogue3.setVisible(true);
		tDialogue3.addKeyListener(new KeyListener() {
			/** Handle the key typed event from the text field. */
			public void keyTyped(KeyEvent e) {

			}

			/** Handle the key pressed event from the text field. */
			public void keyPressed(KeyEvent e) {
				int a =e.getKeyCode();
				//System.out.println(a);
				if(n_Text3<maxText && ( (a>= 65 && a<=90) || (a>= 96 && a<=105) || (a>= 49 && a<=57) || a==110 || a==32) )
				{
					if(maj==0)
					{
						dialogue3[n_Text3] = e.getKeyChar();
					}
					else
					{
						if(a>= 65 && a<=90)
						{
							dialogue3[n_Text3] = Character.toUpperCase(e.getKeyChar());
						}
						else
						{
							dialogue3[n_Text3] = e.getKeyChar();
						}

					}
					n_Text3 = n_Text3 + 1;

				}
				if(a==8 && n_Text3>0)
				{
					n_Text3 = n_Text3 - 1;
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


		// ========================== Sequence =========================


		if(typeSeq==1)
		{
			bufChar = Main.donnees.nameSeq[Main.seq].toCharArray();
		}
		else
		{
			bufChar = Main.donnees.nameSeq1[Main.seq1].toCharArray();
		}
		sequence = new char[maxSeq];
		
		j = bufChar.length;
		if(j>maxSeq){j=maxSeq;}
		for(i=0;i<j;i++)
		{
			sequence[i] = bufChar[i];
		}
		n_seq = bufChar.length;
		tSequence = new JTextArea("");
		tSequence.setBounds(Main.jdim/2-100, 38, 200, 18);
		tSequence.setEditable(false);
		tSequence.setVisible(true);
		tSequence.addKeyListener(new KeyListener() {
			/** Handle the key typed event from the text field. */
			public void keyTyped(KeyEvent e) {

			}

			/** Handle the key pressed event from the text field. */
			public void keyPressed(KeyEvent e) {
				int a =e.getKeyCode();
				if(n_seq<maxSeq && ( (a>= 65 && a<=90) || (a>= 96 && a<=105) || (a>= 49 && a<=57) || a==110 || a==32) )
				{
					if(maj==0)
					{
						sequence[n_seq] = e.getKeyChar();
					}
					else
					{
						if(a>= 65 && a<=90)
						{
							sequence[n_seq] = Character.toUpperCase(e.getKeyChar());
						}
						else
						{
							sequence[n_seq] = e.getKeyChar();
						}

					}
					n_seq = n_seq + 1;

				}
				if(a==8 && n_seq>0)
				{
					n_seq = n_seq - 1;
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



		//============



		bRetour = new JButton(Main.iBouton);
		if(Main.donnees.langue==0)
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

		bValider = new JButton(Main.iBouton);
		if(Main.donnees.langue==0)
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
		bValider.setEnabled(false);
		bValider.addActionListener(new AValider());


		// =========================== Initialisation des actions =================

		for(i=0;i<maxAct;i++)
		{
			tAct[i] = new JTextArea();
			//tAct[i].setBounds(l_haut, h0_act+h_act*i, l_act, 18);
			tAct[i].setBounds(l_haut, h_act*i, l_act, 18);
			tAct[i].setEditable(false);
			tAct[i].setVisible(false);
			panAct.add(tAct[i]);

			tTemps[i] = new JTextArea();
			//tTemps[i].setBounds(l0_act+l_act+l0_tps, h0_act+h_act*i, l_tps, 18);
			tTemps[i].setBounds(l_haut+l_act+l0_tps, h_act*i, l_tps, 18);
			tTemps[i].setEditable(false);
			tTemps[i].setVisible(false);
			panAct.add(tTemps[i]);		

			bSupp[i] = new JButton(croix);
			//bSupp[i].setBounds(l0_act+l_act+l0_tps+l_tps+14, h0_act+h_act*i+1, 16, 16);
			bSupp[i].setBounds(l_haut+l_act+l0_tps+l_tps+l_supp, h_act*i+1, 16, 16);
			bSupp[i].setBorderPainted(false);
			bSupp[i].addMouseListener(new MouseSupp(i));
			bSupp[i].addActionListener(new ASupp(i));
			bSupp[i].setVisible(false);
			bSupp[i].setEnabled(false);
			panAct.add(bSupp[i]);

			bPlus[i] = new JButton(iPlus);
			//bPlus[i].setBounds(l0_act+l_act+l0_tps+l_tps+2, h0_act+h_act*i-1, 10, 10);
			bPlus[i].setBounds(l_haut+l_act+l0_tps+l_tps+2, h_act*i-1, 10, 10);
			bPlus[i].addActionListener(new APlus(i));
			bPlus[i].setVisible(false);
			bPlus[i].setEnabled(false);
			panAct.add(bPlus[i]);

			bMoins[i] = new JButton(iMoins);
			//bMoins[i].setBounds(l0_act+l_act+l0_tps+l_tps+2, h0_act+h_act*i+10, 10, 10);
			bMoins[i].setBounds(l_haut+l_act+l0_tps+l_tps+2, h_act*i+10, 10, 10);
			bMoins[i].addActionListener(new AMoins(i));
			bMoins[i].setVisible(false);
			bMoins[i].setEnabled(false);
			panAct.add(bMoins[i]);
		}
		for(i=1;i<maxAct;i++)
		{
			bFlechesBas[i-1] = new JButton(iBas);
			//bFlechesBas[i-1].setBounds(l0_act-12, h0_act+h_act*(i-1)+10, 10, 10);
			bFlechesBas[i-1].setBounds(0, h_act*(i-1)+10, 10, 10);
			bFlechesBas[i-1].addActionListener(new AFlechesBas(i-1));
			bFlechesBas[i-1].setVisible(false);
			bFlechesBas[i-1].setEnabled(false);
			panAct.add(bFlechesBas[i-1]);

			bFlechesHaut[i-1] = new JButton(iHaut);
			//bFlechesHaut[i-1].setBounds(l0_act-12, h0_act+h_act*i-1, 10, 10);
			bFlechesHaut[i-1].setBounds(0, h_act*i-1, 10, 10);
			bFlechesHaut[i-1].addActionListener(new AFlechesHaut(i-1));
			bFlechesHaut[i-1].setVisible(false);
			bFlechesHaut[i-1].setEnabled(false);
			panAct.add(bFlechesHaut[i-1]);
		}
		
		Main.pan.add(panFenAct);
		Main.pan.add(scrollball);
		Main.pan.add(scrollbar);
		if(typeSeq==1)
		{
			for(i=0;i<Main.n_seq1;i++)
			{
				Main.pan.add(bActions[i]);
				Main.pan.add(bActs[i]);
			}
			/* ancienne version (3 seq1 en dur)
			Main.pan.add(bAction1);
			Main.pan.add(bAction2);
			Main.pan.add(bAction3);
			Main.pan.add(bAct1);
			Main.pan.add(bAct2);
			Main.pan.add(bAct3);
			*/
		}
		else
		{
			Main.pan.add(bCondition);
			Main.pan.add(bSelec);
		}
		Main.pan.add(tTitre);
		Main.pan.add(bClic);
		Main.pan.add(bMarcheUru);
		Main.pan.add(bMarche);
		Main.pan.add(bAttend);
		Main.pan.add(tPersos);
		Main.pan.add(bPerso);
		Main.pan.add(tPerso);
		Main.pan.add(tDialogues);
		Main.pan.add(bDialogue1);
		Main.pan.add(tDialogue1);
		Main.pan.add(bDialogue2);
		Main.pan.add(tDialogue2);
		Main.pan.add(bDialogue3);
		Main.pan.add(tDialogue3);
		Main.pan.add(tSequence);
		Main.pan.add(bRetour);
		Main.pan.add(bValider);
		
		Main.pan.add(Main.fondPanel);

		// affiche les nouvelles actions
		if(n_act>0)
		{
			tAct[0].setText(sAct[0]);
			tAct[0].setVisible(true);

			if(tempo[0]>=250)
			{
				tTemps[0].setText(String.valueOf(( (float) tempo[0])/1000));
			}
			else
			{
				tTemps[0].setText("Clic");
			}
			tTemps[0].setVisible(true);

			bSupp[0].setVisible(true);
			bSupp[0].setEnabled(true);
			bPlus[0].setVisible(true);
			bPlus[0].setEnabled(true);
			bMoins[0].setVisible(true);
			bMoins[0].setEnabled(true);
		}


		for(i=1;i<n_act;i++)
		{
			tAct[i].setText(sAct[i]);
			tAct[i].setVisible(true);

			if(tempo[i]>=250)
			{
				tTemps[i].setText(String.valueOf(( (float) tempo[i])/1000));
			}
			else
			{
				tTemps[i].setText("Clic");
			}
			tTemps[i].setVisible(true);

			bFlechesBas[i-1].setVisible(true);
			bFlechesBas[i-1].setEnabled(true);
			bFlechesHaut[i-1].setVisible(true);
			bFlechesHaut[i-1].setEnabled(true);

			bSupp[i].setVisible(true);
			bSupp[i].setEnabled(true);
			bPlus[i].setVisible(true);
			bPlus[i].setEnabled(true);
			bMoins[i].setVisible(true);
			bMoins[i].setEnabled(true);
		}
		
		Main.pan.repaint(); 
		Main.pan.validate();

		/* ===========================================================
		 * 					Condition de sortie
		 * ===========================================================
		 */
		

		while(cond == 0)
		{
			Thread.sleep(100);
			
			// glissement de la fen�tre d'actions
			h_seq = h_act*n_act;
			if(h_seq>(h_fenSeq) && (clic_scroll==1 || clic==1))
			{
				panAct.setLocation(0,Math.min(0, -(h_seq-h_fenSeq)*(h_scroll-min_scroll)/(max_scroll-min_scroll)));
				System.out.println((h_seq-h_fenSeq)*(h_scroll-min_scroll)/(max_scroll-min_scroll));
				clic_scroll = 0;
				panFenAct.repaint();
				Main.pan.repaint();
			}
			
			if(h_seq>h_fenSeq)
			{
				scrollball.setVisible(true);
			}
			else
			{
				panAct.setLocation(0,0);
				scrollball.setVisible(false);
			}
			Main.pan.repaint();
			
			if(n_act>0)
			{
				bValider.setEnabled(true);
			}
			else
			{
				bValider.setEnabled(false);
			}

			if(clic == 1)
			{
				if(typeSeq==1)
				{
					for(i=0;i<Main.n_seq1;i++)
					{
						if(is_acts[i]==0)
						{
							bActs[i].setBackground(Color.RED);
						}
						else
						{
							bActs[i].setBackground(Color.GREEN);
						}
					}
					/* ancienne version (3 seq1 en dur)
					if(is_act1==0)
					{
						bAct1.setBackground(Color.RED);
					}
					else
					{
						bAct1.setBackground(Color.GREEN);
					}
					if(is_act2==0)
					{
						bAct2.setBackground(Color.RED);
					}
					else
					{
						bAct2.setBackground(Color.GREEN);
					}
					if(is_act3==0)
					{
						bAct3.setBackground(Color.RED);
					}
					else
					{
						bAct3.setBackground(Color.GREEN);
					}
					*/
				}
				
				/*
				// d�sactive les actions
				for(i=0;i<maxAct;i++)
				{
					tAct[i].setVisible(false);
					tTemps[i].setVisible(false);
					bSupp[i].setVisible(false);
					bSupp[i].setEnabled(false);
					bPlus[i].setVisible(false);
					bPlus[i].setEnabled(false);
					bMoins[i].setVisible(false);
					bMoins[i].setEnabled(false);
				}
				for(i=1;i<maxAct;i++)
				{
					bFlechesBas[i-1].setVisible(false);
					bFlechesBas[i-1].setEnabled(false);
					bFlechesHaut[i-1].setVisible(false);
					bFlechesHaut[i-1].setEnabled(false);
				}

				// affiche les nouvelles actions
				if(n_act>0)
				{
					tAct[0].setText(sAct[0]);
					tAct[0].setVisible(true);

					if(tempo[0]>=250)
					{
						tTemps[0].setText(String.valueOf(( (float) tempo[0])/1000));
					}
					else
					{
						tTemps[0].setText("Clic");
					}
					tTemps[0].setVisible(true);

					bSupp[0].setVisible(true);
					bSupp[0].setEnabled(true);
					bPlus[0].setVisible(true);
					bPlus[0].setEnabled(true);
					bMoins[0].setVisible(true);
					bMoins[0].setEnabled(true);
				}


				for(i=1;i<n_act;i++)
				{
					tAct[i].setText(sAct[i]);
					tAct[i].setVisible(true);

					if(tempo[i]>=250)
					{
						tTemps[i].setText(String.valueOf(( (float) tempo[i])/1000));
					}
					else
					{
						tTemps[i].setText("Clic");
					}
					tTemps[i].setVisible(true);

					bFlechesBas[i-1].setVisible(true);
					bFlechesBas[i-1].setEnabled(true);
					bFlechesHaut[i-1].setVisible(true);
					bFlechesHaut[i-1].setEnabled(true);

					bSupp[i].setVisible(true);
					bSupp[i].setEnabled(true);
					bPlus[i].setVisible(true);
					bPlus[i].setEnabled(true);
					bMoins[i].setVisible(true);
					bMoins[i].setEnabled(true);
				}*/
				clic = 0;

			}// fin clic==1
			
			if(clic_add==1)
			{
				// Affiche la derni�re action
				tAct[n_act-1].setText(sAct[n_act-1]);
				tAct[n_act-1].setVisible(true);

				if(tempo[n_act-1]>=250)
				{

					tTemps[n_act-1].setText(String.valueOf(( (float) tempo[n_act-1])/1000));
				}
				else

				{
					tTemps[n_act-1].setText("Clic");
				}
				tTemps[n_act-1].setVisible(true);
				bSupp[n_act-1].setVisible(true);
				bSupp[n_act-1].setEnabled(true);
				bPlus[n_act-1].setVisible(true);
				bPlus[n_act-1].setEnabled(true);
				bMoins[n_act-1].setVisible(true);
				bMoins[n_act-1].setEnabled(true);
				if(n_act>=2)
				{
					bFlechesBas[n_act-2].setVisible(true);
					bFlechesBas[n_act-2].setEnabled(true);
					bFlechesHaut[n_act-2].setVisible(true);
					bFlechesHaut[n_act-2].setEnabled(true);
				}
				clic_add = 0;
				if(n_act<maxAct && (add_att == 1 || Main.add_att == 1))
				{
					sAct[n_act] = "Attend";
					tempo[n_act] = 250;
					n_act = n_act + 1;
					clic_add = 1;
					clic = 1;
					Last_act = 0;//3
					add_att = 0; 
					Main.add_att = 0;
				}
			}
			if(clic_supp>=0)
			{
				// d�sactive les actions
				for(i=clic_supp;i<maxAct;i++)
				{
					tAct[i].setVisible(false);
					tTemps[i].setVisible(false);
					bSupp[i].setVisible(false);
					bSupp[i].setEnabled(false);
					bPlus[i].setVisible(false);
					bPlus[i].setEnabled(false);
					bMoins[i].setVisible(false);
					bMoins[i].setEnabled(false);
				}
				if(clic_supp>=1)
				{
					bFlechesBas[clic_supp-1].setVisible(false);
					bFlechesBas[clic_supp-1].setEnabled(false);
					bFlechesHaut[clic_supp-1].setVisible(false);
					bFlechesHaut[clic_supp-1].setEnabled(false);
				}
				for(i=clic_supp+1;i<maxAct;i++)
				{
					bFlechesBas[i-1].setVisible(false);
					bFlechesBas[i-1].setEnabled(false);
					bFlechesHaut[i-1].setVisible(false);
					bFlechesHaut[i-1].setEnabled(false);
				}
				


				// affiche les nouvelles actions
				if(n_act>clic_supp)
				{
					tAct[clic_supp].setText(sAct[clic_supp]);
					tAct[clic_supp].setVisible(true);

					if(tempo[clic_supp]>=250)
					{

						tTemps[clic_supp].setText(String.valueOf(( (float) tempo[clic_supp])/1000));
					}
					else

					{
						tTemps[clic_supp].setText("Clic");
					}
					tTemps[clic_supp].setVisible(true);


					bSupp[clic_supp].setVisible(true);
					bSupp[clic_supp].setEnabled(true);

					bPlus[clic_supp].setVisible(true);
					bPlus[clic_supp].setEnabled(true);
					bMoins[clic_supp].setVisible(true);
					bMoins[clic_supp].setEnabled(true);
					if(clic_supp>=1)
					{
						bFlechesBas[clic_supp-1].setVisible(true);
						bFlechesBas[clic_supp-1].setEnabled(true);
						bFlechesHaut[clic_supp-1].setVisible(true);
						bFlechesHaut[clic_supp-1].setEnabled(true);
					}

				}


				for(i=clic_supp+1;i<n_act;i++)
				{
					tAct[i].setText(sAct[i]);
					tAct[i].setVisible(true);


					if(tempo[i]>=250)
					{

						tTemps[i].setText(String.valueOf(( (float) tempo[i])/1000));
					}
					else
					{

						tTemps[i].setText("Clic");
					}
					tTemps[i].setVisible(true);


					bFlechesBas[i-1].setVisible(true);
					bFlechesBas[i-1].setEnabled(true);
					bFlechesHaut[i-1].setVisible(true);
					bFlechesHaut[i-1].setEnabled(true);

					bSupp[i].setVisible(true);

					bSupp[i].setEnabled(true);
					bPlus[i].setVisible(true);
					bPlus[i].setEnabled(true);
					bMoins[i].setVisible(true);
					bMoins[i].setEnabled(true);
				}
				clic_supp = -1;
			} // clic_supp>=0
			
			if(clic_tps>=0)
			{
				if(tempo[clic_tps]>=250)
				{
					tTemps[clic_tps].setText(String.valueOf(( (float) tempo[clic_tps])/1000));
				}
				else
				{
					tTemps[clic_tps].setText("Clic");
				}
				clic_tps = -1;
			}// clic_tps>=0

			if(clic_move>=0)
			{
				System.out.println(clic_move);
				tAct[clic_move].setText(sAct[clic_move]);
				if(tempo[clic_move]>=250)
				{
					tTemps[clic_move].setText(String.valueOf(( (float) tempo[clic_move])/1000));
				}
				else
				{
					tTemps[clic_move].setText("Clic");
				}

				tAct[clic_move+1].setText(sAct[clic_move+1]);
				if(tempo[clic_move+1]>=250)
				{
					tTemps[clic_move+1].setText(String.valueOf(( (float) tempo[clic_move+1])/1000));
				}
				else
				{
					tTemps[clic_move+1].setText("Clic");
				}
				clic_move = -1;
			}// clic_move>=0

			if(clic_touche==1)
			{
				if(Main.donnees.langue==0)
				{
					str_nom = "Touche : ";
				}
				else
				{
					str_nom = "Key : ";
				}
				
				str_nom = new StringBuilder().append(str_nom).append(String.valueOf(touche)).toString();
				tPerso.setText(str_nom);
				clic_touche = 0;
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

			// =================
			if(n_Text2!=buf_lnom2)
			{
				if(n_Text2>0)
				{
					if(n_Text2<n_lim)
					{
						str_nom = (String.valueOf(dialogue2[0]));
						for(i=1;i<n_Text2;i++)
						{
							str_nom = new StringBuilder().append(str_nom).append(String.valueOf(dialogue2[i])).toString();
						}
					}
					else
					{
						str_nom = (String.valueOf(dialogue2[n_Text2-n_lim]));
						for(i=n_Text2-n_lim+1;i<n_Text2;i++)
						{
							str_nom = new StringBuilder().append(str_nom).append(String.valueOf(dialogue2[i])).toString();
						}
					}
				}
				else
				{
					str_nom = " ";
				}

				tDialogue2.setText(str_nom);

				buf_lnom2 = n_Text2;

			}
			// =================
			if(n_Text3!=buf_lnom3)
			{
				if(n_Text3>0)
				{
					if(n_Text3<n_lim)
					{
						str_nom = (String.valueOf(dialogue3[0]));
						for(i=1;i<n_Text3;i++)
						{
							str_nom = new StringBuilder().append(str_nom).append(String.valueOf(dialogue3[i])).toString();
						}
					}
					else
					{
						str_nom = (String.valueOf(dialogue3[n_Text3-n_lim]));
						for(i=n_Text3-n_lim+1;i<n_Text3;i++)
						{
							str_nom = new StringBuilder().append(str_nom).append(String.valueOf(dialogue3[i])).toString();
						}
					}
				}
				else
				{
					str_nom = " ";
				}

				tDialogue3.setText(str_nom);

				buf_lnom3 = n_Text3;

			}
			// ==============
			if(n_seq!=buf_lseq)
			{
				if(n_seq>0)
				{
					if(n_seq<n_lim)
					{
						str_nom = (String.valueOf(sequence[0]));
						for(i=1;i<n_seq;i++)
						{
							str_nom = new StringBuilder().append(str_nom).append(String.valueOf(sequence[i])).toString();
						}
					}
					else
					{
						str_nom = (String.valueOf(sequence[n_seq-n_lim]));
						for(i=n_seq-n_lim+1;i<n_seq;i++)
						{
							str_nom = new StringBuilder().append(str_nom).append(String.valueOf(sequence[i])).toString();
						}
					}
				}
				else
				{
					str_nom = " ";
				}

				tSequence.setText(str_nom);

				buf_lseq = n_seq;
				Main.pan.repaint(); 
				Main.pan.validate();

			}
			
			
			// wizz
			if(clic_wizz == 1)
			{
				wizz();
				clic_wizz = 0;
			}

		}// fin de la condition de sortie

		
		// Sauvegarde de la s�quence
		if(cond == 2)
		{
			if(typeSeq==1)
			{
				if(n_seq>0)
				{
					if(n_seq<n_lim)
					{
						str_nom = (String.valueOf(sequence[0]));
						for(i=1;i<n_seq;i++)
						{
							str_nom = new StringBuilder().append(str_nom).append(String.valueOf(sequence[i])).toString();
						}
					}
					else
					{
						str_nom = (String.valueOf(sequence[n_seq-n_lim]));
						for(i=n_seq-n_lim+1;i<n_seq;i++)
						{
							str_nom = new StringBuilder().append(str_nom).append(String.valueOf(sequence[i])).toString();
						}
					}
				}
				Main.donnees.nameSeq[Main.seq] = str_nom;
				Main.donnees.lSeq[Main.seq] = n_act;
				// sauvegarde les propositions de dialogues
				str_nom = String.valueOf(dialogue1[0]);
				for(j=1;j<n_Text1;j++)
				{
					str_nom = new StringBuilder().append(str_nom).append(String.valueOf(dialogue1[j])).toString();
				}
				Main.donnees.dialogues[Main.seq][0] = str_nom;
				str_nom = String.valueOf(dialogue2[0]);
				for(j=1;j<n_Text2;j++)
				{
					str_nom = new StringBuilder().append(str_nom).append(String.valueOf(dialogue2[j])).toString();
				}
				Main.donnees.dialogues[Main.seq][1] = str_nom;
				str_nom = String.valueOf(dialogue3[0]);
				for(j=1;j<n_Text3;j++)
				{
					str_nom = new StringBuilder().append(str_nom).append(String.valueOf(dialogue3[j])).toString();
				}
				Main.donnees.dialogues[Main.seq][2] = str_nom;
				
				for(i=0;i<n_act;i++)
				{
					Main.donnees.aSeq[Main.seq][i] = new Action(sAct[i], tempo[i]);
					if(sAct[i].charAt(0) == 'T')
					{
						str_nom = String.valueOf(sAct[i].charAt(9));
						for(j=10;j<sAct[i].length();j++)
						{
							str_nom = new StringBuilder().append(str_nom).append(String.valueOf(sAct[i].charAt(j))).toString();
						}
						Main.donnees.aSeq[Main.seq][i].keyCode = Integer.valueOf(str_nom);
					}
					
					if(sAct[i].charAt(0) == 'C')
					{
						j = 7;
						str_nom = String.valueOf(sAct[i].charAt(j));
						j = j + 1;
						while(sAct[i].charAt(j)!=',')
						{
							str_nom = new StringBuilder().append(str_nom).append(String.valueOf(sAct[i].charAt(j))).toString();
							j = j + 1;
						}
						//System.out.println(str_nom);
						Main.donnees.aSeq[Main.seq][i].clic_i = Integer.valueOf(str_nom);
						j = j + 2;
						str_nom = String.valueOf(sAct[i].charAt(j));
						j = j + 1;
						while(j<sAct[i].length())
						{
							str_nom = new StringBuilder().append(str_nom).append(String.valueOf(sAct[i].charAt(j))).toString();
							j = j + 1;
						}
						//System.out.println(str_nom);
						Main.donnees.aSeq[Main.seq][i].clic_j = Integer.valueOf(str_nom);
					}
	
					if(sAct[i].charAt(0) == 'D')
					{
						if(sAct[i].charAt(8) == '1')
						{
							Main.donnees.aSeq[Main.seq][i].l_text = n_Text1;
							for(j=0;j<n_Text1;j++)
							{
								Main.donnees.aSeq[Main.seq][i].dialogue[j] = dialogue1[j];
							}
						}
						if(sAct[i].charAt(8) == '2')
						{
							Main.donnees.aSeq[Main.seq][i].l_text = n_Text2;
							for(j=0;j<n_Text2;j++)
							{
								Main.donnees.aSeq[Main.seq][i].dialogue[j] = dialogue2[j];
							}
						}
						if(sAct[i].charAt(8) == '3')
						{
							Main.donnees.aSeq[Main.seq][i].l_text = n_Text3;
							for(j=0;j<n_Text3;j++)
							{
								Main.donnees.aSeq[Main.seq][i].dialogue[j] = dialogue3[j];
							}
						}
					}// fin de la r�cup�ration du dialogue
				} // fin de la r�cup�ration des actions
				for(i=0;i<Main.n_seq1;i++)
				{
					Main.donnees.is_act[Main.seq][i] = is_acts[i];
				}
				/*Main.donnees.is_act[Main.seq][0] = is_act1;
				Main.donnees.is_act[Main.seq][1] = is_act2;
				Main.donnees.is_act[Main.seq][2] = is_act3;*/
				Main.donnees.sauvegarde();
			}
			else
			{
				if(n_seq>0)
				{
					if(n_seq<n_lim)
					{
						str_nom = (String.valueOf(sequence[0]));
						for(i=1;i<n_seq;i++)
						{
							str_nom = new StringBuilder().append(str_nom).append(String.valueOf(sequence[i])).toString();
						}
					}
					else
					{
						str_nom = (String.valueOf(sequence[n_seq-n_lim]));
						for(i=n_seq-n_lim+1;i<n_seq;i++)
						{
							str_nom = new StringBuilder().append(str_nom).append(String.valueOf(sequence[i])).toString();
						}
					}
				}
				Main.donnees.nameSeq1[Main.seq1] = str_nom;
				Main.donnees.lSeq1[Main.seq1] = n_act;
				// sauvegarde les propositions de dialogues
				str_nom = String.valueOf(dialogue1[0]);
				for(j=1;j<n_Text1;j++)
				{
					str_nom = new StringBuilder().append(str_nom).append(String.valueOf(dialogue1[j])).toString();
				}
				Main.donnees.dialogues1[Main.seq1][0] = str_nom;
				str_nom = String.valueOf(dialogue2[0]);
				for(j=1;j<n_Text2;j++)
				{
					str_nom = new StringBuilder().append(str_nom).append(String.valueOf(dialogue2[j])).toString();
				}
				Main.donnees.dialogues1[Main.seq1][1] = str_nom;
				str_nom = String.valueOf(dialogue3[0]);
				for(j=1;j<n_Text3;j++)
				{
					str_nom = new StringBuilder().append(str_nom).append(String.valueOf(dialogue3[j])).toString();
				}
				Main.donnees.dialogues1[Main.seq1][2] = str_nom;
				for(i=0;i<n_act;i++)
				{
					Main.donnees.aSeq1[Main.seq1][i] = new Action(sAct[i], tempo[i]);
					if(sAct[i].charAt(0) == 'T')
					{
						str_nom = String.valueOf(sAct[i].charAt(9));
						for(j=10;j<sAct[i].length();j++)
						{
							str_nom = new StringBuilder().append(str_nom).append(String.valueOf(sAct[i].charAt(j))).toString();
						}
						Main.donnees.aSeq1[Main.seq1][i].keyCode = Integer.valueOf(str_nom);
					}
	
					if(sAct[i].charAt(0) == 'C')
					{
						j = 7;
						str_nom = String.valueOf(sAct[i].charAt(j));
						j = j + 1;
						while(sAct[i].charAt(j)!=',')
						{
							str_nom = new StringBuilder().append(str_nom).append(String.valueOf(sAct[i].charAt(j))).toString();
							j = j + 1;
						}
						//System.out.println(str_nom);
						Main.donnees.aSeq1[Main.seq1][i].clic_i = Integer.valueOf(str_nom);
						j = j + 2;
						str_nom = String.valueOf(sAct[i].charAt(j));
						j = j + 1;
						while(j<sAct[i].length())
						{
							str_nom = new StringBuilder().append(str_nom).append(String.valueOf(sAct[i].charAt(j))).toString();
							j = j + 1;
						}
						//System.out.println(str_nom);
						Main.donnees.aSeq1[Main.seq1][i].clic_j = Integer.valueOf(str_nom);
					}
					
					if(sAct[i].charAt(0) == 'D')
					{
						if(sAct[i].charAt(8) == '1')
						{
							Main.donnees.aSeq1[Main.seq1][i].l_text = n_Text1;
							for(j=0;j<n_Text1;j++)
							{
								Main.donnees.aSeq1[Main.seq1][i].dialogue[j] = dialogue1[j];
							}
						}
						if(sAct[i].charAt(8) == '2')
						{
							Main.donnees.aSeq1[Main.seq1][i].l_text = n_Text2;
							for(j=0;j<n_Text2;j++)
							{
								Main.donnees.aSeq1[Main.seq1][i].dialogue[j] = dialogue2[j];
							}
						}
						if(sAct[i].charAt(8) == '3')
						{
							Main.donnees.aSeq1[Main.seq1][i].l_text = n_Text3;
							for(j=0;j<n_Text3;j++)
							{
								Main.donnees.aSeq1[Main.seq1][i].dialogue[j] = dialogue3[j];
							}
						}
					}// fin de la r�cup�ration du dialogue
				} // fin de la r�cup�ration des actions
				
				Main.donnees.sauvegarde1();
			}
		}// fin de la sauvegarde



	} // fin constructeur


	/*
	 *  ======================================================
	 * 	======================================================
	 * 	======================================================
	 */



	public class AFlechesBas implements ActionListener
	{
		int ind;
		public AFlechesBas(int ind)
		{
			this.ind = ind;
		}
		public void actionPerformed(ActionEvent e) 
		{
			String buf_act;
			int buf_tps;

			buf_tps = tempo[ind];
			tempo[ind] = tempo[ind+1];
			tempo[ind+1] = buf_tps;

			buf_act = sAct[ind];
			sAct[ind] = sAct[ind+1];
			sAct[ind+1] = buf_act;

			Last_act = 0;
			clic_move = ind;
		}
	}

	public class AFlechesHaut implements ActionListener
	{
		int ind;
		public AFlechesHaut(int ind)
		{
			this.ind = ind;
		}
		public void actionPerformed(ActionEvent e) 
		{
			String buf_act;
			int buf_tps;

			buf_tps = tempo[ind];
			tempo[ind] = tempo[ind+1];
			tempo[ind+1] = buf_tps;

			buf_act = sAct[ind];
			sAct[ind] = sAct[ind+1];
			sAct[ind+1] = buf_act;

			Last_act = 0;
			clic_move = ind;
		}
	}

	public class ASupp implements ActionListener
	{
		int ind;
		public ASupp(int ind)
		{
			this.ind = ind;
		}
		public void actionPerformed(ActionEvent e) 
		{
			//String buf_act;
			//int buf_tps;

			for(int i=ind;i<n_act-1;i++)
			{
				tempo[i] = tempo[i+1];
				sAct[i] = sAct[i+1];
			}
			n_act = n_act - 1;
			Last_act = 0;
			clic_supp = ind;
			clic = 1;
		}
	}

	public class APlus implements ActionListener
	{
		int ind;
		public APlus(int ind)
		{
			this.ind = ind;
		}
		public void actionPerformed(ActionEvent e) 
		{
			if(sAct[ind].charAt(0) == 'M' && tempo[ind]<120000)
			{
				tempo[ind] = tempo[ind] + 5000;
			}
			else
			{
				if(sAct[ind].charAt(0) == 'M' && tempo[ind]>=120000)
				{
					tempo[ind] = tempo[ind] + 30000;
				}
			}
			if((sAct[ind].charAt(0) == 'A' || sAct[ind].charAt(0) == 'T') && tempo[ind]<5000)
			{
				tempo[ind] = tempo[ind] + 250;
			}
			else
			{
				if((sAct[ind].charAt(0) == 'A' || sAct[ind].charAt(0) == 'T') && tempo[ind]>=5000 && tempo[ind]<15000)
				{
					tempo[ind] = tempo[ind] + 1000;
				}
				else
				{
					if((sAct[ind].charAt(0) == 'A' || sAct[ind].charAt(0) == 'T') && tempo[ind]>=15000 && tempo[ind]<30000)
					{
						tempo[ind] = tempo[ind] + 5000;
					}
					else
					{
						if((sAct[ind].charAt(0) == 'A' || sAct[ind].charAt(0) == 'T') && tempo[ind]>=30000)
						{
							tempo[ind] = tempo[ind] + 10000;
						}	
					}
				}
			}
			// pas d'attente pour le dialogue
			clic_tps = ind;
		}
	}

	public class AMoins implements ActionListener
	{
		int ind;
		public AMoins(int ind)
		{
			this.ind = ind;
		}
		public void actionPerformed(ActionEvent e) 
		{
			if(sAct[ind].charAt(0) == 'M' && tempo[ind]<120000)
			{
				tempo[ind] = tempo[ind] - 5000;
			}
			else
			{
				if(sAct[ind].charAt(0) == 'M' && tempo[ind]>=120000)
				{
					tempo[ind] = tempo[ind] - 30000;
				}
			}
			if((sAct[ind].charAt(0) == 'A' || sAct[ind].charAt(0) == 'T') && tempo[ind]<=5000 && tempo[ind]>0)
			{
				tempo[ind] = tempo[ind] - 250;
			}
			else
			{
				if((sAct[ind].charAt(0) == 'A' || sAct[ind].charAt(0) == 'T') && tempo[ind]>5000 && tempo[ind]<=15000 )
				{
					tempo[ind] = tempo[ind] - 1000;
				}
				else
				{
					if((sAct[ind].charAt(0) == 'A' || sAct[ind].charAt(0) == 'T') && tempo[ind]>15000 && tempo[ind]<=30000)
					{
						tempo[ind] = tempo[ind] - 5000;
					}
					else
					{
						if((sAct[ind].charAt(0) == 'A' || sAct[ind].charAt(0) == 'T') && tempo[ind]>30000)
						{
							tempo[ind] = tempo[ind] - 10000;
						}	
					}
				}
			}
			// pas d'attente pour le dialogue
			clic_tps = ind;
		}
	}

	public class AIsActs implements ActionListener
	{
		int ind;
		public AIsActs(int ind)
		{
			this.ind = ind;
		}
		public void actionPerformed(ActionEvent e) 
		{
			is_acts[ind] = (is_acts[ind]+1) %2;
			clic = 1;
		}
	}

	public class AIsAct1 implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			is_act1 = (is_act1+1) %2;
			clic = 1;
		}
	}
	
	public class AIsAct2 implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			is_act2 = (is_act2+1) %2;
			clic = 1;
		}
	}
	
	public class AIsAct3 implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			is_act3 = (is_act3+1) %2;
			clic = 1;
		}
	}
	
	public class ASelec implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			if(n_act<maxAct && Last_act!=4)
			{
				str_nom = "Selection";
				sAct[n_act] = str_nom;
				tempo[n_act] = 0;
				n_act = n_act + 1;
				Last_act = 4;
				clic_add = 1;
				clic = 1;
			}
			else
			{
				clic_wizz = 1;
			}
		}
	}
	
	public class APerso implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			if(n_act<maxAct)
			{
				str_nom = "Touche : ";
				str_nom = new StringBuilder().append(str_nom).append(String.valueOf(touche)).toString();
				sAct[n_act] = str_nom;
				tempo[n_act] = 0;
				n_act = n_act + 1;
				Last_act = 0;
				add_att = 1; 
				clic_add = 1;
				clic = 1;
			}
			else
			{
				clic_wizz = 1;
			}
		}
	}

	public class AMarche implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			if(n_act<maxAct && Last_act!=1)
			{
				sAct[n_act] = "Marche";
				tempo[n_act] = 60000;
				n_act = n_act + 1;
				clic_add = 1;
				clic = 1;
				Last_act = 1;
			}
			else
			{
				clic_wizz = 1;
			}
		}
	}

	public class AClic implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			cond = 2;
			if(typeSeq==1)
			{
				Main.onglet = 12;
			}
			else
			{
				Main.onglet = 13;
			}
		}
	}
	
	public class AMarcheUru implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			if(n_act<maxAct && Last_act!=2)
			{
				sAct[n_act] = "Marche Uru";
				tempo[n_act] = 60000;
				n_act = n_act + 1;
				clic_add = 1;
				clic = 1;
				Last_act = 2;
			}
			else
			{
				clic_wizz = 1;
			}
		}
	}

	public class AAttend implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			if(n_act<maxAct && Last_act!=3)
			{
				sAct[n_act] = "Attend";
				tempo[n_act] = 250;
				n_act = n_act + 1;
				clic_add = 1;
				clic = 1;
				Last_act = 0;//3
			}
			else
			{
				clic_wizz = 1;
			}
		}
	}

	public class ADialogue1 implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			if(n_act<maxAct)
			{
				sAct[n_act] = "Dialogue1";
				tempo[n_act] = 0;
				n_act = n_act + 1;
				clic_add = 1;
				clic = 1;
				Last_act = 0;
			}
			else
			{
				clic_wizz = 1;
			}
		}
	}

	public class ADialogue2 implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			if(n_act<maxAct)
			{
				sAct[n_act] = "Dialogue2";
				tempo[n_act] = 0;
				n_act = n_act + 1;
				clic_add = 1;
				clic = 1;
				Last_act = 0;
			}
			else
			{
				clic_wizz = 1;
			}
		}
	}

	public class ADialogue3 implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			if(n_act<maxAct)
			{
				sAct[n_act] = "Dialogue3";
				tempo[n_act] = 0;
				n_act = n_act + 1;
				clic_add = 1;
				clic = 1;
				Last_act = 0;
			}
			else
			{
				clic_wizz = 1;
			}
		}
	}

	public class ASeqActs implements ActionListener
	{
		int ind;
		public ASeqActs(int ind)
		{
			this.ind = ind;
		}
		public void actionPerformed(ActionEvent e) 
		{
			cond = 2;
			Main.onglet = 6;
			Main.seq1 = ind;
		}
	}

	public class ASeqAct1 implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			cond = 2;
			Main.onglet = 6;
			Main.seq1 = 0;
		}
	}
	
	public class ASeqAct2 implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			cond = 2;
			Main.onglet = 6;
			Main.seq1 = 1;
		}
	}
	
	public class ASeqAct3 implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			cond = 2;
			Main.onglet = 6;
			Main.seq1 = 2;
		}
	}
	
	public class ARetour implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			cond = 1;
			if(typeSeq==1)
			{
				Main.onglet = 2;
			}
			else
			{
				Main.onglet = 4;
			}
			
			bRetour.setEnabled(false);
		}
	}

	public class AValider implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			cond = 2;
			if(typeSeq==1)
			{
				Main.onglet = 2;
			}
			else
			{
				Main.onglet = 4;
			}
			bRetour.setEnabled(false);
		}
	}

	public class ACondition implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			cond = 2;
			Main.onglet = 5;
			bRetour.setEnabled(false);
		}
	}


	public class MouseSupp implements MouseListener //action du bouton
	{
		int it;
		public MouseSupp(int i) 
		{
			it = i;
		}
		public void actionPerformed(ActionEvent e)
		{
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			bSupp[it].setIcon(croix_selec);
		}
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			bSupp[it].setIcon(croix);
		}
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}


	}		

	private void wizz()
	{
		int x, y;
		x=0; y=0;
		Point posframe = new Point(x, y);
		posframe = Main.window.getLocation();

		posframe = Main.window.getLocation();
		Main.window.setVisible(true);
		posframe.x = posframe.x - 5;
		posframe.y = posframe.y - 5;
		Main.window.setLocation(posframe);
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		posframe.y = posframe.y + 10;
		Main.window.setLocation(posframe);
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		posframe.x = posframe.x + 10;
		posframe.y = posframe.y - 10;
		Main.window.setLocation(posframe);
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		posframe.y = posframe.y + 10;
		Main.window.setLocation(posframe);
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		posframe.x = posframe.x - 5;
		posframe.y = posframe.y - 5;
		Main.window.setLocation(posframe);
	}

}


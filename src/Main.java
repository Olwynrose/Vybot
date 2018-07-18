
import java.awt.AWTException;
import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class Main {
	static JPanel pan;
	
	static int it_rand;
	static int n_buff_detect;
	static int n_buff_fait;
	static float[] ProbaClic;
	
	static int onglet;			// onglet actuel
	static int seq;				// séquence actuelle
	static int seq1;			// action actuelle
	
	static int ia;				// ia actuelle
	
	static Donnees donnees;
	static int idim, jdim;		// dimensions de la fenetre
	static int cond;
	static int typeInteface;	// détect le type d'interface; 0: normal, 1: Halloween, 2: Noel
	
	static int n_seq;
	static int n_seq1;
	static int[][][] Im_Text_1;
	static int[][][] Im_Text_2;
	static int[][][] Im_Text_3;
	static int[][][] Im_Text_4;
	static int[][][] Im_Text_5;
	static int[][][] Im_Text_6;
	static int[][][] Im_Text_7;
	static int[][][] Im_Text_8;
	static int[][][] Im_Text_9;
	static int[][][] Im_Text_10;
	static int[][][] Im_Text_11;
	static int[][][] Im_Text_12;
	static int[][][] Im_Text_13;
	static int[][][] Im_Text_14;
	static int[][][] Im_Text_15;
	static int[][][] Im_Text_16;
	static int[][][] Im_Text_17;
	static int[][][] Im_Text_18;
	static int iidim1;
	static int jjdim1;
	static int iidim2;
	static int jjdim2;
	static int iidim3;
	static int jjdim3;
	static int iidim4;
	static int jjdim4;
	static int iidim5;
	static int jjdim5;
	static int iidim6;
	static int jjdim6;
	static int iidim7;
	static int jjdim7;
	static int iidim8;
	static int jjdim8;
	static int iidim9;
	static int jjdim9;
	static int iidim10;
	static int jjdim10;
	static int iidim11;
	static int jjdim11;
	static int iidim12;
	static int jjdim12;
	static int iidim13;
	static int jjdim13;
	static int iidim14;
	static int jjdim14;
	static int iidim15;
	static int jjdim15;
	static int iidim16;
	static int jjdim16;
	static int iidim17;
	static int jjdim17;
	static int iidim18;
	static int jjdim18;
	static int iidim_uru;
	static int jjdim_uru;
	static int[][][] Im_map_uru;
	static int[][][][] Im_Cond;	// références ([indice ref,hauteur ref, largeur ref, couleur])
	static int[][] coor_Cond;	// coordonnées des zones à vérifier [indice zone, coordonnée(0hauteur,1largeur)]
	static int dim_cond;
	static int add_att; 		// ajoute un attend après un clic souris
	
	static JFrame window;		// fenetre
	
	static JLabel fondPanel;	// fond
	static ImageIcon iBouton;	// bouton
	static ImageIcon iMarche;	// config marche
	static ImageIcon iMarcheL;	// config marche
	static ImageIcon iMarches;	// config marche
	static ImageIcon iMarcheLs;	// config marche
	static ImageIcon iOpen;		// lancement du bot
	static ImageIcon iBot;		// bot
	static ImageIcon iScroll;	// scroll
	static ImageIcon iScrollR;	// scroll rouge
	static ImageIcon iScrollG;	// scroll vert
	
	
	static JFrame etalonChat;
	static JFrame etalonSelec;
	static JFrame etalonJeux;
	static JFrame etalonCarte;
	static JFrame etalonInt;
	static JFrame etalonVie;
	static JFrame etalonMana;
	static JPanel panEtalonChat;
	static JPanel panEtalonSelec;
	static JPanel panEtalonJeux;
	static JPanel panEtalonCarte;
	static JPanel panEtalonInt;
	static JPanel panEtalonVie;
	static JPanel panEtalonMana;
	static Recalage references;
	
	
	public static void main(String[] args) throws AWTException, InterruptedException, IOException 
	{
		n_buff_detect = 0;
		n_buff_fait = 0;
		add_att = 0;
		onglet = 1;
		ProbaClic = new float[9];
		idim = 650;
		jdim = 1000;
		int i;
		
		
		// séquences
		n_seq = 3;
		n_seq1 = 15;
		
		// condition personalisable
		dim_cond = 20;
		Im_Cond = new int[n_seq1][dim_cond][dim_cond][3];
		coor_Cond = new int [n_seq1][2];
		
		Main.donnees = new Donnees();
		
		Main.fondPanel = new JLabel(new ImageIcon("Icones/dallefond.png"));
		Main.iBouton = new ImageIcon("Icones/Bouton.png");
		Main.iMarche = new ImageIcon("Icones/Marche.png");
		Main.iMarcheL = new ImageIcon("Icones/MarcheL.png");
		Main.iMarches = new ImageIcon("Icones/Marche_s.png");
		Main.iMarcheLs = new ImageIcon("Icones/MarcheL_s.png");
		Main.iOpen = new ImageIcon("Icones/Open.gif");
		Main.iBot = new ImageIcon("Icones/Bot.gif");
		Main.iScroll = new ImageIcon("Icones/Roll.gif");
		Main.iScrollR = new ImageIcon("Icones/RollR.gif");
		Main.iScrollG = new ImageIcon("Icones/RollG.gif");
		
		
		JButton bStop;
		
		// charge les données d'entrainement
        
        BufferedReader br = new BufferedReader(new FileReader("Config/ProbaClic.txt"));
        StringBuilder sb = new StringBuilder();
        String line = "125";
        for(i=0;i<9;i++)
        {
			sb.append(line);
	        sb.append(System.lineSeparator());
	        line = br.readLine();
			ProbaClic[i] = Float.valueOf(line);
        }
        br.close();
        
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int jMax = gd.getDisplayMode().getWidth();
	
		    
		        Main.references = new Recalage();
			    
		     // ligne 1 chat
			    panEtalonChat = new JPanel();
			    panEtalonChat.setBackground(Color.WHITE); 
			    etalonChat = new JFrame();
			    etalonChat.setSize(400, 20);
			    etalonChat.setLocation(references.jdimChat, references.idimChat);
			    etalonChat.setUndecorated(true);
			    etalonChat.setVisible(true);
			    etalonChat.setResizable(false);
			    etalonChat.setAlwaysOnTop(true);
			    etalonChat.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			    etalonChat.setContentPane(panEtalonChat);

			    // Selection
			    panEtalonSelec = new JPanel();
			    panEtalonSelec.setBackground(Color.WHITE); 
			    etalonSelec = new JFrame();
			    etalonSelec.setSize(150, 25);
			    etalonSelec.setLocation(references.jdimSelec, references.idimSelec);
			    etalonSelec.setUndecorated(true);
			    etalonSelec.setVisible(true);
			    etalonSelec.setResizable(false);
			    etalonSelec.setAlwaysOnTop(true);
			    etalonSelec.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			    etalonSelec.setContentPane(panEtalonSelec);
			    
			    // Carte
			    panEtalonCarte = new JPanel();
			    panEtalonCarte.setBackground(Color.WHITE); 
			    etalonCarte = new JFrame();
			    etalonCarte.setSize(160, 160);
			    etalonCarte.setLocation(references.jdimCarte, references.idimCarte);
			    etalonCarte.setUndecorated(true);
			    etalonCarte.setVisible(true);
			    etalonCarte.setResizable(false);
			    etalonCarte.setAlwaysOnTop(true);
			    etalonCarte.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			    etalonCarte.setContentPane(panEtalonCarte);
			    
			    // Vie
			    panEtalonVie = new JPanel();
			    panEtalonVie.setBackground(Color.WHITE); 
			    etalonVie = new JFrame();
			    etalonVie.setSize(90, 16);
			    etalonVie.setLocation(references.jdimVie, references.idimVie);
			    etalonVie.setUndecorated(true);
			    etalonVie.setVisible(true);
			    etalonVie.setResizable(false);
			    etalonVie.setAlwaysOnTop(true);
			    etalonVie.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			    etalonVie.setContentPane(panEtalonCarte);
			    
			    // Mana
			    panEtalonMana = new JPanel();
			    panEtalonMana.setBackground(Color.WHITE); 
			    etalonMana = new JFrame();
			    etalonMana.setSize(90, 16);
			    etalonMana.setLocation(references.jdimVie+3, references.idimVie+32);
			    etalonMana.setUndecorated(true);
			    etalonMana.setVisible(true);
			    etalonMana.setResizable(false);
			    etalonMana.setAlwaysOnTop(true);
			    etalonMana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			    etalonMana.setContentPane(panEtalonCarte);
			    
			    // Corps chat (zone protégée)
			    panEtalonInt = new JPanel();
			    panEtalonInt.setBackground(Color.BLACK); 
			    etalonInt = new JFrame();
			    etalonInt.setSize(520, 150);
			    etalonInt.setLocation(references.jdimChat, references.idimChat-150);
			    etalonInt.setUndecorated(true);
			    etalonInt.setVisible(true);
			    etalonInt.setResizable(false);
			    etalonInt.setAlwaysOnTop(true);
			    etalonInt.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			    etalonInt.setContentPane(panEtalonInt);
			    
			    
			    Thread.sleep(300);
			    
			    etalonChat.dispose();
			    etalonSelec.dispose();
			    etalonCarte.dispose();
			    etalonVie.dispose();
			    etalonMana.dispose();
			    etalonInt.dispose();
			    
			    // Jeux
			    panEtalonJeux = new JPanel();
			    panEtalonJeux.setBackground(Color.WHITE); 
			    etalonJeux = new JFrame();
			    etalonJeux.setSize(1100, 500);
			    etalonJeux.setLocation(100, 150);
			    etalonJeux.setUndecorated(true);
			    etalonJeux.setVisible(true);
			    etalonJeux.setResizable(false);
			    etalonJeux.setAlwaysOnTop(true);
				etalonJeux.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				etalonJeux.setContentPane(panEtalonJeux);
			    
			    Thread.sleep(300);
			    etalonJeux.dispose();
	    
	    window = new JFrame();
		window.setTitle("Vb103");
		window.setSize(jdim, idim);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		window.setLocation(jMax-jdim, 0);
		
		pan = new JPanel();
		pan.setBackground(Color.BLACK); 
		pan.setLayout(null);
	    window.setContentPane(pan);
	    
	    
	    
	    while(true)
	    {
	    	switch(onglet)
	    	{
	    		case 1:
	    		{
	    			// page de démarrage
	    			window.setAlwaysOnTop(false);
	    		    window.setSize(jdim, idim);
	    			new PanSelec();
	    		}
	    		break;
	    		case 2:
	    		{
	    			// choix de la séquences
	    			window.setAlwaysOnTop(false);
	    			window.setSize(jdim, idim);
	    			new PanNewLearn();
	    		}
    			break;
	    		case 3:
	    		{
	    			// non utilisé (avant beta 1)
	    			window.setAlwaysOnTop(false);
	    			window.setSize(jdim, idim);
	    			new PanLearn();
	    		}
    			break;
	    		case 4:
	    		{
	    			// modification d'une séquence
	    			window.setAlwaysOnTop(false);
	    			window.setSize(jdim, idim);
	    			new PanLearn1(1);
	    		}
    			break;
	    		case 5:
	    		{
	    			// modification d'une condition
	    			window.setAlwaysOnTop(false);
	    			window.setSize(jdim, idim);
	    			new PanLearn2();
	    		}
    			break;
	    		case 6:
	    		{
	    			// modification d'une action spéciale
	    			window.setAlwaysOnTop(false);
	    			window.setSize(jdim, idim);
	    			new PanLearn1(2);
	    		}
    			break;
	    		case 7:
	    		{
	    			// choix du bot
	    			window.setAlwaysOnTop(true);
	    			window.setSize(135, 128);
	    			window.setLocation(jMax-135, 0);
	    			window.setLocation(references.jdimCarte-170, 0);
	    			new PanBot();
	    			window.setLocation(jMax-jdim, 0);
	    		}
    			break;
	    		case 8:
	    		{
	    			// bot actif
	    			window.setAlwaysOnTop(true);
	    			window.setSize(135, 128);
	    			window.setLocation(jMax-135, 0);
	    			window.setLocation(references.jdimCarte-170, 0);
	    			new Clic();
	    			window.setLocation(jMax-jdim, 0);
	    		}
    			break;
	    		case 9:
	    		{
	    			// étalonage
	    			window.setAlwaysOnTop(true);
	    			Main.iOpen.getImage().flush();
	    			window.setSize(135, 128);
	    			window.setLocation(jMax-135, 0);
	    			bStop = new JButton(Main.iOpen);
	    			bStop.setVerticalTextPosition(SwingConstants.CENTER);
	    			bStop.setHorizontalTextPosition(SwingConstants.CENTER);
	    			bStop.setBounds(0, 0, 130, 100);
	    			Main.pan.add(bStop);
	    			Main.pan.repaint();
	    			
	    			Thread.sleep(200);
	    			
	    			Main.references = new Recalage();
	    			
	    			// ligne 1 chat
				    etalonChat = new JFrame();
				    etalonChat.setSize(400, 20);
				    etalonChat.setLocation(references.jdimChat, references.idimChat);
				    etalonChat.setUndecorated(true);
				    etalonChat.setVisible(true);
				    etalonChat.setResizable(false);
				    etalonChat.setAlwaysOnTop(true);
				    etalonChat.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				    etalonChat.setContentPane(panEtalonChat);

				    // Selection
				    etalonSelec = new JFrame();
				    etalonSelec.setSize(150, 25);
				    etalonSelec.setLocation(references.jdimSelec, references.idimSelec);
				    etalonSelec.setUndecorated(true);
				    etalonSelec.setVisible(true);
				    etalonSelec.setResizable(false);
				    etalonSelec.setAlwaysOnTop(true);
				    etalonSelec.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				    etalonSelec.setContentPane(panEtalonSelec);
				    
				    // Carte
				    etalonCarte = new JFrame();
				    etalonCarte.setSize(160, 160);
				    etalonCarte.setLocation(references.jdimCarte, references.idimCarte);
				    etalonCarte.setUndecorated(true);
				    etalonCarte.setVisible(true);
				    etalonCarte.setResizable(false);
				    etalonCarte.setAlwaysOnTop(true);
				    etalonCarte.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				    etalonCarte.setContentPane(panEtalonCarte);
				    
				    // Vie
				    etalonVie = new JFrame();
				    etalonVie.setSize(90, 16);
				    etalonVie.setLocation(references.jdimVie, references.idimVie);
				    etalonVie.setUndecorated(true);
				    etalonVie.setVisible(true);
				    etalonVie.setResizable(false);
				    etalonVie.setAlwaysOnTop(true);
				    etalonVie.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				    etalonVie.setContentPane(panEtalonCarte);
				    
				    // Mana
				    etalonMana = new JFrame();
				    etalonMana.setSize(90, 16);
				    etalonMana.setLocation(references.jdimVie+3, references.idimVie+32);
				    etalonMana.setUndecorated(true);
				    etalonMana.setVisible(true);
				    etalonMana.setResizable(false);
				    etalonMana.setAlwaysOnTop(true);
				    etalonMana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				    etalonMana.setContentPane(panEtalonCarte);
				    
				    // Corps chat (zone protégée)
				    etalonInt = new JFrame();
				    etalonInt.setSize(520, 150);
				    etalonInt.setLocation(references.jdimChat, references.idimChat-150);
				    etalonInt.setUndecorated(true);
				    etalonInt.setVisible(true);
				    etalonInt.setResizable(false);
				    etalonInt.setAlwaysOnTop(true);
				    etalonInt.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				    etalonInt.setContentPane(panEtalonInt);
				    
				    
				    Thread.sleep(1500);
				    
				    etalonChat.dispose();
				    etalonSelec.dispose();
				    etalonCarte.dispose();
				    etalonVie.dispose();
				    etalonMana.dispose();
				    etalonInt.dispose();
				    
				    // Jeux
				    etalonJeux = new JFrame();
				    etalonJeux.setSize(1100, 500);
				    etalonJeux.setLocation(100, 150);
				    etalonJeux.setUndecorated(true);
				    etalonJeux.setVisible(true);
				    etalonJeux.setResizable(false);
				    etalonJeux.setAlwaysOnTop(true);
					etalonJeux.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					etalonJeux.setContentPane(panEtalonJeux);
				    
				    Thread.sleep(500);
				    etalonJeux.dispose();
				   
	    		    
	    			Main.onglet = 1;
	    			window.setLocation(jMax-jdim, 0);
	    		}
    			break;
	    		case 10:
	    		{
	    			// choix de l'ia (apprentissage)
	    			window.setSize(jdim, idim);
	    			window.setAlwaysOnTop(false);
	    			new PanNewIA();
	    		}
	    		break;
			    case 11:
				{
					// page de screen pour l'IA
					window.setAlwaysOnTop(true);
					window.setSize(135, 258);
					window.setLocation(jMax-135, 0);
					new PanIA1();
					window.setLocation(jMax-jdim, 0);
				}
				break;
			    case 12:
				{
					// page de screen pour la souris
					window.setAlwaysOnTop(true);
					window.setSize(135, 128);
					window.setLocation(jMax-135, 0);
					new PanSouris(1);
					window.setLocation(jMax-jdim, 0);
				}
				break;
			    case 13:
				{
					// page de screen pour la souris
					window.setAlwaysOnTop(true);
					window.setSize(135, 128);
					window.setLocation(jMax-135, 0);
					new PanSouris(2);
					window.setLocation(jMax-jdim, 0);
				}
				break;
			    case 14:
				{
					// page d'options
					// modification d'une action spéciale
	    			window.setAlwaysOnTop(false);
	    			window.setSize(jdim, idim);
	    			new PanOptions();
					window.setLocation(jMax-jdim, 0);
				}
				break;
	    	}
    	
	    pan.removeAll();
		
	    }
		
		
	    

	}

}


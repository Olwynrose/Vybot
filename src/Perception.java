import java.awt.AWTException;
import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Perception {

	Learning connaissance;
	
	// test
	JButton bScreen;
	JButton bNew;
	JButton bLearn;
	JButton bRetour;

	JFrame window;
	int cond;	// condition de sortie de la fenêtre
	int cond1;	// condition d'entrée/sorti du screen
	int selec;	// sélection d'une imagette (clic souris)
	int valid;	// validation d'une imagette
	int newIm;	// nouvelle image en mémoire (Im_selec)
	int compt_selec; // compte les sélections par screen
	int cond_learn;
	int coor_i;		// coordonées de l'objet trouvé
	int coor_j;		// coordonées de l'objet trouvé
	int score_max;
	
	int[][][] Im1;			// image extrait écran fiesta
	int[][][] Im2;			// image extrait écran fiesta
	int[][][] Im_aff;		// image à afficher
	int idim;		// dimension du screen
	int jdim;		// dimension du screen
	
	int rapp_cherche;
	
	public Perception(Learning apprentissage)
	{
		connaissance = apprentissage;
	}
	
	public void test() throws AWTException, InterruptedException
	{
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int jMax = gd.getDisplayMode().getWidth();
		int[] arrayimage;
		JPanel pan = new JPanel();
		JLabel lab = new JLabel();
		
		// conditions
		cond = 0;
		cond1 = 1;
		selec = 0;
		valid = 0;
		newIm = 0;
		compt_selec = 0;
		cond_learn = 0;
		
		rapp_cherche = 5;
		idim = 500;		// dimension du screen
		jdim = 1050;	// dimension du screen
		int screeni0 = 150;	// position du screen
		int screenj0 = 100;	// position du screen
		
		// buffeurs pour les screens
		ImageIcon ii;
		Rectangle captureRect = new Rectangle(screenj0, screeni0, jdim, idim);
		Robot robot = new Robot();
		BufferedImage screenFiesta;
		
		// images 
		
		Im_aff = new int[idim][jdim][3];
		arrayimage = new int[idim*jdim*3];	
		
		bScreen = new JButton(Main.iBouton);
		bScreen.setBackground(Color.BLUE);
		bScreen.setText("Test");
		bScreen.setVerticalTextPosition(SwingConstants.CENTER);
		bScreen.setHorizontalTextPosition(SwingConstants.CENTER);
		bScreen.setBounds(0, 0, 130, 25);
		bScreen.setForeground(Color.BLACK);
		Main.pan.add(bScreen);
		bScreen.addActionListener(new AScreen());
		
		bNew = new JButton(Main.iBouton);
		bNew.setBackground(Color.BLUE);
		bNew.setText("Valider");
		bNew.setVerticalTextPosition(SwingConstants.CENTER);
		bNew.setHorizontalTextPosition(SwingConstants.CENTER);
		bNew.setBounds(0, 25, 130, 25);
		bNew.setForeground(Color.BLACK);
		Main.pan.add(bNew);
		bNew.addActionListener(new ANew());
		
		bLearn = new JButton(Main.iBouton);
		bLearn.setBackground(Color.BLUE);
		bLearn.setText("Apprentissage");
		bLearn.setVerticalTextPosition(SwingConstants.CENTER);
		bLearn.setHorizontalTextPosition(SwingConstants.CENTER);
		bLearn.setBounds(0, 50, 130, 25);
		bLearn.setForeground(Color.BLACK);
		Main.pan.add(bLearn);
		bLearn.addActionListener(new ALearn());
		bLearn.setEnabled(false);
		
		bRetour = new JButton(Main.iBouton);
		bRetour.setBackground(Color.BLUE);
		bRetour.setText("Retour");
		bRetour.setVerticalTextPosition(SwingConstants.CENTER);
		bRetour.setHorizontalTextPosition(SwingConstants.CENTER);
        bRetour.setBounds(0, 75, 130, 25);
        bRetour.setForeground(Color.BLACK);
		Main.pan.add(bRetour);
		bRetour.addActionListener(new ARetour());
		
		Main.pan.repaint(); 
		Main.pan.validate();
				    
		while(cond == 0)
		{
			Thread.sleep(200);
			if(cond1 == 0)
			{
				selec = 0;
				compt_selec = 0;
				bScreen.setText("Suivant");
	    		screenFiesta = robot.createScreenCapture(captureRect);
	            Im1 = convertTo2DUsingGetRGB(screenFiesta);
	            Thread.sleep(400);
	            screenFiesta = robot.createScreenCapture(captureRect);
	            Im2 = convertTo2DUsingGetRGB(screenFiesta);
	            
	            // recherche les objets
	            
	            recherche();
	            // affiche les objets trouvés
	            System.out.println( coor_i + " " + coor_j);
	    		for(int i = 0; i < jdim; i++)
	    		{
	    			for(int j = 0; j < idim; j++) 
	    			{
	    				for(int band = 0; band < 3; band++)
	    				{
	    					arrayimage[((j*jdim)+i)*3+band] = Im_aff[j][i][band];
	    				}
	    				if(j==coor_i || i==coor_j)
	    				{
	    					arrayimage[((j*jdim)+i)*3+0] = 255;
	    					arrayimage[((j*jdim)+i)*3+1] = 0;
	    					arrayimage[((j*jdim)+i)*3+2] = 0;
	    				}
	    			}
	    		}
	    		ii = new ImageIcon(getImageFromArray(arrayimage, jdim, idim));
	    		lab.setIcon(ii);
	    		pan.setBounds(0, 0, jdim,idim);
	    		pan.add(lab);
	    		pan.repaint();
	    		pan.revalidate();
	    		
	    		window = new JFrame();
				window.setSize(jdim, idim);
				window.setResizable(false);
				window.setAlwaysOnTop(true);
				window.setUndecorated(true);
				window.setVisible(true);
				window.setLocation(jMax-jdim-135, 0);
				window.setContentPane(pan);
	    	    
			
				
	    		
				while(cond1 == 0)
				{
					Thread.sleep(200);
					
					
				} // fin while cond1 == 0
				
				newIm = 0;
				window.setVisible(false); 
				window.dispose();
				bScreen.setText("Test");
				
				
			} // fin if cond1
			
		} // fin cond
		
		
	} // fin test
	
	public void farm() throws AWTException, InterruptedException
	{
		// conditions
		cond = 0;
		cond1 = 1;
		selec = 0;
		valid = 0;
		newIm = 0;
		compt_selec = 0;
		cond_learn = 0;
		int etat_objet = 0;	// état de l'objet cible : 0:rien, 1:selectionné, 2:farmé
		int[][][] Im_obj = new int[2][25][3];
		int[] moy = new int[3];
		int obj_i0 = Main.references.idimSelec+25;
		int obj_j0 = Main.references.jdimSelec-20;
		int th_detect = 10000;		// seuil de détection des objets
		
		// marche
		double a = 0.0;	// hazard 1
		double b = 0.0;	// hazard 2
		int dir = 0;	// direction
		int time = 0;	// nombre de détections
		int d1 = 0;		// mémoire pour la position initiale
		int d2 = 0;		// mémoire pour la position initiale
		int d3 = 0;		// mémoire pour la position initiale
		int d4 = 0;		// mémoire pour la position initiale
				
		rapp_cherche = 5;
		idim = 500;		// dimension du screen
		jdim = 950;	// dimension du screen
		int screeni0 = 150;	// position du screen
		int screenj0 = 100;	// position du screen
		
		// buffeurs pour les screens
		Rectangle captureRect = new Rectangle(screenj0, screeni0, jdim, idim);
		Rectangle captObj = new Rectangle(obj_j0, obj_i0, 25, 2);
		Robot robot = new Robot();
		BufferedImage screenFiesta;
		
		// images 
		
		Im_aff = new int[idim][jdim][3];
		
		bScreen = new JButton(Main.iBouton);
		bScreen.setBackground(Color.BLUE);
		bScreen.setText("Farm");
		bScreen.setVerticalTextPosition(SwingConstants.CENTER);
		bScreen.setHorizontalTextPosition(SwingConstants.CENTER);
		bScreen.setBounds(0, 0, 130, 130);
		bScreen.setForeground(Color.BLACK);
		Main.pan.add(bScreen);
		bScreen.addActionListener(new AScreen());
		
		
		Main.pan.repaint(); 
		Main.pan.validate();
				    
		while(cond == 0)
		{
			Thread.sleep(200);
			if(cond1 == 0)
			{
				selec = 0;
				compt_selec = 0;
				bScreen.setText("Stop");
	    		
				 Thread.sleep(50);
	    		robot.mouseMove(coor_j+screenj0,coor_i+screeni0);
	            Thread.sleep(100);
	            robot.mousePress(InputEvent.BUTTON1_MASK);
	            Thread.sleep(40);
	            robot.mouseRelease(InputEvent.BUTTON1_MASK);
	            Thread.sleep(150);
	            
				while(cond1 == 0)
				{
					score_max=th_detect+1;
					while(score_max>th_detect && time<4)
					{
						// recherche les objets
						Thread.sleep(80);
						screenFiesta = robot.createScreenCapture(captureRect);
			            Im1 = convertTo2DUsingGetRGB(screenFiesta);
			            Thread.sleep(400);
			            screenFiesta = robot.createScreenCapture(captureRect);
			            Im2 = convertTo2DUsingGetRGB(screenFiesta);
			            
			            recherche();
			            
			            if(score_max>th_detect)
			            {
				            Thread.sleep(50);
				    		robot.mouseMove(coor_j+screenj0,coor_i+screeni0);
				            Thread.sleep(100);
				            robot.mousePress(InputEvent.BUTTON1_MASK);
				            Thread.sleep(60);
				            robot.mouseRelease(InputEvent.BUTTON1_MASK);
				            Thread.sleep(40);
				            robot.keyPress(KeyEvent.VK_S);
				            Thread.sleep(35);
				            robot.keyRelease(KeyEvent.VK_S);
				            Thread.sleep(350);
				            screenFiesta = robot.createScreenCapture(captObj);
				            Im_obj = convertTo2DUsingGetRGB(screenFiesta);
				            moy[0] = 0;
				            moy[1] = 0;
				            moy[2] = 0;
		            		for(int i1=0;i1<2;i1++)
				            {
				            	for(int j1=0;j1<25;j1++)
					            {
				            		for(int k1=0;k1<3;k1++)
						            {
						            	moy[k1] = moy[k1] + Im_obj[i1][j1][k1];
						            }
					            }
				            }
		            		if(moy[0]==12475 && moy[1]==5725 && moy[2]==0)
		            		{
		            			Thread.sleep(40);
		            			robot.mousePress(InputEvent.BUTTON1_MASK);
					            Thread.sleep(60);
					            robot.mouseRelease(InputEvent.BUTTON1_MASK);
					            for(int it=0;it<50;it++)
					            {
						            screenFiesta = robot.createScreenCapture(captObj);
						            Im_obj = convertTo2DUsingGetRGB(screenFiesta);
						            moy[0] = 0;
						            moy[1] = 0;
						            moy[2] = 0;
				            		for(int i1=0;i1<2;i1++)
						            {
						            	for(int j1=0;j1<25;j1++)
							            {
						            		for(int k1=0;k1<3;k1++)
								            {
								            	moy[k1] = moy[k1] + Im_obj[i1][j1][k1];
								            }
							            }
						            }
				            		if(moy[0]==12475 && moy[1]==5725 && moy[2]==0)
				            		{
				            			etat_objet = 1;
				            		}
				            		else
				            		{
				            			if(moy[0]==2600 && moy[1]==650 && moy[2]==0)
					            		{
				            				etat_objet = 2;
					            		}
				            			else
				            			{
				            				etat_objet = 0;
				            			}
				            		}
				            		//System.out.println(moy[0]+" "+moy[1]+" "+moy[2]);
						            Thread.sleep(250);
						            if(etat_objet==2)
						            {
						            	it = 50;
						            }
					            }
					            
		            		}
				            
				            
				            robot.keyPress(KeyEvent.VK_0);
				            Thread.sleep(45);
				            robot.keyRelease(KeyEvent.VK_0);
				            Thread.sleep(145);
				            robot.keyPress(KeyEvent.VK_0);
				            Thread.sleep(45);
				            robot.keyRelease(KeyEvent.VK_0);
				            Thread.sleep(145);
				            robot.keyPress(KeyEvent.VK_0);
				            Thread.sleep(45);
				            robot.keyRelease(KeyEvent.VK_0);
				            Thread.sleep(145);
				            robot.keyPress(KeyEvent.VK_0);
				            Thread.sleep(45);
				            robot.keyRelease(KeyEvent.VK_0);
				            Thread.sleep(145);
				            robot.keyPress(KeyEvent.VK_F1);
				            Thread.sleep(45);
				            robot.keyRelease(KeyEvent.VK_F1);
				            Thread.sleep(120);
				            robot.keyPress(KeyEvent.VK_ESCAPE);
				            Thread.sleep(45);
				            robot.keyRelease(KeyEvent.VK_ESCAPE);
			            }
			            time = time + 1;
					}
					time = 0;
					
					a = Math.random();
					b = Math.random();
					
					if(b>0.5)
					{
						// pas aléatoire sur les 8 directions
						if(a<0.125)
						{
							dir = 1;
							d1 = d1+1;
						}
						if(a>=0.125 && a<0.25)
						{
							dir = 2;
							d2 = d2+1;
						}
						if(a>=0.25 && a<0.375)
						{
							dir = 3;
							d3 = d3+1;
						}
						if(a>=0.375 && a<0.5)
						{
							dir = 4;
							d4 = d4+1;
						}
						if(a>=0.5 && a<0.625)
						{
							dir = 5;
							d1 = d1-1;
						}
						if(a>=0.625 && a<0.75)
						{
							dir = 6;
							d2 = d2-1;
						}
						if(a>=0.75 && a<0.825)
						{
							dir = 7;
							d3 = d3-1;
						}
						if(a>=0.825 && a<1)
						{
							dir = 8;
							d4 = d4-1;
						}
					}
					else // si b>0.5
					{
						// pas en direction de l'origine (aléatoirement sur une des 4 directions non orientées)
						if(a<0.25)
						{
							if(d1>0)
							{
								dir = 5;
								d1 = d1-1;
							}
							else
							{
								dir = 1;
								d1 = d1+1;
							}
						}
						if(a>=0.25 && a<0.5)
						{
							if(d2>0)
							{
								dir = 6;
								d2 = d2-1;
							}
							else
							{
								dir = 2;
								d2 = d2+1;
							}
						}
						if(a>=0.5 && a<0.75)
						{
							if(d3>0)
							{
								dir = 7;
								d3 = d3-1;
							}
							else
							{
								dir = 3;
								d3 = d3+1;
							}
						}
						if(a>=0.75)
						{
							if(d4>0)
							{
								dir = 8;
								d4 = d4-1;
							}
							else
							{
								dir = 4;
								d4 = d4+1;
							}
						}
					} // fin du choix de la direction
					
					
// ================ directions =======================================
					
					// la direction a été déterminée précédement
					// le bot marche dans la direction choisie
					if(dir==1) // sud
					{
						Thread.sleep(50);
						robot.keyPress(Main.donnees.marche[3]);
						//System.out.println("gauche");
					}
			        if(dir==10) // sud
					{
						Thread.sleep(50);
						robot.keyPress(Main.donnees.marche[3]);
						//System.out.println("gauche");
					}
					if(dir==7) // ouest
					{
						Thread.sleep(50);
						robot.keyPress(Main.donnees.marche[0]);
						//System.out.println("haut");
					}
					if(dir==5 || dir==9) // nord
					{
						Thread.sleep(50);
						robot.keyPress(Main.donnees.marche[2]);
						//System.out.println("droite");
					}
					if(dir==3 || dir==11) // est
					{
						Thread.sleep(50);
						robot.keyPress(Main.donnees.marche[1]);
						//System.out.println("bas");
					}
					if(dir==8) // sud-ouest
					{
						Thread.sleep(50);
						robot.keyPress(Main.donnees.marche[3]);
						//System.out.println("gauche");
						robot.keyPress(Main.donnees.marche[0]);
						//System.out.println("haut");
					}
					if(dir==6) // nord-ouest
					{
						Thread.sleep(50);
						robot.keyPress(Main.donnees.marche[2]);
						//System.out.println("droite");
						robot.keyPress(Main.donnees.marche[0]);
						//System.out.println("haut");
					}
					if(dir==4 || dir==12) // nord-est
					{
						Thread.sleep(50);
						robot.keyPress(Main.donnees.marche[2]);
						//System.out.println("droite");
						robot.keyPress(Main.donnees.marche[1]);
						//System.out.println("bas");
					}
					if(dir==2) // sud-est
					{
						Thread.sleep(50);
						robot.keyPress(Main.donnees.marche[3]);
						//System.out.println("gauche");
						robot.keyPress(Main.donnees.marche[1]);
						//System.out.println("bas");
					}
					
					Thread.sleep(3500);
					
					if(dir==1) // sud
					{
						Thread.sleep(50);
						robot.keyRelease(Main.donnees.marche[3]);
						//System.out.println("/gauche");
					}
			        if(dir==10) // sud
					{
						Thread.sleep(50);
						robot.keyRelease(Main.donnees.marche[3]);
						//System.out.println("/gauche");
					}
					if(dir==7) // ouest
					{
						Thread.sleep(50);
						robot.keyRelease(Main.donnees.marche[0]);
						//System.out.println("/haut");
					}
					if(dir==5 || dir==9) // nord
					{
						Thread.sleep(50);
						robot.keyRelease(Main.donnees.marche[2]);
						//System.out.println("/droite");
					}
					if(dir==3 || dir==11) // est
					{
						Thread.sleep(50);
						robot.keyRelease(Main.donnees.marche[1]);
						//System.out.println("/bas");
					}
					if(dir==8) // sud-ouest
					{
						Thread.sleep(50);
						robot.keyRelease(Main.donnees.marche[3]);
						//System.out.println("/gauche");
						robot.keyRelease(Main.donnees.marche[0]);
						//System.out.println("/haut");
					}
					if(dir==6) // nord-ouest
					{
						Thread.sleep(50);
						robot.keyRelease(Main.donnees.marche[2]);
						//System.out.println("/droite");
						robot.keyRelease(Main.donnees.marche[0]);
						//System.out.println("/haut");
					}
					if(dir==4 || dir==12) // nord-est
					{
						Thread.sleep(50);
						robot.keyRelease(Main.donnees.marche[2]);
						//System.out.println("/droite");
						robot.keyRelease(Main.donnees.marche[1]);
						//System.out.println("/bas");
					}
					if(dir==2) // sud-est
					{
						Thread.sleep(50);
						robot.keyRelease(Main.donnees.marche[3]);
						//System.out.println("/gauche");
						robot.keyRelease(Main.donnees.marche[1]);
						//System.out.println("/bas");
					}
					
// ================ fin directions =======================================
					
				} // fin while cond1 == 0
				
				newIm = 0;
				bScreen.setText("Farm");
				
				
			} // fin if cond1
			
		} // fin cond
		
		
	} // fin farm
	
	public void recherche()
	{
		// parcours la foret de décision pour rechercher les objets
		
		for(int i = 0; i < idim; i++)
		{
			for(int j = 0; j < jdim; j++) 
			{
				for(int band = 0; band < 3; band++)
				{
					Im_aff[i][j][band] = Im2[i][j][band];
				}
			}
		}
		
		Decision decision;
		int compteur;		// compte le nombre d'arbre positifs
		int[][] score;		// carte des scores de détection (proche du personnage et compteur élevé
		
		int buf_score = -10000;	// buffeur pour la détection du score maximum
		
		int rl = connaissance.rapp_learn;
		int rc = rapp_cherche;
		int iidim = idim/connaissance.rapp_learn;	// dimensions réduites
		int jjdim = jdim/connaissance.rapp_learn;	// dimensions réduites
		
		int iii = (iidim-connaissance.l_learn)/rapp_cherche;	// nombre de patch cherché par colonnes
		int jjj = (jjdim-connaissance.l_learn)/rapp_cherche;		// nombre de patch cherchés par lignes
		int l_min = 0;
		int l_max = connaissance.l_learn;
		
		score = new int[iii+rc][jjj+rc];
		int[][][] Img1 = new int[l_max-l_min][l_max-l_min][3];
		int[][][] Img2 = new int[l_max-l_min][l_max-l_min][3];
		
		// boucle sur les imagettes 
		for(int ii = 0; ii < iii; ii++)
		{
			for(int jj = 0; jj < jjj; jj++) 
			{
				// récupère l'imagette
				for(int i = l_min; i < l_max; i++)
				{
					for(int j = l_min; j < l_max; j++) 
					{
						for(int band = 0; band < 3; band++)
						{
							Img1[i+l_min][j+l_min][band] =Im1[rl*rc*ii+i*rl][rl*rc*jj+j*rl][band];
							Img2[i+l_min][j+l_min][band] =Im2[rl*rc*ii+i*rl][rl*rc*jj+j*rl][band];
						}
					}
				}
				compteur = 0;
				for(int n = 0; n < connaissance.n_arbres; n++)
				{
					decision = connaissance.foret[n];
					while(decision.neud>0)
					{
						//System.out.println("neud" + decision.neud);
						decision = decision.test(Img1, Img2);
					}
					
					//System.out.println("classe" + decision.classe);
					if(decision.classe==1)
					{
						compteur = compteur + 1;
						/*for(int i = 0; i < connaissance.l_learn*rl; i++)
						{
							for(int j = 0; j < connaissance.l_learn*rl; j++)
							{
								Im_aff[rl*rc*ii+i][rl*rc*jj+j][1] = (5*Im_aff[rl*rc*ii+i][rl*rc*jj+j][1] + 255)/6;
							}
						}*/
					}
				}
				for(int li = 0; li<rc; li++)
				{
					for(int lj = 0; lj<rc; lj++)
					{
						score[ii+li][jj+lj] = score[ii+li][jj+lj] - Math.abs(3*ii-2*iii) - Math.abs(4*jj-2*jjj) + 100*compteur;
					}
				}
				
				
				// affichage des zones les plus probables
				if(compteur>(3*connaissance.n_arbres)/4)
				{
					for(int i = 0; i < connaissance.l_learn*rl; i++)
					{
						for(int j = 0; j < connaissance.l_learn*rl; j++)
						{
							Im_aff[rl*rc*ii+i][rl*rc*jj+j][1] = (Im_aff[rl*rc*ii+i][rl*rc*jj+j][1] + 255)/2;
						}
					}
				}
				
			}// fin for j
		}// fin for i
		for(int ii = 0; ii < iii+rc; ii++)
		{
			for(int jj = 0; jj < jjj+rc; jj++) 
			{
				if(score[ii][jj]>buf_score)
				{
					buf_score = score[ii][jj];
					coor_i = rl*rc*ii+(rl*rc/2);
					coor_j = rl*rc*jj+(rl*rc/2);
					//System.out.println(score + " " + coor_i + " " + coor_j);
				}
			}
		}
		score_max = buf_score;
		System.out.println(buf_score + " " + coor_i + " " + coor_j);
	}
	
	
	public class AScreen implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			cond1 = (cond1 + 1)%2;
		}
	}
	
	public class ANew implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			
		}
	}
	
	public class ALearn implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			
		}
	}
	
	public class ARetour implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			cond = 1;
			Main.onglet = 10;
		}
	}
	
	private static int[][][] convertTo2DUsingGetRGB(BufferedImage image) {
	      int width = image.getWidth();
	      int height = image.getHeight();
	      int[][][] result = new int[height][width][3];
	      //Color coul;

	      for (int row = 0; row < height; row++) {
	         for (int col = 0; col < width; col++) {
	        	 int clr=  image.getRGB(col,row); 
	        	 result[row][col][0]   = (clr & 0x00ff0000) >> 16;
	         result[row][col][1]  = (clr & 0x0000ff00) >> 8;
	         result[row][col][2]   =  clr & 0x000000ff;
	         
	         }
	         
	      }
	      return result;
	   } // fin convert
	
	public static Image getImageFromArray(int[] pixels, int width, int height)
	{
		BufferedImage image =
				new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		WritableRaster raster = (WritableRaster) image.getData();
		raster.setPixels(0, 0, width, height, pixels);
		image.setData(raster);
		return image;
	}	
	
}

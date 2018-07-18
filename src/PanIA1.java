import java.awt.AWTException;
import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanIA1 
{
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
	
	int max_selec;	// nombre max d'imagettes par screen
	int i_selec;
	int j_selec;
	int l_selec;	// largeur des images sélectionnées (pour l'affichage)
	int l_learn;	// largeur des images pour l'apprentissage
	int n_learn;	// nombre d'images pour l'apprentissage 
	int rap_true;	// inverse de la proportion d'images positives
	int rapp_learn;	// rapport entre la taille réelle et la taille des images d'apprentissage
	
	public PanIA1() throws InterruptedException, AWTException
	{
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int jMax = gd.getDisplayMode().getWidth();
		int[] arrayimage;
		int[] array_selec;
		JPanel pan = new JPanel();
		JLabel lab = new JLabel();
		JPanel pan_selec = new JPanel();
		JLabel lab_selec = new JLabel();
		
		// conditions
		cond = 0;
		cond1 = 1;
		selec = 0;
		valid = 0;
		newIm = 0;
		compt_selec = 0;
		cond_learn = 0;
		
		// paramètres des données d'apprentissage
		l_learn = 20; 
		rapp_learn = 3;
		l_selec = rapp_learn*l_learn;
		int n_true = 10;
		rap_true = 3;
		
		n_learn = n_true*rap_true;
		
		max_selec = 4;
		int compt_selec1 = 0;
		int compt_learn = -1;
		int idim = 500;		// dimension du screen
		int jdim = 950;	// dimension du screen
		int screeni0 = 150;	// position du screen
		int screenj0 = 100;	// position du screen
		
		// buffeurs pour les screens
		ImageIcon ii;
		Rectangle captureRect = new Rectangle(screenj0, screeni0, jdim, idim);
		Robot robot = new Robot();
		BufferedImage screenFiesta;
		
		// images 
		int[][][] Im1;			// image extrait écran fiesta
		int[][][] Im2;			// image extrait écran fiesta
		int[][][] Im_aff;		// image à afficher
		Im_aff = new int[idim][jdim][3];
		int[][][] Im_selec;		// image sélectionnée
		int[][][][][] data_learn;	// base d'apprentissage (taille de la base, image 1-2, dimensions image)
		data_learn = new int[n_learn][2][l_learn][l_learn][3];
		
		Im_selec = new int[l_selec][l_selec][3];
		arrayimage = new int[idim*jdim*3];
		array_selec = new int[l_selec*l_selec*3];
		
		// buffeurs et compteurs
		int bufi_selec = 0;	// buffeur pour la selection (évite les problèmes de threading)
		int bufj_selec = 0;	// buffeur pour la selection (évite les problèmes de threading)
		int dist = 0;		// distance pour la recherche stochastique des non-éléments
		int buf_d = 0;		// buffeur : distance pour la recherche stochastique des non-éléments
		int[] vect_iselec = new int[n_learn]; // coordonnées des imagettes
		int[] vect_jselec = new int[n_learn]; // coordonnées des imagettes
		
		
		bScreen = new JButton(Main.iBouton);
		bScreen.setBackground(Color.BLUE);
		bScreen.setText("Capture");
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
	            
	    		for(int i = 0; i < jdim; i++)
	    		{
	    			for(int j = 0; j < idim; j++) 
	    			{
	    				for(int band = 0; band < 3; band++)
	    				{
	    					arrayimage[((j*jdim)+i)*3+band] = Im2[j][i][band];
	    					Im_aff[j][i][band] = Im2[j][i][band];
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
	    	    
				lab.addMouseListener(new java.awt.event.MouseAdapter()
				{
			    	public void mouseEntered(java.awt.event.MouseEvent evt)
			    		{
			    			
			    		}
			    	 public void mouseExited(java.awt.event.MouseEvent evt)
			    	 	{

			    	    }
			    	 public void mousePressed(java.awt.event.MouseEvent evt)
			    	 	{
			    		 	selec = 1;
			    		 	i_selec = evt.getY();
			    		 	j_selec = evt.getX();
			    	    }
				});
				
	    		
				while(cond1 == 0)
				{
					Thread.sleep(200);
					
					if(selec == 1)
					{
						newIm = 1;
						i_selec = Math.min(idim-l_selec, Math.max(0, i_selec-l_selec/2));
						j_selec = Math.min(jdim-l_selec, Math.max(0, j_selec-l_selec/2));
						bufi_selec = i_selec;
						bufj_selec = j_selec;
						for(int i=0;i<l_selec;i++)
						{
							for(int j=0;j<l_selec;j++)
							{
								for(int k=0;k<3;k++)
								{
									Im_selec[i][j][k] = Im2[i+i_selec][j+j_selec][k];
								}
							}
						}
						
						for(int i = 0; i < l_selec; i++)
			    		{
			    			for(int j = 0; j < l_selec; j++) 
			    			{
			    				for(int band = 0; band < 3; band++)
			    				{
			    					array_selec[((j*l_selec)+i)*3+band] = Im_selec[j][i][band];
			    				}
			    					
			    			}
			    		}
						Main.pan.remove(pan_selec);
						pan_selec.removeAll();
			    		ii = new ImageIcon(getImageFromArray(array_selec, l_selec, l_selec));
			    		lab_selec.setIcon(ii);
			    		pan_selec.setBounds(0, 100, l_selec,l_selec);

			    		pan_selec.add(lab_selec);
			    		pan_selec.repaint();
			    		pan_selec.revalidate();
			    		Main.pan.add(pan_selec);
			    		Main.pan.repaint(); 
			    		Main.pan.validate();
						selec = 0;
						Thread.sleep(50);
					} // fin selec == 1
					
					if(valid == 1)
					{
						compt_learn = compt_learn +1;
						vect_iselec[compt_learn] = bufi_selec;
						vect_jselec[compt_learn] = bufj_selec;
						// place les rectangles sur le screen
						for(int i=0;i<l_selec;i++)
						{
							Im_aff[i+bufi_selec][bufj_selec+1][0] = 0;
							Im_aff[i+bufi_selec][l_selec+bufj_selec-1][0] = 0;
							Im_aff[bufi_selec+1][i+bufj_selec][0] = 0;
							Im_aff[l_selec+bufi_selec-1][i+bufj_selec][0] = 0;
							Im_aff[i+bufi_selec][bufj_selec+1][1] = 255;
							Im_aff[i+bufi_selec][l_selec+bufj_selec-1][1] = 255;
							Im_aff[bufi_selec+1][i+bufj_selec][1] = 255;
							Im_aff[l_selec+bufi_selec-1][i+bufj_selec][1] = 255;
							Im_aff[i+bufi_selec][bufj_selec+1][2] = 0;
							Im_aff[i+bufi_selec][l_selec+bufj_selec-1][2] = 0;
							Im_aff[bufi_selec+1][i+bufj_selec][2] = 0;
							Im_aff[l_selec+bufi_selec-1][i+bufj_selec][2] = 0;
							
							Im_aff[i+bufi_selec][bufj_selec+2][0] = 0;
							Im_aff[i+bufi_selec][l_selec+bufj_selec-2][0] = 0;
							Im_aff[bufi_selec+2][i+bufj_selec][0] = 0;
							Im_aff[l_selec+bufi_selec-2][i+bufj_selec][0] = 0;
							Im_aff[i+bufi_selec][bufj_selec+2][1] = 255;
							Im_aff[i+bufi_selec][l_selec+bufj_selec-2][1] = 255;
							Im_aff[bufi_selec+2][i+bufj_selec][1] = 255;
							Im_aff[l_selec+bufi_selec-2][i+bufj_selec][1] = 255;
							Im_aff[i+bufi_selec][bufj_selec+2][2] = 155;
							Im_aff[i+bufi_selec][l_selec+bufj_selec-2][2] = 155;
							Im_aff[bufi_selec+2][i+bufj_selec][2] = 155;
							Im_aff[l_selec+bufi_selec-2][i+bufj_selec][2] = 155;
						}
						
						// affichage
						for(int i = 0; i < jdim; i++)
			    		{
			    			for(int j = 0; j < idim; j++) 
			    			{
			    				for(int band = 0; band < 3; band++)
			    				{
			    					arrayimage[((j*jdim)+i)*3+band] = Im_aff[j][i][band];
			    				}
			    					
			    			}
			    		}
			    		ii = new ImageIcon(getImageFromArray(arrayimage, jdim, idim));
			    		lab.setIcon(ii);
			    		pan.setBounds(0, 0, jdim,idim);
			    		pan.add(lab);
			    		pan.repaint();
			    		pan.revalidate();
			    		
			    		// ajoute à la base d'apprentissage
			    		for(int i = 0; i < l_learn; i++)
			    		{
			    			for(int j = 0; j < l_learn; j++) 
			    			{
			    				for(int band = 0; band < 3; band++)
			    				{
			    					data_learn[compt_learn][0][i][j][band] = Im1[rapp_learn*i+i_selec][rapp_learn*j+j_selec][band];
			    					data_learn[compt_learn][1][i][j][band] = Im2[rapp_learn*i+i_selec][rapp_learn*j+j_selec][band];
			    				}
			    					
			    			}
			    		}
			    		
			    		if(compt_learn == n_learn/rap_true-1)
			    		{
			    			cond1 = 1;
			    			cond_learn = 1;
			    		}
			    		compt_selec = compt_selec + 1;
						valid = 0;
						newIm = 0;
						if(compt_selec==max_selec)
						{
							cond1 = 1;
						}
					} // fin valid ==1
					
					
				} // fin while cond1 == 0
				
				if(compt_selec>0)
				{
					// cherche des patchs sans items
					compt_selec1 = 0;
					while(compt_selec1<compt_selec*(rap_true-1))
					{
						buf_d = 0;
						while(buf_d<2*l_selec*l_selec)
						{
							// prend une position aléatoire
							bufi_selec = (int) ( (double) (idim-l_selec-1)*Math.random());
							bufj_selec = (int) ( (double) (jdim-l_selec-1)*Math.random());
							buf_d = jdim*jdim;
							// calcul la distance aux images sélectionnées
							for(int i = compt_learn-compt_selec+1;i<compt_learn+1;i++)
							{
								// distance entre les coordonnées testée et l'imagette compt_learn-compt_selec
								dist = (bufi_selec-vect_iselec[i])*(bufi_selec-vect_iselec[i]);
								dist = dist + (bufj_selec-vect_jselec[i])*(bufj_selec-vect_jselec[i]);
								
								if(dist<buf_d)
								{
									buf_d = dist;
								}
							}
						} // while buf_d<l_selec*l_selec 
						vect_iselec[(n_learn/rap_true)+compt_learn-compt_selec1] = bufi_selec;
						vect_jselec[(n_learn/rap_true)+compt_learn-compt_selec1] = bufj_selec;
						
						for(int i=0;i<l_selec;i++)
						{
							Im_aff[i+bufi_selec][bufj_selec+1][0] = 255;
							Im_aff[i+bufi_selec][l_selec+bufj_selec-1][0] = 255;
							Im_aff[bufi_selec+1][i+bufj_selec][0] = 255;
							Im_aff[l_selec+bufi_selec-1][i+bufj_selec][0] = 255;
							Im_aff[i+bufi_selec][bufj_selec+1][1] = 155;
							Im_aff[i+bufi_selec][l_selec+bufj_selec-1][1] = 155;
							Im_aff[bufi_selec+1][i+bufj_selec][1] = 155;
							Im_aff[l_selec+bufi_selec-1][i+bufj_selec][1] = 155;
							Im_aff[i+bufi_selec][bufj_selec+1][2] = 0;
							Im_aff[i+bufi_selec][l_selec+bufj_selec-1][2] = 0;
							Im_aff[bufi_selec+1][i+bufj_selec][2] = 0;
							Im_aff[l_selec+bufi_selec-1][i+bufj_selec][2] = 0;
							
							Im_aff[i+bufi_selec][bufj_selec+2][0] = 255;
							Im_aff[i+bufi_selec][l_selec+bufj_selec-2][0] = 255;
							Im_aff[bufi_selec+2][i+bufj_selec][0] = 255;
							Im_aff[l_selec+bufi_selec-2][i+bufj_selec][0] = 255;
							Im_aff[i+bufi_selec][bufj_selec+2][1] = 0;
							Im_aff[i+bufi_selec][l_selec+bufj_selec-2][1] = 0;
							Im_aff[bufi_selec+2][i+bufj_selec][1] = 0;
							Im_aff[l_selec+bufi_selec-2][i+bufj_selec][1] = 0;
							Im_aff[i+bufi_selec][bufj_selec+2][2] = 0;
							Im_aff[i+bufi_selec][l_selec+bufj_selec-2][2] = 0;
							Im_aff[bufi_selec+2][i+bufj_selec][2] = 0;
							Im_aff[l_selec+bufi_selec-2][i+bufj_selec][2] = 0;
						}
						
						// affichage
						for(int i = 0; i < jdim; i++)
			    		{
			    			for(int j = 0; j < idim; j++) 
			    			{
			    				for(int band = 0; band < 3; band++)
			    				{
			    					arrayimage[((j*jdim)+i)*3+band] = Im_aff[j][i][band];
			    				}
			    					
			    			}
			    		}
			    		ii = new ImageIcon(getImageFromArray(arrayimage, jdim, idim));
			    		lab.setIcon(ii);
			    		pan.setBounds(0, 0, jdim,idim);
			    		pan.add(lab);
			    		pan.repaint();
			    		pan.revalidate();
						
						// ajoute la nouvelle imagette à la base de donnée
			    		
			    		// ajoute à la base d'apprentissage
			    		for(int i = 0; i < l_learn; i++)
			    		{
			    			for(int j = 0; j < l_learn; j++) 
			    			{
			    				for(int band = 0; band < 3; band++)
			    				{
			    					data_learn[(n_learn/rap_true)+compt_learn-compt_selec1][0][i][j][band] = Im1[bufi_selec+rapp_learn*i][bufj_selec+rapp_learn*j][band];
			    					data_learn[(n_learn/rap_true)+compt_learn-compt_selec1][1][i][j][band] = Im2[bufi_selec+rapp_learn*i][bufj_selec+rapp_learn*j][band];
			    				}
			    					
			    			}
			    		}
			    		
						compt_selec1 = compt_selec1+1;
					} // while compt_selec1<compt_selec
					Thread.sleep(500);
					compt_selec = 0;
				}
				
				newIm = 0;
				window.setVisible(false); 
				window.dispose();
				bScreen.setText("Capture");
				
				// ========================= test ========================
				if(compt_learn==n_learn/rap_true-1)
				{
					
					cond1 = 1;
					cond = 1;
					Main.onglet = 12;
					
					for(int test=0;test<n_learn;test++)
					{
						for(int frm=0;frm<2;frm++)
						{
							for(int i = 0; i < l_learn; i++)
				    		{
				    			for(int j = 0; j < l_learn; j++) 
				    			{
				    				for(int band = 0; band < 3; band++)
				    				{
				    					array_selec[((j*l_learn)+i)*3+band] = data_learn[test][frm][j][i][band];
				    				}	
				    			}
				    		}
						Main.pan.remove(pan_selec);
						pan_selec.removeAll();
			    		ii = new ImageIcon(getImageFromArray(array_selec, l_learn, l_learn));
			    		lab_selec.setIcon(ii);
			    		pan_selec.setBounds(0, 100, l_learn+5,l_learn+5);

			    		pan_selec.add(lab_selec);
			    		pan_selec.repaint();
			    		pan_selec.revalidate();
			    		Main.pan.add(pan_selec);
			    		Main.pan.repaint(); 
			    		Main.pan.validate();
						}
					}
				} //fin du test
			} // fin if cond1
			
		} // fin cond
		
		if(cond_learn==1)
		{
			Learning learn = new Learning();
			learn.rapp_learn = rapp_learn;
			learn.rap_true = rap_true;
			learn.learn(data_learn);
				
			Main.pan.removeAll();
			Perception connaissance = new Perception(learn);
			connaissance.test();
		}
		
	} // fin constructeur
	
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
			if(newIm==1 && compt_selec<max_selec)
			{
				valid = 1;
			}
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

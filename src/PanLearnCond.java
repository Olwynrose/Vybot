import java.awt.AWTException;
import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanLearnCond
{
	JButton bScreen;
	JButton bNew;
	JButton bLearn;
	JButton bRetour;

	Robot robot;
	BufferedImage screenFiesta;
	
	JFrame window;
	int cond;	// condition de sortie de la fenêtre
	int cond1;	// condition d'entrée/sorti du screen
	int selec;	// sélection d'une imagette (clic souris)
	int valid;	// validation d'une imagette (autorise la sauvegarde)
	int newIm;	// nouvelle image en mémoire (Im_selec)
	int compt_selec; // compte les sélections par screen
	int cond_learn;	//
	boolean buf_detec;	// vaut 1 lorque l'état de la condition change (détection/non détection)
	
	int max_selec;	// nombre max d'imagettes par screen
	int i_selec;	// coordonnées de la zone sélectionnée
	int j_selec;	// coordonnées de la zone sélectionnée
	int i_valid;	// coordonnées de la zone validée
	int j_valid;	// coordonnées de la zone validée
	int l_selec;	// largeur des images sélectionnées (pour l'affichage)
	int l_learn;	// largeur des images pour l'apprentissage
	int n_learn;	// nombre d'images pour l'apprentissage (pair !)
	int rapp_learn;	// rapport entre la taille réelle et la taille des images d'apprentissage
	int[][][] Im_selec;		// image sélectionnée
	int[][][] Im_valid;		// image validée
	int x_Scroll;	// position scroll (seuil)
	int h_Scroll;	// position scroll (seuil)
	int d_x;		// déplacement de scroll
	int l_scroll;	// longueur du scroll
	int th_cond;
	
	public PanLearnCond() throws InterruptedException, AWTException, IOException
	{
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int iMax = gd.getDisplayMode().getHeight();
		int jMax = gd.getDisplayMode().getWidth();
		int[] arrayimage;
		int[] array_selec;
		JPanel pan = new JPanel();
		JLabel lab = new JLabel();
		JPanel pan_selec = new JPanel();
		pan_selec.setBackground(Color.BLACK);
		JLabel lab_selec = new JLabel();
		
		// paramètres interface
		
		int h0 = 0;
		int h_bout = 25;
		int h_screen = 25;
		int h_new = 25;
		int h_learn = 25;
		
		// conditions
		cond = 0;
		cond1 = 1;
		selec = 0;
		valid = 0;
		newIm = 0;
		compt_selec = 0;
		cond_learn = 0;
		buf_detec = false;
		
		// paramètres des données d'apprentissage
		l_selec = Main.dim_cond;
		//n_learn = 20;
		int rap_l = 6;
		int l_affiche = l_selec*rap_l;
		max_selec = 4;
		int idim = iMax-50;		// dimension du screen
		int jdim = jMax-135;	// dimension du screen
		int screeni0 = 0;	// position du screen
		int screenj0 = 0;	// position du screen
				
		// images 
		int[][][] Im2;			// image extrait écran fiesta
		int[][][] Im_aff;		// image à afficher
		Im_aff = new int[idim][jdim][3];
		
		Im_selec = new int[l_selec][l_selec][3];
		Im_valid = new int[l_selec][l_selec][3];
		arrayimage = new int[idim*jdim*3];
		array_selec = new int[l_affiche*l_affiche*3];
				
		// charge les données
		for(int i = 0; i < l_selec; i++)
		{
			for(int j = 0; j < l_selec; j++) 
			{
				for(int band = 0; band < 3; band++)
				{
					 Im_valid[j][i][band] = Main.Im_Cond[Main.seq1][i][j][band];
				}
					
			}
		}
		i_valid = Main.coor_Cond[Main.seq1][0] + Main.references.idimVie;
		j_valid = Main.coor_Cond[Main.seq1][1] + Main.references.idimVie;
		th_cond = Main.donnees.th_conds[Main.seq1];
		cond1 = -1;
		valid = 1;
		selec = 1;
		
		
		h_Scroll = h0+h_screen;
		l_scroll = 130;
		x_Scroll = (th_cond*(l_scroll-20)-1)/100;
		boolean detec = false;
		JButton scrollball = new JButton(Main.iScrollG);
		//scrollball.setLayout(null);
		scrollball.setVerticalTextPosition(SwingConstants.CENTER);
		scrollball.setHorizontalTextPosition(SwingConstants.CENTER);
		scrollball.setBackground(Color.red);
		scrollball.setSize(20, 20);
		scrollball.setLocation(x_Scroll, h_Scroll);
		scrollball.setBorderPainted(false);
		scrollball.setVisible(false);
		scrollball.addMouseMotionListener( new MouseMotionAdapter() 
		{
			public void mouseDragged( MouseEvent e )
			{

				d_x = e.getX();
				x_Scroll = Math.min(Math.max(x_Scroll + d_x, 0), l_scroll-20);

				scrollball.setLocation(x_Scroll, h_Scroll);
				th_cond = 1+(100*x_Scroll)/(l_scroll-20);
				Main.pan.repaint();
			}
		} );
		Main.pan.add(scrollball);
		
		// buffeurs pour les screens
		ImageIcon ii;
		Rectangle captureRect = new Rectangle(screenj0, screeni0, jdim, idim);
		robot = new Robot();
		
		// buffeurs et compteurs
		int bufi_selec = 0;	// buffeur pour la selection (évite les problèmes de threading)
		int bufj_selec = 0;	// buffeur pour la selection (évite les problèmes de threading)
		
		
		bScreen = new JButton(Main.iBouton);
		bScreen.setBackground(Color.BLUE);
		if(Main.donnees.langue==0)
		{
			bScreen.setText("Capture");
		}
		else
		{
			bScreen.setText("ScreenShot");
		}
		bScreen.setVerticalTextPosition(SwingConstants.CENTER);
		bScreen.setHorizontalTextPosition(SwingConstants.CENTER);
		bScreen.setBounds(0, h0, 130, h_bout);
		bScreen.setForeground(Color.BLACK);
		Main.pan.add(bScreen);
		bScreen.addActionListener(new AScreen());
		
		bNew = new JButton(Main.iBouton);
		bNew.setBackground(Color.BLUE);
		if(Main.donnees.langue==0)
		{
			bNew.setText("Accepter");
		}
		else
		{
			bNew.setText("Accept");
		}
		bNew.setVerticalTextPosition(SwingConstants.CENTER);
		bNew.setHorizontalTextPosition(SwingConstants.CENTER);
		bNew.setBounds(0, h0+h_screen, 130, h_bout);
		bNew.setForeground(Color.BLACK);
		Main.pan.add(bNew);
		bNew.addActionListener(new ANew());
		bNew.setVisible(false);
		
		bLearn = new JButton(Main.iBouton);
		bLearn.setBackground(Color.BLUE);
		if(Main.donnees.langue==0)
		{
			bLearn.setText("Valider");
		}
		else
		{
			bLearn.setText("Confirm");
		}
		bLearn.setVerticalTextPosition(SwingConstants.CENTER);
		bLearn.setHorizontalTextPosition(SwingConstants.CENTER);
		bLearn.setBounds(0, h0+h_screen+h_new, 130, h_bout);
		bLearn.setForeground(Color.BLACK);
		Main.pan.add(bLearn);
		bLearn.addActionListener(new ALearn());
		bLearn.setEnabled(true);
		
		bRetour = new JButton(Main.iBouton);
		bRetour.setBackground(Color.BLUE);
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
        bRetour.setBounds(0, h0+h_screen+h_new+h_learn, 130, h_bout);
        bRetour.setForeground(Color.BLACK);
		Main.pan.add(bRetour);
		bRetour.addActionListener(new ARetour());
		
		Main.pan.repaint(); 
		Main.pan.validate();
		
		
		while(cond == 0)
		{
			if(cond1>-1)
			{
				Thread.sleep(200);
			}
			if(cond1 <= 0)
			{
				scrollball.setVisible(false);
				selec = 0;
				compt_selec = 0;
				if(Main.donnees.langue==0)
				{
					bScreen.setText("Suivant");
				}
				else
				{
					bScreen.setText("Next");
				}
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
						bNew.setVisible(true);
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
							for(int k = 0; k < rap_l; k++)
		    				{
				    			for(int j = 0; j < l_selec; j++) 
				    			{
				    				for(int band = 0; band < 3; band++)
				    				{
			    						for(int l = 0; l < rap_l; l++)
					    				{
		    								array_selec[(((j*rap_l+k)*l_affiche)+(i*rap_l+l))*3+band] = Im_selec[j][i][band];
					    				}
				    				}
			    				}
			    			}
			    		}
						Main.pan.remove(pan_selec);
						pan_selec.removeAll();
			    		ii = new ImageIcon(getImageFromArray(array_selec, l_affiche, l_affiche));
			    		lab_selec.setIcon(ii);
			    		pan_selec.setBounds(5, 102, l_affiche,l_affiche);

			    		pan_selec.add(lab_selec);
			    		pan_selec.repaint();
			    		pan_selec.revalidate();
			    		Main.pan.add(pan_selec);
			    		Main.pan.remove(bRetour);
			    		Main.pan.add(bRetour);
			    		Main.pan.repaint(); 
			    		Main.pan.validate();
						selec = 0;
						
						
						// place les rectangles sur le screen
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
					} // fin selec == 1
					
					
					
				} // fin while cond1 == 0
				
				bNew.setVisible(false);
				window.setVisible(false); 
				window.dispose();
				if(Main.donnees.langue==0)
				{
					bScreen.setText("Capture");
				}
				else
				{
					bScreen.setText("ScreenShot");
				}
				
				for(int i = 0; i < l_selec; i++)
	    		{
					for(int k = 0; k < rap_l; k++)
					{
		    			for(int j = 0; j < l_selec; j++) 
		    			{
		    				for(int band = 0; band < 3; band++)
		    				{
	    						for(int l = 0; l < rap_l; l++)
			    				{
									array_selec[(((j*rap_l+k)*l_affiche)+(i*rap_l+l))*3+band] = Im_valid[j][i][band];
			    				}
		    				}
	    				}
	    			}
	    		}
				Main.pan.remove(pan_selec);
				pan_selec.removeAll();
	    		ii = new ImageIcon(getImageFromArray(array_selec, l_affiche, l_affiche));
	    		lab_selec.setIcon(ii);
	    		pan_selec.setBounds(5, 102, l_affiche,l_affiche);

	    		pan_selec.add(lab_selec);
	    		pan_selec.repaint();
	    		pan_selec.revalidate();
	    		Main.pan.add(pan_selec);
	    		Main.pan.repaint(); 
	    		Main.pan.validate();
	    		if(cond1==-1)
	    		{
	    			cond1 = 1;
	    		}
			} // fin if cond1
			
			if(valid==1)
			{
				scrollball.setVisible(true);
				
				// test la condition
				detec = condition();
				if(detec)
				{
					if(buf_detec!=detec)
					{
						scrollball.setIcon(Main.iScrollG);
						buf_detec = detec;
					}
				}
				else
				{
					if(buf_detec!=detec)
					{
						scrollball.setIcon(Main.iScrollR);
						buf_detec = detec;
					}
				}
			}
		} // fin cond
		
		if(valid==1)
		{
			// sauvegarde les données
			for(int i = 0; i < l_selec; i++)
    		{
    			for(int j = 0; j < l_selec; j++) 
    			{
    				for(int band = 0; band < 3; band++)
    				{
    					Main.Im_Cond[Main.seq1][i][j][band] = Im_valid[j][i][band];
    				}
    					
    			}
    		}
			Main.coor_Cond[Main.seq1][0] = i_valid - Main.references.idimVie;
			Main.coor_Cond[Main.seq1][1] = j_valid - Main.references.jdimVie;
			Main.donnees.th_conds[Main.seq1] = th_cond;
			Main.donnees.sauvegarde1();
			Main.donnees.sauvegardeCond();
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
			if(newIm==1)
			{
				valid = 1;
				cond1 = 1;
				for(int i=0;i<l_selec;i++)
				{
					for(int j=0;j<l_selec;j++)
					{
						for(int k=0;k<3;k++)
						{
							Im_valid[i][j][k] = Im_selec[i][j][k];
						}
					}
				}
				i_valid = i_selec;
				j_valid = j_selec;
			}
		}
	}
	
	public class ALearn implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			if(valid==1)
			{
				cond = 1;
				Main.onglet = 6;
			}
		}
	}
	
	public class ARetour implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			cond = 1;
			Main.onglet = 5;
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
	
	private boolean condition()
	{
		Rectangle captureRect = new Rectangle(j_valid, i_valid, l_selec, l_selec);
		screenFiesta = robot.createScreenCapture(captureRect);
        Im_selec = convertTo2DUsingGetRGB(screenFiesta);
        int err = 0;
        for(int i=0;i<l_selec;i++)
		{
			for(int j=0;j<l_selec;j++)
			{
				for(int k=0;k<3;k++)
				{
					err = err + Math.abs(Im_selec[i][j][k]-Im_valid[i][j][k]);
				}
			}
		}
        if(err<l_selec*l_selec*th_cond)
        {
        	return true;
        }
		return false;
	}
	
	
}

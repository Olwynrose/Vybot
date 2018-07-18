
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
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanSouris
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
	int typeSeq;	// 1 : séquence de fond, 2 : séquence spéciale
	
	int i_selec;
	int j_selec;
	int l_selec;
	
	public PanSouris(int typeSeq_in) throws InterruptedException, AWTException, IOException
	{
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int iMax = gd.getDisplayMode().getHeight();
		int jMax = gd.getDisplayMode().getWidth();
		int[] arrayimage;
		JPanel pan = new JPanel();
		JLabel lab = new JLabel();
		typeSeq = typeSeq_in;
		
		// conditions
		cond = 0;
		cond1 = 1;
		selec = 0;
		valid = 0;
		newIm = 0;
		compt_selec = 0;
		cond_learn = 0;
		
	
		l_selec = 15;
		int idim = iMax-50;		// dimension du screen
		int jdim = jMax-135;	// dimension du screen
		int screeni0 = 0;	// position du screen
		int screenj0 = 0;	// position du screen
		String str_nom;
		
		// buffeurs pour les screens
		ImageIcon ii;
		Rectangle captureRect = new Rectangle(screenj0, screeni0, jdim, idim);
		Robot robot = new Robot();
		BufferedImage screenFiesta;
		
		// images 
		int[][][] Im1;			// image extrait écran fiesta
		int[][][] Im_aff;		// image à afficher
		Im_aff = new int[idim][jdim][3];
		arrayimage = new int[idim*jdim*3];
		

		
		
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
		bScreen.setBounds(0, 0, 130, 25);
		bScreen.setForeground(Color.BLACK);
		Main.pan.add(bScreen);
		bScreen.addActionListener(new AScreen());
		
		bNew = new JButton(Main.iBouton);
		bNew.setBackground(Color.BLUE);
		if(Main.donnees.langue==0)
		{
			bNew.setText("Clic Droit");
		}
		else
		{
			bNew.setText("Right clic");
		}
		bNew.setVerticalTextPosition(SwingConstants.CENTER);
		bNew.setHorizontalTextPosition(SwingConstants.CENTER);
		bNew.setBounds(0, 25, 130, 25);
		bNew.setForeground(Color.BLACK);
		Main.pan.add(bNew);
		bNew.addActionListener(new ANew());
		
		bLearn = new JButton(Main.iBouton);
		bLearn.setBackground(Color.BLUE);
		if(Main.donnees.langue==0)
		{
			bLearn.setText("Clic Gauche");
		}
		else
		{
			bLearn.setText("Left clic");
		}
		bLearn.setText("Clic Gauche");
		bLearn.setVerticalTextPosition(SwingConstants.CENTER);
		bLearn.setHorizontalTextPosition(SwingConstants.CENTER);
		bLearn.setBounds(0, 50, 130, 25);
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
				if(Main.donnees.langue==0)
				{
					bScreen.setText("Suivant");
				}
				else
				{
					bScreen.setText("Next");
				}
	    		screenFiesta = robot.createScreenCapture(captureRect);
	            Im1 = convertTo2DUsingGetRGB(screenFiesta);
	            
	    		for(int i = 0; i < jdim; i++)
	    		{
	    			for(int j = 0; j < idim; j++) 
	    			{
	    				for(int band = 0; band < 3; band++)
	    				{
	    					arrayimage[((j*jdim)+i)*3+band] = Im1[j][i][band];
	    					Im_aff[j][i][band] = Im1[j][i][band];
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
				window.setLocation(0, 0);
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
			    		 	cond1 = 1;
			    	    }
				});
				
				while(cond1==0)
				{
					Thread.sleep(200);
				}
				if(Main.donnees.langue==0)
				{
					bScreen.setText("Capture");
				}
				else
				{
					bScreen.setText("ScreenShot");
				}
				window.setVisible(false); 
				window.dispose();
			
			} // fin cond1
			
			
		} // fin cond
		
		if(cond>1 && selec==1)
		{
			// ajoute l'action souris à la séquence
			if(cond==2)
			{
				str_nom = "Clic D ";
			}
			else
			{
				str_nom = "Clic G ";
			}
			str_nom = new StringBuilder().append(str_nom).append(String.valueOf(i_selec - Main.references.idimVie)).toString();
			str_nom = new StringBuilder().append(str_nom).append(", ").toString();
			str_nom = new StringBuilder().append(str_nom).append(String.valueOf(j_selec - Main.references.jdimVie)).toString();
			if(typeSeq==1)
			{
				Main.donnees.aSeq[Main.seq][ Main.donnees.lSeq[Main.seq]] = new Action(str_nom, 0);
				Main.donnees.aSeq[Main.seq][ Main.donnees.lSeq[Main.seq]].clic_i = i_selec - Main.references.idimVie;
				Main.donnees.aSeq[Main.seq][ Main.donnees.lSeq[Main.seq]].clic_j = j_selec - Main.references.jdimVie;
				Main.donnees.lSeq[Main.seq] = Main.donnees.lSeq[Main.seq] + 1;
				Main.donnees.sauvegarde();
				Main.donnees = new Donnees();
				Main.donnees.sauvegarde();
			}
			else
			{
				Main.donnees.aSeq1[Main.seq1][ Main.donnees.lSeq1[Main.seq1]] = new Action(str_nom, 0);
				Main.donnees.aSeq1[Main.seq1][ Main.donnees.lSeq1[Main.seq1]].clic_i = i_selec - Main.references.idimVie;
				Main.donnees.aSeq1[Main.seq1][ Main.donnees.lSeq1[Main.seq1]].clic_j = j_selec - Main.references.jdimVie;
				Main.donnees.lSeq1[Main.seq1] = Main.donnees.lSeq1[Main.seq1] + 1;
				Main.donnees.sauvegarde1();
				Main.donnees = new Donnees();
				Main.donnees.sauvegarde1();
			}
			Main.add_att = 1;
			
		} // fin sauvegarde 
	} // fin constructeur
	
	
	
	public class AScreen implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			cond1 = (cond1+1) % 2;
		}
	}
	
	public class ANew implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			cond = 2;
			if(typeSeq==1)
			{
				Main.onglet = 4;
			}
			else
			{
				Main.onglet = 6;
			}
		}
	}
	
	public class ALearn implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			cond = 3;
			if(typeSeq==1)
			{
				Main.onglet = 4;
			}
			else
			{
				Main.onglet = 6;
			}
		}
	}
	
	public class ARetour implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			cond = 1;
			cond1 = 1;
			if(typeSeq==1)
			{
				Main.onglet = 4;
			}
			else
			{
				Main.onglet = 6;
			}
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

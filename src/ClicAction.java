import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class ClicAction {
	
	int maxAct;					// nombre maximum d actions par séquence 
	String[] sAct;
	String[] sTemps;
	int[] tempo;
	
	static int imin;
	static int jmin;
	
	int n_act;
	int keyCode;
	int cond;
	Condition condi;
	UruMap uruMap;
	
	public ClicAction(Condition condi) throws FileNotFoundException, IOException, AWTException, InterruptedException
	{
		
		
		StringSelection selec;
		Clipboard clipboard;
		Robot robot = new Robot();
		String str_nom;
		maxAct = Main.donnees.maxSeq;
		sAct = new String[maxAct];
		sTemps = new String[maxAct];
		tempo = new int[maxAct];
		int i, j, k;
		cond = 0;
		int l_act = 0;
		keyCode = 0;
		
		// pour la selection
		float th_name = (float) 30;
		float th_diff = (float) 0.048;
		float th_diff_perso = (float) 0.05;
		int nameidim = 20;		// dimension nom
        int namejdim = 50;		// dimension nom
        int condRecherche;		// condition recherche perso
        int[] ordre;
        int trans;
        int screeni0 = 150;		// position screen
		int screenj0 = 100;		// position screen
		Rectangle captureRect;
        BufferedImage screenFiesta;
        int screenidim = 500;	// dimension screen
		int screenjdim = 1100;	// dimension screen
		int[][][] Im;			// image extrait écran fiesta
		float[][] Dif_screen;	// diffusion screen
		float[][] Dif_perso;	// diffusion perso
		int coori;				// position perso
        int coorj;				// position perso
        int inti0 = Main.references.idimChat-150;
		int intj0 = Main.references.jdimChat+520;
		int persoi0 = Main.references.idimSelec;	// position perso
  		int persoj0 = Main.references.jdimSelec;	// position perso
		int persoidim = 35;			// dimension perso
        int persojdim = 150;		// dimension perso
        int persoSelec = 0;			// >0 si le perso a été selectionné
        

		for(i=0;i<Main.donnees.lSeq1[Main.seq1];i++)
		{
			sAct[i] = Main.donnees.aSeq1[Main.seq1][i].action;
			//System.out.println(sAct[i]);
			tempo[i] = Main.donnees.aSeq1[Main.seq1][i].tps;
		}
		n_act = Main.donnees.lSeq1[Main.seq1];
		
		Thread.sleep(20);
		
		for(i=0;i<Main.donnees.lSeq1[Main.seq1];i++)
		{
			l_act = Math.max(tempo[i]/250,1);
			
			if(sAct[i].charAt(0)=='T')
			{
				keyCode = Main.donnees.aSeq1[Main.seq1][i].keyCode;
				if(keyCode>2000)
				{
					robot.keyPress(KeyEvent.VK_CONTROL);
					Thread.sleep(50);
					robot.keyPress(keyCode-2000);
				}
				else
				{
					if(keyCode>1000)
					{
						robot.keyPress(16);
						Thread.sleep(50);
						robot.keyPress(keyCode-1000);
					}
					else
					{
						Thread.sleep(40);
						robot.keyPress(keyCode);
					}
				}
				
				for(j=0;j<l_act;j++)
				{
					Thread.sleep(250);
					if(Main.cond>0)
					{
						j = l_act;
					}
				}
				
				if(keyCode>2000)
				{
					robot.keyRelease(keyCode-2000);
					Thread.sleep(50);
					robot.keyRelease(KeyEvent.VK_CONTROL);
				}
				else
				{
					if(keyCode>1000)
					{
						robot.keyRelease(keyCode-1000);
						Thread.sleep(50);
						robot.keyRelease(KeyEvent.VK_SHIFT);
					}
					else
					{
						robot.keyRelease(keyCode);
					}
				}
				
			} // fin de Touche
			
			if(sAct[i].charAt(0)=='D')
			{
				str_nom = (String.valueOf(Main.donnees.aSeq1[Main.seq1][i].dialogue[0]));
				for(k=1;k<Main.donnees.aSeq1[Main.seq1][i].l_text;k++)
				{
					str_nom = new StringBuilder().append(str_nom).append(String.valueOf(Main.donnees.aSeq1[Main.seq1][i].dialogue[k])).toString();
				}
				selec = new StringSelection(str_nom);
		        clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(selec, selec);
				Thread.sleep(40);
				robot.keyPress(KeyEvent.VK_ENTER);
		        Thread.sleep(50);
		        robot.keyRelease(KeyEvent.VK_ENTER);
		        Thread.sleep(150);
		        robot.keyPress(KeyEvent.VK_CONTROL);
			    Thread.sleep(50);
			    robot.keyPress(KeyEvent.VK_V);
			    Thread.sleep(50);
			    robot.keyRelease(KeyEvent.VK_V);
			    Thread.sleep(50);
			    robot.keyRelease(KeyEvent.VK_CONTROL);
			    Thread.sleep(150);
			    robot.keyPress(KeyEvent.VK_ENTER);
		        Thread.sleep(50);
		        robot.keyRelease(KeyEvent.VK_ENTER);
		        Thread.sleep(500);
		        
			} // fin du dialogue
			
			if(sAct[i].charAt(0)=='C')
			{
				// clic souris
				for(j=0;j<l_act;j++)
				{
					Thread.sleep(50);
					robot.mouseMove(Main.donnees.aSeq1[Main.seq1][i].clic_j + Main.references.jdimVie, Main.donnees.aSeq1[Main.seq1][i].clic_i + Main.references.idimVie);
					Thread.sleep(100);
					
					if(sAct[i].charAt(5)=='G')
					{
						robot.mousePress(InputEvent.BUTTON1_MASK);
						Thread.sleep(100);
						robot.mouseRelease(InputEvent.BUTTON1_MASK);
						Thread.sleep(50);
					}
					else
					{
						robot.mousePress(InputEvent.BUTTON3_MASK);
				        Thread.sleep(100);
				        robot.mouseRelease(InputEvent.BUTTON3_MASK);
				        Thread.sleep(50);
					}
				}
			} // fin de clic
			
			if(sAct[i].charAt(0)=='A')
			{
				for(j=0;j<l_act;j++)
				{
					Thread.sleep(250);
					if(Main.cond>0)
					{
						j = l_act;
					}
				}
			} // fin de attend

			if(sAct[i].charAt(0)=='M')
			{
				if(sAct[i].length()<8)
				{
					
				}
				else
				{
					
				}
				for(j=0;j<l_act;j++)
				{
					Thread.sleep(250);
					if(Main.cond>0)
					{
						j = l_act;
					}
				}
			} // fin de Marche
			
			if(sAct[i].charAt(0)=='S' )
			{
				if(condi.chat>0)
				{
					 condRecherche = 4;
		        	 ordre = ordreClic();
		        	 while(condRecherche>0)
		        	 {
		        		 trans = condRecherche%2;
		        		 trans = 5*trans;
			        	 // prends un screen du jeux et met en évidence les textes 
		        		 /*Thread.sleep(100);
		        		 
		        		 Main.panEtalonJeux.setBackground(Color.WHITE);
		         	     Main.etalonJeux.setLocation(screenj0, screeni0);
		         	     Main.etalonJeux.setOpacity(1);
		         	     Main.etalonJeux.repaint();
		         	     Main.etalonJeux.revalidate();
		        	     Main.etalonJeux.setOpacity(1);
		        	     Thread.sleep(100);
		         	     Main.etalonJeux.setOpacity(0);
		         	     */
			     	     Thread.sleep(100);  
			    	     
			     	     
			        	 captureRect = new Rectangle(screenj0, screeni0, screenjdim, screenidim);
			             screenFiesta = robot.createScreenCapture(captureRect);
			             Im = convertTo2DUsingGetRGB(screenFiesta);
			             
			             
			             
			     	    robot.mouseMove(Main.references.jdimCarte-175,10);
			            robot.mousePress(InputEvent.BUTTON1_MASK);
			            Thread.sleep(50);
			            robot.mouseRelease(InputEvent.BUTTON1_MASK);
			             
			             Dif_screen = ImText(Im, screenidim, screenjdim, th_name, 3);
			              
			             // recherche les coordonnées du perso
			             RechercheDiff(Dif_screen, screenidim-3, screenjdim-3, condi.Dif_name, nameidim-3, namejdim-3, th_diff);

			             coori = imin;
			             coorj = jmin;
			             //System.out.println(imin);
			             //System.out.println(jmin);
			             if(coorj>intj0-screenj0 || coori<inti0-screeni0)
			             {
			            	 
			            	 k=0;
			            	 while(k<9)
				             {
				            	 switch(ordre[k])
				            	 {
				            	 case 0:
				    	             {robot.mouseMove(screenj0+coorj+trans-20-14*4, 50+screeni0+coori);}
				    	             break;
				            	 case 1:
					             	 {robot.mouseMove(screenj0+coorj+trans-20-14*3, 50+screeni0+coori);}
					             	break;
				            	 case 2:
					             	 {robot.mouseMove(screenj0+coorj+trans-20-14*2, 50+screeni0+coori);}
					             	break;
				            	 case 3:
				             	 	 {robot.mouseMove(screenj0+coorj+trans-20-14*1, 50+screeni0+coori);}
				             	 	break;
				            	 case 4:
				             	 	 {robot.mouseMove(screenj0+coorj+trans-20, 50+screeni0+coori);}
				             	 	break;
				            	 case 5:
				             	 	 {robot.mouseMove(screenj0+coorj+trans-20+14*1, 50+screeni0+coori);}
				             	 	break;
				            	 case 6:
				            	 	 {robot.mouseMove(screenj0+coorj+trans-20+14*2, 50+screeni0+coori);}
				             	 	break;
				            	 case 7:
				             	 	 {robot.mouseMove(screenj0+coorj+trans-20+14*3, 50+screeni0+coori);}
				             	 	break;
				            	 case 8:
				             	 	 {robot.mouseMove(screenj0+coorj+trans-20+14*4, 50+screeni0+coori);}
				             	 	break;
				            	 }
				            	 
					             Thread.sleep(10);
					             robot.mousePress(InputEvent.BUTTON1_MASK);
					             Thread.sleep(40);
					             robot.mouseRelease(InputEvent.BUTTON1_MASK);
					             Thread.sleep(30);
					             robot.keyPress(KeyEvent.VK_S);
								 Thread.sleep(50);
								 robot.keyRelease(KeyEvent.VK_S);
								 Thread.sleep(10);
				             
							 
							 // vérification
							 
							 captureRect = new Rectangle(persoj0, persoi0, persojdim, persoidim);
				             screenFiesta = robot.createScreenCapture(captureRect);
				             Im = convertTo2DUsingGetRGB(screenFiesta);
				             
				             
				             Dif_perso = ImText(Im, persoidim, persojdim, th_name, 3);
				             persoSelec = RechercheDiff(Dif_perso, persoidim-3, persojdim-3, condi.Dif_name, nameidim-3, namejdim-3, th_diff_perso);

				     	      
							 
							 //new DetectWindow();
							 
				     	     
				             if(persoSelec>0)
				             {
				            	 apprendProba(k);
				            	 k = 10;
				            	 /*Main.panEtalonSelec.setBackground(Color.GREEN);
				         	     Main.etalonSelec.setLocation(persoj0, persoi0);
				         	     Main.etalonSelec.setOpacity(1);
				         	     Main.etalonSelec.repaint();
				         	     Main.etalonSelec.revalidate();
				        	     Main.etalonSelec.setOpacity(1);
				        	     Thread.sleep(100);
				         	     Main.etalonSelec.setOpacity(0);
				         	     */
				
					    	     robot.mouseMove(Main.references.jdimCarte-175,10);
					             robot.mousePress(InputEvent.BUTTON1_MASK);
					             Thread.sleep(50);
					             robot.mouseRelease(InputEvent.BUTTON1_MASK);
				             }
				             k = k + 1;
			             } // fin de la boucle sur les clics
				    	     
				    	     robot.mouseMove(Main.references.jdimCarte-175,10);
				             robot.mousePress(InputEvent.BUTTON1_MASK);
				             Thread.sleep(50);
				             robot.mouseRelease(InputEvent.BUTTON1_MASK);
				             
		             }
		             else
		             {
		            	 
		            	 /*
		            	 Main.panEtalonInt.setBackground(Color.BLACK);
		         	     Main.etalonInt.setLocation(intj0, inti0);
		         	     Main.etalonInt.setOpacity(1);
		         	     Main.etalonInt.repaint();
		         	     Main.etalonInt.revalidate();
		        	     Main.etalonInt.setOpacity(1);
		        	     Thread.sleep(100);
		         	     Main.etalonInt.setOpacity(0);
	            	  	*/
			    	    
			    	    Thread.sleep(2500);
		             }
					 condRecherche = condRecherche - 1;
					 if(persoSelec>0)
					 {
						 condRecherche = 0;
					 }
		        	 } // tant que cond recherche >0
		        	 Thread.sleep(50);
		             robot.keyPress(KeyEvent.VK_W);
		    		 Thread.sleep(100);
		    		 robot.keyRelease(KeyEvent.VK_W);
		    		 Thread.sleep(100);
				}
				
			} // fin de Selection
			
			if(Main.cond==1)
			{
				i = 10000000;
			}
			
		} // fin de la séquence
		
	}// fin du constructeur
	
	
	
	
	
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
	 /*//never used locally
	 private static int RechercheCoul(int[][][] Im, int idim, int jdim, int[][][] Im_buf, int iidim, int jjdim, int th)
	 {
		 int Present = 0;
		 int i;
		 int j;
		 int ii;
		 int jj;
		 int Err;
		 int min = 150*150*3*18*18;
		 
		 th = th*iidim*jjdim*3;
		 for(i=0;i<idim-iidim;i++)
		 {
			 for(j=0;j<jdim-jjdim;j++)
			 {
				 Err = 0;
				 for(ii=0;ii<iidim;ii++)
				 {
					 for(jj=0;jj<jjdim;jj++)
					 {
						 Err = Err + (Im[i+ii][j+jj][1]-Im_buf[ii][jj][1])*(Im[i+ii][j+jj][1]-Im_buf[ii][jj][1]);
						 Err = Err + (Im[i+ii][j+jj][2]-Im_buf[ii][jj][2])*(Im[i+ii][j+jj][2]-Im_buf[ii][jj][2]);
						 Err = Err + (Im[i+ii][j+jj][0]-Im_buf[ii][jj][0])*(Im[i+ii][j+jj][0]-Im_buf[ii][jj][0]);
					 }
				 } 
				 if(min>Err) //(Err<th)
				 {
					 min = Err;
					 //Present = Present + 1;
				 }
			 }
				 
		 }
		 //System.out.println(min);
		 return Present;
	 } // fin rechercheCoul
	 */
	 private static int RechercheDiff(float[][] Im, int idim, int jdim, float[][] Im_buf, int iidim, int jjdim, float th) throws IOException, InterruptedException
	 {
		 int Present = 0;
		 //int compte = 0;
		 int i;
		 int j;
		 int ii;
		 int jj;
		 float Err;
		 float Min = 1000*iidim*jjdim;
		 int[][] Im_ML = new int[idim][jdim];
		 for(i=0;i<idim-iidim;i++)
		 {
			 for(j=0;j<jdim-jjdim;j++)
			 {
				 if(idim>5*iidim)
				 {
					 Im_ML[i][j] = 0;
				 }
				 else
				 {
					 Im_ML[i][j] = 1;
				 }
				 //compte = compte + 1;
			 }
		 }
		 for(i=iidim;i<idim-iidim;i++)
		 {
			 for(j=jjdim;j<jdim-jjdim;j++)
			 {
				 //compte = compte + 1;

				 //System.out.println(Im[i][j]);
				 if(Im[i][j]>0.75)
				 {
					 for(ii=0;ii<iidim;ii++)
					 {
						 for(jj=0;jj<jjdim;jj++)
						 {
							 //compte = compte + 1;
							 if(Im_ML[i-ii][j-jj]==0)
							 {
								 Im_ML[i-ii][j-jj] = 1;
							 }
							 else
							 {
								 jj = jdim;
							 }
						 }
					 } 
				 }
			 }
		 }
		 /*BufferedWriter writer = new BufferedWriter(new FileWriter("Config/tmp.txt"));
       writer.write(Integer.toString(idim-iidim));
       writer.newLine();
       writer.flush();
       writer.write(Integer.toString(jdim-jjdim));
       writer.newLine();
       writer.flush();*/
       
		 for(i=0;i<idim-iidim;i++)
		 {
			 for(j=0;j<jdim-jjdim;j++)
			 {
				 Err = 0;
				 if(Im_ML[i][j]==1)
				 {
					 for(ii=0;ii<iidim;ii++)
					 {
						 for(jj=0;jj<jjdim;jj++)
						 {
							 //compte = compte + 1;
							 Err = Err + (Im[i+ii][j+jj]-Im_buf[ii][jj])*(Im[i+ii][j+jj]-Im_buf[ii][jj]);
						 }
					 } 
				 }
				 else
				 {
					 Err = (float) 100000;
				 }
				 if(Err<th*iidim*jjdim)
				 {
					 Present = Present + 1;
				 }
				 if(Err<Min)
				 {
					 Min = Err;
					 imin = i;
					 jmin = j;
				 }
				 /*writer.write(Float.toString(Err/(iidim*jjdim)));
				 //writer.write(Float.toString(Im[i][j]));
				 //writer.write(Integer.toString(Im_ML[i][j]));
 	          	 writer.newLine();
 	          	 writer.flush();*/
			 }
		 }

		 Min = Min/(iidim*jjdim);
		 //System.out.println(Min);
		 //System.out.println("compte" + compte);
		 /*writer.close();
		 System.out.println("OK");
       Thread.sleep(2000);*/
		 //System.out.println("min = " + Min);
		 return Present;
	 } // fin rechercheDiff
	 private static float[][] ImText(int[][][] Im, int idim, int jdim, float th, int Type) throws IOException, InterruptedException
	 {
		 float[][][] Im_float = new float[idim-3][jdim-3][3];
		 float[][] Gauss = new float[3][3];
		 float[][] Im_text = new float[idim-3][jdim-3];
		 float[] Coul = new float[3];
		 //float Fact = (float) 0.8;
		 float dist;
		 int it;
		 int i,j,ii,jj;
		 
		 // convolution
		 Gauss[0][0] = (float) 0.0113;
		 Gauss[0][1] = (float) 0.0838;
		 Gauss[0][2] = (float) 0.0113;
		 Gauss[1][0] = (float) 0.0838;
		 Gauss[1][1] = (float) 0.6193;
		 Gauss[1][2] = (float) 0.0838;
		 Gauss[2][0] = (float) 0.0113;
		 Gauss[2][1] = (float) 0.0838;
		 Gauss[2][2] = (float) 0.0113;

		 for(i=0;i<idim-3;i++)
		 {
			 for(j=0;j<jdim-3;j++)
			 {
				 Im_text[i][j] = (float) 0;
				 for(ii=0;ii<3;ii++)
				 {
					 for(jj=0;jj<3;jj++)
					 {
						 Im_float[i][j][1] = Im_float[i][j][1] + ( ((float) Im[i+ii][j+jj][1])*Gauss[ii][jj]);
						 Im_float[i][j][2] = Im_float[i][j][2] + ( ((float) Im[i+ii][j+jj][2])*Gauss[ii][jj]);
						 Im_float[i][j][0] = Im_float[i][j][0] + ( ((float) Im[i+ii][j+jj][0])*Gauss[ii][jj]);
					 }
				 } 
				 
			 }
		 }
		 if(Type==1)
		 {
			 for(it=0;it<2;it++)
			 {
				 if(it==0)
				 {
					 // magenta (chucho)
					 Coul[0] = (float) 188;
					 Coul[1] = (float) 109;
					 Coul[2] = (float) 188;
				 }
				 if(it==1)
				 {
					 // blanc (normal)
					 Coul[0] = (float) 200;
					 Coul[1] = (float) 200;
					 Coul[2] = (float) 200;
				 }
				 if(it==2)
				 {
					 // jaune (nom)
					 Coul[0] = (float) 200;
					 Coul[1] = (float) 200;
					 Coul[2] = (float) 0;
				 }
				 
				 for(i=0;i<idim-3;i++)
				 {
					 for(j=0;j<jdim-3;j++)
					 {
						dist = (Im_float[i][j][1] - Coul[1])*(Im_float[i][j][1] - Coul[1]);
						dist = dist + (Im_float[i][j][2] - Coul[2])*(Im_float[i][j][2] - Coul[2]);
						dist = dist + (Im_float[i][j][0] - Coul[0])*(Im_float[i][j][0] - Coul[0]);
						Im_text[i][j] = Im_text[i][j] + (float) Math.exp((double) -dist/(th*th*3));
					 }
				 }
			 }
		 }
		 
		 if(Type==2)
		 {
			 // jaune (nom)
			 Coul[0] = (float) 200;
			 Coul[1] = (float) 200;
			 Coul[2] = (float) 0;
			 
			 for(i=0;i<idim-3;i++)
			 {
				 for(j=0;j<jdim-3;j++)
				 {
					dist = (Im_float[i][j][1] - Coul[1])*(Im_float[i][j][1] - Coul[1]);
					dist = dist + (Im_float[i][j][2] - Coul[2])*(Im_float[i][j][2] - Coul[2]);
					dist = dist + (Im_float[i][j][0] - Coul[0])*(Im_float[i][j][0] - Coul[0]);
					Im_text[i][j] = Im_text[i][j] + (float) Math.exp((double) -dist/(th*th*3));
				 }
			 }
	 	 }
		 if(Type==3)
		 {
			 // blanc (normal)
			 Coul[0] = (float) 200;
			 Coul[1] = (float) 200;
			 Coul[2] = (float) 200;
			 
			 for(i=0;i<idim-3;i++)
			 {
				 for(j=0;j<jdim-3;j++)
				 {
					dist = (Im_float[i][j][1] - Coul[1])*(Im_float[i][j][1] - Coul[1]);
					dist = dist + (Im_float[i][j][2] - Coul[2])*(Im_float[i][j][2] - Coul[2]);
					dist = dist + (Im_float[i][j][0] - Coul[0])*(Im_float[i][j][0] - Coul[0]);
					Im_text[i][j] = Im_text[i][j] + (float) Math.exp((double) -dist/(th*th*3));
				 }
			 }
	 	 }
		 if(Type==4)
		 {
			 // magenta (chucho)
			 Coul[0] = (float) 188;
			 Coul[1] = (float) 109;
			 Coul[2] = (float) 188;
			 
			 for(i=0;i<idim-3;i++)
			 {
				 for(j=0;j<jdim-3;j++)
				 {
					dist = (Im_float[i][j][1] - Coul[1])*(Im_float[i][j][1] - Coul[1]);
					dist = dist + (Im_float[i][j][2] - Coul[2])*(Im_float[i][j][2] - Coul[2]);
					dist = dist + (Im_float[i][j][0] - Coul[0])*(Im_float[i][j][0] - Coul[0]);
					Im_text[i][j] = Im_text[i][j] + (float) Math.exp((double) -dist/(th*th*3));
				 }
			 }
	 	 }
		 if(Type==5)
		 {
			 // jaune terne (nom perso)
			 Coul[0] = (float) 160;
			 Coul[1] = (float) 160;
			 Coul[2] = (float) 25;
			 
			 for(i=0;i<idim-3;i++)
			 {
				 for(j=0;j<jdim-3;j++)
				 {
					dist = (Im_float[i][j][1] - Coul[1])*(Im_float[i][j][1] - Coul[1]);
					dist = dist + (Im_float[i][j][2] - Coul[2])*(Im_float[i][j][2] - Coul[2]);
					dist = dist + (Im_float[i][j][0] - Coul[0])*(Im_float[i][j][0] - Coul[0]);
					Im_text[i][j] = Im_text[i][j] + (float) Math.exp((double) -dist/(th*th*3));
				 }
			 }
	 	 }
		 /*BufferedWriter writer = new BufferedWriter(new FileWriter("Config/tmp.txt"));
		 idim = idim-3;
		 jdim = jdim-3;
       writer.write(Integer.toString(idim));
          writer.newLine();
          writer.flush();
          writer.write(Integer.toString(jdim));
       writer.newLine();
       writer.flush();
       
       for(i=0;i<idim;i++)
		 {
      	 for(j=0;j<jdim;j++)
  		 {
			 		  writer.write(Float.toString(Im_text[i][j]));
      	          writer.newLine();
      	          writer.flush();
  		 }
		 }
       writer.close();
       System.out.println("ok");
       Thread.sleep(2000);*/ // test detection text
       
		 return Im_text;
	 } // fin ImText
	 
	 private int[] ordreClic()
	 {
		 int[] ordre = new int[9];
		 int i;
		 int bufi = 0;
		 int it = 0;
		 int compte = 1;
		 float max;
		 for(i=0;i<9;i++)
		 {
			 ordre[i] = -1;
		 }
		 while(compte>0)
		 {
			 compte = 0;
			 max = 0;
			 for(i=0;i<9;i++)
			 {
				 if(ordre[i]<0 && max<Main.ProbaClic[i])
				 {
					 max = Main.ProbaClic[i];
					 compte = 1;
					 bufi = i;
				 }
			 }
			 if(compte>0)
			 {
				 ordre[bufi] = it;
				 it = it + 1;
			 }
		 }
		 
		 return ordre;
	 }
	 
	 private void apprendProba(int i) throws IOException
	 {
		 Main.ProbaClic[i] = Main.ProbaClic[i] + 1;
		 for(i=0;i<9;i++)
		 {
			 Main.ProbaClic[i] = Main.ProbaClic[i]*((float) 0.997) + ((float) 0.01);
		 }
		 
		 BufferedWriter writer = new BufferedWriter(new FileWriter("Config/ProbaClic.txt"));
       for(i=0;i<9;i++)
		 {

			 writer.write(Float.toString(Main.ProbaClic[i]));
      	     writer.newLine();
      	     writer.flush();
		 }
       writer.close();
	 }
}

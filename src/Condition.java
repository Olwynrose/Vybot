import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Condition 
{
	int detection;
	int chat;
	float[][] Dif_name;		// diffusion nom
	
	static int imin;
	static int jmin;
	
	public Condition() throws IOException, InterruptedException, AWTException
	{
		detection = 0;
		chat = 0;
		
		int i,j,k;
		double a;
		
		// random
		double Min = 25; // 6.25 sec
		double Max = 10800; // 45 min
		double k_rand = 0.01*Math.log(Min/Max);
		int it_lim;
		double th_rand;
		
		// chat
		float th_txt = (float) 40;
		float th_diff = (float) 0.07;
		float th_name = (float) 30;
		int i0 = Main.references.idimChat;	// position chat
		int j0 = Main.references.jdimChat;	// position chat
		int idim = 20;			// dimension chat
        int jdim = 400;			// dimension chat
        int iidim;				// dimension texte ref
        int jjdim;				// dimension texte ref
        int nameidim = 20;		// dimension nom
        int namejdim = 50;		// dimension nom
        int[][][] Im;			// image extrait écran fiesta
        float[][] Dif_Fen_text;	// diffusion chat
        float[][] Dif_text_buff;// diffusion texte "buff"
        int demandeBuff;		// >0 si un buff est demandé
        
        // Vie
        int i0_vie = Main.references.idimVie;	// position vie
		int j0_vie = Main.references.jdimVie;	// position vie
		int idim_vie = 2;			// dimension vie
        int jdim_vie = 90;			// dimension vie
        int cond = 0;
        int col = 0;
        
        // Mob
        int i0_selec = Main.references.idimSelec;	// position vie
		int j0_selec = Main.references.jdimSelec;	// position vie
		int idim_selec = 2;			// dimension vie
        int jdim_selec = 90;			// dimension vie
        int moyr = 0;
        int moyg = 0;
        int moyb = 0;
        int th_selec = 50;
        // personalisable
        int l_selec = Main.dim_cond;
        int i_valid;
        int j_valid;
        
		for(i=0; i<Main.n_seq1; i++)
		{
			if(Main.donnees.is_act[Main.seq][i]==1)
			{
				
				switch(Main.donnees.conds[i])
				{
				case 0:
	    		{ // random
	    			Main.it_rand = Main.it_rand + 1;
	    			a = Math.random();
	    			/*if( a<  Math.pow(2, ((double) Main.donnees.th_conds[i])/12.5)/16000   )
	    			{
	    				// pour le lancement de la séquence d'action correspondant (i)
	    				detection = 1;
	    				Main.seq1 = i;
	    			}*/
	    			it_lim = (int) (Max*Math.exp(k_rand*((double) Main.donnees.th_conds[i])));
	    			if(Main.it_rand<it_lim)
	    			{
	    				th_rand = Main.it_rand/(it_lim*it_lim);
	    			}
	    			else
	    			{
	    				th_rand = (1/it_lim) + (Main.it_rand-it_lim)*0.05/it_lim;
	    			}
	    			System.out.println(th_rand);
	    			if(a<th_rand)
	    			{
	    				// pour le lancement de la séquence d'action correspondant (i)
	    				detection = 1;
	    				Main.seq1 = i;
	    				Main.it_rand = 0;
	    			}
	    			
	    		}
	    		break;
				case 1: // ================================= Chat ================================
	    		{ // chat
	    			 // recherche de mots cles dans le chat
	                
	              	// capture l'ecran
	    			
	                     Robot robot = new Robot();
	                      
	                     Rectangle captureRect = new Rectangle(j0, i0, jdim, idim);
	                     BufferedImage screenFiesta = robot.createScreenCapture(captureRect);
	                     Im = convertTo2DUsingGetRGB(screenFiesta);
	                       
	             switch(i)
	             {
	             case 0:
		    		{ //texte 1
		    			iidim = Main.iidim1;
		    			jjdim = Main.jjdim1;
		    			Dif_text_buff = ImText(Main.Im_Text_1, iidim, jjdim, th_txt, 1);
		    		}
		    		break;
	             case 1:
		    		{ // texte 2
		    			iidim = Main.iidim2;
		    			jjdim = Main.jjdim2;
		    			Dif_text_buff = ImText(Main.Im_Text_2, iidim, jjdim, th_txt, 1);
		    		}
		    		break;
	             case 2:
		    		{ // texte 3
		    			iidim = Main.iidim3;
		    			jjdim = Main.jjdim3;
		    			Dif_text_buff = ImText(Main.Im_Text_3, iidim, jjdim, th_txt, 1);
		    		}
		    		break;
	             case 3:
		    		{ // texte 4
		    			iidim = Main.iidim4;
		    			jjdim = Main.jjdim4;
		    			Dif_text_buff = ImText(Main.Im_Text_4, iidim, jjdim, th_txt, 1);
		    		}
		    		break;
	             case 4:
		    		{ // texte 5
		    			iidim = Main.iidim5;
		    			jjdim = Main.jjdim5;
		    			Dif_text_buff = ImText(Main.Im_Text_5, iidim, jjdim, th_txt, 1);
		    		}
		    		break;
	             case 5:
		    		{ // texte 6
		    			iidim = Main.iidim6;
		    			jjdim = Main.jjdim6;
		    			Dif_text_buff = ImText(Main.Im_Text_6, iidim, jjdim, th_txt, 1);
		    		}
		    		break;
	             case 6:
		    		{ // texte 7
		    			iidim = Main.iidim7;
		    			jjdim = Main.jjdim7;
		    			Dif_text_buff = ImText(Main.Im_Text_7, iidim, jjdim, th_txt, 1);
		    		}
		    		break;
	             case 7:
		    		{ // texte 8
		    			iidim = Main.iidim8;
		    			jjdim = Main.jjdim8;
		    			Dif_text_buff = ImText(Main.Im_Text_8, iidim, jjdim, th_txt, 1);
		    		}
		    		break;
	             case 8:
		    		{ // texte 9
		    			iidim = Main.iidim9;
		    			jjdim = Main.jjdim9;
		    			Dif_text_buff = ImText(Main.Im_Text_9, iidim, jjdim, th_txt, 1);
		    		}
		    		break;
	             case 9:
		    		{ // texte 10
		    			iidim = Main.iidim10;
		    			jjdim = Main.jjdim10;
		    			Dif_text_buff = ImText(Main.Im_Text_10, iidim, jjdim, th_txt, 1);
		    		}
		    		break;
	             case 10:
		    		{ // texte 11
		    			iidim = Main.iidim11;
		    			jjdim = Main.jjdim11;
		    			Dif_text_buff = ImText(Main.Im_Text_11, iidim, jjdim, th_txt, 1);
		    		}
		    		break;
	             case 11:
		    		{ // texte 12
		    			iidim = Main.iidim12;
		    			jjdim = Main.jjdim12;
		    			Dif_text_buff = ImText(Main.Im_Text_12, iidim, jjdim, th_txt, 1);
		    		}
		    		break;
	             case 12:
		    		{ // texte 13
		    			iidim = Main.iidim13;
		    			jjdim = Main.jjdim13;
		    			Dif_text_buff = ImText(Main.Im_Text_13, iidim, jjdim, th_txt, 1);
		    		}
		    		break;
	             case 13:
		    		{ // texte 14
		    			iidim = Main.iidim14;
		    			jjdim = Main.jjdim14;
		    			Dif_text_buff = ImText(Main.Im_Text_14, iidim, jjdim, th_txt, 1);
		    		}
		    		break;
	             case 14:
		    		{ // texte 15
		    			iidim = Main.iidim15;
		    			jjdim = Main.jjdim15;
		    			Dif_text_buff = ImText(Main.Im_Text_15, iidim, jjdim, th_txt, 1);
		    		}
		    		break;
	             case 15:
		    		{ // texte 16
		    			iidim = Main.iidim16;
		    			jjdim = Main.jjdim16;
		    			Dif_text_buff = ImText(Main.Im_Text_16, iidim, jjdim, th_txt, 1);
		    		}
		    		break;
	             case 16:
		    		{ // texte 17
		    			iidim = Main.iidim17;
		    			jjdim = Main.jjdim17;
		    			Dif_text_buff = ImText(Main.Im_Text_17, iidim, jjdim, th_txt, 1);
		    		}
		    		break;
	             default:
	             {
	            	 iidim = Main.iidim18;
	            	 jjdim = Main.jjdim18;
	            	 Dif_text_buff = ImText(Main.Im_Text_18, iidim, jjdim, th_txt, 1);
	             }
	             }
	             // Extrait les cartes de diffusions (extraction des textes)
	                   
	             
	             Dif_Fen_text = ImText(Im, idim, jdim, th_txt, 3); 
	             demandeBuff = RechercheDiff(Dif_Fen_text, idim-3, jdim-3, Dif_text_buff,  iidim-3,  jjdim-3, th_diff);
	             if(demandeBuff>0)
	             {
	            	 Dif_name = ImText(Im, nameidim, namejdim, th_name, 2);
	            	 chat = 1;
	            	// pour le lancement de la séquence d'action correspondant (i)
	            	 detection = 1;
	            	 Main.seq1 = i;
	            	 
	             }
	             if(demandeBuff==0)
	             {
	    	         Dif_Fen_text = ImText(Im, idim, jdim, th_txt, 4); 
	    	         demandeBuff = RechercheDiff(Dif_Fen_text, idim-3, jdim-3, Dif_text_buff,  iidim-3,  jjdim-3, th_diff);
	    	         if(demandeBuff>0)
	    	         {
	    	
	            		 for(k=0;k<nameidim;k++)
	            		 {
	            			 for(j=0;j<namejdim;j++)
	                		 {
	                			 Im[k][j] = Im[k][j+33];
	                		 }
	            		 }
	            		 Dif_name = ImText(Im, nameidim, namejdim, th_name, 2);
	            		 chat = 1;
	            		// pour le lancement de la séquence d'action correspondant (i)
	            		 detection = 1;
	            		 Main.seq1 = i;
	    	         }
	             }
	             
	             
	    		}
	    		break;
				case 2: // ================================= Vie ================================
	    		{ // vie
	    			Robot robot = new Robot();
                    
                    Rectangle captureRect = new Rectangle(j0_vie, i0_vie, jdim_vie, idim_vie);
                    BufferedImage screenFiesta = robot.createScreenCapture(captureRect);
                    Im = convertTo2DUsingGetRGB(screenFiesta);
                    
                    j = 0;
                    cond = 0;
                    while(cond==0)
                    {
                    	
                    	col = Im[0][j][0] + Im[1][j][0];
                    	j = j + 1;
                    	if(col<400 || j==jdim_vie)
                    	{
                    		cond = 1;
                    		j = j - 1;
                    	}
                    }
                    
                    if(j<(Main.donnees.th_conds[i]*90/100-1))
                    {
                    	// pour le lancement de la séquence d'action correspondant (i)
                    	detection = 1;
	    				Main.seq1 = i;
                    }
	    		}
	    		break;
				case 3: // ================================= Mana ================================
	    		{ // mana 
	    			Robot robot = new Robot();
                    
                    Rectangle captureRect = new Rectangle(j0_vie+3, i0_vie+32, jdim_vie, idim_vie);
                    BufferedImage screenFiesta = robot.createScreenCapture(captureRect);
                    Im = convertTo2DUsingGetRGB(screenFiesta);
                    
                    j = 0;
                    cond = 0;
                    while(cond==0)
                    {
                    	col = Im[0][j][2] + Im[1][j][2];
                    	j = j + 1;
                    	if(col<300 || j==jdim_vie)
                    	{
                    		cond = 1;
                    		j = j - 1;
                    	}
                    }
                    if(j<(Main.donnees.th_conds[i]*90/100-1))
                    {
                    	// pour le lancement de la séquence d'action correspondant (i)
                    	detection = 1;
	    				Main.seq1 = i;
                    }
	    		}
	    		break;
				case 4: // ================================= Mob ================================
	    		{ // mob
	    			Robot robot = new Robot();
                    
                    Rectangle captureRect = new Rectangle(j0_selec-68, i0_selec+19, jdim_selec, idim_selec);
                    BufferedImage screenFiesta = robot.createScreenCapture(captureRect);
                    Im = convertTo2DUsingGetRGB(screenFiesta);
                    for(j=0;j<jdim_selec;j++)
                    {
                    	for(k=0;k<idim_selec;k++)
                        {
                        	moyr = moyr + Im[k][j][0];
                        	moyg = moyg + Im[k][j][1];
                        	moyb = moyb + Im[k][j][2];
                        }
                    }
                    moyr = moyr/(idim_selec*jdim_selec);
                    moyg = moyg/(idim_selec*jdim_selec);
                    moyb = moyb/(idim_selec*jdim_selec);
                    //System.out.print("mr " + moyr);
                    //System.out.print("mg " + moyg);
                    //System.out.println("mb " + moyb);
                    if(Main.typeInteface == 2)
                    {
                    	System.out.println(((moyr-78)*(moyr-78) + (moyg-84)*(moyg-84) + (moyb-48)*(moyb-48)));
                    	if( ((moyr-78)*(moyr-78) + (moyg-84)*(moyg-84) + (moyb-48)*(moyb-48))<th_selec )
	                    {
                    		// pour le lancement de la séquence d'action correspondant (i)
	                    	detection = 1;
		    				Main.seq1 = i;
	                    }
                    }
                    else
                    {
	                    if( ((moyr-232)*(moyr-232) + (moyg-247)*(moyg-247) + (moyb-252)*(moyb-252))<th_selec )
	                    {
	                    	// pour le lancement de la séquence d'action correspondant (i)
	                    	detection = 1;
		    				Main.seq1 = i;
	                    }
                    }
	    		}
	    		break;
				case 5: // ================================= Cond perso ================================
	    		{ // condition personalisable
	    			Robot robot = new Robot();
	    			i_valid = Main.coor_Cond[i][0] + Main.references.idimVie;
	    			j_valid = Main.coor_Cond[i][1] + Main.references.jdimVie;
	    			
	    			Rectangle captureRect = new Rectangle(j_valid, i_valid, l_selec, l_selec);
	    			BufferedImage screenFiesta = robot.createScreenCapture(captureRect);
	    	        Im = convertTo2DUsingGetRGB(screenFiesta);
	    	        int err = 0;
	    	        for(int ii=0;ii<l_selec;ii++)
	    			{
	    				for(j=0;j<l_selec;j++)
	    				{
	    					for(k=0;k<3;k++)
	    					{
	    						err = err + Math.abs(Im[ii][j][k]-Main.Im_Cond[i][j][ii][k]);
    						}
	    				}
	    			}
    				
	    	        if(err<l_selec*l_selec*Main.donnees.th_conds[i])
	    	        {
	    	        	// pour le lancement de la séquence d'action correspondant (i)
                    	detection = 1;
	    				Main.seq1 = i;
	    	        }
	    			
	    		}
	    		break;
				}
				
				if(detection==1)
				{
					i = Main.n_seq1;
					
				}
				
			} // fin des conditions
		}
	} // fin du constructeur



	/*
	 *  ======================================================
	 * 	======================================================
	 * 	======================================================
	 */
	
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
	 /*// never used locally
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
	
}

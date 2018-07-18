import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;



public class PanLearn3
{	
	JButton bRetour;
	JButton bValider;
	
	int cond;
	
	public PanLearn3(String str_nom) throws InterruptedException, AWTException, IOException
	{
		
		JTextArea tTips = new JTextArea("La touche valider lance un automate."); 
		tTips.setOpaque(false);
		tTips.setFont(new Font("Consolas", Font.PLAIN, 19));
		tTips.setForeground(Color.WHITE);
		JTextArea tTips1 = new JTextArea("Assurez vous que fiesta online est ouvert et visible."); 
		tTips1.setOpaque(false);
		tTips1.setFont(new Font("Consolas", Font.PLAIN, 16));
		tTips1.setForeground(Color.WHITE);
		Robot robot = new Robot();
		Rectangle captureRect;
		BufferedImage screenFiesta;
		StringSelection selec;
		Clipboard clipboard;
		int i,j,k;
		int bufj = 6;
		int[][][] Im;
		float[][] imText;
		int i0, j0, idim, jdim;
		
		cond = 0;
		Main.pan.removeAll();
		
		Main.donnees.str_conds[Main.seq1] = str_nom;
		Main.donnees.sauvegarde1();
		
		tTips.setBounds(310, 150, Main.jdim-20, 60);
		Main.pan.add(tTips);
		tTips1.setBounds(260, 190, Main.jdim-20, 60);
		Main.pan.add(tTips1);
		
		bRetour = new JButton(Main.iBouton);
		bRetour.setText("Retour");
		bRetour.setVerticalTextPosition(SwingConstants.CENTER);
		bRetour.setHorizontalTextPosition(SwingConstants.CENTER);
		bRetour.setBounds(0, Main.idim - 60, 130, 25);
		Main.pan.add(bRetour);
		bRetour.addActionListener(new ARetour());

		bValider = new JButton(Main.iBouton);
		bValider.setText("Valider");
		bValider.setVerticalTextPosition(SwingConstants.CENTER);
		bValider.setHorizontalTextPosition(SwingConstants.CENTER);
		bValider.setBounds(Main.jdim-130, Main.idim - 60, 130, 25);
		Main.pan.add(bValider);
		bValider.addActionListener(new AValider());
		
		Main.pan.add(Main.fondPanel);
		Main.pan.repaint();
		
		while(cond==0)
		{
			Thread.sleep(200);
		}
		
		if(cond == 2){
			Main.onglet = 5;
			tTips.setText("Ne touchez à rien !");
			tTips1.setVisible(false);
			tTips.setBounds(420, 150, Main.jdim-20, 60);
			Thread.sleep(500);
			robot.mouseMove(100,10);
	        Thread.sleep(50);
	        robot.mousePress(InputEvent.BUTTON1_MASK);
	        Thread.sleep(50);
	        robot.mouseRelease(InputEvent.BUTTON1_MASK);
	        Thread.sleep(50);
	        
	        selec = new StringSelection(str_nom);
	        clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(selec, selec);
			robot.keyPress(KeyEvent.VK_ENTER);
	        Thread.sleep(50);
	        robot.keyRelease(KeyEvent.VK_ENTER);
	        Thread.sleep(50);
	        robot.keyPress(KeyEvent.VK_SPACE);
	        Thread.sleep(50);
	        robot.keyRelease(KeyEvent.VK_SPACE);
	        Thread.sleep(50);
	        robot.keyPress(KeyEvent.VK_SPACE);
	        Thread.sleep(50);
	        robot.keyRelease(KeyEvent.VK_SPACE);
	        Thread.sleep(50);
	        robot.keyPress(KeyEvent.VK_CONTROL);
		    Thread.sleep(50);
		    robot.keyPress(KeyEvent.VK_V);
		    Thread.sleep(50);
		    robot.keyRelease(KeyEvent.VK_V);
		    Thread.sleep(50);
		    robot.keyRelease(KeyEvent.VK_CONTROL);
		    Thread.sleep(100);
		    for(i=0;i<str_nom.length()+3;i++)
		    {
		    	robot.keyPress(KeyEvent.VK_LEFT);
		        Thread.sleep(50);
		        robot.keyRelease(KeyEvent.VK_LEFT);
		        Thread.sleep(50);
		    }
			
	        i0 = Main.references.idimChat+30;
	        j0 = Main.references.jdimChat-2;
	        idim = 10;
	        jdim = 200;
			captureRect = new Rectangle(j0, i0, jdim, idim);
			screenFiesta = robot.createScreenCapture(captureRect);
	        Im = convertTo2DUsingGetRGB(screenFiesta);
	        imText = ImText(Im, idim, jdim, (float) 30, 3); 
	        for(j=jdim-4;j>0;j--)
	        {
	        	for(i=0;i<idim-4;i++)
	            {
	            	if(imText[i][j]>0.2)
	            	{
	            		bufj = j+6;
	            		j = 0;
	            	}
	            }
	        }
	        switch(Main.seq1)
	        {
		        case 0:
		        {
		        	BufferedWriter writer = new BufferedWriter(new FileWriter("Config/LearnedText1.txt"));
			        writer.write(Integer.toString(idim));
			           writer.newLine();
			           writer.flush();
			           writer.write(Integer.toString(bufj));
			        writer.newLine();
			        writer.flush();
			        for(i=0;i<idim;i++)
					 {
			       	 for(j=0;j<bufj;j++)
			   		 {
			       		 for(k=0;k<3;k++)
			       		 {
		       			 	  writer.write(Integer.toString(Im[i][j][k]));
			       	          writer.newLine();
			       	          writer.flush();
			       		 }
			   		 }
					 }
			        writer.close();
		        }
		        break;
		        case 1:
		        {
		        	BufferedWriter writer = new BufferedWriter(new FileWriter("Config/LearnedText2.txt"));
			        writer.write(Integer.toString(idim));
			           writer.newLine();
			           writer.flush();
			           writer.write(Integer.toString(bufj));
			        writer.newLine();
			        writer.flush();
			        for(i=0;i<idim;i++)
					 {
			       	 for(j=0;j<bufj;j++)
			   		 {
			       		 for(k=0;k<3;k++)
			       		 {
		       			 	  writer.write(Integer.toString(Im[i][j][k]));
			       	          writer.newLine();
			       	          writer.flush();
			       		 }
			   		 }
					 }
			        writer.close();
		        }
		        break;
		        case 2:
		        {
		        	BufferedWriter writer = new BufferedWriter(new FileWriter("Config/LearnedText3.txt"));
			        writer.write(Integer.toString(idim));
			           writer.newLine();
			           writer.flush();
			           writer.write(Integer.toString(bufj));
			        writer.newLine();
			        writer.flush();
			        for(i=0;i<idim;i++)
					 {
			       	 for(j=0;j<bufj;j++)
			   		 {
			       		 for(k=0;k<3;k++)
			       		 {
		       			 	  writer.write(Integer.toString(Im[i][j][k]));
			       	          writer.newLine();
			       	          writer.flush();
			       		 }
			   		 }
					 }
			        writer.close();
		        }
		        break;
		        case 3:
		        {
		        	BufferedWriter writer = new BufferedWriter(new FileWriter("Config/LearnedText4.txt"));
			        writer.write(Integer.toString(idim));
			           writer.newLine();
			           writer.flush();
			           writer.write(Integer.toString(bufj));
			        writer.newLine();
			        writer.flush();
			        for(i=0;i<idim;i++)
					 {
			       	 for(j=0;j<bufj;j++)
			   		 {
			       		 for(k=0;k<3;k++)
			       		 {
		       			 	  writer.write(Integer.toString(Im[i][j][k]));
			       	          writer.newLine();
			       	          writer.flush();
			       		 }
			   		 }
					 }
			        writer.close();
		        }
		        break;
		        case 4:
		        {
		        	BufferedWriter writer = new BufferedWriter(new FileWriter("Config/LearnedText5.txt"));
			        writer.write(Integer.toString(idim));
			           writer.newLine();
			           writer.flush();
			           writer.write(Integer.toString(bufj));
			        writer.newLine();
			        writer.flush();
			        for(i=0;i<idim;i++)
					 {
			       	 for(j=0;j<bufj;j++)
			   		 {
			       		 for(k=0;k<3;k++)
			       		 {
		       			 	  writer.write(Integer.toString(Im[i][j][k]));
			       	          writer.newLine();
			       	          writer.flush();
			       		 }
			   		 }
					 }
			        writer.close();
		        }
		        break;
		        case 5:
		        {
		        	BufferedWriter writer = new BufferedWriter(new FileWriter("Config/LearnedText6.txt"));
			        writer.write(Integer.toString(idim));
			           writer.newLine();
			           writer.flush();
			           writer.write(Integer.toString(bufj));
			        writer.newLine();
			        writer.flush();
			        for(i=0;i<idim;i++)
					 {
			       	 for(j=0;j<bufj;j++)
			   		 {
			       		 for(k=0;k<3;k++)
			       		 {
		       			 	  writer.write(Integer.toString(Im[i][j][k]));
			       	          writer.newLine();
			       	          writer.flush();
			       		 }
			   		 }
					 }
			        writer.close();
		        }
		        break;
		        case 6:
		        {
		        	BufferedWriter writer = new BufferedWriter(new FileWriter("Config/LearnedText7.txt"));
			        writer.write(Integer.toString(idim));
			           writer.newLine();
			           writer.flush();
			           writer.write(Integer.toString(bufj));
			        writer.newLine();
			        writer.flush();
			        for(i=0;i<idim;i++)
					 {
			       	 for(j=0;j<bufj;j++)
			   		 {
			       		 for(k=0;k<3;k++)
			       		 {
		       			 	  writer.write(Integer.toString(Im[i][j][k]));
			       	          writer.newLine();
			       	          writer.flush();
			       		 }
			   		 }
					 }
			        writer.close();
		        }
		        break;
		        case 7:
		        {
		        	BufferedWriter writer = new BufferedWriter(new FileWriter("Config/LearnedText8.txt"));
			        writer.write(Integer.toString(idim));
			           writer.newLine();
			           writer.flush();
			           writer.write(Integer.toString(bufj));
			        writer.newLine();
			        writer.flush();
			        for(i=0;i<idim;i++)
					 {
			       	 for(j=0;j<bufj;j++)
			   		 {
			       		 for(k=0;k<3;k++)
			       		 {
		       			 	  writer.write(Integer.toString(Im[i][j][k]));
			       	          writer.newLine();
			       	          writer.flush();
			       		 }
			   		 }
					 }
			        writer.close();
		        }
		        break;
		        case 8:
		        {
		        	BufferedWriter writer = new BufferedWriter(new FileWriter("Config/LearnedText9.txt"));
			        writer.write(Integer.toString(idim));
			           writer.newLine();
			           writer.flush();
			           writer.write(Integer.toString(bufj));
			        writer.newLine();
			        writer.flush();
			        for(i=0;i<idim;i++)
					 {
			       	 for(j=0;j<bufj;j++)
			   		 {
			       		 for(k=0;k<3;k++)
			       		 {
		       			 	  writer.write(Integer.toString(Im[i][j][k]));
			       	          writer.newLine();
			       	          writer.flush();
			       		 }
			   		 }
					 }
			        writer.close();
		        }
		        break;
		        case 9:
		        {
		        	BufferedWriter writer = new BufferedWriter(new FileWriter("Config/LearnedText10.txt"));
			        writer.write(Integer.toString(idim));
			           writer.newLine();
			           writer.flush();
			           writer.write(Integer.toString(bufj));
			        writer.newLine();
			        writer.flush();
			        for(i=0;i<idim;i++)
					 {
			       	 for(j=0;j<bufj;j++)
			   		 {
			       		 for(k=0;k<3;k++)
			       		 {
		       			 	  writer.write(Integer.toString(Im[i][j][k]));
			       	          writer.newLine();
			       	          writer.flush();
			       		 }
			   		 }
					 }
			        writer.close();
		        }
		        break;
		        case 10:
		        {
		        	BufferedWriter writer = new BufferedWriter(new FileWriter("Config/LearnedText11.txt"));
			        writer.write(Integer.toString(idim));
			           writer.newLine();
			           writer.flush();
			           writer.write(Integer.toString(bufj));
			        writer.newLine();
			        writer.flush();
			        for(i=0;i<idim;i++)
					 {
			       	 for(j=0;j<bufj;j++)
			   		 {
			       		 for(k=0;k<3;k++)
			       		 {
		       			 	  writer.write(Integer.toString(Im[i][j][k]));
			       	          writer.newLine();
			       	          writer.flush();
			       		 }
			   		 }
					 }
			        writer.close();
		        }
		        break;
		        case 11:
		        {
		        	BufferedWriter writer = new BufferedWriter(new FileWriter("Config/LearnedText12.txt"));
			        writer.write(Integer.toString(idim));
			           writer.newLine();
			           writer.flush();
			           writer.write(Integer.toString(bufj));
			        writer.newLine();
			        writer.flush();
			        for(i=0;i<idim;i++)
					 {
			       	 for(j=0;j<bufj;j++)
			   		 {
			       		 for(k=0;k<3;k++)
			       		 {
		       			 	  writer.write(Integer.toString(Im[i][j][k]));
			       	          writer.newLine();
			       	          writer.flush();
			       		 }
			   		 }
					 }
			        writer.close();
		        }
		        break;
		        case 12:
		        {
		        	BufferedWriter writer = new BufferedWriter(new FileWriter("Config/LearnedText13.txt"));
			        writer.write(Integer.toString(idim));
			           writer.newLine();
			           writer.flush();
			           writer.write(Integer.toString(bufj));
			        writer.newLine();
			        writer.flush();
			        for(i=0;i<idim;i++)
					 {
			       	 for(j=0;j<bufj;j++)
			   		 {
			       		 for(k=0;k<3;k++)
			       		 {
		       			 	  writer.write(Integer.toString(Im[i][j][k]));
			       	          writer.newLine();
			       	          writer.flush();
			       		 }
			   		 }
					 }
			        writer.close();
		        }
		        break;
		        case 13:
		        {
		        	BufferedWriter writer = new BufferedWriter(new FileWriter("Config/LearnedText14.txt"));
			        writer.write(Integer.toString(idim));
			           writer.newLine();
			           writer.flush();
			           writer.write(Integer.toString(bufj));
			        writer.newLine();
			        writer.flush();
			        for(i=0;i<idim;i++)
					 {
			       	 for(j=0;j<bufj;j++)
			   		 {
			       		 for(k=0;k<3;k++)
			       		 {
		       			 	  writer.write(Integer.toString(Im[i][j][k]));
			       	          writer.newLine();
			       	          writer.flush();
			       		 }
			   		 }
					 }
			        writer.close();
		        }
		        break;
		        case 14:
		        {
		        	BufferedWriter writer = new BufferedWriter(new FileWriter("Config/LearnedText15.txt"));
			        writer.write(Integer.toString(idim));
			           writer.newLine();
			           writer.flush();
			           writer.write(Integer.toString(bufj));
			        writer.newLine();
			        writer.flush();
			        for(i=0;i<idim;i++)
					 {
			       	 for(j=0;j<bufj;j++)
			   		 {
			       		 for(k=0;k<3;k++)
			       		 {
		       			 	  writer.write(Integer.toString(Im[i][j][k]));
			       	          writer.newLine();
			       	          writer.flush();
			       		 }
			   		 }
					 }
			        writer.close();
		        }
		        break;
		        case 15:
		        {
		        	BufferedWriter writer = new BufferedWriter(new FileWriter("Config/LearnedText16.txt"));
			        writer.write(Integer.toString(idim));
			           writer.newLine();
			           writer.flush();
			           writer.write(Integer.toString(bufj));
			        writer.newLine();
			        writer.flush();
			        for(i=0;i<idim;i++)
					 {
			       	 for(j=0;j<bufj;j++)
			   		 {
			       		 for(k=0;k<3;k++)
			       		 {
		       			 	  writer.write(Integer.toString(Im[i][j][k]));
			       	          writer.newLine();
			       	          writer.flush();
			       		 }
			   		 }
					 }
			        writer.close();
		        }
		        break;
		        case 16:
		        {
		        	BufferedWriter writer = new BufferedWriter(new FileWriter("Config/LearnedText17.txt"));
			        writer.write(Integer.toString(idim));
			           writer.newLine();
			           writer.flush();
			           writer.write(Integer.toString(bufj));
			        writer.newLine();
			        writer.flush();
			        for(i=0;i<idim;i++)
					 {
			       	 for(j=0;j<bufj;j++)
			   		 {
			       		 for(k=0;k<3;k++)
			       		 {
		       			 	  writer.write(Integer.toString(Im[i][j][k]));
			       	          writer.newLine();
			       	          writer.flush();
			       		 }
			   		 }
					 }
			        writer.close();
		        }
		        break;
		        case 17:
		        {
		        	BufferedWriter writer = new BufferedWriter(new FileWriter("Config/LearnedText18.txt"));
			        writer.write(Integer.toString(idim));
			           writer.newLine();
			           writer.flush();
			           writer.write(Integer.toString(bufj));
			        writer.newLine();
			        writer.flush();
			        for(i=0;i<idim;i++)
					 {
			       	 for(j=0;j<bufj;j++)
			   		 {
			       		 for(k=0;k<3;k++)
			       		 {
		       			 	  writer.write(Integer.toString(Im[i][j][k]));
			       	          writer.newLine();
			       	          writer.flush();
			       		 }
			   		 }
					 }
			        writer.close();
		        }
		        break;
	        } // fin switch seq1
	        
	        
	        
	        
	        for(i=0;i<str_nom.length()+3;i++)
		    {
		    	robot.keyPress(KeyEvent.VK_DELETE);
		        Thread.sleep(50);
		        robot.keyRelease(KeyEvent.VK_DELETE);
		        Thread.sleep(50);
		    }
	        robot.keyPress(KeyEvent.VK_ENTER);
	        Thread.sleep(50);
	        robot.keyRelease(KeyEvent.VK_ENTER);
	        Thread.sleep(50);
		} // fin de cond == 2
        
        
	} // fin du constructeur
	
	private static int[][][] convertTo2DUsingGetRGB(BufferedImage image) {
	      int width = image.getWidth();
	      int height = image.getHeight();
	      int[][][] result = new int[height][width][3];

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
        Thread.sleep(17000);*/ // test detection text
        
		 return Im_text;
	 } // fin ImText
	 
	 public class ARetour implements ActionListener
		{
			public void actionPerformed(ActionEvent e) 
			{
				cond = 1;
				Main.onglet = 5;
				bRetour.setEnabled(false);
			}
		}

		public class AValider implements ActionListener
		{
			public void actionPerformed(ActionEvent e) 
			{
				cond = 2;
				Main.onglet = 5;
				bRetour.setEnabled(false);
			}
		}
}

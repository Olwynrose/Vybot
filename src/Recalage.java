import java.awt.AWTException;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Recalage {

	int idimChat;	// position du chat
	int jdimChat;	// position du chat
	int idimSelec;	// position de la selection
	int jdimSelec;	// position de la selection
	int idimCarte;	// position de la carte
	int jdimCarte;	// position de la carte
	int idimVie;	// position de la vie
	int jdimVie;	// position de la vie

	int err;		// pour la recherche du type d'interface (normale, noel ou halloween)
	int buf_Err;	// pour la recherche du type d'interface (normale, noel ou halloween)
	int Type; 		// 0: normal, 1: Halloween, 2: Noel
	
	static int imin;
	static int jmin;
	
	public Recalage() throws AWTException, InterruptedException, IOException
	{
		if(Main.donnees.etalonnage==0)
		{
			Robot robot = new Robot();
			Rectangle captureRect;
			BufferedImage screenFiesta;
			int[][][] Im;
			int i0, j0, idim, jdim, buf_imin, buf_jmin;
			int i,j,k;
			BufferedReader br;
	        StringBuilder sb;
	        String line;
	        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			int jMax = gd.getDisplayMode().getWidth();
			int iMax = gd.getDisplayMode().getHeight();
	
	     // ================ recalage chat ===================
	        
	// charge l'invariant du chat normal
	        br = new BufferedReader(new FileReader("Config/Im_InvChat.txt"));
	        sb = new StringBuilder();
	        line = "125";
	    	sb.append(line);
	        sb.append(System.lineSeparator());
	        line = br.readLine();
	        int iidim_chat = Integer.parseInt(line);
	        sb.append(line);
	        sb.append(System.lineSeparator());
	        line = br.readLine();
	        int jjdim_chat = Integer.parseInt(line);
	        int Im_InvChat[][][] = new int[iidim_chat][jjdim_chat][3];
	        for(i=0;i<iidim_chat;i++)
	        {
	        	for(j=0;j<jjdim_chat;j++)
	            {
	        		for(k=0;k<3;k++)
	                {
	        			sb.append(line);
	        	        sb.append(System.lineSeparator());
	        	        line = br.readLine();
	        	        Im_InvChat[i][j][k] = Integer.parseInt(line);
	                }
	            }
	        }
	        br.close();
	
	        i0 = 500;
	        j0 = 0;
	        idim = Math.min(450, iMax-500);
	        jdim = 300;
			captureRect = new Rectangle(j0, i0, jdim, idim);
			screenFiesta = robot.createScreenCapture(captureRect);
	        Im = convertTo2DUsingGetRGB(screenFiesta);
	        buf_Err = RechercheCoul(Im, idim, jdim, Im_InvChat, iidim_chat, jjdim_chat, 1);
	        Type = 0;
	        Main.typeInteface = 0;
	        buf_imin = imin;
	        buf_jmin = jmin;
	        
	     // charge l'invariant du chat Halloween
	        br = new BufferedReader(new FileReader("Config/Im_InvChat_Hallo.txt"));
	        sb = new StringBuilder();
	        line = "125";
	    	sb.append(line);
	        sb.append(System.lineSeparator());
	        line = br.readLine();
	        iidim_chat = Integer.parseInt(line);
	        sb.append(line);
	        sb.append(System.lineSeparator());
	        line = br.readLine();
	        jjdim_chat = Integer.parseInt(line);
	        for(i=0;i<iidim_chat;i++)
	        {
	        	for(j=0;j<jjdim_chat;j++)
	            {
	        		for(k=0;k<3;k++)
	                {
	        			sb.append(line);
	        	        sb.append(System.lineSeparator());
	        	        line = br.readLine();
	        	        Im_InvChat[i][j][k] = Integer.parseInt(line);
	                }
	            }
	        }
	        br.close();
	
	        i0 = 500;
	        j0 = 0;
	        idim = Math.min(450, iMax-500);
	        jdim = 300;
			captureRect = new Rectangle(j0, i0, jdim, idim);
			screenFiesta = robot.createScreenCapture(captureRect);
	        Im = convertTo2DUsingGetRGB(screenFiesta);
	        err = RechercheCoul(Im, idim, jdim, Im_InvChat, iidim_chat, jjdim_chat, 1);
	        if(err<buf_Err)
	        {
	        	buf_Err = err;
		        Type = 1;
		        Main.typeInteface = 1;
		        buf_imin = imin;
		        buf_jmin = jmin;
	        }
	        
	     // charge l'invariant du chat Noel
	        br = new BufferedReader(new FileReader("Config/Im_InvChat_Noel.txt"));
	        sb = new StringBuilder();
	        line = "125";
	    	sb.append(line);
	        sb.append(System.lineSeparator());
	        line = br.readLine();
	        iidim_chat = Integer.parseInt(line);
	        sb.append(line);
	        sb.append(System.lineSeparator());
	        line = br.readLine();
	        jjdim_chat = Integer.parseInt(line);
	        for(i=0;i<iidim_chat;i++)
	        {
	        	for(j=0;j<jjdim_chat;j++)
	            {
	        		for(k=0;k<3;k++)
	                {
	        			sb.append(line);
	        	        sb.append(System.lineSeparator());
	        	        line = br.readLine();
	        	        Im_InvChat[i][j][k] = Integer.parseInt(line);
	                }
	            }
	        }
	        br.close();
	
	        i0 = 500;
	        j0 = 0;
	        idim = Math.min(450, iMax-500);
	        jdim = 300;
			captureRect = new Rectangle(j0, i0, jdim, idim);
			screenFiesta = robot.createScreenCapture(captureRect);
	        Im = convertTo2DUsingGetRGB(screenFiesta);
	        err = RechercheCoul(Im, idim, jdim, Im_InvChat, iidim_chat, jjdim_chat, 1);
	        if(err<buf_Err)
	        {
	        	buf_Err = err;
		        Type = 2;
		        Main.typeInteface = 2;
		        buf_imin = imin;
		        buf_jmin = jmin;
	        }
	        idimChat = 495 + buf_imin;
	        jdimChat = 28 + buf_jmin;
	        
	        
	// charge l'invariant de la carte
	        
	        if(Type==0)
	        {
	        	br = new BufferedReader(new FileReader("Config/Im_InvMap.txt"));
	        }
	        if(Type==1)
	        {
	        	br = new BufferedReader(new FileReader("Config/Im_InvMap_Hallo.txt"));
	        }
	        if(Type==2)
	        {
	        	br = new BufferedReader(new FileReader("Config/Im_InvMap_Noel.txt"));
	        }
	        sb = new StringBuilder();
	        line = "125";
	    	sb.append(line);
	        sb.append(System.lineSeparator());
	        line = br.readLine();
	        int iidim_carte = Integer.parseInt(line);
	        sb.append(line);
	        sb.append(System.lineSeparator());
	        line = br.readLine();
	        int jjdim_carte = Integer.parseInt(line);
	        int Im_InvCarte[][][] = new int[iidim_carte][jjdim_carte][3];
	        for(i=0;i<iidim_carte;i++)
	        {
	        	for(j=0;j<jjdim_carte;j++)
	            {
	        		for(k=0;k<3;k++)
	                {
	        			sb.append(line);
	        	        sb.append(System.lineSeparator());
	        	        line = br.readLine();
	        	        Im_InvCarte[i][j][k] = Integer.parseInt(line);
	                }
	            }
	        }
	        br.close();
	        
	// charge l'invariant de la selection
	        
	        if(Type==0)
	        {
	        	br = new BufferedReader(new FileReader("Config/Im_InvSelec.txt"));
	        }
	        if(Type==1)
	        {
	        	br = new BufferedReader(new FileReader("Config/Im_InvSelec_Hallo.txt"));
	        }
	        if(Type==2)
	        {
	        	br = new BufferedReader(new FileReader("Config/Im_InvSelec_Noel.txt"));
	        }
	        sb = new StringBuilder();
	        line = "125";
	    	sb.append(line);
	        sb.append(System.lineSeparator());
	        line = br.readLine();
	        int iidim_selec = Integer.parseInt(line);
	        sb.append(line);
	        sb.append(System.lineSeparator());
	        line = br.readLine();
	        int jjdim_selec = Integer.parseInt(line);
	        int Im_InvSelec[][][] = new int[iidim_selec][jjdim_selec][3];
	        for(i=0;i<iidim_selec;i++)
	        {
	        	for(j=0;j<jjdim_selec;j++)
	            {
	        		for(k=0;k<3;k++)
	                {
	        			sb.append(line);
	        	        sb.append(System.lineSeparator());
	        	        line = br.readLine();
	        	        Im_InvSelec[i][j][k] = Integer.parseInt(line);
	                }
	            }
	        }
	        br.close();
	        
	        // charge l'invariant de la vie
	        
	        if(Type==0)
	        {
	        	br = new BufferedReader(new FileReader("Config/Im_InvVie.txt"));
	        }
	        if(Type==1)
	        {
	        	br = new BufferedReader(new FileReader("Config/Im_InvVie_Hallo.txt"));
	        }
	        if(Type==2)
	        {
	        	br = new BufferedReader(new FileReader("Config/Im_InvVie_Noel.txt"));
	        }
	        sb = new StringBuilder();
	        line = "125";
	    	sb.append(line);
	        sb.append(System.lineSeparator());
	        line = br.readLine();
	        int iidim_Vie = Integer.parseInt(line);
	        sb.append(line);
	        sb.append(System.lineSeparator());
	        line = br.readLine();
	        int jjdim_Vie = Integer.parseInt(line);
	        int Im_InvVie[][][] = new int[iidim_Vie][jjdim_Vie][3];
	        for(i=0;i<iidim_Vie;i++)
	        {
	        	for(j=0;j<jjdim_Vie;j++)
	            {
	        		for(k=0;k<3;k++)
	                {
	        			sb.append(line);
	        	        sb.append(System.lineSeparator());
	        	        line = br.readLine();
	        	        Im_InvVie[i][j][k] = Integer.parseInt(line);
	                }
	            }
	        }
	        
	
			Thread.sleep(50);
			robot.mouseMove(100,10);
	        Thread.sleep(50);
	        robot.mousePress(InputEvent.BUTTON1_MASK);
	        Thread.sleep(20);
	        robot.mouseRelease(InputEvent.BUTTON1_MASK);
	        Thread.sleep(80);
	        robot.mousePress(InputEvent.BUTTON1_MASK);
	        Thread.sleep(20);
	        robot.mouseRelease(InputEvent.BUTTON1_MASK);
	        Thread.sleep(50);
	        robot.keyPress(KeyEvent.VK_F1);
	        Thread.sleep(40);
	        robot.keyRelease(KeyEvent.VK_F1);
	        Thread.sleep(50);
	        robot.keyPress(KeyEvent.VK_F1);
	        Thread.sleep(40);
	        robot.keyRelease(KeyEvent.VK_F1);
	        Thread.sleep(200);
	        
	        // recalage carte
	        
	        i0 = 20;
	        j0 = 800;
	        idim = 300;
	        jdim = Math.min(700, jMax-800);
			captureRect = new Rectangle(j0, i0, jdim, idim);
			screenFiesta = robot.createScreenCapture(captureRect);
	        Im = convertTo2DUsingGetRGB(screenFiesta);
	        RechercheCoul(Im, idim, jdim, Im_InvCarte, iidim_carte, jjdim_carte, 1);
	        idimCarte = 19 + imin;
	        jdimCarte = 830 + jmin;
	        
	        
	        
	    	// recalage vie
	        
	        i0 = 0;
	        j0 = 0;
	        idim = 100;
	        jdim = 250;
			captureRect = new Rectangle(j0, i0, jdim, idim);
			screenFiesta = robot.createScreenCapture(captureRect);
	        Im = convertTo2DUsingGetRGB(screenFiesta);
	        err = RechercheCoul(Im, idim, jdim, Im_InvVie, iidim_Vie, jjdim_Vie, 1);
	        if(err<50000) 
	        {
		        idimVie = 23 + imin;
		        jdimVie = 174 + jmin;
	        }
	        else
	        {
	        	br = new BufferedReader(new FileReader("Config/Im_InvVie_Grp.txt"));
	        
		        sb = new StringBuilder();
		        line = "125";
		    	sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		        iidim_Vie = Integer.parseInt(line);
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		        jjdim_Vie = Integer.parseInt(line);
		        Im_InvVie = new int[iidim_Vie][jjdim_Vie][3];
		        for(i=0;i<iidim_Vie;i++)
		        {
		        	for(j=0;j<jjdim_Vie;j++)
		            {
		        		for(k=0;k<3;k++)
		                {
		        			sb.append(line);
		        	        sb.append(System.lineSeparator());
		        	        line = br.readLine();
		        	        Im_InvVie[i][j][k] = Integer.parseInt(line);
		                }
		            }
		        }
	        	err = RechercheCoul(Im, idim, jdim, Im_InvVie, iidim_Vie, jjdim_Vie, 1);
		        idimVie = 23 + imin;
		        jdimVie = 174 + jmin;
		        if(err>50000) 
		        {
		        	idimVie = 23;
			        jdimVie = 174;
		        }
	        }
	        
	        // recalage selection
	        
	        
	        i0 = 30;
	        j0 = 400;
	        idim = 50;
	        jdim = Math.min(700, jMax-400);
			captureRect = new Rectangle(j0, i0, jdim, idim);
			screenFiesta = robot.createScreenCapture(captureRect);
	        Im = convertTo2DUsingGetRGB(screenFiesta);
	        RechercheCoul(Im, idim, jdim, Im_InvSelec, iidim_selec, jjdim_selec, 1);
	        idimSelec = 9 + imin;
	        jdimSelec = 371 + jmin;
	        
	        //System.out.println("vie : " + idimVie + " " + jdimVie);
	        //System.out.println("Chat : " + idimChat + " " + jdimChat);
	        //System.out.println("Map : " + idimCarte + " " + jdimCarte);
	        //System.out.println("Selec : " + idimSelec + " " + jdimSelec);
		}
		else 
		{
			// reference Fiesta 1280*768 sur écran 1600*900
			idimVie = 64;
			jdimVie = 190;
			idimChat = 675;
			jdimChat = 38;
			idimCarte = 58;
			jdimCarte = 1114;
			idimSelec = 30;
			jdimSelec = 597;
		}
        
	}
	
	
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
	
	
	private static int RechercheCoul(int[][][] Im, int idim, int jdim, int[][][] Im_buf, int iidim, int jjdim, int th)
	 {
		 int i;
		 int j;
		 int ii;
		 int jj;
		 int Err = 0;
		 int min = 150*150*3*iidim*jjdim;
		 
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
				 if(min>Err)
				 {
					 min = Err;
					 imin = i;
					 jmin = j;
				 }
			 }
				 
		 }
		 return min;
	 } // fin rechercheCoul
	

}

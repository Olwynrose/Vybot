import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UruMap {

	static int imin = 0;
	static int jmin = 0;
	Robot bip;
	int cartei;
	int cartej;
	int dir;
	
	public UruMap(int[][][] Im_map, int iidim, int jjdim) throws AWTException, IOException, InterruptedException
	{
		bip = new Robot();
		double a;					// variable aléatoire
		int i0 = Main.references.idimCarte;
		int j0 = Main.references.jdimCarte;
		//int i0 = 60;			// position map
		//int j0 = 1115;			// position map
		int idim = 160;			// dimension map
        int jdim = 160;			// dimension map
        int refi = 50+2;			// position centrale uruga
        int refj = 66;			// position centrale uruga
        int refEsci = 42+2;		// entrée escaliers
        int refEscj = 63;		// entrée escaliers
        int refPVPi = 63+2;		// entrée zone portail PVP
        int refPVPj = 100;		// entrée zone portail PVP
        int refGuei = 44+2;		// entrée escaliers guérisseuse
        int refGuej = 70;		// entrée escaliers guérisseuse
        int refCeni = 56+2;		// zone centrale
        int refCenj = 66;		// zone centrale
        
        int[][][] Im;			// image extrait écran fiesta
        int dist;				// distance pour la recherche de l'orientation
        int min;				// buffer pour la recherche de l'orientation
        dir = 1;				// direction
        
        Robot robot = new Robot();

        Rectangle captureRect = new Rectangle(j0, i0, jdim, idim);
        BufferedImage screenFiesta = robot.createScreenCapture(captureRect);
        Im = convertTo2DUsingGetRGB(screenFiesta);
   
        RechercheCoul(Im, idim, jdim, Im_map, iidim, jjdim, 1);
        //System.out.println(imin);
        //System.out.println(jmin);
        min = 100000;
        
        // sud
        dist = (imin - refi - 3)*(imin - refi - 3) + (jmin - refj)*(jmin - refj);
        if(min>dist)
        {
        	min = dist;
        	dir = 1;
        }
        
        // sud-est
        dist = (imin - refi - 2)*(imin - refi - 2) + (jmin - refj - 2)*(jmin - refj - 2);
        if(min>dist)
        {
        	min = dist;
        	dir = 2;
        }
        
        // est
        dist = (imin - refi)*(imin - refi) + (jmin - refj - 3)*(jmin - refj - 3);
        if(min>dist)
        {
        	min = dist;
        	dir = 3;
        }
        
        // nord-est
        dist = (imin - refi + 2)*(imin - refi + 2) + (jmin - refj - 2)*(jmin - refj - 2);
        if(min>dist)
        {
        	min = dist;
        	dir = 4;
        }
        
        // nord
        dist = (imin - refi + 3)*(imin - refi + 3) + (jmin - refj)*(jmin - refj);
        if(min>dist)
        {
        	min = dist;
        	dir = 5;
        }
        
        // nord-ouest
        dist = (imin - refi + 2)*(imin - refi + 2) + (jmin - refj + 2)*(jmin - refj + 2);
        if(min>dist)
        {
        	min = dist;
        	dir = 6;
        }
        
        // ouest
        dist = (imin - refi)*(imin - refi) + (jmin - refj + 3)*(jmin - refj + 3);
        if(min>dist)
        {
        	min = dist;
        	dir = 7;
        }
        
        // sud-ouest
        dist = (imin - refi - 2)*(imin - refi - 2) + (jmin - refj + 2)*(jmin - refj + 2);
        if(min>dist)
        {
        	min = dist;
        	dir = 8;
        }
        
        // escaliers (nord)
        dist = (imin - (2*refEsci-(refi-2)) )*(imin - (2*refEsci-(refi-2))) + (jmin - (2*refEscj-(refj-2)))*(jmin - (2*refEscj-(refj-2)));
        if(min>dist)
        {
        	min = dist;
        	dir = 9;
        }
        
        // centre (sud)
        dist = (imin - (2*refCeni-(refi+3)) )*(imin - (2*refCeni-(refi+3))) + (jmin - (2*refCenj-(refj)))*(jmin - (2*refCenj-(refj)));
        if(min>dist)
        {
        	min = dist;
        	dir = 10;
        }
        if(imin>refCeni)
        {
        	min = 0;
        	dir = 10;
        }
        
        // portail et PVP (est)
        dist = (imin - (2*refPVPi-(refi)) )*(imin - (2*refPVPi-(refi))) + (jmin - (2*refPVPj-(refj+3)))*(jmin - (2*refPVPj-(refj+3)));
        if(min>dist)
        {
        	min = dist;
        	dir = 11;
        }
        
        // escalier guérisseuse (nord-est)
        dist = (imin - (2*refGuei-(refi)) )*(imin - (2*refGuei-(refi))) + (jmin - (2*refGuej-(refj+3)))*(jmin - (2*refGuej-(refj+3)));
        if(min>dist)
        {
        	min = dist;
        	dir = 12;
        }
        
        a = Math.random();
        if(a<0.75 && dir<9)
        {
        	a = Math.random();
			if(a<0.125)
			{
				dir = 1;
			}
			if(a>=0.125 && a<0.25)
			{
				dir = 2;
			}
			if(a>=0.25 && a<0.375)
			{
				dir = 3;
			}
			if(a>=0.375 && a<0.5)
			{
				dir = 4;
			}
			if(a>=0.5 && a<0.625)
			{
				dir = 5;
			}
			if(a>=0.625 && a<0.75)
			{
				dir = 6;
			}
			if(a>=0.75 && a<0.825)
			{
				dir = 7;
			}
			if(a>=0.825 && a<1)
			{
				dir = 8;
			}
        }
     
        
        
         cartei = imin;
         cartej = jmin;
	}// fin constructeur
	
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
	 private static int RechercheCoul(int[][][] Im, int idim, int jdim, int[][][] Im_buf, int iidim, int jjdim, int th) throws IOException, InterruptedException
	 {
		 int Present = 0;
		 int i;
		 int j;
		 int ii;
		 int jj;
		 int Err;
		 int min = 150*150*3*18*18;
		 
		 
		 /*BufferedWriter writer = new BufferedWriter(new FileWriter("Config/tmp.txt"));
		writer.write(Integer.toString(idim-iidim));
		   writer.newLine();
		   writer.flush();
		   writer.write(Integer.toString(jdim-jjdim));
		writer.newLine();
		writer.flush();*/
	        
		 th = th*iidim*jjdim*3;
		 for(i=0;i<idim-iidim;i++)
		 {
			 for(j=0;j<jdim-jjdim;j++)
			 {
				 Err = 0;
				 for(ii=0;ii<3*iidim/4;ii++)
				 {
					 for(jj=0;jj<jjdim;jj++)
					 {
						 Err = Err + (Im[i+ii][j+jj][1]-Im_buf[ii][jj][1])*(Im[i+ii][j+jj][1]-Im_buf[ii][jj][1]);
						 Err = Err + (Im[i+ii][j+jj][2]-Im_buf[ii][jj][2])*(Im[i+ii][j+jj][2]-Im_buf[ii][jj][2]);
						 Err = Err + (Im[i+ii][j+jj][0]-Im_buf[ii][jj][0])*(Im[i+ii][j+jj][0]-Im_buf[ii][jj][0]);
					 }
				 } 
				 /*writer.write(Integer.toString(Err));
       	         writer.newLine();
       	         writer.flush();*/
       	         
				 if(min>Err) //(Err<th)
				 {
					 min = Err;
					 imin = i;
					 jmin = j;
					 Present = Present + 1;
				 }
			 }
				 
		 }
		 
		 /*writer.close();
	     System.out.println("ok");
	     Thread.sleep(17000);*/
		 //System.out.println(min);
		 return Present;
	 } // fin rechercheCoul
	 
	
} // fin classe

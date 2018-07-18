import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DetectWindow {
	
	public DetectWindow() throws AWTException, IOException, InterruptedException
	{
		Robot robot = new Robot();
		int i,j;
		int detect = 1;
		int screeni0 = 150;		// position screen
		int screenj0 = 100;		// position screen
		int screenidim = 500;	// dimension screen
		int screenjdim = 1100;	// dimension screen
		float th_detect = (float) 23000; // seuil de détection

		float th_window = (float) 20;
		int[][][] Im;			// image extrait écran fiesta
        
        i = 0;
        j = 2;
        while(detect>0)
        {
        	Rectangle captureRect = new Rectangle(screenj0, screeni0, screenjdim, screenidim);
    		BufferedImage screenFiesta = robot.createScreenCapture(captureRect);
            Im = convertTo2DUsingGetRGB(screenFiesta);
        	detect = DetectInterface(Im, screenidim, screenjdim, th_window, th_detect);
        	detect = detect*(j-i);
        	if(detect>0)
        	{
        		robot.keyPress(KeyEvent.VK_ESCAPE);
        		Thread.sleep(50);
			    robot.keyRelease(KeyEvent.VK_ESCAPE);
			    Thread.sleep(250);
        	}
        	i = i + 1;
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

	private static int DetectInterface(int[][][] Im, int idim, int jdim, float th, float th_detect) throws IOException, InterruptedException
	 {
		 float[][][] Im_float = new float[idim-3][jdim-3][3];
		 float[][] Gauss = new float[3][3];
		 //float[][] Im_text = new float[idim-3][jdim-3];
		 float[] Coul = new float[3];
		 int detect = 0;
		 float sum = 0;
		 //float Fact = (float) 0.8;
		 float dist;
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
				 //Im_text[i][j] = (float) 0;
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
		 

		 // bleu marine (interface)
		 Coul[0] = (float) 31;
		 Coul[1] = (float) 31;
		 Coul[2] = (float) 65;
		 
		 for(i=0;i<idim-3;i++)
		 {
			 for(j=0;j<jdim-3;j++)
			 {
				dist = (Im_float[i][j][1] - Coul[1])*(Im_float[i][j][1] - Coul[1]);
				dist = dist + (Im_float[i][j][2] - Coul[2])*(Im_float[i][j][2] - Coul[2]);
				dist = dist + (Im_float[i][j][0] - Coul[0])*(Im_float[i][j][0] - Coul[0]);
				//Im_text[i][j] = Im_text[i][j] + (float) Math.exp((double) -dist/(th*th*3));
				sum = sum + (float) Math.exp((double) -dist/(th*th*3));
			 }
		 }
		 
		 if(sum>th_detect)
		 {
			 detect = 1;
		 }
		 else
		 {
			 detect = 0;
		 }
		 //System.out.println(sum);
		 //	Thread.sleep(2000);
		 	
		 return detect;
	 } // fin ImText
}

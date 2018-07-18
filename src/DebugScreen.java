import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DebugScreen {

	public DebugScreen(int i0, int j0, int idim, int jdim) throws AWTException, IOException, InterruptedException
	{
		int i,j,k;
		int[][][] Im;
		
		Robot robot = new Robot();
        
        Rectangle captureRect = new Rectangle(j0, i0, jdim, idim);
        BufferedImage screenFiesta = robot.createScreenCapture(captureRect);
        Im = convertTo2DUsingGetRGB(screenFiesta);
		
		BufferedWriter writer = new BufferedWriter(new FileWriter("Config/tmp.txt"));
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
       		 for(k=0;k<3;k++)
       		 {
			 		  writer.write(Integer.toString(Im[i][j][k]));
       	          writer.newLine();
       	          writer.flush();
       		 }
   		 }
		 }
        writer.close();
        System.out.println("ok");
        Thread.sleep(17000);
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
}


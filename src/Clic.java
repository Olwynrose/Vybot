import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class Clic {
	
	int maxAct;					// nombre maximum d actions par séquence 
	String[] sAct;
	String[] sTemps;
	int[] tempo;
	
	int i, j, k;
	int i0, j0;
	int is_act1, is_act2, is_act3;	// sequence d action activée ou non
	int n_act;
	int keyCode;
	Condition condi;
	UruMap uruMap;
	
	JButton bStop;
	
	public Clic() throws FileNotFoundException, IOException, AWTException, InterruptedException
	{
		
		
		StringSelection selec;
		Clipboard clipboard;
		Robot robot = new Robot();
		String str_nom;
		maxAct = Main.donnees.maxSeq;
		sAct = new String[maxAct];
		sTemps = new String[maxAct];
		tempo = new int[maxAct];
		Main.cond = 0;
		int l_act = 0;
		keyCode = 0;
		
		// marche
		double a = 0.0;	// hazard 1
		double b = 0.0;	// hazard 2
		int it = 0;			
		int dir = 0;	// direction
		int time = 0;	// temps de marche (0:choisis une nouvelle direction, >0:marche)
		int d1 = 0;		// mémoire pour la position initiale
		int d2 = 0;		// mémoire pour la position initiale
		int d3 = 0;		// mémoire pour la position initiale
		int d4 = 0;		// mémoire pour la position initiale
		
		i0 = 0;
		j0 = 0;
		
		Main.pan.removeAll();
		
		bStop = new JButton(Main.iBot);
		bStop.setVerticalTextPosition(SwingConstants.CENTER);
		bStop.setHorizontalTextPosition(SwingConstants.CENTER);
		bStop.setBounds(0, 0, 130, 100);
		bStop.addActionListener(new AStop());
		Main.pan.add(bStop);
		
		Main.donnees = new Donnees();



// charge le texte de référence 1
        
        BufferedReader br = new BufferedReader(new FileReader("Config/LearnedText1.txt"));
        StringBuilder sb = new StringBuilder();
        String line = "125";
    	sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
        Main.iidim1 = Integer.parseInt(line);
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
        Main.jjdim1 = Integer.parseInt(line);
        Main.Im_Text_1 = new int[Main.iidim1][Main.jjdim1][3];
        for(i=0;i<Main.iidim1;i++)
        {
        	for(j=0;j<Main.jjdim1;j++)
            {
        		for(k=0;k<3;k++)
                {
        			sb.append(line);
        	        sb.append(System.lineSeparator());
        	        line = br.readLine();
        			Main.Im_Text_1[i][j][k] = Integer.parseInt(line);
                }
            }
        }
        br.close();
// charge le texte de référence 2
        
        br = new BufferedReader(new FileReader("Config/LearnedText2.txt"));
        sb = new StringBuilder();
        line = "125";
    	sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
        Main.iidim2 = Integer.parseInt(line);
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
        Main.jjdim2 = Integer.parseInt(line);
        Main.Im_Text_2 = new int[Main.iidim2][Main.jjdim2][3];
        for(i=0;i<Main.iidim2;i++)
        {
        	for(j=0;j<Main.jjdim2;j++)
            {
        		for(k=0;k<3;k++)
                {
        			sb.append(line);
        	        sb.append(System.lineSeparator());
        	        line = br.readLine();
        			Main.Im_Text_2[i][j][k] = Integer.parseInt(line);
                }
            }
        }
        br.close();
// charge le texte de référence 3
        
        br = new BufferedReader(new FileReader("Config/LearnedText3.txt"));
        sb = new StringBuilder();
        line = "125";
    	sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
        Main.iidim3 = Integer.parseInt(line);
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
        Main.jjdim3 = Integer.parseInt(line);
        Main.Im_Text_3 = new int[Main.iidim3][Main.jjdim3][3];
        for(i=0;i<Main.iidim3;i++)
        {
        	for(j=0;j<Main.jjdim3;j++)
            {
        		for(k=0;k<3;k++)
                {
        			sb.append(line);
        	        sb.append(System.lineSeparator());
        	        line = br.readLine();
        			Main.Im_Text_3[i][j][k] = Integer.parseInt(line);
                }
            }
        }
        br.close();
        
// charge le texte de référence 4
        
        br = new BufferedReader(new FileReader("Config/LearnedText4.txt"));
        sb = new StringBuilder();
        line = "125";
    	sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
        Main.iidim4 = Integer.parseInt(line);
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
        Main.jjdim4 = Integer.parseInt(line);
        Main.Im_Text_4 = new int[Main.iidim4][Main.jjdim4][3];
        for(i=0;i<Main.iidim4;i++)
        {
        	for(j=0;j<Main.jjdim4;j++)
            {
        		for(k=0;k<3;k++)
                {
        			sb.append(line);
        	        sb.append(System.lineSeparator());
        	        line = br.readLine();
        			Main.Im_Text_4[i][j][k] = Integer.parseInt(line);
                }
            }
        }
        br.close();
        
// charge le texte de référence 5
        
        br = new BufferedReader(new FileReader("Config/LearnedText5.txt"));
        sb = new StringBuilder();
        line = "125";
    	sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
        Main.iidim5 = Integer.parseInt(line);
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
        Main.jjdim5 = Integer.parseInt(line);
        Main.Im_Text_5 = new int[Main.iidim5][Main.jjdim5][3];
        for(i=0;i<Main.iidim5;i++)
        {
        	for(j=0;j<Main.jjdim5;j++)
            {
        		for(k=0;k<3;k++)
                {
        			sb.append(line);
        	        sb.append(System.lineSeparator());
        	        line = br.readLine();
        			Main.Im_Text_5[i][j][k] = Integer.parseInt(line);
                }
            }
        }
        br.close();

// charge le texte de référence 6
        
        br = new BufferedReader(new FileReader("Config/LearnedText6.txt"));
        sb = new StringBuilder();
        line = "125";
    	sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
        Main.iidim6 = Integer.parseInt(line);
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
        Main.jjdim6 = Integer.parseInt(line);
        Main.Im_Text_6 = new int[Main.iidim6][Main.jjdim6][3];
        for(i=0;i<Main.iidim6;i++)
        {
        	for(j=0;j<Main.jjdim6;j++)
            {
        		for(k=0;k<3;k++)
                {
        			sb.append(line);
        	        sb.append(System.lineSeparator());
        	        line = br.readLine();
        			Main.Im_Text_6[i][j][k] = Integer.parseInt(line);
                }
            }
        }
        br.close();
        
// charge le texte de référence 7
        
        br = new BufferedReader(new FileReader("Config/LearnedText7.txt"));
        sb = new StringBuilder();
        line = "125";
    	sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
        Main.iidim7 = Integer.parseInt(line);
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
        Main.jjdim7 = Integer.parseInt(line);
        Main.Im_Text_7 = new int[Main.iidim7][Main.jjdim7][3];
        for(i=0;i<Main.iidim7;i++)
        {
        	for(j=0;j<Main.jjdim7;j++)
            {
        		for(k=0;k<3;k++)
                {
        			sb.append(line);
        	        sb.append(System.lineSeparator());
        	        line = br.readLine();
        			Main.Im_Text_7[i][j][k] = Integer.parseInt(line);
                }
            }
        }
        br.close();
        
// charge le texte de référence 8
        
        br = new BufferedReader(new FileReader("Config/LearnedText8.txt"));
        sb = new StringBuilder();
        line = "125";
    	sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
        Main.iidim8 = Integer.parseInt(line);
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
        Main.jjdim8 = Integer.parseInt(line);
        Main.Im_Text_8 = new int[Main.iidim8][Main.jjdim8][3];
        for(i=0;i<Main.iidim8;i++)
        {
        	for(j=0;j<Main.jjdim8;j++)
            {
        		for(k=0;k<3;k++)
                {
        			sb.append(line);
        	        sb.append(System.lineSeparator());
        	        line = br.readLine();
        			Main.Im_Text_8[i][j][k] = Integer.parseInt(line);
                }
            }
        }
        br.close();
        
// charge le texte de référence 9
        
        br = new BufferedReader(new FileReader("Config/LearnedText9.txt"));
        sb = new StringBuilder();
        line = "125";
    	sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
        Main.iidim9 = Integer.parseInt(line);
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
        Main.jjdim9 = Integer.parseInt(line);
        Main.Im_Text_9 = new int[Main.iidim9][Main.jjdim9][3];
        for(i=0;i<Main.iidim9;i++)
        {
        	for(j=0;j<Main.jjdim9;j++)
            {
        		for(k=0;k<3;k++)
                {
        			sb.append(line);
        	        sb.append(System.lineSeparator());
        	        line = br.readLine();
        			Main.Im_Text_9[i][j][k] = Integer.parseInt(line);
                }
            }
        }
        br.close();
        
// charge le texte de référence 10
        
        br = new BufferedReader(new FileReader("Config/LearnedText10.txt"));
        sb = new StringBuilder();
        line = "125";
    	sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
        Main.iidim10 = Integer.parseInt(line);
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
        Main.jjdim10 = Integer.parseInt(line);
        Main.Im_Text_10 = new int[Main.iidim10][Main.jjdim10][3];
        for(i=0;i<Main.iidim10;i++)
        {
        	for(j=0;j<Main.jjdim10;j++)
            {
        		for(k=0;k<3;k++)
                {
        			sb.append(line);
        	        sb.append(System.lineSeparator());
        	        line = br.readLine();
        			Main.Im_Text_10[i][j][k] = Integer.parseInt(line);
                }
            }
        }
        br.close();
        
// charge le texte de référence 11
        
        br = new BufferedReader(new FileReader("Config/LearnedText11.txt"));
        sb = new StringBuilder();
        line = "125";
    	sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
        Main.iidim11 = Integer.parseInt(line);
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
        Main.jjdim11 = Integer.parseInt(line);
        Main.Im_Text_11 = new int[Main.iidim11][Main.jjdim11][3];
        for(i=0;i<Main.iidim11;i++)
        {
        	for(j=0;j<Main.jjdim11;j++)
            {
        		for(k=0;k<3;k++)
                {
        			sb.append(line);
        	        sb.append(System.lineSeparator());
        	        line = br.readLine();
        			Main.Im_Text_11[i][j][k] = Integer.parseInt(line);
                }
            }
        }
        br.close();
        
// charge le texte de référence 12
        
        br = new BufferedReader(new FileReader("Config/LearnedText12.txt"));
        sb = new StringBuilder();
        line = "125";
    	sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
        Main.iidim12 = Integer.parseInt(line);
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
        Main.jjdim12 = Integer.parseInt(line);
        Main.Im_Text_12 = new int[Main.iidim12][Main.jjdim12][3];
        for(i=0;i<Main.iidim12;i++)
        {
        	for(j=0;j<Main.jjdim12;j++)
            {
        		for(k=0;k<3;k++)
                {
        			sb.append(line);
        	        sb.append(System.lineSeparator());
        	        line = br.readLine();
        			Main.Im_Text_12[i][j][k] = Integer.parseInt(line);
                }
            }
        }
        br.close();
        
// charge le texte de référence 13
        
        br = new BufferedReader(new FileReader("Config/LearnedText13.txt"));
        sb = new StringBuilder();
        line = "125";
    	sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
        Main.iidim13 = Integer.parseInt(line);
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
        Main.jjdim13 = Integer.parseInt(line);
        Main.Im_Text_13 = new int[Main.iidim13][Main.jjdim13][3];
        for(i=0;i<Main.iidim13;i++)
        {
        	for(j=0;j<Main.jjdim13;j++)
            {
        		for(k=0;k<3;k++)
                {
        			sb.append(line);
        	        sb.append(System.lineSeparator());
        	        line = br.readLine();
        			Main.Im_Text_13[i][j][k] = Integer.parseInt(line);
                }
            }
        }
        br.close();
        
// charge le texte de référence 14
        
        br = new BufferedReader(new FileReader("Config/LearnedText14.txt"));
        sb = new StringBuilder();
        line = "125";
    	sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
        Main.iidim14 = Integer.parseInt(line);
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
        Main.jjdim14 = Integer.parseInt(line);
        Main.Im_Text_14 = new int[Main.iidim14][Main.jjdim14][3];
        for(i=0;i<Main.iidim14;i++)
        {
        	for(j=0;j<Main.jjdim14;j++)
            {
        		for(k=0;k<3;k++)
                {
        			sb.append(line);
        	        sb.append(System.lineSeparator());
        	        line = br.readLine();
        			Main.Im_Text_14[i][j][k] = Integer.parseInt(line);
                }
            }
        }
        br.close();
        
// charge le texte de référence 15
        
        br = new BufferedReader(new FileReader("Config/LearnedText15.txt"));
        sb = new StringBuilder();
        line = "125";
    	sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
        Main.iidim15 = Integer.parseInt(line);
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
        Main.jjdim15 = Integer.parseInt(line);
        Main.Im_Text_15 = new int[Main.iidim15][Main.jjdim15][3];
        for(i=0;i<Main.iidim15;i++)
        {
        	for(j=0;j<Main.jjdim15;j++)
            {
        		for(k=0;k<3;k++)
                {
        			sb.append(line);
        	        sb.append(System.lineSeparator());
        	        line = br.readLine();
        			Main.Im_Text_15[i][j][k] = Integer.parseInt(line);
                }
            }
        }
        br.close();
        
// charge le texte de référence 16
        
        br = new BufferedReader(new FileReader("Config/LearnedText16.txt"));
        sb = new StringBuilder();
        line = "125";
    	sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
        Main.iidim16 = Integer.parseInt(line);
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
        Main.jjdim16 = Integer.parseInt(line);
        Main.Im_Text_16 = new int[Main.iidim16][Main.jjdim16][3];
        for(i=0;i<Main.iidim16;i++)
        {
        	for(j=0;j<Main.jjdim16;j++)
            {
        		for(k=0;k<3;k++)
                {
        			sb.append(line);
        	        sb.append(System.lineSeparator());
        	        line = br.readLine();
        			Main.Im_Text_16[i][j][k] = Integer.parseInt(line);
                }
            }
        }
        br.close();
        
// charge le texte de référence 17
        
        br = new BufferedReader(new FileReader("Config/LearnedText17.txt"));
        sb = new StringBuilder();
        line = "125";
    	sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
        Main.iidim17 = Integer.parseInt(line);
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
        Main.jjdim17 = Integer.parseInt(line);
        Main.Im_Text_17 = new int[Main.iidim17][Main.jjdim17][3];
        for(i=0;i<Main.iidim17;i++)
        {
        	for(j=0;j<Main.jjdim17;j++)
            {
        		for(k=0;k<3;k++)
                {
        			sb.append(line);
        	        sb.append(System.lineSeparator());
        	        line = br.readLine();
        			Main.Im_Text_17[i][j][k] = Integer.parseInt(line);
                }
            }
        }
        br.close();
        
// charge le texte de référence 18
        
        br = new BufferedReader(new FileReader("Config/LearnedText18.txt"));
        sb = new StringBuilder();
        line = "125";
    	sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
        Main.iidim18 = Integer.parseInt(line);
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
        Main.jjdim18 = Integer.parseInt(line);
        Main.Im_Text_18 = new int[Main.iidim18][Main.jjdim18][3];
        for(i=0;i<Main.iidim18;i++)
        {
        	for(j=0;j<Main.jjdim18;j++)
            {
        		for(k=0;k<3;k++)
                {
        			sb.append(line);
        	        sb.append(System.lineSeparator());
        	        line = br.readLine();
        			Main.Im_Text_18[i][j][k] = Integer.parseInt(line);
                }
            }
        }
        br.close();
 // charge le repère sur la carte Uruga
        
        br = new BufferedReader(new FileReader("Config/Im_map_uru.txt"));
        sb = new StringBuilder();
        line = "125";
    	sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
        Main.iidim_uru = Integer.parseInt(line);
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
        Main.jjdim_uru = Integer.parseInt(line);
        Main.Im_map_uru = new int[Main.iidim_uru][Main.jjdim_uru][3];
        for(i=0;i<Main.iidim_uru;i++)
        {
        	for(j=0;j<Main.jjdim_uru;j++)
            {
        		for(k=0;k<3;k++)
                {
        			sb.append(line);
        	        sb.append(System.lineSeparator());
        	        line = br.readLine();
        	        Main.Im_map_uru[i][j][k] = Integer.parseInt(line);
                }
            }
        }
        br.close();
		for(i=0;i<Main.donnees.lSeq[Main.seq];i++)
		{
			sAct[i] = Main.donnees.aSeq[Main.seq][i].action;
			//System.out.println(sAct[i]);
			tempo[i] = Main.donnees.aSeq[Main.seq][i].tps;
		}
		n_act = Main.donnees.lSeq[Main.seq];
		is_act1 = Main.donnees.is_act[Main.seq][0];
		is_act2 = Main.donnees.is_act[Main.seq][1];
		is_act3 = Main.donnees.is_act[Main.seq][2];

		
		Main.pan.repaint();
		
		
		
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
		
		Main.it_rand = 0;
		
		while(Main.cond == 0)
		{
			//  ======================= séquence =========================
			for(i=0;i<Main.donnees.lSeq[Main.seq];i++)
			{
				l_act = Math.max(tempo[i]/250,1);
				
				if(sAct[i].charAt(0)=='T')
				{
					keyCode = Main.donnees.aSeq[Main.seq][i].keyCode;
					// presse la touche
					if(keyCode>2000)
					{
						robot.keyPress(KeyEvent.VK_CONTROL);
						Thread.sleep(60);
						robot.keyPress(keyCode-2000);
						Thread.sleep(50);
					}
					else
					{
						if(keyCode>1000)
						{
							robot.keyPress(16);
							Thread.sleep(60);
							robot.keyPress(keyCode-1000);
							Thread.sleep(50);
						}
						else
						{
							Thread.sleep(50);
							robot.keyPress(keyCode);
							Thread.sleep(50);
						}
					}
					// attend(touche pressée) + vérifie les conditions
					for(j=j0;j<l_act;j++)
					{
						if(Main.donnees.secret==1)
						{
							Thread.sleep(250);
						}
						else
						{
							Thread.sleep(220+(int)(60*Math.random()) );
						}
						condi = new Condition();
						// si une condition est vrifiée ou le bouton stop pressé
						if(condi.detection==1 || Main.cond>0)
						{
							// sauvegarde la position dans la séquence et sort de l'attente
							i0 = i-1;
							j0 = j;
							j = l_act;
						}
					}
					// relache la touche
					if(keyCode>2000)
					{
						robot.keyRelease(keyCode-2000);
						Thread.sleep(60);
						robot.keyRelease(KeyEvent.VK_CONTROL);
					}
					else
					{
						if(keyCode>1000)
						{
							robot.keyRelease(keyCode-1000);
							Thread.sleep(60);
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
					str_nom = (String.valueOf(Main.donnees.aSeq[Main.seq][i].dialogue[0]));
					for(k=1;k<Main.donnees.aSeq[Main.seq][i].l_text;k++)
					{
						str_nom = new StringBuilder().append(str_nom).append(String.valueOf(Main.donnees.aSeq[Main.seq][i].dialogue[k])).toString();
					}
					// copie le dialogue dans le presse papier et le colle dans le chat fiesta
					selec = new StringSelection(str_nom);
			        clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(selec, selec);
					Thread.sleep(40);
					robot.keyPress(KeyEvent.VK_ENTER);
			        Thread.sleep(50);
			        robot.keyRelease(KeyEvent.VK_ENTER);
			        Thread.sleep(50);
			        robot.keyPress(KeyEvent.VK_CONTROL);
				    Thread.sleep(50);
				    robot.keyPress(KeyEvent.VK_V);
				    Thread.sleep(50);
				    robot.keyRelease(KeyEvent.VK_V);
				    Thread.sleep(50);
				    robot.keyRelease(KeyEvent.VK_CONTROL);
				    Thread.sleep(100);
				    robot.keyPress(KeyEvent.VK_ENTER);
			        Thread.sleep(50);
			        robot.keyRelease(KeyEvent.VK_ENTER);
			        Thread.sleep(500);
			        
			        condi = new Condition();
			        // si une condition est vrifiée
			        if(condi.detection==1)
					{
			        	i0 = i;
						j = l_act;
					}
				} // fin du dialogue
				
				if(sAct[i].charAt(0)=='C')
				{
					// clic souris
					for(j=0;j<l_act;j++)
					{
						Thread.sleep(50);
						robot.mouseMove(Main.donnees.aSeq[Main.seq][i].clic_j + Main.references.jdimVie, Main.donnees.aSeq[Main.seq][i].clic_i + Main.references.idimVie);
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
						condi = new Condition();
						// si une condition est vrifiée ou le bouton stop pressé
						if(condi.detection==1 || Main.cond>0)
						{
							i0 = i-1;
							j0 = j;
							j = l_act;
						}
					}
				} // fin de clic
				
				if(sAct[i].charAt(0)=='A')
				{
					// attend...
					for(j=0;j<l_act;j++)
					{
						if(Main.donnees.secret==1)
						{
							Thread.sleep(250);
						}
						else
						{
							Thread.sleep(220+(int)(60*Math.random()) );
						}
						condi = new Condition();
						// si une condition est vrifiée ou le bouton stop pressé
						if(condi.detection==1 || Main.cond>0)
						{
							i0 = i-1;
							j0 = j;
							j = l_act;
						}
					}
				} // fin de attend

				if(sAct[i].charAt(0)=='M')
				{
					j = j0;
					while(j<l_act)
					{
						if(time<=0)
						{
							if(sAct[i].length()<8)
							{
								// marche pseudo centrée sur le point de départ
								if(Main.donnees.secret==1)
								{
									time = 9;
								}
								else
								{
									time = 6 + (int)(7*Math.random());
								}
								a = Math.random();
								b = Math.random();
								
								if(b>0.5)
								{
									// pas aléatoire sur les 8 directions
									if(a<0.125)
									{
										dir = 1;
										d1 = d1+1;
									}
									if(a>=0.125 && a<0.25)
									{
										dir = 2;
										d2 = d2+1;
									}
									if(a>=0.25 && a<0.375)
									{
										dir = 3;
										d3 = d3+1;
									}
									if(a>=0.375 && a<0.5)
									{
										dir = 4;
										d4 = d4+1;
									}
									if(a>=0.5 && a<0.625)
									{
										dir = 5;
										d1 = d1-1;
									}
									if(a>=0.625 && a<0.75)
									{
										dir = 6;
										d2 = d2-1;
									}
									if(a>=0.75 && a<0.825)
									{
										dir = 7;
										d3 = d3-1;
									}
									if(a>=0.825 && a<1)
									{
										dir = 8;
										d4 = d4-1;
									}
								}
								else // si b>0.5
								{
									// pas en direction de l'origine (aléatoirement sur une des 4 directions non orientées)
									if(a<0.25)
									{
										if(d1>0)
										{
											dir = 5;
											d1 = d1-1;
										}
										else
										{
											dir = 1;
											d1 = d1+1;
										}
									}
									if(a>=0.25 && a<0.5)
									{
										if(d2>0)
										{
											dir = 6;
											d2 = d2-1;
										}
										else
										{
											dir = 2;
											d2 = d2+1;
										}
									}
									if(a>=0.5 && a<0.75)
									{
										if(d3>0)
										{
											dir = 7;
											d3 = d3-1;
										}
										else
										{
											dir = 3;
											d3 = d3+1;
										}
									}
									if(a>=0.75)
									{
										if(d4>0)
										{
											dir = 8;
											d4 = d4-1;
										}
										else
										{
											dir = 4;
											d4 = d4+1;
										}
									}
								} // fin du choix de la direction
							}	// fin de marche pseudo centrée sur le point de départ
							else 
							{	
								// marche à uruga (aléatoire cenrée sur la place limité dans la zone centrale)
								b = Math.random();
								if(b>0.99 && it == 0)
								{
									Thread.sleep(50);
									robot.keyPress(Main.donnees.marche[4]);
									//System.out.println("marche");
									Thread.sleep(100);
									robot.keyRelease(Main.donnees.marche[4]);
									//System.out.println("marche");
									it = 1;
								}
								uruMap = new UruMap(Main.Im_map_uru, Main.iidim_uru, Main.jjdim_uru);
								dir = uruMap.dir;
								if(dir==10)
								{
									// si le joueur s'approche de la statue centrale il s'en éloigne sur 2 pas 
									if(Main.donnees.secret==1)
									{
										time = 19;
									}
									else
									{
										time = 14 + (int)(7*Math.random());
									}
								}
								else
								{
									if(Main.donnees.secret==1)
									{
										time = 9;
									}
									else
									{
										time = 6 + (int)(7*Math.random());
									}
								}
							}
						}// fin de time
						
	// ================ directions =======================================
						
						// la direction a été déterminée précédement
						// le bot marche dans la direction choisie
						if(dir==1) // sud
						{
							Thread.sleep(50);
							robot.keyPress(Main.donnees.marche[3]);
							//System.out.println("gauche");
						}
				        if(dir==10) // sud
						{
							Thread.sleep(50);
							robot.keyPress(Main.donnees.marche[3]);
							//System.out.println("gauche");
						}
						if(dir==7) // ouest
						{
							Thread.sleep(50);
							robot.keyPress(Main.donnees.marche[0]);
							//System.out.println("haut");
						}
						if(dir==5 || dir==9) // nord
						{
							Thread.sleep(50);
							robot.keyPress(Main.donnees.marche[2]);
							//System.out.println("droite");
						}
						if(dir==3 || dir==11) // est
						{
							Thread.sleep(50);
							robot.keyPress(Main.donnees.marche[1]);
							//System.out.println("bas");
						}
						if(dir==8) // sud-ouest
						{
							Thread.sleep(50);
							robot.keyPress(Main.donnees.marche[3]);
							//System.out.println("gauche");
							robot.keyPress(Main.donnees.marche[0]);
							//System.out.println("haut");
						}
						if(dir==6) // nord-ouest
						{
							Thread.sleep(50);
							robot.keyPress(Main.donnees.marche[2]);
							//System.out.println("droite");
							robot.keyPress(Main.donnees.marche[0]);
							//System.out.println("haut");
						}
						if(dir==4 || dir==12) // nord-est
						{
							Thread.sleep(50);
							robot.keyPress(Main.donnees.marche[2]);
							//System.out.println("droite");
							robot.keyPress(Main.donnees.marche[1]);
							//System.out.println("bas");
						}
						if(dir==2) // sud-est
						{
							Thread.sleep(50);
							robot.keyPress(Main.donnees.marche[3]);
							//System.out.println("gauche");
							robot.keyPress(Main.donnees.marche[1]);
							//System.out.println("bas");
						}
						
						Thread.sleep(250);
						time = time - 1;
						condi = new Condition();
						// si une condition est vrifiée ou le bouton stop pressé
						if(condi.detection==1 || Main.cond>0)
						{
							// sauvegarde la position dans la séquence et sort de l'attente
							i0 = i-1;
							j0 = j;
							j = l_act;
							// pour relacher la touche
							time = 0;
						}
						if(time<=0)
						{
							if(dir==1) // sud
							{
								Thread.sleep(50);
								robot.keyRelease(Main.donnees.marche[3]);
								//System.out.println("/gauche");
							}
					        if(dir==10) // sud
							{
								Thread.sleep(50);
								robot.keyRelease(Main.donnees.marche[3]);
								//System.out.println("/gauche");
							}
							if(dir==7) // ouest
							{
								Thread.sleep(50);
								robot.keyRelease(Main.donnees.marche[0]);
								//System.out.println("/haut");
							}
							if(dir==5 || dir==9) // nord
							{
								Thread.sleep(50);
								robot.keyRelease(Main.donnees.marche[2]);
								//System.out.println("/droite");
							}
							if(dir==3 || dir==11) // est
							{
								Thread.sleep(50);
								robot.keyRelease(Main.donnees.marche[1]);
								//System.out.println("/bas");
							}
							if(dir==8) // sud-ouest
							{
								Thread.sleep(50);
								robot.keyRelease(Main.donnees.marche[3]);
								//System.out.println("/gauche");
								robot.keyRelease(Main.donnees.marche[0]);
								//System.out.println("/haut");
							}
							if(dir==6) // nord-ouest
							{
								Thread.sleep(50);
								robot.keyRelease(Main.donnees.marche[2]);
								//System.out.println("/droite");
								robot.keyRelease(Main.donnees.marche[0]);
								//System.out.println("/haut");
							}
							if(dir==4 || dir==12) // nord-est
							{
								Thread.sleep(50);
								robot.keyRelease(Main.donnees.marche[2]);
								//System.out.println("/droite");
								robot.keyRelease(Main.donnees.marche[1]);
								//System.out.println("/bas");
							}
							if(dir==2) // sud-est
							{
								Thread.sleep(50);
								robot.keyRelease(Main.donnees.marche[3]);
								//System.out.println("/gauche");
								robot.keyRelease(Main.donnees.marche[1]);
								//System.out.println("/bas");
							}
							
							if(it == 4)
							{
								Thread.sleep(50);
								robot.keyPress(Main.donnees.marche[4]);
								//System.out.println("marche");
								Thread.sleep(100);
								robot.keyRelease(Main.donnees.marche[4]);
								//System.out.println("marche");
								it = 0;
							}
							if(it > 0)
							{
								it = it + 1;
							}
						}
	// ================ fin directions =======================================
						
						j = j+1;
					}
				} // fin de Marche
				
				// lance une action spéciale si une condition a été détectée
				if(condi.detection==1)
				{
					new ClicAction(condi);
					i = i0;
				}
				else
				{
					j0 = 0;
				}
				if(Main.cond==1)
				{
					i = 10000000;
				}
				
				
			} // fin de la séquence
			Thread.sleep(15);
		}
		

	} // fin du constructeur
	public class AStop implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			j = 100000000;
			Main.cond = 1;
			bStop.setEnabled(false);
			Main.onglet = 7;
		}
	}
}

import java.awt.AWTException;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.SwingConstants;


public class PanSelec 
{
	JButton bLearn;
	JButton bAct;
	JButton bEtalon;
	JButton bIA;
	JButton bOption;
	int cond;
	
	public PanSelec() throws InterruptedException, AWTException, IOException
	{
		cond = 0;
		Main.pan.removeAll();
		Main.pan.setLayout(null);
		
		// interface
		int h0 = 100;
		int h_bout = 50;
		int i_bout = 40;
		int l_bout = 200;
		
		bLearn = new JButton(Main.iBouton);
		bLearn.setBackground(Color.BLUE);
		if(Main.donnees.langue==0)
		{
			bLearn.setText("Mes séquences");
		}
		else
		{
			bLearn.setText("My sequences");
		}
        bLearn.setVerticalTextPosition(SwingConstants.CENTER);
        bLearn.setHorizontalTextPosition(SwingConstants.CENTER);
		bLearn.setBounds((Main.jdim-l_bout)/2, h0, l_bout, i_bout);
		bLearn.setForeground(Color.BLACK);
		Main.pan.add(bLearn);
		bLearn.addActionListener(new ALearn());
        
		bEtalon = new JButton(Main.iBouton);
		bEtalon.setBackground(Color.BLUE);
		if(Main.donnees.langue==0)
		{
			bEtalon.setText("Étalonnage");
		}
		else
		{
			bEtalon.setText("Calibration");
		}
		bEtalon.setVerticalTextPosition(SwingConstants.CENTER);
		bEtalon.setHorizontalTextPosition(SwingConstants.CENTER);
		bEtalon.setBounds((Main.jdim-l_bout)/2, h0+5*h_bout, l_bout, i_bout);
		bEtalon.setForeground(Color.BLACK);
		Main.pan.add(bEtalon);
		bEtalon.addActionListener(new AEtalon());
		
		bAct = new JButton(Main.iBouton);
		bAct.setBackground(Color.BLUE);
		if(Main.donnees.langue==0)
		{
			bAct.setText("Démarrer");
		}
		else
		{
			bAct.setText("Start");
		}
		
		bAct.setVerticalTextPosition(SwingConstants.CENTER);
		bAct.setHorizontalTextPosition(SwingConstants.CENTER);
		bAct.setBounds((Main.jdim-l_bout)/2, h0+h_bout, l_bout, i_bout);
		bAct.setForeground(Color.BLACK);
		Main.pan.add(bAct);
		bAct.addActionListener(new AAct());
		
		bIA = new JButton(Main.iBouton);
		bIA.setBackground(Color.BLUE);
		if(Main.donnees.langue==0)
		{
			bIA.setText("IA (en développement...)");
		}
		else
		{
			bIA.setText("AI (in progress...)");
		}
		bIA.setVerticalTextPosition(SwingConstants.CENTER);
		bIA.setHorizontalTextPosition(SwingConstants.CENTER);
		bIA.setBounds((Main.jdim-l_bout)/2, h0+2*h_bout, l_bout, i_bout);
		bIA.setForeground(Color.BLACK);
		Main.pan.add(bIA);
		bIA.addActionListener(new AIA());
		
		bOption = new JButton(Main.iBouton);
		bOption.setBackground(Color.BLUE);
		if(Main.donnees.langue==0)
		{
			bOption.setText("Option");
		}
		else
		{
			bOption.setText("Option");
		}
		bOption.setVerticalTextPosition(SwingConstants.CENTER);
		bOption.setHorizontalTextPosition(SwingConstants.CENTER);
		bOption.setBounds((Main.jdim-l_bout)/2, h0+3*h_bout, l_bout, i_bout);
		bOption.setForeground(Color.BLACK);
		Main.pan.add(bOption);
		bOption.addActionListener(new AOption());
		Main.fondPanel.setBounds(-1, -15, Main.jdim, Main.idim);
		Main.pan.add(Main.fondPanel);
		
		Main.pan.repaint(); 
		Main.pan.validate();
				    
		while(cond == 0)
		{
			Thread.sleep(200);
		}

		
	}
	public class ALearn implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			cond = 1;
			Main.onglet = 2;
			bLearn.setEnabled(false);
			bAct.setEnabled(false);
		}
	}
	
	public class AEtalon implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			cond = 1;
			Main.onglet = 9;
			bLearn.setEnabled(false);
			bAct.setEnabled(false);
		}
	}
	
	public class AAct implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			cond = 1;
			Main.onglet = 7;
			bLearn.setEnabled(false);
			bAct.setEnabled(false);
		}
	}
	
	public class AIA implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			cond = 1;
			Main.onglet = 10;
			bLearn.setEnabled(false);
			bAct.setEnabled(false);
		}
	}
	
	public class AOption implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			cond = 1;
			Main.onglet = 14;
			bLearn.setEnabled(false);
			bAct.setEnabled(false);
		}
	}
	
}

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;


public class PanBot {
	
	JButton bSeq1;
	JButton bSeq2;
	JButton bSeq3;
	JButton bRetour;
	JTextArea[] tMarche;
	JTextArea tTest;
	int cond;
	
	public PanBot() throws IOException, InterruptedException
	{
		cond = 0;
		Main.pan.removeAll();
		Main.pan.setLayout(null);
        tMarche = new JTextArea[5];
        
        Main.donnees = new Donnees();
        
        bSeq1 = new JButton(Main.iBouton);
        bSeq1.setBackground(Color.BLUE);
        bSeq1.setText(Main.donnees.nameSeq[0]);
        bSeq1.setVerticalTextPosition(SwingConstants.CENTER);
        bSeq1.setHorizontalTextPosition(SwingConstants.CENTER);
        bSeq1.setBounds(0, 0, 130, 25);
        bSeq1.setForeground(Color.BLACK);
		Main.pan.add(bSeq1);
		bSeq1.addActionListener(new ASeq1());
		
		bSeq2 = new JButton(Main.iBouton);
        bSeq2.setBackground(Color.BLUE);
        bSeq2.setText(Main.donnees.nameSeq[1]);
        bSeq2.setVerticalTextPosition(SwingConstants.CENTER);
        bSeq2.setHorizontalTextPosition(SwingConstants.CENTER);
        bSeq2.setBounds(0, 25, 130, 25);
        bSeq2.setForeground(Color.BLACK);
		Main.pan.add(bSeq2);
		bSeq2.addActionListener(new ASeq2());
		
		bSeq3 = new JButton(Main.iBouton);
        bSeq3.setBackground(Color.BLUE);
        bSeq3.setText(Main.donnees.nameSeq[2]);
        bSeq3.setVerticalTextPosition(SwingConstants.CENTER);
        bSeq3.setHorizontalTextPosition(SwingConstants.CENTER);
        bSeq3.setBounds(0, 50, 130, 25);
        bSeq3.setForeground(Color.BLACK);
		Main.pan.add(bSeq3);
		bSeq3.addActionListener(new ASeq3());
		
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
		}
		
	}

	public class ASeq1 implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			cond = 1;
			Main.onglet = 8;
			Main.seq = 0;
			bSeq1.setEnabled(false);
			bSeq2.setEnabled(false);
			bSeq3.setEnabled(false);
			bRetour.setEnabled(false);
		}
	}
	
	public class ASeq2 implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			cond = 1;
			Main.onglet = 8;
			Main.seq = 1;
			bSeq1.setEnabled(false);
			bSeq2.setEnabled(false);
			bSeq3.setEnabled(false);
			bRetour.setEnabled(false);
		}
	}
	
	public class ASeq3 implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			cond = 1;
			Main.onglet = 8;
			Main.seq = 2;
			bSeq1.setEnabled(false);
			bSeq2.setEnabled(false);
			bSeq3.setEnabled(false);
			bRetour.setEnabled(false);
		}
	}

	public class ARetour implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			cond = 1;
			Main.onglet = 1;
			bSeq1.setEnabled(false);
			bSeq2.setEnabled(false);
			bSeq3.setEnabled(false);
			bRetour.setEnabled(false);
		}
	}
}

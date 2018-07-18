import java.awt.AWTException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JTextArea;


public class PanLearn
{
	JButton bSeqFond;
	JButton bSeqInterup;
	JButton bSeqAct;
	JButton bRetour;
	JTextArea[] tMarche;
	int cond;
	
	int clic_touche;
	int maj;
	int ctrl;
	int[] touche;
	
	
	public PanLearn() throws InterruptedException, AWTException, IOException
	{
		cond = 0;
		clic_touche = 0;
		maj = 0;
		ctrl = 0;
		Main.pan.removeAll();
		Main.pan.setLayout(null);
        int i;
        String str_nom;
        tMarche = new JTextArea[5];
        
        
        bSeqFond = new JButton();
        bSeqFond.setText("Séquence de fond");
        bSeqFond.setBounds(0, 0, 200, 25);
		Main.pan.add(bSeqFond);
		bSeqFond.addActionListener(new ASeq1());
		
		bSeqInterup = new JButton("Condition");
        bSeqInterup.setBounds(0, 25, 200, 25);
		Main.pan.add(bSeqInterup);
		bSeqInterup.addActionListener(new ASeq2());
		
		bSeqAct = new JButton("Action");
        bSeqAct.setBounds(0, 50, 200, 25);
		Main.pan.add(bSeqAct);
		bSeqAct.addActionListener(new ASeq3());
		
		bRetour = new JButton("Retour");
		bRetour.setBounds(Main.jdim/2-200, Main.idim - 60, 130, 25);
		Main.pan.add(bRetour);
		bRetour.addActionListener(new ARetour());
		
		touche = new int[5];
		
		
		
		// ============================= 0 ====================================
		
		touche[0] = Main.donnees.marche[0];

			tMarche[0] = new JTextArea(" ");
			tMarche[0].setBounds(20, 200, 100, 18);
			tMarche[0].setEditable(false);
			tMarche[0].setVisible(true);
			Main.pan.add(tMarche[0]);
			tMarche[0].addKeyListener(new KeyListener() {
				/** Handle the key typed event from the text field. */
			      public void keyTyped(KeyEvent e) {
			    	  
			      }
	
			      /** Handle the key pressed event from the text field. */
			      public void keyPressed(KeyEvent e) {
			    	  int a =e.getKeyCode();
			    	  /*
			    	   * Regle codes clavier : 
			    	   * si une touche seule est pressée : touche = keyCode
			    	   * la touche maj ajoute 1000 au keyCode si elle est prssée
			    	   * la touche ctrl ajoute 2000 au keyCode si elle est pressée
			    	   * la touche maj est prioritaire sur ctrl
			    	   */
			    	  if(a==16)
			    	  {
			    		  maj = 1;
			    	  }
		    		  else
		    		  {
		    			  if(a==17)
				    	  {
				    		  ctrl = 1;
				    	  }
		    			  else
		    			  {
		    				  if(maj==1)
		    				  {
		    					  touche[0] = 1000+e.getKeyCode();
		    				  }
		    				  else
		    				  {
		    					  if(ctrl==1)
			    				  {
			    					  touche[0] = 2000+e.getKeyCode();
			    				  }
		    					  else
		    					  {
		    						  touche[0] = e.getKeyCode();
		    					  }
		    				  }
		    			  }
		    		  }
			    	  
	    			  
	    			  clic_touche = 1;
			      }
	
			      /** Handle the key released event from the text field. */
			      public void keyReleased(KeyEvent e) {
			    	  int a =e.getKeyCode();
			    	  if(a==16)
			    	  {
			    		  maj = 0;
			    	  }
			    	  if(a==17)
			    	  {
			    		  ctrl = 0;
			    	  }
			      }
			});

			
			
			// ============================= 1 ====================================
			
			touche[1] = Main.donnees.marche[1];

				tMarche[1] = new JTextArea(" ");
				tMarche[1].setBounds(20, 240, 100, 18);
				tMarche[1].setEditable(false);
				tMarche[1].setVisible(true);
				Main.pan.add(tMarche[1]);
				tMarche[1].addKeyListener(new KeyListener() {
					/** Handle the key typed event from the text field. */
				      public void keyTyped(KeyEvent e) {
				    	  
				      }
		
				      /** Handle the key pressed event from the text field. */
				      public void keyPressed(KeyEvent e) {
				    	  int a =e.getKeyCode();
				    	  /*
				    	   * Regle codes clavier : 
				    	   * si une touche seule est pressée : touche = keyCode
				    	   * la touche maj ajoute 1000 au keyCode si elle est prssée
				    	   * la touche ctrl ajoute 2000 au keyCode si elle est pressée
				    	   * la touche maj est prioritaire sur ctrl
				    	   */
				    	  if(a==16)
				    	  {
				    		  maj = 1;
				    	  }
			    		  else
			    		  {
			    			  if(a==17)
					    	  {
					    		  ctrl = 1;
					    	  }
			    			  else
			    			  {
			    				  if(maj==1)
			    				  {
			    					  touche[1] = 1000+e.getKeyCode();
			    				  }
			    				  else
			    				  {
			    					  if(ctrl==1)
				    				  {
				    					  touche[1] = 2000+e.getKeyCode();
				    				  }
			    					  else
			    					  {
			    						  touche[1] = e.getKeyCode();
			    					  }
			    				  }
			    			  }
			    		  }
				    	  
		    			  
		    			  clic_touche = 1;
				      }
		
				      /** Handle the key released event from the text field. */
				      public void keyReleased(KeyEvent e) {
				    	  int a =e.getKeyCode();
				    	  if(a==16)
				    	  {
				    		  maj = 0;
				    	  }
				    	  if(a==17)
				    	  {
				    		  ctrl = 0;
				    	  }
				      }
				});

				
				
				// ============================= 2 ====================================
				
				touche[2] = Main.donnees.marche[2];

					tMarche[2] = new JTextArea(" ");
					tMarche[2].setBounds(20, 280, 100, 18);
					tMarche[2].setEditable(false);
					tMarche[2].setVisible(true);
					Main.pan.add(tMarche[2]);
					tMarche[2].addKeyListener(new KeyListener() {
						/** Handle the key typed event from the text field. */
					      public void keyTyped(KeyEvent e) {
					    	  
					      }
			
					      /** Handle the key pressed event from the text field. */
					      public void keyPressed(KeyEvent e) {
					    	  int a =e.getKeyCode();
					    	  /*
					    	   * Regle codes clavier : 
					    	   * si une touche seule est pressée : touche = keyCode
					    	   * la touche maj ajoute 1000 au keyCode si elle est prssée
					    	   * la touche ctrl ajoute 2000 au keyCode si elle est pressée
					    	   * la touche maj est prioritaire sur ctrl
					    	   */
					    	  if(a==16)
					    	  {
					    		  maj = 1;
					    	  }
				    		  else
				    		  {
				    			  if(a==17)
						    	  {
						    		  ctrl = 1;
						    	  }
				    			  else
				    			  {
				    				  if(maj==1)
				    				  {
				    					  touche[2] = 1000+e.getKeyCode();
				    				  }
				    				  else
				    				  {
				    					  if(ctrl==1)
					    				  {
					    					  touche[2] = 2000+e.getKeyCode();
					    				  }
				    					  else
				    					  {
				    						  touche[2] = e.getKeyCode();
				    					  }
				    				  }
				    			  }
				    		  }
					    	  
			    			  
			    			  clic_touche = 1;
					      }
			
					      /** Handle the key released event from the text field. */
					      public void keyReleased(KeyEvent e) {
					    	  int a =e.getKeyCode();
					    	  if(a==16)
					    	  {
					    		  maj = 0;
					    	  }
					    	  if(a==17)
					    	  {
					    		  ctrl = 0;
					    	  }
					      }
					});

					
					
					// ============================= 3 ====================================
					
					touche[3] = Main.donnees.marche[3];

						tMarche[3] = new JTextArea("Touche : 49");
						tMarche[3].setBounds(20, 320, 100, 18);
						tMarche[3].setEditable(false);
						tMarche[3].setVisible(true);
						Main.pan.add(tMarche[3]);
						tMarche[3].addKeyListener(new KeyListener() {
							/** Handle the key typed event from the text field. */
						      public void keyTyped(KeyEvent e) {
						    	  
						      }
				
						      /** Handle the key pressed event from the text field. */
						      public void keyPressed(KeyEvent e) {
						    	  int a =e.getKeyCode();
						    	  /*
						    	   * Regle codes clavier : 
						    	   * si une touche seule est pressée : touche = keyCode
						    	   * la touche maj ajoute 1000 au keyCode si elle est prssée
						    	   * la touche ctrl ajoute 2000 au keyCode si elle est pressée
						    	   * la touche maj est prioritaire sur ctrl
						    	   */
						    	  if(a==16)
						    	  {
						    		  maj = 1;
						    	  }
					    		  else
					    		  {
					    			  if(a==17)
							    	  {
							    		  ctrl = 1;
							    	  }
					    			  else
					    			  {
					    				  if(maj==1)
					    				  {
					    					  touche[3] = 1000+e.getKeyCode();
					    				  }
					    				  else
					    				  {
					    					  if(ctrl==1)
						    				  {
						    					  touche[3] = 2000+e.getKeyCode();
						    				  }
					    					  else
					    					  {
					    						  touche[3] = e.getKeyCode();
					    					  }
					    				  }
					    			  }
					    		  }
						    	  
				    			  
				    			  clic_touche = 1;
						      }
				
						      /** Handle the key released event from the text field. */
						      public void keyReleased(KeyEvent e) {
						    	  int a =e.getKeyCode();
						    	  if(a==16)
						    	  {
						    		  maj = 0;
						    	  }
						    	  if(a==17)
						    	  {
						    		  ctrl = 0;
						    	  }
						      }
						});

						
						
						// ============================= 4 ====================================
						
						touche[4] = Main.donnees.marche[4];

							tMarche[4] = new JTextArea("Touche : 49");
							tMarche[4].setBounds(20, 360, 100, 18);
							tMarche[4].setEditable(false);
							tMarche[4].setVisible(true);
							Main.pan.add(tMarche[4]);
							tMarche[4].addKeyListener(new KeyListener() {
								/** Handle the key typed event from the text field. */
							      public void keyTyped(KeyEvent e) {
							    	  
							      }
					
							      /** Handle the key pressed event from the text field. */
							      public void keyPressed(KeyEvent e) {
							    	  int a =e.getKeyCode();
							    	  /*
							    	   * Regle codes clavier : 
							    	   * si une touche seule est pressée : touche = keyCode
							    	   * la touche maj ajoute 1000 au keyCode si elle est prssée
							    	   * la touche ctrl ajoute 2000 au keyCode si elle est pressée
							    	   * la touche maj est prioritaire sur ctrl
							    	   */
							    	  if(a==16)
							    	  {
							    		  maj = 1;
							    	  }
						    		  else
						    		  {
						    			  if(a==17)
								    	  {
								    		  ctrl = 1;
								    	  }
						    			  else
						    			  {
						    				  if(maj==1)
						    				  {
						    					  touche[4] = 1000+e.getKeyCode();
						    				  }
						    				  else
						    				  {
						    					  if(ctrl==1)
							    				  {
							    					  touche[4] = 2000+e.getKeyCode();
							    				  }
						    					  else
						    					  {
						    						  touche[4] = e.getKeyCode();
						    					  }
						    				  }
						    			  }
						    		  }
							    	  
					    			  
					    			  clic_touche = 1;
							      }
					
							      /** Handle the key released event from the text field. */
							      public void keyReleased(KeyEvent e) {
							    	  int a =e.getKeyCode();
							    	  if(a==16)
							    	  {
							    		  maj = 0;
							    	  }
							    	  if(a==17)
							    	  {
							    		  ctrl = 0;
							    	  }
							      }
							});
							
		Main.pan.add(Main.fondPanel);
							
		Main.pan.repaint(); 
		Main.pan.validate();
			
		
		/* ===========================================================
		 * 					Condition de sortie
		 * ===========================================================
		 */
		
		while(cond == 0)
		{
			Thread.sleep(200);
			
				str_nom = "Haut : ";
				str_nom = new StringBuilder().append(str_nom).append(String.valueOf(touche[0])).toString();
				tMarche[0].setText(str_nom);

				str_nom = "Bas : ";
				str_nom = new StringBuilder().append(str_nom).append(String.valueOf(touche[1])).toString();
				tMarche[1].setText(str_nom);
				
				str_nom = "Droite : ";
				str_nom = new StringBuilder().append(str_nom).append(String.valueOf(touche[2])).toString();
				tMarche[2].setText(str_nom);
				
				str_nom = "Gauche : ";
				str_nom = new StringBuilder().append(str_nom).append(String.valueOf(touche[3])).toString();
				tMarche[3].setText(str_nom);
				
				str_nom = "Marche : ";
				str_nom = new StringBuilder().append(str_nom).append(String.valueOf(touche[4])).toString();
				tMarche[4].setText(str_nom);
		}
		
		for(i=0;i<5;i++)
		{
			Main.donnees.marche[i] = touche[i];
		}
		
		
		Main.donnees.sauvegardeM();
		
	}// fin du constructeur
	
	/*
	 *  ======================================================
	 * 	======================================================
	 * 	======================================================
	 */
	public class ASeq1 implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			cond = 1;
			Main.onglet = 4;
			bSeqFond.setEnabled(false);
			bSeqInterup.setEnabled(false);
			bSeqAct.setEnabled(false);
			bRetour.setEnabled(false);
		}
	}
	
	public class ASeq2 implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			cond = 1;
			Main.onglet = 5;
			bSeqFond.setEnabled(false);
			bSeqInterup.setEnabled(false);
			bSeqAct.setEnabled(false);
			bRetour.setEnabled(false);
		}
	}
	
	public class ASeq3 implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			cond = 1;
			Main.onglet = 6;
			bSeqFond.setEnabled(false);
			bSeqInterup.setEnabled(false);
			bSeqAct.setEnabled(false);
			bRetour.setEnabled(false);
		}
	}
	
	public class ARetour implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			cond = 1;
			Main.onglet = 2;
			bSeqFond.setEnabled(false);
			bSeqInterup.setEnabled(false);
			bSeqAct.setEnabled(false);
			bRetour.setEnabled(false);
		}
	}

	
	
}

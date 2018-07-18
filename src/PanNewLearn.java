import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;



public class PanNewLearn
{
	JButton bSeq1;
	JButton bSeq2;
	JButton bSeq3;
	JButton bRetour;
	JButton[] tMarche;//JTextArea
	JTextArea tTest;

	int cond;
	
	int clic_touche;
	int maj;
	int ctrl;
	int[] touche;
	char[] char_touche;
	
	public PanNewLearn() throws InterruptedException, AWTException, IOException
	{
		cond = 0;
		Main.pan.removeAll();
		Main.pan.setLayout(null);
        int i;
        String str_nom;
        tMarche = new JButton[5];//JTextArea
        
        // interface
        int l0 = -200;
 		int h0 = 200;
 		int h_bout = 50;
 		int i_bout = 40;
 		int l_bout = 200;
 		
 		
 		int h_space = -50; // déphasage partie droite / gauche
 		int l_space = 30;
 		int h_txt = 80;
 		int i_txt = 55;
 		int l_txt = 55;
 		
 		// =======================================================================
 		

 		JTextArea haut = new JTextArea("HAUT");
 		JTextArea bas = new JTextArea("BAS");
 		JTextArea droite = new JTextArea("DROITE");
 		JTextArea gauche = new JTextArea("GAUCHE");
 		JTextArea marche = new JTextArea("MARCHER-COURIR");
 		if(Main.donnees.langue==1)
 		{
 	 		haut.setText("UP");
 	 		bas.setText("DOWN");
 	 		droite.setText("RIGHT");
 	 		gauche.setText("LEFT");
 	 		marche.setText("WALK-RUN");
		}
		
 		
 		haut.setBounds((Main.jdim-l_txt)/2-l0+12, h0+2*h_txt+h_space-180, 50, 20);
 		bas.setBounds((Main.jdim-l_txt)/2-l0+15, h0+2*h_txt+h_space-20, 50, 20);
 		droite.setBounds((Main.jdim-l_txt)/2-l_txt-l0-l_space+2, h0+h_txt+h_space-20, 60, 20);
 		gauche.setBounds((Main.jdim-l_txt)/2+l_txt-l0+l_space+2, h0+h_txt+h_space-20, 60, 20);
 		marche.setBounds(635,400,175,20);//(Main.jdim-l_txt)/2-l0, h0+2*h_txt+h_space, 50, 20);
 		 		
 		haut.setOpaque(false);
 		bas.setOpaque(false);
 		droite.setOpaque(false);
 		gauche.setOpaque(false);
 		marche.setOpaque(false);
 		
 		haut.setFont(new Font("Consolas", Font.PLAIN, 16));
 		bas.setFont(new Font("Consolas", Font.PLAIN, 16));
 		droite.setFont(new Font("Consolas", Font.PLAIN, 16));
 		gauche.setFont(new Font("Consolas", Font.PLAIN, 16));
 		marche.setFont(new Font("Consolas", Font.PLAIN, 16));
 		
 		haut.setForeground(Color.WHITE);
 		bas.setForeground(Color.WHITE);
 		droite.setForeground(Color.WHITE);
 		gauche.setForeground(Color.WHITE);
 		marche.setForeground(Color.WHITE);
 		
 		Main.pan.add(haut);
 		Main.pan.add(bas);
 		Main.pan.add(droite);
 		Main.pan.add(gauche);
 		Main.pan.add(marche);
 		 		
 		
 		
 		
 		JTextArea thaut = new JTextArea("touche ");
 		JTextArea tbas = new JTextArea("touche ");
 		JTextArea tdroite = new JTextArea("touche ");
 		JTextArea tgauche = new JTextArea("touche ");
 		JTextArea tmarche = new JTextArea("touche ");
 		
 		if(Main.donnees.langue==1)
 		{
 	 		thaut.setText("key ");
 	 		tbas.setText("key ");
 	 		tdroite.setText("key ");
 	 		tgauche.setText("key ");
 	 		tmarche.setText("key ");
		}
 		
 		thaut.setBounds((Main.jdim-l_txt)/2-l0, h0+2*h_txt+h_space-177+75, 50, 20);
 		tbas.setBounds((Main.jdim-l_txt)/2-l0, h0+2*h_txt+h_space-20+75, 50, 20);
 		tdroite.setBounds((Main.jdim-l_txt)/2-l_txt-l0-l_space, h0+h_txt+h_space-20+75, 60, 20);
 		tgauche.setBounds((Main.jdim-l_txt)/2+l_txt-l0+l_space, h0+h_txt+h_space-20+75, 60, 20);
 		tmarche.setBounds(673,400+55, 50, 20);
 		 		
 		thaut.setOpaque(false);
 		tbas.setOpaque(false);
 		tdroite.setOpaque(false);
 		tgauche.setOpaque(false);
 		tmarche.setOpaque(false);
 		
 		thaut.setFont(new Font("Helvetica.Italic", Font.PLAIN, 10));
 		tbas.setFont(new Font("Helvetica.Italic", Font.PLAIN, 10));
 		tdroite.setFont(new Font("Helvetica.Italic", Font.PLAIN, 10));
 		tgauche.setFont(new Font("Helvetica.Italic", Font.PLAIN, 10));
 		tmarche.setFont(new Font("Helvetica.Italic", Font.PLAIN, 10));
 		
 		thaut.setForeground(Color.WHITE);
 		tbas.setForeground(Color.WHITE);
 		tdroite.setForeground(Color.WHITE);
 		tgauche.setForeground(Color.WHITE);
 		tmarche.setForeground(Color.WHITE);

 		thaut.setEditable(false);
 		tbas.setEditable(false);
 		tdroite.setEditable(false);
 		tgauche.setEditable(false);
 		tmarche.setEditable(false);
 		
 		Main.pan.add(thaut);
 		Main.pan.add(tbas);
 		Main.pan.add(tdroite);
 		Main.pan.add(tgauche);
 		Main.pan.add(tmarche);
 		
 		
 		// =======================================================================
        
        Main.donnees = new Donnees();
        
        bSeq1 = new JButton(Main.iBouton);
        bSeq1.setBackground(Color.BLUE);
        bSeq1.setText(Main.donnees.nameSeq[0]);
		bSeq1.setVerticalTextPosition(SwingConstants.CENTER);
        bSeq1.setHorizontalTextPosition(SwingConstants.CENTER);
        bSeq1.setBounds((Main.jdim-l_bout)/2+l0, h0, l_bout, i_bout);
		bSeq1.setForeground(Color.BLACK);
		Main.pan.add(bSeq1);
		bSeq1.addActionListener(new ASeq1());
		
		bSeq2 = new JButton(Main.iBouton);
        bSeq2.setBackground(Color.BLUE);
        bSeq2.setText(Main.donnees.nameSeq[1]);
		bSeq2.setVerticalTextPosition(SwingConstants.CENTER);
        bSeq2.setHorizontalTextPosition(SwingConstants.CENTER);
        bSeq2.setBounds((Main.jdim-l_bout)/2+l0, h0+h_bout, l_bout, i_bout);
		bSeq2.setForeground(Color.BLACK);
		Main.pan.add(bSeq2);
		bSeq2.addActionListener(new ASeq2());
		
		bSeq3 = new JButton(Main.iBouton);
        bSeq3.setBackground(Color.BLUE);
        bSeq3.setText(Main.donnees.nameSeq[2]);
		bSeq3.setVerticalTextPosition(SwingConstants.CENTER);
        bSeq3.setHorizontalTextPosition(SwingConstants.CENTER);
        bSeq3.setBounds((Main.jdim-l_bout)/2+l0, h0+2*h_bout, l_bout, i_bout);
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
        bRetour.setBounds((Main.jdim-l_bout)/2+l0, h0+4*h_bout, l_bout, i_bout);
        bRetour.setForeground(Color.BLACK);
		Main.pan.add(bRetour);
		bRetour.addActionListener(new ARetour());
		
		touche = new int[5];
		char_touche = new char[5];
		
		
		// ============================= 0 ====================================
		
		touche[0] = Main.donnees.marche[0];
		char_touche[0] = Main.donnees.char_marche[0];

			tMarche[0] = new JButton(" ");
			tMarche[0].setIcon(Main.iMarche);
			tMarche[0].setVerticalTextPosition(SwingConstants.CENTER);
			tMarche[0].setHorizontalTextPosition(SwingConstants.CENTER);
			tMarche[0].setBorderPainted(false);
			tMarche[0].setForeground(Color.WHITE);
			tMarche[0].setBounds((Main.jdim-l_txt)/2-l0, h0+h_space, l_txt, i_txt);
			//tMarche[0].setEditable(false);
			tMarche[0].setVisible(true);
			Main.pan.add(tMarche[0]);
			tMarche[0].addActionListener(new Amarche0());
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
		    				  char_touche[0] = e.getKeyChar();
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
			char_touche[1] = Main.donnees.char_marche[1];

				tMarche[1] = new JButton(" ");
				tMarche[1].setIcon(Main.iMarche);
				tMarche[1].setVerticalTextPosition(SwingConstants.CENTER);
				tMarche[1].setHorizontalTextPosition(SwingConstants.CENTER);
				tMarche[1].setBorderPainted(false);
				tMarche[1].setForeground(Color.WHITE);
				tMarche[1].setBounds((Main.jdim-l_txt)/2-l0, h0+2*h_txt+h_space, l_txt, i_txt);
				//tMarche[1].setEditable(false);
				tMarche[1].setVisible(true);
				Main.pan.add(tMarche[1]);
				tMarche[1].addActionListener(new Amarche1());
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
			    				  char_touche[1] = e.getKeyChar();
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
				char_touche[2] = Main.donnees.char_marche[2];

					tMarche[2] = new JButton(" ");
					tMarche[2].setIcon(Main.iMarche);
					tMarche[2].setVerticalTextPosition(SwingConstants.CENTER);
					tMarche[2].setHorizontalTextPosition(SwingConstants.CENTER);
					tMarche[2].setBorderPainted(false);
					tMarche[2].setForeground(Color.WHITE);
					tMarche[2].setBounds((Main.jdim-l_txt)/2+l_txt-l0+l_space, h0+h_txt+h_space, l_txt, i_txt);
					//tMarche[2].setEditable(false);
					tMarche[2].setVisible(true);
					Main.pan.add(tMarche[2]);
					tMarche[2].addActionListener(new Amarche2());
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
				    				  char_touche[2] = e.getKeyChar();
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
					char_touche[3] = Main.donnees.char_marche[3];

						tMarche[3] = new JButton(" ");
						tMarche[3].setIcon(Main.iMarche);
						tMarche[3].setVerticalTextPosition(SwingConstants.CENTER);
						tMarche[3].setHorizontalTextPosition(SwingConstants.CENTER);
						tMarche[3].setBorderPainted(false);
						tMarche[3].setForeground(Color.WHITE);
						tMarche[3].setBounds((Main.jdim-l_txt)/2-l_txt-l0-l_space, h0+h_txt+h_space, l_txt, i_txt);
						//tMarche[3].setEditable(false);
						tMarche[3].setVisible(true);
						Main.pan.add(tMarche[3]);
						tMarche[3].addActionListener(new Amarche3());
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
					    				  char_touche[3] = e.getKeyChar();
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
						char_touche[4] = Main.donnees.char_marche[4];

							tMarche[4] = new JButton(" ");
							tMarche[4].setIcon(Main.iMarcheL);
							tMarche[4].setVerticalTextPosition(SwingConstants.CENTER);
							tMarche[4].setHorizontalTextPosition(SwingConstants.CENTER);
							tMarche[4].setBorderPainted(false);
							tMarche[4].setForeground(Color.WHITE);
							tMarche[4].setBounds((Main.jdim-l_bout)/2-l0+13, h0+3*h_txt+h_space+25, l_bout-25, i_txt-15);
							//tMarche[4].setEditable(false);
							tMarche[4].setFont(new Font("Helvetica.Italic", Font.PLAIN, 13));
							tMarche[4].setVisible(true);
							Main.pan.add(tMarche[4]);
							tMarche[4].addActionListener(new Amarche4());
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
						    				  char_touche[4] = e.getKeyChar();
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
			
				str_nom = "<html><center>";
				str_nom = new StringBuilder().append(str_nom).append(String.valueOf(char_touche[0])).toString();
				str_nom = new StringBuilder().append(str_nom).append(" <br>(").toString();
				str_nom = new StringBuilder().append(str_nom).append(String.valueOf(touche[0])).toString();
				str_nom = new StringBuilder().append(str_nom).append(")</center></html>").toString();
				str_nom = "touche ";
				if(Main.donnees.langue==1)
		 		{
					str_nom = "key ";
		 		}
				str_nom = new StringBuilder().append(str_nom).append(String.valueOf(touche[0])).toString();
				tMarche[0].setText(String.valueOf(char_touche[0]));
				tMarche[0].setFont(new Font("Consolas", Font.BOLD, 18));
				thaut.setText(str_nom);
		 		
				str_nom = "<html><center>";
				str_nom = new StringBuilder().append(str_nom).append(String.valueOf(char_touche[1])).toString();
				str_nom = new StringBuilder().append(str_nom).append(" <br>(").toString();
				str_nom = new StringBuilder().append(str_nom).append(String.valueOf(touche[1])).toString();
				str_nom = new StringBuilder().append(str_nom).append(")</center></html>").toString();
				str_nom = "touche ";
				if(Main.donnees.langue==1)
		 		{
					str_nom = "key ";
		 		}
				str_nom = new StringBuilder().append(str_nom).append(String.valueOf(touche[1])).toString();
				tMarche[1].setText(String.valueOf(char_touche[1]));
				tMarche[1].setFont(new Font("Consolas", Font.BOLD, 18));
				tbas.setText(str_nom);
				
				str_nom = "<html><center>";
				str_nom = new StringBuilder().append(str_nom).append(String.valueOf(char_touche[2])).toString();
				str_nom = new StringBuilder().append(str_nom).append(" <br>(").toString();
				str_nom = new StringBuilder().append(str_nom).append(String.valueOf(touche[2])).toString();
				str_nom = new StringBuilder().append(str_nom).append(")</center></html>").toString();
				str_nom = "touche ";
				if(Main.donnees.langue==1)
		 		{
					str_nom = "key ";
		 		}
				str_nom = new StringBuilder().append(str_nom).append(String.valueOf(touche[2])).toString();
				tMarche[2].setText(String.valueOf(char_touche[2]));
				tMarche[2].setFont(new Font("Consolas", Font.BOLD, 18));
				tgauche.setText(str_nom);
				
				str_nom = "<html><center>";
				str_nom = new StringBuilder().append(str_nom).append(String.valueOf(char_touche[3])).toString();
				str_nom = new StringBuilder().append(str_nom).append(" <br>(").toString();
				str_nom = new StringBuilder().append(str_nom).append(String.valueOf(touche[3])).toString();
				str_nom = new StringBuilder().append(str_nom).append(")</center></html>").toString();
				str_nom = "touche ";
				if(Main.donnees.langue==1)
		 		{
					str_nom = "key ";
		 		}
				str_nom = new StringBuilder().append(str_nom).append(String.valueOf(touche[3])).toString();
				tMarche[3].setText(String.valueOf(char_touche[3]));
				tMarche[3].setFont(new Font("Consolas", Font.BOLD, 18));
				tdroite.setText(str_nom);
				
				str_nom = "<html><center>";
				str_nom = new StringBuilder().append(str_nom).append(String.valueOf(char_touche[4])).toString();
				//str_nom = new StringBuilder().append(str_nom).append(" (").toString();
				//str_nom = new StringBuilder().append(str_nom).append(String.valueOf(touche[4])).toString();
				str_nom = new StringBuilder().append(str_nom).append("</center></html>").toString();
				tMarche[4].setText(str_nom);
				tMarche[4].setFont(new Font("Consolas", Font.BOLD, 18));
				str_nom = "touche ";
				if(Main.donnees.langue==1)
		 		{
					str_nom = "key ";
		 		}
				str_nom = new StringBuilder().append(str_nom).append(String.valueOf(touche[4])).toString();
				
				tmarche.setText(str_nom);
		}
		
		for(i=0;i<5;i++)
		{
			Main.donnees.marche[i] = touche[i];
			Main.donnees.char_marche[i] = char_touche[i];
		}
		
		
		Main.donnees.sauvegardeM();
		

		
	}
	public class ASeq1 implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			cond = 1;
			Main.onglet = 4;
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
			Main.onglet = 4;
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
			Main.onglet = 4;
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
	
	public class Amarche0 implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			tMarche[0].setIcon(Main.iMarches);
			tMarche[1].setIcon(Main.iMarche);
			tMarche[2].setIcon(Main.iMarche);
			tMarche[3].setIcon(Main.iMarche);
			tMarche[4].setIcon(Main.iMarcheL);
		}
	}
	public class Amarche1 implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			tMarche[0].setIcon(Main.iMarche);
			tMarche[1].setIcon(Main.iMarches);
			tMarche[2].setIcon(Main.iMarche);
			tMarche[3].setIcon(Main.iMarche);
			tMarche[4].setIcon(Main.iMarcheL);
		}
	}
	public class Amarche2 implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			tMarche[0].setIcon(Main.iMarche);
			tMarche[1].setIcon(Main.iMarche);
			tMarche[2].setIcon(Main.iMarches);
			tMarche[3].setIcon(Main.iMarche);
			tMarche[4].setIcon(Main.iMarcheL);
		}
	}
	public class Amarche3 implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			tMarche[0].setIcon(Main.iMarche);
			tMarche[1].setIcon(Main.iMarche);
			tMarche[2].setIcon(Main.iMarche);
			tMarche[3].setIcon(Main.iMarches);
			tMarche[4].setIcon(Main.iMarcheL);
		}
	}
	public class Amarche4 implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			tMarche[0].setIcon(Main.iMarche);
			tMarche[1].setIcon(Main.iMarche);
			tMarche[2].setIcon(Main.iMarche);
			tMarche[3].setIcon(Main.iMarche);
			tMarche[4].setIcon(Main.iMarcheLs);
		}
	}
	
}

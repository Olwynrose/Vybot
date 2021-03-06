import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

public class Donnees {

	
	String[] nameSeq;
	int[] lSeq;
	Action[][] aSeq;
	int[][] is_act;
	int[][] l_text;
	String[][] dialogues;
	
	String[] nameSeq1;
	int[] lSeq1;
	Action[][] aSeq1;
	int[] conds;
	int[] th_conds;
	String[] str_conds;
	int[][] l_text1;
	String[][] dialogues1;
	
	int[] marche;			// 0:haut, 1:bas, 2:droite, 3 gauche, 4:marcher
	char[] char_marche;
	
	int langue;				// option langue 0: Francais, 1: Anglais
	int etalonnage;			// option �talonnage 0:Fiesta, 1: autre jeu
	int secret;				// option secret 0: ind�tectable, 1: pr�cis
	
	
	int maxSeq;
	
	public Donnees() throws FileNotFoundException, IOException
	{
		int i = 0;
		int j = 0;
		int k = 0;
		int ll = 0;
		String str_nom;
		langue = 0;	
		etalonnage = 0;
		secret = 0;
		maxSeq = 120;
		aSeq = new Action[Main.n_seq][maxSeq];
		aSeq1 = new Action[Main.n_seq1][maxSeq];
		is_act = new int[Main.n_seq][Main.n_seq1];
		l_text = new int[Main.n_seq][3];
		l_text1 = new int[Main.n_seq1][3];
		dialogues = new String[Main.n_seq][3];
		dialogues1 = new String[Main.n_seq1][3];
		
		conds = new int[Main.n_seq1];
		th_conds = new int[Main.n_seq1];
		str_conds = new String[Main.n_seq1];
		
		for(i=0; i<Main.n_seq; i++)
		{
			for(j=0; j<maxSeq; j++)
			{
				aSeq[i][j] = new Action(" ", 0);
			}
			for(j=0; j<Main.n_seq1; j++)
			{
				is_act[i][j] = 0;
			}
			
			l_text[i][0] = 0;
			l_text[i][1] = 0;
			l_text[i][2] = 0;
			
			dialogues[i][0] = "Yep";
			dialogues[i][1] = "Hello";
			dialogues[i][2] = "Yolo";
			
		}
		for(i=0; i<Main.n_seq1; i++)
		{
			for(j=0; j<maxSeq; j++)
			{
				aSeq1[i][j] = new Action(" ", 0);
			}
			

			l_text1[i][0] = 0;
			l_text1[i][1] = 0;
			l_text1[i][2] = 0;
			
			dialogues1[i][0] = "Yep";
			dialogues1[i][1] = "Hello";
			dialogues1[i][2] = "Yolo";
			
			conds[i] = 0;
			th_conds[i] = 50;
			str_conds[i] = "buff";
		}
		lSeq = new int[Main.n_seq];
		for(i=0; i<Main.n_seq; i++)
		{
			lSeq[i] = 0;
		}
		lSeq1 = new int[Main.n_seq1];
		for(i=0; i<Main.n_seq1; i++)
		{
			lSeq1[i] = 0;
		}
		nameSeq = new String[Main.n_seq];
		for(i=0; i<Main.n_seq; i++)
		{
			nameSeq[i] = "S�quence";
		}
		
		
		nameSeq1 = new String[Main.n_seq1];
		for(i=0; i<Main.n_seq1; i++)
		{
			nameSeq1[i] = "Vide";
		}
		
		
		marche = new int[5];
		char_marche = new char[5];
		
		int d_seq = 0;
		int f_seq = 0;
		int d_act = 0;
		int f_act = 0;
		int cond = 0;
		int n_line = 0;
		int compt = 0;
		int n_actions = -1;
		int n_uti = 0;
		String[] info;
		String str;
		
		
		// Chargement des s�quences de fond
		
		try(BufferedReader br = new BufferedReader(new FileReader("Config/configuration.txt"))) 
		{
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
	
		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		        n_line = n_line + 1;
		    }
		    String everything = sb.toString();
		    
		    info = new String[n_line];
		    
		    BufferedReader reader  = new BufferedReader(new StringReader(everything));
		    while ((str = reader.readLine()) != null) 
		    {
	    	   if (str.length() > 0) 
	    		   {
	    		   		//System.out.println(str);
	    		   		info[compt] = str;
	    		   		compt = compt + 1;
	    		   }
	    	}
		    
		    
		    i = 0;
		    while(i<n_line && n_uti<Main.n_seq) // recherche dans la memoire
		    {
		    	
		    	if(info[i].equals("<seq>")) // debut s�quence
		    	{
		    		d_seq = i;
		    		j = d_seq;
		    		f_seq = j;
		    		while(i<n_line && j<n_line && !info[j].equals("</seq>")) // recherche la fin de la s�quence
		    		{
		    			j = j + 1;
		    			f_seq = j;
		    		}
		    		
		    		// cr�� une nouvelle s�quence
		    		n_actions = -1;
		    		k = d_seq;
		    		cond = 0;
		    		while(cond==0 && k<f_seq && k+1<n_line)
		    		{
		    			k = k + 1;
		    			if(info[k].equals("<nms>"))
		    			{
		    				cond = 1;
		    				k = k + 1;
		    				nameSeq[n_uti] = info[k];
		    				//System.out.println("S�quence " + n_uti + " : " + info[k]);
		    			}
		    		}
		    		
		    		// r�cup�re les dialogues propos�s
		    		k = d_seq;
		    		cond = 0;
		    		while(cond==0 && k<f_seq && k+1<n_line)
		    		{
		    			k = k + 1;
		    			if(info[k].equals("<dg0>"))
		    			{
		    				cond = 1;
		    				k = k + 1;
		    				dialogues[n_uti][0] = info[k];
		    				//System.out.println("  Dialogue 0 " + n_uti + " : " + info[k]);
		    			}
		    		}
		    		k = d_seq;
		    		cond = 0;
		    		while(cond==0 && k<f_seq && k+1<n_line)
		    		{
		    			k = k + 1;
		    			if(info[k].equals("<dg1>"))
		    			{
		    				cond = 1;
		    				k = k + 1;
		    				dialogues[n_uti][1] = info[k];
		    				//System.out.println("  Dialogue 1 " + n_uti + " : " + info[k]);
		    			}
		    		}
		    		k = d_seq;
		    		cond = 0;
		    		while(cond==0 && k<f_seq && k+1<n_line)
		    		{
		    			k = k + 1;
		    			if(info[k].equals("<dg2>"))
		    			{
		    				cond = 1;
		    				k = k + 1;
		    				dialogues[n_uti][2] = info[k];
		    				//System.out.println("  Dialogue 2 " + n_uti + " : " + info[k]);
		    			}
		    		}
		    		
		    		// r�cup�re les conditions
		    		for(int it=0;it<Main.n_seq1;it++)
		    		{
		    			k = d_seq;
		    			cond = 0;
		    			while(cond==0 && k<f_seq && k+1<n_line)
		    			{
		    				k = k + 1;
		    				str_nom = "<cd";
		    				str_nom = new StringBuilder().append(str_nom).append(String.valueOf(it)).toString();
		    				str_nom = new StringBuilder().append(str_nom).append(">").toString();
			    			
		    				if(info[k].equals(str_nom))
			    			{
			    				cond = 1;
			    				k = k + 1;
			    				is_act[n_uti][it] = Integer.valueOf(info[k]);
			    				//System.out.println("S�quence d'action 1 " + info[k]);
			    			}
			    			
		    			}
		    		}
	    			/*while(cond==0 && k<f_seq && k+1<n_line)
	    			{
	    				k = k + 1;
		    			if(info[k].equals("<cd0>"))
		    			{
		    				cond = 1;
		    				k = k + 1;
		    				is_act[n_uti][0] = Integer.valueOf(info[k]);
		    				//System.out.println("S�quence d'action 1 " + info[k]);
		    			}
		    			
	    			}
	    			k = d_seq;
	    			cond = 0;
	    			while(cond==0 && k<f_seq && k+1<n_line)
	    			{
	    				k = k + 1;
		    			if(info[k].equals("<cd1>"))
		    			{
		    				cond = 1;
		    				k = k + 1;
		    				is_act[n_uti][1] = Integer.valueOf(info[k]);
		    				//System.out.println("S�quence d'action 2 " + info[k]);
		    			}
		    			
	    			}
	    			k = d_seq;
	    			cond = 0;
	    			while(cond==0 && k<f_seq && k+1<n_line)
	    			{
	    				k = k + 1;
		    			if(info[k].equals("<cd2>"))
		    			{
		    				cond = 1;
		    				k = k + 1;
		    				is_act[n_uti][2] = Integer.valueOf(info[k]);
		    				//System.out.println("S�quence d'action 3 " + info[k]);
		    			}
		    			
	    			}*/
	    			// fin des conditions
		    		
		    		// r�cup�re les actions
		    		
		    		k = d_seq;
		    		while(k<f_seq) // cherche dans la s�quence
		    		{
		    			if(info[k].equals("<act>"))
		    			{
		    				d_act = k;
		    				j = d_act;
				    		f_act = j;
				    		while(j<f_seq && !info[j].equals("</act>")) // recherche la fin de l'action
				    		{
				    			j = j + 1;
				    			f_act = j;
				    		}
				    		
				    		
			    			// cr�� une nouvelle action
			    			k = d_act;
			    			cond = 0;
			    			while(cond==0 && k<f_act)
			    			{
			    				k = k + 1;
				    			if(info[k].equals("<nma>"))
				    			{
				    				cond = 1;
				    				k = k + 1;
				    				if(k+3<n_line)
				    				{
				    					n_actions = n_actions + 1;
					    				aSeq[n_uti][n_actions] = new Action(info[k], Integer.valueOf(info[k+3]));
					    				//System.out.println("  Action n" + n_actions + " " + info[k] + ", dure " +info[k+3] + "ms" );
				    				}
				    			}
			    			}
			    			// fin nom + temps
			    			
			    			// r�cup�re la cl�
			    			k = d_act;
			    			cond = 0;
			    			while(cond==0 && k<f_act && k+1<n_line)
			    			{
			    				k = k + 1;
				    			if(info[k].equals("<key>"))
				    			{
				    				cond = 1;
				    				k = k + 1;
				    				aSeq[n_uti][n_actions].keyCode = Integer.valueOf(info[k]);
				    				//System.out.println("    keyCode " + info[k]);
				    			}
				    			
			    			}
			    			// fin de la cl�
			    			
			    			// r�cup�re la touche
			    			k = d_act;
			    			cond = 0;
			    			while(cond==0 && k<f_act && k+1<n_line)
			    			{
			    				k = k + 1;
				    			if(info[k].equals("<cha>"))
				    			{
				    				cond = 1;
				    				k = k + 1;
				    				aSeq[n_uti][n_actions].keyChar = Integer.valueOf(info[k]);
				    				//System.out.println("    keyChar " + info[k]);
				    			}
				    			
			    			}
			    			// fin de la touche
			    			
			    			// r�cup�re les coordonn�es
			    			k = d_act;
			    			cond = 0;
			    			while(cond==0 && k<f_act && k+1<n_line)
			    			{
			    				k = k + 1;
				    			if(info[k].equals("<clk>"))
				    			{
				    				cond = 1;
				    				k = k + 1;
				    				aSeq[n_uti][n_actions].clic_i = Integer.valueOf(info[k]);
				    				//System.out.println("    clic i " + info[k]);
				    				k = k + 1;
				    				aSeq[n_uti][n_actions].clic_j = Integer.valueOf(info[k]);
				    				//System.out.println("    clic j " + info[k]);
				    			}
				    			
			    			}
			    			// fin des coordonn�es
			    			
			    			// r�cup�re le dialogue
			    			k = d_act;
			    			cond = 0;
			    			while(cond==0 && k<f_act && k+1<n_line)
			    			{
			    				k = k + 1;
				    			if(info[k].equals("<ltx>"))
				    			{
				    				cond = 1;
				    				k = k + 1;
				    				aSeq[n_uti][n_actions].l_text = Integer.valueOf(info[k]);
				    				//System.out.println("    longueur du dialogue : " + info[k]);
				    			}
				    			
			    			}
			    			k = k+2;
			    			for(int l=0;l<aSeq[n_uti][n_actions].l_text;l++)
			    			{
			    				k = k+1;
			    				aSeq[n_uti][n_actions].dialogue[l] = info[k].charAt(0);
			    				//System.out.println(    info[k]);
			    			}
			    			// fin dialogue
			    			
				    		
		    			} // action suivante
		    			
		    			lSeq[n_uti] = n_actions+1;
		    			
		    			
		    			if(k<f_act)
	    				{
		    				k = f_act;
	    				}
		    			else
		    			{
		    				k = k + 1;
		    			}
		    		} // fin de la recherche des actions (k<f_seq)
		    		n_uti = n_uti + 1;
		    		i = f_seq;
		    	} // fin de la s�quence
		    	
		    	i = i + 1;
		    	//System.out.println(i);
		    } // fin de la recherche dans la memoire
		}// fin du try
		
		
		
		/*
		 *  ==============================================================================
 			==============================================================================
 			==============================================================================

		 */
	
		d_seq = 0;
		f_seq = 0;
		d_act = 0;
		f_act = 0;
		i = 0;
		j = 0;
		k = 0;
		cond = 0;
		n_line = 0;
		compt = 0;
		n_actions = 0;
		n_uti = 0;
		
		
		// Chargement des s�quences d actions
		
				try(BufferedReader br = new BufferedReader(new FileReader("Config/configuration1.txt"))) 
				{
				    StringBuilder sb = new StringBuilder();
				    String line = br.readLine();
			
				    while (line != null) {
				        sb.append(line);
				        sb.append(System.lineSeparator());
				        line = br.readLine();
				        n_line = n_line + 1;
				    }
				    String everything = sb.toString();
				    
				    info = new String[n_line];
				    
				    BufferedReader reader  = new BufferedReader(new StringReader(everything));
				    while ((str = reader.readLine()) != null) 
				    {
			    	   if (str.length() > 0) 
			    		   {
			    		   		info[compt] = str;
			    		   		compt = compt + 1;
			    		   }
			    	}
				    
				    
				    i = 0;
				    while(i<n_line && n_uti<Main.n_seq1) // recherche dans la memoire
				    {
				    	
				    	if(info[i].equals("<seq>")) // debut s�quence
				    	{
				    		d_seq = i;
				    		j = d_seq;
				    		f_seq = j;
				    		while(i<n_line && j<n_line && !info[j].equals("</seq>")) // recherche la fin de la s�quence
				    		{
				    			j = j + 1;
				    			f_seq = j;
				    		}
				    		
				    		// cr�� une nouvelle s�quence
				    		n_actions = -1;
				    		k = d_seq;
				    		cond = 0;
				    		while(cond==0 && k<f_seq && k+1<n_line)
				    		{
				    			k = k + 1;
				    			if(info[k].equals("<nms>"))
				    			{
				    				cond = 1;
				    				k = k + 1;
				    				nameSeq1[n_uti] = info[k];
				    				//System.out.println("S�quence d action " + n_uti + " : " + info[k]);
				    			}
				    		}
				    		
				    		// r�cup�re les dialogues propos�s
				    		k = d_seq;
				    		cond = 0;
				    		while(cond==0 && k<f_seq && k+1<n_line)
				    		{
				    			k = k + 1;
				    			if(info[k].equals("<dg0>"))
				    			{
				    				cond = 1;
				    				k = k + 1;
				    				dialogues1[n_uti][0] = info[k];
				    				//System.out.println("  Dialogue 0 " + n_uti + " : " + info[k]);
				    			}
				    		}
				    		k = d_seq;
				    		cond = 0;
				    		while(cond==0 && k<f_seq && k+1<n_line)
				    		{
				    			k = k + 1;
				    			if(info[k].equals("<dg1>"))
				    			{
				    				cond = 1;
				    				k = k + 1;
				    				dialogues1[n_uti][1] = info[k];
				    				//System.out.println("  Dialogue 1 " + n_uti + " : " + info[k]);
				    			}
				    		}
				    		k = d_seq;
				    		cond = 0;
				    		while(cond==0 && k<f_seq && k+1<n_line)
				    		{
				    			k = k + 1;
				    			if(info[k].equals("<dg2>"))
				    			{
				    				cond = 1;
				    				k = k + 1;
				    				dialogues1[n_uti][2] = info[k];
				    				//System.out.println("  Dialogue 2 " + n_uti + " : " + info[k]);
				    			}
				    		}
				    		
				    		// r�cup�re la condition
			    			k = d_seq;
			    			cond = 0;
			    			while(cond==0 && k<f_seq && k+1<n_line)
			    			{
			    				k = k + 1;
				    			if(info[k].equals("<cnd>"))
				    			{
				    				cond = 1;
				    				k = k + 1;
				    				conds[n_uti] = Integer.valueOf(info[k]);
				    				//System.out.println("    Code condition " + info[k]);
				    			}
				    			
			    			}
			    			// fin de la condition
			    			
			    			// r�cup�re le seuil de condition
			    			k = d_seq;
			    			cond = 0;
			    			while(cond==0 && k<f_seq && k+1<n_line)
			    			{
			    				k = k + 1;
				    			if(info[k].equals("<thc>"))
				    			{
				    				cond = 1;
				    				k = k + 1;
				    				th_conds[n_uti] = Integer.valueOf(info[k]);
				    				//System.out.println("    Seuil condition " + info[k]);
				    			}
				    			
			    			}
			    			// fin du seuil de condition
			    			
			    			// r�cup�re le texte de condition
			    			k = d_seq;
			    			cond = 0;
			    			while(cond==0 && k<f_seq && k+1<n_line)
			    			{
			    				k = k + 1;
				    			if(info[k].equals("<txc>"))
				    			{
				    				cond = 1;
				    				k = k + 1;
				    				str_conds[n_uti] = info[k];
				    				//System.out.println("    Texte condition " + info[k]);
				    			}
				    			
			    			}
			    			// fin du texte de condition
				    		
				    		// r�cup�re les actions
				    		
				    		k = d_seq;
				    		while(k<f_seq) // cherche dans la s�quence
				    		{
				    			if(info[k].equals("<act>"))
				    			{
				    				d_act = k;
				    				j = d_act;
						    		f_act = j;
						    		while(j<f_seq && !info[j].equals("</act>")) // recherche la fin de l'action
						    		{
						    			j = j + 1;
						    			f_act = j;
						    		}
						    		
						    		
					    			// cr�� une nouvelle action
					    			k = d_act;
					    			cond = 0;
					    			while(cond==0 && k<f_act)
					    			{
					    				k = k + 1;
						    			if(info[k].equals("<nma>"))
						    			{
						    				cond = 1;
						    				k = k + 1;
						    				if(k+3<n_line)
						    				{
						    					n_actions = n_actions + 1;
							    				aSeq1[n_uti][n_actions] = new Action(info[k], Integer.valueOf(info[k+3]));
							    				//System.out.println("  Action n" + n_actions + " " + info[k] + ", dure " +info[k+3] + "ms" );
						    				}
						    			}
					    			}
					    			// fin nom + temps
					    			
					    			// r�cup�re la cl�
					    			k = d_act;
					    			cond = 0;
					    			while(cond==0 && k<f_act && k+1<n_line)
					    			{
					    				k = k + 1;
						    			if(info[k].equals("<key>"))
						    			{
						    				cond = 1;
						    				k = k + 1;
						    				aSeq1[n_uti][n_actions].keyCode = Integer.valueOf(info[k]);
						    				//System.out.println("    keyCode " + info[k]);
						    			}
						    			
					    			}
					    			// fin de la cl�
					    			
					    			// r�cup�re la touche
					    			k = d_act;
					    			cond = 0;
					    			while(cond==0 && k<f_act && k+1<n_line)
					    			{
					    				k = k + 1;
						    			if(info[k].equals("<cha>"))
						    			{
						    				cond = 1;
						    				k = k + 1;
						    				aSeq1[n_uti][n_actions].keyChar = Integer.valueOf(info[k]);
						    				//System.out.println("    keyChar " + info[k]);
						    			}
						    			
					    			}
					    			// fin de la touche
					    			
					    			// r�cup�re les coordonn�es
					    			k = d_act;
					    			cond = 0;
					    			while(cond==0 && k<f_act && k+1<n_line)
					    			{
					    				k = k + 1;
						    			if(info[k].equals("<clk>"))
						    			{
						    				cond = 1;
						    				k = k + 1;
						    				aSeq1[n_uti][n_actions].clic_i = Integer.valueOf(info[k]);
						    				//System.out.println("    clic i " + info[k]);
						    				k = k + 1;
						    				aSeq1[n_uti][n_actions].clic_j = Integer.valueOf(info[k]);
						    				//System.out.println("    clic j " + info[k]);
						    			}
						    			
					    			}
					    			// fin des coordonn�es
					    			
					    			// r�cup�re le dialogue
					    			k = d_act;
					    			cond = 0;
					    			while(cond==0 && k<f_act && k+1<n_line)
					    			{
					    				k = k + 1;
						    			if(info[k].equals("<ltx>"))
						    			{
						    				cond = 1;
						    				k = k + 1;
						    				aSeq1[n_uti][n_actions].l_text = Integer.valueOf(info[k]);
						    				//System.out.println("    longueur du dialogue : " + info[k]);
						    			}
						    			
					    			}
					    			k = k+2;
					    			for(int l=0;l<aSeq1[n_uti][n_actions].l_text;l++)
					    			{
					    				k = k+1;
					    				aSeq1[n_uti][n_actions].dialogue[l] = info[k].charAt(0);
					    				//System.out.println(    info[k]);
					    			}
					    			// fin dialogue
					    			
						    		
				    			} // action suivante
				    			
				    			lSeq1[n_uti] = n_actions+1;
				    			
				    			
				    			if(k<f_act)
			    				{
				    				k = f_act;
			    				}
				    			else
				    			{
				    				k = k + 1;
				    			}
				    		} // fin de la recherche des actions (k<f_seq)
				    		n_uti = n_uti + 1;
				    		i = f_seq;
				    	} // fin de la s�quence
				    	
				    	i = i + 1;
				    	//System.out.println(i);
				    } // fin de la recherche dans la memoire
				}// fin du try
				
				
		/*
		 *  ==============================================================================
 			==============================================================================
 			==============================================================================

		 */
		
		
		// chargement des touches marche
		for(i=0;i<5;i++)
	    {
	    	marche[i] = 0;
	    	char_marche[i] = '0';
	    }
		try(BufferedReader br = new BufferedReader(new FileReader("Config/marche.txt"))) 
		{
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
	
		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		        n_line = n_line + 1;
		    }
		    String everything = sb.toString();
		    
		    info = new String[n_line];
		    compt = 0;
		    BufferedReader reader  = new BufferedReader(new StringReader(everything));
		    while ((str = reader.readLine()) != null) 
		    {
	    	   if (str.length() > 0) 
	    		   {
	    		   		//System.out.println(str);
	    		   		info[compt] = str;
	    		   		compt = compt + 1;
	    		   }
	    	}
		    for(i=0;i<Math.min(5,compt);i++)
		    {
		    	marche[i] = Integer.valueOf(info[i]);
		    }
		    for(i=5;i<Math.min(10,compt);i++)
		    {
		    	char_marche[i-5] = info[i].charAt(0);
		    }
		}// fin du try
		
		
		// chargement des options
				
				try(BufferedReader br = new BufferedReader(new FileReader("Config/Options.txt"))) 
				{
				    StringBuilder sb = new StringBuilder();
				    String line = br.readLine();
			
				    while (line != null) {
				        sb.append(line);
				        sb.append(System.lineSeparator());
				        line = br.readLine();
				        n_line = n_line + 1;
				    }
				    String everything = sb.toString();
				    
				    info = new String[n_line];
				    compt = 0;
				    BufferedReader reader  = new BufferedReader(new StringReader(everything));
				    while ((str = reader.readLine()) != null) 
				    {
			    	   if (str.length() > 0) 
			    		   {
			    		   		//System.out.println(str);
			    		   		info[compt] = str;
			    		   		compt = compt + 1;
			    		   }
			    	}
				    if(compt>0)
				    {
				    	langue = Integer.valueOf(info[0]);
				    }
				    if(compt>1)
				    {
				    	etalonnage = Integer.valueOf(info[1]);
				    }
				    if(compt>2)
				    {
				    	secret = Integer.valueOf(info[2]);
				    }
				   
				}// fin du try
		
		// chargement des conditions (images)
		try(BufferedReader br = new BufferedReader(new FileReader("Config/Conds.txt"))) 
		{
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
	
		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		        n_line = n_line + 1;
		    }
		    String everything = sb.toString();
		    
		    info = new String[n_line];
		    compt = 0;
		    BufferedReader reader  = new BufferedReader(new StringReader(everything));
		    while ((str = reader.readLine()) != null) 
		    {
	    	   if (str.length() > 0) 
	    		   {
	    		   		//System.out.println(str);
	    		   		info[compt] = str;
	    		   		compt = compt + 1;
	    		   }
	    	}
		    for(int ii=0;ii<compt;ii++)
		    {
		    	k = ii%3;
		    	j = ((ii-k)/3)%Main.dim_cond;
		    	i = ((ii-k-j*3)/(3*Main.dim_cond))%Main.dim_cond;
		    	ll = ((ii-k-j*3-i*Main.dim_cond)/(3*Main.dim_cond*Main.dim_cond))%(Main.n_seq1);
		    	
		    	Main.Im_Cond[ll][i][j][k] = Integer.valueOf(info[ii]);
		    	
		    }
		}// fin du try
		// chargement des conditions (coordonn�es)
				try(BufferedReader br = new BufferedReader(new FileReader("Config/Coor.txt"))) 
				{
				    StringBuilder sb = new StringBuilder();
				    String line = br.readLine();
			
				    while (line != null) {
				        sb.append(line);
				        sb.append(System.lineSeparator());
				        line = br.readLine();
				        n_line = n_line + 1;
				    }
				    String everything = sb.toString();
				    
				    info = new String[n_line];
				    compt = 0;
				    BufferedReader reader  = new BufferedReader(new StringReader(everything));
				    while ((str = reader.readLine()) != null) 
				    {
			    	   if (str.length() > 0) 
			    		   {
			    		   		//System.out.println(str);
			    		   		info[compt] = str;
			    		   		compt = compt + 1;
			    		   }
			    	}
				    for(int ii=0;ii<compt;ii++)
				    {
				    	j = ii%2;
				    	i = ((ii-j)/2)%(Main.n_seq1);
				    	Main.coor_Cond[i][j] = Integer.valueOf(info[ii]);
				    	
				    }
				    
				}// fin du try
	}
	
	public void sauvegarde() throws IOException
	{
		int i,j,k;
		String str_nom;
		BufferedWriter writer = new BufferedWriter(new FileWriter("Config/configuration.txt"));

		for(i=0;i<Main.n_seq;i++)
		{
			writer.write("<seq>");
			writer.newLine();
			writer.flush();
			
			writer.write("<nms>");
			writer.newLine();
			writer.flush();
			writer.write(nameSeq[i]);
			writer.newLine();
			writer.flush();
			writer.write("</nms>");
			writer.newLine();
			writer.flush();
			
			writer.write("<lse>");
			writer.newLine();
			writer.flush();
			writer.write(String.valueOf(lSeq[i]));
			writer.newLine();
			writer.flush();
			writer.write("</lse>");
			writer.newLine();
			writer.flush();
			
			writer.write("<dg0>");
			writer.newLine();
			writer.flush();
			writer.write(dialogues[i][0]);
			writer.newLine();
			writer.flush();
			writer.write("</dg0>");
			writer.newLine();
			writer.flush();
			
			writer.write("<dg1>");
			writer.newLine();
			writer.flush();
			writer.write(dialogues[i][1]);
			writer.newLine();
			writer.flush();
			writer.write("</dg1>");
			writer.newLine();
			writer.flush();
			
			writer.write("<dg2>");
			writer.newLine();
			writer.flush();
			writer.write(dialogues[i][2]);
			writer.newLine();
			writer.flush();
			writer.write("</dg2>");
			writer.newLine();
			writer.flush();
			
			for(int it=0;it<Main.n_seq1;it++)
			{
				str_nom = "<cd";
				str_nom = new StringBuilder().append(str_nom).append(String.valueOf(it)).toString();
				str_nom = new StringBuilder().append(str_nom).append(">").toString();
				
				writer.write(str_nom);
				writer.newLine();
				writer.flush();
				writer.write(String.valueOf(is_act[i][it]));
				writer.newLine();
				writer.flush();
				str_nom = "</cd";
				str_nom = new StringBuilder().append(str_nom).append(String.valueOf(it)).toString();
				str_nom = new StringBuilder().append(str_nom).append(">").toString();
				
				writer.write(str_nom);
				writer.newLine();
				writer.flush();
			}
			/*
			writer.write("<cd1>");
			writer.newLine();
			writer.flush();
			writer.write(String.valueOf(is_act[i][1]));
			writer.newLine();
			writer.flush();
			writer.write("</cd1>");
			writer.newLine();
			writer.flush();
			
			writer.write("<cd2>");
			writer.newLine();
			writer.flush();
			writer.write(String.valueOf(is_act[i][2]));
			writer.newLine();
			writer.flush();
			writer.write("</cd2>");
			writer.newLine();
			writer.flush();
			*/
			
		   	for(j=0;j<lSeq[i];j++)
			{
		 		  writer.write("<act>");
		          writer.newLine();
		          writer.flush();
		          
		          writer.write("<nma>");
		          writer.newLine();
		          writer.flush();
		          writer.write(aSeq[i][j].action);
		          writer.newLine();
		          writer.flush();
		          writer.write("</nma>");
		          writer.newLine();
		          writer.flush();
		          
		          writer.write("<tps>");
		          writer.newLine();
		          writer.flush();
		          writer.write(String.valueOf(aSeq[i][j].tps));
		          writer.newLine();
		          writer.flush();
		          writer.write("</tps>");
		          writer.newLine();
		          writer.flush();
		          
		          writer.write("<key>");
		          writer.newLine();
		          writer.flush();
		          writer.write(String.valueOf(aSeq[i][j].keyCode));
		          writer.newLine();
		          writer.flush();
		          writer.write("</key>");
		          writer.newLine();
		          writer.flush();
		          
		          writer.write("<cha>");
		          writer.newLine();
		          writer.flush();
		          writer.write(String.valueOf(aSeq[i][j].keyChar));
		          writer.newLine();
		          writer.flush();
		          writer.write("</cha>");
		          writer.newLine();
		          writer.flush();
		          
		          writer.write("<clk>");
		          writer.newLine();
		          writer.flush();
		          writer.write(String.valueOf(aSeq[i][j].clic_i));
		          writer.newLine();
		          writer.flush();
		          writer.write(String.valueOf(aSeq[i][j].clic_j));
		          writer.newLine();
		          writer.flush();
		          writer.write("</clk>");
		          writer.newLine();
		          writer.flush();
		          
		          writer.write("<ltx>");
		          writer.newLine();
		          writer.flush();
		          writer.write(String.valueOf(aSeq[i][j].l_text));
		          writer.newLine();
		          writer.flush();
		          writer.write("</ltx>");
		          writer.newLine();
		          writer.flush();
		          
		          writer.write("<txt>");
		          writer.newLine();
		          writer.flush();
		          for(k=0;k<aSeq[i][j].l_text;k++)
		          {
			          writer.write(String.valueOf(aSeq[i][j].dialogue[k]));
			          writer.newLine();
			          writer.flush();
		          }
		          writer.write("</txt>");
		          writer.newLine();
		          writer.flush();
		          
		          writer.write("</act>");
		          writer.newLine();
		          writer.flush();
			}
		   	writer.write("</seq>");
			writer.newLine();
			writer.flush();
		}
        writer.close();
	}
	
	public void sauvegarde1() throws IOException
	{
		int i,j,k;
		BufferedWriter writer = new BufferedWriter(new FileWriter("Config/configuration1.txt"));

		for(i=0;i<Main.n_seq1;i++)
		{
			writer.write("<seq>");
			writer.newLine();
			writer.flush();
			
			writer.write("<nms>");
			writer.newLine();
			writer.flush();
			writer.write(nameSeq1[i]);
			writer.newLine();
			writer.flush();
			writer.write("</nms>");
			writer.newLine();
			writer.flush();
			
			writer.write("<cnd>");
			writer.newLine();
			writer.flush();
			writer.write(String.valueOf(conds[i]));
			writer.newLine();
			writer.flush();
			writer.write("</cnd>");
			writer.newLine();
			writer.flush();
			
			writer.write("<thc>");
			writer.newLine();
			writer.flush();
			writer.write(String.valueOf(th_conds[i]));
			writer.newLine();
			writer.flush();
			writer.write("</thc>");
			writer.newLine();
			writer.flush();
			
			writer.write("<txc>");
			writer.newLine();
			writer.flush();
			writer.write(str_conds[i]);
			writer.newLine();
			writer.flush();
			writer.write("</txc>");
			writer.newLine();
			writer.flush();
			
			writer.write("<dg0>");
			writer.newLine();
			writer.flush();
			writer.write(dialogues1[i][0]);
			writer.newLine();
			writer.flush();
			writer.write("</dg0>");
			writer.newLine();
			writer.flush();
			
			writer.write("<dg1>");
			writer.newLine();
			writer.flush();
			writer.write(dialogues1[i][1]);
			writer.newLine();
			writer.flush();
			writer.write("</dg1>");
			writer.newLine();
			writer.flush();
			
			writer.write("<dg2>");
			writer.newLine();
			writer.flush();
			writer.write(dialogues1[i][2]);
			writer.newLine();
			writer.flush();
			writer.write("</dg2>");
			writer.newLine();
			writer.flush();
			
			writer.write("<lse>");
			writer.newLine();
			writer.flush();
			writer.write(String.valueOf(lSeq1[i]));
			writer.newLine();
			writer.flush();
			writer.write("</lse>");
			writer.newLine();
			writer.flush();
			
			
		   	for(j=0;j<lSeq1[i];j++)
			{
		 		  writer.write("<act>");
		          writer.newLine();
		          writer.flush();
		          
		          writer.write("<nma>");
		          writer.newLine();
		          writer.flush();
		          writer.write(aSeq1[i][j].action);
		          writer.newLine();
		          writer.flush();
		          writer.write("</nma>");
		          writer.newLine();
		          writer.flush();
		          
		          writer.write("<tps>");
		          writer.newLine();
		          writer.flush();
		          writer.write(String.valueOf(aSeq1[i][j].tps));
		          writer.newLine();
		          writer.flush();
		          writer.write("</tps>");
		          writer.newLine();
		          writer.flush();
		          
		          writer.write("<key>");
		          writer.newLine();
		          writer.flush();
		          writer.write(String.valueOf(aSeq1[i][j].keyCode));
		          writer.newLine();
		          writer.flush();
		          writer.write("</key>");
		          writer.newLine();
		          writer.flush();
		          
		          writer.write("<cha>");
		          writer.newLine();
		          writer.flush();
		          writer.write(String.valueOf(aSeq1[i][j].keyChar));
		          writer.newLine();
		          writer.flush();
		          writer.write("</cha>");
		          writer.newLine();
		          writer.flush();
		          
		          writer.write("<clk>");
		          writer.newLine();
		          writer.flush();
		          writer.write(String.valueOf(aSeq1[i][j].clic_i));
		          writer.newLine();
		          writer.flush();
		          writer.write(String.valueOf(aSeq1[i][j].clic_j));
		          writer.newLine();
		          writer.flush();
		          writer.write("</clk>");
		          writer.newLine();
		          writer.flush();
		          
		          writer.write("<ltx>");
		          writer.newLine();
		          writer.flush();
		          writer.write(String.valueOf(aSeq1[i][j].l_text));
		          writer.newLine();
		          writer.flush();
		          writer.write("</ltx>");
		          writer.newLine();
		          writer.flush();
		          
		          writer.write("<txt>");
		          writer.newLine();
		          writer.flush();
		          for(k=0;k<aSeq1[i][j].l_text;k++)
		          {
			          writer.write(String.valueOf(aSeq1[i][j].dialogue[k]));
			          writer.newLine();
			          writer.flush();
		          }
		          writer.write("</txt>");
		          writer.newLine();
		          writer.flush();
		          
		          writer.write("</act>");
		          writer.newLine();
		          writer.flush();
			}
		   	writer.write("</seq>");
			writer.newLine();
			writer.flush();
		}
        writer.close();
	}
	
	public void sauvegardeM() throws IOException
	{
		int i;
		BufferedWriter writer = new BufferedWriter(new FileWriter("Config/marche.txt"));

		for(i=0;i<5;i++)
		{
			writer.write(String.valueOf(marche[i]));
			writer.newLine();
			writer.flush();
		}
		for(i=0;i<5;i++)
		{
			writer.write(String.valueOf(char_marche[i]));
			writer.newLine();
			writer.flush();
		}
		writer.close();
	}
	
	public void sauvegardeO() throws IOException
	{
		BufferedWriter writer = new BufferedWriter(new FileWriter("Config/Options.txt"));

		
		writer.write(String.valueOf(langue));
		writer.newLine();
		writer.flush();
		
		writer.write(String.valueOf(etalonnage));
		writer.newLine();
		writer.flush();
		
		writer.write(String.valueOf(secret));
		writer.newLine();
		writer.flush();
		
		
		writer.close();
	}
	
	public void sauvegardeCond() throws IOException
	{
		int i,j,k,l;
		BufferedWriter writer = new BufferedWriter(new FileWriter("Config/Conds.txt"));

		for(l=0;l<Main.n_seq1;l++)
		{
			for(i=0;i<Main.dim_cond;i++)
			{
				for(j=0;j<Main.dim_cond;j++)
				{
					for(k=0;k<3;k++)
					{
						writer.write(String.valueOf(Main.Im_Cond[l][i][j][k]));
						writer.newLine();
						writer.flush();
					}
				}
			}
		}
		writer.close();
		
		writer = new BufferedWriter(new FileWriter("Config/Coor.txt"));

		for(l=0;l<Main.n_seq1;l++)
		{
			writer.write(String.valueOf(Main.coor_Cond[l][0]));
			writer.newLine();
			writer.flush();
			writer.write(String.valueOf(Main.coor_Cond[l][1]));
			writer.newLine();
			writer.flush();
		}
		writer.close();
	}
}

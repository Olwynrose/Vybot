
public class Learning {
	int[][][][][] data_learn;
	int n_learn;	// nombre d'images pour l'apprentissage 
	int l_learn;	// largeur des images pour l'apprentissage
	int rapp_learn;		// rapport entre la taille réelle et la taille des images d'apprentissage
	int rap_true;	// inverse de la proportion d'images positives
	
	int n_arbres;
	Decision[] foret;
	
	
	// mov
	int th_mot;
	boolean sens_mot;
	int conf_mot;
	
	// moy
	int[] c_moy;
	int th_moy;
	int conf_moy;
	
	// col
	int[] c_col;
	float d_col;
	float th_col;
	int conf_col;
	
	public Learning()
	{
		data_learn = null;
		n_learn = 0;
		l_learn = 0;
		rapp_learn = 4;
		n_arbres = 25;
		rap_true = 2;
		foret = new Decision[n_arbres];
	}
	public void learn(int[][][][][] data_learn_in)
	{
		int cond_mot = 0;
		int cond_moy = 0;
		
		n_learn = data_learn_in.length;
		l_learn = data_learn_in[0][0].length;
		data_learn = new int[n_learn][2][l_learn][l_learn][3];
		double a;
		double s1 = 0.001, s2 = 0.60;
		for(int n=0;n<n_learn;n++)
		{
			for(int t=0;t<2;t++)
			{
				for(int i=0;i<l_learn;i++)
				{
					for(int j=0;j<l_learn;j++)
					{
						for(int k=0;k<3;k++)
						{
							data_learn[n][t][i][j][k] = data_learn_in[n][t][i][j][k];
						}
					}
				}
			}
		}
		
		
		mot();
		moy();
		col();
		
		for(int n=0;n<n_arbres;n++)
		{
			cond_mot = 0;
			cond_moy = 0;
			
			// première couche
			foret[n] = new Decision();
			foret[n].l_learn = l_learn;
			a = Math.random();
			if(a<s1)
			{
				cond_moy = 1;
				//System.out.println(n);
				//System.out.println("couche 3");
				//System.out.println("moy");
				
				foret[n].neud = 1;
				foret[n].c_moy = new int[3];
				foret[n].c_moy[0] = c_moy[0];
				foret[n].c_moy[1] = c_moy[1];
				foret[n].c_moy[2] = c_moy[2];
				foret[n].th_moy = th_moy;
				foret[n].conf_moy = conf_moy;
			}
			if(a>=s1 && a<s2)
			{
				col();
				//System.out.println(n);
				//System.out.println("couche 1");
				//System.out.println(c_col[0]+ " " + c_col[1]+ " " + c_col[2]);
				
				foret[n].neud = 2;
				foret[n].c_col = new int[3];
				foret[n].c_col[0] = c_col[0];
				foret[n].c_col[1] = c_col[1];
				foret[n].c_col[2] = c_col[2];
				foret[n].th_col = th_col;
				foret[n].d_col = d_col;
				foret[n].conf_col = conf_col;
			}
			if(a>=s2)
			{
				cond_mot = 1;
				//System.out.println(n);
				//System.out.println("couche 1");
				//System.out.println("mot");
				
				foret[n].neud = 3;
				foret[n].th_mot = th_mot;
				foret[n].sens_mot = sens_mot;
				foret[n].conf_mot = conf_mot;
			}
			
			// deuxième couche
			foret[n].choix0 = new Decision();
			foret[n].choix0.neud = 0;
			foret[n].choix0.classe = 0;
			foret[n].choix1 = new Decision();
			foret[n].choix1.l_learn = l_learn;
			a = Math.random();
			if(cond_moy==1 && cond_mot==0)
			{
				a = (a*(1-s1)+s1);
			}
			if(cond_moy==1 && cond_mot==1)
			{
				a = (a*(1-s1-s2)+s1);
			}
			if(cond_moy==0 && cond_mot==1)
			{
				a = a*s2;
			}
			if(a<s1)
			{
				cond_moy = 1;
				//System.out.println(n);
				//System.out.println("couche 3");
				//System.out.println("moy");
				
				foret[n].choix1.neud = 1;
				foret[n].choix1.c_moy = new int[3];
				foret[n].choix1.c_moy[0] = c_moy[0];
				foret[n].choix1.c_moy[1] = c_moy[1];
				foret[n].choix1.c_moy[2] = c_moy[2];
				foret[n].choix1.th_moy = th_moy;
				foret[n].choix1.conf_moy = conf_moy;
			}
			if(a>=s1 && a<s2)
			{
				col();
				//System.out.println(n);
				//System.out.println("couche 2");
				//System.out.println(c_col[0]+ " " + c_col[1]+ " " + c_col[2]);
				
				foret[n].choix1.neud = 2;
				foret[n].choix1.c_col = new int[3];
				foret[n].choix1.c_col[0] = c_col[0];
				foret[n].choix1.c_col[1] = c_col[1];
				foret[n].choix1.c_col[2] = c_col[2];
				foret[n].choix1.th_col = th_col;
				foret[n].choix1.d_col = d_col;
				foret[n].choix1.conf_col = conf_col;
			}
			if(a>=s2)
			{
				cond_mot = 1;
				//System.out.println(n);
				//System.out.println("couche 2");
				//System.out.println("mot");
				
				foret[n].choix1.neud = 3;
				foret[n].choix1.th_mot = th_mot;
				foret[n].choix1.sens_mot = sens_mot;
				foret[n].choix1.conf_mot = conf_mot;
			}
			
			// troisième couche
			foret[n].choix1.choix0 = new Decision();
			foret[n].choix1.choix0.neud = 0;
			foret[n].choix1.choix0.classe = 0;
			foret[n].choix1.choix1 = new Decision();
			foret[n].choix1.choix1.l_learn = l_learn;
			a = Math.random();
			if(cond_moy==1 && cond_mot==0)
			{
				a = (a*(1-s1)+s1);
			}
			if(cond_moy==1 && cond_mot==1)
			{
				a = (a*(1-s1-s2)+s1);
			}
			if(cond_moy==0 && cond_mot==1)
			{
				a = a*s2;
			}
			if(a<s1)
			{
				//System.out.println(n);
				//System.out.println("couche 3");
				//System.out.println("moy");
				
				foret[n].choix1.choix1.neud = 1;
				foret[n].choix1.choix1.c_moy = new int[3];
				foret[n].choix1.choix1.c_moy[0] = c_moy[0];
				foret[n].choix1.choix1.c_moy[1] = c_moy[1];
				foret[n].choix1.choix1.c_moy[2] = c_moy[2];
				foret[n].choix1.choix1.th_moy = th_moy;
				foret[n].choix1.choix1.conf_moy = conf_moy;
			}
			if(a>=s1 && a<s2)
			{
				col();
				//System.out.println(n);
				//System.out.println("couche 3");
				//System.out.println(c_col[0]+ " " + c_col[1]+ " " + c_col[2]);
				
				foret[n].choix1.choix1.neud = 2;
				foret[n].choix1.choix1.c_col = new int[3];
				foret[n].choix1.choix1.c_col[0] = c_col[0];
				foret[n].choix1.choix1.c_col[1] = c_col[1];
				foret[n].choix1.choix1.c_col[2] = c_col[2];
				foret[n].choix1.choix1.th_col = th_col;
				foret[n].choix1.choix1.d_col = d_col;
				foret[n].choix1.choix1.conf_col = conf_col;
			}
			if(a>=s2)
			{
				//System.out.println(n);
				//System.out.println("couche 3");
				//System.out.println("mot");
				
				foret[n].choix1.choix1.neud = 3;
				foret[n].choix1.choix1.th_mot = th_mot;
				foret[n].choix1.choix1.sens_mot = sens_mot;
				foret[n].choix1.choix1.conf_mot = conf_mot;
			}
			
			// quatrième couche
			foret[n].choix1.choix1.choix0 = new Decision();
			foret[n].choix1.choix1.choix0.neud = 0;
			foret[n].choix1.choix1.choix0.classe = 0;
			foret[n].choix1.choix1.choix1 = new Decision();
			foret[n].choix1.choix1.choix1.l_learn = l_learn;
			a = Math.random();
			if(cond_moy==1 && cond_mot==0)
			{
				a = (a*(1-s1)+s1);
			}
			if(cond_moy==1 && cond_mot==1)
			{
				a = (a*(1-s1-s2)+s1);
			}
			if(cond_moy==0 && cond_mot==1)
			{
				a = a*s2;
			}
			if(a<s1)
			{
				//System.out.println(n);
				//System.out.println("couche 4");
				//System.out.println("moy");
				
				foret[n].choix1.choix1.choix1.neud = 1;
				foret[n].choix1.choix1.choix1.c_moy = new int[3];
				foret[n].choix1.choix1.choix1.c_moy[0] = c_moy[0];
				foret[n].choix1.choix1.choix1.c_moy[1] = c_moy[1];
				foret[n].choix1.choix1.choix1.c_moy[2] = c_moy[2];
				foret[n].choix1.choix1.choix1.th_moy = th_moy;
				foret[n].choix1.choix1.choix1.conf_moy = conf_moy;
			}
			if(a>=s1 && a<s2)
			{
				col();
				//System.out.println(n);
				//System.out.println("couche 4");
				//System.out.println(c_col[0]+ " " + c_col[1]+ " " + c_col[2]);
				
				foret[n].choix1.choix1.choix1.neud = 2;
				foret[n].choix1.choix1.choix1.c_col = new int[3];
				foret[n].choix1.choix1.choix1.c_col[0] = c_col[0];
				foret[n].choix1.choix1.choix1.c_col[1] = c_col[1];
				foret[n].choix1.choix1.choix1.c_col[2] = c_col[2];
				foret[n].choix1.choix1.choix1.th_col = th_col;
				foret[n].choix1.choix1.choix1.d_col = d_col;
				foret[n].choix1.choix1.choix1.conf_col = conf_col;
			}
			if(a>=s2)
			{
				//System.out.println(n);
				//System.out.println("couche 4");
				//System.out.println("mot");
				
				foret[n].choix1.choix1.choix1.neud = 3;
				foret[n].choix1.choix1.choix1.th_mot = th_mot;
				foret[n].choix1.choix1.choix1.sens_mot = sens_mot;
				foret[n].choix1.choix1.choix1.conf_mot = conf_mot;
			}
			// feuilles de la dernière (4ème) couche
			foret[n].choix1.choix1.choix1.choix0 = new Decision();
			foret[n].choix1.choix1.choix1.choix0.neud = 0;
			foret[n].choix1.choix1.choix1.choix0.classe = 0;
			foret[n].choix1.choix1.choix1.choix1 = new Decision();
			foret[n].choix1.choix1.choix1.choix1.neud = 0;
			foret[n].choix1.choix1.choix1.choix1.classe = 1;
			
		} // fin de la boucle sur les arbres
		
	} // fin de l'apprentissage

	// ========================================
	private void mot() 
	{
		int l_min = l_learn/3;
		int l_max = l_learn*2/3;
		int[] vect_mov = new int[n_learn];

		for(int n=0;n<n_learn;n++)
		{
			vect_mov[n] = 0;
			for(int i=l_min;i<l_max;i++)
			{
				for(int j=l_min;j<l_max;j++)
				{
					for(int k=0;k<3;k++)
					{
						vect_mov[n] = vect_mov[n] + (data_learn[n][0][i][j][k]-data_learn[n][1][i][j][k])*(data_learn[n][0][i][j][k]-data_learn[n][1][i][j][k]);
					}
				}
			}
			//System.out.println(vect_mov[n]);
		}

		
		th_mot = l_learn*l_learn;
		
		conf_mot = 0;
		for(int n=0;n<n_learn/rap_true;n++)
		{
				if(vect_mov[n]<th_mot)
				{
					conf_mot = conf_mot + (rap_true-1);
				}
		}
		if(conf_mot<n_learn/3)
		{
			sens_mot = true;
		}
		else
		{
			sens_mot = false;
		}
		for(int n=n_learn/rap_true;n<n_learn;n++)
		{
				if(vect_mov[n]>th_mot)
				{
					conf_mot = conf_mot + 1;
				}
		}
		if(sens_mot == false)
		{
			conf_mot = (2*n_learn*(rap_true-1))/rap_true - conf_mot; // score total : (2*n_learn*(rap_true-1))/rap_true (poid rap_true-1 pour les positifs)
		}
		/*System.out.println(th_mot);
		System.out.println(conf_mot);
		System.out.println(sens_mot);
		System.out.println(" ");*/
	}
	// ============================= 
	private void moy()
	{
		int[][] vect_col = new int[n_learn][3];
		c_moy = new int[3];
		int var = 0;
		int l_min = l_learn/4;
		int l_max = l_learn*3/4;
		for(int n=0;n<n_learn/rap_true;n++)
		{
			vect_col[n][0] = 0;
			vect_col[n][1] = 0;
			vect_col[n][2] = 0;
			for(int i=l_min;i<l_max;i++)
			{
				for(int j=l_min;j<l_max;j++)
				{
					for(int k=0;k<3;k++)
					{
						vect_col[n][k] = vect_col[n][k] + data_learn[n][0][i][j][k]+data_learn[n][1][i][j][k];
					}
				}
			}
			vect_col[n][0] = vect_col[n][0]/((l_max-l_min)*(l_max-l_min)*2);
			vect_col[n][1] = vect_col[n][1]/((l_max-l_min)*(l_max-l_min)*2);
			vect_col[n][2] = vect_col[n][2]/((l_max-l_min)*(l_max-l_min)*2);
			if(n<n_learn)
			{
				c_moy[0] = c_moy[0] + vect_col[n][0];
				c_moy[1] = c_moy[1] + vect_col[n][1];
				c_moy[2] = c_moy[2] + vect_col[n][2];
			}
			
			//System.out.println(vect_col[n][0]+ " " + vect_col[n][1]+ " " + vect_col[n][2]);
		}
		c_moy[0] = c_moy[0]*rap_true/n_learn;
		c_moy[1] = c_moy[1]*rap_true/n_learn;
		c_moy[2] = c_moy[2]*rap_true/n_learn;
		/*for(int n=0;n<n_learn;n++)
		{
			var = var + (col[0]-vect_col[n][0])*(col[0]-vect_col[n][0]);
			var = var + (col[1]-vect_col[n][1])*(col[1]-vect_col[n][1]);
			var = var + (col[2]-vect_col[n][2])*(col[2]-vect_col[n][2]);
		}
		var = var*2/n_learn;
		*/
		int buf_conf = (2*n_learn*(rap_true-1))/rap_true;
		int buf_dist;
		for(int i= 0;i<255;i++)
		{
			conf_moy = 0;
			buf_dist = 3*i*i;
			for(int n=0;n<n_learn;n++)
			{
				var = (c_moy[0]-vect_col[n][0])*(c_moy[0]-vect_col[n][0]);
				var = var + (c_moy[1]-vect_col[n][1])*(c_moy[1]-vect_col[n][1]);
				var = var + (c_moy[2]-vect_col[n][2])*(c_moy[2]-vect_col[n][2]);
				if(n<n_learn/rap_true && var>buf_dist)
				{
					conf_moy = conf_moy + (rap_true-1);
				}
				if(n>=n_learn/rap_true && var<buf_dist)
				{
					conf_moy = conf_moy + 1;
				}
			}
			if(buf_conf>conf_moy)
			{
				buf_conf = conf_moy;
				th_moy = buf_dist;
			}
		}
		conf_moy = buf_conf;
		//System.out.println(th_moy);
		//System.out.println(conf_moy);
		
	} // fin moy
	
	private void col()
	{
		int[] buf_c = new int[3];
		float buf_d = 0;
		float buf_th = 0;
		int buf_conf = n_learn;
		
		for(int i=0;i<4;i++)
		{
			// prend une couleur aléatoire 
			int l_min = l_learn/3;
			int l_max = l_learn*2/3;
			int buf_i = l_min + (int) ( ((float)(l_max-l_min) )*(float)Math.random() );
			int buf_j = l_min + (int) ( ((float)(l_max-l_min) )*(float)Math.random() );
			int buf_l =  (int) ( ((float)n_learn/rap_true )*(float)Math.random() );
			
			c_col = new int[3];
			if(Math.random()>0.5)
			{
				c_col[0] = data_learn[buf_l][0][buf_i][buf_j][0];
				c_col[1] = data_learn[buf_l][0][buf_i][buf_j][1];
				c_col[2] = data_learn[buf_l][0][buf_i][buf_j][2];
			}
			else
			{
				c_col[0] = data_learn[buf_l][0][buf_i][buf_j][0];
				c_col[1] = data_learn[buf_l][0][buf_i][buf_j][1];
				c_col[2] = data_learn[buf_l][0][buf_i][buf_j][2];
			}
			
			d_col = optim(5, 75, (float)0.5);
			
			if(buf_conf>conf_col)
			{
				buf_c[0] = c_col[0];
				buf_c[1] = c_col[1];
				buf_c[2] = c_col[2];
				buf_d = d_col;
				buf_th = th_col;
				/*
				System.out.println(" ");
				System.out.println(d_col);
				System.out.println(conf_col);
				System.out.println(c_col[0] + " " + c_col[1] + " " + c_col[2]);
				*/
			}
			
		}
		c_col[0] = buf_c[0];
		c_col[1] = buf_c[1];
		c_col[2] = buf_c[2];
		d_col =buf_d;
		th_col = buf_th;
		
		
	} // fin col
	
	
	float optim(float aa, float cc, float ep)
	{
	    float r;
	    float a=aa;
	    float b=(float)0.5*(aa+cc);
	    float c=cc;
	    float d;
	    float bufV;
	    float bV;

	    while(c-a>ep)
	    {
	        d=c-b;
	        if(b-a>c-b)
	        {
	            d=b-a;
	        }
	        bV = err();
	        if(b-a>c-b)
	        {
	        	d_col=(float) (b-0.3917*d);
	            bufV=err();
	            if(bufV<bV)//<
	            {
	                b=d_col;
	            }
	            else
	            {
	                a=d_col;
	            }
	        }
	         else
	         {
	        	 d_col=(float) (b+0.3917*d);
	             bufV=err();
	             if(bufV<bV)//<
	             {
	                 b=d_col;
	             }
	             else
	             {
	                 c=d_col;
	             }
	         }
	    }
	    r=b;

	    return r;
	} // fin optim
	
	private float err()
	{
		// calcul l'erreur de classification pour une fenêtre d2_col donnée
		int l_min = l_learn/4;
		int l_max = l_learn*3/4;
		float[] sc = new float[n_learn];
		float m1 = 0;
		float m2 = 0;
		float s1 = 0;
		float s2 = 0;
		int compt = 0;
		int d2 = 0;
		
		for(int n=0;n<n_learn;n++)
		{
			sc[n] = 0;
			for(int t=0;t<2;t++)
			{
				for(int i=l_min;i<l_max;i++)
				{
					for(int j=l_min;j<l_max;j++)
					{
						d2 = 0;
						for(int k=0;k<3;k++)
						{
							d2 = d2 + (c_col[k]-data_learn[n][t][i][j][k])*(c_col[k]-data_learn[n][t][i][j][k]);
						}
						sc[n] = sc[n] + (float) (Math.exp(-((float) d2 )/(d_col*d_col) )); 
					}
				}
			}
			// moyenne des scores pour les deux classes
			if(n_learn>rap_true*n)
			{
				m1 = m1 + sc[n];
			}
			else
			{
				m2 = m2 + sc[n];
			}
		}
		m1 = ((float)rap_true)*m1/((float) n_learn);
		m2 = ((float)rap_true)*m2/((float) (n_learn*(rap_true-1)));
		
		// variance des scores pour les deux classes
		for(int n=0;n<n_learn;n++)
		{
			if(n_learn>rap_true*n)
			{
				s1 = s1 + (m1-sc[n])*(m1-sc[n]);
			}
			else
			{
				s2 = s2 + (m2-sc[n])*(m2-sc[n]);
			}
		}
		s1 = (float) Math.sqrt(s1);
		s2 = (float) Math.sqrt(s2);
		th_col = (m1*s2 + m2*s1)/(s1 + s2);
		/*for(int n=0;n<n_learn;n++)
		{
			System.out.print(sc[n]+ " ");
		}*/
		//System.out.println(" ");
		//System.out.println(m1 + " " + m2 + " " + s1 + " " + s2 + " " + th_col);
		
		// compte le nombre de fausses classifications
		for(int n=0;n<n_learn;n++)
		{
			if(n_learn>rap_true*n)
			{
				if(sc[n]<th_col)
				{
					// pénalise les faut négatifs
					compt = compt + (rap_true-1);
				}
			}
			else
			{
				if(sc[n]>th_col)
				{
					// pénalise les faux positifs
					compt = compt + 1;
				}
			}
		}
		//System.out.println(compt);
		return (float) compt;
	}
}

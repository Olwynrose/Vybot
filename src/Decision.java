
public class Decision {
	
	int classe;		// si neud = 0 : classe de la feuille (0 : rien ou 1 : detection)
	int neud;		// si neud = 0 : type de test
	// 0 : fin
	// 1 : moy
	// 2 : col
	// 3 : mot
	
	int l_learn;
	
	// mot
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
	
	Decision choix0;	// non détection
	Decision choix1;	// détection
	
	public Decision()
	{
		
	}
	
	public Decision test(int[][][] Im1, int[][][] Im2)
	{
		
		switch(neud)
		{
			case 0:
			{
				return null;
			}
			case 1:
			{
				if(moy(Im1, Im2))
				{
					return choix1;
				}
				else
				{
					return choix0;
				}
			}
			case 2:
			{
				if(col(Im1, Im2))
				{
					return choix1;
				}
				else
				{
					return choix0;
				}
			}
			case 3:
			{
				if(mot(Im1, Im2))
				{
					return choix1;
				}
				else
				{
					return choix0;
				}
			}
			
		}
		
		return null;
	}

	public boolean moy(int[][][] Im1, int[][][] Im2)
	{
		int[] vect_col = new int[3];
		int var;
		int l_min = l_learn/3;
		int l_max = l_learn*2/3;
		vect_col[0] = 0;
		vect_col[1] = 0;
		vect_col[2] = 0;
		
		for(int i = l_min; i < l_max; i++)
		{
			for(int j = l_min; j < l_max; j++) 
			{
				for(int band = 0; band < 3; band++)
				{
					vect_col[band] = vect_col[band] + (Im1[i][j][band]+Im2[i][j][band]);
				}
			}
		}
		vect_col[0] = vect_col[0]/((l_max-l_min)*(l_max-l_min)*2);
		vect_col[1] = vect_col[1]/((l_max-l_min)*(l_max-l_min)*2);
		vect_col[2] = vect_col[2]/((l_max-l_min)*(l_max-l_min)*2);
		var = (c_moy[0]-vect_col[0])*(c_moy[0]-vect_col[0]);
		var = var + (c_moy[1]-vect_col[1])*(c_moy[1]-vect_col[1]);
		var = var + (c_moy[2]-vect_col[2])*(c_moy[2]-vect_col[2]);
		
		if(var<th_moy)
		{
			return true;
		}
		return false;
	} // fin moy
	
	public boolean col(int[][][] Im1, int[][][] Im2)
	{
			int l_min = l_learn/4;
			int l_max = l_learn*3/4;
			float sc;
			int d2 = 0;
			
			sc = 0;
			for(int i = l_min; i < l_max; i++)
			{
				for(int j = l_min; j <l_max; j++) 
				{
					d2 = 0;
					for(int band = 0; band < 3; band++)
					{
						d2 = d2 + (Im1[i][j][band]-c_col[band])*(Im1[i][j][band]-c_col[band]);
					}
					sc = sc + (float) (Math.exp(-((float)d2)/(d_col*d_col) ) ); 
					d2 = 0;
					for(int band = 0; band < 3; band++)
					{
						d2 = d2 + (Im2[i][j][band]-c_col[band])*(Im2[i][j][band]-c_col[band]);
					}
					sc = sc + (float) (Math.exp(-((float)d2)/(d_col*d_col) ) );
				}
			}
			
			//System.out.println(c_col[0] + " " + c_col[1] + " " + c_col[2]);
			//System.out.println(sc + " " + th_col);
			if(sc>th_col)
			{
				return true;
			}
			return false;
				
	} // fin col
	
	
	public boolean mot(int[][][] Im1, int[][][] Im2)
	{
		
		int l_min = l_learn/3;
		int l_max = l_learn*2/3;
		int mov;

		// cherche le mouvement sur chaqun des patchs
		mov = 0;
		for(int i = l_min; i < l_max; i++)
		{
			for(int j = l_min; j < l_max; j++) 
			{
				for(int band = 0; band < 3; band++)
				{
					mov = mov + (Im1[i][j][band]-Im2[i][j][band])*(Im1[i][j][band]-Im2[i][j][band]);
				}
			}
		}
		//System.out.println( rl*rc*ii +" " +rl*rc*jj+ " " +mov);
		if((mov>th_mot && sens_mot) || (mov<th_mot && sens_mot==false))
		{
			return true;	
		}
		return false;
	}	// fin mot
	
	
}

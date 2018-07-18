
public class Action {
	
	String action;
	int tps;
	int keyCode;
	int keyChar;
	char[] dialogue;
	int l_text;
	int clic_i;
	int clic_j;
	
	public Action(String actionIn, int tpsIn)
	{
		action = actionIn;
		tps = tpsIn;
		keyCode = 0;
		keyChar = '0';
		dialogue = new char[140];
		l_text = 0; 
		clic_i = 0;
		clic_j = 0;
	}
	
}

package game4InLine;

import java.util.Arrays;


import java.util.List;

public abstract class GameMode {
	
	static List<GameMode> modes = Arrays.asList( 
			new AMode(), 
			new BMode(),
			new CMode());

	public static List<Character> modesChosen = Arrays.asList('A', 'B', 'C');
	
	public static GameMode getInstance(int index) {
        return modes.get(index);
    }
		
	public abstract void checkWin(Linea line, char player);


}
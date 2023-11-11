package game4InLine;

public class BMode extends GameMode{

	public BMode() {
		super();
	}
	
	public void checkWin(Linea linea, char player) {
		linea.playBMode(player);
	}



}

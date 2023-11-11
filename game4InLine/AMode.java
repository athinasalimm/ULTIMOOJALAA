package game4InLine;


public class AMode extends GameMode{

	public AMode() {
		super();
	}
	
	public void checkWin(Linea line, char player) {
		line.playAMode(player);
	}

}


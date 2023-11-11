package game4InLine;

public class CMode extends GameMode{

	public CMode() {
		super();
	}


	public void checkWin(Linea linea, char player) {
		linea.playCMode(player);
	}


}

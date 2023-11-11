package game4InLine;

public class BlueTurn extends State {

	public BlueTurn() {
	}

	public void moveBlue(int chosenColumn, Linea line) {
		line.placeToken(chosenColumn, 'O');
	}
	
	public String condition() {
		return "Blue is playing";
	}
	
	public void next(Linea line) {
		line.setState(new RedTurn());
		line.findDraw();  //solo cuando se llena
		line.getMode().checkWin(line, 'O');
	}

	public boolean finished() {
		return false;
	}

}

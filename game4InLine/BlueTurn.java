package game4InLine;

public class BlueTurn extends State {

	public BlueTurn() {
	}

	public void moveBlue(int chosenColumn, Linea line) {
		line.placeToken(chosenColumn, 'O');
	}
	
	public String condition() {
		return "It's blue's turn";
	}
	
	public void next(Linea line) {
		line.setState(new RedTurn());
		line.findDraw(); 
		line.getMode().checkWin(line, 'O');
	}

	public boolean finished() {
		return false;
	}

}

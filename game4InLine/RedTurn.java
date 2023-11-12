package game4InLine;


public class RedTurn extends State {


	public RedTurn() {
	}

	public void moveRed(int chosenColumn, Linea line) {
		line.placeToken(chosenColumn, 'X'); 

	public String condition() { 
		return "It's red's turn";
	}
	
	public void next(Linea line) {
		line.setState(new BlueTurn());
		line.findDraw(); 
		line.getMode().checkWin(line, 'X');
	}
 
	

	public boolean finished() {
		return false;
	}

}

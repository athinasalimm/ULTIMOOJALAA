package game4InLine;


public class RedTurn extends State {


	public RedTurn() {
	}

	public void moveRed(int chosenColumn, Linea line) {
		line.placeToken(chosenColumn, 'X'); //juego es la linea q entro en el constructor de turno 
	}

	public String condition() { //caption NO
		return "rojo";
	}
	
	public void next(Linea line) {
		line.setState(new BlueTurn());
		line.findDraw(); //solo cuando se llena
		line.getMode().checkWin(line, 'X');
	}
 
	

	public boolean finished() {
		return false;
	}

}

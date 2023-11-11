package game4InLine;

public class GameIsOver extends State{

	private char player;
	protected String winner;
	
	public GameIsOver(char player) {
	this.player = player;
	}


	@Override
	protected String condition() {
		if (player == 'O') {
			winner = "The winner is Blue!";
		}
		else if(player == 'X') {
			winner = "The winner is Red!";
		}
		else {
				winner = "There was a Draw";
			}
		return winner;
	}
	
	public void next(Linea line) {
	}


	@Override
	protected boolean finished() {
		return true;
	}
	
	



}

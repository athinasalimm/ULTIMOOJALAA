package game4InLine;

public abstract class State {
	protected Linea line;
	
	protected abstract String condition();
	
	protected abstract boolean finished();
	
	public State() {}
	
	protected void moveRed(int chosenColumn, Linea line) {
		throw new Error("It's not Red's turn");
	}
	protected void moveBlue(int chosenColumn, Linea line) {
		throw new Error("It's not Blue's turn");
	}
	public void next(Linea line) {
		throw new Error("Game Over");
	}

}

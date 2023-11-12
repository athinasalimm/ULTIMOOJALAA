package game4InLine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Linea {
	public static String ColumnOutOfBounds = "The column chosen is out of bounds";
	public static String CannotAddToken = "You cannot add another token, the limit has been reached"; //TERMINA EL JUEGO
	public int height;
	private int width;
	private GameMode gameMode;
	private State state = new RedTurn();
	public  List<List> panel;

	public Linea(int width, int height, char mode) {
		this.height = height;
		this.width = width;
		panel = new ArrayList<>(width);
	        for (int i = 0; i < width; i++) {
	            panel.add(new ArrayList<Character>());
	        }
	    
	        
	    if (!GameMode.modesChosen.contains(mode)) { //ESTO LO INTENTE PONER EN GAMEMODE Y SE ROMPIO TODO NO SE QUE HACER
	    	//LO DEJO ACA?? QUEDA FEO
	        throw new IllegalArgumentException("Invalid game mode");
	    }
	   gameMode = GameMode.getInstance(GameMode.modesChosen.indexOf(mode));
	}
	

	public boolean finished() {
		return state.finished();
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	public GameMode getMode() {
		return gameMode;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getState() {
		return state.condition();
	}

	public void playRedAt(int chosenColumn) {
		state.moveRed(chosenColumn, this);
		state.next(this);
	}

	public void playBlueAt(int chosenColumn) {
		state.moveBlue(chosenColumn, this);
		state.next(this);
	}
	public void placeToken(int chosenColumn, char token) {
	    if (chosenColumn <= 0 || chosenColumn > width) {
	        throw new RuntimeException(ColumnOutOfBounds);
	    }
	    
	    if (panel.get(chosenColumn - 1).size() < height) {
	        panel.get(chosenColumn - 1).add(token);
	    } else if (panel.get(chosenColumn - 1).size() == height) {
	        throw new RuntimeException(CannotAddToken);
	    }

	}


	public char getCoordinate(int x, int y) {
		if (y < 0 || x < 0 || x >= width || y >= panel.get(x).size()) {
			return ' ';
		}
		return (char) panel.get(x).get(y);
	}

	public boolean verticalWin(char player) { 
		return IntStream.range(0, width - 1)
                .anyMatch(columna -> IntStream.range(0, panel.get(columna).size())
                .anyMatch(fila -> IntStream.range(0, 4)
                .allMatch(offset -> getCoordinate(columna, fila + offset) == player)
                )
                );
    }
	
	public boolean horizontalWin(char player) {
         return IntStream.range(0, height - 1)
                    .anyMatch(fila -> IntStream.range(0, width - 3)
                    .mapToObj(start -> IntStream.range(start, start + 4)
                    .mapToObj(columna -> getCoordinate(columna, fila) == player)
                    .reduce(Boolean::logicalAnd)
                    .orElse(false)
                    )
                    .reduce(Boolean::logicalOr)
                    .orElse(false)
                    );
	 }
	 
	public boolean diagonalWin(char player) {
	        return IntStream.range(0, height- 3)
	                .anyMatch(row -> IntStream.range(0, width - 3)
	                .anyMatch(column -> IntStream.range(0, 4)
	                .allMatch(i -> this.getCoordinate(row + i, column + i) == player)))
	                || IntStream.range(0, height - 3)
	                .anyMatch(row -> IntStream.range(3, width)
	                .anyMatch(column -> IntStream.range(0, 4)
	                .allMatch(i -> this.getCoordinate(row + i, column - i) == player)));
	    }


	public void playAMode(char player) {
			if ((horizontalWin(player) || verticalWin(player))) {
				state = new GameIsOver(player); 
			}
		}
		
	public void playBMode(char player) {
				if ((diagonalWin(player))) {
					state = new GameIsOver(player);
				}
		}
		
		
	public void playCMode(char player) {
			if (horizontalWin(player) || verticalWin(player) || diagonalWin(player)) {
				state = new GameIsOver(player);
			}		
	} 

	public void findDraw() {
	    if (panel.stream().allMatch(column -> column.size() == height)) {
	        state = new GameIsOver('-');
	    }
	}
	

		
	public char[] show() {
		    StringBuilder stringBuilder = new StringBuilder();
		    for (int j = height - 1; j >= 0; j--) {
		        for (List<Character> column : panel) {
		            if (j < column.size()) {
		                stringBuilder.append("| ").append(column.get(j)).append(" ");
		            } else {
		                stringBuilder.append("|   ");
		            }
		        }
		        stringBuilder.append("|\n");
		    }
		    stringBuilder.append("< ");
		    for (int i = 1; i <= width; i++) {
		        stringBuilder.append(i).append("   ");
		    }
		    stringBuilder.append(">\n");
		    stringBuilder.append( getState());
		    return stringBuilder.toString().toCharArray();
		}


}
	
	
	

	
	
	


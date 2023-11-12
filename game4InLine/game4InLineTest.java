package game4InLine;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class game4InLineTest {

	@Test
	public void test00SpaceCreatedWith4x4() {
		Linea linea = new Linea(4, 4, 'A');
		assertEquals(linea.getWidth(), 4);
		assertEquals(linea.getHeight(), 4);
	}
	@Test
	public void test01GameModeIsA() {
		GameModeIsCorrect('A', AMode.class);
	}
	@Test
	public void test02GameModeIsB() {
		GameModeIsCorrect('B', BMode.class);
}
	@Test
	public void test03GameModeIsC() {
		GameModeIsCorrect('C', CMode.class);
	}
	
	@Test
	public void test04RedAlwaysStartsGame() {
		Linea linea = new Linea (4, 4, 'C');
		assertEquals(linea.getState(), "It's red's turn");
	}
	
	@Test
public void test05RedAlwaysStartsAndAfterwardsBlue() {
		Linea linea = new Linea (4, 4, 'A');
		linea.playRedAt(3);
		assertEquals(linea.getState(), "It's blue's turn");
	}
	
	@Test
	public void test06RedAndBlueTokenArePlaced() {
		Linea linea = new Linea(4, 4, 'A');
		linea.playRedAt(3);
		linea.playBlueAt(3);
	}
	@Test
	public void test07ErrorTryingToAddMoreTokensThanPermited() {
		Linea linea = new Linea(4, 4, 'A');
		linea.playRedAt(3);
		linea.playBlueAt(3);
		linea.playRedAt(3);
		linea.playBlueAt(3);
		assertThrowsLike(() -> linea.playRedAt(3), Linea.CannotAddToken);
	}
	@Test
	public void test08ErrorTryingToAddTokenInInvalidColumn() {
		Linea linea = new Linea(4, 4, 'A');
		linea.playRedAt(3);
		linea.playBlueAt(3);
		linea.playRedAt(3);
		linea.playBlueAt(1);
		assertThrowsLike(() -> linea.playRedAt(8), Linea.ColumnOutOfBounds);
	}
	@Test
	public void test10ErrorTryingToAddInvalidGameMode() {
		assertThrowsLike(() -> new Linea(5, 4, 'e'), Linea.InvalidGameMode);
		}
	
	@Test
	public void test11EmptyPanelPrintsEmptyPanel() {
		Linea linea = new Linea(4, 4, 'A');
		String expected = 
		      "|   |   |   |   |\n" +
              "|   |   |   |   |\n" +
              "|   |   |   |   |\n" +
              "|   |   |   |   |\n" +
              "< 1   2   3   4   >\n" +
              "It's red's turn";
		    assertEquals(expected, new String(linea.show()));	
	}
	@Test
	public void test12PanelShowsRedToken() {
		Linea linea = new Linea(4, 4, 'A');
		linea.playRedAt(3);
		String expected = 
				"|   |   |   |   |\n" +
				"|   |   |   |   |\n" +
				"|   |   |   |   |\n" +
				"|   |   | X |   |\n" +
				"< 1   2   3   4   >\n" +
				"It's blue's turn";
		    assertEquals(expected, new String(linea.show()));	
	}
	@Test
	public void test13PanelShowsBlueWin() {
		Linea linea = new Linea(4, 4, 'A');
		BluePlaysVertically(linea);
		String expected = 
				"| O |   |   |   |\n" +
				"| O |   |   |   |\n" +
				"| O |   | X |   |\n" +
				"| O | X | X | X |\n" +
				"< 1   2   3   4   >\n" +
				"The winner is blue!";
		    assertEquals(expected, new String(linea.show()));	
	}
	@Test
	public void test14PanelShowsDraw() {
		Linea linea = new Linea(2, 2, 'A');
		GameIsAtDraw(linea);
		String expected = 
				"| O | O |\n" +
				"| X | X |\n" +
				"< 1   2   >\n" +
				"There was a draw";
		    assertEquals(expected, new String(linea.show()));	
	}
	@Test
	public void test15PlayerDoesNotWinDiagonallyInGameModeA() {
		Linea linea = new Linea(5, 4, 'A');
		RedPlaysDiagonally(linea);
		assertFalse(linea.finished());
	}
	@Test
	public void test16PlayerDoesNotWinHorizontallyInGameModeB() {
		Linea linea = new Linea(5, 4, 'B');
		BluePlaysHorizontally(linea);
		assertFalse(linea.finished());
	}
	@Test
	public void test17PlayerDoesNotWinVerticallyInGameModeB() {
		Linea linea = new Linea(5, 4, 'B');
		BluePlaysVertically(linea);
		assertFalse(linea.finished());
		assertEquals(linea.getState() , "It's red's turn");
	}
	@Test
	public void test19BlueWinsVerticallyInGameModeA() {
		Linea linea = new Linea(5, 4, 'A');
		BluePlaysVertically(linea);
		assertTrue(linea.finished());
		assertEquals("The winner is blue!", linea.getState());
	}
	@Test
	public void test20BlueWinsHorizontallyInGameModeA() {
		Linea linea = new Linea(5, 4, 'A');
		BluePlaysHorizontally(linea);
		assertTrue(linea.finished());
		assertEquals("The winner is red!", linea.getState());
	}
	@Test
	public void test21RedWinsDiagonallyInGameModeB() {
		Linea linea = new Linea(5, 4, 'B');
		RedPlaysDiagonally(linea);
		assertTrue(linea.finished());
		assertEquals("The winner is red!", linea.getState());
	}
	@Test
	public void test22BlueWinsVerticallyInGameModeC() {
		Linea linea = new Linea(5, 4, 'C');
		BluePlaysVertically(linea);
		assertTrue(linea.finished());
		assertEquals("The winner is blue!", linea.getState());
	}
	@Test
	public void test23BlueWinsHorizontallyInGameModeC() {
		Linea linea = new Linea(5, 4, 'A');
		BluePlaysHorizontally(linea);
		assertTrue(linea.finished());
		assertEquals("The winner is red!", linea.getState());
	}
	
	@Test
	public void test24RedWinsDiagonallyInGameModeC() {
		Linea linea = new Linea(5, 4, 'C');
		RedPlaysDiagonally(linea);
		assertTrue(linea.finished());
		assertEquals("The winner is red!", linea.getState());
	}
	
	@Test
	public void test25ItsADraw() {
		Linea linea = new Linea(2, 2, 'A');
		GameIsAtDraw(linea);
		assertTrue(linea.finished());
		assertEquals(linea.getState() , "There was a draw");
	}
	
	public void assertThrowsLike(Executable executable, String message) {
		assertEquals(message, assertThrows(RuntimeException.class, executable).getMessage());
	}
	private void GameModeIsCorrect(char gameMode, Class<?> classMode) {
		Linea linea = new Linea(4, 4, gameMode);
		assertEquals(linea.getMode().getClass(), classMode);
	}
	
	private void BluePlaysVertically(Linea linea) {
		linea.playRedAt(2);
		linea.playBlueAt(1);
		linea.playRedAt(3);
		linea.playBlueAt(1);
		linea.playRedAt(4);
		linea.playBlueAt(1);
		linea.playRedAt(3);
		linea.playBlueAt(1);
	}
	private void BluePlaysHorizontally(Linea linea) {
		linea.playRedAt(2);
		linea.playBlueAt(1);
		linea.playRedAt(3);
		linea.playBlueAt(1);
		linea.playRedAt(4);
		linea.playBlueAt(1);
		linea.playRedAt(5);
	}
	private void RedPlaysDiagonally(Linea linea) {
		linea.playRedAt(1);
		linea.playBlueAt(2);
		linea.playRedAt(2);
		linea.playBlueAt(3);
		linea.playRedAt(3);
		linea.playBlueAt(4);
		linea.playRedAt(3);
		linea.playBlueAt(4);
		linea.playRedAt(4);
		linea.playBlueAt(1);
		linea.playRedAt(4);
	}
	private void GameIsAtDraw(Linea linea) {
		linea.playRedAt(2);
		linea.playBlueAt(2);
		linea.playRedAt(1);
		linea.playBlueAt(1);
	}
	
	
	}
	
	

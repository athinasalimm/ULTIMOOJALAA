package game4InLine;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class game4InLineTest {

	@Test
	public void test00SpaceCreatedWith6x7() {
		Linea linea = new Linea(6, 7, 'A');
		assertEquals(linea.getWidth(), 6);
		assertEquals(linea.getHeight(), 7);
	}
	@Test
	public void test01GameModeIsA() {
		Linea linea = new Linea(6, 7, 'A');
		assertEquals(linea.getMode().getClass(), AMode.class);
	}
	@Test
	public void test02GameModeIsB() {
		Linea linea = new Linea(6, 7, 'B');
		assertEquals(linea.getMode().getClass(), BMode.class);
}
	@Test
	public void test03GameModeIsC() {
		Linea linea = new Linea(6, 7, 'C');
		assertEquals(linea.getMode().getClass(), CMode.class);
	}
	
	@Test
	public void test04RedAlwaysStartsGame() {
		Linea linea = new Linea (6, 7, 'C');
		assertEquals(linea.getState(), "It's red's turn");
	}
	
	@Test
public void test05RedAlwaysStartsAndAfterwardsBlue() {
		Linea linea = new Linea (6, 7, 'A');
		linea.playRedAt(6);
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
		assertThrowsLike(() -> new Linea(5, 4, 'e'), "Invalid game mode");
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
		linea.playRedAt(2);
		linea.playBlueAt(1);
		linea.playRedAt(3);
		linea.playBlueAt(1);
		linea.playRedAt(4);
		linea.playBlueAt(1);
		linea.playRedAt(3);
		linea.playBlueAt(1);
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
		linea.playRedAt(2);
		linea.playBlueAt(2);
		linea.playRedAt(1);
		linea.playBlueAt(1);
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
		assertFalse(linea.finished());
	}
	@Test
	public void test16PlayerDoesNotWinHorizontallyInGameModeB() {
		Linea linea = new Linea(5, 4, 'B');
		linea.playRedAt(2);
		linea.playBlueAt(1);
		linea.playRedAt(3);
		linea.playBlueAt(1);
		linea.playRedAt(4);
		linea.playBlueAt(1);
		linea.playRedAt(5);
		assertFalse(linea.finished());
	}
	@Test
	public void test17PlayerDoesNotWinVerticallyInGameModeB() {
		Linea linea = new Linea(5, 4, 'B');
		linea.playRedAt(2);
		linea.playBlueAt(1);
		linea.playRedAt(3);
		linea.playBlueAt(1);
		linea.playRedAt(4);
		linea.playBlueAt(1);
		linea.playRedAt(3);
		linea.playBlueAt(1);
		assertFalse(linea.finished());
		assertEquals(linea.getState() , "It's red's turn");
	}
	@Test
	public void test19BlueWinsVerticallyInGameModeA() {
		Linea linea = new Linea(5, 4, 'A');
		linea.playRedAt(2);
		linea.playBlueAt(1);
		linea.playRedAt(3);
		linea.playBlueAt(1);
		linea.playRedAt(4);
		linea.playBlueAt(1);
		linea.playRedAt(3);
		linea.playBlueAt(1);
		assertTrue(linea.finished());
		assertEquals("The winner is blue!", linea.getState());
	}
	@Test
	public void test20BlueWinsHorizontallyInGameModeA() {
		Linea linea = new Linea(5, 4, 'A');
		linea.playRedAt(2);
		linea.playBlueAt(1);
		linea.playRedAt(3);
		linea.playBlueAt(1);
		linea.playRedAt(4);
		linea.playBlueAt(1);
		linea.playRedAt(5);
		assertTrue(linea.finished());
		assertEquals("The winner is red!", linea.getState());
	}
	@Test
	public void test21RedWinsDiagonallyInGameModeB() {
		Linea linea = new Linea(5, 4, 'B');
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
		assertTrue(linea.finished());
		assertEquals("The winner is red!", linea.getState());
	}
	@Test
	public void test22BlueWinsVerticallyInGameModeC() {
		Linea linea = new Linea(5, 4, 'C');
		linea.playRedAt(2);
		linea.playBlueAt(1);
		linea.playRedAt(3);
		linea.playBlueAt(1);
		linea.playRedAt(4);
		linea.playBlueAt(1);
		linea.playRedAt(3);
		linea.playBlueAt(1);
		assertTrue(linea.finished());
		assertEquals("The winner is blue!", linea.getState());
	}
	@Test
	public void test23BlueWinsHorizontallyInGameModeC() {
		Linea linea = new Linea(5, 4, 'A');
		linea.playRedAt(2);
		linea.playBlueAt(1);
		linea.playRedAt(3);
		linea.playBlueAt(1);
		linea.playRedAt(4);
		linea.playBlueAt(1);
		linea.playRedAt(5);
		assertTrue(linea.finished());
		assertEquals("The winner is red!", linea.getState());
	}
	
	@Test
	public void test24RedWinsDiagonallyInGameModeC() {
		Linea linea = new Linea(5, 4, 'C');
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
		assertTrue(linea.finished());
		assertEquals("The winner is red!", linea.getState());
	}
	
	@Test
	public void test18ItsADraw() {
		Linea linea = new Linea(2, 2, 'A');
		linea.playRedAt(2);
		linea.playBlueAt(2);
		linea.playRedAt(1);
		linea.playBlueAt(1);
		assertTrue(linea.finished());
		assertEquals(linea.getState() , "There was a draw");
	}
	
	public void assertThrowsLike(Executable executable, String message) {
		assertEquals(message, assertThrows(RuntimeException.class, executable).getMessage());
	}
	
	}
	
	
	
	

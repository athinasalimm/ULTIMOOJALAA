package game4InLine;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class game4InLineTest {

	@Test
	public void test00Espacio6x7() {
		Linea linea = new Linea(6, 7, 'A');
		assertEquals(linea.getWidth(), 6);
		assertEquals(linea.getHeight(), 7);
	}
	@Test
	public void test01VarianteTriunfoEsVerticalesUHorizontales() {
		Linea linea = new Linea(6, 7, 'A');
		assertEquals(linea.getMode().getClass(), AMode.class);
	}
	@Test
	public void test02VarianteTriunfoEsEnDiagonales() {
		Linea linea = new Linea(6, 7, 'B');
		assertEquals(linea.getMode().getClass(), BMode.class);
}
	@Test
	public void test03VarianteTriunfoEsEnCualquierDireccion() {
		Linea linea = new Linea(6, 7, 'C');
		assertEquals(linea.getMode().getClass(), CMode.class);
	}
	
	@Test
	public void test04SiempreEmpiezaRojo() {
		Linea linea = new Linea (6, 7, 'C');
		assertEquals(linea.getState(), "rojo");
	}
	
	@Test
public void test05SiempreEmpiezaRojoYLuegoVaAzul() {
		Linea linea = new Linea (6, 7, 'A');
		linea.playRedAt(6);
		assertEquals(linea.getState(), "Blue is playing");
	}
	
	@Test
	public void test06ColocaFichaRojaYAzul() {
		Linea linea = new Linea(4, 4, 'A');
		linea.playRedAt(3);
		linea.playBlueAt(3);
	}
	@Test
	public void test07ErrorAlColocarMasFichasQueLasPermitidas() {
		Linea linea = new Linea(4, 4, 'A');
		linea.playRedAt(3);
		linea.playBlueAt(3);
		linea.playRedAt(3);
		linea.playBlueAt(3);
		assertThrowsLike(() -> linea.playRedAt(3), Linea.CannotAddToken);
	}
	
	@Test
	public void test08HayEmpate() {
		Linea linea = new Linea(2, 2, 'A');
		linea.playRedAt(2);
		linea.playBlueAt(2);
		linea.playRedAt(1);
		linea.playBlueAt(1);
		assertTrue(linea.finished());
		//assertEquals(linea.getGanador() , "No hay ganador, es un empate");
	}
	
	@Test
	public void test09seganaHorizontal() {
		Linea linea = new Linea(5, 4, 'A');
		linea.playRedAt(2);
		linea.playBlueAt(1);
		linea.playRedAt(3);
		linea.playBlueAt(1);
		linea.playRedAt(4);
		linea.playBlueAt(1);
		linea.playRedAt(5);
		assertTrue(linea.finished());
	}
	
	@Test
	public void test10ganaAzulVertical() {
		Linea linea = new Linea(5, 4, 'A');
		linea.playRedAt(2);
		linea.playBlueAt(1);
		linea.playRedAt(3);
		linea.playBlueAt(1);
		linea.playRedAt(4);
		linea.playBlueAt(1);
		linea.playRedAt(3);
		linea.playBlueAt(1);
		//System.out.println(linea.show());
		assertTrue(linea.finished());
		assertEquals("The winner is Blue!", linea.getState());
	}
	@Test
	public void test11ganaRojoDiagonal() {
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
		System.out.println(linea.show());
		assertTrue(linea.finished());
	}

	
	public void assertThrowsLike(Executable executable, String message) {
		assertEquals(message, assertThrows(RuntimeException.class, executable).getMessage());
	}
	
	}
	
	
	

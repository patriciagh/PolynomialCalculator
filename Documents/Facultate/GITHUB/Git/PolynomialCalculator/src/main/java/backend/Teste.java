package backend;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class Teste {
	ArrayList<Monom> monoame1=new ArrayList<Monom>();
	ArrayList<Monom> monoame2=new ArrayList<Monom>();	
	Polinom p1=new Polinom(0,monoame1);
	Polinom p2=new Polinom(0,monoame2);
	@Test
	public void testAdunare()
	{
		// x^2 + 2x + 1
		p1.adaugareMonom(new Monom(1,2,0));
		p1.adaugareMonom(new Monom(2,1,0));
		p1.adaugareMonom(new Monom(1,0,0));
		// x^3 + 3x^2
		p2.adaugareMonom(new Monom(1,3,0));
		p2.adaugareMonom(new Monom(3,2,0));
		Polinom rezultatOp=p1.adunare(p2);
		ArrayList<Monom> rez=new ArrayList<Monom>();
		// x^3 + 4x^2 + 2x + 1
		Polinom rezultatCorect=new Polinom(0,rez);
		rezultatCorect.adaugareMonom(new Monom(1,3,0));
		rezultatCorect.adaugareMonom(new Monom(4,2,0));
		rezultatCorect.adaugareMonom(new Monom(2,1,0));
		rezultatCorect.adaugareMonom(new Monom(1,0,0));
		assertEquals(rezultatCorect.getPolinom(),rezultatOp.getPolinom());
	}
	@Test
	public void testScadere()
	{
		// x^2 + 2x + 1
		p1.adaugareMonom(new Monom(1,2,0));
		p1.adaugareMonom(new Monom(2,1,0));
		p1.adaugareMonom(new Monom(1,0,0));
		// x^3 + 3x^2
		p2.adaugareMonom(new Monom(1,3,0));
		p2.adaugareMonom(new Monom(3,2,0));
		Polinom rezultatOp=p1.scadere(p2);
		ArrayList<Monom> rez=new ArrayList<Monom>();
		// -x^3 -2x^2 + 2x + 1
		Polinom rezultatCorect=new Polinom(0,rez);
		rezultatCorect.adaugareMonom(new Monom(-1,3,0));
		rezultatCorect.adaugareMonom(new Monom(-2,2,0));
		rezultatCorect.adaugareMonom(new Monom(2,1,0));
		rezultatCorect.adaugareMonom(new Monom(1,0,0));
		assertEquals(rezultatCorect.getPolinom(),rezultatOp.getPolinom());
	}
	@Test
	public void testInmultire()
	{
		// x^2 + 2x + 1
		p1.adaugareMonom(new Monom(1,2,0));
		p1.adaugareMonom(new Monom(2,1,0));
		p1.adaugareMonom(new Monom(1,0,0));
		// x^3 + 3x^2
		p2.adaugareMonom(new Monom(1,3,0));
		p2.adaugareMonom(new Monom(3,2,0));
		Polinom rezultatOp=p1.inmultire(p2);
		ArrayList<Monom> rez=new ArrayList<Monom>();
		// x^5 + 5x^4 + 7x^3 + 3x^2
		Polinom rezultatCorect=new Polinom(0,rez);
		rezultatCorect.adaugareMonom(new Monom(1,5,0));
		rezultatCorect.adaugareMonom(new Monom(5,4,0));
		rezultatCorect.adaugareMonom(new Monom(7,3,0));
		rezultatCorect.adaugareMonom(new Monom(3,2,0));
		assertEquals(rezultatCorect.getPolinom(),rezultatOp.getPolinom());
	}
	@Test
	public void testDerivare()
	{
		// x^2 + 2x + 1
		p1.adaugareMonom(new Monom(1,2,0));
		p1.adaugareMonom(new Monom(2,1,0));
		p1.adaugareMonom(new Monom(1,0,0));		
		Polinom rezultatOp=p1.derivare();
		ArrayList<Monom> rez=new ArrayList<Monom>();
		// 2x+2
		Polinom rezultatCorect=new Polinom(0,rez);
		rezultatCorect.adaugareMonom(new Monom(2,1,0));
		rezultatCorect.adaugareMonom(new Monom(2,0,0));
		assertEquals(rezultatCorect.getPolinom(),rezultatOp.getPolinom());
	}
	@Test
	public void testIntegrare()
	{
		// 3x^2 + 2x + 1
		p1.adaugareMonom(new Monom(3,2,0));
		p1.adaugareMonom(new Monom(2,1,0));
		p1.adaugareMonom(new Monom(1,0,0));		
		Polinom rezultatOp=p1.integrare();
		ArrayList<Monom> rez=new ArrayList<Monom>();
		// x^3 + x^2 + x
		Polinom rezultatCorect=new Polinom(0,rez);
		rezultatCorect.adaugareMonom(new Monom(1,3,0));
		rezultatCorect.adaugareMonom(new Monom(1,2,0));
		rezultatCorect.adaugareMonom(new Monom(1,1,0));
		assertEquals(rezultatCorect.getPolinom(),rezultatOp.getPolinom());
	}
	
}


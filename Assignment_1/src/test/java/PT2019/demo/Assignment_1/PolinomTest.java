package PT2019.demo.Assignment_1;
/**
 * Autor:Calacean Ionut Eugen
 * Facultatea de Automatica si Calculatoare
 * Grupa 30229
 */
import static org.junit.Assert.assertEquals;

import org.junit.*;

import Controller.Controller;
import Model.Polinom;






public class PolinomTest
{
    Polinom p1, p2, p3;
    String pol1 = "x^4+3x^2+2x-1", pol2 = "x^2+2x+3";//doua polinoame random, testam operatiile pe ele

    @Before
    public void setUp() throws Exception//inainte de teste cream polinoamele cu stringurile luate
    {
        p1 = new Polinom(Controller.parsingPol(pol1));
        p2 = new Polinom(Controller.parsingPol(pol2));
    }

    @After
    public void tearDown() throws Exception
    {

    }

    @Test
    public void adunare() throws Exception
    {
        p3 = p1.adunare(p2);
        assertEquals("+1X^4+4X^2+4X^1+2", p3.toString());
    }

    @Test
    public void scadere() throws Exception
    {
        p3 = p1.scadere(p2);
        assertEquals("+1X^4+2X^2-4", p3.toString());

    }

    @Test
    public void inmultire() throws Exception
    {
        p3 = p1.inmultire(p2);
        assertEquals("+1X^6+2X^5+6X^4+8X^3+12X^2+4X^1-3", p3.toString());
    }

    @Test
    public void impartire() throws Exception
    {
        p3 = p1.impartire(p2);
        assertEquals("+1X^2-2X^1+4 Rest: -13", p3.toString() + " Rest: " +  p1.toString());
    }

    @Test
    public void integrare() throws Exception
    {
        p3 = p1.integrare();
        assertEquals("+0.2X^5+1X^3+1X^2-1X^1", p3.toString());
    }

    @Test
    public void derivare() throws Exception
    {
        p3 = p1.derivare();
        assertEquals("+4X^3+6X^1+2", p3.toString());
    }

}

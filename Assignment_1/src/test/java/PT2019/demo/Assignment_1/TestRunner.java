package PT2019.demo.Assignment_1;
/**
 * Autor:Calacean Ionut Eugen
 * Facultatea de Automatica si Calculatoare
 * Grupa 30229
 */
import org.junit.runner.*;
import org.junit.runner.notification.Failure;

public class TestRunner {

	public static void main(String[] argv)
	{
		Result result=JUnitCore.runClasses(PolinomTest.class);// se ruleaza testele declarate in clasa PolinomTest
		
		for(Failure failure: result.getFailures())//iteram cautand teste care au esuat
		{
			System.out.println(failure.toString());
		}
		System.out.println("Succes:"+result.wasSuccessful());//printam succes sau fail
	}
}

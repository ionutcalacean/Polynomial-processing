package PT2019.demo.Assignment_1;
/**
 * Autor:Calacean Ionut Eugen
 * Facultatea de Automatica si Calculatoare
 * Grupa 30229
 */
import Controller.Controller;
import View.View;

public class Main {

	public static void main(String[] args)
    {
        View myView = new View();
        Controller myContr=new Controller(myView);//legam view-ul la Controller
       
    }
}

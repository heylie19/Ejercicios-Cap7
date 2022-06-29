

public class PruebaE_737 {
    public static void main( String args[] )
    {
        E_737 simp = new E_737();
        int operands=0;
        int operation=0;

        simp.displayWelcomeMessage();
        simp.runSimulator();
        simp.inicialiceVariables();
        simp.load();
        simp.execute(operands,operation);
        simp.dumpTheCore();


    }

}

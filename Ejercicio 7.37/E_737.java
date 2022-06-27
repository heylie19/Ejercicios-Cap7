
import java.util.Scanner;

public class E_737 
{
        
	private int accumulator;	
	private int [] memory;	
	private int instructionRegister;	
	private int instructionCounter;	
	private int operationCode;
	private int operand;	
	
	public E_737 ( ) 
	{
		displayWelcomeMessage ();
		initialiseVariables ();
		// runSimulator ();
	}	

	
	public void displayWelcomeMessage ( ) 
	{
		System.out.printf ( "\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s %s\n%s %s\n" ,
			"*** Bienvenido a Simpletron! ***" ,
			"*** Ingrese su programa una instruccion ***" ,
			"*** (o palabra de datos) a la vez en la entrada ***" ,
			"*** campo de texto. Mostrare la ubicación ***" ,
			"*** numero y un signo de interrogacion (?). Luego ***" ,
			"*** escribe la palabra para esa ubicación. Presiona el ***" ,
			"*** Boton Listo para dejar de ingresar a su programa ***" ,
            "*** Teclee -99999 para dejar de introducir su programa. ***",
			 "Loc" , "Inst" , "****" , "*****" );
	}	

	
	public void runSimulator () 
	{
		int submittedInstruction = 0;	
		int memoryPointer = 0;	

		Scanner input = new Scanner ( System.in );

		do
		{
			System.out.printf ("%d %s  ", memoryPointer, "?" );
			submittedInstruction = input.nextInt ();
			if ( submittedInstruction != -99999 ) 
				memory [ memoryPointer ] = submittedInstruction;
			memoryPointer++;	// go to the next memory location
			
		} while ( submittedInstruction != -99999 );	
		
	        System.out.printf ("\n%s%s", "*** Carga del programa completada ***\n", 
				"*** La ejecución del programa comienza ***\n");	
		
		
		for ( int code : memory ) 
		{
			
			if ( code != 0 )	
			{
				load ();
				execute ( operand, operationCode );
			}
		}

	}	
	
	public void initialiseVariables ( )
	{
		memory = new int [100];	
		instructionCounter = 0;	

	}	

	
	public void load ( ) 
	{
		
		
		operationCode = memory [ instructionCounter ] / 100;
		operand = memory [ instructionCounter ] % 100;

	}	 	

	
	public void execute (int operands, int operation ) 
	{
		
		switch ( operation ) 
		{
			case 10: // Leer una palabra del teclado en una ubicación específica en la memoria
				Scanner input = new Scanner ( System.in );
				System.out.print ( "Ingrese un número entero (positivo o negativo)): " );
				memory [ operands ] = input.nextInt ();	// place the result in the specified memory location
				break;
			case 11:	// escribe una ubicación específica en la memoria en la pantalla
				System.out.println ("El resultado de la operación es" + memory [ operands] );
				break;
			case 20: //cargar una palabra desde una ubicación específica en el acumulador
				accumulator = memory [ operands ];
				break;
			case 21: 	// Almacena una palabra del acumulador en una ubicación específica en la memoria
				memory [ operands ] = accumulator;
				break;
			case 30: // agrega una palabra desde una ubicación específica en la memoria a la palabra
			// en el acumulador ( dejar el resultado en el acumulador
				accumulator += memory [ operands ];
				break;
			case 31: // Resta una palabra de una ubicación específica en la memoria del
			// palabra en el acumulador (dejar el resultado en el acumulador)
				accumulator -= memory [ operands ];
				break;
			case 32:	// Divide una palabra de una ubicación específica en la memoria en el
			// palabra en el acumulador (dejar el resultado en el acumulador)
				accumulator /=  memory [ operands ];
				break;
			case 33: // Multiplica una palabra de una ubicación específica en la memoria por el
			// palabra en el acumulador (dejar el resultado en el acumulador)
				accumulator *= memory [ operands ];
				break;
			case 40:	// bifurca a una ubicación específica en la memoria
				instructionCounter = operands;
				break;
			case 41:	 // bifurcarse a una ubicación específica en la memoria si el acumulador es negativo
				if ( accumulator < 0 )
					instructionCounter = operands;
				break;
			case 42:	// bifurcar a una ubicación específica si el acumulador es cero
				if ( accumulator == 0 )
					instructionCounter = operands;
				break;
			case 43: 	// Detener. El programa ha completado sus tareas.
				dumpTheCore ();	// do a "core dump"
				System.out.printf ("\n%s\n", "El programa ha terminado...");
				System.exit ( 0 );
				break;

		}	

		instructionCounter++;	
	}	

	public void dumpTheCore ( )
	{
		System.out.printf ("\n%30s\n%30s\t%s%4d\n%30s\t%2d\n%30s\t%2d\n%30s\t%2d\n%30s\t%2d\n\n%30s\n", "REGISTERS:", 
				"accumulator", "+", accumulator, "instruction counter", instructionCounter, "instruction register",
			       	instructionCounter, "operation code", operationCode, "operand", operand, "MEMORY:" );

		for ( int i = 0; i < 10; i++ )
		{
			System.out.printf ( "%6d", i);
		}

		System.out.println ();
		int counter = 0;	

		for (int i = 0; i < 10; i++ ) 
		{
			if ( counter %10 == 0 )
				System.out.printf ("%2d ", counter);
			for (int j = 0; j < 10; j++) 
			{	
				if ( memory [ counter ] == 0 )
					System.out.printf ( "%s%s", "+", "0000 ");
				else 
					System.out.printf ("%s%4d ", "+", memory [counter]);
				counter++;

			}	
		       
		    System.out.println ();	

		}	
	}	
}	

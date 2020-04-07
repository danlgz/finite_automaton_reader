import java.io.*;

/*
	Utilice esta clase para guardar la informacion de su
	AFD. NO DEBE CAMBIAR LOS NOMBRES DE LA CLASE NI DE LOS 
	METODOS que ya existen, sin embargo, usted es libre de 
	agregar los campos y metodos que desee.
*/
public class AFD{
	AFDState states[];
	Character alphabet[];
	Character statesFromFile[][];
	int totalStates;
	int finalState;
	
	/*
		Implemente el constructor de la clase AFD
		que recibe como argumento un string que 
		representa el path del archivo que contiene
		la informacion del afd (i.e. "Documentos/archivo.afd").
		Puede utilizar la estructura de datos que desee
	*/
	public AFD(String path){
		File file = null;
		FileReader fr = null;
		BufferedReader br = null;
		//Read File
		try {

			file = new File (path);
			fr = new FileReader (file);
			br = new BufferedReader(fr);

			String line = null;
			int lineNumber = 1;
			while((line=br.readLine())!=null){
				if(lineNumber == 1){
					alphabet = line.split(",");
				}else if(lineNumber == 2 ) {
					totalStates = Integer.parseInt(line);
				}else if(lineNumber == 3){
					finalState = Integer.parseInt(line);
				}
				lineNumber++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if( null != fr ){   
				fr.close();     
			}  
		}

		System.out.println(alphabet.length);
		System.out.println(totalStates);
		System.out.println(finalState);

	}

	/*
		Implemente el metodo transition, que recibe de argumento
		un entero que representa el estado actual del AFD y un
		caracter que representa el simbolo a consumir, y devuelve 
		un entero que representa el siguiente estado
	*/
	public int getTransition(int currentState, char symbol) {
		if (currentState > this.states.length) return 0;
		return this.states[currentState].getNextState(symbol);
	}

	/*
		Implemente el metodo accept, que recibe como argumento
		un String que representa la cuerda a evaluar, y devuelve
		un boolean dependiendo de si la cuerda es aceptada o no 
		por el afd
	*/
	public boolean accept(String string){
		return false;
	}

	/*
		El metodo main debe recibir como primer argumento el path
		donde se encuentra el archivo ".afd", como segundo argumento 
		una bandera ("-f" o "-i"). Si la bandera es "-f", debe recibir
		como tercer argumento el path del archivo con las cuerdas a 
		evaluar, y si es "-i", debe empezar a evaluar cuerdas ingresadas
		por el usuario una a una hasta leer una cuerda vacia (""), en cuyo
		caso debe terminar. Tiene la libertad de implementar este metodo
		de la forma que desee. 
	*/
	public static void main(String[] args) throws Exception{
		//Paramns
		String afd_path = args[0];
		
		AFD afd = new AFD(afd_path);

	}

	/* HELPERS */
	private void generateStates(int numberOfStates) {
		this.states = new AFDState[numberOfStates];

		for (int i = 0; i < numberOfStates; i++) {
			this.states[i] = new AFDState(i);
		}
	}

	private AFDState getState(int state) {
		return this.states[state];
	}

	private void setNextState(int state, char symbol, int goToState) {
		AFDState localState = this.getState(state);
		localState.setStage(symbol, goToState);
	}
}

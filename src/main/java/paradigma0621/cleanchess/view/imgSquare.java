package paradigma0621.cleanchess.view;

import paradigma0621.cleanchess.model.Square;

import java.util.Observable;

@SuppressWarnings("all")


public class imgSquare extends Observable {

    //   private Image imgSquares[];

    public imgSquare(Square square) {

	
    }


    	private String acao = "";
	
	public void frente() {
		acao = "frente";
		System.out.println("Carro roubado segue em frente.");
		this.mudaEstado(); //Diz que seu estado mudou
	}


    	
	public void mudaEstado(){
		setChanged(); // Mudou o estado
		notifyObservers(acao); // Notifica os observadores
	}
    
	
}

    

	      

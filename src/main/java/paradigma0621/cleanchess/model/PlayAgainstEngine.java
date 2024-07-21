package paradigma0621.cleanchess.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

import ictk.boardgame.chess.ChessBoard;
import ictk.boardgame.chess.ChessMove;


public class PlayAgainstEngine {

public static void createScriptFile(String FENatual) {	
	PrintWriter writer =null; //Não sei se é o melhor método para conseguir usar o finally depois, 
								//mas desse jeito o Java roda... apesar de não saber se/quando ele
								//executa o finally
	try {
	writer = new PrintWriter("/home/lucas/Documentos/xadrez/java/xadrezQueEstaValendo-16.9/artifactId/src/main/resources/executable/fen", "UTF-8");
	writer.println("#!/bin/bash");
	writer.println("(");
	writer.println("echo \"setoption name Hash value 128\";");
	writer.println("echo \"setoption name threads value 1\";");
	writer.println("echo \"setoption name Contempt Factor value 50\";");
	writer.println("echo \"position fen " + FENatual+"\";");
	writer.println("echo \"go movetime 1000\"");
	writer.println("sleep 1");
	writer.println(") | /home/lucas/Documentos/xadrez/java/xadrezQueEstaValendo-16.9/artifactId/src/main/resources/executable/stockfish > /home/lucas/Documentos/xadrez/java/xadrezQueEstaValendo-16.9/artifactId/src/main/resources/playedGames/out-X3");
	writer.close();
	} catch (IOException ex) {
	    System.out.println("Erro na criação do script de execução do engine: "+ex.getMessage());
	} finally {
		writer.close();
	}
}	

	public static void runEngine() {
			
    try {
        Runtime rt = Runtime.getRuntime();
        String graphDir = "/home/lucas/Documentos/xadrez/java/xadrezQueEstaValendo-16.9/artifactId/src/main/resources/executable/";
        String cmdString = graphDir + "fen";
        System.out.println(cmdString);
        Process pr = rt.exec(cmdString);
        BufferedReader entrada = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String linha = null;
        while ((linha = entrada.readLine()) != null) {
            System.out.println(linha);
        }
        pr.waitFor();
        if(pr.exitValue()!=0)
            System.out.println("Erro de saida " + pr.exitValue());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public static String readEngineOutput() {
		
		//int numLines=1;
		String line=null;
						
		File file = new File("/home/lucas/Documentos/xadrez/java/xadrezQueEstaValendo-16.9/artifactId/src/main/resources/playedGames/out-X3");
		Scanner sc = null;
		try {
			sc = new Scanner(file);
			while (sc.hasNextLine()) {
		    	line = sc.nextLine();
			}
		}
		catch (IOException e) {
		System.out.println("Error opening file: " + e.getMessage());
		}
		finally {
			if (sc != null) {
				sc.close();
			}
		}
		
	System.out.println("Last line: " + line); //the last line is the just one that matters
	return line;
	}	
	
	public static char[] getTheEngineMove(String lineOfBestMove) {
		String strBrute[] = lineOfBestMove.split(" ");
		String interestedMove = strBrute[1];
		
		char var[] = new char[4];
		interestedMove.getChars(0, 4, var,0);
		return var;
	}
	
	public static void makeMove(char[] move, PGNreader pgnFile) {
		//pgnFile.moveMaker(move);
	}
	
}

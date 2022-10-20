import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	
	static ArrayList<Integer> playerPositions = new ArrayList<Integer>(); 
	static ArrayList<Integer> CPUPositions = new ArrayList<Integer>(); 

	public static void main(String[] args) {
		//3 rows and 3 columns so 2d array
		char [][] gameBoard = {{' ', '|', ' ', '|', ' '}, //top of the board
				{'-', '+', '-', '+', '-'}, //second line
				{' ', '|', ' ', '|', ' '}, //middle line 
				{'-', '+', '-', '+', '-'}, //third line
				{' ', '|', ' ', '|', ' '}}; //bottom line 
				
				printGameBoard(gameBoard); //we have to put in gameboard or the gameboard method below won't know what gameBoard is 
				
				while(true) {
					Scanner scan = new Scanner(System.in); 
					System.out.println("Enter your placement 1-9: ");
					int position = scan.nextInt();
					while(playerPositions.contains(position) || CPUPositions.contains(position)) {
						System.out.println("Position taken!");
						position = scan.nextInt();
					}
					System.out.println(position); 
					placePiece(gameBoard, position, "Player"); 
					String result = checkWinner(); 
					if(result.length() > 0) {
						System.out.println(result);
							break; 
					}

					Random cpumove = new Random();
					int cpuPos = cpumove.nextInt(9) + 1; 
					placePiece(gameBoard, cpuPos, "CPU");  
					while(CPUPositions.contains(cpuPos) || playerPositions.contains(cpuPos)) {
						cpuPos = cpumove.nextInt(9) + 1;
					}

					printGameBoard(gameBoard); 
					result = checkWinner(); 
					if(result.length() > 0) {
						System.out.println(result);
							break; 
					}
				}
				
				

		}
				
		
	
	public static void printGameBoard(char[][] gameBoard) {
		for(char[] row : gameBoard) { //for each array inside the gameboard
			for (char c: row) { //for each char inside the row we are in 
					System.out.print(c); //print out the symbol
			}
			System.out.println(); //after each row we want to print the line as well
		
		}
	}
	public static void placePiece(char[][] gameBoard, int position, String user) {	
		char symbol = ' '; 
		
		if (user.equals("Player")) {
			symbol ='X';
			playerPositions.add(position);
		}
		else if(user.equals("CPU")){
			symbol ='O';
			CPUPositions.add(position);
			
		}
		switch(position) {
		
			case 1: 
				gameBoard[0][0] = symbol; //arrays always start with row then column
				break; 
			case 2: 
				gameBoard[0][2] = symbol;
				break;
			case 3: 
				gameBoard[0][4] = symbol;
				break;
			case 4: 
				gameBoard[2][0] = symbol;
				break;
			case 5: 
				gameBoard[2][2] = symbol;
				break;
			case 6: 
				gameBoard[2][4] = symbol;
				break;
			case 7: 
				gameBoard[4][0] = symbol;
				break;
			case 8: 
				gameBoard[4][2] = symbol;
				break;
			case 9: 
				gameBoard[4][4] = symbol;
				break;
			default:
				break; 
			
			
		}
	
}
	public static String checkWinner() {
		List topRow = Arrays.asList(1,2,3);
		List midRow = Arrays.asList(4,5,6);
		List botRow = Arrays.asList(7,8,9);
		List topCol = Arrays.asList(1,4,7);
		List midCol = Arrays.asList(2,5,8);
		List botCol = Arrays.asList(3,6,9);
		List cross1 = Arrays.asList(1,5,9);
		List cross2 = Arrays.asList(3,5,7);

		List<List> winning = new ArrayList<List>(); 
		winning.add(topRow);
		winning.add(midRow);
		winning.add(botRow);
		winning.add(topCol);
		winning.add(midCol);
		winning.add(botCol);
		winning.add(cross1);
		winning.add(cross2); 
		
		for(List l: winning) {
			if(playerPositions.containsAll(l)) {
				return "You won"; 
			}
			else if (CPUPositions.containsAll(l)) {
				return "You lose"; 
			}
			else if(playerPositions.size() + CPUPositions.size() == 9) {
				return "You tie"; 
			}
			
		}
		return""; 
		
	}
}

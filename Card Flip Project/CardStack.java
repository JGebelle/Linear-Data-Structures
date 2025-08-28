//EECS 2500 Linear Data Structures
//Josh Gebelle
//Programming Project #1
//9/26/2022


import java.util.Scanner;

public class CardStack 
{

	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);
		//read in integer n for number of rows
		int n = input.nextInt();
		
		
		//read in integer m for number of columns
		int m = input.nextInt();//read in integer n for number of rows
		
		//variable to keep track of the test number
		int testNum = 1;
		
		//as long as n and m don't equal zero the program will continue to run
		while((n != 0) && (m != 0))
		{	
		//create stack array 
		//the first row is the top row and the first value in each row 
		//is the leftmost card
		LLStack<Integer>[][] cardStack = new LLStack[n][m];
		
		int intCard = 0;
		
		//loop to place card values in the array
		for(int row = 0; row < cardStack.length; row++)
		{
			for(int col = 0; col < cardStack[row].length; col++)
			{
				//each element of the array is a new LLStack
				cardStack[row][col] = new LLStack();
				
				//read in OSV string
	            String card = input.next();
	            
	            if(card.length() != 3)
	            {
	            	System.exit(0);
	            }
				
				//change string to uppercase
				card = card.toUpperCase();
				
				//call method to check that string is valid
				validCard(card);
				
				//change integer to positive or negative
				int orientation = 0;
				
				if(card.charAt(0) == 'U') 
				{
					orientation = 1;
				}
				
				else if(card.charAt(0) == 'D')
				{
					orientation = -1;
				}
				
				else
				{
					System.exit(0);
				}
				
				//allocate value to a numerical value
				int v = card.charAt(2);
				int cardValue = 0;
				
				switch(v)
				{
				case 'A':
					cardValue = 1;
					break;
					
				case '2':
					cardValue = 2;
					break;
					
				case '3':
					cardValue = 3;
					break;
					
				case '4':
					cardValue = 4;
					break;
					
				case '5':
					cardValue = 5;
					break;
					
				case '6':
					cardValue = 6;
					break;
					
				case '7':
					cardValue = 7;
					break;
					
				case '8':
					cardValue = 8;
					break;
					
				case '9':
					cardValue = 9;
					break;
					
				case 'T':
					cardValue = 10;
					break;
					
				case 'J':
					cardValue = 11;
					break;
					
				case 'Q':
					cardValue = 12;
					break;
					
				case 'K':
					cardValue = 13;
					break;
				}
				
				//allocate suit to value
				int s = card.charAt(1);
				int cardSuit = 0;
				switch(s)
				{
				case 'C':
					cardSuit = 0;
					break;
					
				case 'D':
					cardSuit = 13;
					break;
					
				case 'H':
					cardSuit = 26;
					break;
					
				case 'S':
					cardSuit = 39;
				}
				
				//positive or negative * suit number (1-4) * card value (1-13)
				intCard = (cardValue + cardSuit) * orientation;
				
				//push integer onto the stack
				cardStack[row][col].push(intCard);
			}
		}
		
		//variables to keep track of the flips
				int topRow = 0;
				int bottomRow = n - 1;
				int rightCol = m - 1;
				int leftCol = 0;
		
		//if there is only one card then there are no flips
		if(n == 1 && m == 1)
		{
			System.out.println();
			
		}
		
		else
		{
			
		//read in string for flip direction
		String flip = input.next();
		
		for(int i = 0; i < flip.length(); i++)
		{
			//perform top flip
			if(flip.charAt(i) == 't' || flip.charAt(i) == 'T')
			{
				for(int col = leftCol; col <= rightCol; col++)
				{
					while(cardStack[topRow][col].isEmpty() == false)
					{
					intCard = cardStack[topRow][col].top();
					intCard = intCard * -1;
					cardStack[topRow + 1][col].push(intCard);
					cardStack[topRow][col].pop();
					}
				}
				
				topRow++;
			}
			
			//perform bottom flip
			else if(flip.charAt(i) == 'b' || flip.charAt(i) == 'B')
			{
				for(int col = leftCol; col <= rightCol; col++)
				{
					while(cardStack[bottomRow][col].isEmpty() == false)
					{
					intCard = cardStack[bottomRow][col].top();
					intCard = intCard * -1;
					cardStack[bottomRow - 1][col].push(intCard);
					cardStack[bottomRow][col].pop();
					}
					
				}
				
				bottomRow--;
			}
			
			//perform right flip
			else if(flip.charAt(i) == 'r' || flip.charAt(i) == 'R')
			{
				for(int row = topRow; row <= bottomRow; row++)
				{
					while(cardStack[row][rightCol].isEmpty() == false)
					{
					intCard = cardStack[row][rightCol].top();
					intCard = intCard * -1;
					cardStack[row][rightCol - 1].push(intCard);
					cardStack[row][rightCol].pop();
					}
				}
					
				rightCol--;
			}
			
			//perform left flip
			else if(flip.charAt(i) == 'l' || flip.charAt(i) == 'L')
			{
				for(int row = topRow; row <= bottomRow; row++)
				{
					
					while(cardStack[row][leftCol].isEmpty() == false)
					{
					intCard = cardStack[row][leftCol].top();
					intCard = intCard * - 1;
					cardStack[row][leftCol + 1].push(intCard);
					cardStack[row][leftCol].pop();
					}
					
				}
					
					leftCol++;
			}
			
		}
		
		}
		
		LLStack<Integer> tempCardStack = new LLStack();
		
		//pop integers off of stack and put them in temporary stack so they 
		//are in reverse order
		while(cardStack[topRow][leftCol].isEmpty() == false)
		{
		intCard = cardStack[topRow][leftCol].top();
		tempCardStack.push(intCard);
		cardStack[topRow][leftCol].pop();
		}
		
		System.out.print("Test " + testNum + ":");
		
		testNum++;
		
		//loop through stack for face down cards
		while(tempCardStack.isEmpty() == false)
		{
			intCard = tempCardStack.top();
			
			//convert integer back to string
			if(intCard < 0 )
			{	
				intCard = intCard * -1;
				
				//change value to suit
				int s = (intCard - 1) / 13;
				String cardSuit = " ";
				switch(s)
				{
				case 0:
					cardSuit = "C";
					break;
					
				case 1:
					cardSuit = "D";
					break;
					
				case 2:
					cardSuit = "H";
					break;
					
				case 3:
					cardSuit = "S";
				}
				
				//change numerical value to card value
				int v = (intCard - 1) % 13;
				String cardValue = " ";
				
				switch(v)
				{
				case 0:
					cardValue = "A";
					break;
					
				case 1:
					cardValue = "2";
					break;
					
				case 2:
					cardValue = "3";
					break;
					
				case 3:
					cardValue = "4";
					break;
					
				case 4:
					cardValue = "5";
					break;
					
				case 5:
					cardValue = "6";
					break;
					
				case 6:
					cardValue = "7";
					break;
					
				case 7:
					cardValue = "8";
					break;
					
				case 8:
					cardValue = "9";
					break;
					
				case 9:
					cardValue = "T";
					break;
					
				case 10:
					cardValue = "J";
					break;
					
				case 11:
					cardValue = "Q";
					break;
					
				case 12:
					cardValue = "K";
					break;
				}
				
				String card = cardSuit.concat(cardValue);
				
				System.out.print(" " + card);
				
			}
			
			tempCardStack.pop();
		}
		
		System.out.println();
		
		//read in new new rows and columns
		n = input.nextInt();
		m = input.nextInt();
		
		}

	}

		//method to check that string is valid
		public static void validCard(String card)
		{
			
			if(card.charAt(0) == 'U' || card.charAt(0) == 'D')
			{
				
			}
			
			else
			{
				System.out.println("invalid O");
				System.exit(0);
			}
			
			if(card.charAt(1) == 'C' || card.charAt(1) == 'D' || 
					card.charAt(1) == 'H' || card.charAt(1) == 'S')
			{
				
			}
			
			else
			{
				System.out.println("invalid S");
				System.exit(0);
			}
			
			if(card.charAt(2) == '2' || card.charAt(2) == '3' || card.charAt(2) == '4' || 
					card.charAt(2) == '5' || card.charAt(2) == '6' || card.charAt(2) == '7' ||
					card.charAt(2) == '8' || card.charAt(2) == '9' || card.charAt(2) == 'T' ||   
					card.charAt(2) == 'J' || card.charAt(2) == 'Q' || card.charAt(2) == 'K' ||
					card.charAt(2) == 'A')
			{
				
			}
			
			else
			{
				System.out.println("invalid V");
				System.exit(0);
			}
			
			
		}
		
	
}
	


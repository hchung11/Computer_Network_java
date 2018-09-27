

import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.*;
import java.util.Random;


public class Server {
	//Socket - This is a gateway for a connection to be made
	static ServerSocket servers;
	//Socket - This is a gateway for a connection to be made.
	static Socket client;
	static Socket client2;
	static boolean gameOver = true;
	static boolean gamewin = true;
	static int cards[][]=new int[4][4];
	static boolean check[][]=new boolean[4][4];
	static boolean over;
	static int playerOnePoint = 0;
	static int playerTwoPoint = 0;
	static int sumOfTwoPlayers = 0;

	public static void main(String[] args) {

		try {
			System.out.println("This is Server started.");

			servers = new ServerSocket(7799);

			client = servers.accept();

			System.out.println("Player1 connected.");

			DataInputStream in = new DataInputStream(client.getInputStream());
			DataOutputStream out = new DataOutputStream(client.getOutputStream());
			

			client2 = servers.accept();
			System.out.println("Player2 connected.");
			DataInputStream in1 = new DataInputStream(client2.getInputStream());
			DataOutputStream out1 = new DataOutputStream(client2.getOutputStream());

		
			while (gameOver) {
				playerOnePoint = 0;
				playerTwoPoint = 0;
				sumOfTwoPlayers = 0;

				cards=shuffleDeck();
				//check 2d every boolean to false.
				for(int i1=0;i1<4;++i1)
				{
					
					for(int j=0;j<4;++j)
					{
						
						check[i1][j]=false;
					}
				}
				//out 1
				out.writeUTF("Player1 connected. Waiting for Player2");

				out1.writeUTF("player2 connected");
				do {
				//This dosen't make two different data send same time.
				boolean playerOne = true; 
				boolean playerTwo = false;
				out.writeBoolean(playerOne);
				out1.writeBoolean(playerTwo);
			

				
					//just print board to player1 and player2 
					out.writeUTF("   | A B C D ");
					out1.writeUTF("   | A B C D ");
					out.writeUTF("---+---------");
					out1.writeUTF("---+---------");
					for (int r=0; r<4; r++)
				      {
						out.writeUTF(" " +(r+1) +" | ");
						out1.writeUTF(" " +(r+1) +" | ");
				        for (int c=0; c<4; c++)
				        {
				          if (check[r][c] == true)
				          {
				        	  out.writeUTF(cards[r][c] +" ");
				        	  out1.writeUTF(cards[r][c] +" ");
				          }
				          else
				          {
				        	  out.writeUTF("* ");
				        	  out1.writeUTF("* ");
				          }
				        }
			
				      }
			
			
					
					
			
//pshow the anwswer.

				//test: show number
					System.out.println("Cheat key");
				System.out.println("   | A B C D ");
				System.out.println("---+---------");
				for(int i1=0;i1<4;++i1)
				{
					System.out.print(" " +(i1+1) +" | ");
					for(int j=0;j<4;++j)
					{
						System.out.print(cards[i1][j] +" ");

					}
					System.out.println();
				}
//-----------------------------------------------------------------------------
					
				out.writeUTF("Player 1: " + playerOnePoint);
				out1.writeUTF("Player 1: "+playerOnePoint);
				out.writeUTF("Player 2 "+playerTwoPoint);
				out1.writeUTF("Player 2: "+playerTwoPoint);		
				out.writeUTF("Flip over a tile and see if you can find a match!\n"
						+ "Flip which tile?\n"
						+ ">>>>>>> ");
				out1.writeUTF("Wait for player1....");
				

//=============================Player One Start=========================================				
				//where player1 enter first #
				String receive = in.readUTF();
				

				int y =  firstArray(receive);
				int x = secoundArray(receive);

				
				out.writeUTF("   | A B C D ");
				out.writeUTF("---+---------");
				for (int x1 = 0; x1 <4; x1++)
				{
					out.writeUTF(" " +(x1+1) +" | ");
					for (int y1=0; y1<4; y1++)
					{
						if ((x1==x)&&(y1==y))
						{
							out.writeUTF(cards[x][y] + " ");
						}else
						{
							out.writeUTF("* ");
						}
					}
				}

				//just print player1 board out to player2 
				out1.writeUTF("   | A B C D ");
				
				out1.writeUTF("---+---------");
				for (int x1 = 0; x1 <4; x1++)
				{
					out1.writeUTF(" " +(x1+1) +" | ");
					for (int y1=0; y1<4; y1++)
					{
						if ((x1==x)&&(y1==y))
						{
							out1.writeUTF(cards[x][y] + " ");
						}else
						{
							out1.writeUTF("* ");
						}
					}
				}
				
				//Where Player 1 Enter 2nd  
				out.writeUTF("Flip which tile? \n"
						+ ">>>>>>> ");
				out1.writeUTF("wating for Player1...");
				String receive1= in.readUTF();
				int y1 =  firstArray(receive1);
				int x1 = secoundArray(receive1);


				//print board with player1 2nd #
				out.writeUTF("   | A B C D ");
				out.writeUTF("---+---------");
				for (int x11 = 0; x11 <4; x11++)
				{
					out.writeUTF(" " +(x11+1) +" | ");
					for (int y11=0; y11<4; y11++)
					{
						if ((x11==x)&&(y11==y))
						{
							out.writeUTF(cards[x][y] + " ");
						}else if ((x11==x1)&&(y11==y1))
						{
							out.writeUTF(cards[x1][y1] + " ");
						}else
						{
							out.writeUTF("* ");
						}
					}
				}
				//just print player1 board to player2 Screen
				out1.writeUTF("   | A B C D ");
				out1.writeUTF("---+---------");
				for (int x11 = 0; x11 <4; x11++)
				{
					out1.writeUTF(" " +(x11+1) +" | ");
					for (int y11=0; y11<4; y11++)
					{
						if ((x11==x)&&(y11==y))
						{
							out1.writeUTF(cards[x][y] + " ");
						}else if ((x11==x1)&&(y11==y1))
						{
							out1.writeUTF(cards[x1][y1] + " ");
						}else
						{
							out1.writeUTF("* ");
						}
					}
				}
				
				if (cards[x][y]==cards[x1][y1])
				{
					out.writeUTF("Cards Matched!");
					playerOnePoint++;
					out1.writeUTF("Now your Turn!!!");
					check[x][y] = true;
					check[x1][y1] = true;
				}else
				{
					out.writeUTF("Those tiles didn’t match.");
					out1.writeUTF("Now your Turn!!!");
				}
				
//===============================PLAYER ONE TURN IS OVER================================
//===============================PLAYER TWO TURN===============================
			
			boolean	playerTwoT = true;
			out1.writeBoolean(playerTwoT);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			boolean	playerOneT = false;	
			out.writeBoolean(playerOneT);	
			
			
				
				out1.writeUTF("Flip over a tile and see if you can find a match!/n"
						+ "Flip which tile?\n"
						+ ">>>>>>> ");
				out.writeUTF("Wait for player2....");
				
				
				
				//Player 2 in from client
				String receive3 = in1.readUTF();
				

				int ys =  firstArray(receive3);
				int xs = secoundArray(receive3);

				
				out1.writeUTF("   | A B C D ");
				
				out1.writeUTF("---+---------");
				for (int x11 = 0; x11 <4; x11++)
				{
					out1.writeUTF(" " +(x11+1) +" | ");
					for (int y11=0; y11<4; y11++)
					{
						if ((x11==xs)&&(y11==ys))
						{
							out1.writeUTF(cards[xs][ys] + " ");
						}else
						{
							out1.writeUTF("* ");
						}
					}
				}

				//just print out Player1
				out.writeUTF("   | A B C D ");
				
				out.writeUTF("---+---------");
				for (int x11 = 0; x11 <4; x11++)
				{
					out.writeUTF(" " +(x11+1) +" | ");
					for (int y11=0; y11<4; y11++)
					{
						if ((x11==xs)&&(y11==ys))
						{
							out.writeUTF(cards[xs][ys] + " ");
						}else
						{
							out.writeUTF("* ");
						}
					}
				}
				
				//Player 2 2nd #
				out1.writeUTF("Flip which tile? \n"
						+ ">>>>>>> ");
				out.writeUTF("Wait for Player2");
				String receive4= in1.readUTF();
				int ySec =  firstArray(receive4);
				int xSec = secoundArray(receive4);


				//print secound.
				out1.writeUTF("   | A B C D ");
				out1.writeUTF("---+---------");
				for (int x11 = 0; x11 <4; x11++)
				{
					out1.writeUTF(" " +(x11+1) +" | ");
					for (int y11=0; y11<4; y11++)
					{
						if ((x11==xs)&&(y11==ys))
						{
							out1.writeUTF(cards[x11][y11] + " ");
						}else if ((x11==xSec)&&(y11==ySec))
						{
							out1.writeUTF(cards[x11][y11] + " ");
						} 
						else
						{
							out1.writeUTF("* ");
						}
					}
				}
				
				//print to player 1
				out.writeUTF("   | A B C D ");
				out.writeUTF("---+---------");
				for (int x11 = 0; x11 <4; x11++)
				{
					out.writeUTF(" " +(x11+1) +" | ");
					for (int y11=0; y11<4; y11++)
					{
						if ((x11==xs)&&(y11==ys))
						{
							out.writeUTF(cards[x11][y11] + " ");
						}else if ((x11==xSec)&&(y11==ySec))
						{
							out.writeUTF(cards[x11][y11] + " ");
						} 
						else
						{
							out.writeUTF("* ");
						}
					}
				}
				
				//check card is match
				if (cards[xs][ys]==cards[xSec][ySec])
				{
					out1.writeUTF("Cards Matched!");
					playerTwoPoint++;
					out.writeUTF("Now your Turn!!!");
					check[xs][ys] = true;
					check[xSec][ySec] = true;
				}else
				{
					out1.writeUTF("Those tiles didn’t match.");
					out.writeUTF("Now your Turn!!!");
				}
				
				//checks if all card are flipped
				over = false;
				sumOfTwoPlayers = playerOnePoint  + playerTwoPoint;
				
				//if every card i flipped automatically open new game.
				if (sumOfTwoPlayers == 8)
				{
					gameOver = false;
					over = true;
				}

				 out.writeBoolean(over);
		         out1.writeBoolean(over);
				}while(over != true);
				
				
			
			}
			//terminate the connection
			
			servers.close();
			client.close();
			client2.close();
			in.close();
			in1.close();
			out.close();
			out1.close();
		} 
		
		catch (IOException IOex) {
			// Let the end-user know that a problem has arisen. At this point, the
			// connection has been broken.
			System.out.println("Thank you for playing.");		
			}

	}

	//convert a,b,c,d => 1,2,3,4 interger
	public static int firstArray(String x) {
		int  y = 0 ;
		char alphbet [] = {'a','b','c','d'};
		for (int i = 0; i <4; i ++ )
		{
			if (alphbet[i] == x.charAt(0)) {
				y = i;
			}
		}
		return y;
	}

	//covert String 1,2,3,4 to interger
	public static int secoundArray(String x) {
		int y = 0;

		char numbers [] = {'1','2','3','4'};
		for (int i = 0; i <4; i ++ )
		{
			if (x.charAt(1) == numbers[i]) {
				y = i;
			}
		}
		return y;
	}
	//shffle number! 
	public static int[][] shuffleDeck(){
		int start[]={1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8};

		int cards[][]=new int[4][4];
		Random random=new Random();
		int temp,i;

		for (int s=0; s<=20; s++)
		{
			for (int x=0; x<16; x++)
			{
				i=random.nextInt(100000)%15;
				temp=start[x];
				start[x]=start[i];
				start[i]=temp;
			}
		}
		i=0;

		for (int r=0; r<4; r++)
		{
			for (int c=0; c<4; c++)
			{
				cards[r][c]=start[i];
				i=i+1;
			}
		}

		return cards;
	}
}



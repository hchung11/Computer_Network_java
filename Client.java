// Java implementation for a client
// Save file as Client.java

import java.io.*;
import java.net.*;
import java.util.Scanner;

// Client class
public class Client 
{

	static boolean over;
	static boolean gameOver = true;
	static String inputCardCorrect[][]=new String[][] {
		{"a1","a2","a3","a4"},
		{"b1","b2","b3","b4"},
		{"c1","c2","c3","c4"},
		{"d1","d2","d3","d4"}
	};
	public static void main(String[] args) throws IOException {


		boolean pass = true;
		String firstAnswer;
		String input = null;
		Socket s;


		try
		{
			Scanner scn = new Scanner(System.in);

			// getting localhost ip
			//            InetAddress ip = InetAddress.getByName("localhost");

			// establish the connection with server port 5056
			s = new Socket("127.0.0.1", 7799);

			// obtaining input and out streams

			DataInputStream in = new DataInputStream(s.getInputStream());
			DataOutputStream out = new DataOutputStream(s.getOutputStream());

			// the following loop performs the exchange of
			// information between client and client handler
			over = true;
			while (gameOver) 
			{



				//"Player1 Connet to server. Waiting for palyer 2...."); 
				//PLAYER2 ("Player2 Connet to Sever");
				System.out.println(in.readUTF());

				do {
					boolean clientLogin= in.readBoolean();

					//board
					System.out.println(in.readUTF());
					System.out.println(in.readUTF());

					for (int i = 0; i <4; ++i)
					{
						System.out.print(in.readUTF());
						for(int j=0;j<4;++j)
						{
							System.out.print(in.readUTF());
						}
						System.out.println();
					}



					System.out.println(in.readUTF());
					System.out.println(in.readUTF());
					System.out.println(in.readUTF());
					/**
					 * If Player1 is true only can Enter the input
					 *else Player 2 only get copy of Player1
					 */
					//this statement player one can enter.
					if (clientLogin)
					{
						//if player enter wrong key

						//ask player1 Enter	if user enter wrong input ask again.
						do {
							firstAnswer = scn.nextLine();

							for(int i1=0;i1<4;++i1)
							{

								for(int j=0;j<4;++j)
								{

									if(	 firstAnswer.equals(inputCardCorrect[i1][j]) ) {

										pass = false;

									}

								}

							}
							if(pass)
							{
							System.out.println("**Invalid input. Flip other which tile?**");
							}
						}while(pass);
						pass = true;
						//Send to Sever 
						out.writeUTF(firstAnswer);


						//Receive from sever
						System.out.println(in.readUTF());
						System.out.println(in.readUTF());
						for (int x1 = 0; x1 <4; x1++)
						{
							System.out.print(in.readUTF());
							for (int y1=0; y1<4; y1++)
							{
								System.out.print(in.readUTF());
							}
							System.out.println();
						}

					}else {


						//print to player2
						System.out.println(in.readUTF());
						System.out.println(in.readUTF());
						for (int x1 = 0; x1 <4; x1++)
						{
							System.out.print(in.readUTF());
							for (int y1=0; y1<4; y1++)
							{
								System.out.print(in.readUTF());
							}
							System.out.println(); 
						}

					}

					//ask 2nd to Player1
					if (clientLogin)
					{
						System.out.println(in.readUTF());
						do {
							input = scn.nextLine();

							for(int i1=0;i1<4;++i1)
							{

								for(int j=0;j<4;++j)
								{

									if(	 input.equals(inputCardCorrect[i1][j]) ) {

										pass = false;

									}

								}

							}
							if(pass)
							{
								System.out.println("**Invalid input. Flip other which tile?**\n"
										+ ">>>");							
								}
						}while(pass);
						pass = true;
						out.writeUTF(input);

						System.out.println(in.readUTF());
						System.out.println(in.readUTF());
						for (int x1 = 0; x1 <4; x1++)
						{
							System.out.print(in.readUTF());
							for (int y1=0; y1<4; y1++)
							{
								System.out.print(in.readUTF());

							}
							System.out.println();
						}

					}

					
					else 
					{
						//just print to Player2
						System.out.println(in.readUTF());
						System.out.println(in.readUTF());
						System.out.println(in.readUTF());
						for (int x1 = 0; x1 <4; x1++)
						{
							System.out.print(in.readUTF());
							for (int y1=0; y1<4; y1++)
							{
								System.out.print(in.readUTF());

							}
							System.out.println();
						}

					}



					//secound choose.

					//if is matched?

					System.out.println( in.readUTF());

					//===================================================
					boolean clientTwo= in.readBoolean();
					System.out.println(in.readUTF());
					if (clientTwo )
					{

						//ask player2
						String firstAnswer1;
						do 
						{
							firstAnswer1 = scn.nextLine();
							for(int i1=0;i1<4;++i1)
							{
								for(int j=0;j<4;++j)
								{
									if(	 firstAnswer1.equals(inputCardCorrect[i1][j]) ) 
									{
										pass = false;
									}
								}
							}
							if(pass)
							{
								System.out.println("**Invalid input. Flip other which tile?**\n"
										+ ">>>");							}
						}while(pass);
						pass = true;
						out.writeUTF(firstAnswer1);



						System.out.println(in.readUTF());
						System.out.println(in.readUTF());
						for (int x1 = 0; x1 <4; x1++)
						{
							System.out.print(in.readUTF());
							for (int y1=0; y1<4; y1++)
							{
								System.out.print(in.readUTF());
							}
							System.out.println();
						}
					}
					else 
					{
						//print to player2
						System.out.println(in.readUTF());
						System.out.println(in.readUTF());
						for (int x1 = 0; x1 <4; x1++)
						{
							System.out.print(in.readUTF());
							for (int y1=0; y1<4; y1++)
							{
								System.out.print(in.readUTF());
							}
							System.out.println(); 
						}
					}





					
					if (clientTwo)
					{
						String input1 ;
						System.out.println(in.readUTF()); 	
						do 
						{
							input1 = scn.nextLine();
							for(int i1=0;i1<4;++i1)
							{
								for(int j=0;j<4;++j)
								{
									if(	 input1.equals(inputCardCorrect[i1][j]) ) {
										pass = false;
									}
								}
							}
							if(pass)
							{
							System.out.println("**Invalid input. Flip other which tile?**\n"
									+ ">>>");
							}
						}while(pass);
						pass = true;


						out.writeUTF(input1);

						System.out.println(in.readUTF());
						System.out.println(in.readUTF());
						for (int x1 = 0; x1 <4; x1++)
						{
							System.out.print(in.readUTF());
							for (int y1=0; y1<4; y1++)
							{
								System.out.print(in.readUTF());

							}
							System.out.println();
						}
						System.out.println(in.readUTF());


					}
					else 
					{
						//just print to Player1
						System.out.println(in.readUTF());
						System.out.println(in.readUTF());
						for (int x1 = 0; x1 <4; x1++)
						{
							System.out.print(in.readUTF());
							for (int y1=0; y1<4; y1++)
							{
								System.out.print(in.readUTF());

							}
							System.out.println();
						}
						System.out.println(in.readUTF());
						System.out.println(in.readUTF());
					}


					//secound choose.

					//if is matched?

					over = in.readBoolean();
					if (over) {
						System.out.print("==============GAME OVER==============");
						gameOver = false;
					}
				}while(over != true);
				
				
		
			}

			// closing resources
			scn.close();
			s.close();
			in.close();
			out.close();


		}catch(Exception e){
//			e.printStackTrace();
			System.out.println("Sever and client disconnect!!!");
		}

	}


}



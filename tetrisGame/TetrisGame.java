package tetrisGame;

import java.util.Arrays;
import java.util.Scanner;

public class TetrisGame {
	static Scanner s=new Scanner(System.in);
	static int n=8;
	static char[][] board = new char[n][n+n];

	public static void main(String[] args) {
		
		System.out.println("====== Tertis Game ======");
		System.out.println();
		int inp=0;
		do {
			System.out.println("\t1. Start Game");
			System.out.println("\t2. Exit");
			inp=s.nextInt();
			if(inp==1)
				startGame();
			else if(inp==2)
				System.out.println("Thank You");
			else
				System.out.println("Enter Valid Input");
		}while(inp!=2);
	}
	
	// Game Start
	static void startGame() {
		for(int i=0; i<n; i++) {
			Arrays.fill(board[i], ' ');
		}
		// Shapes
		char[][] shape1=shape1();   
		char[][] shape2=shape2();	 
		char[][] shape3=shape3();	 
		char[][] shape4=shape4();  
		char[][] shape5=shape5();  
		
		
		while(true) {
			
			System.out.println("Shape 1 :");
			printShape(shape1);
			System.out.println("Shape 2 :");
			printShape(shape2);
			System.out.println("Shape 3 :");
			printShape(shape3);
			System.out.println("Shape 4 :");
			printShape(shape4);
			System.out.println("Shape 5 :");
			printShape(shape5);
			
			System.out.println("Enter Shape Number : ");
			int inp=s.nextInt();
			if(inp<1||inp>5) {
				System.out.println("Enter Valid Input");
				startGame();
			}
			System.out.println("Number of rotate shape : ");
			int rotate=s.nextInt();
			char[][] rotateArray=rotate((inp==1)?shape1:(inp==2)?shape2:(inp==3)?shape3:(inp==4)?shape4:shape5, rotate);
			printShape(rotateArray);
			print();
			System.out.println("Enter Column : (1-16)");
			int col=s.nextInt();
			if(col<1||col>16) {
				System.out.println("Enter Valid Input");
				startGame();
			}
			col-=1;
			if(!setShape(rotateArray, col)) {
				print();
				System.out.println();
				System.out.println("GAME OVER !");
				System.out.println();
				return;
			}
			checkRow();
			print();
			
		}
	}
	
	// check the row full of bricks
	static void checkRow() {
		for(int i=n-1; i>=0; i--) {
			int count=0;
			for(int j=0; j<n+n; j++) {
				if(board[i][j]=='#') count++;
			}
			if(count==(n+n)) {
				// if the row full of bricks, then remove
				removeRow(i);
				i++;
			}
		}
	}
	
	// remove the row 
	static void removeRow(int row) {
		if(row==0) {
			for(int i=0; i<n+n; i++) {
				board[0][i]=' ';
			}
		}
		else {
			for(int i=row; i>0; i--) {
				for(int j=0; j<n+n; j++) {
					board[i][j]=board[i-1][j];
				}
			}
			for(int i=0; i<n+n; i++) {
				board[0][i]=' ';
			}
		}
	}
	
	// set the shape into the board
	static boolean setShape(char[][] shape, int col) {
		int edge=0;
		for(int i=3; i>=0; i--) {
			if(shape[0][i]=='#'||shape[1][i]=='#'||shape[2][i]=='#'||shape[3][i]=='#') {
				edge=i;
				break;
			}
		}
		
		if(col+edge>=n+n) {
			col-=(col+edge)-(n+n)+1;
		}
		
		for(int i=0; i<n; i++) {
			if(!check(shape, i, col)) { 
				System.out.println(i);
				int count=0;
				int ind=3;
				for(int j=i-1; j>=0&&ind>=0; j--, ind--) {
					for(int k=0; k<4; k++) {
						if(shape[ind][k]=='#') {
							board[j][col+k]='#';
							count++;
						}
					}
				}
				if(count!=4) return false;
				break;
			}
			if(i==n-1) {
				int ind=3;
				for(int j=i; j>=0&&ind>=0; j--, ind--) {
					for(int k=0; k<4; k++) {
						if(shape[ind][k]=='#') board[j][col+k]='#';						
					}
				}
			}
		}
		return true;
		
	}

	// check the input row have any # in the shape size 
	static boolean check(char[][] shape, int ind, int col) {
		for(int i=3; i>=0; i--, ind--) {
			if(ind<0)return true;
			for(int j=0; j<4; j++) {
				if(col+j>=n+n) {
					return true;
				}
				if(board[ind][col+j]=='#'&&shape[i][j]=='#') return false;
			}
		}
		return true;
	}
	
	// Rotate the shape 
	static char[][] rotate(char[][] shape, int count) {
		count%=4;
		// change into another array
		char[][] arr=new char[4][4];
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				arr[i][j]=shape[i][j];
			}
		}
		
		// Rotate the Shape
		while(count-- > 0) {
			for(int i=0; i<4; i++) {
				for(int j=i; j<4; j++) {
					char temp=arr[i][j];
					arr[i][j]=arr[j][i];
					arr[j][i]=temp;
				}
			}
			for(int i=0; i<4; i++) {
				int l=0, r=3;
				while(l<r) {
					char temp=arr[i][l];
					arr[i][l++]=arr[i][r];
					arr[i][r--]=temp;
					
				}
			}
		}

		// Remove empty space in columns
		while(true) {
			if(arr[0][0]=='\0'&&arr[1][0]=='\0'&&arr[2][0]=='\0'&&arr[3][0]=='\0') {
				for(int i=1; i<4; i++) {
					arr[0][i-1]=arr[0][i];
					arr[1][i-1]=arr[1][i];
					arr[2][i-1]=arr[2][i];
					arr[3][i-1]=arr[3][i];
				}
				arr[0][3]='\0';
				arr[1][3]='\0';
				arr[2][3]='\0';
				arr[3][3]='\0';
			}
			else break;
		}
		
		// Remove empty space in rows
		while(true) {
			if(arr[3][0]=='\0'&&arr[3][1]=='\0'&&arr[3][2]=='\0'&&arr[3][3]=='\0') {
				for(int i=2; i>=0; i--) {
					arr[i+1][0]=arr[i][0];
					arr[i+1][1]=arr[i][1];
					arr[i+1][2]=arr[i][2];
					arr[i+1][3]=arr[i][3];
				}
				arr[0][0]='\0';
				arr[0][1]='\0';
				arr[0][2]='\0';
				arr[0][3]='\0';
			}
			else break;
		}

		return arr;
	}
	
	// Shapes 
	static char[][] shape1() {
		char[][] arr=new char[4][4];
		arr[0][0]='#';
		arr[0][1]='#';
		arr[1][0]='#';
		arr[1][1]='#';
		return arr;
	}
	
	static char[][] shape2() {
		char[][] arr=new char[4][4];
		arr[0][0]='#';
		arr[1][0]='#';
		arr[2][0]='#';
		arr[2][1]='#';
		return arr;
	}
	
	static char[][] shape3() {
		char[][] arr=new char[4][4];
		arr[0][1]='#';
		arr[1][1]='#';
		arr[1][0]='#';
		arr[2][0]='#';
		return arr;
	}
	
	static char[][] shape4() {
		char[][] arr=new char[4][4];
		arr[0][0]='#';
		arr[1][0]='#';
		arr[2][0]='#';
		arr[3][0]='#';
		return arr;
	}
	
	static char[][] shape5() {
		char[][] arr=new char[4][4];
		arr[0][0]='#';
		arr[1][0]='#';
		arr[1][1]='#';
		arr[2][0]='#';
		return arr;
	}
	
	// print the shape
	static void printShape(char[][] shape) {
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				System.out.print(shape[i][j]);
			}
			System.out.println();
		}
	}
	
	// print the board
	static void print() {
		for(int i=0; i<n; i++) {
			System.out.print("|");
			for(int j=0; j<n+n; j++) {
				System.out.print(board[i][j]);
			}
			System.out.print("|");
			System.out.println();
		}
		System.out.println("|----------------|");
		System.out.println();
	}

}

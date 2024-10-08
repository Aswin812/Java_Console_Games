package Game;
import java.util.Scanner;
public class PlayerToComputer {
	static Scanner sc=new Scanner(System.in);
	public static void gameStart() {
		int n=7;
		char[][] board=new char[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				board[i][j]=' ';
			}
		}
		
		print(board);
		int count=n*n;
		boolean win=false;
		char player='X';
		char computer='O';
		
		while(!win && count>0) {
			count--;
			System.out.println("Enter Numner (1-7) : ");
			int input=sc.nextInt()-1;
			
			if(input<n && input>=0 && board[0][input]==' ')board[0][input]=player;
			else {
				System.out.println("Enter Valid index");
				count++;
				continue;
			}
			
			assign(board, input, player);
			win=checkWin(board, player);
			if(win) {
				print(board);
				System.out.println("player win...");
				break;
			}
			else {
				if(computer(board, computer, player)) {
					if(checkWin(board, computer)) {
						print(board);
						System.out.println("Computer Win...");
						break;						
					}
				}
				else {
					int com=0;
					while(true) {
						com+=Math.round(Math.random()*n);
						System.out.println(com);
						if(com<n && com>=0 && board[0][com]==' ') {
							board[0][com]=computer;
							break;
						}
						else {
							com=0;
							continue;
						}
					}
					assign(board, com, computer);
				}
			}
			print(board);
		}
	}
	
	public static void assign(char[][] arr, int index, char player) {
		for(int i=1; i<arr.length; i++) {
			if(arr[i][index]==' ') {
				arr[i-1][index]=' ';
				arr[i][index]=player;
			}
		}
	}
	
	public static boolean checkWin(char[][] arr, char player) {
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr.length; j++) {
				if(helper(arr, i, j, 0, 1, 4, player) || helper(arr, i, j, 1, 0, 4, player) || helper(arr, i, j, 1, 1, 4, player) || helper(arr, i, j, 1, -1, 4, player)) {
					return true;
				}
			}
		}
		return false;
	}
	 
	public static boolean helper(char[][] board, int i, int j, int x, int y, int target, char player) {
		if(target==0)return true;
		if(i>=0&&i<board.length&&j>=0&&j<board.length&&board[i][j]==player) {
			return helper(board, i+x, j+y, x, y, target-1, player);
		}
		return false;
	}
	
	public static boolean computer(char[][] board, char computer, char player) {
		for(int i=0; i<board.length; i++) {
			if(board[0][i]==' ') {
				board[0][i]=computer;
				assign(board, i, computer);
				if(checkWin(board, computer)) {
					return true;
				}
				delete(board, i);
				board[0][i]=player;
				assign(board, i, player);
				
				if(checkWin(board, player)) {
					for(int j=0; j<board.length; j++) {
						if(board[j][i]!=' ') {
							board[j][i]=computer;
							break;
						}
					}
					return true;
				}
				else {
					delete(board, i);
				}
			}
		}
		return false;
	}
	
	public static void delete(char[][] board, int index) {
		for(int i=0; i<board.length; i++) {
			if(board[i][index]!=' ') {
				board[i][index]=' ';
				return;
			}
		}
	}
	
	public static void print(char[][] board) {
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board.length; j++) {
				System.out.print(board[i][j]+" |");
			}
			System.out.println();
		}
	}

}

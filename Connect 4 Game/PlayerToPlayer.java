package Game;
import java.util.Scanner;
public class PlayerToPlayer {
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
		while(!win && count>0) {
			count--;
			System.out.println("Player "+player);
			System.out.println("Enter Number (1-7) : ");
			int input=sc.nextInt()-1;
			
			if(board[0][input]==' ')board[0][input]=player;
			else {
				System.out.println("Enter Valid index");
				count++;
				continue;
			}
			
			assign(board, input, player);
			print(board);
			win=check(board, player);
			if(win) {
				System.out.println("player "+player+" win...");
				break;
			}
			else {
				player=(player=='X')?'O':'X';
			}
			
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
	
	public static boolean check(char[][] arr, char player) {
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
	
	public static void print(char[][] arr) {
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr.length; j++) {
				System.out.print(arr[i][j]+" |");
			}
			System.out.println();
		}
	}
	
}

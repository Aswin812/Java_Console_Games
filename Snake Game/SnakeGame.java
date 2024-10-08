package game;

import java.util.*;

public class SnakeGame {
	static Scanner s=new Scanner(System.in);
	public static void main(String[] args) {
		int n=8;
		char[][] board=new char[n][n*2];
		List<List<Integer>> snake=new ArrayList<>();
		for(int i=0; i<n; i++) {
			for(int j=0; j<n*2; j++) {
				if(i==n/2&&j==n) {
					ArrayList<Integer> list=new ArrayList<>();
					list.add(i);
					list.add(j);
					snake.add(new ArrayList<>(list));
					board[i][j]='*';
					continue;
				}
				board[i][j]='.';
			}
		}
		System.out.println(snake);
		boolean flag=false;
		do {
			if(!flag)flag=food(board, n);
			print(board, n);
			inputPrint();
			char input=s.nextLine().charAt(0);
			flag=nextMove(input, snake, board);
			System.out.println(snake);
		}while(true);
	}
	
	public static boolean nextMove(char ch, List<List<Integer>> snake, char[][] board) {
		int n=snake.size()-1;
		int len1=board.length-1;
		int len2=(len1+1)*2-1;
		int headInd1=snake.get(0).get(0);
		int headInd2=snake.get(0).get(1);
		switch(ch) {
		case 'U':
			headInd1-=1;
			if(headInd1<0) {
				headInd1=len1;
			}
			break;
		case 'D':
			headInd1+=1;
			if(headInd1>len1) {
				headInd1=0;
			}
			break;
		case 'R':
			headInd2+=1;
			if(headInd2>len2) {
				headInd2=0;
			}
			break;
		case 'L':
			headInd2-=1;
			if(headInd2<0) {
				headInd2=len2;
			}
			break;
		default:
			System.out.println("Enter Valid Input...");
		}
		if(board[headInd1][headInd2]=='*') {
			System.out.println("Game Over !");
			System.exit(0);
		}
		else if(board[headInd1][headInd2]=='#') {
			board[headInd1][headInd2]='*';
			List<Integer> list=new ArrayList<>();
			list.add(headInd1);
			list.add(headInd2);
			snake.add(0,new ArrayList<>(list));
			return false;
		}
		else {
			board[headInd1][headInd2]='*';
			board[snake.get(n).get(0)][snake.get(n).get(1)]='.';
			snake.remove(n);
			List<Integer> list=new ArrayList<>();
			list.add(headInd1);
			list.add(headInd2);
			snake.add(0,new ArrayList<>(list));
		}
		
		return true;
		
	}
	
	public static boolean food(char[][] board, int n) {
		int ind1=(int) (Math.random()*n);
		int ind2=(int) (Math.random()*n*2);
		if(board[ind1][ind2]=='.') {
			board[ind1][ind2]='#';
		}
		else food(board, n);
		return false;
	}
	
	public static void inputPrint() {
		System.out.println("\t U -> Up");
		System.out.println("\t D -> Down");
		System.out.println("\t R -> Right");
		System.out.println("\t L -> Left");
	}
	
	
	
	public static void print(char[][] arr, int n) {
		for(int i=0; i<n; i++) {
			System.out.print("|");
			for(int j=0; j<n*2; j++) {
				System.out.print(arr[i][j]);
			}
			System.out.print("|");
			System.out.println();
		}
	}

}

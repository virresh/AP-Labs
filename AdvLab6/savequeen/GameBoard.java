package savequeen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;

public class GameBoard {
	
	static LinkedList<Knight> a;
	static Coordinates Queen;
	static Scanner s;
	public static void main(String[] args) throws StackEmptyException, OverlapException, NonCoordinateException, QueenFoundException {
		/**
		 * To simulate the game
		 */
		s = new Scanner (System.in);
		a = new LinkedList<Knight>();
		File file = new File("./src/output.txt");
		
		System.out.print("1. Enter the total number of knights - ");
		int N = s.nextInt();
		System.out.print("2. The total number of iterations - ");
		int I = s.nextInt();
		System.out.print("3. Coordinates of queen x and y - ");
		int x = s.nextInt(), y = s.nextInt();
		Queen = new Coordinates(x,y);
		
		for(int i=1; i<=N; i++){
			Knight l = new Knight(i);
			a.addLast(l);
		}
		Collections.sort(a);
		
		try{
			PrintWriter w = new PrintWriter(file, "UTF-8");

			for(int i=1; i<=I; i++){
				for(int k = 0; k < a.size(); k++){
					String l = null;
					Knight t = a.get(k);
					w.println(i+" "+t.name+ " " +t.c);
					try{
						l = t.pollOne();
					}
					catch(NonCoordinateException e){
						w.println(e.getMessage());
						continue;
					}
					catch(StackEmptyException e){
						w.println(e.getMessage());
						a.remove(k);
						k--;
						continue;
					}
					catch(OverlapException e){
						w.println(e.getMessage());
						if(k>=a.size() || a.get(k) != t)
							k--;
						continue;
					}
					catch(QueenFoundException e){
						w.println(e.getMessage());
						w.close();
						return;
					}
					w.println("No​ ​ exception " + l);
				}
			}
			w.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}	
	}
}


class Node <T> {
	T value;
	String type;
	Node(T d, String s){
		value = d;
		type = s;	
	}
	public String toString(){
		return value.toString();
	}
}

class Coordinates {
	int x,y;
	Coordinates(int a,int b){
		x = a;
		y = b;
	}
	
	@Override
	public String toString(){
		return x+" "+y;
	}
	
	boolean equals(Coordinates b){
		if(x == b.x && y == b.y){
			return true;
		}
		return  false;
	}
	
}


class Knight implements Comparable<Knight>{
	Stack <Node> magicBox;
	String name;
	int sSiz;
	Coordinates c;
	
	void checkDuplicate() throws OverlapException{
		int x =-1;
		for(int i=0; i<GameBoard.a.size(); i++){
			if(GameBoard.a.get(i) !=this){
				if(this.c.equals(GameBoard.a.get(i).c)){
					x = i;
					break;
				}
			}
		}
		if(x==-1){
			return;
		}
		String l = GameBoard.a.get(x).name;
		GameBoard.a.remove(x);
		throw new OverlapException(l);
	}
	
	
	String pollOne() throws NonCoordinateException, StackEmptyException, OverlapException, QueenFoundException{
		
		if(magicBox.isEmpty()){
			throw new StackEmptyException();
		}
		
		if(magicBox.peek().type.equals("Integer")){
			String p = magicBox.peek().toString();
			magicBox.pop();
			sSiz--;
			throw new NonCoordinateException(p);
		}
		else if(magicBox.peek().type.equals("Float")){
			String p = magicBox.peek().toString();
			magicBox.pop();
			sSiz--;
			throw new NonCoordinateException(p);
		}
		else if(magicBox.peek().type.equals("String")){
			String p = magicBox.peek().toString();
			magicBox.pop();
			sSiz--;
			throw new NonCoordinateException(p);
		}
		else if(magicBox.peek().type.equals("Coordinate")){
//			String p = magicBox.peek().toString();
			Node x = magicBox.peek();
			magicBox.pop();
			c = (Coordinates)x.value;
			if(c.equals(GameBoard.Queen)){
				throw new QueenFoundException();
			}
			checkDuplicate();
			sSiz--;
			return c.toString();
		}
		return null;
	}
	
	Knight(int n){
		// construct this knight;
		magicBox = new Stack<Node>();
		File file = new File("./src/"+n + ".txt");
		Scanner s = null;
		try{
			s = new Scanner(file);
		}
		catch(FileNotFoundException e){
			System.out.println("No such knight exists "+n);
			e.printStackTrace();
			throw new NullPointerException("Cannot construct the knight.");
		}
		try {
			name = s.nextLine();
			String t = s.nextLine();
			c = new Coordinates(Integer.parseInt(t.split(" ")[0]),Integer.parseInt(t.split(" ")[1]));
			sSiz = Integer.parseInt((s.nextLine()));
//			s.nextLine();
			for(int i=0; i<sSiz; i++)
			{
				String typ = s.next();
				if(typ.equals("Integer")){
					Node <Integer> nd = new Node<Integer>(s.nextInt(), typ);
					magicBox.push(nd);
				}
				else if(typ.equals("Float")){
					Node <Float> nd = new Node<Float>(s.nextFloat(), typ);
					magicBox.push(nd);
				}
				else if(typ.equals("Coordinate")){
					Node <Coordinates> nd = new Node<Coordinates>(new Coordinates(s.nextInt(),s.nextInt()), typ);
					magicBox.push(nd);
				}
				else if(typ.equals("String")){
					Node <String> nd = new Node<String>(s.next(), typ);
					magicBox.push(nd);
				}
			}
			s.close();
		}
		catch(NoSuchElementException e){
			System.out.println(magicBox.size() + " " + sSiz);
			e.printStackTrace();
		}	
		catch (Exception e){
			System.out.println("Could Not Construct the knight.");
			e.printStackTrace();
		}
	}
	
	void print(){
		System.out.println(name + " " + sSiz);
	}

	@Override
	public int compareTo(Knight o) {
		// TODO Auto-generated method stub
		return name.compareTo(o.name);
	}
}

class NonCoordinateException extends Exception {

	/**
	 * Not a Coordinate Exception
	 */
	private static final long serialVersionUID = -5687799728031877993L;
	
	NonCoordinateException(String s){
		super("NonCoordinateException: ​ Not a coordinate Exception "+s);
	}
}

class OverlapException extends Exception{

	/**
	 * Used when A knight overlaps another knight
	 */
	private static final long serialVersionUID = -3891810959218249578L;
	
	OverlapException(String s){
		super("OverlapException:​ ​ Knights​ ​ Overlap​ ​ Exception " +s);
	}

}

class QueenFoundException extends Exception {

	/**
	 * Queen was found, time to Abort
	 */
	private static final long serialVersionUID = -6191396205249411758L;
	
	QueenFoundException(){
		super("QueenFoundException:​ ​ Queen​ ​ has​ ​ been​ ​ Found.​ ​ Abort!");
	}
	
}

class StackEmptyException extends Exception {

	/**
	 * Stack has become empty exception
	 */
	private static final long serialVersionUID = -4701974171114375109L;
	StackEmptyException(){
		super("StackEmptyException:​ ​ Stack​ ​ Empty​ ​ exception.");
	}
	
	StackEmptyException(String s){
		super("StackEmptyException:​ ​ Stack​ ​ Empty​ ​ exception "+s);
	}
}


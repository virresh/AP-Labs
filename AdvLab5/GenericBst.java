import java.util.ArrayList;

interface testInterface <T>{
	void add(T a);
	void inorderTraversal();
	T getEle(int i);
	int getLoc();
}

class Node<E extends Comparable<E> > implements Comparable<Node <E> >{
	E data;
	Node <E> left;
	Node <E> right;
	Node(E d){
		data = d;
		left = null;
		right = null;
	}
	@Override
	public int compareTo(Node<E> o) {
		return data.compareTo(o.data);
	}
	
	@Override
	public String toString(){
		return data + "";
	}
}

public class GenericBst <T extends Comparable<T> > implements testInterface <T> {
	Node <T> root;
	T val;
	ArrayList<T> arr;
	
	int NumNodes;
	int location;
	static int numTest;
	
	GenericBst(int x){
		NumNodes = x;
		root = null;
		val = null;
		arr = new ArrayList<T>();
		location = 0;
	}
	
	@Override
	public void add(T data){
		root = add(root,data);
	}
	
	Node<T> add(Node<T> r,T data){
		if(r==null){
			r = new Node<T>(data);
		}
		else if(r.data.compareTo(data)>0){
			r.left = add(r.left,data);
		}
		else{
			r.right = add(r.right,data);
		}
		return r;
	}
	
	void inorderTraversal(Node<T> root){
		numTest++;
		if(root.left != null){
			inorderTraversal(root.left);
		}
		
//		System.out.println(root);
		arr.add(root.data);
		if(root == this.root){
			location = numTest;
		}
		
		if(root.right != null){
			inorderTraversal(root.right);
		}
	}
	
	public void inorderTraversal(){
		numTest =0;
		inorderTraversal(root);
	}
	
	@Override
	public T getEle(int i){
		return arr.get(i);
	}

	@Override
	public int getLoc() {
		return location;
	}
}

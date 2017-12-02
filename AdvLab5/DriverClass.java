import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class DriverClass {
	
	static ArrayList<ArrayList<String>> a;
	
	static void fileRead(String x){
		File file = new File(x);
		Scanner sc;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e1) {
			System.out.println(x);
			return;
		}
		String s = sc.nextLine();
//		sc.nextLine();
		int n = sc.nextInt();
		for(int i=0; i<=n; i++){
			a.add(new ArrayList<String>());
		}
		testInterface g = null;
		if(s.equals("String")){
			g = new GenericBst <String>(n);
		}
		else if(s.equals("Integer")){
			System.out.println("IntegerFound");
			g = new GenericBst <Integer>(n);
		}
		else if(s.equals("Float")){
			g = new GenericBst <Float>(n);
		}
		if(g==null){
			System.out.println("Not Instantiated yet " + s);
		}
	    try {

	        while (sc.hasNextLine()) {
	            String inp = sc.next();
//	            System.out.println(inp);
	            if(s.equals("String")){
	            	g.add(inp);
	            }
	            else if(s.equals("Integer")){
	            	g.add((Integer.parseInt(inp)));
	            }
	            else if(s.equals("Float")){
	            	g.add((Float.parseFloat(inp)));
	            }	            
	        }
	        sc.close();
	    } 
	    catch (Exception e) {
	        
	    }
	    g.inorderTraversal();
	    Object o2 = g.getEle(0);
	    
	    for(int i=0; i<n; i++)
	    {
	    	if(o2.getClass()== "".getClass()){
		    	if(i==0)
		    	{o2 = "";}
		    	o2 = (String)o2 + (String)g.getEle(i);
		    }
		    else if(o2.getClass()== new Integer(1).getClass()){
		    	if(i==0)
		    	{o2 = 0;}
		    	o2 = (Integer)((Integer)(o2) + (Integer)(g.getEle(i)));
		    }
		    else if(o2.getClass()== new Float(1).getClass()){
		    	if(i==0)
		    	{o2 = 0.0;}
		    	o2 = (Float)((Float.parseFloat(o2.toString())) + (Float.parseFloat(g.getEle(i).toString())));
		    }
	    }
	    
	    int p = g.getLoc();
	    a.get(p).add(o2.toString());
	}
	
	static void inorderTraversal(Node<?> root){
		if(root.left != null){
			inorderTraversal(root.left);
		}
		
		System.out.println(root.data);
		if(root.right != null){
			inorderTraversal(root.right);
		}
	}
	
	public static void main(String[] args) {
		BSTFilesBuilder x = new BSTFilesBuilder();
		Scanner sca = new Scanner (System.in);
		int numStu=sca.nextInt(), numTrees=sca.nextInt();
		sca.close();
		a = new ArrayList<ArrayList<String>>(numStu+1);
//		x.createBSTFiles(numStu, numTrees);
		for(int i=1; i<=numTrees; i++){
			String s = "./src/"+i+".txt";
			fileRead(s);
		}
		try {
			PrintWriter w = new PrintWriter("./src/answer.txt", "UTF-8");
			int count=0;
			for(int i=1; i<=numStu; i++)
			{
				if(a.get(i).size()!=0){
					System.out.print(a.get(i).size() + " " + i + " ");
					count++;
					w.print(i);
					for(int j=0; j<a.get(i).size(); j++){
						System.out.print(" " + a.get(i).get(j) );
						w.print(" " + a.get(i).get(j) );
					}
					System.out.println("");
					w.println("");
				}
			}
			w.println(""+(numStu-count)+"\n");
			w.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}

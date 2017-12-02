package music;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayList implements Serializable{
	/**
	 * PlayList class whose database will be stored and saved
	 */
	private static final long serialVersionUID = -4912961299744019309L;
	ArrayList<Song> arr;
	String pName;
	public PlayList(String x){
		arr = new ArrayList<Song>();
		pName = x;
	}
	public PlayList(ArrayList<Song> a, String x){
		arr = a;
		pName = x;
	}

	public String add(Song s){
		String p ="";
		arr.add(s);
//		System.out.println("Current Size of the PlayList : "+arr.size());
		p = p + "Current Size of the PlayList : "+arr.size();
		return p;
	}

	public String remove(String n){
		String p = "";
		boolean rem = false;
		for(int i=0; i<arr.size(); i++){
			if(arr.get(i).getName().equals(n)){
				arr.remove(i);
				rem = true;
				break;
			}
		}
		if(rem == true){
//			System.out.println("Current Size of the PlayList : "+arr.size());
			p = p + "Current Size of the PlayList : "+arr.size();
		}
		else{
//			System.out.println("No such song found in this playlist.");
			p = p + "No such song found in this playlist.";
		}
		return p;
	}
	
	public Song search(String n){
		for(int i=0; i<arr.size(); i++){
			if(arr.get(i).getName().equals(n)){
				return arr.get(i);
			}
		}
		return null;
	}
	
	int getSize(){
		return arr.size();
	}

	public String show(){
		String s="";
		if(arr.isEmpty()){
//			System.out.println("No Song Exists.");
			s= "No Song Exists.";
			return s;
		}
		for(int i=0; i<arr.size(); i++){
//			System.out.println(arr.get(i));
//			System.out.println("");
			s = s+arr.get(i).toString()+"\n\n";
		}
		return s;
	}
}


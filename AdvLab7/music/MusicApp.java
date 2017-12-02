package music;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Name - Viresh Gupta
 * Roll No - 2016118
 * Assumption - All names in a playlist are Unique
 * 
 */


public class MusicApp {

	static Scanner s;
	static String fileLoc;
	static ArrayList<File> fileList(String d){
		File folder = new File(d);
		File[] listOfFiles = folder.listFiles();
		ArrayList<File> songfiles = new ArrayList<File>();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				songfiles.add(listOfFiles[i]);
			}
		}
		return songfiles;
	}
	
	static void makeTestFiles(){
		/**
		 * Make new Database File
		 */
		PlayList pl = getNewPlayList();
		saveToDatabase(pl);
		
	}
	
	static PlayList getNewPlayList(){
		System.out.println("Give Name of the Playlist: ");
		String p = s.nextLine();
		System.out.println("Enter Songs: ");
		PlayList pl = new PlayList(p);
		while(true){
			Song sng = getNewSong();
			pl.add(sng);
			System.out.println("Want to add more Songs ? (y/n) ");
			String k = s.next();
			if(k.equals("n")){
				break;
			}
			else{
				s.nextLine();
			}
		}
		return pl;
	}
	
	static Song getNewSong(){
		System.out.println("Enter Song Name: ");
		String x = s.nextLine();
		System.out.println("Enter Singer: ");
		String y = s.nextLine();
		System.out.println("Enter Duration: ");
		int z = s.nextInt();
		s.nextLine();
		Song l = new Song(x,y,z);
		return l;
	}
	
	private static PlayList selectPlaylistfromDatabase(){
		readDatabase();
		while(true){
			System.out.println("\nEnter the name of PlayList you want to Select: ");
			String x = s.nextLine();
			PlayList p = loadPlayListFromDatabase(x);
			if(p!=null){
				return p;
			}
			else{
				System.out.println("PlayList not found. Want to try Again ?(y/n)");
				String y = s.nextLine();
				if(y.equals("n")){
					break;
				}
			}
		}
		return null;
	}
	
	static void workOnDatabase(){
		while(true){
			System.out.println("Welcome to Music App. Choose from following: ");
			System.out.println("1. Select a play list.");
			System.out.println("2. Exit.");
			System.out.println("3. Make new play list. "); 
			int n = s.nextInt();
			s.nextLine();
			if(n==1){
				System.out.println("The current Playlists in the Database are : ");
				PlayList p = selectPlaylistfromDatabase();
				workWithPlayList(p);
				
			}
			else if(n==2){
				break;
			}
			else if(n==3){
				makeTestFiles();
			}
			else{
				System.out.println("No such option. Want to choose again ?(y/n)");
				String y = s.nextLine();
				if(y.equals("n")){
					break;
				}
			}
			System.out.println("\n\n");
		}
	}
	
	public static void main(String[] args) {
		fileLoc = "./src/Playlists/database1";
		s = new Scanner (System.in);
		workOnDatabase();
	}

	private static ArrayList<String> readDatabase() {
		PlayList p = null;
		ArrayList<String> ar = new ArrayList<String>();
		try {
			ObjectInputStream obr = new ObjectInputStream( new FileInputStream(fileLoc));
			while((p = (PlayList)obr.readObject())!=null){
				System.out.println(p.pName);
				ar.add(p.pName);
			}
			obr.close();
		} catch (EOFException e){
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		
		return ar;
	}

	private static void workWithPlayList(PlayList p) {
		while(true){		
			System.out.println("Select from the following: ");
			System.out.println("1. Add");
			System.out.println("2. Delete");
			System.out.println("3. Search");
			System.out.println("4. Show");
			System.out.println("5. Back to Menu");
			System.out.println("6. Exit");
			int c = s.nextInt();
			s.nextLine();
			if(c==6){
				saveToDatabase(p);
				System.exit(0);
			}
			else if(c==5){
				saveToDatabase(p);
				return;
			}
			else if(c==4){
				System.out.println(p.show());
			}
			else if(c==3){
				String songName;
				System.out.println("Enter Song name to search for: ");
				songName = s.nextLine();
				Song sng = p.search(songName);
				if(sng==null){
					System.out.println("Song Not Found.");
				}
				else{
					System.out.println(sng);
				}
			}
			else if(c==2){
				String songName;
				System.out.println("Enter Song name to delete: ");
				songName = s.nextLine();
				System.out.println(p.remove(songName));
			}
			else if(c==1){
				System.out.println(p.add(getNewSong()));
			}
			System.out.println("\n");
		}
	}
	
	private static void saveToDatabase(PlayList pl){
		try {
			ObjectOutputStream oOut = new ObjectOutputStream(new FileOutputStream(fileLoc+"temp"));
			try {
				PlayList p = null;
				ObjectInputStream obr = new ObjectInputStream( new FileInputStream(fileLoc));
				while((p = (PlayList)obr.readObject())!=null){
					if(p.pName.equals(pl.pName)){
						
					}
					else{
						oOut.writeObject(p);
					}
				}
				obr.close();
			} catch (EOFException e){
				
			} catch (FileNotFoundException e){
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} 
			oOut.writeObject(pl);
			oOut.close();
			try {
			    Files.move(new File(fileLoc+"temp").toPath(), new File(fileLoc).toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static PlayList loadPlayListFromDatabase(String l){
		PlayList p = null;
		try {
			ObjectInputStream obr = new ObjectInputStream( new FileInputStream(fileLoc));
			while((p = (PlayList)obr.readObject())!=null){
				if(p.pName.equals(l)){
					obr.close();
					return p;
				}
			}
			obr.close();
		}
		catch(EOFException e){
			p =null;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return p;	//Returning null if no such PlayList found or Empty Database
	}
}
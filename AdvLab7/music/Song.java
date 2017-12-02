package music;

import java.io.Serializable;

public class Song implements Serializable{
	/**
	 * Song Class objects store information of every song.
	 */
	private static final long serialVersionUID = 3978848065572313868L;
	String Name;
	String singer;
	int duration;
	public String getName() {
		return Name;
	}
	public String getSinger() {
		return singer;
	}
	public int getDuration() {
		return duration;
	}

	public Song(String n, String s, int d){
		Name = n;
		singer = s;
		duration = d;
	}

	@Override
	public String toString(){
		return "Name: "+Name+"\nSinger: "+singer+"\nDuration: "+duration+" s";
	}
}
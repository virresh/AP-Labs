import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

public class World {
	static Scanner s;
	static PriorityQueue <Animal> pq;
	static int healthHerbivore, healthCarnivore, grassCapHerbivore;
	static Herbivore[] hList;
	static Carnivore[] cList;
	static Grassland[] gList;
	static Random r;
	
	static int totalTime;
	static int maxEncounteredTime;
	
	static void initialize(){
		s = new Scanner (System.in);
		hList = new Herbivore[2];
		cList = new Carnivore[2];
		gList = new Grassland[2];
		pq = new PriorityQueue<Animal>();
		r = new Random();
		
		System.out.println("Enter Total Final Time for Simulation");
		totalTime = s.nextInt();
	}
	
	static void fillPriorityQ(){
		for(int i=0; i<2; i++)
		{
			String p=null;
			if(i==0){ p = "First";}
			else { p= "Second";}
			
			System.out.print("Enter x, y centre, radius and Grass Available for " + p +" Grassland:");
			int a=s.nextInt(),b=s.nextInt(),c=s.nextInt(),d=s.nextInt();
			gList[i] = new Grassland(a,b,c,d);
		}
		
		System.out.println("Enter Health and Grass Capacity for Herbivores:");
		healthHerbivore = s.nextInt();
		grassCapHerbivore = s.nextInt();
		
		for(int i=0; i<2; i++)
		{
			String p=null;
			if(i==0){ p = "First";}
			else { p= "Second";}
			
			System.out.print("Enter x, y position and timestamp for " + p +"  Herbivore:");
			int a=s.nextInt(),b=s.nextInt(),c=s.nextInt();
			hList[i] = new Herbivore(p,healthHerbivore,a,b,c,grassCapHerbivore);
			pq.add(hList[i]);
		}
		
		System.out.println("Enter Health for Carnivores:");
		healthCarnivore = s.nextInt();
		
		for(int i=0; i<2; i++)
		{
			String p=null;
			if(i==0){ p = "First";}
			else { p= "Second";}
			
			System.out.print("Enter x, y position and timestamp for "+p+" Carnivore:");
			int a=s.nextInt(),b=s.nextInt(),c=s.nextInt();
			cList[i] = new Carnivore(p,healthCarnivore,a,b,c);
			pq.add(cList[i]);
		}
		
	}
	public static void main(String[] args) {
		initialize();
		fillPriorityQ();
		int curTime = 0;
		maxEncounteredTime = 0;
		while(curTime <= totalTime && !pq.isEmpty()){
			Animal a = pq.poll();
			System.out.println(a);
			if(a.timeStamp < maxEncounteredTime){
				maxEncounteredTime = a.timeStamp;
			}
			a.take_turn();
			System.out.println("Time Stamp for this creature - " + a.timeStamp);
			if(a.health >0 && a.timeStamp < totalTime){
				pq.add(a);
				System.out.println("It’s health after taking turn is " + a.health);
			}
			else{
				System.out.println("It is dead");
			}
		}
	}
	
	static int getNumHerbivoresAlive()
	{
		int ans=0;
		for(int i=0; i<hList.length; i++)
		{
			if(hList[i].health >  0){
				ans++;
			}
		}
		return ans;
	}
	
	static int getNumCarnivoreAlive()
	{
		int ans=0;
		for(int i=0; i<cList.length; i++)
		{
			if(cList[i].health >  0){
				ans++;
			}
		}
		return ans;
	}
	
	static int getNearestCarnivore(int x, int y, Animal A){
		int ng = -1;
		for(int i=0; i<cList.length; i++)
		{
			if( ng!=-1 && A.health>0 && A.timeStamp < totalTime && ( HelperClass.getDistance(A.x, A.y, cList[i].x, cList[i].y) < HelperClass.getDistance(A.x, A.y, cList[ng].x, cList[ng].y) )){
				ng = i;
			}
		}
		return ng;
	}
	
	static int getNearestHerbivore(int x, int y, Animal A){
		int ng = -1;
		for(int i=0; i<hList.length; i++)
		{
			if( ng!=-1 && A.health>0 && A.timeStamp < totalTime && ( HelperClass.getDistance(A.x, A.y, hList[i].x, hList[i].y) < HelperClass.getDistance(A.x, A.y, hList[ng].x, hList[ng].y) )){
				ng = i;
			}
		}
		return ng;
	}
	
	static int getNearestGrassLand(int x, int y){
		int ng = 0;
		for(int i=0; i<gList.length; i++)
		{
			if(HelperClass.getDistance(x, y, gList[i].x, gList[i].y) < HelperClass.getDistance(x, y, gList[ng].x, gList[ng].y) ){
				ng = i;
			}
		}
		return ng;
	}
}

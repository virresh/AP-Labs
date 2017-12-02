import java.util.Scanner;

/**
 * 
 * @author viresh
 * Name : Viresh Gupta
 * Roll No : 2016118
 *
 */

class student{
	String name;
	String rNo;
	String program;
	int distance;
	int index;
	boolean allocated;
	static int count=0;
	student(String n, String rn, String pr, int d)
	{
		allocated = false;
		name = n;
		rNo = rn;
		program = pr;
		distance = d;
		count+=1;
		index = count;
	}
	
	void copy(student t)
	{	// this function copies t into this instance
		this.name = t.name;
		this.rNo = t.rNo;
		this.program = t.program;
		this.distance = t.distance;
		this.index = t.index;
		this.allocated = t.allocated;
	}
	
	public String toString()
	{
		return name+" "+rNo+" " + program+ " "+distance;
	}
}

public class HostelAllocation {
	
	
	private static int n,m,phdIndex,ugIndex,pgIndex;
	private static student[] all;
	
	static void sort()
	{
		// this function will use stable bubble sort to sort the array all in ascending order
		// also it takes into account  the priority of a student based on their appearance order in case of same distances
		for(int i=0; i<all.length-1; i++)
		{
			for(int j=0; j<all.length-i-1; j++)
			{
				if(all[j].distance > all[j+1].distance)
				{
					student temp = new student("","","",0);
					temp.copy(all[j]);
					all[j].copy(all[j+1]);
					all[j+1].copy(temp);
				}
				else if(all[j].distance == all[j+1].distance && all[j].index < all[j+1].index)
				{
					student temp = new student("","","",0);
					temp.copy(all[j]);
					all[j].copy(all[j+1]);
					all[j+1].copy(temp);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		n = s.nextInt();	// number of applicants
		m = s.nextInt();	// number of rooms
		phdIndex=0;
		ugIndex =0;
		pgIndex =0;
		all = new student[n];
		for(int i=0; i<n; i++)
		{
			String a=s.next(),b=s.next(),c= s.next(); int d=s.nextInt();
			student st = new student(a,b,c,d);
			all[i] = st;
		}
		int numPhd=m-m/2; 		// stores the number of rooms of PhD applicants
		int numPG=m/2;		// stores the number of rooms of PG applicants
		sort();

		// allocate rooms to the PhD applicants
		for(int i=all.length-1; i>=0; i--)
		{
			//System.out.println(""+all[i].name + " " +all[i].distance);
			if(all[i].program.equals("PhD") && phdIndex < numPhd){
				phdIndex++;
				all[i].allocated=true;
			}
			else if(all[i].program.equals("PG") && pgIndex < numPG){
				pgIndex++;
				all[i].allocated=true;
			}
		}
		//System.out.println(""+phdIndex+" "+pgIndex);
		int slotsEmpty = m - phdIndex - pgIndex; // empty slots if any for UG students
		// Allocate to UG students
		for(int i=all.length-1; i>=0; i--)
		{
			if(all[i].program.equals("UG")){
				if(ugIndex < slotsEmpty)
				{
					ugIndex++;
					all[i].allocated=true;
				}
				else
				{
					break;
				}
			}
		}
				
		// Print according the the order of applying
		for(int i=1; i<=n; i++)
		{
			for(int j=0; j<all.length; j++)
			{
				if(all[j].index==i && all[j].allocated==true)
				{
					System.out.println(all[j]);
				}
			}
		}
		s.close();
	}

}

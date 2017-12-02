package lab2;

import java.util.Scanner;

/**
 * 
 * @author viresh
 * Name - Viresh Gupta
 * Roll No - 2016118
 * 
 * Approach - Make classes of Mess, Hostel, Library Academics and College as whole.
 * Then Sort By each Mess , Hostel , Library , Academics in a separate array to get ranks
 * Then calculate rank 
 * Then sort college according to ranks 
 */

class Mess{
	private float food_Avail,food_nutriVal,hygiene_Maintenance,food_deliveryDelay;
	private int rank;
	Mess(String A){
		rank = 0;
		String[] x = A.split(" ");
		this.food_Avail = Float.parseFloat(x[1]);
		this.food_nutriVal = Float.parseFloat(x[2]);
		this.hygiene_Maintenance = Float.parseFloat(x[3]);
		this.food_deliveryDelay = Float.parseFloat(x[4]);
	}
	
	int getRank(){
		return rank;
	}
	
	void setRank(int x){
		rank =x;
	}
	
	static int compare(Mess A, Mess B)
	{
		if(A.food_Avail<B.food_Avail){
			return 1;
		}
		else if(A.food_Avail==B.food_Avail){
			if(A.food_nutriVal < B.food_nutriVal){
				return 1;
			}
			else if(A.food_nutriVal==B.food_nutriVal){
				if(A.hygiene_Maintenance<B.hygiene_Maintenance){
					return 1;
				}
				else if(A.hygiene_Maintenance == B.hygiene_Maintenance){
					if(A.food_deliveryDelay < B.food_deliveryDelay){
						return -1;
					}
					else if(A.food_deliveryDelay==B.food_deliveryDelay){
						return 0;
					}
					else{
						return 1;
					}
				}
				else{
					return -1;
				}
			}
			else{
				return -1;
			}
		}
		else{
			return -1;
		}
	}
	
	public String toString(){
		return (this.food_Avail + " " + this.food_nutriVal + " " + this.hygiene_Maintenance + " " + this.food_deliveryDelay + " " +this.rank);
	}
}

class Hostel{
	private float room_Conditions,studying_facilities,cleanliness,recreationalFacilities;
	private int rank;
	Hostel(String A){
		rank=0;
		String[] x = A.split(" ");
		this.room_Conditions = Float.parseFloat(x[1]);
		this.studying_facilities = Float.parseFloat(x[2]);
		this.cleanliness = Float.parseFloat(x[3]);
		this.recreationalFacilities = Float.parseFloat(x[4]);
	}
	
	int getRank(){
		return rank;
	}
	
	void setRank(int x){
		rank =x;
	}
	
	static int compare(Hostel A, Hostel B){
		if(A.room_Conditions < B.room_Conditions){
			return 1;
		}
		else if(A.room_Conditions == B.room_Conditions){
			if(A.studying_facilities < B.studying_facilities){
				return 1;
			}
			else if(A.studying_facilities == B.studying_facilities){
				if(A.cleanliness < B.cleanliness){
					return 1;
				}
				else if(A.cleanliness == B.cleanliness){
					if(A.recreationalFacilities< B.recreationalFacilities){
						return 1;
					}
					else if(A.recreationalFacilities == B.recreationalFacilities){
						return 0;
					}
					else{
						return -1;
					}
				}
				else{
					return -1;
				}
			}
			else{
				return -1;
			}
		}
		else{
			return -1;
		}
	}
}

class Library{
	private float books_Availaibility,digital_facility,system_efficiency;
	private int rank;
	Library(String A){
		rank=0;
		String[] x = A.split(" ");
		this.books_Availaibility = Float.parseFloat(x[1]);
		this.digital_facility = Float.parseFloat(x[2]);
		this.system_efficiency = Float.parseFloat(x[3]);
	}
	
	int getRank(){
		return rank;
	}
	
	void setRank(int x){
		rank =x;
	}
	
	static int compare(Library A, Library B)
	{
		double  x = (A.books_Availaibility + A.digital_facility + A.system_efficiency)/3.0;
		double y = (B.books_Availaibility + B.digital_facility + B.system_efficiency)/3.0;
		if(x>y){
			return -1;
		}
		else if(x==y){
			return 0;
		}
		else {
			return 1;
		}
	}
}

class Academics{
	private float knowledge_Imparted,domains_covered,course_efficiency;
	private int rank;
	Academics(String A){
		rank=0;
		String[] x = A.split(" ");
		this.knowledge_Imparted = Float.parseFloat(x[1]);
		this.domains_covered = Float.parseFloat(x[2]);
		this.course_efficiency = Float.parseFloat(x[3]);
	}
	
	int getRank(){
		return rank;
	}
	
	void setRank(int x){
		rank =x;
	}
	
	static int compare(Academics A, Academics B){
		double x = A.knowledge_Imparted + A.domains_covered + 2* A.course_efficiency;
		double y = B.knowledge_Imparted + B.domains_covered + 2* B.course_efficiency;
		if(x>y){
			return -1;
		}
		else if(x==y){
			return 0;
		}
		else {
			return 1;
		}
	}
}

class College{
	private String name;
	private Mess M;
	private Hostel H;
	private Library L;
	private Academics A;
	private double fees;
	private String Grade;
	private float rank;
	
	String getName(){
		return name;
	}
	College(String n, String m, String h,String l, String a,double f, String g){
		name = n;
		M = new Mess(m);
		H = new Hostel(h);
		L = new Library(l);
		A = new Academics(a);
		fees = f;
		Grade = g.split(" ")[2];
	}
	
	Mess getMess(){
		return M;
	}
	Hostel getHostel(){
		return H;
	}
	Library getLib(){
		return L;
	}
	Academics getAcads(){
		return A;
	}
	
	float getRank(){
		return rank;
	}
	
	void calcRank()
	{
		rank = 0.25f * M.getRank() + 0.20f *H.getRank() + 0.25f * L.getRank() + 0.30f * A.getRank();
	}
	
	static int f(String x){
		if(x.equals("A")){
			return 1;
		}
		else if(x.equals("B")){
			return 2;
		}
		else if(x.equals("C")){
			return 3;
		}
		else if(x.equals("D")){
			return 4;
		}
		else{
			return 5;
		}
	}
	
	static int compare(College A, College B){
		if(A.rank < B.rank){
			return -1;
		}
		else if(A.rank == B.rank){
			if(A.fees < B.fees){
				return -1;
			}
			else if(A.fees == B.fees){
				if(f(A.Grade) < f(B.Grade)){
					return -1;
				}
				else if(f(A.Grade)== f(B.Grade)){
					return 0;
				}
				else{
					return 1;
				}
			}
			else {
				return 1;
			}
		}
		else {
			return 1;
		}
	}
}


public class CollegeRank {

	static College[] arr; 
	static Mess[] arr_m;
	static Hostel[] arr_h;
	static Library[] arr_l;
	static Academics[] arr_a;
	
	static void merge(int l, int m, int r){
		int n1 = m-l+1;
		int n2 = r-m;
		
		Mess L_m[] = new Mess[n1];
		Mess R_m[] = new Mess[n2];
		
		Hostel L_h[] = new Hostel[n1];
		Hostel R_h[] = new Hostel[n2];
		
		Library L_l[] = new Library[n1];
		Library R_l[] = new Library[n2];
		
		Academics L_a[] = new Academics[n1];
		Academics R_a[] = new Academics[n2];
		
		for(int i=0; i<n1; i++)
		{
			L_m[i] = arr_m[i+l];
			L_h[i] = arr_h[i+l];
			L_l[i] = arr_l[i+l];
			L_a[i] = arr_a[i+l];
		}
		for(int i=0; i<n2; i++)
		{
			R_m[i] = arr_m[i+m+1];
			R_h[i] = arr_h[i+m+1];
			R_l[i] = arr_l[i+m+1];
			R_a[i] = arr_a[i+m+1];
		}
		int i_m=0, j_m=0, k=l,i_h=0,j_h=0,i_l=0,j_l=0,i_a=0,j_a=0;
		
		while(i_m<n1 && j_m<n2){
			if(Mess.compare(L_m[i_m], R_m[j_m])!=1){
				arr_m[k] = L_m[i_m];
				i_m++;
			}
			else{
				arr_m[k] = R_m[j_m];
				j_m++;
			}
			k++;
		}
		
		while(i_m<n1){
			arr_m[k] = L_m[i_m];
			i_m++;
			k++;
		}
		
		while(j_m<n2){
			arr_m[k] = R_m[j_m];
			j_m++;
			k++;
		}
		
		k=l;
		while(i_h<n1 && j_h<n2){
			if(Hostel.compare(L_h[i_h], R_h[j_h])!=1){
				arr_h[k] = L_h[i_h];
				i_h++;
			}
			else{
				arr_h[k] = R_h[j_h];
				j_h++;
			}
			k++;
		}
		
		while(i_h<n1){
			arr_h[k] = L_h[i_h];
			i_h++;
			k++;
		}
		
		while(j_h<n2){
			arr_h[k] = R_h[j_h];
			j_h++;
			k++;
		}
		
		k=l;
		while(i_l<n1 && j_l<n2){
			if(Library.compare(L_l[i_l], R_l[j_l])!=1){
				arr_l[k] = L_l[i_l];
				i_l++;
			}
			else{
				arr_l[k] = R_l[j_l];
				j_l++;
			}
			k++;
		}
		
		while(i_l<n1){
			arr_l[k] = L_l[i_l];
			i_l++;
			k++;
		}
		
		while(j_l<n2){
			arr_l[k] = R_l[j_l];
			j_l++;
			k++;
		}
		
		k=l;
		while(i_a<n1 && j_a<n2){
			if(Academics.compare(L_a[i_a], R_a[j_a])!=1){
				arr_a[k] = L_a[i_a];
				i_a++;
			}
			else{
				arr_a[k] = R_a[j_a];
				j_a++;
			}
			k++;
		}
		
		while(i_a<n1){
			arr_a[k] = L_a[i_a];
			i_a++;
			k++;
		}
		
		while(j_a<n2){
			arr_a[k] = R_a[j_a];
			j_a++;
			k++;
		}
	}
	
	static void sort(int l, int r){
		if(l<r){
			int mid = (l+r)/2;
			sort(l,mid);
			sort(mid+1,r);
			merge(l,mid,r);
		}
	}
	
	static void merge2(int l, int m, int r){
		int n1 = m-l+1;
		int n2 = r-m;
//		System.out.println(l+ " " + m + " " + r);
		College[] L = new College[n1];
		College[] R = new College[n2];
		
		for(int i=0; i<n1; i++)
		{
			L[i] = arr[i+l];
		}
		for(int j=0; j<n2; j++)
		{
			R[j] = arr[j+m+1];
		}
		int i=0,j=0,k=l;
		while(i<n1 && j<n2){
			if(College.compare(L[i], R[j])==-1){
				arr[k] = L[i];
				i++;
			}
			else{
				arr[k] = R[j];
				j++;
			}
			k++;
		}
		
		while(i<n1){
			arr[k] = L[i];
			i++;
			k++;
		}
		
		while(j<n2){
			arr[k] = R[j];
			j++;
			k++;
		}
	}
	
	static void sort2(int l, int r){
		if(l<r){
			int mid = (l+r)/2;
			sort2(l,mid);
			sort2(mid+1,r);
			merge2(l,mid,r);
//			System.out.println("Here\n");
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner (System.in);
		int n = s.nextInt();
		arr = new College[n];
		
		arr_m = new Mess[n];
		arr_h = new Hostel[n];
		arr_l = new Library[n];
		arr_a = new Academics[n];
		
		s.nextLine();	// consume newline
		for(int i=0; i<n; i++)
		{
			String Name, M,H,L,A,G;
			double fee;
			Name = s.nextLine();	// college name
			M = s.nextLine(); 		// Mess string
			H = s.nextLine();
			L = s.nextLine(); 		// Library String
			A = s.nextLine(); 		// Acads string
			fee = Double.parseDouble(s.nextLine().split(" ")[1]);
			//s.nextLine();			// consume new line
			G = s.nextLine();
			arr[i] = new College(Name,M,H,L,A,fee,G);
			arr_m[i] = arr[i].getMess();
			arr_h[i] = arr[i].getHostel();
			arr_l[i] = arr[i].getLib();
			arr_a[i] = arr[i].getAcads();		
		}
		
//		for(int i=0; i<n; i++)
//		{
//			System.out.println(arr_m[i] + " ");
//		}
		
		sort(0 , n-1);
//		System.out.println("");
		
		for(int i=0; i<n; i++)
		{
//			System.out.println(arr_m[i] + " ");
			arr_m[i].setRank(i+1);
			arr_h[i].setRank(i+1);
			arr_l[i].setRank(i+1);
			arr_a[i].setRank(i+1);
		}
		
//		
		for(int j=0; j<n; j++)
		{
			arr[j].calcRank();
		}
		
		//sort2(0,n-1);
		
		for(int i=0; i<n; i++)
		{
			System.out.print(arr[i].getName()+ " ");
			System.out.println(arr[i].getRank() + " " + arr[i].getMess().getRank() + " " +arr[i].getHostel().getRank() + " " + arr[i].getLib().getRank() + " " + arr[i].getAcads().getRank());
		}
		
		System.out.println("List of colleges as per their ranking (starting from rank-1) is as following:");
		for(int i=0; i<n; i++)
		{
			System.out.println(arr[i].getName());
		}
		s.close();
	}

}

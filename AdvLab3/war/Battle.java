package war;

import java.util.Scanner;

public class Battle {
	
	static int round =0;
	
	static Creature humanDummy,wolfDummy,iceDragonDummy,fireDragonDummy,daemonDummy;
	static Team good,bad;
	
	static boolean goodCanBuy(){
		int x = good.getMoney();
		if(x>=humanDummy.getCost() || x>=wolfDummy.getCost() || x>=fireDragonDummy.getCost()){
			return true;
		}
		return false;
	}
	
	static boolean badCanBuy(){
		int y = bad.getMoney();
		if(y>=daemonDummy.getCost() || y>=iceDragonDummy.getCost()){
			return true;
		}
		return false;
	}
	
	static void selectCreatures(Scanner s){
		// Team good can only have FireDragons, Humans, Wolves
		// Team bad has only Daemons and IceDragons
		
		if(goodCanBuy() == false && badCanBuy() == false)
		{
			return;
		}
		
		while(goodCanBuy() == true)
		{
			System.out.println("Select Creatures for Team Good");
			if(good.getMoney() >= humanDummy.getCost()){
				System.out.println("\t1. Human");
			}
			if(good.getMoney() >= fireDragonDummy.getCost()){
				System.out.println("\t2. Fire Dragon");
			}
			if(good.getMoney() >= wolfDummy.getCost()){
				System.out.println("\t3. Wolf");
			}
			System.out.println("\t4. Exit Selection");
			
			int x = s.nextInt();
			Creature t = null;
			switch(x){
			case 1: if(good.getMoney()>=humanDummy.getCost()){
						System.out.println("Enter the name of Human - ");
						s.nextLine();
						good.buy(humanDummy.getCost());
						t = new Human(s.nextLine(),humanDummy);
					}
					break;
			case 2: if(good.getMoney()>=fireDragonDummy.getCost()){
						System.out.println("Enter the name of Fire Dragon - ");
						s.nextLine();
						good.buy(fireDragonDummy.getCost());
						t = new FireDragon(s.nextLine(),fireDragonDummy);
					}
					break;
			case 3: if(good.getMoney()>=wolfDummy.getCost()){
						System.out.println("Enter the name of Wolf - ");
						s.nextLine();
						good.buy(wolfDummy.getCost());
						t = new Wolf(s.nextLine(),wolfDummy);
					}
					break;
			case 4: break;
			}
			
			if(t==null){
				break;
			}
			else{
				good.addCreature(t);
			}
		}
		
		while(badCanBuy() == true)
		{
			System.out.println("Select Creatures for Team Bad");
			if(bad.getMoney() >= daemonDummy.getCost()){
				System.out.println("\t1. Daemon");
			}
			if(bad.getMoney() >= iceDragonDummy.getCost()){
				System.out.println("\t2. Ice Dragon");
			}
			System.out.println("\t3. Exit Selection");
			
			int x = s.nextInt();
			Creature t = null;
			switch(x){
			case 1: if(bad.getMoney()>=daemonDummy.getCost()){
						System.out.println("Enter the name of Daemon - ");
						s.nextLine();
						bad.buy(daemonDummy.getCost());
						t = new Daemon(s.nextLine(),daemonDummy);
					}
					break;
			case 2: if(bad.getMoney()>=iceDragonDummy.getCost()){
						System.out.println("Enter the name of Ice Dragon - ");
						s.nextLine();
						bad.buy(iceDragonDummy.getCost());
						t = new IceDragon(s.nextLine(),iceDragonDummy);
					}
					break;
			case 3: break;
			}
			
			if(t==null){
				break;
			}
			else{
				bad.addCreature(t);
			}
		}
	}
	
	static void battleBeg(Creature good, Creature bad){
		int damageToBad = good.getDamage();
		int damageToGood = bad.getDamage();
		
		System.out.println(damageToGood + " " + damageToBad);
		
		good.setHealth(good.getHealth()-damageToGood);
		bad.setHealth(bad.getHealth()-damageToBad);
	}
	
	static void initializeDummy(Scanner s)
	{
		humanDummy = new Creature();
		fireDragonDummy = new Creature();
		iceDragonDummy = new Creature();
		daemonDummy = new Creature();
		wolfDummy = new Creature();
		System.out.println("Enter cost, asset , power and health for Human (space-separated) -");
		int a = s.nextInt(), b=s.nextInt(), c=s.nextInt(), d=s.nextInt();
		humanDummy.setParams(a, b, c, d);
		
		System.out.println("Enter cost, asset , power and health for Fire Dragon (space-separated) -");
		a = s.nextInt(); b=s.nextInt(); c=s.nextInt(); d=s.nextInt();
		fireDragonDummy.setParams(a, b, c, d);
				
		System.out.println("Enter cost, asset , power and health for Ice Dragon (space-separated) -");
		a = s.nextInt(); b=s.nextInt(); c=s.nextInt(); d=s.nextInt();
		iceDragonDummy.setParams(a, b, c, d);
		
		System.out.println("Enter cost, asset , power and health for Daemon (space-separated) -");
		a = s.nextInt(); b=s.nextInt(); c=s.nextInt(); d=s.nextInt();
		daemonDummy.setParams(a, b, c, d);
		
		System.out.println("Enter cost, asset , power and health for Wolf (space-separated) -");
		a = s.nextInt(); b=s.nextInt(); c=s.nextInt(); d=s.nextInt();
		wolfDummy.setParams(a, b, c, d);
	}
	
	static Creature selectForGood(Scanner s){
		Creature p = null;
		if(good.getLen()>0){
			do{
				System.out.println("Enter Creature from Good's Team to fight in war -");
				String a = s.nextLine();
				p = good.getCreature(a);
			}while(p==null);
		}
		else{
			p = good.getLastCreature();
		}
		return p;
	}
	
	static Creature selectForBad(Scanner s){
		Creature p = null;
		if(bad.getLen()>0){
			do{
				System.out.println("Enter Creature from Bad's Team to fight in war -");
				String a = s.nextLine();
				p = bad.getCreature(a);
			}while(p==null);
		}
		else{
			p = bad.getLastCreature();
		}
		return p;
	}
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		System.out.println("Enter Team Good's total money");
		good = new Team(s.nextInt());
		System.out.println("Enter Team Bad's total money");
		bad = new Team(s.nextInt());
		
		initializeDummy(s);
		selectCreatures(s);
		
		System.out.println("The War Begins -");
		Creature goodC=null,badC=null;
		do
		{
			round+=1;
			System.out.println("Round-"+round);
			if(goodC==null){
				goodC = selectForGood(s);
			}
			if(badC==null){
				badC = selectForBad(s);
			}
			battleBeg(goodC,badC);
			System.out.println("Health - " + goodC.getHealth() + " "+ badC.getHealth());
			if(goodC.getHealth() >0 && badC.getHealth()>0)
			{
				System.out.println("Both the creatures go into Next Round");
			}
			else if(goodC.getHealth() <=0 && badC.getHealth()<=0)
			{
				System.out.println("Both the creatures die "+goodC.getName() + " " + badC.getName());
				good.removeCreature(goodC.getName());
				bad.removeCreature(badC.getName());
				goodC = null;
				badC = null;
			}
			else if(goodC.getHealth() <=0 && badC.getHealth()>0)
			{
				System.out.println(goodC.getName()+" Loses in Round -"+round);
				good.removeCreature(goodC.getName());
				goodC = null;
				bad.addMoney(badC.getAsset());
			}
			else if(goodC.getHealth() >0 && badC.getHealth()<=0)
			{
				System.out.println(badC.getName()+" Loses in Round -"+round);
				bad.removeCreature(badC.getName());
				badC = null;
				good.addMoney(goodC.getAsset());
			}
			System.out.println("Money of Team Good is "+good.getMoney());
			System.out.println("Money of Team Bad is "+bad.getMoney());
			selectCreatures(s);
			
		}while(!(goodC==null && badC==null));
		
		if(bad.getLen()==0 && good.getLen()!=0){
			System.out.println("Team Good Wins the war. The money the team has is "+good.getMoney());
		}
		else if(good.getLen()==0 && bad.getLen()!=0){
			System.out.println("Team Bad Wins the war. The money the team has is "+bad.getMoney());
		}
		else{
			System.out.println("No body could win.");
		}
		
		s.close();
	}
	
}

package war;

public class Team {
	private int money;
	private int len=0;
	Creature[] fighters;
	
	Team(int m){
		money = m;
		fighters = new Creature[50];
	}
	
	int getMoney(){
		return money;
	}
	void buy(int cost){
		money = money - cost;
	}
	void addMoney(int mon){
		money = money + mon;
	}
	
	void addCreature(Creature x){
		fighters[len]=x;
		len++;
	}
	
	Creature getCreature(String x){
		Creature t = null;
		for(int i=0; i<len; i++)
		{
			if(fighters[i].getName().equals(x)){
				t=fighters[i];
				break;
			}
		}
		return t;
	}
	
	Creature getLastCreature(){
		if(len==1){
			len--;
			return fighters[len];
		}
		else{
			return null;
		}
	}
	
	void removeCreature(String x){
		Creature[] t = new Creature[50];
		int tl=0;
		for(int i=0; i<len; i++)
		{
			if(fighters[i].getName().equals(x) == false){
				t[tl]=fighters[i];
				tl++;
			}
		}
		len = tl;
		fighters = t;
	}
	
	int getLen(){
		return len;
	}
}

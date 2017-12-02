package war;

import java.util.Random;

class Creature{
	protected String name;
	protected int power;
	protected int health;
	protected int cost;
	protected int asset;
	
	Creature()
	{
		name = null;
		power = health = cost = asset = 0;
	}
	
	int getDamage(){
		Random r = new Random();
		int p = r.nextInt(power);
		return p;
	}
	
	void setParams(int C, int A, int P, int H)
	{
		power = P;
		health = H;
		cost = C;
		asset = A;
	}
	
	Creature(Creature B)
	{
		this.power = B.power;
		this.health = B.health;
		this.cost = B.cost;
		this.asset = B.asset;
	}
	
	int getCost(){
		return cost;
	}
	
	int getHealth()
	{
		return health;
	}
	
	int getAsset()
	{
		return asset;
	}
	
	String getName(){
		return name;
	}
	
	void setHealth(int x)
	{
		health = x;
	}
}

class Human extends Creature{
	@Override
	int getDamage(){
		int q = super.getDamage();
		Random r = new Random();
		int p = r.nextInt(2);
		if(p==0)
		{
			q = q+q;
		}
		return q;
	}
	
	Human(String name,Creature B)
	{
		super(B);
		this.name = name;
	}
}

class Wolf extends Creature{
	@Override
	int getDamage(){
		int q = super.getDamage();
		return q;
	}
	
	Wolf(String name,Creature B)
	{
		super(B);
		this.name = name;
	}
}

class Daemon extends Creature{
	@Override
	int getDamage(){
		int q = super.getDamage();
		return q;
	}
	
	Daemon(String name,Creature B)
	{
		super(B);
		this.name = name;
	}
}

class Dragon extends Creature{
	@Override
	int getDamage(){
		int q = super.getDamage();
		Random r = new Random();
		int p = r.nextInt(100);
		if(p<15){
			q = q + 25;
		}
		return q;
	}
	
	Dragon(String name,Creature B)
	{
		super(B);
		this.name = name;
	}
}

class FireDragon extends Dragon{
	@Override
	int getDamage(){
		int q = super.getDamage();
		return q;
	}
	
	FireDragon(String name,Creature B)
	{
		super(name,B);
	}
	
}

class IceDragon extends Dragon{
	@Override
	int getDamage(){
		int q = super.getDamage();
		Random r = new Random();
		int p = r.nextInt(100);
		if(p<5){
			q = q + r.nextInt(power);
		}
		return q;
	}
	
	IceDragon(String name,Creature B)
	{
		super(name,B);
	}
	
}
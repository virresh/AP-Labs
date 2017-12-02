
public class Herbivore extends Animal {
	protected int grassCapacity;
	
	Herbivore(String n, int h, int x, int y, int ts, int gs) {
		super(n, h, x, y, ts);
		grassCapacity = gs;
	}
	
	@Override
	public String toString(){
		return "It is " + name + " Herbivore.";
	}
	
	@Override
	public void take_turn() {
		super.take_turn();
		int z = World.getNearestCarnivore(x, y, this);
		if(World.getNumCarnivoreAlive() == 0  || z==-1){
			// no carnivore alive
			int p = World.r.nextInt(100);
			System.out.println(" Random number - " + p);
			if(p < 50){
				// stay put
				if(World.getNearestGrassLand(x, y) ==-1){
					System.out.println(" Doing nothing");
				}
			}
			else{
				int t= World.getNearestGrassLand(x, y);
				if(HelperClass.isAnyGrassland(x, y)!=-1){
					t = t^1;	// because there are only two grasslands , 0 or 1
				}
				x = HelperClass.getCoordinateXTowards(x, World.gList[t].x, World.gList[t].y, 5);
				y = HelperClass.getCoordinateXTowards(y, World.gList[t].x, World.gList[t].y, 5);
				System.out.println(" Moving towards a grassland");
			}
		}
		else if(World.getNearestGrassLand(x, y) == -1){
			if(World.r.nextInt(100) < 5){
				 // stay put
				System.out.println(" Doing nothing");
			}
			else{
				int tempR = World.r.nextInt(100);
				System.out.println("  Random number 2");
				if(tempR < 65){
					int t= World.getNearestGrassLand(x, y);
					x = HelperClass.getCoordinateXTowards(x, World.gList[t].x, World.gList[t].y, 5);
					y = HelperClass.getCoordinateXTowards(y, World.gList[t].x, World.gList[t].y, 5);
					System.out.println("  Move towards Carnivore");
				}
				else{
					int t = World.getNearestCarnivore(x, y, this);
					x = HelperClass.getCoordinateXTowards(x, -World.cList[t].x, -World.cList[t].y, 2);
					y = HelperClass.getCoordinateXTowards(y, -World.cList[t].x, -World.cList[t].y, 2);
					System.out.println("  Move away from Carnivore");
				}
			}
		}
		else{
			int p = World.getNearestGrassLand(x, y);
			if(World.gList[p].grass >= grassCapacity){
				int tempR = World.r.nextInt(100);
				System.out.println(" Random2" +   tempR);
				if(tempR < 90){
					health += 0.5 * health;
					World.gList[p].grass -= grassCapacity;
					System.out.println("  Eating Grass");
				}
				else{
					int tempR3 = World.r.nextInt(100); 
					if( tempR3 < 50){
						int t = World.getNearestCarnivore(x, y, this);
						x = HelperClass.getCoordinateXTowards(x, -World.cList[t].x, -World.cList[t].y, 2);
						y = HelperClass.getCoordinateXTowards(y, -World.cList[t].x, -World.cList[t].y, 2);
						System.out.println("   Moving away from Carnivore");
					}
					else{
						int t= World.getNearestGrassLand(x, y);
						x = HelperClass.getCoordinateXTowards(x, World.gList[t].x, World.gList[t].y, 5);
						y = HelperClass.getCoordinateXTowards(y, World.gList[t].x, World.gList[t].y, 5);
						System.out.println("   Moving towards Grassland");
					}
				}
			}
			else{
				int tempR = World.r.nextInt(100);
				System.out.println(" Random 2 - " + tempR);
				if(tempR< 20){
					health += 0.2 * health;
					World.gList[p].grass = 0;
					System.out.println("  Eating Grass");
				}
				else{
					int tempR3 = World.r.nextInt(100);
					System.out.println("  Random 3 - " + tempR);
					if(tempR3 < 70){
						int t = World.getNearestCarnivore(x, y, this);
						x = HelperClass.getCoordinateXTowards(x, -World.cList[t].x, -World.cList[t].y, 4);
						y = HelperClass.getCoordinateXTowards(y, -World.cList[t].x, -World.cList[t].y, 4);
						System.out.println("   Going to nearest Carnivore");
					}
					else{
						int t= World.getNearestGrassLand(x, y);
						if(HelperClass.isAnyGrassland(x, y)!=-1){
							t = t^1;	// because there are only two grasslands , 0 or 1
						}
						x = HelperClass.getCoordinateXTowards(x, World.gList[t].x, World.gList[t].y, 2);
						y = HelperClass.getCoordinateXTowards(y, World.gList[t].x, World.gList[t].y, 2);
						System.out.println("   Going to nearest Grassland");
					}
				}
			}
		}
		
		if(World.getNearestGrassLand(x, y) == -1){
			consecTurn ++;
			if(consecTurn > 7){
				health -=5;
				System.out.println("  ** Health reducing because of consecutive turns");
			}
		}
		else{
			consecTurn = 0;
		}
	}
	
	int getGCapacity(){
		return grassCapacity;
	}

}

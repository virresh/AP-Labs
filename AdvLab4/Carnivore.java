
public class Carnivore extends Animal {
	
	Carnivore(String n, int h, int x, int y, int ts) {
		super(n, h, x, y, ts);
	}

	@Override
	public String toString(){
		return "It is " + name + " Carnivore.";
	}
	
	@Override
	public void take_turn() {
		super.take_turn();
		int t= World.getNearestHerbivore(x, y, this);
		if(World.getNumHerbivoresAlive() == 0 || t<0){ 
			// do nothing
			System.out.println("  Do nothing");
		}
		else{
			if(HelperClass.getDistance(x, y, World.hList[t].x, World.hList[t].y) <= 1){
				int l = World.hList[t].health;
				World.hList[t].health = -1;
				health = health + 2 * l /3;
			}
			else if(HelperClass.isAnyGrassland(x, y) == -1){
				int tempR = World.r.nextInt(100);
				System.out.println(" Random " + tempR);
				if(tempR<92){
					x = HelperClass.getCoordinateXTowards(x, World.hList[t].x, World.hList[t].y, 5);
					y = HelperClass.getCoordinateXTowards(y, World.hList[t].x, World.hList[t].y, 5);
					System.out.println("  Going towards Herbivore");
				}
				else{
					// do nothing
					health -=60;
					System.out.println("  Do nothing");
				}
			}
			else{
				int tempR = World.r.nextInt(100); 
				if(tempR< 25){
					// stay inside do nothing
					health -=30;
					System.out.println(" Do Nothing");
				}
				else{
					x = HelperClass.getCoordinateXTowards(x, World.hList[t].x, World.hList[t].y, 2);
					y = HelperClass.getCoordinateXTowards(y, World.hList[t].x, World.hList[t].y, 2);
					System.out.println(" Move toward Herbivore");
				}
			}
		}
		
		if(t == -1 || HelperClass.getDistance(x, y, World.hList[t].x, World.hList[t].x) > 5){
			consecTurn ++;
			if(consecTurn > 7){
				System.out.println(" Health Reduced due to consecutive turns" );
				health -=5;
			}
		}
		else{
			consecTurn = 0;
		}
		
	}

}

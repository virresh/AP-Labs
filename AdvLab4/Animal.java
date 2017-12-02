
public abstract class Animal implements Comparable<Animal>{
	protected int health;
	protected int x,y,timeStamp;
	protected int consecTurn;
	public String name;
	public void take_turn(){
		int r = World.r.nextInt(World.totalTime-World.maxEncounteredTime + 1) + World.maxEncounteredTime;
		timeStamp = r;		
	};
	
	Animal(String n, int h, int x, int y, int ts){
		name = n;
		health = h;
		this.x = x;
		this.y = y;
		consecTurn = 0;
		timeStamp = ts;
	}
	
	@Override
	public int compareTo(Animal b){
		if(timeStamp < b.timeStamp){
			return -1;
		}else if(timeStamp > b.timeStamp){
			return 1;
		}
		else{
			if(health > b.health){
				return -1;
			}
			else if(health < b.health){
				return 1;
			}
			else{
				if(this.getClass() == Herbivore.class && b.getClass() == Carnivore.class){
					return -1;
				}
				else if(this.getClass() == Carnivore.class && b.getClass() == Herbivore.class)
				{
					return 1;
				}
				else{
					if(HelperClass.getDistance(x, y, 0, 0) < HelperClass.getDistance(b.x, b.y, 0, 0) ){
						return -1;
					}
					else if(HelperClass.getDistance(x, y, 0, 0) > HelperClass.getDistance(b.x, b.y, 0, 0)){
						return 1;
					}
				}
			}
		}
		return 0;
	}
	
}

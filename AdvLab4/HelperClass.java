
public class HelperClass {
	static int getDistance(int x1,int y1, int x2, int y2){
		int d = (int)(Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2)));
		return d;
	}
	static int isAnyGrassland(int x,int y){
		for(int i=0; i<World.gList.length; i++)
		{
			if(getDistance(x,y,World.gList[i].getX(),World.gList[i].getY()) <= World.gList[i].getR()){
				return i;
			}
		}
		return -1;
	}
	
	static int getCoordinateXTowards(int x0,int x, int y, int d){
		double z = 1;
		if(x !=0){
			z = Math.sin(Math.atan(y/x));
		}
		double Coordinate = x0 + d * z;
		return (int)Coordinate;
	}
	
	static int getCoordinateYTowards(int y0,int x, int y, int d){
		double z=0;
		if(x !=0){
			z = Math.cos(Math.atan(y/x));
		}
		double Coordinate = y0 + d * z;
		return (int)Coordinate;
	}
}

package my.solarcolony.gop.entities;

public class Planet {
	int x;
	int y;
	int radius;
	int faction;
	int totalPop;
	int curPop;
	boolean isSelected;
	
	public Planet(int x, int y, int radius, int faction)
	{
		isSelected = false;
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.faction = faction;
		this.totalPop = (int) (radius * 1.2) + 500;
		this.curPop = totalPop/2;
	}
	
	public int updatePop()
	{
		curPop += 2;
		if(curPop >= totalPop)
			curPop = totalPop;
		
		return curPop;
	}
	
	public int collision(Ship ship){
		if(ship.getFaction() != faction){
			curPop -= 50;
			if(curPop <= 0){
				faction = ship.getFaction();
				curPop = (int)(totalPop * 0.25);
			}
		}
		return curPop;
	}
	
	public int shipLaunch()
	{
		return 0;
	}
	
	
}

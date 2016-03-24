package my.solarcolony.gop.objects;

public class Ship {
	private int faction;
	private int [] pos = new int[2];
	private int [] vec = new int[2];
	
	public Ship(int[] position, int[] target, int fac)
	{
		pos[0] = position[0];
		pos[1] = position[1];
		int dx = target[0] - pos[0];
		int dy = target[1] - pos[1];
		faction = fac;
	}

}

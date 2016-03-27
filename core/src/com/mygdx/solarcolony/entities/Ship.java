package com.mygdx.solarcolony.entities;


public class Ship {
	protected int faction;
	
	protected int x;
	protected int y;
	
	protected int dx;
	protected int dy;
	
	protected float speed;
	protected float radDir;
	
	public Ship(int x, int y, int dx, int dy, float dir, int faction){
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.radDir = dir;
		this.faction = faction;
		
	}
	
	public int getFaction()
	{
		return faction;
	}
	
}

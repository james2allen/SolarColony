package com.mygdx.solarcolony.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

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
	
	public void draw(ShapeRenderer sr)
	{
		if(faction == 0){
			sr.setColor(1, 1, 1, 1);
		} else if(faction == 1){
			sr.setColor(0, 1, 0, 1);
		} else if(faction == 2){
			sr.setColor(1, 0, 0, 1);
		} else if (faction == 3){
			sr.setColor(0, 1, 0, 1);
		}
		
		sr.begin(ShapeType.Filled);
		sr.rect(x, y, 2, 5);
		sr.end();
		
	}
}

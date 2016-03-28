package com.mygdx.solarcolony.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Ship {
	protected int faction;
	
	protected int x;
	protected int y;
	
	protected double dx;
	protected double dy;
	
	protected float speed;
	protected float radDir;
	
	public Ship(int x, int y, int dx, int dy, int faction){
		double mag = Math.pow(dx ,2) + Math.pow(dy,2);
		mag = Math.sqrt(mag);
		this.x = x;
		this.y = y;
		this.dx = dx/mag;
		this.dy = dy/mag;
		this.radDir = 0;
		this.faction = faction;
		this.speed = 15;
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

	public void shipMove()
	{
		this.x += this.dx * speed;
		this.y += this.dy * speed;
	}
}

package com.mygdx.solarcolony.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Planet {
	int x;
	int y;
	int radius;
	int faction;
	int totalPop;
	int curPop;
	boolean isSelected;
	
	public Planet(int x, int y, int radius)
	{
		isSelected = false;
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.faction = 0;
		this.totalPop = (int) (radius * 1.2) + 500;
		this.curPop = totalPop/2;
	}
	
	//automatic function for population regeneration
	public int updatePop()
	{
		curPop += 2;
		if(curPop >= totalPop)
			curPop = totalPop;
		
		return curPop;
	}
	
	//collision function takes a ship as a parameter and adjusts planet population accordingly
	public int collision(Ship ship){
		if(ship.getFaction() != faction){
			curPop -= 50;
			if(curPop <= 0){
				faction = ship.getFaction();
				curPop = (int)(totalPop * 0.25);
			}
		} else if(ship.getFaction() == faction){
			curPop += 50;
			if(curPop >= totalPop)
				curPop = totalPop;
		}
		return curPop;
	}
	
	public void changeFac(int new_faction)
	{
		faction = new_faction;
	}
	
	public int shipLaunch()
	{
		return 0;
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
		sr.circle(x, y, radius);
		sr.end();
		
		
	}
	
	
}

package com.mygdx.solarcolony.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.physics.box2d.BodyDef;

public class Planet extends BodyDef {
	private int x;			// x-coordinate
	private int y;			// y-coordinate
	private int radius;			// Planet radius
	private int faction;			// Player number
	private int totalPop;			// Max population
	private int curPop;			// Current population
	private boolean isSelected;			// Planet selection

	public Planet(int x, int y, int radius, int faction) {
		this.isSelected = false;
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.faction = faction;
		// placeholder population values, can be changed for better gameplay
		this.totalPop = 750 + (int) (radius*1.2);
		this.curPop = totalPop / 2;
	}

	//automatic function for population regeneration 
	public int updatePop() {
		curPop += 1;
		if (curPop >= totalPop)
			curPop = totalPop;

		return curPop;
	}

	//collision function takes a ship as a parameter and adjusts planet population accordingly
	public int collision(Ship ship) {
		if (ship.getFaction() != faction) {
			curPop -= 75;
			if (curPop <= 0) {
				faction = ship.getFaction();
				curPop = 75;
			}
		} else if (ship.getFaction() == faction) {
			curPop += 75;
			if (curPop >= totalPop)
				curPop = totalPop;
		}
		return curPop;
	}

	public int getPop() {
		return curPop;
	}

	public void shipPop() {
		curPop -= 75;
	}

	public void changeFac(int new_faction) {
		faction = new_faction;
	}

	public int getFac() {
		return faction;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int shipLaunch() {
		return 0;
	}

	public void setSelected(boolean select) {
		this.isSelected = select;
	}

	public boolean isSelected() {
		return this.isSelected;
	}

	//planet drawing function, current not being used except for planet select outline
	public void draw(ShapeRenderer sr) {
		if (faction == 0) {
			sr.setColor(1, 1, 1, 1);
		} else if (faction == 1) {
			sr.setColor(0, 1, 0, 1);
		} else if (faction == 2) {
			sr.setColor(1, 0, 0, 1);
		} else if (faction == 3) {
			sr.setColor(0, 1, 0, 1);
		}

		sr.begin(ShapeType.Filled);
		sr.circle(x, y, radius);
		sr.end();

		//draws the outline of the ship to be 10 pixels larger than the radius of planet
		if (isSelected) {
			sr.begin(ShapeType.Line);
			sr.circle(x, y, radius + 10);
			sr.end();
		}


		//draw population onto planet
		BitmapFont font = new BitmapFont();

		SpriteBatch spriteBatch = new SpriteBatch();

		spriteBatch.begin();
		CharSequence str = Integer.toString(curPop);
		font.draw(spriteBatch, str, x - 15, y + 10);
		spriteBatch.end();

	}

	//check if px & px are within the area of the planet
	public boolean contains(float px, float py) {
		if (Math.pow((px - x), 2) + Math.pow((py - y), 2) < Math.pow(radius, 2))
			return true;
		else
			return false;
	}

	public int getRadius() {
		return this.radius;
	}
	
}

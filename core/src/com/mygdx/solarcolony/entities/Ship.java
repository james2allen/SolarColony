package com.mygdx.solarcolony.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import static com.badlogic.gdx.physics.box2d.BodyDef.BodyType.DynamicBody;

public class Ship extends BodyDef {
	protected int faction;

	private int x;
	private int y;

	private double dx;
	private double dy;

	protected float radDir;

	private Vector2 vel;

	private int speed = 500;
	
	public Ship(int x, int y, double dx, double dy, int faction){
		this.dx = dx;
		this.dy = dy;

		//System.out.println("ship1 dx:" +dx  + ", dy: " + dy);

		this.radDir = 0;
		this.faction = faction;
		//set to dynamic body because it will be traveling
		this.type = DynamicBody;

		//multiply unit vector with speed of ship
		vel = new Vector2((float)dx * this.speed, (float)dy * this.speed);


		//System.out.println("ship2 dx:" +dx * this.speed + ", dy: " + dy* this.speed);

	}

	public Vector2 getSpeed()
	{
		return vel;
	}
	
	public int getFaction()
	{
		return faction;
	}

	//function draws ship to shape renderer
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
		sr.rect(x, y, 0,0, 2, 5,1.f,1.f, vel.angle());
		sr.end();
		
	}

	public void setCoords(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public void setRotation()
	{
		//todo, update rotation
	}

	public int getX()
	{
		return this.x;
	}

	public int getY()
	{
		return this.y;
	}
}

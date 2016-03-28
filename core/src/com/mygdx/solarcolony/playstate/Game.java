package com.mygdx.solarcolony.playstate;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.solarcolony.entities.Planet;
import com.mygdx.solarcolony.entities.Ship;


public class Game implements ApplicationListener{
	
	public static final String TITLE = "Solar Colony";
	public static final int V_WIDTH = 800;
	public static final int V_HEIGHT = 600;
	
	
	private SpriteBatch sb;
	private OrthographicCamera cam;
	private OrthographicCamera hudCam;
	

	private Planet[] planets;
	private Ship[] ships;
	private int numShips;
	private ShapeRenderer sr;
	private MyInputProcessor inputProcessor;
	
	public SpriteBatch getSpriteBatch() {return sb;}
	public OrthographicCamera getCamera() {return cam;}
	public OrthographicCamera getHUDCamera() {return hudCam;}
	
	@Override
	public void create() {
		ships = new Ship[40];
		numShips = 0;
		planets = new Planet[12];
		
		//create and translate camera and update it
		cam = new OrthographicCamera(V_WIDTH, V_HEIGHT);
		cam.translate(V_WIDTH/2, V_HEIGHT/2);
		cam.update();
		
		sr = new ShapeRenderer();
		
		int x = randInt(60,10), y = randInt(60,10);
		int faction = 1;
		
		/*
		 * 	TODO: This is the loop that gens the planets
		 * 	need to write it so that there's enough variance
		 * 	but yet the map is balanced & all planets are within the screen
		 * 
		 */
		for(int i = 0; i< 9; i++) {
			if (i != 0)
				faction = 0;
			if (i == 8)
				faction = 2;

			int radius = randInt(45, 15);

			x += randInt(200, 100);
			y += randInt(100, 50);

			if (x >= 800 || x + radius >= 800) {
				x = randInt(60, 10);
				y += randInt(175, 50);
			}
			if (y >= 600)
				y -= 150;
			else if (y <= 0)
				y += 100;

			planets[i] = new Planet(x, y, radius, faction);
		}
		
		inputProcessor = new MyInputProcessor();
		inputProcessor.setPlanets(planets);
		Gdx.input.setInputProcessor(inputProcessor);
		
	}
	
	public void update()
	{
		if(inputProcessor.getButton() == 2)
		{
			int [] coords = inputProcessor.getCoords();
			shipLaunch(coords[0], coords[1]);
		}
	}
	
	private void shipLaunch(int x, int y) {
		Planet pLaunch = planets[8];
		boolean selected = false;
		
		for(int i = 0; i < 9; i++)
		{
			if(planets[i].isSelected())
			{
				pLaunch = planets[i];
				selected = true;
				break;
			}
			
		}
		
		if(selected)
		{
			//first get the direction that the ship will be moving in
			int dx = x - pLaunch.getX();
			int dy = y - pLaunch.getY();
			ships[numShips] = new Ship(pLaunch.getX(), pLaunch.getY(), dx, dy, pLaunch.getFac());
		}
		
	}
	
	//continuous loop that game calls to update game state
	public void render(){
		
		update();
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		for(int i = 0; i< 9; i++){
			planets[i].draw(sr);
			if(planets[i].getFac()!=0)
				planets[i].updatePop();
		}

		for(int i=0; i<numShips; i++)
		{
			ships[i].draw(sr);
		}
	}
	
	public int randInt(int num, int range){
		int max = num+range,min = num-range;
		return (int)(Math.random()*(max - min))+ min;
	}
	
	@Override
	public void dispose(){
		//world.dispose();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	
}

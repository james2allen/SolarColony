package com.mygdx.solarcolony.playstate;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
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
	private ShapeRenderer sr;
	
	public SpriteBatch getSpriteBatch() {return sb;}
	public OrthographicCamera getCamera() {return cam;}
	public OrthographicCamera getHUDCamera() {return hudCam;}
	
	@Override
	public void create() {
		ships = new Ship[40];
		planets = new Planet[12];
		
		// TODO Auto-generated method stub
		//create and translate camera and update it
		cam = new OrthographicCamera(V_WIDTH, V_HEIGHT);
		cam.translate(V_WIDTH/2, V_HEIGHT/2);
		cam.update();
		
		sr = new ShapeRenderer();
		
		//Game.getCamera().update();
		for(int i = 0; i< 9; i++){
			int radius = (int) (Math.random()*(50-40))+40;
			planets[i] = new Planet(100, 100, radius);
			planets[i].draw(sr);
		}
		
	}
	
	
	//continuous loop that game calls to update game state
	public void render(){
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		for(int i = 0; i< 9; i++){
			planets[i].draw(sr);
		}
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

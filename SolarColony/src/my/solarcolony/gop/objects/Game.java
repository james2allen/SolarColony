package my.solarcolony.gop.objects;

import org.lwjgl.opengl.GL11;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import my.solarcolony.gop.entities.Planet;
import my.solarcolony.gop.entities.Ship;

public class Game implements ApplicationListener{
	
	public static final String TITLE = "Solar Colony";
	public static final int V_WIDTH = 460;
	public static final int V_HEIGHT = 320;
	
	private SpriteBatch sb;
	private OrthographicCamera cam;
	private OrthographicCamera hudCam;
	

	private Planet[] planets;
	private Ship[] ships;
	private ShapeRenderer sr;
	
	public SpriteBatch getSpriteBatch() {return sb;}
	public OrthographicCamera getCamera() {return cam;}
	public OrthographicCamera getHUDCamera() {return hudCam;}
	
	public void create()
	{
		//world = new World(new Vector2(0,0f), true);
		
		//Gdx.input.setInputProcessor(this);
		
		//create and translate camera and update it
		cam = new OrthographicCamera(V_WIDTH, V_HEIGHT);
		cam.translate(V_WIDTH/2, V_HEIGHT/2);
		cam.update();
		
		ships = new Ship[40];
		planets = new Planet[12];
		
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
		
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
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

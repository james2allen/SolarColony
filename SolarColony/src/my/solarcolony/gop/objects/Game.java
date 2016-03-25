package my.solarcolony.gop.objects;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Game implements ApplicationListener{
	public static final String TITLE = "Solar Colony";
	public static final int V_WIDTH = 460;
	public static final int V_HEIGHT = 320;
	public static final int scale = 2;
	
	public void create()
	{
		//world = new World(new Vector2(0,0f), true);
		
		//Gdx.input.setInputProcessor(this);
		
		//camera = new OrthographicCamera(Gdx.graphics.getWidth(),
				//Gdx.graphics.getHeight());
		
	}
	
	public void render(){
		//camera.update();
		//Step the physics simulation forward at a rate of 60hz
		//world.step(1f/60f, 6, 2);
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

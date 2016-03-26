package my.solarcolony.gop.objects;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import my.solarcolony.gop.entities.Planet;
import my.solarcolony.gop.entities.Ship;

public class Main extends ApplicationAdapter implements InputProcessor {
	
	private Box2DDebugRenderer debugRenderer;
	private World world;
	private static Planet[] planets;
	private static Ship[] ships;
	private static ShapeRenderer sr;
	
	public static void main(String[] args)
	{
		ships = new Ship[40];
		planets = new Planet[12];
		
		sr = new ShapeRenderer();
		
		LwjglApplicationConfiguration cfg = 
				new LwjglApplicationConfiguration();
		
		cfg.title = Game.TITLE;
		cfg.width = Game.V_WIDTH;
		cfg.height = Game.V_HEIGHT;
		cfg.resizable = false;
		
		new LwjglApplication(new Game(), cfg);
		
	}
	
	
	public void createPlanets(){
		Game.getCamera().update();
		for(int i = 0; i< 9; i++){
			int radius = (int) (Math.random()*(50-40))+40;
			planets[i] = new Planet(100, 100, radius);
		}
		
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}

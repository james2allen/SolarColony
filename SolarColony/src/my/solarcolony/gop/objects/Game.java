package my.solarcolony.gop.objects;

import org.lwjgl.opengl.GL11;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Game implements ApplicationListener{
	
	public static final String TITLE = "Solar Colony";
	public static final int V_WIDTH = 460;
	public static final int V_HEIGHT = 320;
	
	private SpriteBatch sb;
	private OrthographicCamera cam;
	private OrthographicCamera hudCam;
	
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
		
	}
	
	//continuous loop that game calls to update game state
	public void render(){
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
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

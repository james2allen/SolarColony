package com.mygdx.solarcolony.playstate;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.solarcolony.entities.Planet;
import com.mygdx.solarcolony.entities.Ship;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Input.Keys;

import java.util.Iterator;
import java.util.Random;


public class Game implements ApplicationListener{
	
	public static final String TITLE = "Solar Colony";
	public static final int V_WIDTH = 800;
	public static final int V_HEIGHT = 600;
	
	
	private SpriteBatch sb;
	private OrthographicCamera cam;
	private OrthographicCamera b2dCam;

	private World world;
	private Box2DDebugRenderer b2dr;
	//private static int PPM = 25;
	

	private Planet[] planets;
	private Ship[] ships;
	private int numShips;
	private ShapeRenderer sr;
	private MyInputProcessor inputProcessor;
	
	public SpriteBatch getSpriteBatch() {return sb;}
	public OrthographicCamera getCamera() {return cam;}
	
	@Override
	public void create() {
		ships = new Ship[40];
		numShips = 0;
		planets = new Planet[12];

		world = new World(new Vector2(0,0), false);

		b2dr = new Box2DDebugRenderer();
		
		//create and translate camera and update it
		cam = new OrthographicCamera(V_WIDTH, V_HEIGHT);
		cam.setToOrtho(false, V_WIDTH, V_HEIGHT);
		cam.update();

		//create and translate camera and update it
		b2dCam = new OrthographicCamera(V_WIDTH, V_HEIGHT);
		b2dCam.setToOrtho(false, V_WIDTH, V_HEIGHT);
		b2dCam.update();
		
		sr = new ShapeRenderer();
		sb = new SpriteBatch();
		
		//int x = randInt(1,99), y = randInt(1,99);
		int faction = 1;
		
		/*
		 * 	TODO: This is the loop that gens the planets
		 * 	need to write it so that there's enough variance
		 * 	but yet the map is balanced & all planets are within the screen
		 * 
		 */
		for(int i = 0; i< 9; i++) {
            int x = randInt(1,V_WIDTH-1);         // Planet x-coordinate
            int y = randInt(1,V_HEIGHT-1);         // Planet y-coordinate
            int radius = randInt(10, 60);            // Planet radius

            // Check if X coordinate of planet is out of bounds
            while(true){
                if ((x+radius)>=V_WIDTH || (x-radius)<=0){
                    x = randInt(1,V_WIDTH-1);
                }
                else
                    break;
            }

            // Check if Y coordinate of planet is out of bounds
            while(true){
                if ((y+radius)>=V_HEIGHT || (y-radius)<=0){
                    y = randInt(1,V_HEIGHT-1);
                }
                else
                    break;
            }

			float density = 1.0f;

			if(i > 0 && i < 8)
				faction = 0;
			else if(i == 8)
				faction = 2;

			//planet creation... will be moved into its own method
			planets[i] = new Planet(x, y, radius, faction);

			BodyDef planet = new BodyDef();

			planet.type = BodyDef.BodyType.StaticBody;
			planet.position.set(x,y);

			Body body = world.createBody(planet);

			CircleShape shape = new CircleShape();
			shape.setRadius(radius);

			FixtureDef fixDef = new FixtureDef();

			fixDef.shape = shape;
			fixDef.density = density;

			body.createFixture(fixDef);

			shape.dispose();

			world.setContactListener(new ListenerClass(world, planets, ships));
		}

		//initialize mouse controls
		inputProcessor = new MyInputProcessor();
		inputProcessor.setPlanets(planets);
		Gdx.input.setInputProcessor(inputProcessor);

	}

	public void removeShips(){

	}
	
	public void update(float dt)
	{
		//if right click
		if(inputProcessor.getButton() == 2)
		{
			//get right click coords
			int [] coords = inputProcessor.getCoords();

			//launch ship towards those coords
			shipLaunch(coords[0], coords[1]);
			//System.out.println(coords[0] + " "+ coords[1]);
		}

		world.step(dt, 6, 2);
	}


	private void shipLaunch(int x, int y) {
		Planet pLaunch = planets[8]; //temporary init

		boolean selected = false;

		//loop through planet array
		for(int i = 0; i < 9; i++)
		{
			//if there is a planet that is currently selected and they have enough pop to launch
			if(planets[i].isSelected() && planets[i].getPop()-75 >= 100)
			{
				//update ship population for launch
                planets[i].shipPop();

				//save launching planet
				pLaunch = planets[i];
				selected = true;
				break;
			}

            /*if(planets[i].getPop()-50 >= 100)
            {
                BitmapFont font = new BitmapFont();

                SpriteBatch spriteBatch = new SpriteBatch();

                spriteBatch.begin();
                CharSequence str = "Not enough population to launch!";
                font.draw(spriteBatch, str, 50, 50);
                spriteBatch.end();

            }*/
			
		}

		//if a planet was selected
		if(selected)
		{
			//first get the direction that the ship will be moving in
			int dx = x - pLaunch.getX();
			int dy = y - pLaunch.getY() ;

			/*System.out.println("crosshair x:" + x + ", y: " + y);
			System.out.println("ship x diff:" +dx + ", y diff: " + dy);*/

			//get the magnitude of the vector
			double mag = Math.pow(dx ,2) + Math.pow(dy,2);
			mag = Math.sqrt(mag);

			//get the unit vectors
			double vx = dx/mag;
			double vy = dy/mag;

			//create new ship
			ships[numShips] = new Ship(pLaunch.getX(), pLaunch.getY(), vx, vy, pLaunch.getFac());
			ships[numShips].position.set((float) (pLaunch.getX()+(pLaunch.getRadius() * vx)),
					(float) (pLaunch.getY()+ (pLaunch.getRadius() * vy)));


			//ship init box2d, may move to own method
			Body body = world.createBody(ships[numShips]);

			body.setLinearVelocity(ships[numShips].getSpeed());

			PolygonShape ship = new PolygonShape();
			ship.setAsBox(5,2);

			FixtureDef fixtureDef = new FixtureDef();
			fixtureDef.shape = ship;

			body.createFixture(ship, 0.0f);
			body.setBullet(true);
			body.setTransform(body.getPosition(), ships[numShips].getSpeed().angleRad());

			ship.dispose();

			numShips++;
		}
		
	}
	
	//continuous loop that game calls to update game state & draw
	public void render(){


		update(1.f/60.f);

		//clear screen
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0);
		Texture bg = new Texture("desktop/src/resources/pixel_space.png");
		sb.begin();
		sb.draw(bg ,0,0);
		sb.end();

		//render box2d debug cam
		b2dr.render(world, cam.combined);
		boolean checkPause = false;
		if(Gdx.input.isKeyPressed(Keys.A)){
			Gdx.app.exit();
		}
		else {
			//planet drawing loop
			for (int i = 0; i < 9; i++) {
				planets[i].draw(sr);
				if (planets[i].getFac() != 0)
					planets[i].updatePop();
			}

        /*ship drawing loop commented out due to use of box2d renderer for now
		for(int i=0; i<numShips; i++)
		{
			ships[i].draw(sr);
			//ships[i].shipMove();
			if(ships[i].getX() < 0 || ships[i].getX() > V_WIDTH ||
                    ships[i].getY() < 0 || ships[i].getY() > V_HEIGHT)
                shipRemove(i);

		}*/
		}
	}

	//function removes ship, does not account for box2d, must be fixed
	public void shipRemove(int n)
	{
		for(int i = n; i < numShips; i++)
		{
			ships[i] = ships[i+1];
		}
		ships[numShips-1] = null;
		numShips--;
	}

	/*
	 * function returns a random int using num & a range where the final int
	 * can be the original num plus or minus 0 - range
	 */
    public int randInt(int min, int max){
        if (min>=max){
            throw new IllegalArgumentException("Max must be greater than min value");
        }
        Random random = new Random();
        int randomNum = random.nextInt((max-min)+1) + min;
        return randomNum;
    }
	
	@Override
	public void dispose(){

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

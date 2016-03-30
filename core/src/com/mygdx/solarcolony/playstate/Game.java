package com.mygdx.solarcolony.playstate;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
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


public class Game implements ApplicationListener{
	
	public static final String TITLE = "Solar Colony";
	public static final int V_WIDTH = 800;
	public static final int V_HEIGHT = 600;
	
	
	private SpriteBatch sb;
	private OrthographicCamera cam;
	private OrthographicCamera b2dCam;

	private World world;
	private Box2DDebugRenderer b2dr;
	

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

			float density = 1.0f;

			planets[i] = new Planet(x, y, radius, faction);

			BodyDef planet = new BodyDef();

			planet.type = BodyDef.BodyType.StaticBody;
			planet.position.set(x,y);

			if (i == 0)
				System.out.println("Planet " + i + " x: " + planet.position.x+ " Y: " + planet.position.y);

			Body body = world.createBody(planet);

			CircleShape shape = new CircleShape();
			shape.setRadius(radius);

			FixtureDef fixDef = new FixtureDef();

			fixDef.shape = shape;
			fixDef.density = density;

			body.createFixture(fixDef);


			shape.dispose();



		}
		
		inputProcessor = new MyInputProcessor();
		inputProcessor.setPlanets(planets);
		Gdx.input.setInputProcessor(inputProcessor);
		
	}
	
	public void update(float dt)
	{
		if(inputProcessor.getButton() == 2)
		{
			int [] coords = inputProcessor.getCoords();
			shipLaunch(coords[0], coords[1]);
		}

		world.step(dt, 6, 2);
	}
	
	private void shipLaunch(int x, int y) {
		Planet pLaunch = planets[8];
		boolean selected = false;
		
		for(int i = 0; i < 9; i++)
		{
			if(planets[i].isSelected() && planets[i].getPop()-50 >= 100)
			{
                planets[i].shipPop();
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
		
		if(selected)
		{
			//first get the direction that the ship will be moving in
			int dx = x - pLaunch.getX();
			int dy = (V_HEIGHT - y) - pLaunch.getY() ;

			System.out.println("crosshair x:" + x + ", y: " + y);
			System.out.println("ship x diff:" +dx + ", y diff: " + dy);

			double mag = Math.pow(dx ,2) + Math.pow(dy,2);
			mag = Math.sqrt(mag);

			double vx = dx/mag;
			double vy = dy/mag;

			ships[numShips] = new Ship(pLaunch.getX(), pLaunch.getY(), vx, vy, pLaunch.getFac());
			ships[numShips].position.set((float) (pLaunch.getX()+(pLaunch.getRadius() * vx)),
					(float) (pLaunch.getY()+ (pLaunch.getRadius() * vy)));


			Body body = world.createBody(ships[numShips]);

			body.setLinearVelocity(ships[numShips].getSpeed());

			PolygonShape ship = new PolygonShape();
			ship.setAsBox(5,2);

			FixtureDef fixtureDef = new FixtureDef();
			fixtureDef.shape = ship;

			body.createFixture(ship, 0.0f);
			body.setTransform(body.getPosition(), ships[numShips].getSpeed().angleRad());

			ship.dispose();

			numShips++;
		}
		
	}
	
	//continuous loop that game calls to update game state & draw
	public void render(){

		
		update(1.f/60.f);
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		b2dr.render(world, b2dCam.combined);

        //planet drawing loop
		for(int i = 0; i< 9; i++){
			planets[i].draw(sr);
			if(planets[i].getFac()!=0)
				planets[i].updatePop();
		}

        /*ship drawing loop
		for(int i=0; i<numShips; i++)
		{
			ships[i].draw(sr);
			//ships[i].shipMove();
			if(ships[i].getX() < 0 || ships[i].getX() > V_WIDTH ||
                    ships[i].getY() < 0 || ships[i].getY() > V_HEIGHT)
                shipRemove(i);

		}*/

		if(numShips>0)
		{
			//System.out.println(ships[0].position.x + " " + ships[0].position.y);
		}


	}

	public void shipRemove(int n)
	{
		for(int i = n; i < numShips; i++)
		{
			ships[i] = ships[i+1];
		}
		ships[numShips-1] = null;
		numShips--;
	}
	
	private int randInt(int num, int range){
		int max = num+range,min = num-range;
		return (int)(Math.random()*(max - min))+ min;
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

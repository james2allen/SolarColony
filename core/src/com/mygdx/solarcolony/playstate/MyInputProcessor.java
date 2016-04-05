package com.mygdx.solarcolony.playstate;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.solarcolony.entities.Planet;
import javafx.scene.input.KeyCode;

public class MyInputProcessor implements InputProcessor
{
    private static int appWidth = 800;
    private static int appHeight = 600;
    
    private int buttonPressed = 0;
    
    private Planet [] planets;
    private int [] mCoords = new int [2];
    
    public void setPlanets(Planet[] parr)
	{
		this.planets = parr;
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
	
	public int getButton()
	{
		return buttonPressed;
	}
	
	public int[] getCoords()
	{
		return mCoords;
	}

	//Mouse input processor
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		//checks if mouse left clicked
		if(button ==  Input.Buttons.LEFT)
		{
			//Loops through planets
			for(int i = 0; i < 9; i++)
		    {
				//checks if left click lands on a planet
		        if(planets[i].contains(screenX, (float)600 - screenY))
		        {
		        	for(int j = 0; j < 9; j++) {
						//deselects other planets
						if (planets[j].isSelected() && i != j) {
							planets[j].setSelected(false);
						}
					}

		        	//selects new planet
					if(planets[i].getFac() == 1)
		            	planets[i].setSelected(true);
		            buttonPressed = 1;
		            return true;
		            
		        }
		        
		    }
			//if no planets were clicked, return false
			return false;
		}
		//else check for right mouse click
		else if(button == Input.Buttons.RIGHT)
		{
			mCoords[0] = screenX;
			mCoords[1] = 600 - screenY; // Y values seem to be reversed for mouse clicks
			buttonPressed = 2;
			return true;
		}
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		buttonPressed = 0;
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
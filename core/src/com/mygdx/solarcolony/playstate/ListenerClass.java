package com.mygdx.solarcolony.playstate;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.solarcolony.entities.Planet;
import com.mygdx.solarcolony.entities.Ship;

public class ListenerClass implements ContactListener {

    private World world;
    private Planet planets[];
    private Ship ships[];
    private Ship re_ships[];

    public ListenerClass(World w, Planet p[], Ship s[])
    {
        world = w;
        planets = p;
        ships = s;
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    @Override
    public void beginContact(Contact contact) {
        Body ship, planet;
        if((contact.getFixtureA().getBody().isBullet() &&
                !contact.getFixtureB().getBody().isBullet())) {
            ship = contact.getFixtureA().getBody();
            planet = contact.getFixtureB().getBody();
        } else if(!contact.getFixtureA().getBody().isBullet() &&
                contact.getFixtureB().getBody().isBullet()){
            ship = contact.getFixtureA().getBody();
            planet = contact.getFixtureB().getBody();
        } else{
            return;
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    public Ship[] get_rem_ships()
    {
        return re_ships;
    }
}

package com.mygdx.solarcolony.entities;

/**
 * Created by James on 2016-04-09.
 * this class will hold all the general entity data for handling collisions
 */
public class Entity {
    private int type;       //planet or ship
    private int faction;    //faction of entity
    private int num;        //number corresponding to entity creation

    public Entity(int type, int faction, int num)
    {
        this.type = type;
        this.faction = faction;
        this.num = num;
    }

    public int getFac(){ return faction;}

    public int getType(){ return type;}

    public int getNum() { return num;}
}
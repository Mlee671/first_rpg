package mlee671.basicfx.characters;

import javafx.scene.image.Image;

public class BaseCharacter {

    private String name;
    private int[] image;
    private int health;
    private int str;
    private int agi;
    private int wis;
    private int id;

    public BaseCharacter(int i){
        this.name = "Unnamed";
        this.id = i;
        this.str = (int)(Math.random()*10);
        this.agi = (int)(Math.random()*10);
        this.wis = (int)(Math.random()*10);
        this.image = new int[]{(int)(Math.random()*5), (int)(Math.random()*3)};
        this.health = str*10 + agi*5 + wis*2;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getStr() {
        return str;
    }

    public int getAgi() {
        return agi;
    }

    public int getWis() {
        return wis;
    }

    public int[] getImage() {
        return image;
    }

    public int getId() {
        return id;
    }

}

package mlee671.basicfx.characters;

public class BaseCharacter {

    private String name;
    private int health;
    private int str;
    private int agi;
    private int wis;

    public BaseCharacter(){
        this.name = "Unnamed";
        this.str = (int)(Math.random()*10);
        this.agi = (int)(Math.random()*10);
        this.wis = (int)(Math.random()*10);
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

}

package mlee671.basicfx.characters;

import java.io.BufferedReader;
import java.io.FileReader;

import javafx.scene.image.Image;

public class BaseCharacter {

    protected String name;
    protected String race;
    protected String role;
    protected int[] tileCoords;
    protected int health;
    protected int currentHealth;
    protected int str;
    protected int agi;
    protected int wis;
    protected int id;
    protected boolean isDead;
    protected Image image;
    protected int tileSize;

    public BaseCharacter(int id, String type){  
        
        this.id = id;
        this.tileSize = 18;
        this.tileCoords = new int[2];
        int[] mod = getModifiers(type);
        this.str = (int)(Math.random()*10) + mod[1];
        this.agi = (int)(Math.random()*10) + mod[2];
        this.wis = (int)(Math.random()*10) + mod[3];
        this.health = (int) ((str + agi/2 + wis/5) * mod[0]/10);
        this.name = race + " " + role;
        this.currentHealth = health;
        this.isDead = false;
    }

    protected int[] getModifiers(String type) {
        int[] mod = new int[4];
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/mlee671/basicfx/chardata/" + type + ".txt"))) {
            String line;
            int lineNum = 0;
            int findRace = (int)(Math.random()*3) * 10;
            int findRole = (int)(Math.random()*5) + findRace + 1;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (lineNum == findRace) {
                    this.race = (parts[0]);
                    tileCoords[1] = Integer.parseInt(parts[parts.length-1].trim());
                    for (int j = 1; j < parts.length-1; j++) {
                        mod[j-1] = Integer.parseInt(parts[j].trim());
                    }           
                } else if (lineNum == findRole) {
                    this.role = (parts[0]);
                    tileCoords[0] = Integer.parseInt(parts[parts.length-1].trim());
                    for (int j = 1; j < parts.length-1; j++) {
                    mod[j-1] += Integer.parseInt(parts[j].trim());
                    }
                }
                
                lineNum++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mod;
    }

    public String getName() {
        return name;
    }

    public String getHealth() {
        return currentHealth + "/" + health;
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

    public int[] getTileCoords() {
        return tileCoords;
    }

    public int getId() {
        return id;
    }

    public void setId(int numericValue) {
        this.id = numericValue;
    }

    public Integer getInit() {
        return 30 - (agi + wis / 2);
    }

    public int getAttack() {
        return str + agi / 2;
    }

    public String getRole() {
        return role;
    }

    public void takeDamage(int damage) {
        currentHealth -= damage;
        if (currentHealth < 0) {
            currentHealth = 0;
            isDead = true;
        }
    }

    public boolean isDead() {
        return isDead;
    }

    public Image getImage() {
        return image;
    }

}

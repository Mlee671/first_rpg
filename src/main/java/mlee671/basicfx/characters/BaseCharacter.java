package mlee671.basicfx.characters;

import java.io.BufferedReader;
import java.io.FileReader;

public class BaseCharacter {

    protected String name;
    protected String race;
    protected String role;
    protected int[] image;
    protected int health;
    protected int str;
    protected int agi;
    protected int wis;
    protected int id;

    public BaseCharacter(int id, String type){  
        this.id = id;
        this.image = new int[2];
        int[] mod = getModifiers(type);
        System.out.println("Modifiers: " + mod[0] + ", " + mod[1] + ", " + mod[2] + ", " + mod[3]);
        this.str = (int)(Math.random()*10) + mod[1];
        this.agi = (int)(Math.random()*10) + mod[2];
        this.wis = (int)(Math.random()*10) + mod[3];
        this.health = (int) ((str + agi/2 + wis/5) * mod[0]/10);
        this.name = race + " " + role;
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
                    System.out.println("Found race line: " + line);
                    this.race = (parts[0]);
                    image[1] = Integer.parseInt(parts[parts.length-1].trim());
                    for (int j = 1; j < parts.length-1; j++) {
                        mod[j-1] = Integer.parseInt(parts[j].trim());
                    }           
                } else if (lineNum == findRole) {
                    System.out.println("Found role line: " + line);
                    this.role = (parts[0]);
                    image[0] = Integer.parseInt(parts[parts.length-1].trim());
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

    public void setId(int numericValue) {
        this.id = numericValue;
    }

}

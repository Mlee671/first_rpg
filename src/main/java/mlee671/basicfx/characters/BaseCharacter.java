package mlee671.basicfx.characters;

import java.io.BufferedReader;
import java.io.FileReader;

public class BaseCharacter {

    private String name;
    private String race;
    private String role;
    private int[] image;
    private int health;
    private int str;
    private int agi;
    private int wis;
    private int id;

    public BaseCharacter(int i){
        int[] mod = new int[4];
        image = new int[2];
        double hpMod = 1.0;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/mlee671/basicfx/chardata/playerdata.txt"))) {
            String line;
            int lineNum = 0;
            int findRace = (int)(Math.random()*3) * 10;
            int findRole = (int)(Math.random()*5) + findRace;
            while ((line = reader.readLine()) != null) {
                if (lineNum == findRace) {
                    String[] parts = line.split(",");
                    race = parts[0];
                    hpMod *= Double.parseDouble(parts[1].trim());
                    image[1] = Integer.parseInt(parts[parts.length-1].trim());
                    for (int j = 2; j < parts.length-1; j++) {
                        mod[j-1] = Integer.parseInt(parts[j].trim());
                    }
                } else if (lineNum == findRole) {
                    String[] parts = line.split(",");
                    role = parts[0];
                    hpMod *= Double.parseDouble(parts[1].trim());
                    image[0] = Integer.parseInt(parts[parts.length-1].trim());
                    for (int j = 2; j < parts.length-1; j++) {
                        mod[j-1] += Integer.parseInt(parts[j].trim());
                    }
                }
                lineNum++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.name = race + " " + role;
        this.id = i;
        this.str = (int)(Math.random()*10) + mod[1];
        this.agi = (int)(Math.random()*10) + mod[2];
        this.wis = (int)(Math.random()*10) + mod[3];
        this.health = (int) ((str*10 + agi*5 + wis*2) * hpMod);
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

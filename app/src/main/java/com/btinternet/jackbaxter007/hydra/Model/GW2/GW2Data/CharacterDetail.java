package com.btinternet.jackbaxter007.hydra.Model.GW2.GW2Data;

import java.util.List;

public class CharacterDetail {
    private String name;
    private String race;
    private String gender;
    private String profession;
    private String level;
    private String age;


    public String getName() {
        return name;
    }

    public String getRace() {
        return race;
    }

    public String getGender() {
        return gender;
    }

    public String getProfession() {
        return profession;
    }

    public String getLevel() {
        return level;
    }

    public String getAge() {
        int days = Integer.parseInt(age)/1440;
        String sdays = String.valueOf(days)+" Days";
        return sdays;
    }

}

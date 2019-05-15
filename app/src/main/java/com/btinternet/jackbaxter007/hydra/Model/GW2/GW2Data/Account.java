package com.btinternet.jackbaxter007.hydra.Model.GW2.GW2Data;

import java.util.List;

public class Account {

    private String id;
    private String name;
    private String age;
    private String world;
    private List guilds;
    private List guild_leader;
    private String created;
    private List access;
    private String commander;
    private String fractal_level;
    private String daily_ap;
    private String monthly_ap;
    private String wvw_rank;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        int days = Integer.parseInt(age)/1440;
        String sdays = String.valueOf(days)+" Days";
        return sdays;
    }

    public String getWorld() {
        return world;
    }

    public List getGuilds() {
        return guilds;
    }

    public List getGuild_leader() {
        return guild_leader;
    }

    public String getCreated() {
        return created;
    }

    public List getAccess() {
        return access;
    }

    public String getCommander() {
        return commander;
    }

    public String getFractal_level() {
        return fractal_level;
    }

    public String getDaily_ap() {
        return daily_ap;
    }

    public String getMonthly_ap() {
        return monthly_ap;
    }

    public String getWvw_rank() {
        return wvw_rank;
    }
}




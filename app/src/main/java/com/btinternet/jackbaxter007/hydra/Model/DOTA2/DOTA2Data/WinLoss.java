package com.btinternet.jackbaxter007.hydra.Model.DOTA2.DOTA2Data;

public class WinLoss {
    private String win;
    private String lose;

    public String getWin() {
        return win;
    }

    public String getLose() {
        return lose;
    }

    public String getWL() {
        int twin = Integer.parseInt(win);
        int tlose = Integer.parseInt(lose);
        if(tlose !=0) {
            int wL = (twin / tlose);
            return String.valueOf(wL);
        }
        return "0";
    }
}

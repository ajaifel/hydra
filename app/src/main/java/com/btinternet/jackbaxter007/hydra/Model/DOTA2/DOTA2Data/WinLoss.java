package com.btinternet.jackbaxter007.hydra.Model.DOTA2.DOTA2Data;

import java.text.DecimalFormat;

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
            DecimalFormat df = new DecimalFormat("#.##");
            double wL = 100*((double)twin /(double)(tlose+twin));
            return String.valueOf(df.format(wL)+"%");
        }
        return "0";
    }
}

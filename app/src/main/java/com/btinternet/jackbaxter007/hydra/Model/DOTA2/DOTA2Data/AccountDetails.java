package com.btinternet.jackbaxter007.hydra.Model.DOTA2.DOTA2Data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AccountDetails<mmr_estimate> {
    private String rank_tier;
    private String solo_competitive_rank;
    private String competitive_rank;
    @SerializedName("ModelState")
    private mmr_estimate mmr_estimate;

    public String getRank_tier() {
        if (rank_tier == null){
            return "0";
        }
        return rank_tier;
    }

    public String getSolo_competitive_rank() {
        if (solo_competitive_rank == null){
            return "0";
        }
        return solo_competitive_rank;
    }

    public String getCompetitive_rank() {
        if (competitive_rank == null){
            return "0";
        }
        return competitive_rank;
    }

    public String getMmr_estimate() {
        if (mmr_estimate == null) {
            return "0";
        }
        String str = (mmr_estimate.toString());
        return str;
    }
}

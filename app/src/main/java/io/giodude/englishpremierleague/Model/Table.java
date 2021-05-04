package io.giodude.englishpremierleague.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Table {

    @SerializedName("idStanding")
    @Expose
    private String idStanding;
    @SerializedName("intRank")
    @Expose
    private String intRank;
    @SerializedName("idTeam")
    @Expose
    private String idTeam;
    @SerializedName("strTeam")
    @Expose
    private String strTeam;
    @SerializedName("strTeamBadge")
    @Expose
    private String strTeamBadge;
    @SerializedName("strLeague")
    @Expose
    private String strLeague;
    @SerializedName("strSeason")
    @Expose
    private String strSeason;
    @SerializedName("intPlayed")
    @Expose
    private String intPlayed;
    @SerializedName("intWin")
    @Expose
    private String intWin;
    @SerializedName("intLoss")
    @Expose
    private String intLoss;
    @SerializedName("intDraw")
    @Expose
    private String intDraw;
    @SerializedName("dateUpdated")
    @Expose
    private String dateUpdated;

    public Table(String idStanding, String intRank, String idTeam, String strTeam, String strTeamBadge, String strLeague, String strSeason, String intPlayed, String intWin, String intLoss, String intDraw, String dateUpdated) {
        this.idStanding = idStanding;
        this.intRank = intRank;
        this.idTeam = idTeam;
        this.strTeam = strTeam;
        this.strTeamBadge = strTeamBadge;
        this.strLeague = strLeague;
        this.strSeason = strSeason;
        this.intPlayed = intPlayed;
        this.intWin = intWin;
        this.intLoss = intLoss;
        this.intDraw = intDraw;
        this.dateUpdated = dateUpdated;
    }

    public String getIdStanding() {
        return idStanding;
    }

    public String getIntRank() {
        return intRank;
    }

    public String getIdTeam() {
        return idTeam;
    }

    public String getStrTeam() {
        return strTeam;
    }

    public String getStrTeamBadge() {
        return strTeamBadge;
    }

    public String getStrLeague() {
        return strLeague;
    }

    public String getStrSeason() {
        return strSeason;
    }

    public String getIntPlayed() {
        return intPlayed;
    }

    public String getIntWin() {
        return intWin;
    }

    public String getIntLoss() {
        return intLoss;
    }

    public String getIntDraw() {
        return intDraw;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }
}

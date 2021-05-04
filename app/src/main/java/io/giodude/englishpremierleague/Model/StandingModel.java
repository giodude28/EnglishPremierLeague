package io.giodude.englishpremierleague.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StandingModel {

    @SerializedName("table")
    @Expose
    private List<Table> table = null;

    public List<Table> getTable(){
        return table;
    }
}

package io.giodude.englishpremierleague.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LiveModel {

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;


    public List<Datum> getData(){
        return data;
    }
}

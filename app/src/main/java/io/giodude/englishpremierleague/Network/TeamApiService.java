package io.giodude.englishpremierleague.Network;

import java.util.List;

import io.giodude.englishpremierleague.Model.Team;
import io.giodude.englishpremierleague.Model.TeamModel;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface TeamApiService {


    @GET("search_all_teams.php?l=English%20Premier%20League")
    Observable<TeamModel> getTeam();
}

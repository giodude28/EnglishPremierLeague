package io.giodude.englishpremierleague.Network;

import java.util.List;

import javax.inject.Inject;

import io.giodude.englishpremierleague.Model.LiveModel;
import io.giodude.englishpremierleague.Model.MatchesModel;
import io.giodude.englishpremierleague.Model.StandingModel;
import io.giodude.englishpremierleague.Model.Team;
import io.giodude.englishpremierleague.Model.TeamModel;
import io.reactivex.rxjava3.core.Observable;

public class Repository {

    private TeamApiService apiService;
    private MatchesApiService matchesApiService;
    private TableApiService tableApiService;
    private LiveApiService liveApiService;
    @Inject
    public Repository(TeamApiService apiService,MatchesApiService matchesApiService,TableApiService tableApiService,LiveApiService liveApiService){
        this.matchesApiService = matchesApiService;
        this.apiService = apiService;
        this.tableApiService = tableApiService;
        this.liveApiService = liveApiService;
    }

    public Observable<LiveModel> getLive(){
        return liveApiService.getLive();
    }
    public Observable<TeamModel> getTeam(){
        return apiService.getTeam();
    }

    public Observable<MatchesModel> getMatch(){
        return matchesApiService.getMatches();
    }

    public Observable<StandingModel> getTable(){
        return tableApiService.getTable();
    }
}

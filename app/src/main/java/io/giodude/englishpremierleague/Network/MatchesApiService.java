package io.giodude.englishpremierleague.Network;

import io.giodude.englishpremierleague.Model.MatchesModel;
import io.giodude.englishpremierleague.Model.TeamModel;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface MatchesApiService {

    @GET("eventspastleague.php?id=4328")
    Observable<MatchesModel> getMatches();
}

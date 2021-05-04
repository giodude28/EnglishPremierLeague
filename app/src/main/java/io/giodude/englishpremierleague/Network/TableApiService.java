package io.giodude.englishpremierleague.Network;

import io.giodude.englishpremierleague.Model.StandingModel;
import io.giodude.englishpremierleague.Model.TeamModel;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface TableApiService {

    @GET("lookuptable.php?l=4328&s=2020-2021")
    Observable<StandingModel> getTable();
}

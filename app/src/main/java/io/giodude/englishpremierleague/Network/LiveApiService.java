package io.giodude.englishpremierleague.Network;

import io.giodude.englishpremierleague.Model.LiveModel;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface LiveApiService {

    @GET("events/live")
    @Headers({"x-rapidapi-host: sportscore1.p.rapidapi.com",
            "x-rapidapi-key: 07e55202eemshd454005e3a79774p103cccjsn4b32f05d3a2f",
            "useQueryString: true"})
    Observable<LiveModel> getLive();
}

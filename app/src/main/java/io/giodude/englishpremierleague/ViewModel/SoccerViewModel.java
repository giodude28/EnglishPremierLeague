package io.giodude.englishpremierleague.ViewModel;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import io.giodude.englishpremierleague.Model.Datum;
import io.giodude.englishpremierleague.Model.Event;
import io.giodude.englishpremierleague.Model.LiveModel;
import io.giodude.englishpremierleague.Model.MatchesModel;
import io.giodude.englishpremierleague.Model.StandingModel;
import io.giodude.englishpremierleague.Model.Table;
import io.giodude.englishpremierleague.Model.Team;
import io.giodude.englishpremierleague.Model.TeamModel;
import io.giodude.englishpremierleague.Network.Repository;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SoccerViewModel extends ViewModel {

    private static final String TAG = "ViewModel";
    private MutableLiveData<List<Team>> teamList = new MutableLiveData<>();
    private MutableLiveData<List<Event>> eventList = new MutableLiveData<>();
    private MutableLiveData<List<Table>> tableList = new MutableLiveData<>();
    private MutableLiveData<List<Datum>> datumList = new MutableLiveData<>();
    private final CompositeDisposable disposables = new CompositeDisposable();

    private Repository repository;

    @ViewModelInject
    public SoccerViewModel(Repository repository) {
        this.repository = repository;
    }

    public LiveData<List<Team>> getTeamList() {
        return teamList;
    }

    public LiveData<List<Event>> getEventList() {
        return eventList;
    }

    public LiveData<List<Table>> getTableList() {
        return tableList;
    }

    public LiveData<List<Datum>> getDatumList() {
        return datumList;
    }

    public void getTeams() {
        disposables.add(repository.getTeam()
                .subscribeOn(Schedulers.newThread())
                .map(new Function<TeamModel, List<Team>>() {
                    public List<Team> apply(TeamModel teamResponse) throws Throwable {
                        List<Team> list = teamResponse.getTeams();
                        return list;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> teamList.setValue(result),
                        error -> Log.e(TAG, "getPokemons: " + error.getMessage())));

    }

    public void getMatches() {
        disposables.add(repository.getMatch()
                .subscribeOn(Schedulers.newThread())
                .map(new Function<MatchesModel, List<Event>>() {
                    public List<Event> apply(MatchesModel teamResponse) throws Throwable {
                        List<Event> list = teamResponse.getEvents();
                        return list;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> eventList.setValue(result),
                        error -> System.out.println(TAG + "getTable: " + error.getMessage())));
    }

    public void getLive() {
        disposables.add(repository.getLive()
                .subscribeOn(Schedulers.newThread())
                .map(new Function<LiveModel, List<Datum>>() {
                    public List<Datum> apply(LiveModel teamResponse) throws Throwable {
                        List<Datum> list = teamResponse.getData();
                        return list;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> datumList.setValue(result),
                        error -> System.out.println(TAG + "getTable: " + error.getMessage())));
    }

    public void getTables() {
        disposables.add(repository.getTable()
                .subscribeOn(Schedulers.newThread())
                .map(new Function<StandingModel, List<Table>>() {
                    public List<Table> apply(StandingModel standingResponse) throws Throwable {
                        List<Table> tables = standingResponse.getTable();
                        return tables;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> tableList.setValue(result),
                        error -> System.out.println(TAG + "getTable: " + error.getMessage())));

    }
}

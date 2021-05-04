package io.giodude.englishpremierleague.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import io.giodude.englishpremierleague.Adapter.TeamAdapte;
import io.giodude.englishpremierleague.Model.Team;
import io.giodude.englishpremierleague.Model.TeamModel;
import io.giodude.englishpremierleague.Network.Connection;
import io.giodude.englishpremierleague.R;
import io.giodude.englishpremierleague.ViewModel.SoccerViewModel;
import io.giodude.englishpremierleague.databinding.ActivityHomeViewBinding;
import io.giodude.englishpremierleague.databinding.ActivityTeamViewBinding;

@AndroidEntryPoint
public class TeamView extends Fragment {
    private static final String TAG = "TeamView";
    private SoccerViewModel viewModel;
    private ActivityTeamViewBinding binding;
    private TeamAdapte adapter;
    private List<Team> teamList;
    public ProgressBar progressBar;
    Connection connection;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ActivityTeamViewBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = view.findViewById(R.id.progress);
        Fresco.initialize(getActivity());
        connection = new Connection();
        if (connection.isConnected(getActivity())){
            observeData();
        }
    }

    private void observeData() {
        viewModel = new ViewModelProvider(this).get(SoccerViewModel.class);
        viewModel.getTeams();
        viewModel.getTeamList().observe(getViewLifecycleOwner(), new Observer<List<Team>>() {
            @Override
            public void onChanged(List<Team> pokemons) {
                if (pokemons.size() == 0) {
                    progressBar.setVisibility(View.VISIBLE);
                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    initRecyclerView(pokemons);
                    adapter.updateList(pokemons);
                }

            }
        });
    }

    private void initRecyclerView(List<Team> team) {
        binding.teamRV.setLayoutManager(new LinearLayoutManager((getContext())));
        adapter = new TeamAdapte(getContext(), team);
        binding.teamRV.setAdapter(adapter);
    }

}
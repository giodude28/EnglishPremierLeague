package io.giodude.englishpremierleague.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ProgressBar;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import io.giodude.englishpremierleague.Adapter.MatchesAdapter;
import io.giodude.englishpremierleague.Adapter.StandingAdapter;
import io.giodude.englishpremierleague.Adapter.TeamAdapte;
import io.giodude.englishpremierleague.Model.Event;
import io.giodude.englishpremierleague.Model.Team;
import io.giodude.englishpremierleague.Network.Connection;
import io.giodude.englishpremierleague.R;
import io.giodude.englishpremierleague.ViewModel.SoccerViewModel;
import io.giodude.englishpremierleague.databinding.ActivityHomeViewBinding;
import io.giodude.englishpremierleague.databinding.ActivityMatchesViewBinding;
import io.giodude.englishpremierleague.databinding.ActivityTeamViewBinding;

@AndroidEntryPoint
public class MatchesView extends Fragment {
    private ActivityMatchesViewBinding binding;
    private static final String TAG = "ViewModel";
    private SoccerViewModel viewModel;
    private MatchesAdapter matchesAdapter;
    private List<Event> eventList;
    public ProgressBar progressBar;
    Connection connection;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ActivityMatchesViewBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = view.findViewById(R.id.progress);
        connection = new Connection();
        if (connection.isConnected(getActivity())){
            observeData();
        }
    }

    private void observeData() {
        viewModel = new ViewModelProvider(this).get(SoccerViewModel.class);
        viewModel.getMatches();
        viewModel.getEventList().observe(getViewLifecycleOwner(), new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> event) {
                if (event.size() == 0) {
                    progressBar.setVisibility(View.VISIBLE);
                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    initRecyclerView(event);
                    matchesAdapter.updateList(event);
                }
            }
        });
    }

    private void initRecyclerView(List<Event> events) {
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        matchesAdapter = new MatchesAdapter(getActivity(), events);
        binding.recyclerview.setAdapter(matchesAdapter);
    }

//    @Override
//    public void onDestroyView() {
//        if (mAdView != null) {
//            ViewParent parent = mAdView.getParent();
//            if (parent != null && parent instanceof ViewGroup) {
//                ((ViewGroup) parent).removeView(mAdView);
//            }
//        }
//        super.onDestroyView();
//    }
}
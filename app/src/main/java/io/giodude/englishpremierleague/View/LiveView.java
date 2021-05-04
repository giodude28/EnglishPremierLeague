package io.giodude.englishpremierleague.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import io.giodude.englishpremierleague.Adapter.LiveAdapter;
import io.giodude.englishpremierleague.Adapter.MatchesAdapter;
import io.giodude.englishpremierleague.Model.Datum;
import io.giodude.englishpremierleague.Model.Event;
import io.giodude.englishpremierleague.Network.Connection;
import io.giodude.englishpremierleague.R;
import io.giodude.englishpremierleague.ViewModel.SoccerViewModel;
import io.giodude.englishpremierleague.databinding.ActivityHomeViewBinding;
import io.giodude.englishpremierleague.databinding.ActivityLiveViewBinding;
@AndroidEntryPoint
public class LiveView extends Fragment {
    private static final String TAG = "ViewModel";
    private ActivityLiveViewBinding binding;
    private LiveAdapter liveAdapter;
    private List<Datum> datumModel;
    private SoccerViewModel viewModel;
    private ProgressBar progressBar;
    private Connection connection;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ActivityLiveViewBinding.inflate(inflater, container, false);
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
        viewModel.getLive();
        viewModel.getDatumList().observe(getViewLifecycleOwner(), new Observer<List<Datum>>() {
            @Override
            public void onChanged(List<Datum> event) {
                if (event.size() == 0){
                    progressBar.setVisibility(View.VISIBLE);
                }else {
                    progressBar.setVisibility(View.INVISIBLE);
                    initRecyclerView(event);
                    liveAdapter.updateList(event);
                }
            }
        });
    }

    private void initRecyclerView(List<Datum> events) {
        binding.liveRV.setLayoutManager(new LinearLayoutManager(getContext()));
        liveAdapter = new LiveAdapter(getActivity(), events);
        binding.liveRV.setAdapter(liveAdapter);
    }
}
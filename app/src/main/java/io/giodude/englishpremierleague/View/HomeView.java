package io.giodude.englishpremierleague.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import io.giodude.englishpremierleague.Adapter.StadiumAdapter;
import io.giodude.englishpremierleague.Adapter.StandingAdapter;
import io.giodude.englishpremierleague.Model.Table;
import io.giodude.englishpremierleague.Model.Team;
import io.giodude.englishpremierleague.Network.Connection;
import io.giodude.englishpremierleague.R;
import io.giodude.englishpremierleague.ViewModel.SoccerViewModel;
import io.giodude.englishpremierleague.databinding.ActivityHomeViewBinding;

@AndroidEntryPoint
public class HomeView extends Fragment {
    private ActivityHomeViewBinding binding;
    private StandingAdapter standingAdapter;
    private SoccerViewModel viewModel;
    private List<Table> tableModel;
    private List<Team> teamModel;
    private StadiumAdapter stadiumAdapter;
    private CarouselView carouselView;
    public ArrayList<Integer> carouselpics = new ArrayList<>();
    Connection connection;
    TextView played, draw, lose, win, rank, tname;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ActivityHomeViewBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        carouselView = view.findViewById(R.id.carouselView);
        Fresco.initialize(getActivity());
        showPics();
        connection = new Connection();
        if (connection.isConnected(getActivity())){
            viewModel = new ViewModelProvider(this).get(SoccerViewModel.class);
            viewModel.getTables();
            viewModel.getTeams();
            observeData();
            observeData1();
        }
    }

    public void showPics() {
        carouselpics.addAll(Arrays.asList(R.drawable.banner1, R.drawable.banner2, R.drawable.banner3));
        carouselView.setImageListener((position, imageView) -> {

            ArrayList<Integer> imageListTitle = new ArrayList<Integer>();
            for (int i = 0; i < carouselpics.size(); i++) {
                imageListTitle.add(carouselpics.get(i));
            }
            //imageView.setImageResource(imagelist[position]);
            Picasso.get().load(imageListTitle.get(position)).into(imageView);
        });
        carouselView.setPageCount(carouselpics.size());
    }

    private void observeData1() {

        viewModel.getTeamList().observe(getViewLifecycleOwner(), new Observer<List<Team>>() {
            @Override
            public void onChanged(List<Team> teams) {
                System.out.println("onchanged: " + teams.size());
                initRecyclerViewstaduim(teams);
                stadiumAdapter.updateList(teams);
            }
        });
    }

    private void observeData() {

        viewModel.getTableList().observe(getViewLifecycleOwner(), new Observer<List<Table>>() {
            @Override
            public void onChanged(List<Table> tables) {
                System.out.println("onchanged: " + tables.size());
                initRecyclerView(tables);
                standingAdapter.updateList(tables);
            }
        });
    }

    private void initRecyclerView(List<Table> events) {
        binding.recyclerview.setLayoutManager(new GridLayoutManager((getContext()), 2, RecyclerView.HORIZONTAL, false));
        standingAdapter = new StandingAdapter(getActivity(), events);
        binding.recyclerview.setAdapter(standingAdapter);
    }

    private void initRecyclerViewstaduim(List<Team> events) {
        binding.stagerecyclerview.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(binding.stagerecyclerview);
        stadiumAdapter = new StadiumAdapter(getActivity(), events);
        binding.stagerecyclerview.setAdapter(stadiumAdapter);
    }
//
//    @Override
//    public void onClick(Integer position) {
//        final Dialog myDialog;
//        myDialog = new Dialog(getActivity());
//        myDialog.setContentView(R.layout.standingclicks);
//        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        played = myDialog.findViewById(R.id.teamplayed);
//        draw = myDialog.findViewById(R.id.teamdraw);
//        lose = myDialog.findViewById(R.id.teamlose);
//        win = myDialog.findViewById(R.id.teamwin);
//        rank = myDialog.findViewById(R.id.teamrank);
//        tname = myDialog.findViewById(R.id.teamname);
//
//                played.setText(List<teamModel>.get(position).getIntPlayed());
//                draw.setText(data.get(position).getIntDraw());
//                lose.setText(data.get(position).getIntLoss());
//                win.setText(data.get(position).getIntWin());
//                rank.setText(data.get(position).getIntRank());
//                tname.setText(data.get(position).getStrTeam());
//
//    }
}
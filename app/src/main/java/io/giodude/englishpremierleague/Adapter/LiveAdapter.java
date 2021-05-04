package io.giodude.englishpremierleague.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.giodude.englishpremierleague.Model.Datum;
import io.giodude.englishpremierleague.Model.Event;
import io.giodude.englishpremierleague.R;
import io.giodude.englishpremierleague.databinding.LiveitemBinding;
import io.giodude.englishpremierleague.databinding.MatchesitemBinding;
import io.giodude.englishpremierleague.databinding.StandingitemBinding;

public class LiveAdapter extends RecyclerView.Adapter<LiveAdapter.ViewHolder> {
    Context context;
    private List<Datum> data;
    private LiveitemBinding binding;
    TextView title1,hname,aname,hscore,ascore,date,sport;
    public LiveAdapter(Context context, List<Datum> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = LiveitemBinding.inflate(inflater,parent,false);
        return  new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemBinding.home.setText(data.get(position).getHomeTeam().getName());
        holder.itemBinding.away.setText(data.get(position).getAwayTeam().getName());
        holder.itemBinding.eventdate.setText(data.get(position).getStartAt());

        final Dialog myDialog;
        myDialog = new Dialog(context);
        myDialog.setContentView(R.layout.livesclicks);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        title1 = myDialog.findViewById(R.id.eventtitles);
        hname = myDialog.findViewById(R.id.homes);
        aname = myDialog.findViewById(R.id.aways);
        hscore = myDialog.findViewById(R.id.homescoress);
        ascore = myDialog.findViewById(R.id.awayscores);
        date = myDialog.findViewById(R.id.eventdates);
        sport = myDialog.findViewById(R.id.eventsports);
        for (int i = 0; i<data.size(); i++) {
            if(holder.itemBinding.home.getText()==data.get(position).getHomeTeam().getName()){
                title1.setText(data.get(position).getName());
                hname.setText(data.get(position).getHomeTeam().getName());
                aname.setText(data.get(position).getAwayTeam().getName());
                hscore.setText(data.get(position).getHomeScore().getCurrent().toString());
                ascore.setText(data.get(position).getAwayScore().getCurrent().toString());
                date.setText(data.get(position).getStartAt());
                sport.setText(data.get(position).getSport().getName());
            }
        }

        holder.itemView.setOnClickListener(v -> myDialog.show());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LiveitemBinding itemBinding;

        public ViewHolder(LiveitemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
    }
    public void updateList(List<Datum> updateList){
        data = updateList;
        notifyDataSetChanged();
    }
}

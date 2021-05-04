package io.giodude.englishpremierleague.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import io.giodude.englishpremierleague.Model.Event;
import io.giodude.englishpremierleague.R;
import io.giodude.englishpremierleague.View.MatchesView;
import io.giodude.englishpremierleague.databinding.MatchesitemBinding;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.ViewHolder> {
    Context context;
    private List<Event> data;
    private MatchesitemBinding binding;
    TextView home, away, hs, as, date, venue;
    ImageView simg;

    public MatchesAdapter(Context context, List<Event> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MatchesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = MatchesitemBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchesAdapter.ViewHolder holder, int position) {
        holder.itemBinding.homeTeam.setText(data.get(position).getStrHomeTeam());
        holder.itemBinding.awayTeam.setText(data.get(position).getStrAwayTeam());
        if (data.get(position).getIntAwayScore() == null){
            holder.itemBinding.awayscore.setText("0");
        }else {
            holder.itemBinding.awayscore.setText(data.get(position).getIntAwayScore().toString());
        }
        if (data.get(position).getIntHomeScore() == null){
            holder.itemBinding.homescore.setText("0");
        }else {
            holder.itemBinding.homescore.setText(data.get(position).getIntHomeScore().toString());
        }


        final Dialog myDialog;
        myDialog = new Dialog(context);
        myDialog.setContentView(R.layout.matcherclicks);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        home = myDialog.findViewById(R.id.home);
        away = myDialog.findViewById(R.id.away);
        hs = myDialog.findViewById(R.id.homescore);
        as = myDialog.findViewById(R.id.awayscore);
        date = myDialog.findViewById(R.id.eventdate);
        venue = myDialog.findViewById(R.id.eventvenue);
        simg = myDialog.findViewById(R.id.image);
        for (int i = 0; i < data.size(); i++) {
            if (holder.itemBinding.homeTeam.getText() == data.get(position).getStrHomeTeam()) {
                home.setText(data.get(position).getStrHomeTeam());
                away.setText(data.get(position).getStrAwayTeam());
                hs.setText(data.get(position).getIntHomeScore());
                as.setText(data.get(position).getIntAwayScore());
                date.setText(data.get(position).getDateEvent());
                venue.setText(data.get(position).getStrVenue());
                Picasso.get().load(data.get(position).getStrThumb()).into(simg);
            }
        }
        holder.itemView.setOnClickListener(v -> {
            myDialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private MatchesitemBinding itemBinding;

        public ViewHolder(@NonNull MatchesitemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
    }

    public void updateList(List<Event> updateList) {
        data = updateList;
        notifyDataSetChanged();
    }
}

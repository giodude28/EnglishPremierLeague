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
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.giodude.englishpremierleague.Model.Table;
import io.giodude.englishpremierleague.R;
import io.giodude.englishpremierleague.View.StadingOnclick;
import io.giodude.englishpremierleague.databinding.StandingclicksBinding;
import io.giodude.englishpremierleague.databinding.StandingitemBinding;

public class StandingAdapter extends RecyclerView.Adapter<StandingAdapter.ViewHolder>{
    Context context;
    private List<Table> data;
    private StandingitemBinding binding;
    TextView played, draw, lose, win, rank, tname;

    public StandingAdapter(Context context, List<Table> data) {
        this.context = context;
        this.data = data;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = StandingitemBinding.inflate(inflater,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(data.get(position).getStrTeamBadge()).into(holder.itemBinding.imgid, new Callback() {
            @Override
            public void onSuccess() {
                holder.itemBinding.progressLoadPhoto.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError(Exception e) {
                holder.itemBinding.progressLoadPhoto.setVisibility(View.VISIBLE);
            }
        });
        holder.itemBinding.teamname.setText(data.get(position).getStrTeam());
        holder.itemBinding.teamrank.setText(data.get(position).getIntRank());

        final Dialog myDialog;
        myDialog = new Dialog(context);
        myDialog.setContentView(R.layout.standingclicks);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        played = myDialog.findViewById(R.id.teamplayed);
        draw = myDialog.findViewById(R.id.teamdraw);
        lose = myDialog.findViewById(R.id.teamlose);
        win = myDialog.findViewById(R.id.teamwin);
        rank = myDialog.findViewById(R.id.teamrank);
        tname = myDialog.findViewById(R.id.teamname);
        for (int i = 0; i < data.size(); i++) {
            if (holder.itemBinding.teamname.getText() == data.get(position).getStrTeam()) {
                played.setText(data.get(position).getIntPlayed());
                draw.setText(data.get(position).getIntDraw());
                lose.setText(data.get(position).getIntLoss());
                win.setText(data.get(position).getIntWin());
                rank.setText(data.get(position).getIntRank());
                tname.setText(data.get(position).getStrTeam());
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
        private StandingitemBinding itemBinding;
//        private StadingOnclick standingclicksBinding;, StadingOnclick standingclick
        public ViewHolder(StandingitemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
////            this.standingclicksBinding = standingclick;
//            itemBinding.getRoot().setOnClickListener(this::onClick);
        }

//        @Override
//        public void onClick(View v) {
//            onclick
//        }
    }

    public  void updateList(List<Table> updatedList){
        data = updatedList;
        notifyDataSetChanged();
    }
}

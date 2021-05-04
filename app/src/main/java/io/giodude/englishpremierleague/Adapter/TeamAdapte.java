package io.giodude.englishpremierleague.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import io.giodude.englishpremierleague.Model.Team;
import io.giodude.englishpremierleague.Model.TeamModel;
import io.giodude.englishpremierleague.R;
import io.giodude.englishpremierleague.View.TeamView;
import io.giodude.englishpremierleague.databinding.TeamitemBinding;

public class TeamAdapte extends RecyclerView.Adapter<TeamAdapte.ViewHolder> {
    Context context;
    private List<Team> mList;
    private TeamitemBinding binding;
    TextView name, taon, laro, tdesc;
    ImageView jimg, timg;

    public TeamAdapte(Context context, List<Team> data) {
        this.context = context;
        this.mList = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = TeamitemBinding.inflate(inflater,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemBinding.teamname.setText(mList.get(position).getStrTeam());
        Uri imageRequest = Uri.parse(mList.get(position).getStrTeamFanart3());
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(imageRequest)
                .setResizeOptions(new ResizeOptions(100, 100))
                .build();
        holder.itemBinding.img1.setController(
                Fresco.newDraweeControllerBuilder()
                        .setOldController(holder.itemBinding.img1.getController())
                        .setImageRequest(request)
                        .build());

        final Dialog myDialog;
        myDialog = new Dialog(context);
        myDialog.setContentView(R.layout.teamclicks);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        name = myDialog.findViewById(R.id.name);
        taon = myDialog.findViewById(R.id.year);
        laro = myDialog.findViewById(R.id.sport);
        tdesc = myDialog.findViewById(R.id.teamdesc);
        jimg = myDialog.findViewById(R.id.jerseyimg);
        timg = myDialog.findViewById(R.id.teamimg);
        for (int i = 0; i < mList.size(); i++) {
            if (holder.itemBinding.teamname.getText() == mList.get(position).getStrTeam()) {
                name.setText(mList.get(position).getStrTeam());
                taon.setText(mList.get(position).getIntFormedYear());
                laro.setText(mList.get(position).getStrSport());
                tdesc.setText(mList.get(position).getStrDescriptionEN());
                Picasso.get().load(mList.get(position).getStrTeamJersey()).into(jimg);
                Picasso.get().load(mList.get(position).getStrTeamFanart2()).into(timg);
            }
        }
        holder.itemView.setOnClickListener(v -> myDialog.show());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TeamitemBinding itemBinding;

        public ViewHolder(TeamitemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
    }
    public  void updateList(List<Team> updatedList){
        mList = updatedList;
        notifyDataSetChanged();
    }
}

package io.giodude.englishpremierleague.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.giodude.englishpremierleague.Model.Datum;
import io.giodude.englishpremierleague.Model.Team;
import io.giodude.englishpremierleague.R;
import io.giodude.englishpremierleague.databinding.MatchesitemBinding;
import io.giodude.englishpremierleague.databinding.StadiumitemBinding;
import io.giodude.englishpremierleague.databinding.StandingitemBinding;

public class StadiumAdapter extends RecyclerView.Adapter<StadiumAdapter.ViewHolder> {
    Context context;
    private List<Team> data;
    private StadiumitemBinding binding;
    TextView sname, sdesc;

    public StadiumAdapter(Context context, List<Team> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = StadiumitemBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Picasso.get().load(data.get(position).getStrStadiumThumb()).into(holder.itemBinding.imgid, new Callback() {
//            @Override
//            public void onSuccess() {
//                holder.itemBinding.progressLoadPhoto.setVisibility(View.INVISIBLE);
//            }
//
//            @Override
//            public void onError(Exception e) {
//                holder.itemBinding.progressLoadPhoto.setVisibility(View.VISIBLE);
//            }
//        });
        Uri imageRequest = Uri.parse(data.get(position).getStrStadiumThumb());
//        holder.itemBinding.img1d.setImageURI(imageRequest);

        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(imageRequest)
                .setResizeOptions(new ResizeOptions(50, 50))
                .build();
        holder.itemBinding.img1d.setController(
                Fresco.newDraweeControllerBuilder()
                        .setOldController(holder.itemBinding.img1d.getController())
                        .setImageRequest(request)
                        .build());

        holder.itemBinding.teamname.setText(data.get(position).getStrStadium());

        final Dialog myDialog;
        myDialog = new Dialog(context);
        myDialog.setContentView(R.layout.stadiumclicks);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        sname = myDialog.findViewById(R.id.stadiumname);
        sdesc = myDialog.findViewById(R.id.stadiumdesc);
        for (int i = 0; i < data.size(); i++) {
            if (holder.itemBinding.teamname.getText() == data.get(position).getStrStadium()) {
                sname.setText(data.get(position).getStrStadium());
                sdesc.setText(data.get(position).getStrStadiumDescription());
            }
        }
        holder.itemView.setOnClickListener(v -> myDialog.show());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private StadiumitemBinding itemBinding;

        public ViewHolder(@NonNull StadiumitemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
    }

    public void updateList(List<Team> updateList) {
        data = updateList;
        notifyDataSetChanged();
    }
}

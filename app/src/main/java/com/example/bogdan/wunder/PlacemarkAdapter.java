package com.example.bogdan.wunder;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlacemarkAdapter extends RecyclerView.Adapter<PlacemarkAdapter.ViewHolder> {

    private List<Placemark> placemarkList;
    private Context context;

    public PlacemarkAdapter(List<Placemark> placemarkList, Context context) {
        this.placemarkList = placemarkList;
        this.context = context;
    }

    @NonNull
    @Override
    public PlacemarkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.rv_part, viewGroup, false);
        return new PlacemarkAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlacemarkAdapter.ViewHolder viewHolder, int i) {

        final Placemark placemark = placemarkList.get(i);

        viewHolder.tvName.setText(placemark.getName());
        viewHolder.tvAddress.setText(placemark.getAddress());
        viewHolder.tvVin.setText(placemark.getVin());
        viewHolder.tvExterior.setText(String.valueOf(placemark.getLongitude()));
        viewHolder.tvInterior.setText(placemark.getInterior());
        viewHolder.tvEngineType.setText(placemark.getEngineType());
        viewHolder.tvFuel.setText(String.valueOf(placemark.getFuel()));

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,MapActivity.class);
                intent.putExtra("vin",placemark.getVin());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return placemarkList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvAddress) TextView tvAddress;
        @BindView(R.id.tvName) TextView tvName;
        @BindView(R.id.tvVin) TextView tvVin;
        @BindView(R.id.tvExterior) TextView tvExterior;
        @BindView(R.id.tvInterior) TextView tvInterior;
        @BindView(R.id.tvEngineType) TextView tvEngineType;
        @BindView(R.id.tvFuel) TextView tvFuel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }
}

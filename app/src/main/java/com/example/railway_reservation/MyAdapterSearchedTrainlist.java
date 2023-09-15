package com.example.railway_reservation;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterSearchedTrainlist extends  RecyclerView.Adapter<MyAdapterSearchedTrainlist.MyViewHolder>{
    private Context context;
    private ArrayList train_noad,train_namead,train_fromad,train_toad,time_durationad,arriving_timead,destination_timead;

    public MyAdapterSearchedTrainlist(Context context, ArrayList train_noad, ArrayList train_namead,ArrayList train_fromad,ArrayList train_toad,ArrayList time_durationad,ArrayList arriving_timead,ArrayList destination_timead) {
        this.context = context;
        this.train_noad = train_noad;
        this.train_namead = train_namead;
        this.train_fromad = train_fromad;
        this.train_toad = train_toad;
        this.time_durationad = time_durationad;
        this.arriving_timead = arriving_timead;
        this.destination_timead = destination_timead;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.trainentrys,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.train_noad.setText(String.valueOf(train_noad.get(position)));
        holder.train_namead.setText(String.valueOf(train_namead.get(position)));
        holder.train_fromad.setText(String.valueOf(train_fromad.get(position)));
        holder.train_toad.setText(String.valueOf(train_toad.get(position)));
        holder.time_durationad.setText(String.valueOf(time_durationad.get(position)));
        holder.arriving_timead.setText(String.valueOf(arriving_timead.get(position)));
        holder.destination_timead.setText(String.valueOf(destination_timead.get(position)));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,PassengerAdding.class);
                intent.putExtra("tr_no",holder.train_noad.getText().toString());
                intent.putExtra("tr_name",holder.train_namead.getText().toString());
                intent.putExtra("tr_from",holder.train_fromad.getText().toString());
                intent.putExtra("tr_to",holder.train_toad.getText().toString());
                intent.putExtra("tr_durationtime",holder.time_durationad.getText().toString());
                intent.putExtra("tr_arrivingtime",holder.arriving_timead.getText().toString());
                intent.putExtra("tr_destinationtime",holder.destination_timead.getText().toString());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return train_noad.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView train_noad,train_namead,train_fromad,train_toad,time_durationad,arriving_timead,destination_timead;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            train_noad = itemView.findViewById(R.id.txtTrainNo);
            train_namead = itemView.findViewById(R.id.txtTrainName);
            train_fromad = itemView.findViewById(R.id.txtTrainfrom);
            train_toad = itemView.findViewById(R.id.txtTrainTo);
            time_durationad = itemView.findViewById(R.id.txtTraindurationtime);
            arriving_timead = itemView.findViewById(R.id.txtTrainArrivingtime);
            destination_timead = itemView.findViewById(R.id.txtTraindestitime);

        }
    }
}

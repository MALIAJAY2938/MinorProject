package com.example.railway_reservation;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterfordeletebooking extends RecyclerView.Adapter<MyAdapterfordeletebooking.MyViewHolder> {
    private Context context;
    private ArrayList tr_no,date,pnr;

    public MyAdapterfordeletebooking(Context context, ArrayList tr_no, ArrayList date, ArrayList pnr) {
        this.context = context;
        this.tr_no = tr_no;
        this.date = date;
        this.pnr = pnr;
    }

    @NonNull
    @Override
    public MyAdapterfordeletebooking.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.customlistviewbookedtickets,parent,false);
        return new MyAdapterfordeletebooking.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterfordeletebooking.MyViewHolder holder, int position) {
        holder.pnr.setText(String.valueOf(pnr.get(position)));
        holder.tr_no.setText(String.valueOf(tr_no.get(position)));
        holder.date.setText(String.valueOf(date.get(position)));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,deletebooking_screen.class);
                intent.putExtra("pnr",holder.pnr.getText().toString());
                intent.putExtra("date",holder.date.getText().toString());
                intent.putExtra("tr_no",holder.tr_no.getText().toString());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pnr.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView pnr,tr_no,date;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pnr = itemView.findViewById(R.id.bookedticket_pnrno);
            tr_no = itemView.findViewById(R.id.bookedticket_trainno);
            date = itemView.findViewById(R.id.bookedticket_date);
        }
    }
}

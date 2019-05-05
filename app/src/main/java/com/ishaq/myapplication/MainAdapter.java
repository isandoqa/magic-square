package com.ishaq.myapplication;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ishaq.myapplication.databinding.MainRecyclerItemBinding;
import com.ishaq.myapplication.databinding.NumberItemBinding;


public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private int order;
    private int[][]square;
    private Context mContext;

    MainAdapter(Context context,int order,int [][]square){
        this.order=order;
        this.square=square;
        mContext=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.main_recycler_item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        //int[]row=square[i];
        int[]row=getColumn(i);
        NumberAdapter adapter=new NumberAdapter(row);
        LinearLayoutManager manager=new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        viewHolder.mainRecyclerItemBinding.rowRecycler.setLayoutManager(manager);
        viewHolder.mainRecyclerItemBinding.rowRecycler.setAdapter(adapter);
    }

    private int[]getColumn(int index){
        int []result=new int[order];
        for(int i=0;i<order;i++){
            result[i]=square[i][index];
        }
        return result;
    }

    @Override
    public int getItemCount() {
        return order;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        MainRecyclerItemBinding mainRecyclerItemBinding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mainRecyclerItemBinding=MainRecyclerItemBinding.bind(itemView);
        }
    }


    class NumberAdapter extends RecyclerView.Adapter<NumberAdapter.ViewHolder>{
        private int[]row;
        NumberAdapter(int []row){
            this.row=row;
        }
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.number_item,viewGroup,false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            viewHolder.numberItemBinding.numLabel.setText(String.valueOf(row[i]));
        }

        @Override
        public int getItemCount() {
            return order;
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            NumberItemBinding numberItemBinding;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                numberItemBinding=NumberItemBinding.bind(itemView);
            }
        }
    }
}

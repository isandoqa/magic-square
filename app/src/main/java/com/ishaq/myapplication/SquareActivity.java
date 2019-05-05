package com.ishaq.myapplication;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.ishaq.myapplication.databinding.ActivitySquareBinding;

public class SquareActivity extends AppCompatActivity {
    private ActivitySquareBinding binding;
    private int order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_square);
        order=getIntent().getIntExtra("order",1);
        int [][]square=constructSquare();

        MainAdapter adapter=new MainAdapter(this,order,square);
        LinearLayoutManager manager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        binding.recycler.setLayoutManager(manager);
        binding.recycler.setAdapter(adapter);

    }

    private int[][] constructSquare() {
        int [][]result=new int[order][order];
        int i=order-1;
        int j=order/2;
        result[i][j]=1;
        int v=2;
        while(v<=order*order){
            int tempI=i+1;
            int tempJ=j+1;
            if(tempI==order){
                tempI=0;
            }
            if(tempJ==order){
                tempJ=0;
            }

            if(result[tempI][tempJ]!=0){
                tempJ=j;
                tempI=i-1;
                if(tempI==-1){
                    tempI=order-1;
                }
            }
            while (result[tempI][tempJ]!=0){
                tempI--;
                if(tempI==-1){
                    tempI=order-1;
                }
            }
            i=tempI;
            j=tempJ;
            result[i][j]=v++;
        }
        return result;
    }

    private void printArr(int [][]arr){
        for (int i=0;i<order;i++){
            for (int j=0;j<order;j++){
                System.out.print(arr[i][j]);
                System.out.print("   ");
            }
            System.out.println();
        }
    }
}

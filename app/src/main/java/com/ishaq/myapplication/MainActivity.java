package com.ishaq.myapplication;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ishaq.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.submitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(validate()){
            binding.orderEditText.setError(null);
            Intent i=new Intent(this,SquareActivity.class);
            i.putExtra("order",Integer.parseInt(binding.orderEditText.getText().toString()));
            startActivity(i);
        }
    }

    private boolean validate(){
        String input=binding.orderEditText.getText().toString();
        boolean result=true;
        if(input.isEmpty()){
            binding.orderEditText.setError("Please enter the order");
            result=false;
            return result;
        }
        int order=0;
        try{
            order=Integer.parseInt(input);
        }catch (NumberFormatException e){
            binding.orderEditText.setError("Invalid number");
            result=false;
            return result;
        }
        if(order<0){
            result=false;
            binding.orderEditText.setError("Please enter positive order");
        }else if(order%2==0){
            result=false;
            binding.orderEditText.setError("Order must be odd");
        }
        return result;
    }
}

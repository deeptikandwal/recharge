package com.example.interview;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import com.example.interview.operators.AllOperator;
import com.example.interview.operators.Operators;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView toolbar_title;
    ImageView img;
    TextInputEditText amount, mobile_number;
    AppCompatSpinner spinner;
    Button btn;
    List<String> operatorslist = new ArrayList<>();
    List<String> idlist = new ArrayList<>();
    private String onetimetoken;
    ProgressBar progress_circular;
    private String selected_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.ed_amount);
        toolbar_title = findViewById(R.id.toolbar_title);
        mobile_number = findViewById(R.id.ed_mobile);
        spinner = findViewById(R.id.spinner);
        btn = findViewById(R.id.submit);
        progress_circular = findViewById(R.id.progress_circular);
        img = findViewById(R.id.back_arrow);
        toolbar_title.setText("Recharge");

        img.setOnClickListener((v)->{
            onBackPressed();
        });
        fetchOperators();

        btn.setOnClickListener((v) -> {

            if (mobile_number.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter valid mobile number", Toast.LENGTH_SHORT).show();
            } else if (amount.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter valid amount", Toast.LENGTH_SHORT).show();
            } else {
                progress_circular.setVisibility(View.VISIBLE);
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                submitDetails(mobile_number.getText().toString(), amount.getText().toString(), selected_id);
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (idlist != null) {
                    selected_id = idlist.get(position);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void fetchOperators() {

        ApicallInterface apicallInterface = ApiClient.getRetrofitCLient().create(ApicallInterface.class);

        Map<String, String> map = new HashMap<>();
        try {
            map.put("token", "e22617f3bfc04a6afb962c7a3e78d233");
            map.put("loginsession", "620bb6878381697ca834ed4dd4894dac");
        } catch (Exception e) {
            e.printStackTrace();
        }

//        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json"),jsonObject.toString());
        apicallInterface.fetchOperators(map)
                .enqueue(new Callback<Operators>() {
                    @Override
                    public void onResponse(Call<Operators> call, Response<Operators> response) {
                        Log.d("response ", response.body().toString());
                        onetimetoken = response.body().getRechageonetimetoken();
                        Operators operators = (Operators) response.body();
                        for (AllOperator o : operators.getAllOperator()) {
                            operatorslist.addAll(Collections.singletonList(o.getOperator2()));
                            idlist.addAll(Collections.singletonList(o.getId()));
                        }

                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, R.layout.spinner, R.id.tv_spinner, operatorslist);
                        spinner.setAdapter(arrayAdapter);

                    }

                    @Override
                    public void onFailure(Call<Operators> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void submitDetails(String mobile, String amount, String operator) {

        ApicallInterface apicallInterface = ApiClient.getRetrofitCLient().create(ApicallInterface.class);

        Map<String, String> map = new HashMap<>();
        try {
            map.put("token", "e22617f3bfc04a6afb962c7a3e78d233");
            map.put("loginsession", "620bb6878381697ca834ed4dd4894dac");
            map.put("loginsession", "620bb6878381697ca834ed4dd4894dac");
            map.put("rechageonetimetoken", onetimetoken);
            map.put("operator", operator);
            map.put("mobile", mobile);
            map.put("amount", amount);
        } catch (Exception e) {
            e.printStackTrace();
        }

        apicallInterface.submitTransaction(map)
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        Transaction transaction = new Transaction(response.body().get("amount").getAsString(),
                                response.body().get("mobile").getAsString(),
                                response.body().get("txnid").getAsString(),
                                response.body().get("status").getAsString(), response.body().get("message").getAsString());

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                progress_circular.setVisibility(View.GONE);
                                Intent intent = new Intent(MainActivity.this, TransactionActivity.class)
                                        .putExtra("transaction", transaction);
                                startActivity(intent);
                            }
                        }, 5000);

                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
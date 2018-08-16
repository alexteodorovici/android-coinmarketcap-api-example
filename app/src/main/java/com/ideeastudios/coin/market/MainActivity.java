package com.ideeastudios.coin.market;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ideeastudios.coin.market.retrofit.CryptoList;
import com.ideeastudios.coin.market.retrofit.Datum;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    CryptoListAdapter adapter;
    APIInterface apiInterface;
    private RecyclerView recyclerView;
    private List<Datum> cryptoList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        initRecyclerView();
        getCoinList();
    }


    private void initRecyclerView() {
        // Lookup the recyclerview in activity layout
        recyclerView = findViewById(R.id.my_recycler_view);

        // Initialize data
        cryptoList = new ArrayList<>();

        // Create adapter passing in the sample user data
        adapter = new CryptoListAdapter(cryptoList);

        // Attach the adapter to the recyclerview to populate items
        recyclerView.setAdapter(adapter);

        // Set layout manager to position the items
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setClickListener(new CryptoListAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Toast.makeText(MainActivity.this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, CoinPage.class);
                intent.putExtra("coin", adapter.getItem(position));
                startActivity(intent);
            }
        });
    }


    private void getCoinList() {
        Call<CryptoList> call2 = apiInterface.doGetUserList("100");
        call2.enqueue(new Callback<CryptoList>() {
            @Override
            public void onResponse(Call<CryptoList> call, Response<CryptoList> response) {
                CryptoList list = response.body();

                // do not reinitialize an existing reference used by an adapter
                // add to the existing list
                cryptoList.clear();
                cryptoList.addAll(list.getData());

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<CryptoList> call, Throwable t) {
                Toast.makeText(MainActivity.this, "onFailure", Toast.LENGTH_SHORT).show();
                Log.d("XXXX", t.getLocalizedMessage());
                call.cancel();
            }
        });
    }

}

package com.ideeastudios.coin.market;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.ideeastudios.coin.market.retrofit.Datum;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class CoinPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_page);

        Intent intent = getIntent();
        Datum datum = (Datum) intent.getSerializableExtra("coin");

        TextView name = findViewById(R.id.name);
        TextView price = findViewById(R.id.price);
        TextView date = findViewById(R.id.date);

        TextView symbol = findViewById(R.id.symbol);
        TextView slug = findViewById(R.id.slug);
        TextView volume24h = findViewById(R.id.volume24h);
        TextView circulating_supply = findViewById(R.id.circulating_supply);
        TextView max_supply = findViewById(R.id.max_supply);
        TextView market_cap = findViewById(R.id.market_cap);

        TextView change1h = findViewById(R.id.change1h);
        TextView change24h = findViewById(R.id.change24h);
        TextView change7d = findViewById(R.id.change7d);

        name.setText(datum.getName() + " (" + datum.getSymbol() + ")");
        price.setText("Price: $" + String.format("%,f", datum.getQuote().getUSD().getPrice()));
        date.setText("Last Updated: " + parseDateToddMMyyyy(datum.getLastUpdated()));

        symbol.setText("Symbol: " + datum.getSymbol());
        slug.setText("Slug: " + datum.getSlug());
        volume24h.setText("Volume/24h: $" + String.format("%,d", Math.round(datum.getQuote().getUSD().getVolume24h())));

        circulating_supply.setText("Circulating Supply: " + String.format("%.0f", datum.getCirculatingSupply()) + " " + datum.getSymbol());
        max_supply.setText("Max Supply: " + String.format("%.0f", datum.getMaxSupply()) + " " + datum.getSymbol());

        market_cap.setText("Market Cap: $" + String.format("%,d", Math.round(datum.getQuote().getUSD().getMarketCap())));

        change1h.setText(String.format("Change 1h: %.2f", datum.getQuote().getUSD().getPercentChange1h()) + "%");
        change24h.setText(String.format("Change 24h: %.2f", datum.getQuote().getUSD().getPercentChange24h()) + "%");
        change7d.setText(String.format("Change 7d: %.2f", datum.getQuote().getUSD().getPercentChange7d()) + "%");
    }


    private String parseDateToddMMyyyy(String time) {
        //parse the server timestamp. Make sure it is in UTC timezone as per API specifications.
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        //format the utc server timestamp to local timezone.
        SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        output.setTimeZone(TimeZone.getDefault());

        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return output.format(date);
    }

}

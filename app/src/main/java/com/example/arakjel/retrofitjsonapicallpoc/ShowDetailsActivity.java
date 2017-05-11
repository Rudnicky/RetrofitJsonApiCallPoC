package com.example.arakjel.retrofitjsonapicallpoc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowDetailsActivity extends AppCompatActivity {

    @BindView(R.id.movieIdTextView) TextView movieIdTextView;
    @BindView(R.id.movieNameTextView) TextView movieNameTextView;
    @BindView(R.id.moviePriceTextView) TextView moviePriceTextView;
    @BindView(R.id.movieInStockTextView) TextView movieInStockTextView;
    @BindView(R.id.button) Button goBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        ButterKnife.bind(this);
        setData();
    }

    @OnClick(R.id.button)
    public void goBackButtonClicked() {
        finish();
    }

    private void setData() {
        Intent intent = getIntent();
        movieIdTextView.setText(String.valueOf(intent.getIntExtra(MainActivity.KEY_MOVIE_ID, 0)));
        movieNameTextView.setText(intent.getStringExtra(MainActivity.KEY_MOVIE_NAME));
        moviePriceTextView.setText(intent.getStringExtra(MainActivity.KEY_MOVIE_PRICE));
        movieInStockTextView.setText(String.valueOf(intent.getIntExtra(MainActivity.KEY_MOVIE_STOCK, 0)));
    }
}

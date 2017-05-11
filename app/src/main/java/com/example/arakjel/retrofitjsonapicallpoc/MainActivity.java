package com.example.arakjel.retrofitjsonapicallpoc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener {


    public static final String ROOT_URL = "http://www.delaroystudios.com/";

    // Strings to bind with intent will be used to send data to other activity
    public static final String KEY_MOVIE_ID = "key_movie_id";
    public static final String KEY_MOVIE_NAME = "key_movie_name";
    public static final String KEY_MOVIE_PRICE = "key_movie_price";
    public static final String KEY_MOVIE_STOCK = "key_movie_stock";

    private ListView listView;
    private List<Movie> movies;

    @BindView(R.id.exitApplicationButton) Button exitApplicationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        listView = (ListView) findViewById(R.id.listView);
        getMovies();
        listView.setOnItemClickListener(this);
    }

    private void getMovies() {
        final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please wait...", false, false);

        // Creating a rest adapter
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .build();

        MoviesAPI api =  adapter.create(MoviesAPI.class);
        api.getMovies(new Callback<List<Movie>>() {
            @Override
            public void success(List<Movie> list, Response response){
                loading.dismiss();
                movies = list;
                showList();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private void showList() {
        String[] items = new String[movies.size()];
        for (int i=0; i<movies.size(); i++) {
            items[i] = movies.get(i).getName();
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.simple_list, items);
        listView.setAdapter(adapter);
    }

    @OnClick(R.id.exitApplicationButton)
    public void exitApplicationButtonClicked() {
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ShowDetailsActivity.class);
        Movie movie = movies.get(position);

        intent.putExtra(KEY_MOVIE_ID, movie.getMovieId());
        intent.putExtra(KEY_MOVIE_NAME, movie.getName());
        intent.putExtra(KEY_MOVIE_PRICE, movie.getPrice());
        intent.putExtra(KEY_MOVIE_STOCK, movie.getInStock());

        startActivity(intent);
    }
}

package com.example.movie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Movie{
    String name, year, country, director, genre;
}

class M {
    ArrayList<Movie> movies;
}

public class MainActivity extends AppCompatActivity {

    ArrayList<Movie> randomList = new ArrayList<>();
    int i = 0;
    TextView name;
    TextView year;
    TextView country;
    TextView director;
    TextView genre;
    TextView msg;

    public ArrayList<Movie> loadMovies() throws IOException {
        InputStream stream =  getAssets().open("movies.json");
        InputStreamReader reader = new InputStreamReader(stream);
        Gson gson = new Gson();
        M m = gson.fromJson(reader, M.class);
        return m.movies;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            getStarted();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getStarted() throws IOException {
        randomList.clear();
        ArrayList<Movie> movies = new ArrayList<>(loadMovies());
        int movieN = movies.size();

        ClearContent();
        msg = findViewById(R.id.message);
        msg.setText("Сгенерируй случайный фильм");

        ArrayList<Integer> indexes = new ArrayList<Integer>();
        for (int j = 0; j<movieN; j++)
            indexes.add(j);

        int index = 0;

        for(int m = 0; m<movies.size(); m++){
            index = (int)(Math.random()*movieN);
            randomList.add(movies.get(indexes.get(index)));
            indexes.remove(index);
            movieN--;
        }

    }

    public void ClearContent (){
        name = findViewById(R.id.movieName);
        year = findViewById(R.id.year);
        country = findViewById(R.id.country);
        director = findViewById(R.id.director);
        genre = findViewById(R.id.genre);
        name.setText("");
        year.setText("");
        country.setText("");
        director.setText("");
        genre.setText("");
    }

    public void onClick(View v) throws IOException {
        Button but = (Button) v;
        name = findViewById(R.id.movieName);
        year = findViewById(R.id.year);
        country = findViewById(R.id.country);
        director = findViewById(R.id.director);
        genre = findViewById(R.id.genre);
        if (i<randomList.size()){
            name.setText(randomList.get(i).name);
            year.setText(randomList.get(i).year);
            country.setText(randomList.get(i).country);
            director.setText(randomList.get(i).director);
            genre.setText(randomList.get(i).genre);
            i++;
            msg = findViewById(R.id.message);
            msg.setText("Сгенерируй случайный фильм");
        }
        else {
            ClearContent();
            msg = findViewById(R.id.message);
            msg.setText("Вы посмотрели все фильмы");
        }

    }

    public void onClickNew (View v) throws IOException {
        Button but = (Button) v;
        ClearContent();
        getStarted();
        i = 0;
        msg = findViewById(R.id.message);
        msg.setText("Начнем сначала");
    }
}


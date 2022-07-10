package com.example.hiltproject.rxJavaLearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.ProgressBar;

import com.example.hiltproject.R;
import com.example.hiltproject.WeatherModel;
import com.example.hiltproject.network.RetrofitService;
import com.example.hiltproject.repo.MainRepo;
import com.example.hiltproject.roomDatabase.WeatherDao;
import com.example.hiltproject.roomDatabase.WeatherDatabase;
import com.example.hiltproject.ui.WeatherAdapter;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlinx.coroutines.GlobalScope;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity implements WeatherAdapter.ClickListener {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    WeatherAdapter weatherAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        weatherAdapter = new WeatherAdapter(this);

        recyclerView.setAdapter(weatherAdapter);


        compositeDisposable.add(ApiClient
                .getInstance()
                .getWeatherList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(detail -> {
                    if (detail != null) {
                        weatherAdapter.updateList(detail.getDataseries());}

                }, throwable -> {

                    throwable.printStackTrace();
                    Log.d("throwable_m" , throwable.getLocalizedMessage());
                }));
    }

    @Override
    public void onItemClickListener(int position) {

    }
}
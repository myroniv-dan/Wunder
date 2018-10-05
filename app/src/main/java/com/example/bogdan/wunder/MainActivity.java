package com.example.bogdan.wunder;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

interface PersistPlacemarksCompletion {
    void operation(List<Placemark> placemarks);
}

public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout refreshLayout;
    private PlacemarkAdapter placemarkAdapter;
    private RecyclerView recyclerView;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();
        setupRealm();

        RealmResults<Placemark> realmResults = realm.where(Placemark.class).findAll();
        if(realmResults.isEmpty()){
            refreshLayout.setRefreshing(true);
            Log.v("DB is empty: ", "Let's make query and fill it up !");
            fetchAndPersistPlacemarks(this::fillPlacemarkList);

        } else {
            Log.v("There's data in DB: ", "Setting data from Realm DB");
            fillPlacemarkList(realmResults);
        }

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchAndPersistPlacemarks(placemarks -> fillPlacemarkList(placemarks));
            }
        });

    }

    public void setupViews(){

        refreshLayout = findViewById(R.id.refreshlayout);
        recyclerView = findViewById(R.id.recyclerview);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }

    public void setupRealm(){

        Realm.init(getApplicationContext());
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);

        realm = Realm.getInstance(config);
    }

    public void fetchAndPersistPlacemarks(final PersistPlacemarksCompletion completion){

        PlacemarksList.queryPlacemarks((placemarks, error) -> {
            if(error != null){
                Toast.makeText(MainActivity.this, "Something went wrong...", Toast.LENGTH_SHORT).show();

            } else {
                realm.beginTransaction();
                List<Placemark> persistedPlacemarks = new ArrayList<>();
                for(PlacemarkJSON placemarkJSON: placemarks){
                    Placemark placemark = new Placemark(placemarkJSON);
                    realm.copyToRealmOrUpdate(placemark);
                    persistedPlacemarks.add(placemark);
                }

                realm.commitTransaction();
                completion.operation(persistedPlacemarks);
            }
        });
    }

    public void fillPlacemarkList(List<Placemark> placemarkList){
        placemarkAdapter = new PlacemarkAdapter(placemarkList,this);
        recyclerView.setAdapter(placemarkAdapter);
        refreshLayout.setRefreshing(false);
        Toast.makeText(MainActivity.this, "Loaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}

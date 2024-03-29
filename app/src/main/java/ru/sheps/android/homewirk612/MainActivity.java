package ru.sheps.android.homewirk612;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.sheps.android.homework612.R;


public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private static final String LARGE_TEXT = "large_text";
    List<Map<String, String>> values = new ArrayList<>();
    public static final String APP_PREFERENCES = "mysettings";
    SharedPreferences mySharedPreferences;
    BaseAdapter listContentAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list = findViewById(R.id.list);

        mySharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        boolean hasVisited = mySharedPreferences.getBoolean("hasVisited", false);

        if (!hasVisited) {
            // выводим нужную активность
            SharedPreferences.Editor editor = mySharedPreferences.edit();
            editor.putBoolean("hasVisited", true);
            editor.putString(LARGE_TEXT, getString(R.string.large_text));
            editor.apply();
        }

        values = prepareContent();

        String[] from = {"Header", "Subheader"};
        int[] to = {R.id.header, R.id.subheader};
        listContentAdapter = createAdapter(values, from, to);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                values.remove(position);
                listContentAdapter.notifyDataSetChanged();
            }
        });

        list.setAdapter(listContentAdapter);

        mSwipeRefreshLayout = findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    private BaseAdapter createAdapter(List<Map<String, String>> values, String[] from, int[] to) {
        return new SimpleAdapter(this, values, R.layout.my_simple_list_item, from, to);
    }

    private List<Map<String, String>> prepareContent() {

        String[] strings = {""};
        if (mySharedPreferences.contains(LARGE_TEXT)) {
            strings = mySharedPreferences.getString(LARGE_TEXT, "").split("\n\n");
        }

        List<Map<String, String>> list = new ArrayList<>();

        for (int i = 0; i < strings.length; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("Header", strings[i]);
            map.put("Subheader", String.valueOf(strings[i].length()));
            list.add(map);
        }

        return list;
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(false);
        values.clear();
        values.addAll(prepareContent());
        listContentAdapter.notifyDataSetChanged();
    }


}
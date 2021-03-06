package com.mncomunity1.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.mncomunity1.R;
import com.mncomunity1.adapter.ListRecyclerAdapter;
import com.mncomunity1.event.ActivityResultBus;
import com.mncomunity1.event.ApiBus;
import com.mncomunity1.model.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListLayer1Activity extends AppCompatActivity {
    ProgressBar progressBar3;
    private Toolbar toolbar;
    ListRecyclerAdapter myCourseRecyclerAdapter;

    ArrayList<Post.PostEntity> list = new ArrayList<>();
    RecyclerView recList;
    AQuery aq;
    String url;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_list, menu);
        return true;
    }

    String cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        progressBar3 = (ProgressBar) findViewById(R.id.progressBar3);
        progressBar3.setVisibility(View.VISIBLE);
        cat = getIntent().getStringExtra("cat");
        aq = new AQuery(this);

        url = getIntent().getStringExtra("link");
        Log.e("ddd", url);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Articles");
            toolbar.setTitleTextColor(Color.BLACK);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);

            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

        }
        recList = (RecyclerView) findViewById(R.id.cardList_main);

        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        aq.ajax(url, JSONObject.class, this, "jsonCallback");

        myCourseRecyclerAdapter = new ListRecyclerAdapter(getApplicationContext(), list);
        recList.setAdapter(myCourseRecyclerAdapter);
        myCourseRecyclerAdapter.SetOnItemVideiosClickListener(new ListRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String link = list.get(position).getLink();
                String title = list.get(position).getTitle();
                Log.e("qqqq", link);
                Intent i = new Intent(getApplicationContext(), ListWebViewActivity.class);
                i.putExtra("link", link);
                i.putExtra("title", title);
                startActivity(i);
            }
        });
    }

    public void jsonCallback(String url, JSONObject json, AjaxStatus status) {
        //When JSON is not null
        if (json != null) {
            JSONArray ja = null;
            try {
                ja = json.getJSONArray("post");
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject job = ja.getJSONObject(i);
                    Log.e("job", job + "");

                    Post.PostEntity item = new Post.PostEntity();
                    item.setLink(job.optString("link"));
                    item.setTitle(job.optString("title"));
                    list.add(item);
                }
                myCourseRecyclerAdapter.notifyDataSetChanged();
                progressBar3.setVisibility(View.GONE);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        ActivityResultBus.getInstance().register(this);
        ApiBus.getInstance().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ActivityResultBus.getInstance().unregister(this);
        ApiBus.getInstance().unregister(this);
    }


    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}

package com.samimhossain.jobscircular;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    MyAdapter myAdapter;
    List<Myitem> listItems;

    Button myHomeBtn;
    Button govtJobBtn;
    Button pvtJobBtn;
    Button noticeBtn;
    Button educationBtn;
    Button bankJobBtn;
    Button diffenceBtn;


    TextView urlstr;

    String searchCatTxt = "Govt Jobs";

    //banner ads ==========================================
    AdView adView;

    SpinKitView spin_kit;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spin_kit = findViewById(R.id.spin_kit);




        FirebaseMessaging.getInstance().subscribeToTopic("weather")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Success";
                        if (!task.isSuccessful()) {
                            msg = "Failed";
                        }
                    }
                });


        final SwipeRefreshLayout pullToRefresh = findViewById(R.id.refresId);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                listItems.clear();
                loadurl();
                myAdapter.notifyDataSetChanged();
                myHomeBtn.setBackgroundResource(R.color.primary);
                govtJobBtn.setBackgroundResource(R.color.primary);
                pvtJobBtn.setBackgroundResource(R.color.primary);
                noticeBtn.setBackgroundResource(R.color.primary);
                educationBtn.setBackgroundResource(R.color.primary);
                bankJobBtn.setBackgroundResource(R.color.primary);
                diffenceBtn.setBackgroundResource(R.color.primary);
                pullToRefresh.setRefreshing(false);
            }
        });

        //ads ==========================

        adView = findViewById(R.id.adView);


        urlstr = (TextView) findViewById(R.id.urlStrId);

        myHomeBtn = (Button) findViewById(R.id.homeBtn);
        govtJobBtn = (Button) findViewById(R.id.catGovtJob);
        pvtJobBtn = (Button) findViewById(R.id.catPvtJob);
        noticeBtn = (Button) findViewById(R.id.catNotice);
        educationBtn = (Button) findViewById(R.id.catEducation);
        bankJobBtn = (Button) findViewById(R.id.catBankJob);
        diffenceBtn = (Button) findViewById(R.id.catDiffence);

        recyclerView = (RecyclerView) findViewById(R.id.mainRecycleViewId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems = new ArrayList<>();

        loadurl();


        //ads ===================================================


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


        myHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myHomeBtn.setBackgroundResource(R.color.black);
                govtJobBtn.setBackgroundResource(R.color.primary);
                pvtJobBtn.setBackgroundResource(R.color.primary);
                noticeBtn.setBackgroundResource(R.color.primary);
                educationBtn.setBackgroundResource(R.color.primary);
                bankJobBtn.setBackgroundResource(R.color.primary);
                diffenceBtn.setBackgroundResource(R.color.primary);


                searchCatTxt = myHomeBtn.getText().toString();
                try {
                    myAdapter.getFilter().filter("all");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        govtJobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myHomeBtn.setBackgroundResource(R.color.primary);
                govtJobBtn.setBackgroundResource(R.color.black);
                pvtJobBtn.setBackgroundResource(R.color.primary);
                noticeBtn.setBackgroundResource(R.color.primary);
                educationBtn.setBackgroundResource(R.color.primary);
                bankJobBtn.setBackgroundResource(R.color.primary);
                diffenceBtn.setBackgroundResource(R.color.primary);

                searchCatTxt = govtJobBtn.getText().toString();
                try {
                    myAdapter.getFilter().filter(searchCatTxt);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        pvtJobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myHomeBtn.setBackgroundResource(R.color.primary);
                govtJobBtn.setBackgroundResource(R.color.primary);
                pvtJobBtn.setBackgroundResource(R.color.black);
                noticeBtn.setBackgroundResource(R.color.primary);
                educationBtn.setBackgroundResource(R.color.primary);
                bankJobBtn.setBackgroundResource(R.color.primary);
                diffenceBtn.setBackgroundResource(R.color.primary);

                searchCatTxt = pvtJobBtn.getText().toString();
                try {
                    myAdapter.getFilter().filter(searchCatTxt);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        noticeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myHomeBtn.setBackgroundResource(R.color.primary);
                govtJobBtn.setBackgroundResource(R.color.primary);
                pvtJobBtn.setBackgroundResource(R.color.primary);
                noticeBtn.setBackgroundResource(R.color.black);
                educationBtn.setBackgroundResource(R.color.primary);
                bankJobBtn.setBackgroundResource(R.color.primary);
                diffenceBtn.setBackgroundResource(R.color.primary);

                searchCatTxt = noticeBtn.getText().toString();
                try {
                    myAdapter.getFilter().filter(searchCatTxt);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        educationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myHomeBtn.setBackgroundResource(R.color.primary);
                govtJobBtn.setBackgroundResource(R.color.primary);
                pvtJobBtn.setBackgroundResource(R.color.primary);
                noticeBtn.setBackgroundResource(R.color.primary);
                educationBtn.setBackgroundResource(R.color.black);
                bankJobBtn.setBackgroundResource(R.color.primary);
                diffenceBtn.setBackgroundResource(R.color.primary);

                searchCatTxt = educationBtn.getText().toString();
                try {
                    myAdapter.getFilter().filter(searchCatTxt);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        bankJobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myHomeBtn.setBackgroundResource(R.color.primary);
                govtJobBtn.setBackgroundResource(R.color.primary);
                pvtJobBtn.setBackgroundResource(R.color.primary);
                noticeBtn.setBackgroundResource(R.color.primary);
                educationBtn.setBackgroundResource(R.color.primary);
                bankJobBtn.setBackgroundResource(R.color.black);
                diffenceBtn.setBackgroundResource(R.color.primary);

                searchCatTxt = bankJobBtn.getText().toString();
                try {
                    myAdapter.getFilter().filter(searchCatTxt);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        diffenceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myHomeBtn.setBackgroundResource(R.color.primary);
                govtJobBtn.setBackgroundResource(R.color.primary);
                pvtJobBtn.setBackgroundResource(R.color.primary);
                noticeBtn.setBackgroundResource(R.color.primary);
                educationBtn.setBackgroundResource(R.color.primary);
                bankJobBtn.setBackgroundResource(R.color.primary);
                diffenceBtn.setBackgroundResource(R.color.black);

                searchCatTxt = diffenceBtn.getText().toString();
                try {
                    myAdapter.getFilter().filter(searchCatTxt);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


    }

    public void loadurl() {
        String url = "https://abdurrakib-brur.github.io/jobs-app/index.html";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();
                        urlstr.setText(response);
                        loadData();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "First Server not found!", Toast.LENGTH_LONG).show();
            }

        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);


    }


    public void loadData() {
        String url = urlstr.getText().toString();
        //Toast.makeText(MainActivity.this,urlstr.getText().toString(),Toast.LENGTH_LONG).show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("data");

                            JSONObject recieveLast = array.getJSONObject(0);
                            String notifBody = recieveLast.getString("title");
                            //myPushNotification("Jobs Circular",notifBody);


                            for (int i = 0; i < array.length(); i++) {

                                JSONObject recieve = array.getJSONObject(i);

                                Myitem item = new Myitem(
                                        recieve.getString("title"),
                                        recieve.getString("description"),
                                        recieve.getString("appstart"),
                                        recieve.getString("dadeline"),
                                        recieve.getString("catecgory"),
                                        recieve.getString("file"),
                                        recieve.getString("img2"),
                                        recieve.getString("img3"),
                                        recieve.getString("img4"),
                                        recieve.getString("img5"),
                                        recieve.getString("img6"),
                                        recieve.getString("img7"),
                                        recieve.getString("img8"),
                                        recieve.getString("img9"),
                                        recieve.getString("img10"),
                                        recieve.getString("pdf")

                                );

                                listItems.add(item);

                            }

                            myAdapter = new MyAdapter(listItems, getApplicationContext());
                            recyclerView.setAdapter(myAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        spin_kit.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Please Check Internet Connection or Server Not found!", Toast.LENGTH_LONG).show();

            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);

    }


    public void myPushNotification(String notifTitle, String notifBody) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notifChannel = new NotificationChannel("NotifId", "NotifId", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager myNotifManager = getSystemService(NotificationManager.class);
            myNotifManager.createNotificationChannel(notifChannel);
        }

        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(MainActivity.this, "NotifId");
        nBuilder.setContentTitle(notifTitle);
        nBuilder.setContentText(notifBody);
        nBuilder.setSmallIcon(R.drawable.ic_launcher_background);
        nBuilder.setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(MainActivity.this);
        notificationManagerCompat.notify(1, nBuilder.build());
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        myHomeBtn.setBackgroundResource(R.color.primary);
        govtJobBtn.setBackgroundResource(R.color.primary);
        pvtJobBtn.setBackgroundResource(R.color.primary);
        noticeBtn.setBackgroundResource(R.color.primary);
        educationBtn.setBackgroundResource(R.color.primary);
        bankJobBtn.setBackgroundResource(R.color.primary);
        diffenceBtn.setBackgroundResource(R.color.primary);

        try {
            myAdapter.getFilter().filter("all");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.menuSearchItem);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                try {

                    myAdapter.getFilter().filter("titleSearch" + newText);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);


    }


}
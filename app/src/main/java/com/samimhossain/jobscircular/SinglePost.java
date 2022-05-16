package com.samimhossain.jobscircular;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.UUID;

public class SinglePost extends AppCompatActivity {
    //float xDown = 0, yDown= 0;

    //private float mScale = 1f;
    //private ScaleGestureDetector mScaleDetector;
    //GestureDetector gestureDetector;

    TextView getTitleText;
    TextView getDescText;
    TextView getAppStartText;
    TextView getDadelineText;
    ImageView postImg;
    ImageView img2;
    ImageView img3;
    ImageView img4;
    ImageView img5;
    ImageView img6;
    ImageView img7;
    ImageView img8;
    ImageView img9;
    ImageView img10;

    ImageView imgFull;


    String getTitle;
    String getDesc;
    String getAppStart;
    String getDadeline;
    String getImgUrl;
    String getImg2Url;
    String getImg3Url;
    String getImg4Url;
    String getImg5Url;
    String getImg6Url;
    String getImg7Url;
    String getImg8Url;
    String getImg9Url;
    String getImg10Url;

    String downloadPdfUrl;
    Button downloadBtn;

    LinearLayout imgLayout;
    ScrollView scrollView;


    //banner ads ==========================================
    AdView adView;
    private InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_post);


        imgFull = (ImageView) findViewById(R.id.fullImgId);
        imgLayout = (LinearLayout) findViewById(R.id.ImgLayoutId);
        imgLayout.setVisibility(imgLayout.GONE);


        scrollView = (ScrollView) findViewById(R.id.scrollViewId);



       /* gestureDetector = new GestureDetector(this, new GestureListener());
        mScaleDetector = new ScaleGestureDetector(this, new ScaleGestureDetector.SimpleOnScaleGestureListener(){

            @Override
            public boolean onScale(ScaleGestureDetector detector) {

                float scale =1 - detector.getScaleFactor();

                float prevScale = mScale;
                mScale += scale;

                if(mScale < 0.1f)
                    mScale = 0.1f;

                if(mScale > 1f)
                    mScale = 1f;

                ScaleAnimation scaleAnimation = new ScaleAnimation(1f/prevScale, 1f/mScale, 1f/prevScale, 1f/mScale, detector.getFocusX(),detector.getFocusY());
                scaleAnimation.setDuration(0);
                scaleAnimation.setFillAfter(true);
                ScrollView scrollView = (ScrollView) findViewById(R.id.scrollViewId);
                scrollView.startAnimation(scaleAnimation);
                return true;
            }
        });*/

        //LinearLayout sL = (LinearLayout) findViewById(R.id.sLId);

        /*sL.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch(motionEvent.getActionMasked()){
                    case MotionEvent.ACTION_DOWN:
                        xDown = motionEvent.getX();
                        yDown = motionEvent.getY();

                        break;
                    case MotionEvent.ACTION_MOVE:
                        float movedX, movedY;
                        movedX = motionEvent.getX();
                        //movedY =motionEvent.getY();
                        float distanceX = movedX-xDown;
                        //float distanceY = movedY-yDown;
                        sL.setX(sL.getX()+distanceX);
                        //sL.setY(sL.getY()+distanceY);
                        break;
                }
                return true;
            }
        });*/

        getTitleText = (TextView) findViewById(R.id.spTitleId);
        getDescText = (TextView) findViewById(R.id.spDescId);
        getAppStartText = (TextView) findViewById(R.id.spAppStartId);
        getDadelineText = (TextView) findViewById(R.id.spDadelineId);
        postImg = (ImageView) findViewById(R.id.spImgViewId);
        img2 = (ImageView) findViewById(R.id.img2Id);
        img3 = (ImageView) findViewById(R.id.img3Id);
        img4 = (ImageView) findViewById(R.id.img4Id);
        img5 = (ImageView) findViewById(R.id.img5Id);
        img6 = (ImageView) findViewById(R.id.img6Id);
        img7 = (ImageView) findViewById(R.id.img7Id);
        img8 = (ImageView) findViewById(R.id.img8Id);
        img9 = (ImageView) findViewById(R.id.img9Id);
        img10 = (ImageView) findViewById(R.id.img10Id);


        downloadBtn = (Button) findViewById(R.id.downloadBtnId);


        //ads ==========================

        adView = findViewById(R.id.adView);


        //banner ads===============================

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


        InterstitialAd.load(this, "ca-app-pub-3940256099942544/1033173712", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i("TAG", "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i("TAG", loadAdError.getMessage());
                        mInterstitialAd = null;
                    }
                });


        Intent i = getIntent();
        try {
            getTitle = i.getStringExtra("sendTitle");
            getDesc = i.getStringExtra("sendDesc");
            getAppStart = i.getStringExtra("sendAppStart");
            getDadeline = i.getStringExtra("sendDeadline");
            getImgUrl = i.getStringExtra("sendImgUrl");
            getImg2Url = i.getStringExtra("sendImg2");
            getImg3Url = i.getStringExtra("sendImg3");
            getImg4Url = i.getStringExtra("sendImg4");
            getImg5Url = i.getStringExtra("sendImg5");
            getImg6Url = i.getStringExtra("sendImg6");
            getImg7Url = i.getStringExtra("sendImg7");
            getImg8Url = i.getStringExtra("sendImg8");
            getImg9Url = i.getStringExtra("sendImg9");
            getImg10Url = i.getStringExtra("sendImg10");

            downloadPdfUrl = i.getStringExtra("sendPdf");

            getTitleText.setText(getTitle);
            if (!getDesc.isEmpty()) {
                getDescText.setText(getDesc);
                getDescText.setVisibility(getDescText.VISIBLE);
            } else {
                getDescText.setVisibility(getDescText.GONE);
            }


            if (!getAppStart.isEmpty()) {
                getAppStartText.setText("Application Start: " + getAppStart);
                getAppStartText.setVisibility(getAppStartText.VISIBLE);
            } else {
                getAppStartText.setVisibility(getAppStartText.GONE);
            }

            if (!getDadeline.isEmpty()) {
                getDadelineText.setText("Application End: " + getDadeline);
                getDadelineText.setVisibility(getDadelineText.VISIBLE);
            } else {
                getDadelineText.setVisibility(getDadelineText.GONE);
            }

            if (!getImgUrl.isEmpty()) {
                Picasso.get()
                        .load(getImgUrl)
                        .placeholder(R.drawable.progress_animation)
                        .error(R.drawable.try_later)
                        .into(postImg);
                postImg.setVisibility(postImg.VISIBLE);
            } else {
                postImg.setVisibility(postImg.GONE);
            }
            if (!getImg2Url.isEmpty()) {
                Picasso.get()
                        .load(getImg2Url)
                        .placeholder(R.drawable.progress_animation)
                        .error(R.drawable.try_later)
                        .into(img2);
                img2.setVisibility(img2.VISIBLE);
            } else {
                img2.setVisibility(img2.GONE);
            }
            if (!getImg3Url.isEmpty()) {
                Picasso.get()
                        .load(getImg3Url)
                        .placeholder(R.drawable.progress_animation)
                        .error(R.drawable.try_later)
                        .into(img3);
                img3.setVisibility(img3.VISIBLE);
            } else {
                img3.setVisibility(img3.GONE);
            }
            if (!getImg4Url.isEmpty()) {
                Picasso.get()
                        .load(getImg4Url)
                        .placeholder(R.drawable.progress_animation)
                        .error(R.drawable.try_later)
                        .into(img4);
                img4.setVisibility(img4.VISIBLE);
            } else {
                img4.setVisibility(img4.GONE);
            }
            if (!getImg5Url.isEmpty()) {
                Picasso.get()
                        .load(getImg5Url)
                        .placeholder(R.drawable.progress_animation)
                        .error(R.drawable.try_later)
                        .into(img5);
                img5.setVisibility(img5.VISIBLE);
            } else {
                img5.setVisibility(img5.GONE);
            }
            if (!getImg6Url.isEmpty()) {
                Picasso.get()
                        .load(getImg6Url)
                        .placeholder(R.drawable.progress_animation)
                        .error(R.drawable.try_later)
                        .into(img6);
                img6.setVisibility(img6.VISIBLE);
            } else {
                img6.setVisibility(img6.GONE);
            }
            if (!getImg7Url.isEmpty()) {
                Picasso.get()
                        .load(getImg7Url)
                        .placeholder(R.drawable.progress_animation)
                        .error(R.drawable.try_later)
                        .into(img7);
                img7.setVisibility(img7.VISIBLE);
            } else {
                img7.setVisibility(img7.GONE);
            }
            if (!getImg8Url.isEmpty()) {
                Picasso.get()
                        .load(getImg8Url)
                        .placeholder(R.drawable.progress_animation)
                        .error(R.drawable.try_later)
                        .into(img8);
                img8.setVisibility(img8.VISIBLE);
            } else {
                img8.setVisibility(img8.GONE);
            }
            if (!getImg9Url.isEmpty()) {
                Picasso.get()
                        .load(getImg9Url)
                        .placeholder(R.drawable.progress_animation)
                        .error(R.drawable.try_later)
                        .into(img9);
                img9.setVisibility(img9.VISIBLE);
            } else {
                img9.setVisibility(img9.GONE);
            }
            if (!getImg10Url.isEmpty()) {
                Picasso.get()
                        .load(getImg10Url)
                        .placeholder(R.drawable.progress_animation)
                        .error(R.drawable.try_later)
                        .into(img10);
                img10.setVisibility(img10.VISIBLE);
            } else {
                img10.setVisibility(img10.GONE);
            }

            postImg.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SinglePost.this);

                    builder.setTitle("Download Image");
                    //builder.setMessage("Do You want to download");

                    builder.setPositiveButton("Download", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            String fileName = getImgUrl;
                            fileName = fileName.replace("https://app.jobscirculartoday.com/uploads/", "");

                            downloadFile(getImgUrl, "Jobs-Circular-App-" + fileName);
                            Toast.makeText(SinglePost.this, "Downloading Image..", Toast.LENGTH_LONG).show();

                            dialog.dismiss();


                            //Admob code goes here=============================

                            if (mInterstitialAd != null) {
                                mInterstitialAd.show(SinglePost.this);
                            } else {
                                Log.d("TAG", "The interstitial ad wasn't ready yet.");
                            }

                            //admov ses============================


                        }
                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            // Do nothing
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();

                    return false;
                }
            });


            img2.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SinglePost.this);

                    builder.setTitle("Download Image");
                    //builder.setMessage("Do You want to download");

                    builder.setPositiveButton("Download", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                            String fileName = getImg2Url;
                            fileName = fileName.replace("https://app.jobscirculartoday.com/uploads/", "");

                            downloadFile(getImg2Url, "Jobs-Circular-App-" + fileName);
                            Toast.makeText(SinglePost.this, "Downloading Image..", Toast.LENGTH_LONG).show();
                            dialog.dismiss();


                            //Admob code goes here=============================

                            if (mInterstitialAd != null) {
                                mInterstitialAd.show(SinglePost.this);
                            } else {
                                Log.d("TAG", "The interstitial ad wasn't ready yet.");
                            }

                            //admov ses============================


                        }
                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            // Do nothing
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();

                    return false;
                }
            });


            img3.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SinglePost.this);

                    builder.setTitle("Download Image");
                    //builder.setMessage("Do You want to download");

                    builder.setPositiveButton("Download", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                            String fileName = getImg3Url;
                            fileName = fileName.replace("https://app.jobscirculartoday.com/uploads/", "");

                            downloadFile(getImg3Url, "Jobs-Circular-App-" + fileName);
                            Toast.makeText(SinglePost.this, "Downloading Image..", Toast.LENGTH_LONG).show();
                            dialog.dismiss();


                            //Admob code goes here=============================

                            if (mInterstitialAd != null) {
                                mInterstitialAd.show(SinglePost.this);
                            } else {
                                Log.d("TAG", "The interstitial ad wasn't ready yet.");
                            }

                            //admov ses============================


                        }
                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            // Do nothing
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();

                    return false;
                }
            });


            img4.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SinglePost.this);

                    builder.setTitle("Download Image");
                    //builder.setMessage("Do You want to download");

                    builder.setPositiveButton("Download", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                            String fileName = getImg4Url;
                            fileName = fileName.replace("https://app.jobscirculartoday.com/uploads/", "");

                            downloadFile(getImg4Url, "Jobs-Circular-App-" + fileName);
                            Toast.makeText(SinglePost.this, "Downloading Image..", Toast.LENGTH_LONG).show();
                            dialog.dismiss();


                            //Admob code goes here=============================

                            if (mInterstitialAd != null) {
                                mInterstitialAd.show(SinglePost.this);
                            } else {
                                Log.d("TAG", "The interstitial ad wasn't ready yet.");
                            }

                            //admov ses============================


                        }
                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            // Do nothing
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();

                    return false;
                }
            });


            img5.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SinglePost.this);

                    builder.setTitle("Download Image");
                    //builder.setMessage("Do You want to download");

                    builder.setPositiveButton("Download", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                            String fileName = getImg5Url;
                            fileName = fileName.replace("https://app.jobscirculartoday.com/uploads/", "");

                            downloadFile(getImg5Url, "Jobs-Circular-App-" + fileName);
                            Toast.makeText(SinglePost.this, "Downloading Image..", Toast.LENGTH_LONG).show();
                            dialog.dismiss();


                            //Admob code goes here=============================

                            if (mInterstitialAd != null) {
                                mInterstitialAd.show(SinglePost.this);
                            } else {
                                Log.d("TAG", "The interstitial ad wasn't ready yet.");
                            }

                            //admov ses============================


                        }
                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            // Do nothing
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();

                    return false;
                }
            });


            img6.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SinglePost.this);

                    builder.setTitle("Download Image");
                    //builder.setMessage("Do You want to download");

                    builder.setPositiveButton("Download", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                            String fileName = getImg6Url;
                            fileName = fileName.replace("https://app.jobscirculartoday.com/uploads/", "");

                            downloadFile(getImg6Url, "Jobs-Circular-App-" + fileName);
                            Toast.makeText(SinglePost.this, "Downloading Image..", Toast.LENGTH_LONG).show();
                            dialog.dismiss();


                            //Admob code goes here=============================

                            if (mInterstitialAd != null) {
                                mInterstitialAd.show(SinglePost.this);
                            } else {
                                Log.d("TAG", "The interstitial ad wasn't ready yet.");
                            }

                            //admov ses============================


                        }
                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            // Do nothing
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();

                    return false;
                }
            });


            img7.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SinglePost.this);

                    builder.setTitle("Download Image");
                    //builder.setMessage("Do You want to download");

                    builder.setPositiveButton("Download", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                            String fileName = getImg7Url;
                            fileName = fileName.replace("https://app.jobscirculartoday.com/uploads/", "");

                            downloadFile(getImg7Url, "Jobs-Circular-App-" + fileName);
                            Toast.makeText(SinglePost.this, "Downloading Image..", Toast.LENGTH_LONG).show();
                            dialog.dismiss();


                            //Admob code goes here=============================

                            if (mInterstitialAd != null) {
                                mInterstitialAd.show(SinglePost.this);
                            } else {
                                Log.d("TAG", "The interstitial ad wasn't ready yet.");
                            }

                            //admov ses============================


                        }
                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            // Do nothing
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();

                    return false;
                }
            });


            img8.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SinglePost.this);

                    builder.setTitle("Download Image");
                    //builder.setMessage("Do You want to download");

                    builder.setPositiveButton("Download", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                            String fileName = getImg8Url;
                            fileName = fileName.replace("https://app.jobscirculartoday.com/uploads/", "");

                            downloadFile(getImg8Url, "Jobs-Circular-App-" + fileName);
                            Toast.makeText(SinglePost.this, "Downloading Image..", Toast.LENGTH_LONG).show();
                            dialog.dismiss();


                            //Admob code goes here=============================

                            if (mInterstitialAd != null) {
                                mInterstitialAd.show(SinglePost.this);
                            } else {
                                Log.d("TAG", "The interstitial ad wasn't ready yet.");
                            }

                            //admov ses============================


                        }
                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            // Do nothing
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();

                    return false;
                }
            });


            img9.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SinglePost.this);

                    builder.setTitle("Download Image");
                    //builder.setMessage("Do You want to download");

                    builder.setPositiveButton("Download", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                            String fileName = getImg9Url;
                            fileName = fileName.replace("https://app.jobscirculartoday.com/uploads/", "");

                            downloadFile(getImg9Url, "Jobs-Circular-App-" + fileName);
                            Toast.makeText(SinglePost.this, "Downloading Image..", Toast.LENGTH_LONG).show();
                            dialog.dismiss();


                            //Admob code goes here=============================

                            if (mInterstitialAd != null) {
                                mInterstitialAd.show(SinglePost.this);
                            } else {
                                Log.d("TAG", "The interstitial ad wasn't ready yet.");
                            }

                            //admov ses============================


                        }
                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            // Do nothing
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();

                    return false;
                }
            });


            img10.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SinglePost.this);

                    builder.setTitle("Download Image");
                    //builder.setMessage("Do You want to download");

                    builder.setPositiveButton("Download", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                            String fileName = getImg10Url;
                            fileName = fileName.replace("https://app.jobscirculartoday.com/uploads/", "");

                            downloadFile(getImg10Url, "Jobs-Circular-App-" + fileName);
                            Toast.makeText(SinglePost.this, "Downloading Image..", Toast.LENGTH_LONG).show();
                            dialog.dismiss();


                            //Admob code goes here=============================

                            if (mInterstitialAd != null) {
                                mInterstitialAd.show(SinglePost.this);
                            } else {
                                Log.d("TAG", "The interstitial ad wasn't ready yet.");
                            }

                            //admov ses============================


                        }
                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            // Do nothing
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();

                    return false;
                }
            });

            postImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImgFullScreen(getImgUrl);
                    imgLayout.setVisibility(imgLayout.VISIBLE);
                    scrollView.setVisibility(scrollView.GONE);
                }
            });

            img2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImgFullScreen(getImg2Url);
                    imgLayout.setVisibility(imgLayout.VISIBLE);
                    scrollView.setVisibility(scrollView.GONE);
                }
            });

            img3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImgFullScreen(getImg3Url);
                    imgLayout.setVisibility(imgLayout.VISIBLE);
                    scrollView.setVisibility(scrollView.GONE);
                }
            });

            img4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImgFullScreen(getImg4Url);
                    imgLayout.setVisibility(imgLayout.VISIBLE);
                    scrollView.setVisibility(scrollView.GONE);
                }
            });

            img5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImgFullScreen(getImg5Url);
                    imgLayout.setVisibility(imgLayout.VISIBLE);
                    scrollView.setVisibility(scrollView.GONE);
                }
            });

            img6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImgFullScreen(getImg6Url);
                    imgLayout.setVisibility(imgLayout.VISIBLE);
                    scrollView.setVisibility(scrollView.GONE);
                }
            });

            img7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImgFullScreen(getImg7Url);
                    imgLayout.setVisibility(imgLayout.VISIBLE);
                    scrollView.setVisibility(scrollView.GONE);
                }
            });

            img8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImgFullScreen(getImg8Url);
                    imgLayout.setVisibility(imgLayout.VISIBLE);
                    scrollView.setVisibility(scrollView.GONE);
                }
            });
            img9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImgFullScreen(getImg9Url);
                    imgLayout.setVisibility(imgLayout.VISIBLE);
                    scrollView.setVisibility(scrollView.GONE);
                }
            });
            img10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImgFullScreen(getImg10Url);
                    imgLayout.setVisibility(imgLayout.VISIBLE);
                    scrollView.setVisibility(scrollView.GONE);
                }
            });


            if (!downloadPdfUrl.isEmpty()) {
                downloadBtn.setVisibility(downloadBtn.VISIBLE);

                downloadBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //String uniqueId = UUID.randomUUID().toString();
                        String fileName = downloadPdfUrl;
                        fileName = fileName.replace("https://app.jobscirculartoday.com/uploads/", "");

                        downloadFile(downloadPdfUrl, "Jobs-Circular-App-Pdf-" + fileName);
                        Toast.makeText(SinglePost.this, "Downloading File..", Toast.LENGTH_LONG).show();


                        //Admob code goes here=============================

                        if (mInterstitialAd != null) {
                            mInterstitialAd.show(SinglePost.this);
                        } else {
                            Log.d("TAG", "The interstitial ad wasn't ready yet.");
                        }

                        //admov ses============================


                    }


                });
            } else {
                downloadBtn.setVisibility(downloadBtn.GONE);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

   /* @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);
        mScaleDetector.onTouchEvent(ev);
        gestureDetector.onTouchEvent(ev);
        return gestureDetector.onTouchEvent(ev);
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {

            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

            return true;
        }
    }*/


    public void downloadFile(String url, String fileName) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setTitle(fileName);
        request.setDescription("Downloading: " + fileName);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.allowScanningByMediaScanner();
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }

    public void ImgFullScreen(String imgurl) {

        Picasso.get().load(imgurl).into(imgFull);

    }

    public void onBackPressed() {
        if (imgLayout.getVisibility() == View.VISIBLE) {
            imgLayout.setVisibility(imgLayout.GONE);
            scrollView.setVisibility(scrollView.VISIBLE);
        } else {
            this.finish();
        }

    }

}




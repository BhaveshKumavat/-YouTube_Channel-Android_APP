package com.example.bhavesh.edutechload;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;

public class MainActivity2 extends AppCompatActivity {


    WebView yt;
    ProgressBar pb;
    String url ="https://www.youtube.com/channel/UCRhmjCnvOgULTzEFe-Jpfdw";
    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        dl = (DrawerLayout) findViewById(R.id.dl);
        abdt = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);

        dl.addDrawerListener(abdt);
        abdt.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.facebook)
                {


                    Intent facebookIntent = openFacebook(MainActivity2.this);
                    startActivity(facebookIntent);



                }
                if (id == R.id.instagram)

                {
                    Intent i = new Intent(MainActivity2.this, instagram.class);
                    startActivity(i);

                }

                if (id == R.id.website)

                {
                    Intent i = new Intent(MainActivity2.this, website.class);
                    startActivity(i);

                }

                if (id == R.id.share12)
                {


                    ApplicationInfo api = getApplicationContext().getApplicationInfo();
                    String apkPath = api.sourceDir;

                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("application/vnd.android.package-archive");
                    intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(apkPath)));
                    startActivity(Intent.createChooser(intent,"Share App Using"));






                }

                return true;
            }
        });




        yt=(WebView)findViewById(R.id.webView);
        pb=(ProgressBar)findViewById(R.id.progressBar);



        if(isNetworkAvailable()==true){
            yt.loadUrl(url);

        }
        else
        {

            pb.setVisibility(View.GONE);


            Intent i = new Intent(MainActivity2.this,emojiactivity.class);
            startActivity(i);




        }

        yt.setWebViewClient(new MyWebViewClient());
        yt.getSettings().setJavaScriptEnabled(true);
        yt.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view,int newProgress){

                if(newProgress==100)
                {
                    pb.setVisibility(View.GONE);
                }

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && yt.canGoBack())
        {
            yt.goBack();
            return true;

        }

        return super.onKeyDown(keyCode,event);
    }


    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }
    public boolean isInstalled(String uri) {
        PackageManager pm= getPackageManager();
        boolean isIn;
        try{
            pm.getPackageInfo(uri,PackageManager.GET_ACTIVITIES);
            isIn=true;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            isIn = false;

        }
        return isIn;

    }







    public static Intent openFacebook(Context context)
    {
        try{
            context.getPackageManager().getPackageInfo("com.facbook.katana",0);
            return new Intent(Intent.ACTION_VIEW,Uri.parse("fb://page/1611104945618409"));

        }
        catch(Exception e)
        {
            return new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/edutechload/"));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu manu){
        getMenuInflater().inflate(R.menu.main,manu);
        return true;
    }


    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if(id == R.id.action)
        {
            boolean isAppExists;
            isAppExists = isInstalled("com.google.android.youtube");
            if(isAppExists==true)
            {
                Intent i = new Intent("android.intent.action.VIEW",Uri.parse(url));
                startActivity(i);
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Youtube App is Not Installed",Toast.LENGTH_SHORT).show();
            }


        }

        if (id == R.id.about)
        {

            Intent i = new Intent(MainActivity2.this, about.class);
            startActivity(i);

        }

        if (id == R.id.contact) {

            Intent i = new Intent(MainActivity2.this, contact.class);
            startActivity(i);
        }



        if (id == R.id.blog)

        {
            Intent i = new Intent(MainActivity2.this, website.class);
            startActivity(i);

        }


        if (id == R.id.share)
        {


            ApplicationInfo api = getApplicationContext().getApplicationInfo();
            String apkPath = api.sourceDir;

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("application/vnd.android.package-archive");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(apkPath)));
            startActivity(Intent.createChooser(intent,"Share App Using"));


        }


        if (id == R.id.feedback)

        {
            Intent i = new Intent(MainActivity2.this,feedback .class);
            startActivity(i);

        }


        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);



    }





    @Override
    public void onBackPressed()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
        builder.setMessage("Are You Sure Want To Exit ? ");
        builder.setCancelable(true);
        builder.setNegativeButton("NO !",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface,int i){

                dialogInterface.cancel();
            }


        });

        builder.setPositiveButton("Yes",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface,int i){
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
















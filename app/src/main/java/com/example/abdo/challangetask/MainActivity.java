package com.example.abdo.challangetask;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.example.abdo.challangetask.dataProccess.Connector;
import com.example.abdo.challangetask.dataProccess.DataEncap;
import com.example.abdo.challangetask.dataProccess.JsonParser;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    final static String api = "http://grapesnberries.getsandbox.com/products?count=10&from=1";
    JsonParser parser = new JsonParser();
    private RecyclerView recyclerView;
    private AdapterProducts adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Check Internet Connection In your App
        if(Check_Internet_Connection())
        {
            Connector connector = new Connector();
            Toast.makeText(this, "Loading ....", Toast.LENGTH_SHORT).show();
            try {

                ArrayList<DataEncap> arrayList = parser.JsonProcess(connector.execute(api).get());

                recyclerMain();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


        }
        else
        {
            Toast.makeText(MainActivity.this, "Check Internet Connection .", Toast.LENGTH_LONG).show();
        }

    }
    private void recyclerMain() {

        recyclerView =findViewById(R.id.recycler_view);
        adapter = new AdapterProducts(parser.getlist(), getApplicationContext(), this);

        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    private boolean Check_Internet_Connection()
    {
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}


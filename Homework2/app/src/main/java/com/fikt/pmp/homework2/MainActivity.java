package com.fikt.pmp.homework2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ArrayList<Clubs> l = new ArrayList<Clubs>();
    List<String> listClubs = new ArrayList<String>();
    List<String> selectedClubs = new ArrayList<String>();
    int numQuestion = 0;
    int points = 0;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            read();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("Zlatko"," File not found");
        }

        final ListView listView = (ListView) findViewById(R.id.idListView);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, selectedClubs);

        final Button button = (Button) findViewById(R.id.idButton);
        button.setOnClickListener(lisenerot);

        listView.setOnItemClickListener(new ListClickHandler());


    }

    void read() throws IOException {

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("Answers.txt")));

            // do reading, usually loop until end of file reading
            String cityClub;
            reader.readLine();
            while ((cityClub = reader.readLine()) != null) {
                //process line
                String[] separated = cityClub.split(";");
                separated[1] = separated[1].trim();
                separated[2] = separated[2].trim();
                l.add(new Clubs(separated[0], separated[1], separated[2]));
                listClubs.add(separated[2]);
                Log.d("Zlatko"," has line "+separated[0] +" "+ separated[1] + " " + separated[2]);
            }
        } catch (IOException e) {
            Log.d("Zlatko", " empty");
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
    }


    void next(){
        // public void onClick(View v) {
        // Perform action on click
        int sizeOfArray = l.size();
        ListView listView = (ListView) findViewById(R.id.idListView);
        Log.d("Zlatko","kliknav na kopce");
        TextView txtCity = (TextView) findViewById(R.id.idTown);

        if (numQuestion<sizeOfArray) {
            Clubs cc = l.get(numQuestion);
            txtCity.setText(String.valueOf(cc.getCity()));

            selectedClubs.clear();
            selectedClubs.add(cc.getClub());
            for (int i = 0; i<3; i++)
            {
                List<String> allredyAdded = new ArrayList<String>();
                allredyAdded.clear();
                Random random = new Random();
                int n = random.nextInt(sizeOfArray);
                if (l.get(numQuestion).getCity().equals(l.get(n).getCity()) | selectedClubs.contains(listClubs.get(n)))
                {
                    i--;
                }
                else
                {
                    selectedClubs.add(listClubs.get(n));
                }
            }
            Collections.shuffle(selectedClubs);
            //adapter.clear();
            //adapter.add(selectedClubs);

            listView.setAdapter(adapter);

            numQuestion++;
            Log.d("Zlatko ", " " + cc.getCity());
        }
        else{
            Button button = (Button) findViewById(R.id.idButton);
            listView.setVisibility(View.GONE);
            button.setVisibility(View.VISIBLE);
            TextView vkupnoPoeni = (TextView) findViewById(R.id.idTown);
            vkupnoPoeni.setText("Total points:");
            button.setText("Start again");
            numQuestion = 0;
        }
    }

    View.OnClickListener lisenerot = new View.OnClickListener() {
        public void onClick(View v) {
            // public void onClick(View v) {
            // Perform action on click
           /* TextView txtCity = (TextView) findViewById(R.id.idTown) ;
            txtCity.setVisibility(View.VISIBLE);*/
            points = 0;
            ListView listView = (ListView) findViewById(R.id.idListView);
            listView.setVisibility(View.VISIBLE);
            Button button = (Button) findViewById(R.id.idButton);
            button.setVisibility(View.GONE);
            TextView poeni = (TextView) findViewById(R.id.idPoints);
            poeni.setText(String.valueOf(points));
            next();
        }
    };

    public class ListClickHandler implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3) {
            ListView listView = (ListView) findViewById(R.id.idListView);
            String selected = listView.getItemAtPosition(position).toString();
            Log.d("Zlatko ", selected);
            int sel = position;
            if (selected.equals(l.get(numQuestion-1).getClub()))
            {
                points++;
                TextView poeni = (TextView) findViewById(R.id.idPoints);
                poeni.setText(String.valueOf(points));
            }
            else
            {

            }

            next();
        }
    }
}

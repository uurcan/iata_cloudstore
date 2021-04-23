package com.java.iata_firebase;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private List<IATAData> iataDataList = new ArrayList<>();
    private String result;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.read:
                readCSV();
                break;
            case R.id.insert:
                pushData();
                break;
            case R.id.find:
                convertData();
                break;
        }
    }

    private void convertData() {
        EditText editText = findViewById(R.id.edtFind);
        if (!TextUtils.isEmpty(editText.getText())){
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("all");
            Query query = reference.equalTo(editText.getText().toString());
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.child("name").getValue() != null) {
                        result = snapshot.child("name").getKey();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.d("TEST","Cancelled");
                }
            });
            Toast.makeText(this,"Result is : " + result ,Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Empty, please define a query to see results.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.read);
        Button push = findViewById(R.id.insert);
        Button find = findViewById(R.id.find);
        btn.setOnClickListener(this);
        push.setOnClickListener(this);
        find.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void readCSV(){
        InputStream inputStream = getResources().openRawResource(R.raw.airport_codes);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        String line = "";
        try {
            while ((line = reader.readLine()) != null){
                String[] tokens = line.split(",");
                IATAData iataData  = new IATAData(tokens[0],tokens[1],tokens[2],tokens[3],tokens[4],tokens[5],tokens[6],tokens[7],tokens[8],tokens[9],tokens[10]);
                iataDataList.add(iataData);
            }
        } catch (IOException e) {
            Log.wtf("TEST","Error reading data file on line " + line,e);
            e.printStackTrace();
        }
        Toast.makeText(this,"Reading completed, " + String.valueOf(iataDataList.size()) + " records has been inserted.",Toast.LENGTH_LONG ).show();
    }

    private void pushData(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hint");
        builder.setMessage("This operation will take time, please check your firebase console to see results");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = database.getReference("all").child("test");
                Map<String, IATAData> hashMap = new HashMap<>();
                for (IATAData iataData : iataDataList){
                    hashMap.put(iataData.getIDENT(),iataData);
                }
                databaseReference.setValue(hashMap);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void commented(){
         /*try
        {
            List<String> list = new ArrayList<>();
            String line;

            String fileName = Environment.getDataDirectory() + "/airport_codes.csv";
            FileReader reader = new FileReader(fileName);
            BufferedReader bufrdr = new BufferedReader(reader);
            line = bufrdr.readLine();
            while (line != null) {
                list.add(line);
                line = bufrdr.readLine();
            }
            bufrdr.close();
            reader.close();

            String[] array = new String[list.size()];
            list.toArray(array);

            for (int i = 0; i < list.size(); i++) {
                System.out.println(" 22222222 0 0 " + list.get(i));
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }*/
        /*final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("iata_codes_all");

        DatabaseReference usersRef = ref.child("all");

        Map<String, String> hashMap = new HashMap<>();

            try {
                //File file = new File("C:\\Users\\ucelebi\\Desktop\\");
                //FileReader fr = new FileReader(file);
                BufferedReader br = br = new BufferedReader(new InputStreamReader(getAssets().open("airport_codes.csv")));

                String line = "";
                String[] tempArr;
                while((line = br.readLine()) != null) {
                    tempArr = line.split(",");
                    for(String tempStr : tempArr) {
                        hashMap.put("IATA",tempArr[0]);
                        hashMap.put("TYPE",tempArr[1]);
                        hashMap.put("NAME",tempArr[2]);
                        hashMap.put("ELEVATION_FT",tempArr[3]);
                        hashMap.put("ISO_COUNTRY",tempArr[4]);
                        hashMap.put("ISO_REGION",tempArr[5]);
                        hashMap.put("MUNICIPALITY",tempArr[6]);
                        hashMap.put("GPS_CODE",tempArr[7]);
                        hashMap.put("IATA_CODE",tempArr[8]);
                        hashMap.put("LOCAL_CODE",tempArr[9]);
                        hashMap.put("COORDINATES",tempArr[10]);

                        Log.e("TEST",tempArr[0] + " Is added.");
                    }
                    usersRef.setValue(hashMap);
                    System.out.println();
                }
                br.close();
            } catch(IOException ioe) {
                ioe.printStackTrace();
            }*/
    }
}

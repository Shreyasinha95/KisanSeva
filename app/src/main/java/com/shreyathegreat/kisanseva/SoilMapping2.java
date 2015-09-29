package com.shreyathegreat.kisanseva;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class SoilMapping2 extends ActionBarActivity {
    TextView TV;

    String[][] ARRAY={{"ಅಕ್ಕಿ\n" +
            "\n" +
            "Akki", "ಗೋಧಿ\n" +
            "\n" +
            "Gōdhi","ಕಬ್ಬು\n" +
            "\n" +
            "Kabbu", "ಹತ್ತಿ\n" +
            "\n" +
            "Hatti" , "ಶೇಂಗಾ\n" +
            "Śēṅgā"," ರಾಗಿ\n" +
            "Rāgi" ,"ಬೆಳೆಗಳನ್ನು\n" +
            "Beḷegaḷannu" },
            {"ಬಾರ್ಲಿ\n" +
                    "Bārli","  ರಾಗಿ\n" +
                    "Rāgi\" "},
            { "ಗೋಡಂಬಿ\n" +
                    "Gōḍambi", "ರಬ್ಬರ್\n" +
                    "Rabbar"," ತೆಂಗಿನ\n" +
                    "Teṅgina", "ಚಹಾ\n" +
                    "Cahā" ," ಕಾಫಿ\n" +
                    "Kāphi"},
            {"ಅಕ್ಕಿ\n" +
                    "\n" +
                    "Akki", "ಗೋಧಿ\n" +
                    "\n" +
                    "Gōdhi","ಕಬ್ಬು\n" +
                    "\n" +
                    "Kabbu", "ರಾಗಿ\n" +
                    "Rāgi"," ಶೇಂಗಾ\n" +
                    "Śēṅgā"," ಯೀಸ್ಟ್\n" +
                    "Yīsṭ ","ಆಲೂಗಡ್ಡೆ\n" +
                    "Ālūgaḍḍe"},
            { "ಚಹಾ\n" +
                    "Cahā" ," ಕಾಫಿ\n" +
                    "Kāphi", "ಮಸಾಲೆಗಳು\n" +
                    "Masālegaḷu" ," ಉಷ್ಣವಲಯದ ಹಣ್ಣುಗಳ\n" +
                    "Uṣṇavalayada haṇṇugaḷa"  },
            {"ಚಹಾ\n" +
                    "Cahā" ," ಕಾಫಿ\n" +
                    "Kāphi", "ಮಸಾಲೆಗಳು\n",  "ಗೋಧಿ\n" +
                    "\n" +
                    "Gōdhi", "\n" +
                    "ಮೆಕ್ಕೆ\n" +
                    "Mekke", "ಬಾರ್ಲಿ\n" +
                    "Bārli", " ಉಷ್ಣವಲಯದ ಹಣ್ಣುಗಳ\n" +
                    "Uṣṇavalayada haṇṇugaḷa" ,"ಮಸಾಲೆಗಳು\n" +
                    "Masālegaḷu"}};


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soil_mapping2);
        // TV= (TextView) findViewById(R.id.tv);

        Intent in=getIntent();
        String pos = in.getStringExtra("Pos");
        int position=Integer.parseInt(pos);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, ARRAY[position-1]);

        ListView listView = (ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);

        // TV.setText(pos);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_soil_mapping2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
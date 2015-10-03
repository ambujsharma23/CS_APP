package hk.ust.cse.comp107x.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class ShowUserProfile extends ActionBarActivity {
    ListView listView ;
    String Option="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_profile);
        listView = (ListView) findViewById(R.id.listView);

        // Defined Array values to show in ListView
        final String[] values = new String[] { "Profile",
                "Appointments",
                "Appointment History",
                "Rate HealthyCar",
                "Support",
                "Terms of use",


        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) listView.getItemAtPosition(position);
                String Options = "";
                if (itemPosition == 0)
                    Options = "Profile";
                if (itemPosition == 1)
                    Options = "Appointments";
                if (itemPosition == 2)
                    Options = "Appointment History";
                if (itemPosition == 3)
                    Options = "Rate HealthyCar";
                if (itemPosition == 4)
                    Options = "Support";
                if (itemPosition == 5)
                    Options = "Terms of use";
                // Show Alert
                Option = Options;
                Toast.makeText(getApplicationContext(),
                        " You have selected  : " + Option, Toast.LENGTH_LONG)
                        .show();

            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_user_profile, menu);
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

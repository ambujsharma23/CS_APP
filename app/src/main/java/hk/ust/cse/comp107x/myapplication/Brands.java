package hk.ust.cse.comp107x.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
public class Brands extends ActionBarActivity {
    ListView list;
    String[] itemname ={"Tata",
            "Fiat",
            "Ford",
            "Honda",
            "VolksWagen",
            "Mitsubishi",
            "Nissan",
            "Renault",
            "Suzuki",
    };
    Integer[] imgid={
            R.drawable.tata,
            R.drawable.fiat,
            R.drawable.ford,
            R.drawable.honda,
            R.drawable.ww,
            R.drawable.mitsubishi,
            R.drawable.nissan,
            R.drawable.renault,
            R.drawable.suzuki,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brands);
       Adapter adapter=new Adapter(this, itemname, imgid);
        list=(ListView)findViewById(R.id.list1);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem= itemname[+position];
               // Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                Intent in = new Intent(getApplicationContext(), BookService.class);
                // in.putExtra("Message",Area);
                //Create the bundle
                String pos=String.valueOf(+position);
                Bundle bundle = new Bundle();
                bundle.putString("message", pos);
                in.putExtras(bundle);
                startActivity(in);
            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_brands, menu);
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

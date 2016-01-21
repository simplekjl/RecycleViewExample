package company.example.recycleviewexample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import company.example.recycleviewexample.database.DbHandler;
import company.example.recycleviewexample.models.Adreess;
import company.example.recycleviewexample.models.Contact;

public class MainActivity extends AppCompatActivity {

    //Variables
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private List<String> contactos = new ArrayList<>();
    private TextView dateCurr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //TODO look for database
        DbHandler db = new DbHandler(this);

        db.onUpgrade(db.getWritableDatabase(), 1, 1);
        /**
         * CRUD Operations
         * */
        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        //int id, String name, int phoneNumber,String street , int number, String state
        db.addContact(new Contact(1, "Ravi", 910000000));
        db.addContact(new Contact(2,"Srinivas",919999999));
        db.addContact(new Contact(3,"Tommy", 952222222 ));
        db.addContact(new Contact(4, "Karthik", 953333333));
        //adding adreess
        db.addAdress(new Adreess(1, "Miguel Hidalgo", "85", "GA", 1));
        db.addAdress(new Adreess(2, "Miguel Hidalgo 1", "86", "GA", 2));
        db.addAdress(new Adreess(3, "Miguel Hidalgo 2", "87", "GA", 3));
        db.addAdress(new Adreess(4, "Miguel Hidalgo 3", "88", "GA", 4));

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Contact> contacts = db.getAllContacts();
        int i =0;
        for (Contact cn : contacts) {
            String log = "Id: " + cn.getId() + " ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
            contactos.add("Name: " + log);
            i++;
            // Writing Contacts to log
            Log.d("Name: ", log);
        }

        // getting the RecycleView
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        //using the layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //adapter
        mAdapter = new MyAdapter(contacts);

        String example="This a comment";
        Log.d("TAG", example);

        mRecyclerView.setAdapter(mAdapter);







        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

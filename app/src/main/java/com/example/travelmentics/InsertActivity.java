package com.example.travelmentics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertActivity extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private EditText mTitle,mPrice,mDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        mFirebaseDatabase=FirebaseDatabase.getInstance();
        mDatabaseReference=mFirebaseDatabase.getReference().child("traveldeals");
        mTitle=findViewById(R.id.txtTitle);
        mPrice=findViewById(R.id.txtPrice);
        mDescription=findViewById(R.id.txtDescription);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.save_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_menu:
                saveDeal();
                clean();
                Toast.makeText(this,"Deal Saved",Toast.LENGTH_LONG).show();
                return true;
                default:
                    return super.onOptionsItemSelected(item);

        }

    }

    private void clean() {
        mDescription.setText("");
        mTitle.setText("");
        mPrice.setText("");
    }

    private void saveDeal() {
        String title=mTitle.getText().toString();
        String description=mDescription.getText().toString();
        String price=mPrice.getText().toString();
        TravelDeals deal=new TravelDeals(title,price,description);
        mDatabaseReference.push().setValue(deal);


    }
}


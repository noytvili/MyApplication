package com.example.doriyaspielman.myapplication;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StoreScreenManager extends AppCompatActivity {
    ListView lst;
    private EditText product_id;
    private DatabaseReference db;

//    private void displayProductList(){
//        StoreScreen.arr_p.add(new Product("001","red shoes","140","2",R.drawable.red_adidas));
//        StoreScreen.arr_p.add(new Product("002","black shoes","120","2",R.drawable.black_shoes));
//        StoreScreen.arr_p.add(new Product("003","black coat","150","2",R.drawable.black_coat));
//        StoreScreen.arr_p.add(new Product("004","casual dress","150","2",R.drawable.casual_dress));
//        StoreScreen.arr_p.add(new Product("005","evening dress","170","2",R.drawable.evening_dress));
//        StoreScreen.arr_p.add(new Product("006","pink shirt","100","2",R.drawable.pink_shirt));
//        StoreScreen.arr_p.add(new Product("007","white shirt","80","2",R.drawable.white_shirt));
//        StoreScreen.arr_p.add(new Product("008","wedding dress","300","2",R.drawable.wedding_dress));
//    }
//    String[] productName = {"red shoes", "black shoes", "black coat", "casual dress", "evening dress", "pink shirt", "white shirt", "wedding dress"};
//    String[] price = {"140", "120", "150", "90" , "170", "100", "80", "300"};
//    Integer[] pic_id={R.drawable.red_adidas, R.drawable.black_shoes, R.drawable.black_coat, R.drawable.casual_dress, R.drawable.evening_dress, R.drawable.pink_shirt, R.drawable.white_shirt,R.drawable.wedding_dress };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final ArrayList<Product> arr_p = new ArrayList();
        super.onCreate(savedInstanceState);
        db = FirebaseDatabase.getInstance().getReference();
        setContentView(R.layout.activity__store_manager);
        lst = (ListView) findViewById(R.id.listviewmanager);
        final Manager_listview manager_listview = new Manager_listview(this, arr_p);
        db.child("products").addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d("", "BLA1");

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    Log.d("", "BLA2");

                    Product product = userSnapshot.getValue(Product.class);
                    Log.d(product.getName(), "BLA");
                    arr_p.add(new Product(product.getName(),product.getPrice(),product.getPicture()));
                    manager_listview.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildAdded (@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
        lst.setAdapter(manager_listview);
    }
    public void OnClickPlus (View v){
        Intent i = new Intent(this, AddManager.class);
        startActivity(i);
    }

    public void OnClickMinos (View v){
        Intent i = new Intent(this, RemoveManager.class);
        startActivity(i);
    }
}
package com.example.circlea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProductActivity extends AppCompatActivity {
    String type = " ", addition, size = " ";
    int quantityCounter = 1;
    int productPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        getSupportActionBar().hide();
        Button addToCartBtn = findViewById(R.id.addCartBtn);

        TextView title = findViewById(R.id.title);
        TextView price = findViewById(R.id.price);
        ImageView image = findViewById(R.id.productBackgroundImage);
        Bundle extras=getIntent().getExtras();
        title.setText(extras.getString("productName"));
        Glide.with(this).load(extras.getString("productImage")).into(image);
        price.setText(extras.getString("productprice"));
        RadioButton normal = findViewById(R.id.normalBtn);
        RadioButton spice = findViewById(R.id.spiceBtn);

        Spinner additionSpin = findViewById(R.id.spinner);

        Button smallBtn = findViewById(R.id.smallSize);
        Button midBtn = findViewById(R.id.midSize);
        midBtn.setTextColor(Color.BLACK);
        Button largeBtn = findViewById(R.id.largeSize);

        Button plusBtn = (Button) findViewById(R.id.plusBtn);
        Button minusBtn = (Button) findViewById(R.id.minusBtn);
        TextView quantity = (TextView) findViewById(R.id.quantityCounter);

        final ProductDBHelper productDBHelper = new ProductDBHelper(this);

        spice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "spice";
            }
        });
        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "normal";
            }
        });

        additionSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedOpt = additionSpin.getSelectedItem().toString();
                switch (selectedOpt) {
                    case "Onions":
                        addition = "onion";
                        break;
                    case "Cheese":
                        addition = "cheese";
                        break;
                    case "Peppers":
                        addition = "peppers";
                        break;
                    case "Ketchup":
                        addition = "ketchup";
                        break;
                    case "Mayonnaise":
                        addition = "mayonnaise";
                        break;
                    default:
                        addition = " ";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                addition = "#";
            }
        });

        productPrice = Integer.parseInt(price.getText().toString());
        smallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                size = "small";
                smallBtn.setTextColor(Color.BLACK);
                midBtn.setTextColor(Color.WHITE);
                largeBtn.setTextColor(Color.WHITE);
                midBtn.setBackgroundResource(R.drawable.circle_btn);
                largeBtn.setBackgroundResource(R.drawable.circle_btn);
                int bonus= (int) (productPrice*0.2);
                price.setText(Integer.toString(productPrice - bonus));
            }
        });
        midBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                size = "mid";
                smallBtn.setTextColor(Color.WHITE);
                midBtn.setTextColor(Color.BLACK);
                largeBtn.setTextColor(Color.WHITE);
                smallBtn.setBackgroundResource(R.drawable.circle_btn);
                midBtn.setBackgroundResource(R.drawable.circle_btn2);
                largeBtn.setBackgroundResource(R.drawable.circle_btn);

                price.setText(Integer.toString(productPrice));
            }
        });
        largeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                size = "large";
                smallBtn.setTextColor(Color.WHITE);
                midBtn.setTextColor(Color.WHITE);
                largeBtn.setTextColor(Color.BLACK);
                smallBtn.setBackgroundResource(R.drawable.circle_btn);
                midBtn.setBackgroundResource(R.drawable.circle_btn);
                largeBtn.setBackgroundResource(R.drawable.circle_btn2);
                int bonus= (int) (productPrice*0.25);
                price.setText(Integer.toString(productPrice + bonus));
            }
        });

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantityCounter++;
                quantity.setText(Integer.toString(quantityCounter));
            }
        });
        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantityCounter >= 2) {
                    quantityCounter--;
                    quantity.setText(Integer.toString(quantityCounter));
                }
            }
        });


        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type.equals(" ")) {
                    type = "normal";
                }
                if (size.equals(" ")) {
                    size = "mid";
                }

                String res = title.getText().toString() + ' ' + price.getText().toString() + ' ' + type + ' ' + addition + ' ' + size + ' ' + quantity.getText().toString();
                int priceInt = Integer.parseInt(price.getText().toString());
                int quantityInt = Integer.parseInt(quantity.getText().toString());
                productDBHelper.addNewProduct(title.getText().toString(), priceInt , type, addition, size, quantityInt,User.getEmail2());

                Toast.makeText(getApplicationContext(), "The product added to Cart with detail: " + res, Toast.LENGTH_LONG).show();
            }
        });

        FloatingActionButton cartBtn = findViewById(R.id.cart_floating_btn);
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProductActivity.this, CartActivity.class);
                startActivity(i);
            }
        });
    }
}
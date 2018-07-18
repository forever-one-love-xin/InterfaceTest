package com.example.dell.interfacetest.activitys;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.interfacetest.R;

public class FruitActivity extends AppCompatActivity {
    public static final String FRUIT_NAME = "fruit_name";
    public static final String FRUIT_IMAGE_ID = "fruit_image_id";
    private static final String url = "http://i1.hdslb.com/bfs/archive/94f3176d682dd5bfcf2587b69f193e034804b41f.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.fruit);

        Intent intent = getIntent ();
        String fruitName = intent.getStringExtra (FRUIT_NAME);
        int fruitImageId = intent.getIntExtra (FRUIT_IMAGE_ID, 0);
        String fruiturl = intent.getStringExtra (FRUIT_IMAGE_ID);

        Toolbar toolbar = findViewById (R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById (R.id.collapsing_toolbar);
        ImageView fruitImage = findViewById (R.id.fruit_image_view);
        TextView fruitContentText = findViewById (R.id.fruit_content_text);

        setSupportActionBar (toolbar);
        ActionBar actionBar = getSupportActionBar ();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled (true);
        }

        collapsingToolbarLayout.setTitle (fruitName);
        Glide.with (this).load (fruiturl).into (fruitImage);

        String fruitContent = generateFruitContent (fruitName);
        fruitContentText.setText (fruitContent);
    }

    private String generateFruitContent(String fruitName) {
        StringBuilder fruitContent = new StringBuilder ();
        for (int i = 0; i < 500; i++) {
            fruitContent.append (fruitName);
        }

        return fruitContent.toString ();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId ()) {
            case android.R.id.home:
                finish ();
                return true;
        }

        return super.onOptionsItemSelected (item);
    }
}

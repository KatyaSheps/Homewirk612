package ru.sheps.android.homewirk612;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String SAVED_KEY = "text_for_save";
    TextView textView;
    private String savedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Lifecycle", "onCreate");
        textView = findViewById(R.id.textView);
        if (savedInstanceState == null) {
            textView.setText("\n" + "ОnClick");
        }
//        else {
//           // final String savedText = savedInstanceState.getString(SAVED_KEY);
//            textView.append(savedText + "\n" + "ОnClick");
//        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: " + this);
        textView = findViewById(R.id.textView);
        textView.append("\n" + "ОnStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: " + this);
        textView = findViewById(R.id.textView);
        textView.append("\n" + "ОnResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: " + this);
        textView = findViewById(R.id.textView);
        textView.append("\n" + "ОnPaused");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: " + this);
        textView = findViewById(R.id.textView);
        textView.append("\n" + "ОnStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: " + this);
        textView = findViewById(R.id.textView);
        textView.append("\n" + "ОnDestroy");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d(TAG, "onKeyDown:" +this);
        TextView textView = findViewById(R.id.textView);
        textView.append("\n" + "onKeyDown");
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        Log.d(TAG, "onKeyLongPress: " + this);
        textView = findViewById(R.id.textView);
        textView.append("\n" + "onKeyLongPress");
        return super.onKeyLongPress(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed: " + this);
        textView = findViewById(R.id.textView);
        textView.append("\n" + "onBackPressed");
        super.onBackPressed();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: " + this);
        textView = findViewById(R.id.textView);
        textView.append("\n" + "onSaveInstanceState");
        String textForSave = textView.getText().toString();
        outState.putString(SAVED_KEY, textForSave);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "onRestoreInstanceState: ");
        super.onRestoreInstanceState(savedInstanceState);
        textView = findViewById(R.id.textView);
        savedText = savedInstanceState.getString(SAVED_KEY);
        textView.append("\n" + "onRestoreInstanceState");
    }
}

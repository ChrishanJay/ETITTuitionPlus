package com.etit.smartpay.ui.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.etit.smartpay.R;
import com.etit.smartpay.adapters.ClassAdapter;
import com.etit.smartpay.model.TuitionClass;
import com.etit.smartpay.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchActivity extends AppCompatActivity {

    List<TuitionClass> classes;
    EditText searchText;
    RecyclerView searchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchList = findViewById(R.id.searchList);
        searchText = findViewById(R.id.searchText);

        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionID, KeyEvent keyEvent) {
                if (actionID == EditorInfo.IME_ACTION_SEARCH) {
                    searchClass(textView.getText().toString());
                    return true;
                }
                return false;
            }
        });
    }

    private void searchClass(String query) {
        List<TuitionClass> filteredClasses = new ArrayList<>();
        classes = getClasses();

        for (TuitionClass tClass: classes) {
            if (tClass.getClassName().toLowerCase().contains(query.toLowerCase())) {
                filteredClasses.add(tClass);
            }
        }

        ClassAdapter adapter = new ClassAdapter(filteredClasses);
        searchList.setAdapter(adapter);
        searchList.setLayoutManager(new LinearLayoutManager(this));
    }

    private List<TuitionClass> getClasses() {
        String jsonFileString = Utils.getJsonFromAssets(getApplicationContext(), "classes.json");
        Log.i("ETIT", jsonFileString);
        Gson gson = new Gson();
        Type listUserType = new TypeToken<List<TuitionClass>>() { }.getType();
        List<TuitionClass> classes = gson.fromJson(jsonFileString, listUserType);
        return classes;
    }
}
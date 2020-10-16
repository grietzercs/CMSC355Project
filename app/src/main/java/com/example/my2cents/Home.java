package com.example.my2cents;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {

    ViewPager viewPager;
    HomeAdapter adapter;
    List<HomeModel> models;
    TabLayout tabLayout;
    FloatingActionButton fab1;
    Context context;
    Spinner typeSpinner;
    Spinner cateSpinner;
    EditText amount;
    Button save;
    Button cancel;


    public Home() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //Set info to display for card view
        setModels();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container,false);

        //Create and set adapter for pager
        adapter = new HomeAdapter(models, this.getContext());
        viewPager = v.findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        //Connect dots indicator to pager
        tabLayout = v.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager, true);

            //dhruv

        amount = v.findViewById(R.id.amountEt);
        save = v.findViewById(R.id.saveBtn);

        fab1 = v.findViewById(R.id.floatingActionButton1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               showDialog();

            }

            });

                return v;
    }


    public void setModels(){

        models = new ArrayList<>();

        //Add temporary strings
        models.add(new HomeModel("Month Summary",
                "Starting Balance", "Total Expenses", "Upcoming Deductions",
                "$000.00", "$000.00", "3",
                null, null, null));

        models.add(new HomeModel("Recent Expenses",
                "Title - Category", "Title - Category", "Title - Category",
                "$000.00","$000.00","$000.00",
                "MM/DD/YYYY","MM/DD/YYYY","MM/DD/YYYY"));

        models.add(new HomeModel("Upcoming Deductions",
                "Title - Category", "Title - Category", "Title - Category",
                "$000.00","$000.00","$000.00",
                "MM/DD/YYYY","MM/DD/YYYY","MM/DD/YYYY"));
    }

    // alert dialog that shows when floating action button is clicked
    public void showDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        LayoutInflater factory = LayoutInflater.from(getActivity());
        View view2 = factory.inflate(R.layout.dialog_box, null);
        alertDialog.setView(view2);

        final AlertDialog builder = alertDialog.create();

        //cancel button to close the alert dialog
        cancel = view2.findViewById(R.id.cancelBtn);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.cancel();
            }
        });

        builder.show();

        // spinner for input output
        typeSpinner = view2.findViewById(R.id.typeSpinner);
        List<String> typeOfInput = new ArrayList<String>();
        typeOfInput.add("Expense");
        typeOfInput.add("Income");
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, typeOfInput);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);

        //spinner for categories
        cateSpinner = view2.findViewById(R.id.categorySpinner);
        List<String> typeOfCategories = new ArrayList<>();
        typeOfCategories.add("Bills");
        typeOfCategories.add("Food");
        typeOfCategories.add("Gas");
        typeOfCategories.add("Entertainment");
        typeOfCategories.add("Pay Check");
        typeOfCategories.add("Savings");
        typeOfCategories.add("Miscellaneous");
        typeOfCategories.add("Lottery");
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, typeOfCategories);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cateSpinner.setAdapter(categoryAdapter);

    }


}
package com.powerproject.main;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.powerproject.login.R;

import java.util.ArrayList;

import static com.powerproject.splashActivity.project;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectFragment extends Fragment {
    ListView lvProject;
    public static String nameproject = "";

    ArrayAdapter<String> adapter;

    public ProjectFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_project, container, false);
        lvProject = (ListView)rootView .findViewById(R.id.lvProject);



        //EDITED Code
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, project);

        lvProject.setAdapter(adapter);
        lvProject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3)
            {
                String data=(String)arg0.getItemAtPosition(arg2);
                Toast.makeText(getContext(), data+" is selected", Toast.LENGTH_SHORT).show();


            }});

        lvProject.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View v,
                                           int index, long arg3) {
                // TODO Auto-generated method stub
                deleteProject(v, index);
                return true;
            }
        });
        //To have custom list view use this : you must define CustomeAdapter class
        //listview.setadapter(new CustomeAdapter(getActivity()));
        //getActivty is used instead of Context
        return rootView;
    }

    public void deleteProject(View view, int index){
        AlertDialog.Builder adb=new AlertDialog.Builder(view.getContext());
        adb.setTitle("Delete?");
        adb.setMessage("Are you sure you want to delete this project?");
        final int positionToRemove = index;
        adb.setNegativeButton("Cancel", null);
        adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                project.remove(positionToRemove);
                adapter.notifyDataSetChanged();
            }});
        adb.show();
    }


}

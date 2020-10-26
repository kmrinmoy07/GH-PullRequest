package com.myapp.ghpullrequest;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class EnterDetailsFragment extends Fragment {


    EditText editTextOwner, editTextRepo;
    TextView textViewSubmit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_enter_details, container, false);

        editTextOwner=view.findViewById(R.id.editOwnerName);
        editTextRepo=view.findViewById(R.id.editRepoName);
        textViewSubmit=view.findViewById(R.id.textViewSubmit);

        textViewSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!editTextOwner.getText().toString().equals("") && !editTextRepo.getText().toString().equals("")){


                    if(JsonUtils.isNetworkAvailable(getActivity())){
                        new FetchData().execute("https://api.github.com/repos/"
                                +editTextOwner.getText().toString()
                                +"/"+editTextRepo.getText().toString()+"/pulls");


                    }
                    else {
                        Toast.makeText(getActivity(), "Network Not available", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getActivity(), "Please enter all details", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }



    private class FetchData extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {

            Toast.makeText(getActivity(), "Please wait loading", Toast.LENGTH_SHORT).show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {


            try {
                String json = JsonUtils.getJSONString(strings[0]);
                JSONArray jsonArray = new JSONArray(json);
                JSONObject objJson = null;

                for (int i = 0; i < jsonArray.length(); i++) {
                    objJson = jsonArray.getJSONObject(i);

                    String number = objJson.getString("id");
                    String status= objJson.getString("state");
                    String created = objJson.getString("created_at");
                    String title = objJson.getString("title");

                    Items objItem = new Items(number, status, created, title);
                    Constant.arrayList.add(objItem);

                }


                return "1";
            } catch (Exception e) {
                e.printStackTrace();
                return "0";
            }

        }

        @Override
        protected void onPostExecute(String s) {

            if (getActivity() != null) {
                if (s.equals("1")) {


                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    FetchDetailsFragment fetchDetailsFragment = new FetchDetailsFragment();
                    ft.replace(R.id.Fragment, fetchDetailsFragment , "FetchDetailsFragment");
                    ft.addToBackStack("FetchDetailsFragment");
                    ft.commit();

                } else {
                        Toast.makeText(getActivity(), getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                }

                super.onPostExecute(s);
            }

        }

    }
}
package com.example.seproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class ProfileEditFragment extends Fragment {

    String[] job_items = {"-", "고등학생", "대학생", "직장인", "기타"};
    String[] local_items = {"-", "서울특별시", "부산광역시", "대구광역시", "인천광역시", "광주광역시", "대전광역시", "울산광역시",
            "세종특별자치시", "경기도", "강원도", "충청북도", "충청남도", "전라북도", "전라남도", "경상북도", "경상남도", "제주도", "기타"};

    public ProfileEditFragment(){}

    public static ProfileEditFragment newInstance(){
        return new ProfileEditFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.profile_edit, container, false);



        Spinner job_spinner = (Spinner) view.findViewById(R.id.job_spinner);

        ArrayAdapter<String> job_adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,job_items);
        job_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        job_spinner.setAdapter(job_adapter);
        job_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getActivity(),Integer.toString(position),Toast.LENGTH_SHORT); //본인이 원하는 작업.
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner local_spinner = (Spinner) view.findViewById(R.id.local_spinner);

        ArrayAdapter<String> local_adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,local_items);
        local_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        local_spinner.setAdapter(local_adapter);
        local_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getActivity(),Integer.toString(position),Toast.LENGTH_SHORT); //본인이 원하는 작업.
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button edit_btn = (Button) view.findViewById(R.id.edit_btn);
        edit_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ((MainActivity)getActivity()).replaceFragment(ProfileFragment.newInstance());
            }
        });

        return view;
    }
}
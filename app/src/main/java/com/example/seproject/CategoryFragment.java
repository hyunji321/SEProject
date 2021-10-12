package com.example.seproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class CategoryFragment extends Fragment {
    private ListView listView;
    private Post_ListItemAdapter adapter;
    public static String category_str = "";



    public static CategoryFragment newInstance(){
        return new CategoryFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.category, container, false);

        Bundle bundle = getArguments();
        String category ;

        //로그인 이후에 제대로 아이디값 받아오는지 확인하는 용도
        if (bundle != null){
            TextView category_name = (TextView) v.findViewById(R.id.category_name);
            category = bundle.getString("category");
            category_name.setText(category);
            category_str = category;
        }
        else{
            TextView category_name = (TextView) v.findViewById(R.id.category_name);
            category_name.setText("null받음");
        }

        listView = (ListView) v.findViewById(R.id.listView);
        adapter = new Post_ListItemAdapter();
        adapter.addItem(new Post_ListItem("writer", "[ㅇㅇ공모전] 같이 하실 분", "(1/4)", "D-16"));
        adapter.addItem(new Post_ListItem("writer", "[ㄴㄴ공모전] 같이 하실 분", "(2/4)", "D-16"));
        adapter.addItem(new Post_ListItem("writer", "[ㅁㅁ공모전] 같이 하실 분", "(3/4)", "D-16"));
        adapter.addItem(new Post_ListItem("writer", "[ㅅㅅ공모전] 같이 하실 분", "(4/4)", "D-16"));
        adapter.addItem(new Post_ListItem("writer", "[ㄹㄹ공모전] 같이 하실 분", "(1/5)", "D-16"));

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Post_ListItem item = (Post_ListItem) adapter.getItem(position);
                Toast.makeText(getActivity(), item.getTitle(), Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).replaceFragment(PostDetailFragment.newInstance());
            }
        });

        ImageButton write_btn = (ImageButton) v.findViewById(R.id.trash_btn);
        write_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ((MainActivity)getActivity()).replaceFragment(PostWriteFragment.newInstance());
            }
        });

        return v;
    }
}

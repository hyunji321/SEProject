package com.example.seproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SendMsgFragment extends Fragment {
    public SendMsgFragment() {
    }

    EditText write_msg_insert_et;
    Button send_btn;
String content;
String sender;
String receiver;

    public static SendMsgFragment newInstance() {
        return new SendMsgFragment();
    }
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.send_message, container, false);

        write_msg_insert_et = (EditText) v.findViewById(R.id.write_msg_insert_et);
        send_btn = (Button) v.findViewById(R.id.send_btn);
        if(SearchUserFragment.search_id != null) //만약 search해서 들어올경우
        receiver = SearchUserFragment.search_id;
//프로필보기에서 메세지보내기를 눌렀을때 들어올 변수도 검증해줘야겠지..
        sender = MainActivity.userID;



        send_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //DB에 update
                content = write_msg_insert_et.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                           if (success) {

                               Toast.makeText(getActivity(),"메세지 전송에 성공하였습니다.",Toast.LENGTH_SHORT).show();
                               // ((MainActivity)getActivity()).replaceFragment(SearchUserFragment.newInstance()); //화면전환
                            }

                            else {
                               Toast.makeText(getActivity(),"메세지 전송에 실패하였습니다.",Toast.LENGTH_SHORT).show();
                                return;
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                //서버로 Volley를 이용해서 요청
                SendMsgRequest sendMsgRequest = new SendMsgRequest(sender, receiver, content, responseListener);
                RequestQueue queue = Volley.newRequestQueue(SendMsgFragment.this.getActivity());
                queue.add(sendMsgRequest);



            }
        });


        return v;
    }
}
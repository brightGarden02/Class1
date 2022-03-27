// 회원가입 처리
package com.brightGarden02.class1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private EditText join_email, join_password, join_name, join_pwck;
    private Button check_button, join_button;
    private AlertDialog dialog;
    private boolean validate = false;

    @Override
    protected  void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        // id값 찾아서 EditText data에 assign하기
        join_email = findViewById(R.id.join_email);
        join_password = findViewById(R.id.join_password);
        join_name = findViewById(R.id.join_name);
        join_pwck = findViewById(R.id.join_pwck);

        // id 중복 체크
        check_button = findViewById(R.id.check_button);
        check_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                String UserEmail = join_email.getText().toString();
                if(validate){
                    return; // id 중복 확인
                }

                if(UserEmail.equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("아이디를 입력하세요.").setPositiveButton("확인", null).create();
                    dialog.show();
                    return;
                }

                Response.Listener<String> respoonseListener = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response){
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("sucess");

                            if(success){
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("사용할 수 있는 아이디입니다.").setPositiveButton("확인", null).create();
                                dialog.show();
                                join_email.setEnabled(false); // 아이디값 고정
                                validate = true; // 검증 완료
                                check_button.setBackgroundColor(getResources().getColor(R.color.colorGray));

                            }
                            else{

                            }

                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }

                    }

                };


            }



        });


    }


}

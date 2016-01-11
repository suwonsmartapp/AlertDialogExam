package com.massivcode.alertdialogexam;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mPopUpButton1,mPopUpButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPopUpButton1 = (Button) findViewById(R.id.btn_popup1);
        mPopUpButton1.setOnClickListener(this);

        mPopUpButton2 = (Button) findViewById(R.id.btn_popup2);
        mPopUpButton2.setOnClickListener(this);
    }

    private void popUpAlertDialog() {
        // AlertDialog 를 쉽게 만들도록 도와주는 AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // AlertDialog 의 제목을 설정
        builder.setTitle("타이틀");
        // AlertDialog 의 메세지를 설정
        builder.setMessage("메세지");
        // 팝업된 AlertDialog 가 다른 곳을 클릭했을 때 닫히지 않도록 설정하는 메소드
        builder.setCancelable(false);

        // NegativeButton 을 설정
        // 버튼을 클릭했을 때 별다른 작업을 하지 않을 때는 리스너에 null 세팅
        builder.setNegativeButton("Negative", null);

        // NeutralButton 을 설정
        // 버튼을 클릭했을 때 리스너를 세팅하고, 중립 버튼이 눌렸어요를 출력해볼게요.
        builder.setNeutralButton("Neutral", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "중립 버튼이 눌렸어요.", Toast.LENGTH_SHORT).show();
            }
        });

        // PositiveButton 을 설정
        // AlertDialog 에선 기본적으로 세가지 버튼을 지원합니다 : Negative, Neutral, Positive
        builder.setPositiveButton("Positive", null);

        // 위의 저러한 세팅값이 적용된 다이얼로그 객체를 생성
        AlertDialog dialog = builder.create();

        // 그렇게 생성된 다이얼로그 객체를 화면에 출력합니다
        dialog.show();

        // 아래처럼 1줄로 줄일 수도 있습니다.
//        builder.show();
    }

    private void popUpAlertDiaiogWithCustomView() {
        // AlertDialog 를 쉽게 만들도록 도와주는 AlertDialog.Builder
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // CustomView 를 세팅하여 사용되는 경우 Inflater 를 이용하여 View 를 inflate 해야한다.
        View innerView = getLayoutInflater().inflate(R.layout.layout_dialog, null);
        // inflate 한 CustomView 를 세팅하는 메소드
        builder.setView(innerView);
        // 팝업된 AlertDialog 가 다른 곳을 클릭했을 때 닫히지 않도록 설정하는 메소드
        builder.setCancelable(false);

        /**
         * CustomView 에 위치한 View 들을 객체화하는 부분
         */
        final EditText editText1 = (EditText) innerView.findViewById(R.id.et_1);
        final EditText editText2 = (EditText) innerView.findViewById(R.id.et_2);
        Button closeButton = (Button) innerView.findViewById(R.id.btn_close);
        Button loginButton = (Button) innerView.findViewById(R.id.btn_login);


        // 다이얼로그 빌더에 세팅한 여러가지 값이 적용된 AlertDialog 객체를 만드는 부분
        final AlertDialog dialog = builder.create();
        // 그렇게 만들어진 AlertDialog 객체를 화면에 출력하는 부분
        dialog.show();

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "닫기 버튼이 눌렸습니다!", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editText1.getText().toString();
                String pw = editText2.getText().toString();
                if("admin".equals(id) && "admin".equals(pw)) {
                    Toast.makeText(MainActivity.this, "로그인에 성공하셨습니다!", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                } else {
                    Toast.makeText(MainActivity.this, "아이디 또는 비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_popup1:
                popUpAlertDialog();
                break;
            case R.id.btn_popup2:
                popUpAlertDiaiogWithCustomView();
                break;
        }
    }
}

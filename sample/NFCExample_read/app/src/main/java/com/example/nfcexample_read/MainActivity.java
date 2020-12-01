package com.example.nfcexample_read;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.tech.NfcF;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

/*
*  NfcAdapter : 안드로이드 단말기의 NFC 정보를 가져오는 변수타입,
    PendingIntent : NFC로 전송 받은 데이터를 Intent를 이용해서 다른 액티비티로 넘겨주는 역할을 한다.

*
*
    1.    NfcAdapter 변수를 이용하여 NFC 정보 가져오기
    2.    Intent 설정하기
    3.    Intent 값을 PendingIntent 변수를 이용하여 옮겨 넣기
    4.    IntenFilter 변수를 이용하여 Intent 값을 필터링 하기
    5.    String 변수를 이용하여 techlist 설정
    6.    액티비티 순환주기 중 onResume() 에서 전송 메소드 실행
    7.    액티비티 순환주기 중 onPause() 에서 종료 메소드 실행
    8.    NFC 정보들 중 텍스트 정보 받아들여 화면에 뿌리기
*
*
*
*
*
* */

public class MainActivity extends AppCompatActivity {

    private NfcAdapter nfcAdapter;
    private PendingIntent pendingIntent;
    private IntentFilter[] mlntentFilters;
    private String[][] mNFCTechLists;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView)findViewById(R.id.text); //위젯 선언
        /*
         * 1. 단말기의 NFC 상태를 점검
         */

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if(nfcAdapter == null){
            text.setText("NFC가 꺼짐");
        } else {
            text.setText("NFC가 켜짐");
        }

        Intent intent = new Intent(this, second.class); //다른액티비티에서 처리할 경우에 이렇게 지정한다.

//        Intent intent = new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP); // 현재액티비티에서 처리

        pendingIntent = PendingIntent.getActivity(this,0,intent,0);


        /*
         *  3. IntentFilter 지정
         */

        IntentFilter ndefIntent = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);

        try {
            ndefIntent.addDataType("*/*");
        } catch (Exception e) {
            Log.e("TagDispatch",e.toString());
        }

        mlntentFilters = new IntentFilter [] {ndefIntent,};

        /*
         *  4. NFCtech 지정
         */

        mNFCTechLists = new String[][] { new String[]{ NfcF.class.getName()}};

    }


    @Override
    protected void onResume() {
        super.onResume();
        /*
         * NFC 태그가 사용 가능할 경우(nfcAdapter가 null 이 아닐경우)에 작동
         */
        if (nfcAdapter != null) {
            nfcAdapter.enableForegroundDispatch(this, pendingIntent, mlntentFilters, mNFCTechLists);
        }
    }

    /*
     * 6. onPause() 지정
     * 화면에 액티비티가 보이지 않을때 실행될 내용을 기술하는 메소드이다..
     * NFC 신호 대기를 중지한다.
     * 중지 후 finish() 명령어를 통해 이 액티비티를 종료한다.
     */
    @Override
    protected void onPause() { //화면이 중지될 때 사용안하기 위해
        //onPause() 에서 .disableForegroundDispatch(this); 사용
        super.onPause();
        if (nfcAdapter != null) {
            nfcAdapter.disableForegroundDispatch(this); //수신
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}

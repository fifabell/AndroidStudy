package com.example.nfcexample_write;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.base.Charsets;
import com.google.common.primitives.Bytes;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Locale;

/*
*     NfcAdapter 는 안드로이드 단말기의 NFC 정보를 가져오는 변수타입이고
        NdefMessage 는 NFC로 전송할 데이터를 저장하는 변수이다.
*
*       1. NfcAdapter 변수를 이용하여 NFC 정보 가져오기
        2. 텍스트 데이터를 NdefMessage 변수에 담을 수 있도록 가공하는 메소드 작성
        3. 가공된 데이터를 NdefMessage 변수에 저장
        4. onResume() 메소드에서 NdefMessage 변수를 전송
        5. onPause() 메소드에서 NFC 통신 종료
*
*
*
*
*
* */

public class NFC_WAIT extends Activity {
    /*
     * 위젯 관련 변수
    */private NfcAdapter nfcAdapter;
    private PendingIntent pendingIntent;

    public static final int TYPE_TEXT = 1;
    public static final int TYPE_URI = 2;

    EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (EditText) findViewById(R.id.text);

        // NFC 관련 객체 생성
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        Intent intent = new Intent(this, getClass())
                .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
    }

    /***************************************************************
     * 여기서부턴 NFC 관련 메소드
     * NFC 단말에 태그가 인식되면, Intent를 통해서 Activity로 전달됩니다.
     * Activity가 이 Intent를 받기 위해서는 NfcAdapter 클래스의
     * enableForegroundDispatch(..) 를 이용합니다.
     **************************************************************/
    @Override
    protected void onPause() {
        if (nfcAdapter != null) {
            nfcAdapter.disableForegroundDispatch(this);
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (nfcAdapter != null) {
            nfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null);
        }
    }

    /*********************************************
     *  NFC 태그 스캔시 자동 호출되는 메소드
     *  ******************************************/
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (intent != null) {
            processTag(intent); // processTag 메소드 호출
        }
    }

    /******************************************************************
     * EditText에 입력된 값을 NFC tag에 기록하는
     * 메소드
     * @param intent - 데이터를 기록할 감지된 NFC tag를 가지고 있는 intent
     ******************************************************************/
    private void processTag(Intent intent) {
        // EditText에 입력된 값을 가져옴
        String s = text.getText().toString();

        // 감지된 태그를 가리키는 객체
        Tag detectedTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

        // 아무것도 입력받지 않으면 태그에 쓰지 않음
        if (s.equals("")) {
            Toast.makeText(getApplicationContext(), "내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
        }

        // 입력받은 값을 감지된 태그에 씀
        else {
            NdefMessage message = createTagMessage(s, TYPE_TEXT);
            writeTag(message, detectedTag);
        }
    }

    // 감지된 태그에 NdefMessage를 쓰는 메소드
    public boolean writeTag(NdefMessage message, Tag tag) {
        int size = message.toByteArray().length;
        try {
            Ndef ndef = Ndef.get(tag);
            if (ndef != null) {
                ndef.connect();
                if (!ndef.isWritable()) {
                    return false;
                }

                if (ndef.getMaxSize() < size) {
                    return false;
                }

                ndef.writeNdefMessage(message);
                Toast.makeText(getApplicationContext(), "쓰기 성공!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "포맷되지 않은 태그이므로 먼저 포맷하고 데이터를 씁니다.",
                        Toast.LENGTH_SHORT).show();

                NdefFormatable formatable = NdefFormatable.get(tag);
                if (formatable != null) {
                    try {
                        formatable.connect();
                        formatable.format(message);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }

        return true;
    }

    /***********************
     * NdefMessage를 생성
     *
     * @param msg
     * @param type
     * @return
     ***********************/
    private NdefMessage createTagMessage(String msg, int type) {
        NdefRecord[] records = new NdefRecord[1];

        if (type == TYPE_TEXT) {
            records[0] = createTextRecord(msg, Locale.KOREAN, true);
        } else if (type == TYPE_URI) {
            records[0] = createUriRecord(msg.getBytes());
        }

        NdefMessage mMessage = new NdefMessage(records);

        return mMessage;
    }

    private NdefRecord createTextRecord(String text, Locale locale,
                                        boolean encodeInUtf8) {
        final byte[] langBytes = locale.getLanguage().getBytes(Charsets.US_ASCII);
        final Charset utfEncoding = encodeInUtf8 ? Charsets.UTF_8 : Charset
                .forName("UTF-16");
        final byte[] textBytes = text.getBytes(utfEncoding);
        final int utfBit = encodeInUtf8 ? 0 : (1 << 7);
        final char status = (char) (utfBit + langBytes.length);
        final byte[] data = Bytes.concat(new byte[] { (byte) status }, langBytes,
                textBytes);
        return new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT,
                new byte[0], data);
    }

    private NdefRecord createUriRecord(byte[] data) {
        return new NdefRecord(NdefRecord.TNF_ABSOLUTE_URI, NdefRecord.RTD_URI,
                new byte[0], data);
    }




}

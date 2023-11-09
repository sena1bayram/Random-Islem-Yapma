package com.example.randomlemvesoru;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ToggleButton toggleTopla,toggleFark,toggleBol,toggleCarp;
    private TextView txtSoru,txtMessage;
    private EditText editTxtTahmin;
    private ArrayList<String> islemTurleri;
    private Random rndIslem,rndSayi;
    private int rndIslemNumber,rndSayiNumber;
    private String soru,txtTahmin;
    private int s1,s2,sonuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        islemTurleri=new ArrayList<>();
        rndIslem=new Random();
        rndSayi=new Random();

        toggleTopla=(ToggleButton) findViewById(R.id.toggleBtnTopla);
        toggleFark=(ToggleButton) findViewById(R.id.toggleBtnFark);
        toggleBol=(ToggleButton) findViewById(R.id.toggleBtnBol);
        toggleCarp=(ToggleButton) findViewById(R.id.toggleBtnCarp);

        txtSoru=(TextView) findViewById(R.id.txtSoru);
        txtMessage=(TextView) findViewById(R.id.txtMessage);

        editTxtTahmin=(EditText) findViewById(R.id.editTxtTahmin);

        toggleTopla.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    islemTurleri.add("+");
                }
                else {
                    islemTurleri.remove("+");
                }
            }
        });
        toggleFark.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    islemTurleri.add("-");
                }
                else {
                    islemTurleri.remove("-");
                }
            }
        });
        toggleBol.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    islemTurleri.add("/");
                }
                else {
                    islemTurleri.remove("/");
                }
            }
        });
        toggleCarp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    islemTurleri.add("*");
                }
                else {
                    islemTurleri.remove("*");
                }
            }
        });

    }
    public void btnClick(View v){

        switch (v.getId()){
            case R.id.btnSoruGetir:
                txtSoru.setText(islemTurunuVeSoruyuBelirle());
                break;
            case R.id.btnTahmindeBulun:
                tahminControl();
                break;
        }
    }
    private void tahminControl(){
        txtMessage.setVisibility(View.VISIBLE);
        txtTahmin=editTxtTahmin.getText().toString();
        if(!TextUtils.isEmpty(txtTahmin)){
            if(txtTahmin.matches(String.valueOf(sonuc))){
                txtMessage.setText("TEBRİKLER! Doğru Tahminde Bulundunuz");
                editTxtTahmin.setText("");
                txtSoru.setText(islemTurunuVeSoruyuBelirle());
            }
            else {
                txtMessage.setText("Yanlış Tahminde Bulundunuz");
            }
        }else {
            txtMessage.setText("Tahmin Değeri Boş Olamaz");
        }
    }

    private String islemTurunuVeSoruyuBelirle(){
        soru="";
        if(islemTurleri.size()>0) {
            rndIslemNumber = rndIslem.nextInt(islemTurleri.size());

            s1=randomSayiGetir();
            soru += (s1);
            soru += " ";

            soru += islemTurleri.get(rndIslemNumber);

            soru += " ";
            s2=randomSayiGetir();
            soru += (s2);

            switch (islemTurleri.get(rndIslemNumber)){
                case "+":
                    sonuc=s1+s2;
                    break;
                case "-":
                    sonuc=s1-s2;
                    break;
                case "/":
                    sonuc=s1/s2;
                    break;
                case "*":
                    sonuc=s1*s2;
                    break;
            }
        }
        return soru;
    }
    private int randomSayiGetir(){
        rndSayiNumber=rndSayi.nextInt(10)+1;
        return rndSayiNumber;
    }
}
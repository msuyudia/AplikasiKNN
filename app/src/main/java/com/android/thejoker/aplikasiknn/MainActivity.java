package com.android.thejoker.aplikasiknn;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    EditText et_x1, et_x2, et_x3;
    Button btn_pre;
    TextView tv_pre, tv_x1, tv_x2, tv_x3, tv_y;
    TableLayout tbl_pre;
    ProgressBar pb;
    int k1, k2, k3, maxValue = 0, maxCount = 0;
    String prediksi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_x1 = findViewById(R.id.et_x1);
        et_x2 = findViewById(R.id.et_x2);
        et_x3 = findViewById(R.id.et_x3);
        btn_pre = findViewById(R.id.btn_prediksi);
        pb = findViewById(R.id.pb);
        tv_pre = findViewById(R.id.tv_prediksi);
        tbl_pre = findViewById(R.id.tbl_pre);
        tv_x1 = findViewById(R.id.tv_x1);
        tv_x2 = findViewById(R.id.tv_x2);
        tv_x3 = findViewById(R.id.tv_x3);
        tv_y = findViewById(R.id.tv_y);

        btn_pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //mengambil inputan data untuk tiap variabel
                int x1 = Integer.parseInt(et_x1.getText().toString());
                int x2 = Integer.parseInt(et_x2.getText().toString());
                int x3 = Integer.parseInt(et_x3.getText().toString());

                //menghitung euclidean tiap data
                int data_1 = (2 - x1) * (2 - x1) + (3 - x2) * (3 - x2) + (1 - x3) * (1 - x3);
                int data_2 = (3 - x1) * (3 - x1) + (2 - x2) * (2 - x2) + (1 - x3) * (1 - x3);
                int data_3 = (3 - x1) * (3 - x1) + (2 - x2) * (2 - x2) + (2 - x3) * (2 - x3);
                int data_4 = (3 - x1) * (3 - x1) + (1 - x2) * (1 - x2) + (2 - x3) * (2 - x3);
                int data_5 = (1 - x1) * (1 - x1) + (1 - x2) * (1 - x2) + (3 - x3) * (3 - x3);
                int data_6 = (1 - x1) * (1 - x1) + (2 - x2) * (2 - x2) + (1 - x3) * (1 - x3);
                int data_7 = (3 - x1) * (3 - x1) + (3 - x2) * (3 - x2) + (2 - x3) * (2 - x3);

                //membuat array euclidean
                int[] euclidean = {data_1, data_2, data_3, data_4, data_5, data_6, data_7};

                //mengurutkan isi array dari data euclidean
                Arrays.sort(euclidean);

                //membuat objek nilai terkecil pertama
                int min1 = euclidean[0];

                //membuat objek nilai terkecil kedua
                int min2 = euclidean[1];

                //membuat objek nilai terkecil ketiga
                int min3 = euclidean[2];

                //menentukan kelas dari nilai terkecil pertama
                //catatan : 1 sebagai A, dan 2 sebagai B
                if (min1 == data_1) {
                    k1 = 1;
                } else if (min1 == data_2) {
                    k1 = 1;
                } else if (min1 == data_3) {
                    k1 = 2;
                } else if (min1 == data_4) {
                    k1 = 2;
                } else if (min1 == data_5) {
                    k1 = 1;
                } else if (min1 == data_6) {
                    k1 = 1;
                } else if (min1 == data_7) {
                    k1 = 2;
                }

                //menentukan kelas dari nilai terkecil kedua
                if (min2 == data_1) {
                    k2 = 1;
                } else if (min2 == data_2) {
                    k2 = 1;
                } else if (min2 == data_3) {
                    k2 = 2;
                } else if (min2 == data_4) {
                    k2 = 2;
                } else if (min2 == data_5) {
                    k2 = 2;
                } else if (min2 == data_6) {
                    k2 = 1;
                } else if (min2 == data_7) {
                    k2 = 2;
                }

                //menentukan kelas dari nilai terkecil ketiga
                if (min3 == data_1) {
                    k3 = 1;
                } else if (min3 == data_2) {
                    k3 = 1;
                } else if (min3 == data_3) {
                    k3 = 2;
                } else if (min3 == data_4) {
                    k3 = 2;
                } else if (min3 == data_5) {
                    k3 = 1;
                } else if (min3 == data_6) {
                    k3 = 1;
                } else if (min3 == data_7) {
                    k3 = 2;
                }

                //membuat array untuk kelas dari 3 nilai terkecil
                int[] Y = {k1, k2, k3};

                //mencari modus/angka yang sering muncul di array Y
                for (int i = 0; i < Y.length; ++i) {
                    int count = 0;
                    for (int j = 0; j < Y.length; ++j) {
                        if (Y[j] == Y[i]) ++count;
                    }
                    if (count > maxCount) {
                        maxCount = count;
                        maxValue = Y[i];
                    }
                }

                //setelah menemukan modus, angka tersebut diubah menjadi A jika modusnya 1
                //dan diubah menjadi B jika modusnya 2
                if (maxValue == 1) {
                    prediksi = "A";
                } else if (maxValue == 2) {
                    prediksi = "B";
                }

                et_x1.setText("");
                et_x2.setText("");
                et_x3.setText("");
                tv_pre.setVisibility(View.VISIBLE);
                tbl_pre.setVisibility(View.VISIBLE);
                tv_x1.setText(String.valueOf(x1));
                tv_x2.setText(String.valueOf(x2));
                tv_x3.setText(String.valueOf(x3));
                tv_y.setText(prediksi);
            }
        });
    }
}
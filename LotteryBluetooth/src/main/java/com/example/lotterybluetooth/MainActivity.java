package com.example.lotterybluetooth;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private ListView lvDevices;
    private ArrayAdapter aAdapter;
    private BluetoothAdapter bAdapter = BluetoothAdapter.getDefaultAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btGetDevices = (Button)findViewById(R.id.btDevices);
        btGetDevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bAdapter == null){
                    Toast.makeText(getApplicationContext(),"Bluetooth Not Supported",Toast.LENGTH_SHORT).show();
                }
                else{
                    @SuppressLint("MissingPermission") Set<BluetoothDevice> pairedDevices = bAdapter.getBondedDevices();
                    ArrayList list = new ArrayList();
                    if(pairedDevices.size()>0){
                        for(BluetoothDevice device: pairedDevices){
                            @SuppressLint("MissingPermission") String deviceName = device.getName();
                            String macAddress = device.getAddress();
                            list.add("Name: "+deviceName+"MAC Address: "+macAddress);
                        }
                        lvDevices = (ListView) findViewById(R.id.lvDeviceList);
                        aAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
                        lvDevices.setAdapter(aAdapter);
                    }
                }
            }
        });
    }
}
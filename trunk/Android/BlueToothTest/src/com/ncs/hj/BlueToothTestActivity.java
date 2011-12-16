package com.ncs.hj;

import java.util.Iterator;
import java.util.Set;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class BlueToothTestActivity extends Activity {
	/** Called when the activity is first created. */
	Button btn = null;
	TextView tv = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		btn = (Button)findViewById(R.id.button1);
		tv = (TextView)findViewById(R.id.textView1);
		
		btn.setOnClickListener(new ButtonClickListener());
	}
	
	class ButtonClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
			if(adapter != null) {
				System.out.println("本机拥有蓝牙！");
				if(!adapter.isEnabled()) {
					Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
					startActivity(intent);
				}
				Set<BluetoothDevice> devices = adapter.getBondedDevices();
				if(devices.size() > 0) {
					for(Iterator iterator = devices.iterator();iterator.hasNext();) {
						BluetoothDevice bluetoothDevice = (BluetoothDevice)iterator.next();
						System.out.println(bluetoothDevice.getAddress());
					}
				}
			}
			else
				System.out.println("没有蓝牙设备");
		}
	}
}
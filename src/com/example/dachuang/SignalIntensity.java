package com.example.dachuang;  

import android.net.wifi.WifiInfo;  
import android.net.wifi.WifiManager;  
import android.os.Bundle;  
import android.os.Handler;  
import android.os.Message;  
import android.app.Activity;  
import android.view.Menu;  
import android.widget.TextView;  
import android.annotation.SuppressLint;  
  
public class SignalIntensity extends Activity {  
      
  
    private WifiManager wm;         //WifiManager�ڰ� android.net.wifi.WifiManager��         
    private WifiInfo wi;            // WifiInfo�ڰ�android.net.wifi.WifiInfo��  
    private int strength;           //�ź�ǿ��  
    private TextView tvwifi;  
      
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main);  
          
        tvwifi  = (TextView) findViewById(R.id.action_bar);  //����TextView,�����޸�TextView������  
        new TvThread().start();  
    }  
  
    class TvThread extends Thread{  
        @Override  
        public void run(){  
            do{  
                try{  
                    Thread.sleep(1000);  
                    Message msg = new Message();  
                    msg.what = 1;//what��int���ͣ�δ�������Ϣ���Ա������Ϣ�߿��Լ�����Ϣ�ǹ���ʲô�ġ�ÿ����������Լ�����Ϣ�����ռ䣬���ص��ĳ�ͻ   
                    mHandler.sendMessage(msg);  
                }  
                catch (InterruptedException e){  
                    e.printStackTrace();  
                }  
            }while (true);  
              
        }  
          
    }  
  
      
    private Handler mHandler = new Handler(){  
        @Override  
        public void handleMessage(Message msg) {  
              
            super.handleMessage(msg);  
            switch(msg.what){  
            case 1:  
                wm = (WifiManager) getSystemService(WIFI_SERVICE); //getSystemService(String)��ͨ������������ϵͳ������ľ��������������Ҫ��仯��  
                                                                    //ʹ��getSystemService(String)��ȡ��WifiManagerȻ����wifi���룬  
                wi = wm.getConnectionInfo();//getConnectionInfo����wifi���ӵĶ�̬��Ϣ  
                if(wi.getBSSID() != null)//getBSSID���ػ������񼯱�ʶ�������δ���ӣ�����null�����򷵻�MAC��ַ��ʽ��XX:XX:XX:XX:XX  
                    strength = wi.getRssi();//���ؽ��յ���Ŀǰ��802.11������ź�ǿ��  
                tvwifi.setText(((Integer) strength).toString());  
                break;  
                default:  
                    break;  
            }  
              
        }  
    };  
      
      
   
    @Override  
    public boolean onCreateOptionsMenu(Menu menu) {  
        // Inflate the menu; this adds items to the action bar if it is present.  
        getMenuInflater().inflate(R.menu.main, menu);  
        return true;  
    }  
      
}  


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
      
  
    private WifiManager wm;         //WifiManager在包 android.net.wifi.WifiManager中         
    private WifiInfo wi;            // WifiInfo在包android.net.wifi.WifiInfo中  
    private int strength;           //信号强度  
    private TextView tvwifi;  
      
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main);  
          
        tvwifi  = (TextView) findViewById(R.id.action_bar);  //关联TextView,方便修改TextView的内容  
        new TvThread().start();  
    }  
  
    class TvThread extends Thread{  
        @Override  
        public void run(){  
            do{  
                try{  
                    Thread.sleep(1000);  
                    Message msg = new Message();  
                    msg.what = 1;//what，int类型，未定义的消息，以便接收消息者可以鉴定消息是关于什么的。每个句柄都有自己的消息命名空间，不必担心冲突   
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
                wm = (WifiManager) getSystemService(WIFI_SERVICE); //getSystemService(String)，通过名字来返回系统级服务的句柄。返回类型随要求变化。  
                                                                    //使用getSystemService(String)来取回WifiManager然后处理wifi接入，  
                wi = wm.getConnectionInfo();//getConnectionInfo返回wifi连接的动态信息  
                if(wi.getBSSID() != null)//getBSSID返回基本服务集标识符，如果未连接，返回null，否则返回MAC地址形式：XX:XX:XX:XX:XX  
                    strength = wi.getRssi();//返回接收到的目前的802.11网络的信号强度  
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


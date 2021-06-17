package rawatapps.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class devicelist extends AppCompatActivity {
RecyclerView recyclerView;
Button wifi,search;
WifiManager wifiManager;
WifiP2pManager wmanager;
WifiP2pManager.Channel mcChannel;
BroadcastReceiver bBroadcastReceiver;
IntentFilter intentFilter;
List<WifiP2pDevice> peers=new ArrayList<>();
String[] deviceName;
WifiP2pDevice[] deviceArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devicelist);
        recyclerView=findViewById(R.id.recycler);
        wifi=findViewById(R.id.openwifi);
        search=findViewById(R.id.usersearch);

        wifiManager= (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wmanager= (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        mcChannel=wmanager.initialize(this,getMainLooper(),null);
        bBroadcastReceiver=new WifiBroadCaster(wmanager,mcChannel,this);
        intentFilter=new IntentFilter();
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},29);
            }
        }

        if(wifiManager.isWifiEnabled()){
            wifi.setBackgroundResource(R.drawable.wifi);
        }

recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(),
        recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(devicelist.this, "Working", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLongItemClick(View view, int position) {
        Toast.makeText(devicelist.this, deviceName[position], Toast.LENGTH_SHORT).show();
    }
}));

            recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
                @Override
                public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                    return false;
                }

                @Override
                public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

                }

                @Override
                public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

                }
            });

    }

    final WifiP2pManager.PeerListListener peerListListener=new WifiP2pManager.PeerListListener() {
        @Override
        public void onPeersAvailable(WifiP2pDeviceList peer) {
            if(!peer.getDeviceList().equals(peers)){
               // peers.clear();
                peers.addAll(peer.getDeviceList());
                deviceName=new String[peer.getDeviceList().size()];
                deviceArr=new WifiP2pDevice[peer.getDeviceList().size()];
                int index=0;
                final List<Userclass> sample=new ArrayList<>();
                RecyclerView.LayoutManager layoutManager;
                Userclass userclass=new Userclass();

                for(WifiP2pDevice device : peer.getDeviceList()){
                    userclass.img=R.drawable.ic_cry;
                    userclass.usname=device.deviceName;
                    sample.add(userclass);
                    index++;
                }

                layoutManager=new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
                recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
                recyclerView.setAdapter(new RecyclerAdapter(sample));
            }
            if(peers.size()==0){
                Toast.makeText(devicelist.this, "No Device Found", Toast.LENGTH_SHORT).show();
            }
        }
    };



    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(bBroadcastReceiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(bBroadcastReceiver);
    }

    public void openWifi(View view) {
        if(!wifiManager.isWifiEnabled()){
            wifi.setBackgroundResource(R.drawable.wifi);
             wifiManager.setWifiEnabled(true);
        }
    }

    public void searchPlayer(View view) {
        wmanager.discoverPeers(mcChannel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(devicelist.this, "Seraching Players", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int reason) {
                Toast.makeText(devicelist.this, "Failed to Search "+reason, Toast.LENGTH_SHORT).show();
            }
        });

    }
}

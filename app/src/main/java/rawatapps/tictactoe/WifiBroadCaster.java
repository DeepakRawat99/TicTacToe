package rawatapps.tictactoe;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.widget.Toast;

public class WifiBroadCaster extends BroadcastReceiver {
    private WifiP2pManager manager;
    private WifiP2pManager.Channel channel;
    private devicelist devicelists;

    public WifiBroadCaster(WifiP2pManager manager,WifiP2pManager.Channel mchannel,devicelist device){
        this.channel=mchannel;
        this.manager=manager;
        this.devicelists=device;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action=intent.getAction();

        assert action != null;
        switch (action){

            case WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION:
                int state=intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE,-1);
                if(state==WifiP2pManager.WIFI_P2P_STATE_ENABLED){
                    Toast.makeText(context, "Wifi is On" , Toast.LENGTH_SHORT).show();
                }else Toast.makeText(context, "Wifi is OFF", Toast.LENGTH_SHORT).show();
                break;

            case WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION:
                if(manager!=null){
                    manager.requestPeers(channel,devicelists.peerListListener);
                }
                break;

            case WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION:
                break;

            case WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION:
                break;
            default:
                break;

        }
    }
}

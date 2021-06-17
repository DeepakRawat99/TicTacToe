package rawatapps.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.net.wifi.WpsInfo;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.View;

public class Local extends AppCompatActivity implements WifiP2pManager.ConnectionInfoListener {
    protected static final int CHOOSE_FILE_RESULT_CODE = 20;
    private View mContentView = null;
    private WifiP2pDevice device;
    private WifiP2pInfo info;
    ProgressDialog progressDialog = null;
    /*local match using wifi still on progress*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        findDevice();
    }

    private void findDevice() {
        WifiP2pConfig config=new WifiP2pConfig();
        config.deviceAddress=device.deviceAddress;
        config.wps.setup= WpsInfo.PBC;
        if(progressDialog!=null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
        progressDialog= ProgressDialog.show(getApplicationContext(),"Press Back To Cancel",
                "Connecting to : "+device.deviceAddress,true,true);
        
    }

    @Override
    public void onConnectionInfoAvailable(WifiP2pInfo info) {

    }
}

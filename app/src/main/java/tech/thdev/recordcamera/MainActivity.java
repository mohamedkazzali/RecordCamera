package tech.thdev.recordcamera;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import butterknife.BindView;
import tech.thdev.base.util.ActivityUtilKt;
import tech.thdev.base.view.BaseActivity;

public class MainActivity extends BaseActivity {

    private static final int REQUEST_CAMERA_PERMISSION = 1000;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private MainFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar.setTitle(R.string.title);
        setSupportActionBar(toolbar);

        fragment = new MainFragment();

        ActivityUtilKt.replaceContentFragment(this, R.id.frame_layout, fragment);
    }

    public void requestCameraPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(this, "Sorry!!!, you can't use this app with granting permission", Toast.LENGTH_SHORT).show();
                finish();
            }

        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}

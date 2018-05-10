package edu.qc.cs370.macrotracker;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import java.io.IOException;

import edu.qc.cs370.macrotracker.R;

// BarcodeScannerActivity written by RG

public class BarcodeScannerActivity extends AppCompatActivity {

  SurfaceView cameraPreview;
  TextView resultText;
  BarcodeDetector barcodeDetector;
  CameraSource cameraSource;
  final int RequestCameraPermissionID = 1001;
  boolean vibrated = false;

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    switch (requestCode) {
      case RequestCameraPermissionID: {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            return;
          }
          try {
            cameraSource.start(cameraPreview.getHolder());
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_barcode_scanner);

    cameraPreview = findViewById(R.id.cameraPreview);
    // resultText = findViewById(R.id.textResult);

    barcodeDetector = new BarcodeDetector
        .Builder(this)
        .build();
    cameraSource = new CameraSource
        .Builder(this, barcodeDetector)
        .setAutoFocusEnabled(true)
        .setRequestedPreviewSize(1920, 1080)
        .build();

    cameraPreview.getHolder().addCallback(new SurfaceHolder.Callback() {
      @Override
      public void surfaceCreated(SurfaceHolder holder) {
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
          ActivityCompat.requestPermissions(BarcodeScannerActivity.this, new String[]{Manifest.permission.CAMERA}, RequestCameraPermissionID);
          return;
        }
        try {
          cameraSource.start(cameraPreview.getHolder());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

      @Override
      public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) { }

      @Override
      public void surfaceDestroyed(SurfaceHolder holder) {
        cameraSource.stop();
      }
    });

    barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
      @Override
      public void release() {

      }

      @Override
      public void receiveDetections(Detector.Detections<Barcode> detections) {
        final SparseArray<Barcode> barcode = detections.getDetectedItems();
        if (barcode.size() != 0) {
          new Runnable() {
            @Override
            public void run() {
              Vibrator vibrator = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
              if (!vibrated) {
                vibrator.vibrate(200);
              }
              vibrated = true;
            }
          };

          // Commenting the below code out because it was just for testing purposes, by DV
          /*
          resultText.post(new Runnable() {
            @Override
            public void run() {
              Vibrator vibrator = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
              if (!vibrated) {
                vibrator.vibrate(200);
              }
              // resultText.setText("Barcode: " + barcode.valueAt(0).displayValue);
              vibrated = true;
            }
          });
          */

          // Retrieving the UPC
          String upc = barcode.valueAt(0).displayValue;
          // Sending back the UPC
          Intent intent = new Intent();
          intent.putExtra("upc", upc);
          setResult(RESULT_OK, intent);
          finish();
        }
        // TODO create an else block that will send back a sentinel value if the barcode is not the correct UPC format
        // TODO the sentinel value will be checked during the API call to inform the user to search via the hot search.
      }
    });
  }
}

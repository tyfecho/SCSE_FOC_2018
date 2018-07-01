package com.example.tyrone.scse_foc_2018.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.net.sip.SipSession;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tyrone.scse_foc_2018.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TrReportActivity extends AppCompatActivity {

    static final int REQUEST_BEFORE_IMAGE = 0;
    static final int REQUEST_AFTER_IMAGE = 1;

    static final int STATUS_EMPTY = 0;
    static final int STATUS_HALFWAY = 1;
    static final int STATUS_FINISH = 2;

    String mCurrentPhotoPath = "";

    SharedPreferences sharedPref;

    TextView textView1;
    TextView textView2;
    TextView InfoMessage;

    Button UpdateButton;
    Spinner TRSpinner;
    Spinner TimingSpinner;

    String[] TutorialRooms;
    String[] TutorialTimings;

    String CurrentRoom;
    String CurrentTiming;

    ImageView BeforeImage;
    ImageView AfterImage;

    private static final String[]paths = {"item 1", "item 2", "item 3"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tr_report);

        sharedPref = this.getPreferences(Context.MODE_PRIVATE);


        textView1 = findViewById(R.id.textView4);
        textView2 = findViewById(R.id.textView5);
        InfoMessage = findViewById(R.id.InfoMessage);

        BeforeImage = findViewById(R.id.BeforeImage);
        AfterImage = findViewById(R.id.AfterImage);

        UpdateButton = findViewById(R.id.UpdateButton);
        UpdateButton.setEnabled(false);

        TRSpinner = findViewById(R.id.TRspinner);
        TimingSpinner = findViewById(R.id.TimeSpinner);

        TutorialRooms = getResources().getStringArray((R.array.tutorialRooms));
        TutorialTimings = getResources().getStringArray((R.array.tutorialTimings));

        ArrayAdapter<String> RoomAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,TutorialRooms);
        ArrayAdapter<String> TimingAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,TutorialTimings);

        RoomAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        TimingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        TRSpinner.setAdapter(RoomAdapter);
        TimingSpinner.setAdapter(TimingAdapter);

        CurrentRoom = TRSpinner.getSelectedItem().toString();
        CurrentTiming = TimingSpinner.getSelectedItem().toString();

        textView1.setText(CurrentRoom);

        setUpListeners();

    }


    private void setUpListeners()
    {
        //TRSpinner.setOnItemSelectedListener();
        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {


                CurrentRoom = TRSpinner.getSelectedItem().toString();

                if(!CurrentRoom.equals("Select TR")) {
                    textView1.setText(CurrentRoom);
                    checkForAvailability();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        };

        AdapterView.OnItemSelectedListener listener2 = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                CurrentTiming = TimingSpinner.getSelectedItem().toString();
                if(!CurrentRoom.equals("Select Time"))
                {
                    textView2.setText(CurrentTiming);
                    checkForAvailability();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        };

        TRSpinner.setOnItemSelectedListener(listener);
        TimingSpinner.setOnItemSelectedListener(listener2);
    }

    private void checkForAvailability()
    {
        //check the DB for the currently selected room

        //if both the things havent been selected yet den dont search
        if( CurrentRoom.equals("Select TR") || CurrentTiming.equals("Select Time"))
        {
            UpdateButton.setEnabled(false);
            return;
        }
        else {
            //3 statuses : STATUS_EMPTY, STATUS_HALFWAY, STATUS_FINISH

            String key = CurrentRoom + ":" + CurrentTiming;
            int status = sharedPref.getInt(key, STATUS_EMPTY);

            if (status == STATUS_EMPTY) {
                InfoMessage.setText("No record submitted");

                //set button onclick to take a before picture and create a new form
                setButtonClickToTakeBefore();
                UpdateButton.setEnabled(true);
                UpdateButton.setText("Submit New Record");
                //update both the before and after images

                BeforeImage.setImageResource(R.mipmap.ic_launcher);
                AfterImage.setImageResource(R.mipmap.ic_launcher);


            }
            else if (status == STATUS_HALFWAY)
            {
                InfoMessage.setText("A Before photo was submitted, please submit the After photo when you are done");

                //set button onclick to take after picture and complete the form
                setButtonClickToTakeAfter();
                UpdateButton.setEnabled(true);
                UpdateButton.setText("Submit After photo");
                //update both the before and after images
                setPic(BeforeImage, 0);
                AfterImage.setImageResource(R.mipmap.ic_launcher);
            }
            else if(status == STATUS_FINISH)
            {
                InfoMessage.setText("Record has been submitted");
                UpdateButton.setEnabled(false);
                UpdateButton.setText("Record submitted");

                //update both the before and after images
                setPic(BeforeImage, 0);
                setPic(AfterImage, 1);

            }
        } }
    private void setButtonClickToTakeBefore()
    {
        UpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent(UpdateButton, REQUEST_BEFORE_IMAGE);
            }
        });
    }
    private void setButtonClickToTakeAfter()
    {
        UpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent(UpdateButton, REQUEST_AFTER_IMAGE);
                //setPic(UpdateButton);
            }
        });
    }
    public void insertfake(View v)
    {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("TR2:10:00 - 12:00", STATUS_HALFWAY);
        editor.putInt("TR3:12:00 - 14:00", STATUS_FINISH);
        editor.commit();
    }
    public void dispatchTakePictureIntent(View view, int request) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile(request);
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, request);
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == REQUEST_BEFORE_IMAGE || requestCode == REQUEST_AFTER_IMAGE) && resultCode == RESULT_OK) {
            /*Log.i("asd", "came inside here");
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView mImageView = (ImageView) findViewById(R.id.PreviewImage);
            mImageView.setImageBitmap(imageBitmap);
            Log.i("asd", "helo");
*/
            galleryAddPic();

            SharedPreferences.Editor editor = sharedPref.edit();
            if(requestCode == REQUEST_BEFORE_IMAGE) {
                editor.putInt(CurrentRoom + ":" + CurrentTiming, STATUS_HALFWAY);
                setPic(BeforeImage, 0);
            }
            else if(requestCode == REQUEST_AFTER_IMAGE) {
                editor.putInt(CurrentRoom + ":" + CurrentTiming, STATUS_FINISH);
                setPic(AfterImage, 1);
            }
            else
                Log.i("huge error", "did not save into shared pref correctly");

            editor.commit();
            checkForAvailability();
        }
    }

    private File createImageFile(int request) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        //String imageFileName = "JPEG_" + timeStamp + "_";
        String imageFileName = CurrentRoom + ":" + CurrentTiming;

        if(request == REQUEST_BEFORE_IMAGE) imageFileName += ":" + "before";
        else imageFileName += ":" + "after";

        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = new File(storageDir, "/" + imageFileName + ".jpg");

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    public void setPic(ImageView mImageView, int beforeOrAfter) {

        //ImageView mImageView = (ImageView) findViewById(R.id.PreviewImage2);
        // Get the dimensions of the View
        int targetW = mImageView.getWidth();
        int targetH = mImageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        String PhotoPath = getExternalFilesDir(Environment.DIRECTORY_PICTURES) + "/" + CurrentRoom + ":" + CurrentTiming;

        //before and after
        if(beforeOrAfter == 0) PhotoPath += ":" + "before.jpg";
        else PhotoPath += ":" + "after.jpg";

        Bitmap bitmap = BitmapFactory.decodeFile(PhotoPath, bmOptions);
        mImageView.setImageBitmap(bitmap);
    }
}

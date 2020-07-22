package com.pmposs.touristphotocollection;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.*;
import android.provider.MediaStore;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import java.io.FileDescriptor;
import java.io.IOException;

public class VisitorUpload extends AppCompatActivity {
    ImageView photoPreView;
    Button takePhoto,pickPhoto,selectStyle,uploadBackToMain;
    TextView upload_text_show;
    Uri photoUri;
    String picTruePath="";
    String user_account;
    private final static int SELECT_PIC_BY_TACK_PHOTO=1;
    private final static int SELECT_PIC_BY_PICK_PHOTO=2;
    private final static int TO_SELECT_STYLE=3;
    private final static int NULL_TRUE_PATH=4;
    private final static int REST_PHOTO=5;
    private  final int REQUEST_EXTERNAL_STORAGE = 1;
    private  String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_upload);
        initView();
    }
    public void initView()
    {
        //初始化界面
        photoPreView=findViewById(R.id.photoPreView);
        takePhoto=findViewById(R.id.takePhoto);
        pickPhoto=findViewById(R.id.pickPhoto);
        selectStyle=findViewById(R.id.selectStyle);
        uploadBackToMain=findViewById(R.id.uploadBackToMain);
        upload_text_show=findViewById(R.id.upload_text_show);
        //设置点击事件
        uploadBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToLogin();
            }
        });
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
            }
        });
        pickPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickPhoto();
            }
        });
        selectStyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectStyle();
            }
        });
        getUserAccount();
        verifyStoragePermissions(this);
    }
    private Handler handler=new Handler()
    {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch(msg.what)
            {
                case NULL_TRUE_PATH:
                    upload_text_show.setText("*请先拍摄或从相册选择照片*");
                    break;
                case REST_PHOTO:
                    photoPreView.setImageBitmap(null);
                    break;
                default:
                    break;
            }
        }
    };
    public void getUserAccount()
    {
        Intent intent=getIntent();
        user_account=intent.getStringExtra("user_account");
    }
    public void backToLogin()
    {
        Intent intent=new Intent();
        intent.putExtra("return_u","upload->main");
        setResult(RESULT_OK,intent);
        finish();
    }
    public void takePhoto()
    {
        String SDState= Environment.getExternalStorageState();
        if(SDState.equals(Environment.MEDIA_MOUNTED))
        {
            Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            ContentValues values=new ContentValues();
            photoUri=this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
            startActivityForResult(intent,SELECT_PIC_BY_TACK_PHOTO);
        }
    }
    public void pickPhoto()
    {
        Intent intent=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,SELECT_PIC_BY_PICK_PHOTO);
    }
    public void selectStyle()
    {
        if(picTruePath.equals("") || picTruePath ==null)
        {
            Message message=new Message();
            message.what=NULL_TRUE_PATH;
            handler.sendMessage(message);
        }
        else {
            Intent intent = new Intent("com.pmposs.toursitphotocollection.VISITORSELECT_START");
            intent.putExtra("picTruePath", picTruePath);
            intent.putExtra("user_account", user_account);
            startActivityForResult(intent, TO_SELECT_STYLE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == Activity.RESULT_OK)
        {
            if(requestCode != TO_SELECT_STYLE) {
                try {
                    setPhoto(requestCode, data);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else
            {
                Message message=new Message();
                message.what=REST_PHOTO;
                handler.sendMessage(message);
                picTruePath="";
            }
        }
        super.onActivityResult(requestCode,resultCode,data);
    }
    private void setPhoto(int requestCode,Intent data)throws IOException
    {
        //判断请求码，确认选择的照片的方法是拍摄照片还是从相册选择照片
        if(requestCode == SELECT_PIC_BY_PICK_PHOTO)
        {
            //若为从相册选择照片，需要进行进一步的检验
            if (data==null)
            {
                Toast.makeText(this,"出错",Toast.LENGTH_LONG).show();
            }
            photoUri=data.getData();
            if(photoUri == null)
            {
                Toast.makeText(this,"出错",Toast.LENGTH_LONG).show();
                return;
            }
        }
        //利用Cursor和图片路径查找到照片
        String[] pojo={MediaStore.Images.Media.DATA};
        Cursor cursor=getContentResolver().query(photoUri,pojo,null,null,null);
        cursor.moveToFirst();
        int columnIndex=cursor.getColumnIndex(pojo[0]);
        //获得照片存储的真实路径
        String picPath=cursor.getString(columnIndex);
        cursor.close();
        //如果查找照片成功，将照片进行以下处理
        if(picPath !=null && (picPath.endsWith(".png") || picPath.endsWith(".PNG") || picPath.endsWith(".jpg") || picPath.endsWith(".JPG")))
        {
            //利用照片的路径使用FileDescriptor来获得照片
            ParcelFileDescriptor parcelFileDescriptor=getContentResolver().openFileDescriptor(photoUri,"r");
            FileDescriptor fileDescriptor=parcelFileDescriptor.getFileDescriptor();
            BitmapFactory.Options options=new BitmapFactory.Options();
            options.inJustDecodeBounds=false;
            //将照片的尺寸缩放到原来的五分之一
            options.inSampleSize=5;
            //自定的读取照片角度是否有旋转
            int angel=readPictureDegree(picPath);
            //利用将照片注入到bitmap中
            Bitmap bm=BitmapFactory.decodeFileDescriptor(fileDescriptor,null,options);
            parcelFileDescriptor.close();
            //使用ImageView的setImageBitmap方法将图片设置进去
            photoPreView.setImageBitmap(rotaingImageView(angel,bm));
            picTruePath=picPath;
        }
    }
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }
    public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
        Bitmap returnBm = null;
        // 根据旋转角度，生成旋转矩阵
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        try {
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
            returnBm = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
        }
        if (returnBm == null) {
            returnBm = bitmap;
        }
        if (bitmap != returnBm) {
            bitmap.recycle();
        }
        return returnBm;
    }
    public  void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        }
    }
}

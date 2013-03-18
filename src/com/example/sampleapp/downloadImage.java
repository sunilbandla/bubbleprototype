package com.example.sampleapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;
 
public class downloadImage extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloadimage);
         
//        Bitmap bitmap = DownloadImage(
//            "http://www.allindiaflorist.com/imgs/arrangemen4.jpg");
//        File imgfile = new File(Environment.getExternalStorageDirectory()+"/imagesFolder/pic.jpg");
        AssetManager assetManager = getAssets();
        try {
			System.out.println(assetManager.list("").toString());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        Bitmap bitmap = null;
        try {
        	bitmap = BitmapFactory.decodeStream(assetManager.open("pic.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(Environment.getExternalStorageDirectory());
//        Drawable d = Drawable.createFromPath(imgfile.getAbsolutePath());
//        Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/assets/imagesFolder/pic.jpg");
        ImageView img = (ImageView) findViewById(R.id.imgview);
        img.setImageBitmap(bitmap);
//        img.setImageDrawable(d);
    }
   
    private InputStream OpenHttpConnection(String urlString)
    throws IOException
    {
        InputStream in = null;
        int response = -1;
                
        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();
              
        if (!(conn instanceof HttpURLConnection))                    
            throw new IOException("Not an HTTP connection");
         
        try{
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            
            httpConn.connect();
            
            response = httpConn.getResponseCode();                
            if (response == HttpURLConnection.HTTP_OK) {
            	in = httpConn.getInputStream();                                
            }                    
        }
        catch (Exception ex)
        {
            throw new IOException("Error connecting: " + ex.toString());
        }
        return in;    
    }
    private Bitmap DownloadImage(String URL)
    {       
        Bitmap bitmap = null;
        InputStream in = null;       
        try {
            in = OpenHttpConnection(URL);
            bitmap = BitmapFactory.decodeStream(in);
            in.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return bitmap;               
    }
}

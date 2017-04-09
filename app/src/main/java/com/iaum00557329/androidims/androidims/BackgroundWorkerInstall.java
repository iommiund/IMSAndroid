package com.iaum00557329.androidims.androidims;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundWorkerInstall extends AsyncTask<String,Void,String> {

    Context context;

    BackgroundWorkerInstall (Context ctx){
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params){

        String type = params[0];
        String install_url = "http://192.168.1.11/ims/Android/install.php";
        /*String install_url = "http://10.254.236.43/ims/Android/install.php";*/

        if (type.equals("install")){

            try {

                URL url = new URL(install_url);
                try {

                    String orderId = params[1];
                    String resource = params[2];

                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("orderId","UTF-8")+"="+URLEncoder.encode(orderId,"UTF-8")+"&"
                            +URLEncoder.encode("resource","UTF-8")+"="+URLEncoder.encode(resource,"UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String result="";
                    String line="";
                    while((line = bufferedReader.readLine()) != null){
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;

                } catch (IOException e) {
                    e.printStackTrace();
                }



            } catch (MalformedURLException e) {

                e.printStackTrace();

            }

        }

        return null;
    }

    @Override
    protected void onPreExecute(){

    }

    @Override
    protected void onPostExecute(String result){

        if(result == null)
        {
            Toast.makeText(context, "Action not successful", Toast.LENGTH_SHORT).show();
        }
        else if (result.contains("All fields are required"))
        {
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
        }
        else if (result.contains("Install order provided not found"))
        {
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
        }
        else if (result.contains("Resource is not available on your location, or is not of the same type"))
        {
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        }
        else if (result.contains("Orders table could not be updated"))
        {
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
        }
        else if (result.contains("Resources table could not be updated"))
        {
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
        }
        else if (result.contains("Transactions table could not be updated"))
        {
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
        }
        else if (result.contains("Install Successful"))
        {
            Intent intent = new Intent(context,OptionsActivity.class);
            context.startActivity(intent);
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onProgressUpdate(Void... values){
        super.onProgressUpdate(values);
    }

}

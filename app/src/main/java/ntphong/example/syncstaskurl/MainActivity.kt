package ntphong.example.syncstaskurl

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URI
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        readURL().execute("https://www.24h.com.vn/")
    }
  inner  class readURL : AsyncTask<String,Void,String>(){
        override fun doInBackground(vararg params: String?): String {
           var content  :StringBuilder = StringBuilder()
            val url :URL= URL(params[0])
            val urlConnection : HttpURLConnection=url.openConnection() as HttpURLConnection
            var inputStream:InputStream=urlConnection.inputStream
            val inputStreamReader :InputStreamReader= InputStreamReader(inputStream)
            val bufferedReader : BufferedReader = BufferedReader(inputStreamReader)
            var line :String=""
            try {
                do {
                    line = bufferedReader.readLine()
                    if (line!=null){
                        content.append(line)
                    }
                }while (line!=null)
                bufferedReader.close()

            }catch (e:Exception){
                    Log.d("aaa",e.toString())}
            return content.toString()
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            Toast.makeText(this@MainActivity,result,Toast.LENGTH_LONG).show()
        }
    }
}

//package com.example.yusufhamdadi.uas5;
//
//
//import android.support.v4.app.Fragment;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//
//public  class halaman2 extends Fragment {
//
//    public halaman2() {
//        // Required empty public constructor
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        // halaman1 merujuk pada file halaman1.xml
//        return inflater.inflate(R.layout.halaman2, container, false);
//    }
//
//}

package com.example.yusufhamdadi.uas5;

import android.app.Activity;
import android.os.Bundle;
import java.util.ArrayList;
import android.database.Cursor;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class halaman2 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halaman2);

        ListView lViewSMS = (ListView) findViewById(R.id.listViewSMS);

        if(fetchInbox()!=null)
        {
            ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_inbox, R.id.textListView,fetchInbox());
            lViewSMS.setAdapter(adapter);

            lViewSMS.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Object o = lViewSMS.getItemAtPosition(position);
                    String person = (String)parent.getItemAtPosition(position);

                    Toast.makeText(getBaseContext(),person,Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public ArrayList fetchInbox()
    {
        ArrayList sms = new ArrayList();

        Uri uriSms = Uri.parse("content://sms/inbox");
        Cursor cursor = getContentResolver().query(uriSms, new String[]{"_id", "address", "date", "body"},null,null,null);

        cursor.moveToFirst();
        while  (cursor.moveToNext())
        {
            String address = cursor.getString(1);
            String body = cursor.getString(3);

            sms.add(address);
        }
        return sms;

    }

}
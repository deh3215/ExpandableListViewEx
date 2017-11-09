package com.example.deh3215.expandablelistviewex;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Context context;
    ExpandableListView expListView;
    String[] menu_group = {"主餐", "副餐"};
    String[][] menu_sub = {
            { "牛排", "義大利麵", "咖喱飯" },
            { "咖啡", "紅茶", "冰淇淋", "奶酪" }
    };

    List<Map<String, String>> group = new ArrayList<Map<String, String>>();
    List<List<Map<String, String>>> childrenList = new ArrayList<List<Map<String, String>>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        expListView = (ExpandableListView)findViewById(R.id.expListView);

        for (int i = 0; i< menu_group.length ; i++ ) {
            // Group
            Map<String, String> groupFood = new HashMap<String, String>();
            groupFood.put("groupKey", menu_group[i]);
            group.add(groupFood);
            // Sub
            List<Map<String, String>> children = new ArrayList<Map<String, String>>();
            for (String food : menu_sub[i]) {
                Map<String, String> childMap = new HashMap<String, String>();
                childMap.put("childKey", food);
                children.add(childMap);
            }
            childrenList.add(children);
        }
        SimpleExpandableListAdapter mAdapter =
                new SimpleExpandableListAdapter(
                        context,
                        group,
                        android.R.layout.simple_expandable_list_item_1,
                        new String[] { "groupKey" },
                        new int[] { android.R.id.text1 },
                        childrenList,
                        android.R.layout.simple_expandable_list_item_1,
                        new String[] { "childKey" },
                        new int[] { android.R.id.text1  }
                );

        // 設定 Adapter
        expListView.setAdapter(mAdapter);
        // 設定可擴展列表 child click 監聽器

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
        @Override
        public boolean onChildClick(ExpandableListView parent, View
        view, int groupPosition, int childPosition, long id) {
            String mainfood = menu_group[groupPosition];
            String subfood = menu_sub[groupPosition][childPosition];
            Toast.makeText(context, mainfood + " : " + subfood, Toast.LENGTH_SHORT).show();
            return false;
        } });

    }



}

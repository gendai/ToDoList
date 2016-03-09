package com.example.english.todolist;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

class CusAdapt extends BaseAdapter {

    Context context;
    String[] data;
    ArrayList<String> lsts;
    private static LayoutInflater inflater = null;
    ArrayList<ToDoItems> items = new ArrayList<>();

    public CusAdapt(Context context, String[] data, ArrayList<String> ls) {
        this.context = context;
        this.data = data;
        this.lsts = ls;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public void add(String s){
        items.add(new ToDoItems(s, false));
        lsts.add(s);
    }

    public void delete(int position){
        items.remove(position);
        lsts.remove(position);
    }

    @Override
    public int getCount() {
        return lsts.size();
    }

    @Override
    public Object getItem(int position) {
        return lsts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolderItem viewHolder;

        if(convertView==null){

            Context context = parent.getContext();
            convertView = LayoutInflater.from(context).inflate(R.layout.items, null);
            viewHolder = new ViewHolderItem();
            viewHolder.textViewItem = (TextView) convertView.findViewById(R.id.textList);
            viewHolder.checkboxItem = (CheckBox) convertView.findViewById(R.id.checkbox);
            convertView.setTag(viewHolder);

        }else{

            viewHolder = (ViewHolderItem) convertView.getTag();
        }
        viewHolder.checkboxItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                items.get(position).setChecked(isChecked);
                if(checkAll() && items.size() > 0){
                    Toast.makeText(context, "All tasks has been completed", Toast.LENGTH_LONG).show();
                }
            }
        });
        viewHolder.checkboxItem.setChecked(items.get(position).getChecked());
        viewHolder.textViewItem.setText(items.get(position).getText());
        return convertView;
    }

    public void Clear(){
        int len = items.size();
        for(int i = 0; i < len;i++){
            delete(0);
        }
    }

    private boolean checkAll(){
        for (ToDoItems i: items) {
            if(!i.getChecked()){
                return false;
            }
        }
        return true;
    }

    static class ViewHolderItem {
        TextView textViewItem;
        CheckBox checkboxItem;
    }


}
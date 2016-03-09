package com.example.english.todolist;

public class ToDoItems {

    private String text;
    private boolean checked;

    public ToDoItems(String txt, boolean b){
        this.text = txt;
        this.checked = b;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }


}

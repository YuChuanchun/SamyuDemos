package com.samyu.demos.xml;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {
    private String name;
    private String description;
    private String type;
    private String value;
    
    public Item() {}
    
    public Item(String n, String d, String t, String v) {
        name = n;
        description = d;
        type = t;
        value = v;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getType() {
        return type;
    }
    
    public String getValue() {
        return value;
    }
    
    public static final Parcelable.Creator<Item> CREATOR = new Creator<Item>(){  

      public Item createFromParcel(Parcel source) {  
          // TODO Auto-generated method stub  
          Item item = new Item();
          item.name = source.readString();  
          item.description = source.readString();  
          item.type = source.readString();  
          item.value = source.readString();  
          return item;  
      }  

      public Item[] newArray(int size) {  
          // TODO Auto-generated method stub  
          return new Item[size];  
      }       
      
    }; 
    
    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(type);
        dest.writeString(value);
    }
}

package com.samyu.demos.xml;

import java.util.TreeMap;
import java.util.Iterator;
import java.util.ArrayList;

import com.samyu.demos.R;

import android.content.Intent;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.util.Log;
import android.view.inputmethod.EditorInfo;

public class Settings extends PreferenceActivity {
    public static final String TAG = "Settings";

    TreeMap<String, Item> publicTreeMap = new TreeMap<String, Item>();
    TreeMap<String, ArrayList<Item>> privateTreeMap = new TreeMap<String, ArrayList<Item>>();
    ArrayList<String> privateName = new ArrayList<String>();

    PreferenceCategory publicPreferenceCategory, privatePreferenceCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.layout.settings);

        publicPreferenceCategory = (PreferenceCategory) findPreference("public");
        privatePreferenceCategory = (PreferenceCategory) findPreference("private");

        ReadXML.getDataFromXML(publicTreeMap, privateName);
        loadData();

    }

    private void loadData() {
        Iterator<String> puIterator = publicTreeMap.keySet().iterator();
        while (puIterator.hasNext()) {
            String key = (String) puIterator.next();
            Item item = (Item) publicTreeMap.get(key);
            createPublicPre(key, item);
        }

        for (int i = 0; i < privateName.size(); i++) {
            createPrivatePre(privateName.get(i));
        }
    }

    private void createPublicPre(final String key, final Item i) {
        if (i.getType().equals("string") || i.getType().equals("number")) {
            final EditTextPreference etp = new EditTextPreference(this);
            etp.setKey(key);
            etp.setTitle(i.getDescription());
            etp.setSummary(i.getValue());
            etp.setText(i.getValue());
            if (i.getType().equals("number")) etp.getEditText().setInputType(EditorInfo.TYPE_CLASS_NUMBER);
            etp.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

                @Override
                public boolean onPreferenceChange(Preference arg0, Object arg1) {
                    // TODO Auto-generated method stub
                    String text = etp.getEditText().getText().toString();
                    etp.setSummary(text);
                    WriteXML.writePublicXML(key, text);
                    return false;
                }

            });
            etp.setDialogTitle(i.getDescription());
            publicPreferenceCategory.addPreference(etp);
        } else if (i.getType().equals("boolean")) {
            final CheckBoxPreference cbp = new CheckBoxPreference(this);
            cbp.setChecked(i.getValue().toLowerCase().equals("true") ? true : false);
            cbp.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

                public boolean onPreferenceChange(Preference arg0, Object arg1) {
                    // TODO Auto-generated method stub
                    String text = !cbp.isChecked() + "";
                    WriteXML.writePublicXML(i.getName(), text);
                    return true;
                }

            });
            cbp.setTitle(i.getDescription());
            publicPreferenceCategory.addPreference(cbp);
        }
    }

    private void createPrivatePre(String key) {
        Preference etp = new Preference(this);
        etp.setKey(key);
        etp.setTitle(key);
        privatePreferenceCategory.addPreference(etp);
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        // TODO Auto-generated method stub
        String key = preference.getKey();
        if (privateName.contains(key)) {
            Intent intent = new Intent(this, ModuelSettings.class);
            intent.putExtra("name", key);
            startActivity(intent);
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

}

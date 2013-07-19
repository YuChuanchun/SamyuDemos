package com.samyu.demos.xml;

import java.util.ArrayList;

import com.samyu.demos.R;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.preference.Preference.OnPreferenceChangeListener;
import android.view.inputmethod.EditorInfo;

public class ModuelSettings extends PreferenceActivity {

    public static final String TAG = "ModuelSettings";

    ArrayList<Item> items = new ArrayList<Item>();

    PreferenceScreen root;

    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.layout.moduel_settings);

        root = (PreferenceScreen) findPreference("root");

        name = getIntent().getStringExtra("name");
        setTitle(name);
        ReadXML.getDataFromXML(name, items);

        createPrivatePre();
    }

    private void createPrivatePre() {
        for (int i = 0; i < items.size(); i++) {
            final Item item = items.get(i);
            if (item.getType().equals("string") || item.getType().equals("number")) {
                final EditTextPreference etp = new EditTextPreference(this);
                etp.setTitle(item.getDescription());
                etp.setSummary(item.getValue());
                etp.setText(item.getValue());
                if (item.getType().equals("number")) etp.getEditText().setInputType(EditorInfo.TYPE_CLASS_NUMBER);
                etp.setDialogTitle(item.getDescription());
                etp.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

                    @Override
                    public boolean onPreferenceChange(Preference arg0, Object arg1) {
                        // TODO Auto-generated method stub
                        String text = etp.getEditText().getText().toString();
                        etp.setSummary(text);
                        WriteXML.writePrivateXML(name, item.getName(), text);
                        return false;
                    }

                });
                root.addPreference(etp);
            } else if (item.getType().equals("boolean")) {
                final CheckBoxPreference cbp = new CheckBoxPreference(this);
                cbp.setChecked(item.getValue().toLowerCase().equals("true") ? true : false);
                cbp.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

                    @Override
                    public boolean onPreferenceChange(Preference arg0, Object arg1) {
                        // TODO Auto-generated method stub
                        String text = !cbp.isChecked() + "";
                        WriteXML.writePrivateXML(name, item.getName(), text);
                        return true;
                    }

                });
                cbp.setTitle(item.getDescription());
                root.addPreference(cbp);
            }
        }
    }
}

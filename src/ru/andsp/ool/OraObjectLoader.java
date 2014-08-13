package ru.andsp.ool;

import static ru.andsp.ool.DBHelper.getConnection;
import ru.andsp.ool.domain.OraSchema;
import ru.andsp.ool.domain.Setting;
import ru.andsp.ool.gui.MainForm;

public class OraObjectLoader {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ParamsComandLine param = new ParamsComandLine(args);
        Setting s;
        if (!param.isEmptySetting()) {
            s = SettingHelper.read(param.getFileSetting());
        } else {
            s = new Setting();
        }
        if (param.isIsGui()) {
            MainForm.start(s);
        } else if (!param.isEmptySetting()) {
            load(s);
        }
    }

    public static void load(Setting s) {
        SchemaLoader sl = new SchemaLoader();
        OraSchema load = sl.load(getConnection(s), s.getTypes());
        FileSchemaStorage fs = new FileSchemaStorage();
        fs.save(load, s.getOutDir(), s.getClear().equals(Setting.CLEAR));
    }

}

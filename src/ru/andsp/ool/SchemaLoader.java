/*
 * The MIT License
 *
 * Copyright 2014 Serg.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package ru.andsp.ool;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import ru.andsp.ool.domain.ObjectType;
import static ru.andsp.ool.domain.ObjectType.valueOf;
import static ru.andsp.ool.domain.ObjectType.values;
import ru.andsp.ool.domain.OraObject;
import ru.andsp.ool.domain.OraSchema;

/**
 *
 * @author Serg
 */
public class SchemaLoader {

    private Connection con;

    private String getFilter(ArrayList<String> types) {
        if (types.isEmpty()) {
            for (ObjectType o : values()) {
                types.add(o.name());
            }
        }
        return types.toString().replace("[", "'").replace("]", "'").replace(",", "','").replace(" ", "");
    }

    private OraSchema selectObject(ArrayList<String> types) {
        OraSchema schema = null;
        String qq = "select o.object_name, o.object_type\n"
                + "  from user_objects o\n"
                + " where o.object_type in (" + getFilter(types) + ")\n"
                + " order by o.object_type,o.object_name ";
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(
            qq    
        )) {
            schema = new OraSchema();
            while (rs.next()) {
                OraObject obj = new OraObject(rs.getString("object_name"),
                        valueOf(rs.getString("object_type")));
                schema.addObject(obj);
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return schema;
    }

    private void initMetadata() throws SQLException {
        try (Statement st = this.con.createStatement()) {
            st.execute(
                    "begin\n"
                    + "  DBMS_METADATA.SET_TRANSFORM_PARAM(DBMS_METADATA.SESSION_TRANSFORM,'STORAGE',FALSE);\n"
                    + "  DBMS_METADATA.SET_TRANSFORM_PARAM(DBMS_METADATA.SESSION_TRANSFORM,'TABLESPACE',FALSE);\n"
                    + "  DBMS_METADATA.SET_TRANSFORM_PARAM(DBMS_METADATA.SESSION_TRANSFORM,'SEGMENT_ATTRIBUTES',FALSE);\n"
                    + "  DBMS_METADATA.SET_TRANSFORM_PARAM(DBMS_METADATA.SESSION_TRANSFORM,'PRETTY',FALSE);\n"
                    + "  DBMS_METADATA.SET_TRANSFORM_PARAM(DBMS_METADATA.SESSION_TRANSFORM,'SQLTERMINATOR',TRUE);\n"
                    + "end;");
        }
    }

    private void pullSource(OraSchema schema) throws SQLException {
        this.initMetadata();
        try (CallableStatement cstmt = con.prepareCall("{? = call dbms_metadata.get_ddl(?,? )}")) {
            cstmt.registerOutParameter(1, Types.CLOB);
            for (OraObject obj : schema.getObjects()) {
                cstmt.setString(2, obj.getType().name());
                cstmt.setString(3, obj.getName());
                cstmt.execute();
                String st = cstmt.getString(1);
                obj.setSource(st.trim());
            }
        }
    }

    public OraSchema load(Connection con, ArrayList<String> types) {
        try {
            this.con = con;
            if(types==null)
                types = new ArrayList<>();
            OraSchema schema = this.selectObject(types);
            pullSource(schema);
            this.con.close();
            return schema;
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return null;
    }
}

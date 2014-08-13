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

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import ru.andsp.ool.domain.ObjectType;
import static ru.andsp.ool.domain.ObjectType.values;
import ru.andsp.ool.domain.Setting;

/**
 *
 * @author Serg
 */
public class SettingHelper {

    public static void write(Setting f, String filename) {
        try (XMLEncoder encoder = new XMLEncoder(
                new BufferedOutputStream(
                        new FileOutputStream(filename)))) {
                    encoder.writeObject(f);
                } catch (FileNotFoundException ex) {
                    System.err.println(ex);
                }
    }

    public static Setting read(String filename) {
        Setting o = null;
        try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(
                new FileInputStream(filename)))) {
            o = (Setting) decoder.readObject();
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
        return o;
    }

    public static ArrayList<String> invertTypes(ArrayList<String> types) {
        if(types==null)
            types = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();
        for (ObjectType o : values()) {
            if (types.indexOf(o.name()) == -1) {
                result.add(o.name());
            }
        }
        return result;
    }
}

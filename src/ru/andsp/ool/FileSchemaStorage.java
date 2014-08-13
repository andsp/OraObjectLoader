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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import ru.andsp.ool.domain.ObjectType;
import static ru.andsp.ool.domain.ObjectType.valueOf;
import ru.andsp.ool.domain.OraObject;
import ru.andsp.ool.domain.OraSchema;
import ru.andsp.ool.domain.SchemaStorage;

/**
 *
 * @author Serg
 */
public class FileSchemaStorage implements SchemaStorage {

    private File dir;

    /**
     * Сохранеие файла объекта
     *
     * @param file File файл скрипта
     * @param source String текст объекта
     */
    private static void saveFile(File file, String source) {
        if(source != null)
        try (Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"))) {
            out.write(source);
        } catch (IOException ex) {
            System.err.println(ex);
        }

    }

    private void initRootDirectory(String path) {
        dir = new File(path);
        if (!dir.isDirectory()) {
            System.out.println("Результирующая папка должна быть папкой!");
        }
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * Сохранение схемы в директорию
     *
     * @param schema
     * @param outDir
     * @param clearBefore
     */
    @Override
    public void save(OraSchema schema, String outDir, boolean clearBefore) {
        initRootDirectory(outDir);
        if (clearBefore) {
            clear(dir);
        }
        saveItems(schema);
    }

    /**
     * Очистка от файлов всех вложенных папок
     *
     * @param f
     */
    private void clear(File f) {
        if (f.isDirectory()) {
            if (!f.getName().toUpperCase().equals("SCRIPT")) {
                for (File c : f.listFiles()) {
                    clear(c);
                }
            }
        } else if (isTrueDirName(f)) {
            f.delete();
        }
    }

    private boolean isTrueDirName(File f) {
        try {
            String name = f.getParentFile().getName().toUpperCase();
            ObjectType o = valueOf(name);
            return (o != null);
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    private void saveItems(OraSchema schema) {
        for (OraObject object : schema.getObjects()) {
            File type;
            type = new File(dir, object.getType().name());
            type.mkdir();
            saveFile(new File(type, object.getFileName()), object.getSource());
        }
    }

}

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

public class ParamsComandLine {

    private boolean isGui = false;
    private String fileSetting;
    private final String[] args;

    private void parse() {
        System.out.println("Использование:  [-gui] [-settingFile filename]");
        System.out.println("        -gui:  запускать графический интерфейс");
        System.out.println(" -settingFile:  указывает файл с настройками выгрузки");
        int i = 0;
        String currentArg;
        while (i < args.length && args[i].startsWith("-")) {
            currentArg = args[i++];

            switch (currentArg.toLowerCase()) {
                case "-gui":
                    isGui = true;
                    System.out.println("указан параметр запустить графический интерфейс");
                    break;
                case "-settingfile":
                    if (i < args.length) {
                        fileSetting = args[i++];
                        System.out.println("указан файл с настройками:" + fileSetting);
                    } else {
                        System.out.println("для параметра -settingFile необходимо указать имя файла с настройками");
                    }   break;
            }
        }
    }

    public boolean isIsGui() {
        return isGui;
    }
    
    public boolean isEmptySetting(){
      return (fileSetting == null);
    }

    public void setIsGui(boolean isGui) {
        this.isGui = isGui;
    }

    public String getFileSetting() {
        return fileSetting;
    }

    public void setFileSetting(String fileSetting) {
        this.fileSetting = fileSetting;
    }

    public ParamsComandLine(String[] args) {
        this.args = args;
        parse();
    }
    
}

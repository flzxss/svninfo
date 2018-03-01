package svninfo;

import cn.hutool.setting.Setting;

public class Main {

    public static void main(String[] args) {
        int millis = 5000;
        int version = 55;
        String url = "svn://112.74.182.63/icessm";
        Setting setting = new Setting("svninfo.txt");
        millis = setting.getInt("millis", millis);
        version = setting.getInt("version", version);
        url = setting.getStr("url", url);
        SvnMonitor.monitor(url, version, millis);
        System.out.println("svninfo start ...");
    }

}

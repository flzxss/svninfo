package svninfo;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.core.util.StrUtil;

// http://central.maven.org/maven2/cn/hutool/hutool-all/4.0.5/hutool-all-4.0.5.jar
public class SvnMonitor {
    static int version = 0;

    public static void monitor(String url, int version, int millis) {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            ThreadUtil.sleep(millis);
            monitorInfo(url, version);
        }
    }

    public static void monitorInfo(String url, int version) {
        // int seconds=DateUtil.timeToSecond(DateUtil.format(new Date(),
        // "HH:mm:ss"));
        if (SvnMonitor.version == 0) {
            SvnMonitor.version = version;
        }
        String cmds = "svn info " + url;
        String execForStr = RuntimeUtil.execForStr(cmds);
        execForStr = StrUtil.trim(execForStr);
        execForStr = StrUtil.subAfter(execForStr, "Revision:", false);
        String part = StrUtil.subBefore(execForStr, "Node Kind:", false);
        part = part.trim();
        int verNew = 0;
        try {
            verNew = Integer.parseInt(part);
        } catch (NumberFormatException e) {
            // e.printStackTrace();
        }
        if (verNew > SvnMonitor.version) {
            SvnMonitor.version = verNew;
            System.out.println("新版本:" + verNew + "    作者:" + author(execForStr) + "    " + DateUtil.now());
        }
    }

    private static String author(String str) {
        str = StrUtil.subAfter(str, "Last Changed Author:", false);
        String part = StrUtil.subBefore(str, "Last Changed Rev:", false);
        return part.trim();
    }

}
// Path: icessm
// URL: svn://112.74.182.63/icessm
// Relative URL: ^/icessm
// Repository Root: svn://112.74.182.63
// Repository UUID: e6041ee2-9d01-d747-b01e-c59bff143e4a
// Revision: 55
// Node Kind: directory
// Last Changed Author: zs
// Last Changed Rev: 55
// Last Changed Date: 2018-03-01 14:52:13 +0800 (周四, 01 三月 2018)
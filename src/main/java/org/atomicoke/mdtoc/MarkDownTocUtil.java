package org.atomicoke.mdtoc;

import cn.hutool.core.util.URLUtil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/8/1 19:36
 */
public class MarkDownTocUtil {

    /**
     * 处理生成的锚点，去掉不能用的字符
     * <pre>
     *     demo: b.关于使用lb://server-name 语法遇到的问题 => b关于使用lbserver-name-语法遇到的问题
     *     problem: 可能还有些字符没有剔除，欢迎RP！
     *  </pre>
     *
     * @param raw 原始数据
     * @return {@link String}
     */
    public static String processAnchorpoint(String raw) {
        return URLUtil.encode(
                raw.replace(".", "")
                   .replace(" ", "-")
                   .replace("://", "")
                   .replace("。", "")
                   .replace("！", "")
                   .replace("，", ""));
    }

    /**
     * 处理前缀
     * <pre>
     *      #   =>  *
     *      ##  =>     *
     *      ...
     * </pre>
     *
     * @param raw 生
     * @return {@link String}
     */
    public static String processPrefix(String raw) {
        raw = raw + "#";
        final String[] raws = raw.split("");

        StringBuilder rest = new StringBuilder("");
        for (int i = 1; i < raws.length; i++) {
            rest.append("  ");
        }
        rest.append("* ");

        return rest.toString();
    }
}

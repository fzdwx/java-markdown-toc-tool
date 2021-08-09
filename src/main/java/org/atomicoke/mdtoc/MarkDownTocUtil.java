package org.atomicoke.mdtoc;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.URLUtil;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 一些辅助方法
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/8/1 19:36
 */
public class MarkDownTocUtil {

    static AtomicReference<String> mdRef;

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
     * 修复最后标题行打印不全
     *
     * @param titleLine 标题行
     * @return {@link String}
     */
    public static String processTitleLine(String titleLine) {
        return titleLine
                .replace("\r", "")
                .replace("\n", "");
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

        StringBuilder rest = new StringBuilder();
        for (int i = 1; i < raws.length; i++) {
            rest.append("  ");
        }
        rest.append("* ");

        return rest.toString();
    }

    /**
     * 处理原始md中不需要的部分
     */
    public static String processContent(String md) {
        mdRef = new AtomicReference<>(md);

        // 排除代码块
        excludeCodeBlock(md);

        return mdRef.get();
    }


    /**
     * 排除代码块
     */
    private static void excludeCodeBlock(String md) {
        // ``` 是成对出现的
        final AtomicInteger integer = new AtomicInteger(1);
        Arrays.stream(md.split("```")).forEach((s) -> {
            if (integer.get() % 2 == 0) {
                mdRef.set(ReUtil.delAll(ReUtil.escape(s), mdRef.get()));
            }
            integer.incrementAndGet();
        });
    }
}

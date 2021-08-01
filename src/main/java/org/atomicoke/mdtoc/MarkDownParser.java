package org.atomicoke.mdtoc;

import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * Description: markdown 文件解析器  <br>
 *
 * <pre>
 *     将md中的标题行挑出来，并封装成类似这种格式的: * [..后端整合框架](#%E5%90%8E%E7%AB%AF%E6%95%B4%E5%90%88%E6%A1%86%E6%9E%B6)
 * </pre>
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021-08-01 19:11:26
 */
public class MarkDownParser {

    public static final String pattern = "[{0}](#{1})";

    public static void process(String md) {
        parseTitleLinesFromMarkdown(md).forEach(titleLine->{
            final String[] split = titleLine.split("# ");
            System.out.println(MarkDownTocUtil.processPrefix(split[0]) + MessageFormat.format(MarkDownParser.pattern, split[1], MarkDownTocUtil.processAnchorpoint(split[1])));
        });
    }

    /**
     * 从md文件中解析出标题行
     * <pre>
     *      1.解析成功的:
     *          # 标题一
     *          ## 标题二
     *      2.失败的:
     *          #asdasd
     *          ###asdasdasd
     *  </pre>
     *
     * @param md markdown 文本片段
     * @return {@link List<String>}
     */
    static List<String> parseTitleLinesFromMarkdown(String md) {
        final List<String> list = new LinkedList<>();
        final String[] split = md.split("\n");
        for (String s : split) {
            if (s.startsWith("#")) {
                if (s.contains("# "))
                    list.add(s);
            }
        }
        return list;
    }
}

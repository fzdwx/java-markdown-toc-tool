package org.atomicoke.mdtoc;

import cn.hutool.core.io.FileUtil;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/8/1 19:14
 */
class MarkDownParserTest {

    @Test
    void parseTitleLinesFromMarkdown() {
        final String md = FileUtil.readUtf8String("..\\..\\doc\\test.md");
        final List<String> list = MarkDownParser.parseTitleLinesFromMarkdown(md);

        list.forEach(System.out::println);
    }

    @Test
    void testProcess() {
        System.out.println(MarkDownTocUtil.processPrefix(""));
        System.out.println(MarkDownTocUtil.processPrefix("#"));
        System.out.println(MarkDownTocUtil.processPrefix("##"));
        System.out.println(MarkDownTocUtil.processPrefix("###"));
        System.out.println(MarkDownTocUtil.processPrefix("####"));
        System.out.println(MarkDownTocUtil.processPrefix("#####"));
    }

}
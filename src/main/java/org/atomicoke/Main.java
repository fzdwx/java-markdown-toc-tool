package org.atomicoke;

import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpUtil;
import org.atomicoke.mdtoc.MarkDownParser;

import java.nio.charset.Charset;
import java.util.List;

/**
 * D:\Java\jdk-8\bin\java  -jar D:\Java\project\markdown-toc-tool\target\markdown-toc-tool-0.01.jar
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/8/1 18:29
 */
public class Main {

    /** dns查询的网站 */
    public static final String DNS_INQUIRE_WEBSITE = "https://tool.chinaz.com/dns/?type=1&host=raw.githubusercontent.com&ip=";

    public static void main(String[] args) {
        // final String file = HttpUtil.get("https://raw.githubusercontent.com/ekalinin/github-markdown-toc.go/master/README.md");
        final String md = HttpUtil.get("https://gitee.com/atomic-coke/like-back-end/raw/like/README.md");


        final List<String> process = MarkDownParser.process(md);
        process.forEach(Console::log);

        printCharsetDoc();
    }

    /**
     * 打印关于编码问题的文档
     */
    static void printCharsetDoc() {
        System.out.println("=======================================================================================================================================");
        System.out.println("|| Your JAVA Virtual Machine default charset:[ " + Charset.defaultCharset() + " ] (Depends on the locale and character set of the underlying operating system.)");
        System.out.println("|| If you have garbled Chinese characters, you can use the following command:");
        System.out.println("|| chcp               # View the encoding format of the current cmd");
        System.out.println("||     chcp 65001     # Set UTF-8");
        System.out.println("||     chcp 936       # Set GKB");
        System.out.println("||     chcp 437       # Set American English");
        System.out.println("=======================================================================================================================================");
    }

    /*
    chcp #查看当前cmd的编码格式
    chcp 65001   #换成utf-8代码页
    chcp 936       #换成默认的gbk
    chcp 437       #美国英语
    * */
}

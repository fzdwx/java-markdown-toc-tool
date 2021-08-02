package org.atomicoke;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;
import com.beust.jcommander.JCommander;
import org.atomicoke.mdtoc.MdTocToolArgHandlerInvocation;
import org.atomicoke.mdtoc.MdTocToolCliArg;
import org.atomicoke.mdtoc.MdTocToolProperties;
import org.yaml.snakeyaml.Yaml;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;

/**
 * D:\Java\jdk-8\bin\java  -jar D:\Java\project\markdown-toc-tool\target\markdown-toc-tool-0.01.jar
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/8/1 18:29
 */
public class MarkDownTocTool {

    public final static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DatePattern.NORM_DATETIME_MS_PATTERN);
    /** 当前项目的常量池 */
    public static final MdTocToolProperties MD_TOC_TOOL_PROPERTIES;
    /** dns查询的网站 */
    public static final String DNS_INQUIRE_WEBSITE = "https://tool.chinaz.com/dns/?type=1&host=raw.githubusercontent.com&ip=";

    // private
    private static JCommander commander;
    private static MdTocToolCliArg toolArgs;

    static {
        // 读取配置文件中的信息
        MD_TOC_TOOL_PROPERTIES = new MdTocToolProperties();
        BeanUtil.copyProperties(new Yaml().load(FileUtil.getInputStream("application.yml")), MD_TOC_TOOL_PROPERTIES);
    }

    public static void main(String[] args) {
        printCharsetDoc();

        if (ArrayUtil.isEmpty(args)) {
            seeUsage();
            return;
        }

        toolArgs = new MdTocToolCliArg();
        resolveCmd(args);
    }

    private static void resolveCmd(String[] args) {
        initArgs(toolArgs);

        try {
            commander.parse(args);
        } catch (Exception e) {
            seeUsage();
            return;
        }
        // 根据 commander解析出来的toolArgs调用对应的处理器进行处理
        MdTocToolArgHandlerInvocation.invoke(toolArgs);
    }

    // tool method======================================

    static void seeUsage() {
        System.out.println();
        if (commander == null) {
            initArgs(new MdTocToolCliArg());
        }
        commander.usage();
    }

    static void initArgs(MdTocToolCliArg arg) {
        commander = JCommander.newBuilder().addObject(arg).build();
        commander.setProgramName(MD_TOC_TOOL_PROPERTIES.name);
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
        System.out.println("||     chcp 437       # Set American English                       Author:like mailto:likelovec@gmail.com   https://github.com/likedeke");
        System.out.println("=======================================================================================================================================");
    }
}

import cn.hutool.http.HttpUtil;
import org.atomicoke.mdtoc.MarkDownParser;

/**
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021/8/1 18:29
 */
public class Main {

    /** dns查询的网站 */
    public static final String DNS_INQUIRE_WEBSITE = "https://tool.chinaz.com/dns/?type=1&host=raw.githubusercontent.com&ip=";

    public static void main(String[] args) {
        // final String file = HttpUtil.get("https://raw.githubusercontent.com/ekalinin/github-markdown-toc.go/master/README.md");
        final String md = HttpUtil.get("https://gitee.com/atomic-coke/like-back-end/raw/like/README.md");


        MarkDownParser.process(md);

    }
}

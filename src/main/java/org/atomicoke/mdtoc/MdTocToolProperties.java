package org.atomicoke.mdtoc;

import lombok.Setter;
import lombok.ToString;
import org.atomicoke.MarkDownTocTool;

import java.util.List;

/**
 * 1
 * md toc tool constants <br>
 * <pre>
 *      根据application.yml中的配置对当前类进行初始化
 *  </pre>
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021-08-02 15:41:12
 */
@Setter
@ToString
public class MdTocToolProperties {

    /** instance */
    private static MdTocToolProperties PROPERTIES;

    /** app的名字 */
    public String name;
    /** app版本 */
    public String version;
    /** 需要加载的MdTocToolArgHandler的实现类的全名称 */
    public List<String> handlerClassNames;

    /**
     * 延迟加载（lazy）
     */
    public static MdTocToolProperties getProperties() {
        if (PROPERTIES == null) {
            return MarkDownTocTool.MD_TOC_TOOL_PROPERTIES;
        }
        return PROPERTIES;
    }
}

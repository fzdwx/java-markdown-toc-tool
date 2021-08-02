package org.atomicoke.mdtoc;

import cn.hutool.core.util.ReflectUtil;
import org.atomicoke.mdtoc.handler.MdTocToolArgHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * Description: md toc tool cli 参数处理程序调用 <br>
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021-08-02 17:02:14
 */
public class MdTocToolArgHandlerInvocation {

    private final static List<MdTocToolArgHandler> handlers = new ArrayList<>();

    static {
        // load
        loadHandler();
    }

    /**
     * 调用方法
     *
     * @param mdTocToolCliArg md toc 命令行参数
     */
    public static void invoke(MdTocToolCliArg mdTocToolCliArg) {
        handlers.forEach(handler -> {
            if (handler.test(mdTocToolCliArg)) {
                handler.handler(mdTocToolCliArg);
            }
        });
    }

    /**
     * 加载 handler
     */
    static void loadHandler() {
        Optional.ofNullable(MdTocToolProperties.getProperties().handlerClassNames)
                .ifPresent((classNames) -> {
                    classNames.forEach(className -> {
                        handlers.add(ReflectUtil.newInstance(className));
                    });
                });
    }
}

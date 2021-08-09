package org.atomicoke.mdtoc.handler;

import org.atomicoke.mdtoc.MdTocToolCliArg;

import java.util.function.Predicate;

/**
 * handler的顶级父类
 * <pre>
 *      根据断言中的方法是否成功来决定是否调用handler()方法
 *  </pre>
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021-08-02 16:59:33
 * @see Predicate
 */
public abstract class AbstractMdTocToolArgHandler implements Predicate<MdTocToolCliArg> {

    /**
     * 当前handler被调用的条件
     * {@link org.atomicoke.mdtoc.MdTocToolArgHandlerInvocation#invoke(org.atomicoke.mdtoc.MdTocToolCliArg)}
     */
    @Override
    public abstract boolean test(MdTocToolCliArg mdTocToolCliArg);

    /**
     * 对被解析的MdTocToolCliArg进行处理
     *
     * @param mdTocToolCliArg md toc 命令行参数
     */
    public abstract void handler(MdTocToolCliArg mdTocToolCliArg);
}

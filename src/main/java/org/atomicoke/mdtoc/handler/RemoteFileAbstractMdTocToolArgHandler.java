package org.atomicoke.mdtoc.handler;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import org.atomicoke.mdtoc.MarkDownParser;
import org.atomicoke.mdtoc.MdTocToolCliArg;

import java.util.List;

/**
 * 针对--rf {@link MdTocToolCliArg#remoteFile}
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021-08-02 17:40:13
 * @see AbstractMdTocToolArgHandler
 */
public class RemoteFileAbstractMdTocToolArgHandler extends AbstractMdTocToolArgHandler {

    @Override
    public boolean test(MdTocToolCliArg mdTocToolCliArg) {
        return StrUtil.isNotBlank(mdTocToolCliArg.remoteFile) && StrUtil.isBlank(mdTocToolCliArg.localFile);
    }

    @Override
    public void handler(MdTocToolCliArg mdTocToolCliArg) {
        final String md = HttpUtil.get(mdTocToolCliArg.remoteFile);
        final List<String> process = MarkDownParser.process(md);
        process.forEach(System.out::println);
    }
}

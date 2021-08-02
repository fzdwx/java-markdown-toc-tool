package org.atomicoke.mdtoc.handler;

import cn.hutool.core.util.StrUtil;
import org.atomicoke.mdtoc.MdTocToolCliArg;

/**
 * Description: 针对 --lf {@link MdTocToolCliArg#localFile} <br>
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021-08-02 17:39:37
 * @see MdTocToolCliArg#localFile
 */
public class LocalFileMdTocToolArgHandler extends MdTocToolArgHandler {

    @Override
    public boolean test(MdTocToolCliArg mdTocToolCliArg) {
        return StrUtil.isNotBlank(mdTocToolCliArg.localFile) && StrUtil.isBlank(mdTocToolCliArg.remoteFile);
    }

    @Override
    public void handler(MdTocToolCliArg mdTocToolCliArg) {
        final String localFile = mdTocToolCliArg.localFile;

        System.out.println(localFile);
    }
}

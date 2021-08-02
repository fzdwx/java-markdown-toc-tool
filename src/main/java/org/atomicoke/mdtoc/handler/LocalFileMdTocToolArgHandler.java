package org.atomicoke.mdtoc.handler;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import org.atomicoke.MarkDownTocTool;
import org.atomicoke.mdtoc.MarkDownParser;
import org.atomicoke.mdtoc.MdTocToolCliArg;

import java.util.List;

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
        final String md = FileUtil.readString(mdTocToolCliArg.localFile, MarkDownTocTool.DefaultCharset);
        final List<String> process = MarkDownParser.process(md);
        process.forEach(Console::log);
    }
}

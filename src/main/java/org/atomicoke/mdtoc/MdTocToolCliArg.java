package org.atomicoke.mdtoc;

import com.beust.jcommander.Parameter;

/**
 * cli参数
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021-08-02 15:50:45
 */
public class MdTocToolCliArg {

    @Parameter(names = {"--version", "-v"})
    public boolean version;
    @Parameter(names = {"--localFile", "-lf"}, description = "解析本地的MarkDown文件 \nParse the local MarkDown file,like D:\\Java\\project\\markdown-toc-tool\\README.md")
    public String localFile;
    @Parameter(names = {"--remoteFile", "-rf"}, description = "解析远程的MarkDown文件 \nParse remote MarkDown file,like https://gitee.com/atomic-coke/like-back-end/raw/like/README.md")
    public String remoteFile;
}

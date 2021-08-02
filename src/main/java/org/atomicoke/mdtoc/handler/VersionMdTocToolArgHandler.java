package org.atomicoke.mdtoc.handler;

import org.atomicoke.mdtoc.MdTocToolCliArg;

import static org.atomicoke.MarkDownTocTool.MD_TOC_TOOL_PROPERTIES;

/**
 * Description: 针对 -v {@link MdTocToolCliArg#version}<br>
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021-08-02 17:40:47
 * @see MdTocToolArgHandler
 */
public class VersionMdTocToolArgHandler extends MdTocToolArgHandler {

    @Override
    public boolean test(MdTocToolCliArg mdTocToolCliArg) {
        return mdTocToolCliArg.version;
    }

    @Override
    public void handler(MdTocToolCliArg mdTocToolCliArg) {
        System.out.println(MD_TOC_TOOL_PROPERTIES.name + ": " + MD_TOC_TOOL_PROPERTIES.Version);
    }
}

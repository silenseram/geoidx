package com.ewmw.addr.services.gar.hierarchy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class HierarchyPath {

    @Getter @Setter
    private String path;

    public String cutLastPart() {
        List<String> parts = new LinkedList<>(Arrays.asList(path.split("\\.")));

        if (parts.size() < 1)
            return "";

        parts.remove(parts.size() - 1);

        return StringUtils.join(parts, ".");
    }
}

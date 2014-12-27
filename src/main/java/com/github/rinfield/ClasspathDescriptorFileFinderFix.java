package com.github.rinfield;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.glassfish.hk2.utilities.ClasspathDescriptorFileFinder;

public class ClasspathDescriptorFileFinderFix extends
    ClasspathDescriptorFileFinder {

    @Override
    public List<InputStream> findDescriptorFiles() throws IOException {
        final List<InputStream> res = new ArrayList<>();
        final Enumeration<URL> es = this.getClass().getClassLoader()
            .getResources("hk2-locator/default");
        while (es.hasMoreElements()) {
            res.add(es.nextElement().openStream());
        }
        return res;
    }
}

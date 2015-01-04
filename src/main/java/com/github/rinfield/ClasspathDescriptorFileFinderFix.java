package com.github.rinfield;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

import org.glassfish.hk2.utilities.ClasspathDescriptorFileFinder;

public class ClasspathDescriptorFileFinderFix extends
    ClasspathDescriptorFileFinder {

    @Override
    public List<InputStream> findDescriptorFiles() throws IOException {
        final Enumeration<URL> urls = this.getClass().getClassLoader()
            .getResources("hk2-locator/default");
        return Collections.list(urls).stream().map(x -> {
            try {
                return x.openStream();
            } catch (final IOException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }
}

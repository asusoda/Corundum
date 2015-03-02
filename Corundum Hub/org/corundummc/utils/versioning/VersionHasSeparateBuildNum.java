package org.corundummc.utils.versioning;

import java.util.Arrays;

/** Used for versioning systems like those seen in Forge's versioning where a section of the version is independent of the regular
 * format number that decides how late in development the project is.
 * 
 * @author Niadel */
public class VersionHasSeparateBuildNum extends Version {
    public int buildNum;

    public VersionHasSeparateBuildNum(String version) {
        super(version);
        buildNum = getMinorVersions()[getMinorVersions().length - 1];
        minorVersions = Arrays.copyOf(getMinorVersions(), getMinorVersions().length - 2);
    }

    public VersionHasSeparateBuildNum(byte majorVersion, int[] minorVersions, int buildNum, String[] tags) {
        super(majorVersion, minorVersions, tags);
        this.buildNum = buildNum;
    }

    public VersionHasSeparateBuildNum(byte majorVersion, int[] minorVersions, int buildNum) {
        super(majorVersion, minorVersions);
        this.buildNum = buildNum;
    }

    @Override
    public Object[] getSortPriorities() {
        return new Object[] { getMajorVersion(), getMinorVersions(), buildNum, getTags() };
    }
}

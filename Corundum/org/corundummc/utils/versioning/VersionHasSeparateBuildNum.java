package org.corundummc.utils.versioning;

import java.util.Arrays;

/**
 * Used for versioning systems like those seen in Forge's versioning where a section of the version is independent of the regular
 * format number that decides how late in development the project is.
 * @author Niadel
 */
public class VersionHasSeparateBuildNum extends Version {
    public int buildNum;

    public VersionHasSeparateBuildNum(String version) {
        super(version);
        this.buildNum = this.getMinorVersions()[this.getMinorVersions().length- 1];
        this.setMinorVersions(Arrays.copyOf(this.getMinorVersions(), this.getMinorVersions().length - 2));
    }

    public VersionHasSeparateBuildNum(byte majorVersion, int[] minorVersions, int buildNum, String[] tags) {
        super(majorVersion, minorVersions, tags);
        this.buildNum = buildNum;
    }

    public VersionHasSeparateBuildNum(byte majorVersion, int[] minorVersions, int buildNum) {
        super(majorVersion, minorVersions);
        this.buildNum = buildNum;
    }
}

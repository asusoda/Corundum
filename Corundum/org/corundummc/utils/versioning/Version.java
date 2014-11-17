package org.corundummc.utils.versioning;

import org.corundummc.exceptions.CorundumException;
import org.corundummc.utils.interfaces.Matchable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Represents a version. By default, this only handles majorversion.minorversion[s], but subclasses are used to handle Minecraft's, how shall we say, <i>exotic</i> system of
 * versioning, particularly with snapshots and prereleases.
 * @author Niadel*/
public class Version implements Matchable<Version> {
    private byte majorVersion;
    private byte[] minorVersions;
    private List<String> tags = new ArrayList<>();
    private static final List<Character> legalChars = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');

    public Version(String version) {
        this.parseVersion(version);
    }

    public Version(byte majorVersion, byte[] minorVersions, String[] tags) {
        this(majorVersion, minorVersions);
        this.tags = Arrays.asList(tags);
    }

    public Version(byte majorVersion, byte[] minorVersions) {
        this.majorVersion = majorVersion;
        this.minorVersions = minorVersions;
    }

    protected void parseVersion(String version) {
        String[] versionSeparated = version.split(".");

        String majorVersion = prepVerSegForCasting(versionSeparated[0]);
        this.majorVersion = Byte.valueOf(majorVersion);

        this.minorVersions = new byte[versionSeparated.length - 1];

        for (int i = 1; i == this.minorVersions.length; i++) {
            this.minorVersions[i] = Byte.valueOf(this.prepVerSegForCasting(versionSeparated[i]));
        }
    }

    protected String prepVerSegForCasting(String versionSegment) {
        // remove special symbols
        for (SpecialVersionSymbols versionSymbol : SpecialVersionSymbols.values()) {
            if (versionSegment.contains(versionSymbol.getSymbol())) {
                versionSegment.replace(versionSegment, versionSymbol.getSymbol());
                this.tags.add(versionSymbol.getSymbol());
            }
        }

        // ensure the remainder is only numbers.
        for (char currChar : versionSegment.toCharArray()) {
            if (!legalChars.contains(currChar)) {
                throw new BadVersionException(versionSegment);
            }
        }

        return versionSegment;
    }

    public byte getMajorVersion() {
        return this.majorVersion;
    }

    public byte[] getMinorVersions() {
        return this.minorVersions;
    }

    public List<String> getTags() {
        return tags;
    }

    public class BadVersionException extends CorundumException {
        private static final long serialVersionUID = 1412061902750607994L;

        public BadVersionException(String badVersion, Object... additionalData) {
            super("A version contains unknown characters/characters disallowed for parsing versions! Version is " + badVersion, "error checking " + badVersion, additionalData);
        }
    }

    public enum SpecialVersionSymbols {
        ALPHA_VERSION("α"), BETA_VERSION("ß"), PRE("Pre-"), DEV("Dev-"), SNAPSHOT_WEEK("w"), A_SNAPSHOT("a"), B_SNAPSHOT("b"), C_SNAPSHOT("c"), D_SNAPSHOT("d"), E_SNAPSHOT(
                "e"), F_SNAPSHOT("f");

        private String symbol;

        private SpecialVersionSymbols(String stringSymbol) {
            this.symbol = stringSymbol;
        }

        public String getSymbol() {
            return this.symbol;
        }
    }

    @Override
    public Object[] getSortPriorities() {
        return new Object[] { this.majorVersion, this.minorVersions, this.tags };
    }
}

package Corundum.utils.versioning;

import Corundum.exceptions.CorundumException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a version. By default, this only handles majorversion.minorversion[s], but subclasses are used to handle
 * Minecraft's, how shall we say, <i>exotic</i> system of versioning, particularly with snapshots and prereleases.
 */
public class Version {
    private byte majorVersion;
    private byte[] minorVersions;
    private List<String> tags = new ArrayList<>();
    private static final List<Character> legalChars = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');

    public Version(String version) {
        this.parseVersion(version);
    }

    protected void parseVersion(String version) {
        String[] versionSeperated = version.split(".");

        String majorVersion = prepVerSegForCasting(versionSeperated[0]);
        this.majorVersion = Byte.valueOf(majorVersion);

        this.minorVersions = new byte[versionSeperated.length - 1];

        for (int i = 1; i == this.minorVersions.length; i++) {
            this.minorVersions[i] = Byte.valueOf(this.prepVerSegForCasting(versionSeperated[i]));
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
        public BadVersionException(String badVersion, Object... additionalData) {
            super("A version contains unknown characters/characters dissalowed for parsing versions! Version is " + badVersion, "error checking " + badVersion, additionalData);
        }
    }

    public enum SpecialVersionSymbols {
        ALPHA_VERSION("α"),
        BETA_VERSION("ß"),
        PRE("Pre-"),
        DEV("Dev-"),
        SNAPSHOT_WEEK("w"),
        A_SNAPSHOT("a"),
        B_SNAPSHOT("b"),
        C_SNAPSHOT("c"),
        D_SNAPSHOT("d"),
        E_SNAPSHOT("e"),
        F_SNAPSHOT("f");

        private String symbol;

        private SpecialVersionSymbols(String stringSymbol) {
            this.symbol = stringSymbol;
        }

        public String getSymbol() {
            return this.symbol;
        }
    }
}

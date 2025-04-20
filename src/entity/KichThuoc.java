package entity;

public enum KichThuoc {
    S,
    M,
    L,
    D;

    public static KichThuoc fromStringIgnoreCase(String text) {
        for (KichThuoc kichThuoc : KichThuoc.values()) {
            if (kichThuoc.name().equalsIgnoreCase(text)) {
                return kichThuoc;
            }
        }
        return null;
    }
}

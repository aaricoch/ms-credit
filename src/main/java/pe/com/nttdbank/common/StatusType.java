package pe.com.nttdbank.common;

public enum StatusType {
    Inactive(0),
    Active(1);

    public final int value;

    private StatusType(int val) {
        this.value = val;
    }
}

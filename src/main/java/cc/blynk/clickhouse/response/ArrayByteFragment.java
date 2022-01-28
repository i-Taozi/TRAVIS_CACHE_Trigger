package cc.blynk.clickhouse.response;

final class ArrayByteFragment extends ByteFragment {

    private ArrayByteFragment(byte[] buf, int start, int len) {
        super(buf, start, len);
    }

    static ArrayByteFragment wrap(ByteFragment fragment) {
        return new ArrayByteFragment(fragment.buf, fragment.start, fragment.len);
    }

    @Override
    public boolean isNull() {
        // NULL
        return len == 4 && buf[start] == 'N' && buf[start + 1] == 'U' && buf[start + 2] == 'L' && buf[start + 3] == 'L';
    }

    boolean isNaN() {
        // nan
        return len == 3 && buf[start] == 'n' && buf[start + 1] == 'a' && buf[start + 2] == 'n';
    }
}

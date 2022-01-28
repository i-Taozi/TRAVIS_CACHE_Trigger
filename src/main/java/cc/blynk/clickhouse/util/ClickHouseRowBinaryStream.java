package cc.blynk.clickhouse.util;

import cc.blynk.clickhouse.settings.ClickHouseProperties;
import cc.blynk.clickhouse.util.guava.LittleEndianDataOutputStream;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Dmitry Andreev <a href="mailto:AndreevDm@yandex-team.ru"></a>
 */
public final class ClickHouseRowBinaryStream {

    private static final int U_INT8_MAX = (1 << 8) - 1;
    private static final int U_INT16_MAX = (1 << 16) - 1;
    private static final long U_INT32_MAX = (1L << 32) - 1;
    protected static final long MILLIS_IN_DAY = TimeUnit.DAYS.toMillis(1);

    private final LittleEndianDataOutputStream out;
    private final TimeZone timeZone;

    public ClickHouseRowBinaryStream(OutputStream outputStream, TimeZone timeZone, ClickHouseProperties properties) {
        this.out = new LittleEndianDataOutputStream(outputStream);
        if (properties.isUseServerTimeZoneForDates()) {
            this.timeZone = timeZone;
        } else {
            this.timeZone = TimeZone.getDefault();
        }
    }

    public void writeUnsignedLeb128(int value) throws IOException {
        if (value < 0) {
            throw new IllegalArgumentException();
        }

        int remaining = value >>> 7;
        while (remaining != 0) {
            out.write((byte) ((value & 0x7f) | 0x80));
            value = remaining;
            remaining >>>= 7;
        }
        out.write((byte) (value & 0x7f));
    }

    /**
     * Dangerous. Can only be used for rare optimizations, for example when the string is written in parts
     * without prior concatenation. The size of the string in bytes must be passed through writeUnsignedLeb128.
     * @param bytes byte array will be written into stream
     * @throws IOException in case if an I/O error occurs
     */
    public void writeBytes(byte[] bytes) throws IOException {
        out.write(bytes);
    }

    /**
     * Dangerous. Can only be used for rare optimizations, for example when the string is written in parts
     * without prior concatenation. The size of the string in bytes must be passed through writeUnsignedLeb128.
     *
     * @param b byte value
     * @throws IOException in case if an I/O error occurs
     */
    public void writeByte(byte b) throws IOException {
        out.write(b);
    }

    public void writeString(String string) throws IOException {
        Objects.requireNonNull(string);
        byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
        writeUnsignedLeb128(bytes.length);
        out.write(bytes);
    }

    private void validateInt(int value, int minValue, int maxValue, String dataType) {
        if (value < minValue || value > maxValue) {
            throw new IllegalStateException("Not a " + dataType + " value: " + value);
        }
    }

    public void writeUInt8(boolean value) throws IOException {
        out.writeByte(value ? 1 : 0);
    }

    public void writeUInt8(int value) throws IOException {
        validateInt(value, 0, U_INT8_MAX, "UInt8");
        byte unsigned = (byte) (value & 0xffL);
        out.writeByte(unsigned);
    }

    public void writeInt8(int value) throws IOException {
        validateInt(value, Byte.MIN_VALUE, Byte.MAX_VALUE, "Int8");
        out.writeByte(value);
    }

    public void writeInt8(byte value) throws IOException {
        out.writeByte(value);
    }

    public void writeInt16(int value) throws IOException {
        validateInt(value, Short.MIN_VALUE, Short.MAX_VALUE, "Int6");
        out.writeShort(value);
    }

    public void writeInt16(short value) throws IOException {
        out.writeShort(value);
    }

    public void writeUInt16(int value) throws IOException {
        validateInt(value, 0, U_INT16_MAX, "UInt16");
        short unsigned = (short) (value & 0xffffL);
        out.writeShort(unsigned);
    }

    public void writeInt32(int value) throws IOException {
        out.writeInt(value);
    }

    public void writeUInt32(long value) throws IOException {
        if (value < 0 || value > U_INT32_MAX) {
            throw new IllegalStateException("Not a UInt32 value: " + value);
        }
        int unsigned = (int) (value & 0xffffffffL);
        out.writeInt(unsigned);
    }

    public void writeInt64(long value) throws IOException {
        out.writeLong(value);
    }

    public void writeUInt64(long value) throws IOException {
        if (value < 0) {
            throw new IllegalStateException("Not a UInt64 value: " + value);
        }
        out.writeLong(value);
    }

    public void writeUInt64(BigInteger value) throws IOException {
        byte[] bytes = new byte[8];
        System.arraycopy(value.toByteArray(), 1, bytes, 0, 8);
        out.write(bytes);
    }

    public void writeDateTime(Date date) throws IOException {
        Objects.requireNonNull(date);
        writeUInt32(TimeUnit.MILLISECONDS.toSeconds(date.getTime()));
    }

    public void writeDate(Date date) throws IOException {
        Objects.requireNonNull(date);
        long localMillis = date.getTime() + timeZone.getOffset(date.getTime());
        int daysSinceEpoch = (int) (localMillis / MILLIS_IN_DAY);
        writeUInt16(daysSinceEpoch);
    }

    public void writeFloat32(float value) throws IOException {
        out.writeFloat(value);
    }

    public void writeFloat64(double value) throws IOException {
        out.writeDouble(value);
    }


    public void writeDateArray(Date[] dates) throws IOException {
        Objects.requireNonNull(dates);
        writeUnsignedLeb128(dates.length);
        for (Date date: dates) {
            writeDate(date);
        }
    }

    public void writeDateTimeArray(Date[] dates) throws IOException {
        Objects.requireNonNull(dates);
        writeUnsignedLeb128(dates.length);
        for (Date date: dates) {
            writeDateTime(date);
        }
    }

    public void writeStringArray(String[] strings) throws IOException {
        Objects.requireNonNull(strings);
        writeUnsignedLeb128(strings.length);
        for (String el : strings) {
            writeString(el);
        }
    }

    public void writeInt8Array(byte[] bytes) throws IOException {
        Objects.requireNonNull(bytes);
        writeUnsignedLeb128(bytes.length);
        for (byte b: bytes) {
            writeInt8(b);
        }
    }
    public void writeInt8Array(int[] ints) throws IOException {
        Objects.requireNonNull(ints);
        writeUnsignedLeb128(ints.length);
        for (int i: ints) {
            writeInt8(i);
        }
    }

    public void writeUInt8Array(int[] ints) throws IOException {
        Objects.requireNonNull(ints);
        writeUnsignedLeb128(ints.length);
        for (int i: ints) {
            writeUInt8(i);
        }
    }

    public void writeInt16Array(short[] shorts) throws IOException {
        Objects.requireNonNull(shorts);
        writeUnsignedLeb128(shorts.length);
        for (short s: shorts) {
            writeInt16(s);
        }
    }

    public void writeUInt16Array(int[] ints) throws IOException {
        Objects.requireNonNull(ints);
        writeUnsignedLeb128(ints.length);
        for (int i: ints) {
            writeUInt16(i);
        }
    }

    public void writeInt32Array(int[] ints) throws IOException {
        Objects.requireNonNull(ints);
        writeUnsignedLeb128(ints.length);
        for (int i: ints) {
            writeInt32(i);
        }
    }

    public void writeUInt32Array(long[] longs) throws IOException {
        Objects.requireNonNull(longs);
        writeUnsignedLeb128(longs.length);
        for (long l: longs) {
            writeUInt32(l);
        }
    }

    public void writeInt64Array(long[] longs) throws IOException {
        Objects.requireNonNull(longs);
        writeUnsignedLeb128(longs.length);
        for (long l: longs) {
            writeInt64(l);
        }
    }

    public void writeUInt64Array(long[] longs) throws IOException {
        Objects.requireNonNull(longs);
        writeUnsignedLeb128(longs.length);
        for (long l: longs) {
            writeUInt64(l);
        }
    }

    public void writeFloat32Array(float[] floats) throws IOException {
        Objects.requireNonNull(floats);
        writeUnsignedLeb128(floats.length);
        for (float f: floats) {
            writeFloat32(f);
        }
    }

    public void writeFloat64Array(double[] doubles) throws IOException {
        Objects.requireNonNull(doubles);
        writeUnsignedLeb128(doubles.length);
        for (double d: doubles) {
            writeFloat64(d);
        }
    }

    /** Write a marker indicating if value is nullable or not.
     *
     * E.g., to write Nullable(Int32):
     *
     * <pre>
     *     void writeNullableInt32(Integer value) {
     *         if (value == null) {
     *             markNextNullable(true);
     *         } else {
     *             markNextNullable(false);
     *             writeInt32(value);
     *         }
     *     }
     * </pre>
     *
     * @param isNullable if it's true, 1 will be written otherwise 0
     * @throws IOException in case if an I/O error occurs
     */
    void markNextNullable(boolean isNullable) throws IOException {
        writeByte(isNullable ? (byte) 1 : (byte) 0);
    }

    public void writeUUID(UUID uuid) throws IOException {
        Objects.requireNonNull(uuid);
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]).order(ByteOrder.LITTLE_ENDIAN);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        byte[] array = bb.array();
        this.writeBytes(array);
    }

}

/*
 * Copyright (c) 2014-2023 Archeta All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.archeta.asterix.tools;

import com.archeta.asterix.*;
import us.hebi.matlab.mat.format.Mat5;
import us.hebi.matlab.mat.types.Array;
import us.hebi.matlab.mat.types.Char;
import us.hebi.matlab.mat.types.MatlabType;
import us.hebi.matlab.mat.types.Matrix;

import java.util.Arrays;
import java.util.Comparator;

final class DataFields implements DataFieldValueConsumer {
    private static final int CATEGORY_MAX = 256;

    static DataFields[] from(final FilteredDataFields filtered, final int numRecords, final ASTERIXParser parser) {
        final FilteredDataFieldConsumer c = new FilteredDataFieldConsumer(filtered, numRecords);
        parser.forEachDataField(c);
        return c.buildDataFields();
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static final class FilteredDataFieldConsumer implements DataFieldConsumer {
        private static Array newMatrixInt(final int nbits, final boolean unsigned, final int numRecords) {
            if (nbits <= 8) {
                return Mat5.newMatrix(numRecords, 1, unsigned ? MatlabType.UInt8 : MatlabType.Int8);
            } else if (nbits <= 16) {
                return Mat5.newMatrix(numRecords, 1, unsigned ? MatlabType.UInt16 : MatlabType.Int16);
            } else if (nbits <= 32) {
                return Mat5.newMatrix(numRecords, 1, unsigned ? MatlabType.UInt32 : MatlabType.Int32);
            } else if (nbits <= 64) {
                return Mat5.newMatrix(numRecords, 1, unsigned ? MatlabType.UInt64 : MatlabType.Int64);
            } else {
                throw new InternalError("Unsupported number of bits: " + nbits);
            }
        }

        private static Array newArray(final DataField field, final int numRecords) {
            switch (field.encoding) {
                case UNSIGNED:
                case SIGNED:
                case HEX:
                case OCTAL:
                case VALUE:
                case VALUES:
                case ASCII_CHAR:
                    return newMatrixInt(field.numBits, field.unsigned, numRecords);
                case ASCII:
                case IA5:
                    return Mat5.newChar(numRecords, field.width);
                case MEASURE_SIGNED:
                case MEASURE_UNSIGNED:
                case MEASURE_GRAYCODE:
                    return Mat5.newMatrix(numRecords, 1, MatlabType.Double);
                default:
                    throw new InternalError("unsupported bits field encoding " + field.encoding);
            }
        }

        private final ImmutableLong2ObjectHashMap.Builder[] namedArraysByCategoryNumbers =
                new ImmutableLong2ObjectHashMap.Builder[CATEGORY_MAX];
        private final ImmutableLong2ObjectHashMap<String> dataFieldFullNamesByIdsMap;
        private final int numRecords;
        private int id;

        private FilteredDataFieldConsumer(final FilteredDataFields filtered, final int numRecords) {
            this.numRecords = numRecords;
            this.dataFieldFullNamesByIdsMap = filtered.dataFieldFullNamesByIdsMap;
        }

        public void accept(final DataItem item, final Subitem subitem, final Subfield subfield, final Part part, final DataField field) {
            final String dataFieldFullName = dataFieldFullNamesByIdsMap.get(field.id);
            if (null == dataFieldFullName) {
                return;
            }

            final String name = dataFieldFullName.replace('/', '_');
            final int catno = ASTERIXIds.getCategoryNumber(item.getId());
            ImmutableLong2ObjectHashMap.Builder<NamedArray> arrays = namedArraysByCategoryNumbers[catno];
            if (null == arrays) {
                arrays = new ImmutableLong2ObjectHashMap.Builder<>(dataFieldFullNamesByIdsMap.size());
                namedArraysByCategoryNumbers[catno] = arrays;
            }

            arrays.put(field.id, new NamedArray(id++, name, newArray(field, numRecords)));
        }

        private DataFields[] buildDataFields() {
            final DataFields[] dataFields = new DataFields[CATEGORY_MAX];
            for (int categoryNumber = 0; categoryNumber < CATEGORY_MAX; categoryNumber++) {
                final ImmutableLong2ObjectHashMap.Builder arrays = namedArraysByCategoryNumbers[categoryNumber];
                if (arrays != null) {
                    dataFields[categoryNumber] = new DataFields(categoryNumber, arrays.build(), numRecords);
                }
            }

            return dataFields;
        }
    }

    private final int categoryNumber;
    private final ImmutableLong2ObjectHashMap<NamedArray> arraysByDataFieldIdsMap;
    private final Matrix fspec;
    private final Matrix fspecLength;
    private int rowIndex;

    private DataFields(
            final int categoryNumber,
            final ImmutableLong2ObjectHashMap<NamedArray> arraysByDataFieldIdsMap,
            final int numRecords) {

        this.arraysByDataFieldIdsMap = arraysByDataFieldIdsMap;
        this.categoryNumber = categoryNumber;
        fspec = Mat5.newMatrix(numRecords, 1, MatlabType.UInt64);
        fspecLength = Mat5.newMatrix(numRecords, 1, MatlabType.UInt8);
    }

    void set(final int rowIndex, final long fspec, final int fspecLength) {
        this.rowIndex = rowIndex;
        this.fspec.setLong(rowIndex, fspec);
        this.fspecLength.setByte(rowIndex, (byte) fspecLength);
    }

    NamedArray[] build() {
        final NamedArrayConsumer c = new NamedArrayConsumer(arraysByDataFieldIdsMap.size());
        arraysByDataFieldIdsMap.forEach(c);
        final String prefix = "I" + ASTERIXIds.getCategoryNumberString(categoryNumber);
        final NamedArray[] a = c.build();
        final int na = a.length;
        final NamedArray[] b = new NamedArray[na + 2];
        b[0] = new NamedArray(0, prefix + "_FSPEC", fspec);
        b[1] = new NamedArray(1, prefix + "_FSPEC_LENGTH", fspecLength);
        System.arraycopy(a, 0, b, 2, na);
        return b;
    }

    public void accept(final DataItem item, final Subitem subitem, final Subfield subfield, final Part part,
                       final int repIndex, final DataField field, final boolean value) {
        // This callback is only used for FX and SPARE data fields.
    }

    public void accept(final DataItem item, final Subitem subitem, final Subfield subfield, final Part part,
                       final int repIndex, final DataField field, final byte value) {
        final NamedArray a = arraysByDataFieldIdsMap.get(field.id);
        if (a != null) {
            set((Matrix) a.array, field.numBits, value);
        }
    }

    public void accept(final DataItem item, final Subitem subitem, final Subfield subfield, final Part part,
                       final int repIndex, final DataField field, final long value) {
        final NamedArray a = arraysByDataFieldIdsMap.get(field.id);
        if (a != null) {
            set((Matrix) a.array, field.numBits, value);
        }
    }

    public void accept(final DataItem item, final Subitem subitem, final Subfield subfield, final Part part,
                       final int repIndex, final DataField field, final double value) {
        final NamedArray a = arraysByDataFieldIdsMap.get(field.id);
        if (a != null) {
            ((Matrix) a.array).setDouble(rowIndex, value);
        }
    }

    public void accept(final DataItem item, final Subitem subitem, final Subfield subfield, final Part part,
                       final int repIndex, final DataField field, final String value) {
        final NamedArray a = arraysByDataFieldIdsMap.get(field.id);
        if (a != null) {
            final Char c = (Char) a.array;
            for (int j = 0, n = value.length(); j < n; j++) {
                c.setChar(rowIndex, j, value.charAt(j));
            }
        }
    }

    public void accept(final DataItem item, final Subitem subitem, final Subfield subfield, final Part part,
                       final int repIndex, final DataField field, final BitsValue value) {
        final NamedArray a = arraysByDataFieldIdsMap.get(field.id);
        if (a != null) {
            set((Matrix) a.array, field.numBits, value.value());
        }
    }

    private void set(final Matrix m, final int nbits, final long value) {
        if (nbits <= 8) {
            m.setByte(rowIndex, (byte) value);
        } else if (nbits <= 16) {
            m.setShort(rowIndex, (short) value);
        } else if (nbits <= 32) {
            m.setInt(rowIndex, (int) value);
        } else if (nbits <= 64) {
            m.setLong(rowIndex, value);
        }
    }

    private static final class NamedArrayConsumer implements LongObjectConsumer<NamedArray> {
        private static final Comparator<NamedArray> NAMED_ARRAY_COMPARATOR = Comparator.comparingInt(o -> o.id);

        private final NamedArray[] arrays;
        private int index;

        private NamedArrayConsumer(final int sz) {
            arrays = new NamedArray[sz];
        }

        public void accept(final long id, final NamedArray a) {
            arrays[index++] = a;
        }

        private NamedArray[] build() {
            Arrays.sort(arrays, NAMED_ARRAY_COMPARATOR);
            return arrays;
        }
    }
}

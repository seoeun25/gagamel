package com.seoeun.udf;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorConverters;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.StringObjectInspector;
import org.apache.hadoop.io.Text;

public class GenericUDFHello extends GenericUDF{

    private ObjectInspectorConverters.Converter[] converters;
    private ObjectInspector[] argumentOIs;
    private ObjectInspector returnOI;
    private Text result = new Text();

    @Override
    public ObjectInspector initialize(ObjectInspector[] arguments) throws UDFArgumentException {

        if (arguments.length < 1) {
            throw new UDFArgumentLengthException("The function addhello needs at least one arguments.");
        }

        for (int i = 0; i < arguments.length; i++) {
            if (!(arguments[i] instanceof StringObjectInspector)) {
                throw new UDFArgumentTypeException(i, "Only STRING type argument is accepted but " + arguments[i].getTypeName() + " is passed");

            }
        }

        returnOI = PrimitiveObjectInspectorFactory.writableStringObjectInspector;

        converters = new ObjectInspectorConverters.Converter[arguments.length];
        for (int i = 0; i < arguments.length; i++) {
            converters[i] = ObjectInspectorConverters.getConverter(arguments[i], returnOI);
        }

        return returnOI;
    }

    @Override
    public Object evaluate(DeferredObject[] arguments) throws HiveException {
        if (arguments[0].get() == null) {
            return null;
        }

        result.clear();

        String str = converters[0].convert(arguments[0].get()).toString();

        result.set(str + "---hello");

        return result;

    }

    @Override
    public String getDisplayString(String[] children) {
        StringBuilder sb = new StringBuilder();
        sb.append("addhello (");
        for (int i = 0; i < children.length - 1; i++) {
            sb.append(children[i]).append(", ");
        }
        sb.append(children[children.length - 1]).append(")");
        return sb.toString();
    }
}

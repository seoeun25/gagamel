package com.seoeun.udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

@Description(name = "addhello",
        value = "_FUNC_(str) -  Add 'hello' to str.\n"
                + "\nExample:\n"
                + "> SELECT _FUNC_('abc') FROM src; returns 'str-hello'\n"
)
public class UDFHello extends UDF{

    public Text evaluate(Text input) {
        String a = input.toString();
        return new Text(a + "-hello");
    }
}

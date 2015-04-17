package com.seoeun.udf;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;


public class UDFHello extends UDF{

    public Text evaluate(Text input) {
        String a = input.toString();
        return new Text(a + "-hello");
    }
}

package com.seoeun.camus.kafka.common;

import com.linkedin.camus.coders.CamusWrapper;
import com.linkedin.camus.etl.IEtlKey;
import com.linkedin.camus.etl.RecordWriterProvider;
import com.linkedin.camus.etl.kafka.mapred.EtlMultiOutputFormat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.DefaultCodec;
import org.apache.hadoop.io.compress.GzipCodec;
import org.apache.hadoop.io.compress.SnappyCodec;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputCommitter;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.DataOutputStream;
import java.io.IOException;

public class ByteArrayRecordWriterProvider implements RecordWriterProvider{
    public static final String ETL_OUTPUT_RECORD_DELIMITER = "etl.output.record.delimiter";
    public static final String DEFAULT_RECORD_DELIMITER = "\n";

    protected String recordDelimiter = null;

    private String extension = "";
    private boolean isCompressed = false;
    private CompressionCodec codec = null;

    public ByteArrayRecordWriterProvider(TaskAttemptContext context) {
        Configuration conf = context.getConfiguration();

        if (recordDelimiter == null) {
            recordDelimiter = conf.get(ETL_OUTPUT_RECORD_DELIMITER, DEFAULT_RECORD_DELIMITER);
        }

        isCompressed = FileOutputFormat.getCompressOutput(context);

        if (isCompressed) {
            Class<? extends CompressionCodec> codecClass = null;
            if ("snappy".equals(EtlMultiOutputFormat.getEtlOutputCodec(context))) {
                codecClass = SnappyCodec.class;
            } else if ("gzip".equals((EtlMultiOutputFormat.getEtlOutputCodec(context)))) {
                codecClass = GzipCodec.class;
            } else {
                codecClass = DefaultCodec.class;
            }
            codec = ReflectionUtils.newInstance(codecClass, conf);
            extension = codec.getDefaultExtension();
        }
    }

    @Override
    public String getFilenameExtension() {
        return extension;
    }

    @Override
    public RecordWriter<IEtlKey, CamusWrapper> getDataRecordWriter(TaskAttemptContext context, String fileName, CamusWrapper camusWrapper, FileOutputCommitter committer) throws IOException, InterruptedException {
        // If recordDelimiter hasn't been initialized, do so now
        if (recordDelimiter == null) {
            recordDelimiter = context.getConfiguration().get(ETL_OUTPUT_RECORD_DELIMITER, DEFAULT_RECORD_DELIMITER);
        }

        // Get the filename for this RecordWriter.
        Path path =
                new Path(committer.getWorkPath(), EtlMultiOutputFormat.getUniqueFile(context, fileName, getFilenameExtension()));

        FileSystem fs = path.getFileSystem(context.getConfiguration());
        if (!isCompressed) {
            FSDataOutputStream fileOut = fs.create(path, false);
            return new ByteRecordWriter(fileOut, recordDelimiter);
        } else {
            FSDataOutputStream fileOut = fs.create(path, false);
            return new ByteRecordWriter(new DataOutputStream(codec.createOutputStream(fileOut)), recordDelimiter);
        }

    }

    protected static class ByteRecordWriter extends RecordWriter<IEtlKey, CamusWrapper> {
        private DataOutputStream out;
        private String recordDelimiter;

        public ByteRecordWriter(DataOutputStream out, String recordDelimiter) {
            this.out = out;
            this.recordDelimiter = recordDelimiter;
        }

        @Override
        public void write(IEtlKey ignore, CamusWrapper value) throws IOException {
            boolean nullValue = value == null;
            if (!nullValue) {
                String record = (String) value.getRecord() + recordDelimiter;
                out.write(record.getBytes());
            }
        }

        @Override
        public void close(TaskAttemptContext var1) throws IOException, InterruptedException{
            out.close();
        }





    }
}

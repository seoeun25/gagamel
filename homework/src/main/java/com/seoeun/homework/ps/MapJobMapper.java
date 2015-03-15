package com.seoeun.homework.ps;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.filecache.DistributedCache;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class MapJobMapper extends Mapper<LongWritable, Text, Text, Text> {

    private static HashMap<String, String[]> productMap = new HashMap<String, String[]>();
    private BufferedReader brReader;
    private String[] prodcutRow;
    private Text outputKey = new Text("");
    private Text outputValue = new Text("");

    private static String DELIMITER = ",";

    enum MYCOUNTER {
        RECORD_COUNT, FILE_EXISTS, FILE_NOT_FOUND, RECORD_INVALID, SOME_OTHER_ERROR
    }

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {

        Path[] cacheFilesLocal = DistributedCache.getLocalCacheFiles(context.getConfiguration());

        for (Path eachPath : cacheFilesLocal) {
            if (eachPath.getName().trim().equals("product.txt")) {
                context.getCounter(MYCOUNTER.FILE_EXISTS).increment(1);
                loadProductTable(eachPath, context);
            }
        }

    }

    private void loadProductTable(Path filePath, Context context) throws IOException {
        String line = "";

        try {
            brReader = new BufferedReader(new FileReader(filePath.toString()));

            while ((line = brReader.readLine()) != null) {
                String productRow[] = line.split(DELIMITER);
                if (productRow.length < 3) {
                    context.getCounter(MYCOUNTER.RECORD_INVALID).increment(1);
                } else {
                    productMap.put(productRow[0].trim(), new String[]{productRow[1], productRow[2]});
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            context.getCounter(MYCOUNTER.FILE_NOT_FOUND).increment(1);
        } catch (IOException e) {
            context.getCounter(MYCOUNTER.SOME_OTHER_ERROR).increment(1);
            e.printStackTrace();
        } finally {
            if (brReader != null) {
                brReader.close();
            }
        }
    }

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        context.getCounter(MYCOUNTER.RECORD_COUNT).increment(1);
        outputKey.set("");
        outputValue.set("");

        if (value.toString().length() > 0) {
            String purchaseRow[] = value.toString().split(DELIMITER);

            this.prodcutRow = productMap.get(purchaseRow[1].toString());
            if (prodcutRow == null) {
                context.getCounter(MYCOUNTER.SOME_OTHER_ERROR).increment(1);
                return;
            }

            StringBuilder builder = new StringBuilder();
            builder.append(purchaseRow[0] + DELIMITER);
            builder.append(purchaseRow[1] + DELIMITER);
            builder.append(prodcutRow[0] + DELIMITER);
            builder.append(prodcutRow[1] + DELIMITER);
            builder.append(purchaseRow[2]);

            outputValue.set(builder.toString());
        }
        context.write(outputKey, outputValue);
        prodcutRow = null;
    }

}

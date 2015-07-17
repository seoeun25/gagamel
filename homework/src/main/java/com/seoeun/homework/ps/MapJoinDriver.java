package com.seoeun.homework.ps;

import com.seoeun.homework.ps.MapJobMapper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.filecache.DistributedCache;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.net.URI;

public class MapJoinDriver extends Configured implements Tool {

    @Override
    public int run(String[] args) throws Exception {

        printUsage();

        if (args.length != 3) {
            System.out.printf("Two parameters are required- <product dir> <purchase dir> <output dir>\n");
            return -1;
        }

        Job job = new Job(getConf());
        Configuration conf = job.getConfiguration();
        job.setJobName("MapJoin");
        DistributedCache.addCacheFile(new URI(args[0] + "/product.txt"), conf);

        job.setJarByClass(MapJoinDriver.class);
        FileInputFormat.setInputPaths(job, new Path(args[1]));
        FileOutputFormat.setOutputPath(job, new Path(args[2]));
        job.setMapperClass(MapJobMapper.class);

        job.setNumReduceTasks(0);

        boolean success = job.waitForCompletion(true);
        return success ? 0 : 1;
    }

    private void printUsage() {
        System.out.println("  Make sure the following files exits, \n"
                + "      hdfs://${namenode}/arg[0]/product.txt"
                + "      hdfs://${namenode}/arg[1]/purchase.txt");
        System.out.println("  to excute, \n"
                + "      $ hadoop jar homework/target/gagamel-homework-0.9-SNAPSHOT.jar com.seoeun.homework.ps.MapJoinDriver /user/seoeun/homework/product /user/seoeun/homework/purchase /user/seoeun/homework/output");
    }

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new Configuration(), new MapJoinDriver(), args);
        System.exit(exitCode);
    }
}

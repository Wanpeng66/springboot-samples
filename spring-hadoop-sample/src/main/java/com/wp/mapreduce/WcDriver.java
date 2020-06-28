package com.wp.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author: wp
 * @Title: WcDriver
 * @Description:
 * @date 2020/6/28 20:53
 */
public class WcDriver {
    public static void main( String[] args ) throws IOException, ClassNotFoundException, InterruptedException {
        Job job = Job.getInstance( new Configuration(  ) );

        job.setJarByClass( WcDriver.class );
        job.setMapperClass( WcMapper.class );
        job.setReducerClass( WcReducer.class );

        job.setMapOutputKeyClass( Text.class );
        job.setMapOutputValueClass( IntWritable.class );
        job.setOutputKeyClass( Text.class );
        job.setOutputValueClass( IntWritable.class );

        FileInputFormat.addInputPath( job, new Path( args[0] ) );
        FileOutputFormat.setOutputPath( job, new Path( args[1] ) );

        boolean flag = job.waitForCompletion( true );
        System.exit( flag?0:1 );
    }

}

class WcMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    @Override
    protected void map( LongWritable key, Text value, Context context ) throws IOException, InterruptedException {
        String line = value.toString();
        String[] words = line.split( " " );
        for (String word : words) {
            context.write( new Text( word ), new IntWritable( 1 ) );
        }

    }
}

class WcReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
    @Override
    protected void reduce( Text key, Iterable<IntWritable> values, Context context ) throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable value : values) {
            sum += value.get();
        }
        context.write( key, new IntWritable( sum ) );

    }
}
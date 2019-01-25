package com.lpf.bigdata.hadoop.ch03;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;

/**
 * 路径过滤器
 * 过滤掉符合regex正则表达式的路径
 *
 * @author lipengfei
 * @create 2018-10-25 15:51
 **/
public class RegexExcludePathFilter implements PathFilter {

    private final String regex;

    public RegexExcludePathFilter(String regex) {
        this.regex = regex;
    }

    @Override
    public boolean accept(Path path) {
        return !path.toString().matches(regex);
    }
}

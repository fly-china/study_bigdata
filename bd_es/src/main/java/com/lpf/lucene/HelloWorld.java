package com.lpf.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by zx on 2017/9/12.
 * <p>
 * Lucene Index ToolBox : https://github.com/DmitryKey/luke/releases
 * <p>
 * https://segmentfault.com/a/1190000010367206
 */
public class HelloWorld {

    //    private static final String indexPath = "G:\\es_data\\lucene_data";
    private static final String indexPath = "G:\\es_data\\lucene_ik_data";


    /**
     * 往用lucene写入数据
     *
     * @throws IOException
     */
    @Test
    public void testCreate() throws IOException {
        Article article = new Article();
        article.setId(100L);
        article.setAuthor("张三");
        article.setTitle("学习大数据");
        article.setContent("学习大数据，迎娶白富美");
        article.setUrl("https://baike.baidu.com/item/%E5%A4%A7%E6%95%B0%E6%8D%AE/1356941?fr=aladdin");

        FSDirectory fsDirectory = FSDirectory.open(Paths.get(indexPath));
        //创建一个标准分词器，一个字分一次
//        Analyzer analyzer = new StandardAnalyzer();
        Analyzer analyzer = new IKAnalyzer(true);
        //写入索引的配置，设置了分词器
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        //指定了写入数据目录和配置
        IndexWriter indexWriter = new IndexWriter(fsDirectory, indexWriterConfig);
        //创建一个文档对象
        Document document = article.toDocument();
        //通过IndexWriter写入
        indexWriter.addDocument(document);
        indexWriter.close();
    }

    @Test
    public void testSearch() throws IOException, ParseException {

//        Analyzer analyzer = new StandardAnalyzer();
        Analyzer analyzer = new IKAnalyzer(true);
        DirectoryReader directoryReader = DirectoryReader.open(FSDirectory.open(Paths.get(indexPath)));
        //索引查询器
        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);

        String queryStr = "李鹏飞";
        //创建一个查询条件解析器
        QueryParser parser = new QueryParser("author", analyzer);
        //对查询条件进行解析
//        Query query = parser.parse(queryStr);

        // 根据ID查询
        Query query = LongPoint.newRangeQuery("id", 100L, 109L);

        //TermQuery将查询条件当成是一个固定的词
//        Query query = new TermQuery(new Term("author", "李鹏飞"));
        //在【索引】中进行查找
        TopDocs topDocs = indexSearcher.search(query, 10);

        //获取到查找到的文文档ID和得分
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            //从索引中查询到文档的ID，
            int doc = scoreDoc.doc;
            //在根据ID到文档中查找文档内容
            Document document = indexSearcher.doc(doc);
            //将文档转换成对应的实体类
            Article article = Article.parseArticle(document);
            System.out.println("doc=" + doc +  "---" +article);
        }

        directoryReader.close();
    }

    @Test
    public void testDelete() throws IOException, ParseException {

        Analyzer analyzer = new IKAnalyzer(true);
        FSDirectory fsDirectory = FSDirectory.open(Paths.get(indexPath));
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        IndexWriter indexWriter = new IndexWriter(fsDirectory, indexWriterConfig);

        //Term词条查找，内容必须完全匹配，不分词
//        indexWriter.deleteDocuments(new Term("title", "大数据很好玩"));

        QueryParser parser = new QueryParser("title", analyzer);
        Query query = parser.parse("大数据");

        //LongPoint是建立索引的
//        Query query = LongPoint.newRangeQuery("id", 99L, 120L);   // 范围查询
//        Query query = LongPoint.newExactQuery("id", 108L); // 精准查询

        long delNum = indexWriter.deleteDocuments(query);
        System.out.println("删除条数：" + delNum);

        indexWriter.commit();
        indexWriter.close();
    }

    /**
     * lucene的update比较特殊，update的代价太高，先删除，然后在插入
     *
     * @throws IOException
     * @throws ParseException
     */
    @Test
    public void testUpdate() throws IOException, ParseException {

        IKAnalyzer analyzer = new IKAnalyzer();
        FSDirectory fsDirectory = FSDirectory.open(Paths.get(indexPath));
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        IndexWriter indexWriter = new IndexWriter(fsDirectory, indexWriterConfig);


        Article article = new Article();
        article.setId(106L);
        article.setAuthor("大鹏");
        article.setTitle("学好大数据，要找赵老师");
        article.setContent("迎娶白富美，走上人生巅峰！！！");
        article.setUrl("http://www.edu360.cn/a111");
        Document document = article.toDocument();

        indexWriter.updateDocument(new Term("author", "老王"), document);

        indexWriter.commit();
        indexWriter.close();
    }

    /**
     * 可以从多个字段中查找
     *
     * @throws IOException
     * @throws ParseException
     */
    @Test
    public void testMultiField() throws IOException, ParseException {

        Analyzer analyzer = new IKAnalyzer(true);
        DirectoryReader directoryReader = DirectoryReader.open(FSDirectory.open(Paths.get(indexPath)));
        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);

        String[] fields = {"title", "content"};
        //多字段的查询转换器
        MultiFieldQueryParser queryParser = new MultiFieldQueryParser(fields, analyzer);
        Query query = queryParser.parse("高度可伸缩");

        TopDocs topDocs = indexSearcher.search(query, 10);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            int doc = scoreDoc.doc;
            Document document = indexSearcher.doc(doc);
            Article article = Article.parseArticle(document);
            System.out.println(article);
        }

        directoryReader.close();
    }

    /**
     * 查找全部的数据
     *
     * @throws IOException
     * @throws ParseException
     */
    @Test
    public void testMatchAll() throws IOException, ParseException {

        DirectoryReader directoryReader = DirectoryReader.open(FSDirectory.open(Paths.get(indexPath)));
        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);

        Query query = new MatchAllDocsQuery();

        TopDocs topDocs = indexSearcher.search(query, 10);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            int doc = scoreDoc.doc;
            Document document = indexSearcher.doc(doc);
            Article article = Article.parseArticle(document);
            System.out.println(article);
        }

        directoryReader.close();
    }

    /**
     * 布尔查询，可以组合多个查询条件
     *
     * @throws Exception
     */
    @Test
    public void testBooleanQuery() throws Exception {
        DirectoryReader directoryReader = DirectoryReader.open(FSDirectory.open(Paths.get(indexPath)));
        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);

        Query query1 = new TermQuery(new Term("title", "数据"));
        Query query2 = new TermQuery(new Term("content", "巅峰"));
        BooleanClause bc1 = new BooleanClause(query1, BooleanClause.Occur.MUST);
        BooleanClause bc2 = new BooleanClause(query2, BooleanClause.Occur.SHOULD);
        BooleanQuery boolQuery = new BooleanQuery.Builder().add(bc1).add(bc2).build();
        System.out.println(boolQuery);

        TopDocs topDocs = indexSearcher.search(boolQuery, 10);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            int doc = scoreDoc.doc;
            Document document = indexSearcher.doc(doc);
            Article article = Article.parseArticle(document);
            System.out.println(article);
        }

        directoryReader.close();
    }

    @Test
    public void testQueryParser() throws Exception {
        DirectoryReader directoryReader = DirectoryReader.open(FSDirectory.open(Paths.get(indexPath)));
        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);

        //创建一个QueryParser对象。参数1：默认搜索域 参数2：分析器对象。
        QueryParser queryParser = new QueryParser("title", new IKAnalyzer(true));

        //Query query = queryParser.parse("数据");
//        Query query = queryParser.parse("title:数据 AND title:老师");
        Query query = queryParser.parse("title:数据 OR title:老师");
        System.out.println(query);

        TopDocs topDocs = indexSearcher.search(query, 10);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            int doc = scoreDoc.doc;
            Document document = indexSearcher.doc(doc);
            Article article = Article.parseArticle(document);
            System.out.println(article);
        }

        directoryReader.close();
    }


    @Test
    public void testRangeQuery() throws Exception {
        String indexPath = "/Users/zx/Documents/dev/lucene/index";
        DirectoryReader directoryReader = DirectoryReader.open(FSDirectory.open(Paths.get(indexPath)));
        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);


        Query query = LongPoint.newRangeQuery("id", 107L, 108L);

        System.out.println(query);

        TopDocs topDocs = indexSearcher.search(query, 10);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            int doc = scoreDoc.doc;
            Document document = indexSearcher.doc(doc);
            Article article = Article.parseArticle(document);
            System.out.println(article);
        }

        directoryReader.close();
    }
}

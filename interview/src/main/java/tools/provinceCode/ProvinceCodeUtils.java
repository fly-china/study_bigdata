package tools.provinceCode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 省份编码转化工具
 *
 * @author lipengfei
 * @date 2019-10-24 15:46
 **/
public class ProvinceCodeUtils {

    private static final String BASE_PATH = "F:\\project_study\\study_bigdata\\interview\\src\\main\\java\\tools\\provinceCode\\";
    private static final String ORI_FILE_NAME = "ProvinceCodeData.txt";

    // (110000, '北京', 1, parentCode);
    private static final String sql_format = "(%s, '%s', %s, %s),";

    public static void main(String[] args) throws Exception {

        BufferedReader input = new BufferedReader(new FileReader(BASE_PATH + ORI_FILE_NAME));

        // 仅有省份
        List<String> oneList = new ArrayList<>();
        // 省和市
        List<String> oneOrTwoList = new ArrayList<>();
        // 省、市、县
        List<String> oneOrTwoOrThreeList = new ArrayList<>();
        List<String> sqlList = new ArrayList<>();

        String line;
        while ((line = input.readLine()) != null) {
            String[] split = line.split(",");
            String code = split[0].trim();
            String provinceOrCity = split[1].trim();
            int level = 3;
            if (code.endsWith("0000")) {
                level = 1;
            } else if (code.endsWith("00")) {
                level = 2;
            } else {
                level = 3;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(code).append("\t");
            sb.append(provinceOrCity).append("\t");
            sb.append(level);


            if (level == 1) {
                String sql = String.format(sql_format, code, provinceOrCity, level, -1);
                sqlList.add(sql);

                oneList.add(sb.toString());
                oneOrTwoList.add(sb.toString());
                oneOrTwoOrThreeList.add(sb.toString());
            } else if (level == 2) {
                String parentCode = code.substring(0, 2) + "0000";
                String sql = String.format(sql_format, code, provinceOrCity, level, parentCode);
                sqlList.add(sql);

                oneOrTwoList.add(sb.toString());
                oneOrTwoOrThreeList.add(sb.toString());
            } else {
                String parentCode = code.substring(0, 4) + "00";
                String sql = String.format(sql_format, code, provinceOrCity, level, parentCode);
                sqlList.add(sql);

                oneOrTwoOrThreeList.add(sb.toString());
            }
        }

        sqlList.forEach(System.out::println);
//        oneList.forEach(System.out::println);
//        oneOrTwoList.forEach(System.out::println);
//        oneOrTwoOrThreeList.forEach(System.out::println);

    }
}

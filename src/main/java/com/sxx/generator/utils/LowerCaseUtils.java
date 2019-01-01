package com.sxx.generator.utils;
/**
 * 驼峰命名法
 * 将字符串的首字母小写，并遇大写横杆
 * 小写字母范围是从97到122
 * ascii 码表中 a的ASCIII码值是65而A的ASCII码值是  97 两个之间的差值是 32
 * 例：GoToSchool  go-to-school
 * Author sxx
 * Date   2018/9/7
 */
public class LowerCaseUtils {
    public static String generateFtlName(String name) {
        String newName = "";
        char[] chars = name.toCharArray();
        //判断第一个字母是否小写
        if (chars[0] >= 97 && chars[0] <= 122) {
            newName = String.valueOf(chars);
        } else {
            chars[0] += 32;//大写
            newName = String.valueOf(chars);
        }

        StringBuilder sb = new StringBuilder(newName);
        int temp = 0;//定位
        for (int i = 0; i < newName.length(); i++) {
            if (Character.isUpperCase(newName.charAt(i))) {
                sb.insert(i + temp, "-");
                temp += 1;
            }
        }
        return sb.toString().toLowerCase();
    }
}

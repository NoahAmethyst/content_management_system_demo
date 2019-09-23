package amethyst.util;


/**
 * 驼峰命名转下划线命名
 * @for_example: postId->post_id
 */
public class StringUtils {
    public static String toUnderScoreCase(String string){
        if(string==null){
            return null;
        }
        for (char ch:string.toCharArray()){
            if(Character.isUpperCase(ch)){
                string=string.replace(ch+"", "_"+Character.toLowerCase(ch));
            }
        }
        return string;
    }

    /**
     * 验证输入手机号码
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isPhone(String str) {
        String regex = "^[1]+[3,9]+\\d{9}$";
        return str.matches(regex);
    }

    /**
     * 验证邮箱
     * @return 如果是符合的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isEmail(String str) {
        String regex = "^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        return str.matches(regex);
    }
}

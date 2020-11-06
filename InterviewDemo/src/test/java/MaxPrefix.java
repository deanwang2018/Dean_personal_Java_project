/**
 * @author wangdian
 * @package PACKAGE_NAME
 * @date 2020/11/6
 * @time 9:30
 */
public class MaxPrefix {
    //    一个有很多元素的字符串数组，找到这些元素的最大前缀
    public static String[] source = {"abcebabc", "abccdefabc", "aababc123defg", "abcd", "abceabc",
            "abc123abc567", "abcdefgabc", "abctrabceraw", "abcd", "abcdeee"};
    public static int sourceLen = source.length;
    public static String minLenValue;

    public static void main(String[] args) {
        // 要得出的都匹配的最大前缀
        String MaxPrefix;
        // 如果有一个字符串前缀跟最小字符串前缀一个都不匹配，直接退出
        boolean NoMatch = false;

        // 获取最小字符串的长度和下角标
        int minLenIndex = MinLenIndex(source);

        // 把大数组按最小字符串长度截取
        String[] cut = CutToMinLen(source, minLenIndex);
        int minind = minLenIndex;

        // 循环遍历数组，找出都匹配的最小前缀数
        for (int i = 0; i < sourceLen; i++) {
            int ind = 1;
//            如果存在前缀没有匹配的直接退出
            if(!(minLenValue.charAt(0)==cut[i].charAt(0))){
                NoMatch = true;
                break;
            }
//            算出最小匹配前缀的值
            for (int j = 1; j < minLenIndex; j++) {
                if(minLenValue.charAt(j)==cut[j].charAt(j)){
                    ind = ind + 1;
                }
            }

            if(minind > ind){
                minind = ind;
            }
        }

//        取最小长度字符串最小匹配前缀的值
        MaxPrefix = minLenValue.substring(0,minind);

        if (NoMatch) {
            System.out.println("no match max prefix!");
        } else {
            System.out.println("match max prefix is: " + MaxPrefix);
        }
    }

    public static int MinLenIndex(String[] arr) {
        int index = arr[0].length();
        for (int i = 0; i < sourceLen; i++) {
            if(arr[i].length() < index){
                index = arr[i].length();
                minLenValue = arr[i];
            }
        }
        return index;
    }

    public static String[] CutToMinLen(String[] beforeCut, int len) {
        String[] afterCut = new String[sourceLen];
        for (int i = 0; i < sourceLen; i++) {
            afterCut[i] = beforeCut[i].substring(0, len);
        }
        return afterCut;
    }
}

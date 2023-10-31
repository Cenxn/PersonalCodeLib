public class ConcatContain {

    /**
     * Compute the smallest number of times source is concatenated with itself
     * so that the resulting string contains target.
     * For example, For example, source "ab" concatenated 2 times "ab"+"ab"+"ab" into "ababab"
     * contains target "baba".
     * @param source a non-empty string to be concatenated.
     * @param target a non-empty string that can be contained in repeatedly concatenated source.
     * @return the smallest number of times of the concatenation.
     */
    public static int concatContain(String source, String target) {
		if (source.contains(target))return 0; //如果包括直接返回
        int sl = source.length(), tl = target.length();
        char[] s = source.toCharArray(), t = target.toCharArray();
        for(int i=0;i<sl;i++)  //检查source之中有没有target包含的字符，没有就报错
            if(s[i] == t[0]) break;
            else if((i == sl-1)&&s[i]!=t[0])
                return -1;
        int res = Integer.MAX_VALUE, i = 0, index = 0;
        for(i=0;i<sl;i++){
            if(s[i] == t[0]){  //检测到有和target第一个字符匹配，开始对target扫描
                for(index = 0;index < tl; index++)
                    if(t[index] != s[(i + index) % sl])
                        break; //因为i没动，而且i还要用来计算res，所以直接把target和soure索引加起来求余
                if(index == tl) //index等于target长度，确定target已经被扫描完了，计算res
                    res = ((i + index - 1)/sl) < res?((i + index - 1)/sl):res;
            }//第i开始的连续的j个字符，所以-1，如果不-1测试会一直出错
        }
        return (i == sl && res == Integer.MAX_VALUE)?-1:res;//条件满足说明到i结束遍历也没有成功扫完target
    }

    
}
package common.util;

import org.htmlparser.Parser;
import org.htmlparser.visitors.TextExtractingVisitor;

/**
 * 开发者: wangxiang
 * 邮件地址: wx_iter@163.com
 * 日期: 2017/9/30.
 */
public class HtmlTextUtil{

    public static String getText(String str){
        String txt =  "";
        try {
            Parser parser = new Parser(str);
            TextExtractingVisitor visitor = new TextExtractingVisitor();
            parser.visitAllNodesWith(visitor);
            txt = visitor.getExtractedText();
        }catch (Exception e){
            e.printStackTrace();
        }
        return txt;

    }

}

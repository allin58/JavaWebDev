package markettag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.Locale;

public class TimeTag extends TagSupport {

    public int doStartTag() throws JspException {
        GregorianCalendar gc = new GregorianCalendar();
        String time = "<hr>Time : <b> " + gc.getTime() + " </b><hr/>";



        try {
            JspWriter out = pageContext.getOut();
            out.write(time);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}

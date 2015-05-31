
package validator;

import java.io.IOException;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

public class MyErrorHandler implements ErrorHandler {
    private boolean valid = true;
    private Logger logger;

    public boolean isValid() {
        return valid;
    }
  
    public MyErrorHandler(String log) throws IOException {
    //создание регистратора ошибок 
        logger = Logger.getLogger("error");
        logger.addAppender(new FileAppender(new SimpleLayout(), log));
    }
 
    @Override
    public void warning(SAXParseException e) {
        logger.warn(getLineAddress(e) + "-" + e.getMessage());
    }
    
    @Override
    public void error(SAXParseException e) {
        valid = false;
        logger.error(getLineAddress(e) + " - " + e.getMessage());
    }
 
    @Override
    public void fatalError(SAXParseException e) {
        valid = false;
        logger.fatal(getLineAddress(e) + " - " + e.getMessage());
    }
 
    private String getLineAddress(SAXParseException e) {
   //определение строки и столбца ошибки
        return e.getLineNumber() + " : " + e.getColumnNumber();
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExternalJavaFiles;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author farzeen
 */
public class EmailValidator {
    
    private Pattern pattern;
    private Matcher matcher;

private static final String EMAIL_PATTERN = "(\\W|^)[\\w.+\\-]*@(gmail|yahoo|ymail|outlook|google)\\.(com|edu|org)(\\W|$)";

public EmailValidator() {
    pattern = Pattern.compile(EMAIL_PATTERN);
}
public boolean validate(final String hex) {

    matcher = pattern.matcher(hex);
    return matcher.matches();

}
}
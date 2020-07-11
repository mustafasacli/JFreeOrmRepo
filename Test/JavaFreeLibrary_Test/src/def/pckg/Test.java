/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.pckg;

import jv.freeorm.util.*;
import static java.lang.System.out;

/**
 *
 * @author Krkt
 */
public class Test {

    public static void main(String[] args) {
        String text = "this is mey text;;1;;";
        String trm_end = StringUtil.trimEnd(text, ';');
        out.println(trm_end);
        text = "--2--Hi Guys, I am a pogram.";
        String trm_strt = StringUtil.trimStart(text, '-');
        out.println(trm_strt);

    }

}

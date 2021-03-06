/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright (c) 1997-2016 Oracle and/or its affiliates. All rights reserved.
 * 
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 * 
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 * 
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 * 
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
package test.java.com.sun.faces.test.javaee8.localizedResources;

import com.gargoylesoftware.htmlunit.CollectingAlertHandler;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class Issue4082IT {

    private String webUrl;
    private WebClient webClient;

    private static final ArrayList<String> expectedAlerts = new ArrayList<String>() {
        {
            add("J'ai gagné Roland Garros ...(resources/fr/1_0/css/js/rafa.js/1_2.js)");
            add("I won Wimbledon ...(resources/en/1_0/css/js/rafa.js/1_1.js)");
            add("I won US Open ...(resources/us/1_0/css/js/rafa.js/1_0.js)");
            add("I won Australian Open ...(resources/au/1_0/css/js/rafa.js/1_0.js)");
        }
    };

    @Before
    public void setUp() {
        webUrl = System.getProperty("integration.url");
        webClient = new WebClient();
    }

    @After
    public void tearDown() {
        webClient.closeAllWindows();
    }

    public Issue4082IT() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testLocalizedResources() throws Exception {

        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(true);

        // test locale="en" (default)
        final List collectedAlertsEN = new ArrayList();
        webClient.setAlertHandler(new CollectingAlertHandler(collectedAlertsEN));

        HtmlPage pageEN = webClient.getPage(webUrl + "faces/Issue4082.xhtml");

        Object alertEN = collectedAlertsEN.get(0);
        validateAlert(alertEN, 1);

        // test locale="fr"
        final List collectedAlertsFR = new ArrayList();
        webClient.setAlertHandler(new CollectingAlertHandler(collectedAlertsFR));

        HtmlSubmitInput submitFR = (HtmlSubmitInput) pageEN.getHtmlElementById("atp:rg");
        HtmlPage pageFR = submitFR.click();

        Object alertFR = collectedAlertsFR.get(0);
        validateAlert(alertFR, 0);

        // test locale="au"
        final List collectedAlertsAU = new ArrayList();
        webClient.setAlertHandler(new CollectingAlertHandler(collectedAlertsAU));

        HtmlSubmitInput submitAU = (HtmlSubmitInput) pageFR.getHtmlElementById("atp:au");
        HtmlPage pageAU = submitAU.click();

        Object alertAU = collectedAlertsAU.get(0);
        validateAlert(alertAU, 3);

        // test locale="us"
        final List collectedAlertsUS = new ArrayList();
        webClient.setAlertHandler(new CollectingAlertHandler(collectedAlertsUS));

        HtmlSubmitInput submitUS = (HtmlSubmitInput) pageAU.getHtmlElementById("atp:us");
        HtmlPage pageUS = submitUS.click();

        Object alertUS = collectedAlertsUS.get(0);
        validateAlert(alertUS, 2);

        // test locale="en" from submit
        final List collectedAlertsEN2 = new ArrayList();
        webClient.setAlertHandler(new CollectingAlertHandler(collectedAlertsEN2));

        HtmlSubmitInput submitEN2 = (HtmlSubmitInput) pageUS.getHtmlElementById("atp:wo");
        submitEN2.click();

        Object alertEN2 = collectedAlertsEN2.get(0);
        validateAlert(alertEN2, 1);
    }

    private static void validateAlert(Object alert, int pos) {
        if (alert != null) {
            assertEquals(alert, expectedAlerts.get(pos));
        } else {
            fail("No alerts detected, so no JavaScript resource was loaded");
        }
    }
}

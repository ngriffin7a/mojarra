/*
* Copyright 2004 The Apache Software Foundation
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package webtiersample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * Implicit object that ${Color} resolves to.
 *
 * @author Mark Roth
 */
public class ColorImplicitObject {

    /**
     * Set of colors by name
     */
    private static HashMap<String,ColorRGB> colorNames = null;

    /**
     * Returns a color from an HTML-style hex String, e.g. #f0f0f0
     */
    public static ColorRGB fromHex(String hex) {
        return fromColor(java.awt.Color.decode(hex));
    }

    /**
     * Returns a color from a java.awt.Color object.
     */
    public static ColorRGB fromColor(java.awt.Color color) {
        return new ColorRGB(color.getRed(), color.getGreen(), color.getBlue());
    }

    /**
     * Returns a color from a name.  Uses the resource rgb.txt to load color
     * names.
     */
    public static ColorRGB fromName(String name) {
        if (colorNames == null) {
            loadColorNames();
        }
        return colorNames.get(name);
    }

    public String toString() {
        return "Color Implicit Object";
    }

    /**
     * Package-scope method to get list of all color names
     */
    static Iterator<String> colorNameIterator() {
        if (colorNames == null) {
            loadColorNames();
        }
        return colorNames.keySet().iterator();
    }

    /**
     * Loads colors from resource rgb.txt and converts them to instances of
     * ColorRGB.
     */
    private synchronized static void loadColorNames() {
        if (colorNames == null) {
            colorNames = new HashMap<String, ColorRGB>();
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(
                    ColorImplicitObject.class.getResourceAsStream(
                        "/webtiersample/rgb.txt")));
                String line;
                while ((line = in.readLine()) != null) {
                    if (!line.startsWith("!")) {
                        String colorText = line.substring(0, 12);
                        String colorName = line.substring(12).trim();
                        StringTokenizer st = new StringTokenizer(
                            colorText, " ");
                        int red = Integer.parseInt(st.nextToken().trim());
                        int green = Integer.parseInt(st.nextToken().trim());
                        int blue = Integer.parseInt(st.nextToken().trim());
                        colorNames.put(colorName, new ColorRGB(red, green,
                                                               blue));
                    }
                }
                in.close();
            }
            catch (IOException e) {
                throw new RuntimeException("Could not load rgb.txt", e);
            }
        }
    }

    /**
     * Color whose red property has been specified (e.g. ${Color[100]})
     */
    public static class ColorR {
        private int red;

        public ColorR(int red) {
            // Handle low or high values robustly.
            if (red < 0) {
                red = 0;
            }
            if (red > 255) {
                red = 255;
            }
            this.red = red;
        }

        public int getRed() {
            return red;
        }

        public void setRed(int r) {
            red = r;
        }

        public String toString() {
            return "Color(" + red + ", ?, ?)";
        }
    }

    /**
     * Color whose red and green properties have been specified (e.g.
     * ${Color[100][150]})
     */
    public static class ColorRG
        extends ColorR {
        private int green;

        public ColorRG(int red, int green) {
            super(red);
            // Handle low or high values robustly.
            if (green < 0) {
                green = 0;
            }
            if (green > 255) {
                green = 255;
            }
            this.green = green;
        }

        public int getGreen() {
            return green;
        }

        public void setGreen(int g) {
            green = g;
        }

        public String toString() {
            return "Color(" + getRed() + ", " + green + ", ?)";
        }
    }

    public static class ColorRGB
        extends ColorRG {
        private int blue;

        public ColorRGB(int red, int green, int blue) {
            super(red, green);
            // Handle low or high values robustly.
            if (blue < 0) {
                blue = 0;
            }
            if (blue > 255) {
                blue = 255;
            }
            this.blue = blue;
        }

        public int getBlue() {
            return blue;
        }

        public void setBlue(int b) {
            blue = b;
        }

        public java.awt.Color getColor() {
            return new java.awt.Color(getRed(), getGreen(), getBlue());
        }

        public ColorRGB getDarker() {
            java.awt.Color darkerColor = getColor().darker();
            return fromColor(darkerColor);
        }

        public ColorRGB getBrighter() {
            java.awt.Color brighterColor = getColor().brighter();
            return fromColor(brighterColor);
        }

        public String getHex() {
            return "#" + toHex(getRed()) + toHex(getGreen()) +
                   toHex(getBlue());
        }

        private String toHex(int i) {
            String result;
            if (i < 16) {
                result = "0" + Integer.toHexString(i);
            } else {
                result = Integer.toHexString(i);
            }
            return result;
        }

        public String toString() {
            return "Color(" + getRed() + ", " + getGreen() + ", " + blue + ")";
        }
    }
}

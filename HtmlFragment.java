/**
 * A single line of HTML to be rendered by a {@link TextWindow}, containing
 * text, font and color. In general, do not create these directly; a
 * {@link HtmlPrinter} will create and render them.
 *
 * @author Ofek Gila
 * @author Saagar Jha
 * @since October 20th, 2016
 */

import java.awt.Font;
import java.awt.Color;
import java.awt.FontMetrics;
import javax.swing.JComponent;

public class HtmlFragment implements HtmlComponent {

	private String text;
	private Font font;
	private Color color;
	private FontMetrics fontMetrics;

	/**
	 * HtmlFragment constructor with all the necessary values
	 * @param  text      the text to display
	 * @param  font      the {@link Font} to use
	 * @param  color     the {@link Color} to use
	 * @param  component any old {@link JComponent} to get font metrics from
	 */
	public HtmlFragment(String text, Font font, Color color, JComponent component) {
		this.text = text;
		this.font = font;
		this.color = color;
		fontMetrics = component.getFontMetrics(font);
	}

	/**
	 * Appends text to this line
	 * @param text String of text to append
	 */
	public void append(String text) {
		this.text += text;
	}

	/**
	 * Gets the text of this line
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Gets the font of this line
	 * @return the {@link Font}
	 */
	public Font getFont() {
		return font;
	}

	/**
	 * Gets the color of this line
	 * @return the {@link Color}
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Gets the line height of this line
	 * @return the height of this line
	 */
	public int getHtmlComponentHeight() {
		return fontMetrics.getHeight();
	}

	/**
	 * Gets the line width of this line
	 * @return the width of this line
	 */
	public int getHtmlComponentWidth() {
		return fontMetrics.stringWidth(text);
	}

	/**
	 * Gets how high below the text is offset
	 * @return the ascent
	 */
	public int getAscent() {
		return fontMetrics.getAscent();
	}
}